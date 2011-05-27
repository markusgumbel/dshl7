/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.hl7.hibernate.Persistence;
import org.hl7.hpath.Expression;
import org.hl7.merger.MergerUsingCache;
import org.hl7.meta.MessageType;
import org.hl7.meta.MessageTypeLoader;
import org.hl7.rim.RimObject;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.MessageContentHandler;

/** A simple HL7 message processor servlet.
		
  Processes wrapper-less message, where the message type is sent in a request attribute and we are right in the payload.

	No output is being produced.
	
  @author: Gunther Schadow
*/
public class MessageServlet extends HttpServlet implements ApplicationContext {
  private Class _readerClass = null;
	private MessageTypeLoader<MessageType> _messageTypeLoader = null;
	private MessageType _fixedMessageType = null;
	private Expression _hpathExpression = null;
	private boolean _useHibernate = false;
	private boolean _saveThroughHibernate = false;
	private boolean _emptyResponse = false;
	private Map<String, Object> _settings;

	protected static final Logger LOGGER = Logger.getLogger("org.hl7.util.MessageServlet");

	public Object getSetting(String name) { 
		return 	_settings.get(name);
	}
	public <T> T getSetting(String name, T defaultValue) { 
		T result = (T)_settings.get(name);
		if(result == null)
			return defaultValue;
		else
			return result;
	}
	public void setSetting(String name, Object value) { 
		_settings.put(name, value);
	}
        Persistence _persistence = null;
	public Persistence getPersistence() { 
	    if(_persistence == null)
		_persistence = Persistence.instance().spawn();
	    return _persistence;
	}
	public void closePersistence() { 
	    if(_persistence != null) {
		_persistence.close();
		_persistence = null;
	    }
	}

  /** Overrides the init method to set initialization parameters
      and do per-servlet instance setup.
  */
  public void init() {
		_settings = new HashMap(System.getProperties());
		Enumeration<String> paramNameEnum = getInitParameterNames();
		while(paramNameEnum.hasMoreElements()) {
			String paramName = paramNameEnum.nextElement();
			String paramValue = (String)getInitParameter(paramName);
			_settings.put(paramName, paramValue);
		}

		String messageTypeLoaderClassName = getInitParameter("messageTypeLoader");
		try {
			Class messageTypeLoaderClass = org.hl7.meta.mif.MessageTypeLoaderAdapter.class;
			if(messageTypeLoaderClassName != null)
				messageTypeLoaderClass = Class.forName(messageTypeLoaderClassName);
			
			try {
				_messageTypeLoader = (MessageTypeLoader)messageTypeLoaderClass.getMethod("getInstance").invoke(null, (Object[])null);
			} catch(NoSuchMethodException ex) { 
				try {
					_messageTypeLoader = (MessageTypeLoader)messageTypeLoaderClass.getMethod("instance").invoke(null, (Object[])null);
				} catch(NoSuchMethodException ex2) { 
					_messageTypeLoader = (MessageTypeLoader)messageTypeLoaderClass.newInstance();
				}
			}			
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}

		String fixedMessageTypeName = getInitParameter("HL7MessageType");
		if(fixedMessageTypeName != null) {
			try {
				_fixedMessageType = _messageTypeLoader.loadMessageType(fixedMessageTypeName);
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}

		String hpathExpressionString = getInitParameter("HL7HPathExpression");
		if(hpathExpressionString != null) {
			try {
				_hpathExpression = Expression.valueOf(hpathExpressionString);
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}

		String useHibernateString = getInitParameter("org.hl7.merger.useHibernate");
		if(useHibernateString != null)
			_useHibernate = Boolean.parseBoolean(useHibernateString);

		String saveThroughHibernateString = getInitParameter("SaveThroughHibernate");
		if(saveThroughHibernateString != null)
			_saveThroughHibernate = Boolean.parseBoolean(saveThroughHibernateString);

		String emptyResponseString = getInitParameter("EmptyResponse");
		if(emptyResponseString != null)
			_emptyResponse = Boolean.parseBoolean(emptyResponseString);

		String readerClassName = getInitParameter("reader");
		if(readerClassName != null) {
			try {
				_readerClass = Class.forName(readerClassName);
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
  }
	
  public String getServletInfo() {
    return getClass().getName();
  }
	
  /** Responds to HTTP/POST requests. See the superclass' definition
      of these for general information. This servlet only handles POST requests.

      <p>We either process for a fixedMessageType, which would have been set with the initialization parameter HL7MessageType.
      If no such initialization parameter has been configured, the servlet obeys the request header HL7MessageType.

			<p>The configuration parameters (settings for the mergers, use hibernate, caching etc.) would have been set by initialization parameters.

			<p>It can be configured by initialization parameter if the object read should be saved through hibernate.

			<p>The return data will at the present time just be the data rebuilt after all parsing and saving is completed.

			<p>If an HL7HPathExpression string has been configured, that is used to pull out of the data which is being returned.

      @param request  the HTTP request object
      @param response the HTTP response object 
	*/
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
			MessageType messageType = _fixedMessageType;
			if(messageType == null) {
				String messageTypeName = request.getHeader("HL7MessageType");
				messageType = _messageTypeLoader.loadMessageType(messageTypeName);
			}
			
			//System.err.println("EFFECTIVE: " + request.getRequestURI() + ": " + _settings);
			MergerUsingCache.clearObjectCache();
			MergerUsingCache.clearQueryCache();
			
			LOGGER.info("request: " + request.getRequestURI());

			Object inputObject = MessageContentHandler.parseMessage(this, request.getInputStream(), messageType);

			LOGGER.finer("request: " + request.getRequestURI() + " ok");

			if(_useHibernate && _saveThroughHibernate) {
				LOGGER.info("request: " + request.getRequestURI() + " saving");
			  getPersistence().save(inputObject);
			  getPersistence().commit();
			} else {
				LOGGER.finer("request: " + request.getRequestURI() + " saving");
			}

			Object outputObject = null;
			
			if(!_emptyResponse) {
				if(_hpathExpression == null) 
					outputObject = inputObject;
				else {
					Iterator iter = _hpathExpression.evaluate(inputObject).iterator();
					if(iter.hasNext())
						outputObject = iter.next();
					else
						outputObject = null;
				}
			}

			LOGGER.finer("response: " + request.getRequestURI() + " begin");
			response.setContentType("text/xml");
			response.setStatus(HttpServletResponse.SC_OK);

			if(outputObject != null) {
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				RimGraphXMLSpeaker speaker = new RimGraphXMLSpeaker();
				Source source = new SAXSource(speaker, new RimGraphXMLSpeaker.InputSource((RimObject) outputObject, messageType.getRootClass()));
				transformer.transform(source, new StreamResult(response.getOutputStream()));
			} else {
				new java.io.PrintStream(response.getOutputStream()).println("<empty/>");
			}

			LOGGER.info("response: " + request.getRequestURI() + " sent");

    } catch(Throwable x) {
    	//System.out.println("SHOULD NOT BE USING PERSISTENCE IN STATIC CONTEXT!");
    	// FIXME: We shouldn't be using Persistence in a static context?
			getPersistence().rollback();
			response.setContentType("text/xml");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("<error msg=\"" + x.getMessage() +  "\"/>");
			LOGGER.throwing(this.getClass().getName(), "doPost", x);

			x.printStackTrace();
      throw new ServletException(x);
    } finally {
	closePersistence();
		}
  }
}

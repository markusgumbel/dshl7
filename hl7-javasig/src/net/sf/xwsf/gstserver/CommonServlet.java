/*  XSLT Web Services Framework (XWSF)
 *
 *  Copyright (C) 2002-2007, Regenstrief Institute. All rights reserved.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Written and maintained by Gunther Schadow <gschadow@regenstrief.org>
 *  Regenstrief Institute for Health Care
 *  410 W 10th Street, Indianapolis, IN 46202, USA.
 *
 * $Id: CommonServlet.java 12230 2007-12-26 21:07:35Z gschadow $
 */
// FIXME: move to package xwsf toplevel
package net.sf.xwsf.gstserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FilterInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.regenstrief.xhl7.XMLWriter;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
	 <p>This is the main servlet class for the Generic SOAP Transform
	 Server, a web services framework that mainly uses XSLT to
	 transform SOAP requests into responses.</p>

	 <p>The design prinicples are</p>
	 <ol>
	 <li>Keep most of the XML processing in the XSLT layer</li>
	 <li>Need a general SOAP-fault response to send in case the
	 transform call throws an exception.</li>
	 <li>The XSLT transform is fixed per servlet initialization
	 parameter</li>
	 <li>When XSLT transform results in a SOAP-Fault, we must
	 catch that and setStatus accordingly.</li>
	 <li>Handling SOAP requests or non-SOAP requests, even
	 HTTP GET requests.</li>
	 <li>Leave the decision what content-type to return up to the
	 transform.</li>
	 <li>Catch runtime messages as SOAP-faults</li>
	 <li>Use SAXON for now, but allow others</li>
	 </ol>

	 @author: Gunther Schadow
	 @version: $Id: CommonServlet.java 12230 2007-12-26 21:07:35Z gschadow $
*/
public class CommonServlet extends HttpServlet {
  private String _transformerFactoryName;
  private String _transformName;
  private String _exceptionLocation;
  private boolean _cacheTransform = true;
  private TransformerFactory _transformerFactory;
  private Templates _templates = null;
  private String _defaultInput = "/empty.xsl";
  private Class _readerClass = null;
  private Class _writerClass = null;

  public String GST_PREFIX="{http://xwsf.sf.net/gstserver/}";

  public CommonServlet() {
    System.err.println(this.toString() + " created in thread " + Thread.currentThread());
  }

  /** Overrides the init method to set initialization parameters
      and do per-servlet instance setup.
  */
  public void init() {
    System.err.println(this.toString() + " initialized in thread " + Thread.currentThread() + " as " + getServletName());
		
    try {
      this._transformerFactoryName = getInitParameter("transformerFactory");
      this._transformName = getInitParameter("transform");
      this._cacheTransform = Boolean.getBoolean(getInitParameter("cacheTransform"));
      this._exceptionLocation = getInitParameter("exceptionLocation");
      this._defaultInput = getInitParameter("defaultInput");
			
      this._transformerFactory = (TransformerFactory)Class.forName(this._transformerFactoryName).newInstance();
      this._standardURIResolver = this._transformerFactory.getURIResolver();
      this._transformerFactory.setURIResolver(_uriResolver);
			
      /*
				StreamSource tmp =
				(StreamSource)this._uriResolver.resolve(this._transformName,null);
				System.err.println("tmp: " + tmp.getSystemId() + " : " + tmp);
				InputStream is = tmp.getInputStream();
				for(int c=is.read(); c!=-1; c=is.read()) {
				System.err.write(c);
				}
      */
			
      String readerClassName = getInitParameter("reader");
      if(readerClassName != null) {
				System.err.println("reader " + readerClassName);
				try {
					_readerClass = Class.forName(readerClassName);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
      }
			
      String writerClassName = getInitParameter("writer");
      if(writerClassName != null) {
				System.err.println("writer " + writerClassName);
				try {
					_writerClass = Class.forName(writerClassName);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
      }
			
      /* keep this for a while until we tested as a WAR file

      java.util.Iterator paths = getServletContext().getResourcePaths("/").iterator();
      while(paths.hasNext()) {
			System.err.println(paths.next());
      }
			
      URL transformURL = getServletContext().getResource(this._transformName);
      System.err.println("transform:"+this._transformName+"->"+transformURL);
      InputStream is = transformURL.openStream();
      for(int c=is.read(); c!=-1; c=is.read()) {
			System.err.write(c);
      }
			
      System.err.println("transform: "+this._transformName);
      InputStream is
			= getServletContext().getResourceAsStream(this._transformName);
      for(int c=is.read(); c!=-1; c=is.read()) {
			System.err.write(c);
      }
      */
			
      if(this._cacheTransform) {
				this._templates = getTemplates();
      }
    } catch(Exception x) {
      throw new RuntimeException(x);
    }
  }
	
  public String getServletInfo() {
    return "Generic SOAP Transform Server - " + this._transformName;
  }
	
  /** Responds to HTTP/POST requests. See the superclass' definition
      of these for general information.

      <p>In POST requests, the XSLT transform is invoked on the request
      message. The request, response, and session objects, plus all
      request parameters and attributes, are passed to the transform
      as parameters (in separate namespaces).

      <p>What exactly gets passed to the XSLT depends on what media
      type the request is. We can distinguish the following cases:

      <p>text/xml    - handled as feeding the text to the transform
      <p>text/plain  - need to escape XML special character and feed
			text to transform
      <p>application/x-www-form-urlencoded
			- need to parse the URL encoded form to give
			parameters to transform (just like GET)
      <p>multipart/form-data
			- parse MIME multipart into XML SAX events and
			pass those into the transform
      <p>application/x-hl7
			- parse into lazy-structured HL7 format using the
			HL7XMLReader.

      <p>Only text/xml is handled at this time. 

      <p>HL7 can be added at any time. Will require the
      org.regenstrief.xhl7.HL7XMLReader class which I maintain in a
      separate project.

      <p>A special case for text/xml is a SOAP request. The jury is still
      out whether passing the whole SOAP request to the XSLT is the
      preferred way or if the SOAP header ought to be handled in Java
      and only the SOAP content be passed into XSLT. For now I try to
      put all XML handling into the XSLT.

      <p>Another special case is for servlet called as fault handlers.
      If a request attribute "javax.servlet.error.servlet_name" is
      set, we will not attemt to open the input stream but supply
      the default XML stream instead.

      @param request  the HTTP request object
      @param response the HTTP response object */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      // if sOAPAction is null, we have a free-form HTTP request
      String sOAPAction = request.getHeader("SOAPAction");
			
      Templates templates = getTemplates();
			
      // create a new transformer working context
      Transformer transformer = templates.newTransformer();
      transformer.setURIResolver(this._uriResolver);
			
      // set the content type
      String contentType = transformer.getOutputProperty(OutputKeys.MEDIA_TYPE);
      if(contentType==null) contentType = "text/xml";
      System.err.println("contentType " + contentType + "\n" + transformer.getOutputProperties());
      response.setContentType(contentType);
			
      setTransformerParameters(transformer,request,response);

      InputStream is = null;
      if(request.getAttribute("javax.servlet.error.servlet_name") == null)
				is = request.getInputStream();
			
      Source source = getSource(is);
      source.setSystemId(request.getRequestURI()+"/POSTED-DATA");
			
      Result result = getResult(response.getOutputStream());
      result.setSystemId(request.getRequestURI()+"/RESPONSE");
			
      try {
				transformer.transform(source, result);
      } catch(net.sf.saxon.instruct.TerminationException x) { }
      
    } catch(Exception x) {
      throw new ServletException(x);
    }
  }

  /** Set all init-params, request attributes and request parameters
      to be available to the transformer under their not in any
      special way qualified names. Because of possible name conflicts
      and because of potential risks, init-params cannot be overridden
      by request attributes or parameters and request attributes cannot
      be overridden by request parameters.
  */
  void setTransformerParameters(Transformer transformer,
																HttpServletRequest request,
																HttpServletResponse response) {

    System.err.println(this.toString() + " serves in thread " + 
											 Thread.currentThread() + " as " + getServletName() +
											 " for " + request.getSession());

    // pass request as an XSLT param
    transformer.setParameter(GST_PREFIX+"request", request);
    // pass response as an XSLT param, XSLT needs to set the status!
    transformer.setParameter(GST_PREFIX+"response", response);
    // for convenience pass session as an XSLT param too
    transformer.setParameter(GST_PREFIX+"session", request.getSession());

    // request parameters
    Iterator parameters = request.getParameterMap().entrySet().iterator();
    while(parameters.hasNext()) {
      Map.Entry param = (Map.Entry)parameters.next();
      transformer.setParameter((String)param.getKey(), param.getValue());
      System.err.println(""+param.getKey()+"="+param.getValue());
    }

    // request attributes
    Enumeration e = request.getAttributeNames();
    while(e.hasMoreElements()) {
      String att = (String)e.nextElement();
      transformer.setParameter(att, request.getAttribute(att));
      System.err.println(att+"="+request.getAttribute(att));
    }

    // init-param
    Enumeration initParams = getInitParameterNames();
    while(initParams.hasMoreElements()) {
      String paramName = (String)initParams.nextElement();
      String paramValue = (String)getInitParameter(paramName);
      transformer.setParameter(paramName, paramValue);
      System.err.println(paramName+"="+paramValue);
    }
  }


  /** Read templates dealing with this transformer factory business. 
   */
  private Templates getTemplates() throws TransformerConfigurationException, TransformerException {
    if(this._templates != null)
      return this._templates;
    else {
      Source transformSource = this._uriResolver.resolve(this._transformName,null);
      return this._transformerFactory.newTemplates(transformSource);
    }
  }
	
  /** Responds to HTTP/GET requests. See the superclass' definition
      of these for general information.

      In GET requests, the XSLT transform is invoked on an empty
      XML node set. The request, response, and session objects, plus
      all request parameters and attributes, are passed to the
      transform as parameters. Thus, only the template for the root
      element is invoked, which will produce the output "page".

      @param request  the HTTP request object
      @param response the HTTP response object
	*/
  public void doGet(HttpServletRequest request,
										HttpServletResponse response)
    throws ServletException, IOException {
    try {
      // if sOAPAction is null, we have a free-form HTTP request
      String sOAPAction = request.getHeader("SOAPAction");

      Templates templates = getTemplates();

      // create a new transformer working context
      Transformer transformer = templates.newTransformer();
      transformer.setURIResolver(this._uriResolver);

      // set the content type
      String contentType = transformer.getOutputProperty(OutputKeys.MEDIA_TYPE);
      if(contentType==null) contentType = "text/xml";
      System.err.println("contentType " + contentType + "\n" + transformer.getOutputProperties());
      response.setContentType(contentType);

      setTransformerParameters(transformer,request,response);

      transformer.transform(getSource(null), getResult(response.getOutputStream()));
    } catch(Exception x) {
      throw new ServletException(x);
    }
  }

  /** Dump the request for debugging. */
  private InputStream debug(InputStream is) {
    /* * /
    return is;
    /* */
			 return new FilterInputStream(is) {
			 public int read() throws IOException {
			 int result = super.read();
			 if(result != -1) System.err.write(result);
			 return result;
			 }
			 public int read(byte b[]) throws IOException {
			 int result = super.read(b);
			 if(result != -1) System.err.write(b, 0, result);
			 return result;
			 }
			 public int read(byte b[], int off, int len) throws IOException {
			 int result = super.read(b, off, len);
			 if(result != -1) System.err.write(b, off, result);
			 return result;
			 }
			 };
			 /* */
  }
	
  /** Get a Source object */
  private Source getSource(InputStream is) throws Exception	{    
    if(is == null) {
      Source source = this._uriResolver.resolve(this._defaultInput,null);
      if(_readerClass != null && source instanceof StreamSource) {
				StreamSource streamSource = (StreamSource)source;
				source = new SAXSource((XMLReader)_readerClass.newInstance(),
															 new InputSource(debug(streamSource.getInputStream())));
				source.setSystemId(streamSource.getSystemId());
      } 
      return source;

    } else {	
      if(_readerClass != null)
				return new SAXSource((XMLReader)_readerClass.newInstance(),
														 new InputSource(debug(is)));
      else
				return new StreamSource(debug(is));
    }
  }
	
  /** Get a Result object */
  private Result getResult(OutputStream os) throws Exception {
    if(_writerClass != null) {
      XMLWriter writer = (XMLWriter)_writerClass.newInstance();
      writer.setOutputStream(os);
      return new SAXResult(writer.getContentHandler());
    } else 
      return new StreamResult(os);
  }


  /* A URL resolver that retrieves things using the getResourcePath method
     such that we can retrieve stuff from WAR files and such that files are
     looked up from the correct place.

     We assume that a resource is requested if a href starts with "/" or
     with "file:". If the base is not null and not empty, it will be
     used to complete the href *before* the file: or / determination is
     made.
  */
  private URIResolver _standardURIResolver;
  private URIResolver _uriResolver = new URIResolver() {
      public Source resolve(String href, String base)	throws TransformerException	{
				System.err.println("resolve: "+href+" in "+base);
				URL theURL = null;
				String resourcePath = null;
				
				try {
					if(base != null && !base.equals("")) {
						URL baseURL = new URL(base);
						//System.err.println(base+"->"+baseURL);
						if(href != null && !href.equals("")) {
							theURL = new URL(baseURL, href);
						} else {
							theURL = baseURL;
						}
					} else {
						theURL = new URL(href);
					}
					
					String protocol = theURL.getProtocol();
					
					if(protocol.equals("file")) {
						resourcePath = theURL.getPath();
					}
				} catch(java.net.MalformedURLException ex) {
					theURL = null;
					resourcePath = href;
				}
				
				try {
					if(resourcePath != null) {
						System.err.println(href+"->"+resourcePath);
						
						if(resourcePath.charAt(0) == '/')
							theURL = getServletContext().getResource(resourcePath);
						
						System.err.println(resourcePath+"->"+theURL);
					}

					if(theURL == null)
						theURL = Thread.currentThread().getContextClassLoader().getResource(href);
					
					if(theURL != null) {
						return new StreamSource(debug(theURL.openStream()),	theURL.toString());
					} else {
						throw new TransformerException("unable to resolve " + href);
					}
				} catch(Throwable ex) {
					throw new TransformerException(ex);
				}
      }
    };
}

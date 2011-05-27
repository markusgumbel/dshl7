/*  XMLMultiTransformer - an XMLFilter/Transformer for multi-document stream.
 *
 *  Copyright (C) 2004, Regenstrief Institute. All rights reserved.
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
 *  1050 Wishard Blvd., Indianapolis, IN 46202, USA.
 *
 * $Id: XMLMultiTransformer.java 5657 2007-03-31 16:20:07Z gschadow $
 */
package org.regenstrief.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/** Transforms a multi-document SAX stream, which is a SAX
		event stream that can contain several startDocument ...
		endDocument sections. At each startDocument, the 
		transformer is initialized.

		This is implemented as an XMLFilter, which is an XMLReader
		with a parent property. As an XMLReader, this will ovey
		setContentHandler calls, with the promise to have those 
		take immediate effect. Hence, this XMLReader will always 
		be the sender of the downstream events, and will receive
		events from the transformer. Therefore, a second XMLFilter
		is created that links to the parent XMLReader and forwards
		the events to the transformer.

		This is actually both an XMLFilter that also can be called
		like a transformer using the transform(Source, Result) method. 
  
		@author Gunther Schadow
		@version $Id: XMLMultiTransformer.java 5657 2007-03-31 16:20:07Z gschadow $
*/
public class XMLMultiTransformer 
  extends XMLFilterImpl
{
  Templates _templates = null;
  SAXTransformerFactory _saxTransformerFactory = null;
  UpstreamReceiver _upstreamReceiver = new UpstreamReceiver();

  /** Create one that acts as a regular XMLFilter. */
  public XMLMultiTransformer(SAXTransformerFactory saxTransformerFactory, 
														 Templates templates) { 
    _templates = templates;
    _saxTransformerFactory = saxTransformerFactory;
    super.setParent(_upstreamReceiver);
  }

  Result _result = null;
  
  /** Set a result other than the filter. This will be used for transformers
      that have non-XML output. */
  public void setResult(Result result) {
    _result = result;
  }

  Map _transformerParameters = null;
  Map _transformerOutputProperties = null;

  public void setParameter(String name, Object value) {
    if(_transformerParameters == null)
      _transformerParameters = new HashMap();
    _transformerParameters.put(name,value);
  }

  public void setOutputProperty(String name, String value) {
    if(_transformerOutputProperties == null)
      _transformerOutputProperties = new HashMap();
    _transformerOutputProperties.put(name,value);
  }

  class UpstreamReceiver extends XMLFilterImpl implements XMLFilter {
    TransformerHandler transformerHandler;
        
    public void startDocument() throws SAXException { 
      try {
				if(_templates != null)
					transformerHandler
						= _saxTransformerFactory.newTransformerHandler(_templates);
				else 
					transformerHandler
						= _saxTransformerFactory.newTransformerHandler();
	  
				if(_result == null) {
					SAXResult saxResult = new SAXResult(XMLMultiTransformer.this);
					transformerHandler.setResult(saxResult);
				} else {
					transformerHandler.setResult(_result);
				}

				if(_transformerParameters != null) {
					Transformer transformer = transformerHandler.getTransformer();
					Iterator params = _transformerParameters.entrySet().iterator();
					while(params.hasNext()) {
						Map.Entry param = (Map.Entry)params.next();
						transformer.setParameter((String)param.getKey(), param.getValue());
					}
				}
	
				if(_transformerOutputProperties != null) {
					Transformer transformer = transformerHandler.getTransformer();
					Iterator params = _transformerOutputProperties.entrySet().iterator();
					while(params.hasNext()) {
						Map.Entry param = (Map.Entry)params.next();
						transformer.setOutputProperty((String)param.getKey(),
																					(String)param.getValue());
					}
				}
	
				setContentHandler(transformerHandler);
				setDTDHandler(transformerHandler);
				try {
					setProperty("http://xml.org/sax/properties/lexical-handler",
											transformerHandler);      
				} catch(org.xml.sax.SAXNotRecognizedException x) { }
	
				//String systemId = parent.getSystemId();
				//transformerHandler.setSystemId(systemId);
				//saxResult.setSystemId(systemId);
	
				getContentHandler().startDocument();
      } catch(TransformerConfigurationException ex) {
				throw new SAXException(ex);
      }
    }
    
    public void endDocument() throws SAXException { 
      getContentHandler().endDocument();

      transformerHandler = null;
    }
  }

  public void setParent(XMLReader parent) {
    _upstreamReceiver.setParent(parent);
    parent.setContentHandler(_upstreamReceiver);
    parent.setErrorHandler(_upstreamReceiver); 
    parent.setDTDHandler(_upstreamReceiver);
    parent.setEntityResolver(_upstreamReceiver);
  }

  public XMLReader getParent() {
    return _upstreamReceiver.getParent();
  }

  /** Call this like a Transformer (for the final transformation) */
  public void transform(SAXSource source, Result result) 
    throws SAXException
	{
			setResult(result);
			setParent(source.getXMLReader());
			try {
				getParent().parse(source.getInputSource());
			} catch(java.io.IOException x) {
				throw new SAXException(x);
			}
	}
}

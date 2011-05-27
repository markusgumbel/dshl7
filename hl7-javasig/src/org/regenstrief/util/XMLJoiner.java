/*  XMLJoiner - receives a sequence of documents from a SAX pipeline
 *    and joins them together into a single document.
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
 * $Id: XMLJoiner.java 5657 2007-03-31 16:20:07Z gschadow $
 */
package org.regenstrief.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

/** Joins a stream of XML documents into a single document. The tricky
    part of this is for the joiner to know when the stream of
    documents actually ends. For this purpose the joiner must receive
    a second endDocument event directly from the upstream splitter
    or other multi-document stream source.
*/
public class XMLJoiner extends XMLFilterImpl implements XMLFilter {

  public static final String NAMESPACE_URI = "";
  public static final String NAMESPACE_ATT_URI = "";
  public static final String TAG_ROOT = "root";
  public static final Attributes ATTS = new AttributesImpl();

  /** Create an XMLJoiner in multi-document-pipeline mode */
  public XMLJoiner() { }

  private boolean inDocument = false;
  private boolean isConnected = false;

  /** Search for the nearest multi document source up the chain
      of parents and connect myself to it.
  */
  private void connectToMultiDocumentSource() {
    if(isConnected)
      return;
    else {
      XMLReader p = getParent();
      while(p != null) {
	if(p instanceof XMLMultiDocumentSource) {
	  ((XMLMultiDocumentSource)p).setJoiner(this);
	  isConnected = true;
	  return;
	} else if(p instanceof XMLFilter) {
	  p = ((XMLFilter)p).getParent();
	} else 
	  return;
      }
    }
  }

  public void startDocument() 
      throws SAXException
  {
    if(! isConnected) {
      connectToMultiDocumentSource();
      getContentHandler().startDocument();
      getContentHandler().startElement(NAMESPACE_URI,TAG_ROOT,TAG_ROOT,ATTS);
    }
    if(!inDocument) {
      inDocument = true;
    } else {
      throw new SAXException("start document in document");
    }
  }

  /** The XMLJoiner needs a final endDocument() event from the 
      XMLSplitter.
  */
  public void endDocument() 
      throws SAXException
  {
    if(! isConnected) 
      System.err.println("WARNING: XMLJoiner is not connected to XMLSplitter");

    if(inDocument) {
      inDocument = false;
    } else {
      getContentHandler().endElement(NAMESPACE_URI,TAG_ROOT,TAG_ROOT);
      getContentHandler().endDocument();
    }
  }
}

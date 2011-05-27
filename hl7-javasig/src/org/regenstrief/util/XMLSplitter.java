/*  XMLSplitter - an XMLFilter that splits a big XML document into
 *    a sequence of sub-documents at the second-level element.
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
 * $Id: XMLSplitter.java 5414 2007-01-30 16:44:17Z crosenthal $
 */
package org.regenstrief.util;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.helpers.XMLFilterImpl;

/** Splits an XML document at the root element and creates new 
    documents for each second level element. 
*/
public class XMLSplitter 
  extends XMLFilterImpl 
  implements XMLFilter, XMLMultiDocumentSource {

  /** Create an XMLSplitter in multi-document-pipeline mode */
  public XMLSplitter() { }

  private int _level = -1;

  private ContentHandler _joiner = null;

  public void startDocument() { _level++; }
  public void endDocument()
      throws SAXException
  { 
    if(--_level == -1 && _joiner != null)
      _joiner.endDocument();
  }

  public void setJoiner(ContentHandler joiner) {
    _joiner = joiner;
  }

  public void startElement(String uri, String localName, 
		    String qName, Attributes atts) 
      throws SAXException
  {
    switch(_level++) {
    case -1: 
      throw new SAXException("elements received before document started");
    case 0: // this is the root element, do nothing
      break;
    case 1: // this is the second-level element
      getContentHandler().startDocument();
      /*FALLTHROUGH*/
    default:
      getContentHandler().startElement(uri, localName, qName, atts);
      break;
    }
  }

  public void endElement(String uri, String localName, String qName)
      throws SAXException
  {
    switch(--_level) {
    case -1: 
      throw new SAXException("elements received after document ended");
    case 0: // this is the root element, do nothing
      break;
    case 1: // this is the second-level element
      getContentHandler().endElement(uri, localName, qName);
      getContentHandler().endDocument();
      break;
    default:
      getContentHandler().endElement(uri, localName, qName);
      break;
    }
  }
}

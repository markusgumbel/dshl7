/*  XMLInspector - a simple debug tool for a SAX pipeline.
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
 * $Id: XMLInspector.java 5414 2007-01-30 16:44:17Z crosenthal $
 */
package org.regenstrief.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.helpers.XMLFilterImpl;

/** Shows output elements in the middle of a TRAX-pipeline for debugging. */
public class XMLInspector extends XMLFilterImpl implements XMLFilter {
  public XMLInspector() { }

  public void startDocument() 
      throws SAXException
  { 
    System.err.println("startDocument()");
    getContentHandler().startDocument();
  }
  
  public void endDocument() 
      throws SAXException
  { 
    System.err.println("endDocument()");
    getContentHandler().endDocument();
  }
  
  public void startElement(String uri, String localName, 
			   String qName, Attributes atts) 
      throws SAXException
  {
    System.err.println("startElement("+uri+","+localName+","+qName+")");
    getContentHandler().startElement(uri, localName, qName, atts);
  }

  public void endElement(String uri, String localName, String qName)
      throws SAXException
  {
    System.err.println("endElement("+qName+")");
    getContentHandler().endElement(uri, localName, qName);
  }
}

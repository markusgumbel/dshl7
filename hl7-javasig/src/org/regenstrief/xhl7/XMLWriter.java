/*  XMLWriter - a SAX ContentHandler that can generate an external 
 *              representation to an output stream.
 *
 *  Copyright (C) 2002, 2003 Regenstrief Institute. All rights reserved.
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
 * $Id: XMLWriter.java 322 2003-01-06 18:21:02Z gschadow $
 */
package org.regenstrief.xhl7;

import java.io.OutputStream;
import org.xml.sax.ContentHandler;

/** Interface of a class that can be configured with an output stream
    and that can give a SAX ContentHandler for producing external 
    representations of SAX events. Sort of a fast transform into any
    serialization syntax for (a subset of) XML. An XMLWriter object's
    ContentHandler can be passed to a javax.xml.transform.sax.SAXResult
    constructor to receive SAX events from a transform, or, it can
    be directly linked to a SAX parser to output the parsed events
    right away.

    @author Gunther Schadow
    @version $Id: XMLWriter.java 322 2003-01-06 18:21:02Z gschadow $ 
*/
public interface XMLWriter
{  
  public void setOutputStream(OutputStream outputStream);
  public ContentHandler getContentHandler();
}

/*  XMLMultiDocumentSource - an interface for an source that sends
 *    multiple documents through the same SAX event pipeline.
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
 * $Id: XMLMultiDocumentSource.java 5349 2007-01-23 18:32:25Z gschadow $
 */
package org.regenstrief.util;

import org.xml.sax.ContentHandler;

public interface XMLMultiDocumentSource {
  void setJoiner(ContentHandler joiner);
}

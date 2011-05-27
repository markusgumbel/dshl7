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
 * The Initial Developer of the Original Code is .
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.xml;

import org.hl7.meta.Datatype;
import org.hl7.xml.builder.DatatypeBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;

/**
 * This interface provides access to presentation functionality for data types. For inbound XML parsing, call
 * <code>getContentHandler()</code>; for generating outbound SAX events, use <code>getBuilder()</code>.
 * 
 * @author Skirmantas Kligys, Eric Chen
 */
public interface DatatypePresenter {
	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype);

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder();
}

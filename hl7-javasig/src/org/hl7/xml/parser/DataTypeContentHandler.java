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
package org.hl7.xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A DynamicContentHandler used for data types. It has many methods implemented to just do nothing.
 */
public abstract class DataTypeContentHandler extends DynamicContentHandlerBase implements DynamicContentHandler,
		Cloneable {
	/**
	 * Use this object to avoid creating a new instance of AttributesImpl on each startElement(). Make sure you call
	 * attributes_.clear() before you use it.
	 * 
	 * DON'T USE THIS, for one it is not at the right place (this is a parser class, not builder) and it is still too much
	 * duplication. Instead use the attributes object in the PresentationHelper class.
	 * 
	 * @deprecated
	 */
	protected final AttributesImpl attributes_ = new AttributesImpl();

	public abstract void startElement(String namespaceURI, String localName, String qName, Attributes atts)
			throws SAXException;

	public abstract void endElement(String namespaceURI, String localName, String qName) throws SAXException;

	/**
	 * A standard override to make an object cloneable.
	 * 
	 * @return a clone object
	 */
	public Object clone() throws CloneNotSupportedException {
		// This does shallow cloning. Is that OK???
		return super.clone();
	}
}

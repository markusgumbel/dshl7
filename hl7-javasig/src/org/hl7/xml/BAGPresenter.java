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
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.DataTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class BAGPresenter extends DatatypePresenterBase {
	private static final BAGPresenter INSTANCE = new BAGPresenter();
	private static final ContentHandler NULL_CONTENTHANDLER = new BAGContentHandler();

	private static class BAGContentHandler extends DataTypeContentHandler {
		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
			throw new UnsupportedOperationException();
		}

		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			throw new UnsupportedOperationException();
		}
	} // BAGContentHandler

	private static class BAGBuilder implements DatatypeBuilder<BAG<ANY>> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, BAG<ANY> value, String localName) throws BuilderException {
			if (value.isNullJ())
				return;
			if (value.isEmpty().isTrue())
				return;
			Datatype typeConstraint = builder.getTypeConstraint();
			try {
				for (ANY component : value) {
					builder.setTypeConstraint(typeConstraint);
					builder.build(component, localName);
				}
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, BAG value, String localName)
				throws BuilderException {
			throw new BuilderException("BAG<T> cannot be a structural attribute");
		}
	} // BAGBuilder

	private BAGPresenter() {}

	public static BAGPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,	Datatype datatype) {
		return NULL_CONTENTHANDLER;
	}

	public DatatypeBuilder getBuilder() {
		return new BAGBuilder();
	}
}

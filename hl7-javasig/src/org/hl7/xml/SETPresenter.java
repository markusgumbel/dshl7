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
import org.hl7.types.DSET;
import org.hl7.types.QSET;
import org.hl7.types.SET;
import org.hl7.types.TS;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.DataTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class SETPresenter extends DatatypePresenterBase {
	private static final SETPresenter INSTANCE = new SETPresenter();

	private static class SETContentHandler extends DataTypeContentHandler {
		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
			throw new UnsupportedOperationException();
		}

		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			throw new UnsupportedOperationException();
		}
	}

	private static class SETBuilder implements DatatypeBuilder<SET> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, SET value, String localName) throws BuilderException {
			if (value.isNullJ())
				return;
			if (((SET) value).isEmpty().isTrue())
				return;
			Datatype typeConstraint = builder.getTypeConstraint();
			try {
				if (value instanceof DSET) {
					for (ANY component : (DSET<ANY>) value) {
						// This looks like a serious hack. We can pass down xsi:type and the RimGraphXMLSpeaker.ContentBuilder
						// will then override all its own xsi:type considerations. Why the special case for TS? The reason
						// is that really all SET elements should be SXCM<T>, not just for SET<TS>, but the HL7 schema
						// generator doesn't do it and it might be that the HL7 committee would even disagree that that
						// should be done. So we have to improvise here.
						if (component instanceof TS)
							builder.addAttribute(DatatypePresenterBase.W3_XML_SCHEMA, "type", "xsi:type", "CDATA", "SXCM_TS");
						else
							builder.setTypeConstraint(typeConstraint);
						if (component != null && !component.isNull().isTrue()) {
							builder.build(component, localName);
						}
					}
				} else if (value instanceof QSET) {
					QSETPresenter.instance().getBuilder().build(builder, value, localName);
				} else
					new AssertionError("value is neither DSET nor QSET nor empty" + value);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, SET value, String localName)
				throws BuilderException {
			StringBuffer sb = new StringBuffer();
			// FIXME: this does not care if the set.isNull, and never shows any nullFlavor of the set,
			// even though someone declared that if the entire set is null, then one element would
			// be produced with the nullFlavor.
			for (ANY element : (DSET<ANY>) value) {
				if (!element.isNullJ()) {
					if (sb.length() > 0)
						sb.append(" ");
					sb.append(element.toString()); // XXX: are we sure toString outputs a valid literal?
				}
			}
			if (sb.length() > 0)
				builder.addAttribute(localName, sb.toString());
		}
	}

	private SETPresenter() {}

	public static SETPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new SETContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new SETBuilder();
	}
}

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
import org.hl7.types.BL;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.BLimpl;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This is presentation layer for BL data type.
 * 
 * BL exists in 2 forms the BL full element type (where BL is the text data) and the st type (where BL is usually some
 * attribute data.) In the case of BL as attribute value, the data types containing such st attributes will usually
 * allow just java.lang.String in their factories. So, this is for the full blown BL.
 */
public class BLPresenter extends DatatypePresenterBase {
	public static final String ATTR_VALUE = "value";

	private static final BLPresenter INSTANCE = new BLPresenter();

	private static class BLContentHandler extends SimpleTypeContentHandler {
		BL _result = null;

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = BLimpl.nullValueOf(nullFlavorString);
			} else {
				String valueString = atts.getValue(ATTR_VALUE);
				if (valueString != null) {
					_result = ValueFactory.getInstance().BLvalueOfLiteral(valueString);
				}
			}
		}

		protected Object getResult() {
			return (_result == null) ? BLimpl.NI : _result;
		}

		public void characters(char[] ch, int start, int length) {}
	}

	private static class BLBuilder implements DatatypeBuilder<BL> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, BL value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				builder.addAttribute(ATTR_VALUE, value);
				builder.startElement(localName);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, BL blValue, String localName)
				throws BuilderException {
			builder.addAttribute(localName, blValue.toString());
		}
	}

	private BLPresenter() {}

	public static BLPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new BLContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new BLBuilder();
	}
}

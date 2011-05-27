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

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hl7.meta.Datatype;
import org.hl7.types.PQR;
import org.hl7.types.REAL;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.PQRimpl;
import org.hl7.types.impl.REALnull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class PQRPresenter extends DatatypePresenterBase {

	public static final String ATTR_NULL_FLAVOR = "nullFlavor";
	public static final String ATTR_CODE = "code";
	public static final String ATTR_CODE_SYSTEM = "codeSystem";
	public static final String ATTR_CODE_SYSTEM_NAME = "codeSystemName";
	public static final String ATTR_CODE_SYSTEM_VERSION = "codeSystemVersion";
	public static final String ATTR_DISPLAY_NAME = "displayName";
	public static final String ATTR_ORIGINAL_TEXT = "originalText";
	public static final String ATTR_VALUE = "value";
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml");

	private static final PQRPresenter INSTANCE = new PQRPresenter();

	protected static class PQRContentHandler extends TreeContentHandler {
		public PQRContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			PQR result = null;
			try {
				result = valueOf(element);
			} catch (IllegalArgumentException ex) {
				// IGNORE broken translation
				LOGGER.log(Level.WARNING, "illegal argument in PQR, not returning anything");
				result = null;
				// returning null means not to return anything, i.e. the ResultReceiver is never notified
			}
			super.returnResult(result);
		}

		protected PQR valueOf(TreeContentHandler.Element element) throws SAXException {
			String nullFlavorString = element.getAttributeValue(ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				throw new UnsupportedOperationException("Can't have a null PQR in PQRPresenter!");
				// return CVnull.valueOf(nullFlavorString);
			}
			// Handle other attributes.
			ST code = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_CODE));
			UID codeSystem = ValueFactory.getInstance().UIDvalueOfLiteral(element.getAttributeValue(ATTR_CODE_SYSTEM));
			ST codeSystemName = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_CODE_SYSTEM_NAME));
			ST codeSystemVersion = ValueFactory.getInstance().STvalueOfLiteral(
					element.getAttributeValue(ATTR_CODE_SYSTEM_VERSION));
			ST displayName = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_DISPLAY_NAME));
			String strValue = element.getAttributeValue(ATTR_VALUE);
			REAL value;
			if (strValue != null)
				value = ValueFactory.getInstance().REALvalueOfLiteral(strValue);
			else
				value = REALnull.NI;
			// Handle nested elements...
			Iterator<Element> it = element.findChildren(ATTR_ORIGINAL_TEXT);
			ST originalTextString = ValueFactory.getInstance().STvalueOfLiteral(it.hasNext() ? it.next().getText() : null);
			return PQRimpl.valueOf(value, code, codeSystem, originalTextString, displayName, codeSystemName,
					codeSystemVersion);
		}
	}

	// -------------------------------------------------------------------------
	private static class PQRBuilder implements DatatypeBuilder<PQR> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, PQR value, String localName) throws BuilderException {
			try {
				PQR pqr = (PQR) value;
				if (builder.nullValueHandled(value, localName))
					return;
				builder.addAttribute(ATTR_VALUE, pqr.value());
				builder.addAttribute(ATTR_CODE, pqr.code());
				builder.addAttribute(ATTR_DISPLAY_NAME, pqr.displayName());
				builder.addAttribute(ATTR_CODE_SYSTEM, pqr.codeSystem());
				builder.addAttribute(ATTR_CODE_SYSTEM_NAME, pqr.codeSystemName());
				builder.addAttribute(ATTR_CODE_SYSTEM_VERSION, pqr.codeSystemVersion());
				builder.startElement(localName);
				// An ugly workaround to avoid fake <originalText nullFlavor="NA"/>
				// entries in output for all CDs, CEs, CVs. Should it be here???
				if (!pqr.originalText().notApplicableJ()) {
					builder.build(pqr.originalText(), ATTR_ORIGINAL_TEXT);
				}
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, PQR value, String localName)
				throws BuilderException {
			PQR pqrValue = (PQR) value;
			builder.addAttribute(localName, pqrValue.code().toString());
		}
	}

	// -------------------------------------------------------------------------
	private PQRPresenter() {}

	public static PQRPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		// do nothing with the datatype
		return new PQRContentHandler(namespaceURI, localName, qName, atts);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new PQRBuilder();
	}
}

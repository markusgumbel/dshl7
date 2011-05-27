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

import org.hl7.meta.Datatype;
import org.hl7.types.CV;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.CVExceptionalImpl;
import org.hl7.types.impl.CVimpl;
import org.hl7.types.impl.CVnull;
import org.hl7.types.impl.NullFlavorImpl;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker.ContentBuilder;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class CVPresenter extends DatatypePresenterBase {
	// -------------------------------------------------------------------------
	public static final String ATTR_NULL_FLAVOR = "nullFlavor";
	public static final String ATTR_CODE = "code";
	public static final String ATTR_CODE_SYSTEM = "codeSystem";
	public static final String ATTR_CODE_SYSTEM_NAME = "codeSystemName";
	public static final String ATTR_CODE_SYSTEM_VERSION = "codeSystemVersion";
	public static final String ATTR_DISPLAY_NAME = "displayName";
	public static final String ATTR_ORIGINAL_TEXT = "originalText";
	// -------------------------------------------------------------------------
	private static final CVPresenter INSTANCE = new CVPresenter();

	// -------------------------------------------------------------------------
	protected static class CVContentHandler extends TreeContentHandler {
		// .......................................................................
		public CVContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		// .......................................................................
		protected void returnResult(Object intermediate) throws SAXException {
			Element element = (Element) intermediate;
			super.returnResult(valueOf(element));
		}

		// .......................................................................
		protected CV valueOf(Element element) throws SAXException {
			String nullFlavorString = element.getAttributeValue(ATTR_NULL_FLAVOR);
			if (nullFlavorString != null && !nullFlavorString.equals(NullFlavorImpl.sOTH))
				return CVnull.valueOf(nullFlavorString);
			// Handle other attributes.
			Iterator<Element> it = element.findChildren(ATTR_ORIGINAL_TEXT);
			ST originalTextString = ValueFactory.getInstance().STvalueOfLiteral(it.hasNext() ? it.next().getText() : null);
			if (nullFlavorString != null && nullFlavorString.equals(NullFlavorImpl.sOTH))
				return CVExceptionalImpl.valueOf(originalTextString);
			ST code = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_CODE));
			UID codeSystem = ValueFactory.getInstance().UIDvalueOfLiteral(element.getAttributeValue(ATTR_CODE_SYSTEM));
			ST codeSystemName = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_CODE_SYSTEM_NAME));
			ST codeSystemVersion = ValueFactory.getInstance().STvalueOfLiteral(
					element.getAttributeValue(ATTR_CODE_SYSTEM_VERSION));
			ST displayName = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_DISPLAY_NAME));
			// Handle nested elements...
			return CVimpl.valueOf(code, codeSystem, originalTextString, displayName, codeSystemName, codeSystemVersion);
		}
	}

	// -------------------------------------------------------------------------
	private static class CVBuilder implements DatatypeBuilder<CV> {
		public void build(ContentBuilder builder, CV value, String localName) throws BuilderException {
			try {
				if (value instanceof CVExceptionalImpl)
					builder.nullValueHandled(value);
				else if (builder.nullValueHandled(value, localName))
					return;

				builder.addAttribute(ATTR_CODE, value.code());
				builder.addAttribute(ATTR_DISPLAY_NAME, value.displayName());
				builder.addAttribute(ATTR_CODE_SYSTEM, value.codeSystem());
				builder.addAttribute(ATTR_CODE_SYSTEM_NAME, value.codeSystemName());
				builder.addAttribute(ATTR_CODE_SYSTEM_VERSION, value.codeSystemVersion());
				builder.startElement(localName);
				// An ugly workaround to avoid fake <originalText nullFlavor="NA"/>
				// entries in output for all CDs, CEs, CVs. Should it be here???
				if ((!value.originalText().notApplicableJ()) && (!value.originalText().isNullJ()))
					builder.build(value.originalText(), ATTR_ORIGINAL_TEXT);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(ContentBuilder builder, CV value, String localName) throws BuilderException {
			builder.addAttribute(localName, value.code().toString());
		}
	}

	// -------------------------------------------------------------------------
	private CVPresenter() {}

	public static CVPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		// do nothing with the datatype
		return new CVContentHandler(namespaceURI, localName, qName, atts);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new CVBuilder();
	}
}

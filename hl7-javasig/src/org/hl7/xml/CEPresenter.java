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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hl7.meta.Datatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.CD;
import org.hl7.types.CE;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.CEimpl;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.CVimpl;
import org.hl7.types.impl.SETjuSetAdapter;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class CEPresenter extends DatatypePresenterBase {
	// -------------------------------------------------------------------------
	public static final String ATTR_CODE = "code";
	public static final String ATTR_CODE_SYSTEM = "codeSystem";
	public static final String ATTR_CODE_SYSTEM_NAME = "codeSystemName";
	public static final String ATTR_CODE_SYSTEM_VERSION = "codeSystemVersion";
	public static final String ATTR_DISPLAY_NAME = "displayName";
	public static final String TAG_ORIGINAL_TEXT = "originalText";
	public static final String TAG_TRANSLATION = "translation";
	// -------------------------------------------------------------------------
	private static final CEPresenter INSTANCE = new CEPresenter();

	// -------------------------------------------------------------------------
	protected static class CEContentHandler extends TreeContentHandler {
		// .......................................................................
		public CEContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		// .......................................................................
		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			CE result = valueOf(element);
			super.returnResult(result);
		}

		// .......................................................................
		protected static CE valueOf(TreeContentHandler.Element element) throws SAXException {
			String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				return CEnull.valueOf(nullFlavorString);
			}
			// Handle other attributes.
			ST code = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_CODE));
			UID codeSystem = ValueFactory.getInstance().UIDvalueOfLiteral(element.getAttributeValue(ATTR_CODE_SYSTEM));
			ST codeSystemName = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_CODE_SYSTEM_NAME));
			ST codeSystemVersion = ValueFactory.getInstance().STvalueOfLiteral(
					element.getAttributeValue(ATTR_CODE_SYSTEM_VERSION));
			ST displayName = ValueFactory.getInstance().STvalueOfLiteral(element.getAttributeValue(ATTR_DISPLAY_NAME));
			// Handle nested elements...
			try {
				Iterator<Element> it = element.findChildren(TAG_ORIGINAL_TEXT);
				ST originalText = ValueFactory.getInstance().STvalueOfLiteral(it.hasNext() ? it.next().getText() : null);
				Set<CD> translationSet = null;
				it = element.findChildren(TAG_TRANSLATION);
				if (it.hasNext()) {
					// Should be optimized.
					Datatype cv = DatatypeMetadataFactoryImpl.instance().create("CV");
					translationSet = new HashSet<CD>();
					do {
						Element el = it.next();
						// Should be optimized.
						/*
						 * PQRPresenter.PQRContentHandler newContext =
						 * (PQRPresenter.PQRContentHandler)pqr.getHandler(el.getNamespaceURI(),el.getLocalName(), el.getQName(),
						 * el.getAttributes());
						 * 
						 * PQR translation = newContext.valueOf(el);
						 */
						CVPresenter.CVContentHandler newContext = (CVPresenter.CVContentHandler) cv.getHandler(
								el.getNamespaceURI(), el.getLocalName(), el.getQName(), el.getAttributes());
						CD translation = newContext.valueOf(el);
						translationSet.add(translation);
					} while (it.hasNext());
				}
				// FIXME: the HMD default must go here
				if (codeSystem == null || codeSystem.isNull().isTrue())
					codeSystem = ValueFactory.getInstance().UIDvalueOfLiteral("UNKNOWN");
				return CEimpl.valueOf(CVimpl.valueOf(code, codeSystem, originalText, displayName, codeSystemName,
						codeSystemVersion), (SET<CD>) SETjuSetAdapter.valueOf(translationSet));
			} catch (UnknownDatatypeException ex) {
				throw new SAXException(ex);
			} catch (FactoryException ex) {
				throw new SAXException(ex);
			}
		}
	}

	// -------------------------------------------------------------------------
	private static class CEBuilder implements DatatypeBuilder<CE> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, CE value, String localName) throws BuilderException {
			try {
				CE ce = (CE) value;
				if (builder.nullValueHandled(value, localName))
					return;
				builder.addAttribute(ATTR_CODE, ce.code());
				builder.addAttribute(ATTR_DISPLAY_NAME, ce.displayName());
				builder.addAttribute(ATTR_CODE_SYSTEM, ce.codeSystem());
				builder.addAttribute(ATTR_CODE_SYSTEM_NAME, ce.codeSystemName());
				builder.addAttribute(ATTR_CODE_SYSTEM_VERSION, ce.codeSystemVersion());
				builder.startElement(localName);
				// An ugly workaround to avoid fake <originalText nullFlavor="NA"/>
				// entries in output for all CDs, CEs, CVs. Should it be here???
				if (!(ce.originalText().notApplicableJ() || ce.originalText().isNullJ())) {
					builder.build(ce.originalText(), TAG_ORIGINAL_TEXT);
				}
				builder.build(ce.translation(), TAG_TRANSLATION);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, CE value, String localName)
				throws BuilderException {
			CE ceValue = (CE) value;
			builder.addAttribute(localName, ceValue.code().toString());
		}
	}

	// ----------------------------------------------------------------
	private CEPresenter() {}

	public static CEPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		// do nothing with the datatype
		return new CEContentHandler(namespaceURI, localName, qName, atts);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new CEBuilder();
	}
}

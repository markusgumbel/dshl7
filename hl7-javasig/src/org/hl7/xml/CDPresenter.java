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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hl7.meta.Datatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.ED;
import org.hl7.types.impl.CDimpl;
import org.hl7.types.impl.CDExceptionalImpl;
import org.hl7.types.impl.CDnull;
import org.hl7.types.impl.NullFlavorImpl;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class CDPresenter extends DatatypePresenterBase {
	// -------------------------------------------------------------------------
	public static final String ATTR_CODE = "code";
	public static final String ATTR_CODE_SYSTEM = "codeSystem";
	public static final String ATTR_CODE_SYSTEM_NAME = "codeSystemName";
	public static final String ATTR_CODE_SYSTEM_VERSION = "codeSystemVersion";
	public static final String ATTR_DISPLAY_NAME = "displayName";
	public static final String TAG_ORIGINAL_TEXT = "originalText";
	public static final String TAG_QUALIFIER = "qualifier";
	public static final String TAG_TRANSLATION = "translation";
	// -------------------------------------------------------------------------
	private static final CDPresenter INSTANCE = new CDPresenter();

	// -------------------------------------------------------------------------
	protected static class CDContentHandler extends TreeContentHandler {
		// .......................................................................
		public CDContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		// .......................................................................
		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			CD result = valueOf(element);
			super.returnResult(result);
		}

		// .......................................................................
		protected static CD valueOf(TreeContentHandler.Element element) throws SAXException {
			String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null && !nullFlavorString.equals(NullFlavorImpl.sOTH)) {
				return CDnull.valueOf(nullFlavorString);
			}
			// Handle other attributes.
			String code = element.getAttributeValue(ATTR_CODE);
			String codeSystem = element.getAttributeValue(ATTR_CODE_SYSTEM);
			String codeSystemName = element.getAttributeValue(ATTR_CODE_SYSTEM_NAME);
			String codeSystemVersion = element.getAttributeValue(ATTR_CODE_SYSTEM_VERSION);
			String displayName = element.getAttributeValue(ATTR_DISPLAY_NAME);
			// Handle nested elements...
			try {
				Iterator<Element> it = element.findChildren(TAG_ORIGINAL_TEXT);
				ED originalText = null;
				if (it.hasNext()) {
					// Should be optimized.
					Datatype ed = DatatypeMetadataFactoryImpl.instance().create("ED");
					Element el = it.next();
					// Should be optimized.
					EDPresenter.EDContentHandler newContext = (EDPresenter.EDContentHandler) ed.getHandler(el.getNamespaceURI(),
							el.getLocalName(), el.getQName(), el.getAttributes());
					originalText = newContext.valueOf(el);
				}
				if (nullFlavorString != null && nullFlavorString.equals(NullFlavorImpl.sOTH)) {
					return CDExceptionalImpl.valueOf(originalText);
				}
				List<CR> qualifierList = null;
				it = element.findChildren(TAG_QUALIFIER);
				if (it.hasNext()) {
					// Should be optimized.
					Datatype cr = DatatypeMetadataFactoryImpl.instance().create("CR");
					qualifierList = new ArrayList<CR>();
					do {
						Element el = it.next();
						// Should be optimized.
						CRPresenter.CRContentHandler newContext = (CRPresenter.CRContentHandler) cr.getHandler(
								el.getNamespaceURI(), el.getLocalName(), el.getQName(), el.getAttributes());
						CR qualifier = newContext.valueOf(el);
						qualifierList.add(qualifier);
					} while (it.hasNext());
				}
				Set<CD> translationSet = null;
				it = element.findChildren(TAG_TRANSLATION);
				if (it.hasNext()) {
					// Should be optimized.
					Datatype cd = DatatypeMetadataFactoryImpl.instance().create("CD");
					translationSet = new HashSet<CD>();
					do {
						Element el = it.next();
						// Should be optimized.
						CDPresenter.CDContentHandler newContext = (CDPresenter.CDContentHandler) cd.getHandler(
								el.getNamespaceURI(), el.getLocalName(), el.getQName(), el.getAttributes());
						CD translation = newContext.valueOf(el);
						translationSet.add(translation);
					} while (it.hasNext());
				}
				// FIXME: the HMD default must go here
				if (codeSystem == null)
					codeSystem = "unknown";
				return CDimpl.valueOf(code, codeSystem, codeSystemName, codeSystemVersion, displayName, originalText,
						qualifierList, translationSet);
			} catch (UnknownDatatypeException ex) {
				throw new SAXException(ex);
			} catch (FactoryException ex) {
				throw new SAXException(ex);
			}
		}
	}

	// -------------------------------------------------------------------------
	private static class CDBuilder implements DatatypeBuilder<CD> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, CD cd, String localName) throws BuilderException {
			try {
				if (cd instanceof CDExceptionalImpl)
					builder.nullValueHandled(cd);
				else if (builder.nullValueHandled(cd, localName))
					return;

				builder.addAttribute(ATTR_CODE, cd.code());
				builder.addAttribute(ATTR_DISPLAY_NAME, cd.displayName());
				builder.addAttribute(ATTR_CODE_SYSTEM, cd.codeSystem());
				builder.addAttribute(ATTR_CODE_SYSTEM_NAME, cd.codeSystemName());
				builder.addAttribute(ATTR_CODE_SYSTEM_VERSION, cd.codeSystemVersion());
				builder.startElement(localName);
				// An ugly workaround to avoid fake <originalText nullFlavor="NA"/>
				// entries in output for all CDs, CEs, CVs. Should it be here???
				if (cd.originalText() != null && cd.originalText().isNull().isFalse()) {
					builder.build(cd.originalText(), TAG_ORIGINAL_TEXT);
				}
				// The CDExceptionalImpl implementation of this method throws an UnsupportedOperationException
				if (!(cd instanceof CDExceptionalImpl)) {
					builder.build(cd.qualifier(), TAG_QUALIFIER);
				}
				builder.build(cd.translation(), TAG_TRANSLATION);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, CD cdValue, String localName)
				throws BuilderException {
			builder.addAttribute(localName, cdValue.code().toString());
		}
	}

	// -------------------------------------------------------------------------
	private CDPresenter() {}

	public static CDPresenter instance() {
		return INSTANCE;
	}

	public static CD getValue(Object valueString) {
		return CDimpl.valueOf(valueString.toString(), "UNKNOWN");
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		// do nothing with the datatype
		return new CDContentHandler(namespaceURI, localName, qName, atts);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new CDBuilder();
	}
}

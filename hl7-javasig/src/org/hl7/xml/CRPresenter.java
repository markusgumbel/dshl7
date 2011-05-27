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
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.CV;
import org.hl7.types.impl.CDnull;
import org.hl7.types.impl.CRimpl;
import org.hl7.types.impl.CRnull;
import org.hl7.types.impl.CVnull;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class CRPresenter extends DatatypePresenterBase {
	// -------------------------------------------------------------------------
	public static final String ATTR_INVERTED = "inverted";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_VALUE = "value";
	// -------------------------------------------------------------------------
	private static final CRPresenter INSTANCE = new CRPresenter();

	// -------------------------------------------------------------------------
	protected static class CRContentHandler extends TreeContentHandler {
		// .......................................................................
		public CRContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		// .......................................................................
		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			CR result = valueOf(element);
			super.returnResult(result);
		}

		// .......................................................................
		protected CR valueOf(TreeContentHandler.Element element) throws SAXException {
			String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				return CRnull.valueOf(nullFlavorString);
			}
			// Handle other attributes.
			String invertedString = element.getAttributeValue(ATTR_INVERTED);
			boolean invertedBool = (invertedString != null) ? Boolean.parseBoolean(invertedString) : false;
			// Handle nested elements...
			try {
				Iterator<Element> it = element.findChildren(ATTR_NAME);
				CV name = CVnull.NA;
				if (it.hasNext()) {
					Element el = (TreeContentHandler.Element) it.next();
					// Should be optimized.
					Datatype cv = DatatypeMetadataFactoryImpl.instance().create("CV");
					CVPresenter.CVContentHandler newContext = (CVPresenter.CVContentHandler) cv.getHandler(el.getNamespaceURI(),
							el.getLocalName(), el.getQName(), el.getAttributes());
					name = newContext.valueOf(el);
				}
				it = element.findChildren(ATTR_VALUE);
				CD value = CDnull.NI;
				if (it.hasNext()) {
					Element el = (TreeContentHandler.Element) it.next();
					// Should be optimized.
					Datatype cd = DatatypeMetadataFactoryImpl.instance().create("CD");
					CDPresenter.CDContentHandler newContext = (CDPresenter.CDContentHandler) cd.getHandler(el.getNamespaceURI(),
							el.getLocalName(), el.getQName(), el.getAttributes());
					value = newContext.valueOf(el);
				}
				return (invertedString != null) ? CRimpl.valueOf(name, value, invertedBool) : CRimpl.valueOf(name, value);
			} catch (UnknownDatatypeException ex) {
				throw new SAXException(ex);
			} catch (FactoryException ex) {
				throw new SAXException(ex);
			}
		}
	}

	// -------------------------------------------------------------------------
	private static class CRBuilder implements DatatypeBuilder<CR> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, CR value, String localName) throws BuilderException {
			try {
				CR cr = (CR) value;
				if (builder.nullValueHandled(value, localName))
					return;
				builder.addAttribute(ATTR_INVERTED, cr.inverted());
				builder.startElement(localName);
				builder.build(cr.name(), ATTR_NAME);
				builder.build(cr.value(), ATTR_VALUE);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, CR value, String localName)
				throws BuilderException {
			throw new BuilderException("CR cannot be a structural attribute");
		}
	}

	// ---------------------------------------------------------------------------
	private CRPresenter() {}

	public static CRPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		// do nothing with the datatype
		return new CRContentHandler(namespaceURI, localName, qName, atts);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new CRBuilder();
	}
}

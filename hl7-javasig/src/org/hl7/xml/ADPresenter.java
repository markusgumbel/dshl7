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
import java.util.Iterator;
import java.util.List;

import org.hl7.meta.Datatype;
import org.hl7.types.AD;
import org.hl7.types.ADXP;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.QSET;
import org.hl7.types.TS;
import org.hl7.types.impl.ADimpl;
import org.hl7.types.impl.ADnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ADPresenter extends DatatypePresenterBase {

	public static final String ATTR_USE = "use";
	public static final String ATTR_VALID_TIME = "validTime";
	public static final String ATTR_IS_NOT_ORDERED = "isNotOrdered";

	private static final ADPresenter INSTANCE = new ADPresenter();


	protected static class ADContentHandler extends TreeContentHandler {

		public ADContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			AD result = valueOf(element);
			super.returnResult(result);
		}

		protected AD valueOf(TreeContentHandler.Element element) {
			String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				return ADnull.valueOf(nullFlavorString);
			}
			String useValue = element.getAttributeValue(ATTR_USE);
			BL isNotOrdered = BLimpl.valueOf(element.getAttributeValue(ATTR_IS_NOT_ORDERED));
			DSET<CS> use = CSPresenter.parseList(useValue, "PostalAddressUse"); // FIXME: code system wrong! Use ENUM!
			Iterator<Element> it = element.findChildren(ATTR_VALID_TIME);
			// NYI.
			// QSET<TS> validTime = it.hasNext() ? GTSPresenter.valueOf(it.next()) : null;
			QSET<TS> validTime = null;
			List<ADXP> addressParts = new ArrayList<ADXP>();
			String ns1 = element.getNamespaceURI();
			for (Iterator<TreeContentHandler.Node> it2 = element.getChildren().iterator(); it2.hasNext();) {
				TreeContentHandler.Node node = (TreeContentHandler.Node) it2.next();
				ADXP adxp = ADXPPresenter.ADXPContentHandler.valueOf(node);
				if (adxp != null)
					addressParts.add(adxp);
			}
			return ADimpl.valueOf(addressParts, use, validTime, isNotOrdered);
		}
	}

	private static class ADBuilder implements DatatypeBuilder<AD> {

		private final CSPresenter.CSBuilder csBuilder_ = (CSPresenter.CSBuilder) CSPresenter.instance().getBuilder();
		private final ADXPPresenter.ADXPBuilder adxpBuilder_ = (ADXPPresenter.ADXPBuilder) ADXPPresenter.instance()
			.getBuilder();

		public void build(RimGraphXMLSpeaker.ContentBuilder builder, AD ad, String localName) throws BuilderException {
			boolean started = false;
			try {
				if (builder.nullValueHandled(ad, localName))
					return;
				csBuilder_.buildAttribute(builder, ad.use(), ATTR_USE);
				builder.addAttribute(ATTR_IS_NOT_ORDERED, ad.isNotOrdered());
				builder.startElement(localName);
				started = true;
				for (Iterator<ADXP> it = ad.iterator(); it.hasNext();) {
					ADXP adxp = (ADXP) it.next();
					adxpBuilder_.build(builder, adxp);
				}
				// Emit validTime if present.
				builder.build(ad.validTime(), ATTR_VALID_TIME);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			} finally {
				if (started) {
					try {
						builder.endElement(localName);
					} catch (SAXException ex) {
						throw new BuilderException(ex);
					}
				}
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, AD value, String localName)
			throws BuilderException {
			throw new BuilderException("AD cannot be a structural attribute");
		}
	}

	private ADPresenter() {}

	public static ADPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
																					Datatype datatype) {
		// do nothing with the datatype
		return new ADContentHandler(namespaceURI, localName, qName, atts);
	}

	public DatatypeBuilder getBuilder() {
		return new ADBuilder();
	}
}

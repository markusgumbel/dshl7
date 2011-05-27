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
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.IVL;
import org.hl7.types.ON;
import org.hl7.types.ONXP;
import org.hl7.types.TS;
import org.hl7.types.impl.ONimpl;
import org.hl7.types.impl.ONnull;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ONPresenter extends ENPresenter {

	private static final ONPresenter INSTANCE = new ONPresenter();

	protected static class ONContentHandler extends TreeContentHandler {
		public ONContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		protected void returnResult(Object intermediate) throws SAXException {
			Element element = (Element) intermediate;
			ON result = valueOf(element);
			super.returnResult(result);
		}

		ONXPPresenter.ONXPContentHandler _pnxpContentHandler = new ONXPPresenter.ONXPContentHandler(null,null,null,null);

		protected ON valueOf(Element element) {
			String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				return ONnull.valueOf(nullFlavorString);
			}
			String useValue = element.getAttributeValue(ATTR_USE);
			DSET<CS> use = CSPresenter.parseList(useValue, "EntityNameUse");
			Iterator<Element> it = element.findChildren(TAG_USEABLE_PERIOD);
			// NYI.
			// IVL<TS> validTime = it.hasNext() ? IVLPresenter.valueOf(it.next()) : null;
			IVL<TS> validTime = null;
			List<ONXP> nameParts = new ArrayList<ONXP>();
			for (Iterator<Node> it2 = element.getChildren().iterator(); it2.hasNext();) {
				Node node = (TreeContentHandler.Node) it2.next();
				ONXP part = (ONXP)_pnxpContentHandler.valueOf(node);
				if (part != null)
					nameParts.add(part);
			}
			return ONimpl.valueOf(nameParts, use, validTime);
		}
	}

	private static class ONBuilder extends ENPresenter.ENBuilder<ON> {
		private final ONXPPresenter.ONXPBuilder _partBuilder = (ONXPPresenter.ONXPBuilder) ONXPPresenter.instance().getBuilder();

		protected ONXPPresenter.ONXPBuilder partBuilder() {
			return _partBuilder;
		}
	}

	private ONPresenter() {}

	public static ONPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		// do nothing with the datatype
		return new ONContentHandler(namespaceURI, localName, qName, atts);
	}

	public DatatypeBuilder getBuilder() {
		return new ONBuilder();
	}
}

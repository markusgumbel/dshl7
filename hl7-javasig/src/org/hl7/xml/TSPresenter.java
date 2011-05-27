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
import org.hl7.types.TS;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.TSjuDateAdapter;
import org.hl7.types.impl.TSnull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Eric Chen
 * @version $Id: TSPresenter.java 5921 2007-05-15 04:12:30Z gschadow $
 */
public class TSPresenter extends DatatypePresenterBase {

	public static final String ATTR_VALUE = "value";

	private static final TSPresenter INSTANCE = new TSPresenter();

	protected static class TSContentHandler extends SimpleTypeContentHandler {
		TS _result = null;

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = TSnull.valueOf(nullFlavorString);
			} else {
				String valueString = atts.getValue(ATTR_VALUE);
				if (valueString != null) {
					_result = ValueFactory.getInstance().TSvalueOfLiteral(valueString);
				}
			}
		}

		protected Object getResult() {
			return (_result == null) ? TSnull.NI : _result;
		}

		protected static TS valueOf(TreeContentHandler.Node node) {
			if (node instanceof TreeContentHandler.Element) {
				TreeContentHandler.Element element = (TreeContentHandler.Element) node;
				String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
				if (nullFlavorString != null) {
					return TSnull.valueOf(nullFlavorString);
				}
				String value = element.getAttributeValue(ATTR_VALUE);
				return TSjuDateAdapter.valueOf(value);
			} else
				return TSnull.NI;
		}
	}

	private static class TSBuilder implements DatatypeBuilder<TS> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, TS value, String localName) throws BuilderException {
			try {
				TS ts = (TS) value;
				// WHAT A HACK!!! WHY? builder.setShouldSetXsiType(false);
				// builder.setShouldSetXsiType(false);
				if (builder.nullValueHandled(ts, localName))
					return;
				// builder.setShouldSetXsiType(true);
				builder.addAttribute(ATTR_VALUE, ts);
				builder.startElement(localName);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, TS value, String localName)
				throws BuilderException {
			throw new BuilderException("TS cannot be a structural attribute");
		}
	}

	private TSPresenter() {}

	public static TSPresenter instance() {
		return INSTANCE;
	}

	public static TS getValue(Object value) {
		if (value == null)
			return TSnull.NI;
		return ValueFactory.getInstance().TSvalueOfLiteral(value.toString());
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,	Datatype datatype) {
		return new TSContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new TSBuilder();
	}
}
/**
 * HISTORY : $Log$ HISTORY : Revision 1.15 2005/09/08 20:33:53 gschadow HISTORY : this should address the outstanding
 * SXCM awkwardness HISTORY : HISTORY : Revision 1.14 2005/09/08 19:37:06 echen HISTORY : Saved point HISTORY :
 */

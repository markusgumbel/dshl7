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
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.QSET;
import org.hl7.types.TEL;
import org.hl7.types.TS;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.TELimpl;
import org.hl7.types.impl.TELnull;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.DataTypeContentHandler;
import org.hl7.xml.parser.DynamicContentHandler;
import org.hl7.xml.parser.IgnoreContentHandler;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * This little guy knows how to build TEL values.
 * 
 * FIXME: useablePeriod not supported here.
 */
public class TELPresenter extends DatatypePresenterBase {
	public static final String ATTR_VALUE = "value";
	public static final String ATTR_USE = "use";
	public static final String TAG_USEABLE_PERIOD = "useablePeriod";
	private static final TELPresenter INSTANCE = new TELPresenter();

	private static class TELContentHandler extends DataTypeContentHandler implements DynamicContentHandler,
			DynamicContentHandler.ResultReceiver {
		private TEL _result = null;
		private String _urlString = null;
		private String _useString = null;
		private QSET<TS> _useablePeriod = null;

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = TELnull.valueOf(nullFlavorString);
			} else {
				_urlString = atts.getValue(ATTR_VALUE);
				_useString = atts.getValue(ATTR_USE);
			}
		}

		@SuppressWarnings("unchecked")
		public void notifyResult(Object result) {
			if (_currentLocalName == TAG_USEABLE_PERIOD) {
				if (result instanceof QSET) {
					_useablePeriod = (QSET<TS>) result;
				} else if (result instanceof TS) {
					_useablePeriod = ValueFactory.getInstance().QSETvalueOf((TS) result);
				} else {
					//
				}
			}
		}

		private static final Datatype USEABLE_PERIOD_DATATYPE;
		static {
			try {
				USEABLE_PERIOD_DATATYPE = DatatypeMetadataFactoryImpl.instance().create("SET<TS>");
			} catch (Exception x) {
				throw new Error(x);
			}
		}
		private String _currentLocalName;

		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
			_currentLocalName = localName.intern();
			DynamicContentHandler newContext = null;
			if (_currentLocalName == TAG_USEABLE_PERIOD) {
				try {
					newContext = USEABLE_PERIOD_DATATYPE.getHandler(namespaceURI, localName, qName, atts);
				} catch (FactoryException x) {}
			}
			if (newContext == null)
				newContext = new IgnoreContentHandler();
			this.suspendWith(newContext, atts);
		}

		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			returnResult(getResult());
		}

		protected Object getResult() {
			if (_result == null) {
				if (_urlString != null)
					_result = TELimpl.valueOf(_urlString, _useString, _useablePeriod);
				else
					_result = TELnull.NI;
			}
			return _result;
		}
	}

	private static class TELBuilder implements DatatypeBuilder<TEL> {
		private final CSPresenter.CSBuilder csBuilder_ = (CSPresenter.CSBuilder) CSPresenter.instance().getBuilder();

		public void build(RimGraphXMLSpeaker.ContentBuilder builder, TEL value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				TEL tel = (TEL) value;
				if (tel instanceof TELimpl)
					builder.addAttribute(ATTR_VALUE, tel.scheme().code().toString() + ':' + tel.address());
				else
					builder.addAttribute(ATTR_VALUE, tel.address());
				csBuilder_.buildAttribute(builder, tel.use(), ATTR_USE);
				builder.startElement(localName);
				builder.build(tel.useablePeriod(), TAG_USEABLE_PERIOD);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, TEL value, String localName)
				throws BuilderException {
			throw new BuilderException("TEL cannot be a structural attribute");
		}
	}

	// -------------------------------------------------------------------------
	private TELPresenter() {}

	public static TELPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public DynamicContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new TELContentHandler();
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new TELBuilder();
	}

	protected static TEL valueOf(TreeContentHandler.Element element) {
		String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
		if (nullFlavorString != null) {
			return TELnull.valueOf(nullFlavorString);
		}
		String use = element.getAttributeValue(ATTR_USE);
		String value = element.getAttributeValue(ATTR_VALUE);
		// TODO: add support for nested "useablePeriod" (TAG_USEABLE_PERIOD)
		// try {
		return TELimpl.valueOf(value, use, null);
		// } catch (IllegalArgumentException e) {
		// return TELimplWithIrregularURL.valueOf(value, use, null);
		//}
	}
}

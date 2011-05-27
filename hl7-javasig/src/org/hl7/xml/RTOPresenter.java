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
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.QTY;
import org.hl7.types.RTO;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.RTOnull;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.DynamicContentHandler;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This little guy knows how to build reals. <p/> FIXME: defaults are not configurable.
 */
public class RTOPresenter extends DatatypePresenterBase {
	public static final String TAG_NUMERATOR = "numerator";
	public static final String TAG_DENOMINATOR = "denominator";
	public static final String XSI_TYPE_LOCAL_NAME = "type";
	// this is a singleton:
	private static final RTOPresenter INSTANCE = new RTOPresenter();

	private RTOPresenter() {}

	public static RTOPresenter instance() {
		return INSTANCE;
	}

	private static class RTOContentHandler extends SimpleTypeContentHandler implements
			DynamicContentHandler.ResultReceiver {
		// Expected RTO data type goes as follows:
		// first expectation may be set by the initialization of the RTOContentHandler
		// second expectation may be refined by the xsi:type sent in the RTO element
		// this sets the expectations for numerator and denominator, however, when we parse these components
		// each component may also have xsiType to refine the datatype
		// FIXME: we should check whether later specified types are valid constraints of the earlier specified types.
		ParametrizedDatatype _expectedRTODatatype = null;
		String _currentComponentName = null;
		RTO _result = null;
		QTY.diff _numerator = null;
		QTY.diff _denominator = null;
		boolean _parsingContent = false;

		private RTOContentHandler(ParametrizedDatatype datatype) {
			_expectedRTODatatype = datatype;
		}

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = RTOnull.valueOf(nullFlavorString);
				_parsingContent = false;
			} else
				_parsingContent = true;
			// Old comment: Nice trick to help with stand alone parsing. If the parent has an xsi:type, i.e. RTO_PQ_INT,
			// remember it just in case.
			// New comment: refine the type expectation from xsiType
			if (atts != null) {
				String xsiType = atts.getValue(DatatypePresenterBase.W3_XML_SCHEMA, XSI_TYPE_LOCAL_NAME);
				if (xsiType != null) {
					try {
						ParametrizedDatatype datatypeFromXsiType = (ParametrizedDatatype) DatatypeMetadataFactoryImpl.instance()
								.createByXsiType(xsiType);
						// XXX ADD: if(_expectedRTODatatype == null || datatypeFromXsiType.implies(_expectedRTODatatype)
						_expectedRTODatatype = datatypeFromXsiType;
					} catch (UnknownDatatypeException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		}

		protected Object getResult() {
			if (_result != null)
				return _result;
			else if (_numerator == null && _denominator == null)
				return RTOnull.NI;
			else
				return ValueFactory.getInstance().RTOvalueOf(_numerator, _denominator);
		}

		public void notifyResult(Object result) {
			if (_currentComponentName == TAG_NUMERATOR)
				_numerator = (QTY.diff) result;
			else if (_currentComponentName == TAG_DENOMINATOR)
				_denominator = (QTY.diff) result;
			else
				throw new Error("internal error, current component: " + _currentComponentName);
		}

		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
			if (_parsingContent) {
				localName = localName.intern();
				if (localName == TAG_NUMERATOR || localName == TAG_DENOMINATOR) {
					_currentComponentName = localName;
					// now we figure out the actually effective data type which we need to parse
					Datatype effectiveComponentDatatype = null;
					// initialize the effective component datatype from the effective RTO datatype
					if (_expectedRTODatatype != null) {
						if (_currentComponentName == TAG_NUMERATOR)
							effectiveComponentDatatype = _expectedRTODatatype.getParameter(0);
						if (_currentComponentName == TAG_DENOMINATOR)
							effectiveComponentDatatype = _expectedRTODatatype.getParameter(1);
					}
					if (atts != null) {
						String xsiType = atts.getValue(DatatypePresenterBase.W3_XML_SCHEMA, XSI_TYPE_LOCAL_NAME);
						if (xsiType != null) {
							try {
								Datatype datatypeFromXsiType = DatatypeMetadataFactoryImpl.instance().createByXsiType(xsiType);
								// XXX ADD: if(effectiveComponentDatatype == null ||
								// datatypeFromXsiType.implies(effectiveComponentDatatype)
								effectiveComponentDatatype = datatypeFromXsiType;
							} catch (UnknownDatatypeException ex) {
								throw new RuntimeException(ex);
							}
						}
					}
					if (effectiveComponentDatatype == null)
						throw new RuntimeException("data type to parse could not be determined");
					try {
						suspendWith(effectiveComponentDatatype.getHandler(namespaceURI, localName, qName, atts), atts);
					} catch (FactoryException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		}
	}

	private static class RTOBuilder implements DatatypeBuilder<RTO> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, RTO value, String localName) throws BuilderException {
			try {
				RTO rtoValue = (RTO) value;
				if (builder.nullValueHandled(value, localName))
					return;
				builder.startElement(localName);
				QTY.diff numerator = rtoValue.numerator();
				QTY.diff denominator = rtoValue.denominator();
				builder.setTypeConstraint(DatatypeMetadataFactoryDatatypes.instance().QTYTYPE);
				QTYPresenter.instance().getBuilder().build(builder, numerator, TAG_NUMERATOR);
				builder.setTypeConstraint(DatatypeMetadataFactoryDatatypes.instance().QTYTYPE);
				QTYPresenter.instance().getBuilder().build(builder, denominator, TAG_DENOMINATOR);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, RTO value, String localName)
				throws BuilderException {
			throw new BuilderException("RTO cannot be a structural attribute");
		}
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new RTOContentHandler((ParametrizedDatatype) datatype);
	}

	public DatatypeBuilder getBuilder() {
		return new RTOBuilder();
	}
}

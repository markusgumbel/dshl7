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

import java.util.logging.Logger;

import org.hl7.meta.Datatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.CS;
import org.hl7.types.PQ;
import org.hl7.types.QTY;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.CSimpl;
import org.hl7.types.impl.MOimpl;
import org.hl7.types.impl.PQimpl;
import org.hl7.types.impl.QTYnull;
import org.hl7.types.impl.TSjuDateAdapter;
import org.hl7.util.DatatypeAnalyzer.AnalysisException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.DataTypeContentHandler;
import org.hl7.xml.parser.DynamicContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class QTYPresenter extends DatatypePresenterBase {
	// -------------------------------------------------------------------------
	public static final String ATTR_UNIT = "unit";
	public static final String ATTR_VALUE = "value";
	public static final String ATTR_CURRENCY = "currency";
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml");
	// -------------------------------------------------------------------------
	private static final QTYPresenter INSTANCE = new QTYPresenter();

	// -------------------------------------------------------------------------
	protected static class QTYContentHandler extends DataTypeContentHandler implements
			DynamicContentHandler.ResultReceiver {
		// .......................................................................
		QTY _result = null;
		Datatype _datatype = null;
		String _unit = null;
		String _value = null;
		String _nullFlavorString = null;
		PQ _numerator = null;
		PQ _denominator = null;
		CS _currency = null;

		private QTYContentHandler(Datatype datatype) {
			super();
			_datatype = datatype;
		}

		/*******************************************************************************************************************
		 * startElement:
		 * 
		 * Should never get called because a QTY is specified with attributes
		 ******************************************************************************************************************/
		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {}

		/*******************************************************************************************************************
		 * endElement:
		 * 
		 * Must determine which Presenter should do the work. All possible choices are: TS, INT, REAL, RTO, PQ, MO.
		 ******************************************************************************************************************/
		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			if (_nullFlavorString != null) {
				_result = QTYnull.valueOf(_nullFlavorString);
			}
			if (_datatype.equals(DatatypeMetadataFactoryDatatypes.instance().TSTYPE)) {
				_result = TSjuDateAdapter.valueOf(_value);
			} else if (_datatype.equals(DatatypeMetadataFactoryDatatypes.instance().INTTYPE)) {
				_result = ValueFactory.getInstance().INTvalueOfLiteral(_value);
			} else if (_datatype.equals(DatatypeMetadataFactoryDatatypes.instance().REALTYPE)) {
				_result = ValueFactory.getInstance().REALvalueOfLiteral(_value);
			} else if (_datatype.equals(DatatypeMetadataFactoryDatatypes.instance().RTOTYPE)) {
				_result = ValueFactory.getInstance().RTOvalueOf((QTY.diff) _numerator, (QTY.diff) _denominator);
			} else if (_datatype.equals(DatatypeMetadataFactoryDatatypes.instance().PQTYPE)) {
				_result = PQimpl.valueOf(_value, _unit);
			} else if (_datatype.equals(DatatypeMetadataFactoryDatatypes.instance().MOTYPE)) {
				_result = MOimpl.valueOf(ValueFactory.getInstance().REALvalueOfLiteral(_value), _currency);
			}
			returnResult(_result);
		}

		// Never will be called
		public void notifyResult(Object result) {}

		/*******************************************************************************************************************
		 * notifyActivation
		 * 
		 * Our chance to set the attributes. Must set alignment, operator, and institutionSpecified attributes.
		 ******************************************************************************************************************/
		protected void notifyActivation(Attributes atts) {
			_nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			_value = atts.getValue(ATTR_VALUE);
			_unit = atts.getValue(ATTR_UNIT);
			_numerator = ValueFactory.getInstance().PQvalueOfLiteral(atts.getValue(RTOPresenter.TAG_NUMERATOR));
			_denominator = ValueFactory.getInstance().PQvalueOfLiteral(atts.getValue(RTOPresenter.TAG_DENOMINATOR));
			_currency = CSimpl.valueOf(atts.getValue(ATTR_CURRENCY), "unknown");
		}

		protected Object getResult() {
			return (this._result == null) ? QTYnull.NI : _result;
		}
	}

	// --------------------------------------------------------------------------
	private static class QTYBuilder implements DatatypeBuilder<QTY> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, QTY value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				Datatype actualType = null;
				try {
					actualType = DatatypeMetadataFactoryImpl.instance().createFromValue(value);
				} catch (AnalysisException ex) {
					new AnalysisException(ex);
				} catch (UnknownDatatypeException ex) {
					new UnknownDatatypeException(ex.toString());
				}
				// let the approprated builder handle building
				// XXX: change me to use the Datatype.equals method once it is implemented
				if (actualType.equals(DatatypeMetadataFactoryDatatypes.instance().TSTYPE)) {
					TSPresenter.instance().getBuilder().build(builder, value, localName);
				} else if (actualType.equals(DatatypeMetadataFactoryDatatypes.instance().INTTYPE)) {
					INTPresenter.instance().getBuilder().build(builder, value, localName);
				} else if (actualType.equals(DatatypeMetadataFactoryDatatypes.instance().REALTYPE)) {
					REALPresenter.instance().getBuilder().build(builder, value, localName);
				} else if (actualType.equals(DatatypeMetadataFactoryDatatypes.instance().RTOTYPE)) {
					RTOPresenter.instance().getBuilder().build(builder, value, localName);
				} else if (actualType.equals(DatatypeMetadataFactoryDatatypes.instance().PQTYPE)) {
					PQPresenter.instance().getBuilder().build(builder, value, localName);
				} else if (actualType.equals(DatatypeMetadataFactoryDatatypes.instance().MOTYPE)) {
					MOPresenter.instance().getBuilder().build(builder, value, localName);
				}
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, QTY value, String localName)
				throws BuilderException {
			throw new BuilderException("QTY cannot be a structural attribute");
		}
	}

	// --------------------------------------------------------------------------
	private QTYPresenter() {}

	public static QTYPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public DynamicContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new QTYContentHandler(datatype);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new QTYBuilder();
	}
}

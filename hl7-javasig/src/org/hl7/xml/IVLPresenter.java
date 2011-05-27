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
 * The Initial Developer of the Original Code is Skirmantas Kligys.
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): Eric Chen
 */
package org.hl7.xml;

import static org.hl7.xml.IVLPresenter.Child.LOW;
import static org.hl7.xml.IVLPresenter.Child.evalChild;

import java.util.logging.Logger;

import org.hl7.meta.Datatype;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.SimpleDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.meta.impl.SimpleDatatypeImpl;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.IVL;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.ValueFactory;
import org.hl7.types.enums.SetOperator;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.IVLimpl;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.QTYnull;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.DataTypeContentHandler;
import org.hl7.xml.parser.DynamicContentHandler;
import org.hl7.xml.parser.IgnoreContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class IVLPresenter extends DatatypePresenterBase {
	// XML sub element will start from TAG_
	public static final String TAG_LOW = "low";
	public static final String TAG_CENTER = "center";
	public static final String TAG_WIDTH = "width";
	public static final String TAG_HIGH = "high";
	public static final String ATTR_HIGHCLOSED = "highClosed";
	public static final String ATTR_LOWCLOSED = "lowClosed";
	public static final String INCLUSIVE = "inclusive"; // Take the prefix 'TAG_' out. Inclusive acctually is special
	// because it does appear at IVL but at low, high element
	public static final String ATTR_OPERATOR = "operator";
	public static final String ATTR_VALUE = "value";
	public static final String ATTR_UNIT = "unit";
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml");

	public static enum Child {
		LOW, LOW_HIGH, LOW_WIDTH, HIGH, HIGH_WIDTH, CENTER, CENTER_WIDTH, WIDTH, NULL_NI;
		// Determine the combinatoin type of Children todo: improve to bit operation??
		public static Child evalChild(QTY low, QTY high, QTY center, QTY.diff width) {
			if (isNotNull(low) && isNull(high) && isNull(center) && isNull(width))
				return Child.LOW;
			if (isNotNull(low) && isNotNull(high) && isNull(center) && isNull(width))
				return Child.LOW_HIGH;
			if (isNotNull(low) && isNull(high) && isNull(center) && isNotNull(width))
				return Child.LOW_WIDTH;
			if (isNull(low) && isNotNull(high) && isNull(center) && isNull(width))
				return Child.HIGH;
			if (isNull(low) && isNotNull(high) && isNull(center) && isNotNull(width))
				return Child.HIGH_WIDTH;
			if (isNull(low) && isNull(high) && isNotNull(center) && isNull(width))
				return Child.CENTER;
			if (isNull(low) && isNull(high) && isNotNull(center) && isNotNull(width))
				return Child.CENTER_WIDTH;
			if (isNull(low) && isNull(high) && isNull(center) && isNotNull(width))
				return Child.WIDTH;
			if (isNotNull(low) && isNotNull(high) && isNotNull(center) && isNotNull(width))
				return Child.LOW_HIGH;
			if (isNull(low) && isNull(high) && isNull(center) && isNull(width))
				return Child.NULL_NI;
			throw new RuntimeException("Unknown IVL Children Combination: " + "low=" + low + " high=" + high + " center="
					+ center + " width=" + width);
		}

		private static boolean isNull(ANY any) {
			return any == null || any.isNullJ();
		}

		private static boolean isNotNull(ANY any) {
			return any != null && !any.isNullJ();
		}
	}

	private static Child child = LOW;

	public static Child getChild() {
		return child;
	}

	public static void setChild(Child c) {
		child = c;
	}

	public static IVL getIVLValue(BL lowClosed, QTY low, BL highClosed, QTY high, QTY.diff width, QTY center) {
		Child c = evalChild(low, high, center, width);
		switch (c) {
		case LOW:
			return IVLimpl.valueOf(lowClosed, low, QTYnull.NI, highClosed);
		case LOW_HIGH:
			return IVLimpl.valueOf(lowClosed, low, high, highClosed);
		case LOW_WIDTH:
			return IVLimpl.valueOf(lowClosed, highClosed, low, width);
		case HIGH:
			return IVLimpl.valueOf(lowClosed, (QTY) QTYnull.NI, high, highClosed);
		case HIGH_WIDTH:
			return IVLimpl.valueOf(lowClosed, width, highClosed, high);
		case CENTER:
			return IVLimpl.valueOf(center, QTYnull.NI, lowClosed, highClosed);
		case CENTER_WIDTH:
			return IVLimpl.valueOf(center, width, lowClosed, highClosed);
		case WIDTH:
			return IVLimpl.valueOf(QTYnull.NI, width, lowClosed, highClosed);
		case NULL_NI:
			return IVLimpl.valueOf(BLimpl.NI, QTYnull.NI, QTYnull.NI, BLimpl.NI);
		}
		throw new Error("Unknown IVL children combination: " + c);
	}

	public static IVL<QTY> getIVLValue(String valueString, String unitString, Datatype datatype) {
		// Here we are assuming that if a value is specified as an attribute we are intended to handle something of the flavor:
		// <doseQuantity xsi:type="IVL_PQ" value="250" unit="mg"/>
		// and turn this into an IVL with low and high set to 250 mg. 
		// It is never the case that a value is specified as an attribute and low/high are also specified.
		// The parameter type is used to parse the value. If the parameter type isn't known for some reason, 
		// it is assumed that if a value and a unit is specified, it is a PQ. If only a value is specified, it is a TS.

		// XXX: GS 11/5/2007: I don't think we should be doing this? Why are we doing it? We can't be sure about the type.

		QTY low = null, high = null;
		if (valueString != null) {
			Datatype paramType = null;
			if (datatype instanceof ParametrizedDatatype)
				paramType = ((ParametrizedDatatype) datatype).getParameter(0);
			if (paramType == null) {
				if (unitString != null) {
					low = ValueFactory.getInstance().PQvalueOf(valueString, unitString);
					high = ValueFactory.getInstance().PQvalueOf(valueString, unitString);
				} else { // FIXME: if they didn't specify the @unit attribute because unit is 1, what then?
					low = ValueFactory.getInstance().TSvalueOfLiteral(valueString);
					high = ValueFactory.getInstance().TSvalueOfLiteral(valueString);
				}
			} else {
				if (paramType.equals(DatatypeMetadataFactoryDatatypes.instance().TSTYPE)) {
					low = ValueFactory.getInstance().TSvalueOfLiteral(valueString);
					high = ValueFactory.getInstance().TSvalueOfLiteral(valueString);
				} else if (paramType.equals(DatatypeMetadataFactoryDatatypes.instance().PQTYPE)) {
					low = ValueFactory.getInstance().PQvalueOf(valueString, unitString != null ? unitString : "1");
					high = ValueFactory.getInstance().PQvalueOf(valueString, unitString != null ? unitString : "1");
				}
			}
		} else {
			return IVLnull.NI;
		}
		/** we have a special IVL where low and high are the same * */
		return IVLimpl.valueOf(BLimpl.TRUE, low, high, BLimpl.TRUE);
	}

	private static final IVLPresenter INSTANCE = new IVLPresenter();

	public static class IVLContentHandler extends DataTypeContentHandler implements DynamicContentHandler.ResultReceiver {
		QSET _result = null;
		QTY _high = null;
		QTY _low = null;
		QTY _center = null;
		QTY.diff _width = null;
		BL _lowClosed = BLimpl.TRUE; // initialized to avoid null pointer exception (UGLY)
		BL _highClosed = BLimpl.TRUE; // initialized to avoid null pointer exception (UGLY)
		SetOperator _operator = null;
		String _value = null;
		String _unit = null;
		Datatype _datatype;
		String _currentLocalName = null;

		private IVLContentHandler(Datatype datatype) {
			super();
			_datatype = datatype;
		}


		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
			// Called for the components of an IVL. The datatype should never be null when startElement is called, 
			// so an error is thrown when this occurs.
			// The datatype is used to determine the ContentHandler for low and high.
			
			if (TAG_LOW.equals(localName)) {
				String closed = atts.getValue(INCLUSIVE);
				if (closed != null)
					_lowClosed = BLimpl.valueOf(closed);
			}
			if (TAG_HIGH.equals(localName)) {
				String closed = atts.getValue(INCLUSIVE);
				if (closed != null)
					_highClosed = BLimpl.valueOf(closed);
			}
			DynamicContentHandler newContext = null;
			_currentLocalName = localName.intern();
			if (_datatype == null) {
				throw new UnsupportedOperationException("_datatype is null in IVLPresenter");
			}

			/*
			 * This deserves a little explanation. If we are parsing the width of an IVL, the type of the difference is used
			 * to delegate the handler. Otherwise, the datatype is used. Beacause IVL is a SimpleDatatype rather than a
			 * ParametrizedDatatype (why is that btw?), we need to correct the datatype using the xsi type. In this case, the
			 * xsi type is used to make the decision as to which handler is called.
			 * 
			 * FIXME: YEAH, WHY IS IVL A SIMPLE TYPE????
			 */
			try {
				if (_currentLocalName == TAG_WIDTH) {
					Datatype diffDatatype = _datatype.diff();
					newContext = diffDatatype.getHandler(namespaceURI, localName, qName, atts);
				} else if (_currentLocalName == TAG_LOW || _currentLocalName == TAG_HIGH || _currentLocalName == TAG_CENTER) {

					// FIXME!!!! WHAT AN AWFUL HACK!
					// quick correction when the datatype is a SimpleDatatype IVL without the parameter

					if (_datatype instanceof SimpleDatatypeImpl && _datatype.equals(DatatypeMetadataFactoryDatatypes.instance().IVL_GENERIC_TYPE)) {
						// this creates an IVL datatype with parameter inferred from the data
						_datatype = DatatypeMetadataFactoryImpl.instance().createByXsiType(((SimpleDatatypeImpl) _datatype).getXsiTypeString());
					}

					if (_datatype instanceof SimpleDatatype) {
						newContext = _datatype.getHandler(namespaceURI, localName, qName, atts);
					} else if (_datatype instanceof ParametrizedDatatype)
						newContext = ((ParametrizedDatatype) _datatype).getParameter(0).getHandler(namespaceURI, localName, qName, atts);
					else
						throw new UnsupportedOperationException("unknown datatype:" + _datatype.getFullName());
				} else
					throw new UnsupportedOperationException("unknown localName:" + _currentLocalName);
			} catch (FactoryException ex) {} catch (UnknownDatatypeException udte) {
				udte.printStackTrace();
			}
			if (newContext != null) {
				this.suspendWith(newContext, atts);
			} else {
				LOGGER.warning("no content handler for data type " + _datatype.getFullName() + " ignoring " + localName);
				this.suspendWith(new IgnoreContentHandler(), atts);
			}
		}

		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			// This must get the IVL value, handle the operator attribute, and then return the _result.
			// If a value attribute has been specified, we already have the result in _result. 
			// Otherwise we have a "real" IVL	and we must get the do some more work to get the value
			if (_result == null)
				_result = getIVLValue(_lowClosed, _low, _highClosed, _high, _width, _center);
			if(_operator != null)
				_result.setOperator(_operator);
			returnResult(_result);
		}

		public void notifyResult(Object result) {
			if (_currentLocalName == TAG_LOW)
				_low = (QTY) result;
			else if (_currentLocalName == TAG_HIGH)
				_high = (QTY) result;
			else if (_currentLocalName == TAG_WIDTH)
				_width = (QTY.diff) result;
			else if (_currentLocalName == TAG_CENTER)
				_center = (QTY) result;
		}

		public Object getResult() throws SAXException {
			return _result;
		}

		// Our chance to set the attributes. Operator is the only valid attribute for IVL
		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if(nullFlavorString != null)
				_result = IVLnull.valueOf(nullFlavorString);
			else if(_value != null)
				_result = getIVLValue(atts.getValue(ATTR_VALUE), atts.getValue(ATTR_UNIT), _datatype);

			_operator = SetOperator.valueOf(ValueFactory.getInstance().STvalueOfLiteral(atts.getValue(ATTR_OPERATOR)));
		}
	}


	protected static class IVLBuilder implements DatatypeBuilder<IVL> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, IVL value, String localName) throws BuilderException {
			try {
				IVL ivl = (IVL) value;
				if (builder.nullValueHandled(value, localName))
					return;
				// get whatever XSI type was determined
				String determinedXsiType = builder.getAttributeValue(DatatypePresenterBase.W3_XML_SCHEMA, "type");
				// see continuation of this thought under WIDTH (only) form
					
				builder.startElement(localName);
				
				// NOTE: the effect of calling setChild here will always result in the child being of type LOW_HIGH. 
				// This is because low(), high(), center(), and width() are determined by one another if they are null. 
				// The result is an effectiveTime that has low and high.

				switch (evalChild(ivl.low(), ivl.high(), ivl.center(), (QTY.diff) ivl.width())) {
				case LOW:
					builder.addAttribute(INCLUSIVE, ivl.lowClosed());
					builder.build(ivl.low(), TAG_LOW);
					break;
				case LOW_HIGH:
					builder.addAttribute(INCLUSIVE, ivl.lowClosed());
					builder.build(ivl.low(), TAG_LOW);
					builder.addAttribute(INCLUSIVE, ivl.highClosed());
					builder.build(ivl.high(), TAG_HIGH);
					break;
				case LOW_WIDTH:
					builder.addAttribute(INCLUSIVE, ivl.lowClosed());
					builder.build(ivl.low(), TAG_LOW);					
					builder.build(ivl.width(), TAG_WIDTH);
					break;
				case HIGH:
					builder.addAttribute(INCLUSIVE, ivl.highClosed());
					builder.build(ivl.high(), TAG_HIGH);
					break;
				case HIGH_WIDTH:
					builder.addAttribute(INCLUSIVE, ivl.highClosed());
					builder.build(ivl.high(), TAG_HIGH);
					builder.build(ivl.width(), TAG_WIDTH);
					break;
				case CENTER:
					builder.build(ivl.center(), TAG_CENTER);
					break;
				case CENTER_WIDTH:
					builder.build(ivl.center(), TAG_CENTER);
					builder.build(ivl.width(), TAG_WIDTH);
					break;
				case WIDTH:
					QTY width = ivl.width();

					if(determinedXsiType.equals("IVL_QTY")) {
						// this will happen if the DatatypeMetadataFactory.createFromValue has returned IVL_QTY,
						// i.e. it could not get a value from a boundary either (has already tried any).
						// so we're checking width and then passing at least it:
						try {
							String xsiType = builder.convertNameToXsiType(DatatypeMetadataFactoryImpl.instance().createFromValue(width).getFullName());
							if(xsiType != null && !xsiType.equals("QTY")) // pass down the value to make sure it shows up
								builder.addAttribute(DatatypePresenterBase.W3_XML_SCHEMA, "type", "xsi:type", "CDATA", xsiType);
						} catch(Exception ex) { // if it fails we're out of luck anyway
						}
					}
					builder.build(width, TAG_WIDTH);
					break;
				case NULL_NI:
					break;
				}
				// builder.endElement(DatatypePresenter.HL7_URI,localName,localName);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, IVL value, String localName)
				throws BuilderException {
			throw new BuilderException("IVL<T> cannot be a structural attribute");
		}
	}


	protected IVLPresenter() {}

	public static IVLPresenter instance() {
		return INSTANCE;
	}


	public DynamicContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new IVLContentHandler(datatype);
	}


	public DatatypeBuilder getBuilder() {
		return new IVLBuilder();
	}
}

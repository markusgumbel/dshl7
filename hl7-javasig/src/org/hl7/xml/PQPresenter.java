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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hl7.meta.Datatype;
import org.hl7.types.PQ;
import org.hl7.types.SET;
import org.hl7.types.impl.PQRimpl;
import org.hl7.types.impl.PQimpl;
import org.hl7.types.impl.PQnull;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.IgnoreContentHandler;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.hl7.xml.parser.TreeContentHandler;
import org.regenstrief.ucum.Unit;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This little guy knows how to build PQ values.
 * 
 * FIXME: the PQR stuff is not supported here
 */
public class PQPresenter extends DatatypePresenterBase {
	// -------------------------------------------------------------------------
	public static final String ATTR_VALUE = "value";
	public static final String ATTR_UNIT = "unit";
	public static final String ATTR_ORIGINAL_UNIT = "originalUnit";
	public static final String ATTR__ORIGINAL_VALUE = "originalValue";
	public static final String ATTR_TRANSLATION = "translation";
	// static final Datatype PQTYPE = DatatypeMetadataFactoryImpl.instance().create("PQ");
	// -------------------------------------------------------------------------
	private static final PQPresenter INSTANCE = new PQPresenter();
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml");

	// -------------------------------------------------------------------------
	protected static class PQContentHandler extends SimpleTypeContentHandler {
		// .......................................................................
		PQ _result = null;
		Datatype _datatype = null;

		private PQContentHandler(Datatype datatype) {
			super();
			_datatype = datatype;
		}

		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
			if (ATTR_TRANSLATION.equals(localName)) {
				try {
					PQRPresenter.PQRContentHandler dch = (PQRPresenter.PQRContentHandler) DatatypePresenterFactory.getInstance()
							.createPresenter("PQR").getContentHandler(namespaceURI, localName, qName, atts, _datatype);
					dch.setResultReceiver(new ResultReceiver() {
						public void notifyResult(Object result) {
							PQRimpl pqr = (PQRimpl) result;
							if (!_result.isNullJ())
								((PQimpl) _result).addTranslation(pqr);
						}
					});
					this.suspendWith(dch, atts);
				} catch (Exception e) {
					// FIXME: why are we ignoring this?
					// FIXME: if we want to ignore erroneous translations, we should do this in PQRContentHandler
					e.printStackTrace();
				}
			} else {
				suspendWith(new IgnoreContentHandler(), null);
			}
		}

		// .......................................................................
		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = PQnull.valueOf(nullFlavorString);
			} else {
				String value = atts.getValue(ATTR_VALUE);
				String unit = atts.getValue(ATTR_UNIT);
				String originalUnit = atts.getValue(ATTR_ORIGINAL_UNIT);
				String originalValue = atts.getValue(ATTR__ORIGINAL_VALUE);
				if (value == null) {
					_result = PQnull.NI;
				} else {
					try {
						// the default unit is 1, this had been allowed at least in earlier drafts and some implementations rely on
						// it.
						// it also make sense, this way, a PQ is basically same as an REAL if no unit is provided.
						if (unit == null)
							unit = "1";
						_result = PQimpl.valueOf(value, unit);
					} catch (Unit.SyntaxException ex) {
						LOGGER.log(Level.WARNING, "Error with unit=\"" + unit + "\"", ex.getCause());
						_result = PQnull.NI;
					}
				}
			}
		}

		// .......................................................................
		protected Object getResult() {
			return (this._result == null) ? PQnull.NI : _result;
		}

		// .......................................................................
		protected static PQ valueOf(TreeContentHandler.Node node) {
			if (node instanceof TreeContentHandler.Element) {
				TreeContentHandler.Element element = (TreeContentHandler.Element) node;
				String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
				if (nullFlavorString != null) {
					return PQnull.valueOf(nullFlavorString);
				}
				String value = element.getAttributeValue(ATTR_VALUE);
				String unit = element.getAttributeValue(ATTR_UNIT);
				try {
					return PQimpl.valueOf(value, unit);
				} catch (Unit.SyntaxException ex) {
					LOGGER.log(Level.WARNING, "Error with data <unit=\"" + unit + "\"> because of " + ex.getMessage(), ex);
					return PQnull.NI;
				}
			} else
				return PQnull.NI;
		}
	}

	// --------------------------------------------------------------------------
	private static class PQBuilder implements DatatypeBuilder<PQ> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, PQ value, String localName) throws BuilderException {
			// FIXME: need to take care of infinite values better
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				PQ pqValue = (PQ) value;
				builder.addAttribute(ATTR_VALUE, pqValue.value());
				builder.addAttribute(ATTR_UNIT, pqValue.unit());
				// Build out <translation> if found.
				builder.startElement(localName);
				if (pqValue.translation() != null) {
					try {
						SET s = pqValue.translation();
						DatatypePresenterFactory.getInstance().createPresenter("SET").getBuilder().build(builder, s,
								ATTR_TRANSLATION);
					} catch (FactoryException e) {
						e.printStackTrace();
					}
				}
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, PQ value, String localName)
				throws BuilderException {
			throw new BuilderException("PQ cannot be a structural attribute");
		}
	}

	// --------------------------------------------------------------------------
	private PQPresenter() {}

	public static PQPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new PQContentHandler(datatype);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new PQBuilder();
	}
}

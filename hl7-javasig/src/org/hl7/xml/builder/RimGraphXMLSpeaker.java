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
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.xml.builder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hl7.meta.Attribute;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Datatype;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.rim.RimObject;
import org.hl7.types.ANY;
import org.hl7.util.FactoryException;
import org.hl7.util.DatatypeAnalyzer.AnalysisException;
import org.hl7.xml.DatatypePresenterBase;
import org.hl7.xml.DatatypePresenterFactory;
import org.hl7.xml.builder.impl.MessageBuilderImpl;
import org.xml.sax.SAXException;

/**
 * An XMLSpeaker that serializes HL7 RIM Object graphs.
 * 
 * XXX: this might better be combined with the MessageElementBuilder class.
 * 
 * @author Gunther Schadow
 * @version $Id: RimGraphXMLSpeaker.java 7766 2007-11-05 23:29:07Z gschadow $
 */
public class RimGraphXMLSpeaker extends XMLSpeaker {
	Datatype _typeConstraint;

	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml.builder");

	/**
	 * The ContentBuilder with more HL7 specific convenience methods added.
	 */
	public class ContentBuilder extends XMLSpeaker.ContentBuilder {
		protected ContentBuilder() {
			super();
			super.setNamespace(DatatypePresenterBase.HL7_URI);
		}

		/**
		 * Check if the value is a null value and if so, put the nullFlavor
		 * attribute on the attributes list. Also create the xsi:type
		 * attribute.
		 * 
		 * @param value
		 *            the value checked
		 * @return true if value was null and attribute added otherwise false
		 */
		public boolean nullValueHandled(ANY value) throws SAXException {
			Datatype typeConstraint = getTypeConstraint();
			Datatype actualType = null;
			try {
				actualType = DatatypeMetadataFactoryImpl.instance().createFromValue(value);
			}
			catch (AnalysisException ex) {
				throw new SAXException(ex);
			}
			catch (UnknownDatatypeException ex) {
				throw new SAXException(ex);
			}
			boolean isSetComponent = false;
			String passedDownXsiType = getAttributeValue(DatatypePresenterBase.W3_XML_SCHEMA, "type");
			if (passedDownXsiType == null) {
				if (actualType.getFullName().startsWith("RTO")) {
					this.addAttribute(DatatypePresenterBase.W3_XML_SCHEMA, "type", "xsi:type", "CDATA", "RTO");

				} else if (actualType.isCompleteTypeOf(DatatypeMetadataFactoryDatatypes.instance().IVL_GENERIC_TYPE) || actualType.isCompleteTypeOf(DatatypeMetadataFactoryDatatypes.instance().PIVL_GENERIC_TYPE)) {

					this.addAttribute(DatatypePresenterBase.W3_XML_SCHEMA, "type", "xsi:type", "CDATA", ((ParametrizedDatatype) actualType).getType() + "_" + ((ParametrizedDatatype) actualType).getParameter(0).getFullName());

				} else if (typeConstraint != null && !typeConstraint.equals(actualType)) { 

					String xsiType = convertNameToXsiType(actualType.getFullName());
					if (isSetComponent && !xsiType.contains("_")) this.addAttribute(DatatypePresenterBase.W3_XML_SCHEMA, "type", "xsi:type", "CDATA", "SXCM_" + xsiType);
					else this.addAttribute(DatatypePresenterBase.W3_XML_SCHEMA, "type", "xsi:type", "CDATA", xsiType);
				}
			}
			if (value.isNullJ()) {
				addAttribute("nullFlavor", value.nullFlavor().code().toString());
				return true;
			}
			else {
				return false;
			}
		}

		/** This is a bad hack. Metadata should do this. Some helper, something other than this.
		 * The implementation is bad, but still I make it public because it's better to have one 
		 * bad implementation than 2 or 3 bad implementations.
		 * HACK - only used in above code. If name is RTO<PQ,INT> will return
		 * RTO_PQ_INT
		 *
		 * @deprecated
		 * FIXME!!!
		 */
		public String convertNameToXsiType(String name) {
			name = name.replaceAll("<", "_");
			name = name.replaceAll(">", "_");
			name = name.replaceAll(",", "_");
			if (name.endsWith("_")) name = name.substring(0, name.lastIndexOf("_"));

			return name;
		}

		/**
		 * Check if the value is a null value and if so, put the nullFlavor
		 * attribute on the attributes list and emit an otherwise empty element.
		 * 
		 * @param value
		 *            the value checked
		 * @param localName
		 *            the localName of the XML element
		 * @return true if value was null and attribute added otherwise false
		 */
		public boolean nullValueHandled(ANY value, String localName) throws SAXException {
			if (nullValueHandled(value)) {
				startElement(localName);
				endElement(localName);
				return true;
			}
			else return false;
		}

		/**
		 * If the value is not null and has no null flavor, add it as an
		 * attribute with given local name and no namespace.
		 */
		public void addAttribute(String localName, ANY value) {
			if (value != null && !value.isNullJ()) {
				addAttribute(localName, value.toString());
			}
		}

		public void setTypeConstraint(Datatype typeConstraint) {
			_typeConstraint = typeConstraint;
		}

		public Datatype getTypeConstraint() {
			return _typeConstraint;
		}

		protected void clearAttributes() {
			super.clearAttributes();
			_typeConstraint = null;
		}

		/**
		 * Called by Message Builder
		 * 
		 * @param value
		 * @param attribute
		 * @throws SAXException
		 */
		public void build(ANY value, Attribute attribute) throws SAXException {
			if (value == null) return;
			Datatype datatype = attribute.getDatatype();
			setTypeConstraint(datatype);
			build(value, attribute.getName());
		}

		/**
		 * Build a component.
		 * 
		 * @param value
		 *            the value of the component
		 * @param localName
		 *            the localName of the XML element
		 */
		public void build(ANY value, String localName) throws SAXException {
			if (value == null) return;

			try {
				DatatypeBuilder builder = DatatypePresenterFactory.getInstance().createPresenter(value).getBuilder();
				builder.build(this, value, localName);
			}
			catch (FactoryException ex) {
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				throw new SAXException(ex);
			}
			catch (BuilderException ex) {
				if (ex.getCause() == null) {
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
					throw new SAXException(ex);
				}
				else if (ex.getCause() instanceof SAXException) {
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex.getCause());
					throw (SAXException) ex.getCause();
				}
				else if (ex.getCause() instanceof Exception) {
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex.getCause());
					throw new SAXException((Exception) ex.getCause());
				}
				else {
					LOGGER.log(Level.SEVERE, ex.getMessage(), ex.getCause());
					throw new SAXException(ex);
				}
			}
		}
	}

	/**
	 * A specialization of the standard SAX InputSource that is passed to the
	 * parse() method to begin serialization.
	 */
	public static class InputSource extends org.xml.sax.InputSource {
		private RimObject _entryPoint = null;
		private CloneClass _cloneClass = null;

		public InputSource(RimObject entryPoint, CloneClass cloneClass) {
			_entryPoint = entryPoint;
			_cloneClass = cloneClass;
		}

		public void setEntryPoint(RimObject entryPoint) {
			_entryPoint = entryPoint;
		}

		public RimObject getEntryPoint() {
			return _entryPoint;
		}

		public void setCloneClass(CloneClass cloneClass) {
			_cloneClass = cloneClass;
		}

		public CloneClass getCloneClass() {
			return _cloneClass;
		}
	}

	/**
	 * Another specialization of the standard SAX InputSource that is passed to
	 * the parse() method to begin serialization of just a standalone data
	 * value.
	 */
	public static class DataValueInputSource extends org.xml.sax.InputSource {
		public DataValueInputSource(ANY value, String localName, Datatype typeConstraint, String wrappingElementName) {
			_typeConstraint = typeConstraint;
			_value = value;
			_localName = localName;
			_wrappingElementName = wrappingElementName;
		}

		public DataValueInputSource(ANY value, String localName, Datatype typeConstraint) {
			_typeConstraint = typeConstraint;
			_value = value;
			_localName = localName;
		}

		public Datatype getTypeConstraint() {
			return _typeConstraint;
		}

		private Datatype _typeConstraint = null;
		private ANY _value = null;

		public void setValue(ANY value) {
			_value = value;
		}

		public ANY getValue() {
			return _value;
		}

		private String _localName = null;

		public void setLocalName(String localName) {
			_localName = localName;
		}

		public String getLocalName() {
			return _localName;
		}

		private String _wrappingElementName = null;

		public String getWrappingElementName() {
			return _wrappingElementName;
		}
	}

	/**
	 * Start generating SAX events from the given InputSource.
	 * 
	 * @see org.hl7.xml.builder.XMLSpeaker#parse
	 */
	public void parse(org.xml.sax.InputSource input) throws IOException, SAXException {
		try {
			if (input instanceof RimGraphXMLSpeaker.InputSource) {
				RimGraphXMLSpeaker.InputSource oginput = (RimGraphXMLSpeaker.InputSource) input;
				RimGraphXMLSpeaker.ContentBuilder contentBuilder = new RimGraphXMLSpeaker.ContentBuilder();
				contentBuilder.addAttribute("", "xsi", "xmlns:xsi", "CDATA", DatatypePresenterBase.W3_XML_SCHEMA);                
				// 
				// Get the clone class of the specialization so that the element has the correct tag.
				RimObject rimObject = oginput.getEntryPoint();
				String cloneCode = rimObject.getCloneCode().toString();
				CloneClass cloneClass = oginput.getCloneClass();
				CloneClass specializedCloneClass = cloneClass.getSpecialization(cloneCode);
				if (specializedCloneClass != null) {
					cloneClass = specializedCloneClass;
				}
                
				new MessageBuilderImpl(/* laxMode: */true).build(contentBuilder, rimObject, cloneClass, cloneClass.getName());
			}
			else if (input instanceof RimGraphXMLSpeaker.DataValueInputSource) {
				RimGraphXMLSpeaker.DataValueInputSource dvinput = (RimGraphXMLSpeaker.DataValueInputSource) input;
				RimGraphXMLSpeaker.ContentBuilder contentBuilder = new RimGraphXMLSpeaker.ContentBuilder();
				// contentBuilder.startDocument();
				contentBuilder.addAttribute("", "xsi", "xmlns:xsi", "CDATA", DatatypePresenterBase.W3_XML_SCHEMA);
				String wrappingElementName = dvinput.getWrappingElementName();
				if (wrappingElementName != null) contentBuilder.startElement(wrappingElementName);
				contentBuilder.setTypeConstraint(dvinput.getTypeConstraint());
				final ANY value = dvinput.getValue();
				DatatypePresenterFactory.getInstance().createPresenter(value).getBuilder().build(contentBuilder, value, dvinput.getLocalName());
				if (wrappingElementName != null) contentBuilder.endElement(wrappingElementName);
				// contentBuilder.endDocument();
			}
			else {
				// XXX: we could switch to normal XML parsing if input is a
				// stream?
				throw new IllegalArgumentException("input must be " + RimGraphXMLSpeaker.InputSource.class.getName());
			}
		}
		catch (FactoryException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw new SAXException(ex);
		}
		catch (BuilderException ex) {
			if (ex.getCause() == null) {
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
				throw new SAXException(ex);
			}
			else if (ex.getCause() instanceof SAXException) {
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex.getCause());
				throw (SAXException) ex.getCause();
			}
			else if (ex.getCause() instanceof Exception) {
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex.getCause());
				throw new SAXException((Exception) ex.getCause());
			}
			else {
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex.getCause());
				throw new SAXException(ex);
			}
		}
	}

	/**
	 * Parse from a URL. This URL could be a special scheme (e.g. like XPointer)
	 * that would denote an entryPoint and cloneClass, but that is not yet
	 * implemented.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public void parse(String url) throws IOException, SAXException {
		throw new UnsupportedOperationException();
	}
}
/**
 * HISTORY : $Log$ HISTORY : Revision 1.43 2006/06/11 22:17:24 echen HISTORY :
 * Fix print out QSETnull error HISTORY : HISTORY : Revision 1.42 2006/06/11
 * 03:26:44 echen HISTORY : Performer input text missing one attribute HISTORY :
 * HISTORY : Revision 1.41 2006/06/10 23:34:43 echen HISTORY : Change RTO xml
 * output from HISTORY : <quantity xsi:type="RTO_INT_INT"> HISTORY : <numerator
 * value="1"/> HISTORY : <denominator value="100"/> HISTORY : </quantity>
 * HISTORY : to HISTORY : <quantity>
 * HISTORY      :          <numerator xsi:type="INT" value="1"/>
 * HISTORY      :          <denominator xsi:type="INT" value="100"/>
 * HISTORY      :       </quantity>
 * HISTORY      : because xml datatype schema does not fully support RTO related type
 * HISTORY      :
 * HISTORY      : Revision 1.40  2006/04/19 20:43:49  gschadow
 * HISTORY      : CR: REAL's that have precision always have a decimal point, even if there is no value after the decimal point. (ex. 180 with precision 3 --> 180.)
 * HISTORY      :
 * HISTORY      : Revision 1.39  2006/04/14 18:04:53  gschadow
 * HISTORY      : problems with serializing null values to the database fixed.
 * HISTORY      : an attempt to prevent infinite recursion through object graph cycles
 * HISTORY      :
 * HISTORY      : Revision 1.38  2005/12/27 15:26:22  echen
 * HISTORY      : Add history comment
 * HISTORY      :
 */

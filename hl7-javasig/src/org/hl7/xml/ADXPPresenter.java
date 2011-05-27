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

import java.util.HashMap;
import java.util.Map;

import org.hl7.meta.Datatype;
import org.hl7.types.ADXP;
import org.hl7.types.ValueFactory;
import org.hl7.types.enums.AddressPartType;
import org.hl7.types.impl.ADXPimpl;
import org.hl7.types.impl.ADXPnull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ADXPPresenter extends STPresenter {
	protected static final String ATTR_PART_TYPE = "partType";

	/** Maps local element names into part types. */
	public static final Map<String, AddressPartType> TAGS_TO_PART_TYPES = new HashMap<String, AddressPartType>();
	static {
		TAGS_TO_PART_TYPES.put("delimiter", AddressPartType.Delimiter);
		TAGS_TO_PART_TYPES.put("country", AddressPartType.Country);
		TAGS_TO_PART_TYPES.put("state", AddressPartType.StateOrProvince);
		TAGS_TO_PART_TYPES.put("county", AddressPartType.CountyOrParish);
		TAGS_TO_PART_TYPES.put("city", AddressPartType.Municipality);
		TAGS_TO_PART_TYPES.put("postalCode", AddressPartType.PostalCode);
		TAGS_TO_PART_TYPES.put("streetAddressLine", AddressPartType.StreetAddressLine);
		TAGS_TO_PART_TYPES.put("streetName", AddressPartType.StreetName);
		TAGS_TO_PART_TYPES.put("houseNumber", AddressPartType.BuildingNumber);
		TAGS_TO_PART_TYPES.put("buildingNumber", AddressPartType.BuildingNumber);
		TAGS_TO_PART_TYPES.put("additionalLocator", AddressPartType.AdditionalLocator);
		TAGS_TO_PART_TYPES.put("postBox", AddressPartType.PostBox);
		TAGS_TO_PART_TYPES.put("censusTract", AddressPartType.CensusTract);
		TAGS_TO_PART_TYPES.put("direction", AddressPartType.Direction);
	}

	/** Maps part types into local element names. */
	private static final Map<AddressPartType, String> PART_TYPES_TO_TAGS = new HashMap<AddressPartType, String>();
	static {
		PART_TYPES_TO_TAGS.put(AddressPartType.Delimiter, "delimiter");
		PART_TYPES_TO_TAGS.put(AddressPartType.Country, "country");
		PART_TYPES_TO_TAGS.put(AddressPartType.StateOrProvince, "state");
		PART_TYPES_TO_TAGS.put(AddressPartType.CountyOrParish, "county");
		PART_TYPES_TO_TAGS.put(AddressPartType.Municipality, "city");
		PART_TYPES_TO_TAGS.put(AddressPartType.PostalCode, "postalCode");
		PART_TYPES_TO_TAGS.put(AddressPartType.StreetAddressLine, "streetAddressLine");
		PART_TYPES_TO_TAGS.put(AddressPartType.StreetName, "streetName");
		PART_TYPES_TO_TAGS.put(AddressPartType.BuildingNumber, "houseNumber");
		PART_TYPES_TO_TAGS.put(AddressPartType.AdditionalLocator, "additionalLocator");
		PART_TYPES_TO_TAGS.put(AddressPartType.PostBox, "postBox");
		PART_TYPES_TO_TAGS.put(AddressPartType.CensusTract, "censusTract");
		PART_TYPES_TO_TAGS.put(AddressPartType.Direction, "direction");
	}

	private static final ADXPPresenter INSTANCE = new ADXPPresenter();

	protected static class ADXPContentHandler extends TreeContentHandler {
		public ADXPContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			ADXP result = valueOf(element);
			super.returnResult(result);
		}

		protected static ADXP valueOf(TreeContentHandler.Node node) {
			if (node instanceof TreeContentHandler.Element) {
				TreeContentHandler.Element element = (TreeContentHandler.Element) node;
				String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
				if (nullFlavorString != null) {
					return ADXPnull.valueOf(nullFlavorString);
				}
				String partTypeString = element.getAttributeValue(ATTR_PART_TYPE);
				AddressPartType type = partTypeString != null ? AddressPartType.valueOf(ValueFactory.getInstance().STvalueOfLiteral(partTypeString)) : TAGS_TO_PART_TYPES.get(element.getLocalName());
				return ADXPimpl.valueOf(element.getText(), type);
			} else if (node instanceof TreeContentHandler.Text) {
				TreeContentHandler.Text text = (TreeContentHandler.Text) node;
				return ADXPimpl.valueOf(text.getValue());
			} else
				return null;
		}
	}

	protected static class ADXPBuilder implements DatatypeBuilder<ADXP> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, ADXP value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				if(!localName.equals(PART_TYPES_TO_TAGS.get(value.type())))
					builder.addAttribute(ATTR_PART_TYPE, value.type());
				builder.startElement(localName);
				String s = value.toString();
				char[] ac = s.toCharArray();
				builder.characters(ac, 0, ac.length);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void build(RimGraphXMLSpeaker.ContentBuilder builder, ADXP value) throws BuilderException {
			try {
				String tag = (String) PART_TYPES_TO_TAGS.get(value.type());
				if (tag != null) {
					// Emit typed part.
					build(builder, value, tag);
				} else {
					// Emit untyped part.
					String s = value.toString();
					char[] ac = s.toCharArray();
					builder.characters(ac, 0, ac.length);
				}
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, ADXP value, String localName) throws BuilderException {
			throw new UnsupportedOperationException();
		}
	}

	private ADXPPresenter() {}

	public static ADXPPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,	Datatype datatype) {
		return new ADXPContentHandler(namespaceURI, localName, qName, atts);
	}

	public DatatypeBuilder getBuilder() {
		return new ADXPBuilder();
	}
}

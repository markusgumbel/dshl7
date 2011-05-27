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
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.ENXP;
import org.hl7.types.ValueFactory;
import org.hl7.types.enums.EntityNamePartQualifier;
import org.hl7.types.enums.EntityNamePartType;
import org.hl7.types.impl.ENXPimpl;
import org.hl7.types.impl.ENXPnull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.DynamicContentHandler;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ENXPPresenter extends STPresenter {
	protected static final String ATTR_PART_TYPE = "partType";
	protected static final String ATTR_QUALIFIER = "qualifier";

	/** Maps local element names into part types. */
	protected static final Map<String, EntityNamePartType> TAGS_TO_PART_TYPES = new HashMap<String, EntityNamePartType>();
	static {
		TAGS_TO_PART_TYPES.put("delimiter", EntityNamePartType.Delimiter);
		TAGS_TO_PART_TYPES.put("family", EntityNamePartType.Family);
		TAGS_TO_PART_TYPES.put("given", EntityNamePartType.Given);
		TAGS_TO_PART_TYPES.put("prefix", EntityNamePartType.Prefix);
		TAGS_TO_PART_TYPES.put("suffix", EntityNamePartType.Suffix);
	}
	/** Maps part types into local element names. */
	protected static final Map<EntityNamePartType, String> PART_TYPES_TO_TAGS = new HashMap<EntityNamePartType, String>();
	static {
		PART_TYPES_TO_TAGS.put(EntityNamePartType.Delimiter, "delimiter");
		PART_TYPES_TO_TAGS.put(EntityNamePartType.Family, "family");
		PART_TYPES_TO_TAGS.put(EntityNamePartType.Given, "given");
		PART_TYPES_TO_TAGS.put(EntityNamePartType.Prefix, "prefix");
		PART_TYPES_TO_TAGS.put(EntityNamePartType.Suffix, "suffix");
	}
	private static final ENXPPresenter INSTANCE = new ENXPPresenter();

	protected static class ENXPContentHandler<T extends ENXP> extends TreeContentHandler {
		public ENXPContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			T result = valueOf(element);
			super.returnResult(result);
		}

		/** Must be overridden to return proper type. */
		protected T valueOf(String text, EntityNamePartType type, DSET<CS> qualifiers) {
			return (T)ENXPimpl.valueOf(text, type, qualifiers);
		}

		/** Must be overridden to return proper type. */
		protected T nullValueOf(String nullFlavorString) {
			return (T)ENXPnull.valueOf(nullFlavorString);
		}

		/** Is not overridden. */
		protected T valueOf(TreeContentHandler.Node node) {
			if (node instanceof TreeContentHandler.Element) {
				TreeContentHandler.Element element = (TreeContentHandler.Element) node;
				String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
				if (nullFlavorString != null) {
					return nullValueOf(nullFlavorString);
				}
				String partTypeString = element.getAttributeValue(ATTR_PART_TYPE);
				EntityNamePartType type = partTypeString != null ? EntityNamePartType.valueOf(ValueFactory.getInstance().STvalueOfLiteral(partTypeString)) : TAGS_TO_PART_TYPES.get(element.getLocalName());
				DSET<CS> qualifiers = CSPresenter.parseList(element.getAttributeValue(ATTR_QUALIFIER),
																										EntityNamePartQualifier.root.codeSystem().toString());
				
				return valueOf(element.getText(), type, qualifiers);

			} else if (node instanceof TreeContentHandler.Text) {
				
				TreeContentHandler.Text text = (TreeContentHandler.Text) node;
				return valueOf(text.getValue(), null, null);

			} else
				return null;
		}
	}

	protected static class ENXPBuilder<T extends ENXP> implements DatatypeBuilder<T> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, T value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				if(!localName.equals(PART_TYPES_TO_TAGS.get(value.type())))
					builder.addAttribute(ATTR_PART_TYPE, value.type().code());
				if (value.qualifier() != null && !value.qualifier().isNullJ())
					builder.addAttribute(ATTR_QUALIFIER, ((CS) value.qualifier().iterator().next()).code());
				builder.startElement(localName);
				String s = value.toString();
				char[] ac = s.toCharArray();
				builder.characters(ac, 0, ac.length);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void build(RimGraphXMLSpeaker.ContentBuilder builder, T value) throws BuilderException {
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

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, T value, String localName)	throws BuilderException {
			throw new UnsupportedOperationException();
		}
	}

	protected ENXPPresenter() {}

	public static ENXPPresenter instance() {
		return INSTANCE;
	}

	public DynamicContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
																								 Datatype datatype) {
		// do nothing with the datatype
		return new ENXPContentHandler(namespaceURI, localName, qName, atts);
	}

	public DatatypeBuilder getBuilder() {
		return new ENXPBuilder();
	}
}

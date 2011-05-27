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
import org.hl7.types.II;
import org.hl7.types.impl.IIimpl;
import org.hl7.types.impl.IInull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This little guy knows how to build II values.
 */
public class IIPresenter extends DatatypePresenterBase {
	// constant should match this data type XML attribute name
	public static final String ATTR_ROOT = "root";
	public static final String ATTR_EXTENSION = "extension";
	public static final String ATTR_AAN = "assigningAuthorityName";
	public static final String ATTR_DISPLAYABLE = "displayable";

	private static final IIPresenter INSTANCE = new IIPresenter();

	private static class IIContentHandler extends SimpleTypeContentHandler {
		II _result = null;

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = IInull.valueOf(nullFlavorString);
			} else {
				String rootString = atts.getValue(ATTR_ROOT);
				String extensionString = atts.getValue(ATTR_EXTENSION);
				String assigningAuthorityNameString = atts.getValue(ATTR_AAN);
				String displayableString = atts.getValue(ATTR_DISPLAYABLE);
				_result = IIimpl.valueOf(rootString, extensionString, assigningAuthorityNameString, displayableString);
			}
		}

		protected Object getResult() {
			return (_result == null) ? IInull.NI : _result;
		}
	}

	private static class IIBuilder implements DatatypeBuilder<II> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, II value, String localName) throws BuilderException {
			try {
				II ii = (II) value;
				if (builder.nullValueHandled(value, localName))
					return;
				builder.addAttribute(ATTR_ROOT, ii.root());
				builder.addAttribute(ATTR_EXTENSION, ii.extension());
				builder.addAttribute(ATTR_AAN, ii.assigningAuthorityName());
				builder.addAttribute(ATTR_DISPLAYABLE, ii.displayable());
				builder.startElement(localName);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, II value, String localName)
				throws BuilderException {
			throw new BuilderException("II cannot be a structural attribute");
		}
	}

	private IIPresenter() {}

	public static IIPresenter instance() {
		return INSTANCE;
	}

	public static II getValue(Object valueString) {
		return IIimpl.valueOf(valueString.toString(), null, null);
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new IIContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new IIBuilder();
	}
}

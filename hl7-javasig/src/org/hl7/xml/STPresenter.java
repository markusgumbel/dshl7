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
import org.hl7.types.ST;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.STjlStringAdapter;
import org.hl7.types.impl.STnull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This little guy knows how to build strings.
 * 
 * <p>
 * ST exists in 2 forms the ST full element type (where ST is the text data) and the st type (where ST is usually some
 * attribute data.) In the case of ST as attribute value, the data types containing such st attributes will usually
 * allow just java.lang.String in their factories. So, this is for the full blown ST.
 * </p>
 */
public class STPresenter extends DatatypePresenterBase {
	public static final String ATTR_REPRESENTATION = "representation"; // "TXT" or "B64" only
	public static final String ATTR_MEDIA_TYPE = "mediaType";

	private static final STPresenter INSTANCE = new STPresenter();

	private static class STContentHandler extends SimpleTypeContentHandler {
		ST _result = null;
		/**
		 * This holds the potentially multiple characters events that we might get from the XML reader.
		 */
		// this maybe should be in STPresenter
		private StringBuffer _stringBuffer = null;

		protected void notifyActivation(Attributes atts) {
			_stringBuffer = null;
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = STnull.valueOf(nullFlavorString);
			} else {
				_result = null;
			}
		}

		protected Object getResult() {
			if (_result == null) {
				if (_stringBuffer != null) {
					_result = ValueFactory.getInstance().STvalueOf(_stringBuffer.toString());
					_stringBuffer = null;
					return _result;
				} else {
					return ValueFactory.getInstance().STvalueOf("");
				}
			} else {
				ST temp = _result;
				_result = null;
				return temp;
			}
		}

		/**
		 * This only gathers characters if a _result is not already set. I.e., if this came with a nullFlavor attribute, we
		 * silently ignore the characters.
		 */
		public void characters(char[] ch, int start, int length) {
			if (_stringBuffer == null)
				_stringBuffer = new StringBuffer(length);
			_stringBuffer.append(ch, start, length);
		}
	}

	private static class STBuilder implements DatatypeBuilder<ST> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, ST value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				ST stValue = (ST) value;
				builder.addAttribute(ATTR_REPRESENTATION, "TXT");
				builder.addAttribute(ATTR_MEDIA_TYPE, stValue.mediaType());
				builder.startElement(localName);
				builder.characters(stValue.toString());
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, ST value, String localName)
				throws BuilderException {
			throw new BuilderException("ST cannot be a structural attribute");
		}
	}

	protected STPresenter() {}

	public static STPresenter instance() {
		return INSTANCE;
	}

	public static ST getValue(Object valueString) {
		return STjlStringAdapter.valueOf(valueString.toString());
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new STContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new STBuilder();
	}
}

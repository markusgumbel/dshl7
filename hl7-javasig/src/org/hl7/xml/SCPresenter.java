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
 * Contributor(s): Eric Chen
 */
package org.hl7.xml;

import org.hl7.meta.Datatype;
import org.hl7.types.CE;
import org.hl7.types.SC;
import org.hl7.types.ST;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.CEimpl;
import org.hl7.types.impl.SCimpl;
import org.hl7.types.impl.SCnull;
import org.hl7.types.impl.STjlStringAdapter;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class SCPresenter extends DatatypePresenterBase {
	public static final String ATTR_LANGUAGE = "language";
	public static final String ATTR_REPRESENTATION = "representation"; // "TXT" or "B64" only
	public static final String ATTR_MEDIA_TYPE = "mediaType";
	public static final String ATTR_CODE = "code";
	public static final String ATTR_CODE_SYSTEM = "codeSystem";
	public static final String ATTR_CODE_SYSTEM_NAME = "codeSystemName";
	public static final String ATTR_CODE_SYSTEM_VERSION = "codeSystemVersion";
	public static final String ATTR_DISPLAY_NAME = "displayName";

	private static final SCPresenter INSTANCE = new SCPresenter();


	private static class SCContentHandler extends SimpleTypeContentHandler {
		SC _result = null;
		/**
		 * This holds the potentially multiple characters events that we might get from the XML reader.
		 */
		// this maybe should be in STPresenter
		private StringBuffer _stringBuffer = null;
		private CE _ce = null;

		protected void notifyActivation(Attributes atts) {
			_stringBuffer = null;
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = SCnull.valueOf(nullFlavorString);
			} else {
				String code = atts.getValue(ATTR_CODE);
				String codeSystem = atts.getValue(ATTR_CODE_SYSTEM);
				String codeSystemName = atts.getValue(ATTR_CODE_SYSTEM_NAME);
				String codeSystemVersion = atts.getValue(ATTR_CODE_SYSTEM_VERSION);
				String displayName = atts.getValue(ATTR_DISPLAY_NAME);
				if ((code != null) && (codeSystem != null) && (displayName != null)) {
					_ce = CEimpl.valueOf(code, codeSystem, displayName);
				}
				_result = null;
			}
		}

		protected Object getResult() {
			if (_result == null) {
				if (_stringBuffer != null) {
					_result = SCimpl.valueOf(_stringBuffer.toString(), _ce);
					_stringBuffer = null;
					_ce = null;
					return _result;
				} else {
					return ValueFactory.getInstance().SCvalueOf("");
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

	private static class SCBuilder implements DatatypeBuilder<SC> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, SC value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				SC scValue = (SC) value;
				builder.addAttribute(ATTR_LANGUAGE, scValue.language());
				builder.addAttribute(ATTR_REPRESENTATION, "TXT");
				builder.addAttribute(ATTR_MEDIA_TYPE, scValue.mediaType());
				CE ce = scValue.code();
				builder.addAttribute(ATTR_CODE, ce.code());
				builder.addAttribute(ATTR_DISPLAY_NAME, ce.displayName());
				builder.addAttribute(ATTR_CODE_SYSTEM, ce.codeSystem());
				builder.addAttribute(ATTR_CODE_SYSTEM_NAME, ce.codeSystemName());
				builder.addAttribute(ATTR_CODE_SYSTEM_VERSION, ce.codeSystemVersion());
				builder.startElement(localName);
				builder.characters(scValue.toString());
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, SC value, String localName)
				throws BuilderException {
			throw new BuilderException("ST cannot be a structural attribute");
		}
	}

	protected SCPresenter() {}

	public static SCPresenter instance() {
		return INSTANCE;
	}

	public static ST getValue(Object valueString) {
		return STjlStringAdapter.valueOf(valueString.toString());
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new SCContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new SCBuilder();
	}
}

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
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.TN;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.TNnull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TNPresenter extends DatatypePresenterBase {
	private static final TNPresenter INSTANCE = new TNPresenter();

	private static class TNContentHandler extends SimpleTypeContentHandler {
		private TN _result = null;
		private DSET<CS> _use = null;
		private StringBuffer _stringBuffer = null;

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = TNnull.valueOf(nullFlavorString);
			} else {
				String useString = atts.getValue(ENPresenter.ATTR_USE);
				if (useString != null)
					_use = CSPresenter.parseList(useString, "2.16.840.1.113883.5.45"); // FIXME use enums
			}
		}

		protected Object getResult() {
			if (_result == null) {
				if (_stringBuffer != null) {
					_result = ValueFactory.getInstance().TNvalueOf(_stringBuffer.toString(), _use, null);
				} else {
					_result = ValueFactory.getInstance().TNvalueOf("", _use, null);
				}
			}
			return _result;
		}

		/**
		 * This only gathers characters if a _result is not already set. I.e., if this came with a nullFlavor attribute, we
		 * silently ignore the characters.
		 */
		public void characters(char[] ch, int start, int length) {
			if (_result == null) {
				if (_stringBuffer == null)
					_stringBuffer = new StringBuffer(length);
				_stringBuffer.append(ch, start, length);
			}
		}
	}

	private static final CSPresenter.CSBuilder CS_BUILDER = (CSPresenter.CSBuilder) CSPresenter.instance().getBuilder();

	private static class TNBuilder implements DatatypeBuilder<TN> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, TN value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
				TN tnValue = (TN) value;
				CS_BUILDER.buildAttribute(builder, tnValue.use(), ENPresenter.ATTR_USE);
				builder.startElement(localName);
				builder.characters(tnValue.formatted().toString());
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, TN value, String localName)
				throws BuilderException {
			throw new BuilderException("TN cannot be a structural attribute");
		}
	}

	private TNPresenter() {}

	public static TNPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new TNContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new TNBuilder();
	}
}

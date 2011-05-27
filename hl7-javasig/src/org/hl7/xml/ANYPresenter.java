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
import org.hl7.types.ANY;
import org.hl7.types.impl.ANYnull;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This content handler builds ANY abstract data type.
 */
public class ANYPresenter extends DatatypePresenterBase {
	private static final ANYPresenter INSTANCE = new ANYPresenter();

	protected static class ANYContentHandler extends SimpleTypeContentHandler {
		ANY _result = null;

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = ANYnull.valueOf(nullFlavorString);
			} else {
				throw new UnsupportedOperationException("unknown data type");
			}
		}

		protected Object getResult() {
			return (_result == null) ? ANYnull.NI : _result;
		}
	}

	private static class ANYBuilder implements DatatypeBuilder<ANY> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, ANY value, String localName) throws BuilderException {
			try {
				if (builder.nullValueHandled(value, localName))
					return;
			} catch (SAXException e) {
				throw new BuilderException(e);
			}
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, ANY value, String localName)
				throws BuilderException {
			throw new BuilderException("ANY cannot be a structural attribute");
		}
	}

	// -------------------------------------------------------------------------
	private ANYPresenter() {}

	public static ANYPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new ANYContentHandler();
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new ANYBuilder();
	}
}

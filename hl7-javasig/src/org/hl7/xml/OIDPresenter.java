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
import org.hl7.types.OID;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;

public class OIDPresenter extends DatatypePresenterBase {
	public static final String ATTR_VALUE = "value";
	// -------------------------------------------------------------------------
	private static final OIDPresenter INSTANCE = new OIDPresenter();
	private final ContentHandler contentHandler_ = new OIDContentHandler();

	// ------------------------------------------------------------------------
	private static class OIDContentHandler extends SimpleTypeContentHandler {
		// .......................................................................
		protected Object getResult() {
			throw new UnsupportedOperationException();
		}
	}

	// --------------------------------------------------------------------------------------
	private static class OIDBuilder implements DatatypeBuilder<OID> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, OID value, String localName) throws BuilderException {
			throw new UnsupportedOperationException();
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, OID value, String localName)
				throws BuilderException {
			throw new UnsupportedOperationException();
		}
	}

	// --------------------------------------------------------------------------
	protected OIDPresenter() {}

	public static OIDPresenter instance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		// do nothing with the datatype
		return contentHandler_;
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new OIDBuilder();
	}
}

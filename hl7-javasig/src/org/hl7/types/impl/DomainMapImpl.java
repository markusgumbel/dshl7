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
package org.hl7.types.impl;

import java.util.HashMap;

import javax.xml.parsers.SAXParserFactory;

import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * A map constant, that maps domain names to codeSystem OIDs. This is an neat self-consistent example of how a Map just
 * like properties can be easily set from an XML file. It the allows for special semantics such as here the OID
 * inheriting from parent elements.
 * 
 * <p>
 * However, I don't think that this is addressing the problem with "Domains" for good. I don't even know how to begin to
 * think about these "Domains". I have a hard time to see the point for this additional layer of indirection. It has
 * something to do with vocabulary validation, but how?
 * 
 * <p>
 * One thing is clear: if the notion of "Domain" is here to stay, it needs to be implemented in a class of its own. It's
 * bad practice that we handle these things as just Strings in the meta package. So, perhaps Domain goes into meta as a
 * real class. I also think that CodeSystem might have to be a real class somewhere here in types or over there in meta.
 * 
 * <p>
 * But I think the HL7 methodology is too crazy and unstable about these vocab thingies, and the recent change of mind
 * (which occurs every 2 years it seems) is never properly implemented in the specification files, mif and stuff. So
 * we'll have to wait until (a) this is finally settled (yeah right) or (b) until we have understood our true use case
 * in a real implementation. I'm close with this when I get the SPL data from FDA which is done in such a way that
 * codeSystem means almost nothing and Domain will become everything.
 */
public class DomainMapImpl extends HashMap<String, UID> {
	private static DomainMapImpl _instance = null;

	public static DomainMapImpl getInstance() {
		if (_instance == null) {
			_instance = new DomainMapImpl();
		}
		return _instance;
	}

	private DomainMapImpl() {
		super();
		java.net.URL url = null;
		try {
			final XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			reader.setFeature("http://xml.org/sax/features/namespaces", true);
			reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
			reader.setContentHandler(new MapContentHandler(this, reader, null, null));
			url = Thread.currentThread().getContextClassLoader().getResource("domain-oid-map.xml");
			if (url == null) {
                throw new RuntimeException("domain-oid-map.xml file not found in classpath");
            }
			reader.parse(new InputSource(url.openStream()));
		} catch (final Throwable ex) {
			throw new RuntimeException(url.toString() + ": " + ex.getMessage(), ex);
		}
	}

	private static final class MapContentHandler implements ContentHandler {
		private final MapContentHandler _parentContentHandler;
		private final UID _parentCodeSystem;
		private final XMLReader _reader;
		private final DomainMapImpl _map;

		MapContentHandler(final DomainMapImpl map, final XMLReader reader, final MapContentHandler parentContentHandler, final UID parentCodeSystem) {
			_map = map;
			_reader = reader;
			_parentContentHandler = parentContentHandler;
			_parentCodeSystem = parentCodeSystem;
		}

		public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) {
			if (localName.equals("domain")) {
				final String name = atts.getValue("name");
				final String codeSystemString = atts.getValue("codeSystem");
				UID codeSystem = null;
				if (codeSystemString != null) {
                    codeSystem = ValueFactory.getInstance().UIDvalueOfLiteral(codeSystemString);
                }
				if (codeSystem == null) {
                    codeSystem = _parentCodeSystem;
                }
				if (codeSystem != null && name != null) {
					_map.put(name, codeSystem);
				}
				_reader.setContentHandler(new MapContentHandler(_map, _reader, this, codeSystem));
			}
		}

		public void endElement(final String namespaceURI, final String localName, final String qName) {
			if (localName.equals("domain")) {
                _reader.setContentHandler(_parentContentHandler);
            }
		}

		public void characters(final char[] ch, final int start, final int length) {}

		public void startDocument() {}

		public void endDocument() {}

		public void startPrefixMapping(final String prefix, final String uri) {}

		public void endPrefixMapping(final String prefix) {}

		public void ignorableWhitespace(final char[] ch, final int start, final int length) {}

		public void processingInstruction(final String target, final String data) {}

		public void setDocumentLocator(final org.xml.sax.Locator locator) {}

		public void skippedEntity(final String name) {}
	}
}

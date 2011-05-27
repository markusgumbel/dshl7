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

import java.util.Iterator;
import java.util.List;
import java.io.ByteArrayInputStream;
import org.hl7.meta.Datatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.BIN;
import org.hl7.types.CS;
import org.hl7.types.ED;
import org.hl7.types.TEL;
import org.hl7.types.impl.BINbyteArrayImpl;
import org.hl7.types.impl.EDbyteArrayImpl;
import org.hl7.types.impl.EDjlStringAdapter;
import org.hl7.types.impl.STjlStringAdapter;
import org.hl7.types.impl.EDnull;
import org.hl7.types.impl.TELnull;
import org.hl7.util.Base64;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.builder.RimGraphXMLSpeaker.ContentBuilder;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

/**
 * This content handler builds EDs.
 */
public class EDPresenter extends DatatypePresenterBase {
	// -------------------------------------------------------------------------
	public static final String ATTR_MEDIA_TYPE = "mediaType";
	public static final String ATTR_LANGUAGE = "language";
	public static final String ATTR_COMPRESSION = "compression";
	public static final String ATTR_INTEGRITY_CHECK = "integrityCheck";
	public static final String ATTR_INTEGRITY_CHECK_ALGORITHM = "integrityCheckAlgorithm";
	public static final String ATTR_REFERENCE = "reference";
	public static final String ATTR_THUMBNAIL = "thumbnail";
	public static final String ATTR_REPRESENTATION_OLD = "encoding"; // for backwards compatibility
	public static final String ATTR_REPRESENTATION = "representation";
	// -------------------------------------------------------------------------
	private static final EDPresenter INSTANCE = new EDPresenter();

	// --------------------------------------------------------------------
	protected static class EDContentHandler extends TreeContentHandler {
		// .......................................................................
		public EDContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName, atts);
		}

		// .......................................................................
		protected void returnResult(Object intermediate) throws SAXException {
			TreeContentHandler.Element element = (TreeContentHandler.Element) intermediate;
			ED result = valueOf(element);
			super.returnResult(result);
		}

		// .......................................................................
		protected ED valueOf(TreeContentHandler.Element element) throws SAXException {
			String nullFlavorString = element.getAttributeValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				return EDnull.valueOf(nullFlavorString);
			}
			// Handle other attributes.
			String encoding = element.getAttributeValue(ATTR_REPRESENTATION);
			if (encoding == null)
				encoding = element.getAttributeValue(ATTR_REPRESENTATION_OLD); // for backwards compatibility
			if (encoding == null)
				encoding = "TXT"; // default
			boolean binary = encoding.equals("B64");
			String mediaType = element.getAttributeValue(ATTR_MEDIA_TYPE);
			String language = element.getAttributeValue(ATTR_LANGUAGE);
			String compression = element.getAttributeValue(ATTR_COMPRESSION);
			String integrityCheck = element.getAttributeValue(ATTR_INTEGRITY_CHECK);
			BIN integrityCheckBin = BINbyteArrayImpl.valueOf(integrityCheck);
			String integrityCheckAlgorithm = element.getAttributeValue(ATTR_INTEGRITY_CHECK_ALGORITHM);
			// How do we get this from XML preamble?
			String charSet = "UTF-8";
			// Nested text or base-64 encoded binary data.
			String text = getEDContent(element);
			if(mediaType == null && text.startsWith("<"))
				mediaType = "text/xml";					
			// Handle nested elements...
			try {
				Iterator<Element> it = element.findChildren(ATTR_REFERENCE);
				TEL reference = null;
				if (it.hasNext()) {
					reference = TELPresenter.valueOf(it.next());
				} else {
					reference = TELnull.NI;
				}
				it = element.findChildren(ATTR_THUMBNAIL);
				ED thumbnail = null;
				if (it.hasNext()) {
					// Should be optimized.
					Datatype ed = DatatypeMetadataFactoryImpl.instance().create("ED");
					Element el = (TreeContentHandler.Element) it.next();
					// Should be optimized.
					EDPresenter.EDContentHandler newContext = (EDPresenter.EDContentHandler) ed.getHandler(el.getNamespaceURI(),
							el.getLocalName(), el.getQName(), el.getAttributes());
					thumbnail = newContext.valueOf(el);
				} else {
					thumbnail = EDnull.NI;
				}
				if (binary) {
					byte[] bytes = Base64.decode(text);
					return EDbyteArrayImpl.valueOf(bytes, 0, bytes.length, mediaType, charSet, language, compression, reference,
							integrityCheckBin, integrityCheckAlgorithm, thumbnail);
				} else {
					return EDjlStringAdapter.valueOf(text, mediaType, charSet, language, compression, reference,
							integrityCheckBin, integrityCheckAlgorithm, thumbnail);
				}
			} catch (UnknownDatatypeException ex) {
				throw new SAXException(ex);
			} catch (FactoryException ex) {
				throw new SAXException(ex);
			}
		}

		/**
		 * This method returns the entire content of the Element (including text, attributes and nested Elements) as a
		 * String. ED's standard nested elements (reference and thumbnail) are not included.
		 * 
		 * @author Dolev Dotan
		 * @since 13 March 2006
		 */
		public String getEDContent(Element node) {
			StringBuffer sb = new StringBuffer();
			appendEDContent(node, 0, sb);
			return sb.toString();
		}

		public void appendEDContent(Node node, int depth, StringBuffer sb) {
			if (node instanceof Text) {
				Text text = (Text) node;
				sb.append(text.getValue().trim().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&", "&amp;")
						.replaceAll("\"", "&quot;").replaceAll("'", "&apos;"));
			} else if (node instanceof Element) {
				Element element = (Element) node;
				String qName = element.getQName();
				// skip standard nested elements (reference and thumbnail)
				if (depth == 1 && (qName.equals(ATTR_REFERENCE) || qName.equals(ATTR_THUMBNAIL)))
					return;
				List<Node> children = element.getChildren();
				// start element (but ignore top-level element)
				if (depth != 0) {
					// append nested element
					sb.append("<" + qName);
					// if this element's namespace is different than its
					// parent's, add a namespace declaration:
					// if a prefix is defined, add xmlns:prefix=namespaceURI
					// otherwise, add xmlns=namespaceURI
					Element parent = element.getParent();
					String namespaceURI = element.getNamespaceURI();
					if (parent != null && parent.getNamespaceURI() != namespaceURI) {
						sb.append(" xmlns");
						if (!qName.equals(element.getLocalName())) {
							String prefix = qName.split(":")[0];
							sb.append(':').append(prefix);
						}
						sb.append("=\"" + namespaceURI + "\"");
					}
					// append attributes
					for (Node child : children) {
						if (child instanceof Attribute) {
							Attribute attr = (Attribute) child;
							String aName = attr.getLocalName();
							if ("".equals(aName))
								aName = attr.getQName();
							sb.append(" ");
							sb.append(aName + "=\"" + attr.getValue() + "\"");
						}
					}
					sb.append(">");
				}
				// recursively append content
				for (Node child : children) {
					if (!(child instanceof Attribute))
						appendEDContent(child, depth + 1, sb);
				}
				// end element (but ignore the top-level element
				if (depth != 0)
					sb.append("</" + qName + ">");
			}
		}
	}

	// -------------------------------------------------------------------------
	private static class EDBuilder implements DatatypeBuilder<ED> {
		/**
		 * Adapts RimGraphXMLSpeaker.ContentBuilder to SAX' DefaultHandler
		 * 
		 * @author dotan
		 * 
		 */
		public class ContentBuilderAdapter extends DefaultHandler {
			private ContentBuilder builder;

			/**
			 * @param builder
			 * 
			 */
			public ContentBuilderAdapter(ContentBuilder builder) {
				this.builder = builder;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
			 *      org.xml.sax.Attributes)
			 */
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				if (localName.equals("XMLHACK")) // filter the synthetic root node away again
					return;
				if (uri.equals("")) {
					for (int i = 0; i < attributes.getLength(); i++) {
						builder.addAttribute(attributes.getLocalName(i), attributes.getValue(i));
					}
					builder.startElement(localName);
				} else
					builder.startElement(uri, localName, qName, attributes);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
			 */
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if (localName.equals("XMLHACK"))
					return;
				if (uri.equals(""))
					builder.endElement(localName);
				else
					builder.endElement(uri, localName, qName);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
			 */
			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {
				builder.characters(ch, start, length);
			}

			@Override
			public void endPrefixMapping(String prefix) throws SAXException {
				builder.endPrefixMapping(prefix);
			}

			@Override
			public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
				builder.ignorableWhitespace(ch, start, length);
			}

			@Override
			public void processingInstruction(String target, String data) throws SAXException {
				builder.processingInstruction(target, data);
			}

			@Override
			public void setDocumentLocator(Locator locator) {
				builder.setDocumentLocator(locator);
			}

			@Override
			public void skippedEntity(String name) throws SAXException {
				builder.skippedEntity(name);
			}

			@Override
			public void startPrefixMapping(String prefix, String uri) throws SAXException {
				builder.startPrefixMapping(prefix, uri);
			}
		}

		public void build(RimGraphXMLSpeaker.ContentBuilder builder, ED value, String localName) throws BuilderException {
			try {
				ED ed = (ED) value;
				if (builder.nullValueHandled(value, localName))
					return;
				CS mediaType = ed.mediaType();
				builder.addAttribute(ATTR_MEDIA_TYPE, mediaType);
				builder.addAttribute(ATTR_LANGUAGE, ed.language());
				builder.addAttribute(ATTR_COMPRESSION, ed.compression());
				if (ed.integrityCheck().nonNull().isTrue()) {
					builder.addAttribute(ATTR_INTEGRITY_CHECK, ed.integrityCheck());
					builder.addAttribute(ATTR_INTEGRITY_CHECK_ALGORITHM, ed.integrityCheckAlgorithm());
				}
				// FIXME: this doesn't handle B64 for binary data!
				builder.addAttribute(ATTR_REPRESENTATION, "TXT");
				builder.startElement(localName);
				if (ed.reference() == null || ed.reference().isNullJ()) { // do
					// nothing.
				} else {
					builder.build(ed.reference(), ATTR_REFERENCE);
				}
				if (ed.thumbnail() == null || ed.thumbnail().isNullJ()) { // do
					// nothing.
				} else {
					builder.build(ed.thumbnail(), ATTR_THUMBNAIL);
				}
				// EDbyteArrayImpl.toString() -> BINbyteArrayImpl.toString()
				// performs base64 encoding on bytes.
				// EDjlStringAdapter.toString() -> STjlStringAdapter.toString()
				// returns the string without any changes.
				// In both cases, that is exactly what is needed here.
				String s = ed.toString();
				if(ed.mediaType().code().toString().endsWith("xml")) {
				  s = "<XMLHACK>" + s + "</XMLHACK>"; // make a root node to put the content in
					// Parse the string as XML 
					SAXParser saxParser = getSAXParser();
					saxParser.parse(new ByteArrayInputStream(s.getBytes("UTF-8")), new ContentBuilderAdapter(builder)); 
				} else {
					char[] ac = s.toCharArray();
					builder.characters(ac, 0, ac.length);
				}
				builder.endElement(localName);
			} catch (Exception ex) {
				throw new BuilderException(ex);
			}
		}

		private static SAXParserFactory _theSAXParserFactory;
		private SAXParser getSAXParser() throws javax.xml.parsers.ParserConfigurationException, SAXException {
			if(_theSAXParserFactory == null) {
				_theSAXParserFactory = SAXParserFactory.newInstance();
				_theSAXParserFactory.setNamespaceAware(true); 
			}
			return _theSAXParserFactory.newSAXParser();
		}

		// .......................................................................
		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, ED value, String localName)
				throws BuilderException {
			throw new BuilderException("ED cannot be a structural attribute");
		}
	}

	// -------------------------------------------------------------------------
	private EDPresenter() {}

	public static EDPresenter instance() {
		return INSTANCE;
	}

	public static ED getValue(Object valueString) {
		return EDjlStringAdapter.valueOf(valueString.toString(), null, null, null, null, null, null, null, null);
	}

	// -------------------------------------------------------------------------
	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new EDContentHandler(namespaceURI, localName, qName, atts);
	}

	// -------------------------------------------------------------------------
	public DatatypeBuilder getBuilder() {
		return new EDBuilder();
	}
}

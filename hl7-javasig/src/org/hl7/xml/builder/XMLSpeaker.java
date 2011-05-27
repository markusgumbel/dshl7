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
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.xml.builder;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 * An XMLSpeaker implements the SAX XMLReader interface but is meant to generate SAX events by means other than
 * "reading" XML (e.g., by traversing some data structure.) The name comes from the similarity between reader and
 * speaker: a reader is a speaker but a speaker speaks without necessarily reading.
 * 
 * <p>
 * This class has two parts, the outer and inner class. The outer class implements the XMLReader interface and is used
 * by the parts of the program that will expect to receive SAX events. The inner ContentBuilder class essentially
 * implements a standard ContentHandler with a number of additional features to increase both convenience and efficiency
 * of the SAX event creation process.
 * 
 * <p>
 * Just like any XMLReader, the SAX event stream is started by invoking the XMLReader.parse() method with a SAX
 * InputSource. Unlike the common XML parsers, however, the InputSource may be of a special kind with informations other
 * than character streams. For instance, the InputSource may contain references to Objects that are to be serialized.
 * 
 * <p>
 * For any particular method of generating SAX events the XMLSpeaker class should be specialized. At a minimum the
 * specialization must implement the parse() method. It should also provide a specialized InputSource class and
 * factories, and must know how to use that specialized InputSource.
 * 
 * <p>
 * The parse method will normally create an instance of the inner ContentBuilder class and can pass this instance down
 * the call chain where SAX events are generated. All SAX events generated <strong>must</strong> be sent to that inner
 * ContentHandler and will be forwarded to whatever ContentHandler is registered with the outer XMLReader at the time
 * the SAX event is emitted.
 * 
 * <p>
 * Thus the inner ContentHandler object is a proxy for whatever ContentHandler is registered with the XMLReader. The
 * rationale for designing the ContentHandler as an inner class is so that all the methods that make SAX events appear
 * at the XMLReader end are protected from the XMLReader user.
 * 
 * <p>
 * <strong>You must never ever pass the ContentHandler object that is currently registered with the XMLReader down a
 * call chain.</strong> Best practice is to never use the registered ContentHandler. Since the inner ContentBuilder
 * class implements the ContentHandler interface it can be passed whenever a ContentHandler is required (such as when
 * invoking subsequent XMLReaders.)
 * 
 * <p>
 * In addition, the inner ContentBuilder class implements some convenience methods that makes working with namespaces
 * easier and provides other functions convenient for creating XML instances.
 * 
 * <p>
 * One particular feature of the inner ContentBuilder is the fact that it holds a single implementation object of SAX
 * Attributes. Since SAX Attributes are a fairly complex object it is usually reused between SAX startElement events and
 * SAX clients (should) know that Attributes change between two startElement calls.
 * 
 * <p>
 * One can add any XML attributes with the setAttributes method (simplified for expressing the normal namespace behavior
 * automatically.) Upon calling a simplified startElement method, these attributes are being emitted with the
 * startElement invocation of the XMLReader's current ContentHandler.
 * 
 * <p>
 * SAX Attributes are cleared at the end of the startElement method. It is possible for a caller of a build method to
 * "sneak" in additional XML attributes to a subordinate element.
 * 
 * <p>
 * <strong>TODO </strong> we are missing a way to ensure that elements and namespace declarations are properly nested.
 * 
 * @author Gunther Schadow
 * @version $Id: XMLSpeaker.java 7377 2007-09-26 19:28:36Z gschadow $
 */
public abstract class XMLSpeaker implements XMLReader {
	/**
	 * The currently registered ContentHandler to receive the next SAX event. This method is <strong>private </strong> and
	 * not protected because the event generator side must never directly use it.
	 */
	private ContentHandler _contentHandler;

	/**
	 * A proxy for whatever ContentHandler is registered with the XMLReader. Since it is an inner class it maintains a
	 * reference to the XMLReader yet hides the functions that produce and influence SAX events from the user of the
	 * XMLReader.
	 * 
	 * <p>
	 * Only the ContentBuilder inner class should be used to generate the SAX events, never use the registered
	 * ContentHandler directly. Since the inner ContentBuilder class implements the ContentHandler interface it can be
	 * passed whenever a ContentHandler is required (such as when invoking subsequent XMLReaders.)
	 * 
	 * <p>
	 * In addition, the inner ContentBuilder class implements methods to improve correctness and efficiency and
	 * convenience. This includes working with attributes and namespace properly.
	 * 
	 * <p>
	 * This class holds a single SAX Attributes instance. Since SAX Attributes is a fairly complex object, it is usually
	 * reused between SAX startElement events. SAX clients (should) expect that Attributes may change between two
	 * startElement calls.
	 * 
	 * <p>
	 * The setAttributes method is simplified for expressing the normal namespace behavior automatically. Upon calling a
	 * simplified startElement method, the attributes are being emited with the startElement invocation of the XMLReader's
	 * current ContentHandler.
	 * 
	 * <p>
	 * Attributes are cleared after the startElement event. You do not usually have to clear the attributes manually. This
	 * allows different steps that lead up to a startElement event to place data into the Attributes list.
	 * 
	 * <p>
	 * To support convenience methods specific to the kind of XML instance generated one can also specialize this inner
	 * class inside the specialized outer class.
	 */
	public class ContentBuilder implements ContentHandler {
		/** The current namespacePrefix. */
		private String _namespacePrefix = "";
		/** The current namespaceURI */
		private String _namespaceURI = "";
		/** Attributes should generally be in no namespace at all. */
		public static final String ATTR_NO_NAMESPACE_PREFIX = "";
		/** Attributes should generally be in no namespace at all. */
		public static final String ATTR_NO_NAMESPACE_URI = "";

		/**
		 * Sets the current namespace to the given URI and prefix used in the simplified startElement/endElement methods.
		 * 
		 * FIXME: namespace object events are not created! This is actually quite tricy because namespace declarations nest
		 * on elements.
		 */
		public void setNamespace(String namespaceURI, String namespacePrefix) {
			_namespacePrefix = namespacePrefix;
			_namespaceURI = namespaceURI;
		}

		/**
		 * Sets the current namespace used in the simplified startElement/endElement methods.to the given URI as the default
		 * namespace (without a prefix).
		 * 
		 * FIXME: namespace object events are not created! This is actually quite tricy because namespace declarations nest
		 * on elements.
		 */
		public void setNamespace(String namespaceURI) {
			_namespacePrefix = null;
			_namespaceURI = namespaceURI;
		}

		/**
		 * Returns the current namespaceURI used for the simplified startElement/endElement methods.
		 */
		public String getNamespaceURI() {
			return _namespaceURI;
		}

		/**
		 * Returns the current namespacePrefix used for constructing qNames in the simplified startElement/endElement
		 * methods.
		 */
		public String getNamespacePrefix() {
			return _namespacePrefix;
		}

		/** The SAX Attributes list. */
		private AttributesImpl _attributes = new AttributesImpl();

		/** Add an attribute as CDATA in no namespace. */
		public void addAttribute(String localName, String value) {
			if (value != null)
				_attributes.addAttribute(ATTR_NO_NAMESPACE_URI, localName, localName, "CDATA", value);
		}

		/** This is a hook for specializations to clear other temporary variables. */
		protected void clearAttributes() {
			_attributes.clear();
		}

		/** Add an attribute with DTD data type in no namespace. */
		public void addAttribute(String localName, String type, String value) {
			if (value != null)
				_attributes.addAttribute(ATTR_NO_NAMESPACE_URI, localName, localName, type, value);
		}

		/** Add any attribute. */
		public void addAttribute(String namespaceURI, String localName, String qName, String type, String value) {
			if (value != null)
				_attributes.addAttribute(namespaceURI, localName, qName, type, value);
		}

		/** Return an attribute */
		public String getAttributeValue(String namespaceURI, String localName) {
			return _attributes.getValue(namespaceURI, localName);
		}

		/** get _attributes for testing * */
		public AttributesImpl getAttributes() {
			return _attributes;
		}

		public void removeAttribute(String namespaceURI, String localName) {
			_attributes.removeAttribute(_attributes.getIndex(namespaceURI, localName));
		}

		/** Start element in the curent namespace. */
		public void startElement(String localName) throws SAXException {
			startElement(_namespaceURI, localName, (_namespacePrefix != null ? _namespacePrefix + localName : localName),
					_attributes);
			clearAttributes();
		}

		/** End element in the current namespace. */
		public void endElement(String localName) throws SAXException {
			endElement(_namespaceURI, localName, (_namespacePrefix != null ? _namespacePrefix + localName : localName));
		}

		public void characters(char ch[], int start, int length) throws SAXException {
			_contentHandler.characters(ch, start, length);
		}

		public void characters(String text) throws SAXException {
			char ch[] = text.toCharArray();
			_contentHandler.characters(ch, 0, ch.length);
		}

		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
			_contentHandler.startElement(namespaceURI, localName, qName, atts);
			clearAttributes();
		}

		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			// _depthFromRoot--;
			_contentHandler.endElement(namespaceURI, localName, qName);
		}

		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
			_contentHandler.ignorableWhitespace(ch, start, length);
		}

		// Stuff we don't handle for now
		public void startDocument() throws SAXException {
			_contentHandler.startDocument();
		}

		public void endDocument() throws SAXException {
			_contentHandler.endDocument();
		}

		public void processingInstruction(String target, String data) throws SAXException {
			_contentHandler.processingInstruction(target, data);
		}

		public void startPrefixMapping(String prefix, String uri) throws SAXException {
			_contentHandler.startPrefixMapping(prefix, uri);
		}

		public void endPrefixMapping(String prefix) throws SAXException {
			_contentHandler.endPrefixMapping(prefix);
		}

		public void setDocumentLocator(Locator locator) {
			_contentHandler.setDocumentLocator(locator);
		}

		public void skippedEntity(String name) throws SAXException {
			_contentHandler.skippedEntity(name);
		}
	}

	// INTERFACE XMLReader
	/**
	 * Returns the content handler currently set.
	 * 
	 * @see org.xml.sax.XMLReader#getContentHandler
	 */
	public ContentHandler getContentHandler() {
		return this._contentHandler;
	}

	/**
	 * Sets content handler that will receive the next event that we emit (and all events after that until another content
	 * handler is set).
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public void setContentHandler(ContentHandler contentHandler) {
		this._contentHandler = contentHandler;
	}

	/**
	 * Echoes back features that had been set earlier (or their defaults.)
	 * 
	 * @see org.hl7.xml.builder.XMLSpeaker.setFeature for what is supported.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
		throw new SAXNotRecognizedException(name);
	}

	/**
	 * Sets a feature.
	 * 
	 * <p>
	 * The following feature requests are tolerated without error, but silently ignored and not echoed back with get-
	 * feature.
	 * </p>
	 * 
	 * <ul>
	 * <li>"http://xml.org/sax/features/namespaces"</li>
	 * <li>"http://xml.org/sax/features/namespace-prefixes"</li>
	 * </ul>
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
		name = name.intern();
		if (name == "http://xml.org/sax/features/namespaces" || name == "http://xml.org/sax/features/namespace-prefixes") {
			// Saxon needs a positive response to this, I don't know what
			// this is supposed to do, but let's just silently accept it.
		} else
			throw new SAXNotRecognizedException(name);
	}

	// The rest is all more or less irrelevant stuff from the XMLReader
	// interface. Gotta do the dance :-).
	// XXX: could use some of this
	/**
	 * A no-op at this time.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
		throw new SAXNotRecognizedException(name);
	}

	/**
	 * A no-op at this time.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
		throw new SAXNotRecognizedException(name);
	}

	private ErrorHandler _ErrorHandler;

	/**
	 * A no-op at this time.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public ErrorHandler getErrorHandler() {
		return this._ErrorHandler;
	}

	/**
	 * A no-op at this time.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public void setErrorHandler(ErrorHandler ErrorHandler) {
		this._ErrorHandler = ErrorHandler;
	}

	// rest is all bogus as we never use DTDs, or Entities.
	/**
	 * A no-op. This is an irrelevant issue for HL7 parsing.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public DTDHandler getDTDHandler() {
		return null;
	}

	/**
	 * A no-op. This is an irrelevant issue for HL7 parsing.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public EntityResolver getEntityResolver() {
		return null;
	}

	/**
	 * A no-op. This is an irrelevant issue for HL7 parsing.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public void setDTDHandler(DTDHandler x) {}

	/**
	 * A no-op. This is an irrelevant issue for HL7 parsing.
	 * 
	 * @see org.xml.sax.XMLReader
	 */
	public void setEntityResolver(EntityResolver x) {}
}

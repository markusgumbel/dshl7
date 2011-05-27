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
package org.hl7.xml.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A DynamicContentHandler used to generate small (mini-DOM) trees that can be used in two ways:
 *  - as an intermediary representation for a value constructor to get the parameters.
 *  - as a default representation for yet unhandled types or unknown field extensions.
 * 
 * Since I love Saxon so much, I am tempted to building a Saxon Tinytree. If I did that I would have access to Saxon's
 * XPath functions as well as would be able to quickly exchange tree fragments with Saxon XSLT transforms.
 * 
 * On the other hand, JDOM seems to be the way to go today. But who knows. For now I keep it simple, but could change
 * that.
 * 
 * @author Gunther Schadow, Regenstrief Institute
 * @version $Id: TreeContentHandler.java 5671 2007-04-03 20:01:45Z crosenthal $
 */
public class TreeContentHandler extends DynamicContentHandlerBase implements DynamicContentHandler, Cloneable {
	/**
	 * Use this object to avoid creating a new instance of AttributesImpl on each startElement(). Make sure you call
	 * attributes.clear() immediately after startElement().
	 */
	protected final AttributesImpl attributes_ = new AttributesImpl();

	/**
	 * A super simple DOM that simply collects SAX elements, attributes and text nodes.
	 */
	public static class Node {
		protected Element _parent = null; // top node has null here

		public Element getParent() {
			return _parent;
		}
	}

	public static class NamedNode extends Node {
		protected String _namespaceURI;
		protected String _localName;
		protected String _qName;

		public String getNamespaceURI() {
			return _namespaceURI;
		}

		public String getLocalName() {
			return _localName;
		}

		public String getQName() {
			return _qName;
		}

		public NamedNode(String namespaceURI, String localName, String qName) {
			_namespaceURI = namespaceURI;
			_localName = localName;
			_qName = qName;
		}
	}

	public static class Element extends NamedNode {
		protected Attributes _atts;
		protected ArrayList<Node> _children = null;

		public List<Node> getChildren() {
			return (_children != null) ? _children : new ArrayList<Node>();
		}

		public Element(String namespaceURI, String localName, String qName, Attributes atts) {
			super(namespaceURI, localName, qName);
			_atts = atts;
			if (atts != null) {
				int max = atts.getLength();
				for (int i = 0; i < max; i++) {
					this.addChild(new Attribute(atts.getURI(i), atts.getLocalName(i), atts.getQName(i), atts.getValue(i)));
				}
			}
		}

		public Attributes getAttributes() {
			return _atts;
		}

		public void addChild(Node item) {
			if (_children == null)
				_children = new ArrayList<Node>();
			_children.add(item);
			if (item instanceof Element) {
				((Element) item)._parent = this;
			}
		}

		public void compactChildren() {
			if (_children != null)
				if (_children.isEmpty())
					_children = null;
				else
					_children.trimToSize();
		}

		/**
		 * Returns the attribute value for the attribute with the given name and with the same namespace as the enclosing
		 * element; null if there is no such attribute, and the empty string if the attribute value is empty.
		 * 
		 * @param name
		 *          local name of the attribute whose value to be returned
		 * @return the named attribute's value, or null if no such attribute
		 */
		public String getAttributeValue(String localName) {
			return getAttributeValue(localName, "");
		}

		public String getAttributeValue(String localName, String namespaceURI) {
			if (getChildren() == null)
				return null;
			for (Iterator<Node> it = getChildren().iterator(); it.hasNext();) {
				Node node = it.next();
				if (node instanceof Attribute) {
					Attribute attr = (Attribute) node;
					String ns2 = attr.getNamespaceURI();
					if ((namespaceURI == null && ns2 == null || namespaceURI != null && ns2 != null && namespaceURI.equals(ns2))
							&& localName.equals(attr.getLocalName())) {
						return attr.getValue();
					}
				}
			}
			return null;
		}

		/**
		 * Returns an iterator to child elements with the given name and with the same namespace as the enclosing element.
		 * 
		 * @param name
		 *          local name of elements to iterate
		 * @return the iterator
		 */
		public Iterator<Element> findChildren(String localName) {
			return new ChildElementIterator(getNamespaceURI(), (getChildren() != null) ? getChildren().iterator() : null,
					localName);
		}

		/**
		 * Concatenates and returns all child text nodes.
		 * 
		 * @return string content of the element
		 */
		public String getText() {
			StringBuffer sb = new StringBuffer();
			for (Iterator<Node> it = getChildren().iterator(); it.hasNext();) {
				Node node = it.next();
				if (node instanceof Text) {
					Text text = (Text) node;
					sb.append(text.getValue());
				}
			}
			return sb.toString();
		}
	}

	private static class ChildElementIterator implements Iterator<Element> {
		private final String _namespaceURI;
		private final Iterator<Node> _baseIterator;
		private final String _localName;
		private Element _next;

		ChildElementIterator(String namespaceURI, Iterator<Node> baseIterator, String localName) {
			_namespaceURI = namespaceURI;
			_baseIterator = baseIterator;
			_localName = localName;
			findNext();
		}

		public boolean hasNext() {
			return _next != null;
		}

		public Element next() {
			Element result = _next;
			findNext();
			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		private void findNext() {
			if (_baseIterator == null) {
				_next = null;
				return;
			}
			while (_baseIterator.hasNext()) {
				Node node = _baseIterator.next();
				if (node instanceof Element) {
					Element el = (Element) node;
					String ns2 = el.getNamespaceURI();
					if ((_namespaceURI == null && ns2 == null || _namespaceURI != null && ns2 != null
							&& _namespaceURI.equals(ns2))
							&& _localName.equals(el.getLocalName())) {
						_next = el;
						return;
					}
				}
			}
			_next = null;
		}
	}

	public static class Attribute extends NamedNode {
		protected String _value = null;

		public String getValue() {
			return _value;
		}

		public Attribute(String namespaceURI, String localName, String qName, String value) {
			super(namespaceURI, localName, qName);
			_value = value;
		}
	}

	public static class Text extends Node {
		String _value = null;

		public String getValue() {
			return _value;
		}

		public Text(char[] ch, int start, int length) {
			_value = new String(ch, start, length);
		}

		public Text(StringBuffer buffer) {
			_value = buffer.toString();
		}

		public Text(String value) {
			_value = value;
		}
	}

	/* END OF DOM */
	Element _currentElement;

	/**
	 * A TreeContentHandler must be given the name of the root node, since nothing else would allow us to reconstruct the
	 * name. Rememeber, this could be an extension element (Z-element in HL7-speak.)
	 */
	public TreeContentHandler(String namespaceURI, String localName, String qName, Attributes atts) {
		_currentElement = new Element(namespaceURI, localName, qName, atts);
	}

	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
		if (_currentTextBuffer != null) { // flush current text node
			_currentElement.addChild(new Text(_currentTextBuffer));
			_currentTextBuffer = null;
		}
		Element newElement = new Element(namespaceURI, localName, qName, atts);
		_currentElement.addChild(newElement);
		_currentElement = newElement;
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (_currentTextBuffer != null) { // flush current text node
			_currentElement.addChild(new Text(_currentTextBuffer));
			_currentTextBuffer = null;
		}
		_currentElement.compactChildren();
		Element oldElement = _currentElement.getParent();
		if (oldElement == null) { // we're back at the top
			// we're done, return results, thank you, good bye
			returnResult(_currentElement);
		} else
			_currentElement = oldElement;
	}

	/**
	 * Holds the current text node. Since characters events can repeat and should be glued together to make a single text
	 * node, we use this string buffer to hold the current text node.
	 * 
	 * <p>
	 * The charactes event only gathers the contents of this buffer, at the beginning of any event that delimits the text
	 * node, we must flush this buffer into a Text node.
	 */
	private StringBuffer _currentTextBuffer = null;

	public void characters(char[] ch, int start, int length) {
		if (_currentTextBuffer == null)
			_currentTextBuffer = new StringBuffer(length);
		_currentTextBuffer.append(ch, start, length);
	}

	/** Strip all ignorable whitespace. */
	public void ignorableWhitespace(char[] ch, int start, int length) {
		// don't think this would ever happen, but safer is safer.
		if (_currentTextBuffer != null) { // flush current text node
			_currentElement.addChild(new Text(_currentTextBuffer));
			_currentTextBuffer = null;
		}
	}

	/**
	 * A standard override to make an object cloneable.
	 * 
	 * @return a clone object
	 */
	public Object clone() throws CloneNotSupportedException {
		// FIXME: This does shallow cloning. Is that OK???
		return super.clone();
	}
}

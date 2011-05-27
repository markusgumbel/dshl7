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

import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.SAXException;

/**
 * This little guy knows how to build TreeContenHandler.Element value.
 */
public class ElementPresenter {
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	private final ElementBuilder builder_ = new TreeContentElementBuilder();

	// ---------------------------------------------------------------------------
	public static class TreeContentElementBuilder implements org.hl7.xml.ElementBuilder {
		// .......................................................................
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, TreeContentHandler.Element element)
				throws BuilderException {
			try {
				if (element == null)
					return;
				List<TreeContentHandler.Node> children = element.getChildren();
				for (Iterator iterator = children.iterator(); iterator.hasNext();) {
					TreeContentHandler.Node node = (TreeContentHandler.Node) iterator.next();
					if (node instanceof TreeContentHandler.Element)
						build(builder, (TreeContentHandler.Element) node);
					else if (node instanceof TreeContentHandler.Attribute) {
						TreeContentHandler.Attribute attribute = (TreeContentHandler.Attribute) node;
						builder.addAttribute(attribute.getLocalName(), attribute.getValue());
					}
				}
				builder.startElement(element.getLocalName());
				builder.characters(element.getText());
				builder.endElement(element.getLocalName());
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}
	}

	public ElementBuilder getBuilder() {
		return builder_;
	}
}

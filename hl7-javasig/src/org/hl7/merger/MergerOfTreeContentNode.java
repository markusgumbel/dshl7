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
package org.hl7.merger;

import org.hl7.meta.Feature;
import org.hl7.rim.RimObject;
import org.hl7.util.ApplicationContext;
import org.hl7.xml.parser.Extensible;
import org.hl7.xml.parser.TreeContentHandler;
import org.xml.sax.Locator;

/** Merges association data. 
		Right now this just makes a (new) association assignment, but in the future
    there is lots more to come, such as update modes, merging relationship 
    classes, etc.
*/
public class MergerOfTreeContentNode<C extends RimObject, T extends TreeContentHandler.Node> implements Merger<C, T> {
	public MergerOfTreeContentNode(ApplicationContext applicationContext) { }
	
	public boolean isStaticallyApplicable(Object object) {
		return object != null && object instanceof Extensible;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && value instanceof TreeContentHandler.Node;
	}

	/** Merge the object. */
	public C merge(C object, Feature feature, T value, Locator loc) {
		((Extensible)object).addExtensionNode(value);
		return object;
	}

	public C finish(C object, Locator loc) { 
		return object;
	}
}

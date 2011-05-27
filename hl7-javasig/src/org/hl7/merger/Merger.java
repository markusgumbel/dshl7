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
import org.xml.sax.Locator;

/** A tool which we can use to merge a data objects with the 
		database. Really this does not belong into the parser package,
		as it can be used with any object, not just those objects 
		which are partially parsed.
*/
public interface Merger<C extends RimObject, T> {
	/** Checks if this merger can handle the given object. */
	boolean isStaticallyApplicable(Object object);

	/** Checks if this merger can handle the given object and value. Must only be called if merger isStaticallyApplicable for object. */
	boolean isApplicable(C object, Object value);

	/** Merge the object. */
	C merge(C object, Feature feature, T value, Locator loc);

	/** Finish merging, flushes any pending state or releases any held resources. */
	C finish(C object, Locator loc);
}

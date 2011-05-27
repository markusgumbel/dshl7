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

import org.hl7.types.NullFlavor;
import org.hl7.types.ON;

public class ONnull extends ENnull implements ON {
	public static final ONnull NI = new ONnull(NullFlavorImpl.NI);
	public static final ONnull NA = new ONnull(NullFlavorImpl.NA);
	public static final ONnull UNK = new ONnull(NullFlavorImpl.UNK);
	public static final ONnull NASK = new ONnull(NullFlavorImpl.NASK);
	public static final ONnull ASKU = new ONnull(NullFlavorImpl.ASKU);
	public static final ONnull NAV = new ONnull(NullFlavorImpl.NAV);
	public static final ONnull OTH = new ONnull(NullFlavorImpl.OTH);
	public static final ONnull PINF = new ONnull(NullFlavorImpl.PINF);
	public static final ONnull NINF = new ONnull(NullFlavorImpl.NINF);

	private ONnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static ONnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(ONnull.class, nullFlavor);
	}
}

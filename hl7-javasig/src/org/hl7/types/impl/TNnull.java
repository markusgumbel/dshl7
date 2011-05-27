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
import org.hl7.types.TN;

public class TNnull extends ENnull implements TN {
	public static final TNnull NI = new TNnull(NullFlavorImpl.NI);
	public static final TNnull NA = new TNnull(NullFlavorImpl.NA);
	public static final TNnull UNK = new TNnull(NullFlavorImpl.UNK);
	public static final TNnull NASK = new TNnull(NullFlavorImpl.NASK);
	public static final TNnull ASKU = new TNnull(NullFlavorImpl.ASKU);
	public static final TNnull NAV = new TNnull(NullFlavorImpl.NAV);
	public static final TNnull OTH = new TNnull(NullFlavorImpl.OTH);
	public static final TNnull PINF = new TNnull(NullFlavorImpl.PINF);
	public static final TNnull NINF = new TNnull(NullFlavorImpl.NINF);

	private TNnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static TNnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(TNnull.class, nullFlavor);
	}
}

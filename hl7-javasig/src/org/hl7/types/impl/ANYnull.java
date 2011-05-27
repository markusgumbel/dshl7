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

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.NullFlavor;

public class ANYnull extends ANYimpl implements ANY, Comparable<ANYnull> {
	public static final ANYnull NI = new ANYnull(NullFlavorImpl.NI);
	public static final ANYnull NA = new ANYnull(NullFlavorImpl.NA);
	public static final ANYnull UNK = new ANYnull(NullFlavorImpl.UNK);
	public static final ANYnull NASK = new ANYnull(NullFlavorImpl.NASK);
	public static final ANYnull ASKU = new ANYnull(NullFlavorImpl.ASKU);
	public static final ANYnull NAV = new ANYnull(NullFlavorImpl.NAV);
	public static final ANYnull OTH = new ANYnull(NullFlavorImpl.OTH);
	public static final ANYnull PINF = new ANYnull(NullFlavorImpl.PINF);
	public static final ANYnull NINF = new ANYnull(NullFlavorImpl.NINF);
	public static final ANYnull MSK = new ANYnull(NullFlavorImpl.MSK);
	public static final ANYnull NP = new ANYnull(NullFlavorImpl.NP);

	protected ANYnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static ANYnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(ANYnull.class, nullFlavor);
	}

	public CS type() {
		return CSnull.NA;
	}

	/**
	 * FIXME: is NA correct or should it be derived from this and that?
	 */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	@Override
    public String toString() {
		return nullFlavor().code().toString();
	}

	public int compareTo(final ANYnull that) {
		return that.isNull().isTrue() ? 0 : -1;
	}
}

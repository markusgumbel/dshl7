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
import org.hl7.types.DSET;
import org.hl7.types.NullFlavor;
import org.hl7.types.QSET;
import org.hl7.types.ST;
import org.hl7.types.TEL;
import org.hl7.types.TS;

/**
 * Minimal implementation of TEL as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class TELnull extends ANYimpl implements TEL {
	public static final TELnull NI = new TELnull(NullFlavorImpl.NI);
	public static final TELnull NA = new TELnull(NullFlavorImpl.NA);
	public static final TELnull UNK = new TELnull(NullFlavorImpl.UNK);
	public static final TELnull NASK = new TELnull(NullFlavorImpl.NASK);
	public static final TELnull ASKU = new TELnull(NullFlavorImpl.ASKU);
	public static final TELnull NAV = new TELnull(NullFlavorImpl.NAV);
	public static final TELnull OTH = new TELnull(NullFlavorImpl.OTH);
	public static final TELnull PINF = new TELnull(NullFlavorImpl.PINF);
	public static final TELnull NINF = new TELnull(NullFlavorImpl.NINF);

	private TELnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static TELnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(TELnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	public CS scheme() {
		return CSnull.NA;
	}

	public ST address() {
		return STnull.NA;
	}

	@SuppressWarnings("unchecked")
	public QSET<TS> useablePeriod() {
		return QSETnull.NA;
	}

	@SuppressWarnings("unchecked")
	public DSET<CS> use() {
		return (DSET<CS>) DSETnull.NA;
	}
}
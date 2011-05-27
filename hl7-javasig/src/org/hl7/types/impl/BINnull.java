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

import java.util.Iterator;

import org.hl7.types.ANY;
import org.hl7.types.BIN;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;

/**
 * Minimal implementation of BIN as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class BINnull extends ANYimpl implements BIN {
	public static final BINnull NI = new BINnull(NullFlavorImpl.NI);
	public static final BINnull NA = new BINnull(NullFlavorImpl.NA);
	public static final BINnull UNK = new BINnull(NullFlavorImpl.UNK);
	public static final BINnull NASK = new BINnull(NullFlavorImpl.NASK);
	public static final BINnull ASKU = new BINnull(NullFlavorImpl.ASKU);
	public static final BINnull NAV = new BINnull(NullFlavorImpl.NAV);
	public static final BINnull OTH = new BINnull(NullFlavorImpl.OTH);
	public static final BINnull PINF = new BINnull(NullFlavorImpl.PINF);
	public static final BINnull NINF = new BINnull(NullFlavorImpl.NINF);

	private BINnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static BINnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(BINnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	public BL head() {
		throw new UnsupportedOperationException();
	}

	public LIST<BL> tail() {
		throw new UnsupportedOperationException();
	}

	public Iterator<BL> iterator() {
		throw new UnsupportedOperationException();
	}

	public BL isEmpty() {
		return BLimpl.NA;
	}

	public BL nonEmpty() {
		return BLimpl.NA;
	}

	public INT length() {
		return INTnull.NA;
	}
};

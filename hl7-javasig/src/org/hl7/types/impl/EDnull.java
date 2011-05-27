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
import org.hl7.types.CS;
import org.hl7.types.ED;
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.TEL;

/**
 * Minimal implementation of ED as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class EDnull extends ANYimpl implements ED {
	/**
	 * TODO: Duplicate code of EDNull and STNull regarding to NullFlavor
	 */
	public static final EDnull NI = new EDnull(NullFlavorImpl.NI);
	public static final EDnull NA = new EDnull(NullFlavorImpl.NA);
	public static final EDnull UNK = new EDnull(NullFlavorImpl.UNK);
	public static final EDnull NASK = new EDnull(NullFlavorImpl.NASK);
	public static final EDnull ASKU = new EDnull(NullFlavorImpl.ASKU);
	public static final EDnull NAV = new EDnull(NullFlavorImpl.NAV);
	public static final EDnull OTH = new EDnull(NullFlavorImpl.OTH);
	public static final EDnull PINF = new EDnull(NullFlavorImpl.PINF);
	public static final EDnull NINF = new EDnull(NullFlavorImpl.NINF);

	private EDnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static EDnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(EDnull.class, nullFlavor);
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

	// The ED interface
	public CS mediaType() {
		return CSnull.NA;
	}

	public CS charset() {
		return CSnull.NA;
	}

	public CS compression() {
		return CSnull.NA;
	}

	public CS language() {
		return CSnull.NA;
	}

	public TEL reference() {
		return TELnull.NA;
	}

	public BIN integrityCheck() {
		return BINnull.NA;
	}

	public CS integrityCheckAlgorithm() {
		return CSnull.NA;
	}

	public ED thumbnail() {
		return EDnull.NA;
	}
}
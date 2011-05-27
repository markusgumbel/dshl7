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

import org.hl7.types.BIN;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.ED;
import org.hl7.types.ENXP;
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.ST;
import org.hl7.types.TEL;

public class ENXPnull extends STnull implements ENXP {
	public static final ENXPnull NI = new ENXPnull(NullFlavorImpl.NI);
	public static final ENXPnull NA = new ENXPnull(NullFlavorImpl.NA);
	public static final ENXPnull UNK = new ENXPnull(NullFlavorImpl.UNK);
	public static final ENXPnull NASK = new ENXPnull(NullFlavorImpl.NASK);
	public static final ENXPnull ASKU = new ENXPnull(NullFlavorImpl.ASKU);
	public static final ENXPnull NAV = new ENXPnull(NullFlavorImpl.NAV);
	public static final ENXPnull OTH = new ENXPnull(NullFlavorImpl.OTH);
	public static final ENXPnull PINF = new ENXPnull(NullFlavorImpl.PINF);
	public static final ENXPnull NINF = new ENXPnull(NullFlavorImpl.NINF);

	protected ENXPnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static ENXPnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(ENXPnull.class, nullFlavor);
	}

	public CS type() {
		return CSnull.NA;
	}

	@SuppressWarnings("unchecked")
	public DSET<CS> qualifier() {
		return (DSET<CS>) DSETnull.NA;
	}

	/**
	 * use equal() and hashCode() method from the super class.
	 */
	// /** FIXME: is NA correct or should it be derived from this and that?
	// */
	// public BL equal(ANY that) { return BLimpl.NA; }
	@Override
    public ST headST() {
		throw new UnsupportedOperationException();
	}

	@Override
    public ST tailST() {
		throw new UnsupportedOperationException();
	}

	@Override
    public Iterator<ST> listSTIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
    public String toString() {
		return nullFlavor().code().toString();
	}

	@Override
    public BL head() {
		throw new UnsupportedOperationException();
	}

	@Override
    public LIST<BL> tail() {
		throw new UnsupportedOperationException();
	}

	public Iterator<BL> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
    public BL isEmpty() {
		return BLimpl.NA;
	}

	@Override
    public BL nonEmpty() {
		return BLimpl.NA;
	}

	@Override
    public INT length() {
		return INTnull.NA;
	}

	// The ED interface
	@Override
    public CS mediaType() {
		return CSnull.NA;
	}

	@Override
    public CS charset() {
		return CSnull.NA;
	}

	@Override
    public CS compression() {
		return CSnull.NA;
	}

	@Override
    public CS language() {
		return CSnull.NA;
	}

	@Override
    public TEL reference() {
		return TELnull.NA;
	}

	@Override
    public BIN integrityCheck() {
		return BINnull.NA;
	}

	@Override
    public CS integrityCheckAlgorithm() {
		return CSnull.NA;
	}

	@Override
    public ED thumbnail() {
		return EDnull.NA;
	}
}

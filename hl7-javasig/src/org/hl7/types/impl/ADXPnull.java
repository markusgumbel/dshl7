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

import org.hl7.types.ADXP;
import org.hl7.types.ANY;
import org.hl7.types.BIN;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.ED;
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.ST;
import org.hl7.types.TEL;

public class ADXPnull extends STnull implements ADXP {
	public static final ADXPnull NI = new ADXPnull(NullFlavorImpl.NI);
	public static final ADXPnull NA = new ADXPnull(NullFlavorImpl.NA);
	public static final ADXPnull UNK = new ADXPnull(NullFlavorImpl.UNK);
	public static final ADXPnull NASK = new ADXPnull(NullFlavorImpl.NASK);
	public static final ADXPnull ASKU = new ADXPnull(NullFlavorImpl.ASKU);
	public static final ADXPnull NAV = new ADXPnull(NullFlavorImpl.NAV);
	public static final ADXPnull OTH = new ADXPnull(NullFlavorImpl.OTH);
	public static final ADXPnull PINF = new ADXPnull(NullFlavorImpl.PINF);
	public static final ADXPnull NINF = new ADXPnull(NullFlavorImpl.NINF);

	private ADXPnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static ADXPnull valueOf(final String nullFlavorString) {
	    return ANYimpl.nullValueOf(ADXPnull.class, nullFlavorString);
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

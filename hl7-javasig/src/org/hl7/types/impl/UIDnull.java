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
import org.hl7.types.ST;
import org.hl7.types.TEL;
import org.hl7.types.UID;

/**
 * Minimal implementation of UID as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class UIDnull extends ANYimpl implements UID {
	public static final UIDnull NI = new UIDnull(NullFlavorImpl.NI);
	public static final UIDnull NA = new UIDnull(NullFlavorImpl.NA);
	public static final UIDnull UNK = new UIDnull(NullFlavorImpl.UNK);
	public static final UIDnull NASK = new UIDnull(NullFlavorImpl.NASK);
	public static final UIDnull ASKU = new UIDnull(NullFlavorImpl.ASKU);
	public static final UIDnull NAV = new UIDnull(NullFlavorImpl.NAV);
	public static final UIDnull OTH = new UIDnull(NullFlavorImpl.OTH);
	public static final UIDnull PINF = new UIDnull(NullFlavorImpl.PINF);
	public static final UIDnull NINF = new UIDnull(NullFlavorImpl.NINF);

	private UIDnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static UIDnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(UIDnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	public ST headST() {
		throw new UnsupportedOperationException();
	}

	public ST tailST() {
		throw new UnsupportedOperationException();
	}

	public Iterator<ST> listSTIterator() {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
		// return BLimpl.NA;
	}

	public BL nonEmpty() {
		throw new UnsupportedOperationException();
		// return BLimpl.NA;
	}

	public INT length() {
		throw new UnsupportedOperationException();
		// return BLimpl.NA;
	}

	// The ED interface
	public CS mediaType() {
		throw new UnsupportedOperationException();
		// return CSnull.NA;
	}

	public CS charset() {
		throw new UnsupportedOperationException();
		// return CSnull.NA;
	}

	public CS compression() {
		throw new UnsupportedOperationException();
		// return CSnull.NA;
	}

	public CS language() {
		throw new UnsupportedOperationException();
		// return CSnull.NA;
	}

	public TEL reference() {
		throw new UnsupportedOperationException();
		// return TELnull.NA;
	}

	public BIN integrityCheck() {
		throw new UnsupportedOperationException();
		// return BINnull.NA;
	}

	public CS integrityCheckAlgorithm() {
		throw new UnsupportedOperationException();
		// return CSnull.NA;
	}

	public ED thumbnail() {
		throw new UnsupportedOperationException();
		// return EDnull.NA;
	}
};

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

/**
 * Minimal implementation of ST as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public class STnull extends ANYimpl implements ST {
	public static final STnull NI = new STnull(NullFlavorImpl.NI);
	public static final STnull NA = new STnull(NullFlavorImpl.NA);
	public static final STnull UNK = new STnull(NullFlavorImpl.UNK);
	public static final STnull NASK = new STnull(NullFlavorImpl.NASK);
	public static final STnull ASKU = new STnull(NullFlavorImpl.ASKU);
	public static final STnull NAV = new STnull(NullFlavorImpl.NAV);
	public static final STnull OTH = new STnull(NullFlavorImpl.OTH);
	public static final STnull PINF = new STnull(NullFlavorImpl.PINF);
	public static final STnull NINF = new STnull(NullFlavorImpl.NINF);
	public static final STnull MSK = new STnull(NullFlavorImpl.MSK);
	public static final STnull NP = new STnull(NullFlavorImpl.NP);

	protected STnull(final NullFlavor nf) {
		super(nf);
	}

	public static STnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(STnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	// public BL equal(ANY that) { return BLimpl.NA; }
	/**
	 * the equal will tell whether the given ANY is equal to this object.
	 * 
	 * @param that
	 * @return BLimpl.TRUE if that is equal to this object.
	 */
	@Override
    public BL equal(final ANY that) {
		/**
		 * The contract of equal shall be carried no matter the semantics value of the class itself.
		 */
		if (that instanceof STnull) {
			final STnull thatObj = (STnull) that;
			return thatObj.nullFlavor().equal(this.nullFlavor());
		} else {
			return BLimpl.FALSE;
		}
	}

	// equals() method has been overriden in ANYimpl class
	/**
	 * override hashCode() method.
	 * 
	 * @return hashCode
	 */
	@Override
    public int hashCode() {
		return this.nullFlavor().hashCode();
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
};

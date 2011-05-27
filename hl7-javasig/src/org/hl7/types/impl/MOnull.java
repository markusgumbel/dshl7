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
import org.hl7.types.CD;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.MO;
import org.hl7.types.NullFlavor;
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.SET;

/**
 * Minimal implementation of MO as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class MOnull extends ORDimpl implements MO {
	public static final MOnull NI = new MOnull(NullFlavorImpl.NI);
	public static final MOnull NA = new MOnull(NullFlavorImpl.NA);
	public static final MOnull UNK = new MOnull(NullFlavorImpl.UNK);
	public static final MOnull NASK = new MOnull(NullFlavorImpl.NASK);
	public static final MOnull ASKU = new MOnull(NullFlavorImpl.ASKU);
	public static final MOnull NAV = new MOnull(NullFlavorImpl.NAV);
	public static final MOnull OTH = new MOnull(NullFlavorImpl.OTH);
	public static final MOnull PINF = new MOnull(NullFlavorImpl.PINF);
	public static final MOnull NINF = new MOnull(NullFlavorImpl.NINF);

	private MOnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static MOnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(MOnull.class, nullFlavor);
	}

	/**
	 * FIXME: is NA correct or should it be derived from this and that?
	 * 
	 * FIXME: infinities may need to be treated differently!
	 */
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
		if (that instanceof MOnull) {
			final MOnull thatObj = (MOnull) that;
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

	// FIXME: we're returning NAs here. Shouldn't we return a null
	// that is this flavor or a flavor that's a result of the
	// combination of this and that?
	// FIXME: infinities can be compared!
	@Override
    public BL lessOrEqual(final ORD x) {
		return BLimpl.NA;
	}

	@Override
    public BL compares(final ORD x) {
		return BLimpl.NA;
	}

	public QTY plus(final QTY.diff x) {
		return MOnull.NA;
	}

	public MO plus(final MO x) {
		return MOnull.NA;
	}

	public MO minus(final QTY x) {
		throw new UnsupportedOperationException();
		// return MOnull.NA;
	}

	public MO minus(final QTY.diff x) {
		return MOnull.NA;
	}

	public MO minus(final MO x) {
		throw new UnsupportedOperationException();
		// return MOnull.NA;
	}

	public BL isZero() {
		return BLimpl.NA;
	}

	public BL nonZero() {
		return isZero().not();
	}

	public REAL value() {
		return REALnull.NA;
	}

	public CS currency() {
		return CSnull.NA;
	}

	public MO canonical() {
		return MOnull.NA;
	}

	public MO negated() {
		return MOnull.NA;
	}

	public MO times(final REAL that) {
		return MOnull.NA;
	}

	public MO times(final MO that) {
		return MOnull.NA;
	}

	public MO inverted() {
		return MOnull.NA;
	}

	public MO power(final INT that) {
		return MOnull.NA;
	}

	/**
	 * This is really SET<PQR> but PQR is an extension of CD and we need this so that one can have CDs that are also MOs
	 * (which is currency of measure.
	 */
	public SET<CD> translation() {
		throw new UnsupportedOperationException();
	}

	public MO epsilon() {
		return NI;
	}
}

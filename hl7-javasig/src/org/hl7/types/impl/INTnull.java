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
import org.hl7.types.INT;
import org.hl7.types.NullFlavor;
import org.hl7.types.Numeric;
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;

/**
 * Minimal implementation of INT as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public class INTnull extends ORDimpl implements INT, Numeric {
	public static final INTnull NI = new INTnull(NullFlavorImpl.NI);
	public static final INTnull NA = new INTnull(NullFlavorImpl.NA);
	public static final INTnull UNK = new INTnull(NullFlavorImpl.UNK);
	public static final INTnull NASK = new INTnull(NullFlavorImpl.NASK);
	public static final INTnull ASKU = new INTnull(NullFlavorImpl.ASKU);
	public static final INTnull NAV = new INTnull(NullFlavorImpl.NAV);
	public static final INTnull OTH = new INTnull(NullFlavorImpl.OTH);
	public static final INTnull PINF = INTpinf.VALUE;
	public static final INTnull NINF = INTninf.VALUE;

	protected INTnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static INTnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(INTnull.class, nullFlavor);
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
	 * At least this function will return BLimpl.TRUE on INTnull.PINF.equal(INTnull.PINF) and BLimpl.FALSE on
	 * INTnull.PINF.equal(INTnull.NI)!
	 * 
	 * @param that
	 * @return BLimpl.TRUE if that is equal to this object.
	 */
	@Override
    public BL equal(final ANY that) {
		/**
		 * The contract of equal shall be carried no matter the semantics value of the class itself.
		 */
		if (that instanceof INTnull) {
			final INTnull thatObj = (INTnull) that;
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

	public BL equal(final int x) {
		return BLimpl.NA;
	}

	public BL equal(final long x) {
		return BLimpl.NA;
	}

	public BL equal(final float x) {
		return BLimpl.NA;
	}

	public BL equal(final double x) {
		return BLimpl.NA;
	}

	// FIXME: we're returning NAs here. Shouldn't we return a null
	// that is this flavor or a flavor that's a result of the
	// combination of this and that?
	// FIXME: infinities can be compared!
	@Override
    public BL lessOrEqual(final ORD x) {
		return BLimpl.NA;
	}

	public BL lessOrEqual(final int x) {
		return BLimpl.NA;
	}

	public BL lessOrEqual(final long x) {
		return BLimpl.NA;
	}

	public BL lessOrEqual(final float x) {
		return BLimpl.NA;
	}

	public BL lessOrEqual(final double x) {
		return BLimpl.NA;
	}

	@Override
    public BL compares(final ORD x) {
		return BLimpl.NA;
	}

	public BL compares(final long x) {
		return BLimpl.TRUE;
	}

	public QTY plus(final QTY.diff x) {
		return INTnull.NA;
	}

	public INT plus(final INT x) {
		return INTnull.NA;
	}

	public INT plus(final int x) {
		return INTnull.NA;
	}

	public INT plus(final long x) {
		return INTnull.NA;
	}

	public INT plus(final float x) {
		return INTnull.NA;
	}

	public INT plus(final double x) {
		return INTnull.NA;
	}

	public INT minus(final QTY x) {
		return INTnull.NA;
	}

	public INT minus(final QTY.diff x) {
		return INTnull.NA;
	}

	public INT minus(final INT x) {
		return INTnull.NA;
	}

	public INT minusReverse(final int x) {
		return INTnull.NA;
	}

	public INT minusReverse(final long x) {
		return INTnull.NA;
	}

	public INT minusReverse(final float x) {
		return INTnull.NA;
	}

	public INT minusReverse(final double x) {
		return INTnull.NA;
	}

	public BL isZero() {
		return BLimpl.NA;
	}

	public BL nonZero() {
		return BLimpl.NA;
	}

	public BL isOne() {
		return BLimpl.NA;
	}

	public INT successor() {
		return INTnull.NA;
	}

	public INT predecessor() {
		return INTnull.NA;
	}

	public INT times(final REAL x) {
		return INTnull.NA;
	}

	public INT times(final INT x) {
		return INTnull.NA;
	}

	public INT times(final int x) {
		return INTnull.NA;
	}

	public INT times(final long x) {
		return INTnull.NA;
	}

	public INT dividedBy(final INT that) {
		return INTnull.NA;
	}

	public INT dividedByReverse(final int that) {
		return INTnull.NA;
	}

	public INT dividedByReverse(final long that) {
		return INTnull.NA;
	}

	public INT negated() {
		return INTnull.NA;
	}

	public BL isNegative() {
		return BLimpl.NA;
	}

	public BL nonNegative() {
		return BLimpl.NA;
	}

	public int intValue() {
		throw new NullPointerException();
	}

	public long longValue() {
		throw new NullPointerException();
	}

	public float floatValue() {
		throw new NullPointerException();
	}

	public double doubleValue() {
		throw new NullPointerException();
	}

	public REAL toREAL() {
		return REALnull.valueOf(nullFlavor().toString());
	}

	public INT epsilon() {
		return INTlongAdapter.ONE;
	}
};

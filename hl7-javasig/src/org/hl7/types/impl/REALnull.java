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
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;

/**
 * Minimal implementation of REAL as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public class REALnull extends ORDimpl implements REAL {
	public static final REALnull NI = new REALnull(NullFlavorImpl.NI);
	public static final REALnull NA = new REALnull(NullFlavorImpl.NA);
	public static final REALnull UNK = new REALnull(NullFlavorImpl.UNK);
	public static final REALnull NASK = new REALnull(NullFlavorImpl.NASK);
	public static final REALnull ASKU = new REALnull(NullFlavorImpl.ASKU);
	public static final REALnull NAV = new REALnull(NullFlavorImpl.NAV);
	public static final REALnull OTH = new REALnull(NullFlavorImpl.OTH);
	public static final REALnull PINF = REALpinf.VALUE;
	public static final REALnull NINF = REALninf.VALUE;

	protected REALnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static REALnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(REALnull.class, nullFlavor);
	}

	/**
	 * FIXME: is NA correct or should it be derived from this and that?
	 * 
	 * FIXME: infinities may need to be treated differently!
	 */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
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
		return REALnull.NA;
	}

	public REAL plus(final REAL x) {
		return REALnull.NA;
	}

	public REAL plus(final int x) {
		return REALnull.NA;
	}

	public REAL plus(final long x) {
		return REALnull.NA;
	}

	public REAL plus(final float x) {
		return REALnull.NA;
	}

	public REAL plus(final double x) {
		return REALnull.NA;
	}

	public REAL plus(final int x, final INT precision) {
		return REALnull.NA;
	}

	public REAL plus(final long x, final INT precision) {
		return REALnull.NA;
	}

	public REAL plus(final float x, final INT precision) {
		return REALnull.NA;
	}

	public REAL plus(final double x, final INT precision) {
		return REALnull.NA;
	}

	public REAL minus(final QTY x) {
		return REALnull.NA;
	}

	public REAL minus(final QTY.diff x) {
		return REALnull.NA;
	}

	public REAL minus(final REAL x) {
		return REALnull.NA;
	}

	public REAL minusReverse(final int x) {
		return REALnull.NA;
	}

	public REAL minusReverse(final long x) {
		return REALnull.NA;
	}

	public REAL minusReverse(final float x) {
		return REALnull.NA;
	}

	public REAL minusReverse(final double x) {
		return REALnull.NA;
	}

	public REAL minusReverse(final int x, final INT precision) {
		return REALnull.NA;
	}

	public REAL minusReverse(final long x, final INT precision) {
		return REALnull.NA;
	}

	public REAL minusReverse(final float x, final INT precision) {
		return REALnull.NA;
	}

	public REAL minusReverse(final double x, final INT precision) {
		return REALnull.NA;
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

	public REAL times(final REAL x) {
		return REALnull.NA;
	}

	public REAL times(final int x) {
		return REALnull.NA;
	}

	public REAL times(final long x) {
		return REALnull.NA;
	}

	public REAL times(final float x) {
		return REALnull.NA;
	}

	public REAL times(final double x) {
		return REALnull.NA;
	}

	public REAL times(final int x, final INT precision) {
		return REALnull.NA;
	}

	public REAL times(final long x, final INT precision) {
		return REALnull.NA;
	}

	public REAL times(final float x, final INT precision) {
		return REALnull.NA;
	}

	public REAL times(final double x, final INT precision) {
		return REALnull.NA;
	}

	public REAL dividedBy(final REAL that) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final int that) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final long that) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final float that) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final double that) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final int that, final INT precision) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final long that, final INT precision) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final float that, final INT precision) {
		return REALnull.NA;
	}

	public REAL dividedByReverse(final double that, final INT precision) {
		return REALnull.NA;
	}

	public REAL negated() {
		return REALnull.NA;
	}

	public REAL inverted() {
		return REALnull.NA;
	}

	public REAL power(final REAL x) {
		return REALnull.NA;
	}

	public REAL powerReverse(final int x) {
		return REALnull.NA;
	}

	public REAL powerReverse(final long x) {
		return REALnull.NA;
	}

	public REAL powerReverse(final float x) {
		return REALnull.NA;
	}

	public REAL powerReverse(final double x) {
		return REALnull.NA;
	}

	public REAL powerReverse(final int x, final INT precision) {
		return REALnull.NA;
	}

	public REAL powerReverse(final long x, final INT precision) {
		return REALnull.NA;
	}

	public REAL powerReverse(final float x, final INT precision) {
		return REALnull.NA;
	}

	public REAL powerReverse(final double x, final INT precision) {
		return REALnull.NA;
	}

	public REAL log() {
		return REALnull.NA;
	}

	public REAL exp() {
		return REALnull.NA;
	}

	public REAL floor() {
		return REALnull.NA;
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

	public INT precision() {
		throw new NullPointerException();
	}
	
	public REAL withLimitedPrecision(final INT precisionLimit) {
		return REALnull.NA;
	}

	public diff epsilon() {
		return REALdoubleAdapter.ONE;
	}

	public String toString(final INT precision) {
		return toString();
	}
};

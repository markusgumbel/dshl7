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
import org.hl7.types.INT;
import org.hl7.types.NullFlavor;
import org.hl7.types.ORD;
import org.hl7.types.PQ;
import org.hl7.types.PQR;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.SET;

/**
 * Minimal implementation of PQ as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class PQnull extends ORDimpl implements PQ {
	public static final PQnull NI = new PQnull(NullFlavorImpl.NI);
	public static final PQnull NA = new PQnull(NullFlavorImpl.NA);
	public static final PQnull UNK = new PQnull(NullFlavorImpl.UNK);
	public static final PQnull NASK = new PQnull(NullFlavorImpl.NASK);
	public static final PQnull ASKU = new PQnull(NullFlavorImpl.ASKU);
	public static final PQnull NAV = new PQnull(NullFlavorImpl.NAV);
	public static final PQnull OTH = new PQnull(NullFlavorImpl.OTH);
	public static final PQnull PINF = new PQnull(NullFlavorImpl.PINF);
	public static final PQnull NINF = new PQnull(NullFlavorImpl.NINF);

	private PQnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static PQnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(PQnull.class, nullFlavor);
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
		if (that instanceof PQnull) {
			final PQnull thatObj = (PQnull) that;
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
		return PQnull.NA;
	}

	public PQ plus(final PQ x) {
		return PQnull.NA;
	}

	public PQ minus(final QTY x) {
		// throw new NullPointerException("Cannot use substraction against a null PQ value.");
		return PQnull.NA;
	}

	public PQ minus(final QTY.diff x) {
		return PQnull.NA;
	}

	public PQ minus(final PQ x) {
		// throw new NullPointerException("Cannot use substraction against a null PQ value.");
		return PQnull.NA;
	}

	// FIXME: the null handling needs to be more intelligent
	// FIXME: UNK.isZero = UNK, not NA!!!
	// FIXME: same for all the other operations
	public BL isZero() {
		return BLimpl.NA;
	}

	public BL nonZero() {
		return isZero().not();
	}

	public REAL value() {
		return REALnull.NA;
	}

	public REAL toREAL() {
		return REALnull.NA;
	}

	public CS unit() {
		return CSnull.NA;
	}

	public PQ canonical() {
		return PQnull.NA;
	}

	public PQ negated() {
		return PQnull.NA;
	}

	public PQ times(final REAL that) {
		return PQnull.NA;
	}

	public PQ times(final PQ that) {
		return PQnull.NA;
	}

	public PQ inverted() {
		return PQnull.NA;
	}

	public PQ power(final INT that) {
		return PQnull.NA;
	}

	public PQ dividedBy(final PQ that) {
		return PQnull.NA;
	}

	public PQ dividedBy(final REAL that) {
		return PQnull.NA;
	}

	public SET<PQR> translation() {
		return SETnull.NAV;
	}

	public PQ expressedIn(final PQ that) {
		return PQnull.NA;
	}
	public PQ withLimitedPrecision(final INT precisionLimit) {
		return PQnull.NA;		
	}
	public INT precision() {
		return INTnull.NA;		
	}
	public PQ epsilon() {
		return NI;
	}
}

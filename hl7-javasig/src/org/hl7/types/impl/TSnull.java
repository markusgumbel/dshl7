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

import java.util.Calendar;
import java.util.Date;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.NullFlavor;
import org.hl7.types.ORD;
import org.hl7.types.PQ;
import org.hl7.types.QTY;
import org.hl7.types.TS;

/**
 * Minimal implementation of TS as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class TSnull extends ORDimpl implements TS {
	public static final TSnull NI = new TSnull(NullFlavorImpl.NI);
	public static final TSnull NA = new TSnull(NullFlavorImpl.NA);
	public static final TSnull UNK = new TSnull(NullFlavorImpl.UNK);
	public static final TSnull NASK = new TSnull(NullFlavorImpl.NASK);
	public static final TSnull ASKU = new TSnull(NullFlavorImpl.ASKU);
	public static final TSnull NAV = new TSnull(NullFlavorImpl.NAV);
	public static final TSnull OTH = new TSnull(NullFlavorImpl.OTH);
	public static final TSnull PINF = new TSnull(NullFlavorImpl.PINF);
	public static final TSnull NINF = new TSnull(NullFlavorImpl.NINF);

	private TSnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static TSnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(TSnull.class, nullFlavor);
	}

	public Date toDate() {
		return null;
	}

	public Calendar toCalendar() {
		return null;
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
		if (that instanceof TSnull) {
			final TSnull thatObj = (TSnull) that;
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
		return TSnull.NA;
	}

	public TS plus(final PQ x) {
		return TSnull.NA;
	}

	public PQ minus(final QTY x) {
		return PQnull.NA;
	} // throw new UnsupportedOperationException();

	public TS minus(final QTY.diff x) {
		return TSnull.NA;
	}

	public PQ minus(final TS x) {
		return PQnull.NA;
	} // throw new UnsupportedOperationException();

	public PQ offset() {
		return PQnull.NA;
	} // throw new UnsupportedOperationException();

	public CS calendar() {
		return CSnull.NA;
	}

	public INT precision() {
		return INTnull.NA;
	}

	public PQ timezone() {
		throw new UnsupportedOperationException();
	}

	public PQ epsilon() {
		return TSjuDateAdapter.MILLISECOND;
	}
}

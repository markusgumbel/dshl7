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
import org.hl7.types.Numeric;
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;

/**
 * INT adapter to Java builtin long. We will have BigInteger as well.
 */
public final class INTlongAdapter extends ORDimpl implements INT, Numeric {
	private final long _value;
	/**
	 * Some flyweight's for common constants. Why not? Would be interesting to know how much they end up being used. If we
	 * have those and a byte-size wrapper, we can move them there. But I assume int is the smallest wrapper.
	 */
	public static INTlongAdapter MINUS_ONE = new INTlongAdapter(-1);
	public static INTlongAdapter ZERO = new INTlongAdapter(0);
	public static INTlongAdapter ONE = new INTlongAdapter(1);
	public static INTlongAdapter TWO = new INTlongAdapter(2);
	public static INTlongAdapter TEN = new INTlongAdapter(10);

	private INTlongAdapter(final long value) {
		super(null);
		this._value = value;
	}

	/**
	 * We should probably have a small array of reuseable objects of this type such that we can avoid object creation. How
	 * do we know, though, whether someone's still holding on to those? I guess weak references are the thing to use ...
	 * later.
	 */
	public static INT valueOf(final long x) {
		/*
		 * This is the only concern with those flyweight INTs, it takes long to used.
		 */
		switch ((int) x) {
		case -1:
			return INTlongAdapter.MINUS_ONE;
		case 0:
			return INTlongAdapter.ZERO;
		case 1:
			return INTlongAdapter.ONE;
		case 2:
			return INTlongAdapter.TWO;
		case 10:
			return INTlongAdapter.TEN;
		default:
			return new INTlongAdapter(x);
		}
	}

	public static INT valueOf(final double x) {
		if (Long.MIN_VALUE <= x && x <= Long.MAX_VALUE) {
			return valueOf((long) x);
		} else {
			throw new UnsupportedOperationException("overflow");
		}
	}

	public static INT valueOf(final String str) {
		return valueOf(Long.parseLong(str));
	}

	/**
	 * Test for equality. <p/> We make it super simple and effective: all this is doing is to unwrap my integer and I
	 * expect everyone to be able to take equal(long x), which is very straight forward and convenient for using this API.
	 */
	@Override
    public BL equal(final ANY that) {
		if (that instanceof QTY) {
			return this.minus((QTY) that).isZero();
		} else {
			return BLimpl.FALSE;
		}
	}

	public BL equal(final int x) {
		return BLimpl.valueOf(this._value == x);
	}

	public BL equal(final long x) {
		return BLimpl.valueOf(this._value == x);
	}

	public BL equal(final float x) {
		return BLimpl.valueOf(this._value == x);
	}

	public BL equal(final double x) {
		return BLimpl.valueOf(this._value == x);
	}

	/**
	 * QTY interface
	 */
	@Override
    public BL lessOrEqual(final ORD that) {
		if (that == null) {
			throw new NullPointerException("argument cannot be null");
		}
		if (that instanceof INTpinf) {
			return BLimpl.TRUE;
		} else if (that instanceof INTninf) {
			return BLimpl.TRUE;
		} else if (that instanceof INTlongAdapter) {
			final INTlongAdapter xthat = (INTlongAdapter) that;
			return lessOrEqual(xthat.doubleValue());
		} else if (that.isNull().isTrue()) {
			return BLimpl.NI;
		} else {
			throw new IllegalArgumentException(that.getClass().getName() + ": " + that.toString());
		}
	}

	public BL lessOrEqual(final int that) {
		return BLimpl.valueOf(this._value <= that);
	}

	public BL lessOrEqual(final long that) {
		return BLimpl.valueOf(this._value <= that);
	}

	public BL lessOrEqual(final float that) {
		return BLimpl.valueOf(this._value <= that);
	}

	public BL lessOrEqual(final double that) {
		return BLimpl.valueOf(this._value <= that);
	}

	public BL compares(final long that) {
		return BLimpl.TRUE;
	}

	public BL isZero() {
		return BLimpl.valueOf(this._value == 0);
	}

	public BL nonZero() {
		return BLimpl.valueOf(this._value != 0);
	}

	public BL isNegative() {
		return BLimpl.valueOf(this._value < 0);
	}

	public BL nonNegative() {
		return BLimpl.valueOf(this._value >= 0);
	}

	public INT successor() {
		return INTlongAdapter.valueOf(this._value + 1);
	}

	public INT predecessor() {
		return INTlongAdapter.valueOf(this._value - 1);
	}

	public QTY plus(final QTY.diff that) {
		if (that instanceof INTlongAdapter)
			return valueOf(this._value + ((INTlongAdapter) that)._value);
		else
			throw new UnsupportedOperationException();
	}

	public INT plus(final INT that) {
		return that.plus(this._value);
	}

	public INT plus(final int that) {
		return INTlongAdapter.valueOf(this._value + that);
	}

	public INT plus(final long that) {
		return INTlongAdapter.valueOf(this._value + that);
	}

	public QTY plus(final float that) {
		return valueOf(this._value + that);
	}

	public QTY plus(final double that) {
		return valueOf(this._value + that);
	}

	public INT minus(final INT that) {
		if (that instanceof INTlongAdapter) {
			return valueOf(this._value - ((INTlongAdapter) that)._value);
		} else {
			return that.minusReverse(this._value);
		}
	}

	public INT minusReverse(final int that) {
		return valueOf(that - this._value);
	}

	public INT minusReverse(final long that) {
		return valueOf(that - this._value);
	}

	public QTY minusReverse(final float that) {
		return valueOf(that - this._value);
	}

	public QTY minusReverse(final double that) {
		return valueOf(that - this._value);
	}

	public QTY minus(final QTY.diff that) {
		if (that instanceof INT)
			return minus((INT) that);
		else
			throw new UnsupportedOperationException();
	}

	public QTY.diff minus(final QTY that) {
		if (that instanceof INT)
			return minus((INT) that);
		else 
			throw new UnsupportedOperationException();
	}

	public INT negated() {
		return INTlongAdapter.valueOf(-this._value);
	}

	public BL isOne() {
		return BLimpl.valueOf(this._value == 1);
	}

	public INT times(final REAL that) {
		return valueOf(that.times(ValueFactory.getInstance().REALvalueOf(this._value)).longValue());
	}

	public INT times(final INT that) {
		return that.times(this._value);
	}

	public INT times(final int that) {
		return INTlongAdapter.valueOf(that * this._value);
	}

	public INT times(final long that) {
		return INTlongAdapter.valueOf(that * this._value);
	}

	public INT dividedBy(final INT that) {
		return that.dividedByReverse(this._value);
	}

	public INT dividedByReverse(final int that) {
		return INTlongAdapter.valueOf(that / this._value);
	}

	public INT dividedByReverse(final long that) {
		return INTlongAdapter.valueOf(that / this._value);
	}

	@Override
    public String toString() {
		return Long.toString(this._value);
	}

	// FIXME: overflow!
	public int intValue() {
		return (int) this._value;
	}

	public long longValue() {
		return this._value;
	}

	public float floatValue() {
		return this._value;
	}

	public double doubleValue() {
		return this._value;
	}

	public REAL toREAL() {
		return ValueFactory.getInstance().REALvalueOf(this._value);
	}

	public diff epsilon() {
		return ONE;
	}

	@Override
    public int hashCode() {
		return (int) (_value ^ (_value >>> 32));
	}
}

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
import org.hl7.types.MO;
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;

/**
 * Minimal implementation of MO as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class MOimpl extends ORDimpl implements MO {
	private final REAL _value;
	private CS _currency;
	private MO _canonical;

	private MOimpl(final REAL value, final CS currency) {
		// NOTE: why does every instance of MOimpl has to be NullFlavorImpl.OTH?
		// Currently, commented it out and replace it with super(null);
		// for complex handling of null flavor, consider overriding the isNull() methods from ANYimpl class.
		// super(NullFlavorImpl.OTH);
		super(null);
		this._value = value;
		// Do we allow currencys to be null???
		if (currency == null) {
			this._currency = CSnull.NI;
		} else {
			this._currency = currency;
		}
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static MO valueOf(final REAL value, final CS currency) {
		if (value == null) {
			return MOnull.NI;
		} else {
			return new MOimpl(value, currency);
		}
	}

	@Override
    public BL equal(final ANY x) {
		MOimpl that = null;
		if (x instanceof MOimpl) {
			that = (MOimpl) x;
			return (this._value.equal(that.value().doubleValue()).and(this._currency.equal(that.currency())));
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
    public int hashCode() {
		int result;
		result = (_value != null ? _value.hashCode() : 0);
		result = 29 * result + (_currency != null ? _currency.hashCode() : 0);
		result = 29 * result + (_canonical != null ? _canonical.hashCode() : 0);
		return result;
	}

	public BL equal(final int x) {
		return BLimpl.valueOf(this._value.intValue() == x);
	}

	public BL equal(final long x) {
		return BLimpl.valueOf(this._value.longValue() == x);
	}

	public BL equal(final float x) {
		return BLimpl.valueOf(this._value.floatValue() == x);
	}

	public BL equal(final double x) {
		return BLimpl.valueOf(this._value.doubleValue() == x);
	}

	@Override
    public BL lessOrEqual(final ORD x) {
		MOimpl that = null;
		if (x instanceof MOimpl) {
			that = (MOimpl) x;
			return _value.lessOrEqual(that.value());
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
    public BL compares(final ORD x) {
		return _value.compares(x);
	}

	public QTY plus(final QTY.diff x) {
		return _value.plus(x);
	}

	public MO plus(final MO x) {
		MOimpl that = null;
		if (x instanceof MOimpl) {
			that = (MOimpl) x;
		} else {
			throw new IllegalArgumentException();
		}
		if (_currency.equal(x.currency()).isFalse()) {
			throw new IllegalArgumentException();
		}
		return MOimpl.valueOf(_value.plus(that.value()), _currency);
	}

	public QTY.diff minus(final QTY x) {
		MOimpl xx = null;
		if (x instanceof MOimpl) {
			xx = (MOimpl) x;
			return minus(xx);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public QTY minus(final QTY.diff x) {
		return minus((QTY) x);
	}

	public MO minus(final MO x) {
		return MOimpl.valueOf(_value.minus(x.value()), _currency);
	}

	public BL isZero() {
		return _value.isZero();
	}

	public BL nonZero() {
		return isZero().not();
	}

	public REAL value() {
		return _value;
	}

	public CS currency() {
		return _currency;
	}

	public MO negated() {
		return MOimpl.valueOf(_value.negated(), _currency);
	}

	public MO times(final REAL that) {
		return MOimpl.valueOf(_value.times(that), _currency);
	}

	public MO times(final MO that) {
		return times(that.value());
	}

	public MO power(final INT that) {
		return MOimpl.valueOf(_value.powerReverse(that.intValue(), INTnull.PINF), _currency);
	}

	public MO epsilon() {
		return valueOf(REALdoubleAdapter.ONE, this._currency);
	}
}

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
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.RTO;

public class RTOimpl extends ANYimpl implements RTO {
	private final QTY.diff _numerator;
	private final QTY.diff _denominator;

	private RTOimpl(final QTY.diff numerator, final QTY.diff denominator) {
		super(null);
		_numerator = numerator;
		_denominator = denominator;
	}

	public static RTOimpl valueOf(final QTY.diff numerator, final QTY.diff denominator) {
		return new RTOimpl(numerator, denominator);
	}

	public QTY.diff numerator() {
		return _numerator;
	}

	public QTY.diff denominator() {
		return _denominator;
	}

	@Override
    public BL equal(final ANY that) {
		if (this == that) {
			return BLimpl.TRUE;
		} else if (that instanceof RTO) {
			return equalr((RTO) that);
		} else {
			return BLimpl.FALSE;
		}
	}

	private BL equalr(final RTO x) {
		if (x.numerator().equals(_numerator) && x.denominator().equals(_denominator)) {
			return BLimpl.TRUE;
		} else {
			return BLimpl.FALSE;
		}
	}

	public BL isZero() {
		return numerator().isZero();
	}

	public BL nonZero() {
		return isZero().not();
	}

	public QTY.diff negated() {
		return valueOf(numerator().negated(), denominator());
	}

	public QTY.diff times(final REAL x) {
		return valueOf(numerator().times(x), denominator());
	}

	public QTY plus(final QTY.diff that) {
		throw new UnsupportedOperationException();
	}

	public QTY minus(final QTY.diff that) {
		throw new UnsupportedOperationException();
	}

	public QTY.diff minus(final QTY that) {
		throw new UnsupportedOperationException();
	}

	public BL lessOrEqual(final ORD that) {
		throw new UnsupportedOperationException();
	}

	public BL lessThan(final ORD that) {
		throw new UnsupportedOperationException();
	}

	public BL greaterThan(final ORD that) {
		throw new UnsupportedOperationException();
	}

	public BL greaterOrEqual(final ORD that) {
		throw new UnsupportedOperationException();
	}

	public BL compares(final ORD that) {
		throw new UnsupportedOperationException();
	}

	public RTO epsilon() {
		return RTOimpl.valueOf(this._numerator.epsilon(), this._denominator.epsilon());
	}

	@Override
    public int hashCode() {
		int result;
		result = (_numerator != null ? _numerator.hashCode() : 0);
		result = 29 * result + (_denominator != null ? _denominator.hashCode() : 0);
		return result;
	}

	public int compareTo(final ORD that) {
		return 0;
	}
}

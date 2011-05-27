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
import org.hl7.types.Criterion;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.PQ;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.SET;
import org.hl7.types.TS;

/*
 * Result of periodic hull operation between two QSETs
 */
public class QSETPeriodicHullImpl<T extends QTY> extends QSETTermBase<T> implements QSET<T> {
	/*
	 * The hull is considered the space (inclusive) of occurence intervals of _thisset and _thatset. Note: this is
	 * different from the intervals of _thatset and _thisset. As of now, we assume that the two sets are interleaving
	 */
	QSET<T> _thisset; // occurs first
	QSET<T> _thatset; // occurs second

	@Override
    public String toString() {
		return _thisset.toString() + " .. " + _thatset.toString();
	}

	public static QSETPeriodicHullImpl valueOf(final QSET thisset, final QSET thatset) {
		return new QSETPeriodicHullImpl(thisset, thatset);
	}

	private QSETPeriodicHullImpl(final QSET<T> thisset, final QSET<T> thatset) {
		_thisset = thisset;
		_thatset = thatset;
	}

	public QSET<T> getThisSet() {
		return _thisset;
	}

	public QSET<T> getThatSet() {
		return _thatset;
	}

	public BL contains(final T element) {
		return this.nextTo(element).low().lessOrEqual(element).and(element.lessOrEqual(this.nextTo(element).high()));
	}

	public BL contains(final SET<T> subset) {
		if (subset instanceof IVL) {
			final IVL<T> ivl = (IVL) subset;
			return this.contains(ivl.low()).and(this.contains(ivl.high())).and(
					this.nextTo(ivl.low()).equal(this.nextTo(ivl.high())));
		} else {
            throw new UnsupportedOperationException();
        }
	}

	public IVL<T> hull() {
		throw new UnsupportedOperationException();
	}

	public IVL<T> nextTo(final T element) {
		IVL<T> thisIVL, thatIVL;
		thisIVL = _thisset.nextTo(element);
		thatIVL = _thatset.nextTo(element);
		if (thisIVL.low().lessOrEqual(element).isTrue()) {
			return IVLimpl.valueOf(thisIVL.lowClosed(), thisIVL.low(), thatIVL.high(), thatIVL.highClosed());
		} else if (thatIVL.high().lessOrEqual(thisIVL.low()).isTrue()) {
			final PQ diff = (PQ) _thisset.nextAfter(thisIVL.low()).low().minus(thisIVL.low());
			return IVLimpl.valueOf(thisIVL.lowClosed(), (T) ((TS) thisIVL.low()).minus(diff), thatIVL.high(), thatIVL
					.highClosed());
		} else {
            return IVLimpl.valueOf(thisIVL.lowClosed(), thisIVL.low(), thatIVL.high(), thatIVL.highClosed());
        }
	}

	public IVL<T> nextAfter(final T element) {
		final IVL<T> ans = this.nextTo(element);
		if (element.lessOrEqual(ans.low()).isTrue()) {
            return ans;
        } else { // we have to get the next ans
			final IVL<T> thisIVL = _thisset.nextAfter(ans.high());
			final IVL<T> thatIVL = _thatset.nextAfter(ans.high());
			return IVLimpl.valueOf(thisIVL.lowClosed(), thisIVL.low(), thatIVL.high(), thatIVL.highClosed());
		}
	}

	public BL interleaves(final QSET<T> otherset) {
		throw new UnsupportedOperationException();
	}

	@Override
    public BL equal(final ANY that) {
		throw new UnsupportedOperationException();
	}

	public INT cardinality() {
		throw new UnsupportedOperationException();
	}

	public BL isEmpty() {
		return _thisset.isEmpty().and(_thatset.isEmpty());
	}

	public T any() {
		throw new UnsupportedOperationException();
	}

	public SET<T> select(final Criterion c) {
		throw new UnsupportedOperationException();
	}
}

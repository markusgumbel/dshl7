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
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.SET;

/**
 * Result of except operation with another set. Implemented as pair of minuend and subtrahend.
 *  - a pair of minuend and subtrahend - excluded could often be be a union (for subsequent differences) - more
 * optimization: remove the overlapping parts of the first interval ...
 * 
 */
public class QSETDifferenceImpl<T extends QTY> extends QSETTermBase<T> {
	private final QSET<T> _minuend;
	private final QSET<T> _subtrahend;

	@Override
    public String toString() {
		return _minuend.toString() + " \\ " + _subtrahend.toString();
	}

	public static <T extends QTY> QSETDifferenceImpl<T> valueOf(final QSET<T> minuend, final QSET<T> subtrahend) {
		return new QSETDifferenceImpl<T>(minuend, subtrahend);
	}

	private QSETDifferenceImpl(final QSET<T> minuend, final QSET<T> subtrahend) {
		_minuend = minuend;
		_subtrahend = subtrahend;
	}

	public QSET<T> getMinuend() {
		return _minuend;
	}

	public QSET<T> getSubtrahend() {
		return _subtrahend;
	}

	public BL contains(final T element) {
		return _minuend.contains(element).and(_subtrahend.contains(element).not());
	}

	public BL contains(final SET<T> subset) {
		return _minuend.contains(subset).and(_subtrahend.intersection(subset).isEmpty());
	}

	public IVL<T> hull() {
		final IVL<T> hull = _minuend.hull();
		BL lowClosed = hull.lowClosed();
		T low = hull.low();
		T high = hull.high();
		BL highClosed = hull.highClosed();
		final IVL<T> lowerEnd = _subtrahend.nextTo(low);
		if (lowerEnd.contains(low).isTrue()) {
			low = lowerEnd.high();
			lowClosed = lowerEnd.highClosed().not();
		}
		final IVL<T> higherEnd = _subtrahend.nextTo(high);
		if (higherEnd.contains(high).isTrue()) {
			high = higherEnd.low();
			highClosed = higherEnd.lowClosed().not();
		}
		if (low.lessOrEqual(high).isTrue()) {
			return IVLimpl.valueOf(lowClosed, low, high, highClosed);
		} else {
			return (IVL<T>) EmptySet.OVALUE;
		}
	}

	public IVL<T> nextTo(T element) {
		if (_subtrahend.contains(_minuend).isTrue()) {
			return IVLnull.NA;
		} else {
			while (true) {
				final IVL<T> next = _minuend.nextTo(element);
				if (next.isNull().or(next.isEmpty()).isTrue()) {
                    return next;
                }
				BL lowClosed = next.lowClosed();
				T low = next.low();
				T high = next.high();
				BL highClosed = next.highClosed();
				final IVL<T> lowerEnd = _subtrahend.nextTo(low);
				if (lowerEnd.contains(low).isTrue()) {
					low = lowerEnd.high();
					lowClosed = lowerEnd.highClosed().not();
				}
				final IVL<T> higherEnd = _subtrahend.nextTo(high);
				if (higherEnd.contains(high).isTrue()) {
					high = higherEnd.low();
					highClosed = higherEnd.lowClosed().not();
				}
				if (low.lessOrEqual(high).isTrue()) {
					return IVLimpl.valueOf(lowClosed, low, high, highClosed);
				} else {
					element = low;
				}
			}
		}
	}

	public IVL<T> nextAfter(T element) {
		if (_subtrahend.contains(_minuend).isTrue()) {
            return IVLnull.NA;
        } else {
			while (true) {
				final IVL<T> next = _minuend.nextAfter(element);
				if (next.isNull().or(next.isEmpty()).isTrue()) {
                    return next;
                }
				BL lowClosed = next.lowClosed();
				T low = next.low();
				T high = next.high();
				BL highClosed = next.highClosed();
				final IVL<T> lowerEnd = _subtrahend.nextTo(low);
				if (lowerEnd.contains(low).isTrue()) {
					low = lowerEnd.high();
					lowClosed = lowerEnd.highClosed().not();
				}
				final IVL<T> higherEnd = _subtrahend.nextTo(high);
				if (higherEnd.contains(high).isTrue()) {
					high = higherEnd.low();
					highClosed = higherEnd.lowClosed().not();
				}
				if (low.lessOrEqual(high).isTrue()) {
                    return IVLimpl.valueOf(lowClosed, low, high, highClosed);
                } else {
					element = low;
				}
			}
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
		return this.nextTo((T) TSjuDateAdapter.valueOf("197001010000")).isNull();
		// throw new UnsupportedOperationException();
	}

	public T any() {
		throw new UnsupportedOperationException();
	}

	public SET<T> select(final Criterion c) {
		throw new UnsupportedOperationException();
	}
}

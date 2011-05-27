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

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.Criterion;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.SET;

/**
 * A singularity is a set with a single element. This is created when a simple value is cast into a set.
 */
public class QSETSingularityImpl<T extends QTY> extends QSETTermBase<T> implements QSET<T>, IVL<T> {
	T _element;

	public static QSETSingularityImpl valueOf(final QTY element) {
		return new QSETSingularityImpl(element);
	}

	private QSETSingularityImpl(final T element) {
		_element = element;
	}

	@Override
    public String toString() {
		final StringBuffer sb = new StringBuffer("(");
		sb.append("[" + _element.toString() + "]");
		sb.append(")");
		return sb.toString();
	}

	public T getElement() {
		return _element;
	}

	public BL contains(final T element) {
		return _element.equal(element);
	}

	public BL contains(final SET<T> subset) {
		if (subset.cardinality().isOne().isTrue()) {
            return subset.contains(_element);
        } else {
            return BLimpl.FALSE;
        }
	}

	public IVL<T> hull() {
		return this;
	}

	public IVL<T> hull(final IVL<T> that) {
		if (_element.equal(that.low()).and(_element.equal(that.high())).isTrue()) {
            return this;
        } else if (_element.lessOrEqual(that.low()).isTrue()) {
            return IVLimpl.valueOf(BLimpl.TRUE, _element, that.high(), that.highClosed());
        } else if (that.high().lessOrEqual(_element).isTrue()) {
            return IVLimpl.valueOf(that.lowClosed(), that.low(), _element, BLimpl.TRUE);
        } else {
            throw new AssertionError();
        }
	}

	public T low() {
		return _element;
	}

	public T high() {
		return _element;
	}

	public T center() {
		return _element;
	}

	public/* T.diff */QTY.diff width() {
		// FIXME: need to return a T.zeroDiffValue()
		throw new UnsupportedOperationException();
	}

	public BL lowClosed() {
		return BLimpl.TRUE;
	}

	public BL highClosed() {
		return BLimpl.TRUE;
	}

	public BL overlaps(final IVL<T> x) {
		return x.contains(_element);
	}

	public IVL<T> intersection(final IVL<T> otherset) {
		if (otherset.contains(_element).isTrue()) {
            return this;
        } else {
            return IVLnull.NA;
        }
	}

	public IVL<T> nextTo(final T element) {
		if (element.lessOrEqual(_element).isTrue()) {
            return this;
        } else {
            return IVLnull.NA;
        }
	}

	public IVL<T> nextAfter(final T element) {
		if (element.lessThan(_element).isTrue()) {
            return this;
        } else {
            return IVLnull.NA;
        }
	}

	@Override
    public QSET<T> periodicHull(final QSET<T> otherset) {
		return QSETnull.NA;
	}

	public BL interleaves(final QSET<T> otherset) {
		return BLimpl.FALSE;
	}

	@Override
    public BL equal(final ANY that) {
		if (that == this) {
            return BLimpl.TRUE;
        } else if (that instanceof QSETSingularityImpl) {
            return _element.equal(((QSETSingularityImpl<T>) that)._element);
        } else if (that instanceof IVL) {
			final IVL<T> thatIVL = (IVL<T>) that;
			return _element.equal(thatIVL.low()).and(_element.equal(thatIVL.high()));
		} else {
            throw new UnsupportedOperationException(that.getClass().getName());
        }
	}

	@Override
    public Iterator<T> iterator() {
		return new Iterator<T>() {
			private boolean used = false;

			public boolean hasNext() {
				if (used) {
                    return false;
                } else {
					used = false;
					return true;
				}
			}

			public T next() {
				if (used) {
                    throw new NoSuchElementException();
                } else {
                    return _element;
                }
			}

			public void remove() {
				throw new UnsupportedOperationException(); // sic!
			}
		};
	}

	public INT cardinality() {
		return INTlongAdapter.ONE;
	}

	public BL isEmpty() {
		return BLimpl.FALSE;
	}

	@Override
    public BL nonEmpty() {
		return BLimpl.TRUE;
	}

	public T any() {
		return _element;
	}

	public SET<T> select(final Criterion c) {
		throw new UnsupportedOperationException();
	}
}

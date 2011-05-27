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

import java.util.ArrayList;
import java.util.List;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.Criterion;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.SET;

/**
 * Result of unions with another set. Implemented as a Set of arguments combined as union.
 * 
 * TODO: optimize contains operations by sorting the 'biggest' components first
 */
public class QSETUnionImpl<T extends QTY> extends QSETTermBase<T> implements QSET<T> {
	private final List<QSET<T>> _args;

	@Override
    public String toString() {
		final StringBuffer sb = new StringBuffer("(");
		sb.append(_args.get(0).toString());
		for (int i = 1; i < _args.size(); i++) {
			sb.append("; ");
			sb.append(_args.get(i).toString());
		}
		sb.append(")");
		return sb.toString();
	}

	public static QSETUnionImpl valueOf(final QSET one, final QSET two) {
		return new QSETUnionImpl(one, two);
	}

	private QSETUnionImpl<T> cloneAndAppend(final QSET<T> appendix) {
		return new QSETUnionImpl(this, appendix);
	}

	private QSETUnionImpl(final QSET<T> one, final QSET<T> two) {
		_args = new ArrayList<QSET<T>>(2);
		_args.add(one);
		_args.add(two);
	}

	private QSETUnionImpl(final QSETUnionImpl<T> that, final QSET<T> appendix) {
		_args = that._args;
		// _args.ensureCapacity(_args.size()+1);
		_args.add(appendix);
	}

	public BL contains(final T element) {
		// exists(QSET<T> s) where this.contains(s) {
		// s.contains(element);
		// }
		for (int i = 0; i < _args.size(); i++) {
            if (_args.get(i).contains(element).isTrue()) {
                return BLimpl.TRUE;
            }
        }
		return BLimpl.FALSE;
		// TODO: what about null values from contains()?
	}

	public BL contains(final SET<T> subset) {
		// exists(QSET<T> s) where this.contains(s) {
		// s.contains(element);
		// }
		for (int i = 0; i < _args.size(); i++) {
            if (_args.get(i).contains(subset).isTrue()) {
                return BLimpl.TRUE;
            }
        }
		return BLimpl.FALSE;
		// TODO: must make sure that overlapping and directly
		// connecting sets are fusioned together.
	}

	@Override
    public QSET<T> union(final QSET<T> otherset) {
		return cloneAndAppend(otherset);
	}

	public IVL<T> hull() {
		/*
		 * Note that this may very well throw an exception if one of the args of this is a PIVL-- one can't take the hull of
		 * a PIVL
		 */
		IVL<T> hull = _args.get(0).hull();
		for (int i = 1; i < _args.size(); i++) {
			hull = hull.hull(_args.get(i).hull());
		}
		return hull;
	}

	public IVL<T> nextTo(final T element) {
		IVL<T> result = _args.get(0).nextTo(element);
		for (int i = 1; i < _args.size(); i++) {
			final IVL<T> next = _args.get(i).nextTo(element);
			if (result.isEmpty().isTrue()) {
                result = next;
            } else if (result.overlaps(next).isTrue()) {
                result = result.hull(next);
            } else if (result.low().lessOrEqual(next.low()).isFalse()) {
                result = next;
            }
		}
		return result;
	}

	public IVL<T> nextAfter(final T element) {
		IVL<T> result = _args.get(0).nextAfter(element);
		for (int i = 1; i < _args.size(); i++) {
			final IVL<T> next = _args.get(i).nextAfter(element);
			if (result.isEmpty().isTrue()) {
                result = next;
            } else if (result.overlaps(next).isTrue()) {
                result = result.hull(next);
            } else if (result.low().lessOrEqual(next.low()).isFalse()) {
                result = next;
            }
		}
		return result;
	}

	/*
	 * // GS/MC: this is commented out because it is a particular challenging // operation which has very little practical
	 * value besides being used // in an assertion about the definition of the periodic hull.
	 * 
	 * public BL interleaves(QSET<T> otherset) {
	 *  // find argument with the shortest period. If otherset // interleaves with that argument, then otherset
	 * interleaves // this // int mostFrequent=0; for (int i=0;i<_args.size();i++) { if (!(_args.get(i) instanceof PIVL))
	 * return BLimpl.FALSE; else { if
	 * (((PIVL)_args.get(i)).period().lessOrEqual(((PIVL)_args.get(mostFrequent)).period()).isTrue()) mostFrequent=i; } }
	 * return _args.get(mostFrequent).interleaves(otherset); }
	 */
	public QSET<T> getArgument(final int i) {
		return _args.get(i);
	}

	public int size() {
		return _args.size();
	}

	@Override
    public BL equal(final ANY that) {
		if (that instanceof QSETUnionImpl) {
			final QSETUnionImpl<T> thatUnion = (QSETUnionImpl<T>) that;
			if (_args.size() != thatUnion._args.size()) {
                return BLimpl.FALSE;
            }
			BL result = BLimpl.TRUE;
			for (int i = 0; i < _args.size(); i++) {
				result = result.and(_args.get(i).equal(thatUnion._args.get(i)));
				if (result.isFalse()) {
                    break;
                }
			}
			return result;
		} else {
            throw new UnsupportedOperationException("Need a QSET, got a " + that.getClass());
		// FIXME: there can be equivalence of two terms that look different!!
        }
	}

	public INT cardinality() {
		throw new UnsupportedOperationException();
	}

	public BL isEmpty() {
		return REALdoubleAdapter.ZERO.equal(_args.size());
	}

	public T any() {
		throw new UnsupportedOperationException();
	}

	public SET<T> select(final Criterion c) {
		throw new UnsupportedOperationException();
	}
}

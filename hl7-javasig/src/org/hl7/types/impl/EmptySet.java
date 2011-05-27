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
 * Singleton implementation of the empty set. It's implemented as omnipotent (i.e., implementing all known SET
 * interfaces, because all operations will likely have to return itself.)
 */
public class EmptySet extends ANYimpl implements SET, QSET, IVL {
	// the empty set
	public static final EmptySet VALUE = new EmptySet();
	public static final Object OVALUE = VALUE;

	protected EmptySet() {
		super(null);
	}

	@Override
    public BL equal(final ANY that) {
		if (that instanceof SET && ((SET) that).isEmpty().isTrue()) {
            return BLimpl.TRUE;
        } else {
            return BLimpl.FALSE;
        }
	}

	public BL contains(final ANY element) {
		// XXX: Dr. Cantor, does the empty set contain the empty set?
		return BLimpl.FALSE;
	}

	public BL contains(final SET subset) {
		// XXX: Dr. Cantor, does the empty set contain the empty set?
		return BLimpl.FALSE;
	}

	public INT cardinality() {
		return INTlongAdapter.ZERO;
	}

	public SET union(final SET subset) {
		return subset;
	}

	public QSET union(final QSET subset) {
		return subset;
	}

	public SET intersection(final SET otherset) {
		return this;
	}

	public QSET intersection(final QSET otherset) {
		return this;
	}

	public IVL intersection(final IVL otherset) {
		return this;
	}

	public SET except(final ANY element) {
		return this;
	}

	public QSET except(final QTY element) {
		return this;
	}

	public SET except(final SET element) {
		return this;
	}

	public QSET except(final QSET element) {
		return this;
	}

	public BL isEmpty() {
		return BLimpl.TRUE;
	}

	public BL nonEmpty() {
		return BLimpl.FALSE;
	}

	public IVL hull() {
		return this;
	}

	public QTY low() {
		// FIXME: need to return NULL but which type???
		throw new UnsupportedOperationException();
	}

	public QTY high() {
		// FIXME: need to return NULL but which type???
		throw new UnsupportedOperationException();
	}

	public QTY center() {
		// FIXME: need to return NULL but which type???
		throw new UnsupportedOperationException();
	}

	public QTY.diff width() {
		// FIXME: would have to return QTY.diff x where x.isZero, but how if
		// we don't know the type of QTY.diff?
		throw new UnsupportedOperationException();
	}

	public BL lowClosed() {
		return BLimpl.FALSE;
	}

	public BL highClosed() {
		return BLimpl.FALSE;
	}

	public BL overlaps(final IVL x) {
		return BLimpl.FALSE;
	}

	public IVL hull(final IVL that) {
		return that;
	}

	public IVL hull(final QSET that) {
		return that.hull();
	}

	public IVL nextTo(final QTY t) {
		return this;
	}

	public IVL nextAfter(final QTY t) {
		return this;
	}

	public QSET periodicHull(final QSET otherset) {
		return this;
	}

	public BL interleaves(final QSET otherset) {
		return BLimpl.FALSE;
	}

	public Iterator<ANY> iterator() {
		return new NullIterator<ANY>();
	}

	/**
	 * @deprecated instantiate the {@link EmptySet.NullIterator} class instead.
	 */
	@Deprecated
	public static final Iterator<? extends ANY> ITERATOR = new Iterator<ANY>() {
		public boolean hasNext() {
			return false;
		}

		public ANY next() {
			throw new NoSuchElementException();
		}

		public void remove() {
			throw new NoSuchElementException();
		}
	};

	public static class NullIterator<T extends ANY> implements Iterator<T> {
        public boolean hasNext() {
            return false;
        }
        
        public T next() {
            throw new NoSuchElementException();
        }
        
        public void remove() {
            throw new NoSuchElementException();
        }
    }
	
	public ANY any() {
		throw new UnsupportedOperationException();
	}

	public SET select(final Criterion c) {
		throw new UnsupportedOperationException();
	}
}

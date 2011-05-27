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

import org.hl7.types.BL;
import org.hl7.types.IVL;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.SET;

/** Common basis for QSET term components. */
public abstract class QSETTermBase<T extends QTY> extends ANYimpl implements QSET<T> {
	protected QSETTermBase() {
		super(null);
	}

	public QSET<T> union(final SET<T> otherset) {
		if (otherset instanceof QSET) {
            return union((QSET<T>) otherset);
        } else {
            throw new IllegalArgumentException("need a QSET here");
        }
	}

	public QSET<T> intersection(final SET<T> otherset) {
		if (otherset instanceof QSET) {
            return intersection((QSET<T>) otherset);
        } else {
            throw new IllegalArgumentException("need a QSET here");
        }
	}

	public QSET<T> except(final SET<T> otherset) {
		if (otherset instanceof QSET) {
            return except((QSET<T>) otherset);
        } else {
            throw new IllegalArgumentException("need a QSET here");
        }
	}

	public QSET<T> union(final QSET<T> otherset) {
		return QSETUnionImpl.valueOf(this, otherset);
	}

	public QSET<T> intersection(final QSET<T> otherset) {
		return QSETIntersectionImpl.valueOf(this, otherset);
	}

	public QSET<T> except(final T element) {
		return QSETDifferenceImpl.valueOf(this, QSETSingularityImpl.valueOf(element));
	}

	public QSET<T> except(final QSET<T> otherset) {
		return QSETDifferenceImpl.valueOf(this, otherset);
	}

	public QSET<T> periodicHull(final QSET<T> otherset) {
		return QSETPeriodicHullImpl.valueOf(this, otherset);
	}

	public QSET<T> periodicHull(final SET<T> otherset) {
		if (otherset instanceof QSET) {
            return QSETPeriodicHullImpl.valueOf(this, (QSET<T>) otherset);
        } else {
            throw new IllegalArgumentException("need a QSET here");
        }
	}

	public BL nonEmpty() {
		return isEmpty().not();
	}

	public Iterator iterator() {
		return new Iterator<IVL<T>>() {
			private IVL<T> _next = nextTo(hull().low());

			public boolean hasNext() {
				return _next != null;
			}

			public IVL<T> next() {
				if (_next == null) {
                    throw new NoSuchElementException();
                } else {
					final IVL<T> _this = _next;
					_next = nextAfter(_this.high());
					return _this;
				}
			}

			public void remove() {
				throw new UnsupportedOperationException(); // sic!
			}
		};
	}
}

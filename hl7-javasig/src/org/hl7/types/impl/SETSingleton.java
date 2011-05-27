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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.Criterion;
import org.hl7.types.INT;
import org.hl7.types.QTY;
import org.hl7.types.SET;

public class SETSingleton<T extends ANY> extends ANYimpl implements SET<T> {
	T _element;

	protected SETSingleton() {
		super(null);
	}

	protected SETSingleton(final T data) {
		super(null);
		if (data == null) {
            throw new IllegalArgumentException();
        }
		if (data.isNull().isFalse() && data instanceof QTY) {
            new IllegalArgumentException("WARNING: shouldn't stick a quantity like a " + data.getClass() + ": " + data
					+ " in a SETSingleton").printStackTrace();
        }
		this._element = data;
	}

	public static SET valueOf(final ANY data) {
		if (data == null) {
            return SETnull.NI;
        } else {
            return new SETSingleton(data);
        }
	}

	/** SET interface */
	/**
	 * @param element
	 *          the elements to be compared against this SET.
	 * @return BL true if this SET contains the parameter element.
	 */
	public BL contains(final T element) {
		return this.equal(_element);
	}

	/**
	 * @return BL true if this SET contains no elements.
	 */
	public BL isEmpty() {
		return _element.isNull();
	}

	/**
	 * @return BL true if this SET contains at least one element.
	 */
	public BL nonEmpty() {
		return _element.nonNull();
	}

	/**
	 * @param that
	 *          should be a SET that can be compared to this SET for equality.
	 * @return BL true if the parameter is the same as this SET or contains the same elements and this SET.
	 */
	@Override
    public BL equal(final ANY that) {
		return _element.equal(_element);
	}

	/**
	 * @param subset
	 *          SET that defines the elements that will compared to this SET.
	 * @return BL true if the elements in the parameter SET are contained in this SET.
	 */
	public BL contains(final SET<T> subset) {
		if (!(subset instanceof SETSingleton)) {
            return BLimpl.FALSE;
        } else {
            return subset.equal(_element);
        }
	}

	/**
	 * @return the number of elements in this SET.
	 */
	public INT cardinality() {
		if (_element.isNull().isTrue()) {
            return INTlongAdapter.valueOf(0);
        } else {
            return INTlongAdapter.valueOf(1);
        }
	}

	/**
	 * @param otherset
	 *          SET that defines the elements that will be added to this SET.
	 * @return a new SET that contains the elements that are both in this SET and in the parameter SET.
	 */
	public SET<T> union(final SET<T> otherset) {
		return otherset.union(this);
	}

	/**
	 * @param element
	 *          SET defines which element of this SET that will not be in the returned SET.
	 * @return a new SET that does not contain the parameter element.
	 */
	public SET<T> except(final T element) {
		if (_element.equal(element).isTrue()) {
            return SETnull.NI;
        } else {
            return this;
        }
	}

	/**
	 * @param otherset
	 *          SET that defines which elements will not be in the returned SET.
	 * @return a new SET that contains the elements that are in this SET but not in the parameter SET.
	 */
	public SET<T> except(final SET<T> otherset) {
		if (otherset instanceof SETSingleton) {
			final SETSingleton that = (SETSingleton) otherset;
			if (this.equal(that).isTrue()) {
                return SETnull.NI;
            } else {
                return this;
            }
		} else if (otherset instanceof SETjuSetAdapter) {
			final SETjuSetAdapter that = (SETjuSetAdapter) otherset;
			if (that.contains(_element).isTrue()) {
                return SETnull.NI;
            } else {
                return this;
            }
		} else {
            throw new UnsupportedOperationException();
        }
	}

	/**
	 * @param otherset
	 *          SET that defines which elements the returned SET will retain.
	 * @return a new SET that contains the elements that are in both this SET and the parameter SET.
	 */
	public SET<T> intersection(final SET<T> otherset) {
		if (otherset instanceof SETjuSetAdapter) {
			final SETjuSetAdapter that = (SETjuSetAdapter) otherset;
			if (that.contains(_element).isTrue()) {
                return this;
            } else {
                return SETnull.NI;
            }
		} else if (otherset instanceof SETSingleton) {
			final SETSingleton that = (SETSingleton) otherset;
			if (that.contains(_element).isTrue()) {
                return this;
            } else {
                return SETnull.NI;
            }
		} else {
            throw new UnsupportedOperationException();
        }
	}

	public class SETSingletonIterator implements Iterator {
		int counter = 0;

		public boolean hasNext() {
			if (counter == 0) {
                return true;
            } else {
                return false;
            }
		}

		public T next() {
			if (hasNext()) {
				counter++;
				return _element;
			} else {
                throw new Error("No more elements");
            }
		}

		public void remove() {
		// not used
		}
	}

	public Iterator<ANY> iterator() {
		return new SETSingletonIterator();
	}

	public SET<T> select(final Criterion c) {
		final Set<T> returnSet = new HashSet<T>();
		if (c.test(_element)) {
			returnSet.add(_element);
			return SETjuSetAdapter.valueOf(returnSet);
		} else {
			return SETnull.NA;
		}
	}

	public T any() {
		if (_element.isNull().isFalse()) {
            return _element;
        } else {
            return (T) SETnull.NA;
        }
	}
}

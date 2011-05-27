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
import java.util.Set;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.Criterion;
import org.hl7.types.INT;
import org.hl7.types.NullFlavor;
import org.hl7.types.SET;

/**
 * Minimal implementation of SET for nulls. We don't want to instantiate new such objects every time we use them.
 */
public class SETnull<T extends ANY> extends ANYimpl implements SET<T> {
	public static final SETnull NI = new SETnull(NullFlavorImpl.NI);
	public static final SETnull NA = new SETnull(NullFlavorImpl.NA);
	public static final SETnull UNK = new SETnull(NullFlavorImpl.UNK);
	public static final SETnull NASK = new SETnull(NullFlavorImpl.NASK);
	public static final SETnull ASKU = new SETnull(NullFlavorImpl.ASKU);
	public static final SETnull NAV = new SETnull(NullFlavorImpl.NAV);
	public static final SETnull OTH = new SETnull(NullFlavorImpl.OTH);

	protected SETnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static SETnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(SETnull.class, nullFlavor);
	}

	// /** FIXME: is NA correct or should it be derived from this and that? */
	// public BL equal(ANY that) { return BLimpl.NA; }
	/**
	 * the equal will tell whether the given ANY is equal to this object.
	 * 
	 * @param that
	 * @return BLimpl.TRUE if that is equal to this object.
	 */
	@Override
    public BL equal(final ANY that) {
		/**
		 * Designed by S. Jiang: The contract of equal shall be carried no matter the semantics value of the class itself.
		 */
		if (that instanceof SETnull) {
			final SETnull thatObj = (SETnull) that;
			return thatObj.nullFlavor().equal(this.nullFlavor());
		} else {
			return BLimpl.FALSE;
		}
	}

	// equals() method has been overriden in ANYimpl class
	/**
	 * override hashCode() method.
	 * 
	 * @return hashCode
	 */
	@Override
    public int hashCode() {
		return this.nullFlavor().hashCode();
	}

	public BL contains(final T element) {
		return BLimpl.NA;
	}

	public BL contains(final SET<T> subset) {
		return BLimpl.NA;
	}

	public INT cardinality() {
		return INTnull.NA;
	}

	public SET<T> union(final SET<T> subset) {
		return SETnull.NA;
	}

	public SET<T> intersection(final SET<T> otherset) {
		return SETnull.NA;
	}

	public SET<T> except(final T element) {
		return SETnull.NA;
	}

	public SET<T> except(final SET<T> element) {
		return SETnull.NA;
	}

	public BL isEmpty() {
		return BLimpl.NA;
	}

	public BL nonEmpty() {
		return BLimpl.NA;
	}

	public Set<T> getUnderlyingSet() {
		return null;
	}

	public Iterator<T> iterator() {
		return new EmptySet.NullIterator<T>();
	}

	public SET<T> select(final Criterion c) {
		throw new UnsupportedOperationException();
	}

	public T any() {
		// CR: A NullPointerException makes more sense to me for these types of references.
		// We will need to be consistent though... *
		// throw new NullPointerException("can't return anything of a set that is null");
		return null; // FIXME!
	}
}

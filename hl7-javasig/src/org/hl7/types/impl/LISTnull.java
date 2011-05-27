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

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;

/**
 * Minimal implementation of LIST for nulls. We don't want to instantiate new such objects every time we use them.
 */
public class LISTnull<T extends ANY> extends ANYimpl implements LIST<T> {
	@SuppressWarnings("unchecked")
	public static final LISTnull NI = new LISTnull(NullFlavorImpl.NI);
	@SuppressWarnings("unchecked")
	public static final LISTnull NA = new LISTnull(NullFlavorImpl.NA);
	@SuppressWarnings("unchecked")
	public static final LISTnull UNK = new LISTnull(NullFlavorImpl.UNK);
	@SuppressWarnings("unchecked")
	public static final LISTnull NASK = new LISTnull(NullFlavorImpl.NASK);
	@SuppressWarnings("unchecked")
	public static final LISTnull ASKU = new LISTnull(NullFlavorImpl.ASKU);
	@SuppressWarnings("unchecked")
	public static final LISTnull NAV = new LISTnull(NullFlavorImpl.NAV);
	@SuppressWarnings("unchecked")
	public static final LISTnull OTH = new LISTnull(NullFlavorImpl.OTH);
	// the empty list
	@SuppressWarnings("unchecked")
	public static final LISTnull NIL = new LISTnull(null);

	protected LISTnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	@SuppressWarnings("unchecked")
	public static LISTnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(LISTnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	// public BL equal(ANY that) { return BLimpl.NA; }
	/**
	 * the equal will tell whether the given ANY is equal to this object.
	 * 
	 * @param that
	 * @return BLimpl.TRUE if that is equal to this object.
	 */
	@Override
    @SuppressWarnings("unchecked")
	public BL equal(final ANY that) {
		/**
		 * The contract of equal shall be carried no matter the semantics value of the class itself.
		 */
		if (that instanceof LISTnull) {
			final LISTnull thatObj = (LISTnull) that;
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

	// FIXME: returning null here is easy, but I better throw some
	// exception or return NullFlavorImpl.NA.
	public T head() {
		throw new UnsupportedOperationException();
	}

	// FIXME: should I return the empty list (NIL) here instead?
	public LIST<T> tail() {
		return NA;
	}

	public BL isEmpty() {
		return BLimpl.NA;
	}

	public BL nonEmpty() {
		return BLimpl.NA;
	}

	public INT length() {
		return INTnull.NA;
	}

	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}
}

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

import org.hl7.types.BL;
import org.hl7.types.IVL;
import org.hl7.types.NullFlavor;
import org.hl7.types.QTY;

/**
 * Minimal implementation of QSET for nulls. We don't want to instantiate new such objects every time we use them.
 */
public class IVLnull<T extends QTY> extends QSETnull<T> implements IVL<T> {
	@SuppressWarnings("unchecked")
	public static final IVLnull NI = new IVLnull(NullFlavorImpl.NI);
	@SuppressWarnings("unchecked")
	public static final IVLnull NA = new IVLnull(NullFlavorImpl.NA);
	@SuppressWarnings("unchecked")
	public static final IVLnull UNK = new IVLnull(NullFlavorImpl.UNK);
	@SuppressWarnings("unchecked")
	public static final IVLnull NASK = new IVLnull(NullFlavorImpl.NASK);
	@SuppressWarnings("unchecked")
	public static final IVLnull ASKU = new IVLnull(NullFlavorImpl.ASKU);
	@SuppressWarnings("unchecked")
	public static final IVLnull NAV = new IVLnull(NullFlavorImpl.NAV);
	@SuppressWarnings("unchecked")
	public static final IVLnull OTH = new IVLnull(NullFlavorImpl.OTH);

	protected IVLnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	@SuppressWarnings("unchecked")
	public static IVLnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(IVLnull.class, nullFlavor);
	}

	public T low() { // FIXME: should really return NI here, but how do I know which type null value I should throw?
		throw new UnsupportedOperationException();
	}

	public T high() {
		throw new UnsupportedOperationException();
	}

	public T center() {
		throw new UnsupportedOperationException();
	}

	public QTY.diff width() {
		throw new UnsupportedOperationException();
	}

	public BL lowClosed() {
		return BLimpl.NA;
	}

	public BL highClosed() {
		return BLimpl.NA;
	}

	public BL overlaps(final IVL<T> x) {
		return BLimpl.NA;
	}

	@Override
    public IVL<T> intersection(final IVL<T> otherset) {
		return IVLnull.NA;
	}

	public IVL<T> hull(final IVL<T> x) {
		return IVLnull.NA;
	}

	@Override
    public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}
}

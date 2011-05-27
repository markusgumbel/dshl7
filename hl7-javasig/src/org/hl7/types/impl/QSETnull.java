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
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.SET;

/**
 * Minimal implementation of QSET for nulls. We don't want to instantiate new such objects every time we use them.
 */
public class QSETnull<T extends QTY> extends SETnull<T> implements QSET<T> {
	public static final QSETnull NI = new QSETnull(NullFlavorImpl.NI);
	public static final QSETnull NA = new QSETnull(NullFlavorImpl.NA);
	public static final QSETnull UNK = new QSETnull(NullFlavorImpl.UNK);
	public static final QSETnull NASK = new QSETnull(NullFlavorImpl.NASK);
	public static final QSETnull ASKU = new QSETnull(NullFlavorImpl.ASKU);
	public static final QSETnull NAV = new QSETnull(NullFlavorImpl.NAV);
	public static final QSETnull OTH = new QSETnull(NullFlavorImpl.OTH);
	// the empty set
	public static final QSETnull NIL = new QSETnull(null);

	protected QSETnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static QSETnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(QSETnull.class, nullFlavor);
	}

	@Override
    public QSET<T> union(final SET<T> subset) {
		return QSETnull.NA;
	}

	public QSET<T> union(final QSET<T> subset) {
		return QSETnull.NA;
	}

	@Override
    public QSET<T> intersection(final SET<T> otherset) {
		return QSETnull.NA;
	}

	public QSET<T> intersection(final QSET<T> otherset) {
		return QSETnull.NA;
	}

	public IVL<T> intersection(final IVL<T> otherset) {
		return IVLnull.NA;
	}

	@Override
    public QSET<T> except(final T element) {
		return QSETnull.NA;
	}

	@Override
    public QSET<T> except(final SET<T> element) {
		return QSETnull.NA;
	}

	public QSET<T> except(final QSET<T> element) {
		return QSETnull.NA;
	}

	public IVL<T> hull() {
		return IVLnull.NA;
	}

	public IVL<T> nextTo(final T t) {
		return IVLnull.NA;
	}

	public IVL<T> nextAfter(final T t) {
		return IVLnull.NA;
	}

	public QSET<T> periodicHull(final QSET<T> otherset) {
		return QSETnull.NA;
	}

	public BL interleaves(final QSET<T> otherset) {
		return BLimpl.NA;
	}

	@Override
    public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}
}

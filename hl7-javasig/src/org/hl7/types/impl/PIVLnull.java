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
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.NullFlavor;
import org.hl7.types.PIVL;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.SET;
import org.hl7.types.TS;

/**
 * @author Gunther Schadow, Geoffry Roberts
 */
public class PIVLnull<T extends QTY> extends QSETnull<T> implements PIVL<T> {
	public static final PIVLnull NI = new PIVLnull(NullFlavorImpl.NI);
	public static final PIVLnull NA = new PIVLnull(NullFlavorImpl.NA);
	public static final PIVLnull UNK = new PIVLnull(NullFlavorImpl.UNK);
	public static final PIVLnull NASK = new PIVLnull(NullFlavorImpl.NASK);
	public static final PIVLnull ASKU = new PIVLnull(NullFlavorImpl.ASKU);
	public static final PIVLnull NAV = new PIVLnull(NullFlavorImpl.NAV);
	public static final PIVLnull OTH = new PIVLnull(NullFlavorImpl.OTH);
	// the empty set
	public static final PIVLnull NIL = new PIVLnull(null);

	private PIVLnull(final NullFlavor nf) {
		super(nf);
	}

	public IVL<T> phase() {
		return IVLnull.NA;
	}

	public QTY.diff period() {
		return PQnull.NA;
	}

	public CS alignment() {
		return CSnull.NA;
	}

	/**
	 * Utilize the equal() method from super.
	 */
	// public BL equal(ANY that) { return BLimpl.NA; }
	@Override
    public QSET<T> intersection(final SET<T> otherset) {
		return QSETnull.NA;
	}

	@Override
    public QSET<T> intersection(final QSET<T> otherset) {
		return QSETnull.NA;
	}

	public BL institutionSpecified() {
		return BLimpl.NA;
	}

	public BL contains(final TS element) {
		return BLimpl.NA;
	}

	@Override
    public BL isEmpty() {
		return BLimpl.NA;
	}

	@Override
    public BL nonEmpty() {
		return BLimpl.NA;
	}

	@Override
    public BL contains(final SET<T> otherset) {
		return BLimpl.NA;
	}

	@Override
    public INT cardinality() {
		return INTnull.NA;
	}

	@Override
    public QSET<T> union(final SET<T> otherset) {
		return QSETnull.NAV;
	}

	@Override
    public QSET<T> union(final QSET<T> otherset) {
		return QSETnull.NAV;
	}

	public QSET<T> except(final TS element) {
		return QSETnull.NAV;
	}

	@Override
    public QSET<T> except(final SET<T> otherset) {
		return QSETnull.NAV;
	}

	@Override
    public QSET<T> except(final QSET<T> otherset) {
		return QSETnull.NAV;
	}

	@Override
    public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}
}

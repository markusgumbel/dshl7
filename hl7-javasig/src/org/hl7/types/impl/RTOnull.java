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

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.NullFlavor;
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.RTO;

public class RTOnull extends ANYimpl implements RTO {
	public static final RTOnull NI = new RTOnull(NullFlavorImpl.NI);
	public static final RTOnull NA = new RTOnull(NullFlavorImpl.NA);
	public static final RTOnull UNK = new RTOnull(NullFlavorImpl.UNK);
	public static final RTOnull NASK = new RTOnull(NullFlavorImpl.NASK);
	public static final RTOnull ASKU = new RTOnull(NullFlavorImpl.ASKU);
	public static final RTOnull NAV = new RTOnull(NullFlavorImpl.NAV);
	public static final RTOnull OTH = new RTOnull(NullFlavorImpl.OTH);
	public static final RTOnull PINF = new RTOnull(NullFlavorImpl.PINF);
	public static final RTOnull NINF = new RTOnull(NullFlavorImpl.NINF);

	private RTOnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static RTOnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(RTOnull.class, nullFlavor);
	}

	public QTY.diff numerator() {
		return QTYnull.NA;
	}

	public QTY.diff denominator() {
		return QTYnull.NA;
	}

	/**
	 * FIXME: is NA correct or should it be derived from this and that?
	 * 
	 * FIXME: infinities may need to be treated differently!
	 */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	public BL isZero() {
		return BLimpl.NA;
	}

	public BL nonZero() {
		return BLimpl.NA;
	}

	public QTY.diff negated() {
		return QTYnull.NA;
	}

	public QTY.diff times(final REAL x) {
		return QTYnull.NA;
	}

	public QTY plus(final QTY.diff that) {
		return QTYnull.NA;
	}

	public QTY minus(final QTY.diff that) {
		return QTYnull.NA;
	}

	public QTY.diff minus(final QTY that) {
		return QTYnull.NA;
	}

	public BL lessOrEqual(final ORD that) {
		return BLimpl.NA;
	}

	public BL lessThan(final ORD that) {
		return BLimpl.NA;
	}

	public BL greaterThan(final ORD that) {
		return BLimpl.NA;
	}

	public BL greaterOrEqual(final ORD that) {
		return BLimpl.NA;
	}

	public BL compares(final ORD that) {
		return BLimpl.NA;
	}

	public diff epsilon() {
		return NI;
	}

	public int compareTo(final ORD that) {
		return 0;
	}
}

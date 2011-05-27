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
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.CV;
import org.hl7.types.NullFlavor;

/**
 * Minimal implementation of CS as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 * 
 * FIXME: this and all other C*null classes must have a code system for at least the OTH null value.
 */
public final class CRnull extends ANYimpl implements CR {
	public static final CRnull NI = new CRnull(NullFlavorImpl.NI);
	public static final CRnull NA = new CRnull(NullFlavorImpl.NA);
	public static final CRnull UNK = new CRnull(NullFlavorImpl.UNK);
	public static final CRnull NASK = new CRnull(NullFlavorImpl.NASK);
	public static final CRnull ASKU = new CRnull(NullFlavorImpl.ASKU);
	public static final CRnull NAV = new CRnull(NullFlavorImpl.NAV);
	public static final CRnull OTH = new CRnull(NullFlavorImpl.OTH);
	public static final CRnull PINF = new CRnull(NullFlavorImpl.PINF);
	public static final CRnull NINF = new CRnull(NullFlavorImpl.NINF);

	private CRnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static CRnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(CRnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	/** CR interface */
	public CV name() {
		return CVnull.NA;
	}

	public BL inverted() {
		return BLimpl.NA;
	}

	public CD value() {
		return CDnull.NA;
	}

	@Override
    public String toString() {
		return nullFlavor().code().toString();
	}
};

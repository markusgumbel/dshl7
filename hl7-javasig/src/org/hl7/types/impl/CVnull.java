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
import org.hl7.types.ED;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

/**
 * Minimal implementation of CV as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public class CVnull extends ANYimpl implements CV {
	public static final CVnull NI = new CVnull(NullFlavorImpl.NI);
	public static final CVnull NA = new CVnull(NullFlavorImpl.NA);
	public static final CVnull UNK = new CVnull(NullFlavorImpl.UNK);
	public static final CVnull NASK = new CVnull(NullFlavorImpl.NASK);
	public static final CVnull ASKU = new CVnull(NullFlavorImpl.ASKU);
	public static final CVnull NAV = new CVnull(NullFlavorImpl.NAV);
	public static final CVnull OTH = new CVnull(NullFlavorImpl.OTH);
	public static final CVnull PINF = new CVnull(NullFlavorImpl.PINF);
	public static final CVnull NINF = new CVnull(NullFlavorImpl.NINF);

	protected CVnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static CVnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(CVnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	public ST code() {
		return STnull.NA;
	}

	public ST displayName() {
		return STnull.NA;
	}

	public UID codeSystem() {
		return UIDnull.NA;
	}

	public ST codeSystemName() {
		return STnull.NA;
	}

	public ST codeSystemVersion() {
		return STnull.NA;
	}

	public ED originalText() {
		return EDnull.NA;
	}

	public BL implies(final CD x) {
		return BLimpl.NA;
	}

	public CD mostSpecificGeneralization(final CD x) {
		return CDnull.NA;
	}

	public LIST<CR> qualifier() {
		throw new UnsupportedOperationException();
	}

	public SET<CD> translation() {
		throw new UnsupportedOperationException();
	}
}
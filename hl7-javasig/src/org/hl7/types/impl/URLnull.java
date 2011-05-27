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
import org.hl7.types.CS;
import org.hl7.types.NullFlavor;
import org.hl7.types.ST;
import org.hl7.types.URL;

/**
 * Minimal implementation of URL as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public final class URLnull extends ANYimpl implements URL {
	public static final URLnull NI = new URLnull(NullFlavorImpl.NI);
	public static final URLnull NA = new URLnull(NullFlavorImpl.NA);
	public static final URLnull UNK = new URLnull(NullFlavorImpl.UNK);
	public static final URLnull NASK = new URLnull(NullFlavorImpl.NASK);
	public static final URLnull ASKU = new URLnull(NullFlavorImpl.ASKU);
	public static final URLnull NAV = new URLnull(NullFlavorImpl.NAV);
	public static final URLnull OTH = new URLnull(NullFlavorImpl.OTH);
	public static final URLnull PINF = new URLnull(NullFlavorImpl.PINF);
	public static final URLnull NINF = new URLnull(NullFlavorImpl.NINF);

	private URLnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static URLnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(URLnull.class, nullFlavor);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	/** URL interface */
	public CS scheme() {
		return CSnull.NA;
	}

	public ST address() {
		return STnull.NA;
	}
}
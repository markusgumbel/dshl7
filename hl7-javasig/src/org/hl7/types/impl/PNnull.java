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

import org.hl7.types.NullFlavor;
import org.hl7.types.PN;

public class PNnull extends ENnull implements PN {
	public static final PNnull NI = new PNnull(NullFlavorImpl.NI);
	public static final PNnull NA = new PNnull(NullFlavorImpl.NA);
	public static final PNnull UNK = new PNnull(NullFlavorImpl.UNK);
	public static final PNnull NASK = new PNnull(NullFlavorImpl.NASK);
	public static final PNnull ASKU = new PNnull(NullFlavorImpl.ASKU);
	public static final PNnull NAV = new PNnull(NullFlavorImpl.NAV);
	public static final PNnull OTH = new PNnull(NullFlavorImpl.OTH);
	public static final PNnull PINF = new PNnull(NullFlavorImpl.PINF);
	public static final PNnull NINF = new PNnull(NullFlavorImpl.NINF);

	private PNnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static PNnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(PNnull.class, nullFlavor);
	}
}

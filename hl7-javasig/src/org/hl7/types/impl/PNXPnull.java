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
import org.hl7.types.PNXP;

public class PNXPnull extends ENXPnull implements PNXP {
	public static final PNXPnull NI = new PNXPnull(NullFlavorImpl.NI);
	public static final PNXPnull NA = new PNXPnull(NullFlavorImpl.NA);
	public static final PNXPnull UNK = new PNXPnull(NullFlavorImpl.UNK);
	public static final PNXPnull NASK = new PNXPnull(NullFlavorImpl.NASK);
	public static final PNXPnull ASKU = new PNXPnull(NullFlavorImpl.ASKU);
	public static final PNXPnull NAV = new PNXPnull(NullFlavorImpl.NAV);
	public static final PNXPnull OTH = new PNXPnull(NullFlavorImpl.OTH);
	public static final PNXPnull PINF = new PNXPnull(NullFlavorImpl.PINF);
	public static final PNXPnull NINF = new PNXPnull(NullFlavorImpl.NINF);

	private PNXPnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static PNXPnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(PNXPnull.class, nullFlavor);
	}
}

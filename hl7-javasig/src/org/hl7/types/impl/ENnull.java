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

import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.IVL;
import org.hl7.types.NullFlavor;
import org.hl7.types.ST;
import org.hl7.types.TS;

public class ENnull extends LISTnull<ENXP> implements EN {
	public static final ENnull NI = new ENnull(NullFlavorImpl.NI);
	public static final ENnull NA = new ENnull(NullFlavorImpl.NA);
	public static final ENnull UNK = new ENnull(NullFlavorImpl.UNK);
	public static final ENnull NASK = new ENnull(NullFlavorImpl.NASK);
	public static final ENnull ASKU = new ENnull(NullFlavorImpl.ASKU);
	public static final ENnull NAV = new ENnull(NullFlavorImpl.NAV);
	public static final ENnull OTH = new ENnull(NullFlavorImpl.OTH);
	public static final ENnull PINF = new ENnull(NullFlavorImpl.PINF);
	public static final ENnull NINF = new ENnull(NullFlavorImpl.NINF);

	protected ENnull(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Get the a null value according to the null flavor code.
	 */
	public static ENnull valueOf(final String nullFlavor) {
	    return ANYimpl.nullValueOf(ENnull.class, nullFlavor);
	}

	// equal() method is defined in super class, no need to override

	@SuppressWarnings("unchecked")
	public DSET<CS> use() {
		return (DSET<CS>) DSETnull.NA;
	}
	
	public IVL<TS> validTime() {
	    return IVLnull.NA;
	}

	@Deprecated
	public IVL<TS> useablePeriod() {
		return validTime();
	}

	public ST formatted() {
		return STnull.NA;
	}
}

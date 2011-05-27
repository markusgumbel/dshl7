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

import java.util.List;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.IVL;
import org.hl7.types.PN;
import org.hl7.types.PNXP;
import org.hl7.types.TS;

public class PNimpl extends ENimpl implements PN {
	private PNimpl(final List<PNXP> parts, final DSET<CS> use, final IVL<TS> validTime) {
		super(((List)parts), use, validTime);
	}

	public static PN valueOf(final List<PNXP> parts, final DSET<CS> use, final IVL<TS> validTime) {
		if(parts == null) {
            return PNnull.NI;
        } else {
            return new PNimpl(parts, use, validTime);
        }
	}

	public static PN valueOf(final List<PNXP> parts, final DSET<CS> use) {
		return valueOf(parts, use, null);
	}

	public static PN valueOf(final List<PNXP> parts, final IVL<TS> validTime) {
		return valueOf(parts, null, validTime);
	}

	public static PN valueOf(final List<PNXP> parts) {
		return valueOf(parts, null, null);
	}

	@Override
    public BL equal(final ANY x) {
		if (!(x instanceof PN)) {
			return BLimpl.FALSE;
		}
		return super.equal(x);
	}
}

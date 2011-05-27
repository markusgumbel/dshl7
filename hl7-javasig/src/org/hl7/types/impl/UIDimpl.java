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
import org.hl7.types.UID;

public class UIDimpl extends STjlStringAdapter implements UID {
	protected UIDimpl(final String data) {
		super(data);
	}

	public static UID valueOf(final String data) {
		if (data == null || data.length() == 0) {
            return UIDnull.NI;
        } else {
			// here we could check for patterns and dispatch to the
			// proper subclass for OID, UUID and RUID.
			return new UIDimpl(data);
		}
	}

	@Override
    public BL equal(final ANY that) {
		if (!(that instanceof UID)) {
            return BLimpl.FALSE;
        } else {
			return BLimpl.valueOf(this.toString().equals(that.toString()));
		}
	}
}

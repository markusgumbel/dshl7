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
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.OID;

public class OIDimpl extends UIDimpl implements OID {
	private INT _leaf;
	private OID _butLeaf;
	private LIST<INT> _list;

	private OIDimpl(final String data) {
		super(data);
	}

	public static OID valueOf(final String data) {
		if (data == null || data.length() == 0) {
            return OIDnull.NI;
        } else {
			// here we could check for patterns and dispatch to the
			// proper subclass for OID, UUID and RUID.
			return new OIDimpl(data);
		}
	}

	@Override
    public final BL equal(final ANY that) {
		if (!(that instanceof OID)) {
            return BLimpl.FALSE;
        } else {
			return BLimpl.valueOf(this.toString().equals(that.toString()));
		}
	}

	public INT leaf() {
		return _leaf;
	}

	public LIST<INT> list() {
		return _list;
	}

	public OID value(final OID namespace) {
		throw new UnsupportedOperationException(); // fixed me: nned to be implemented
	}

	public OID butLeaf() {
		return _butLeaf;
	}
}

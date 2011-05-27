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

import org.hl7.types.ADXP;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.enums.AddressPartType;

public class ADXPimpl extends STjlStringAdapter implements ADXP {
	private final AddressPartType _type;

	private ADXPimpl(final String data, final AddressPartType type) {
		super(data);
		_type = type;
	}

	public static ADXP valueOf(final String data) {
		return valueOf(data, null);
	}

	public static ADXP valueOf(final String data, final AddressPartType type) {
		if(type != null && type.implies(AddressPartType.Delimiter).isTrue()) {
			return new ADXPimpl((data == null) ? "\n" : data, type);
		} else {
			return (data == null) ? (ADXP) ADXPnull.NI : (ADXP) new ADXPimpl(data.trim(), type);
		}
	}

	public CS type() {
		if(_type == null) {
            return CSnull.NI;
        } else {
            return _type;
        }
	}

	@Override
    public BL equal(final ANY x) {
		if(!(x instanceof ADXP)) {
            return BLimpl.FALSE;
        } else {
			final ADXP that = (ADXP) x;
			return super.equal(that).and(this.type().equal(that.type()));
		}
	}

	@Override
    public int hashCode() {
		int result = super.hashCode();
		result = 29 * result + (_type != null ? _type.hashCode() : 0);
		return result;
	}
}

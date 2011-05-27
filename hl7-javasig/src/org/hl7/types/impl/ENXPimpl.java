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
import org.hl7.types.DSET;
import org.hl7.types.ENXP;
import org.hl7.types.enums.EntityNamePartType;

public class ENXPimpl extends STjlStringAdapter implements ENXP {
    private final EntityNamePartType _type;
    private final DSET<CS> _qualifier;
    
    @SuppressWarnings("unchecked")
    protected ENXPimpl(final String data, final EntityNamePartType type,
            final DSET<CS> qualifier) {
        super(data.trim());
        _type = type;
        if (qualifier == null) {
            this._qualifier = (DSET<CS>) DSETnull.NI;
        } else {
            this._qualifier = qualifier;
        }
    }
    
    public static ENXP valueOf(final String data) {
        return valueOf(data, null, null);
    }
    
    public static ENXP valueOf(final String data, final EntityNamePartType type) {
        return valueOf(data, type, null);
    }
    
    public static ENXP valueOf(final String data,
            final EntityNamePartType type, final DSET<CS> qualifier) {
        if (data == null) {
            return ENXPnull.NI;
        } else {
            return new ENXPimpl(data, type, qualifier);
        }
    }
    
    @Override
    public BL equal(final ANY x) {
        if (!(x instanceof ENXP)) {
            return BLimpl.FALSE;
        } else {
            final ENXP that = (ENXP) x;
            return super.equal(that).and(this.type().equal(that.type()));
        }
    }
    
    public CS type() {
        if (_type == null) {
            return CSnull.NI;
        } else {
            return _type;
        }
    }
    
    public DSET<CS> qualifier() {
        return _qualifier;
    }
}

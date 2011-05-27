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

import org.hl7.types.AD;
import org.hl7.types.ADXP;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.NullFlavor;
import org.hl7.types.QSET;
import org.hl7.types.ST;
import org.hl7.types.TS;

public class ADnull extends LISTnull<ADXP> implements AD {
    public static final ADnull NI = new ADnull(NullFlavorImpl.NI);
    public static final ADnull NA = new ADnull(NullFlavorImpl.NA);
    public static final ADnull UNK = new ADnull(NullFlavorImpl.UNK);
    public static final ADnull NASK = new ADnull(NullFlavorImpl.NASK);
    public static final ADnull ASKU = new ADnull(NullFlavorImpl.ASKU);
    public static final ADnull NAV = new ADnull(NullFlavorImpl.NAV);
    public static final ADnull OTH = new ADnull(NullFlavorImpl.OTH);
    public static final ADnull PINF = new ADnull(NullFlavorImpl.PINF);
    public static final ADnull NINF = new ADnull(NullFlavorImpl.NINF);
    
    private ADnull(final NullFlavor nf) {
        super(nf);
    }
    
    /**
     * Get the a null value according to the null flavor code.
     */
    public static ADnull valueOf(final String nullFlavorString) {
        return ANYimpl.nullValueOf(ADnull.class, nullFlavorString);
    }
    
    // FIXME: is NA correct or should it be derived from this and that?
    @Override
    public BL equal(final ANY that) {
        return BLimpl.NA;
    }
    
    @SuppressWarnings("unchecked")
    public DSET<CS> use() {
        return (DSET<CS>) DSETnull.NA;
    }
    
    @SuppressWarnings("unchecked")
    public QSET<TS> validTime() {
        return QSETnull.NA;
    }
    
    public ST formatted() {
        return STnull.NA;
    }
    
    public BL isNotOrdered() {
        return BLimpl.NA;
    }
}
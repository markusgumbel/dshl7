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
import org.hl7.types.NullFlavor;

public final class BLimpl extends ANYimpl implements BL {
    public static final BLimpl TRUE = new BLimpl(true);
    public static final BLimpl FALSE = new BLimpl(false);
    public static final BLimpl NI = new BLimpl(NullFlavorImpl.NI);
    public static final BLimpl NA = new BLimpl(NullFlavorImpl.NA);
    public static final BLimpl UNK = new BLimpl(NullFlavorImpl.UNK);
    public static final BLimpl NASK = new BLimpl(NullFlavorImpl.NASK);
    public static final BLimpl ASKU = new BLimpl(NullFlavorImpl.ASKU);
    public static final BLimpl NAV = new BLimpl(NullFlavorImpl.NAV);
    public static final BLimpl OTH = new BLimpl(NullFlavorImpl.OTH);
    private static final byte cFALSE = -1;
    private static final byte cTRUE = 1;
    private byte _value;
    
    private BLimpl(final NullFlavor nf) {
        super(nf);
    }
    
    private BLimpl(final boolean value) {
        super(null);
        if (value) {
            this._value = cTRUE;
        } else {
            this._value = cFALSE;
        }
    }
    
    public static BL valueOf(final boolean x) {
        if (x) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
    
    public static BL valueOf(final String x) {
        if (x == null) {
            return NI;
        }
        final String s = x.intern();
        if (s.trim().length() == 0) {
            return NI;
        }
        if (s.toLowerCase().equals(TRUE_STRING)) {
            return TRUE;
        }
        if (s.toLowerCase().equals(FALSE_STRING)) {
            return FALSE;
        }
        
        throw new IllegalArgumentException("illegal symbol for BL value " + x);
    }
    
    public static BL nullValueOf(final String nullFlavorString) {
        return ANYimpl.nullValueOf(BLimpl.class, nullFlavorString);
    }
    
    public static BL valueOf(final NullFlavor x) {
        if (x.implies(NullFlavorImpl.NA).isTrue()) {
            return BLimpl.NA;
        }
        if (x.implies(NullFlavorImpl.OTH).isTrue()) {
            return BLimpl.OTH;
        }
        if (x.implies(NullFlavorImpl.NAV).isTrue()) {
            return BLimpl.NAV;
        }
        if (x.implies(NullFlavorImpl.ASKU).isTrue()) {
            return BLimpl.ASKU;
        }
        if (x.implies(NullFlavorImpl.NASK).isTrue()) {
            return BLimpl.NASK;
        }
        if (x.implies(NullFlavorImpl.UNK).isTrue()) {
            return BLimpl.UNK;
        }
        return BLimpl.NI;
    }
    
    public BL and(final BL that) {
        switch (this._value) {
        case cTRUE:
            return that;
        case cFALSE:
            return FALSE;
        default:
            if (that.nonNull().isTrue()) {
                return that;
            } else {
                // System.out.println("ARE WE HERE?? --> "+ that);
                // System.out.println("ARE WE HERE?? --> "+
                // this.nullFlavor().mostSpecificGeneralization(that.nullFlavor()));
                return valueOf(this.nullFlavor().mostSpecificGeneralization(
                        that.nullFlavor()));
            }
        }
    }
    
    public BL not() {
        switch (this._value) {
        case cTRUE:
            return FALSE;
        case cFALSE:
            return TRUE;
        default:
            return this;
        }
    }
    
    public BL or(final BL that) {
        switch (this._value) {
        case cTRUE:
            return this;
        case cFALSE:
            return that;
        default:
            if (that.nonNull().isTrue()) {
                return that;
            } else {
                return valueOf(this.nullFlavor().mostSpecificGeneralization(
                        that.nullFlavor()));
            }
        }
    }
    
    public BL eor(final BL that) {
        return this.or(that).and(this.and(that).not());
    }
    
    public BL implies(final BL that) {
        return this.not().or(that);
    }
    
    @Override
    public BL equal(final ANY that) {
        if (!(that instanceof BL)) {
            return BLimpl.FALSE;
        } else {
            final BL thatBL = (BL) that;
            // now I use the bit more costly method in order to promote
            // objects of this implementation to be used rather than
            // other implementations.
            if (thatBL.isTrue()) {
                return this;
            } else if (thatBL.isFalse()) {
                return this.not();
            } else if (this.isNullJ()) {
                return valueOf(thatBL.nullFlavor().mostSpecificGeneralization(
                        this.nullFlavor()));
            } else {
                return thatBL;
            }
        }
    }
    
    public boolean isTrue() {
        return this._value == cTRUE;
    }
    
    public boolean isFalse() {
        return this._value == cFALSE;
    }
    
    @Override
    public String toString() {
        if (isTrue()) {
            return TRUE_STRING;
        } else if (isFalse()) {
            return FALSE_STRING;
        } else {
            return null;
        }
    }
}

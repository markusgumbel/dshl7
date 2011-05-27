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
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.CV;

public class CRimpl extends ANYimpl implements CR {
    private final CV _name;
    private final CD _value;
    private final BL _inverted;
    
    private CRimpl(final CV name, final CD value, final BL inverted) {
        super(null);
        if (value == null || value.isNull().isTrue()) {
            throw new IllegalArgumentException();
        } else {
            this._name = name;
            this._value = value;
            this._inverted = inverted;
        }
    }
    
    public static CR valueOf(final CV name, final CD value,
            final boolean invertedBool) {
        final BL inverted = BLimpl.valueOf(invertedBool);
        return new CRimpl(name, value, inverted);
    }
    
    public static CR valueOf(final CV name, final CD value) {
        return valueOf(name, value, false);
    }
    
    public static CR valueOf(final CD value) {
        return valueOf(CVnull.NA, value, false);
    }
    
    @Override
    public BL equal(final ANY that) {
        if (this == that) {
            return BLimpl.TRUE;
        } else if (that.isNull().isTrue()) {
            return BLimpl.FALSE; // FIXME: false or NI or UNK?
        } else if (that instanceof CR) {
            final CR thatCR = (CR) that;
            return this.name().equal(thatCR.name()).and(
                    this.value().equal(thatCR.value()).and(
                            this.inverted().equal(thatCR.inverted())));
        } else {
            return BLimpl.FALSE;
        }
    }
    
    public CV name() {
        return this._name;
    }
    
    public CD value() {
        return this._value;
    }
    
    public BL inverted() {
        return this._inverted;
    }
}
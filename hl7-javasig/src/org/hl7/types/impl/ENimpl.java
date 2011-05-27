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
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.IVL;
import org.hl7.types.ST;
import org.hl7.types.TS;

public class ENimpl extends LISTjuListAdapter<ENXP> implements EN {
    private final DSET<CS> _use;
    private final IVL<TS> _validTime;
    
    @SuppressWarnings("unchecked")
    protected ENimpl(final List<ENXP> list, final DSET<CS> use,
            final IVL<TS> validTime) {
        super(list);
        if (use == null) {
            _use = (DSET<CS>) DSETnull.NI;
        } else {
            _use = use;
        }
        if (validTime == null) {
            _validTime = IVLnull.NI;
        } else {
            _validTime = validTime;
        }
    }
    
    /**
     * Get the a null value according to the null flavor code.
     */
    public static EN valueOf(final List<ENXP> list, final DSET<CS> use,
            final IVL<TS> useablePeriod) {
        return (list == null) ? (EN) ENnull.NI : (EN) new ENimpl(list, use,
                useablePeriod);
    }
    
    @SuppressWarnings("unchecked")
    public static EN valueOf(final List<ENXP> list, final DSET<CS> use) {
        return (list == null) ? (EN) ENnull.NI : (EN) new ENimpl(list, use,
                IVLnull.NI);
    }
    
    @SuppressWarnings("unchecked")
    public static EN valueOf(final List<ENXP> list, final IVL<TS> useablePeriod) {
        return (list == null) ? (EN) ENnull.NI : (EN) new ENimpl(list,
                (DSET<CS>) DSETnull.NI, useablePeriod);
    }
    
    @SuppressWarnings("unchecked")
    public static EN valueOf(final List<ENXP> list) {
        return (list == null) ? (EN) ENnull.NI : (EN) new ENimpl(list,
                (DSET<CS>) DSETnull.NI, IVLnull.NI);
    }
    
    @Override
    public BL equal(final ANY y) {
        if (!(y instanceof EN)) {
            return BLimpl.FALSE;
        }
        
        final EN that = (EN) y;
        
        /*
         * we can't call LISTjuListAdapter.equal(ANY) here because for EN, order
         * is not significant
         */

        if (!containsAllValues(this, that)) {
            return BLimpl.FALSE;
        }
        if (!containsAllValues(that, this)) {
            return BLimpl.FALSE;
        }
        
        return BLimpl.TRUE;
        
        // use code and valid time are excluded from the equality test
    }
    
    /**
     * Check if one <code>EN</code> contains all of the <code>ENXP</code>
     * values in another <code>EN</code>.
     * 
     * @param en1
     *                first.
     * @param en2
     *                second.
     * @return <code>true</code> if <code>en2</code> contains all of the
     *         values in <code>en1</code> (does not check if <code>en1</code>
     *         contains all of the values in <code>en2</code>); otherwise
     *         <code>false</code>).
     */
    private static boolean containsAllValues(final EN en1, final EN en2) {
        for (ENXP enxp1 : en1) {
            boolean foundMatch = false;
            for (ENXP enxp2 : en2) {
                if (enxp1.equal(enxp2).isTrue()) {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }
        return true;
    }
    
    public DSET<CS> use() {
        return _use;
    }
    
    /**
     * @deprecated use {@link #validTime()} instead.
     */
    @Deprecated
    public IVL<TS> useablePeriod() {
        return validTime();
    }
    
    public IVL<TS> validTime() {
        return _validTime;
    }
    
    public ST formatted() {
        throw new UnsupportedOperationException();
    }
}

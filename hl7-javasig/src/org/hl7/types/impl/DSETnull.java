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

import java.util.Iterator;
import java.util.Set;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.Criterion;
import org.hl7.types.DSET;
import org.hl7.types.INT;
import org.hl7.types.NullFlavor;
import org.hl7.types.SET;

/**
 * Minimal implementation of DSET for nulls. We don't want to instantiate new
 * such objects every time we use them.
 */
public class DSETnull<T extends ANY> extends ANYimpl implements DSET<T> {
    public static final DSETnull<? extends ANY> NI = new DSETnull<ANY>(NullFlavorImpl.NI);
    public static final DSETnull<? extends ANY> NA = new DSETnull<ANY>(NullFlavorImpl.NA);
    public static final DSETnull<? extends ANY> UNK = new DSETnull<ANY>(NullFlavorImpl.UNK);
    public static final DSETnull<? extends ANY> NASK = new DSETnull<ANY>(NullFlavorImpl.NASK);
    public static final DSETnull<? extends ANY> ASKU = new DSETnull<ANY>(NullFlavorImpl.ASKU);
    public static final DSETnull<? extends ANY> NAV = new DSETnull<ANY>(NullFlavorImpl.NAV);
    public static final DSETnull<? extends ANY> OTH = new DSETnull<ANY>(NullFlavorImpl.OTH);
    
    protected DSETnull(final NullFlavor nf) {
        super(nf);
    }
    
    /**
     * Get the a null value according to the null flavor code.
     */
    @SuppressWarnings("unchecked")
    public static DSETnull<? extends ANY> valueOf(final String nullFlavor) {
        return ANYimpl.nullValueOf(DSETnull.class, nullFlavor);
    }
    
    // /** FIXME: is NA correct or should it be derived from this and that? */
    // public BL equal(ANY that) { return BLimpl.NA; }
    /**
     * the equal will tell whether the given ANY is equal to this object.
     * 
     * @param that
     * @return BLimpl.TRUE if that is equal to this object.
     */
    @Override
    @SuppressWarnings("unchecked")
    public BL equal(final ANY that) {
        /**
         * The contract of equal shall be carried no matter the semantics value
         * of the class itself.
         */
        if (that instanceof DSETnull) {
            final DSETnull thatObj = (DSETnull) that;
            return thatObj.nullFlavor().equal(this.nullFlavor());
        } else {
            return BLimpl.FALSE;
        }
    }
    
    // equals() method has been overriden in ANYimpl class
    /**
     * override hashCode() method.
     * 
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return this.nullFlavor().hashCode();
    }
    
    public BL contains(final T element) {
        return BLimpl.NA;
    }
    
    public BL contains(final SET<T> subset) {
        return BLimpl.NA;
    }
    
    public INT cardinality() {
        return INTnull.NA;
    }
    
    @SuppressWarnings("unchecked")
    public DSET<T> union(final SET<T> subset) {
        return (DSET<T>) DSETnull.NA;
    }
    
    @SuppressWarnings("unchecked")
    public DSET<T> intersection(final SET<T> otherset) {
        return (DSET<T>) DSETnull.NA;
    }
    
    @SuppressWarnings("unchecked")
    public DSET<T> except(final T element) {
        return (DSET<T>) DSETnull.NA;
    }
    
    @SuppressWarnings("unchecked")
    public DSET<T> except(final SET<T> element) {
        return (DSET<T>) DSETnull.NA;
    }
    
    public BL isEmpty() {
        return BLimpl.NA;
    }
    
    public BL nonEmpty() {
        return BLimpl.NA;
    }
    
    public Set<T> getUnderlyingSet() {
        return null;
    }
    
    public Iterator<T> iterator() {
        return new EmptySet.NullIterator<T>();
    }
    
    public DSET<T> select(final Criterion<T> c) {
        throw new UnsupportedOperationException();
    }
    
    @SuppressWarnings("unchecked")
    public T any() {
        return (T) DSETnull.NA;
    }
}

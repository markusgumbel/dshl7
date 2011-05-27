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

import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.NullFlavor;

/**
 * Minimal implementation of <code>BAG</code> for nulls. We don't want to
 * instantiate new such objects every time we use them.
 */
public class BAGnull<T extends ANY> extends ANYimpl implements BAG<T> {
    @SuppressWarnings("unchecked")
    public static final BAG NI = new BAGnull(NullFlavorImpl.NI);
    @SuppressWarnings("unchecked")
    public static final BAG NA = new BAGnull(NullFlavorImpl.NA);
    @SuppressWarnings("unchecked")
    public static final BAG UNK = new BAGnull(NullFlavorImpl.UNK);
    @SuppressWarnings("unchecked")
    public static final BAG NASK = new BAGnull(NullFlavorImpl.NASK);
    @SuppressWarnings("unchecked")
    public static final BAG ASKU = new BAGnull(NullFlavorImpl.ASKU);
    @SuppressWarnings("unchecked")
    public static final BAG NAV = new BAGnull(NullFlavorImpl.NAV);
    @SuppressWarnings("unchecked")
    public static final BAG OTH = new BAGnull(NullFlavorImpl.OTH);
    // the empty bag
    @SuppressWarnings("unchecked")
    public static final BAG NIL = new BAGnull(null);
    
    protected BAGnull(final NullFlavor nf) {
        super(nf);
    }
    
    /**
     * Get the a null value according to the null flavor code.
     */
    @SuppressWarnings("unchecked")
    public static <T extends ANY> BAG<T> valueOf(final String nullFlavor) {
        return ANYimpl.nullValueOf(BAGnull.class, nullFlavor);
    }
    
    /** FIXME: is NA correct or should it be derived from this and that? */
    @Override
    public BL equal(final ANY that) {
        return BLimpl.NA;
    }
    
    public INT contains(final T kind) {
        return INTnull.NA;
    }
    
    public BAG<T> plus(final BAG<T> y) {
        throw new UnsupportedOperationException();
    }
    
    public BAG<T> minus(final BAG<T> y) {
        throw new UnsupportedOperationException();
    }
    
    public BL isEmpty() {
        return BLimpl.NA;
    }
    
    @Deprecated
    public BL nonEmpty() {
        return notEmpty();
    }
    
    public BL notEmpty() {
        return BLimpl.NA;
    }
    
    public INT size() {
        return INTnull.NA;
    }
    
    public Iterator<T> iterator() {
        return new EmptySet.NullIterator<T>();
    }
}

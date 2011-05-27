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
import java.util.Set;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.CV;
import org.hl7.types.ED;
import org.hl7.types.LIST;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

/**
 * Minimal implementation of <code>CD</code> as a model we can use for all
 * other type. This one is only used to convey NULL values. Again, we have
 * static values of it, so we don't create so many objects in a running program.
 */
public final class CDimpl extends ANYimpl implements CD {
    private final CV _primary;
    private final ED _originalText;
    private final LIST<CR> _qualifier;
    private final SET<CD> _translation;
    
    private CDimpl(final ST code, final UID codeSystem,
            final ST codeSystemName, final ST codeSystemVersion,
            final ST displayName, final ED originalText,
            final LIST<CR> qualifier, final SET<CD> translation) {
        super(null);
        if (code == null || code.isNull().isTrue() || codeSystem == null
                || codeSystem.isNull().isTrue()) {
            throw new IllegalArgumentException();
        }
        _primary =
                CVimpl.valueOf(code, codeSystem, STnull.NA, displayName,
                        codeSystemName, codeSystemVersion);
        _originalText = originalText;
        _qualifier = qualifier;
        _translation = translation;
    }
    
    /**
     * allows creating a new CDimpl when we already get all the parameters from
     * the user
     */
    public static CD valueOf(final ST code, final UID codeSystem,
            final ST codeSystemName, final ST codeSystemVersion,
            final ST displayName, final ED originalText,
            final LIST<CR> qualifiers, final SET<CD> translations) {
        return new CDimpl(code, codeSystem, codeSystemName, codeSystemVersion,
                displayName, originalText, qualifiers, translations);
    }
    
    public static CD valueOf(final String codeString,
            final String codeSystemString, final String codeSystemNameString,
            final String codeSystemVersionString,
            final String displayNameString, final ED originalText,
            final List<CR> qualifierList, final Set<CD> translationSet) {
        final ST code = STjlStringAdapter.valueOf(codeString);
        final UID codeSystem = UIDimpl.valueOf(codeSystemString);
        if (code.isNull().isTrue() || codeSystem.isNull().isTrue()) {
            return CDnull.NI; // enforce that we need a root!
            // FIXME: should this sort of convenience be one level up?
            // I mean, we could just throw an IllegalArgumentException?
        }
        final ST codeSystemName =
                STjlStringAdapter.valueOf(codeSystemNameString);
        final ST codeSystemVersion =
                STjlStringAdapter.valueOf(codeSystemVersionString);
        final ST displayName = STjlStringAdapter.valueOf(displayNameString);
        final LIST<CR> qualifiers = LISTjuListAdapter.valueOf(qualifierList);
        final SET<CD> translations = SETjuSetAdapter.valueOf(translationSet);
        return new CDimpl(code, codeSystem, codeSystemName, codeSystemVersion,
                displayName, originalText, qualifiers, translations);
    }
    
    public static CD valueOf(final String codeString,
            final String codeSystemString) {
        return valueOf(codeString, codeSystemString, null, null, null, null,
                null, null);
    }
    
    @Override
    public BL equal(final ANY that) {
        if (this == that) {
            return BLimpl.TRUE;
        } else if (that.isNull().isTrue()) {
            return BLimpl.FALSE; // FIXME: false or NI or UNK?
        } else if (that instanceof CD) {
            final CD thatCD = (CD) that;
            return this.codeSystem().equal(thatCD.codeSystem()).and(
                    this.code().equal(thatCD.code()));
        } else {
            return BLimpl.FALSE;
        }
    }
    
    // SK: hashCode() needs to be implemented and to be compatible with equal().
    @Override
    public int hashCode() {
        return this.codeSystem().toString().hashCode() + 23
                * this.code().toString().hashCode();
    }
    
    public ST code() {
        return _primary.code();
    }
    
    public ST displayName() {
        return _primary.displayName();
    }
    
    public UID codeSystem() {
        return _primary.codeSystem();
    }
    
    public ST codeSystemName() {
        return _primary.codeSystemName();
    }
    
    public ST codeSystemVersion() {
        return _primary.codeSystemVersion();
    }
    
    public ED originalText() {
        return _originalText;
    }
    
    public BL implies(final CD x) {
        final BL equality = this.equal(x);
        if (equality.isTrue()) {
            return equality;
        } else {
            throw new UnsupportedOperationException();
        }
    }
    
    public CD mostSpecificGeneralization(final CD x) {
        throw new UnsupportedOperationException();
    }
    
    public LIST<CR> qualifier() {
        return this._qualifier;
    }
    
    public SET<CD> translation() {
        return this._translation;
    }
}
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
import org.hl7.types.CE;
import org.hl7.types.CR;
import org.hl7.types.CV;
import org.hl7.types.ED;
import org.hl7.types.LIST;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

public final class CEimpl extends ANYimpl implements CE {
    private CV _primary;
    private SET<CD> _translation;
    
    private CEimpl(final CV primary) {
        super(null);
        if (primary == null || primary.isNull().isTrue()) {
            // FIXME: this wouldn't be necessary, or would it?
            throw new IllegalArgumentException();
        } else {
            this._primary = primary;
        }
    }
    
    /**
     * &quot;copy&quot; the old <code>CE</code>, and add the given
     * translations
     * 
     * @param old
     * @param translation
     * @return
     */
    public static CE valueOf(final CE old, final SET<CD> translation) {
        if (old instanceof CV) {
            return valueOf((CV) old, translation);
        }
        if (old.isNull().isTrue()) {
            return CEnull.NI; // enforce that we need a root!
            // FIXME: does this make sense?
        } else {
            final CEimpl theCE = new CEimpl(((CEimpl) old)._primary);
            theCE._translation = translation;
            return theCE;
        }
    }
    
    public static CE valueOf(final CV primary, final SET<CD> translation) {
        if (primary.isNull().isTrue()) {
            return CEnull.NI; // enforce that we need a root!
            // FIXME: does this make sense?
        } else {
            final CEimpl theCE = new CEimpl(primary);
            theCE._translation = translation;
            return theCE;
        }
    }
    
    // this is here mostly for testing the hibernate stuff
    @SuppressWarnings("unchecked")
    public static CE valueOf(final String code, final String codeSystem,
            final String displayName) {
        if (code == null) {
            throw new IllegalArgumentException("code");
        }
        if (codeSystem == null) {
            throw new IllegalArgumentException("codeSystem");
        }
        if (displayName == null) {
            throw new IllegalArgumentException("displayName");
        }
        return valueOf(CVimpl.valueOf(STjlStringAdapter.valueOf(code), UIDimpl
                .valueOf(codeSystem), STnull.NI, STjlStringAdapter
                .valueOf(displayName), STnull.NI, STnull.NI), SETnull.NI);
    }
    
    @Override
    public BL equal(final ANY that) {
        if (this == that) {
            return BLimpl.TRUE;
        } else if (that.isNull().isTrue()) {
            return BLimpl.FALSE; // FIXME: false or NI or UNK?
        } else if (that instanceof CEimpl) {
            final CEimpl thatCE = (CEimpl) that;
            return this._primary.equal(thatCE._primary).or(
                    this._primary.equal(thatCE._translation)).or(
                    this._translation.equal(thatCE._primary)).or(
                    this._translation.equal(thatCE._translation));
        } else if (that instanceof CE) { // FIXME: now what?
            final CE thatCE = (CE) that;
            return this.codeSystem().equal(thatCE.codeSystem()).and(
                    this.code().equal(thatCE.code())).and(
                    this._translation.equal(thatCE.translation()));
            // throw new UnsupportedOperationException();
        } else if (that instanceof CV) {
            final CV thatCV = (CV) that;
            return this._primary.equal(thatCV).or(
                    this._translation.equal(thatCV));
        } else {
            return BLimpl.FALSE;
        }
        // FIXME: there are much more combinations, and there is CD too.
        // this can't be done right until we have tackled the SET
    }
    
    public ST code() {
        return this._primary.code();
    }
    
    public ST displayName() {
        return this._primary.displayName();
    }
    
    public UID codeSystem() {
        return this._primary.codeSystem();
    }
    
    public ST codeSystemName() {
        return this._primary.codeSystemName();
    }
    
    public ST codeSystemVersion() {
        return this._primary.codeSystemVersion();
    }
    
    public ED originalText() {
        return this._primary.originalText();
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
    
    @SuppressWarnings("unchecked")
    public LIST<CR> qualifier() {
        return LISTnull.NA;
    }
    
    public SET<CD> translation() {
        return _translation;
    }
    
    @Override
    public int hashCode() {
        int result;
        result = (_primary != null ? _primary.hashCode() : 0);
        result =
                29 * result
                        + (_translation != null ? _translation.hashCode() : 0);
        return result;
    }
}
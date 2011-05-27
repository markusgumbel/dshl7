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
 x*
 * Contributor(s): 
 */
package org.hl7.types.impl;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.ED;
import org.hl7.types.LIST;
import org.hl7.types.PQR;
import org.hl7.types.REAL;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

public class PQRimpl extends ANYimpl implements PQR {
	private REAL _value;
	private ST _code;
	private UID _codeSystem;
	protected ST _codeSystemName = STnull.NI; // don't need to store that
	protected ST _codeSystemVersion = STnull.NI;
	protected ST _displayName = STnull.NI; // don't need to store that either
	protected ST _originalText = STnull.NI;

	protected PQRimpl(final REAL value, final ST code, final UID codeSystem) {
		super(null);
		/*
		 * if(code == null || code.isNullJ() || codeSystem == null || codeSystem.isNullJ() || value == null ||
		 * value.isNull().isTrue()) {
		 */
		if (code == null || code.isNullJ() || codeSystem == null || codeSystem.isNullJ() || value == null) {
			throw new IllegalArgumentException();
		} else {
			this._value = value;
			this._code = code;
			this._codeSystem = codeSystem;
		}
	}


	public static PQR valueOf(final REAL value, final String code, final String codeSystem) {
		return valueOf(value, STjlStringAdapter.valueOf(code), UIDimpl.valueOf(codeSystem), STnull.NI, STnull.NI,
				STnull.NI, STnull.NI);
	}

	public static PQR valueOf(final REAL value, final ST code, final UID codeSystem, final ST originalText, final ST displayName, final ST codeSystemName,
			final ST codeSystemVersion) {
		if (code.isNullJ() || codeSystem.isNullJ()) {
			throw new IllegalArgumentException("code and codeSystem cannot be null");
		} else {
			// if code system is not known, we create an unchecked code value
			final PQRimpl thePQR = new PQRimpl(value, code, codeSystem);
			thePQR._displayName = (displayName != null ? displayName : STnull.NI);
			thePQR._codeSystemName = (codeSystemName != null ? codeSystemName : STnull.NI);
			thePQR._codeSystemVersion = (codeSystemVersion != null ? codeSystemVersion : STnull.NI);
			thePQR._originalText = (originalText != null ? originalText : STnull.NI);
			return thePQR;
		}
	}

	@Override
    public BL equal(final ANY that) {
		if (this == that) {
            return BLimpl.TRUE;
        } else if (that.isNullJ()) {
            return BLimpl.FALSE; // FIXME: false or NI or UNK?
        } else if (that instanceof PQR) {
			final PQR thatPQR = (PQR) that;
			return this.codeSystem().equal(thatPQR.codeSystem()).and(this.code().equal(thatPQR.code()));
		} else {
            return BLimpl.FALSE;
        }
	}

	public REAL value() {
		return this._value;
	}

	public ST code() {
		return this._code;
	}

	public ST displayName() {
		return this._displayName;
	}

	public UID codeSystem() {
		return this._codeSystem;
	}

	public ST codeSystemName() {
		return this._codeSystemName;
	}

	public ST codeSystemVersion() {
		return this._codeSystemVersion;
	}

	public ED originalText() {
		return this._originalText;
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
		return LISTnull.NA;
	}

	public SET<CD> translation() {
		return SETnull.NA;
	}
};

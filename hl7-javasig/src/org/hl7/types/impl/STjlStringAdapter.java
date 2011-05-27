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
import org.hl7.types.BIN;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.ED;
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.ST;
import org.hl7.types.TEL;

public class STjlStringAdapter extends ANYimpl implements ST {
	private String _data;
	protected CS _language;

	protected STjlStringAdapter(final String data) {
		super(null);
		if (data == null) {
            throw new IllegalArgumentException();
        } else {
            this._data = data;
        }
	}

	protected STjlStringAdapter(final String data, final CS language) {
		super(null);
		if (data == null) {
            throw new IllegalArgumentException();
        } else {
            _data = data;
        }
		if(language == null || language.nonNull().implies(language.codeSystem().equal(UIDRegistry.HUMAN_LANGUAGE)).isTrue()) {
            _language = language;
        } else {
            throw new IllegalArgumentException("if language is specified it must be HumanLanguage (" + UIDRegistry.HUMAN_LANGUAGE + "), please do not make up code systems");
        }
	}

	public static ST valueOf(final String data) {
		if (data == null) {
            // FIXME: should this sort of convenience be one level up?
			return STnull.NA;
        } else {
            return new STjlStringAdapter(data, null);
        }
	}

	@Override
    public BL equal(final ANY that) {
		if (that instanceof ST) {
			if (that.isNull().isFalse()) {
                return BLimpl.valueOf(this._data.equals(that.toString()));
            } else {
                return BLimpl.NI;
            }
		} else {
            return BLimpl.FALSE;
        }
	}

	// FIXME: these look pretty silly here, but for now I keep trying
	// a 1:1 object of V3DT to Java as much as possible.
	public BL head() {
		throw new UnsupportedOperationException();
	}

	public LIST<BL> tail() {
		throw new UnsupportedOperationException();
	}

	public Iterator<BL> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
    public String toString() {
		return this._data;
	}

	public ST headST() {
		return new STjlStringAdapter(this._data.substring(0, 1));
	}

	public ST tailST() {
		return new STjlStringAdapter(this._data.substring(1));
	}

	// FIXME: this ought to do something good
	public Iterator<ST> listSTIterator() {
		throw new UnsupportedOperationException();
	}

	public BL isEmpty() {
		if (this._data.length() == 0) {
            return BLimpl.TRUE;
        } else {
            return BLimpl.FALSE;
        }
	}

	public BL nonEmpty() {
		if (this._data.length() > 0) {
            return BLimpl.TRUE;
        } else {
            return BLimpl.FALSE;
        }
	}

	public INT length() {
		return INTlongAdapter.valueOf(this._data.length());
	}

	// The ED interface
	public CS mediaType() {
		// throw new UnsupportedOperationException();
		return CSimpl.valueOf("text/plain", "unknown");
	}

	public CS charset() {
		throw new UnsupportedOperationException();
		// return /* UTF-8 */;
	}

	public CS compression() {
		throw new UnsupportedOperationException();
		// return CSnull.NA;
	}

	public CS language() {
		if(_language == null) {
            return _language;
        } else {
            return CSnull.NA;
        }
	}

	public TEL reference() {
		throw new UnsupportedOperationException();
		// return TELnull.NA;
	}

	public BIN integrityCheck() {
		throw new UnsupportedOperationException();
		// return BINnull.NA;
	}

	public CS integrityCheckAlgorithm() {
		throw new UnsupportedOperationException();
		// return CSnull.NA;
	}

	public ED thumbnail() {
		throw new UnsupportedOperationException();
		// return EDnull.NA;
	}

	@Override
    public int hashCode() {
		return _data.hashCode();
	}
}

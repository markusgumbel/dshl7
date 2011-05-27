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

import java.util.logging.Logger;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.CV;
import org.hl7.types.CodeValueFactory;
import org.hl7.types.ED;
import org.hl7.types.LIST;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

public class CVimpl extends ANYimpl implements CV {
	private final ST _code;
	private final UID _codeSystem;
	protected ST _codeSystemName = STnull.NI; // don't need to store that
	protected ST _codeSystemVersion = STnull.NI;
	protected ST _displayName = STnull.NI; // don't need to store that either
	protected ST _originalText = STnull.NI;
	private static final Logger LOGGER = Logger.getLogger(CV.class.getName());

	protected CVimpl(final ST code, final UID codeSystem) {
		super(null);
		if (code == null || code.isNull().isTrue() || codeSystem == null || codeSystem.isNull().isTrue()) {
            throw new IllegalArgumentException();
        } else {
			this._code = code;
			this._codeSystem = codeSystem;
		}
	}

	public static CV valueOf(final String code, final String codeSystem) {
		return valueOf(STjlStringAdapter.valueOf(code), UIDimpl.valueOf(codeSystem), STnull.NI, STnull.NI, STnull.NI,
				STnull.NI);
	}

	public static CV valueOf(final ST code, final UID codeSystem, final ST originalText, final ST displayName, final ST codeSystemName,
			final ST codeSystemVersion) {
		if (code.isNull().isTrue() || codeSystem.isNull().isTrue()) {
			// enforce that we need a root!
			// FIXME: should this sort of convenience be one level up?
			// I mean, we could just throw an IllegalArgumentException?
			return originalText.isNull().isTrue() ? CVnull.NI : CVExceptionalImpl.valueOf(originalText);
		} else {
			try { // try to create a specialized code value instance
				final CV result = CodeValueFactory.getInstance().valueOf(code, codeSystem, codeSystemVersion, displayName,
						originalText);
				// System.err.println("#CVF#: " + result.getClass());
				LOGGER.fine("#CVF#: " + result.getClass());
				return result;
			} catch (final CodeValueFactory.InvalidCodeException x) {
				throw new IllegalArgumentException("codeSystem " + codeSystem.toString(), x);
			} catch (final CodeValueFactory.UnknownCodeSystemException x) {
				// if code system is not known, we create an unchecked code value
				final CVimpl theCV = new CVimpl(code, codeSystem);
				theCV._displayName = (displayName != null ? displayName : STnull.NI);
				theCV._codeSystemName = (codeSystemName != null ? codeSystemName : STnull.NI);
				theCV._codeSystemVersion = (codeSystemVersion != null ? codeSystemVersion : STnull.NI);
				theCV._originalText = (originalText != null ? originalText : STnull.NI);
				return theCV;
			} catch (final CodeValueFactory.DelegateException x) {
				LOGGER.warning(x.getCause().getMessage() + ":" + x.getMessage());
				return CVnull.NI;
				// throw new RuntimeException(x.getCause());
			} catch (final CodeValueFactory.Exception x) {
				throw new RuntimeException(x);
			}
		}
	}

	@Override
    public BL equal(final ANY that) {
		if (this == that) {
			return BLimpl.TRUE;
		} else if (that.isNull().isTrue()) {
			return BLimpl.FALSE; // FIXME: false or NI or UNK?
		} else if (that instanceof CV) {
			final CV thatCV = (CV) that;
			return this.codeSystem().equal(thatCV.codeSystem()).and(this.code().equal(thatCV.code()));
		} else {
			return BLimpl.FALSE;
		}
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
            throw new UnsupportedOperationException("cannot imply anything of " + this);
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

	/** HashCode to allow using CVs in hash maps and sets. */
	@Override
    public int hashCode() {
		// notice how this relates with the definition of equal
		return (codeSystem().hashCode() << 16) + code().hashCode();
	}

	@Override
    public String toString() {
		return code().toString() + "@" + codeSystem().toString();
	}
}

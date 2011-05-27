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

import org.hl7.types.CV;
import org.hl7.types.ED;
import org.hl7.types.ST;
import org.hl7.types.UID;

public final class CVExceptionalImpl extends CVnull implements CV {
	private final UID _codeSystem = UIDnull.NI;
	protected ST _codeSystemName = STnull.NI; // don't need to store that
	protected ST _codeSystemVersion = STnull.NI;
	protected ST _originalText = STnull.NI;

	protected CVExceptionalImpl(final ST originalText) {
		super(NullFlavorImpl.OTH);
/*		if (originalText == null || originalText.isNull().isTrue())
			throw new IllegalArgumentException("invalid code, no original text, can't make even an exceptional value.");
		else
*/
		this._originalText = originalText;
	}

	public static CV valueOf(final UID codeSystem, final ST originalText, final ST codeSystemName, final ST codeSystemVersion) {
		// Passing the codeSystem argument to the one-ST constructor causes it to be put into _originalText field.
		// That seems wrong, but the _originalText field is then assigned again several lines below,
		// so the codeSystem argument is not used at all.
		// It seems like either the codeSystem argument should be removed,
		// or the _codeSystem field should not be final so that we could actually use the argument.
		final CVExceptionalImpl theCV = new CVExceptionalImpl(codeSystem);
		theCV._codeSystemName = (codeSystemName != null ? codeSystemName : STnull.NI);
		theCV._codeSystemVersion = (codeSystemVersion != null ? codeSystemVersion : STnull.NI);
		theCV._originalText = (originalText != null ? originalText : STnull.NI);
		return theCV;
	}

	public static CV valueOf(final ST originalText) {
		return new CVExceptionalImpl(originalText);
	}

	@Override
    public UID codeSystem() {
		return this._codeSystem;
	}

	@Override
    public ST codeSystemName() {
		return this._codeSystemName;
	}

	@Override
    public ST codeSystemVersion() {
		return this._codeSystemVersion;
	}

	@Override
    public ED originalText() {
		return this._originalText;
	}

	/** HashCode to allow using CVs in hash maps and sets. */
	@Override
    public int hashCode() {
		// notice how this relates with the definition of equal
		return (codeSystem().hashCode() << 16) + originalText().hashCode();
	}

	@Override
    public String toString() {
		return originalText().toString() + "@" + codeSystem().toString();
	}
}

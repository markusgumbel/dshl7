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

import org.hl7.types.CD;
import org.hl7.types.ED;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

public final class CDExceptionalImpl extends CDnull implements CD {
	private UID _codeSystem = UIDnull.NI;
	protected ST _codeSystemName = STnull.NI; // don't need to store that
	protected ST _codeSystemVersion = STnull.NI;
	protected ED _originalText = STnull.NI;
	protected SET<CD> _translation = SETnull.NI;

	protected CDExceptionalImpl(final ED originalText) {
		super(NullFlavorImpl.OTH);
/*		if (originalText == null || originalText.isNull().isTrue())
			throw new IllegalArgumentException("invalid code, no original text, can't make even an exceptional value.");
		else
*/
		this._originalText = originalText != null ? originalText : STnull.NI;
	}

	public static CD valueOf(final UID codeSystem, final ED originalText, final ST codeSystemName, final ST codeSystemVersion) {
		final CDExceptionalImpl theCD = new CDExceptionalImpl(originalText);
		theCD._codeSystem = (codeSystem != null ? codeSystem : UIDnull.NI);
		theCD._codeSystemName = (codeSystemName != null ? codeSystemName : STnull.NI);
		theCD._codeSystemVersion = (codeSystemVersion != null ? codeSystemVersion : STnull.NI);
		return theCD;
	}

	public static CD valueOf(final ED originalText) {
		return new CDExceptionalImpl(originalText);
	}

	public static CD valueOf(final SET<CD> translation) {
		final CDExceptionalImpl theCD =  new CDExceptionalImpl(null);
		theCD._translation = (translation != null ? translation : SETnull.NI);
		return theCD;
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

	@Override
	public SET<CD> translation() {
		return this._translation;
	}

	/** HashCode to allow using CDs in hash maps and sets. */
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

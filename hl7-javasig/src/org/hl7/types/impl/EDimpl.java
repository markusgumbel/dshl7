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
import org.hl7.types.TEL;

public final class EDimpl extends ANYimpl implements ED {
	private EDimpl() {
		super(null);
	}

	/** FIXME: is NA correct or should it be derived from this and that? */
	@Override
    public BL equal(final ANY that) {
		return BLimpl.NA;
	}

	public BL head() {
		throw new UnsupportedOperationException();
	}

	public LIST<BL> tail() {
		throw new UnsupportedOperationException();
	}

	public Iterator<BL> iterator() {
		throw new UnsupportedOperationException();
	}

	public BL isEmpty() {
		return BLimpl.NA;
	}

	public BL nonEmpty() {
		return BLimpl.NA;
	}

	public INT length() {
		return INTnull.NA;
	}

	// The ED interface
	public CS mediaType() {
		return CSnull.NA;
	}

	public CS charset() {
		return CSnull.NA;
	}

	public CS compression() {
		return CSnull.NA;
	}

	public CS language() {
		return CSnull.NA;
	}

	public TEL reference() {
		return TELnull.NA;
	}

	public BIN integrityCheck() {
		return BINnull.NA;
	}

	public CS integrityCheckAlgorithm() {
		return CSnull.NA;
	}

	public ED thumbnail() {
		return EDnull.NA;
	}
};

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

/**
 * User: Eric Chen Date: Jan 30, 2004 Time: 4:34:45 PM To change this template use Options | File Templates.
 */
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CE;
import org.hl7.types.SC;

public class SCimpl extends STjlStringAdapter implements SC {
	private final CE _code;

	private SCimpl(final String data, final CE code) {
		super(data);
		this._code = (code == null) ? CEnull.NI : code;
	}

	private SCimpl(final String data, final String language, final CE code) {
		super(data, CSimpl.valueOf(language, "languagePart"));
		this._code = (code == null) ? CEnull.NI : code;
	}

	public static SC valueOf(final String data) {
		return (data == null || data.length() == 0) ? SCnull.NI : (SC) new SCimpl(data, null);
	}

	public static SC valueOf(final String data, final CE code) {
		return (data == null) ? (SC) SCnull.NI : (SC) new SCimpl(data.trim(), code);
	}

	public static SC valueOf(final String data, final String language, final CE code) {
		return (data == null) ? (SC) SCnull.NI : (SC) new SCimpl(data.trim(), language, code);
	}

	@Override
    public final BL equal(final ANY x) {
		if (!(x instanceof SC)) {
            return BLimpl.FALSE;
        }
		final SCimpl that = (SCimpl) x;
		return (super.equal(that).and(_code.equal(that.code())));
	}

	public CE code() {
		return _code;
	}
}

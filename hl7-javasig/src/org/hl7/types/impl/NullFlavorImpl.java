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
import org.hl7.types.CR;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

/**
 * Because there is only a handful of null flavors, we want each null flavor to be a single constant object.
 * 
 * The null flavor enumeration encodes the specialization heirarchy of null flavors in integer numbers using the
 * bitfield method. This is the fastest way to resolve the implies question, and fast we want to be here. However, we do
 * not in any way publish those numbers, that's how we keep this safe for future extensions that may require
 * renumbering.
 * 
 * Remember that not present is not a real null flavor and should never ever be visible to an application. That's why we
 * better don't even include it here even though it might be convenient for the interface routines.
 */
public final class NullFlavorImpl extends ANYimpl implements NullFlavor {
	private static final short bNI = 1 << 0;
	private static final short bNA = 1 << 1;
	private static final short bUNK = 1 << 2;
	private static final short bNASK = 1 << 3;
	private static final short bASKU = 1 << 4;
	private static final short bNAV = 1 << 5;
	private static final short bOTH = 1 << 6;
	private static final short bPINF = 1 << 7;
	private static final short bNINF = 1 << 8;
	private static final short bMSK = 1 << 9;
	private static final short bNP = 1 << 10;
	private static final short mNOT_A_NULL_FLAVOR = 0;
	private static final short mNINF = bNINF;
	private static final short mPINF = bPINF;
	private static final short mOTH = bOTH | mPINF | mNINF;
	private static final short mNAV = bNAV;
	private static final short mASKU = bASKU | mNAV;
	private static final short mNASK = bNASK;
	private static final short mUNK = bUNK | mNASK | mASKU;
	private static final short mNA = bNA;
	private static final short mMSK = bMSK;
	private static final short mNI = bNI | mNA | mUNK | mOTH | mMSK;
	private static final short mNP = bNP;
	public static final NullFlavorImpl NI = new NullFlavorImpl(mNI);
	public static final NullFlavorImpl NA = new NullFlavorImpl(mNA);
	public static final NullFlavorImpl UNK = new NullFlavorImpl(mUNK);
	public static final NullFlavorImpl NASK = new NullFlavorImpl(mNASK);
	public static final NullFlavorImpl ASKU = new NullFlavorImpl(mASKU);
	public static final NullFlavorImpl NAV = new NullFlavorImpl(mNAV);
	public static final NullFlavorImpl OTH = new NullFlavorImpl(mOTH);
	public static final NullFlavorImpl PINF = new NullFlavorImpl(mPINF);
	public static final NullFlavorImpl NINF = new NullFlavorImpl(mNINF);
	public static final NullFlavorImpl MSK = new NullFlavorImpl(mMSK);
	public static final NullFlavorImpl NP = new NullFlavorImpl(mNP);
	/**
	 * This is the special NullFlavor that is itself null, that means it is NOT an actual null flavor. Sound confusing?
	 * Think of it this way: if you ask me what the null-flavor of this value is, and if it is not a null value, the
	 * answer would be NOT_A_NULL_FLAVOR, i.e., that the flavor is not applicable.
	 */
	public static final NullFlavorImpl NOT_A_NULL_FLAVOR = new NullFlavorImpl(mNOT_A_NULL_FLAVOR);
	public static final String sNI = "NI";
	public static final String sNA = "NA";
	public static final String sUNK = "UNK";
	public static final String sNASK = "NASK";
	public static final String sASKU = "ASKU";
	public static final String sNAV = "NAV";
	public static final String sOTH = "OTH";
	public static final String sPINF = "PINF";
	public static final String sNINF = "NINF";
	public static final String sMSK = "MSK";
	public static final String sNP = "NP";
	short _mask;

	/**
	 * Jere (?) changed this from private to package protected noting: "originally private, changed to package protected
	 * for testing purposes ..."
	 * 
	 * GS changed it back to private. I don't get it. Why would we test whether the screwy null flavors would work when we
	 * make sure by using a private constructor that such screwy null flavors don't exist?
	 */
	private NullFlavorImpl(final short m) {
		super(m == 0 ? NullFlavorImpl.NA : NullFlavorImpl.NOT_A_NULL_FLAVOR);
		this._mask = m;
	}

	public static NullFlavor valueOf(final ST code) {
		return valueOf(code.toString());
	}

	/* package */static NullFlavor valueOf(String codeString) {
		codeString = codeString.intern();
		if (codeString == sNI) {
			return NI;
		}
		if (codeString == sNA) {
			return NA;
		}
		if (codeString == sUNK) {
			return UNK;
		}
		if (codeString == sNASK) {
			return NASK;
		}
		if (codeString == sASKU) {
			return ASKU;
		}
		if (codeString == sNAV) {
			return NAV;
		}
		if (codeString == sOTH) {
			return OTH;
		}
		if (codeString == sPINF) {
			return PINF;
		}
		if (codeString == sNINF) {
			return NINF;
		}
		if (codeString == sMSK) {
			return MSK;
		}
		if (codeString == sNP) {
			return NP;
		} else {
			return NOT_A_NULL_FLAVOR;
		}
	}

	// The CD interface
	public ST code() {
		String codeString = null;
		switch (_mask) {
		case mNOT_A_NULL_FLAVOR:
			return STnull.NA;
		case mNI:
			codeString = sNI;
			break;
		case mNA:
			codeString = sNA;
			break;
		case mUNK:
			codeString = sUNK;
			break;
		case mNASK:
			codeString = sNASK;
			break;
		case mASKU:
			codeString = sASKU;
			break;
		case mNAV:
			codeString = sNAV;
			break;
		case mOTH:
			codeString = sOTH;
			break;
		case mPINF:
			codeString = sPINF;
			break;
		case mNINF:
			codeString = sNINF;
			break;
		case mMSK:
			codeString = sMSK;
			break;
		case mNP:
			codeString = sNP;
			break;
		}
		return STjlStringAdapter.valueOf(codeString);
	}

	public ST displayName() {
		String nameString = null;
		switch (_mask) {
		case mNOT_A_NULL_FLAVOR:
			nameString = "not a null flavor";
			break;
		case mNI:
			nameString = "no information";
			break;
		case mNA:
			nameString = "not applicable";
			break;
		case mUNK:
			nameString = "unknown";
			break;
		case mNASK:
			nameString = "not asker";
			break;
		case mASKU:
			nameString = "asked but unknown";
			break;
		case mNAV:
			nameString = "not available at this time";
			break;
		case mOTH:
			nameString = "other";
			break;
		case mPINF:
			nameString = "positive infinity";
			break;
		case mNINF:
			nameString = "negative infinity";
			break;
		case mMSK:
			nameString = "masked";
			break;
		case mNP:
			nameString = "not present";
			break;
		}
		return STjlStringAdapter.valueOf(nameString);
	}

	private static UID CODE_SYSTEM = null;

	public UID codeSystem() {
		if (CODE_SYSTEM == null) {
			CODE_SYSTEM = UIDimpl.valueOf("2.16.840.1.113883.5.1008");
		}
		return CODE_SYSTEM;
	}

	private static ST CODE_SYSTEM_NAME = null;

	public ST codeSystemName() {
		if (CODE_SYSTEM_NAME == null) {
			CODE_SYSTEM_NAME = STjlStringAdapter.valueOf("null flavor");
		}
		return CODE_SYSTEM_NAME;
	}

	public ST codeSystemVersion() {
		return STnull.NI;
	}

	public ST originalText() {
		return STnull.NI;
	}

	public SET<CD> translation() {
		return SETnull.NA;
	}

	public LIST<CR> qualifier() {
		return LISTnull.NA;
	}

	@Override
    public final BL equal(final ANY that) {
		if (that instanceof NullFlavorImpl) {
			return BLimpl.valueOf(this._mask != 0 && this._mask == ((NullFlavorImpl) that)._mask);
		} else if (!(that instanceof CD)) {
			return BLimpl.FALSE;
		} else {
			// FIXME: need to work with value sets here!
			throw new UnsupportedOperationException("unimplemented critical feature!");
		}
	}

	public final BL implies(final CD that) {
		if (that instanceof NullFlavorImpl) {
			return BLimpl.valueOf(this._mask != 0 && (this._mask & ((NullFlavorImpl) that)._mask) == this._mask);
		} else {
			// FIXME: need to work with value sets here!
			throw new UnsupportedOperationException("unimplemented critical feature!");
		}
	}

	/**
	 * Returns the most specific common generalization of two null flavors.
	 */
	public final NullFlavorImpl mostSpecificGeneralization(final CD that) {
		if (that instanceof NullFlavor) {
			return mostSpecificGeneralization((NullFlavor) that);
		} else // FIXME: need to work with value sets here!
		{
			throw new UnsupportedOperationException("unimplemented critical feature!");
		}
	}

	public final NullFlavorImpl mostSpecificGeneralization(final NullFlavor that) {
		if (that instanceof NullFlavor) {
			// IMPLEMENTATION NOTE: this needs to be updated carefully when
			// the null-flavor hierarchy is modified. The idea here is to
			// first check for equality, that catches all leaf flavors.
			// Then we simply check whether there is any intermediary flavor
			// that catches all of the union of both flavors. We have to
			// work from most specific intermediarys up.
			if (this._mask == 0) {
				return NOT_A_NULL_FLAVOR;
			}
			if (this._mask == ((NullFlavorImpl) that)._mask) {
				return this;
			}
			final short union = (short) (this._mask | ((NullFlavorImpl) that)._mask);
			if ((union & ~mUNK) == 0) {
				return UNK;
			}
			if ((union & ~mASKU) == 0) {
				return ASKU;
			}
			if ((union & ~mOTH) == 0) {
				return OTH;
			}
			return NI;
		} else if (!(that instanceof CD)) {
			return NullFlavorImpl.NA;
		} else // FIXME: need to work with value sets here!
		{
			throw new UnsupportedOperationException("unimplemented critical feature!");
		}
	}

	// FIXME:
	// equals and impliesJ are ideosyncratic, if they become public
	// in ANY (or so) this needs to be fixed:
	public final boolean equals(final NullFlavor that) {
		if (that instanceof NullFlavorImpl) {
			final NullFlavorImpl thatNFI = (NullFlavorImpl) that;
			return this._mask != 0 && this._mask == thatNFI._mask;
		}
		throw new UnsupportedOperationException("unimplemented critical feature!");
	}

	public final boolean impliesJ(final NullFlavor that) {
		if (that instanceof NullFlavorImpl) {
			final NullFlavorImpl thatNFI = (NullFlavorImpl) that;
			return this._mask != 0 && (this._mask & thatNFI._mask) == this._mask;
		}
		throw new UnsupportedOperationException("unimplemented critical feature!");
	}

	// equals() method has been overriden in ANYimpl class
	/**
	 * override NULL flavor hashCode() method.
	 * 
	 * @return hashCode
	 */
	@Override
    public int hashCode() {
		return _mask;
	}
}

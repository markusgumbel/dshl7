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

import java.util.EnumSet;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.QSET;
import org.hl7.types.ST;
import org.hl7.types.TEL;
import org.hl7.types.TS;
import org.hl7.types.enums.TelecommunicationAddressUse;

public final class TELimpl extends URLjnURLAdapter implements TEL {
	private final QSET<TS> _useablePeriod;
	private final DSET<? extends CS> _use;

	/** @deprecated */
	@Deprecated
	private TELimpl(final QSET<TS> useablePeriod, final DSET<? extends CS> use, final String data) {
		super(data);
		// specifically don't require that useablePeriod or use != null
		// because it's checking this on the way out
		this._useablePeriod = useablePeriod;
		this._use = use;
	}

	public static TEL valueOf(final CS scheme, final ST address, final DSET<? extends CS> use, final QSET<TS> useablePeriod) {	
		if(scheme == null || scheme.isNull().isTrue() || address == null || address.isNull().isTrue()) {
            return TELnull.NI;
        } else {
            return new TELimpl(useablePeriod, use, scheme.toString() + ":" + address.toString());
        }
	}

	public static TEL valueOf(final String address, final String useType, final QSET<TS> timeSet) {
		if(address == null) {
			return TELnull.NI;
		}
		final EnumSet<TelecommunicationAddressUse> telecomUseSet = EnumSet.noneOf(TelecommunicationAddressUse.class);
		DSET<? extends CS> useCSSET = null;
		if (useType != null) {
			final StringTokenizer useTypeTokens = new StringTokenizer(useType);
			while (useTypeTokens.hasMoreTokens()) {
				final TelecommunicationAddressUse telecomUse = TelecommunicationAddressUse.valueOf(STjlStringAdapter
						.valueOf(useTypeTokens.nextToken()));
				telecomUseSet.add(telecomUse);
			}
			useCSSET = SETjuSetAdapter.valueOf(telecomUseSet);
		}
		try {
			return new TELimpl(timeSet, useCSSET, address);
		} catch(final IllegalArgumentException ex) {
			// FIXME: URL handler does not know how work with "relative" URLs.
			return TELnull.NI;
		}
	}

	public String spaceDelimitedListOfUseCode() {
		final StringBuffer spaceDelimitedListofUses = new StringBuffer();
		if(_use != null) {
		  final Iterator<? extends CS> itCS = _use.iterator();
		  while (itCS.hasNext()) {
		    final CS useEntry = itCS.next();
		    spaceDelimitedListofUses.append(useEntry.toString()).append(' ');
		  }
		}
		return spaceDelimitedListofUses.toString();
	}

	// and now for a simple one to use for our message
	// which doesn't specify "useable period" or "use", or even "schema"
	// we must make parameters to feed our private constructor
	// even if they are empty of real info
	@SuppressWarnings("unchecked")
	public static TELimpl valueOf(final String data) {
		final QSET<TS> tsSN = QSETnull.NI;
		final DSET<CS> csSN = (DSET<CS>) DSETnull.NI;
		return new TELimpl(tsSN, csSN, data);
	} // the simple valueOF method

	/*
	 * marked for deletion, no longer needed
	 * 
	 * public static TELimpl valueOf(String use, String useCodesystem, String data){ QSET<TS> tsSN = QSETnull.NI;
	 * 
	 * CS useCS = CSimpl.valueOf(use, useCodesystem); HashSet uSet = new HashSet(); uSet.add(useCS); DSET<CS> useCSSET =
	 * SETjuSetAdapter.valueOf(uSet);
	 * 
	 * return new TELimpl(tsSN, useCSSET, data); }
	 */
        @SuppressWarnings("unchecked")
	public QSET<TS> useablePeriod() {
		if (this._useablePeriod != null) {
			return this._useablePeriod;
		} else {
			return (QSET<TS>) QSETnull.NI;
		} // if else
	}

        @SuppressWarnings("unchecked")
	public DSET<? extends CS> use() {
		if (this._use != null) {
			return this._use;
		} else {
			return (DSET<? extends CS>) DSETnull.NI;
		}
	}
	// we don't have to do equal() we only care if the address is equal, and
	// that is already taken care of by the super.equal() which we inherit
}// class

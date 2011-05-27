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
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.Criterion;
import org.hl7.types.EIVL;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.PQ;
import org.hl7.types.SET;
import org.hl7.types.TS;

/**
 * @author Gunther Schadow
 */
public class EIVLimpl extends ANYimpl implements EIVL {
	CS _event;
	IVL<PQ> _offset;

	private EIVLimpl(final CS event, final IVL<PQ> offset) {
		super(null);
		_event = event;
		_offset = offset;
	}

	public static EIVL valueOf(final CS event, final IVL<PQ> offset) {
		return new EIVLimpl(event, offset);
	}

	@Override
    public BL equal(final ANY that) {
		if (that instanceof EIVL) {
			final EIVL thatEIVL = (EIVL) that;
			return this.event().equal(thatEIVL.event()).and(this.offset().equal(thatEIVL.offset()));
		} else {
            return BLimpl.FALSE;
        }
	}

	public CS event() {
		return _event;
	}

	public IVL<PQ> offset() {
		return _offset;
	}

	public IVL<TS> occurrenceAt(final TS eventTime) {
		throw new UnsupportedOperationException();
	}

	/* SET<TS> interface */
	public INT cardinality() {
		throw new UnsupportedOperationException();
	}

	public BL contains(final TS element) {
		throw new UnsupportedOperationException();
	}

	public BL contains(final SET<TS> subset) {
		throw new UnsupportedOperationException();
	}

	public BL isEmpty() {
		throw new UnsupportedOperationException();
	}

	public BL nonEmpty() {
		return isEmpty().not();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hl7.types.SET#except(null)
	 */
	public SET<TS> except(final TS element) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hl7.types.SET#except(null)
	 */
	public SET<TS> except(final SET<TS> otherset) {
		throw new UnsupportedOperationException();
	}

	public SET<TS> intersection(final SET<TS> otherset) {
		throw new UnsupportedOperationException();
	}

	public SET<TS> union(final SET<TS> otherset) {
		throw new UnsupportedOperationException();
	}

	public Iterator<ANY> iterator() {
		throw new UnsupportedOperationException();
	}

	public TS any() {
		throw new UnsupportedOperationException();
	}

	public SET select(final Criterion c) {
		throw new UnsupportedOperationException();
	}
}

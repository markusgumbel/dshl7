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
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;

public class LISTconsImpl<T extends ANY> extends ANYimpl implements LIST<T> {
	T _car = null;
	LISTconsImpl<T> _cdr = null;

	// public static final LISTconsImpl<T> NIL = new LISTconsImpl(); // unchecked -- please ignore this warning!
	private LISTconsImpl() {
		super(NullFlavorImpl.NI);
	}

	private LISTconsImpl(final NullFlavor nf) {
		super(nf);
	}

	private LISTconsImpl(final T head, final LISTconsImpl<T> tail) {
		super(null);
		this._car = head;
		this._cdr = tail;
	}

	public T head() {
		return this._car;
	}

	public LIST<T> tail() {
		if (this._cdr != null) {
            return this._cdr;
        } else {
            return new LISTconsImpl<T>();
        }
	}

	public BL isEmpty() {
		return BLimpl.valueOf(this._car == null);
	}

	public BL nonEmpty() {
		return BLimpl.valueOf(this._car != null);
	}

	public INT length() {
		throw new UnsupportedOperationException();
	}

	public boolean nonEmptyJ() {
		return this._car != null;
	}

	public static <T extends ANY> LIST<T> cons(final T head, final LISTconsImpl<T> tail) {
		if (head == null || tail == null) {
            throw new NullPointerException();
        }
		return new LISTconsImpl<T>(head, tail);
	}

	public Iterator<T> iterator() {
		return new LISTconsImplIterator<T>(this);
	}

	@Override
    public BL equal(final ANY that) {
		if (that instanceof LIST) {
			final Iterator<T> thissi = this.iterator();
			final Iterator<T> thatti = ((LIST) that).iterator(); // unchecked assignment ignore this warning, nothing we can do
																											// about it
			while (thissi.hasNext() && thatti.hasNext()) {
				if (thissi.next().equal(thatti.next()).isTrue()) {
					return BLimpl.FALSE;
				}
			}
			if (thissi.hasNext() || thatti.hasNext()) {
                return BLimpl.FALSE;
            } else {
                return BLimpl.TRUE;
            }
		} else {
            return BLimpl.FALSE;
        }
	}
}

class LISTconsImplIterator<T extends ANY> implements Iterator<T> {
	LISTconsImpl<T> _nextNode;

	LISTconsImplIterator(final LISTconsImpl<T> nextNode) {
		this._nextNode = nextNode;
	}

	public boolean hasNext() {
		return this._nextNode.nonEmptyJ();
	}

	public T next() {
		final T element = this._nextNode.head();
		this._nextNode = this._nextNode._cdr;
		return element;
	}

	public void remove() {
		throw new UnsupportedOperationException("sorry, not implemented");
	}
}

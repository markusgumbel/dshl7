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
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.util.Base64;

public class BINbyteArrayImpl extends ANYimpl implements BIN {
	byte[] _bits;
	long _offset;
	long _length;

	protected BINbyteArrayImpl(final byte[] bits, final long offset, final long length) {
		super(null);
		if ((offset < 0) || (length < 0) || (bits.length <= (offset + length) / 8)) {
            throw new IllegalArgumentException();
        } else {
			this._bits = bits;
			this._offset = offset;
			this._length = length;
		}
	}

	public static BIN valueOf(final byte[] bits, final long offset, final long length) {
		return new BINbyteArrayImpl(bits, offset, length);
	}

	public static BIN valueOf(final String base64Encoded) {
		if (base64Encoded == null || "".equals(base64Encoded)) {
            return BINnull.NI;
        }
		final byte[] bytes = Base64.decode(base64Encoded);
		return valueOf(bytes, 0, bytes.length);
	}

	/**
	 * FIXME: I think endian-ness does matter here somehow, but don't worry about it right now. I assume that the whole
	 * bit stream is sorted least significant bit first:
	 * 
	 * 0 1 2 3.. LSB...MSB LSB...MSB LSB...MSB ...
	 */
	public BL head() {
		if (_length == 0) {
            return BLimpl.NA;
        } else {
			final int byteIndex = (int) (this._offset / 8);
			final byte bitMask = (byte) (0x01 << (this._offset % 8));
			return BLimpl.valueOf((this._bits[byteIndex] & bitMask) != 0);
		}
	}

	/**
	 * The tail function is dangerous as it wastes objects if used for tail recursion. We need an interator instead. So,
	 * what I'll do is if anybody asks for tail, I'll sneak in an interator that would then return head() as next() and
	 * tail() as this with an updated position counter.
	 * 
	 * FIXME: is that really safe, since an iterator is not immutable?
	 */
	public LIST<BL> tail() {
		if (this._length != 0) {
            return new InnerIterator(this._offset + 1, this._length - 1);
        } else {
            return LISTnull.NA;
        }
	}

	public BL isEmpty() {
		return BLimpl.valueOf(this._length == 0);
	}

	public BL nonEmpty() {
		return BLimpl.valueOf(this._length > 0);
	}

	public INT length() {
		return INTlongAdapter.valueOf(this._length);
	}

	public Iterator<BL> iterator() {
		return new InnerIterator(this._offset, this._length);
	}

	class InnerIterator extends ANYimpl implements Iterator<BL>, LIST<BL> {
		long _offset;
		long _length;

		InnerIterator(final long offset, final long length) {
			super(null);
			if ((offset < 0) || (length < 0) || (_bits.length <= (offset + length) / 8)) {
                throw new IllegalArgumentException();
            } else {
				this._offset = offset;
				this._length = length;
			}
		}

		public boolean hasNext() {
			return this._length > 0;
		}

		public BL next() {
			if (this._length > 0) {
				final int byteIndex = (int) (this._offset / 8);
				final byte bitMask = (byte) (0x01 << (this._offset % 8));
				this._offset += 1;
				this._length -= 1;
				return BLimpl.valueOf((_bits[byteIndex] & bitMask) != 0);
			} else {
                return BLimpl.NA;
            }
		}

		public void remove() {
			throw new UnsupportedOperationException("this method is intentionally left unimplemented");
		}

		public BL head() {
			if (this._length > 0) {
				final int byteIndex = (int) (this._offset / 8);
				final byte bitMask = (byte) (0x01 << (this._offset % 8));
				return BLimpl.valueOf((_bits[byteIndex] & bitMask) != 0);
			} else {
                return BLimpl.NA;
            }
		}

		public LIST<BL> tail() {
			this._offset += 1;
			this._length -= 1;
			if (this._length >= 0) {
                return this;
            } else {
                return LISTnull.NA;
            }
		}

		public BL isEmpty() {
			return BLimpl.valueOf(this._length <= 0);
		}

		public BL nonEmpty() {
			return BLimpl.valueOf(this._length > 0);
		}

		public INT length() {
			return INTlongAdapter.valueOf(this._length);
		}

		public Iterator<BL> iterator() {
			// here we better clone ourselves
			return new InnerIterator(this._offset, this._length);
		}

		@Override
        public BL equal(final ANY that) {
			throw new UnsupportedOperationException();
			// FIXME this is not functional
			// return BLimpl.valueOf(this==that);
		}
	}

	@Override
    public BL equal(final ANY that) {
		if (that instanceof BINbyteArrayImpl) {
			final BINbyteArrayImpl thatB = (BINbyteArrayImpl) that;
			if (this._length != thatB._length) {
                return BLimpl.FALSE;
            } else if (this._offset % 8 == thatB._offset % 8) {
				final int max = (int) this._length / 8;
				for (int i = (int) (this._offset / 8), j = (int) (thatB._offset / 8); i < max; // FIXME: tests too few bytes!
				i++, j++) {
					// FIXME: this is not exactly right as we will fail if
					// both lists have the same offset%8 > 0 and the initial
					// prefix in the byte are not the same ... other weaknesses
					// also exist.
					if (this._bits[i] != thatB._bits[i]) {
                        return BLimpl.FALSE;
                    }
				}
				return BLimpl.TRUE;
			} else {
                throw new UnsupportedOperationException();
            }
		} else {
            return BLimpl.FALSE;
        }
	}

	@Override
    public String toString() {
		return new Base64().encode(_bits);
	}
}

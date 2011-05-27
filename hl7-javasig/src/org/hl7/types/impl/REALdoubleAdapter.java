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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.ORD;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;

/**
 * REAL adapter to Java builtin double. This implementation attempts to deal faithfully with precision and with the
 * surprizing behavior of floating points. However, this does not always work. Be especially careful with equality
 * tests, as some errors introduced in the final fractional places may cause false negatives. One should generally test
 * lessOrEqual for ranges. Note that these issues are issues with the underlying floating point representation, not so
 * much with this implementation, although this implementation could be improved by some expert in floating point
 * arithmetic issues to munge over this for a couple of weeks.
 */
public final class REALdoubleAdapter extends ORDimpl implements REAL {
	private double _value;
	private INT _precision;
	public static INT DOUBLE_PRECISION = ValueFactory.getInstance().INTvalueOf(15); // To avoid "1.9999 Issue"
	public static INT FLOAT_PRECISION = ValueFactory.getInstance().INTvalueOf(7); // To avoid "1.9999 Issue"
	public static INT INFINITE_PRECISION = (INT) ValueFactory.getInstance().nullValueOf("INT", "PINF");
	/*
	 * Some flyweight's for common constants. Why not? Would be interesting to know how much they end up being used. If we
	 * have those and a byte-size wrapper, we can move them there. But I assume int is the smallest wrapper.
	 */
	public static REALdoubleAdapter MINUS_ONE = new REALdoubleAdapter(-1, INFINITE_PRECISION);
	public static REALdoubleAdapter ZERO = new REALdoubleAdapter(0, INFINITE_PRECISION);
	public static REALdoubleAdapter ONE_TENTH = new REALdoubleAdapter(0.1, INFINITE_PRECISION);
	public static REALdoubleAdapter HALF = new REALdoubleAdapter(0.5, INFINITE_PRECISION);
	public static REALdoubleAdapter ONE = new REALdoubleAdapter(1, INFINITE_PRECISION);
	public static REALdoubleAdapter TWO = new REALdoubleAdapter(2, INFINITE_PRECISION);
	public static REALdoubleAdapter TEN = new REALdoubleAdapter(10, INFINITE_PRECISION);
	private static final DecimalFormat NOPOINT_FORMAT = new DecimalFormat(".###############E0");

	private REALdoubleAdapter(final double value, final INT precision) {
		super(null);
		this._value = value;
		if (precision == null) {
            throw new NullPointerException("precision can't be null");
        }
		if (precision.isNull().and(precision.nullFlavor().implies(NullFlavorImpl.PINF).not()).isTrue()) {
            throw new IllegalArgumentException("precision can't be: " + precision);
        }
		if(precision.isZero().isTrue())
			throw new IllegalArgumentException("precision must not be zero");
		this._precision = precision;
		// System.err.println("REALLLLLLLL:" + this._precision);
	}

	/**
	 * We should probably have a small array of reuseable objects of this type such that we can avoid object creation. How
	 * do we know, though, whether someone's still holding on to those? I guess weak references are the thing to use ...
	 * later.
	 */
	public static REAL valueOf(final double x, final INT precision) {
		if (precision.equal(INFINITE_PRECISION).isTrue()) {
			if (x == 0) {
				return REALdoubleAdapter.ZERO;
			} else if (x == 1) {
				return REALdoubleAdapter.ONE;
			} else if (x == 2) {
				return REALdoubleAdapter.TWO;
			} else if (x == -1.0) {
				return REALdoubleAdapter.MINUS_ONE;
			} else if (x == 10) {
				return REALdoubleAdapter.TEN;
			} else if (x == 0.1) {
				return REALdoubleAdapter.ONE_TENTH;
			} else if (x == 0.5) {
				return REALdoubleAdapter.HALF;
			}
		}
		return new REALdoubleAdapter(x, precision);
	}

	public static REAL valueOf(final String str) {
		return valueOf(str, countSigFigs(str));
	}
	
	public static REAL valueOf(final String str, INT precision) {
		final double value = Double.parseDouble(str);
		// System.err.println("#######:" + precision + "\t" + str);
		if (precision.nonNull().and(DOUBLE_PRECISION.lessOrEqual(precision)).isTrue()) {
			precision = DOUBLE_PRECISION;
		}
		return valueOf(value, precision);
	}
	
	public REAL withLimitedPrecision(final INT precisionLimit) {
		return valueOf(_value, minimizePrecision(precisionLimit));
	}

	public static REAL valueOf(final int x) {
		return valueOf(x, INFINITE_PRECISION);
	}

	public static REAL valueOf(final long x) {
		return valueOf(x, INFINITE_PRECISION);
	}

	public static REAL valueOf(final float x) {
		return valueOf(x, FLOAT_PRECISION);
	}

	public static REAL valueOf(final double x) {
		return valueOf(x, DOUBLE_PRECISION);
	}

	public INT precision() {
		return _precision;
	}

	private static final long FPM_MASK = ~0x1L;

	/**
	 * Test for equality.
	 */
	@Override
    public BL equal(final ANY that) {
		if (that instanceof QTY) {
			if (that instanceof REAL) {
				if (that instanceof REALdoubleAdapter) {
					final double thatValue = ((REALdoubleAdapter) that)._value;
					final INT thatPrecision = ((REAL) that).precision();
					if (this.precision().isNull().isTrue() && thatPrecision.isNull().isTrue()) {
						return BLimpl
								.valueOf((Double.doubleToLongBits(_value) & FPM_MASK) == (Double.doubleToLongBits(thatValue) & FPM_MASK));
					} else if (this.precision().equal(thatPrecision).isFalse()) {
                        return BLimpl.FALSE;
                    } else { // XXX: this is a nifty and cool hack: we use the IEEE floating point format to round off extraneous
									// digits in comparison
						final int p = _precision.intValue() * 33219 / 10000;
						final long xbits = Double.doubleToLongBits(_value);
						final long ybits = Double.doubleToLongBits(thatValue);
						if (p > 0 && p < 52) {
							final long mask = ~((0x1L << (52 - p)) - 1);
							// System.out.println("p=" + p);
							final long maskedx = xbits & mask;
							final long maskedy = ybits & mask;
							// System.out.println("" + _value + " bits=" + Long.toHexString(xbits) + " mask=" + Long.toHexString(mask)
							// + " masked=" + Long.toHexString(maskedx) + " r=" + Double.longBitsToDouble(maskedx));
							// System.out.println("" + thatValue + " bits=" + Long.toHexString(ybits) + " mask=" +
							// Long.toHexString(mask) + " masked=" + Long.toHexString(maskedy) + " r=" +
							// Double.longBitsToDouble(maskedy));
							return BLimpl.valueOf(maskedx == maskedy);
						} else {
							return BLimpl
									.valueOf((Double.doubleToLongBits(_value) & FPM_MASK) == (Double.doubleToLongBits(thatValue) & FPM_MASK));
						}
					}
				} else {
					return this.minus((REAL) that).isZero().and(
							this.precision().equal(((REAL) that).precision()).or(
									this.precision().isNull().and(((REAL) that).precision().isNull())));
				}
			} else {
				return this.minus((QTY) that).isZero();
			}
		} else {
			return BLimpl.FALSE;
		}
	}

	public BL equal(final int x) {
		return BLimpl.valueOf(this._value == x);
	}

	public BL equal(final long x) {
		return BLimpl.valueOf((long) this._value == x);
	}

	public BL equal(final float x) {
		return BLimpl.valueOf((float) this._value == x);
	}

	public BL equal(final double x) {
		return BLimpl.valueOf(this._value == x);
	}

	public BL isOne() { // FIXME: what about epsilon? 0.999999999999999 or 1.000000000000002??
		return BLimpl.valueOf(this._value == 1);
	}

	public BL isZero() {
		// System.out.println("IZERO: " + this + ": " + (Double.longBitsToDouble(Double.doubleToLongBits(_value) &
		// 0x800fffffffffffffL)));
		// this is more difficult than we think, because we can't consider differences beyond the precision
		return BLimpl.valueOf(_value == 0);
	}

	public BL nonZero() {
		return isZero().not();
	}

	@Override
    public BL lessOrEqual(final ORD that) { // FIXME: what about epsilon??
		if (that instanceof REALpinf) { // FIXME: this is implementation dependent
			return BLimpl.FALSE;
		} else if (that instanceof REALninf) {
			return BLimpl.TRUE;
		} else if (that instanceof REALdoubleAdapter) {
			final REALdoubleAdapter xthat = (REALdoubleAdapter) that;
			return lessOrEqual(xthat.doubleValue());
		} else {
			throw new IllegalArgumentException();
		}
	}

	public BL lessOrEqual(final int that) {
		return BLimpl.valueOf(this._value <= that);
	}

	public BL lessOrEqual(final long that) {
		return BLimpl.valueOf((long) this._value <= that);
	}

	public BL lessOrEqual(final float that) {
		return BLimpl.valueOf((float) this._value <= that);
	}

	public BL lessOrEqual(final double that) {
		return BLimpl.valueOf(this._value <= that);
	}

	public BL compares(final long that) {
		return BLimpl.TRUE;
	}

	public QTY plus(final QTY.diff that) {
		if(that instanceof REAL)
			return plus((REAL)that);
		else
			throw new UnsupportedOperationException(that.getClass().getName() + ":" + that.toString());
	}

	public REAL plus(final REAL that) {
		return that.plus(this._value, _precision);
	}

	public REAL plus(final int thatValue, final INT thatPrecision) {
		return plus((double) thatValue, thatPrecision);
	}

	public REAL plus(final long thatValue, final INT thatPrecision) {
		return plus((double) thatValue, thatPrecision);
	}

	public REAL plus(final float thatValue, final INT thatPrecision) {
		return plus((double) thatValue, thatPrecision);
	}

	public REAL plus(final double thatValue, final INT thatPrecision) {
		final double resultValue = this._value + thatValue;
		return REALdoubleAdapter.valueOf(resultValue, precisionForMinimizedDecimals(resultValue, this._value, this._precision, thatValue, thatPrecision));
	}

	public REAL plus(final int thatValue) {
		return plus((double) thatValue, INFINITE_PRECISION);
	}

	public REAL plus(final long thatValue) {
		return plus((double) thatValue, INFINITE_PRECISION);
	}

	public REAL plus(final float thatValue) {
		return plus((double) thatValue, FLOAT_PRECISION);
	}

	public REAL plus(final double thatValue) {
		return plus(thatValue, DOUBLE_PRECISION);
	}

	public REAL minus(final REAL that) {
		return that.minusReverse(this._value, _precision);
	}

	public REAL minusReverse(final int thatValue, final INT thatPrecision) {
		return minusReverse((double) thatValue, thatPrecision);
	}

	public REAL minusReverse(final long thatValue, final INT thatPrecision) {
		return minusReverse((double) thatValue, thatPrecision);
	}

	public REAL minusReverse(final float thatValue, final INT thatPrecision) {
		return minusReverse((double) thatValue, thatPrecision);
	}

	public REAL minusReverse(final double thatValue, final INT thatPrecision) {
		final double resultValue = thatValue - this._value;
		return REALdoubleAdapter.valueOf(resultValue, precisionForMinimizedDecimals(resultValue, this._value,	this._precision, thatValue, thatPrecision));
	}

	public REAL minusReverse(final int thatValue) {
		return minusReverse((double) thatValue, INFINITE_PRECISION);
	}

	public REAL minusReverse(final long thatValue) {
		return minusReverse((double) thatValue, INFINITE_PRECISION);
	}

	public REAL minusReverse(final float thatValue) {
		return minusReverse((double) thatValue, FLOAT_PRECISION);
	}

	public REAL minusReverse(final double thatValue) {
		return minusReverse(thatValue, DOUBLE_PRECISION);
	}

	public QTY minus(final QTY.diff that) {
		if(that instanceof REAL)
			return minus((REAL)that);
		else
			throw new UnsupportedOperationException(that.getClass().getName() + ":" + that.toString());
	}

	public QTY.diff minus(final QTY that) {
		if(that instanceof REAL)
			return minus((REAL)that);
		else
			throw new UnsupportedOperationException(that.getClass().getName() + ":" + that.toString());
	}

	public REAL negated() {
		return REALdoubleAdapter.valueOf(-this._value, _precision);
	}

	public REAL inverted() {
		return REALdoubleAdapter.valueOf(1.0 / this._value, _precision);
	}

	public REAL times(final REAL that) {
		return that.times(this._value, _precision);
	}

	public REAL times(final int thatValue, final INT thatPrecision) {
		return times((double) thatValue, thatPrecision);
	}

	public REAL times(final long thatValue, final INT thatPrecision) {
		return times((double) thatValue, thatPrecision);
	}

	public REAL times(final float thatValue, final INT thatPrecision) {
		return times((double) thatValue, thatPrecision);
	}

	public REAL times(final double that, final INT precision) {
		return REALdoubleAdapter.valueOf(that * this._value, minimizePrecision(precision));
	}

	public REAL times(final int thatValue) {
		return times((double) thatValue, INFINITE_PRECISION);
	}

	public REAL times(final long thatValue) {
		return times((double) thatValue, INFINITE_PRECISION);
	}

	public REAL times(final float thatValue) {
		return times((double) thatValue, FLOAT_PRECISION);
	}

	public REAL times(final double thatValue) {
		return times(thatValue, DOUBLE_PRECISION);
	}

	public REAL dividedBy(final REAL that) {
		return that.dividedByReverse(this._value, _precision);
	}

	public REAL dividedByReverse(final int thatValue, final INT thatPrecision) {
		return dividedByReverse((double) thatValue, thatPrecision);
	}

	public REAL dividedByReverse(final long thatValue, final INT thatPrecision) {
		return dividedByReverse((double) thatValue, thatPrecision);
	}

	public REAL dividedByReverse(final float thatValue, final INT thatPrecision) {
		return dividedByReverse((double) thatValue, thatPrecision);
	}

	public REAL dividedByReverse(final double thatValue, final INT thatPrecision) {
		return REALdoubleAdapter.valueOf(thatValue / this._value, minimizePrecision(thatPrecision));
	}

	public REAL dividedByReverse(final int thatValue) {
		return dividedByReverse((double) thatValue, INFINITE_PRECISION);
	}

	public REAL dividedByReverse(final long thatValue) {
		return dividedByReverse((double) thatValue, INFINITE_PRECISION);
	}

	public REAL dividedByReverse(final float thatValue) {
		return dividedByReverse((double) thatValue, FLOAT_PRECISION);
	}

	public REAL dividedByReverse(final double thatValue) {
		return dividedByReverse(thatValue, DOUBLE_PRECISION);
	}

	public REAL power(final REAL that) {
		return that.powerReverse(this._value, _precision);
	}

	public REAL powerReverse(final int that, final INT precision) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), minimizePrecision(precision));
	}

	public REAL powerReverse(final long that, final INT precision) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), minimizePrecision(precision));
	}

	public REAL powerReverse(final float that, final INT precision) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), minimizePrecision(precision));
	}

	public REAL powerReverse(final double that, final INT precision) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), minimizePrecision(precision));
	}

	public REAL powerReverse(final int that) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), _precision);
	}

	public REAL powerReverse(final long that) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), _precision);
	}

	public REAL powerReverse(final float that) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), minimizePrecision(FLOAT_PRECISION));
	}

	public REAL powerReverse(final double that) {
		return REALdoubleAdapter.valueOf(Math.pow(that, this._value), minimizePrecision(DOUBLE_PRECISION));
	}

	/**
	 * Natural logarithm.
	 */
	public REAL log() {
		return REALdoubleAdapter.valueOf(Math.log(this._value), _precision);
	}

	/**
	 * Power of e. FIXME: needs to check for overflow.
	 */
	public REAL exp() {
		return REALdoubleAdapter.valueOf(Math.exp(this._value), _precision);
	}

	public REAL floor() {
		return REALdoubleAdapter.valueOf(Math.floor(this._value), _precision);
	}

	/** @return the number in decimal form. */
	@Override
    public String toString() {
		return toString(_precision);
	}

	/** @return the number in decimal form with no more than given precision. */
	public String toString(INT precision) {
		return formatDouble(_value, minimizePrecision(precision), isZero().isTrue());
	}

	public static final String formatDouble(double value, INT precision) {
		return formatDouble(value, precision, false);
	}

	public static final String formatDouble(double value, INT precision, boolean isZero) {
		if(isZero) {
			final StringBuilder result = new StringBuilder("0");
			if (precision.lessOrEqual(DOUBLE_PRECISION).isTrue()) {
				result.append(new java.text.DecimalFormatSymbols().getDecimalSeparator());
				for (int i = precision.intValue(); i > 1; i--) {
                    result.append('0');
                }
			}
			return result.toString();
		}

		int exponent = 0;
		int mantissaSign = 1;
		int nfigs = 0;
		StringBuilder result = new StringBuilder();
		final String s = NOPOINT_FORMAT.format(value); // get the digits .##############

		/* parse the string */
		{
			final int slen = s.length();
			int i = 0;
			boolean zeroMantissa = false;
			int leadingZerosExponentCorrection = 0;
			char c;
			if (i < slen && (c = s.charAt(i)) == '-') {
				mantissaSign = -1;
				i++;
			}
			if (i < slen && (c = s.charAt(i++)) != '.') {
                // TODO (GUM) Seems to be an error. If not
                // commented out we cannot build the project.
				//throw new Error("Internal error. Thought that " + s + " must have a . at " + (i - 1) + ", was: " + c + ". Please report with stackdump.");
			}
			// System.err.printf("#1# s=%s ms=%d rs=%s e=%d nf=%d p=%s\n", s, mantissaSign, result.toString(), exponent,	nfigs, precision.toString());
			while (i < slen && s.charAt(i) == '0') { // consume any leading zeroes, just in case
				i++;
				leadingZerosExponentCorrection--;
				zeroMantissa = true;
			}
			// System.err.printf("#2# s=%s ms=%d rs=%s e=%d nf=%d p=%s\n", s, mantissaSign, result.toString(), leadingZerosExponentCorrection, nfigs, precision.toString());
			while (i < slen && (c = s.charAt(i)) != 'e' && c != 'E') {
				result.append(c);
				nfigs++;
				i++;
				zeroMantissa = false;
			}
			if (zeroMantissa) { // means exponent is zero also
				result.append('0');
				nfigs = 1;
			} else if (i < slen && ((c = s.charAt(i)) == 'e' || c == 'E')) {
				exponent += Integer.parseInt(s.substring(++i)) + leadingZerosExponentCorrection - nfigs;
			}
		}

		// System.err.printf("#3# s=%s ms=%d rs=%s e=%d nf=%d p=%s\n", s, mantissaSign, result.toString(), exponent, nfigs, precision.toString());
		// Now we have to set the decimal point. Cases
		// 
		// 12345 e0 precision 3 -> 123.e2 exp 5-3 = 2 = nfigs - precision
		// 12345 e0 precision 8 -> 12345000.e-3 zeroes 8-5 = 3 = precision - nfigs
		// 12345 e2 precision 8 -> 12345000.e0 point at the end
		// if we remove the point, that's the notation for "exact" numbers.
		if (!precision.greaterThan(DOUBLE_PRECISION).isFalse()) {
			while (exponent > 0) {
				result.append('0');
				--exponent;
			}
			if (exponent != 0) {
				result.append('e');
				result.append(exponent);
			}
		} else {
			final int prec = precision.intValue();
			// now we have to set the decimal point
			// 12345 e0 precision 8 -> 12345.000
			// 12345 e2 precision 8 -> 1234500.0
			// 12345 e2 precision 3 -> 123.e4
			// 12345 e-2 precision 8 -> 123.45000
			// 12345 e-2 precision 3 -> 123.
			// 12345 e-8 precision 8 -> 1.2345000e-4
			// 12345 e-8 precision 5 -> 1.2345e-3
			// the goal would is to reduce use of E as much as possible
			// minimize abs(exponent) by moving the point in that string.
			int zeroesNeeded = prec - nfigs;
			// System.err.printf("#4# s=%s ms=%d rs=%s e=%d nf=%d p=%s zn=%d v=%f p=%s\n", s, mantissaSign, result.toString(), exponent, nfigs, precision.toString(), zeroesNeeded, _value, _precision.toString());
			if (zeroesNeeded < 0) { // clip off digits, round, and adjust exponent
				long intpart = Long.parseLong(result.substring(0, prec));
				final char rounddigit = result.charAt(prec);
				if (rounddigit > '5' || (rounddigit == '5' && intpart % 2 == 0)) {
                    intpart++;
                }
				final String intpartString = Long.toString(intpart);
				if (intpartString.length() > prec) {
                    exponent++;
                }
				result.replace(0, prec, intpartString);
				result.setLength(prec);
				nfigs = prec;
				exponent += -zeroesNeeded;
				zeroesNeeded = 0;
			}
			int pointPosition = nfigs + exponent;
			// System.err.printf("#5# s=%s ms=%d rs=%s e=%d nf=%d p=%s zn=%d pp=%d\n", s, mantissaSign, result.toString(), exponent, nfigs, precision.toString(), zeroesNeeded, pointPosition);
			if (-3 <= pointPosition && pointPosition <= 0) { // .0001234
				final StringBuilder newResult = new StringBuilder("0.");
				for (int i = 0; i > pointPosition; i--) {
					newResult.append('0');
				}
				newResult.append(result);
				result = newResult;
				exponent = exponent + nfigs - pointPosition;
			} else if (pointPosition <= -3) { // .000+1234 -> 1.234e-1
				pointPosition = 1;
				result.insert(pointPosition, '.');
				exponent = exponent + nfigs - pointPosition;
			} else if (pointPosition < nfigs) {
				result.insert(pointPosition, '.');
				exponent = exponent + nfigs - pointPosition;
			} else {
				while (zeroesNeeded > 0 && exponent > 0) {
					result.append('0');
					zeroesNeeded--;
					exponent--;
				}
				result.append('.');
			}
			while (zeroesNeeded > 0) {
				result.append('0');
				zeroesNeeded--;
			}
			if (exponent != 0) {
				result.append('e');
				result.append(exponent);
			}
		}

		if (mantissaSign < 0) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
	}

	// FIXME: overflow!
	public int intValue() {
		return (int) Math.round(this._value);
	}

	public long longValue() {
		return Math.round(this._value);
	}

	public float floatValue() {
		return (float) this._value;
	}

	public double doubleValue() {
		return this._value;
	}

	private static INT countSigFigs(final String val) {
		final int len = val.length();
		int sigfigs = 0;
		int zeros = 0;
		int sigzeros = 0;
		boolean hasPoint = false;
		loop: for (int i = 0; i < len; i++) {
			switch (val.charAt(i)) {
			case '1': // FALLTHROUGH
			case '2': // FALLTHROUGH
			case '3': // FALLTHROUGH
			case '4': // FALLTHROUGH
			case '5': // FALLTHROUGH
			case '6': // FALLTHROUGH
			case '7': // FALLTHROUGH
			case '8': // FALLTHROUGH
			case '9': // FALLTHROUGH
				sigfigs++;
				break;
			case '0':
				if (sigfigs > 0) {
                    sigfigs++;
                } else if (sigzeros > 0) {
                    sigzeros++;
                } else {
                    zeros++;
                }
				break;
			case '.':
				hasPoint = true;
				if (sigfigs == 0 && zeros > 0) {
                    sigzeros = 1;
                }
				break;
			case 'E': // FALLTHROUGH
			case 'e':
				break loop;
			}
		}

		if (sigfigs == 0)
			sigfigs = sigzeros;
		if (hasPoint)
			return ValueFactory.getInstance().INTvalueOf(sigfigs);
		else
			return INFINITE_PRECISION;
	}

	private INT minimizePrecision(final INT thatPrecision) {
		return minimizePrecision(_precision, thatPrecision);
	}

	private final static INT minimizePrecision(final INT thisPrecision, final INT thatPrecision) {
		if (!thisPrecision.lessOrEqual(thatPrecision).isFalse()) {
			return thisPrecision;
		} else {
			return thatPrecision;
		}
	}

	private final static INT precisionForMinimizedDecimals(final double r, final double v1, final INT p1, final double v2, final INT p2) {
		if (v1 == 0.0 || p1.nullFlavor().implies(NullFlavorImpl.PINF).isTrue())
			return p2;
		else if (v2 == 0.0 || p2.nullFlavor().implies(NullFlavorImpl.PINF).isTrue())
			return p1;
		else if (r == 0.0)
			return minimizePrecision(p1, p2);
		else {
			int lg1 = (int) Math.floor(Math.log10(Math.abs(v1)));
			int lg2 = (int) Math.floor(Math.log10(Math.abs(v2)));
			int lgr = (int) Math.floor(Math.log10(Math.abs(r)));
			INT dec1 = p1.plus(-lg1);
			INT dec2 = p2.plus(-lg2);
			INT dec = !dec1.lessOrEqual(dec2).isFalse() ? dec1 : dec2;
			INT p = dec.plus(lgr);
			// System.err.printf("%e %d %s %s - %e %d %s %s = %e %d %s %s %s\n", v1, lg1, p1.toString(), dec1.toString(), v2, lg2, p2.toString(), dec2.toString(), r, lgr, p.toString(), dec.toString(), p.getClass().getName());
			return p;
		}
	}

	public diff epsilon() {
		return ONE;
	}

	@Override
    public int hashCode() {
		int result;
		long temp;
		temp = _value != +0.0d ? Double.doubleToLongBits(_value) : 0L;
		result = (int) (temp ^ (temp >>> 32));
		result = 29 * result + (_precision != null ? _precision.hashCode() : 0);
		return result;
	}
    
    
    /**
     * An eclipse code generator is used to add an Externalizable 
     * implementation to the org.hl7 types.  Normally this implementation is
     * not checked into SVN.  However, the code generated for this class
     * did not work and had to be replaced with a hand-coded implementation.
     * 
     * @author jmoore
     *
     * @hand-coded 
     */
    @Override
    public void readExternal(final ObjectInput oi) throws IOException
    {
        super.readExternal(oi);
        try
        {
            _value = oi.readDouble();
            _precision = (INT) oi.readObject();
						if(_precision.isZero().isTrue())
							throw new IllegalArgumentException("precision must not be zero");
        }
        catch (final ClassNotFoundException exc)
        {
            throw new IOException(exc.getMessage());
        }
    }

    /**
     * An eclipse code generator is used to add an Externalizable 
     * implementation to the org.hl7 types.  Normally this implementation is
     * not checked into SVN.  However, the code generated for this class
     * did not work and had to be replaced with a hand-coded implementation.
     * 
     * @author jmoore
     *
     * @hand-coded 
     */
    @Override
    public void writeExternal(final ObjectOutput oo) throws IOException
    {
        try
        {
            super.writeExternal(oo);
            final BigDecimal value = BigDecimal.valueOf(_value);
            if (_precision != null && _precision.nonNull().isTrue())
            {
                value.setScale(_precision.intValue(), RoundingMode.HALF_UP);
            }
            oo.writeDouble(value.doubleValue());
            oo.writeObject(_precision);
        }
        catch (final Throwable t)
        {
            t.printStackTrace();
            final IOException ioe = new IOException(toString());
            ioe.initCause(t);
            throw ioe;
        }
    }

}

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

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.ORD;
import org.hl7.types.PQ;
import org.hl7.types.QTY;
import org.hl7.types.TS;
import org.hl7.types.ValueFactory;
import org.regenstrief.ucum.Unit;
import org.regenstrief.ucum.UnitSystem;

/**
 * An implementation of TS that is based on java.util.Date. Note, we are not encapsulating a Date object here since that
 * is not an immutable value object. So, this is not really a Date adapter but rather an implementation based on the
 * same conventions as Date.
 * 
 * To current implementation as of 05-22-2006, the expected input and output (via toString()) string representation of
 * the date value is in format of "yyyyMMddHHmmss.SSS".
 * 
 * For example, "20020830185026.789" means "2002, August, 30th, 18:50:26, and milli-sec: 789".
 * 
 * This class will neither preserve the string, Date, nor long values that were passed in by calling valueOf() methods
 * when an instance of this object was created.
 * 
 * When the toString() method is called, the output will be expected to comply with the format aforementioned, even
 * though with several appended default values.
 */
public final class TSjuDateAdapter extends ORDimpl implements TS {
	Calendar _cal;
	int _precision = 0; // 0 means unknown precision
	boolean _hasTimezone = false;
	public static final PQ ZERO_SECONDS = PQnull.NI; // PQimpl.valueOf(0,"s");
	// FIXME: where do I get the calendar OID from? I will add a bunch
	// basic UID constants later.
	public static final CS CAL_GREGORIAN = CSimpl.valueOf("GREG", "UID-Calendar");
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.types");
	protected static final int MILLIS_PER_MINUTE = 60 * 1000;
	protected static final int MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

	private TSjuDateAdapter(final long time) {
		super(null);
		_cal = Calendar.getInstance();
		_cal.setTimeInMillis(time);
	}

	private TSjuDateAdapter(final long time, final int precision) {
		this(time);
		this._precision = precision;
	}

	private TSjuDateAdapter(final Calendar cal, final int precision, final boolean hasTimezone) {
		super(null);
		this._cal = cal;
		this._precision = precision;
		this._hasTimezone = hasTimezone;
	}

	/**
	 * Get a TS object from a java.util.Date object.
	 */
	public static TS valueOf(final Date date, final int precision) {
		if (date == null) {
			return TSnull.NI;
		} else {
			return new TSjuDateAdapter(date.getTime(), precision);
		}
	}

	public static TS valueOf(final Date date, final INT precision) {
		return valueOf(date, intValueOfINT(precision));
	}

	public static TS valueOf(final Calendar cal, final int precision) {
		return cal == null ? TSnull.NI : valueOf(cal.getTimeInMillis(), precision);
	}

	public static TS valueOf(final Calendar cal, final INT precision, final boolean preserveTimezone) {
		return cal == null ? TSnull.NI : new TSjuDateAdapter((Calendar) cal.clone(), intValueOfINT(precision), preserveTimezone);
	}

	public static TS valueOf(final Calendar cal) {
		return cal == null ? TSnull.NI : valueOf(cal.getTimeInMillis());
	}
	
	/**
	 * Get a TS object from a java.util.Date object.
	 */
	/* package */
	static TS valueOf(final Date date) {
		return valueOf(date.getTime(), 0); // Does not preserve the Date's zimezone
	}

	/**
	 * Get a TS object from a time value that is the offset from java.util.Date epoch 1-1-1970 00:00:00.
	 */
	public static TS valueOf(final long time) {
		return valueOf(time, 0);
	}

	// XXX: there are too many of those variances
	public static TS valueOf(final long time, final int precision) {
		return new TSjuDateAdapter(time, precision);
	}

	// XXX: there are too many of those variances
	public static TS valueOf(final long time, final INT precision) {
		return new TSjuDateAdapter(time, intValueOfINT(precision));
	}

	private static final int intValueOfINT(final INT precision) {
		if (precision.isNull().isTrue()) {
			if (precision == INTnull.PINF) // FIXME: not correct to require the flyweight
			{
				return Integer.MAX_VALUE;
			} else {
				return 0;
			}
		} else {
			return precision.intValue();
		}
	}

	private static int parseInt(final String s, final int startIndex, final int endIndex) {
		int i, n = 0;
		char c;
		for (i = startIndex; i < endIndex; i++) {
			c = s.charAt(i);
			if (c < '0' || c > '9') {
				throw new NumberFormatException(s);
			}
			n = (n * 10) + (c - '0');
		}
		return n;
	}


	/**
	 * Get a TS object from a time value expressed as a string in the format "yyyyMMddHHmmss.SSS".
	 */
	public static TS valueOf(String time) {
		if (time != null) {
			time = time.trim();
		}
		if (time == null || time.equals("")) {
			return TSnull.NA;
		}
		//return new TSjuDateAdapter(parseDate(time).getTime(), time.length());

		final Calendar c = Calendar.getInstance();
		int precision = time.indexOf('+'), field, i = 0, zoneOffset;
		final int realLen = time.length();
		boolean hasTimezone = false;
		c.clear();
		if (precision < 0) {
			precision = time.indexOf('-');
			if (precision < 0) {
				precision = realLen;
			} else {
				i = -1;
			}
		} else {
			i = 1;
		}
		if (i != 0) {
			if ((realLen - precision) % 2 == 0) {
				throw new RuntimeException("not a valid date: " + time);
			}
			zoneOffset = MILLIS_PER_HOUR * parseInt(time, precision + 1, precision + 3);
			if (precision + 3 < realLen) {
				zoneOffset += (MILLIS_PER_MINUTE * parseInt(time, precision + 3, precision + 5));
			}
			zoneOffset *= i;
			c.set(Calendar.ZONE_OFFSET, zoneOffset);
			hasTimezone = true;
		}
		if (precision < 4 || (precision < 14 && precision % 2 == 1)) {
			throw new RuntimeException("not a valid date: " + time);
		}
		c.set(Calendar.YEAR, parseInt(time, 0, 4));
		if (precision >= 6) {
			c.set(Calendar.MONTH, parseInt(time, 4, 6) - 1);
			if (precision >= 8) {
				c.set(Calendar.DAY_OF_MONTH, parseInt(time, 6, 8));
				if (precision >= 10) {
					c.set(Calendar.HOUR_OF_DAY, parseInt(time, 8, 10));
					if (precision >= 12) {
						c.set(Calendar.MINUTE, parseInt(time, 10, 12));
						if (precision >= 14) {
							c.set(Calendar.SECOND, parseInt(time, 12, 14));
							if (precision >= 16) {
								field = parseInt(time, 15, precision);
								for (i = precision; i < 18; i++)
								{	field = field * 10;
								}
								for (i = precision; i > 18; i--)
								{	field = field / 10;
								}
								c.set(Calendar.MILLISECOND, field);
							}
						}
					}
				}
			}
		}
		if (time.indexOf('.') >= 0) {
			precision--;
		}
		return new TSjuDateAdapter(c, precision, hasTimezone);
	}

	@Override
    public String toString() {
		// FIXME: format should be thread-local
		// XXX: this is ridiculous
		//final String result = (new SimpleDateFormat(DATE_FORMAT_STRING)).format(new Date(_time), new StringBuffer(),
		//		new FieldPosition(DateFormat.YEAR_FIELD)).toString();
		//if (_precision > 0) {
		//	return result.substring(0, _precision <= result.length() ? _precision : result.length());
		//} else {
		//	return result;
		//}

		final StringBuffer sb = new StringBuffer(_precision);
		int i, hours;
		final int precision = _precision == 0 ? 17 : _precision;
		appendPadded(sb, _cal.get(Calendar.YEAR), 4);
		if (precision > 4) {
			appendPadded(sb, _cal.get(Calendar.MONTH) + 1, 2);
			if (precision > 6) {
				appendPadded(sb, _cal.get(Calendar.DAY_OF_MONTH), 2);
				if (precision > 8) {
					appendPadded(sb, _cal.get(Calendar.HOUR_OF_DAY), 2);
					if (precision > 10) {
						appendPadded(sb, _cal.get(Calendar.MINUTE), 2);
						if (precision > 12) {
							appendPadded(sb, _cal.get(Calendar.SECOND), 2);
							if (precision > 14) {
								sb.append('.');
								appendPadded(sb, _cal.get(Calendar.MILLISECOND), 3, precision - 14);
							}
						}
					}
				}
			}
		}
		if (_hasTimezone) {
			i = _cal.get(Calendar.ZONE_OFFSET);
			if (i < 0) {
				sb.append('-');
				i = -i;
			} else {
				sb.append('+');
			}
			hours = i / MILLIS_PER_HOUR;
			appendPadded(sb, hours, 2);
			appendPadded(sb, (i - (hours * MILLIS_PER_HOUR)) / MILLIS_PER_MINUTE, 2);
		}
		return sb.toString();
	}

	private void appendPadded(final StringBuffer sb, final int i, final int n) {
		appendPadded(sb, i, n, n);
	}

	private void appendPadded(final StringBuffer sb, final int i, final int n, int lim) {
		final int len = length(i), curr = sb.length();
		if (len == n) {
			sb.append(i);
		} else {
			for (int j = len; j < n; j++) {
				sb.append('0');
			}
			sb.append(i);
		}
		if (lim < n) {
			sb.delete(curr + lim, curr + n);
		}
	}

	private int length(final int i) {
		if (i < 0 || i >= 10000) {
			throw new RuntimeException(i + " outside of expected range");
		}
		if (i < 10) {
			return 1;
		}
		if (i < 100) {
			return 2;
		}
		if (i < 1000) {
			return 3;
		}
		return 4;
	}

	public Date toDate() {
		return _cal.getTime(); // Does not preserve the Calendar's zimezone
	}
	
	public Calendar toCalendar() {
		// Could just return _cal, but then changes to the Calendar returned by this method
		// would also change the TS, which was not the case when this was backed by a long value.
		return (Calendar) _cal.clone();
	}

	/**
	 * Test for equality between two TS values. The trick is, to find a common denomination in which we can compare. In
	 * order to stay totally independent from any specific representation, we can simply subtract the two dates and test
	 * if we get zero in return. <p/> We could actually do this for all QTY types.
	 */
	@Override
    public BL equal(final ANY that) {
		if (that instanceof TS) {
			final TS thatTS = (TS) that;
			return this.minus(thatTS).isZero();
		} else {
			return BLimpl.FALSE;
		}
	}

	@Override
    public BL lessOrEqual(final ORD that) {
		if (that instanceof TS) {
			final TS thatTS = (TS) that;
			return this.minus(thatTS).lessOrEqual(PQimpl.valueOf("0", "ms"));
			// return this.minus(thatTS).lessOrEqual(ZERO_SECONDS);
		} else {
			return BLimpl.FALSE;
		}
	}

	@Override
    public BL greaterOrEqual(final ORD that) {
		if (that instanceof TS) {
			final TS thatTS = (TS) that;
			if (this.equal(thatTS).isTrue()) {
				return BLimpl.TRUE;
			} else if (this.lessOrEqual(thatTS).isTrue()) {
				return BLimpl.FALSE;
			} else {
				return BLimpl.TRUE;
			}
		} else {
			return BLimpl.FALSE;
		}
	}

	@Override
    public BL compares(final ORD that) {
		return BLimpl.valueOf(that instanceof TS);
	}

	public QTY plus(final QTY.diff that) {
		if (that instanceof PQ) {
			return this.plus((PQ) that);
		} else {
			if (that.isNullJ()) {
				return QTYnull.NI;
			}
			throw new IllegalArgumentException();
		}
	}

	public static final PQ ONE_MS = PQimpl.valueOf(REALdoubleAdapter.ONE, "ms");

	public TS plus(final PQ that) {
		if (that.nonZero().isTrue()) {
			return TSjuDateAdapter.valueOf(this.offset().plus(that).expressedIn(ONE_MS).value().longValue(), _precision);
		} else if (that.isZero().isTrue()) {
			return this;
		} else if (that.isNull().isTrue()) {
			return TSnull.NI;
		} else {
			throw new Error("what other case is there?");
		}
	}

	public PQ minus(final QTY that) {
		if (that.isNullJ()) {
			return PQnull.NI;
		}
		if (that instanceof TS) {
			return minus((TS) that);
		}
		throw new UnsupportedOperationException();
	}

	public TS minus(final QTY.diff that) {
		final QTY.diff thatQ = that;
		if (thatQ.isNull().isTrue()) {
			return TSnull.NI;
		} else if (thatQ.isZero().isTrue()) {
			return this;
		} else if (thatQ instanceof PQ) {
			final PQ thatPQ = (PQ) that;
			return TSjuDateAdapter.valueOf(this.offset().minus(thatPQ).expressedIn(ONE_MS).value().longValue(), _precision);
		} else {
			throw new UnsupportedOperationException("" + this.toString() + ".minus(" + that.toString() + ")");
		}
	}

	private static final PQ[] DIGIT_VALUES = { PQimpl.valueOf("1e3", "a"), PQimpl.valueOf("1e2", "a"),
			PQimpl.valueOf("1e1", "a"), PQimpl.valueOf("1", "a"), PQimpl.valueOf("10", "mo"), PQimpl.valueOf("1", "mo"),
			PQimpl.valueOf("10", "d"), PQimpl.valueOf("1", "d"), PQimpl.valueOf("10", "h"), PQimpl.valueOf("1", "h"),
			PQimpl.valueOf("10", "min"), PQimpl.valueOf("1", "min"), PQimpl.valueOf("10", "s"), PQimpl.valueOf("1", "s") };

	public PQ minus(final TS that) {
		final PQ diff = this.offset().minus(that.offset());
		if (diff.isNull().isTrue()) {
			return diff;
		}
		final int thatPrecision = that.precision().nonNull().isTrue() ? that.precision().intValue() : Integer.MAX_VALUE;
		final int precision = this._precision <= thatPrecision ? this._precision : thatPrecision;
		final PQ unit = _precision < 15 && _precision > 0 ? DIGIT_VALUES[precision - 1] : MILLISECOND;
		return PQimpl.valueOf(Long.toString(diff.expressedIn(unit).value().longValue()) + ".", (Unit) unit.unit());
	}

	/* package */final static PQ MILLISECOND = PQimpl.valueOf("1", "ms");
	private final static UnitSystem UNIT_SYSTEM = UnitSystem.getInstance();
	private final static Unit MILLISECOND_UNIT = Unit.valueOf("ms");

	public PQ offset() {
		return PQimpl.valueOf(ValueFactory.getInstance().REALvalueOf((double) _cal.getTimeInMillis()), MILLISECOND_UNIT);
	}

	public CS calendar() {
		return CAL_GREGORIAN;
	}

	public INT precision() {
		if (_precision <= 0) {
			return INTnull.NA;
		} else {
			return INTlongAdapter.valueOf(_precision);
		}
	}

	public PQ timezone() {
		throw new UnsupportedOperationException();
	}

	@Override
    public int hashCode() {
		return _cal.hashCode();
	}

	// fix me
	public long getInternalTime() {
		return 0; // To change body of created methods use File | Settings | File Templates.
	}

	public PQ epsilon() {
		return MILLISECOND;
	}
}

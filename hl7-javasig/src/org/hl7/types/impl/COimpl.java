package org.hl7.types.impl;

import org.hl7.types.BL;
import org.hl7.types.CO;
import org.hl7.types.INT;
import org.hl7.types.ST;
import org.hl7.types.UID;

/**
 * The main outstanding issue with this class is determining whether or
 * not this ordinal is comparable to another ordinal. This is left as 
 * an exercise for the reader.
 * 
 * @author Brian DeCamp
 */
public class COimpl extends CVimpl implements CO {

    private final INT ordinal;
    
    protected COimpl(final ST code, final UID codeSystem, final INT ordinal) {
        super(code, codeSystem);
        if(ordinal == null) {
            throw new IllegalArgumentException("null ordinal");
        }
        this.ordinal = ordinal;
    }

    protected boolean isComparableTo(final COimpl other) {
        return true; // FIXME: How is this determined?
    }
    
    public BL greaterOrEqual(final CO that) {
        if(that instanceof COimpl && isComparableTo((COimpl)that)) {
            final COimpl other = (COimpl)that;
            if(ordinal.isNull().isTrue() || other.ordinal.isNull().isTrue()) {
                return BLimpl.NA;
            } else {
                return ordinal.intValue() >= other.ordinal.intValue() ? BLimpl.TRUE : BLimpl.FALSE;
            }
        } else {
            return BLimpl.NA;
        }
    }

    public BL greaterThan(final CO that) {
        if(that instanceof COimpl && isComparableTo((COimpl)that)) {
            final COimpl other = (COimpl)that;
            if(ordinal.isNull().isTrue() || other.ordinal.isNull().isTrue()) {
                return BLimpl.NA;
            } else {
                return ordinal.intValue() > other.ordinal.intValue() ? BLimpl.TRUE : BLimpl.FALSE;
            }
        } else {
            return BLimpl.NA;
        }
    }

    public BL lessOrEqual(final CO that) {
        if(that instanceof COimpl && isComparableTo((COimpl)that)) {
            final COimpl other = (COimpl)that;
            if(ordinal.isNull().isTrue() || other.ordinal.isNull().isTrue()) {
                return BLimpl.NA;
            } else {
                return ordinal.intValue() <= other.ordinal.intValue() ? BLimpl.TRUE : BLimpl.FALSE;
            }
        } else {
            return BLimpl.NA;
        }
    }

    public BL lessThan(final CO that) {
        if(that instanceof COimpl && isComparableTo((COimpl)that)) {
            final COimpl other = (COimpl)that;
            if(ordinal.isNull().isTrue() || other.ordinal.isNull().isTrue()) {
                return BLimpl.NA;
            } else {
                return ordinal.intValue() < other.ordinal.intValue() ? BLimpl.TRUE : BLimpl.FALSE;
            }
        } else {
            return BLimpl.NA;
        }
    }
}

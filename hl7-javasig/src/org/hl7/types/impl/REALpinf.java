package org.hl7.types.impl;

import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.ORD;
import org.hl7.types.REAL;

public class REALpinf extends REALnull {
	static final REALpinf VALUE = new REALpinf();

	private REALpinf() {
		super(NullFlavorImpl.PINF);
	}

	@Override
    public BL lessOrEqual(final ORD x) {
		if (this.compares(x).isTrue()) {
            return BLimpl.FALSE;
        } else {
            return BLimpl.NA;
        }
	}

	@Override
    public BL lessOrEqual(final int x) {
		return BLimpl.FALSE;
	}

	@Override
    public BL lessOrEqual(final long x) {
		return BLimpl.FALSE;
	}

	@Override
    public BL lessOrEqual(final float x) {
		return BLimpl.FALSE;
	}

	@Override
    public BL lessOrEqual(final double x) {
		return BLimpl.FALSE;
	}

	@Override
    public BL compares(final ORD x) {
		return BLimpl.valueOf(x instanceof REAL || x instanceof INT).and(x.nonNull());
	}

	@Override
    public BL compares(final long x) {
		return BLimpl.TRUE;
	}

	@Override
    public BL isZero() {
		return BLimpl.FALSE;
	}

	@Override
    public BL nonZero() {
		return BLimpl.TRUE;
	}

	@Override
    public BL isOne() {
		return BLimpl.FALSE;
	}

	@Override
    public REAL negated() {
		return REALnull.NINF;
	}

	public BL isNegative() {
		return BLimpl.FALSE;
	}

	public BL nonNegative() {
		return BLimpl.TRUE;
	}
}
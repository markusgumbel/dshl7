package org.hl7.types.impl;

import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.ORD;
import org.hl7.types.REAL;

public class REALninf extends REALnull {
	static final REALninf VALUE = new REALninf();

	private REALninf() {
		super(NullFlavorImpl.NINF);
	}

	@Override
    public BL lessOrEqual(final ORD x) {
		if (this.compares(x).isTrue()) {
            return BLimpl.TRUE;
        } else {
            return BLimpl.NA;
        }
	}

	@Override
    public BL lessOrEqual(final int x) {
		return BLimpl.TRUE;
	}

	@Override
    public BL lessOrEqual(final long x) {
		return BLimpl.TRUE;
	}

	@Override
    public BL lessOrEqual(final float x) {
		return BLimpl.TRUE;
	}

	@Override
    public BL lessOrEqual(final double x) {
		return BLimpl.TRUE;
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
		return BLimpl.FALSE;
	}

	@Override
    public BL isOne() {
		return BLimpl.FALSE;
	}

	@Override
    public REAL negated() {
		return REALnull.PINF;
	}

	public BL isNegative() {
		return BLimpl.TRUE;
	}

	public BL nonNegative() {
		return BLimpl.FALSE;
	}
}
package org.hl7.types.impl;

import org.hl7.types.BL;
import org.hl7.types.CO;
import org.hl7.types.NullFlavor;

/**
 * Minimal implementation of CO as a model we can use for all other type. This one is only used to convey NULL values.
 * Again, we have static values of it, so we don't create so many objects in a running program.
 */
public class COnull extends CVnull implements CO {
    public static final COnull NI = new COnull(NullFlavorImpl.NI);
    public static final COnull NA = new COnull(NullFlavorImpl.NA);
    public static final COnull UNK = new COnull(NullFlavorImpl.UNK);
    public static final COnull NASK = new COnull(NullFlavorImpl.NASK);
    public static final COnull ASKU = new COnull(NullFlavorImpl.ASKU);
    public static final COnull NAV = new COnull(NullFlavorImpl.NAV);
    public static final COnull OTH = new COnull(NullFlavorImpl.OTH);
    public static final COnull PINF = new COnull(NullFlavorImpl.PINF);
    public static final COnull NINF = new COnull(NullFlavorImpl.NINF);

    protected COnull(final NullFlavor nf) {
        super(nf);
    }

    public BL greaterOrEqual(final CO o) {
        return BLimpl.NA;
    }

    public BL greaterThan(final CO o) {
        return BLimpl.NA;
    }

    public BL lessOrEqual(final CO o) {
        return BLimpl.NA;
    }

    public BL lessThan(final CO o) {
        return BLimpl.NA;
    }
}

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

import junit.framework.TestCase;

import org.hl7.types.QSET;
import org.hl7.types.TS;

/**
 * test to exercise QSETUnionImpls
 */
public class QSETUnionImplTest extends TestCase
{
    /*
     * Build a QSETUnionImpl to test
     */
    static final QSET union;

    static final QSET union2;
    static
    {
        // build every 5 days from 12:00 to 12:10 starting at Feb 5th 2004
        IVLimpl ivl1 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, (TS) TSjuDateAdapter.valueOf("200402051200"), (TS) TSjuDateAdapter.valueOf("200402051210"), BLimpl.TRUE);
        PIVLimpl pivl1 = (PIVLimpl) PIVLimpl.valueOf(ivl1, PQimpl.valueOf("5", "d"), CSnull.NI, BLimpl.FALSE);
        // build every 15 days from Feb 9th 2004 at 14:00 to 14:30 to Aug 1 2004
        IVLimpl ivl2 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, (TS) TSjuDateAdapter.valueOf("200402091400"), (TS) TSjuDateAdapter.valueOf("200402091430"), BLimpl.TRUE);
        PIVLimpl pivl2 = (PIVLimpl) PIVLimpl.valueOf(ivl2, PQimpl.valueOf("15", "d"), CSnull.NI, BLimpl.FALSE);
        // and finally put it together
        union = QSETUnionImpl.valueOf(pivl1, pivl2);

        IVLimpl ivl3 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, (TS) TSjuDateAdapter.valueOf("200402051200"), (TS) TSjuDateAdapter.valueOf("200402051210"), BLimpl.TRUE);
        PIVLimpl pivl3 = (PIVLimpl) PIVLimpl.valueOf(ivl1, PQimpl.valueOf("5", "d"), CSnull.NI, BLimpl.FALSE);
        IVLimpl ivl4 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, (TS) TSjuDateAdapter.valueOf("200402051400"), (TS) TSjuDateAdapter.valueOf("200402051430"), BLimpl.TRUE);
        PIVLimpl pivl4 = (PIVLimpl) PIVLimpl.valueOf(ivl4, PQimpl.valueOf("5", "d"), CSnull.NI, BLimpl.FALSE);
        // and finally put it together
        union2 = QSETUnionImpl.valueOf(pivl3, pivl4);
    }

    static final IVLimpl i1 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402091400"), TSjuDateAdapter.valueOf("200402091430"), BLimpl.TRUE);

    static final IVLimpl i2 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402101200"), TSjuDateAdapter.valueOf("200402101210"), BLimpl.TRUE);

    static final IVLimpl i3 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402241400"), TSjuDateAdapter.valueOf("200402241430"), BLimpl.TRUE);

    static final IVLimpl i4 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402301200"), TSjuDateAdapter.valueOf("200402301210"), BLimpl.TRUE);

    public void test_isEmpty()
    {
        assertTrue("union empty", union.isEmpty().isFalse());
        assertTrue("union2 empty", union2.isEmpty().isFalse());
    }

    public void test_equal()
    {
        //TODO: verify this operation should be unsupported
        try
        {
            union.equal(union);
            fail("Should have thrown UnsupportedOperationException.");
        }
        catch (UnsupportedOperationException success)
        {
        }
    }

    public void test_nextTo()
    {
        // test union
        assertTrue("union.nextTo(200402081215) != i1", i1.equal(union.nextTo(TSjuDateAdapter.valueOf("200402081215"))).isTrue());
        assertTrue("union.nextTo(200402101155) != i2", i2.equal(union.nextTo(TSjuDateAdapter.valueOf("200402101155"))).isTrue());
        assertTrue("union.nextTo(200402201555) != i2", i3.equal(union.nextTo(TSjuDateAdapter.valueOf("200402201555"))).isTrue());
        assertTrue("union.nextTo(200402261300) != i4", i4.equal(union.nextTo(TSjuDateAdapter.valueOf("200402261300"))).isTrue());
    }

    public void test_nextAfter()
    {
        // test union
        assertTrue("union.nextAfter(200402051203) != i1", i1.equal(union.nextAfter(TSjuDateAdapter.valueOf("200402051209"))).isTrue());
        assertTrue("union.nextAfter(200402081203) != i1", i1.equal(union.nextAfter(TSjuDateAdapter.valueOf("200402081209"))).isTrue());
        assertTrue("union.nextAfter(200402091403) != i2", i2.equal(union.nextAfter(TSjuDateAdapter.valueOf("200402091403"))).isTrue());
    }
}

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
 * test to exercise QSETs
 */
public class QSETPeriodicHullImplTest extends TestCase
{
    static final QSET ph;
    static
    {
        IVLimpl ivl1 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, (TS) TSjuDateAdapter.valueOf("200402051200"), (TS) TSjuDateAdapter.valueOf("200402051210"), BLimpl.TRUE);
        PIVLimpl pivl1 = (PIVLimpl) PIVLimpl.valueOf(ivl1, PQimpl.valueOf("5", "d"), CSnull.NI, BLimpl.FALSE);
        IVLimpl ivl2 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, (TS) TSjuDateAdapter.valueOf("200402091400"), (TS) TSjuDateAdapter.valueOf("200402091430"), BLimpl.TRUE);
        PIVLimpl pivl2 = (PIVLimpl) PIVLimpl.valueOf(ivl2, PQimpl.valueOf("5", "d"), CSnull.NI, BLimpl.FALSE);
        ph = QSETPeriodicHullImpl.valueOf(pivl1, pivl2);
    }

    // some IVLs for testing
    static final IVLimpl i1 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402101200"), TSjuDateAdapter.valueOf("200402141430"), BLimpl.TRUE);

    static final IVLimpl i2 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402051200"), TSjuDateAdapter.valueOf("200402091430"), BLimpl.TRUE);

    static final IVLimpl i3 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402151200"), TSjuDateAdapter.valueOf("200402191430"), BLimpl.TRUE);

    static final IVLimpl i4 = (IVLimpl) IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter.valueOf("200402201200"), TSjuDateAdapter.valueOf("200402241430"), BLimpl.TRUE);

    public void test_containsElement()
    {
        assertTrue(ph.contains(TSjuDateAdapter.valueOf("200402091431")).isFalse());
        assertTrue(ph.contains(TSjuDateAdapter.valueOf("200402091430")).isTrue());
        assertTrue(ph.contains(TSjuDateAdapter.valueOf("200402141331")).isTrue());
        assertTrue(ph.contains(TSjuDateAdapter.valueOf("200402100131")).isFalse());
        assertTrue(ph.contains(TSjuDateAdapter.valueOf("200402171431")).isTrue());
    }

    public void test_isEmpty()
    {
        assertTrue(ph.isEmpty().isFalse());
    }

    public void test_nextTo()
    {
        assertTrue(i1.equal(ph.nextTo(TSjuDateAdapter.valueOf("200402091431"))).isTrue());
        assertTrue(i2.equal(ph.nextTo(TSjuDateAdapter.valueOf("200402091430"))).isTrue());
        assertTrue(i1.equal(ph.nextTo(TSjuDateAdapter.valueOf("200402141331"))).isTrue());
        assertTrue(i1.equal(ph.nextTo(TSjuDateAdapter.valueOf("200402100131"))).isTrue());
        assertTrue(i3.equal(ph.nextTo(TSjuDateAdapter.valueOf("200402171431"))).isTrue());
    }

    public void test_nextAfter()
    {
        assertTrue(i1.equal(ph.nextAfter(TSjuDateAdapter.valueOf("200402091431"))).isTrue());
        assertTrue(i1.equal(ph.nextAfter(TSjuDateAdapter.valueOf("200402091430"))).isTrue());
        assertTrue(i3.equal(ph.nextAfter(TSjuDateAdapter.valueOf("200402141331"))).isTrue());
        assertTrue(i1.equal(ph.nextAfter(TSjuDateAdapter.valueOf("200402100131"))).isTrue());
        assertTrue(i4.equal(ph.nextAfter(TSjuDateAdapter.valueOf("200402171431"))).isTrue());
    }
}

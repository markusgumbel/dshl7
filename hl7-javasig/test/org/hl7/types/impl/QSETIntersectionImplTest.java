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

import org.hl7.types.IVL;
import org.hl7.types.PIVL;
import org.hl7.types.QSET;
import org.hl7.types.TS;

/**
 * test to exercise QSETs
 */
public class QSETIntersectionImplTest extends TestCase {
    /*
     * Build some QSETIntersectionImpls to test
     */
    private static final QSET<TS> INTERSECTION_1;
    
    private static final QSET<TS> INTERSECTION_2;
    
    private static final QSET<TS> INTERSECTION_3;
    
    static {
        final IVL<TS> x5d =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402051200"), TSjuDateAdapter
                        .valueOf("200402051210"), BLimpl.TRUE);
        final PIVL<TS> pid =
                PIVLimpl.valueOf(x5d, PQimpl.valueOf("5", "d"), CSnull.NI,
                        BLimpl.FALSE);
        final IVL<TS> ivl1 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402051100"), TSjuDateAdapter
                        .valueOf("200402051430"), BLimpl.TRUE);
        final PIVL<TS> pivl1 =
                PIVLimpl.valueOf(ivl1, PQimpl.valueOf("10", "d"), CSnull.NI,
                        BLimpl.FALSE);
        INTERSECTION_1 = QSETIntersectionImpl.valueOf(pid, pivl1);
        
        final IVL<TS> ivl2 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402251203"), TSjuDateAdapter
                        .valueOf("200402251215"), BLimpl.TRUE);
        final PIVL<TS> pivl2 =
                PIVLimpl.valueOf(ivl2, PQimpl.valueOf("40", "d"), CSnull.NI,
                        BLimpl.FALSE);
        INTERSECTION_2 = QSETIntersectionImpl.valueOf(INTERSECTION_1, pivl2);
        
        final IVL<TS> ivl3 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402051100"), TSjuDateAdapter
                        .valueOf("200402051430"), BLimpl.TRUE);
        final PIVL<TS> pivl3 =
                PIVLimpl.valueOf(ivl3, PQimpl.valueOf("2", "d"), CSnull.NI,
                        BLimpl.FALSE);
        QSETIntersectionImpl.valueOf(pid, pivl3);
        
        final IVL<TS> ivl4 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402061300"), TSjuDateAdapter
                        .valueOf("200402061430"), BLimpl.TRUE);
        final PIVL<TS> pivl4 =
                PIVLimpl.valueOf(ivl4, PQimpl.valueOf("2", "d"), CSnull.NI,
                        BLimpl.FALSE);
        INTERSECTION_3 = QSETIntersectionImpl.valueOf(pivl3, pivl4);
        QSETIntersectionImpl.valueOf(pid, pivl1);
        
        // first build "3 times a day"
        final PIVL<TS> tid =
                PIVLimpl.valueOf((IVL<TS>) IVLnull.NI, PQimpl.valueOf("8", "h"),
                        CSnull.NI, BLimpl.FALSE);
        // the the outer bound interval "for 10 days"
        final IVL<TS> x10d =
                IVLimpl.valueOf((TS) TSnull.NI, PQimpl.valueOf("10", "d"),
                        BLimpl.TRUE, BLimpl.FALSE);
        // and finally put it together as the intersection:
        QSETIntersectionImpl.valueOf(tid, x10d);
        
        /*
         * Build another copy of that QSET for sake of testing equality
         */
        final PIVL<TS> pivl =
                PIVLimpl.valueOf((IVL<TS>) IVLnull.NI, PQimpl.valueOf("12", "h"),
                        CSnull.NI, BLimpl.FALSE);
        final IVL<TS> ivl =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("19690219000000"), TSjuDateAdapter
                        .valueOf("19690220000000"), BLimpl.FALSE);
        QSETIntersectionImpl.valueOf(pivl, ivl);
        
        // To build the schedule for my birthday: 19690219..20/(1 a)
        // first build the phase interval, that is any of my birthdays:
        final IVL<TS> dobPhase =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("19690219000000"), TSjuDateAdapter
                        .valueOf("19690220000000"), BLimpl.FALSE);
        // then make that repeat every year:
        final PIVL<TS> allBirthdays =
                PIVLimpl.valueOf(dobPhase, PQimpl.valueOf("1", "a"), CSnull.NI,
                        BLimpl.FALSE);
        // now make the intersection with the interval
        // that begins when I was born and ends when I will die
        final IVL<TS> lifeSpan =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("19690219000000"), TSnull.NA, BLimpl.FALSE);
        // and now put it all together:
        QSETIntersectionImpl.valueOf(allBirthdays, lifeSpan);
        
        final IVL<TS> ivl19 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402101209"), TSjuDateAdapter
                        .valueOf("200402101910"), BLimpl.TRUE);
        PIVLimpl.valueOf(ivl19, PQimpl.valueOf("10", "d"), CSnull.NI,
                        BLimpl.FALSE);
    }
    
    private static final IVL<TS> i5 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402051200"), TSjuDateAdapter
                    .valueOf("200402051210"), BLimpl.TRUE);
    
    private static final IVL<TS> i6 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402151200"), TSjuDateAdapter
                    .valueOf("200402151210"), BLimpl.TRUE);
    
    private static final IVL<TS> i9 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402251203"), TSjuDateAdapter
                    .valueOf("200402251210"), BLimpl.TRUE);
    
    private static final IVL<TS> i14 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402151200"), TSjuDateAdapter
                    .valueOf("200402151210"), BLimpl.TRUE);
    
    private static final IVL<TS> i15 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402251203"), TSjuDateAdapter
                    .valueOf("200402251210"), BLimpl.TRUE);
    
    public void test_isEmpty() {
        assertTrue("intersection3 !empty", INTERSECTION_3.isEmpty().isTrue());
        assertTrue("intersection empty", INTERSECTION_1.isEmpty().isFalse());
    }
    
    public void test_nextTo() {
        assertTrue("intersection.nextTo(200402051205)", i5.equal(
                INTERSECTION_1.nextTo(TSjuDateAdapter.valueOf("200402051205")))
                .isTrue());
        assertTrue("intersection.nextTo(200402051213)", i6.equal(
                INTERSECTION_1.nextTo(TSjuDateAdapter.valueOf("200402051213")))
                .isTrue());
        assertTrue("intersection2.nextTo(200402161113)", i9.equal(
                INTERSECTION_2.nextTo(TSjuDateAdapter.valueOf("200402161113")))
                .isTrue());
        // test case when they never intersect-- make sure there isn't an
        // infinite loop
        assertEquals(IVLnull.class, INTERSECTION_3.nextTo(
                TSjuDateAdapter.valueOf("200406150100")).getClass());
    }
    
    public void test_nextAfter() {
        assertTrue("intersection.nextAfter(200402051205)", i14
                .equal(
                        INTERSECTION_1.nextAfter(TSjuDateAdapter
                                .valueOf("200402051205"))).isTrue());
        assertTrue("intersection.nextAfter(200402051213)", i14.equal(
                INTERSECTION_1.nextTo(TSjuDateAdapter.valueOf("200402051213")))
                .isTrue());
        assertTrue("intersection.nextAfter(200402161113)", i15.equal(
                INTERSECTION_2.nextTo(TSjuDateAdapter.valueOf("200402161113")))
                .isTrue());
        // test case when they never intersect-- make sure there isn't an
        // infinite loop
        assertEquals(IVLnull.class, INTERSECTION_3.nextTo(
                TSjuDateAdapter.valueOf("200406150100")).getClass());
    }
}

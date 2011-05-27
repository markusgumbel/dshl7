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
import org.hl7.types.TS;

/**
 * test to exercise {@link QSET}s
 */
public class QSETDifferenceImplTest extends TestCase {
    /*
     * Build some QSETDifferenceImpls for testing
     */
    private static final QSETDifferenceImpl<TS> DIFFERENCE_1;
    private static final QSETDifferenceImpl<TS> DIFFERENCE_2;
    private static final QSETDifferenceImpl<TS> DIFFERENCE_3;
    static {
        final IVL<TS> ivl1 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402151200"), TSjuDateAdapter
                        .valueOf("200402151210"), BLimpl.TRUE);
        final PIVL<TS> pivl1 =
                PIVLimpl.valueOf(ivl1, PQimpl.valueOf("2", "d"), CSnull.NI,
                        BLimpl.FALSE);
        final IVL<TS> ivl2 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402131209"), TSjuDateAdapter
                        .valueOf("200402131400"), BLimpl.TRUE);
        final PIVL<TS> pivl2 =
                PIVLimpl.valueOf(ivl2, PQimpl.valueOf("4", "d"), CSnull.NI,
                        BLimpl.FALSE);
        DIFFERENCE_1 = QSETDifferenceImpl.valueOf(pivl1, pivl2);
        
        final IVL<TS> ivl3 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402151200"), TSjuDateAdapter
                        .valueOf("200402151210"), BLimpl.TRUE);
        final PIVL<TS> pivl3 =
                PIVLimpl.valueOf(ivl3, PQimpl.valueOf("2", "d"), CSnull.NI,
                        BLimpl.FALSE);
        final IVL<TS> ivl4 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402131100"), TSjuDateAdapter
                        .valueOf("200402131400"), BLimpl.TRUE);
        final PIVL<TS> pivl4 =
                PIVLimpl.valueOf(ivl4, PQimpl.valueOf("4", "d"), CSnull.NI,
                        BLimpl.FALSE);
        DIFFERENCE_2 = QSETDifferenceImpl.valueOf(pivl3, pivl4);
        
        // this difference is empty!
        final IVL<TS> ivl5 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402151200"), TSjuDateAdapter
                        .valueOf("200402151210"), BLimpl.TRUE);
        final PIVL<TS> pivl5 =
                PIVLimpl.valueOf(ivl5, PQimpl.valueOf("2", "d"), CSnull.NI,
                        BLimpl.FALSE);
        final IVL<TS> ivl6 =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200402131100"), TSjuDateAdapter
                        .valueOf("200402131400"), BLimpl.TRUE);
        final PIVL<TS> pivl6 =
                PIVLimpl.valueOf(ivl6, PQimpl.valueOf("2", "d"), CSnull.NI,
                        BLimpl.FALSE);
        DIFFERENCE_3 = QSETDifferenceImpl.valueOf(pivl5, pivl6);
    }
    
    private static final IVL<TS> IVL_200402131200_200402131209 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402131200"), TSjuDateAdapter
                    .valueOf("200402131209"), BLimpl.FALSE);
    private static final IVL<TS> IVL_200402171200_200402171209 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402171200"), TSjuDateAdapter
                    .valueOf("200402171209"), BLimpl.FALSE);
    private static final IVL<TS> IVL_200402231200_200402231210 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402231200"), TSjuDateAdapter
                    .valueOf("200402231210"), BLimpl.TRUE);
    private static final IVL<TS> IVL_200402191200_200402191210 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402191200"), TSjuDateAdapter
                    .valueOf("200402191210"), BLimpl.TRUE);
    private static final IVL<TS> IVL_200402151200_200402151210 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402151200"), TSjuDateAdapter
                    .valueOf("200402151210"), BLimpl.TRUE);
    private static final IVL<TS> IVL_200402211200_200402211209_A =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402211200"), TSjuDateAdapter
                    .valueOf("200402211209"), BLimpl.FALSE);
    private static final IVL<TS> IVL_200402211200_200402211209_B =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402211200"), TSjuDateAdapter
                    .valueOf("200402211209"), BLimpl.FALSE);
    private static final IVL<TS> IVL_200402111200_200402111210 =
            IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                    .valueOf("200402111200"), TSjuDateAdapter
                    .valueOf("200402111210"), BLimpl.FALSE);
    
    public void test_containsElement() {
        assertTrue("difference !contain 200402131203", DIFFERENCE_1.contains(
                TSjuDateAdapter.valueOf("200402131203")).isTrue());
        assertTrue("difference contains 200402211209", DIFFERENCE_1.contains(
                TSjuDateAdapter.valueOf("200402211209")).isFalse());
        assertTrue("difference !contain 200402231210", DIFFERENCE_1.contains(
                TSjuDateAdapter.valueOf("200402231210")).isTrue());
    }
    
    public void test_containsSET() {
        // TODO: someone verify that these should be unsupported operations
        try {
            DIFFERENCE_1.contains(IVL_200402211200_200402211209_B);
            fail("Should have thrown "
                    + UnsupportedOperationException.class.getName());
        } catch (final UnsupportedOperationException success) {
            // expected
        }
        try {
            DIFFERENCE_1.contains(IVL_200402111200_200402111210);
            fail("Should have thrown "
                    + UnsupportedOperationException.class.getName());
        } catch (final UnsupportedOperationException success) {
            // expected
        }
    }
    
    public void test_isEmpty() {
        assertTrue(DIFFERENCE_1.isEmpty().isFalse());
        assertTrue(DIFFERENCE_2.isEmpty().isFalse());
        assertTrue(DIFFERENCE_3.isEmpty().isTrue());
    }
    
    public void test_nextTo() {
        assertTrue(IVL_200402131200_200402131209.equal(
                DIFFERENCE_1.nextTo(TSjuDateAdapter.valueOf("200402130400")))
                .isTrue());
        assertTrue(IVL_200402171200_200402171209.equal(
                DIFFERENCE_1.nextTo(TSjuDateAdapter.valueOf("200402151300")))
                .isTrue());
        assertTrue(IVL_200402231200_200402231210.equal(
                DIFFERENCE_1.nextTo(TSjuDateAdapter.valueOf("200402221400")))
                .isTrue());
        assertTrue(IVL_200402171200_200402171209.equal(
                DIFFERENCE_1.nextTo(TSjuDateAdapter.valueOf("200402171202")))
                .isTrue());
        assertTrue(IVL_200402191200_200402191210.equal(
                DIFFERENCE_1.nextTo(TSjuDateAdapter.valueOf("200402191201")))
                .isTrue());
    }
    
    public void test_nextAfter() {
        assertTrue(IVL_200402171200_200402171209
                .equal(
                        DIFFERENCE_1.nextAfter(TSjuDateAdapter
                                .valueOf("200402151300"))).isTrue());
        assertTrue(IVL_200402231200_200402231210
                .equal(
                        DIFFERENCE_1.nextAfter(TSjuDateAdapter
                                .valueOf("200402221400"))).isTrue());
        assertTrue(IVL_200402191200_200402191210
                .equal(
                        DIFFERENCE_1.nextAfter(TSjuDateAdapter
                                .valueOf("200402171202"))).isTrue());
        assertTrue(IVL_200402151200_200402151210
                .equal(
                        DIFFERENCE_1.nextAfter(TSjuDateAdapter
                                .valueOf("200402140400"))).isTrue());
        assertTrue(IVL_200402211200_200402211209_A
                .equal(
                        DIFFERENCE_1.nextAfter(TSjuDateAdapter
                                .valueOf("200402191201"))).isTrue());
    }
}

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

import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.types.IVL;
import org.hl7.types.PQ;
import org.hl7.types.QTY;
import org.hl7.types.TS;

/**
 * * A test suite that exercises {@link IVLimpl} class.
 * 
 * @author Geoffry Roberts
 * @author nradov
 */
public class IVLimplTest extends TestCase {
    /**
     * Constructor for <code>test_PIVLimpl</code>.
     * 
     * @param name
     *                the test name
     */
    
    private IVL<PQ> thisIVL;
    private IVL<PQ> thatIVL;
    private IVL<PQ> otherIVL;
    private IVL<PQ> unionIVL;
    private IVL<QTY> literalIVL;
    private IVL<TS> blehIVL;
    private IVL<QTY> literalIVL2;
    private IVL<TS> blehIVL2;
    private IVL<QTY> literalIVL3;
    private IVL<PQ> blehIVL3;
    private IVL<QTY> literalIVL4;
    private IVL<? extends QTY> onlyLowIVL;
    private PQ thisLow;
    private PQ thatLow;
    private PQ thisHigh;
    private PQ thatHigh;
    private PQ otherLow;
    private PQ otherHigh;
    private PQ unionHigh;
    private PQ unionLow;
    
    public void setUp() {
        thisLow = PQimpl.valueOf(REALdoubleAdapter.valueOf(1), "s");
        assertNotNull("thisLow is null!!", thisLow);
        thisHigh = PQimpl.valueOf(REALdoubleAdapter.valueOf(9), "s");
        assertNotNull("thisHigh is null!!", thisHigh);
        thisIVL = IVLimpl.valueOf(BLimpl.TRUE, thisLow, thisHigh, BLimpl.TRUE);
        assertNotNull("thisIVL is null!!", thisIVL);
        
        thatLow = PQimpl.valueOf(REALdoubleAdapter.valueOf(3), "s");
        assertNotNull("thatLow is null!!", thatLow);
        thatHigh = PQimpl.valueOf(REALdoubleAdapter.valueOf(5), "s");
        assertNotNull("thatHigh is null!!", thatHigh);
        thatIVL =
                IVLimpl.valueOf(BLimpl.FALSE, thatLow, thatHigh, BLimpl.FALSE);
        assertNotNull("thatIVL is null!!", thatIVL);
        
        onlyLowIVL =
                IVLimpl.valueOf(BLimpl.FALSE, thatLow, PQnull.NI, BLimpl.NA);
        
        otherLow = PQimpl.valueOf(REALdoubleAdapter.valueOf(3), "s");
        otherHigh = PQimpl.valueOf(REALdoubleAdapter.valueOf(20), "s");
        otherIVL =
                IVLimpl.valueOf(BLimpl.TRUE, otherLow, otherHigh, BLimpl.FALSE);
        
        blehIVL =
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("200004111950"), TSjuDateAdapter
                        .valueOf("200004112012"), BLimpl.FALSE);
        
        literalIVL =
                IVLimpl.valueOf("[200004111950;200004112012[",
                        DatatypeMetadataFactoryDatatypes.instance().TSTYPE);
        
        blehIVL2 =
                IVLimpl.valueOf(BLimpl.FALSE, TSjuDateAdapter
                        .valueOf("200004111950"), TSjuDateAdapter
                        .valueOf("200004112012"), BLimpl.TRUE);
        literalIVL2 =
                IVLimpl.valueOf("]200004111950;200004112012]",
                        DatatypeMetadataFactoryDatatypes.instance().TSTYPE);
        
        blehIVL3 =
                IVLimpl.valueOf(BLimpl.FALSE, PQimpl.valueOf("250", "mg"),
                        PQimpl.valueOf("250", "mg"), BLimpl.TRUE);
        literalIVL3 =
                IVLimpl.valueOf("]250 mg;250 mg]",
                        DatatypeMetadataFactoryDatatypes.instance().PQTYPE);
        
        IVLimpl.valueOf(BLimpl.FALSE, PQimpl.valueOf("1", "kg"), PQimpl
                .valueOf("10", "kg"), BLimpl.TRUE);
        literalIVL4 =
                IVLimpl.valueOf("]1 kg;10 kg]",
                        DatatypeMetadataFactoryDatatypes.instance().PQTYPE);
        
        unionLow = PQimpl.valueOf(REALdoubleAdapter.valueOf(1), "s");
        unionHigh = PQimpl.valueOf(REALdoubleAdapter.valueOf(20), "s");
        unionIVL =
                IVLimpl.valueOf(BLimpl.TRUE, unionLow, unionHigh, BLimpl.FALSE);
    }
    
    public void test_equal() {
        assertTrue(thisIVL.nonNull().isTrue());
        assertTrue(thatIVL.nonNull().isTrue());
        
        assertTrue(thisIVL.equal(thisIVL).isTrue());
        assertTrue(thatIVL.equal(thatIVL).isTrue());
        assertFalse(thisIVL.equal(thisIVL).isFalse());
        assertFalse(thatIVL.equal(thatIVL).isFalse());
        
        assertFalse(thisIVL.equal(thatIVL).isTrue());
        assertFalse(thatIVL.equal(thisIVL).isTrue());
        assertTrue(thisIVL.equal(thatIVL).isFalse());
        assertTrue(thatIVL.equal(thisIVL).isFalse());
        
        assertTrue(onlyLowIVL.center().isNull().isTrue());
        assertTrue(onlyLowIVL.width().isNull().isTrue());
        
        // check the literals
        assertTrue(blehIVL.equal(literalIVL).isTrue());
        assertTrue(blehIVL2.equal(literalIVL2).isTrue());
        assertTrue(blehIVL3.equal(literalIVL3).isTrue());
        assertTrue(literalIVL.equal(literalIVL2).isFalse());
        assertTrue(literalIVL2.equal(literalIVL3).isFalse());
        assertTrue(literalIVL3.equal(literalIVL4).isFalse());
    }
    
    public void test_intersectionIVL() {
        assertTrue(thisIVL.equal(thisIVL.intersection(thisIVL)).isTrue());
        assertTrue(thatIVL.equal(thatIVL.intersection(thatIVL)).isTrue());
        assertFalse(thisIVL.equal(thisIVL.intersection(thisIVL)).isFalse());
        assertFalse(thatIVL.equal(thatIVL.intersection(thatIVL)).isFalse());
        
        assertTrue(thisIVL.equal(thisIVL.intersection(thatIVL)).isFalse());
        assertFalse(thisIVL.equal(thisIVL.intersection(thatIVL)).isTrue());
        assertTrue(thatIVL.equal(thatIVL.intersection(thisIVL)).isTrue());
        assertFalse(thatIVL.equal(thatIVL.intersection(thisIVL)).isFalse());
    }
    
    public void test_hullIVL() {
        
        assertTrue(thisIVL.equal(thisIVL.hull(thisIVL)).isTrue());
        assertTrue(thatIVL.equal(thatIVL.hull(thatIVL)).isTrue());
        assertFalse(thisIVL.equal(thisIVL.hull(thisIVL)).isFalse());
        assertFalse(thatIVL.equal(thatIVL.hull(thatIVL)).isFalse());
        
        assertTrue(thisIVL.equal(thisIVL.hull(thatIVL)).isTrue());
        assertFalse(thisIVL.equal(thisIVL.hull(thatIVL)).isFalse());
        
        assertTrue(thatIVL.equal(thisIVL.hull(thatIVL)).isFalse());
        assertFalse(thatIVL.equal(thisIVL.hull(thatIVL)).isTrue());
        
        assertTrue(thatIVL.equal(thatIVL.hull(thisIVL)).isFalse());
        assertFalse(thatIVL.equal(thatIVL.hull(thisIVL)).isTrue());
    }
    
    public void test_containsElement() {
        assertTrue(thisIVL.contains(thisLow).isTrue());
        assertTrue(thisIVL.contains(thisHigh).isTrue());
        assertFalse(thisIVL.contains(thisLow).isFalse());
        assertFalse(thisIVL.contains(thisHigh).isFalse());
        
        assertTrue(thatIVL.contains(thatLow).isFalse());
        assertTrue(thatIVL.contains(thatHigh).isFalse());
        assertFalse(thatIVL.contains(thatLow).isTrue());
        assertFalse(thatIVL.contains(thatHigh).isTrue());
        
        assertTrue(thatIVL.contains(thisLow).isFalse());
        assertTrue(thatIVL.contains(thisHigh).isFalse());
        assertFalse(thatIVL.contains(thisLow).isTrue());
        assertFalse(thatIVL.contains(thisHigh).isTrue());
        
        assertTrue(thisIVL.contains(thatLow).isTrue());
        assertTrue(thisIVL.contains(thatHigh).isTrue());
        assertFalse(thisIVL.contains(thatLow).isFalse());
        assertFalse(thisIVL.contains(thatHigh).isFalse());
        
    }
    
    public void test_containsIVL() {
        assertTrue(thisIVL.contains(thatIVL).isTrue());
        assertFalse(thisIVL.contains(thatIVL).isFalse());
        assertTrue(thatIVL.contains(thisIVL).isFalse());
        assertFalse(thatIVL.contains(thisIVL).isTrue());
        
    }
    
    public void test_union() {
        assertTrue(thisIVL.equal(thisIVL.union(thisIVL)).isTrue());
        assertFalse(thisIVL.equal(thisIVL.union(thisIVL)).isFalse());
        
        // TODO: someone verify this should be an unsupported operation
        try {
            thatIVL.union(thatIVL);
            fail("Should have thrown "
                    + UnsupportedOperationException.class.getName());
        } catch (UnsupportedOperationException success) {
            // expected
        }
        
        assertTrue(thisIVL.equal(thatIVL.union(thisIVL)).isTrue());
        assertFalse(thisIVL.equal(thatIVL.union(thisIVL)).isFalse());
        
        assertTrue(thatIVL.equal(thatIVL.union(thisIVL)).isFalse());
        assertFalse(thatIVL.equal(thatIVL.union(thisIVL)).isTrue());
        
        assertTrue(unionIVL.equal(thisIVL.union(otherIVL)).isTrue());
        assertFalse(unionIVL.equal(thisIVL.union(otherIVL)).isFalse());
    }
}

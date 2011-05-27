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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.hl7.types.INT;
import org.hl7.types.SET;
import org.hl7.types.ST;

/**
 * * A test suite that exercises {@link SETjuSetAdapter SETjuSetAdapter}class.
 * 
 * @author Geoffry Roberts
 * @author nradov
 */
public class SETjuSetAdapterTest extends TestCase {
    private final static ST ST_A = STjlStringAdapter.valueOf("a_Value");
    
    private final static ST ST_B = STjlStringAdapter.valueOf("b_Value");
    
    private final static ST ST_C = STjlStringAdapter.valueOf("c_Value");
    
    private final static ST ST_D = STjlStringAdapter.valueOf("d_Value");
    
    private static final SET<ST> THIS_SET =
            SETjuSetAdapter.valueOf(new HashSet<ST>(Arrays.asList(new ST[] {
                    ST_A, ST_B, ST_C })));
    
    private static final SET<ST> THAT_SET =
            SETjuSetAdapter.valueOf(new HashSet<ST>(Arrays.asList(new ST[] {
                    ST_A, ST_B, ST_D })));
    
    public void test_containsElement() {
        assertEquals(BLimpl.TRUE, THIS_SET.contains(ST_A));
        assertEquals(BLimpl.FALSE, THIS_SET.contains(ST_D));
    }
    
    public void test_containsSET() {
        final Set<ST> subSet = new HashSet<ST>();
        subSet.add(ST_A);
        subSet.add(ST_C);
        final SET<ST> subSET = SETjuSetAdapter.valueOf(subSet);
        assertEquals(BLimpl.TRUE, THIS_SET.contains(subSET));
        assertEquals(BLimpl.FALSE, THAT_SET.contains(subSET));
    }
    
    public void testEmptiness() {
        final SET<ST> emptySET = SETjuSetAdapter.valueOf(new HashSet<ST>());
        assertEquals(BLimpl.TRUE, emptySET.isEmpty());
        assertEquals(BLimpl.FALSE, THIS_SET.isEmpty());
        assertEquals(BLimpl.TRUE, THIS_SET.nonEmpty());
        assertEquals(BLimpl.FALSE, emptySET.nonEmpty());
        
    }
    
    public void test_equal() {
        final SETjuSetAdapter<ST> tempSET = (SETjuSetAdapter<ST>) THIS_SET;
        final SET<ST> cloneSET = SETjuSetAdapter.valueOf(tempSET);
        assertEquals(BLimpl.TRUE, cloneSET.equal(THIS_SET));
        assertEquals(BLimpl.TRUE, THIS_SET.equal(THIS_SET));
        assertEquals(BLimpl.FALSE, cloneSET.equal(THAT_SET));
    }
    
    public void test_cardinality() {
        final INT I = THIS_SET.cardinality();
        assertEquals(BLimpl.TRUE, I.equal(3));
    }
    
    public void test_union() {
        final SET<ST> tempSET = THIS_SET.union(THAT_SET);
        final INT size = tempSET.cardinality();
        assertTrue(size.equal(4).isTrue());
        assertFalse(size.equal(4).isFalse());
    }
    
    public void test_exceptElement() {
        final SET<ST> exceptSET = THIS_SET.except(ST_A);
        assertEquals(BLimpl.FALSE, exceptSET.contains(ST_A));
    }
    
    public void test_exceptSET() {
        final Set<ST> subSet = new HashSet<ST>();
        subSet.add(ST_A);
        subSet.add(ST_C);
        final SET<ST> subSET = SETjuSetAdapter.valueOf(subSet);
        final SET<ST> exceptSET = THIS_SET.except(subSET);
        assertEquals(BLimpl.FALSE, exceptSET.contains(subSET));
    }
    
    public void test_intersection() {
        final Set<ST> subSet = new HashSet<ST>();
        subSet.add(ST_A);
        subSet.add(ST_C);
        final SET<ST> subSET = SETjuSetAdapter.valueOf(subSet);
        final SET<ST> intersectSET = THIS_SET.intersection(subSET);
        assertEquals(BLimpl.TRUE, intersectSET.contains(subSET));
        assertEquals(BLimpl.FALSE, intersectSET.contains(ST_B));
    }
}

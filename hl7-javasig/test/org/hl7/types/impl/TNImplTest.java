package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.TN;

/**
 * This class defines unit test code against data type Trivial Name ({@link TN}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12538 $ date $Date: 2007-03-30
 *          08:35:44 -0700 (Fri, 30 Mar 2007) $
 */
public class TNImplTest extends TestCase {
    // identical but do not share any member instance.
    private final TN instance1 = TNjlStringAdapter.valueOf("a simple name");
    private final TN instance2 = TNjlStringAdapter.valueOf("a simple name");
    
    private final TN instance3 = TNjlStringAdapter.valueOf("A SIMPLE NAME");
    private final TN instance4 = TNjlStringAdapter.valueOf(null);
    private final TN instance5 = TNjlStringAdapter.valueOf(null);
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
    }
    
    public void testNULLEquals() {
        assertTrue(instance4.equals(instance5));
    }
    
    public void testNotEquals() {
        assertFalse(instance1.equals(instance3));
    }
    
    public void testHashCodeEquals() {
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }
    
    public void testStringEquals() {
        assertEquals(instance1.toString(), instance2.toString());
    }
}
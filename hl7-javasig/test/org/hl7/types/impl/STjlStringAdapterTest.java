package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.ST;

/**
 * This class defines unit test code against data type Character String ({@link ST}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12551 $ date $Date:
 *          2008-01-15 11:08:59 -0800 (Tue, 15 Jan 2008) $
 */
public class STjlStringAdapterTest extends TestCase {
    // identical but do not share any member instance.
    private final ST instance1 = STjlStringAdapter.valueOf("30971-6");
    private final ST instance2 = STjlStringAdapter.valueOf("30971-6");
    
    private final ST instance3 = STjlStringAdapter.valueOf("30971-6-7");
    private final ST instance4 = STjlStringAdapter.valueOf(null);
    private final ST instance5 = STjlStringAdapter.valueOf(null);
    
    public void testNullEquals() {
        assertTrue(instance4.equals(instance5));
    }
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
    }
    
    public void testNotEquals() {
        assertFalse(instance1.equals(instance3));
    }
    
    public void testHashCodeEquals() {
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }
}
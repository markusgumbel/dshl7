package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.SC;

/**
 * This class defines unit test code against data type Character String with
 * Code ({@link SC}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12539 $ date $Date:
 *          2008-01-15 11:08:59 -0800 (Tue, 15 Jan 2008) $
 */
public class SCImplTest extends TestCase {
    // identical but do not share any member instance.
    private final SC instance1 = SCimpl.valueOf("30971-6");
    private final SC instance2 = SCimpl.valueOf("30971-6");
    
    private final SC instance3 = SCimpl.valueOf("30971-6-7");
    private final SC instance4 = SCimpl.valueOf(null);
    private final SC instance5 = SCimpl.valueOf("");
    
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
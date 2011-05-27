package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.INT;

/**
 * This class defines unit test code against data type Integer Number ({@link INT}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12538 $ date $Date: 2007-03-30
 *          08:35:44 -0700 (Fri, 30 Mar 2007) $
 */
public class INTImplTest extends TestCase {
    // identical but do not share any member instance.
    private final INT instance1 = INTlongAdapter.valueOf(100);
    private final INT instance2 = INTlongAdapter.valueOf("100");
    
    private final INT instance3 = INTlongAdapter.valueOf(101);
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
    }
    
    public void testSuccessorEquals() {
        assertTrue(instance2.successor().equals(instance3));
    }
    
    public void testPreccessorEquals() {
        assertTrue(instance3.predecessor().equals(instance2));
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
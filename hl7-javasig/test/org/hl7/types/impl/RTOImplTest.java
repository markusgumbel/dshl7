package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.RTO;

/**
 * This class defines unit test code against data type Ratio (RTO).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12540 $ date $Date:
 *          2008-01-15 11:08:59 -0800 (Tue, 15 Jan 2008) $
 */
public class RTOImplTest extends TestCase {
    // identical but do not share any member instance.
    private RTO instance1 =
            RTOimpl.valueOf(INTlongAdapter.valueOf(10), INTlongAdapter
                    .valueOf(100));
    private RTO instance2 =
            RTOimpl.valueOf(INTlongAdapter.valueOf(10), INTlongAdapter
                    .valueOf(100));
    
    private RTO instance3 =
            RTOimpl.valueOf(INTlongAdapter.valueOf(20), INTlongAdapter
                    .valueOf(200));
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
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
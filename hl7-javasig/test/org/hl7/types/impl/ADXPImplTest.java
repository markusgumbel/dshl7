package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.ADXP;

/**
 * This class defines unit test code against data type Address Line (ADXP).
 * 
 * @author Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12538 $ date $Date: 2007-03-30
 *          08:35:44 -0700 (Fri, 30 Mar 2007) $
 */
public class ADXPImplTest extends TestCase {
    // identical but do not share any member instance.
    private ADXP instance1 = ADXPimpl.valueOf("123 Main Street");
    private ADXP instance2 = ADXPimpl.valueOf("123 Main Street");
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
    }
    
    public void testHashCodeEquals() {
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }
    
    public void testStringEquals() {
        assertEquals(instance1.toString(), instance2.toString());
    }
    
}

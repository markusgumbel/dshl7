package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.CE;

/**
 * This class defines unit test code against data type Coded With Equivalents
 * ({@link CE}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12538 $ date $Date: 2007-03-30
 *          08:35:44 -0700 (Fri, 30 Mar 2007) $
 */
public class CEImplTest extends TestCase {
    // identical but do not share any member instance.
    private final CE instance1 =
            CEimpl.valueOf("30971-6", "2.16.840.1.113883.19",
                    "Coded With Equivalents (CE)");
    private final CE instance2 =
            CEimpl.valueOf("30971-6", "2.16.840.1.113883.19",
                    "Coded With Equivalents (CE)");
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
    }
    
    public void testHashCodeEquals() {
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }
}
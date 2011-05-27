package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.II;

/**
 * This class defines unit test code against data type Instance Identifier ({@link II}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12538 $ date $Date: 2007-03-30
 *          08:35:44 -0700 (Fri, 30 Mar 2007) $
 */
public class IIImplTest extends TestCase {
    // identical but do not share any member instance.
    private final II instance1 =
            IIimpl.valueOf("root123", "extension123", "MyAuthority");
    private final II instance2 =
            IIimpl.valueOf("root123", "extension123", "MyAuthority");
    
    private final II instance3 =
            IIimpl.valueOf("ROOT123", "EXTENSION123", "My_Authority");
    private final II instance4 = IIimpl.valueOf((String) null, null);
    private final II instance5 = IIimpl.valueOf((String) null, null);
    
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
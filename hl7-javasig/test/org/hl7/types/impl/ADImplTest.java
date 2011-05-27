package org.hl7.types.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.hl7.types.AD;
import org.hl7.types.ADXP;
import org.hl7.types.CS;
import org.hl7.types.DSET;

/**
 * This class defines unit test code against data type Postal Address (AD).
 * 
 * @author Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12542 $ date $Date:
 *          2007-09-26 12:28:36 -0700 (Wed, 26 Sep 2007) $
 */
public class ADImplTest extends TestCase {
    // identical but do not share any member instance.
    private final AD instance1 =
            ADimpl.valueOf(constructAddressList(), constructSETofCode());
    private final AD instance2 =
            ADimpl.valueOf(constructAddressList(), constructSETofCode());
    
    /**
     * Construct a collection of address components. Even though the contained
     * values will always be the same, we want to create different objects for
     * equality testing.
     */
    private List<ADXP> constructAddressList() {
        final List<ADXP> addressList = new ArrayList<ADXP>();
        addressList.add(ADXPimpl.valueOf("123 Main Street"));
        addressList.add(ADXPimpl.valueOf("Some City, Some State"));
        addressList.add(ADXPimpl.valueOf("12345-6789"));
        addressList.add(ADXPimpl.valueOf("U.S.A"));
        return addressList;
    }
    
    private DSET<CS> constructSETofCode() {
        final Set<CS> resultSet = new LinkedHashSet<CS>();
        for (int i = 0; i < 3; i++) {
            resultSet.add(CSimpl.valueOf("code" + i, "system_test"));
        }
        return new SETjuSetAdapter<CS>(resultSet);
    }
    
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
package org.hl7.types.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import org.hl7.types.PN;
import org.hl7.types.PNXP;

/**
 * This class defines unit test code against data type Person Name (PN).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12541 $ date $Date:
 *          2008-01-15 11:08:59 -0800 (Tue, 15 Jan 2008) $
 */
public class PNImplTest extends TestCase {
    // identical but do not share any member instance.
    private final PN instance1 = PNimpl.valueOf(constructElementList(false));
    private final PN instance2 = PNimpl.valueOf(constructElementList(false));
    
    private final PN instance3 = PNimpl.valueOf(constructElementList(true));
    private final PN instance4 = PNimpl.valueOf((List<PNXP>) null);
    private final PN instance5 = PNimpl.valueOf((List<PNXP>) null);
    
    private final static String FIRST_STRING = "John";
    private final static String SECOND_STRING = "William";
    private final static String THIRD_STRING = "Smith";
    
    private List<PNXP> constructElementList(final boolean allCap) {
        final List<PNXP> elementList = new ArrayList<PNXP>();
        if (allCap) {
            elementList.add(PNXPimpl.valueOf(FIRST_STRING.toUpperCase(Locale.US)));
            elementList.add(PNXPimpl.valueOf(SECOND_STRING.toUpperCase(Locale.US)));
            elementList.add(PNXPimpl.valueOf(THIRD_STRING.toUpperCase(Locale.US)));
        } else {
            elementList.add(PNXPimpl.valueOf(FIRST_STRING));
            elementList.add(PNXPimpl.valueOf(SECOND_STRING));
            elementList.add(PNXPimpl.valueOf(THIRD_STRING));
        }
        return elementList;
    }
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
    }
    
    public void testNULLEquals() {
        assertTrue(instance4.equals(instance5));
    }
    
    public void testNotEquals() {
        assertFalse(instance1.equals(instance3));
    }
    
    /*
     * No hash code test since the underlying data structure uses java.util.List
     */
}
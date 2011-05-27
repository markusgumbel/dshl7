package org.hl7.types.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import org.hl7.types.ON;
import org.hl7.types.ONXP;

/**
 * This class defines unit test code against data type Organization Name (ON).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12531 $ date $Date: 2007-07-22
 *          07:15:47 -0700 (Sun, 22 Jul 2007) $
 */
public class ONImplTest extends TestCase {
    // identical but do not share any member instance.
    private ON instance1 = ONimpl.valueOf(getElementList(false));
    private ON instance2 = ONimpl.valueOf(getElementList(false));
    
    private ON instance3 = ONimpl.valueOf(getElementList(true));
    private ON instance4 = ONimpl.valueOf((List<ONXP>) null);
    private ON instance5 = ONimpl.valueOf((List<ONXP>) null);
    
    private static final String FIRST_STRING = "Health";
    private static final String SECOND_STRING = "Level";
    private static final String THIRD_STRING = "Seven";
    
    private List<ONXP> getElementList(final boolean allCap) {
        final List<ONXP> elementList = new ArrayList<ONXP>();
        if (allCap) {
            elementList.add(ONXPimpl.valueOf(FIRST_STRING
                    .toUpperCase(Locale.US)));
            elementList.add(ONXPimpl.valueOf(SECOND_STRING
                    .toUpperCase(Locale.US)));
            elementList.add(ONXPimpl.valueOf(THIRD_STRING
                    .toUpperCase(Locale.US)));
        } else {
            elementList.add(ONXPimpl.valueOf(FIRST_STRING));
            elementList.add(ONXPimpl.valueOf(SECOND_STRING));
            elementList.add(ONXPimpl.valueOf(THIRD_STRING));
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
package org.hl7.types.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hl7.types.ADXP;
import org.hl7.types.ANY;
import org.hl7.types.BAG;

/**
 * This class defines unit test code against data type {@link BAG}.
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 12511 $ date $Date:
 *          2008-01-14 11:23:45 -0800 (Mon, 14 Jan 2008) $
 */
public class BAGImplTest extends TestCase {
    public void testContains() {
        final ADXP data = ADXPimpl.valueOf("123 Main Street");
        final List<ADXP> dataList = new ArrayList<ADXP>();
        dataList.add(data);
        final BAG<ADXP> oneBag = new BAGjuListAdapter<ADXP>(dataList);
        assertTrue(oneBag.contains(data).isZero().isFalse());
    }
    
    public void testIsEmpty() {
        final List<ANY> dataList = new ArrayList<ANY>();
        final BAG<ANY> oneBag = new BAGjuListAdapter<ANY>(dataList);
        assertTrue(oneBag.isEmpty().isTrue());
    }
}

package org.hl7.types.impl;

import java.util.Calendar;

import junit.framework.TestCase;

import org.hl7.types.TS;

/**
 * This class defines unit test code against data type Point in Time ({@link TS}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: amartin $
 * @version Since HL7 SDK v1.2 revision $Revision: 14484 $ date $Date: 2007-03-30
 *          08:35:44 -0700 (Fri, 30 Mar 2007) $
 */
public class TSImplTest extends TestCase {
    // identical but do not share any member instance.
    // format of "yyyyMMddHHmmss.SSS"
    // 2002/01/31 18:38:28.789
    private final TS instance1 = TSjuDateAdapter.valueOf("20020131183828.789");
    private final TS instance2 = TSjuDateAdapter.valueOf("20020131183828.789");
    
    // 2002/01/31 18:38:28.700
    private final TS instance3 = TSjuDateAdapter.valueOf("20020131183828.700");
    private final TS instance4 = TSjuDateAdapter.valueOf((String) null);
    private final TS instance5 = TSjuDateAdapter.valueOf("");
    
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

    public void testParse() {
    	compareParsed("2002");
    	compareParsed("2002+0100");
    	compareParsed("200201");
    	compareParsed("200201+0100");
    	compareParsed("20020131");
    	compareParsed("20020131+0100");
    	compareParsed("20020131183828");
    	compareParsed("20020131183828+0100");
    	compareParsed("20020131183828.7");
    	compareParsed("20020131183828.7+0100");
    	compareParsed("20020131183828.78");
    	compareParsed("20020131183828.78+0100");
    	compareParsed("20020131183828.789+0100");
    	compareParsed("20020131183828.789-0430");
    }

    public void compareParsed(String s) {
    	TS ts = TSjuDateAdapter.valueOf(s);
    	boolean preserveTimezone = s.indexOf('+') >= 0 || s.indexOf('-') >= 0;
    	Calendar cal = ts.toCalendar();
    	TS tsFromCalendar = TSjuDateAdapter.valueOf(cal, ts.precision(), preserveTimezone);
    	// Conversion to and from Date does not preserve timezone
    	//TS tsFromDate = TSjuDateAdapter.valueOf(ts.toDate(), ts.precision(), preserveTimezone);

    	assertEquals(s, ts.toString()); // Make sure that toString is the same as the input String

    	assertEquals(ts, tsFromCalendar); // Make sure that the TS created from the Calendar represents the same timestamp
    	assertEquals(s, tsFromCalendar.toString());
    	assertNotSame(cal, tsFromCalendar.toCalendar()); // Make sure that toCalendar() returns a different Calendar instance

    	//assertEquals(ts, tsFromDate);
    	//assertEquals(s, tsFromDate.toString());
    }

    public void testCalendar() {
    	Calendar cal;
    	TS ts;
    	final String expected = "20020131183828.789";

    	cal = Calendar.getInstance();
    	cal.clear();
    	cal.set(Calendar.YEAR, 2002);
    	cal.set(Calendar.MONTH, 0); // (January)
    	cal.set(Calendar.DAY_OF_MONTH, 31);
    	cal.set(Calendar.HOUR_OF_DAY, 18);
    	cal.set(Calendar.MINUTE, 38);
    	cal.set(Calendar.SECOND, 28);
    	cal.set(Calendar.MILLISECOND, 789);
    	ts = TSjuDateAdapter.valueOf(cal);
    	assertEquals(expected, ts.toString());
    	ts = TSjuDateAdapter.valueOf(cal.getTime());
    	assertEquals(expected, ts.toString());
    }
}
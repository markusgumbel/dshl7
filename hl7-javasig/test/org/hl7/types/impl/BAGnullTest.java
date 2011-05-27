package org.hl7.types.impl;

import junit.framework.TestCase;

/**
 * Test the {@link BAGnull} class.
 * 
 * @author nradov
 */
public class BAGnullTest extends TestCase {

    /**
     * Test the {@link BAGnull#valueOf(String)} method.
     */
    public void testValueOf() {
	assertEquals(NullFlavorImpl.NI, BAGnull.valueOf("NI").nullFlavor());
	assertEquals(NullFlavorImpl.UNK, BAGnull.valueOf("UNK").nullFlavor());

	try {
	    BAGnull.valueOf("???");
	    fail("should throw "
		    + IllegalArgumentException.class.getSimpleName());
	} catch (final IllegalArgumentException e) {
	    // expected
	}
    }

}

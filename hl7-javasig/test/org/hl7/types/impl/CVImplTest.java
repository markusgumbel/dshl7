
package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.CV;

/**
 * This class defines unit test code against data type Coded Value (CV).
 *
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2
 *          revision    $Revision: 12538 $
 *          date        $Date: 2008-01-15 14:08:59 -0500 (Tue, 15 Jan 2008) $
 */
public class CVImplTest extends TestCase
{
	//identical but do not share any member instance.
	private CV instance1 = null;
	private CV instance2 = null;

	public void setUp() throws Exception
	{
		super.setUp();
		instance1 = CVimpl.valueOf("30971-6", "2.16.840.1.113883.19");
		instance2 = CVimpl.valueOf("30971-6", "2.16.840.1.113883.19");
	}

	public void testEquals()
	{
		assertEquals(instance1.equals(instance2), true);
	}

	public void testHashCodeEquals()
	{
		assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	public void testStringEquals()
	{
		assertEquals(instance1.toString(), instance2.toString());
	}

	public void tearDown() throws Exception
	{
		instance1 = null;
		instance2 = null;
		super.tearDown();
	}

}

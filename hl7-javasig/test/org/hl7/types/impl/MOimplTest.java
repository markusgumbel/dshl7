/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is .
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.types.impl;

import junit.framework.TestCase;

/**
 * * A test suite that exercises {@link PQimpl}class.
 * 
 * @author Geoffry Roberts
 */
public class MOimplTest extends TestCase
{
    MOimpl thisMO;

    MOimpl thatMO;

    MOimpl otroMO;

    public void setUp()
    {
        thisMO = (MOimpl) MOimpl.valueOf(REALdoubleAdapter.valueOf(1), CSimpl.valueOf("USD", "sys"));
        thatMO = (MOimpl) MOimpl.valueOf(REALdoubleAdapter.valueOf(4), CSimpl.valueOf("USD", "sys"));
        otroMO = (MOimpl) MOimpl.valueOf(REALdoubleAdapter.valueOf(4), CSimpl.valueOf("MXN", "sys"));
    }

    public void test_equal()
    {
        assertTrue(thisMO.equal(thisMO).isTrue());
        assertTrue(thisMO.equal(thatMO).isFalse());
        assertFalse(thisMO.equal(thisMO).isFalse());
        assertFalse(thisMO.equal(thatMO).isTrue());
    }

    public void test_lessOrEqual()
    {
        assertTrue(thisMO.lessOrEqual(thisMO).isTrue());
        assertTrue(thatMO.lessOrEqual(thisMO).isFalse());
        assertFalse(thisMO.lessOrEqual(thisMO).isFalse());
        assertFalse(thatMO.lessOrEqual(thisMO).isTrue());
    }

    public void test_plus()
    {
        double d = 8;
        double delta = 0;
        assertEquals(thatMO.plus(thatMO).value().doubleValue(), d, delta);
    }

    public void test_minus()
    {
        double d = 0;
        double delta = 0;
        assertEquals(thatMO.minus(thatMO).value().doubleValue(), d, delta);
    }
}

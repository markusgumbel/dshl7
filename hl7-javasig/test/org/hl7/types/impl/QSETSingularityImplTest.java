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
 * test to exercise QSETs
 */
public class QSETSingularityImplTest extends TestCase
{

    /*
     * Build a QSETSingularityImpl to test
     */
    QSETSingularityImpl singularity = QSETSingularityImpl.valueOf(TSjuDateAdapter.valueOf("200501300755"));

    public void test_nextTo()
    {
        assertEquals(QSETSingularityImpl.class, singularity.nextTo(TSjuDateAdapter.valueOf("198106151200")).getClass());
        assertEquals(IVLnull.class, singularity.nextTo(TSjuDateAdapter.valueOf("200606151200")).getClass());
    }

    public void test_nextAfter()
    {
        assertEquals(QSETSingularityImpl.class, singularity.nextAfter(TSjuDateAdapter.valueOf("198106151200")).getClass());
        assertEquals(IVLnull.class, singularity.nextAfter(TSjuDateAdapter.valueOf("200506151200")).getClass());
    }

}

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
package org.hl7.types;

import junit.framework.TestCase;

/**
 * * A test suite that exercises
 * {@link UID}
 * class.
 *
 * @author Gunther Schadow
 */
public class UIDtest extends TestCase {

  private ValueFactory valueFactory;
  private UID thingOne;
  private UID thingTwo;
  private UID catInTheHat;

  public UIDtest(final String name) {
    super(name);
  }

  public void setUp() throws CodeValueFactory.Exception {
    valueFactory = ValueFactory.getInstance();
    thingOne = valueFactory.UIDvalueOfLiteral("thing");
    thingTwo = valueFactory.UIDvalueOfLiteral("thi" + "ng");
    catInTheHat = valueFactory.UIDvalueOfLiteral("catInTheHat");
  }

  public void test_equal() {
    assertTrue(thingOne.equal(thingTwo).isTrue());
    assertTrue(thingOne.equal(catInTheHat).isFalse());
  }

  public void test_equals() {
    assertTrue(thingOne.equals(thingTwo));
    assertFalse(thingOne.equals(catInTheHat));
  }

  public void test_hashCode() {
    assertTrue(thingOne.hashCode() == thingTwo.hashCode());
    assertTrue(thingOne.hashCode() != catInTheHat.hashCode());
  }
}

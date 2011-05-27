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


/**
 * 
 * A monetary amount is a quantity expressing the amount of
 * money in some currency. Currencies are the units in which
 * monetary amounts are denominated in different economic
 * regions. While the monetary amount is a single kind of
 * quantity (money) the exchange rates between the different
 * units are variable.  This is the principle difference
 * between physical quantity and monetary amounts, and the
 * reason why currency units are not physical units.
 * 
 * @generatedComment
 */
public interface MO extends QTY.diff {
  /**
   * 
   * The magnitude of the monetary amount in terms of the
   * currency unit.
   * 
   * @generatedComment
   */
  REAL value();
  /**
   * 
   * The currency unit as defined in ISO 4217.
   * 
   * @generatedComment
   */
  CS   currency();

  MO  plus(MO x);
  MO  minus(MO x);
  MO  negated();
  MO  times(REAL x);
}

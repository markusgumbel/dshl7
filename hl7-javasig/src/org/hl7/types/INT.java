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
 * Integer numbers (-1,0,1,2, 100, 3398129, etc.) are precise
 * numbers that are results of counting and enumerating.
 * Integer numbers are discrete, the set of integers is
 * infinite but countable.  No arbitrary limit is imposed on
 * the range of integer numbers. Two NULL flavors are
 * defined for the positive and negative infinity.
 * 
 * @generatedComment
 */
public interface INT extends QTY.diff, Numeric {
  INT   successor();

  /** This is a constrained form of QTY.plus(QTY.diff) that we defined
      such that we can tell the compiler that our return type is
      actually INT if the compiler knows that the argument is INT.  
  */
  INT   plus(INT that);
  INT   plus(int that);
  INT   plus(long that);

  /** This is a constrained form of both QTY.minus(QTY.diff) and
      QTY.minus(QTY) that we defined such that we can tell the
      compiler that our return type is actually INT if the compiler
      knows that the argument is INT. 
  */
  INT   minus(INT that);
  INT   minusReverse(int that);
  INT   minusReverse(long that);

  /** Multiplication
  */
  INT   times(REAL that);
  INT   times(INT that);
  INT   times(int that);
  INT   times(long that);

  /** Integer division.  
  */
  INT   dividedBy(INT that);
  INT   dividedByReverse(int that);
  INT   dividedByReverse(long that);

  INT   negated();
  INT   predecessor();
  BL    isOne();
  BL    isNegative();
  BL    nonNegative();

  String toString();
  int intValue();
  long longValue();
  float floatValue();
  double doubleValue();

  REAL toREAL();
}

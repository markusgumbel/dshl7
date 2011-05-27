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

/** This interface is defined to plug in a bunch of methods useful
    in Java. These methods are like the methods that use HL7 types
    as arguments, but they use Java builtins. That way we can speed
    up some work and it's easier for the programmer since they can
    use Java literals for those builtins.
*/
public interface Numeric {
  BL equal(int x);
  BL equal(long x);
  BL equal(float x);
  BL equal(double x);
  BL compares(long x);
  BL lessOrEqual(int x);
  BL lessOrEqual(long x);
  BL lessOrEqual(float x);
  BL lessOrEqual(double x);
  QTY plus(int x);
  QTY plus(long x);
  QTY plus(float x);
  QTY plus(double x);
  QTY minusReverse(int x);
  QTY minusReverse(long x);
  QTY minusReverse(float x);
  QTY minusReverse(double x);
}
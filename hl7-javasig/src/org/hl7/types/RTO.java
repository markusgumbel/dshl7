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
 * A quantity constructed as the quotient of a numerator
 * quantity divided by a denominator quantity. Common
 * factors in the numerator and denominator are not
 * automatically cancelled out.   supports titers
 * (e.g., "1:128") and other quantities produced by
 * laboratories that truly represent ratios. Ratios are
 * not simply "structured numerics", particularly blood
 * pressure measurements (e.g. "120/60") are not ratios.
 * In many cases REAL should be used instead
 * of .
 * 
 * @generatedComment
 */
public interface RTO extends QTY.diff
{
  QTY.diff numerator();
  QTY.diff denominator();
}

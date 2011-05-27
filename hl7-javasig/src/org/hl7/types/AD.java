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
 * Mailing and home or office addresses. A sequence of
 * address parts, such as street or post office Box, city,
 * postal code, country, etc.
 * 
 * @generatedComment
 */
public interface AD extends LIST<ADXP> {
  /**
   * 
   * A set of codes advising a system or user which address
   * in a set of like addresses to select for a given purpose.
   * 
   * @generatedComment
   */
  DSET<CS> use();
  QSET<TS> validTime();
  /**
   * 
   * A boolean value specifying whether the order of the
   * address parts is known or not. While the address parts
   * are always a Sequence, the order in which they are
   * presented may or may not be known. Where this matters,
   * can be used to convey this
   * information.
   * 
   * @generatedComment
   */
  BL isNotOrdered();
  ST formatted();
}

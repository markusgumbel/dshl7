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
 * An identifier that uniquely identifies a thing or object.
 * Examples are object identifier for HL7 RIM objects,
 * medical record number, order id, service catalog item id,
 * Vehicle Identification Number (VIN), etc. Instance
 * identifiers are defined based on ISO object identifiers.
 * 
 * @generatedComment
 */
public interface II extends ANY {
  /**
   * 
   * A character string as a unique identifier within the
   * scope of the identifier root.
   * 
   * @generatedComment
   */
  ST  extension();
  /**
   * 
   * A unique identifier that guarantees the global uniqueness
   * of the instance identifier. The root alone may be the
   * entire instance identifier.
   * 
   * @generatedComment
   */
  UID root();
  /**
   * 
   * A human readable name or mnemonic for the assigning
   * authority. This name may be provided solely for the
   * convenience of unaided humans interpreting an  value
   * and can have no computational meaning. Note: no
   * automated processing must depend on the assigning
   * authority name to be present in any form.
   * 
   * @generatedComment
   */
  ST  assigningAuthorityName();
  /**
   * 
   * Specifies if the identifier is intended for human
   * display and data entry (displayable = true) as
   * opposed to pure machine interoperation (displayable
   * = false).
   * 
   * @generatedComment
   */
  BL  displayable();  
  // redefine BL  equal(II x);
}

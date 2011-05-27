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
 * A concept qualifier code with optionally named role.
 * Both qualifier role and value codes must be defined by
 * the coding system.  For example, if SNOMED RT defines a
 * concept "leg", a role relation "has-laterality", and
 * another concept "left", the concept role relation allows
 * to add the qualifier "has-laterality: left" to a primary
 * code "leg" to construct the meaning "left leg".
 * 
 * @generatedComment
 */
public interface CR extends ANY {
  CV  name();
  /**
   * 
   * Indicates if the sense of the role name is inverted.
   * This can be used in cases where the underlying code
   * system defines inversion but does not provide reciprocal
   * pairs of role names. By default, inverted is false.
   * 
   * @generatedComment
   */
  BL  inverted();
  CD  value();
}


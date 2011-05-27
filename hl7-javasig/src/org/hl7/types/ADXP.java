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
 * A character string that may have a type-tag signifying its
 * role in the address. Typical parts that exist in about
 * every address are street, house number, or post box,
 * postal code, city, country but other roles may be defined
 * regionally, nationally, or on an enterprise level (e.g. in
 * military addresses). Addresses are usually broken up into
 * lines, which are indicated by special line-breaking
 * delimiter elements (e.g., DEL).
 * 
 * @generatedComment
 */
public interface ADXP extends ST {
  CS type();
}

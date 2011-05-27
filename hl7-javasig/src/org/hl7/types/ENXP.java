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
 * A character string token representing a part of a name.
 * May have a type code signifying the role of the part in
 * the whole entity name, and a qualifier code for more detail
 * about the name part type. Typical name parts for person
 * names are given names, and family names, titles, etc.
 * 
 * @generatedComment
 */
public interface ENXP extends ST {
  CS type();

  /**
   * is a set of codes each of which specifies
   * a certain subcategory of the name part in addition to
   * the main name part type. For example, a given name may
   * be flagged as a nickname, a family name may be a
   * pseudonym or a name of public records.
   * 
   * @generatedComment
   */
  DSET<CS> qualifier();
}

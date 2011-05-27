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
 * A telephone number (voice or fax), e-mail address, or
 * other locator for a resource (information or service)
 * mediated by telecommunication equipment. The address
 * is specified as a URL
 * qualified by time specification and use codes that help
 * in deciding which address to use for a given time and
 * purpose.
 * 
 * @generatedComment
 */
public interface TEL extends URL {
  QSET<TS> useablePeriod();
  /**
   * 
   * One or more codes advising a system or user which
   * telecommunication address in a set of like addresses
   * to select for a given telecommunication need.
   * 
   * @generatedComment
   */
  DSET<? extends CS> use();
}

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
package org.hl7.meta;

/**
 * A read only interface to the class
 * {@link org.hl7.meta.impl.CmetAssociationImpl CmetAssociationImpl},
 * which represents an HL7 v3 association between a clone class and a CMET.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public interface CmetAssociation extends Association
{
  //-------------------------------------------------------------------------
  /**
   * Returns the far end CMET ID; for example, COCT_MT010101.
   * 
   * @return the CMET ID
   */
  public String getCmetId();
}

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
 * {@link org.hl7.meta.impl.AssociationImpl AssociationImpl}, which
 * represents an HL7 v3 association between two clone classes, or between a
 * clone class and a CMET.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public interface Association extends Feature
{
  //-------------------------------------------------------------------------
  /**
   * Returns the MET source enumeration for this association.
   * 
   * @return the MET source
   * @deprecated replaced by isRecursive
   */
  public MetSource getMetSource();

    /**
     *  Since the introduction of MIF file, the concept of MET source Reused and Recursive is replaced by reference 
     * @return is referenced before or not
     */
  public boolean isReference();

  /**
   * Returns the clone class that is targeted by this association.
   * 
   * @return the target clone class
   * @throws LoaderException  if on-demand loading fails for
   *   {@link CmetAssociation CmetAssociation}
   */
  public CloneClass getTarget() throws LoaderException;
  
  /**
   * @return the name of this association on the most specialized 
   * implementation of this association's owner.
   *
   * @author jmoore
   */
  public String getPropertyName();
}

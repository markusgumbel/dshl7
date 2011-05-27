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
package org.hl7.xml.builder;

import org.hl7.meta.ChoiceAssociation;

/**
 * ...
 * 
 * @author Skirmantas Kligys
 */
public class ChoiceAssociationException extends BuilderException
{
  //-------------------------------------------------------------------------
  /** Choice association definition in HMD. */
  private final ChoiceAssociation choiceAssociation_;

  //-------------------------------------------------------------------------
  public ChoiceAssociationException(ChoiceAssociation choiceAssociation)
  {
    super(createMessage(choiceAssociation));
    choiceAssociation_ = choiceAssociation;
  }

  //-------------------------------------------------------------------------
  public ChoiceAssociationException(ChoiceAssociation choiceAssociation,
    String message)
  {
    super(createMessage(choiceAssociation) + ": " + message);
    choiceAssociation_ = choiceAssociation;
  }

  //-------------------------------------------------------------------------
  public ChoiceAssociationException(ChoiceAssociation choiceAssociation,
    Exception ex)
  {
    super(ex, createMessage(choiceAssociation));
    choiceAssociation_ = choiceAssociation;
  }

  //-------------------------------------------------------------------------
  protected static String createMessage(ChoiceAssociation choiceAssociation)
  {
    return choiceAssociation.getParent().getName() + "." +
      choiceAssociation.getName();
  }

  //-------------------------------------------------------------------------
  /**
   * @return Returns the choice association.
   */
  public ChoiceAssociation getAssociation() { return choiceAssociation_; }
}

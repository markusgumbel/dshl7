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

import org.hl7.meta.Attribute;

/**
 * ...
 * 
 * @author Skirmantas Kligys
 */
public class AttributeBuilderException extends BuilderException
{
  //-------------------------------------------------------------------------
  /** Attribute definition in HMD. */
  private final Attribute attribute_;

  //-------------------------------------------------------------------------
  public AttributeBuilderException(Attribute attribute)
  {
    super(createMessage(attribute));
    attribute_ = attribute;
  }

  //-------------------------------------------------------------------------
  public AttributeBuilderException(Attribute attribute, String message)
  {
    super(createMessage(attribute) + ": " + message);
    attribute_ = attribute;
  }

  //-------------------------------------------------------------------------
  public AttributeBuilderException(Attribute attribute, Exception ex)
  {
    super(ex, createMessage(attribute));
    attribute_ = attribute;
  }

  //-------------------------------------------------------------------------
  protected static String createMessage(Attribute attribute)
  {
      return attribute.getParent().getName() + "." + attribute.getName();
  }

  //-------------------------------------------------------------------------
  /**
   * @return Returns the attribute.
   */
  public Attribute getAttribute() { return attribute_; }
}

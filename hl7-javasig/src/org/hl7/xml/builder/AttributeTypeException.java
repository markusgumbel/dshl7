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
 * Exception that is thrown when an attribute data type specified in HMD
 * and values present in RIM object are incompatible.
 * 
 * @author Skirmantas Kligys
 */
public class AttributeTypeException extends AttributeBuilderException
{
  //-------------------------------------------------------------------------
  /** Java class of value present in RIM object. */
  private final Class actualClass_;

  //-------------------------------------------------------------------------
  public AttributeTypeException(Attribute attribute, Class actualClass)
  {
    super(attribute, createMessage(attribute, actualClass));
    
    actualClass_ = actualClass;
  }

  //-------------------------------------------------------------------------
  protected static String createMessage(Attribute attribute,
    Class actualClass)
  {
    return AttributeBuilderException.createMessage(attribute) +
      ": HMD specifies data type " + attribute.getDatatype().getFullName() +
      ", RIM object has " + actualClass.getName();
  }

  //-------------------------------------------------------------------------
  /**
   * @return Returns the actual Java class of an attribute.
   */
  public Class getActualClass() { return actualClass_; }
}

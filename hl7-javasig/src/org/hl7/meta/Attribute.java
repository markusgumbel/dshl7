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
 * {@link org.hl7.meta.impl.AttributeImpl AttributeImpl},
 * which represents an HL7 v3 clone class attribute.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public interface Attribute extends Feature
{
  //-------------------------------------------------------------------------
  /**
   * Returns the datatype of this attribute.
   * 
   * @return the Datatype
   */
  public Datatype getDatatype();

  //-------------------------------------------------------------------------
  /**
   * Returns the vocabulary domains for this attribute.
   * 
   * @return the vocabulary domains
   */
  public String[] getDomains();

  /**
   * Returns the fixed values for this attribute.
   * 
   * @return the fixed values
   */
  public String[] getFixedValues();

  /**
   * Returns the default value for this attribute.
   * 
   * @return the default values
   */
  public String getDefaultValue();

  /**
   * Returns the coding strength for this attribute.
   * 
   * @return the coding strength
   */
  public CodingStrength getCodingStrength();

  /**
   * Returns <code>true</code> if this attribute is defined as structural
   * in XML ITS (that means in XML instance it is represented by an XML
   * attribute instead of an XML element.
   *
   * @return the structural attribute flag
   */
  public boolean isStructural();

  /**
   * Returns <code>true</code> if this attribute is defined as Extra attribute
   * in XML ITS (that means in XML instance it is represented by an XML
   * attribute instead of an XML element.
   *
   * @return the structural attribute flag
   * @deprecated replaced by XmlItsUtil.isAdditionalAttribute(String name)
   */
//  public boolean isExtra();
}

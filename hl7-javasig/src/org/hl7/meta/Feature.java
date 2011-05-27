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
 * A read only interface to the class FeatureImpl, which
 * is a common parent class for both AttributeImpl and AssociationImpl.
 * Feature is the commonality between an attribute and an association inside
 * a clone class.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public interface Feature extends Metadata
{
  /**
   * Returns the feature's name, which may be the attribute name, or the short
   * name in case of an association.
   * 
   * @return the feature's name
   */
  public String getName();

  /**
   * Returns the feature's sort key.
   *
   * @return the feature's sort key
   */
  public int getSortKey();

  /**
   * Returns the feature's cardinality.
   * 
   * @return the feature's cardinality
   */
  public Cardinality getCardinality();

  //-------------------------------------------------------------------------
  /**
   * Returns the name of the RIM class where the feature was originally
   * defined.
   * 
   * @return the RIM class name
   */
  public String getRimClass();

  /**
   * Returns the property name inside the RIM class.  This may be the
   * attribute name or the association name.
   * 
   * @return the RIM Property name
   */
  public String getRimPropertyName();

  //-------------------------------------------------------------------------
  /**
   * Returns the clone class where the feature belongs.
   * 
   * @return the clone class
   */
  public CloneClass getParent();

  //-------------------------------------------------------------------------
  /**
   * Returns a flag showing if the feature is mandatory in the clone class.
   * 
   * @return the flag
   */
  public boolean isMandatory();

  /**
   * Returns the conformance field attached to this feature.
   * 
   * @return the conformance field
   */
  public Conformance getConformance();

  /**
   * Returns the textual constraint note attached to the feature.
   * 
   * @return the constraint note
   */
  public String getConstraint();

}

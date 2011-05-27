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

import java.lang.reflect.Method;
import org.hl7.rim.RimObject;

/**
 * Provides Java ITS specific functionality.
 *
 * @author  Jerry Joyce
 */
public interface JavaIts
{
  //-------------------------------------------------------------------------
  /**
   * Returns the Java interface for the given RIM datatype.
   *
   * @param datatype  RIM datatype
   * @return  corresponding Java interface
   * @throws ClassNotFoundException  if the Java interface cannot be found
   */
  Class getDatatypeInterface(Datatype datatype)
    throws ClassNotFoundException;

//  //-------------------------------------------------------------------------
//  /**
//   * Returns the Java class implementing the given RIM datatype.
//   *
//   * @param datatype  RIM datatype
//   * @return  corresponding Java class
//   * @throws ClassNotFoundException  if the Java class cannot be found
//   */
//  Class getDatatypeImplementation(Datatype datatype)
//    throws ClassNotFoundException;

  //-------------------------------------------------------------------------
  /**
   * Returns the Java interface for the given RIM class.
   *
   * @param rimClass  RIM class
   * @return  corresponding Java interface
   * @throws ClassNotFoundException  if the Java interface cannot be found
   */
  Class getRimInterface(String rimClass) throws ClassNotFoundException;

  //-------------------------------------------------------------------------
  /**
   * Returns the Java class implementing the given RIM class.
   *
   * @param rimName  RIM class name
   * @return  corresponding Java class
   * @throws ClassNotFoundException  if the Java class cannot be found
   */
  Class getRimImplementation(String rimName) throws ClassNotFoundException;

  //-------------------------------------------------------------------------
  /**
   * Returns the getter method for the given attribute or association (feature).
   *
   * @param rimClass  RIM class implementation
   * @param featureName  RIM feature name
   * @return  corresponding Java getter method
   * @throws NoSuchMethodException  if there is no such getter
   */
  Method getFeatureGetter(Class rimClass, String featureName)
    throws NoSuchMethodException;

  //-------------------------------------------------------------------------
  /**
   * Returns the setter method for the given attribute.
   *
   * @param rimClass  RIM class implementation
   * @param attributeName  RIM attribute name
   * @param dt  RIM datatype
   * @return  corresponding Java setter method
   * @throws NoSuchMethodException  if there is no such setter
   */
  Method getAttributeSetter(Class rimClass, String attributeName, Datatype dt)
    throws ClassNotFoundException, NoSuchMethodException;

  //-------------------------------------------------------------------------
  /**
   * Returns the setter method for the given association.
   *
   * @param rimClass  RIM class implementation
   * @param associationName  RIM association name
   * @param distalRimClass  Distal RIM class name
   * @return  corresponding Java setter method
   * @throws NoSuchMethodException  if there is no such setter
   */
  Method getAssociationSetter(Class rimClass, String associationName,
    String distalRimClass) throws ClassNotFoundException, NoSuchMethodException;

  /**
   * Returns the adder method for the given association.
   *
   * @param rimClass  RIM class implementation
   * @param associationName  RIM association name
   * @param distalRimClass  Distal RIM class name
   * @return  corresponding Java adder method
   * @throws NoSuchMethodException  if there is no such setter
   */
  Method getAssociationAdder(Class rimClass, String associationName,
    String distalRimClass) throws ClassNotFoundException, NoSuchMethodException;

  /**
   * 
   * @param rimObject
   * @param methodNameStem
   * @return
   * @throws NoSuchMethodException
   */
  public Class getRIMDataType(RimObject rimObject, String methodNameStem)
            throws NoSuchMethodException;

}

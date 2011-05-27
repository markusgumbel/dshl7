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

import java.util.Set;

/**
 * A read only interface to class
 * {@link org.hl7.meta.impl.MessageTypeImpl MessageTypeImpl},
 * which represents an HL7 v3 message type metadata.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public interface MessageType extends Cloneable
{
  //-------------------------------------------------------------------------
  /**
   * Returns the message type ID, such as 'PRPA_MT101101'.
   * 
   * @return the ID
   */
  String getId();

  /**
   * Returns the message type long name, such as
   * 'A_Encounter_person_registry_active_detailed'.
   * 
   * @return the long name
   */
  String getName();
  
  //-------------------------------------------------------------------------
  /**
   * Returns the reference to the clone class in the message type.  The
   * remaining clone classes can be reached only by traversing associations
   * pointing at them.
   * 
   * @return the root clone class
   */
  CloneClass getRootClass();
  
  //-------------------------------------------------------------------------
  /**
   * Returns set of names of all clone classes.
   * 
   * @return  the set of names
   */
  Set/*<String>*/ getAllCloneClassNames();
  
  //-------------------------------------------------------------------------
  /**
   * Looks up a clone class by its name.  Returns <code>null</code> if not
   * found.
   * 
   * @param name  clone class name
   * @return  reference to the clone class; <code>null</code> if not found
   */
  CloneClass lookupCloneClass(String name);
  
  /**
   * Adds a CMET referenced by this MessageType's CMET to a map of referenced
   * CMETs.
   * 
   * @param referencedCmetName The name (not the id) of the referenced CMET.
   * 
   * @return The MessageType for the referenced CMET.
   *
   * @author jmoore
   */
  MessageType addReferencedCmet(String referencedCmetName);
}
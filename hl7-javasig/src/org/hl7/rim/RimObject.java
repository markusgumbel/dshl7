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
package org.hl7.rim;

import org.hl7.hibernate.Hibernatable;
import org.hl7.types.CS;

/**
 * Base RIM object interface.
 */
public interface RimObject extends Cloneable, Hibernatable {
  /**
   * A standard override to make an object cloneable.
   *
   * @return  a clone object
   */
  public Object clone() throws CloneNotSupportedException;

  /**
   * Clone code is used to associated this Rim object w/
   * a particular clone object (contains the tagname)
   *
   * @return
   */
  public CS getCloneCode();

  public void setCloneCode(CS value);

  /**
   * Type is used to support  XML ITS 'type' attribute to capture the RIM class name
   *
   * @return type
   */
  public CS getType();

  /**
   * Type is used to support XML ITS 'type' attribute to capture the RIM class name
   */
  public void setType(CS type);
}

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
 * An exception thrown when somebody tries to create an unknown data type.
 *
 * @author Skirmantas Kligys
 */
public class UnknownDatatypeException extends Exception
{
  //-------------------------------------------------------------------------
  /**
   * Constructor for UnknownDatatypeException.
   * 
   * @param message  full name of the data type
   */
  public UnknownDatatypeException(String message)
  {
    super(message);
  }
}

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

package org.hl7.util;

/**
 * An exception thrown by <code>RimFactory.getInstance()</code> or
 * <code>DatatypeHandlerFactory.getInstance()</code> or else if findinging
 * a factory instance fails.
 */
public class FactoryException extends Exception
{
  //-------------------------------------------------------------------------
  /**
   * Creates a factory configuration exception with given message an no
   * nested exception.
   *
   * @param message
   */
  public FactoryException(String message)
  {
    super(message);
  }

  //-------------------------------------------------------------------------
  /**
   * Creates a factory configuration exception with given message and a
   * nested exception.
   *
   * @param message
   * @param cause
   */
  public FactoryException(String message, Throwable cause)
  {
    super(message, cause);
  }

  //-------------------------------------------------------------------------
  /**
   * Creates a factory configuration exception with no message and a
   * nested exception.
   * @param cause
   */
  public FactoryException(Throwable cause)
  {
    super(cause);
  }
}

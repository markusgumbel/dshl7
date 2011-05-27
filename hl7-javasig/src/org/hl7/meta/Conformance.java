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
 * A type safe enumeration of conformance flags for HL7 v3 attributes.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public class Conformance
{
  //-------------------------------------------------------------------------
  // Typesafe enumeration pattern.
  /**
   * Conformance flag R (Required).
   */
  public static Conformance REQUIRED = new Conformance();
  /**
   * Conformance flag NP (Not Permitted).
   */
  public static Conformance NOT_PERMITTED = new Conformance();

  //-------------------------------------------------------------------------
  /**
   * All constructors are private according to typesafe enumeration pattern.
   */
  private Conformance() {}

  //-------------------------------------------------------------------------
  /**
   * Looks up conformance by its string representation.
   * 
   * @param s  the string to look up
   * @throws IllegalArgumentException  if the string cannot be looked up
   */
  public static Conformance create(String s)
  {
    if (s == null) return null;
    else if (s.equals("R")) return REQUIRED;
    else if (s.equals("NP")) return NOT_PERMITTED;
    else throw new IllegalArgumentException("Illegal conformance: " + s);
  }

  //-------------------------------------------------------------------------
  /**
   *  Converts conofrmance to its string representation.
   *
   * @return the string representation
   * @throws IllegalArgumentException  on internal error
   */
  public String toString()
  {
    if (this == REQUIRED) return "R";
    else if (this == NOT_PERMITTED) return "NP";
    else throw new IllegalArgumentException();
  }
}

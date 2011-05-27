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
 * A type safe enumeration of MET sources for HL7 v3 HMD class and association
 * rows.  Possible values are:
 * <ul>
 *   <li>NEW (definition of a new class),</li>
 *   <li>CMET (reference to a CMET),</li>
 *   <li>REUSED (reference to a class already defined in the current
 * mesage type),</li>
 *   <li>IDENTICAL (reference to the content in the row must be
 * identical),</li>
 *   <li>RECURSIVE (reference to an already defined class, and a
 * recursive association as well; needs special care to avoid infinite loops).</li>
 * </ul>
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public class MetSource
{
  //-------------------------------------------------------------------------
  // Typesafe enumeration pattern.
  /**
   * MET source N (New).
   */
  public static MetSource NEW = new MetSource();

  /**
   * MET source C (CMET).
   */
  public static MetSource CMET = new MetSource();

  /**
   * MET source U (Reused).
   */
  public static MetSource REUSED = new MetSource();

 /**
     * MET source I (Identical).
     */
    public static MetSource IDENTICAL = new MetSource();


  /**
   * MET source R (Recursive).
   */
  public static MetSource RECURSIVE = new MetSource();

  //-------------------------------------------------------------------------
  /**
   * All constructors are private according to typesafe enumeration pattern.
   */
  private MetSource() {}

  //-------------------------------------------------------------------------
  /**
   * Looks up MET source by its string representation.
   * 
   * @param s  the string to look up
   * @throws IllegalArgumentException  if the string cannot be looked up
   */
  public static MetSource create(String s)
  {
    if (s == null) return null;
    else if (s.equals("N")) return NEW;
    else if (s.equals("C")) return CMET;
    else if (s.equals("U")) return REUSED;
    else if (s.equals("I")) return IDENTICAL;
    else if (s.equals("R")) return RECURSIVE;
    else throw new IllegalArgumentException("Illegal MET source: " + s);
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
    if (this == NEW) return "N";
    else if (this == CMET) return "C";
    else if (this == REUSED) return "U";
    else if (this == IDENTICAL) return "I";
    else if (this == RECURSIVE) return "R";
    else throw new IllegalArgumentException();
  }
}

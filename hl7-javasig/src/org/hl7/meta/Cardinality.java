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

import java.util.HashMap;

/**
 * A flyweight implementation for cardinalities of HL7 v3 attributes and
 * associations.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 */
public class Cardinality
{
  //-------------------------------------------------------------------------
  /**
   * A cache of known cardinalities for the flyweight pattern.
   * Maps a string representation into its corresponding unique
   * <code>Cardinality</code> object. */
  private static final HashMap<String, Cardinality> CACHE =
    new HashMap<String, Cardinality>();
  
  //-------------------------------------------------------------------------
  /** Minimum cardinality. */
  private final int min_;
  /** Maximum cardinality; -1 corresponds to *
   *
   * GS: I changed that to UNBOUNDED (Integer.MAX_VALUE) because otherwise 
   * it is quite unintuitive when I want to test if(card.getMax() > 1).
   */
  private final int max_;

  public static final int UNBOUNDED = Integer.MAX_VALUE;

  //-------------------------------------------------------------------------
  /**
   * Constructors is called only from create() to ensure identity equality.
   */
  private Cardinality(int min, int max)
  {
    if (min > max)
    {
      throw new IllegalArgumentException("min=" + min + ", max=" + max);
    }
    
    min_ = min;
    max_ = max;
  }

  //-------------------------------------------------------------------------
  /**
   * Looks up cardinality by its string representation.
   * 
   * @param s  the string to look up
   * @throws IllegalArgumentException  if the string cannot be looked up
   */
  public static Cardinality create(String s)
  {
    if (s == null) return null;
    
    int idxDoubleDot = s.indexOf("..");
    if (idxDoubleDot == -1)
    {
      throw new IllegalArgumentException("Illegal cardinality: " + s);
    }

    int min = -2;
    int max = -2;
    try
    {
      min = Integer.parseInt(s.substring(0, idxDoubleDot));

      String sMax = s.substring(idxDoubleDot + 2);
      max = sMax.equals("*") ? UNBOUNDED : Integer.parseInt(sMax);
    }
    catch (NumberFormatException ex)
    {
      throw new IllegalArgumentException("Illegal cardinality: " + s);
    }
    
    // Multithreading-safe.
    Cardinality card = null;
    synchronized (CACHE)
    {
      card = CACHE.get(s);
      if (card == null)
      {
        card = new Cardinality(min, max);
        CACHE.put(s, card);
      }
    }
    return card;
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the minimum cardinality.
   * 
   * @return  the minimum
   */
  public int getMin()
  {
    return min_;
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the maximum cardinality.
   * 
   * @return  the maximum; UNBOUNDED means "*". UNBOUNDED happens to
   * be the Integer.MAX_VALUE, but who will ever distinguish cardinalities
   * of that magnitude. Noone will.
   */
  public int getMax()
  {
    return max_;
  }

  //-------------------------------------------------------------------------
  /**
   *  Converts cardinality to its string representation.
   *
   * @return the string representation
   * @throws IllegalArgumentException  on internal error
   */
  public String toString()
  {
    return String.valueOf(min_) + ".." +
      (max_ == UNBOUNDED ? "*" : String.valueOf(max_));
  }
}

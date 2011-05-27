/*
 * Copyright (c) 1998-2003 Regenstrief Institute.  All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.regenstrief.ucum;

import org.hl7.types.REAL;
import org.hl7.types.ST;

/** This class implements a prefix for a metric unit atom.

    @author Gunther Schadow.
    @version $Id: Prefix.java 4607 2006-10-18 19:43:42Z crosenthal $
*/
public final class Prefix /* implements CS */ {
  
  /** The prefix symbol. */
  private ST _code;
  
  /** The numeric value of the prefix. */
  private REAL _value;
  
  /** The null-prefix or identity-prefix with value one. 
  public final Prefix ONE 
    = new Prefix(ValueFactory.getInstance()
                 .STvalueOf(""), valueFactory.REALvalueOf(1));
  */

  /** A constructor for new prefices. Package protected because only
      UnitSystem calls this.
  */
  Prefix(ST code, REAL value) {
    assert code.nonEmpty().isTrue();
    assert value.nonZero().isTrue();

    this._code  = code;
    this._value = value;

    Prefix oldPrefix 
      = UnitSystem._stringPrefixMap.put(code.toString().intern(), this);
    
    assert oldPrefix == null : "duplicate prefix " + code.toString();
  }
  
  /** Lookup a prefix value given the prefix as an ST. */
  public static Prefix valueOf(ST symbol) {
    return Prefix.valueOf(symbol.toString());
  }

  /** Lookup a prefix value given the prefix as a String. */
  public static Prefix valueOf(String symbol) {
    return UnitSystem._stringPrefixMap.get(symbol.intern());
  }
  
  /** The prefix symbol. */
  public ST code() {
    return this._code;
  }
  
  /** The value of the prefix. */
  public REAL value() {
    return this._value;
  }
}

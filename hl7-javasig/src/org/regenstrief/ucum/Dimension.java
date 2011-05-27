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

/** This class implements a physical measurement dimension, which
    is a vector of exponents applied to the base unit system.

    @author Gunther Schadow.
    @version $Id: Dimension.java 7377 2007-09-26 19:28:36Z gschadow $
*/
public final class Dimension {

  /** The vector of exponents. */
  private byte _component[] = new byte[UnitSystem._baseVectorSize]; 
   
  /** The absolute value of the maximum and minimum exponent value. */
  private static int _maxExponent = 127;

  public static final Dimension ZERO = new Dimension();

  /** Constructor for the zero dimension. */
  private Dimension() {
    int max = UnitSystem._baseVectorSize;
    for(int i = 0; i < max; i++)
      this._component[i] = 0; 
  }

  /** Constructor for the n-th base dimension. */
  Dimension(int n) {
    assert 0 <= n && n < UnitSystem._baseVectorSize:
      "illegal dimension index " + n + " " + UnitSystem._baseVectorSize; 
    this._component[n] = 1;
  }

  /** Return a string representation of this dimension. */
  public String toString() {
    assert UnitSystem._baseVectorSize > 0 : "not initialized";

    StringBuffer sbuf = new StringBuffer();
    boolean didOne = false;
    for(int i = 0; i < UnitSystem._baseVectorSize; i++) {
      int component = this._component[i];
      if(component != 0) {
	if(didOne)
	  sbuf.append(".");
	else
	  didOne = true;
	sbuf.append(UnitSystem._baseDimensionString[i]);
	if(component != 1) {
	  sbuf.append(component);
	}
      }
    }

    if(didOne)
      return sbuf.toString();
    else 
      return "1";
  }

  /** Return a canonical unit string for this dimension. This is a
      valid UCUM unit string.
  */
  public String toUnitString() {
    assert UnitSystem._baseVectorSize > 0 : "not initialized";

    StringBuffer sbuf = new StringBuffer();
    boolean didOne = false;

    for(int i = 0; i < UnitSystem._baseVectorSize; i++) {
      int component = this._component[i];
      if(component != 0) {	
	if(didOne)
	  sbuf.append(".");
	else
	  didOne = true;
	sbuf.append(UnitSystem._baseUnitString[i]);
	if(component != 1) {
	  sbuf.append(component);
	}
      }
    }    

    if(didOne)
      return sbuf.toString();
    else 
      return "1";
  }

  /** Tests for equality. */
  public boolean equals(Dimension that) {
  	for(int i = 0; i < UnitSystem._baseVectorSize; i++)
  		if(this._component[i] != that._component[i])
  			return false;
  	
  	return true;
  }
  
  /** Multiplies two dimensions.

      @return the product
  */
  public Dimension times(Dimension that) {
    assert UnitSystem._baseVectorSize > 0 : "not initialized";
    assert that != null : "argument must not be null";
    
    Dimension result = new Dimension();
    for(int i = 0; i < UnitSystem._baseVectorSize; i++) {
      int component = this._component[i] + that._component[i];

      assert component < Dimension._maxExponent : 
	"dimension exponent overflow in dimension " + i;
      
      result._component[i] = (byte)component;
    }

    return result;
  }

  /** Divides this Dimension by the argument Dimension.

      @return the quotient
  */
  public Dimension dividedBy(Dimension that) {
    assert UnitSystem._baseVectorSize > 0 : "not initialized";
    assert that != null : "argument must not be null";
    
    Dimension result = new Dimension();
    for(int i = 0; i < UnitSystem._baseVectorSize; i++) {
      int component = this._component[i] - that._component[i];

      assert component < Dimension._maxExponent : 
	"dimension exponent overflow in dimension " + i;
      
      result._component[i] = (byte)component;
    }

    return result;
  }

  /** Inverts the Dimension.

      @return the inverted Dimension
  */
  public Dimension inverted() {
    assert UnitSystem._baseVectorSize > 0 : "not initialized";
    
    Dimension result = new Dimension();
    for(int i = 0; i < UnitSystem._baseVectorSize; i++) {
      result._component[i] = (byte)(- this._component[i]);
    }

    return result;
  }

  /** Puts this Dimension to the n-th power

      @return the inverted Dimension
  */
  public Dimension power(int n) {
    assert UnitSystem._baseVectorSize > 0 : "not initialized";
    
    Dimension result = new Dimension();
    for(int i = 0; i < UnitSystem._baseVectorSize; i++) {
      int component = this._component[i] * n;

      assert component < Dimension._maxExponent : 
	"dimension exponent overflow in dimension " + i;
      
      result._component[i] = (byte)component;
    }

    return result;
  }
}

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

import org.hl7.types.INT;
import org.hl7.types.REAL;

/** This class implements a physical quantity.

    - REAL      magnitude 
    - Dimension dimension

    where Dimension is essentially a vector of exponents of the base 
    units.

    This class does not, however, preserve any sense of "original
    unit", i.e., the value of this class is always in canonical 
    form.
    
    @author Gunther Schadow.
    @version $Id: DimensionedQuantity.java 13134 2008-02-26 14:12:12Z ccrafton $
*/
public class DimensionedQuantity {
  
  /** The numeric magnitude of the unit. */
  private REAL _magnitude;

  /** The dimension of the unit. */
  private Dimension _dimension;
  
	public DimensionedQuantity withLimitedPrecision(INT precisionLimit) {
		return new DimensionedQuantity(_magnitude.withLimitedPrecision(precisionLimit), _dimension);
	}
  
  /** A constructor for new dimensioned quantity.
  */
  protected DimensionedQuantity(REAL magnitude, Dimension dimension) {
    if(magnitude == null)
      throw new NullPointerException("magnitude");
    //if(!magnitude.nonNull().isTrue())
    //  throw new IllegalArgumentException("magnitude must be non-NULL");
    if(dimension == null)
      throw new NullPointerException("dimension");
    
    this._magnitude = magnitude;
    this._dimension = dimension;
  }

  /** A constructor for new dimensioned quantity as a clone. Notice
      that this is protected, so that Unit can call it as super().
  */
  protected DimensionedQuantity(DimensionedQuantity that) {
    this._magnitude = that._magnitude;
    this._dimension = that._dimension;
  }

  // ACCESSORS

  /** The magnitude of the quantity relative to the canonical unit, i.e.,
      relative to the coherent unit made of only base units. 
   */
  public REAL magnitude() {
    return this._magnitude;
  }

  /** The dimension of the unit. */
  public Dimension dimension() {
    return this._dimension;
  }

  // RELATIONS

  public boolean equals(Object that) {
		//System.out.println("####EQ: "+this+" == "+that+": "+this._dimension.equals(((DimensionedQuantity)that).dimension())+" && "+this._magnitude.equal(((DimensionedQuantity)that).magnitude()));

  	return this == that || (this instanceof DimensionedQuantity &&
  			this._dimension.equals(((DimensionedQuantity)that).dimension()) && 
  			this._magnitude.equal(((DimensionedQuantity)that).magnitude()).isTrue());
  }
  
  public boolean lessOrEqual(DimensionedQuantity that) {
  	if(this._dimension.equals(that._dimension))
  		return this._magnitude.lessOrEqual(that._magnitude).isTrue();
    else
      return false;
  }

  // OPERATIONS
  
  /** Multiply two quantities. */
  public DimensionedQuantity times(DimensionedQuantity that) {
    if(that == null)
      throw new NullPointerException("operand");

    return valueOf(this._magnitude.times(that._magnitude),
		   this._dimension.times(that._dimension));
  }

  /** Multiply a quantity with a real. */
  public DimensionedQuantity times(REAL that) {
    if(that == null)
      throw new NullPointerException("operand");
    //if(!that.nonNull().isTrue())
    //  throw new IllegalArgumentException("operand must be non-NULL");

    return valueOf(this.magnitude().times(that), this.dimension());
  }

  /** Divide two quantities. */
  public DimensionedQuantity dividedBy(DimensionedQuantity that) {
    if(that == null)
      throw new NullPointerException("operand");

    return valueOf(this._magnitude.dividedBy(that._magnitude),
		   this._dimension.dividedBy(that._dimension));
  }

  /** Invert a quantity. */
  public DimensionedQuantity inverted() {
    return valueOf(this._magnitude.inverted(),
		   this._dimension.inverted());
  }

  /** Raise the quantity to the n-th power. */
  public DimensionedQuantity power(INT n) {
    if(n == null)
      throw new NullPointerException("operand");
    //if(!n.nonNull().isTrue())
    //  throw new IllegalArgumentException("operand must be non-NULL");
	    
    return valueOf(this._magnitude.power(n.toREAL()),
		   this._dimension.power(n.intValue()));
  }

  /* Negate the quantity. */
  public DimensionedQuantity negated() {
    return valueOf(this._magnitude.negated(),
		   this._dimension);
  }
  
  /** Add two comparable quantities. */
  public DimensionedQuantity plus(DimensionedQuantity that) {
    if(that == null)
      throw new NullPointerException("operand");
    if(this._dimension.equals(that._dimension))
      return valueOf(this._magnitude.plus(that._magnitude),
		     this._dimension);
    else
      throw new IllegalArgumentException("operand is not comparable");
  }

  /** Subtract a comparable quantity from this quantity. */
  public DimensionedQuantity minus(DimensionedQuantity that) {
    if(that == null)
      throw new NullPointerException("operand");
    if(this._dimension.equals(that._dimension))
      return valueOf(this._magnitude.minus(that._magnitude),
		     this._dimension);
    else
      throw new IllegalArgumentException("operand is not comparable");
  }

  /** Return a REAL number if the quantity is dimensionless. If it is not dimensionless, we cannot return a real number. */
  public REAL toREAL() {
    if(Dimension.ZERO.equals(_dimension))
      return this._magnitude;
    else
      throw new IllegalArgumentException("quantity must be dimensionless, this was: "+_dimension.toString());
  }

  // LITERAL FORM
  
  /** A string representation of the quantity that is a valid literal.
   */
  public String toString() {
    return this._magnitude.toString() + " " + this._dimension.toUnitString();
  }
  
  /** Return a DimensionedQuantity value from a magnitude and a dimension. 

      FIXME: this just creates a new instance, which is not good. Need 
      to look values up by dimension and magnitude.
  */
  public static DimensionedQuantity valueOf(REAL magnitude, Dimension dimension) {
    return new DimensionedQuantity(magnitude, dimension);
  }
}

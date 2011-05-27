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
package org.hl7.types;

/** A general quantity, at least of the level of a difference-scale, also
    known as interval-scale. It may be a ratio scale quantity, which
    is expressed by another quantity diff that can represent such 
    difference. 

    DISCUSSION

    We need to clarify the expectations that come with the operations
    that we declare in these abstract interfaces. For example, a 
    difference x.minus(y) may only exist between two values of comparable 
    types. I.e., you can't calculate a difference between a time 
    stamp minus a temperature. When we declare an operation QTY.minus(QTY) 
    aren't we saying that there is a difference between each and every
    QTY value? 

    No, we are not saying this and we never could. For instance, a
    PhysicalQuantity (PQ) as a subtype of quantity will not have a
    difference of (5 min).minus(1 cm). Just like an Ordinal type
    may only have a partial order. E.g., in a tree there can be 
    an order among the nodes of a path in the tree, but not between
    the nodes on different branches. If we add a distance metric
    (to turn the tree into a difference-scale quantity) we can
    calculate a difference between comparable nodes, but not between
    nodes on different branches (unless of course we adjust our 
    distance metric to allow backing up to a common ancestor.)

    In practice the PQ, that is a subtype of QTY.diff shows us that
    QTY really only means that there *may* be a distance between
    two values, whereas if I am not a specialization of QTY, I 
    know that there never is a distance. So, this type hierarchy
    is conveying maximal expectations, not minimal expectations.
    Is this a departure from common practices of abstract datas type
    design? I don't think so. There are always *some* cases where
    an operation is not allowed, for example, division by zero.

    What would be the alternative? It would be to not specify the
    minus operation in the QTY data type generalization. In fact,
    there would not be a useful generalization. However, we would
    use ad-hoc polymorphism to allow quantities of different types
    to be used together in comparison and operations. The problem
    with this is that the variant of an overloaded function is 
    selected not based on the run-time object type of the arguments, 
    but based on the compile-time declared types of the arguments.
    Hence, it would require much run time type checking to sort 
    out the polymorphism.
*/
public interface QTY extends ORD {

  /** The difference between two quantities as a ratio-scale 
      quantity. This is the most fundamental operation of difference-
      scale quantities, i.e., that between every two values of that 
      quantity, there is a difference.
  */
  diff minus(QTY that);

  /** A quantity that can be the difference of two quantities. 
      Such a quantity has a neutral element of addition (zero),
      which is the difference between the same two quantities.
      Also, values in such a quantity may have an inverse value.
   */
  interface diff extends QTY {
    /** The neutral element of addition. */
    BL   isZero();

    /** Equals isZero().not() */
    BL   nonZero();

    /** The inverse element of addition. */
    diff negated();

    /** Need some sort of scaling multiplication */
    diff times(REAL x);

    /** The inverse of the scaling.
	@param x denominator, must be of the same type and dimension as this quantity.
    REAL dividedBy(diff x);
     */
  }
  
  /** A new quantity of the kind of this quantity, that linearly
      translated by a ratio-quantity expressing the difference 
      between two values of the kind of this quantity. 

      Note that plus as can be defined in QTY alone is not commutative.
      One *could* make it commutative by defining QTY diff.plus(QTY)
      but that cannot be specified here.
  */
  QTY   plus(diff that);

  /** A short hand for QTY.plus(that.negated());

      ISSUE: We are using the same operation name and the difference
      in signature is subtle because an diff is a QTY. For all
      ratio-quantities these two forms of minus(QTY) and
      minus(diff) are in fact the same. Having the same name is
      nice on the one hand, but may be confusing.
  */
  QTY   minus(diff that); 
  
  /** Return a small increment such that x: x + e > x */
  QTY.diff epsilon();
}

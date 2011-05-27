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

/** A general ordinal value. Ordinal means that there exist values in the 
    type's value space that can be compared as to which one is greater.
    
    Note: the ordinal type does not say that all values can be compared.
    Thus, ordinal is a value set with at least a partial ordering. We 
    cannot universally assert total order, because of our important use
    case PhysicalQuantities, where you can't compare 1 min and 5 cm. 
*/
public interface ORD extends ANY, Comparable<ORD> {

  /** Tests whether this ordinal is comparable with that ordinal.
      The result is equivalent to

        this.lessOrEqual(that).or(that.lessOrEqual(this));
	
      What makes values comparable is defined in concert by the 
      data types implementation. In general we can neither say 
      that all values of the samel type are comparable (PQ 1 min
      and 5 cm are not comparable even though they are the same
      type PQ), nor can we say that only values of the same type 
      are comparable (the integer 1 and the real number 1.3 are 
      comparable.)
   */
  BL  compares(ORD that);

  /** An ordering relation that tests whether this ordinal is less 
      or equal to that ordinal.
      
      IMPLEMENTATION NOTE: as a convention, this should be the 
      one basic ordering relation that should be implemented for
      every implementation. The other comparison relations can
      be derived from this one and the equality property (defined
      for all data values, ANY).
   */
  BL  lessOrEqual(ORD that);

  /** Equivalent to this.lessOrEqual(that).and(this.equal(that).not());
   */
  BL  lessThan(ORD that);

  /** Equivalent to that.lessThan(this)
   */  
  BL  greaterOrEqual(ORD that);

  /** Equivalent to that.lessOrEqual(this)
   */
  BL  greaterThan(ORD that);
}

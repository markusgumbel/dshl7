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


/**
 * 
 * A dimensioned quantity expressing the result of a
 * measurement act.
 * 
 * @generatedComment
 */
public interface PQ extends QTY.diff {

  /**
     * <p>
     * Some number that together with the <code>unit()</code> represent this
     * physical quantity. The number should never be separated from the unit and
     * no computations can reasonably be made with the <code>value()</code>
     * property alone, because the number can be anything depending on the unit.
     * </p>
     * 
     * <p>
     * <em>Warning:</em> this method is for display only, do computations with
     * the entire <code>PQ</code> not the number in isolation. For example,
     * you should not write code like this:
     * 
     * <pre>
     * if(getVolume().value().toDouble() &lt; 5) // too low;
     * </pre>
     * 
     * Instead, do something more like this:
     * 
     * <pre>
     * private static final PQ FIVE_ML = ValueFactory.getInstance().PQvalueOf(&quot;5&quot;, &quot;mL&quot;);
     * ...
     * if(getVolume().lessThan(FIVE_ML)) // too low;
     * </pre>
     * 
     * </p>
     */
    REAL value();

  /**
   * 
   * The unit of measure specified in the <a href="http://aurora.rg.iupui.edu/UCUM">Unified Code for
   * Units of Measure (UCUM)</a>
   * 
   * @generatedComment
   */
  CS    unit();

  PQ    canonical();

  /** Return a <code>REAL</code> if the quantity is dimensionless. Otherwise throw an exception. Even though
      this method might throw an {@link IllegalArgumentException}, it should be used instead of the
      {@link #value()} property to fetch the real value that results from any computation that would have
      cancelled out the units.
  */
  REAL toREAL();

  PQ  minus(PQ x);
  PQ  plus(PQ x);
  PQ  negated();
  PQ  times(REAL x);
  PQ  times(PQ x);
  PQ  inverted();
  PQ  power(INT x);
  PQ  dividedBy(PQ x);
  PQ  dividedBy(REAL x);
  
  SET<PQR> translation();

  /** Returns this physical quantity using the unit of the given other quantity. 
			The following holds true: <code>this.compares(that).implies(this.expressedIn(that).equsls(this))</code>.  */
  PQ expressedIn(PQ that);
  INT precision();
	PQ withLimitedPrecision(INT precisionLimit);
}

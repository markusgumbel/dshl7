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
 * <p>
 * A value that contains other distinct values in no particular order.
 * </p>
 * 
 * <pre>
 * template&lt;ANY T&gt;
 * type Set&lt;T&gt; alias SET&lt;T&gt; specializes ANY {
 *              BL      contains(T element);
 *              BL      isEmpty;
 *              BL      notEmpty;
 *              BL      contains(SET&lt;T&gt; subset);
 *              INT     cardinality;
 *              SET&lt;T&gt;  union(SET&lt;T&gt; otherset);
 *              SET&lt;T&gt;  union(T element);
 *              SET&lt;T&gt;  except(T element);
 *              SET&lt;T&gt;  except(SET&lt;T&gt; otherset);
 *              SET&lt;T&gt;  intersection(SET&lt;T&gt; otherset);
 *   literal    ST;
 *   promotion  SET&lt;T&gt;  (T x);
 *              IVL&lt;T&gt;  hull;
 * };
 * </pre>
 * 
 * @param <T>
 *                contained data type
 * @see <a
 *      href="http://www.hl7.org/v3ballot2007sep/html/infrastructure/datatypes/datatypes.htm#dt-SET"
 *      target="_" title="HL7 Version 3 Standard">Set (SET) </a>
 */
public interface SET<T extends ANY> extends ANY {
    /**
     * <p>A relation of the set with its elements, true if the given value is an
     * element of the set.</p>
     * <p>This is the primitive semantic property of a set, based on which all
     * other properties are defined.</p>
     * <p>A set may only contain distinct non-NULL elements. Exceptional values
     * (NULL-values) cannot be elements of a set.</p>
     * <pre>
     * invariant(SET&lt;T&gt; s, T n)
     *       where s.nonNull.and(n.isNull) {
     *    s.contains(n).not;
     * };
     * </pre>
     * 
     * @param element
     * @return
     */
    BL contains(T element);
    
    BL isEmpty();
    
    BL nonEmpty();
    
    BL contains(SET<T> subset);
    
    INT cardinality();
    
    SET<T> union(SET<T> otherset);
    
    SET<T> except(T element);
    
    SET<T> except(SET<T> otherset);
    
    SET<T> intersection(SET<T> otherset);
    
    /**
     * Allow an element of this set to be selected according to a Criterion
     */
    SET<T> select(Criterion<T> c);
    
    /** return &quot;any&quot; element of the set * */
    T any();
}

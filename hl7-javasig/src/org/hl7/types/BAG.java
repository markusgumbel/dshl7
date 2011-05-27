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

import java.util.Iterator;

/**
 * <p>
 * An unordered collection of values, where each value can be contained more
 * than once in the collection.
 * </p>
 * 
 * <pre>
 * template&lt;ANY T&gt;
 * type Bag&lt;T&gt; alias BAG&lt;T&gt; specializes ANY {
 *               INT     contains(T kind);
 *               BL      isEmpty;
 *               BL      notEmpty;
 *               BAG&lt;T&gt;  plus(BAG&lt;T&gt; x);
 *               BAG&lt;T&gt;  minus(BAG&lt;T&gt; x);
 *    promotion  BAG&lt;T&gt;  (T x);
 * };
 * </pre>
 * 
 * <p>
 * <em>NOTE:</em> A <code>BAG</code> can be represented in two ways. Either
 * as a simple enumeration of elements, including repeated elements, or as a
 * &quot;compressed bag&quot; whereby the content of the <code>BAG</code> is
 * listed in pairs of element value and number. A histogram showing absolute
 * frequencies is a <code>BAG</code> represented in compressed form.
 * <code>BAG</code> is therefore useful to communicate raw statistical data
 * samples.
 * </p>
 * 
 * @param <T>
 *                contained data type.
 * @see <a
 *      href="http://www.hl7.org/v3ballot2007sep/html/infrastructure/datatypes/datatypes.htm#dt-BAG"
 *      target="_" title="HL7 Version 3 Standard">Bag (BAG)</a>
 */
public interface BAG<T extends ANY> extends ANY, Iterable<T> {
    /**
     * <p>
     * The number of items in this bag with the given item value.
     * </p>
     * 
     * <p>
     * This is the primitive semantic property of a <code>BAG</code>, on
     * which all other properties are defined.
     * </p>
     * 
     * <pre>
     * invariant(BAG&lt;T&gt; bag; T item)
     *       where bag.nonNull.and(item.nonNull) {
     *    bag.contains(item).nonNegative;
     *    bag.isEmpty.equal(bag.contains(item).isZero);
     * };
     * </pre>
     * 
     * @param item
     * @return
     */
    INT contains(T item);
    
    /**
     * <p>
     * A predicate indicating that this <code>BAG</code> has no elements
     * (negation of the {@link #notEmpty()} predicate. The empty
     * <code>BAG</code> is a proper value, not an exceptional (NULL) value.
     * </p>
     * 
     * <pre>
     * invariant(BAG&lt;T&gt; bag)
     *       where bag.nonNull {
     *    bag.isEmpty.equal(notEmpty.not);
     * };
     * </pre>
     * 
     * @return
     */
    BL isEmpty();
    
    /**
     * @deprecated the name was changed to {@link #notEmpty()}
     * @return
     */
    @Deprecated
    BL nonEmpty();
    
    /**
     * <p>
     * A predicate indicating that this <code>BAG</code> contains item.
     * </p>
     * 
     * <pre>
     * invariant(BAG&lt;T&gt; bag)
     *       where bag.nonNull {
     *    bag.notEmpty.equal(exists(T item) {
     *       bag.contains(item);
     *       });
     * };
     * </pre>
     * 
     * @return
     */
    BL notEmpty();
    
    /**
     * <p>
     * A <code>BAG</code> that contains all items of the operand
     * <code>BAG</code>s, i.e. the number of items of each item value are
     * added.
     * </p>
     * 
     * <pre>
     * invariant(BAG&lt;T&gt; x, y, z)
     *       where x.nonNull.and(y.nonNull) {
     *    x.plus(y).equal(z).equal(
     *       forall(T e)
     *             where e.nonNull {
     *          z.contains(e).equal(x.contains(e)
     *                       .plus(y.contains(e)));
     *          });
     * };
     * </pre>
     * 
     * @param y
     * @return
     */
    BAG<T> plus(BAG<T> y);
    
    /**
     * <p>
     * A <code>BAG</code> that contains all items of this <code>BAG</code>
     * (minuend) diminished by the items in the other <code>BAG</code>
     * (subtrahend). <code>BAG</code>s cannot carry deficits. When the
     * subtrahend contains more items of one value than the minuend, the
     * difference contains zero items of that value.
     * </p>
     * 
     * <pre>
     * invariant(BAG&lt;T&gt; x, y, z)
     *       where x.nonNull.and(y.nonNull) {
     *    x.minus(y).equal(z).equal(
     *       forall(T e)
     *             where e.nonNull {
     *                exists(INT n)
     *                   where n.equal(x.contains(e).minus(y.contains(e)) {
     *          n.nonNegative.equal(z.contains(e));
     *          n.isNegative.equal(z.contains(e).isZero);
     *          };
     *       });
     * };
     * </pre>
     * 
     * @param y
     * @return
     */
    BAG<T> minus(BAG<T> y);
    
    INT size();
    
    /**
     * An iterator over this bag. This need not be an iterator of <code>T</code>;
     * it can be any iterator that allows one to get at the partitions of this
     * bag (i.e. intervals or whatever.)
     */
    Iterator<T> iterator();
}

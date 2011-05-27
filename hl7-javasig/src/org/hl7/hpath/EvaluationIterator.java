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
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2005 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hpath;

import java.util.Iterator;
import java.lang.reflect.Type;

/** A common interface extending the standard java.util.Interator
    interface as a uniform result of the evaluation of HPath
    Expressions.  Most uses of HPath can treat the result of HPath
    Expression evaluation as normal java Iterator. However, there are
    needs to access the collection that is at the basis of the
    Iterator, including accessing the properties of an underlying
    collection as a whole at any time when its items are being
    enumerated. This requirement exists by the HPath language itself.

    <p>Note  that by  "collection" we  do not  only mean  instances of
    java.util.Collection, but generally any  class that has a property
    iterator() returning  a java.util.Iterator. We  call this property
    "source" because in the case that single result values are wrapped
    in an Iterator, that source is the same as the single next() value
    returned from the Iterator.

		@param R the type of the root context
		@param T the type of this iterator (i.e., what is returned by next)

    @author Gunther Schadow
    @version $Id: EvaluationIterator.java,v 1.2 2006/08/16 07:52:35 gschadow Exp $		
*/
public interface EvaluationIterator<R,T> extends Iterator<T> {	
	/** Returns true if this iterator holds only a single value, based on this a caller may decide to unwrap the single result and not use the iterator any further. 
            This only works after the iterator has been initialized, i.e., after at hasNext (nor next) has been called at least once. */
	boolean hasOnlySingleValue();

	/** Returns the current object, i.e. the one we last got from the source. This is important for property arguments. I.e., when I say a.b[c.d(e)].f then all of c, e need to refer to the same object every time, and f too. Notice, don't think this is the XSLT current() function, in fact it is the opposite, more like the "." in XPath. So, it is always true that next() == current() but current() != next(), (side effect of next). */
	public T current();
	
	/* The estimated type of the result, used to borrow a value against this property if it had returned no value. */
	Type currentType();

	/** Returns true if updates will create a new context object and false if updates would be applied to the existing object.
			This may be checked by functions that implement copy-on-write protection. 
	*/
	public boolean updateIsConstructive();
	/** Prepares the iterator for updates. This usually must be called before the first normal access to the iterator. */
	public void beginUpdate();
	/** Updates the current value shown by the iterator. */
	public void update(T newValue);
	/** Inserts a new value into the underlying sequence. */
	public void insert(T newValue);
	/** Removes the current element from the underlying sequence. This is the same signature as the standard java.util.Iterator method, but it generally is only applicable if the iterator is in update mode, i.e., if beginUpdate() has been called first. */
	public void remove();
	/** Completes the update. This should be called at the end when the iterator is exhausted. If it is called before the iterator has been fully exhausted the result is undefined. It might cause the updated set to loose all values not yet returned by the iterator. */
	public R finalizeUpdates();
}

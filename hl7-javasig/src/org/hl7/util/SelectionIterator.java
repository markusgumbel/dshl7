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
 * Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** A tool to filter elements from a collection (via the collection's iterator). Allows 
 constraining the type of element of the source list and the type of elements returned..
 
 @author Gunther Schadow
 */
public class SelectionIterator<S, T> implements Iterator<T> {

	int _index = 0;
	
	/** An interface to a selection criterion that can be tested against an object of type T. */
	public static interface Criterion<S> {
		/** Predicate to test whether an object conforms to the criterion. */
		boolean test(S object);
	}

	/** An interface to transform the input object to an output object. Typical use case is unnesting. */
	public static interface Transformer<S,T> {
		/** Transforms the object after the test to whatever type the user desires. */
		T transform(S object);
	}
	
	static final Iterator EMPTY_ITERATOR = new Iterator() {
		public boolean hasNext() { return false; }
		public Object next() {
			throw new NoSuchElementException();
		}
		public void remove() {
			throw new IllegalStateException();
		}
	};

	public static final Transformer IDENTITY_TRANSFORMER = new Transformer() {
			public Object transform(Object object) { return object; }
		};

	static final Criterion ALL_CRITERION = new Criterion() {
			public boolean test(Object object) { return true; }
		};

	/** Filter and sort a collection to only return elements that validate against the Criterion. 
			The output is sorted in the order defined by the Comparator and the objects transformed through the Transformer. 
			Comparator or Transformer can be null or IDENTITY_TRANSFORMER, in which case no sorting and no transformation is done respectively.
			Note, sorting implies that the collection is copied to an array which is then sorted.
	*/
	public static <S,T> Iterator<T> select(Collection<S> collection, Criterion<S> criterion, Transformer<S,T> transformer, Comparator<S> comparator) {
		if(transformer == null)
			transformer = (Transformer<S,T>)IDENTITY_TRANSFORMER;
		if(collection == null)
			return (Iterator<T>)EMPTY_ITERATOR;
		else if(comparator == null)
			return select(collection, criterion, transformer); 
		else { 
			// if we need to sort, we first check the criterion while populating a ...
			// sorted set - wrong! sorted set will think that things which have no distinguishable order are the same!
			// have to use a list here and then sort that
			List<S> resultList = new ArrayList<S>();
			for(S element : collection)
				if(criterion.test(element))
					resultList.add(element);
			Collections.sort(resultList, comparator);
			return new SelectionIterator<S,T>(resultList, (Criterion<S>)ALL_CRITERION, transformer);

			// Another alternative for an iterator which would not have to walk over the whole list and
			// still can return some sort order, would be one which returns a tuple of object and 
			// a REAL order number. That number would be used to insert into the final list (like the GUI list)
			// using a binary sort. So the result set would then be sorted on display and wouldn't need to
			// be loaded completely before anything could be shown.
		}
	}
		
	/** Filter a collection to only return elements that validate against the Criterion. */
	public static <S,T> Iterator<T> select(Collection<S> collection, Criterion<S> criterion, Transformer<S,T> transformer) {
		if(collection == null)
			return (Iterator<T>)EMPTY_ITERATOR;
		else
			return new SelectionIterator<S, T>(collection, criterion, transformer);
	}
	
	/** Filter a collection to only return elements that validate against the Criterion. */
	public static <S,T> Iterator<T> select(Collection<S> collection, Criterion<S> criterion, Comparator<S> comparator) {
		return select(collection, criterion, (Transformer<S,T>)IDENTITY_TRANSFORMER, comparator);
	}
	
	/** Filter a collection to only return elements that validate against the Criterion. */
	public static <S,T> Iterator<T> select(Collection<S> collection, Criterion<S> criterion) {
		if(collection == null)
			return (Iterator<T>)EMPTY_ITERATOR;
		else
			return new SelectionIterator<S, T>(collection, criterion, (Transformer<S,T>)IDENTITY_TRANSFORMER);
	}
	
	Collection<S> _source;
	Iterator<S> _sourceIterator;
	Criterion<S> _criterion;
	
	/** This MUST be kept in sync with the next element. No next can be called on the source iterator. */
	private T _nextElement = null;
	private boolean _removable = false;
	private Transformer<S,T> _transformer = (Transformer<S,T>)IDENTITY_TRANSFORMER;
	
	private void seek() {
		_removable = false;
		while(_sourceIterator.hasNext()) {
			S element = _sourceIterator.next();
			if(_criterion.test(element)) {
				_nextElement = _transformer.transform(element);
				return;
			}
		}
	}
	
	/** We don't like calling new. */
	private SelectionIterator(Collection<S> collection, Criterion<S> criterion, Transformer<S,T> transformer) {
		_source = collection;
		_sourceIterator = collection.iterator();
		_criterion = criterion;
		_transformer = transformer;
	}
	
	/** @see java.util.Iterator#hasNext() */
	public boolean hasNext() {
		if(_nextElement == null)
			seek();
		return _nextElement != null;
	}
	
	/** @see java.util.Iterator#next() */
	public T next() {
		if(_nextElement == null)
			seek();
		
		if(_nextElement == null)
			throw new NoSuchElementException();
		else {
			T element = _nextElement;
			_nextElement = null;
			_removable = true;

			return element;
		}
	}
	
	/** @see java.util.Iterator#remove() */
	public void remove() {
		if(_removable)
			_sourceIterator.remove();
		else 
			throw new IllegalStateException();
	}
}

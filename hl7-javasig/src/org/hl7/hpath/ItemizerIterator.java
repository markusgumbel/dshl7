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
 * Portions created by Initial Developer are Copyright (C) 2002-2006
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;

import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.LIST;
import org.hl7.types.ST;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.enums.EntityNamePartType;
import org.hl7.types.PN;
import org.hl7.types.PNXP;
import org.hl7.types.ON;
import org.hl7.types.ONXP;
import org.hl7.types.AD;
import org.hl7.types.ADXP;
import org.hl7.types.enums.AddressPartType;
import org.hl7.types.DSET;
import org.hl7.types.ValueFactory;

import java.lang.reflect.Type;

/** A decorator to render an Iterable or Array collection into an ExpressionIterator.
		This is done for itemizing collections in the expression, which is the default behavior unless the
		doNotItemize flag is set, indicated by the at ('@') prefix in an expression.

		@param P the type of the original collection
		@param Q the type returned by this iterator

		Note that this decorator is strictly used to unroll collections and its parent iterator is never 
		another Evaluation iterator. Hence, the root type R of this is always same as P.
		
    @author Gunther Schadow
    @version $Id: ItemizerIterator.java,v 1.4 2006/09/28 18:46:56 gschadow Exp $
*/
/*package*/ class ItemizerIterator<P,Q> implements EvaluationIterator<P,Q> {
	private Q _current;
	private P _originalCollection;
	private Iterator<Q> _parentIterator;
	
	private ItemizerIterator(P originalCollection, Iterable<Q> iterable) {
		if(originalCollection == null)
			throw new NullPointerException("originalCollection");
		_originalCollection = originalCollection;
		_parentIterator = iterable.iterator();
	}
	
	public ItemizerIterator(Iterable<Q> iterable) {
		this((P)iterable,iterable);
		// XXX: shouldn't have to cast to P here, as we're saying in this case P is an Iterable<Q>
	}
	
	public ItemizerIterator(Q[] originalCollection) {
		this((P)originalCollection, Arrays.asList(originalCollection));
		// XXX: shouldn't have to cast to P here, as we're saying in this case P is an Iterable<Q>
	}
	
  public Q next() { 
		_current = _parentIterator.next();
		if(_updateTool != null)
			_updateTool.next(_current);
		return _current;
	}

  public Q current() { return _current; }

	public Type currentType() {
		if(_current != null)
			return _current.getClass();
		else 
			return null;
	}

  public boolean hasNext() { return _parentIterator.hasNext(); }

	public boolean hasOnlySingleValue() {
		return false;
	}

	UpdateTool<P,Q> _updateTool = null;

	private void initializeUpdateStrategy() {
		if(_originalCollection == null)
			throw new NullPointerException("_originalCollection");
		if(_updateTool != null)
			throw new IllegalStateException("update tool already defined: " + _updateTool);
		for(UpdateTool.Definition updateStrategy : UPDATE_STRATEGIES)
			if(updateStrategy.isApplicable(_originalCollection)) {
				_updateTool = updateStrategy.instantiate(_originalCollection);
				return;
			}
		throw new UnsupportedOperationException("no update strategy found for " + _originalCollection.getClass());
	}
	
	public boolean updateIsConstructive() { 
		if(_updateTool == null)
			initializeUpdateStrategy();
		return _updateTool.isConstructive();
		// FIXME: how will we actually use this result in an itemizer iterator?
	}

	/** Prepares the iterator for updates. This usually must be called before the first normal access to the iterator. */
	public void beginUpdate() {
		if(_current != null)
			throw new IllegalStateException("must set the update mode before first access");
		if(_updateTool == null)
			initializeUpdateStrategy();
	}

	/** Updates the current value shown by the iterator. */
	public void update(Q newValue) {
		_updateTool.update(newValue);
	}
	
	/** Inserts a new value into the underlying sequence before the current value (if the sequence is of relevance). */
	public void insert(Q newValue) {
		_updateTool.insert(newValue);
	}

	/** Removes the current element from the underlying sequence. This is the same signature as the standard java.util.Iterator method, but it generally is only applicable if the iterator is in update mode, i.e., if beginUpdate() has been called first. */
	public void remove() {
		_updateTool.remove();
	}

	/** Completes the update. This should be called at the end when the iterator is exhausted. If it is called before the iterator has been fully exhausted the result is undefined. It might cause the updated set to loose all values not yet returned by the iterator. To complete the update, one of several update strategies will be used depending on the kinf of collection being updated. */
	public P finalizeUpdates() {
		return _updateTool.finish();
	}

	static interface UpdateTool<P,Q> {
		static interface Definition {
			public boolean isApplicable(Object originalCollection);
			public UpdateTool instantiate(Object originalCollection);
		}
		boolean isConstructive();
		void next(Q nextElement);
		void update(Q newValue);
		void insert(Q newValue);
		void remove();
		P finish();
	}

	static abstract class ConstructiveBufferedUpdateTool<P,Q,B extends Collection<Q>> implements UpdateTool<P,Q> {
		private P _originalCollection;
		private B _buffer;
		protected Q _current = null;
		private Q _currentUpdate = null;
		private boolean _removeCurrentElement = false;
		private boolean _hasAnyUpdates = false;
		
		public boolean isConstructive() { return true; }

		ConstructiveBufferedUpdateTool(P originalCollection, B buffer) {
			if(originalCollection == null)
				throw new NullPointerException("originalCollection");
			_originalCollection = originalCollection;
			_buffer = buffer;
		}

		public void next(Q nextElement) {
			if(_current != null && !_removeCurrentElement)
				_buffer.add(_currentUpdate != null ? _currentUpdate : _current);
			_currentUpdate = null;
			_removeCurrentElement = false;
			_current = nextElement;
		}
		
		protected abstract P createValueFromBuffer(B buffer, P originalCollection);
		
		public P finish() {
			if(!_hasAnyUpdates)
				return _originalCollection;
			else {
				next(null); // flush the last element
				return createValueFromBuffer(_buffer, _originalCollection);
			}
		}
		
		public void insert(Q newValue) {
			_buffer.add(newValue);
			_hasAnyUpdates = true;
		}
		
		public void update(Q newValue) {
			_currentUpdate = newValue;
			_hasAnyUpdates = true;
		}
		
		public void remove() {
			_removeCurrentElement = true;
			_hasAnyUpdates = true;
		}
	}	
	
	static class ConstructiveArrayUpdateTool<T> extends ConstructiveBufferedUpdateTool<T[],T,List<T>> {
		static class Definition<T> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof Object[];
			}
			public UpdateTool<T[],T> instantiate(Object originalCollection) {
				return new ConstructiveArrayUpdateTool<T>((T[])originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveArrayUpdateTool(T[] originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected T[] createValueFromBuffer(List<T> buffer, T[] originalCollection) {
			return buffer.toArray(originalCollection);
		}
	}

	static class ConstructiveJLListUpdateTool<T extends ANY> extends ConstructiveBufferedUpdateTool<List<T>,T,List<T>> {
		static class Definition<T extends ANY> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof List;
			}
			public UpdateTool<List<T>,T> instantiate(Object originalCollection) {
				return new ConstructiveJLListUpdateTool<T>((List<T>)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveJLListUpdateTool(List<T> originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected List<T> createValueFromBuffer(List<T> buffer, List<T> _) {
			return new ArrayList(buffer);
		}
	}

	static class ConstructiveJLSetUpdateTool<T extends ANY> extends ConstructiveBufferedUpdateTool<Set<T>,T,List<T>> {
		static class Definition<T extends ANY> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof Set;
			}
			public UpdateTool<Set<T>,T> instantiate(Object originalCollection) {
				return new ConstructiveJLSetUpdateTool<T>((Set<T>)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveJLSetUpdateTool(Set<T> originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected Set<T> createValueFromBuffer(List<T> buffer, Set<T> _) {
			return new HashSet(buffer);
		}
	}

	static class ConstructiveLISTUpdateTool<T extends ANY> extends ConstructiveBufferedUpdateTool<LIST<T>,T,List<T>> {
		static class Definition<T extends ANY> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof LIST;
			}
			public UpdateTool<LIST<T>,T> instantiate(Object originalCollection) {
				return new ConstructiveLISTUpdateTool<T>((LIST<T>)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveLISTUpdateTool(LIST<T> originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected LIST<T> createValueFromBuffer(List<T> buffer, LIST<T> _) {
			return ValueFactory.getInstance().LISTvalueOf(buffer);
		}
	}
	
	static class ConstructiveDSETUpdateTool<T extends ANY> extends ConstructiveBufferedUpdateTool<DSET<T>,T,List<T>> {
		static class Definition<T extends ANY> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof DSET;
			}
			public UpdateTool<DSET<T>,T> instantiate(Object originalCollection) {
				return new ConstructiveDSETUpdateTool<T>((DSET<T>)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveDSETUpdateTool(DSET<T> originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected DSET<T> createValueFromBuffer(List<T> buffer, DSET<T> _) {
			return ValueFactory.getInstance().DSETvalueOf(buffer);
		}
	}
	
	static class ConstructiveBAGUpdateTool<T extends ANY> extends ConstructiveBufferedUpdateTool<BAG<T>,T,List<T>> {
		static class Definition<T extends ANY> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof BAG;
			}
			public UpdateTool<BAG<T>,T> instantiate(Object originalCollection) {
				return new ConstructiveBAGUpdateTool<T>((BAG<T>)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveBAGUpdateTool(BAG<T> originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected BAG<T> createValueFromBuffer(List<T> buffer, BAG<T> _) {
			return ValueFactory.getInstance().BAGvalueOf(buffer);
		}
	}

	static class ConstructiveENUpdateTool<T extends ENXP> extends ConstructiveBufferedUpdateTool<EN,T,List<T>> {
		static class Definition<T extends ENXP> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof EN;
			}
			public UpdateTool<EN,T> instantiate(Object originalCollection) {
				return new ConstructiveENUpdateTool<T>((EN)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveENUpdateTool(EN originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected EN createValueFromBuffer(List<T> buffer, EN originalName) {
			return ValueFactory.getInstance().ENvalueOf((List<ENXP>)buffer, originalName.use(), originalName.useablePeriod());
		}
		
		private T typeFixup(ANY newValue) {
			if(newValue instanceof ENXP)
				return (T)newValue;
			else if(newValue instanceof ST) {
				if(newValue.isNull().isFalse())
					return (T)ValueFactory.getInstance().ENXPvalueOf(newValue.toString(), (EntityNamePartType)_current.type(), _current.qualifier());
				else
					return (T)ValueFactory.getInstance().nullValueOf("ENXP",newValue.nullFlavor().toString());
			} else
				throw new IllegalArgumentException("bad value supplied for ENXP: " + newValue.getClass() + ": " + newValue);
		}
		
		public void insert(T newValue) {
			super.insert(typeFixup(newValue));
		}
		
		public void update(T newValue) {
			super.update(typeFixup(newValue));
		}		
	}

	static class ConstructivePNUpdateTool<T extends PNXP> extends ConstructiveBufferedUpdateTool<PN,T,List<T>> {
		static class Definition<T extends PNXP> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof PN;
			}
			public UpdateTool<PN,T> instantiate(Object originalCollection) {
				return new ConstructivePNUpdateTool<T>((PN)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructivePNUpdateTool(PN originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected PN createValueFromBuffer(List<T> buffer, PN originalName) {
			return ValueFactory.getInstance().PNvalueOf((List<PNXP>)buffer, originalName.use(), originalName.useablePeriod());
		}

		
		private T typeFixup(ANY newValue) {
			if(newValue instanceof PNXP)
				return (T)newValue;
			else if(newValue instanceof ST) {
				if(newValue.isNull().isFalse())
					return (T)ValueFactory.getInstance().PNXPvalueOf(newValue.toString(), (EntityNamePartType)_current.type(), _current.qualifier());
				else
					return (T)ValueFactory.getInstance().nullValueOf("PNXP",newValue.nullFlavor().toString());
			} else
				throw new IllegalArgumentException("bad value supplied for PNXP: " + newValue.getClass() + ": " + newValue);
		}
		
		public void insert(T newValue) {
			super.insert(typeFixup(newValue));
		}
		
		public void update(T newValue) {
			super.update(typeFixup(newValue));
		}		
	}

	static class ConstructiveONUpdateTool<T extends ONXP> extends ConstructiveBufferedUpdateTool<ON,T,List<T>> {
		static class Definition<T extends ONXP> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof ON;
			}
			public UpdateTool<ON,T> instantiate(Object originalCollection) {
				return new ConstructiveONUpdateTool<T>((ON)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveONUpdateTool(ON originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected ON createValueFromBuffer(List<T> buffer, ON originalName) {
			return ValueFactory.getInstance().ONvalueOf((List<ONXP>)buffer, originalName.use(), originalName.useablePeriod());
		}

		private T typeFixup(ANY newValue) {
			if(newValue instanceof ONXP)
				return (T)newValue;
			else if(newValue instanceof ST) {
				if(newValue.isNull().isFalse())
					return (T)ValueFactory.getInstance().ONXPvalueOf(newValue.toString(), (EntityNamePartType)_current.type(), _current.qualifier());
				else
					return (T)ValueFactory.getInstance().nullValueOf("ONXP",newValue.nullFlavor().toString());
			} else
				throw new IllegalArgumentException("bad value supplied for ONXP: " + newValue.getClass() + ": " + newValue);
		}
		
		public void insert(T newValue) {
			super.insert(typeFixup(newValue));
		}
		
		public void update(T newValue) {
			super.update(typeFixup(newValue));
		}		
	}

	static class ConstructiveADUpdateTool<T extends ADXP> extends ConstructiveBufferedUpdateTool<AD,T,List<T>> {
		static class Definition<T extends ADXP> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof AD;
			}
			public UpdateTool<AD,T> instantiate(Object originalCollection) {
				return new ConstructiveADUpdateTool<T>((AD)originalCollection, new ArrayList<T>());
			}
		}
		public static Definition DEFINITION = new Definition();
		private ConstructiveADUpdateTool(AD originalCollection, List<T> buffer) {
			super(originalCollection, buffer);
		}
		protected AD createValueFromBuffer(List<T> buffer, AD originalName) {
			return ValueFactory.getInstance().ADvalueOf((List<ADXP>)buffer, originalName.use(), originalName.validTime());
		}


		private T typeFixup(ANY newValue) {
			if(newValue instanceof ADXP)
				return (T)newValue;
			else if(newValue instanceof ST) {
				if(newValue.isNull().isFalse())
					return (T)ValueFactory.getInstance().ADXPvalueOf(newValue.toString(), (AddressPartType)_current.type());
				else
					return (T)ValueFactory.getInstance().nullValueOf("ADXP",newValue.nullFlavor().toString());
			} else
				throw new IllegalArgumentException("bad value supplied for ADXP: " + newValue.getClass() + ": " + newValue);
		}
		
		public void insert(T newValue) {
			super.insert(typeFixup(newValue));
		}
		
		public void update(T newValue) {
			super.update(typeFixup(newValue));
		}		
	}

	
	/** Destructive update must be used when updating association sets etc., especially when they are mapped to a database by Hibernate, because it would be too expensive to delete and insert all those relationships. Destructive means that a change is made to the original collection. This class will buffer all changes as diffs and apply them in one go at the end; this avoids concurrent modification exceptions while iterating. */
	static abstract class DestructiveUpdateTool<P extends Collection<Q>,Q> implements UpdateTool<P,Q> {
		private P _originalCollection;
		private Q _current = null;
		private int _index = -1;
		
		/** A diff, i.e., a change that cann be applied later. 
				The way a diff is constructed specifies how the diff gets applied.
				If originalElement == null it means insert. 
				If newElement == null it means remove.
				The index is only used for collections types in which it makes sense.
		*/
		private static abstract class Diff<P,Q> {
			protected int _index;
			protected Q _originalElement;
			protected Q _newElement;
			protected Diff(Q originalElement, Q newElement, int index) {
				_index = index;
				_originalElement = originalElement;
				_newElement = newElement;
			}
		  protected abstract int apply(P subjectCollection, int indexAdjustment);
		}
		private List<Diff<P,Q>> diffList = new ArrayList<Diff<P,Q>>();

		protected abstract Diff<P,Q> newDiff(Q originalElement, Q newElement, int index);
		
		public boolean isConstructive() { return false; }

		DestructiveUpdateTool(P originalCollection) {
			if(originalCollection == null)
				throw new NullPointerException("originalCollection");
			_originalCollection = originalCollection;
		}
		public void next(Q nextElement) {
			_current = nextElement;
			_index++;
		}
		public void insert(Q newValue) {
			diffList.add(newDiff(null, newValue, _index));
		}		
		public void update(Q newValue) {
			diffList.add(newDiff(_current, newValue, _index));
		}		
		public void remove() {
			diffList.add(newDiff(_current, null, _index));
		}		
		public P finish() {
			int indexAdjustment = 0;
			for(Diff<P,Q> diff : diffList)
				indexAdjustment = diff.apply(_originalCollection, indexAdjustment);
			return _originalCollection;
		}
	}

	static class DestructiveSetUpdateTool<T> extends DestructiveUpdateTool<Set<T>,T> {
		static class Definition<T> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof Set;
			}
			public UpdateTool<Set<T>,T> instantiate(Object originalCollection) {
				return new DestructiveSetUpdateTool<T>((Set<T>)originalCollection);
			}
		}
		static class Diff<T> extends DestructiveUpdateTool.Diff<Set<T>,T> {
			private Diff(T originalElement, T newElement, int index) { super(originalElement, newElement, index);	}
			protected int apply(Set<T> set, int indexAdjustment) {
				if(_originalElement != null)
					if(set.remove(_originalElement)) indexAdjustment--;
				if(_newElement != null)
					if(set.add(_newElement)) indexAdjustment++;
				return indexAdjustment;
			}
		}
		public static Definition DEFINITION = new Definition();
		protected Diff<T> newDiff(T originalElement, T newElement, int index) {
			return new Diff<T>(originalElement, newElement, index);
		}
		private DestructiveSetUpdateTool(Set<T> originalCollection) {	super(originalCollection);}
	}

	static class DestructiveListUpdateTool<T> extends DestructiveUpdateTool<List<T>,T> {
		static class Definition<T> implements UpdateTool.Definition {
			private Definition() { }			
			public boolean isApplicable(Object originalCollection) {
				return originalCollection instanceof List;
			}
			public UpdateTool<List<T>,T> instantiate(Object originalCollection) {
				return new DestructiveListUpdateTool<T>((List<T>)originalCollection);
			}
		}
		static class Diff<T> extends DestructiveUpdateTool.Diff<List<T>,T> {
			private Diff(T originalElement, T newElement, int index) { super(originalElement, newElement, index);	}
			protected int apply(List<T> list, int indexAdjustment) {
				int index = _index + indexAdjustment;
				if(_originalElement != null) {
					T actualElement = list.get(index);
					if(!actualElement.equals(_originalElement))
						throw new NoSuchElementException("at index " + index + "(" + _index + " " + indexAdjustment + ") expected " + _originalElement + " but found " + actualElement);
					if(_newElement == null) {
						list.remove(index);
						indexAdjustment--;
					}	else
						list.set(index, _newElement);
				} else if(_newElement != null) {
					list.add(index,_newElement);
					indexAdjustment++;
				}
				return indexAdjustment;
			}
		}
		public static Definition DEFINITION = new Definition();
		protected Diff<T> newDiff(T originalElement, T newElement, int index) {
			return new Diff<T>(originalElement, newElement, index);
		}
		private DestructiveListUpdateTool(List<T> originalCollection) {	super(originalCollection);}
	}

	private static UpdateTool.Definition UPDATE_STRATEGIES[] = {
		ConstructiveArrayUpdateTool.DEFINITION,
		ConstructivePNUpdateTool.DEFINITION,
		ConstructiveONUpdateTool.DEFINITION,
		ConstructiveENUpdateTool.DEFINITION,
		ConstructiveADUpdateTool.DEFINITION,
		ConstructiveLISTUpdateTool.DEFINITION,
		ConstructiveDSETUpdateTool.DEFINITION,
		ConstructiveBAGUpdateTool.DEFINITION,
		DestructiveListUpdateTool.DEFINITION,
		DestructiveSetUpdateTool.DEFINITION
	};
}



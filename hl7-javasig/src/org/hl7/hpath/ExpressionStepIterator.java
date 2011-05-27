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

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;

/** A base class with common strategies that most Expressions share
		which occur in the middle of a chain of evaluation steps. So they all deal
		with preceding (parent) expression, itemization of intermediary results,
		and the things related to their updating.

		@param R - the type of the root context object
		@param P - the type of the parent (previous) expression step
		@param Q - the type returned by itemization
		@param T - the type returned by this step

		@author Gunther Schadow
		@version $Id$
*/
/*package*/ abstract class ExpressionStepIterator<R,P,Q,T> extends EvaluationIteratorBase<R,T> {
	protected Evaluation<R,?> _evaluation;
	protected EvaluationIterator<R,P> _parentIterator;
	protected EvaluationIterator<P,Q> _itemizerIterator;
	private boolean _doNotItemize = false;
	/** Whether we would call our apply(Q) method on null if nothing else comes down the pike (used for Conversions). */
	private boolean _defaultApplicableNullPending = false;
	/** Whether we return a default value which we do not apply any more if nothing else comes down the pike. */ 
	private Expression<R,T> _defaultResultExpression = null;
	
	ExpressionStepIterator(Evaluation<R,?> evaluation,  EvaluationIterator<R,P> parentIterator, boolean doNotItemize, boolean allowDefaultNull) {
		this(evaluation, parentIterator, doNotItemize, allowDefaultNull, null);
	}

	ExpressionStepIterator(Evaluation<R,?> evaluation,  EvaluationIterator<R,P> parentIterator, boolean doNotItemize, boolean allowDefaultNull, Expression<R,T> defaultResultExpression) {
		_evaluation = evaluation;
		_parentIterator = parentIterator;
		_defaultApplicableNullPending = allowDefaultNull;
		_defaultResultExpression = defaultResultExpression;
		_doNotItemize = doNotItemize;
	}

	// XXX: I don't really like the defaultResultExpression here. It has nothing to do with the 
	// actual filter or whatever the expression step might be. Rather have a default expression
	// step that would exist in any place where an ItemizerIterator might be used? Just the 
	// default expression step that returns previous result (itemized if needed) or if no such
	// value came down from predecessor step, give the result value. Also this would need to 
	// fit into the ItemizerIterator step so that a default could be added to the itemized
	// collection when it is being updated. As of now, this default expression would come down
	// and would be itemized. But there is the issue of when the ItemizerIterator is closed
	// and finalized. It should not be closed and finalized until a new one is created or until
	// everything is finalized. We must keep the last itemizer iterator always pending in order
	// to be able to add on to it even if we already determined that it has been exhausted.

	/** This applies the expression to whatever comes out of the parent (or itemized). 
			@return a value to show the result on the output, nulls are suppressed.
	*/
	protected abstract T apply(Q context);
	
	public boolean hasNext() {
		return super.hasNext() || _defaultApplicableNullPending || _defaultResultExpression != null;
		// FIXME: defaultApplicableNullPending does not actually guarantee we get something from apply, does it?
	}
	
	protected T seek() {
		T nextElement = null;
		while(nextElement == null) {
			Q contextObject = nextContextObject();
			if(contextObject == null && !_defaultApplicableNullPending && _defaultResultExpression == null)
				return null;
			else {
				_defaultApplicableNullPending = false; // if any value was seen from parent, no default null is apply()ed any more.
				if(contextObject != null)
					nextElement = apply(contextObject);
				else { // now we have exhausted all our options
					if(_defaultResultExpression == null)
						nextElement = apply(null);
					else {
						Iterator<T> defaultResultIterator = _evaluation.getCurrentAsChild(_defaultResultExpression).iterator();
						if(defaultResultIterator.hasNext()) // we only need one default, not multiple, and we never itemize it
							nextElement = defaultResultIterator.next();
					}
				}
			}
		}
		if(nextElement != null) // if anything real has ever been returned, we don't need the default result any more.
			_defaultResultExpression = null;
		return nextElement;
	}

	private Q nextContextObject() {
		Q result = null;
		if(_doNotItemize) { // means Q == P
			while(result == null && _parentIterator.hasNext())
				result =  (Q)_parentIterator.next();
			return result;

		}	else {
			while(result == null) {
				if(_itemizerIterator == null || !_itemizerIterator.hasNext()) {
					if(_itemizerIterator != null && _isUpdating && !_defaultApplicableNullPending) // HERE IS WHERE WE HAVE TO PRODUCE THE DEFAULT VALUE FIRST
						_parentIterator.update(_itemizerIterator.finalizeUpdates());

					if(!_parentIterator.hasNext())
						return null; // XXX: _itemizerIterator still set, good, but finalizedUpdates, bad
					else {
						P source = _parentIterator.next();
						if(source == null)
							continue;
						else {
							_itemizerIterator = (EvaluationIterator<P,Q>)Evaluation.itemizerIterator(source);
							if(_itemizerIterator == null) // means that the source needed no wrapping and Q == P
								return (Q)source;
							if(_isUpdating)
								_itemizerIterator.beginUpdate();
						}
					}
				}

				if(_itemizerIterator.hasNext())
					result = _itemizerIterator.next();
			}
			return result;
		}
	}

	public boolean hasOnlySingleValue() {
		return (_parentIterator.hasOnlySingleValue() || _doNotItemize) 
			&& (_itemizerIterator == null || _itemizerIterator.hasOnlySingleValue());
	}

	/** The estimated type of the result, used to borrow a value against this property if it had returned no value. 
			Let us remember, 
			<dl>
			<dt>R</dt> <dd>the type of the root context object</dd>
			<dt>P</dt> <dd>the type of the parent (previous) expression step,</dd>
			<dt>Q</dt> <dd>the type returned by itemization</dd>
			<dt>T</dt> <dd>the type returned by this step</dd>
			</dl>

			So, we take the parent's type P and see if we would have used itemization to get Q. 
			Then, in case of Property (or Conversion) T is the type finally returned. 
			For other steps, e.g., Filters, T is same as Q. 
			So, here we will return the type for the case where T == Q, and Q == P if we are not itemizing. 
			This method does know how to infer the type of itemization.
	*/
	public Type currentType() {
		if(_parentIterator instanceof EvaluationIterator) {
			Type parentType = ((EvaluationIterator)_parentIterator).currentType();
			if(_doNotItemize)
				return parentType;
			else {
				Type itemizerResultType = itemizerResultTypeOf(parentType);
				if(itemizerResultType == null) // meaning no itemization applies
					return parentType;
				else
					return itemizerResultType;
			}
		} else // if parent is not expression step iterator we bail out, ... what about SingletonIterator?
			return null;

		// now that wasn't so hard, was it?
		// Property (and Conversion) will override this and call super.currentType() to get Q and then infer T from it
		// Wow, we are getting to the point where we could do static type checking in HPath expressions.
	}
	
	boolean _isUpdating = false;

	public boolean updateIsConstructive() {
		return _parentIterator.updateIsConstructive();
	}
	
	public void beginUpdate() {
		_parentIterator.beginUpdate();
		_isUpdating = true;
	}	

	public R finalizeUpdates() { 
		return _parentIterator.finalizeUpdates();
	}


	// Estimate the type coming out of an itemizer, used for borrowing against a property

	/*package*/ static Type itemizerResultTypeOf(Type type) {
		if(type instanceof Class) {
			Class clazz = (Class)type;
			if(clazz.isArray())
				return clazz.getComponentType();
			else
				return null;
		} else {
			Type result = iterableResultTypeOf(type);
			if(result instanceof WildcardType) {
				return ((WildcardType)result).getUpperBounds()[0];
			} else if(result instanceof TypeVariable) {
				return ((TypeVariable)result).getBounds()[0];
			} else 
				return result;
		}
	}
	
	/*package*/ static Type iterableResultTypeOf(Type type) {
		final Class raw;
		final Type actualArguments[];

		if(type instanceof Class) {
			raw = (Class)type;
			actualArguments = null;
		} else if(type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType)type;
			raw = (Class)parameterizedType.getRawType();
			actualArguments = parameterizedType.getActualTypeArguments();
		} else if(type == null) {
			return null;
		} else {
			throw new Error("unhandled case: " + type + " " + type.getClass());
		}
		
		if(raw.equals(Iterable.class)) { // direct hit
			if(actualArguments != null)
				return actualArguments[0]; // we know there must be one argument
			else // no actual argument was given and we know the bound is Object
				return Object.class;
			
		} else { // search for the type in supers
			Type theType = null;
			Type superType = raw.getGenericSuperclass();
			if(superType != null)
				theType = iterableResultTypeOf(superType);
			if(theType == null) {
				for(Type interfaceType : raw.getGenericInterfaces()) {
					theType = iterableResultTypeOf(interfaceType);
					if(theType != null)
						break;
				}
			}
			// now we have the type
			if(theType instanceof TypeVariable) { // resolve a variable if any
				String name = ((TypeVariable)theType).getName();
				TypeVariable formalArguments[] = raw.getTypeParameters();
				for(int i = 0; i < formalArguments.length; i++)
					if(formalArguments[i].getName().equals(name))
						return actualArguments[i]; 
				throw new Error("a type variable without a matching actual argument is not possible");
			} else 
				return theType;
		}
	}
}


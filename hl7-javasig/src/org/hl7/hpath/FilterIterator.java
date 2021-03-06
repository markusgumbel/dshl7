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

/*package*/ abstract class FilterIterator<R,P,Q,T> extends ExpressionStepIterator<R,P,Q,T> {
	FilterIterator(Evaluation<R,P> evaluation,  EvaluationIterator<R,P> parentIterator, boolean doNotItemize) {
		this(evaluation, parentIterator, doNotItemize, null);
	}
	FilterIterator(Evaluation<R,P> evaluation,  EvaluationIterator<R,P> parentIterator, boolean doNotItemize, Expression<R,T> defaultExpression) {
		super(evaluation, parentIterator, doNotItemize, false, defaultExpression);
	}
		
	protected T apply(Q nextElement) {
		if(test(nextElement))
			return (T)nextElement;
		else
			return null;
	}

	protected abstract boolean test(Q element);
		
	public void update(T newValue) { 
		if(_itemizerIterator == null)
			_parentIterator.update((P)newValue);
		else
			_itemizerIterator.update((Q)newValue);
	}

	public void insert(T newValue) {
		if(_itemizerIterator == null)	
			_parentIterator.insert((P)newValue);
		else
			_itemizerIterator.insert((Q)newValue);
	}

	public void remove() { 
		if(_itemizerIterator == null)
			_parentIterator.remove();
		else
			_itemizerIterator.remove();
	}
}

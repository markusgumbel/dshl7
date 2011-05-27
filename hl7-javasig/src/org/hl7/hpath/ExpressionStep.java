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
/*package*/ abstract class ExpressionStep<R,P,Q,T> extends Expression<R,T> {
  protected Expression<R,P> _previousExpression = null;
  protected boolean _doNotItemize = false; // if the property is from the source of the iterator or from the next element
	protected Expression<R,T> _defaultExpression = null;

  protected ExpressionStep(Expression<R,P> previousExpression, boolean doNotItemize, Expression<R,T> defaultExpression) {
    _previousExpression = previousExpression;
    _doNotItemize = doNotItemize;  
		_defaultExpression = defaultExpression;
	}

  protected ExpressionStep(Expression<R,P> previousExpression, boolean doNotItemize) {
		this(previousExpression, doNotItemize, null);
	}

	protected abstract ExpressionStepIterator<R,P,Q,T> newIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,P> parentIterator);
	
  protected EvaluationIterator<R,T> createEvaluationIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,R> sourceIterator) {
		if(_previousExpression == null)
			return newIterator(evaluation, (EvaluationIterator<R,P>)sourceIterator);
		else
			return newIterator(evaluation, _previousExpression.createEvaluationIterator(evaluation, sourceIterator));
	}  
}

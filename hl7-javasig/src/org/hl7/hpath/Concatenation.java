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
import java.util.LinkedList;
import java.util.List;

/** The Concatenation Expression allows to chain another expression after the previous. */
/*package protected*/ class Concatenation<R,T> extends Expression<R,T> {
  private List<Expression<R,T>> _concatenatedExpressionsList;

  public static final Language.ExpressionForm FORM = new Language.ExpressionForm() {
      public Expression parse(StaticContext staticContext) throws SyntaxError {
				return Concatenation.parse(staticContext);
      }
    };
  
  public static Expression parse(StaticContext staticContext) throws SyntaxError {
		if(staticContext.tokenizer().peek().type() == TokenType.CONCAT) {
			List<Expression> concatenatedExpressionsList = new LinkedList<Expression>();

			Expression previousExpression = staticContext.expression();
			if(previousExpression == null)
				throw new SyntaxError("missing expression before " + staticContext.expressionString() + " at '" + staticContext.tokenizer().peek().string() + "'");
			else
				concatenatedExpressionsList.add(previousExpression);			
			
			while(staticContext.tokenizer().peek().type() == TokenType.CONCAT) {
				staticContext.tokenizer().next();
				staticContext.expression(null);
				Expression concatenatedExpression = Expression.parse(staticContext);
				if(concatenatedExpression == null)
					throw new SyntaxError("missing expression after " + staticContext.expressionString() + " at '" + staticContext.tokenizer().peek().string() + "'");
				if(concatenatedExpression instanceof Concatenation)
					concatenatedExpressionsList.addAll(((Concatenation)concatenatedExpression)._concatenatedExpressionsList);
				else
					concatenatedExpressionsList.add(concatenatedExpression);
			}
			
			return staticContext.expression(new Concatenation(concatenatedExpressionsList));			
		} else 
			return null;
	}
	
  private Concatenation(List<Expression<R,T>> concatenatedExpressionsList) {
		_concatenatedExpressionsList = concatenatedExpressionsList;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
		sb.append("Concatenation[");
		boolean separatorNeeded = false;
		for(Expression expression : _concatenatedExpressionsList) {
			if(separatorNeeded)
				sb.append(' ');
			else
				separatorNeeded = true;
			sb.append(expression.toString());			
		}
		sb.append("]");
		return sb.toString();
  }

  public EvaluationIterator<R,T> createEvaluationIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,R> sourceIterator) {
		return new InnerIterator(evaluation, sourceIterator);
	}  

  // The concatenation iterator could be so simple, but it is not simple because 
	// iterating over the first item in the concatenate list will exhaust the 
	// context iterator and then there is nothing left for the following expressions.

	// So we need to control the iteration of the context iterator and give all
	// contatenated expressions a chance to work with each context object.

  class InnerIterator<S> extends EvaluationIteratorBase<R,T> {
		private Evaluation<R,?> _evaluation;

		private EvaluationIterator<R,S> _sourceIterator; // the context iterator
		private Iterator<Expression<R,T>> _concatenatedExpressionsIterator = null; // iterates over expression, not evaluation
		private EvaluationIterator<R,T> _evaluationIterator = null; // when evaluating one of the concatenated expressions in the current context from sourceIterator, this will have the results.
		
    InnerIterator(Evaluation<R,?> evaluation,  EvaluationIterator<R,S> sourceIterator) {
			_evaluation = evaluation;
			_sourceIterator = sourceIterator;
    }
	
		/** Sets the _nextElement to a new non-null value, searching as needed. */
		protected T seek() {
			T nextElement = null;

			while(nextElement == null) {
				if(_evaluationIterator == null || !_evaluationIterator.hasNext()) {
					if(_evaluationIterator != null && _isUpdating)
						_sourceIterator.update((S)_evaluationIterator.finalizeUpdates());

					if(_concatenatedExpressionsIterator == null || !_concatenatedExpressionsIterator.hasNext()) {
						if(_sourceIterator == null || !_sourceIterator.hasNext()) {
							return null;
						}
						_sourceIterator.next();
						_concatenatedExpressionsIterator = _concatenatedExpressionsList.iterator();
					}

					if(_concatenatedExpressionsIterator.hasNext()) {
						_evaluationIterator = _evaluation.getCurrentAsChild(_concatenatedExpressionsIterator.next()).iterator();
						if(_isUpdating)
							_evaluationIterator.beginUpdate();
					}
				}
				
				if(_evaluationIterator.hasNext())
					nextElement = _evaluationIterator.next();
			}
			return nextElement;
		}

		public boolean hasOnlySingleValue() {
			return false;
		}
		
		boolean _isUpdating = false;

		public boolean updateIsConstructive() {
			return false; // XXX: that's just a safe guess
		}
	
		public void beginUpdate()  { 
			if(_evaluationIterator != null)
				throw new IllegalStateException("begin update must be called before accessing the iterator in any other way");
			else
				_isUpdating = true;
			_sourceIterator.beginUpdate();
		}	

		public void update(T newValue) { 
			if(_evaluationIterator == null)
				throw new IllegalStateException("nothing to update yet");
			else
				_evaluationIterator.update(newValue);
		}

		public void insert(T newValue) {
			if(_evaluationIterator == null)
				throw new IllegalStateException("nothing in scope before which we can insert");
			else
				_evaluationIterator.insert(newValue);
		}

		public void remove() {
			if(_evaluationIterator == null)
				throw new IllegalStateException("nothing to remove yet");
			else
				_evaluationIterator.remove();
		}

		public R finalizeUpdates() { 
			return _sourceIterator.finalizeUpdates();
		}
	}
}

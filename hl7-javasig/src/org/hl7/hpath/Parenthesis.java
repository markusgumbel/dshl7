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


/** The Parenthesis Expression allows overriding precedence, needed for the concatenation 
		expression followed by a filter, i.e., (foo|bar)[1] to do something like coalesce. 
*/
/*package protected*/ class Parenthesis<R,T> extends Expression<R,T> {
  public static final Language.ExpressionForm FORM = new Language.ExpressionForm() {
      public Expression parse(StaticContext staticContext) throws SyntaxError {
				return Parenthesis.parse(staticContext);
      }
    };
  
  public static Expression parse(StaticContext staticContext) throws SyntaxError {
    Token token = staticContext.tokenizer().peek();
    switch(token.type()) {
		case LPAR:
			staticContext.tokenizer().next();
			Expression previousExpression = staticContext.expression();
			if(previousExpression == null) {
				staticContext.expression(null);
				Expression parentheticalExpression = Expression.parse(staticContext);
				staticContext.tokenizer().next(TokenType.RPAR);
				return parentheticalExpression;
      } else 
				throw new SyntaxError("parenthesis in context in " + staticContext.expressionString() + " at '" + token.string() + "'");
			
    default:
      return null;
		}
  }

	EvaluationIterator<R,T> createEvaluationIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,R> sourceIterator) {
		throw new IllegalStateException("should never be here");
	}
}

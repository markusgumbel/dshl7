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

import org.hl7.types.INT;

/** The Filter Expression will select only those values that meet the filter criterion . */
/*package*/ abstract class Filter<R,P,Q,T> extends ExpressionStep<R,P,Q,T> {

  public static final Language.ExpressionForm FORM = new Language.ExpressionForm() {
      public Expression parse(StaticContext staticContext) throws SyntaxError {
				return Filter.parse(staticContext);
      }
    };
  
  public static Expression parse(StaticContext staticContext) throws SyntaxError {
    Token token = staticContext.tokenizer().peek();
		boolean doNotItemize = false;
    switch(token.type()) {
		case CLBRACK:
			doNotItemize = true;
			//FALLTHROUGH
		case LBRACK:
			staticContext.tokenizer().next();
			Expression previousExpression = staticContext.expression();
			staticContext.expression(null);
      Expression criterionExpression = Expression.parse(staticContext);
			Expression defaultExpression = null;
			if(staticContext.tokenizer().peek().type() == TokenType.SLASHDOT) { // optional default
				staticContext.tokenizer().next();
				staticContext.expression(null);
				defaultExpression = Expression.parse(staticContext);
				staticContext.expression(criterionExpression);  // write expression back. FIXME: should have nested static contexts instead.
			}
      staticContext.tokenizer().next(TokenType.RBRACK);
			if(criterionExpression instanceof Literal) {
				Object value = ((Literal)criterionExpression).getValue();
				if(value instanceof INT)
					return staticContext.expression(new SingleIndexFilter(previousExpression, ((INT)value).intValue(), doNotItemize));
			}
			return staticContext.expression(new CriteriaFilter(previousExpression, criterionExpression, doNotItemize, defaultExpression));
			
    default:
      return null;
		}
  }

  protected Filter(Expression<R,P> previousExpression, boolean doNotItemize) {
		this(previousExpression, doNotItemize, null);
  }

  protected Filter(Expression<R,P> previousExpression, boolean doNotItemize, Expression<R,T> defaultExpression) {
		super(previousExpression, doNotItemize, defaultExpression);
  }
}

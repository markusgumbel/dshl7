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

import java.util.HashMap;
import java.util.Map;

/** The abstract Expression for HPath. HPath is the HL7 RIM graph path 
    language, similar to xpath, but made specifically to navigate HL7 RIM 
    object graphs.

    <p>Similar to XPath, in HPath expression results are generally 
    considered collections, to which filters can be applied. That's why
    the evaluation of expressions happens with iterators.

    <p>The LALR(1) form of the grammar for HPath expressions at present is
    this:
    
    <pre>
      expression : simpleExprDef   { return $1 }
			           | concatenation   { return $1 }
???							 | exprWithDefault { return $1 }
								 ;

			simpleExpr : property     { return $1; }
                 | filter       { return $1; }
								 | variable     { return $1; }
								 | parenthesis  { return $1; }
								 | literal      { return $1; }
								 ;

      property : expression "." IDENT arguments { return new Property($1, $3, $4); }
               | IDENT arguments                { return new Property(null, $1, $2}; }
							 ;

      arguments : "(" argumentlist ")" { return $2; }
               | /empty/               { return null; }
							 ;

      argumentlist : argumentlist "," expression  { $1.add($3); return $1; }
                   | expression                   { List<Expression> args = new ArrayList<Expression>(); args.add($1); return args; }
									 ;

      filter : expression "[" expression "]" { return new Filter{$1, $3}; }
             ;

      parenthesis : "(" expression ")" { return $2; }
						      ;

			concatenation : expression | simpleExpr { return new Concatenation{$1, $2}; }
			              ;

???		exprWithDefault : simpleExpr "!" parenthesis { return new ExpressionWithDefault($1, $2); }
								      ;
			      
      variable : "$" IDENT { return new Variable{$2}; }
               ; 

      literal : INTLIT  { return $1; }
              | REALLIT { return $1; }
              | STLIT   { return $1; }
              | BLLIT   { return $1; }
              | TSLIT   { return $1; }
              | PQLIT   { return $1; }
							| CSLIT   { retyrn $1; }
							...
	      ;
    </pre>

    <p>This language is implemented as an extensible language, that is, 
    one can add additional expression types simply by extending the expression
    and registering it with the base expression class. The key is that 
    extensions constructs of the language are registered before the established
    language constructs so that the extended language overrides the established
    forms. For example, if we want to add a "some ... in ... satisfies ..." form,
    we would register this form last, that way "some" would become a keyword.

		<p>The API to HPath is as demonstrated in the following example:</p>
		<pre>
		  Expression expr = Expression.valueOf(expressionString);
			Evaluation eval = expr.evaluate(source)
          .bindVariable(varName1, varValue1)
          .bindVariable(varName2, varValue2)
				//...
				  .bindVariable(varNameN, varValueN);
			for(Object value : eval) {
			  System.out.println(value.toString());
			}

			if(expr.effectiveBooleanValue())
			  ...

		</pre>

		<p>Notice the parameters T is the type to which the expression evaluates
		(T = type of the expression). S is the type to which the parent expression 
		should evaluate (S = source of this evaluation step). R is the type of the 
		context (R = root). Remember, even though an expression represents a link
		in a chain, the expression has all its previous links determined, therefore
		the S parameter of the initial link is the same as the R parameter of the 
		expression.
*/
public abstract class Expression<R,T> {

	/** A cache of parsed expressions. */
	private static final Map<String, Expression> _expressionCache = new HashMap<String, Expression>();
	
  /** Parses a string into an expression. */
  public static Expression valueOf(String expressionString) throws SyntaxError {
		Expression expression = _expressionCache.get(expressionString);
		if(expression == null) {
			String expandedExpressionString = Expression.expandMacros(expressionString);
			expression = _expressionCache.get(expandedExpressionString);
			if(expression == null) {
				expression = Expression.parse(new StaticContext(expandedExpressionString));
				if(expression == null)
					throw new NullPointerException("expression parser returned null for " + expandedExpressionString);
				_expressionCache.put(expandedExpressionString,expression);
			}
			_expressionCache.put(expressionString,expression);
		}
		return expression;
	}
	
  /** Expands macros in an expression to normalize the expression. */
  public static String expandMacros(String text) throws SyntaxError {
    return Language.instance().macros().expandMacros(text);
  }

  /** Parses a token stream into an expression. */
  protected static Expression parse(StaticContext staticContext) throws SyntaxError {
  tokens:
    while(staticContext.tokenizer().peek() != null) {
			// System.out.println(staticContext.tokenizer().peek().toString());
      for(Language.ExpressionForm expressionForm : Language.instance().expressionForm()) {
				Expression expression = expressionForm.parse(staticContext);
				if(expression != null)
					continue tokens;
      }
      break;
    }
    return staticContext.expression();
  }

	public Evaluation<R,T> evaluate(R sourceObject) {
		return new Evaluation<R,T>(sourceObject, this);	
	}

	/*package*/ abstract EvaluationIterator<R,T> createEvaluationIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,R> sourceIterator);
	
	/** Update an expression with the constant value provided.
			@return the updated root object, or, if that is immutable, return a new object that has these updates applied.
	*/
	public R update(R sourceObject, T newValue) {
		EvaluationIterator<R,T> iterator = new Evaluation<R,T>(sourceObject, this).iterator();
		iterator.beginUpdate();
		if(iterator.hasNext()) {
			while(iterator.hasNext()) {
				iterator.next();
				iterator.update(newValue);
			}
		} else
			iterator.insert(newValue);
		return iterator.finalizeUpdates();
	}
}

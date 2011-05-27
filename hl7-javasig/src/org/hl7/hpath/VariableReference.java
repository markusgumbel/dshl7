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


/** Any of the variable references in HPath are similar to XPath, 
		identifiers following the '$'. 

  @author Gunther Schadow
  @version $Id: Variable.java,v 1.6 2006/08/16 04:01:13 gschadow Exp $
*/ 
/*package protected*/ class VariableReference<R,T> extends Expression<R,T> {
	String _name;
	
  public static final Language.ExpressionForm FORM = new Language.ExpressionForm() {
      public Expression parse(StaticContext staticContext) throws SyntaxError {
				return VariableReference.parse(staticContext);
      }
    };
  
  public static Expression parse(StaticContext staticContext) throws SyntaxError {
		if(staticContext.expression() == null && staticContext.tokenizer().peek().type() == TokenType.VARREF)
      return staticContext.expression(new VariableReference((String)staticContext.tokenizer().next().value()));
    else 
      return null;			
	}
	
  private VariableReference(String name) {
    _name = name;
  }

	/*package*/ T getValue() {
		return (T)_name;
	}
	
  public String toString() {
    return "Variable[" + _name + "]";
  }
  
  /** Evaluates the expression, always returning an iterator, even if there is only a single result. */
  public EvaluationIterator<R,T> createEvaluationIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,R> sourceIterator) {
		return new SingletonIterator(evaluation.getVariableValue(_name));
  }  
}

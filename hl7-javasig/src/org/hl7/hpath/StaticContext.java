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

public class StaticContext {
  private Tokenizer<?> _tokenizer;
  private Expression<?,?> _expression = null;
	private String _expressionString = null;
	
  /*package protected*/ StaticContext(String expressionString) {
		_expressionString = expressionString;
    _tokenizer = new Tokenizer(_expressionString);
  }
	
  public Tokenizer<?> tokenizer() { return _tokenizer; }
  public Expression<?,?> expression() { return _expression; }
  public <R,T> Expression<R,T> expression(Expression<R,T> expression) { 
		_expression = expression; 
		return expression;
	}
	public String expressionString() { return _expressionString; }
}

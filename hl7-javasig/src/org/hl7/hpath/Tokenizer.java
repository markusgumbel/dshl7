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

import org.regenstrief.util.RegexTokenizer;

/** The HPath Tokenizer.

    <p>The EBNF for HPath expressions at present is:
    
    <pre>
      expression : path;

      path : step "." path;

      step : primary ( filter )*;

      filter : "[" predicate "]";

      primary : property | literal | variable | parenthesis;

      parenthesis : "(" expression ")";

      variable : "$" variable;
    </pre>

 */
/*package protected*/ final class Tokenizer<T> implements java.util.Iterator<Token<T>> {
  RegexTokenizer _regexTokenizer;
  private Token<T> _nextToken;

  public String getTokenString() { return _nextToken.string(); }
  public T getTokenValue() { return _nextToken.value(); }

  private static final String PATTERN;

  static {
    StringBuffer sb = new StringBuffer();
    boolean isFirst = true;
    for(TokenType tokenType : TokenType.ALL_TOKEN_TYPES) {
      String pattern = tokenType.pattern();
      if(pattern != null) {
				if(isFirst) {
					sb.append("(");
					isFirst = false;
				} else
					sb.append("|(");
				sb.append(pattern);
				sb.append(")");
      }
    }
    PATTERN = sb.toString();
    //System.out.println(PATTERN);
  }

  public Tokenizer(String input) throws SyntaxError {
    _regexTokenizer = new RegexTokenizer(PATTERN, input);
    _nextToken = nextToken();
  }
	
  private Token<T> nextToken() throws SyntaxError {
    if(_regexTokenizer.next()) {
    //	System.out.println("#"+_regexTokenizer.token()+"#");
			int group = 0;
      for(TokenType tokenType : TokenType.ALL_TOKEN_TYPES) {
				if(tokenType.pattern() != null) {
					group++;				
					//System.out.println("#"+tokenType+tokenType.ordinal()+" "+group);
					String tokenString = _regexTokenizer.group(group);
					if(tokenString != null && tokenString.length() > 0) {
						if(tokenType.isIgnorable()) {
							return nextToken();
						} 
						else {
							Token<T> token = new Token<T>(tokenType, tokenString);
						//	System.out.println("TOK: " + token);
							return token;
						}
					}
				}
			}
      throw new Error("program error, please report with stack dump: " + _regexTokenizer.token());
    } else {
      String rest = _regexTokenizer.rest();
      if(rest == null || rest.length() == 0)
				return new Token<T>(TokenType.END_OF_STREAM, null);
      else
				throw new SyntaxError("syntax error at: " + rest);
    }  
  }

  public boolean hasNext() {
    return _nextToken == null;
  }

  /** Peeks at the next token without consuming it. This can be called without checking hasNext first
      @return the next token or null if there is none.
  */
  public Token peek() {
    return _nextToken;
  }

  public Token<T> next() throws SyntaxError {
    Token<T> token = _nextToken;
    _nextToken = nextToken();
    return token;
  }

  /** Require that the next token is as given in the argument and consume that token or throw a SyntaxError. */
  public Token<T> next(TokenType tokenType) throws SyntaxError {
    Token<T> token = _nextToken;
    if(token.type() == tokenType) {
      _nextToken = nextToken();
      return token;
    } else
      throw new SyntaxError("expected " + tokenType + " instead of " + token.type() + " at " + token.string());
  }
  
  public void remove() { throw new UnsupportedOperationException(); }
}

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

/** The HPath Token */
/*package protected*/ final class Token<T> {
  TokenType _type;
  String _string;
  T _value;
  
  public Token(TokenType type, String string) { 
    _type = type;
    _string = string;
    _value = null; // lazy
  }
  
  public TokenType type() { return _type; }
  public String string() { return _string; }
  public T value() { 
    if(_value == null)
      _value = (T) _type.tokenValueOf(_string);
    return _value;
  }
  
  public String toString() {
    return _type.toString() + "[" + _string + " " + _value + "]";
  }
}

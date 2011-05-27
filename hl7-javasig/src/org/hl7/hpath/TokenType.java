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

import java.util.EnumSet;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.OID;
import org.hl7.types.PQ;
import org.hl7.types.REAL;
import org.hl7.types.ST;
import org.hl7.types.TS;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;

/** The HPath TokenTypes. */
interface ValueConstructor<T> {
  T valueOf(String string);
  
  static final ValueConstructor LITERAL = new ValueConstructor() {
      public String valueOf(String string) { return string; }
    };

  static final ValueConstructor VARREF_LITERAL = new ValueConstructor() {
      public String valueOf(String string) { return string.substring(1); }
    };

  static final ValueConstructor CPROPIDENT_LITERAL = new ValueConstructor() {
      public String valueOf(String string) { return string.substring(1); }
    };

  static final ValueConstructor INTVAL = new ValueConstructor() {
      public INT valueOf(String string) { return ValueFactory.getInstance().INTvalueOfLiteral(string); }
    };

  static final ValueConstructor REALVAL = new ValueConstructor() {
      public REAL valueOf(String string) { return ValueFactory.getInstance().REALvalueOfLiteral(string); }
    };

  static final ValueConstructor STVAL = new ValueConstructor() {
      public ST valueOf(String string) { return ValueFactory.getInstance().STvalueOfLiteral(string.substring(1,string.length()-1)); }
    };

  static final ValueConstructor BLVAL = new ValueConstructor() {
      public BL valueOf(String string) { return ValueFactory.getInstance().BLvalueOfLiteral(string); }
    };

  static final ValueConstructor TSVAL = new ValueConstructor() {
      public TS valueOf(String string) { return ValueFactory.getInstance().TSvalueOfLiteral(string.substring(4,string.length()-1)); }
    };

  static final ValueConstructor PQVAL = new ValueConstructor() {
      public PQ valueOf(String string) { return ValueFactory.getInstance().PQvalueOfLiteral(string.substring(4,string.length()-1)); }
    };

  static final ValueConstructor CSVAL = new ValueConstructor() {
      public CS valueOf(String string) { 
				int prefixLength = "CS:".length();
				int indexOfColon = string.indexOf(':', prefixLength);
				String classString = string.substring(prefixLength,indexOfColon);
				String codeString = string.substring(indexOfColon+1);
				return CSasEnumStringConversion.CSvalueOf(classString, codeString);
			}
    };

    static final ValueConstructor CSOIDVAL = new ValueConstructor() {
      public CS valueOf(String string) { 
				int prefixLength = "CS:".length();
				int indexOfColon = string.indexOf(':', prefixLength);
				String codeString = string.substring(prefixLength,indexOfColon);
				String codeSystemString = string.substring(indexOfColon+1);
				return ValueFactory.getInstance().CSvalueOf(codeString, codeSystemString);
			}
    };

  static final ValueConstructor OIDVAL = new ValueConstructor() {
      public OID valueOf(String string) { return ValueFactory.getInstance().OIDvalueOfLiteral(string); }
    };

  static final ValueConstructor UUIDVAL = new ValueConstructor() {
      public UID valueOf(String string) { return ValueFactory.getInstance().UIDvalueOfLiteral(string); }
    };
}

/*package protected*/ enum TokenTypeCategory { SYNTAX, IDENT, LITERAL, IGNORABLE }

interface GenericTokenType<T> {
  public String pattern();
  public T tokenValueOf(String string);
  public boolean isIgnorable();
  public boolean isLiteral();
}

/*package protected*/ enum TokenType implements GenericTokenType {
  	LBRACK("\\.?\\[", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
	  PROP("\\.", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		CLBRACK("@\\[", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		RBRACK("\\]", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		LPAR("\\(", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		RPAR("\\)", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		CONCAT("\\|", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		SLASHDOT("\\!", ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		COMMA("\\,",  ValueConstructor.LITERAL, TokenTypeCategory.SYNTAX),
		VARREF("\\$[a-zA-Z_][a-zA-Z0-9_]*", ValueConstructor.VARREF_LITERAL, TokenTypeCategory.IDENT),
		TSLIT("TS:\"(?:[0-9]{1,8}|(?:[0-9]{9,14}|[0-9]{14,14}\\.[0-9]+)(?:[+\\-][0-9]{1,4})?)\"", ValueConstructor.TSVAL, TokenTypeCategory.LITERAL),
		PQLIT("PQ:\"[^\"]+\"", ValueConstructor.PQVAL, TokenTypeCategory.LITERAL),
		CSLIT("CS:[A-Za-z_][A-Za-z0-9_]*:[A-Za-z_][A-Za-z0-9_]*", ValueConstructor.CSVAL, TokenTypeCategory.LITERAL),
		CSOIDLIT("CS:[A-Za-z_][A-Za-z0-9_]*:(?:[1-9][0-9]*)(?:\\.[1-9][0-9]*){2,}", ValueConstructor.CSOIDVAL, TokenTypeCategory.LITERAL),
		STLIT("\"[^\"]*\"|'[^']*'", ValueConstructor.STVAL, TokenTypeCategory.LITERAL),
		BLLIT("true|false", ValueConstructor.BLVAL, TokenTypeCategory.LITERAL),
		CPROPIDENT("@[a-zA-Z_][a-zA-Z0-9_\\-]*", ValueConstructor.CPROPIDENT_LITERAL, TokenTypeCategory.IDENT),
		IDENT("[a-zA-Z_][a-zA-Z0-9_\\-]*", ValueConstructor.LITERAL, TokenTypeCategory.IDENT),
		OIDLIT("(?:[1-9][0-9]*)(?:\\.[1-9][0-9]*){2,}", ValueConstructor.OIDVAL, TokenTypeCategory.LITERAL),
		INTLIT("[+\\-]?[0-9]+(?![\\.0-9])", ValueConstructor.INTVAL, TokenTypeCategory.LITERAL),
		REALLIT("[+\\-]?(?:[0-9]+\\.[0-9]*|[0-9]*\\.[0-9]+)(?:[eE][+\\-]?[0-9]+)?", ValueConstructor.REALVAL, TokenTypeCategory.LITERAL),
		UUIDLIT("[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}", ValueConstructor.UUIDVAL, TokenTypeCategory.LITERAL),
		IGNSPACE("[ \t\r\n]", null, TokenTypeCategory.IGNORABLE),
		END_OF_STREAM(null, null, TokenTypeCategory.IGNORABLE);
	  
		private final String _pattern;
		private final ValueConstructor _valueConstructor;
		
		private final TokenTypeCategory _category;
		
		TokenType(String pattern, ValueConstructor valueConstructor, TokenTypeCategory category) { 
			_pattern = pattern; 
			_valueConstructor = valueConstructor;
			_category = category;
		}
		
		public final String pattern() { return _pattern; }

		public final Object tokenValueOf(String string) {
			return _valueConstructor.valueOf(string);
		}
		
		public final boolean isIgnorable() {
			return _category == TokenTypeCategory.IGNORABLE;
		}
		
		public final boolean isLiteral() {
			return _category == TokenTypeCategory.LITERAL;
		}
		
		public static final EnumSet<TokenType> ALL_TOKEN_TYPES = EnumSet.allOf(TokenType.class);
}

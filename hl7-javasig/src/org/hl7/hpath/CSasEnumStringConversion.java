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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import org.hl7.types.CS;

public class CSasEnumStringConversion<R> extends Conversion<R,CS,CS,String> {
	private static final String NAME = "CSasEnumString";

	static CS CSvalueOf(String classString, String codeString) {
		try {
			Class enumClass = Class.forName("org.hl7.types.enums." + classString);
			Method staticFactoryMethod = enumClass.getMethod("valueOf", String.class);
			if(staticFactoryMethod != null && ((staticFactoryMethod.getModifiers() & Modifier.STATIC) != 0)
					&& CS.class.isAssignableFrom(staticFactoryMethod.getReturnType()))
				return (CS)staticFactoryMethod.invoke(null, codeString);
			else
				throw new SyntaxError(classString);
		} catch(Exception ex) {
			throw new RuntimeException(classString + ":" + codeString,ex);
		}
	}
		
  public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
			public String name() { return NAME; }
			public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
				return new CSasEnumStringConversion(previousExpression, arguments);
			}
		};
	
	private CSasEnumStringConversion(Expression<R,CS> previousExpression, List<Expression<R,?>> arguments) {
		super(previousExpression, arguments);
	}
	
	public String name() { return NAME; }
	
	public String convertForward(CS value, Object... args) {
		if(value == null || value.isNull().isTrue())
			return "";
		else
			return value.toString();	
	}
	
	public CS convertBackward(String name, Object... args) { 
		if(name == null)
			return null;
		else {
			name = name.trim();
			if(name.length() == 0)
				return null;
			
			if(args.length < 1)
				throw new UpdateException(this, null, "enum argument required");
			
			return CSvalueOf(args[0].toString(),name);
		}
	}
}

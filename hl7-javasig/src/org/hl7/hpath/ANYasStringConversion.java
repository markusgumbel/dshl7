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

import java.util.List;
import org.hl7.types.ANY;
import org.hl7.types.ValueFactory;

/*package*/ class ANYasStringConversion<R> extends Conversion<R,ANY,ANY,String> {
	private static final String NAME = "ANYasString";
	private static final ValueFactory VFACTORY = ValueFactory.getInstance();
	
	public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
			public String name() { return NAME; }
			public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
				return new ANYasStringConversion(previousExpression, arguments);
			}
		};
	
	private ANYasStringConversion(Expression<R,ANY> previousExpression, List<Expression<R,?>> arguments) {
		super(previousExpression, arguments);
	}
	
	public String name() { return NAME; }
	
	public String convertForward(ANY any, Object... args) {	
		if(any == null || any.isNull().isTrue())
			return "";
		else
			return any.toString(); // Any attempt to check instances would be too sympathetic
	}
	
	public ANY convertBackward(String literal, Object... args) {		
		String typeSpec = (String) args[0];
		if (typeSpec == null)
			throw new IllegalArgumentException("ANYasStringConversion cannot have null arguments");
		
		if(literal == null)
			return VFACTORY.nullValueOf(typeSpec, "NA");
		else
			return VFACTORY.valueOfLiteral(typeSpec, literal);

		// FIXME: would like some generic way to call the ValueFactory methods with other arguments provided
	}
}

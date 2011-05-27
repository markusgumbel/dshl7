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

import org.hl7.types.ED;
import org.hl7.types.ST;
import org.hl7.types.ENXP;
import org.hl7.types.PNXP;
import org.hl7.types.ONXP;
import org.hl7.types.ADXP;
import org.hl7.types.enums.EntityNamePartType;
import org.hl7.types.enums.AddressPartType;
import org.hl7.types.ValueFactory;

//should add a general asString method, which goes for literals.

/*package*/ class STasStringConversion<R> extends Conversion<R,ED,ED,String> {
	private static final String NAME = "STasString";

	public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
			public String name() { return NAME; }
			public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
				return new STasStringConversion(previousExpression, arguments);
			}
		};

	private STasStringConversion(Expression<R,ED> previousExpression, List<Expression<R,?>> arguments) {
		super(previousExpression, arguments);
	}

	public String name() { return NAME; }

	public String convertForward(ED object, Object... args) {
		return object == null || object.isNull().isTrue() ? "" : object.toString();
	}

	public ED convertBackward(String string, Object... args) { 
		if(string == null)
			return null;
		else {
			string = string.trim();
			if(string.length() == 0)
				return fixUpType(null);
			else
				return fixUpType(string);
		}
	}

	private ED fixUpType(String string) {
		if(currentSource() instanceof ONXP)
			return ValueFactory.getInstance().PNXPvalueOf(string, (EntityNamePartType)((ONXP)currentSource()).type(), ((ONXP)currentSource()).qualifier());
		else if(currentSource() instanceof PNXP)
			return ValueFactory.getInstance().PNXPvalueOf(string, (EntityNamePartType)((PNXP)currentSource()).type(), ((PNXP)currentSource()).qualifier());
		else if(currentSource() instanceof ENXP)
			return ValueFactory.getInstance().ENXPvalueOf(string, (EntityNamePartType)((ENXP)currentSource()).type(), ((ENXP)currentSource()).qualifier());
		else if(currentSource() instanceof ADXP)
			return ValueFactory.getInstance().ADXPvalueOf(string, (AddressPartType)((ADXP)currentSource()).type());
		else 
			return ValueFactory.getInstance().STvalueOfLiteral(string);
	}
}

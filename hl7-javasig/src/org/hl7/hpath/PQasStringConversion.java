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
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.PQ;
import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;

/** Convert between a PQ and literal form but using a given maximum precision 
		so that the trailing decimal point does not appear. Also, do not show the 
    unity symbol "1" if a precision is set. */
/*package*/ class PQasStringConversion<R> extends Conversion<R,PQ,PQ,String> {
  private static final String NAME = "PQasString";
	
  public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
      public String name() { return NAME; }
      public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
				return new PQasStringConversion(previousExpression, arguments);
      }
    };

  private PQasStringConversion(Expression<R,PQ> previousExpression, List<Expression<R,?>> arguments) {
    super(previousExpression, arguments);
  }

  public String name() { return NAME; }

  public String convertForward(PQ object, Object... args) {
    if(object == null || object.isNull().isTrue())
			return "";
		else if(args.length > 0 && args[0] instanceof INT) {
			String number = object.value().toString((INT)args[0]);
			String unit = object.unit().toString();
			if(!unit.equals("1"))
				return number + " " + unit;
			else 
				return number;
		} else
			return object.toString();
  }

  public PQ convertBackward(String object, Object... args) { 
    if(object == null)
      return null;
    else {
      object = object.trim();
      if(object.length() == 0)
				return null;
      else if(args.length > 0 && args[0] instanceof INT) {
				int spacePos = object.indexOf(" ");
				String numberString = spacePos > 0 ? object.substring(0,spacePos) : object;
				String unitString = spacePos > 0 ? object.substring(spacePos+1) : "1";
				REAL number = ValueFactory.getInstance().REALvalueOf(numberString,(INT)args[0]);
				CS unit =  ValueFactory.getInstance().UnitvalueOf(unitString);
				return  ValueFactory.getInstance().PQvalueOf(number, unit);
			} else
				return ValueFactory.getInstance().PQvalueOfLiteral(object);				
    }
  }
}

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
import org.hl7.types.INT;
import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;

/** Convert between REAL and literal form but using a given maximum precision 
		so that the trailing decimal point does not appear. */
/*package*/ class REALasStringConversion<R> extends Conversion<R,REAL,REAL,String> {
  private static final String NAME = "REALasString";

  public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
      public String name() { return NAME; }
      public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
				return new REALasStringConversion(previousExpression, arguments);
      }
    };

  private REALasStringConversion(Expression<R,REAL> previousExpression, List<Expression<R,?>> arguments) {
    super(previousExpression, arguments);
  }

  public String name() { return NAME; }

  public String convertForward(REAL object, Object... args) {
    if(object == null || object.isNull().isTrue())
			return "";
		else if(args.length > 0 && args[0] instanceof INT)
			return object.toString((INT)args[0]);
		else
			return object.toString();
  }

  public REAL convertBackward(String object, Object... args) { 
    if(object == null)
      return null;
    else {
      object = object.trim();
      if(object.length() == 0)
				return null;
      else if(args.length > 0 && args[0] instanceof INT)
				return ValueFactory.getInstance().REALvalueOf(object,(INT)args[0]);
			else
				return ValueFactory.getInstance().REALvalueOfLiteral(object);				
    }
  }
}

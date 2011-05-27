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
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.INTlongAdapter;

//should add a general asString method, which goes for literals.

/*package*/ class IVL_INTasStringConversion<R> extends Conversion<R,IVL<INT>,IVL<INT>,String> {
  private static final String NAME = "IVL_INTasString";

  private static final INT ZERO = INTlongAdapter.ZERO;
  private static final BL TRUE = BLimpl.TRUE;

  public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
      public String name() { return NAME; }
      public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
	return new IVL_INTasStringConversion(previousExpression, arguments);
      }
    };

  private IVL_INTasStringConversion(Expression<R,IVL<INT>> previousExpression, List<Expression<R,?>> arguments) {
    super(previousExpression, arguments);
  }

  public String name() { return NAME; }

  public String convertForward(IVL<INT> object, Object... args) {
    return object == null || object.isNull().isTrue() ? "" : object.center().toString();
  }

  public IVL<INT> convertBackward(String object, Object... args) { 
    if(object == null)
      return null;
    else {
      object = object.trim();
      if(object.length() == 0)
	return null;
      else {
	INT center = ValueFactory.getInstance().INTvalueOfLiteral(object);				
	return ValueFactory.getInstance().IVLvalueOf(center, ZERO, TRUE, TRUE);
      }
    }
  }
}

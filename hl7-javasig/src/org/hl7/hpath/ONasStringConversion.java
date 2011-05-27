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

public class ONasStringConversion<R> extends Conversion<R,ED,ED,String>  {

	private static final String NAME = "ONasString";

	public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
			public String name() { return NAME; }
			public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
				return new ONasStringConversion(previousExpression, arguments);
			}
		};
	protected ONasStringConversion(Expression<R,ED> previousExpression, List<Expression<R,?>> arguments) {
		super(previousExpression, arguments);
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

	public String convertForward(ED object, Object... args) {
		return object == null || object.isNull().isTrue() ? "" : object.toString();
	}

	public String name() {
		return NAME;
	}
	
	private ED fixUpType(String string) {
		if(currentSource() instanceof ONXP)
			return ValueFactory.getInstance().ONXPvalueOf(string, (EntityNamePartType)((ONXP)currentSource()).type(), ((ONXP)currentSource()).qualifier());
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

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
import org.hl7.types.II;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;

public class IIasExtensionStringConversion<R> extends Conversion<R,II,II,String> {
	private static final String NAME = "IIasExtensionString";
	
  public static final Conversion.Definition DEFINITION = new Conversion.Definition() {
			public String name() { return NAME; }
			public Conversion instantiate(Expression previousExpression, List<Expression> arguments) {
				return new IIasExtensionStringConversion(previousExpression, arguments);
			}
		};
	
	private IIasExtensionStringConversion(Expression<R,II> previousExpression, List<Expression<R,?>> arguments) {
		super(previousExpression, arguments);
	}
	
	public String name() { return NAME; }
	
	public String convertForward(II id, Object... args) {
		if(id == null || id.isNull().isTrue())
			return "";
		else
			return id.extension().toString();	
	}
	
	public II convertBackward(String extension, Object... args) { 
		if(extension == null)
			return null;
		else {
			extension = extension.trim();
			if(extension.length() == 0)
				return null;
			
			if(args.length < 1)
				throw new UpdateException(this, null, "root UID argument required");
			
			UID root = (UID)args[0];
			
			return ValueFactory.getInstance().IIvalueOf(root, extension);
		}
	}
}

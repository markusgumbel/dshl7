package org.hl7.hpath;

import org.hl7.types.ANY;
import org.hl7.types.CD;

public class IncompleteValueUpdateException extends UpdateException {
  ANY _incompleteValue; 
	public IncompleteValueUpdateException(Expression expression, Object context, CD code, String queryName) {
		super(expression, context, "cannot resolve \"" + code.originalText() + "\" in " + queryName);
		_incompleteValue = code;
	}
	
	public ANY getIncompleteValue() {
		return _incompleteValue;
	}
}

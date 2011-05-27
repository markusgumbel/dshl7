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
 * Portions created by Initial Developer are Copyright (C) 2002-2007
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.util;

import java.util.Iterator;
import java.util.logging.Logger;

import org.hl7.hpath.Evaluation;
import org.hl7.hpath.Expression;
import org.hl7.rim.Act;
import org.hl7.rim.ActRelationship;
import org.hl7.rim.Observation;
import org.hl7.types.ANY;
import org.hl7.types.PQ;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.enums.ActMood;
import org.hl7.types.enums.ActRelationshipType;

/** A tool which deals with calculated observations. */
public class FormulaEvaluator {
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.util.FormulaEvaluator");

	/** Update attributes with formulas based on associated data. 
			Currently we only handle 
			Observations using
			derivationExpr which contains
			hpath to compute only the
			value.

			Currently we also only compute the calue from associated events.

			Later, we need to support the new PQFX so we can compute SusbstanceAdministration.doseQty check.
			
	*/
	public static void update(Observation act) {
		Evaluation evaluation = null;
		try {
			ST derivationExpr = act.getDerivationExpr();
			ActMood moodCode = (ActMood)act.getMoodCode();
			if(moodCode != null && moodCode.implies(ActMood.Event).isTrue() && derivationExpr != null && derivationExpr.isNull().isFalse()) {
				Expression expression = Expression.valueOf(derivationExpr.toString());
				/*Evaluation*/ evaluation = expression.evaluate(act);
				Observation definition = addLocalVariablesAndGetDefinition(act, evaluation);
				Iterator valueIterator = evaluation.iterator();
				if(valueIterator.hasNext()) {
					ANY theValue = (ANY)valueIterator.next();
					if(valueIterator.hasNext()) {
						throw new IllegalArgumentException("we don't expect multiple results here: " + theValue + " followed by " + valueIterator.next());
					}
					if(theValue instanceof PQ && theValue.isNull().isFalse()) {
					  PQ ourValue = (PQ)theValue;
					  PQ exampleValue = ((SET<PQ>)definition.getValue()).any();
					  theValue = ourValue.expressedIn(exampleValue).withLimitedPrecision(exampleValue.precision());
					}
					act.setValue(theValue);
				}		
				// FIXME: handle exceptions
				// FIXME: do not overwrite existing value unconditionally?
			}		
		} catch(Exception ex) {
			LOGGER.warning(ex.getMessage());
		}
	}
	

	private static Observation addLocalVariablesAndGetDefinition(Observation act, Evaluation evaluation) {
		Observation definition = null;
		for(ActRelationship relationship : act.getOutboundRelationship()) {
			ST localVariableName = relationship.getLocalVariableName();
			if(localVariableName != null) {
				Act targetAct = relationship.getTarget();
				if(targetAct.getMoodCode().implies(ActMood.Event).isTrue()) {
					evaluation.bindVariable(localVariableName.toString(), targetAct);
				}
			}
		  if(relationship.getTypeCode().implies(ActRelationshipType.Instantiates).isTrue())
		  	definition = (Observation)relationship.getTarget();
		}
		return definition;
	}
	
	
	
}

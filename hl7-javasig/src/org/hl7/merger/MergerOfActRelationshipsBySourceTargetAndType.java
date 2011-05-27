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
 * Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.merger;

import java.util.Iterator;
import org.hl7.meta.Feature;
import org.hl7.rim.ActRelationship;
import org.hl7.rim.RimObject;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CE;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.PQ;
import org.hl7.types.ST;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Locator;

/** A merger for ActRelationship which finds duplicate actRelationships (such as ingredients which are already known).
		Find actRelationships of same type between same two entities, if the present actRelationship is not identified.

		This merger is active after pretty much all of the ActRelationship has already been parsed, that means it 
		needs to merge ActRelationship data from the ActRelationship just parsed into the actRelationship from the database. That's
		not nice. You'd almost like to parse the entire section of the message again after the 
		merge than to manually sift through the data now.

		This merger is called on the ActRelationship object after any attributes and associations have been
		set.
 */
public class MergerOfActRelationshipsBySourceTargetAndType<C extends ActRelationship, T> extends MergerUsingCache<C, T> implements Merger<C, T> {
	public MergerOfActRelationshipsBySourceTargetAndType(ApplicationContext applicationContext) { 
		super(applicationContext);
	}

	public boolean isStaticallyApplicable(Object object) {
		return object instanceof ActRelationship;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && (value instanceof RimObject || value instanceof ANY);
	}
	
	/** Merge the object. */
	public C merge(C object, Feature feature, T value, Locator loc) {
		CS typeCode = object.getTypeCode();
		String featureRimName = feature.getRimPropertyName();

		boolean result = (featureRimName.equals("typeCode")
						|| featureRimName.equals("source")
						|| featureRimName.equals("target"))
			&& typeCode != null && typeCode.isNull().isFalse()
			&& object.getTarget() != null
			&& object.getSource() != null;

		// FIXME: this is never true because the target or source is not connected at this time given the way that the parser works
		// FIXME: so this class is essentially defunct, it cannot do anything
		
		// LOGGER.info("MERGER OF MATCHING ACTRELATIONSHIPS(" + object.toString() + "," + feature.getRimPropertyName() +","+ value.toString() +") -> "+ result);

		if(result)
			return doMerge(object, feature, value, loc);
		else 
			return object;
	}

  private C doMerge(C object, Feature feature, T value, Locator loc) {
		if (useHibernate()) {
			C objectFromDatabase = null;
			
			LOGGER.finest("QUERYING FOR MATCHING ACT-RELATIONSHIP IN DATABASE");

			Iterator<C> results = getQuery("matchingActRelationship")
				.setParameter("source", object.getSource())
				.setParameter("target", object.getTarget())
				.setParameter("typeCode", object.getTypeCode())
				.list().iterator();
			
			if(results != null && results.hasNext()) {
				objectFromDatabase = results.next();
				LOGGER.info("FOUND MATCHING ACTRELATIONSHIP: --> " + objectFromDatabase);
				if(results.hasNext())
					LOGGER.info("BUT ACTRELATIONSHIP IS NOT UNIQUE");
				else {
					// merge data, which is difficult and this is not the best way to do it, it's also not complete
					try {
						objectFromDatabase.setCheckpointCode((CS)MergerTool.merge(objectFromDatabase.getCheckpointCode(), object.getCheckpointCode(), false, "checkpointCode"));
						objectFromDatabase.setConjunctionCode((CS)MergerTool.merge(objectFromDatabase.getConjunctionCode(), object.getConjunctionCode(), false, "conjunctionCode"));
						objectFromDatabase.setContextConductionInd((BL)MergerTool.merge(objectFromDatabase.getContextConductionInd(), object.getContextConductionInd(), false, "contextConductionInd"));
						objectFromDatabase.setContextControlCode((CS)MergerTool.merge(objectFromDatabase.getContextControlCode(), object.getContextControlCode(), false, "contextControlCode"));
						objectFromDatabase.setInversionInd((BL)MergerTool.merge(objectFromDatabase.getInversionInd(), object.getInversionInd(), false, "inversionInd"));
						objectFromDatabase.setJoinCode((CS)MergerTool.merge(objectFromDatabase.getJoinCode(), object.getJoinCode(), false, "joinCode"));
						objectFromDatabase.setLocalVariableName((ST)MergerTool.merge(objectFromDatabase.getLocalVariableName(), object.getLocalVariableName(), false, "localVariableName"));
						objectFromDatabase.setNegationInd((BL)MergerTool.merge(objectFromDatabase.getNegationInd(), object.getNegationInd(), false, "negationInd"));
						objectFromDatabase.setPauseQuantity((PQ)MergerTool.merge(objectFromDatabase.getPauseQuantity(), object.getPauseQuantity(), false, "pauseQuantity"));
						// objectFromDatabase.setPriorityNumber((INT)MergerTool.merge(objectFromDatabase.getPriorityNumber(), object.getPriorityNumber(), false, "priorityNumber"));
						objectFromDatabase.setSeperatableInd((BL)MergerTool.merge(objectFromDatabase.getSeperatableInd(), object.getSeperatableInd(), false, "seperatableInd"));
						objectFromDatabase.setSequenceNumber((INT)MergerTool.merge(objectFromDatabase.getSequenceNumber(), object.getSequenceNumber(), false, "sequenceNumber"));
						objectFromDatabase.setSplitCode((CS)MergerTool.merge(objectFromDatabase.getSplitCode(), object.getSplitCode(), false, "splitCode"));
						objectFromDatabase.setSubsetCode((CS)MergerTool.merge(objectFromDatabase.getSubsetCode(), object.getSubsetCode(), false, "subsetCode"));
						objectFromDatabase.setUncertaintyCode((CE)MergerTool.merge(objectFromDatabase.getUncertaintyCode(), object.getUncertaintyCode(), false, "uncertaintyCode"));

						// participations should follow after source and target or else they'll be dropped.
					} catch(MergerTool.ConsistencyCheckException ex) {
						LOGGER.warning(ex.getMessage());
					}
				}					
			}	else 
				LOGGER.finest("NO MATCHING ACTRELATIONSHIP FOUND: " + object.getTypeCode() + ": " + object.getTarget() + "->" + object.getSource());
		}

		// Merge ActRelationship Data

		// this

		return object;
	}
}

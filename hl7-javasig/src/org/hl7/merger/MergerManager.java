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

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.hl7.util.ApplicationContext;


/** A manager of mergers, serves as a tool to get a set of applicable mergers for any context. */
public class MergerManager {
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.merger");

	private static final String CLASS_NAME = MergerManager.class.getName();
	private static final String ALL_MERGERS_LIST_NAME = CLASS_NAME + ".allMergers";

	private static final Class CONSTRUCTOR_SIGNATURE[] = new Class[] {
		ApplicationContext.class
	};
	
	private static final Class ALL_MERGER_CLASSES[] = new Class[] {
		MergerOfAttributeAssignment.class,
		MergerOfAssociationAssignment.class,
		MergerById.class,
		MergerOfActDefinitionsByCode.class,
		MergerOfEntityKindsByCode.class,
		MergerOfEntityKindsByName.class,
		MergerOfRolesByScoperPlayerAndClass.class,
		MergerOfActRelationshipsBySourceTargetAndType.class,
		MergerWedgeToCommitControlActs.class,
		MergerOfTreeContentNode.class,
		ContextConductionMerger.class
	};

	private static final Set<Class> REQUIRED_MERGERS = new HashSet<Class>(Arrays.asList(new Class[] {
		MergerOfAttributeAssignment.class,
		MergerOfAssociationAssignment.class,
		MergerById.class,
		MergerOfTreeContentNode.class		
	}));
	
	private static List<Merger> getAllMergers(ApplicationContext applicationContext) {
		List<Merger> allMergers = (List<Merger>)applicationContext.getSetting(ALL_MERGERS_LIST_NAME);
		if(allMergers == null) {
			allMergers = new LinkedList<Merger>();
			for(Class<Merger> mergerClass : ALL_MERGER_CLASSES) {
				String enabled = (String)applicationContext.getSetting(mergerClass.getName() + ".enabled");
				if((enabled == null && REQUIRED_MERGERS.contains(mergerClass)) || (enabled != null && enabled.equals("true"))) {
					try {
						allMergers.add(mergerClass.getConstructor(CONSTRUCTOR_SIGNATURE).newInstance(applicationContext));
					} catch(NoSuchMethodException ex) {
						LOGGER.warning("cannot instantiate " + mergerClass.getName() + ": " + ex);
					} catch(InstantiationException ex) {
						LOGGER.warning("cannot instantiate " + mergerClass.getName() + ": " + ex);
					} catch(IllegalAccessException ex) {
						LOGGER.warning("cannot instantiate " + mergerClass.getName() + ": " + ex);
					} catch(InvocationTargetException ex) {
						LOGGER.warning("cannot instantiate " + mergerClass.getName() + ": " + ex);
					}
				}
			}
			applicationContext.setSetting(ALL_MERGERS_LIST_NAME, allMergers);			
		}
		return allMergers;
	}

	public static List<Merger> getApplicableMergers(ApplicationContext applicationContext, Object object) {
		List<Merger> mergers = new LinkedList<Merger>();
		for(Merger merger : getAllMergers(applicationContext))
			if(merger.isStaticallyApplicable(object))
				mergers.add(merger);
		return mergers;
	}

	public static List<Merger> getAttributeMergerOnly(ApplicationContext applicationContext, Object object) {
		List<Merger> mergers = new LinkedList<Merger>();
		mergers.add(new MergerOfAttributeAssignment(applicationContext));
		return mergers;
	}

}

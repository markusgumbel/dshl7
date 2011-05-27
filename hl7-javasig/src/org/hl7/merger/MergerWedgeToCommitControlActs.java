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

import java.util.logging.Logger;
import org.hl7.meta.Feature;
import org.hl7.rim.Act;
import org.hl7.rim.ActRelationship;
import org.hl7.rim.ControlAct;
import org.hl7.types.enums.ActClass;
import org.hl7.types.enums.ActMood;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Locator;

/** Merger which we use to to commit control acts and clear the cache. */
public class MergerWedgeToCommitControlActs<C extends ActRelationship, T extends Act> extends MergerUsingCache<C, T> {
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.merger");

	public MergerWedgeToCommitControlActs(ApplicationContext applicationContext) { 
		super(applicationContext);
	}
	
	public boolean isStaticallyApplicable(Object object) {
		return _useHibernate && object != null && object instanceof ActRelationship;
	}
	
	public boolean isApplicable(C object, Object value) {
		return value != null 
			&& (value instanceof ControlAct || (value instanceof Act && ((T)value).getClassCode().implies(ActClass.ControlAct).isTrue()))
			&& ((T)value).getMoodCode().implies(ActMood.Event).isTrue();
	}
	
	/** Merging means saving, we return the same object. */
	public C merge(C object, Feature feature, T value, Locator loc) {
		if(value == null)
			return object;
		
		LOGGER.info(addLoc("SAVING:" + value + "[" + value.getTitle() + "] ...",loc));
		value.setInboundRelationship(null);
		object.setTarget(null);
		_applicationContext.getPersistence().save(value);
		_applicationContext.getPersistence().commit();
		_applicationContext.getPersistence().close();
		clearQueryCache();
		clearObjectCache();
		LOGGER.info("... as " + value);
		return null;
	}
}

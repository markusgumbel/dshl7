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
 * The Original Name is all this file.
 *
 * The Initial Developer of the Original Name is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.merger;

import java.util.Iterator;
import org.hl7.meta.Feature;
import org.hl7.rim.Entity;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.II;
import org.hl7.types.SET;
import org.hl7.types.enums.EntityDeterminer;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Locator;

/** A merger for Entity kinds with same name. */
public class MergerOfEntityKindsByName<C extends Entity, T extends EN> extends MergerUsingCache<C, T> implements Merger<C, T> {
	public MergerOfEntityKindsByName(ApplicationContext applicationContext) { 
		super(applicationContext);
	}

	public boolean isStaticallyApplicable(Object object) {
		return object != null && object instanceof Entity;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && value instanceof EN && ((EN)value).isNull().isFalse();
	}

	public C merge(C object, Feature feature, T value, Locator loc) {
		SET<II> currentId = object.getId();
		if(feature.getName().equals("name")
			 && (currentId == null || currentId.isNull().isTrue() || currentId.isEmpty().isTrue())
			 && object.getDeterminerCode().implies(EntityDeterminer.Determined).isTrue())
			return doMerge(object, feature, value, loc);
		else
			return object;
	}

  private C doMerge(C object, Feature feature, T name, Locator loc) {
		if(name.tail().isNull().isTrue()) {
			ENXP onlypart = name.head();
			String nameString = onlypart.toString();
			
			C cachedObject = (C)findObjectInCache(nameString);
			if (cachedObject != null) {
				LOGGER.finest("OBJECT NAME IN CACHE: " + name.toString() + " --> " + cachedObject.getInternalId());
				// FIXME: should MERGE properties loaded so far!
				return cachedObject;
			}	else {
				if (_useHibernate && !getApplicationContext().getPersistence().isPersistent(object))	{
					Iterator<C> results = getQuery("entityKindByName")
						.setParameter("trivialname", nameString)
						.list().iterator();
					if(results.hasNext())	{ // an existing entity with this name has been found
						object = results.next();
						LOGGER.finest(addLoc("FOUND ENTITY BY NAME: " + nameString + " --> " + object, loc));
					}
				}
				
				putObjectInCache(name, object);
			}
		}
		return object;
	}
}


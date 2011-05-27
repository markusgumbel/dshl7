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
import org.hl7.rim.Entity;
import org.hl7.types.CD;
import org.hl7.types.CE;
import org.hl7.types.II;
import org.hl7.types.SET;
import org.hl7.types.DSET;
import org.hl7.types.enums.EntityDeterminer;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Locator;

/** A merger for Entity kinds with same code. */
public class MergerOfEntityKindsByCode<C extends Entity, T extends CD> extends MergerUsingCache<C, T> implements Merger<C, T> {
	public MergerOfEntityKindsByCode(ApplicationContext applicationContext) { 
		super(applicationContext);
	}

	public boolean isStaticallyApplicable(Object object) {
		return object != null && object instanceof Entity;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && value instanceof CD && ((CD)value).isNull().isFalse();
	}

	public C merge(C object, Feature feature, T value, Locator loc) {
		SET<II> currentId = object.getId();
		LOGGER.finest("IS TIME TO MERGE? " + object + " " + feature + " " + value + " " + currentId + " " + object.getDeterminerCode());
		if(feature.getName().equals("code")
			 && (currentId == null || currentId.isNull().isTrue() || currentId.isEmpty().isTrue())
			 && object.getDeterminerCode().implies(EntityDeterminer.Determined).isTrue())
			return doMerge(object, feature, value, loc);
		else
			return object;
	}

  private C doMerge(C object, Feature feature, T code, Locator loc) {
		LOGGER.fine("MERGER OF ENTITY KINDS BY CODE(" + object.toString() + "," + feature.getRimPropertyName() +","+ code.toString() +")");		
		C replacedObject = null;
		
		C cachedObject = (C)findObjectInCache(code);
		if (cachedObject != null) {
			LOGGER.finest("OBJECT CODE IN CACHE: " + code.toString() + " --> " + cachedObject.getInternalId());
			replacedObject = cachedObject;

		}	else {
			if (useHibernate() && !getApplicationContext().getPersistence().isPersistent(object))	{
				Iterator<C> results = getQuery("entityKindByCode")
					.setParameter("code", code.code().toString())
					.setParameter("codeSystem", code.codeSystem().toString())
					.list().iterator();
				if(results.hasNext())	{ // an existing entity with this code has been found
					LOGGER.finest(addLoc("FOUND ENTITY BY CODE: " + code.code() + "@" + code.codeSystem() + " --> " + object, loc));
					replacedObject = results.next();
				}
			}
		}

		if(replacedObject != null) {
			// FIXME: should MERGE ALL properties loaded so far!
			try {
				replacedObject.setId((DSET<II>)MergerTool.merge((DSET<II>)replacedObject.getId(), (DSET<II>)object.getId(), true, "code"));
				replacedObject.setCode((CE)MergerTool.merge(replacedObject.getCode(), object.getCode(), true, "code"));
				
      } catch(MergerTool.ConsistencyCheckException ex) {
				LOGGER.warning(ex.getMessage());
      }

			putObjectInCache(code, replacedObject);
			return replacedObject;
		} else {
			putObjectInCache(code, object);
			return object;
		}
	}
}


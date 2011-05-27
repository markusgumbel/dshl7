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
import org.hl7.rim.Act;
import org.hl7.rim.Entity;
import org.hl7.rim.ManagedParticipation;
import org.hl7.rim.RimObject;
import org.hl7.rim.Role;
import org.hl7.types.II;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Locator;

/** A merger for RIM objects with id-attributes. */
public class MergerById<C extends RimObject, T extends II> extends MergerUsingCache<C, T> implements Merger<C, T> {
	public MergerById(ApplicationContext applicationContext) { 
		super(applicationContext);
	}

	public boolean isStaticallyApplicable(Object object) {
		return object instanceof Act || object instanceof Role || object instanceof Entity || object instanceof ManagedParticipation;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && value instanceof II && ((II)value).isNull().isFalse();
	}

	private final String cacheKey(C object, T id) {
		return ((object instanceof Act) ? "A:"
						: (object instanceof Role) ? "R:"
						: (object instanceof Entity) ? "E:"
						: (object instanceof ManagedParticipation) ? "P:"
						: "X") + id.toString();
	}
	
	/** Merge the object. */
  public C merge(C object, Feature feature, T id, Locator loc) {
		if(!feature.getName().equals("id"))
			return object;

		String cacheKey = cacheKey(object, id);
		C cachedObject = findObjectInCache(cacheKey);;
		if(cachedObject != null) {
			if ((object instanceof Act && cachedObject instanceof Act)
					|| (object instanceof Entity && cachedObject instanceof Entity)
					|| (object instanceof Role && cachedObject instanceof Role)
					|| (object instanceof ManagedParticipation && cachedObject instanceof ManagedParticipation))
				return cachedObject;
			else
				LOGGER.warning(addLoc("objects with same id whave different classes: " + id + ": " + object + " != " + cachedObject, loc));

		} else {
			if (useHibernate()) {
				C objectFromDatabase;
				// query for all other id's to see if this one already exists
				if (object instanceof Act)
					objectFromDatabase = findResultInDatabaseById("actById", id);
				else if(object instanceof Role)
					objectFromDatabase = findResultInDatabaseById("roleById", id);
				else if(object instanceof Entity)
					objectFromDatabase = findResultInDatabaseById("entityById", id);
				else if(object instanceof ManagedParticipation)
					objectFromDatabase = findResultInDatabaseById("managedParticipationById", id);
				else 
					throw new IllegalArgumentException("programming error, please report stack dump. " + object.getClass() + " " + object);
				
				if(objectFromDatabase != null)
					object = objectFromDatabase;
			}

			putObjectInCache(cacheKey, object);
		}

		return object;
	}

	private C findResultInDatabaseById(String queryName, II id) {
		LOGGER.finest("QUERYING FOR ID IN DATABASE: " + queryName + " " + id);
		// query for all other id's to see if this one already exists

		Iterator<C> results = (id.extension().nonNull().isTrue() ?	
													 getQuery(queryName).setParameter("extension", id.extension().toString()) :
													 getQuery(queryName + "RootOnly") 
													 ).setParameter("root", id.root().toString()).list().iterator();
		
		if(results != null && results.hasNext()) {
			C object = results.next();
			LOGGER.finest("FOUND OBJECT BY ID: " + id + " --> " + object );
			return object;
		}

		LOGGER.finest("OBJECT NOT FOUND: " + queryName + " " + id);
		return null;
	}
}

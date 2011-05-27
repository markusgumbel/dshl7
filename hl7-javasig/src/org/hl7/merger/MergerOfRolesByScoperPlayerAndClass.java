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
import org.hl7.meta.Association;
import org.hl7.meta.Feature;
import org.hl7.rim.Entity;
import org.hl7.rim.RimObject;
import org.hl7.rim.Role;
import org.hl7.types.AD;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.CE;
import org.hl7.types.CS;
import org.hl7.types.EN;
import org.hl7.types.II;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.RTO;
import org.hl7.types.SET;
import org.hl7.types.TEL;
import org.hl7.types.TS;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Locator;

/** A merger for Roles which finds duplicate roles (such as ingredients which are already known).
		Find roles of same type between same two entities, if the present role is not identified.

		This merger is active after pretty much all of the Role has already been parsed, that means it 
		needs to merge Role data from the Role just parsed into the role from the database. That's
		not nice. You'd almost like to parse the entire section of the message again after the 
		merge than to manually sift through the data now.

		This merger is called on the Role object after any attributes and associations have been
		set.
*/
public class MergerOfRolesByScoperPlayerAndClass<C extends Role, T> extends MergerUsingCache<C, T> implements Merger<C, T> {
	public MergerOfRolesByScoperPlayerAndClass(ApplicationContext applicationContext) { 
		super(applicationContext);
	}

	public boolean isStaticallyApplicable(Object object) {
		return object instanceof Role;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && (value instanceof RimObject || value instanceof ANY);
	}
	
	/** Merge the object. */
	public C merge(C object, Feature feature, T value, Locator loc) {
		SET<II> ids = object.getId();
		CS classCode = object.getClassCode();
		String featureRimName = feature.getRimPropertyName();

		boolean result = (featureRimName.equals("classCode")
											|| featureRimName.equals("player")
											|| featureRimName.equals("scoper"))
			&& classCode != null && classCode.isNull().isFalse()
			&& (ids == null || ids.isNull().isTrue() || ids.isEmpty().isTrue())
			&& object.getScoper() != null
			&& object.getPlayer() != null;

		// This would never be true because the scoper or player is not connected at this time given the way that the parser works
		// However, one can use establishBackLink to make it true.
		LOGGER.finest("MERGER OF MATCHING ROLES(" + object.toString() + "," + feature.getRimPropertyName() +","+ value.toString() +") -> "+ result);

		if(result)
			return doMerge(object, feature, value, loc);
		else 
			return object;
	}

  private C doMerge(C object, Feature feature, T value, Locator loc) {
    String key = object.getPlayer().getInternalId() + ":" + object.getScoper().getInternalId() + ":" + object.getClassCode().toString();
    C replacedObject = (C)findObjectInCache(key);
    if(replacedObject == null && useHibernate() && !getApplicationContext().getPersistence().isPersistent(object)) {
      LOGGER.finest("QUERYING FOR MATCHING ROLE IN DATABASE");

      Iterator<C> results = getQuery("matchingRole")
				.setParameter("playerId", object.getPlayer().getInternalId())
				.setParameter("scoperId", object.getScoper().getInternalId())
				.setParameter("classCode", object.getClassCode())
				.list().iterator();
      
      if(results != null && results.hasNext()) {
				replacedObject = results.next();
				LOGGER.info("FOUND MATCHING ROLE: --> " + replacedObject);
				if(results.hasNext()) {
					LOGGER.info("BUT ROLE IS NOT UNIQUE");
					replacedObject = null;
				}
      }
    }

    if(replacedObject != null) {
      // merge data, which is difficult and this is not the best way to do it, it's also not complete
      try {
				replacedObject.setCode((CE)MergerTool.merge(replacedObject.getCode(), object.getCode(), true, "code"));
				replacedObject.setNegationInd(MergerTool.merge(replacedObject.getNegationInd(), object.getNegationInd(), true, "negationInd"));
				replacedObject.setName((BAG<EN>)MergerTool.merge(replacedObject.getName(), object.getName(), false, "name"));
				replacedObject.setAddr((BAG<AD>)MergerTool.merge(replacedObject.getAddr(), object.getAddr(), false, "addr"));
				replacedObject.setTelecom((BAG<TEL>)MergerTool.merge(replacedObject.getTelecom(), object.getTelecom(), false, "telecom"));
				replacedObject.setStatusCode((CS)MergerTool.merge(replacedObject.getStatusCode(), object.getStatusCode(), false, "statusCode"));
				replacedObject.setEffectiveTime((IVL<TS>)MergerTool.merge(replacedObject.getEffectiveTime(), object.getEffectiveTime(), true, "effectiveTime"));
				replacedObject.setQuantity((RTO)MergerTool.merge(replacedObject.getQuantity(), object.getQuantity(), true, "quantity"));
				replacedObject.setPositionNumber((LIST<INT>)MergerTool.merge(replacedObject.getPositionNumber(), object.getPositionNumber(), true, "positionNumber"));
	
				// participations should follow after player and scoper or else they'll be dropped.

				// Thoroughly remove all references to this object which we don't want to retain
				object.getPlayer().getPlayedRole().remove(object);
				object.getScoper().getScopedRole().remove(object);

      } catch(MergerTool.ConsistencyCheckException ex) {
				LOGGER.warning(ex.getMessage());
      }

      putObjectInCache(key, replacedObject);
      return replacedObject;
    } else {
      putObjectInCache(key, object);
      return object;
    }
    
  }
  
  public static void establishBackLink(Object object, Feature feature, Object parentObject) {
    if(object instanceof Role && parentObject instanceof Entity && feature instanceof Association) {
		  Role role = (Role)object;                   
		  
		  String propertyName = ((Association)feature).getRimPropertyName(); // should this use getPropertyName()?  
			LOGGER.fine("MAKE LINK(" + object.toString() + "," + feature.getRimPropertyName() +","+ parentObject.toString() +") -> "+ propertyName);
			if(propertyName.equals("playedRole"))
				role.setPlayer((Entity)parentObject);
			else if(propertyName.equals("scopedRole"))
				role.setScoper((Entity)parentObject);
			else
			  LOGGER.warning("WRONG propertyName "+ propertyName);			  
		}
	}
}

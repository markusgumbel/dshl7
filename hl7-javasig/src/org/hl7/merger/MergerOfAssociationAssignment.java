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
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.logging.Logger;

import org.hl7.meta.Association;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Feature;
import org.hl7.meta.JavaIts;
import org.hl7.meta.LoaderException;
import org.hl7.meta.impl.JavaItsImpl;
import org.hl7.rim.Act;
import org.hl7.rim.ActRelationship;
import org.hl7.rim.Entity;
import org.hl7.rim.Participation;
import org.hl7.rim.RimObject;
import org.hl7.rim.RimObjectFactory;
import org.hl7.rim.Role;
import org.hl7.rim.RoleLink;
import org.hl7.types.CS;
import org.hl7.types.impl.CSimpl;
import org.hl7.util.ApplicationContext;
import org.hl7.util.FactoryException;
import org.hl7.util.StringUtils;
import org.xml.sax.Locator;

/** Merges association data. 
		Right now this just makes a (new) association assignment, but in the future
    there is lots more to come, such as update modes, merging relationship 
    classes, etc.
*/
public class MergerOfAssociationAssignment<C extends RimObject, T extends RimObject> implements Merger<C, T> {
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.merger");

	private static final JavaIts javaIts = new JavaItsImpl();

	public MergerOfAssociationAssignment(ApplicationContext applicationContext) { }

	public boolean isStaticallyApplicable(Object object) {
		return object != null && object instanceof RimObject;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && value instanceof RimObject;
	}

	/** Merge the object. */
    public C merge(C object, Feature feature, T value, Locator loc)
    {
        if (! (feature instanceof Association))
        {
            assert(false);
        }

        Association association = (Association) feature;
        LOGGER.finer(MergerUsingCache.addLoc("add association: " + association.getName() + " RIM property name: " + association.getRimPropertyName() + " assoc: " + association, loc));

        // FIXME: remove this cloneCode hack!
        CS cloneCode = CSimpl.valueOf(association.getName(), "1.2.3.4"); // FIXME
        value.setCloneCode(cloneCode);
        // FIXME: cloneCode is bogus, non standard and bound to fail!!!

        // propertyName will be the most specific name of the property, not 
        // necessiarily the rim property. 
        String propertyName = association.getPropertyName();

        // FIXME: the reverse reference is not being set here at all!
        // This only works by accident when Hibernate is used, because
        // then the association bundle collection will be loaded from
        // the foreign keys in the database. But until this is saved
        // and loaded in the database, the links are inconsistent!

        // so, let's do something about it

        if (value instanceof Act)
        {
            if (object instanceof ActRelationship)
            {
                if (propertyName.equals("target"))
                {
                    ((Act) value).addInboundRelationship((ActRelationship) object);
                    return object;
                }
                else if (propertyName.equals("source"))
                {
                    ((Act) value).addOutboundRelationship((ActRelationship) object);
                    return object;
                }
            }
            else if (object instanceof Participation)
            {
                if (propertyName.equals("act"))
                {
                    ((Act) value).addParticipation((Participation) object);
                    return object;
                }
            }
        }
        else if (value instanceof ActRelationship && object instanceof Act)
        {
            if (propertyName.equals("outboundRelationship"))
            {
                ((Act) object).addOutboundRelationship((ActRelationship) value);
                return object;
            }
            else if (propertyName.equals("inboundRelationship"))
            {
                ((Act) object).addInboundRelationship((ActRelationship) value);
                return object;
            }
        }
        else if (value instanceof Role)
        {
            if (object instanceof Participation)
            {
                if (propertyName.equals("role"))
                {
                    ((Role) value).addParticipation((Participation) object);
                    return object;
                }
            }
            else if (object instanceof RoleLink)
            {
                if (propertyName.equals("target"))
                {
                    ((Role) value).addInboundLink((RoleLink) object);
                    return object;
                }
                else if (propertyName.equals("source"))
                {
                    ((Role) value).addOutboundLink((RoleLink) object);
                    return object;
                }
            }
        }
        else if (value instanceof Entity)
        {
            if (object instanceof Role)
            {
                if (propertyName.equals("player"))
                {
                    ((Entity) value).addPlayedRole((Role) object);
                    return object;
                }
                else if (propertyName.equals("scoper"))
                {
                    ((Entity) value).addScopedRole((Role) object);
                    return object;
                }
            }

            // the good thing is above code covers almost all cases, hence
            // we hardly ever have to
            // run through the more expensive reflection and method search
            // that follows
        }

        // now the expensive stuff which is hardly needed

        // FIXME, what we're doing here looks very similar to the many
        // ClassMethodTools which I have
        // written and one of them is around here. Use it instead.
        try
        {
            String methodNameStem = StringUtils.forceInitialCap(propertyName);

            // this should work for any collection types
            if (/* AssociationSet */Collection.class.isAssignableFrom(javaIts.getRIMDataType(object, methodNameStem)))
            {                
                String methodName = ("add" + methodNameStem).intern();
                for (Method method : object.getClass().getMethods())
                    if (method.getName().equalsIgnoreCase(methodName))
                    {
                        Class fargs[] = method.getParameterTypes();
                        if (fargs.length == 1 && fargs[0].isAssignableFrom(value.getClass()))
                        {
                            // now we really got something useful
                            method.invoke(object, value);
                            return object;
                        }
                    }
                throw new NoSuchMethodException(methodName);

            }
            else
            { // cardinality is max 1
                
                // distalClassName is be the most specialized class supported by the factory 
                RimObjectFactory factory = RimObjectFactory.getInstance();
                CloneClass target = association.getTarget();
                String[] prioritizedRelativeClassNames = target.getClasses(); 
                String distalClassName = factory.mostSpecializedClassName(prioritizedRelativeClassNames);
                
                if (distalClassName.equals("ActHeir")) distalClassName = "Act";
                else if (distalClassName.equals("RoleHeir")) distalClassName = "Role";
                else if (distalClassName.equals("EntityHeir")) distalClassName = "Entity";

                Class distalClass = factory.rimObjectClass(distalClassName);

                String methodName = ("set" + methodNameStem).intern();
                try
                {
                    object.getClass().getMethod(methodName, new Class[]
                    { distalClass }).invoke(object, new Object[]
                    { value });
                    return object;

                }
                catch (NoSuchMethodException ex)
                {
                    // if the method isn't found, it could be that we didn't have
                    // the exact formal argument type right. Sadly enough we have
                    // to manually search for it. Yuck!
                    for (Method method : object.getClass().getMethods())
                    {
                        if (method.getName().equalsIgnoreCase(methodName))
                        {
                            Class fargs[] = method.getParameterTypes();
                            if (fargs.length == 1 && fargs[0].isAssignableFrom(value.getClass()))
                            {
                                // now we really got something useful
                                method.invoke(object, new Object[]
                                { value });
                                return object;
                            }
                        }
                    }
                    // if the for loop is exhausted, throw hands up in the air
                    throw ex;
                }
            }
        }
        catch (LoaderException ex)
        {
            throw new Error(ex);
        }
        catch (FactoryException ex)
        {
            throw new Error(ex);
        }
        catch (NoSuchMethodException ex)
        {
            throw new Error(ex);
        }
        catch (IllegalAccessException ex)
        {
            throw new Error(ex);
        }
        catch (InvocationTargetException ex)
        {
            throw new Error(ex);
        }
    }
		
	public C finish(C object, Locator loc) { 
		return object;
	}	
}

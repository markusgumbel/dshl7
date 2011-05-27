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
 * The Initial Developer of the Original Code is Brian DeCamp.
 * Portions created by Initial Developer are Copyright (C) 2002-2007
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hibernate;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.PropertyAccessException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.util.ReflectHelper;

import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.BasicPropertyAccessor;
import org.hibernate.property.DirectPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;

public class HybridPropertyAccessor implements PropertyAccessor {

  public Getter getGetter(Class theClass, String propertyName)
    throws PropertyNotFoundException {
    try {
      return (new DirectPropertyAccessor()).getGetter(theClass, "_"+propertyName);
    } catch (PropertyNotFoundException e) {
      return BasicPropertyAccessor.createGetter(theClass, propertyName);
    }
  }

  public Setter getSetter(Class theClass, String propertyName)
    throws PropertyNotFoundException {
    try {
      return (new DirectPropertyAccessor()).getSetter(theClass, "_"+propertyName);
    } catch (PropertyNotFoundException e) {
      return (new BasicPropertyAccessor()).getSetter(theClass, propertyName);
    }
  }

}

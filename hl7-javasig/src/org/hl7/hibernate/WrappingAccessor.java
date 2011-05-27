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
package org.hl7.hibernate;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.PropertyAccessException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;
import org.hibernate.util.ReflectHelper;
/**
 * Accesses property values via a get/set pair, but then intercepts
 * the value in order to do some data type changes (such as unwrapping
 * and wrapping collections.) This class is meant to be extended by
 * specializations that do specific wrapping and unwrapping actions.
 * As it is, this class will work without doing any wrapping or unwrapping.
 *
 * @author Gunther Schadow
 */
public class WrappingAccessor implements PropertyAccessor {
	
  protected WrappingSetter newWrappingSetter(Class clazz, Method method, String propertyName) {
    return new WrappingSetter(clazz, method, propertyName);
  }
	
  protected UnwrappingGetter newUnwrappingGetter(Class clazz, Method method, String propertyName) {
    return new UnwrappingGetter(clazz, method, propertyName);
  }
	
  public class WrappingSetter implements Setter {
		
    protected Object wrap(Object value) { return value; }
    
    private Class clazz;
    private final transient Method method;
    private final String propertyName;
		
    protected WrappingSetter(Class clazz, Method method, String propertyName) {
      this.clazz=clazz;
      this.method=method;
      this.propertyName=propertyName;
    }
		
    public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException {
      System.err.println("SETTING");
      try {
	method.invoke( target, new Object[] { wrap(value) } );
      }
      catch (NullPointerException npe) {
	if ( value==null && method.getParameterTypes()[0].isPrimitive() ) {
	  throw new PropertyAccessException(npe, "Null value was assigned to a property of primitive type", true, clazz, propertyName);
	}
	else {
	  throw new PropertyAccessException(npe, "NullPointerException occurred while calling", true, clazz, propertyName);
	}
      }
      catch (InvocationTargetException ite) {
	throw new PropertyAccessException(ite, "Exception occurred inside", true, clazz, propertyName);
      }
      catch (IllegalAccessException iae) {
	throw new PropertyAccessException(iae, "IllegalAccessException occurred while calling", true, clazz, propertyName);
	//cannot occur
      }
      catch (IllegalArgumentException iae) {
	if ( value==null && method.getParameterTypes()[0].isPrimitive() ) {
	  throw new PropertyAccessException(iae, "Null value was assigned to a property of primitive type", true, clazz, propertyName);
	}
	else {
	  throw new PropertyAccessException(iae, "IllegalArgumentException occurred while calling", true, clazz, propertyName);
	}
      }
    }
		
    public Method getMethod() {
      return method;
    }
		
    public String getMethodName() {
      return method.getName();
    }
		
    Object readResolve() {
      return createSetter(clazz, propertyName);
    }
		
    public String toString() {
      return "WrappingSetter(" + clazz.getName() + '.' + propertyName + ')';
    }
  }
	
  public class UnwrappingGetter implements Getter {
		
    protected Object unwrap(Object value) { return value; }
		
    private Class clazz;
    private final transient Method method;
    private final String propertyName;
		
    protected UnwrappingGetter(Class clazz, Method method, String propertyName) {
      this.clazz=clazz;
      this.method=method;
      this.propertyName=propertyName;
    }
		
    public Object get(Object target) throws HibernateException {
      try {
	return unwrap(method.invoke(target));
      }
      catch (InvocationTargetException ite) {
	throw new PropertyAccessException(ite, "Exception occurred inside", false, clazz, propertyName);
      }
      catch (IllegalAccessException iae) {
	throw new PropertyAccessException(iae, "IllegalAccessException occurred while calling", false, clazz, propertyName);
	//cannot occur
      }
      catch (IllegalArgumentException iae) {
	throw new PropertyAccessException(iae, "IllegalArgumentException occurred calling", false, clazz, propertyName);
      }
    }
		
    public Object getForInsert(Object target, SessionImplementor session) {
      return get(target);
    }

    public Object getForInsert(Object target, Map mergeMap, SessionImplementor session) {
      return get(target);
    }
		
    public Class getReturnType() {
      return method.getReturnType();
    }
		
    public Method getMethod() {
      return method;
    }
		
    public String getMethodName() {
      return method.getName();
    }
		
    public String toString() {
      return "UnwrappingGetter(" + clazz.getName() + '.' + propertyName + ')';
    }
		
    Object readResolve() {
      return createGetter(clazz, propertyName);
    }
		
  }
	
	
  public Setter getSetter(Class theClass, String propertyName) throws PropertyNotFoundException {
    return createSetter(theClass, propertyName);
  }
	
  private Setter createSetter(Class theClass, String propertyName) throws PropertyNotFoundException {
    WrappingSetter result = getSetterOrNull(theClass, propertyName);
    if (result==null) {
      throw new PropertyNotFoundException( "Could not find a setter for property " + propertyName + " in class " + theClass.getName() );
    }
    return result;
  }
	
  private WrappingSetter getSetterOrNull(Class theClass, String propertyName) {
		
    if (theClass==Object.class || theClass==null) return null;
		
    Method method = setterMethod(theClass, propertyName);
		
    if (method!=null) {
      if ( !ReflectHelper.isPublic(theClass, method) ) method.setAccessible(true);
      return newWrappingSetter(theClass, method, propertyName);
    }
    else {
      WrappingSetter setter = getSetterOrNull( theClass.getSuperclass(), propertyName );
      if (setter==null) {
	Class[] interfaces = theClass.getInterfaces();
	for ( int i=0; setter==null && i<interfaces.length; i++ ) {
	  setter=getSetterOrNull( interfaces[i], propertyName );
	}
      }
      return setter;
    }
		
  }
	
  private Method setterMethod(Class theClass, String propertyName) {
		
    UnwrappingGetter getter = getGetterOrNull(theClass, propertyName);
    Class returnType = (getter==null) ? null : getter.getReturnType();
		
    Method[] methods = theClass.getDeclaredMethods();
    Method potentialSetter = null;
    int currentPriority = 0;
    String propertyNameForHibernate = propertyName + "ForHibernate";
    for (int i=0; i<methods.length; i++) {
      String methodName = methods[i].getName();
			
      if ( methods[i].getParameterTypes().length==1 && methodName.startsWith("set") ) {
	String testStdMethod = Introspector.decapitalize( methodName.substring(3) );
	String testOldMethod = methodName.substring(3);
	int priority = 0;
	if ( testStdMethod.equals(propertyName) || testOldMethod.equals(propertyName) )
	  priority |= 2;
	else if ( testStdMethod.equals(propertyNameForHibernate) )
	  priority |= 4;
	if ( priority > 0 && ( returnType==null || methods[i].getParameterTypes()[0].equals(returnType) ) )
	  priority |= 1;
	if ( priority >= currentPriority && priority > 0 ) {
	  potentialSetter = methods[i];			
	  currentPriority = priority;
	}
	if ( priority == 5 )
	  return potentialSetter;
      }
    }
    return potentialSetter;
  }
	
  public Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException {
    return createGetter(theClass, propertyName);
  }
	
  public Getter createGetter(Class theClass, String propertyName) throws PropertyNotFoundException {
    UnwrappingGetter result = getGetterOrNull(theClass, propertyName);
    if (result==null) {
      throw new PropertyNotFoundException( 
					  "Could not find a getter for " + 
					  propertyName + 
					  " in class " + 
					  theClass.getName() 
					   );
    }
    return result;
		
  }
	
  private UnwrappingGetter getGetterOrNull(Class theClass, String propertyName) {
		
    if (theClass==Object.class || theClass==null) return null;
		
    Method method = getterMethod(theClass, propertyName);
		
    if (method!=null) {
      if ( !ReflectHelper.isPublic(theClass, method) ) method.setAccessible(true);
      return newUnwrappingGetter(theClass, method, propertyName);
    }
    else {
      UnwrappingGetter getter = getGetterOrNull( theClass.getSuperclass(), propertyName );
      if (getter==null) {
	Class[] interfaces = theClass.getInterfaces();
	for ( int i=0; getter==null && i<interfaces.length; i++ ) {
	  getter=getGetterOrNull( interfaces[i], propertyName );
	}
      }
      return getter;
    }
  }
	
  private Method getterMethod(Class theClass, String propertyName) {
		
    Method[] methods = theClass.getDeclaredMethods();
    for (int i=0; i<methods.length; i++) {
      // only carry on if the method has no parameters
      if ( methods[i].getParameterTypes().length==0 ) {
	String methodName = methods[i].getName();
				
	// try "get"
	if( methodName.startsWith("get") ) {
	  String testStdMethod = Introspector.decapitalize( methodName.substring(3) );
	  String testOldMethod = methodName.substring(3);
	  if( testStdMethod.equals(propertyName) || testOldMethod.equals(propertyName) ) return methods[i];
					
	}
				
	// if not "get" then try "is"
	/*boolean isBoolean = methods[i].getReturnType().equals(Boolean.class) ||
	  methods[i].getReturnType().equals(boolean.class);*/
	if( methodName.startsWith("is") ) {
	  String testStdMethod = Introspector.decapitalize( methodName.substring(2) );
	  String testOldMethod = methodName.substring(2);
	  if( testStdMethod.equals(propertyName) || testOldMethod.equals(propertyName) ) return methods[i];
	}
      }
    }
    return null;
  }
	
}

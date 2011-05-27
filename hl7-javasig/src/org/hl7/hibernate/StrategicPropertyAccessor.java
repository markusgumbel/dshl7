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
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;

/**
 * Use field access for Hibernate PropertyAccessor Getter and Setter
 * implementations with an HL7 property naming strategy, and 
 * wrapping strategy.
 * 
 * @author bdecamp
 */
public class StrategicPropertyAccessor implements PropertyAccessor  {

  private PropertyAccessor propertyAccessStrategy = getPropertyAccessStrategy();
    
  // Hibernate ProertyAccessor interface
  public final Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException {
    Getter propertyGetterDelegate = propertyAccessStrategy.getGetter(theClass, getFieldNamingStrategy().getFieldName(propertyName));
    return new FieldWrappingGetter(propertyGetterDelegate, getWrappingStrategy());
  }

  // Hibernate ProertyAccessor interface
  public final Setter getSetter(Class theClass, String propertyName) throws PropertyNotFoundException {
    Setter propertySetterDelegate = propertyAccessStrategy.getSetter(theClass, getFieldNamingStrategy().getFieldName(propertyName));
    return new FieldWrappingSetter(propertySetterDelegate, getWrappingStrategy());
  }

  /**
   * Default PropertyAccessor uses direct field access, 
   * may be overridden.
   */
  public PropertyAccessor getPropertyAccessStrategy() {
    //return new org.hibernate.property.BasicPropertyAccessor();
    //return new org.hibernate.property.DirectPropertyAccessor();
    return new org.hl7.hibernate.HybridPropertyAccessor();
  }
    
  /**
   * Naming strategy for locating fields.
   */
  public interface FieldNamingStrategy {
    String getFieldName(String propertyName);
  }
    
  private static class BasicHL7FieldNamingStrategy implements FieldNamingStrategy {
    public String getFieldName(String propertyName) {
      return propertyName;
    }
  }

  private static class DirectHL7FieldNamingStrategy implements FieldNamingStrategy {
    public String getFieldName(String propertyName) {
      return "_" + propertyName;
    }
  }

  private static class HybridHL7FieldNamingStrategy implements FieldNamingStrategy {
    public String getFieldName(String propertyName) {
      return propertyName;
    }
  }



  /**
   * FieldNamingStrategy factory method may be overridden
   */
  protected FieldNamingStrategy getFieldNamingStrategy() {
    //return new BasicHL7FieldNamingStrategy();
    //return new DirectHL7FieldNamingStrategy();
    return new HybridHL7FieldNamingStrategy();
  }

  /**
   * Wrapping strategy
   */
  public interface FieldWrappingStrategy {
    public Object bean2DBValue(Object beanValue);
    public Object db2BeanValue(Object dbValue);
  }
    
  private static class NoopWrapper implements FieldWrappingStrategy {
    public Object bean2DBValue(Object beanValue) { return beanValue; }
    public Object db2BeanValue(Object dbValue) { return dbValue; }
        
  }

  /**
   * FieldWrappingStrategy factory method to be overridden
   */
  public FieldWrappingStrategy getWrappingStrategy() {
    return new NoopWrapper();
  }

  /**
   * Default FieldWrappingGetter used the Hibernate field access,
   * with the HL7 field naming strategy, and a null wrapper.
   */
  private static class FieldWrappingGetter implements Getter {
    private Getter delegateGetter;
    private FieldWrappingStrategy wrapper;
    public FieldWrappingGetter(Getter delegateGetter, FieldWrappingStrategy wrapper) {
      this.delegateGetter = delegateGetter;
      this.wrapper = wrapper;
    }
        
    public Object get(Object owner) throws HibernateException {
      Object beanValue = delegateGetter.get(owner);
      return wrapper.bean2DBValue(beanValue);
    }

    // Delegation
    public Class getReturnType() { return delegateGetter.getReturnType(); }
    public Method getMethod() { return delegateGetter.getMethod(); }
    public String getMethodName() { return delegateGetter.getMethodName(); }
    public Object getForInsert(Object owner, Map mergeMap, SessionImplementor session) throws HibernateException {
      Object beanValue = delegateGetter.getForInsert(owner, mergeMap, session);
      return wrapper.bean2DBValue(beanValue);
    }
  }

  /**
   * Default FieldWrappingSetter used the Hibernate field access,
   * with the HL7 field naming strategy, and a Noop wrapper.
   */
  private static class FieldWrappingSetter implements Setter {
        
    private Setter delegateSetter;
    private FieldWrappingStrategy wrapper;
    public FieldWrappingSetter(Setter delegateSetter, FieldWrappingStrategy wrapper) {
      this.delegateSetter = delegateSetter;
      this.wrapper = wrapper;
    }

    // Delegation
    public Method getMethod() { return delegateSetter.getMethod(); }
    public String getMethodName() { return delegateSetter.getMethodName(); }
    public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException {
      Object beanValue = wrapper.db2BeanValue(value);
      delegateSetter.set(target, beanValue, factory);
    }
  }
}

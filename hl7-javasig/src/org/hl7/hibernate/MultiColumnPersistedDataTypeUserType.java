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
 * Portions created by Initial Developer are Copyright (C) 2002-2006
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */

package org.hl7.hibernate;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.type.TypeFactory;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.ParameterizedType;
import org.hl7.types.ANY;
import org.hl7.types.ValueFactory;
import org.hl7.types.ValueFactoryException;
import org.hl7.util.ClassMethodTool;

/** A Hibernate UserType that persists HL7 data type values in multiple columns of a table.
    This is a generic approach, where one mapper class does it all. It uses the ParameterizedType
    interface of hibernate

    <pre>
    &lt;property name="code">
    &lt;type class="org.hl7.hibernate.MultiColumnPersistedDataTypeUserType"> 
    &lt;param name="type">CV&lt;/param>
    &lt;param name="interface">org.hl7.type.CV&lt;/param>
    &lt;param name="class">org.hl7.type.impl.CVimpl&lt;/param>
    &lt;param name="staticFactoryMethod">valueOf&lt;/param>
    &lt;param name="properties">3&lt;/param>
    &lt;param name="property.0.name">code&lt;/param>
    &lt;param name="property.0.type">string&lt;/param>
    &lt;param name="property.0.class">java.lang.String&lt;/param>
    &lt;param name="property.1.name">codeSystem&lt;/param>
    &lt;param name="property.1.type">string&lt;/param>
    &lt;param name="property.1.class">java.lang.String&lt;/param>
    &lt;param name="property.2.class">java.lang.String&lt;/param>
    &lt;/type>
    &lt;/property> 
    </pre>

    <p>All properties mentioned here will end up on the constructor's or factory method's
    argument list in the order they are specified. In order to do this, all properties 
    must have the java class or interface defined exactly as it occurs on the formal
    argument list of the respective constructor or factory method.</p>

    <p>Properties with a name will also become those properties that are explicitly mappable
    to columns. These properties must have a hibernate Type defined.</p>

    <p>Properties that are not named, and therefore not mappable to columns, but that are
    still necessary to construct a value, can be specified with constant values. By default,
    the constant value is Java null.</p>

    <p>FIXME: We will have to provide a way to specify the constructor of the proper
    argument type in case the type is something other than the vanilla data types.</p>

    @author Gunther Schadow
    @version $Id: MultiColumnPersistedDataTypeUserType.java 6625 2007-07-10 16:29:05Z ccrafton $
*/
public class MultiColumnPersistedDataTypeUserType extends AbstractDataTypeCompositeUserType implements ParameterizedType, CompositeUserType {
  private static final Logger LOGGER = Logger.getLogger("org.hl7.hibernate");
  private String _typeSpec = null;
  private Class _interface = null;
  private Class _class = null;

  /** A unifying interface to static Methods and Constructors since both are statically callable. */
  private static class StaticallyCallable {
    private AccessibleObject _callable;
    private Object _target = null;
    public StaticallyCallable(AccessibleObject callable, Object target) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
      _callable = callable;
      _target = target;
    }
    public StaticallyCallable(AccessibleObject callable) throws NoSuchMethodException, IllegalAccessException, InstantiationException { 
      _callable = callable; 
      if((_callable instanceof Method) && ((((Method)_callable).getModifiers() & Modifier.STATIC) == 0))
				throw new NoSuchMethodException("not a static method " + _callable);
    }
    public Object call(Object args[]) throws InvocationTargetException, IllegalAccessException, InstantiationException {
      if(_target != null)
				return ((Method)_callable).invoke(_target, args);
      else if(_callable instanceof Method)
				return ((Method)_callable).invoke(null, args);
      else
				return ((Constructor)_callable).newInstance(args);
    }
  }

  /** The constructor or factory that creates a value object. */
  private StaticallyCallable _constructor = null;
  private static class Property {
    public String _name = null;
    public Class  _class = null;
    public Type   _hibernateType = null;
    public String _type = null;
    public Object _constantValue = null;
    public String _accessor =null;
    public String _accessorTool = null;
    public String _accessorMethod = null;
  }

  /** All the properties in the order in which they appear on the constructor or factory method's argument list */
  private Property _properties[];

  /** All the properties exposed to hibernate mapping to different columns. */
  private Property _exposedProperties[];

  /** The names of the properties that can be mapped to different columns. */
  private String _flatExposedPropertyNames[] = null;
  /** The hibernate types of the properties that can be mapped to different columns. */
  private Type _flatExposedPropertyTypes[] = null;
  
  private static String STRING_ARRAY_PROTOTYPE[] = new String[0];
  private static Type   TYPE_ARRAY_PROTOTYPE[] = new Type[0];
  private static Property   PROPERTY_ARRAY_PROTOTYPE[] = new Property[0];
  /** Gets called by Hibernate to pass the configured type parameters to the implementation. */
  public void setParameterValues(Properties parameters) {
    try {      
	
      /** Mandatory parameter **/
      _typeSpec = (String)parameters.get("type");
      if(_typeSpec == null) {
				throw new Error("type parameter is missing");
      }

      String className = (String)parameters.get("class");
      String interfaceName = (String)parameters.get("interface");      
      int propertyCount = Integer.parseInt((String)parameters.get("properties"));
      
      // requiring this makes our job of analyzing the parameters easier for now

      int exposedPropertiesCount = propertyCount;
      
      _properties = new Property[propertyCount];
      
      // we need this separately in order to find the constructor or static factory method
      Class constructorSignature[] = new Class[propertyCount];
      
      // this is the length of the exposed property arrays, initially same as argument count and decremented for each anonymous property
      List<String> exposedPropertyNameList = new ArrayList<String>();
      List<Type> exposedPropertyTypeList = new ArrayList<Type>();
      List<Property> exposedPropertyList = new ArrayList<Property>();
      
      for(int i = 0; i < propertyCount; i++) {
				Property property = _properties[i] = new Property();
	
				// class is mandatory
				constructorSignature[i] = property._class = Class.forName((String)parameters.get("property." + i + ".class"));
		      
				// name is optional, and only named properties are exposed to hibernate
				String name = (String)parameters.get("property." + i + ".name");
				if(name != null) {
					exposedPropertyList.add(property);
					property._name = name;
					property._type = (String)parameters.get("property." + i + ".type");
					property._accessor = (String)parameters.get("property." + i + ".accessor");
					if(property._accessor == null)
						property._accessor = property._name;
					property._accessorTool=(String)parameters.get("property." + i +	".accessorTool");
					property._accessorMethod=(String)parameters.get("property." + i + ".accessorMethod");
					String params = (String)parameters.get("property."+i+".type.params");
					if(params != null) {
						Properties propertyParams = new Properties();
						Iterator<Map.Entry<Object,Object>> paramIter = parameters.entrySet().iterator();
						String prefix = "property."+i+".type.param.";
						while(paramIter.hasNext()) {
							Map.Entry<Object,Object> entry = paramIter.next();
							String paramKey = (String)entry.getKey();
							if(paramKey.startsWith(prefix)) {
								propertyParams.put("type",entry.getValue());
							}
						}

						if (property._type != null) {
							property._hibernateType = TypeFactory.heuristicType(property._type, propertyParams);
						} else
							throw new Error("property type must be set");
					} else {
						property._hibernateType = TypeFactory.heuristicType(property._type);
					}

					if(property._hibernateType instanceof CompositeUserType) {
						CompositeUserType compositeUserType = (CompositeUserType)property._hibernateType;
						String propertyNames[] = compositeUserType.getPropertyNames();
						for(int j = 0; j < propertyNames.length; j++)
							exposedPropertyNameList.add(propertyNames[j]);
						Type propertyTypes[] = compositeUserType.getPropertyTypes();
						for(int j = 0; j < propertyTypes.length; j++)
							exposedPropertyTypeList.add(propertyTypes[j]); 
					} else  {
						exposedPropertyNameList.add(property._name);
						exposedPropertyTypeList.add(property._hibernateType);
					}
				} 
				String constLiteral = (String)parameters.get("property." + i + ".const");
				if (constLiteral!=null) {
					String typeSpec=(String)parameters.get("property."+i+".typeSpec");
					if (typeSpec==null) {
						/** assume the type is java.lang.String **/
						property._constantValue=constLiteral;
					} else if (typeSpec!=null) {
						try {
							property._constantValue=ValueFactory.getInstance().valueOfLiteral(typeSpec, constLiteral);
						} catch (ValueFactoryException vfe) {
							vfe.printStackTrace();
						}
					} 
				} 
	
      }
      
      _flatExposedPropertyNames = exposedPropertyNameList.toArray(STRING_ARRAY_PROTOTYPE);
      _flatExposedPropertyTypes = exposedPropertyTypeList.toArray(TYPE_ARRAY_PROTOTYPE);
      _exposedProperties = exposedPropertyList.toArray(PROPERTY_ARRAY_PROTOTYPE);
      
      // find the constructor or static factory method
      String staticFactoryMethodName = (String)parameters.get("staticFactoryMethod");
      String valueFactoryMethodName = (String)parameters.get("valueFactoryMethod");

      if(valueFactoryMethodName == null) { 
				_class = Class.forName(className);

				// interface is optional, if not provided it's same as class
				if(interfaceName != null)
					_interface = Class.forName(interfaceName);
				else
					_interface = _class;
      } else { 
				_interface = Class.forName(interfaceName);
				_class = null;
      }

      if(valueFactoryMethodName != null) {
				ValueFactory vfact = ValueFactory.getInstance();
				_constructor = new StaticallyCallable(ClassMethodTool.getMethod(vfact.getClass(), valueFactoryMethodName, constructorSignature), vfact);
      } else if(staticFactoryMethodName != null) {
				_constructor = new StaticallyCallable(ClassMethodTool.getMethod(_class, staticFactoryMethodName, constructorSignature));
      } else {
				_constructor = new StaticallyCallable(ClassMethodTool.getConstructor(_class, constructorSignature));
      }
		 
    } 
    catch(ClassNotFoundException x) {
      throw new Error(x);
    } 
    catch(NoSuchMethodException x) {
      throw new Error(x);
    } 
    catch(InstantiationException x) {
			throw new Error(x);
    } 
    catch(IllegalAccessException x) {
      throw new Error(x);
    }
  }

  /** The class returned by nullSafeGet(). */
  public Class returnedClass() {
    return _interface;
  }

  /** Return the hibernate types for the columns mapped by this type. */
  public Type[] getPropertyTypes() { return _flatExposedPropertyTypes; }

  /** Return the hibernate types for the columns mapped by this type. */
  public String[] getPropertyNames() { return _flatExposedPropertyNames; }

  /* Get the value of a property. */
  public Object getPropertyValue(Object rawValue, int propertyIndex) throws HibernateException {
    if(rawValue!=null) {
      ANY value = (ANY)rawValue;
      Property property = _exposedProperties[propertyIndex];
      
      /** If a DatatypeTool and and Method have been specified, use them, 
					else use the accessorName **/

      try {      
				if (property._accessorTool!=null) {
					if (property._accessorMethod==null) 
						throw new Error("Must specify both accessorTool AND accesorMethod");
					Class clazz=Class.forName(property._accessorTool);
					Method[] methods=clazz.getMethods();
					Method getter=null;
					for (Method m : methods) {
						if (m.getName().equals(property._accessorMethod))
							getter=m;
					}
					if (getter==null) 
						throw new Error("Method "+property._accessorMethod+" not found!");
					Class[] paramTypes=getter.getParameterTypes();
					if (paramTypes==null) 
						throw new Error("Method "+getter+"has no args!");
					Class valueClazz=paramTypes[0];
					return getter.invoke(null,new Object[] {valueClazz.cast(value)});
	  
				} else {
					return value.getClass().getMethod(property._accessor).invoke(value);
				}
      } catch(NoSuchMethodException ex) {
				throw new HibernateException(ex);
      } catch(InvocationTargetException ex) {
				Throwable twb = ex.getCause();
				if(twb instanceof UnsupportedOperationException) {
					System.err.println(value.getClass().getName() + " " + value.isNull() + " " + value);
					if(value.isNull().isTrue())
						return null;
				}
				throw new HibernateException(ex);
      } catch(IllegalAccessException ex) {
				throw new HibernateException(ex);
      } catch (ClassNotFoundException ex) {
				throw new HibernateException(ex);
      }
    } else 
      return null;      
  }
  
  /** Retrieve an instance of the mapped class from a JDBC resultset. */
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException {
    try {
      int propertyCount = _properties.length;
      Object propertyValues[] = new Object[_properties.length]; 
      boolean allNull = true;
      int j = 0;
      for(int i = 0; i < propertyCount; i++) {
				Property property = _properties[i];
				if(property._class != null) {
					Object value;
					
					if (property._constantValue!=null) {
						value=property._constantValue; 
					} else {
						try {
							value = property._class.cast(property._hibernateType.nullSafeGet(rs, names[i], session, owner));
						} catch (ClassCastException c) {
							try {
								/** intution tells me that if we get here it's because
										something isn't corrent in template.hbm.xml **/
								value = property._class.cast(property._hibernateType.nullSafeGet(rs, names[i], session, owner).toString());
							} catch (ClassCastException cce) {
								throw new HibernateException(cce.getMessage() + ": " + property._name + ": " + property._hibernateType + ": (" + property._class + ")" + property._hibernateType.nullSafeGet(rs, names[i], session, owner), cce);
							}
						}
					}
					allNull = allNull && (value == null);
					propertyValues[i] = value;
					
				}
      }
      
      if(allNull) // if all arguments are null, make a null value
				return ValueFactory.getInstance().nullValueOf(_typeSpec,"NI");
      else 
				return _constructor.call(propertyValues);
			
    } catch(SQLException ex) {
      throw new HibernateException(ex);
    } catch(ValueFactoryException ex) {
      throw new HibernateException(ex);
    } catch(InvocationTargetException ex) {
      throw new HibernateException(ex);
    } catch(IllegalAccessException ex) {
      throw new HibernateException(ex);
    } catch(IllegalArgumentException ex) {
      throw new HibernateException(ex);
    } catch(InstantiationException ex) {
      throw new HibernateException(ex);
    }
  }

  /** Write an instance of the mapped class to a prepared statement. */
  public void nullSafeSet(PreparedStatement st, Object rawValue, int index, SessionImplementor session) throws HibernateException {
    final int exposedPropertyCount = _exposedProperties.length;
	
    try {
      if(rawValue!=null) {
				final ANY value = (ANY)rawValue;
				if(value != null && value.isNull().isFalse()) {
					for(int i = 0; i < exposedPropertyCount; i++) {
						Property property = _exposedProperties[i];
						Object propertyValue = getPropertyValue(value, i);
						property._hibernateType.nullSafeSet(st, propertyValue, index + i, session);      
					}
				} else { // XXX: this is too radical, we could save some of the non-essential properties still
					for(int i = 0; i < exposedPropertyCount; i++) {
						_exposedProperties[i]._hibernateType.nullSafeSet(st, null, index + i, session);      
					}
				}
      } else {
				// don't ever forget to set this to null explicitly or 
				// else the value from the previous insert might be used!
				for(int i = 0; i < exposedPropertyCount; i++) {
					_exposedProperties[i]._hibernateType.nullSafeSet(st, null, index + i, session);      
				}
      }
    } catch(SQLException ex) {
      throw new HibernateException(ex);
    }
  }
}

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
* The Initial Developer of the Original Code is .
* Portions created by Initial Developer are Copyright (C) 2002-2004 
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*/
package org.hl7.meta.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.hl7.meta.Datatype;
import org.hl7.meta.JavaIts;
import org.hl7.rim.RimObject;
import org.hl7.util.StringUtils;

/**
 * Provides Java ITS specific functionality.
 *
 * @author  Jerry Joyce
 */
public class JavaItsImpl implements JavaIts
{
  //-------------------------------------------------------------------------
  /** Prefix for data type interfaces. */
  private static final String DT_INTF_PREFIX = "org.hl7.types.";
  /** Suffix for data type interfaces. */
  private static final String DT_INTF_SUFFIX = "";

  /** Prefix for RIM interfaces. */
  private static final String RIM_INTF_PREFIX = "org.hl7.rim.";
  /** Suffix for RIM interfaces. */
  private static final String RIM_INTF_SUFFIX = "";

  /** Prefix for RIM implementation classes. */
  private static final String RIM_IMPL_PREFIX = "org.hl7.rim.impl.";
  /** Suffix for RIM implementation classes. */
  private static final String RIM_IMPL_SUFFIX = "Impl";

  /** Prefix for RIM attribute and association getters. */
  private static final String GETTER_PREFIX = "get";
  /** Prefix for RIM attribute and association setters. */
  private static final String SETTER_PREFIX = "set";
  /** Prefix for RIM attribute and association setters. */
  private static final String ADDER_PREFIX = "add";

  //-------------------------------------------------------------------------
  /** Cache for datatype interfaces */
  private static final HashMap<String, Class> DT_INTF_CACHE =
    new HashMap<String, Class>();

//  //-------------------------------------------------------------------------
//  /** Cache for datatype implementation classes */
//  private static final HashMap<String, Class> DATATYPE_IMPL_CACHE =
//    new HashMap<String, Class>();
//
//  static
//  {
//    // AD is not implemented.
//    // ANY implementation is not visible.
//    // BAG<T> is not implemented.
//    DATATYPE_IMPL_CACHE.put("BIN", org.hl7.types.impl.BINbyteArrayImpl.class);
//    DATATYPE_IMPL_CACHE.put("BL", org.hl7.types.impl.BLimpl.class);
//    // CD non-null implementation is missing.
//    DATATYPE_IMPL_CACHE.put("CE", org.hl7.types.impl.CEimpl.class);
//    DATATYPE_IMPL_CACHE.put("CS", org.hl7.types.impl.CSimpl.class);
//    DATATYPE_IMPL_CACHE.put("CV", org.hl7.types.impl.CVimpl.class);
//    // ED non-null implementation is missing.
//    // EN is not implemented.
//    // GTS is not implemented.
//    DATATYPE_IMPL_CACHE.put("II", org.hl7.types.impl.IIimpl.class);
//    DATATYPE_IMPL_CACHE.put("INT", org.hl7.types.impl.INTlongAdapter.class);
//    // IVL<T> is not implemented.
////    DATATYPE_IMPL_CACHE.put("LIST<INT>",
////      new org.hl7.types.impl.LISTjcListAdapter<org.hl7.types.INT>().getClass());
//    // MO is not implemented.
//    // PQ non-null implementation is missing.
//    DATATYPE_IMPL_CACHE.put("REAL", org.hl7.types.impl.REALdoubleAdapter.class);
//    // RTO is not implemented.
//    // RTO<T1,T2> is not implemented.
//    // SET<AD> cannot be created since AD is not implemented.
////    DATATYPE_IMPL_CACHE.put("SET<CD>",
////      org.hl7.types.impl.SETjuSetAdapter<org.hl7.types.CD>.class);
////    DATATYPE_IMPL_CACHE.put("SET<CE>",
////      org.hl7.types.impl.SETjuSetAdapter<org.hl7.types.CE>.class);
////    DATATYPE_IMPL_CACHE.put("SET<CS>",
////      org.hl7.types.impl.SETjuSetAdapter<org.hl7.types.CS>.class);
//    // SET<ED> cannot be created since ED is not implemented.
//    // SET<EN> cannot be created since EN is not implemented.
////    DATATYPE_IMPL_CACHE.put("SET<II>",
////      org.hl7.types.impl.SETjuSetAdapter<org.hl7.types.II>.class);
//    // SET<ON> cannot be created since ON is not implemented.
//    // SET<PN> cannot be created since PN is not implemented.
//    // SET<PQ> cannot be created since PQ is not implemented.
//    // SET<RTO> cannot be created since RTO is not implemented.
//    // SET<TEL> cannot be created since TEL is not implemented.
//    DATATYPE_IMPL_CACHE.put("ST", org.hl7.types.impl.STjlStringAdapter.class);
//    // TEL non-null implementation is missing.
//    // TS non-null implementation is missing.
//    // UVP<RTO<PQ,PQ>> cannot be created since RTO<T1,T2> is not implemented.
//  }

  //-------------------------------------------------------------------------
  /** Cache for RIM interfaces */
  private static final HashMap<String, Class> RIM_INTF_CACHE =
    new HashMap<String, Class>();
  /** Cache for RIM classes */
  private static final HashMap<String, Class> RIM_IMPL_CACHE =
    new HashMap<String, Class>();

  //-------------------------------------------------------------------------
  /**
   * Returns the Java interface for the given RIM datatype.
   *
   * @param datatype  RIM datatype
   * @return  corresponding Java interface
   * @throws ClassNotFoundException  if the Java interface cannot be found
   */
  public Class getDatatypeInterface(Datatype datatype)
    throws ClassNotFoundException
  {
    String name = datatype.getFullName();

    Class dtClass = null;
    synchronized (DT_INTF_CACHE)
    {
      dtClass = DT_INTF_CACHE.get(name);
    }
    if (dtClass != null) return dtClass;

    dtClass = Class.forName(DT_INTF_PREFIX + name + DT_INTF_SUFFIX);
    synchronized (DT_INTF_CACHE)
    {
      DT_INTF_CACHE.put(name, dtClass);
    }
    return dtClass;
  }

  //-------------------------------------------------------------------------
  /**
   * Returns the Java interface for the given RIM class.
   *
   * @param rimName  RIM class
   * @return  corresponding Java interface
   * @throws ClassNotFoundException  if the Java interface cannot be found
   */
  public Class getRimInterface(String rimName)
    throws ClassNotFoundException
  {
    Class intfClass = null;
    synchronized (RIM_INTF_CACHE)
    {
      intfClass = RIM_INTF_CACHE.get(rimName);
    }
    if (intfClass != null) return intfClass;

    intfClass = Class.forName(RIM_INTF_PREFIX + mangleHl7Name(rimName) +
      RIM_INTF_SUFFIX);
    synchronized (RIM_INTF_CACHE)
    {
      RIM_INTF_CACHE.put(rimName, intfClass);
    }
    return intfClass;
  }

  //-------------------------------------------------------------------------
  /**
   * Returns the Java class implementing the given RIM class.
   *
   * @param rimName  RIM class name
   * @return  corresponding Java class
   * @throws ClassNotFoundException  if the Java class cannot be found
   */
  public Class getRimImplementation(String rimName)
    throws ClassNotFoundException
  {
    Class implClass = null;
    synchronized (RIM_IMPL_CACHE)
    {
      implClass = RIM_IMPL_CACHE.get(rimName);
    }
    if (implClass != null) return implClass;

    implClass = Class.forName(RIM_IMPL_PREFIX + mangleHl7Name(rimName) +
      RIM_IMPL_SUFFIX);
    synchronized (RIM_IMPL_CACHE)
    {
      RIM_IMPL_CACHE.put(rimName, implClass);
    }
    return implClass;
  }

  //-------------------------------------------------------------------------
  /**
   * Returns the getter method for the given attribute or association (feature).
   * 
   * @param rimClass  RIM class implementation
   * @param featureName  RIM feature name
   * @return  corresponding Java getter method
   * @throws NoSuchMethodException  if there is no such getter
   */
  public Method getFeatureGetter(Class rimClass, String featureName)
    throws NoSuchMethodException
  {
    return rimClass.getMethod(GETTER_PREFIX + mangleHl7Name(featureName),
      (Class[])null);
  }

  //-------------------------------------------------------------------------
  /**
   * Returns the name of setter for the given attribute.
   * 
   * @param rimClass  RIM class implementation
   * @param attributeName  RIM attribute name
   * @param datatype  RIM datatype
   * @return  corresponding Java setter method name
   * @throws ClassNotFoundException  if the Java class for the datatype
   *           cannot be found
   * @throws NoSuchMethodException  if there is no such getter
   */
  public Method getAttributeSetter(Class rimClass, String attributeName,
    Datatype datatype) throws ClassNotFoundException, NoSuchMethodException
  {
    return rimClass.getMethod(SETTER_PREFIX + mangleHl7Name(attributeName),
      new Class[] { getDatatypeInterface(datatype) });
  }

  //-------------------------------------------------------------------------
  /**
   * Returns the setter method for the given association.
   * 
   * @param rimClass  RIM class implementation
   * @param associationName  RIM association name
   * @param distalRimClass  Distal RIM class name
   * @return  corresponding Java setter method
   * @throws NoSuchMethodException  if there is no such setter
   */
  public Method getAssociationSetter(Class rimClass, String associationName,
    String distalRimClass)
    throws ClassNotFoundException, NoSuchMethodException
  {
    return rimClass.getMethod(SETTER_PREFIX + mangleHl7Name(associationName),
      new Class[] { getRimInterface(distalRimClass) });
  }


  //-------------------------------------------------------------------------
  /**
   * Returns the adder method for the given association.
   * 
   * @param rimClass  RIM class implementation
   * @param associationName  RIM association name
   * @param distalRimClass  Distal RIM class name
   * @return  corresponding Java setter method
   * @throws NoSuchMethodException  if there is no such setter
   */
  public Method getAssociationAdder(Class rimClass, String associationName, String distalRimClass)
    throws ClassNotFoundException, NoSuchMethodException
  {
    return rimClass.getMethod(ADDER_PREFIX + mangleHl7Name(associationName),
      new Class[] { getRimInterface(distalRimClass) });
  }


  //-------------------------------------------------------------------------
  /**
   * Applies CamelCase to class names (first letter capital).
   *
   * @param s  RIM class name to mangle
   * @return  the same name mangled into CamelCase
   */
  protected String mangleHl7Name(String s)
  {
    if (s.indexOf('_') == -1)
    {
      return StringUtils.forceInitialCap(s);
    }
    else
    {
      StringBuffer result = new StringBuffer();

      for (StringTokenizer st = new StringTokenizer(s, "_");
        st.hasMoreTokens(); )
      {
        String token = st.nextToken();

        result.append(StringUtils.forceInitialCap(token));
      }

      return result.toString();
    }
  }

  //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------

    /**
     *
     * @param rimObject
     * @param methodNameStem
     * @return
     * @throws NoSuchMethodException
     */

    public Class getRIMDataType(RimObject rimObject, String methodNameStem) throws NoSuchMethodException {
			Method methods[] = rimObject.getClass().getMethods();
			int max = methods.length;
			String methodName = ("get" + methodNameStem).intern();
			for(int i = 0; i < max; i++) {
				Method method = methods[i];
				if ( methodName.equals(method.getName()))
					return method.getReturnType();
			}
			throw new NoSuchMethodException(rimObject.getClass().getName() + "." + methodName);
    }
}

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

package org.hl7.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

public class FactoryFinder
{
  //-------------------------------------------------------------------------
  private static HashMap<String, Object> cache_ =
    new HashMap<String, Object>();

  //-------------------------------------------------------------------------
  private static Object newInstance(String className)
    throws FactoryException
  {
     try
     {
       return Class.forName(className).newInstance();
     }
     catch (ClassNotFoundException ex)
     {
       throw new FactoryException("Factory " + className + " not found", ex);
     }
     catch (Exception ex)
     {
       throw new FactoryException("Factory " + className +
         " cannot be created", ex);
     }
  }

  //-------------------------------------------------------------------------
  public static Object find(String factoryId, String fallbackClassName)
    throws FactoryException
  {
    synchronized (cache_)
    {
      Object result = cache_.get(factoryId);
      if (result == null)
      {
        // Check system property first.
        result = tryFindFromSystemProperty(factoryId);
        if (result == null)
        {
          // Check file hl7.properties in current directory next.
          result = tryFindFromHl7Properties(factoryId);
          if (result == null)
          {
            // Use Services API.
            result = tryFindFromServicesApi(factoryId);
            if (result == null)
            {
              // As a last resort, fall back to the default factory.
              result = newInstance(fallbackClassName);
            }
          }
        }

        // Guaranteed (result != null) here, since the default factory is always
        // creates, or throws a FactoryException.
        cache_.put(factoryId, result);
      }

      return result;
    }
  }

  //-------------------------------------------------------------------------
  private static Object tryFindFromSystemProperty(String factoryId)
    throws FactoryException
  {
    String systemProperty = System.getProperty(factoryId);
    return (systemProperty != null) ? newInstance(systemProperty) : null;
  }

  //-------------------------------------------------------------------------
  private static Object tryFindFromHl7Properties(String factoryId)
    throws FactoryException
  {
    InputStream inputStream = null;
    try
    {
      inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("hl7.properties");

      Properties props = new Properties();
      props.load(inputStream);

      String factoryClassName = props.getProperty(factoryId);
      return newInstance(factoryClassName);
    }
    catch (Exception ignore)
    {
      return null;
    }
    finally
    {
      if (inputStream != null)
      {
        try { inputStream.close(); } catch (IOException ignore) {}
      }
    }
  }

  //-------------------------------------------------------------------------
  private static Object tryFindFromServicesApi(String factoryId)
    throws FactoryException
  {
    String serviceId = "META-INF/services/" + factoryId;
    InputStream inputStream = null;
    BufferedReader reader = null;
    try
    {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      inputStream = classLoader.getResourceAsStream(serviceId);
      if (inputStream == null) return null;

      reader = new BufferedReader(new InputStreamReader(inputStream));
      String factoryClassName = reader.readLine();
      return newInstance(factoryClassName);
    }
    catch (Exception ignore)
    {
      return null;
    }
    finally
    {
      if (reader != null)
      {
        try { reader.close(); } catch (IOException ignore) {}
      }
      if (inputStream != null)
      {
        try { inputStream.close(); } catch (IOException ignore) {}
      }
    }
  }
}

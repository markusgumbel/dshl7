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

package org.hl7.rim.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.hl7.rim.RimObject;
import org.hl7.rim.RimObjectFactory;
import org.hl7.util.FactoryException;

public class RimObjectFactoryUsingProperties extends RimObjectFactory {
  protected static final Logger LOGGER =
	Logger.getLogger("org.hl7.factories");

  private Properties props_ = new Properties();
  private HashMap<String, RimObject> cache_ = new HashMap<String, RimObject>();

  public RimObjectFactoryUsingProperties() throws FactoryException {
    InputStream fi = null;
    try {
      fi = Thread.currentThread().getContextClassLoader().getResourceAsStream("rim-map.properties");
      props_.load(fi);
    }
    catch (IOException ex) {
      throw new FactoryException("Factory RimObjectFactoryUsingProperties cannot " +
																 "be created", ex);
    }
    finally {
      if (fi != null) try { fi.close(); } catch (IOException ignore) {}
    }
  }


  public RimObject createRimObject(String name) throws FactoryException {
    LOGGER.finest("Creating RIM object: " + name);

    synchronized (cache_) {
      RimObject rimObject = cache_.get(name);

      if (rimObject == null) {
        String clsName = (String) props_.get(name);
        try {
          rimObject = (RimObject)Class.forName(clsName).newInstance();

        } catch (Exception ex) {
          throw new FactoryException("RIM object " + name + " cannot be created", ex);
        }

        cache_.put(name, rimObject);
      }

			try {
				return (RimObject)rimObject.clone();
			} catch(CloneNotSupportedException ex) {
				throw new FactoryException(ex);
			}
    }
  }

    /**
     * @see RimObjectFactory#canCreate(String)
     * 
     * @author jmoore
     */
    public boolean canCreate(String relativeClassName)
    {    
        boolean canCreate = cache_.containsKey(relativeClassName);
        canCreate |= props_.containsKey(relativeClassName);
        return canCreate;
    }
    
    /**
     * @see RimObjectFactory#rimObjectClass(String[])
     * 
     * @author jmoore
     */
    public Class<? extends RimObject> rimObjectClass(String... prioritizedRelativeClassNames) throws FactoryException
    {
        try
        {
            String relativeClassName = mostSpecializedClassName(prioritizedRelativeClassNames);
            String className = (String) props_.get(relativeClassName);
            Class<? extends RimObject> rimClass = (Class<? extends RimObject>) Class.forName(className);
            return rimClass;
        }
        catch (ClassNotFoundException e)
        {
            throw new FactoryException(e);
        }
    }

    /**
     * NOTE: This is a fairly expensive toString() method.  Use it with care in
     * Logging statements.
     * 
     * @author jmoore
     */    
    public String toString()
    {
        SortedSet<String> keys = new TreeSet<String>();
        for (Object key : props_.keySet())
        {
            keys.add(key.toString());
        }
        
        StringBuffer mappings = new StringBuffer();
        for (String key : keys)
        {
            Object value = props_.get(key);
            if (mappings.length() > 0) mappings.append('\n');            
            mappings.append(key).append('=').append(value);
        }
        return mappings.toString();
    }
}

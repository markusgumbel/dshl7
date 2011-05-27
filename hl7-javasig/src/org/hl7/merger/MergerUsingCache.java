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

import java.util.Map;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hl7.hibernate.Persistence;
import org.hl7.rim.RimObject;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Locator;

/** A common baseclass with a single static cache, where mergers can find objects. */
public abstract class MergerUsingCache<C extends RimObject, T> implements  Merger<C, T> {
  protected static final Logger LOGGER = Logger.getLogger("org.hl7.merger");

  // XXX: with this static, it's always one global cache per thread
  private static final ThreadLocal<Map<Object, RimObject>> _objectCache = new ThreadLocal<Map<Object, RimObject>>();
  private static final ThreadLocal<Map<String,Query>> _queryCache = new ThreadLocal<Map<String,Query>>();

  protected final ApplicationContext _applicationContext;
  protected ApplicationContext getApplicationContext() { return _applicationContext; }

  protected final boolean _useHibernate;
  protected boolean useHibernate() { return _useHibernate; }

  protected MergerUsingCache(ApplicationContext applicationContext) {
    _applicationContext = applicationContext;
    _useHibernate = Boolean.parseBoolean(applicationContext.getSetting("org.hl7.merger.useHibernate", "false"));
  }
	
  /** An easy way of adding location information to error or log messages.
      @param message the message text
      @return the message text with location information added to it
  */
  static String addLoc(String message, Locator loc) {
    String sysId = loc.getSystemId();
    String pubId = loc.getPublicId();		
    return message + " at " + (pubId != null ? pubId + ":" : "") + (sysId != null ? sysId + ":" : "") + loc.getLineNumber() + ":" + loc.getColumnNumber();
  }

  private static Map<Object, RimObject> getObjectCache() {
    if(_objectCache.get() == null)
      _objectCache.set(new java.util.HashMap<Object, RimObject>());
    return _objectCache.get();
  }

  private static Map<String, Query> getQueryCache() {
    if(_queryCache.get() == null)
      _queryCache.set(new java.util.HashMap<String, Query>());
    return _queryCache.get();
  }
	
  public static void clearObjectCache() {
    getObjectCache().clear();
  }

  public static void clearQueryCache() {
    getQueryCache().clear();
  }

  protected C findObjectInCache(Object key) {
    LOGGER.finest("LOOKING FOR ID IN CACHE: " + key);
    C cachedObject =  (C)getObjectCache().get(key);
    if(cachedObject != null) {
      LOGGER.finest("OBJECT IN CACHE: " + key + " --> " + cachedObject.toString());
      //cachedObject = Persistence.instance().assimilate(cachedObject);
    }
    return cachedObject;
  }
	
  protected void putObjectInCache(Object key, C object) {
    LOGGER.finest("CACHING: " + key + " --> " + object);
    getObjectCache().put(key, object);
  }

  protected Query getQuery(String queryName) {
    Query query = getQueryCache().get(queryName);
    if (query == null) {
      query = _applicationContext.getPersistence().createNamedQuery(queryName).setReadOnly(false);
      getQueryCache().put(queryName, query);
    }
    return query;
  }

  public C finish(C object, Locator loc) { 
    return object;
  }
}

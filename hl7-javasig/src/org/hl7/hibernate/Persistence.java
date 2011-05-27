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
package org.hl7.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import org.hibernate.Criteria;
import org.hibernate.EmptyInterceptor;
import org.hibernate.FlushMode;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jmx.StatisticsService;
import org.hl7.merger.MergerUsingCache;

/** Simple interface to the persistence mechanism. 
    Used to have the thread-local Session singleton and 
    thread-global SessionFactory singleton.
    However the thread-local session has been given up now because that is causing problems in intentionally multi-threaded applications.
    For HL7 applications it makes sense to use special Acts as computational units, which own a Hibernate session.
    Such Acts could be scheduled to run in different Threads and in this scenario ThreadLocal would be fatal.

    TODO: more work is needed for the web-server to make sure it uses a Hibernate session exclusively for itself 
    (consider the Persistence.spawn method).
*/
public class Persistence {
  static final Logger LOGGER = Logger.getLogger("org.hl7.hibernate");

  private static Configuration _configuration = null;
  private static SessionFactory _sessionFactory = null;
  private static Persistence _instance = null;

  private Session _session = null;

  private Connection _connection = null;
  private String _url = null;
  private String _username = null;
  private String _password = null;
  private boolean _readOnlyQuery = true;
  private Interceptor _traceInterceptor = new EmptyInterceptor() {
      public void onCollectionRecreate(Object collection, Serializable key) {
	LOGGER.warning("adding collection with key " + key + " to session, " + Integer.toHexString(System.identityHashCode(_session)) + " collection is " + collection);
      }
    };

  public static Persistence instance() { 
    if(_instance == null) {
      synchronized(Persistence.class) {
	// check again inside synchronized
	if(_instance == null) {
	  _instance = new Persistence();
	}
      }
    }
    return _instance; 
  }

  public static Configuration getConfiguration() {
    if(_configuration == null) {
      synchronized (Persistence.class) {
	// check again inside synchronized
	if(_configuration == null) {
	  _configuration = new Configuration()
	    .setNamingStrategy(ReservedWordAvoidanceNamingStrategy.instance())
	    //.setInterceptor( null )
	    .configure();
	}
      }
    }
    return _configuration;
  }

  public static SessionFactory getSessionFactory() {
    if(_sessionFactory == null) {
      synchronized(Persistence.class) {
	// check again inside synchronized
	if(_sessionFactory == null) {
	  _sessionFactory = getConfiguration().buildSessionFactory();         

	  if(System.getProperty("org.hl7.hibernate.jmxstats") != null) {
	    try {
	      MBeanServer server = (MBeanServer)MBeanServerFactory.findMBeanServer(null).get(0);
	      StatisticsService mBean = new StatisticsService();
	      mBean.setSessionFactory(_sessionFactory);
	      server.registerMBean(mBean, new ObjectName("Hibernate:type=statistics,application=mw"));
	    } catch(MalformedObjectNameException ex) {
	      LOGGER.warning("statistics mbean could not be started: " + ex);
	    } catch(InstanceAlreadyExistsException ex) {
	      LOGGER.warning("statistics mbean could not be started: " + ex);
	    } catch(MBeanRegistrationException ex) {
	      LOGGER.warning("statistics mbean could not be started: " + ex);
	    } catch(NotCompliantMBeanException ex) {
	      LOGGER.warning("statistics mbean could not be started: " + ex);
	    }
	  }
	}
      }
    }
    return _sessionFactory;
  }

  // Constructors
  protected Persistence() {
    getSessionFactory(); // initialize
  }
    
  private Persistence(String username, String password) {
    if(username != null) {
      _username = username;
      _password = password;
    }   // FIXME activate this: else {
    _username = getConfiguration().getProperty("hibernate.connection.username");
    _password = getConfiguration().getProperty("hibernate.connection.password");
    // }
  }

  public Session getSession() {
    if(_session == null) {
      _session = openSession();
      Transaction tx = _session.beginTransaction();
      Transaction tx2 = _session.getTransaction();
      if(tx != tx2)
	throw new Error("assertion failed about " + tx + " == " + tx2);		
    }
    // only flush on commits
    _session.setFlushMode(FlushMode.COMMIT);
    return _session;
  }

  private Session openSession() {
    _connection = newConnection();
    return getSessionFactory().openSession(_connection /*, _traceInterceptor */);
  }
  
  private Connection newConnection() {
    _username = getConfiguration().getProperty("hibernate.connection.username");
    _password = getConfiguration().getProperty("hibernate.connection.password");
    if(_url == null) 
      _url = getConfiguration().getProperty("hibernate.connection.url");
	    		
    try{
      return ConnectionPool.getConnection(_url, _username, _password);
    } catch(SQLException e) {
      throw new RuntimeException(e);
    }
  }
  

  public Object load(Class clazz, java.io.Serializable id) {
    return getSession().load(clazz, id);
  }

  public void delete(Object obj) {
    getSession().delete(obj);
  }

  public boolean isPersistent(Object object) {
    return getSession().contains(object);
  }

  public Query createHQLQuery(String hql) {
    return getSession().createQuery(hql).setReadOnly(_readOnlyQuery);
  }

  /**
   * Returns an SQL query from a Hibernate Session. <b>Warning:</b> These queries are <b>NOT</b> read-only
   * because by Hibernate rules 
   * @param sql the sql query
   * @return the Hibernate SQLQuery
   */
  public SQLQuery createSQLQuery(String sql) {
    return getSession().createSQLQuery(sql);
  }

  public Query createNamedQuery(String query) {
    return getSession().getNamedQuery(query).setReadOnly(_readOnlyQuery);
  }

  public Query createFilter(Collection collection, String hql) {
    return getSession().createFilter(collection, hql).setReadOnly(_readOnlyQuery);
  }

  public Criteria createCriteria(Class criteriaClass) {
    return getSession().createCriteria(criteriaClass);
  }

  public void save(Object object) {
    Session session = getSession();
    LOGGER.finest("saving " + object + " through " + this + " in " + session);
    session.saveOrUpdate(object);
  }

  public void commit() {
    Session session = getSession();
    session.getTransaction().commit();
    MergerUsingCache.clearObjectCache();
    MergerUsingCache.clearQueryCache();

    Transaction tx = session.beginTransaction();
    Transaction tx2 = session.getTransaction();

    if(tx != tx2){
      throw new Error("assertion failed about " + tx + " == " + tx2);		
    }
  }

  public void close() {
    Session session = getSession();
    session.disconnect();
    session.close();
    _session = null;
    
    if(_connection != null) {
      returnConnection(_connection);
      _connection = null;
    }
		
    MergerUsingCache.clearObjectCache();
    MergerUsingCache.clearQueryCache();
  }

  private void returnConnection(Connection connection) {
    ConnectionPool.returnConnection(_url, _username, connection);
  }


  public void close(boolean logout) {
    if(logout)
      logout();
    else
      close();			
  }

  // FIXME: this should be done by counting references?
  public void logout() {
    close();
    ConnectionPool.closeAllConnections(_url, _username);
  }

  public Persistence spawn(String username, String password) {
    return new Persistence(username, password);
  }

  public Persistence spawn() {
    return spawn(_username, _password);
  }

  public static enum AssimilationMode {
    READ_ONLY, READ_WRITE;
  }

  public void setReadOnly(Object object, boolean readOnly) {
    if (getSession().contains(object))
      getSession().setReadOnly(object, readOnly);
    else if (readOnly)
      LOGGER.warning("Object is not in session.  May not be read-only: " + object);
  }

  public <T extends Hibernatable> T assimilate(T object, AssimilationMode assimilationMode) {
    T assimilatedObject; 

    if (getSession().contains(object))
      assimilatedObject = object;	
    else {
      assimilatedObject = (T) getSession().get(object.getClass(), object.getInternalId());
      assert assimilatedObject != null; // we know it never comes out of here as null
    }
		
    switch (assimilationMode) {
    case READ_ONLY:
      getSession().setReadOnly(assimilatedObject, true);
      break;
    case READ_WRITE:
      getSession().setReadOnly(assimilatedObject, false);
      break;
    }
		
    return assimilatedObject;
  }

  public void rollback() {
    Session session = getSession();
    session.getTransaction().rollback();
    Transaction tx = session.beginTransaction();
    Transaction tx2 = session.getTransaction();
    if(tx != tx2)
      throw new Error("assertion failed about " + tx + " == " + tx2);		
  }
} 

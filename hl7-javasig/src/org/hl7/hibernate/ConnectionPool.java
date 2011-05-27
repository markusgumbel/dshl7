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
 * Portions created by Initial Developer are Copyright (C) 2002-2006
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/** */
public class ConnectionPool {
	static final Logger LOGGER = Logger.getLogger("org.hl7.hibernate");

	private static class ConnectionDescriptor {
		private String _url;
		private String _username;

		ConnectionDescriptor(String url, String username) {
			_url = url;
			_username = username;
		}

		public String getUrl() { return _url; }
		public String getUsername() { return _username; }
		
		public int hashCode() {
			return _url.hashCode() * 17 + _username.hashCode();
		}
		
		public boolean equals(Object obj) {
			if(obj instanceof ConnectionDescriptor) {
				ConnectionDescriptor that = (ConnectionDescriptor)obj;
				return this._url.equals(that._url) && this._username.equals(that._username);
			} else 
				return false;
		}

		public String toString() {
			return _username + "@" + _url;
		}
	}

	private static class ConnectionDescriptorThatCanMakeConnections extends ConnectionDescriptor {
		private String _password;

		ConnectionDescriptorThatCanMakeConnections(String url, String username, String password) {
			super(url,username);
			_password = password;
		}

		public Connection getConnection() throws SQLException {
		  Connection con = DriverManager.getConnection(getUrl(), getUsername(), _password);
		  con.setAutoCommit(false);
		  return con;
		}
	}

	private static class ConnectionManager {

		private ConnectionDescriptorThatCanMakeConnections _connectionDescriptor;
		private List<Connection> _availableConnections  = new LinkedList<Connection>();
		private int _howManyToGet = 1;
		private SQLException _sqlException = null;
		private Map<Connection, Long> _creationTimes = new IdentityHashMap<Connection, Long>();
		private static final long EXPIRATION_PERIOD = 5*60*1000;
		
		public ConnectionManager(ConnectionDescriptorThatCanMakeConnections connectionDescriptor) {
			_connectionDescriptor = connectionDescriptor;
		}
		
		public Connection checkOut() throws SQLException {
			synchronized(_availableConnections) {
				while(_sqlException == null) {
					if(!_availableConnections.isEmpty()) {
						Connection connection = _availableConnections.remove(0);
						LOGGER.info("connection checked out: " + connection + " " + _connectionDescriptor);
						if(_availableConnections.isEmpty()) // if we took the last, be polite and order some more
							makeConnections();

						return connection;
					}					
					waitForConnection();
				}
				SQLException ex = _sqlException;
				_sqlException = null;
				throw ex;
			}
		}

		public void checkIn(Connection connection) {
			LOGGER.info("connection checked in: " + connection + " " + _connectionDescriptor);
			try {
				connection.rollback();

				// we check if the connection is older than 5 min and do not recycle if that's the case.
				// why? because the connections grow stale, big (if the JDBC driver has any memory leaks)
				// and we are safer with fresh connections. The caching of pre-made connections is mostly
				// relevant in high-turnover situations, and our algorithm makes sure there are always 
				// new connections ready for the next guy to pick up.

				long currentTime = System.currentTimeMillis();

				synchronized(_availableConnections) {
				  try {

				    Long creationTime = _creationTimes.get(connection);
				    if(creationTime == null) {
				      creationTime = currentTime;
				      _creationTimes.put(connection, creationTime);
				    }
				    if(currentTime - creationTime < EXPIRATION_PERIOD) {
				      _availableConnections.add(connection);
				      connection = null; // done, no more touching this one
				    } else
				      _creationTimes.remove(connection);

				  } finally {
				    _availableConnections.notify();
				  }
				}

			} catch(SQLException ex) {
			  // FALLTHROUGH
				LOGGER.severe(ex.getMessage());
				ex.printStackTrace();
			} finally {
			  if(connection != null) { // if we should be closing it it is still here
			    LOGGER.info("discarding connection: " + connection + " " + _connectionDescriptor);
			    try { connection.close(); } catch(SQLException ex2) { }
			  }
			}
		}

		private Thread _connectionMaker = null;

		private synchronized void makeConnections() throws SQLException {
			if(_connectionMaker == null) {
				_connectionMaker = new Thread(new Runnable() {
						public void run() {
							try {
								for(int i = 0; i < _howManyToGet; i++) {
									checkIn(_connectionDescriptor.getConnection());
									LOGGER.info("made connection: " + _connectionDescriptor);
								}
							} catch(SQLException ex) {
							  LOGGER.severe(ex.getMessage());
							  ex.printStackTrace();
							  _sqlException = ex;
							} finally {
								_connectionMaker = null;
							}
						}
					}, "ConnectionMaker");
				_connectionMaker.start();
			}
		}
		
		private void waitForConnection() throws SQLException {
			makeConnections();
			try { _availableConnections.wait(); } catch(InterruptedException ie) { }
		}

		public void closeAll() {
			synchronized(_availableConnections) {
				for(Connection connection : _availableConnections)
					try {
						connection.rollback();
						connection.close();	
					} catch(SQLException ex) {
						try {	connection.close();	} catch(SQLException ex2) { }
					}
				_availableConnections.clear();
			}
		}
	}

	private static final Map<ConnectionDescriptor,ConnectionManager> _map = new HashMap<ConnectionDescriptor,ConnectionManager>();

	public static Connection getConnection(String url, String username, String password) throws SQLException {
		return checkOut(new ConnectionDescriptorThatCanMakeConnections(url, username, password));
	}

	public static void returnConnection(String url, String username, Connection connection) {
		checkIn(new ConnectionDescriptor(url, username), connection);
	}

	public static void closeAllConnections(String url, String username) {
		closeAll(new ConnectionDescriptor(url, username));
	}

	private static Connection checkOut(ConnectionDescriptorThatCanMakeConnections connectionDescriptor) throws SQLException {
		ConnectionManager connectionManager = null;
		synchronized(_map) {
			connectionManager = _map.get(connectionDescriptor);
			if(connectionManager == null) {
				connectionManager = new ConnectionManager(connectionDescriptor);
				_map.put(connectionDescriptor, connectionManager);
			}
		}
		return connectionManager.checkOut();
	}

	private static void checkIn(ConnectionDescriptor connectionDescriptor, Connection connection) {
		ConnectionManager connectionManager = _map.get(connectionDescriptor);
		if(connectionManager != null)
			connectionManager.checkIn(connection);
		else 
			throw new IllegalArgumentException("unknown connection descriptor " + connectionDescriptor);
	}

	private static void closeAll(ConnectionDescriptor connectionDescriptor) {
		ConnectionManager connectionManager = _map.get(connectionDescriptor);
		if(connectionManager != null)
			connectionManager.closeAll();
		else 
			throw new IllegalArgumentException("unknown connection descriptor " + connectionDescriptor);
	}
} 




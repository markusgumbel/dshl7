package org.hl7.hibernate;

import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.cfg.Environment;

public class PooledConnectionProvider implements ConnectionProvider {

  public void close() { }
  
  public void closeConnection(Connection conn) {
    ConnectionPool.returnConnection(_url, _username, conn);
  }
  
  Properties _properties;
  
  public void configure(Properties props) {  
    try {
      _properties = props;
      Class.forName(props.getProperty(Environment.DRIVER));
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }
  
  String _username;
  String _password;
  String _url;
  
  public Connection getConnection() {
    if(_url == null) {
      _url = _properties.getProperty(Environment.URL);
      _username = _properties.getProperty(Environment.USER);
      _password = _properties.getProperty(Environment.PASS);
    }
    
    try{      
      return ConnectionPool.getConnection(_url, _username, _password);
    } catch(SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  public boolean supportsAggressiveRelease() { return true;  }
}

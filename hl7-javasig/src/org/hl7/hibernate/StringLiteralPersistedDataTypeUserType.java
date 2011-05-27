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


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.hl7.meta.Datatype;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.ANY;
import org.hl7.types.ValueFactory;
import org.hl7.types.ValueFactoryException;
public class StringLiteralPersistedDataTypeUserType extends AbstractDataTypeUserType implements UserType, ParameterizedType {

   private static final Logger LOGGER = Logger.getLogger("org.hl7.hibernate");
  private Class _interface = null;
  private Datatype _datatype = null;
  
  /** Gets called by Hibernate to pass the configured type parameters to the implementation. */
  public void setParameterValues(Properties parameters) {    
    try {
	_datatype = DatatypeMetadataFactoryImpl.instance().create((String)parameters.get("type"));
	_interface = Class.forName("org.hl7.types." +_datatype.getFullName());
    } catch(ClassNotFoundException x) {
	throw new Error("data type interface not found", x);
    } catch(UnknownDatatypeException x) {
	throw new Error(x);
    }
  }

  /** The class returned by nullSafeGet(). */
  public Class returnedClass() {
    return _interface;
  }

  /** Return the SQL type codes for the columns mapped by this type. */
  public int[] sqlTypes() { return SQL_TYPES; }

  private static final int[] SQL_TYPES = new int[] { Hibernate.STRING.sqlType() };

  /** Retrieve an instance of the mapped class from a JDBC resultset. */
  public Object nullSafeGet(ResultSet rs, String names[], Object owner) throws HibernateException {
    try {
      String literal = rs.getString(names[0]);
      if(!rs.wasNull()) {
	return ValueFactory.getInstance().valueOfLiteral(_datatype.getFullName(), literal);
      } else {
	if(_datatype instanceof ParametrizedDatatype)
	  return ValueFactory.getInstance().nullValueOf(((ParametrizedDatatype)_datatype).getType(),"NI");
	else
	  return ValueFactory.getInstance().nullValueOf(_datatype.getFullName(),"NI");
      }
    } catch(SQLException ex) {
      throw new HibernateException(ex);
    } catch(ValueFactoryException ex) {
      throw new HibernateException(ex);
    } catch(IllegalArgumentException ex) {
      throw new HibernateException(ex);
    }
  }

  /** Write an instance of the mapped class to a prepared statement. */
  public void nullSafeSet(PreparedStatement st, Object rawValue, int index) throws HibernateException {
    try {
      if(rawValue != null) {

	if (rawValue instanceof String) { 
	  Hibernate.STRING.nullSafeSet(st,(String)rawValue,index);
	  return;
	}

	final ANY value = (ANY)rawValue;
	if(value.nonNull().isTrue()) {
	  Hibernate.STRING.nullSafeSet(st,value.toString(),index);
	  return;
	}
      }

      // don't ever forget to set this to null explicitly or 
      // else the value from the previous insert might be used!
      Hibernate.STRING.nullSafeSet(st, null, index);	
      
    } catch(SQLException ex) {
      throw new HibernateException(ex);
    }
  }
}

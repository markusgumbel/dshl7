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

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.hl7.types.ANY;

/** An abstract Hibernate UserType that minimally implements the UserType interface appropriate 
    for all HL7 data types.

    @author Gunther Schadow
    @version $Id: AbstractDataTypeUserType.java 5652 2007-03-30 15:35:44Z crosenthal $
 */
public abstract class AbstractDataTypeUserType implements UserType {
          
  /** The class returned by nullSafeGet(). */
  public abstract Class returnedClass();

  /** Return the SQL type codes for the columns mapped by this type. */
  public abstract int[] sqlTypes();

  /** Retrieve an instance of the mapped class from a JDBC resultset. */
  public abstract Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException;

  /** Write an instance of the mapped class to a prepared statement. */
  public abstract void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException;

  /** Reconstruct an object from the cacheable representation,
      
      @deprecated this operation is currently not supported
   */
	public Object assemble(Serializable cached, Object owner) {
		return deepCopy(cached);
	}

  /** Transform the object into its cacheable representation. 

      @deprecated this operation is currently not supported
   */
	public Serializable disassemble(Object value) {
		return (Serializable) deepCopy(value);
	}

  /** Return a deep copy of the persistent state, stopping at entities and at collections. */
  public Object deepCopy(Object value) throws HibernateException { 
		return value; 
	}

  /** Compare two instances of the class mapped by this type for persistence "equality". */
  public boolean equals(Object x, Object y) throws HibernateException {
		Persistence.LOGGER.finest("EQUALS-1 " + x + " == " + y);
    if(x == y)
			return true;
		Persistence.LOGGER.finest("EQUALS-2 == null " + (x == y));
		if(x == null)
			return x==y;
		Persistence.LOGGER.finest("EQUALS-3 ANY eq ?" + (!((ANY)x).equal((ANY)y).isFalse()));
		if(x instanceof ANY && y instanceof ANY)
			return !((ANY)x).equal((ANY)y).isFalse();
		Persistence.LOGGER.finest("EQUALS-4 DEFAULT");
		return x.equals(y);
  }

  /** Get a hashcode for the instance, consistent with persistence "equality". */
  public int hashCode(Object x) throws HibernateException  {
    // all HL7 data types should have this method
    return x.hashCode();
  }
          
  /** Are objects of this type mutable? No never! */
  public boolean isMutable() { return false; }

  /** What does this do? 
      @deprecated not implemented yet
   */
  public Object replace(Object original, Object target, Object owner) throws HibernateException { 
    return original;
  }
}


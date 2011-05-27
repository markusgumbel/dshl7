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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hl7.types.ANY;

/** An abstract Hibernate CompositeUserType that minimally implements the CompositeUserType 
    interface appropriate for many HL7 data types.

    @author Gunther Schadow
    @version $Id: AbstractDataTypeCompositeUserType.java 5652 2007-03-30 15:35:44Z crosenthal $
 */
public abstract class AbstractDataTypeCompositeUserType implements CompositeUserType {
          
  /** The class returned by nullSafeGet(). */
  public abstract Class returnedClass();

  /** Retrieve an instance of the mapped class from a JDBC resultset. */
  public abstract Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException;

  /** Write an instance of the mapped class to a prepared statement. */
  public abstract void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException;

  /** Reconstruct an object from the cacheable representation, UNSUPPORTED. 
      
      @deprecated this operation is currently not supported
   */
	public Object assemble(Serializable cached, SessionImplementor session,    Object owner) {
		return deepCopy(cached);
	}

  /** Transform the object into its cacheable representation. 

      @deprecated this operation is currently not supported
   */
	public Serializable disassemble(Object value, SessionImplementor session) {
		return (Serializable) deepCopy(value);
	}

  /** Return a deep copy of the persistent state, stopping at entities and at collections. */
  public Object deepCopy(Object value) throws HibernateException { 
		return value; 
	}

  /** Compare two instances of the class mapped by this type for persistence "equality". 
			This is used mainly for dirty-checking, hence what really matters is not whether they are 
			in fact equal, but whether the database representation has changed.
	*/
  public boolean equals(Object x, Object y) throws HibernateException {
    if(x == y)
			return true;
		else if(x == null)
			return x==y;
		else if(x instanceof ANY && y instanceof ANY)
			return !((ANY)x).equal((ANY)y).isFalse();
		else
			return x.equals(y);
  }

  /** Get a hashcode for the instance, consistent with persistence "equality". */
  public int hashCode(Object x) throws HibernateException  {
    // all HL7 data types should have this method
    return x.hashCode();
  }
          
  /** Are objects of this type mutable? No never! */
  public boolean isMutable() { return false; }

  /* Get the "property names" that may be used in a query. */
  public abstract String[] getPropertyNames();

  /* Get the corresponding "property types". */
  public abstract Type[] getPropertyTypes();

  /* Get the value of a property. */
  public abstract Object getPropertyValue(Object component, int property) throws HibernateException;
  
  /* Set the value of a property. This always throws and ImmutableException. */
  public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
    throw new ImmutableException();
  }

  /** What does this do? (see hibernate ... test ... sql ... MonetaryAmountUserType) */
  public Object replace(Object original, Object target, SessionImplementor session, Object owner) throws HibernateException { 
    return original;
  }

  public static class ImmutableException extends HibernateException {
    ImmutableException() { 
      super("HL7 data types are immutable, there is no setting of properties");
    }
  }
}


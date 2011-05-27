/*
THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

The contents of this file are subject to the Health Level-7 Public
License Version 1.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License
at http://www.hl7.org/HPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
the License for the specific language governing rights and limitations
under the License.

The Original Code is all this file.

The Initial Developer of the Original Code is automatically generated
from HL7 copyrighted standards specification which may be subject to
different license. Portions created by Initial Developer are Copyright
(C) 2002-2004 Health Level Seven, Inc. All Rights Reserved.

THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.
*/
package org.hl7.rim.impl;

import org.hibernate.Criteria;
import org.hl7.hibernate.Persistence;

import org.hl7.rim.Parameter;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.II;

import org.hl7.rim.QueryByParameter;
import org.hl7.rim.ParameterList;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Parameter as a simple data holder bean.
    @see org.hl7.rim.Parameter
  */
public abstract class ParameterImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements Parameter {

  private II _id;
  /** Gets the property id.
      @see org.hl7.rim.Parameter#getId
  */
  public II getId() { return _id; }
  /** Sets the property id.
      @see org.hl7.rim.Parameter#setId
  */
  public void setId(II id) {
    if(id instanceof org.hl7.hibernate.ClonableCollection)
      id = ((org.hl7.hibernate.ClonableCollection<II>) id).cloneHibernateCollectionIfNecessary();
    _id = id;
  }
  /** Sets the property id. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Parameter#setId
  */
  public void setIdForHibernate(II id) {
    _id = id;
  }

  private org.hl7.rim.QueryByParameter _queryByParameter;
  /** Gets the property queryByParameter.
      @see org.hl7.rim.Parameter#getQueryByParameter
  */
  public org.hl7.rim.QueryByParameter getQueryByParameter() {
    return _queryByParameter;
  }
  /** Sets the property queryByParameter.
      @see org.hl7.rim.Parameter#setQueryByParameter
  */
  public void setQueryByParameter(org.hl7.rim.QueryByParameter queryByParameter) {
    _queryByParameter = queryByParameter;
  }

  private org.hl7.rim.ParameterList _parameterList;
  /** Gets the property parameterList.
      @see org.hl7.rim.Parameter#getParameterList
  */
  public org.hl7.rim.ParameterList getParameterList() {
    return _parameterList;
  }
  /** Sets the property parameterList.
      @see org.hl7.rim.Parameter#setParameterList
  */
  public void setParameterList(org.hl7.rim.ParameterList parameterList) {
    _parameterList = parameterList;
  }
  public Object clone() throws CloneNotSupportedException {
    ParameterImpl that = (ParameterImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

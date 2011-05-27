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

import org.hl7.rim.QueryEvent;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.II;
import org.hl7.types.CS;

import org.hl7.rim.ControlAct;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.QueryEvent as a simple data holder bean.
    @see org.hl7.rim.QueryEvent
  */
public class QueryEventImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements QueryEvent {

  private II _queryId;
  /** Gets the property queryId.
      @see org.hl7.rim.QueryEvent#getQueryId
  */
  public II getQueryId() { return _queryId; }
  /** Sets the property queryId.
      @see org.hl7.rim.QueryEvent#setQueryId
  */
  public void setQueryId(II queryId) {
    if(queryId instanceof org.hl7.hibernate.ClonableCollection)
      queryId = ((org.hl7.hibernate.ClonableCollection<II>) queryId).cloneHibernateCollectionIfNecessary();
    _queryId = queryId;
  }
  /** Sets the property queryId. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QueryEvent#setQueryId
  */
  public void setQueryIdForHibernate(II queryId) {
    _queryId = queryId;
  }

  private CS _statusCode;
  /** Gets the property statusCode.
      @see org.hl7.rim.QueryEvent#getStatusCode
  */
  public CS getStatusCode() { return _statusCode; }
  /** Sets the property statusCode.
      @see org.hl7.rim.QueryEvent#setStatusCode
  */
  public void setStatusCode(CS statusCode) {
    if(statusCode instanceof org.hl7.hibernate.ClonableCollection)
      statusCode = ((org.hl7.hibernate.ClonableCollection<CS>) statusCode).cloneHibernateCollectionIfNecessary();
    _statusCode = statusCode;
  }
  /** Sets the property statusCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QueryEvent#setStatusCode
  */
  public void setStatusCodeForHibernate(CS statusCode) {
    _statusCode = statusCode;
  }

  private org.hl7.rim.ControlAct _controlAct;
  /** Gets the property controlAct.
      @see org.hl7.rim.QueryEvent#getControlAct
  */
  public org.hl7.rim.ControlAct getControlAct() {
    return _controlAct;
  }
  /** Sets the property controlAct.
      @see org.hl7.rim.QueryEvent#setControlAct
  */
  public void setControlAct(org.hl7.rim.ControlAct controlAct) {
    _controlAct = controlAct;
  }
  public Object clone() throws CloneNotSupportedException {
    QueryEventImpl that = (QueryEventImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

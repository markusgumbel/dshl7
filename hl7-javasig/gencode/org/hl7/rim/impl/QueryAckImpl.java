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

import org.hl7.rim.QueryAck;
import org.hl7.rim.impl.QueryEventImpl;
import org.hl7.types.CS;
import org.hl7.types.INT;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.QueryAck as a simple data holder bean.
    @see org.hl7.rim.QueryAck
  */
public class QueryAckImpl extends org.hl7.rim.impl.QueryEventImpl implements QueryAck {

  private CS _queryResponseCode;
  /** Gets the property queryResponseCode.
      @see org.hl7.rim.QueryAck#getQueryResponseCode
  */
  public CS getQueryResponseCode() { return _queryResponseCode; }
  /** Sets the property queryResponseCode.
      @see org.hl7.rim.QueryAck#setQueryResponseCode
  */
  public void setQueryResponseCode(CS queryResponseCode) {
    if(queryResponseCode instanceof org.hl7.hibernate.ClonableCollection)
      queryResponseCode = ((org.hl7.hibernate.ClonableCollection<CS>) queryResponseCode).cloneHibernateCollectionIfNecessary();
    _queryResponseCode = queryResponseCode;
  }
  /** Sets the property queryResponseCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QueryAck#setQueryResponseCode
  */
  public void setQueryResponseCodeForHibernate(CS queryResponseCode) {
    _queryResponseCode = queryResponseCode;
  }

  private INT _resultTotalQuantity;
  /** Gets the property resultTotalQuantity.
      @see org.hl7.rim.QueryAck#getResultTotalQuantity
  */
  public INT getResultTotalQuantity() { return _resultTotalQuantity; }
  /** Sets the property resultTotalQuantity.
      @see org.hl7.rim.QueryAck#setResultTotalQuantity
  */
  public void setResultTotalQuantity(INT resultTotalQuantity) {
    if(resultTotalQuantity instanceof org.hl7.hibernate.ClonableCollection)
      resultTotalQuantity = ((org.hl7.hibernate.ClonableCollection<INT>) resultTotalQuantity).cloneHibernateCollectionIfNecessary();
    _resultTotalQuantity = resultTotalQuantity;
  }
  /** Sets the property resultTotalQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QueryAck#setResultTotalQuantity
  */
  public void setResultTotalQuantityForHibernate(INT resultTotalQuantity) {
    _resultTotalQuantity = resultTotalQuantity;
  }

  private INT _resultCurrentQuantity;
  /** Gets the property resultCurrentQuantity.
      @see org.hl7.rim.QueryAck#getResultCurrentQuantity
  */
  public INT getResultCurrentQuantity() { return _resultCurrentQuantity; }
  /** Sets the property resultCurrentQuantity.
      @see org.hl7.rim.QueryAck#setResultCurrentQuantity
  */
  public void setResultCurrentQuantity(INT resultCurrentQuantity) {
    if(resultCurrentQuantity instanceof org.hl7.hibernate.ClonableCollection)
      resultCurrentQuantity = ((org.hl7.hibernate.ClonableCollection<INT>) resultCurrentQuantity).cloneHibernateCollectionIfNecessary();
    _resultCurrentQuantity = resultCurrentQuantity;
  }
  /** Sets the property resultCurrentQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QueryAck#setResultCurrentQuantity
  */
  public void setResultCurrentQuantityForHibernate(INT resultCurrentQuantity) {
    _resultCurrentQuantity = resultCurrentQuantity;
  }

  private INT _resultRemainingQuantity;
  /** Gets the property resultRemainingQuantity.
      @see org.hl7.rim.QueryAck#getResultRemainingQuantity
  */
  public INT getResultRemainingQuantity() { return _resultRemainingQuantity; }
  /** Sets the property resultRemainingQuantity.
      @see org.hl7.rim.QueryAck#setResultRemainingQuantity
  */
  public void setResultRemainingQuantity(INT resultRemainingQuantity) {
    if(resultRemainingQuantity instanceof org.hl7.hibernate.ClonableCollection)
      resultRemainingQuantity = ((org.hl7.hibernate.ClonableCollection<INT>) resultRemainingQuantity).cloneHibernateCollectionIfNecessary();
    _resultRemainingQuantity = resultRemainingQuantity;
  }
  /** Sets the property resultRemainingQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QueryAck#setResultRemainingQuantity
  */
  public void setResultRemainingQuantityForHibernate(INT resultRemainingQuantity) {
    _resultRemainingQuantity = resultRemainingQuantity;
  }
  public Object clone() throws CloneNotSupportedException {
    QueryAckImpl that = (QueryAckImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

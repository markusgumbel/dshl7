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

import org.hl7.rim.ManagedParticipation;
import org.hl7.rim.impl.ParticipationImpl;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.ManagedParticipation as a simple data holder bean.
    @see org.hl7.rim.ManagedParticipation
  */
public class ManagedParticipationImpl extends org.hl7.rim.impl.ParticipationImpl implements ManagedParticipation {

  private SET<II> _id;
  /** Gets the property id.
      @see org.hl7.rim.ManagedParticipation#getId
  */
  public SET<II> getId() { return _id; }
  /** Sets the property id.
      @see org.hl7.rim.ManagedParticipation#setId
  */
  public void setId(SET<II> id) {
    if(id instanceof org.hl7.hibernate.ClonableCollection)
      id = ((org.hl7.hibernate.ClonableCollection<SET<II>>) id).cloneHibernateCollectionIfNecessary();
    _id = id;
  }
  /** Sets the property id. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ManagedParticipation#setId
  */
  public void setIdForHibernate(SET<II> id) {
    _id = id;
  }

  private CS _statusCode;
  /** Gets the property statusCode.
      @see org.hl7.rim.ManagedParticipation#getStatusCode
  */
  public CS getStatusCode() { return _statusCode; }
  /** Sets the property statusCode.
      @see org.hl7.rim.ManagedParticipation#setStatusCode
  */
  public void setStatusCode(CS statusCode) {
    if(statusCode instanceof org.hl7.hibernate.ClonableCollection)
      statusCode = ((org.hl7.hibernate.ClonableCollection<CS>) statusCode).cloneHibernateCollectionIfNecessary();
    _statusCode = statusCode;
  }
  /** Sets the property statusCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ManagedParticipation#setStatusCode
  */
  public void setStatusCodeForHibernate(CS statusCode) {
    _statusCode = statusCode;
  }
  public Object clone() throws CloneNotSupportedException {
    ManagedParticipationImpl that = (ManagedParticipationImpl) super.clone();

    // deep clone of persistent component collections
    that.setId(that.getId());
    return that;
  }
}

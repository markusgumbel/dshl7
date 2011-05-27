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

import org.hl7.rim.CommunicationFunction;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.CS;
import org.hl7.types.TEL;

import org.hl7.rim.Entity;
import org.hl7.rim.Transmission;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.CommunicationFunction as a simple data holder bean.
    @see org.hl7.rim.CommunicationFunction
  */
public class CommunicationFunctionImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements CommunicationFunction {

  private CS _typeCode;
  /** Gets the property typeCode.
      @see org.hl7.rim.CommunicationFunction#getTypeCode
  */
  public CS getTypeCode() { return _typeCode; }
  /** Sets the property typeCode.
      @see org.hl7.rim.CommunicationFunction#setTypeCode
  */
  public void setTypeCode(CS typeCode) {
    if(typeCode instanceof org.hl7.hibernate.ClonableCollection)
      typeCode = ((org.hl7.hibernate.ClonableCollection<CS>) typeCode).cloneHibernateCollectionIfNecessary();
    _typeCode = typeCode;
  }
  /** Sets the property typeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.CommunicationFunction#setTypeCode
  */
  public void setTypeCodeForHibernate(CS typeCode) {
    _typeCode = typeCode;
  }

  private TEL _telecom;
  /** Gets the property telecom.
      @see org.hl7.rim.CommunicationFunction#getTelecom
  */
  public TEL getTelecom() { return _telecom; }
  /** Sets the property telecom.
      @see org.hl7.rim.CommunicationFunction#setTelecom
  */
  public void setTelecom(TEL telecom) {
    if(telecom instanceof org.hl7.hibernate.ClonableCollection)
      telecom = ((org.hl7.hibernate.ClonableCollection<TEL>) telecom).cloneHibernateCollectionIfNecessary();
    _telecom = telecom;
  }
  /** Sets the property telecom. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.CommunicationFunction#setTelecom
  */
  public void setTelecomForHibernate(TEL telecom) {
    _telecom = telecom;
  }

  private /*AssociationSet*/List<org.hl7.rim.Entity> _entity;
  /** Gets the property entity.
      @see org.hl7.rim.CommunicationFunction#getEntity
  */
  public /*AssociationSet*/List<org.hl7.rim.Entity> getEntity() {
    return _entity;
  }
  /** Sets the property entity.
      @see org.hl7.rim.CommunicationFunction#setEntity
  */
  public void setEntity(/*AssociationSet*/List<org.hl7.rim.Entity> entity) {
    _entity = entity;
  }
  /** Adds an association entity.
      @see org.hl7.rim.CommunicationFunction#setEntity
  */
  public void addEntity(org.hl7.rim.Entity entity) {
        // create the association set if it doesn't exist already
    if(_entity == null) _entity = new AssociationSetImpl<org.hl7.rim.Entity>();
    // add the association to the association set
    getEntity().add(entity);
  }

  private /*AssociationSet*/List<org.hl7.rim.Transmission> _transmission;
  /** Gets the property transmission.
      @see org.hl7.rim.CommunicationFunction#getTransmission
  */
  public /*AssociationSet*/List<org.hl7.rim.Transmission> getTransmission() {
    return _transmission;
  }
  /** Sets the property transmission.
      @see org.hl7.rim.CommunicationFunction#setTransmission
  */
  public void setTransmission(/*AssociationSet*/List<org.hl7.rim.Transmission> transmission) {
    _transmission = transmission;
  }
  /** Adds an association transmission.
      @see org.hl7.rim.CommunicationFunction#setTransmission
  */
  public void addTransmission(org.hl7.rim.Transmission transmission) {
        // create the association set if it doesn't exist already
    if(_transmission == null) _transmission = new AssociationSetImpl<org.hl7.rim.Transmission>();
    // add the association to the association set
    getTransmission().add(transmission);
  }
  public Object clone() throws CloneNotSupportedException {
    CommunicationFunctionImpl that = (CommunicationFunctionImpl) super.clone();

    // deep clone of persistent component collections
    that._entity= null;
    that._transmission= null;
    return that;
  }
}

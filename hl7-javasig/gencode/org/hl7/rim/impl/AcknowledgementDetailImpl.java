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

import org.hl7.rim.AcknowledgementDetail;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.CS;
import org.hl7.types.CE;
import org.hl7.types.ED;
import org.hl7.types.SET;
import org.hl7.types.ST;

import org.hl7.rim.Acknowledgement;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.AcknowledgementDetail as a simple data holder bean.
    @see org.hl7.rim.AcknowledgementDetail
  */
public class AcknowledgementDetailImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements AcknowledgementDetail {

  private CS _typeCode;
  /** Gets the property typeCode.
      @see org.hl7.rim.AcknowledgementDetail#getTypeCode
  */
  public CS getTypeCode() { return _typeCode; }
  /** Sets the property typeCode.
      @see org.hl7.rim.AcknowledgementDetail#setTypeCode
  */
  public void setTypeCode(CS typeCode) {
    if(typeCode instanceof org.hl7.hibernate.ClonableCollection)
      typeCode = ((org.hl7.hibernate.ClonableCollection<CS>) typeCode).cloneHibernateCollectionIfNecessary();
    _typeCode = typeCode;
  }
  /** Sets the property typeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.AcknowledgementDetail#setTypeCode
  */
  public void setTypeCodeForHibernate(CS typeCode) {
    _typeCode = typeCode;
  }

  private CE _code;
  /** Gets the property code.
      @see org.hl7.rim.AcknowledgementDetail#getCode
  */
  public CE getCode() { return _code; }
  /** Sets the property code.
      @see org.hl7.rim.AcknowledgementDetail#setCode
  */
  public void setCode(CE code) {
    if(code instanceof org.hl7.hibernate.ClonableCollection)
      code = ((org.hl7.hibernate.ClonableCollection<CE>) code).cloneHibernateCollectionIfNecessary();
    _code = code;
  }
  /** Sets the property code. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.AcknowledgementDetail#setCode
  */
  public void setCodeForHibernate(CE code) {
    _code = code;
  }

  private ED _text;
  /** Gets the property text.
      @see org.hl7.rim.AcknowledgementDetail#getText
  */
  public ED getText() { return _text; }
  /** Sets the property text.
      @see org.hl7.rim.AcknowledgementDetail#setText
  */
  public void setText(ED text) {
    if(text instanceof org.hl7.hibernate.ClonableCollection)
      text = ((org.hl7.hibernate.ClonableCollection<ED>) text).cloneHibernateCollectionIfNecessary();
    _text = text;
  }
  /** Sets the property text. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.AcknowledgementDetail#setText
  */
  public void setTextForHibernate(ED text) {
    _text = text;
  }

  private SET<ST> _location;
  /** Gets the property location.
      @see org.hl7.rim.AcknowledgementDetail#getLocation
  */
  public SET<ST> getLocation() { return _location; }
  /** Sets the property location.
      @see org.hl7.rim.AcknowledgementDetail#setLocation
  */
  public void setLocation(SET<ST> location) {
    if(location instanceof org.hl7.hibernate.ClonableCollection)
      location = ((org.hl7.hibernate.ClonableCollection<SET<ST>>) location).cloneHibernateCollectionIfNecessary();
    _location = location;
  }
  /** Sets the property location. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.AcknowledgementDetail#setLocation
  */
  public void setLocationForHibernate(SET<ST> location) {
    _location = location;
  }

  private org.hl7.rim.Acknowledgement _acknowledgement;
  /** Gets the property acknowledgement.
      @see org.hl7.rim.AcknowledgementDetail#getAcknowledgement
  */
  public org.hl7.rim.Acknowledgement getAcknowledgement() {
    return _acknowledgement;
  }
  /** Sets the property acknowledgement.
      @see org.hl7.rim.AcknowledgementDetail#setAcknowledgement
  */
  public void setAcknowledgement(org.hl7.rim.Acknowledgement acknowledgement) {
    _acknowledgement = acknowledgement;
  }
  public Object clone() throws CloneNotSupportedException {
    AcknowledgementDetailImpl that = (AcknowledgementDetailImpl) super.clone();

    // deep clone of persistent component collections
    that.setLocation(that.getLocation());
    return that;
  }
}

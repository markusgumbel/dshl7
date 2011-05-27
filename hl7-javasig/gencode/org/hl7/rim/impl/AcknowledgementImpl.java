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

import org.hl7.rim.Acknowledgement;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.CE;

import org.hl7.rim.AcknowledgementDetail;
import org.hl7.rim.Transmission;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Acknowledgement as a simple data holder bean.
    @see org.hl7.rim.Acknowledgement
  */
public class AcknowledgementImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements Acknowledgement {

  private CS _typeCode;
  /** Gets the property typeCode.
      @see org.hl7.rim.Acknowledgement#getTypeCode
  */
  public CS getTypeCode() { return _typeCode; }
  /** Sets the property typeCode.
      @see org.hl7.rim.Acknowledgement#setTypeCode
  */
  public void setTypeCode(CS typeCode) {
    if(typeCode instanceof org.hl7.hibernate.ClonableCollection)
      typeCode = ((org.hl7.hibernate.ClonableCollection<CS>) typeCode).cloneHibernateCollectionIfNecessary();
    _typeCode = typeCode;
  }
  /** Sets the property typeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Acknowledgement#setTypeCode
  */
  public void setTypeCodeForHibernate(CS typeCode) {
    _typeCode = typeCode;
  }

  private INT _expectedSequenceNumber;
  /** Gets the property expectedSequenceNumber.
      @see org.hl7.rim.Acknowledgement#getExpectedSequenceNumber
  */
  public INT getExpectedSequenceNumber() { return _expectedSequenceNumber; }
  /** Sets the property expectedSequenceNumber.
      @see org.hl7.rim.Acknowledgement#setExpectedSequenceNumber
  */
  public void setExpectedSequenceNumber(INT expectedSequenceNumber) {
    if(expectedSequenceNumber instanceof org.hl7.hibernate.ClonableCollection)
      expectedSequenceNumber = ((org.hl7.hibernate.ClonableCollection<INT>) expectedSequenceNumber).cloneHibernateCollectionIfNecessary();
    _expectedSequenceNumber = expectedSequenceNumber;
  }
  /** Sets the property expectedSequenceNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Acknowledgement#setExpectedSequenceNumber
  */
  public void setExpectedSequenceNumberForHibernate(INT expectedSequenceNumber) {
    _expectedSequenceNumber = expectedSequenceNumber;
  }

  private INT _messageWaitingNumber;
  /** Gets the property messageWaitingNumber.
      @see org.hl7.rim.Acknowledgement#getMessageWaitingNumber
  */
  public INT getMessageWaitingNumber() { return _messageWaitingNumber; }
  /** Sets the property messageWaitingNumber.
      @see org.hl7.rim.Acknowledgement#setMessageWaitingNumber
  */
  public void setMessageWaitingNumber(INT messageWaitingNumber) {
    if(messageWaitingNumber instanceof org.hl7.hibernate.ClonableCollection)
      messageWaitingNumber = ((org.hl7.hibernate.ClonableCollection<INT>) messageWaitingNumber).cloneHibernateCollectionIfNecessary();
    _messageWaitingNumber = messageWaitingNumber;
  }
  /** Sets the property messageWaitingNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Acknowledgement#setMessageWaitingNumber
  */
  public void setMessageWaitingNumberForHibernate(INT messageWaitingNumber) {
    _messageWaitingNumber = messageWaitingNumber;
  }

  private CE _messageWaitingPriorityCode;
  /** Gets the property messageWaitingPriorityCode.
      @see org.hl7.rim.Acknowledgement#getMessageWaitingPriorityCode
  */
  public CE getMessageWaitingPriorityCode() { return _messageWaitingPriorityCode; }
  /** Sets the property messageWaitingPriorityCode.
      @see org.hl7.rim.Acknowledgement#setMessageWaitingPriorityCode
  */
  public void setMessageWaitingPriorityCode(CE messageWaitingPriorityCode) {
    if(messageWaitingPriorityCode instanceof org.hl7.hibernate.ClonableCollection)
      messageWaitingPriorityCode = ((org.hl7.hibernate.ClonableCollection<CE>) messageWaitingPriorityCode).cloneHibernateCollectionIfNecessary();
    _messageWaitingPriorityCode = messageWaitingPriorityCode;
  }
  /** Sets the property messageWaitingPriorityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Acknowledgement#setMessageWaitingPriorityCode
  */
  public void setMessageWaitingPriorityCodeForHibernate(CE messageWaitingPriorityCode) {
    _messageWaitingPriorityCode = messageWaitingPriorityCode;
  }

  private /*AssociationSet*/List<org.hl7.rim.AcknowledgementDetail> _acknowledgementDetail;
  /** Gets the property acknowledgementDetail.
      @see org.hl7.rim.Acknowledgement#getAcknowledgementDetail
  */
  public /*AssociationSet*/List<org.hl7.rim.AcknowledgementDetail> getAcknowledgementDetail() {
    return _acknowledgementDetail;
  }
  /** Sets the property acknowledgementDetail.
      @see org.hl7.rim.Acknowledgement#setAcknowledgementDetail
  */
  public void setAcknowledgementDetail(/*AssociationSet*/List<org.hl7.rim.AcknowledgementDetail> acknowledgementDetail) {
    _acknowledgementDetail = acknowledgementDetail;
  }
  /** Adds an association acknowledgementDetail.
      @see org.hl7.rim.Acknowledgement#setAcknowledgementDetail
  */
  public void addAcknowledgementDetail(org.hl7.rim.AcknowledgementDetail acknowledgementDetail) {
        // create the association set if it doesn't exist already
    if(_acknowledgementDetail == null) _acknowledgementDetail = new AssociationSetImpl<org.hl7.rim.AcknowledgementDetail>();
    // add the association to the association set
    getAcknowledgementDetail().add(acknowledgementDetail);
    // make the inverse link
    acknowledgementDetail.setAcknowledgement(this);
  }

  private org.hl7.rim.Transmission _acknowledges;
  /** Gets the property acknowledges.
      @see org.hl7.rim.Acknowledgement#getAcknowledges
  */
  public org.hl7.rim.Transmission getAcknowledges() {
    return _acknowledges;
  }
  /** Sets the property acknowledges.
      @see org.hl7.rim.Acknowledgement#setAcknowledges
  */
  public void setAcknowledges(org.hl7.rim.Transmission acknowledges) {
    _acknowledges = acknowledges;
  }

  private org.hl7.rim.Transmission _conveyingTransmission;
  /** Gets the property conveyingTransmission.
      @see org.hl7.rim.Acknowledgement#getConveyingTransmission
  */
  public org.hl7.rim.Transmission getConveyingTransmission() {
    return _conveyingTransmission;
  }
  /** Sets the property conveyingTransmission.
      @see org.hl7.rim.Acknowledgement#setConveyingTransmission
  */
  public void setConveyingTransmission(org.hl7.rim.Transmission conveyingTransmission) {
    _conveyingTransmission = conveyingTransmission;
  }
  public Object clone() throws CloneNotSupportedException {
    AcknowledgementImpl that = (AcknowledgementImpl) super.clone();

    // deep clone of persistent component collections
    that._acknowledgementDetail= null;
    return that;
  }
}

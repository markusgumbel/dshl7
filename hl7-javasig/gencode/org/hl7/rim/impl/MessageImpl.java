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

import org.hl7.rim.Message;
import org.hl7.rim.impl.TransmissionImpl;
import org.hl7.types.LIST;
import org.hl7.types.II;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.SET;
import org.hl7.types.ED;

import org.hl7.rim.ControlAct;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Message as a simple data holder bean.
    @see org.hl7.rim.Message
  */
public class MessageImpl extends org.hl7.rim.impl.TransmissionImpl implements Message {

  private LIST<II> _profileId;
  /** Gets the property profileId.
      @see org.hl7.rim.Message#getProfileId
  */
  public LIST<II> getProfileId() { return _profileId; }
  /** Sets the property profileId.
      @see org.hl7.rim.Message#setProfileId
  */
  public void setProfileId(LIST<II> profileId) {
    if(profileId instanceof org.hl7.hibernate.ClonableCollection)
      profileId = ((org.hl7.hibernate.ClonableCollection<LIST<II>>) profileId).cloneHibernateCollectionIfNecessary();
    _profileId = profileId;
  }
  /** Sets the property profileId. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Message#setProfileId
  */
  public void setProfileIdForHibernate(LIST<II> profileId) {
    _profileId = profileId;
  }

  private CS _processingCode;
  /** Gets the property processingCode.
      @see org.hl7.rim.Message#getProcessingCode
  */
  public CS getProcessingCode() { return _processingCode; }
  /** Sets the property processingCode.
      @see org.hl7.rim.Message#setProcessingCode
  */
  public void setProcessingCode(CS processingCode) {
    if(processingCode instanceof org.hl7.hibernate.ClonableCollection)
      processingCode = ((org.hl7.hibernate.ClonableCollection<CS>) processingCode).cloneHibernateCollectionIfNecessary();
    _processingCode = processingCode;
  }
  /** Sets the property processingCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Message#setProcessingCode
  */
  public void setProcessingCodeForHibernate(CS processingCode) {
    _processingCode = processingCode;
  }

  private CS _processingModeCode;
  /** Gets the property processingModeCode.
      @see org.hl7.rim.Message#getProcessingModeCode
  */
  public CS getProcessingModeCode() { return _processingModeCode; }
  /** Sets the property processingModeCode.
      @see org.hl7.rim.Message#setProcessingModeCode
  */
  public void setProcessingModeCode(CS processingModeCode) {
    if(processingModeCode instanceof org.hl7.hibernate.ClonableCollection)
      processingModeCode = ((org.hl7.hibernate.ClonableCollection<CS>) processingModeCode).cloneHibernateCollectionIfNecessary();
    _processingModeCode = processingModeCode;
  }
  /** Sets the property processingModeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Message#setProcessingModeCode
  */
  public void setProcessingModeCodeForHibernate(CS processingModeCode) {
    _processingModeCode = processingModeCode;
  }

  private CS _acceptAckCode;
  /** Gets the property acceptAckCode.
      @see org.hl7.rim.Message#getAcceptAckCode
  */
  public CS getAcceptAckCode() { return _acceptAckCode; }
  /** Sets the property acceptAckCode.
      @see org.hl7.rim.Message#setAcceptAckCode
  */
  public void setAcceptAckCode(CS acceptAckCode) {
    if(acceptAckCode instanceof org.hl7.hibernate.ClonableCollection)
      acceptAckCode = ((org.hl7.hibernate.ClonableCollection<CS>) acceptAckCode).cloneHibernateCollectionIfNecessary();
    _acceptAckCode = acceptAckCode;
  }
  /** Sets the property acceptAckCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Message#setAcceptAckCode
  */
  public void setAcceptAckCodeForHibernate(CS acceptAckCode) {
    _acceptAckCode = acceptAckCode;
  }

  private CS _responseCode;
  /** Gets the property responseCode.
      @see org.hl7.rim.Message#getResponseCode
  */
  public CS getResponseCode() { return _responseCode; }
  /** Sets the property responseCode.
      @see org.hl7.rim.Message#setResponseCode
  */
  public void setResponseCode(CS responseCode) {
    if(responseCode instanceof org.hl7.hibernate.ClonableCollection)
      responseCode = ((org.hl7.hibernate.ClonableCollection<CS>) responseCode).cloneHibernateCollectionIfNecessary();
    _responseCode = responseCode;
  }
  /** Sets the property responseCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Message#setResponseCode
  */
  public void setResponseCodeForHibernate(CS responseCode) {
    _responseCode = responseCode;
  }

  private INT _sequenceNumber;
  /** Gets the property sequenceNumber.
      @see org.hl7.rim.Message#getSequenceNumber
  */
  public INT getSequenceNumber() { return _sequenceNumber; }
  /** Sets the property sequenceNumber.
      @see org.hl7.rim.Message#setSequenceNumber
  */
  public void setSequenceNumber(INT sequenceNumber) {
    if(sequenceNumber instanceof org.hl7.hibernate.ClonableCollection)
      sequenceNumber = ((org.hl7.hibernate.ClonableCollection<INT>) sequenceNumber).cloneHibernateCollectionIfNecessary();
    _sequenceNumber = sequenceNumber;
  }
  /** Sets the property sequenceNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Message#setSequenceNumber
  */
  public void setSequenceNumberForHibernate(INT sequenceNumber) {
    _sequenceNumber = sequenceNumber;
  }

  private SET<ED> _attachmentText;
  /** Gets the property attachmentText.
      @see org.hl7.rim.Message#getAttachmentText
  */
  public SET<ED> getAttachmentText() { return _attachmentText; }
  /** Sets the property attachmentText.
      @see org.hl7.rim.Message#setAttachmentText
  */
  public void setAttachmentText(SET<ED> attachmentText) {
    if(attachmentText instanceof org.hl7.hibernate.ClonableCollection)
      attachmentText = ((org.hl7.hibernate.ClonableCollection<SET<ED>>) attachmentText).cloneHibernateCollectionIfNecessary();
    _attachmentText = attachmentText;
  }
  /** Sets the property attachmentText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Message#setAttachmentText
  */
  public void setAttachmentTextForHibernate(SET<ED> attachmentText) {
    _attachmentText = attachmentText;
  }

  private /*AssociationSet*/List<org.hl7.rim.ControlAct> _controlAct;
  /** Gets the property controlAct.
      @see org.hl7.rim.Message#getControlAct
  */
  public /*AssociationSet*/List<org.hl7.rim.ControlAct> getControlAct() {
    return _controlAct;
  }
  /** Sets the property controlAct.
      @see org.hl7.rim.Message#setControlAct
  */
  public void setControlAct(/*AssociationSet*/List<org.hl7.rim.ControlAct> controlAct) {
    _controlAct = controlAct;
  }
  /** Adds an association controlAct.
      @see org.hl7.rim.Message#setControlAct
  */
  public void addControlAct(org.hl7.rim.ControlAct controlAct) {
        // create the association set if it doesn't exist already
    if(_controlAct == null) _controlAct = new AssociationSetImpl<org.hl7.rim.ControlAct>();
    // add the association to the association set
    getControlAct().add(controlAct);
    // make the inverse link
    controlAct.setPayload(this);
  }
  public Object clone() throws CloneNotSupportedException {
    MessageImpl that = (MessageImpl) super.clone();

    // deep clone of persistent component collections
    that.setProfileId(that.getProfileId());
    that.setAttachmentText(that.getAttachmentText());
    that._controlAct= null;
    return that;
  }
}

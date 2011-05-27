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
package org.hl7.rim.decorators;

import org.hl7.rim.Acknowledgement;
import org.hl7.rim.decorators.InfrastructureRootDecorator;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.CE;

import org.hl7.rim.AcknowledgementDetail;
import org.hl7.rim.Transmission;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.CEnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Acknowledgement as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Acknowledgement
  */
public abstract class AcknowledgementDecorator extends org.hl7.rim.decorators.InfrastructureRootDecorator implements Acknowledgement {
  /** Property accessor, returns NULL/NA if not overloaded.typeCode.
      @see org.hl7.rim.Acknowledgement#getTypeCode
  */
  public CS getTypeCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.typeCode.
      @see org.hl7.rim.Acknowledgement#setTypeCode
  */
  public void setTypeCode(CS typeCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.expectedSequenceNumber.
      @see org.hl7.rim.Acknowledgement#getExpectedSequenceNumber
  */
  public INT getExpectedSequenceNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.expectedSequenceNumber.
      @see org.hl7.rim.Acknowledgement#setExpectedSequenceNumber
  */
  public void setExpectedSequenceNumber(INT expectedSequenceNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.messageWaitingNumber.
      @see org.hl7.rim.Acknowledgement#getMessageWaitingNumber
  */
  public INT getMessageWaitingNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.messageWaitingNumber.
      @see org.hl7.rim.Acknowledgement#setMessageWaitingNumber
  */
  public void setMessageWaitingNumber(INT messageWaitingNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.messageWaitingPriorityCode.
      @see org.hl7.rim.Acknowledgement#getMessageWaitingPriorityCode
  */
  public CE getMessageWaitingPriorityCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.messageWaitingPriorityCode.
      @see org.hl7.rim.Acknowledgement#setMessageWaitingPriorityCode
  */
  public void setMessageWaitingPriorityCode(CE messageWaitingPriorityCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.acknowledgementDetail.
      @see org.hl7.rim.Acknowledgement#getAcknowledgementDetail
  */
  public /*AssociationSet*/List<org.hl7.rim.AcknowledgementDetail> getAcknowledgementDetail() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.acknowledgementDetail.
      @see org.hl7.rim.Acknowledgement#setAcknowledgementDetail
  */
  public void setAcknowledgementDetail(/*AssociationSet*/List<org.hl7.rim.AcknowledgementDetail> acknowledgementDetail) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded acknowledgementDetail.
      @see org.hl7.rim.Acknowledgement#setAcknowledgementDetail
  */
  public void addAcknowledgementDetail(org.hl7.rim.AcknowledgementDetail acknowledgementDetail) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns null if not overloaded.acknowledges.
      @see org.hl7.rim.Acknowledgement#getAcknowledges
  */
  public org.hl7.rim.Transmission getAcknowledges() { return null; }
  /** Property mutator, does nothing if not overloaded.acknowledges.
      @see org.hl7.rim.Acknowledgement#setAcknowledges
  */
  public void setAcknowledges(org.hl7.rim.Transmission acknowledges) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns null if not overloaded.conveyingTransmission.
      @see org.hl7.rim.Acknowledgement#getConveyingTransmission
  */
  public org.hl7.rim.Transmission getConveyingTransmission() { return null; }
  /** Property mutator, does nothing if not overloaded.conveyingTransmission.
      @see org.hl7.rim.Acknowledgement#setConveyingTransmission
  */
  public void setConveyingTransmission(org.hl7.rim.Transmission conveyingTransmission) { /* throw new UnsupportedOperationException(); */ }
}

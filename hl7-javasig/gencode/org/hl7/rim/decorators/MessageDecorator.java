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

import org.hl7.rim.Message;
import org.hl7.rim.decorators.TransmissionDecorator;
import org.hl7.types.LIST;
import org.hl7.types.II;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.SET;
import org.hl7.types.ED;

import org.hl7.rim.ControlAct;
import org.hl7.types.impl.LISTnull;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.EDnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Message as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Message
  */
public abstract class MessageDecorator extends org.hl7.rim.decorators.TransmissionDecorator implements Message {
  /** Property accessor, returns NULL/NA if not overloaded.profileId.
      @see org.hl7.rim.Message#getProfileId
  */
  public LIST<II> getProfileId() { return LISTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.profileId.
      @see org.hl7.rim.Message#setProfileId
  */
  public void setProfileId(LIST<II> profileId) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.processingCode.
      @see org.hl7.rim.Message#getProcessingCode
  */
  public CS getProcessingCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.processingCode.
      @see org.hl7.rim.Message#setProcessingCode
  */
  public void setProcessingCode(CS processingCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.processingModeCode.
      @see org.hl7.rim.Message#getProcessingModeCode
  */
  public CS getProcessingModeCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.processingModeCode.
      @see org.hl7.rim.Message#setProcessingModeCode
  */
  public void setProcessingModeCode(CS processingModeCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.acceptAckCode.
      @see org.hl7.rim.Message#getAcceptAckCode
  */
  public CS getAcceptAckCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.acceptAckCode.
      @see org.hl7.rim.Message#setAcceptAckCode
  */
  public void setAcceptAckCode(CS acceptAckCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.responseCode.
      @see org.hl7.rim.Message#getResponseCode
  */
  public CS getResponseCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.responseCode.
      @see org.hl7.rim.Message#setResponseCode
  */
  public void setResponseCode(CS responseCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.sequenceNumber.
      @see org.hl7.rim.Message#getSequenceNumber
  */
  public INT getSequenceNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.sequenceNumber.
      @see org.hl7.rim.Message#setSequenceNumber
  */
  public void setSequenceNumber(INT sequenceNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.attachmentText.
      @see org.hl7.rim.Message#getAttachmentText
  */
  public SET<ED> getAttachmentText() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.attachmentText.
      @see org.hl7.rim.Message#setAttachmentText
  */
  public void setAttachmentText(SET<ED> attachmentText) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.controlAct.
      @see org.hl7.rim.Message#getControlAct
  */
  public /*AssociationSet*/List<org.hl7.rim.ControlAct> getControlAct() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.controlAct.
      @see org.hl7.rim.Message#setControlAct
  */
  public void setControlAct(/*AssociationSet*/List<org.hl7.rim.ControlAct> controlAct) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded controlAct.
      @see org.hl7.rim.Message#setControlAct
  */
  public void addControlAct(org.hl7.rim.ControlAct controlAct) { throw new UnsupportedOperationException(); }
}

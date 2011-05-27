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

import org.hl7.rim.Transmission;
import org.hl7.rim.decorators.InfrastructureRootDecorator;
import org.hl7.types.II;
import org.hl7.types.TS;
import org.hl7.types.ST;
import org.hl7.types.CS;

import org.hl7.rim.Acknowledgement;
import org.hl7.rim.Attachment;
import org.hl7.rim.AttentionLine;
import org.hl7.rim.Batch;
import org.hl7.rim.CommunicationFunction;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.CSnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Transmission as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Transmission
  */
public abstract class TransmissionDecorator extends org.hl7.rim.decorators.InfrastructureRootDecorator implements Transmission {
  /** Property accessor, returns NULL/NA if not overloaded.id.
      @see org.hl7.rim.Transmission#getId
  */
  public II getId() { return IInull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.id.
      @see org.hl7.rim.Transmission#setId
  */
  public void setId(II id) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.creationTime.
      @see org.hl7.rim.Transmission#getCreationTime
  */
  public TS getCreationTime() { return TSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.creationTime.
      @see org.hl7.rim.Transmission#setCreationTime
  */
  public void setCreationTime(TS creationTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.securityText.
      @see org.hl7.rim.Transmission#getSecurityText
  */
  public ST getSecurityText() { return STnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.securityText.
      @see org.hl7.rim.Transmission#setSecurityText
  */
  public void setSecurityText(ST securityText) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.responseModeCode.
      @see org.hl7.rim.Transmission#getResponseModeCode
  */
  public CS getResponseModeCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.responseModeCode.
      @see org.hl7.rim.Transmission#setResponseModeCode
  */
  public void setResponseModeCode(CS responseModeCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.versionCode.
      @see org.hl7.rim.Transmission#getVersionCode
  */
  public CS getVersionCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.versionCode.
      @see org.hl7.rim.Transmission#setVersionCode
  */
  public void setVersionCode(CS versionCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.interactionId.
      @see org.hl7.rim.Transmission#getInteractionId
  */
  public II getInteractionId() { return IInull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.interactionId.
      @see org.hl7.rim.Transmission#setInteractionId
  */
  public void setInteractionId(II interactionId) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.acknowledgedBy.
      @see org.hl7.rim.Transmission#getAcknowledgedBy
  */
  public /*AssociationSet*/List<org.hl7.rim.Acknowledgement> getAcknowledgedBy() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.acknowledgedBy.
      @see org.hl7.rim.Transmission#setAcknowledgedBy
  */
  public void setAcknowledgedBy(/*AssociationSet*/List<org.hl7.rim.Acknowledgement> acknowledgedBy) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded acknowledgedBy.
      @see org.hl7.rim.Transmission#setAcknowledgedBy
  */
  public void addAcknowledgedBy(org.hl7.rim.Acknowledgement acknowledgedBy) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.conveyedAcknowledgement.
      @see org.hl7.rim.Transmission#getConveyedAcknowledgement
  */
  public /*AssociationSet*/List<org.hl7.rim.Acknowledgement> getConveyedAcknowledgement() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.conveyedAcknowledgement.
      @see org.hl7.rim.Transmission#setConveyedAcknowledgement
  */
  public void setConveyedAcknowledgement(/*AssociationSet*/List<org.hl7.rim.Acknowledgement> conveyedAcknowledgement) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded conveyedAcknowledgement.
      @see org.hl7.rim.Transmission#setConveyedAcknowledgement
  */
  public void addConveyedAcknowledgement(org.hl7.rim.Acknowledgement conveyedAcknowledgement) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.attachment.
      @see org.hl7.rim.Transmission#getAttachment
  */
  public /*AssociationSet*/List<org.hl7.rim.Attachment> getAttachment() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.attachment.
      @see org.hl7.rim.Transmission#setAttachment
  */
  public void setAttachment(/*AssociationSet*/List<org.hl7.rim.Attachment> attachment) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded attachment.
      @see org.hl7.rim.Transmission#setAttachment
  */
  public void addAttachment(org.hl7.rim.Attachment attachment) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.attentionLine.
      @see org.hl7.rim.Transmission#getAttentionLine
  */
  public /*AssociationSet*/List<org.hl7.rim.AttentionLine> getAttentionLine() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.attentionLine.
      @see org.hl7.rim.Transmission#setAttentionLine
  */
  public void setAttentionLine(/*AssociationSet*/List<org.hl7.rim.AttentionLine> attentionLine) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded attentionLine.
      @see org.hl7.rim.Transmission#setAttentionLine
  */
  public void addAttentionLine(org.hl7.rim.AttentionLine attentionLine) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns null if not overloaded.batch.
      @see org.hl7.rim.Transmission#getBatch
  */
  public org.hl7.rim.Batch getBatch() { return null; }
  /** Property mutator, does nothing if not overloaded.batch.
      @see org.hl7.rim.Transmission#setBatch
  */
  public void setBatch(org.hl7.rim.Batch batch) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns an empty collection if not overloaded.communicationFunction.
      @see org.hl7.rim.Transmission#getCommunicationFunction
  */
  public /*AssociationSet*/List<org.hl7.rim.CommunicationFunction> getCommunicationFunction() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.communicationFunction.
      @see org.hl7.rim.Transmission#setCommunicationFunction
  */
  public void setCommunicationFunction(/*AssociationSet*/List<org.hl7.rim.CommunicationFunction> communicationFunction) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded communicationFunction.
      @see org.hl7.rim.Transmission#setCommunicationFunction
  */
  public void addCommunicationFunction(org.hl7.rim.CommunicationFunction communicationFunction) { throw new UnsupportedOperationException(); }
}

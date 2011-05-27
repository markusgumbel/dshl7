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

import org.hl7.rim.Act;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CD;
import org.hl7.types.BL;
import org.hl7.types.ST;
import org.hl7.types.ED;
import org.hl7.types.TS;
import org.hl7.types.CE;
import org.hl7.types.CE;
import org.hl7.types.IVL;
import org.hl7.types.INT;
import org.hl7.types.CE;

import org.hl7.rim.ActRelationship;
import org.hl7.rim.Participation;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.CDnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.EDnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.CEnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Act as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Act
  */
public abstract class ActDecorator extends BasicActDecorator implements Act {
  /** Property accessor, returns NULL/NA if not overloaded.id.
      @see org.hl7.rim.Act#getId
  */
  public SET<II> getId() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.id.
      @see org.hl7.rim.Act#setId
  */
  public void setId(SET<II> id) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.code.
      @see org.hl7.rim.Act#getCode
  */
  public CD getCode() { return CDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.code.
      @see org.hl7.rim.Act#setCode
  */
  public void setCode(CD code) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.negationInd.
      @see org.hl7.rim.Act#getNegationInd
  */
  public BL getNegationInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.negationInd.
      @see org.hl7.rim.Act#setNegationInd
  */
  public void setNegationInd(BL negationInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.derivationExpr.
      @see org.hl7.rim.Act#getDerivationExpr
  */
  public ST getDerivationExpr() { return STnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.derivationExpr.
      @see org.hl7.rim.Act#setDerivationExpr
  */
  public void setDerivationExpr(ST derivationExpr) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.title.
      @see org.hl7.rim.Act#getTitle
  */
  public ED getTitle() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.title.
      @see org.hl7.rim.Act#setTitle
  */
  public void setTitle(ED title) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.text.
      @see org.hl7.rim.Act#getText
  */
  public ED getText() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.text.
      @see org.hl7.rim.Act#setText
  */
  public void setText(ED text) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.statusCode.
      @see org.hl7.rim.Act#getStatusCode
  */
  public CS getStatusCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.statusCode.
      @see org.hl7.rim.Act#setStatusCode
  */
  public void setStatusCode(CS statusCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.effectiveTime.
      @see org.hl7.rim.Act#getEffectiveTime
  */
  public SET<TS> getEffectiveTime() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.effectiveTime.
      @see org.hl7.rim.Act#setEffectiveTime
  */
  public void setEffectiveTime(SET<TS> effectiveTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.activityTime.
      @see org.hl7.rim.Act#getActivityTime
  */
  public SET<TS> getActivityTime() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.activityTime.
      @see org.hl7.rim.Act#setActivityTime
  */
  public void setActivityTime(SET<TS> activityTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.availabilityTime.
      @see org.hl7.rim.Act#getAvailabilityTime
  */
  public TS getAvailabilityTime() { return TSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.availabilityTime.
      @see org.hl7.rim.Act#setAvailabilityTime
  */
  public void setAvailabilityTime(TS availabilityTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.priorityCode.
      @see org.hl7.rim.Act#getPriorityCode
  */
  public SET<CE> getPriorityCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.priorityCode.
      @see org.hl7.rim.Act#setPriorityCode
  */
  public void setPriorityCode(SET<CE> priorityCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.confidentialityCode.
      @see org.hl7.rim.Act#getConfidentialityCode
  */
  public SET<CE> getConfidentialityCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.confidentialityCode.
      @see org.hl7.rim.Act#setConfidentialityCode
  */
  public void setConfidentialityCode(SET<CE> confidentialityCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.repeatNumber.
      @see org.hl7.rim.Act#getRepeatNumber
  */
  public IVL<INT> getRepeatNumber() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.repeatNumber.
      @see org.hl7.rim.Act#setRepeatNumber
  */
  public void setRepeatNumber(IVL<INT> repeatNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.interruptibleInd.
      @see org.hl7.rim.Act#getInterruptibleInd
  */
  public BL getInterruptibleInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.interruptibleInd.
      @see org.hl7.rim.Act#setInterruptibleInd
  */
  public void setInterruptibleInd(BL interruptibleInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.levelCode.
      @see org.hl7.rim.Act#getLevelCode
  */
  public CE getLevelCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.levelCode.
      @see org.hl7.rim.Act#setLevelCode
  */
  public void setLevelCode(CE levelCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.independentInd.
      @see org.hl7.rim.Act#getIndependentInd
  */
  public BL getIndependentInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.independentInd.
      @see org.hl7.rim.Act#setIndependentInd
  */
  public void setIndependentInd(BL independentInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.uncertaintyCode.
      @see org.hl7.rim.Act#getUncertaintyCode
  */
  public CE getUncertaintyCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.uncertaintyCode.
      @see org.hl7.rim.Act#setUncertaintyCode
  */
  public void setUncertaintyCode(CE uncertaintyCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.reasonCode.
      @see org.hl7.rim.Act#getReasonCode
  */
  public SET<CE> getReasonCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.reasonCode.
      @see org.hl7.rim.Act#setReasonCode
  */
  public void setReasonCode(SET<CE> reasonCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.languageCode.
      @see org.hl7.rim.Act#getLanguageCode
  */
  public CE getLanguageCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.languageCode.
      @see org.hl7.rim.Act#setLanguageCode
  */
  public void setLanguageCode(CE languageCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.outboundRelationship.
      @see org.hl7.rim.Act#getOutboundRelationship
  */
  public /*AssociationSet*/List<org.hl7.rim.ActRelationship> getOutboundRelationship() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.outboundRelationship.
      @see org.hl7.rim.Act#setOutboundRelationship
  */
  public void setOutboundRelationship(/*AssociationSet*/List<org.hl7.rim.ActRelationship> outboundRelationship) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded outboundRelationship.
      @see org.hl7.rim.Act#setOutboundRelationship
  */
  public void addOutboundRelationship(org.hl7.rim.ActRelationship outboundRelationship) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.inboundRelationship.
      @see org.hl7.rim.Act#getInboundRelationship
  */
  public /*AssociationSet*/List<org.hl7.rim.ActRelationship> getInboundRelationship() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.inboundRelationship.
      @see org.hl7.rim.Act#setInboundRelationship
  */
  public void setInboundRelationship(/*AssociationSet*/List<org.hl7.rim.ActRelationship> inboundRelationship) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded inboundRelationship.
      @see org.hl7.rim.Act#setInboundRelationship
  */
  public void addInboundRelationship(org.hl7.rim.ActRelationship inboundRelationship) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.participation.
      @see org.hl7.rim.Act#getParticipation
  */
  public /*AssociationSet*/List<org.hl7.rim.Participation> getParticipation() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.participation.
      @see org.hl7.rim.Act#setParticipation
  */
  public void setParticipation(/*AssociationSet*/List<org.hl7.rim.Participation> participation) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded participation.
      @see org.hl7.rim.Act#setParticipation
  */
  public void addParticipation(org.hl7.rim.Participation participation) { throw new UnsupportedOperationException(); }
}

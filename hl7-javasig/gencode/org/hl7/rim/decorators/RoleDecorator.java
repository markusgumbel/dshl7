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

import org.hl7.rim.Role;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CE;
import org.hl7.types.BL;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.AD;
import org.hl7.types.TEL;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.ED;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.LIST;
import org.hl7.types.INT;

import org.hl7.rim.Participation;
import org.hl7.rim.Entity;
import org.hl7.rim.RoleLink;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.BAGnull;
import org.hl7.types.impl.ENnull;
import org.hl7.types.impl.ADnull;
import org.hl7.types.impl.TELnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.EDnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.RTOnull;
import org.hl7.types.impl.LISTnull;
import org.hl7.types.impl.INTnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Role as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Role
  */
public abstract class RoleDecorator extends BasicRoleDecorator implements Role {
  /** Property accessor, returns NULL/NA if not overloaded.id.
      @see org.hl7.rim.Role#getId
  */
  public SET<II> getId() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.id.
      @see org.hl7.rim.Role#setId
  */
  public void setId(SET<II> id) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.code.
      @see org.hl7.rim.Role#getCode
  */
  public CE getCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.code.
      @see org.hl7.rim.Role#setCode
  */
  public void setCode(CE code) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.negationInd.
      @see org.hl7.rim.Role#getNegationInd
  */
  public BL getNegationInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.negationInd.
      @see org.hl7.rim.Role#setNegationInd
  */
  public void setNegationInd(BL negationInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.name.
      @see org.hl7.rim.Role#getName
  */
  public BAG<EN> getName() { return BAGnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.name.
      @see org.hl7.rim.Role#setName
  */
  public void setName(BAG<EN> name) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.addr.
      @see org.hl7.rim.Role#getAddr
  */
  public BAG<AD> getAddr() { return BAGnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.addr.
      @see org.hl7.rim.Role#setAddr
  */
  public void setAddr(BAG<AD> addr) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.telecom.
      @see org.hl7.rim.Role#getTelecom
  */
  public BAG<TEL> getTelecom() { return BAGnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.telecom.
      @see org.hl7.rim.Role#setTelecom
  */
  public void setTelecom(BAG<TEL> telecom) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.statusCode.
      @see org.hl7.rim.Role#getStatusCode
  */
  public CS getStatusCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.statusCode.
      @see org.hl7.rim.Role#setStatusCode
  */
  public void setStatusCode(CS statusCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.effectiveTime.
      @see org.hl7.rim.Role#getEffectiveTime
  */
  public IVL<TS> getEffectiveTime() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.effectiveTime.
      @see org.hl7.rim.Role#setEffectiveTime
  */
  public void setEffectiveTime(IVL<TS> effectiveTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.certificateText.
      @see org.hl7.rim.Role#getCertificateText
  */
  public ED getCertificateText() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.certificateText.
      @see org.hl7.rim.Role#setCertificateText
  */
  public void setCertificateText(ED certificateText) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.confidentialityCode.
      @see org.hl7.rim.Role#getConfidentialityCode
  */
  public SET<CE> getConfidentialityCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.confidentialityCode.
      @see org.hl7.rim.Role#setConfidentialityCode
  */
  public void setConfidentialityCode(SET<CE> confidentialityCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.quantity.
      @see org.hl7.rim.Role#getQuantity
  */
  public RTO getQuantity() { return RTOnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.quantity.
      @see org.hl7.rim.Role#setQuantity
  */
  public void setQuantity(RTO quantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.positionNumber.
      @see org.hl7.rim.Role#getPositionNumber
  */
  public LIST<INT> getPositionNumber() { return LISTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.positionNumber.
      @see org.hl7.rim.Role#setPositionNumber
  */
  public void setPositionNumber(LIST<INT> positionNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.participation.
      @see org.hl7.rim.Role#getParticipation
  */
  public /*AssociationSet*/List<org.hl7.rim.Participation> getParticipation() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.participation.
      @see org.hl7.rim.Role#setParticipation
  */
  public void setParticipation(/*AssociationSet*/List<org.hl7.rim.Participation> participation) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded participation.
      @see org.hl7.rim.Role#setParticipation
  */
  public void addParticipation(org.hl7.rim.Participation participation) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns null if not overloaded.player.
      @see org.hl7.rim.Role#getPlayer
  */
  public org.hl7.rim.Entity getPlayer() { return null; }
  /** Property mutator, does nothing if not overloaded.player.
      @see org.hl7.rim.Role#setPlayer
  */
  public void setPlayer(org.hl7.rim.Entity player) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns null if not overloaded.scoper.
      @see org.hl7.rim.Role#getScoper
  */
  public org.hl7.rim.Entity getScoper() { return null; }
  /** Property mutator, does nothing if not overloaded.scoper.
      @see org.hl7.rim.Role#setScoper
  */
  public void setScoper(org.hl7.rim.Entity scoper) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns an empty collection if not overloaded.outboundLink.
      @see org.hl7.rim.Role#getOutboundLink
  */
  public /*AssociationSet*/List<org.hl7.rim.RoleLink> getOutboundLink() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.outboundLink.
      @see org.hl7.rim.Role#setOutboundLink
  */
  public void setOutboundLink(/*AssociationSet*/List<org.hl7.rim.RoleLink> outboundLink) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded outboundLink.
      @see org.hl7.rim.Role#setOutboundLink
  */
  public void addOutboundLink(org.hl7.rim.RoleLink outboundLink) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.inboundLink.
      @see org.hl7.rim.Role#getInboundLink
  */
  public /*AssociationSet*/List<org.hl7.rim.RoleLink> getInboundLink() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.inboundLink.
      @see org.hl7.rim.Role#setInboundLink
  */
  public void setInboundLink(/*AssociationSet*/List<org.hl7.rim.RoleLink> inboundLink) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded inboundLink.
      @see org.hl7.rim.Role#setInboundLink
  */
  public void addInboundLink(org.hl7.rim.RoleLink inboundLink) { throw new UnsupportedOperationException(); }
}

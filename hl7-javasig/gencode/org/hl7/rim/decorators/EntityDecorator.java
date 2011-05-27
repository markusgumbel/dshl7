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

import org.hl7.rim.Entity;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CE;
import org.hl7.types.PQ;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.ED;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.TEL;

import org.hl7.rim.CommunicationFunction;
import org.hl7.rim.LanguageCommunication;
import org.hl7.rim.Role;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.BAGnull;
import org.hl7.types.impl.ENnull;
import org.hl7.types.impl.EDnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.TELnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Entity as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Entity
  */
public abstract class EntityDecorator extends BasicEntityDecorator implements Entity {
  /** Property accessor, returns NULL/NA if not overloaded.id.
      @see org.hl7.rim.Entity#getId
  */
  public SET<II> getId() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.id.
      @see org.hl7.rim.Entity#setId
  */
  public void setId(SET<II> id) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.code.
      @see org.hl7.rim.Entity#getCode
  */
  public CE getCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.code.
      @see org.hl7.rim.Entity#setCode
  */
  public void setCode(CE code) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.quantity.
      @see org.hl7.rim.Entity#getQuantity
  */
  public SET<PQ> getQuantity() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.quantity.
      @see org.hl7.rim.Entity#setQuantity
  */
  public void setQuantity(SET<PQ> quantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.name.
      @see org.hl7.rim.Entity#getName
  */
  public BAG<EN> getName() { return BAGnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.name.
      @see org.hl7.rim.Entity#setName
  */
  public void setName(BAG<EN> name) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.desc.
      @see org.hl7.rim.Entity#getDesc
  */
  public ED getDesc() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.desc.
      @see org.hl7.rim.Entity#setDesc
  */
  public void setDesc(ED desc) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.statusCode.
      @see org.hl7.rim.Entity#getStatusCode
  */
  public CS getStatusCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.statusCode.
      @see org.hl7.rim.Entity#setStatusCode
  */
  public void setStatusCode(CS statusCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.existenceTime.
      @see org.hl7.rim.Entity#getExistenceTime
  */
  public IVL<TS> getExistenceTime() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.existenceTime.
      @see org.hl7.rim.Entity#setExistenceTime
  */
  public void setExistenceTime(IVL<TS> existenceTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.telecom.
      @see org.hl7.rim.Entity#getTelecom
  */
  public BAG<TEL> getTelecom() { return BAGnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.telecom.
      @see org.hl7.rim.Entity#setTelecom
  */
  public void setTelecom(BAG<TEL> telecom) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.riskCode.
      @see org.hl7.rim.Entity#getRiskCode
  */
  public CE getRiskCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.riskCode.
      @see org.hl7.rim.Entity#setRiskCode
  */
  public void setRiskCode(CE riskCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.handlingCode.
      @see org.hl7.rim.Entity#getHandlingCode
  */
  public CE getHandlingCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.handlingCode.
      @see org.hl7.rim.Entity#setHandlingCode
  */
  public void setHandlingCode(CE handlingCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.communicationFunction.
      @see org.hl7.rim.Entity#getCommunicationFunction
  */
  public /*AssociationSet*/List<org.hl7.rim.CommunicationFunction> getCommunicationFunction() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.communicationFunction.
      @see org.hl7.rim.Entity#setCommunicationFunction
  */
  public void setCommunicationFunction(/*AssociationSet*/List<org.hl7.rim.CommunicationFunction> communicationFunction) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded communicationFunction.
      @see org.hl7.rim.Entity#setCommunicationFunction
  */
  public void addCommunicationFunction(org.hl7.rim.CommunicationFunction communicationFunction) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.languageCommunication.
      @see org.hl7.rim.Entity#getLanguageCommunication
  */
  public /*AssociationSet*/List<org.hl7.rim.LanguageCommunication> getLanguageCommunication() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.languageCommunication.
      @see org.hl7.rim.Entity#setLanguageCommunication
  */
  public void setLanguageCommunication(/*AssociationSet*/List<org.hl7.rim.LanguageCommunication> languageCommunication) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded languageCommunication.
      @see org.hl7.rim.Entity#setLanguageCommunication
  */
  public void addLanguageCommunication(org.hl7.rim.LanguageCommunication languageCommunication) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.playedRole.
      @see org.hl7.rim.Entity#getPlayedRole
  */
  public /*AssociationSet*/List<org.hl7.rim.Role> getPlayedRole() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.playedRole.
      @see org.hl7.rim.Entity#setPlayedRole
  */
  public void setPlayedRole(/*AssociationSet*/List<org.hl7.rim.Role> playedRole) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded playedRole.
      @see org.hl7.rim.Entity#setPlayedRole
  */
  public void addPlayedRole(org.hl7.rim.Role playedRole) { throw new UnsupportedOperationException(); }
  /** Property accessor, returns an empty collection if not overloaded.scopedRole.
      @see org.hl7.rim.Entity#getScopedRole
  */
  public /*AssociationSet*/List<org.hl7.rim.Role> getScopedRole() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.scopedRole.
      @see org.hl7.rim.Entity#setScopedRole
  */
  public void setScopedRole(/*AssociationSet*/List<org.hl7.rim.Role> scopedRole) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded scopedRole.
      @see org.hl7.rim.Entity#setScopedRole
  */
  public void addScopedRole(org.hl7.rim.Role scopedRole) { throw new UnsupportedOperationException(); }
}

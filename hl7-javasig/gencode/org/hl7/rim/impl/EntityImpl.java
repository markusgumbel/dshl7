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
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Entity as a simple data holder bean.
    @see org.hl7.rim.Entity
  */
public class EntityImpl extends BasicEntityImpl implements Entity {

  private SET<II> _id;
  /** Gets the property id.
      @see org.hl7.rim.Entity#getId
  */
  public SET<II> getId() { return _id; }
  /** Sets the property id.
      @see org.hl7.rim.Entity#setId
  */
  public void setId(SET<II> id) {
    if(id instanceof org.hl7.hibernate.ClonableCollection)
      id = ((org.hl7.hibernate.ClonableCollection<SET<II>>) id).cloneHibernateCollectionIfNecessary();
    _id = id;
  }
  /** Sets the property id. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setId
  */
  public void setIdForHibernate(SET<II> id) {
    _id = id;
  }

  private CE _code;
  /** Gets the property code.
      @see org.hl7.rim.Entity#getCode
  */
  public CE getCode() { return _code; }
  /** Sets the property code.
      @see org.hl7.rim.Entity#setCode
  */
  public void setCode(CE code) {
    if(code instanceof org.hl7.hibernate.ClonableCollection)
      code = ((org.hl7.hibernate.ClonableCollection<CE>) code).cloneHibernateCollectionIfNecessary();
    _code = code;
  }
  /** Sets the property code. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setCode
  */
  public void setCodeForHibernate(CE code) {
    _code = code;
  }

  private SET<PQ> _quantity;
  /** Gets the property quantity.
      @see org.hl7.rim.Entity#getQuantity
  */
  public SET<PQ> getQuantity() { return _quantity; }
  /** Sets the property quantity.
      @see org.hl7.rim.Entity#setQuantity
  */
  public void setQuantity(SET<PQ> quantity) {
    if(quantity instanceof org.hl7.hibernate.ClonableCollection)
      quantity = ((org.hl7.hibernate.ClonableCollection<SET<PQ>>) quantity).cloneHibernateCollectionIfNecessary();
    _quantity = quantity;
  }
  /** Sets the property quantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setQuantity
  */
  public void setQuantityForHibernate(SET<PQ> quantity) {
    _quantity = quantity;
  }

  private BAG<EN> _name;
  /** Gets the property name.
      @see org.hl7.rim.Entity#getName
  */
  public BAG<EN> getName() { return _name; }
  /** Sets the property name.
      @see org.hl7.rim.Entity#setName
  */
  public void setName(BAG<EN> name) {
    if(name instanceof org.hl7.hibernate.ClonableCollection)
      name = ((org.hl7.hibernate.ClonableCollection<BAG<EN>>) name).cloneHibernateCollectionIfNecessary();
    _name = name;
  }
  /** Sets the property name. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setName
  */
  public void setNameForHibernate(BAG<EN> name) {
    _name = name;
  }

  private ED _desc;
  /** Gets the property desc.
      @see org.hl7.rim.Entity#getDesc
  */
  public ED getDesc() { return _desc; }
  /** Sets the property desc.
      @see org.hl7.rim.Entity#setDesc
  */
  public void setDesc(ED desc) {
    if(desc instanceof org.hl7.hibernate.ClonableCollection)
      desc = ((org.hl7.hibernate.ClonableCollection<ED>) desc).cloneHibernateCollectionIfNecessary();
    _desc = desc;
  }
  /** Sets the property desc. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setDesc
  */
  public void setDescForHibernate(ED desc) {
    _desc = desc;
  }

  private CS _statusCode;
  /** Gets the property statusCode.
      @see org.hl7.rim.Entity#getStatusCode
  */
  public CS getStatusCode() { return _statusCode; }
  /** Sets the property statusCode.
      @see org.hl7.rim.Entity#setStatusCode
  */
  public void setStatusCode(CS statusCode) {
    if(statusCode instanceof org.hl7.hibernate.ClonableCollection)
      statusCode = ((org.hl7.hibernate.ClonableCollection<CS>) statusCode).cloneHibernateCollectionIfNecessary();
    _statusCode = statusCode;
  }
  /** Sets the property statusCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setStatusCode
  */
  public void setStatusCodeForHibernate(CS statusCode) {
    _statusCode = statusCode;
  }

  private IVL<TS> _existenceTime;
  /** Gets the property existenceTime.
      @see org.hl7.rim.Entity#getExistenceTime
  */
  public IVL<TS> getExistenceTime() { return _existenceTime; }
  /** Sets the property existenceTime.
      @see org.hl7.rim.Entity#setExistenceTime
  */
  public void setExistenceTime(IVL<TS> existenceTime) {
    if(existenceTime instanceof org.hl7.hibernate.ClonableCollection)
      existenceTime = ((org.hl7.hibernate.ClonableCollection<IVL<TS>>) existenceTime).cloneHibernateCollectionIfNecessary();
    _existenceTime = existenceTime;
  }
  /** Sets the property existenceTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setExistenceTime
  */
  public void setExistenceTimeForHibernate(IVL<TS> existenceTime) {
    _existenceTime = existenceTime;
  }

  private BAG<TEL> _telecom;
  /** Gets the property telecom.
      @see org.hl7.rim.Entity#getTelecom
  */
  public BAG<TEL> getTelecom() { return _telecom; }
  /** Sets the property telecom.
      @see org.hl7.rim.Entity#setTelecom
  */
  public void setTelecom(BAG<TEL> telecom) {
    if(telecom instanceof org.hl7.hibernate.ClonableCollection)
      telecom = ((org.hl7.hibernate.ClonableCollection<BAG<TEL>>) telecom).cloneHibernateCollectionIfNecessary();
    _telecom = telecom;
  }
  /** Sets the property telecom. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setTelecom
  */
  public void setTelecomForHibernate(BAG<TEL> telecom) {
    _telecom = telecom;
  }

  private CE _riskCode;
  /** Gets the property riskCode.
      @see org.hl7.rim.Entity#getRiskCode
  */
  public CE getRiskCode() { return _riskCode; }
  /** Sets the property riskCode.
      @see org.hl7.rim.Entity#setRiskCode
  */
  public void setRiskCode(CE riskCode) {
    if(riskCode instanceof org.hl7.hibernate.ClonableCollection)
      riskCode = ((org.hl7.hibernate.ClonableCollection<CE>) riskCode).cloneHibernateCollectionIfNecessary();
    _riskCode = riskCode;
  }
  /** Sets the property riskCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setRiskCode
  */
  public void setRiskCodeForHibernate(CE riskCode) {
    _riskCode = riskCode;
  }

  private CE _handlingCode;
  /** Gets the property handlingCode.
      @see org.hl7.rim.Entity#getHandlingCode
  */
  public CE getHandlingCode() { return _handlingCode; }
  /** Sets the property handlingCode.
      @see org.hl7.rim.Entity#setHandlingCode
  */
  public void setHandlingCode(CE handlingCode) {
    if(handlingCode instanceof org.hl7.hibernate.ClonableCollection)
      handlingCode = ((org.hl7.hibernate.ClonableCollection<CE>) handlingCode).cloneHibernateCollectionIfNecessary();
    _handlingCode = handlingCode;
  }
  /** Sets the property handlingCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Entity#setHandlingCode
  */
  public void setHandlingCodeForHibernate(CE handlingCode) {
    _handlingCode = handlingCode;
  }

  private /*AssociationSet*/List<org.hl7.rim.CommunicationFunction> _communicationFunction;
  /** Gets the property communicationFunction.
      @see org.hl7.rim.Entity#getCommunicationFunction
  */
  public /*AssociationSet*/List<org.hl7.rim.CommunicationFunction> getCommunicationFunction() {
    return _communicationFunction;
  }
  /** Sets the property communicationFunction.
      @see org.hl7.rim.Entity#setCommunicationFunction
  */
  public void setCommunicationFunction(/*AssociationSet*/List<org.hl7.rim.CommunicationFunction> communicationFunction) {
    _communicationFunction = communicationFunction;
  }
  /** Adds an association communicationFunction.
      @see org.hl7.rim.Entity#setCommunicationFunction
  */
  public void addCommunicationFunction(org.hl7.rim.CommunicationFunction communicationFunction) {
        // create the association set if it doesn't exist already
    if(_communicationFunction == null) _communicationFunction = new AssociationSetImpl<org.hl7.rim.CommunicationFunction>();
    // add the association to the association set
    getCommunicationFunction().add(communicationFunction);
  }

  private /*AssociationSet*/List<org.hl7.rim.LanguageCommunication> _languageCommunication;
  /** Gets the property languageCommunication.
      @see org.hl7.rim.Entity#getLanguageCommunication
  */
  public /*AssociationSet*/List<org.hl7.rim.LanguageCommunication> getLanguageCommunication() {
    return _languageCommunication;
  }
  /** Sets the property languageCommunication.
      @see org.hl7.rim.Entity#setLanguageCommunication
  */
  public void setLanguageCommunication(/*AssociationSet*/List<org.hl7.rim.LanguageCommunication> languageCommunication) {
    _languageCommunication = languageCommunication;
  }
  /** Adds an association languageCommunication.
      @see org.hl7.rim.Entity#setLanguageCommunication
  */
  public void addLanguageCommunication(org.hl7.rim.LanguageCommunication languageCommunication) {
        // create the association set if it doesn't exist already
    if(_languageCommunication == null) _languageCommunication = new AssociationSetImpl<org.hl7.rim.LanguageCommunication>();
    // add the association to the association set
    getLanguageCommunication().add(languageCommunication);
    // make the inverse link
    languageCommunication.setEntity(this);
  }

  private /*AssociationSet*/List<org.hl7.rim.Role> _playedRole;
  /** Gets the property playedRole.
      @see org.hl7.rim.Entity#getPlayedRole
  */
  public /*AssociationSet*/List<org.hl7.rim.Role> getPlayedRole() {
    return _playedRole;
  }
  /** Sets the property playedRole.
      @see org.hl7.rim.Entity#setPlayedRole
  */
  public void setPlayedRole(/*AssociationSet*/List<org.hl7.rim.Role> playedRole) {
    _playedRole = playedRole;
  }
  /** Adds an association playedRole.
      @see org.hl7.rim.Entity#setPlayedRole
  */
  public void addPlayedRole(org.hl7.rim.Role playedRole) {
        // create the association set if it doesn't exist already
    if(_playedRole == null) _playedRole = new AssociationSetImpl<org.hl7.rim.Role>();
    // add the association to the association set
    getPlayedRole().add(playedRole);
    // make the inverse link
    playedRole.setPlayer(this);
  }

  private /*AssociationSet*/List<org.hl7.rim.Role> _scopedRole;
  /** Gets the property scopedRole.
      @see org.hl7.rim.Entity#getScopedRole
  */
  public /*AssociationSet*/List<org.hl7.rim.Role> getScopedRole() {
    return _scopedRole;
  }
  /** Sets the property scopedRole.
      @see org.hl7.rim.Entity#setScopedRole
  */
  public void setScopedRole(/*AssociationSet*/List<org.hl7.rim.Role> scopedRole) {
    _scopedRole = scopedRole;
  }
  /** Adds an association scopedRole.
      @see org.hl7.rim.Entity#setScopedRole
  */
  public void addScopedRole(org.hl7.rim.Role scopedRole) {
        // create the association set if it doesn't exist already
    if(_scopedRole == null) _scopedRole = new AssociationSetImpl<org.hl7.rim.Role>();
    // add the association to the association set
    getScopedRole().add(scopedRole);
    // make the inverse link
    scopedRole.setScoper(this);
  }
  public Object clone() throws CloneNotSupportedException {
    EntityImpl that = (EntityImpl) super.clone();

    // deep clone of persistent component collections
    that.setId(that.getId());
    that.setQuantity(that.getQuantity());
    that.setName(that.getName());
    that.setTelecom(that.getTelecom());
    that._communicationFunction= null;
    that._languageCommunication= null;
    that._playedRole= null;
    that._scopedRole= null;
    return that;
  }
}

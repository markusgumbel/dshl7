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
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Role as a simple data holder bean.
    @see org.hl7.rim.Role
  */
public class RoleImpl extends BasicRoleImpl implements Role {

  private SET<II> _id;
  /** Gets the property id.
      @see org.hl7.rim.Role#getId
  */
  public SET<II> getId() { return _id; }
  /** Sets the property id.
      @see org.hl7.rim.Role#setId
  */
  public void setId(SET<II> id) {
    if(id instanceof org.hl7.hibernate.ClonableCollection)
      id = ((org.hl7.hibernate.ClonableCollection<SET<II>>) id).cloneHibernateCollectionIfNecessary();
    _id = id;
  }
  /** Sets the property id. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setId
  */
  public void setIdForHibernate(SET<II> id) {
    _id = id;
  }

  private CE _code;
  /** Gets the property code.
      @see org.hl7.rim.Role#getCode
  */
  public CE getCode() { return _code; }
  /** Sets the property code.
      @see org.hl7.rim.Role#setCode
  */
  public void setCode(CE code) {
    if(code instanceof org.hl7.hibernate.ClonableCollection)
      code = ((org.hl7.hibernate.ClonableCollection<CE>) code).cloneHibernateCollectionIfNecessary();
    _code = code;
  }
  /** Sets the property code. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setCode
  */
  public void setCodeForHibernate(CE code) {
    _code = code;
  }

  private BL _negationInd;
  /** Gets the property negationInd.
      @see org.hl7.rim.Role#getNegationInd
  */
  public BL getNegationInd() { return _negationInd; }
  /** Sets the property negationInd.
      @see org.hl7.rim.Role#setNegationInd
  */
  public void setNegationInd(BL negationInd) {
    if(negationInd instanceof org.hl7.hibernate.ClonableCollection)
      negationInd = ((org.hl7.hibernate.ClonableCollection<BL>) negationInd).cloneHibernateCollectionIfNecessary();
    _negationInd = negationInd;
  }
  /** Sets the property negationInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setNegationInd
  */
  public void setNegationIndForHibernate(BL negationInd) {
    _negationInd = negationInd;
  }

  private BAG<EN> _name;
  /** Gets the property name.
      @see org.hl7.rim.Role#getName
  */
  public BAG<EN> getName() { return _name; }
  /** Sets the property name.
      @see org.hl7.rim.Role#setName
  */
  public void setName(BAG<EN> name) {
    if(name instanceof org.hl7.hibernate.ClonableCollection)
      name = ((org.hl7.hibernate.ClonableCollection<BAG<EN>>) name).cloneHibernateCollectionIfNecessary();
    _name = name;
  }
  /** Sets the property name. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setName
  */
  public void setNameForHibernate(BAG<EN> name) {
    _name = name;
  }

  private BAG<AD> _addr;
  /** Gets the property addr.
      @see org.hl7.rim.Role#getAddr
  */
  public BAG<AD> getAddr() { return _addr; }
  /** Sets the property addr.
      @see org.hl7.rim.Role#setAddr
  */
  public void setAddr(BAG<AD> addr) {
    if(addr instanceof org.hl7.hibernate.ClonableCollection)
      addr = ((org.hl7.hibernate.ClonableCollection<BAG<AD>>) addr).cloneHibernateCollectionIfNecessary();
    _addr = addr;
  }
  /** Sets the property addr. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setAddr
  */
  public void setAddrForHibernate(BAG<AD> addr) {
    _addr = addr;
  }

  private BAG<TEL> _telecom;
  /** Gets the property telecom.
      @see org.hl7.rim.Role#getTelecom
  */
  public BAG<TEL> getTelecom() { return _telecom; }
  /** Sets the property telecom.
      @see org.hl7.rim.Role#setTelecom
  */
  public void setTelecom(BAG<TEL> telecom) {
    if(telecom instanceof org.hl7.hibernate.ClonableCollection)
      telecom = ((org.hl7.hibernate.ClonableCollection<BAG<TEL>>) telecom).cloneHibernateCollectionIfNecessary();
    _telecom = telecom;
  }
  /** Sets the property telecom. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setTelecom
  */
  public void setTelecomForHibernate(BAG<TEL> telecom) {
    _telecom = telecom;
  }

  private CS _statusCode;
  /** Gets the property statusCode.
      @see org.hl7.rim.Role#getStatusCode
  */
  public CS getStatusCode() { return _statusCode; }
  /** Sets the property statusCode.
      @see org.hl7.rim.Role#setStatusCode
  */
  public void setStatusCode(CS statusCode) {
    if(statusCode instanceof org.hl7.hibernate.ClonableCollection)
      statusCode = ((org.hl7.hibernate.ClonableCollection<CS>) statusCode).cloneHibernateCollectionIfNecessary();
    _statusCode = statusCode;
  }
  /** Sets the property statusCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setStatusCode
  */
  public void setStatusCodeForHibernate(CS statusCode) {
    _statusCode = statusCode;
  }

  private IVL<TS> _effectiveTime;
  /** Gets the property effectiveTime.
      @see org.hl7.rim.Role#getEffectiveTime
  */
  public IVL<TS> getEffectiveTime() { return _effectiveTime; }
  /** Sets the property effectiveTime.
      @see org.hl7.rim.Role#setEffectiveTime
  */
  public void setEffectiveTime(IVL<TS> effectiveTime) {
    if(effectiveTime instanceof org.hl7.hibernate.ClonableCollection)
      effectiveTime = ((org.hl7.hibernate.ClonableCollection<IVL<TS>>) effectiveTime).cloneHibernateCollectionIfNecessary();
    _effectiveTime = effectiveTime;
  }
  /** Sets the property effectiveTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setEffectiveTime
  */
  public void setEffectiveTimeForHibernate(IVL<TS> effectiveTime) {
    _effectiveTime = effectiveTime;
  }

  private ED _certificateText;
  /** Gets the property certificateText.
      @see org.hl7.rim.Role#getCertificateText
  */
  public ED getCertificateText() { return _certificateText; }
  /** Sets the property certificateText.
      @see org.hl7.rim.Role#setCertificateText
  */
  public void setCertificateText(ED certificateText) {
    if(certificateText instanceof org.hl7.hibernate.ClonableCollection)
      certificateText = ((org.hl7.hibernate.ClonableCollection<ED>) certificateText).cloneHibernateCollectionIfNecessary();
    _certificateText = certificateText;
  }
  /** Sets the property certificateText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setCertificateText
  */
  public void setCertificateTextForHibernate(ED certificateText) {
    _certificateText = certificateText;
  }

  private SET<CE> _confidentialityCode;
  /** Gets the property confidentialityCode.
      @see org.hl7.rim.Role#getConfidentialityCode
  */
  public SET<CE> getConfidentialityCode() { return _confidentialityCode; }
  /** Sets the property confidentialityCode.
      @see org.hl7.rim.Role#setConfidentialityCode
  */
  public void setConfidentialityCode(SET<CE> confidentialityCode) {
    if(confidentialityCode instanceof org.hl7.hibernate.ClonableCollection)
      confidentialityCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) confidentialityCode).cloneHibernateCollectionIfNecessary();
    _confidentialityCode = confidentialityCode;
  }
  /** Sets the property confidentialityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setConfidentialityCode
  */
  public void setConfidentialityCodeForHibernate(SET<CE> confidentialityCode) {
    _confidentialityCode = confidentialityCode;
  }

  private RTO _quantity;
  /** Gets the property quantity.
      @see org.hl7.rim.Role#getQuantity
  */
  public RTO getQuantity() { return _quantity; }
  /** Sets the property quantity.
      @see org.hl7.rim.Role#setQuantity
  */
  public void setQuantity(RTO quantity) {
    if(quantity instanceof org.hl7.hibernate.ClonableCollection)
      quantity = ((org.hl7.hibernate.ClonableCollection<RTO>) quantity).cloneHibernateCollectionIfNecessary();
    _quantity = quantity;
  }
  /** Sets the property quantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setQuantity
  */
  public void setQuantityForHibernate(RTO quantity) {
    _quantity = quantity;
  }

  private LIST<INT> _positionNumber;
  /** Gets the property positionNumber.
      @see org.hl7.rim.Role#getPositionNumber
  */
  public LIST<INT> getPositionNumber() { return _positionNumber; }
  /** Sets the property positionNumber.
      @see org.hl7.rim.Role#setPositionNumber
  */
  public void setPositionNumber(LIST<INT> positionNumber) {
    if(positionNumber instanceof org.hl7.hibernate.ClonableCollection)
      positionNumber = ((org.hl7.hibernate.ClonableCollection<LIST<INT>>) positionNumber).cloneHibernateCollectionIfNecessary();
    _positionNumber = positionNumber;
  }
  /** Sets the property positionNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Role#setPositionNumber
  */
  public void setPositionNumberForHibernate(LIST<INT> positionNumber) {
    _positionNumber = positionNumber;
  }

  private /*AssociationSet*/List<org.hl7.rim.Participation> _participation;
  /** Gets the property participation.
      @see org.hl7.rim.Role#getParticipation
  */
  public /*AssociationSet*/List<org.hl7.rim.Participation> getParticipation() {
    return _participation;
  }
  /** Sets the property participation.
      @see org.hl7.rim.Role#setParticipation
  */
  public void setParticipation(/*AssociationSet*/List<org.hl7.rim.Participation> participation) {
    _participation = participation;
  }
  /** Adds an association participation.
      @see org.hl7.rim.Role#setParticipation
  */
  public void addParticipation(org.hl7.rim.Participation participation) {
        // create the association set if it doesn't exist already
    if(_participation == null) _participation = new AssociationSetImpl<org.hl7.rim.Participation>();
    // add the association to the association set
    getParticipation().add(participation);
    // make the inverse link
    participation.setRole(this);
  }

  private org.hl7.rim.Entity _player;
  /** Gets the property player.
      @see org.hl7.rim.Role#getPlayer
  */
  public org.hl7.rim.Entity getPlayer() {
    return _player;
  }
  /** Sets the property player.
      @see org.hl7.rim.Role#setPlayer
  */
  public void setPlayer(org.hl7.rim.Entity player) {
    _player = player;
  }

  private org.hl7.rim.Entity _scoper;
  /** Gets the property scoper.
      @see org.hl7.rim.Role#getScoper
  */
  public org.hl7.rim.Entity getScoper() {
    return _scoper;
  }
  /** Sets the property scoper.
      @see org.hl7.rim.Role#setScoper
  */
  public void setScoper(org.hl7.rim.Entity scoper) {
    _scoper = scoper;
  }

  private /*AssociationSet*/List<org.hl7.rim.RoleLink> _outboundLink;
  /** Gets the property outboundLink.
      @see org.hl7.rim.Role#getOutboundLink
  */
  public /*AssociationSet*/List<org.hl7.rim.RoleLink> getOutboundLink() {
    return _outboundLink;
  }
  /** Sets the property outboundLink.
      @see org.hl7.rim.Role#setOutboundLink
  */
  public void setOutboundLink(/*AssociationSet*/List<org.hl7.rim.RoleLink> outboundLink) {
    _outboundLink = outboundLink;
  }
  /** Adds an association outboundLink.
      @see org.hl7.rim.Role#setOutboundLink
  */
  public void addOutboundLink(org.hl7.rim.RoleLink outboundLink) {
        // create the association set if it doesn't exist already
    if(_outboundLink == null) _outboundLink = new AssociationSetImpl<org.hl7.rim.RoleLink>();
    // add the association to the association set
    getOutboundLink().add(outboundLink);
    // make the inverse link
    outboundLink.setSource(this);
  }

  private /*AssociationSet*/List<org.hl7.rim.RoleLink> _inboundLink;
  /** Gets the property inboundLink.
      @see org.hl7.rim.Role#getInboundLink
  */
  public /*AssociationSet*/List<org.hl7.rim.RoleLink> getInboundLink() {
    return _inboundLink;
  }
  /** Sets the property inboundLink.
      @see org.hl7.rim.Role#setInboundLink
  */
  public void setInboundLink(/*AssociationSet*/List<org.hl7.rim.RoleLink> inboundLink) {
    _inboundLink = inboundLink;
  }
  /** Adds an association inboundLink.
      @see org.hl7.rim.Role#setInboundLink
  */
  public void addInboundLink(org.hl7.rim.RoleLink inboundLink) {
        // create the association set if it doesn't exist already
    if(_inboundLink == null) _inboundLink = new AssociationSetImpl<org.hl7.rim.RoleLink>();
    // add the association to the association set
    getInboundLink().add(inboundLink);
    // make the inverse link
    inboundLink.setTarget(this);
  }
  public Object clone() throws CloneNotSupportedException {
    RoleImpl that = (RoleImpl) super.clone();

    // deep clone of persistent component collections
    that.setId(that.getId());
    that.setName(that.getName());
    that.setAddr(that.getAddr());
    that.setTelecom(that.getTelecom());
    that.setConfidentialityCode(that.getConfidentialityCode());
    that.setPositionNumber(that.getPositionNumber());
    that._participation= null;
    that._outboundLink= null;
    that._inboundLink= null;
    return that;
  }
}

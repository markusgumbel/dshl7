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
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Act as a simple data holder bean.
    @see org.hl7.rim.Act
  */
public class ActImpl extends BasicActImpl implements Act {

  private SET<II> _id;
  /** Gets the property id.
      @see org.hl7.rim.Act#getId
  */
  public SET<II> getId() { return _id; }
  /** Sets the property id.
      @see org.hl7.rim.Act#setId
  */
  public void setId(SET<II> id) {
    if(id instanceof org.hl7.hibernate.ClonableCollection)
      id = ((org.hl7.hibernate.ClonableCollection<SET<II>>) id).cloneHibernateCollectionIfNecessary();
    _id = id;
  }
  /** Sets the property id. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setId
  */
  public void setIdForHibernate(SET<II> id) {
    _id = id;
  }

  private CD _code;
  /** Gets the property code.
      @see org.hl7.rim.Act#getCode
  */
  public CD getCode() { return _code; }
  /** Sets the property code.
      @see org.hl7.rim.Act#setCode
  */
  public void setCode(CD code) {
    if(code instanceof org.hl7.hibernate.ClonableCollection)
      code = ((org.hl7.hibernate.ClonableCollection<CD>) code).cloneHibernateCollectionIfNecessary();
    _code = code;
  }
  /** Sets the property code. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setCode
  */
  public void setCodeForHibernate(CD code) {
    _code = code;
  }

  private BL _negationInd;
  /** Gets the property negationInd.
      @see org.hl7.rim.Act#getNegationInd
  */
  public BL getNegationInd() { return _negationInd; }
  /** Sets the property negationInd.
      @see org.hl7.rim.Act#setNegationInd
  */
  public void setNegationInd(BL negationInd) {
    if(negationInd instanceof org.hl7.hibernate.ClonableCollection)
      negationInd = ((org.hl7.hibernate.ClonableCollection<BL>) negationInd).cloneHibernateCollectionIfNecessary();
    _negationInd = negationInd;
  }
  /** Sets the property negationInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setNegationInd
  */
  public void setNegationIndForHibernate(BL negationInd) {
    _negationInd = negationInd;
  }

  private ST _derivationExpr;
  /** Gets the property derivationExpr.
      @see org.hl7.rim.Act#getDerivationExpr
  */
  public ST getDerivationExpr() { return _derivationExpr; }
  /** Sets the property derivationExpr.
      @see org.hl7.rim.Act#setDerivationExpr
  */
  public void setDerivationExpr(ST derivationExpr) {
    if(derivationExpr instanceof org.hl7.hibernate.ClonableCollection)
      derivationExpr = ((org.hl7.hibernate.ClonableCollection<ST>) derivationExpr).cloneHibernateCollectionIfNecessary();
    _derivationExpr = derivationExpr;
  }
  /** Sets the property derivationExpr. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setDerivationExpr
  */
  public void setDerivationExprForHibernate(ST derivationExpr) {
    _derivationExpr = derivationExpr;
  }

  private ED _title;
  /** Gets the property title.
      @see org.hl7.rim.Act#getTitle
  */
  public ED getTitle() { return _title; }
  /** Sets the property title.
      @see org.hl7.rim.Act#setTitle
  */
  public void setTitle(ED title) {
    if(title instanceof org.hl7.hibernate.ClonableCollection)
      title = ((org.hl7.hibernate.ClonableCollection<ED>) title).cloneHibernateCollectionIfNecessary();
    _title = title;
  }
  /** Sets the property title. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setTitle
  */
  public void setTitleForHibernate(ED title) {
    _title = title;
  }

  private ED _text;
  /** Gets the property text.
      @see org.hl7.rim.Act#getText
  */
  public ED getText() { return _text; }
  /** Sets the property text.
      @see org.hl7.rim.Act#setText
  */
  public void setText(ED text) {
    if(text instanceof org.hl7.hibernate.ClonableCollection)
      text = ((org.hl7.hibernate.ClonableCollection<ED>) text).cloneHibernateCollectionIfNecessary();
    _text = text;
  }
  /** Sets the property text. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setText
  */
  public void setTextForHibernate(ED text) {
    _text = text;
  }

  private CS _statusCode;
  /** Gets the property statusCode.
      @see org.hl7.rim.Act#getStatusCode
  */
  public CS getStatusCode() { return _statusCode; }
  /** Sets the property statusCode.
      @see org.hl7.rim.Act#setStatusCode
  */
  public void setStatusCode(CS statusCode) {
    if(statusCode instanceof org.hl7.hibernate.ClonableCollection)
      statusCode = ((org.hl7.hibernate.ClonableCollection<CS>) statusCode).cloneHibernateCollectionIfNecessary();
    _statusCode = statusCode;
  }
  /** Sets the property statusCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setStatusCode
  */
  public void setStatusCodeForHibernate(CS statusCode) {
    _statusCode = statusCode;
  }

  private SET<TS> _effectiveTime;
  /** Gets the property effectiveTime.
      @see org.hl7.rim.Act#getEffectiveTime
  */
  public SET<TS> getEffectiveTime() { return _effectiveTime; }
  /** Sets the property effectiveTime.
      @see org.hl7.rim.Act#setEffectiveTime
  */
  public void setEffectiveTime(SET<TS> effectiveTime) {
    if(effectiveTime instanceof org.hl7.hibernate.ClonableCollection)
      effectiveTime = ((org.hl7.hibernate.ClonableCollection<SET<TS>>) effectiveTime).cloneHibernateCollectionIfNecessary();
    _effectiveTime = effectiveTime;
  }
  /** Sets the property effectiveTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setEffectiveTime
  */
  public void setEffectiveTimeForHibernate(SET<TS> effectiveTime) {
    _effectiveTime = effectiveTime;
  }

  private SET<TS> _activityTime;
  /** Gets the property activityTime.
      @see org.hl7.rim.Act#getActivityTime
  */
  public SET<TS> getActivityTime() { return _activityTime; }
  /** Sets the property activityTime.
      @see org.hl7.rim.Act#setActivityTime
  */
  public void setActivityTime(SET<TS> activityTime) {
    if(activityTime instanceof org.hl7.hibernate.ClonableCollection)
      activityTime = ((org.hl7.hibernate.ClonableCollection<SET<TS>>) activityTime).cloneHibernateCollectionIfNecessary();
    _activityTime = activityTime;
  }
  /** Sets the property activityTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setActivityTime
  */
  public void setActivityTimeForHibernate(SET<TS> activityTime) {
    _activityTime = activityTime;
  }

  private TS _availabilityTime;
  /** Gets the property availabilityTime.
      @see org.hl7.rim.Act#getAvailabilityTime
  */
  public TS getAvailabilityTime() { return _availabilityTime; }
  /** Sets the property availabilityTime.
      @see org.hl7.rim.Act#setAvailabilityTime
  */
  public void setAvailabilityTime(TS availabilityTime) {
    if(availabilityTime instanceof org.hl7.hibernate.ClonableCollection)
      availabilityTime = ((org.hl7.hibernate.ClonableCollection<TS>) availabilityTime).cloneHibernateCollectionIfNecessary();
    _availabilityTime = availabilityTime;
  }
  /** Sets the property availabilityTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setAvailabilityTime
  */
  public void setAvailabilityTimeForHibernate(TS availabilityTime) {
    _availabilityTime = availabilityTime;
  }

  private SET<CE> _priorityCode;
  /** Gets the property priorityCode.
      @see org.hl7.rim.Act#getPriorityCode
  */
  public SET<CE> getPriorityCode() { return _priorityCode; }
  /** Sets the property priorityCode.
      @see org.hl7.rim.Act#setPriorityCode
  */
  public void setPriorityCode(SET<CE> priorityCode) {
    if(priorityCode instanceof org.hl7.hibernate.ClonableCollection)
      priorityCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) priorityCode).cloneHibernateCollectionIfNecessary();
    _priorityCode = priorityCode;
  }
  /** Sets the property priorityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setPriorityCode
  */
  public void setPriorityCodeForHibernate(SET<CE> priorityCode) {
    _priorityCode = priorityCode;
  }

  private SET<CE> _confidentialityCode;
  /** Gets the property confidentialityCode.
      @see org.hl7.rim.Act#getConfidentialityCode
  */
  public SET<CE> getConfidentialityCode() { return _confidentialityCode; }
  /** Sets the property confidentialityCode.
      @see org.hl7.rim.Act#setConfidentialityCode
  */
  public void setConfidentialityCode(SET<CE> confidentialityCode) {
    if(confidentialityCode instanceof org.hl7.hibernate.ClonableCollection)
      confidentialityCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) confidentialityCode).cloneHibernateCollectionIfNecessary();
    _confidentialityCode = confidentialityCode;
  }
  /** Sets the property confidentialityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setConfidentialityCode
  */
  public void setConfidentialityCodeForHibernate(SET<CE> confidentialityCode) {
    _confidentialityCode = confidentialityCode;
  }

  private IVL<INT> _repeatNumber;
  /** Gets the property repeatNumber.
      @see org.hl7.rim.Act#getRepeatNumber
  */
  public IVL<INT> getRepeatNumber() { return _repeatNumber; }
  /** Sets the property repeatNumber.
      @see org.hl7.rim.Act#setRepeatNumber
  */
  public void setRepeatNumber(IVL<INT> repeatNumber) {
    if(repeatNumber instanceof org.hl7.hibernate.ClonableCollection)
      repeatNumber = ((org.hl7.hibernate.ClonableCollection<IVL<INT>>) repeatNumber).cloneHibernateCollectionIfNecessary();
    _repeatNumber = repeatNumber;
  }
  /** Sets the property repeatNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setRepeatNumber
  */
  public void setRepeatNumberForHibernate(IVL<INT> repeatNumber) {
    _repeatNumber = repeatNumber;
  }

  private BL _interruptibleInd;
  /** Gets the property interruptibleInd.
      @see org.hl7.rim.Act#getInterruptibleInd
  */
  public BL getInterruptibleInd() { return _interruptibleInd; }
  /** Sets the property interruptibleInd.
      @see org.hl7.rim.Act#setInterruptibleInd
  */
  public void setInterruptibleInd(BL interruptibleInd) {
    if(interruptibleInd instanceof org.hl7.hibernate.ClonableCollection)
      interruptibleInd = ((org.hl7.hibernate.ClonableCollection<BL>) interruptibleInd).cloneHibernateCollectionIfNecessary();
    _interruptibleInd = interruptibleInd;
  }
  /** Sets the property interruptibleInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setInterruptibleInd
  */
  public void setInterruptibleIndForHibernate(BL interruptibleInd) {
    _interruptibleInd = interruptibleInd;
  }

  private CE _levelCode;
  /** Gets the property levelCode.
      @see org.hl7.rim.Act#getLevelCode
  */
  public CE getLevelCode() { return _levelCode; }
  /** Sets the property levelCode.
      @see org.hl7.rim.Act#setLevelCode
  */
  public void setLevelCode(CE levelCode) {
    if(levelCode instanceof org.hl7.hibernate.ClonableCollection)
      levelCode = ((org.hl7.hibernate.ClonableCollection<CE>) levelCode).cloneHibernateCollectionIfNecessary();
    _levelCode = levelCode;
  }
  /** Sets the property levelCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setLevelCode
  */
  public void setLevelCodeForHibernate(CE levelCode) {
    _levelCode = levelCode;
  }

  private BL _independentInd;
  /** Gets the property independentInd.
      @see org.hl7.rim.Act#getIndependentInd
  */
  public BL getIndependentInd() { return _independentInd; }
  /** Sets the property independentInd.
      @see org.hl7.rim.Act#setIndependentInd
  */
  public void setIndependentInd(BL independentInd) {
    if(independentInd instanceof org.hl7.hibernate.ClonableCollection)
      independentInd = ((org.hl7.hibernate.ClonableCollection<BL>) independentInd).cloneHibernateCollectionIfNecessary();
    _independentInd = independentInd;
  }
  /** Sets the property independentInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setIndependentInd
  */
  public void setIndependentIndForHibernate(BL independentInd) {
    _independentInd = independentInd;
  }

  private CE _uncertaintyCode;
  /** Gets the property uncertaintyCode.
      @see org.hl7.rim.Act#getUncertaintyCode
  */
  public CE getUncertaintyCode() { return _uncertaintyCode; }
  /** Sets the property uncertaintyCode.
      @see org.hl7.rim.Act#setUncertaintyCode
  */
  public void setUncertaintyCode(CE uncertaintyCode) {
    if(uncertaintyCode instanceof org.hl7.hibernate.ClonableCollection)
      uncertaintyCode = ((org.hl7.hibernate.ClonableCollection<CE>) uncertaintyCode).cloneHibernateCollectionIfNecessary();
    _uncertaintyCode = uncertaintyCode;
  }
  /** Sets the property uncertaintyCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setUncertaintyCode
  */
  public void setUncertaintyCodeForHibernate(CE uncertaintyCode) {
    _uncertaintyCode = uncertaintyCode;
  }

  private SET<CE> _reasonCode;
  /** Gets the property reasonCode.
      @see org.hl7.rim.Act#getReasonCode
  */
  public SET<CE> getReasonCode() { return _reasonCode; }
  /** Sets the property reasonCode.
      @see org.hl7.rim.Act#setReasonCode
  */
  public void setReasonCode(SET<CE> reasonCode) {
    if(reasonCode instanceof org.hl7.hibernate.ClonableCollection)
      reasonCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) reasonCode).cloneHibernateCollectionIfNecessary();
    _reasonCode = reasonCode;
  }
  /** Sets the property reasonCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setReasonCode
  */
  public void setReasonCodeForHibernate(SET<CE> reasonCode) {
    _reasonCode = reasonCode;
  }

  private CE _languageCode;
  /** Gets the property languageCode.
      @see org.hl7.rim.Act#getLanguageCode
  */
  public CE getLanguageCode() { return _languageCode; }
  /** Sets the property languageCode.
      @see org.hl7.rim.Act#setLanguageCode
  */
  public void setLanguageCode(CE languageCode) {
    if(languageCode instanceof org.hl7.hibernate.ClonableCollection)
      languageCode = ((org.hl7.hibernate.ClonableCollection<CE>) languageCode).cloneHibernateCollectionIfNecessary();
    _languageCode = languageCode;
  }
  /** Sets the property languageCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Act#setLanguageCode
  */
  public void setLanguageCodeForHibernate(CE languageCode) {
    _languageCode = languageCode;
  }

  private /*AssociationSet*/List<org.hl7.rim.ActRelationship> _outboundRelationship;
  /** Gets the property outboundRelationship.
      @see org.hl7.rim.Act#getOutboundRelationship
  */
  public /*AssociationSet*/List<org.hl7.rim.ActRelationship> getOutboundRelationship() {
    return _outboundRelationship;
  }
  /** Sets the property outboundRelationship.
      @see org.hl7.rim.Act#setOutboundRelationship
  */
  public void setOutboundRelationship(/*AssociationSet*/List<org.hl7.rim.ActRelationship> outboundRelationship) {
    _outboundRelationship = outboundRelationship;
  }
  /** Adds an association outboundRelationship.
      @see org.hl7.rim.Act#setOutboundRelationship
  */
  public void addOutboundRelationship(org.hl7.rim.ActRelationship outboundRelationship) {
        // create the association set if it doesn't exist already
    if(_outboundRelationship == null) _outboundRelationship = new AssociationSetImpl<org.hl7.rim.ActRelationship>();
    // add the association to the association set
    getOutboundRelationship().add(outboundRelationship);
    // make the inverse link
    outboundRelationship.setSource(this);
  }

  private /*AssociationSet*/List<org.hl7.rim.ActRelationship> _inboundRelationship;
  /** Gets the property inboundRelationship.
      @see org.hl7.rim.Act#getInboundRelationship
  */
  public /*AssociationSet*/List<org.hl7.rim.ActRelationship> getInboundRelationship() {
    return _inboundRelationship;
  }
  /** Sets the property inboundRelationship.
      @see org.hl7.rim.Act#setInboundRelationship
  */
  public void setInboundRelationship(/*AssociationSet*/List<org.hl7.rim.ActRelationship> inboundRelationship) {
    _inboundRelationship = inboundRelationship;
  }
  /** Adds an association inboundRelationship.
      @see org.hl7.rim.Act#setInboundRelationship
  */
  public void addInboundRelationship(org.hl7.rim.ActRelationship inboundRelationship) {
        // create the association set if it doesn't exist already
    if(_inboundRelationship == null) _inboundRelationship = new AssociationSetImpl<org.hl7.rim.ActRelationship>();
    // add the association to the association set
    getInboundRelationship().add(inboundRelationship);
    // make the inverse link
    inboundRelationship.setTarget(this);
  }

  private /*AssociationSet*/List<org.hl7.rim.Participation> _participation;
  /** Gets the property participation.
      @see org.hl7.rim.Act#getParticipation
  */
  public /*AssociationSet*/List<org.hl7.rim.Participation> getParticipation() {
    return _participation;
  }
  /** Sets the property participation.
      @see org.hl7.rim.Act#setParticipation
  */
  public void setParticipation(/*AssociationSet*/List<org.hl7.rim.Participation> participation) {
    _participation = participation;
  }
  /** Adds an association participation.
      @see org.hl7.rim.Act#setParticipation
  */
  public void addParticipation(org.hl7.rim.Participation participation) {
        // create the association set if it doesn't exist already
    if(_participation == null) _participation = new AssociationSetImpl<org.hl7.rim.Participation>();
    // add the association to the association set
    getParticipation().add(participation);
    // make the inverse link
    participation.setAct(this);
  }
  public Object clone() throws CloneNotSupportedException {
    ActImpl that = (ActImpl) super.clone();

    // deep clone of persistent component collections
    that.setId(that.getId());
    that.setPriorityCode(that.getPriorityCode());
    that.setConfidentialityCode(that.getConfidentialityCode());
    that.setReasonCode(that.getReasonCode());
    that._outboundRelationship= null;
    that._inboundRelationship= null;
    that._participation= null;
    return that;
  }
}

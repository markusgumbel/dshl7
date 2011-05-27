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

import org.hl7.rim.ActRelationship;
import org.hl7.types.CS;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.PQ;
import org.hl7.types.ST;
import org.hl7.types.CE;

import org.hl7.rim.Act;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.ActRelationship as a simple data holder bean.
    @see org.hl7.rim.ActRelationship
  */
public class ActRelationshipImpl extends BasicActRelationshipImpl implements ActRelationship {

  private BL _inversionInd;
  /** Gets the property inversionInd.
      @see org.hl7.rim.ActRelationship#getInversionInd
  */
  public BL getInversionInd() { return _inversionInd; }
  /** Sets the property inversionInd.
      @see org.hl7.rim.ActRelationship#setInversionInd
  */
  public void setInversionInd(BL inversionInd) {
    if(inversionInd instanceof org.hl7.hibernate.ClonableCollection)
      inversionInd = ((org.hl7.hibernate.ClonableCollection<BL>) inversionInd).cloneHibernateCollectionIfNecessary();
    _inversionInd = inversionInd;
  }
  /** Sets the property inversionInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setInversionInd
  */
  public void setInversionIndForHibernate(BL inversionInd) {
    _inversionInd = inversionInd;
  }

  private CS _contextControlCode;
  /** Gets the property contextControlCode.
      @see org.hl7.rim.ActRelationship#getContextControlCode
  */
  public CS getContextControlCode() { return _contextControlCode; }
  /** Sets the property contextControlCode.
      @see org.hl7.rim.ActRelationship#setContextControlCode
  */
  public void setContextControlCode(CS contextControlCode) {
    if(contextControlCode instanceof org.hl7.hibernate.ClonableCollection)
      contextControlCode = ((org.hl7.hibernate.ClonableCollection<CS>) contextControlCode).cloneHibernateCollectionIfNecessary();
    _contextControlCode = contextControlCode;
  }
  /** Sets the property contextControlCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setContextControlCode
  */
  public void setContextControlCodeForHibernate(CS contextControlCode) {
    _contextControlCode = contextControlCode;
  }

  private BL _contextConductionInd;
  /** Gets the property contextConductionInd.
      @see org.hl7.rim.ActRelationship#getContextConductionInd
  */
  public BL getContextConductionInd() { return _contextConductionInd; }
  /** Sets the property contextConductionInd.
      @see org.hl7.rim.ActRelationship#setContextConductionInd
  */
  public void setContextConductionInd(BL contextConductionInd) {
    if(contextConductionInd instanceof org.hl7.hibernate.ClonableCollection)
      contextConductionInd = ((org.hl7.hibernate.ClonableCollection<BL>) contextConductionInd).cloneHibernateCollectionIfNecessary();
    _contextConductionInd = contextConductionInd;
  }
  /** Sets the property contextConductionInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setContextConductionInd
  */
  public void setContextConductionIndForHibernate(BL contextConductionInd) {
    _contextConductionInd = contextConductionInd;
  }

  private INT _sequenceNumber;
  /** Gets the property sequenceNumber.
      @see org.hl7.rim.ActRelationship#getSequenceNumber
  */
  public INT getSequenceNumber() { return _sequenceNumber; }
  /** Sets the property sequenceNumber.
      @see org.hl7.rim.ActRelationship#setSequenceNumber
  */
  public void setSequenceNumber(INT sequenceNumber) {
    if(sequenceNumber instanceof org.hl7.hibernate.ClonableCollection)
      sequenceNumber = ((org.hl7.hibernate.ClonableCollection<INT>) sequenceNumber).cloneHibernateCollectionIfNecessary();
    _sequenceNumber = sequenceNumber;
  }
  /** Sets the property sequenceNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setSequenceNumber
  */
  public void setSequenceNumberForHibernate(INT sequenceNumber) {
    _sequenceNumber = sequenceNumber;
  }

  private INT _priorityNumber;
  /** Gets the property priorityNumber.
      @see org.hl7.rim.ActRelationship#getPriorityNumber
  */
  public INT getPriorityNumber() { return _priorityNumber; }
  /** Sets the property priorityNumber.
      @see org.hl7.rim.ActRelationship#setPriorityNumber
  */
  public void setPriorityNumber(INT priorityNumber) {
    if(priorityNumber instanceof org.hl7.hibernate.ClonableCollection)
      priorityNumber = ((org.hl7.hibernate.ClonableCollection<INT>) priorityNumber).cloneHibernateCollectionIfNecessary();
    _priorityNumber = priorityNumber;
  }
  /** Sets the property priorityNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setPriorityNumber
  */
  public void setPriorityNumberForHibernate(INT priorityNumber) {
    _priorityNumber = priorityNumber;
  }

  private PQ _pauseQuantity;
  /** Gets the property pauseQuantity.
      @see org.hl7.rim.ActRelationship#getPauseQuantity
  */
  public PQ getPauseQuantity() { return _pauseQuantity; }
  /** Sets the property pauseQuantity.
      @see org.hl7.rim.ActRelationship#setPauseQuantity
  */
  public void setPauseQuantity(PQ pauseQuantity) {
    if(pauseQuantity instanceof org.hl7.hibernate.ClonableCollection)
      pauseQuantity = ((org.hl7.hibernate.ClonableCollection<PQ>) pauseQuantity).cloneHibernateCollectionIfNecessary();
    _pauseQuantity = pauseQuantity;
  }
  /** Sets the property pauseQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setPauseQuantity
  */
  public void setPauseQuantityForHibernate(PQ pauseQuantity) {
    _pauseQuantity = pauseQuantity;
  }

  private CS _checkpointCode;
  /** Gets the property checkpointCode.
      @see org.hl7.rim.ActRelationship#getCheckpointCode
  */
  public CS getCheckpointCode() { return _checkpointCode; }
  /** Sets the property checkpointCode.
      @see org.hl7.rim.ActRelationship#setCheckpointCode
  */
  public void setCheckpointCode(CS checkpointCode) {
    if(checkpointCode instanceof org.hl7.hibernate.ClonableCollection)
      checkpointCode = ((org.hl7.hibernate.ClonableCollection<CS>) checkpointCode).cloneHibernateCollectionIfNecessary();
    _checkpointCode = checkpointCode;
  }
  /** Sets the property checkpointCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setCheckpointCode
  */
  public void setCheckpointCodeForHibernate(CS checkpointCode) {
    _checkpointCode = checkpointCode;
  }

  private CS _splitCode;
  /** Gets the property splitCode.
      @see org.hl7.rim.ActRelationship#getSplitCode
  */
  public CS getSplitCode() { return _splitCode; }
  /** Sets the property splitCode.
      @see org.hl7.rim.ActRelationship#setSplitCode
  */
  public void setSplitCode(CS splitCode) {
    if(splitCode instanceof org.hl7.hibernate.ClonableCollection)
      splitCode = ((org.hl7.hibernate.ClonableCollection<CS>) splitCode).cloneHibernateCollectionIfNecessary();
    _splitCode = splitCode;
  }
  /** Sets the property splitCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setSplitCode
  */
  public void setSplitCodeForHibernate(CS splitCode) {
    _splitCode = splitCode;
  }

  private CS _joinCode;
  /** Gets the property joinCode.
      @see org.hl7.rim.ActRelationship#getJoinCode
  */
  public CS getJoinCode() { return _joinCode; }
  /** Sets the property joinCode.
      @see org.hl7.rim.ActRelationship#setJoinCode
  */
  public void setJoinCode(CS joinCode) {
    if(joinCode instanceof org.hl7.hibernate.ClonableCollection)
      joinCode = ((org.hl7.hibernate.ClonableCollection<CS>) joinCode).cloneHibernateCollectionIfNecessary();
    _joinCode = joinCode;
  }
  /** Sets the property joinCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setJoinCode
  */
  public void setJoinCodeForHibernate(CS joinCode) {
    _joinCode = joinCode;
  }

  private BL _negationInd;
  /** Gets the property negationInd.
      @see org.hl7.rim.ActRelationship#getNegationInd
  */
  public BL getNegationInd() { return _negationInd; }
  /** Sets the property negationInd.
      @see org.hl7.rim.ActRelationship#setNegationInd
  */
  public void setNegationInd(BL negationInd) {
    if(negationInd instanceof org.hl7.hibernate.ClonableCollection)
      negationInd = ((org.hl7.hibernate.ClonableCollection<BL>) negationInd).cloneHibernateCollectionIfNecessary();
    _negationInd = negationInd;
  }
  /** Sets the property negationInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setNegationInd
  */
  public void setNegationIndForHibernate(BL negationInd) {
    _negationInd = negationInd;
  }

  private CS _conjunctionCode;
  /** Gets the property conjunctionCode.
      @see org.hl7.rim.ActRelationship#getConjunctionCode
  */
  public CS getConjunctionCode() { return _conjunctionCode; }
  /** Sets the property conjunctionCode.
      @see org.hl7.rim.ActRelationship#setConjunctionCode
  */
  public void setConjunctionCode(CS conjunctionCode) {
    if(conjunctionCode instanceof org.hl7.hibernate.ClonableCollection)
      conjunctionCode = ((org.hl7.hibernate.ClonableCollection<CS>) conjunctionCode).cloneHibernateCollectionIfNecessary();
    _conjunctionCode = conjunctionCode;
  }
  /** Sets the property conjunctionCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setConjunctionCode
  */
  public void setConjunctionCodeForHibernate(CS conjunctionCode) {
    _conjunctionCode = conjunctionCode;
  }

  private ST _localVariableName;
  /** Gets the property localVariableName.
      @see org.hl7.rim.ActRelationship#getLocalVariableName
  */
  public ST getLocalVariableName() { return _localVariableName; }
  /** Sets the property localVariableName.
      @see org.hl7.rim.ActRelationship#setLocalVariableName
  */
  public void setLocalVariableName(ST localVariableName) {
    if(localVariableName instanceof org.hl7.hibernate.ClonableCollection)
      localVariableName = ((org.hl7.hibernate.ClonableCollection<ST>) localVariableName).cloneHibernateCollectionIfNecessary();
    _localVariableName = localVariableName;
  }
  /** Sets the property localVariableName. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setLocalVariableName
  */
  public void setLocalVariableNameForHibernate(ST localVariableName) {
    _localVariableName = localVariableName;
  }

  private BL _seperatableInd;
  /** Gets the property seperatableInd.
      @see org.hl7.rim.ActRelationship#getSeperatableInd
  */
  public BL getSeperatableInd() { return _seperatableInd; }
  /** Sets the property seperatableInd.
      @see org.hl7.rim.ActRelationship#setSeperatableInd
  */
  public void setSeperatableInd(BL seperatableInd) {
    if(seperatableInd instanceof org.hl7.hibernate.ClonableCollection)
      seperatableInd = ((org.hl7.hibernate.ClonableCollection<BL>) seperatableInd).cloneHibernateCollectionIfNecessary();
    _seperatableInd = seperatableInd;
  }
  /** Sets the property seperatableInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setSeperatableInd
  */
  public void setSeperatableIndForHibernate(BL seperatableInd) {
    _seperatableInd = seperatableInd;
  }

  private CS _subsetCode;
  /** Gets the property subsetCode.
      @see org.hl7.rim.ActRelationship#getSubsetCode
  */
  public CS getSubsetCode() { return _subsetCode; }
  /** Sets the property subsetCode.
      @see org.hl7.rim.ActRelationship#setSubsetCode
  */
  public void setSubsetCode(CS subsetCode) {
    if(subsetCode instanceof org.hl7.hibernate.ClonableCollection)
      subsetCode = ((org.hl7.hibernate.ClonableCollection<CS>) subsetCode).cloneHibernateCollectionIfNecessary();
    _subsetCode = subsetCode;
  }
  /** Sets the property subsetCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setSubsetCode
  */
  public void setSubsetCodeForHibernate(CS subsetCode) {
    _subsetCode = subsetCode;
  }

  private CE _uncertaintyCode;
  /** Gets the property uncertaintyCode.
      @see org.hl7.rim.ActRelationship#getUncertaintyCode
  */
  public CE getUncertaintyCode() { return _uncertaintyCode; }
  /** Sets the property uncertaintyCode.
      @see org.hl7.rim.ActRelationship#setUncertaintyCode
  */
  public void setUncertaintyCode(CE uncertaintyCode) {
    if(uncertaintyCode instanceof org.hl7.hibernate.ClonableCollection)
      uncertaintyCode = ((org.hl7.hibernate.ClonableCollection<CE>) uncertaintyCode).cloneHibernateCollectionIfNecessary();
    _uncertaintyCode = uncertaintyCode;
  }
  /** Sets the property uncertaintyCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ActRelationship#setUncertaintyCode
  */
  public void setUncertaintyCodeForHibernate(CE uncertaintyCode) {
    _uncertaintyCode = uncertaintyCode;
  }

  private org.hl7.rim.Act _source;
  /** Gets the property source.
      @see org.hl7.rim.ActRelationship#getSource
  */
  public org.hl7.rim.Act getSource() {
    return _source;
  }
  /** Sets the property source.
      @see org.hl7.rim.ActRelationship#setSource
  */
  public void setSource(org.hl7.rim.Act source) {
    _source = source;
  }

  private org.hl7.rim.Act _target;
  /** Gets the property target.
      @see org.hl7.rim.ActRelationship#getTarget
  */
  public org.hl7.rim.Act getTarget() {
    return _target;
  }
  /** Sets the property target.
      @see org.hl7.rim.ActRelationship#setTarget
  */
  public void setTarget(org.hl7.rim.Act target) {
    _target = target;
  }
  public Object clone() throws CloneNotSupportedException {
    ActRelationshipImpl that = (ActRelationshipImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

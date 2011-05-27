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

import org.hl7.rim.LivingSubject;
import org.hl7.rim.impl.EntityImpl;
import org.hl7.types.CE;
import org.hl7.types.TS;
import org.hl7.types.BL;
import org.hl7.types.INT;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.LivingSubject as a simple data holder bean.
    @see org.hl7.rim.LivingSubject
  */
public class LivingSubjectImpl extends org.hl7.rim.impl.EntityImpl implements LivingSubject {

  private CE _administrativeGenderCode;
  /** Gets the property administrativeGenderCode.
      @see org.hl7.rim.LivingSubject#getAdministrativeGenderCode
  */
  public CE getAdministrativeGenderCode() { return _administrativeGenderCode; }
  /** Sets the property administrativeGenderCode.
      @see org.hl7.rim.LivingSubject#setAdministrativeGenderCode
  */
  public void setAdministrativeGenderCode(CE administrativeGenderCode) {
    if(administrativeGenderCode instanceof org.hl7.hibernate.ClonableCollection)
      administrativeGenderCode = ((org.hl7.hibernate.ClonableCollection<CE>) administrativeGenderCode).cloneHibernateCollectionIfNecessary();
    _administrativeGenderCode = administrativeGenderCode;
  }
  /** Sets the property administrativeGenderCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LivingSubject#setAdministrativeGenderCode
  */
  public void setAdministrativeGenderCodeForHibernate(CE administrativeGenderCode) {
    _administrativeGenderCode = administrativeGenderCode;
  }

  private TS _birthTime;
  /** Gets the property birthTime.
      @see org.hl7.rim.LivingSubject#getBirthTime
  */
  public TS getBirthTime() { return _birthTime; }
  /** Sets the property birthTime.
      @see org.hl7.rim.LivingSubject#setBirthTime
  */
  public void setBirthTime(TS birthTime) {
    if(birthTime instanceof org.hl7.hibernate.ClonableCollection)
      birthTime = ((org.hl7.hibernate.ClonableCollection<TS>) birthTime).cloneHibernateCollectionIfNecessary();
    _birthTime = birthTime;
  }
  /** Sets the property birthTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LivingSubject#setBirthTime
  */
  public void setBirthTimeForHibernate(TS birthTime) {
    _birthTime = birthTime;
  }

  private BL _deceasedInd;
  /** Gets the property deceasedInd.
      @see org.hl7.rim.LivingSubject#getDeceasedInd
  */
  public BL getDeceasedInd() { return _deceasedInd; }
  /** Sets the property deceasedInd.
      @see org.hl7.rim.LivingSubject#setDeceasedInd
  */
  public void setDeceasedInd(BL deceasedInd) {
    if(deceasedInd instanceof org.hl7.hibernate.ClonableCollection)
      deceasedInd = ((org.hl7.hibernate.ClonableCollection<BL>) deceasedInd).cloneHibernateCollectionIfNecessary();
    _deceasedInd = deceasedInd;
  }
  /** Sets the property deceasedInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LivingSubject#setDeceasedInd
  */
  public void setDeceasedIndForHibernate(BL deceasedInd) {
    _deceasedInd = deceasedInd;
  }

  private TS _deceasedTime;
  /** Gets the property deceasedTime.
      @see org.hl7.rim.LivingSubject#getDeceasedTime
  */
  public TS getDeceasedTime() { return _deceasedTime; }
  /** Sets the property deceasedTime.
      @see org.hl7.rim.LivingSubject#setDeceasedTime
  */
  public void setDeceasedTime(TS deceasedTime) {
    if(deceasedTime instanceof org.hl7.hibernate.ClonableCollection)
      deceasedTime = ((org.hl7.hibernate.ClonableCollection<TS>) deceasedTime).cloneHibernateCollectionIfNecessary();
    _deceasedTime = deceasedTime;
  }
  /** Sets the property deceasedTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LivingSubject#setDeceasedTime
  */
  public void setDeceasedTimeForHibernate(TS deceasedTime) {
    _deceasedTime = deceasedTime;
  }

  private BL _multipleBirthInd;
  /** Gets the property multipleBirthInd.
      @see org.hl7.rim.LivingSubject#getMultipleBirthInd
  */
  public BL getMultipleBirthInd() { return _multipleBirthInd; }
  /** Sets the property multipleBirthInd.
      @see org.hl7.rim.LivingSubject#setMultipleBirthInd
  */
  public void setMultipleBirthInd(BL multipleBirthInd) {
    if(multipleBirthInd instanceof org.hl7.hibernate.ClonableCollection)
      multipleBirthInd = ((org.hl7.hibernate.ClonableCollection<BL>) multipleBirthInd).cloneHibernateCollectionIfNecessary();
    _multipleBirthInd = multipleBirthInd;
  }
  /** Sets the property multipleBirthInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LivingSubject#setMultipleBirthInd
  */
  public void setMultipleBirthIndForHibernate(BL multipleBirthInd) {
    _multipleBirthInd = multipleBirthInd;
  }

  private INT _multipleBirthOrderNumber;
  /** Gets the property multipleBirthOrderNumber.
      @see org.hl7.rim.LivingSubject#getMultipleBirthOrderNumber
  */
  public INT getMultipleBirthOrderNumber() { return _multipleBirthOrderNumber; }
  /** Sets the property multipleBirthOrderNumber.
      @see org.hl7.rim.LivingSubject#setMultipleBirthOrderNumber
  */
  public void setMultipleBirthOrderNumber(INT multipleBirthOrderNumber) {
    if(multipleBirthOrderNumber instanceof org.hl7.hibernate.ClonableCollection)
      multipleBirthOrderNumber = ((org.hl7.hibernate.ClonableCollection<INT>) multipleBirthOrderNumber).cloneHibernateCollectionIfNecessary();
    _multipleBirthOrderNumber = multipleBirthOrderNumber;
  }
  /** Sets the property multipleBirthOrderNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LivingSubject#setMultipleBirthOrderNumber
  */
  public void setMultipleBirthOrderNumberForHibernate(INT multipleBirthOrderNumber) {
    _multipleBirthOrderNumber = multipleBirthOrderNumber;
  }

  private BL _organDonorInd;
  /** Gets the property organDonorInd.
      @see org.hl7.rim.LivingSubject#getOrganDonorInd
  */
  public BL getOrganDonorInd() { return _organDonorInd; }
  /** Sets the property organDonorInd.
      @see org.hl7.rim.LivingSubject#setOrganDonorInd
  */
  public void setOrganDonorInd(BL organDonorInd) {
    if(organDonorInd instanceof org.hl7.hibernate.ClonableCollection)
      organDonorInd = ((org.hl7.hibernate.ClonableCollection<BL>) organDonorInd).cloneHibernateCollectionIfNecessary();
    _organDonorInd = organDonorInd;
  }
  /** Sets the property organDonorInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LivingSubject#setOrganDonorInd
  */
  public void setOrganDonorIndForHibernate(BL organDonorInd) {
    _organDonorInd = organDonorInd;
  }
  public Object clone() throws CloneNotSupportedException {
    LivingSubjectImpl that = (LivingSubjectImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

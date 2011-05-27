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

import org.hl7.rim.Person;
import org.hl7.rim.impl.LivingSubjectImpl;
import org.hl7.types.BAG;
import org.hl7.types.AD;
import org.hl7.types.CE;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CE;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Person as a simple data holder bean.
    @see org.hl7.rim.Person
  */
public class PersonImpl extends org.hl7.rim.impl.LivingSubjectImpl implements Person {

  private BAG<AD> _addr;
  /** Gets the property addr.
      @see org.hl7.rim.Person#getAddr
  */
  public BAG<AD> getAddr() { return _addr; }
  /** Sets the property addr.
      @see org.hl7.rim.Person#setAddr
  */
  public void setAddr(BAG<AD> addr) {
    if(addr instanceof org.hl7.hibernate.ClonableCollection)
      addr = ((org.hl7.hibernate.ClonableCollection<BAG<AD>>) addr).cloneHibernateCollectionIfNecessary();
    _addr = addr;
  }
  /** Sets the property addr. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setAddr
  */
  public void setAddrForHibernate(BAG<AD> addr) {
    _addr = addr;
  }

  private CE _maritalStatusCode;
  /** Gets the property maritalStatusCode.
      @see org.hl7.rim.Person#getMaritalStatusCode
  */
  public CE getMaritalStatusCode() { return _maritalStatusCode; }
  /** Sets the property maritalStatusCode.
      @see org.hl7.rim.Person#setMaritalStatusCode
  */
  public void setMaritalStatusCode(CE maritalStatusCode) {
    if(maritalStatusCode instanceof org.hl7.hibernate.ClonableCollection)
      maritalStatusCode = ((org.hl7.hibernate.ClonableCollection<CE>) maritalStatusCode).cloneHibernateCollectionIfNecessary();
    _maritalStatusCode = maritalStatusCode;
  }
  /** Sets the property maritalStatusCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setMaritalStatusCode
  */
  public void setMaritalStatusCodeForHibernate(CE maritalStatusCode) {
    _maritalStatusCode = maritalStatusCode;
  }

  private CE _educationLevelCode;
  /** Gets the property educationLevelCode.
      @see org.hl7.rim.Person#getEducationLevelCode
  */
  public CE getEducationLevelCode() { return _educationLevelCode; }
  /** Sets the property educationLevelCode.
      @see org.hl7.rim.Person#setEducationLevelCode
  */
  public void setEducationLevelCode(CE educationLevelCode) {
    if(educationLevelCode instanceof org.hl7.hibernate.ClonableCollection)
      educationLevelCode = ((org.hl7.hibernate.ClonableCollection<CE>) educationLevelCode).cloneHibernateCollectionIfNecessary();
    _educationLevelCode = educationLevelCode;
  }
  /** Sets the property educationLevelCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setEducationLevelCode
  */
  public void setEducationLevelCodeForHibernate(CE educationLevelCode) {
    _educationLevelCode = educationLevelCode;
  }

  private SET<CE> _disabilityCode;
  /** Gets the property disabilityCode.
      @see org.hl7.rim.Person#getDisabilityCode
  */
  public SET<CE> getDisabilityCode() { return _disabilityCode; }
  /** Sets the property disabilityCode.
      @see org.hl7.rim.Person#setDisabilityCode
  */
  public void setDisabilityCode(SET<CE> disabilityCode) {
    if(disabilityCode instanceof org.hl7.hibernate.ClonableCollection)
      disabilityCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) disabilityCode).cloneHibernateCollectionIfNecessary();
    _disabilityCode = disabilityCode;
  }
  /** Sets the property disabilityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setDisabilityCode
  */
  public void setDisabilityCodeForHibernate(SET<CE> disabilityCode) {
    _disabilityCode = disabilityCode;
  }

  private CE _livingArrangementCode;
  /** Gets the property livingArrangementCode.
      @see org.hl7.rim.Person#getLivingArrangementCode
  */
  public CE getLivingArrangementCode() { return _livingArrangementCode; }
  /** Sets the property livingArrangementCode.
      @see org.hl7.rim.Person#setLivingArrangementCode
  */
  public void setLivingArrangementCode(CE livingArrangementCode) {
    if(livingArrangementCode instanceof org.hl7.hibernate.ClonableCollection)
      livingArrangementCode = ((org.hl7.hibernate.ClonableCollection<CE>) livingArrangementCode).cloneHibernateCollectionIfNecessary();
    _livingArrangementCode = livingArrangementCode;
  }
  /** Sets the property livingArrangementCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setLivingArrangementCode
  */
  public void setLivingArrangementCodeForHibernate(CE livingArrangementCode) {
    _livingArrangementCode = livingArrangementCode;
  }

  private CE _religiousAffiliationCode;
  /** Gets the property religiousAffiliationCode.
      @see org.hl7.rim.Person#getReligiousAffiliationCode
  */
  public CE getReligiousAffiliationCode() { return _religiousAffiliationCode; }
  /** Sets the property religiousAffiliationCode.
      @see org.hl7.rim.Person#setReligiousAffiliationCode
  */
  public void setReligiousAffiliationCode(CE religiousAffiliationCode) {
    if(religiousAffiliationCode instanceof org.hl7.hibernate.ClonableCollection)
      religiousAffiliationCode = ((org.hl7.hibernate.ClonableCollection<CE>) religiousAffiliationCode).cloneHibernateCollectionIfNecessary();
    _religiousAffiliationCode = religiousAffiliationCode;
  }
  /** Sets the property religiousAffiliationCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setReligiousAffiliationCode
  */
  public void setReligiousAffiliationCodeForHibernate(CE religiousAffiliationCode) {
    _religiousAffiliationCode = religiousAffiliationCode;
  }

  private SET<CE> _raceCode;
  /** Gets the property raceCode.
      @see org.hl7.rim.Person#getRaceCode
  */
  public SET<CE> getRaceCode() { return _raceCode; }
  /** Sets the property raceCode.
      @see org.hl7.rim.Person#setRaceCode
  */
  public void setRaceCode(SET<CE> raceCode) {
    if(raceCode instanceof org.hl7.hibernate.ClonableCollection)
      raceCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) raceCode).cloneHibernateCollectionIfNecessary();
    _raceCode = raceCode;
  }
  /** Sets the property raceCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setRaceCode
  */
  public void setRaceCodeForHibernate(SET<CE> raceCode) {
    _raceCode = raceCode;
  }

  private SET<CE> _ethnicGroupCode;
  /** Gets the property ethnicGroupCode.
      @see org.hl7.rim.Person#getEthnicGroupCode
  */
  public SET<CE> getEthnicGroupCode() { return _ethnicGroupCode; }
  /** Sets the property ethnicGroupCode.
      @see org.hl7.rim.Person#setEthnicGroupCode
  */
  public void setEthnicGroupCode(SET<CE> ethnicGroupCode) {
    if(ethnicGroupCode instanceof org.hl7.hibernate.ClonableCollection)
      ethnicGroupCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) ethnicGroupCode).cloneHibernateCollectionIfNecessary();
    _ethnicGroupCode = ethnicGroupCode;
  }
  /** Sets the property ethnicGroupCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Person#setEthnicGroupCode
  */
  public void setEthnicGroupCodeForHibernate(SET<CE> ethnicGroupCode) {
    _ethnicGroupCode = ethnicGroupCode;
  }
  public Object clone() throws CloneNotSupportedException {
    PersonImpl that = (PersonImpl) super.clone();

    // deep clone of persistent component collections
    that.setAddr(that.getAddr());
    that.setDisabilityCode(that.getDisabilityCode());
    that.setRaceCode(that.getRaceCode());
    that.setEthnicGroupCode(that.getEthnicGroupCode());
    return that;
  }
}

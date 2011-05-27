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

import org.hl7.rim.LanguageCommunication;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.CE;
import org.hl7.types.BL;

import org.hl7.rim.Entity;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.LanguageCommunication as a simple data holder bean.
    @see org.hl7.rim.LanguageCommunication
  */
public class LanguageCommunicationImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements LanguageCommunication {

  private CE _languageCode;
  /** Gets the property languageCode.
      @see org.hl7.rim.LanguageCommunication#getLanguageCode
  */
  public CE getLanguageCode() { return _languageCode; }
  /** Sets the property languageCode.
      @see org.hl7.rim.LanguageCommunication#setLanguageCode
  */
  public void setLanguageCode(CE languageCode) {
    if(languageCode instanceof org.hl7.hibernate.ClonableCollection)
      languageCode = ((org.hl7.hibernate.ClonableCollection<CE>) languageCode).cloneHibernateCollectionIfNecessary();
    _languageCode = languageCode;
  }
  /** Sets the property languageCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LanguageCommunication#setLanguageCode
  */
  public void setLanguageCodeForHibernate(CE languageCode) {
    _languageCode = languageCode;
  }

  private CE _modeCode;
  /** Gets the property modeCode.
      @see org.hl7.rim.LanguageCommunication#getModeCode
  */
  public CE getModeCode() { return _modeCode; }
  /** Sets the property modeCode.
      @see org.hl7.rim.LanguageCommunication#setModeCode
  */
  public void setModeCode(CE modeCode) {
    if(modeCode instanceof org.hl7.hibernate.ClonableCollection)
      modeCode = ((org.hl7.hibernate.ClonableCollection<CE>) modeCode).cloneHibernateCollectionIfNecessary();
    _modeCode = modeCode;
  }
  /** Sets the property modeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LanguageCommunication#setModeCode
  */
  public void setModeCodeForHibernate(CE modeCode) {
    _modeCode = modeCode;
  }

  private CE _proficiencyLevelCode;
  /** Gets the property proficiencyLevelCode.
      @see org.hl7.rim.LanguageCommunication#getProficiencyLevelCode
  */
  public CE getProficiencyLevelCode() { return _proficiencyLevelCode; }
  /** Sets the property proficiencyLevelCode.
      @see org.hl7.rim.LanguageCommunication#setProficiencyLevelCode
  */
  public void setProficiencyLevelCode(CE proficiencyLevelCode) {
    if(proficiencyLevelCode instanceof org.hl7.hibernate.ClonableCollection)
      proficiencyLevelCode = ((org.hl7.hibernate.ClonableCollection<CE>) proficiencyLevelCode).cloneHibernateCollectionIfNecessary();
    _proficiencyLevelCode = proficiencyLevelCode;
  }
  /** Sets the property proficiencyLevelCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LanguageCommunication#setProficiencyLevelCode
  */
  public void setProficiencyLevelCodeForHibernate(CE proficiencyLevelCode) {
    _proficiencyLevelCode = proficiencyLevelCode;
  }

  private BL _preferenceInd;
  /** Gets the property preferenceInd.
      @see org.hl7.rim.LanguageCommunication#getPreferenceInd
  */
  public BL getPreferenceInd() { return _preferenceInd; }
  /** Sets the property preferenceInd.
      @see org.hl7.rim.LanguageCommunication#setPreferenceInd
  */
  public void setPreferenceInd(BL preferenceInd) {
    if(preferenceInd instanceof org.hl7.hibernate.ClonableCollection)
      preferenceInd = ((org.hl7.hibernate.ClonableCollection<BL>) preferenceInd).cloneHibernateCollectionIfNecessary();
    _preferenceInd = preferenceInd;
  }
  /** Sets the property preferenceInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LanguageCommunication#setPreferenceInd
  */
  public void setPreferenceIndForHibernate(BL preferenceInd) {
    _preferenceInd = preferenceInd;
  }

  private org.hl7.rim.Entity _entity;
  /** Gets the property entity.
      @see org.hl7.rim.LanguageCommunication#getEntity
  */
  public org.hl7.rim.Entity getEntity() {
    return _entity;
  }
  /** Sets the property entity.
      @see org.hl7.rim.LanguageCommunication#setEntity
  */
  public void setEntity(org.hl7.rim.Entity entity) {
    _entity = entity;
  }
  public Object clone() throws CloneNotSupportedException {
    LanguageCommunicationImpl that = (LanguageCommunicationImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

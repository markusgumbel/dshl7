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

import org.hl7.rim.PatientEncounter;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.CE;
import org.hl7.types.PQ;
import org.hl7.types.BL;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.PatientEncounter as a simple data holder bean.
    @see org.hl7.rim.PatientEncounter
  */
public class PatientEncounterImpl extends org.hl7.rim.impl.ActImpl implements PatientEncounter {

  private CE _admissionReferralSourceCode;
  /** Gets the property admissionReferralSourceCode.
      @see org.hl7.rim.PatientEncounter#getAdmissionReferralSourceCode
  */
  public CE getAdmissionReferralSourceCode() { return _admissionReferralSourceCode; }
  /** Sets the property admissionReferralSourceCode.
      @see org.hl7.rim.PatientEncounter#setAdmissionReferralSourceCode
  */
  public void setAdmissionReferralSourceCode(CE admissionReferralSourceCode) {
    if(admissionReferralSourceCode instanceof org.hl7.hibernate.ClonableCollection)
      admissionReferralSourceCode = ((org.hl7.hibernate.ClonableCollection<CE>) admissionReferralSourceCode).cloneHibernateCollectionIfNecessary();
    _admissionReferralSourceCode = admissionReferralSourceCode;
  }
  /** Sets the property admissionReferralSourceCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PatientEncounter#setAdmissionReferralSourceCode
  */
  public void setAdmissionReferralSourceCodeForHibernate(CE admissionReferralSourceCode) {
    _admissionReferralSourceCode = admissionReferralSourceCode;
  }

  private PQ _lengthOfStayQuantity;
  /** Gets the property lengthOfStayQuantity.
      @see org.hl7.rim.PatientEncounter#getLengthOfStayQuantity
  */
  public PQ getLengthOfStayQuantity() { return _lengthOfStayQuantity; }
  /** Sets the property lengthOfStayQuantity.
      @see org.hl7.rim.PatientEncounter#setLengthOfStayQuantity
  */
  public void setLengthOfStayQuantity(PQ lengthOfStayQuantity) {
    if(lengthOfStayQuantity instanceof org.hl7.hibernate.ClonableCollection)
      lengthOfStayQuantity = ((org.hl7.hibernate.ClonableCollection<PQ>) lengthOfStayQuantity).cloneHibernateCollectionIfNecessary();
    _lengthOfStayQuantity = lengthOfStayQuantity;
  }
  /** Sets the property lengthOfStayQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PatientEncounter#setLengthOfStayQuantity
  */
  public void setLengthOfStayQuantityForHibernate(PQ lengthOfStayQuantity) {
    _lengthOfStayQuantity = lengthOfStayQuantity;
  }

  private CE _dischargeDispositionCode;
  /** Gets the property dischargeDispositionCode.
      @see org.hl7.rim.PatientEncounter#getDischargeDispositionCode
  */
  public CE getDischargeDispositionCode() { return _dischargeDispositionCode; }
  /** Sets the property dischargeDispositionCode.
      @see org.hl7.rim.PatientEncounter#setDischargeDispositionCode
  */
  public void setDischargeDispositionCode(CE dischargeDispositionCode) {
    if(dischargeDispositionCode instanceof org.hl7.hibernate.ClonableCollection)
      dischargeDispositionCode = ((org.hl7.hibernate.ClonableCollection<CE>) dischargeDispositionCode).cloneHibernateCollectionIfNecessary();
    _dischargeDispositionCode = dischargeDispositionCode;
  }
  /** Sets the property dischargeDispositionCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PatientEncounter#setDischargeDispositionCode
  */
  public void setDischargeDispositionCodeForHibernate(CE dischargeDispositionCode) {
    _dischargeDispositionCode = dischargeDispositionCode;
  }

  private BL _preAdmitTestInd;
  /** Gets the property preAdmitTestInd.
      @see org.hl7.rim.PatientEncounter#getPreAdmitTestInd
  */
  public BL getPreAdmitTestInd() { return _preAdmitTestInd; }
  /** Sets the property preAdmitTestInd.
      @see org.hl7.rim.PatientEncounter#setPreAdmitTestInd
  */
  public void setPreAdmitTestInd(BL preAdmitTestInd) {
    if(preAdmitTestInd instanceof org.hl7.hibernate.ClonableCollection)
      preAdmitTestInd = ((org.hl7.hibernate.ClonableCollection<BL>) preAdmitTestInd).cloneHibernateCollectionIfNecessary();
    _preAdmitTestInd = preAdmitTestInd;
  }
  /** Sets the property preAdmitTestInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PatientEncounter#setPreAdmitTestInd
  */
  public void setPreAdmitTestIndForHibernate(BL preAdmitTestInd) {
    _preAdmitTestInd = preAdmitTestInd;
  }

  private SET<CE> _specialCourtesiesCode;
  /** Gets the property specialCourtesiesCode.
      @see org.hl7.rim.PatientEncounter#getSpecialCourtesiesCode
  */
  public SET<CE> getSpecialCourtesiesCode() { return _specialCourtesiesCode; }
  /** Sets the property specialCourtesiesCode.
      @see org.hl7.rim.PatientEncounter#setSpecialCourtesiesCode
  */
  public void setSpecialCourtesiesCode(SET<CE> specialCourtesiesCode) {
    if(specialCourtesiesCode instanceof org.hl7.hibernate.ClonableCollection)
      specialCourtesiesCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) specialCourtesiesCode).cloneHibernateCollectionIfNecessary();
    _specialCourtesiesCode = specialCourtesiesCode;
  }
  /** Sets the property specialCourtesiesCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PatientEncounter#setSpecialCourtesiesCode
  */
  public void setSpecialCourtesiesCodeForHibernate(SET<CE> specialCourtesiesCode) {
    _specialCourtesiesCode = specialCourtesiesCode;
  }

  private SET<CE> _specialArrangementCode;
  /** Gets the property specialArrangementCode.
      @see org.hl7.rim.PatientEncounter#getSpecialArrangementCode
  */
  public SET<CE> getSpecialArrangementCode() { return _specialArrangementCode; }
  /** Sets the property specialArrangementCode.
      @see org.hl7.rim.PatientEncounter#setSpecialArrangementCode
  */
  public void setSpecialArrangementCode(SET<CE> specialArrangementCode) {
    if(specialArrangementCode instanceof org.hl7.hibernate.ClonableCollection)
      specialArrangementCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) specialArrangementCode).cloneHibernateCollectionIfNecessary();
    _specialArrangementCode = specialArrangementCode;
  }
  /** Sets the property specialArrangementCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PatientEncounter#setSpecialArrangementCode
  */
  public void setSpecialArrangementCodeForHibernate(SET<CE> specialArrangementCode) {
    _specialArrangementCode = specialArrangementCode;
  }
  public Object clone() throws CloneNotSupportedException {
    PatientEncounterImpl that = (PatientEncounterImpl) super.clone();

    // deep clone of persistent component collections
    that.setSpecialCourtesiesCode(that.getSpecialCourtesiesCode());
    that.setSpecialArrangementCode(that.getSpecialArrangementCode());
    return that;
  }
}

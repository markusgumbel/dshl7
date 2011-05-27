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

import org.hl7.rim.PublicHealthCase;
import org.hl7.rim.impl.ObservationImpl;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.PublicHealthCase as a simple data holder bean.
    @see org.hl7.rim.PublicHealthCase
  */
public class PublicHealthCaseImpl extends org.hl7.rim.impl.ObservationImpl implements PublicHealthCase {

  private CE _detectionMethodCode;
  /** Gets the property detectionMethodCode.
      @see org.hl7.rim.PublicHealthCase#getDetectionMethodCode
  */
  public CE getDetectionMethodCode() { return _detectionMethodCode; }
  /** Sets the property detectionMethodCode.
      @see org.hl7.rim.PublicHealthCase#setDetectionMethodCode
  */
  public void setDetectionMethodCode(CE detectionMethodCode) {
    if(detectionMethodCode instanceof org.hl7.hibernate.ClonableCollection)
      detectionMethodCode = ((org.hl7.hibernate.ClonableCollection<CE>) detectionMethodCode).cloneHibernateCollectionIfNecessary();
    _detectionMethodCode = detectionMethodCode;
  }
  /** Sets the property detectionMethodCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PublicHealthCase#setDetectionMethodCode
  */
  public void setDetectionMethodCodeForHibernate(CE detectionMethodCode) {
    _detectionMethodCode = detectionMethodCode;
  }

  private CE _transmissionModeCode;
  /** Gets the property transmissionModeCode.
      @see org.hl7.rim.PublicHealthCase#getTransmissionModeCode
  */
  public CE getTransmissionModeCode() { return _transmissionModeCode; }
  /** Sets the property transmissionModeCode.
      @see org.hl7.rim.PublicHealthCase#setTransmissionModeCode
  */
  public void setTransmissionModeCode(CE transmissionModeCode) {
    if(transmissionModeCode instanceof org.hl7.hibernate.ClonableCollection)
      transmissionModeCode = ((org.hl7.hibernate.ClonableCollection<CE>) transmissionModeCode).cloneHibernateCollectionIfNecessary();
    _transmissionModeCode = transmissionModeCode;
  }
  /** Sets the property transmissionModeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PublicHealthCase#setTransmissionModeCode
  */
  public void setTransmissionModeCodeForHibernate(CE transmissionModeCode) {
    _transmissionModeCode = transmissionModeCode;
  }

  private CE _diseaseImportedCode;
  /** Gets the property diseaseImportedCode.
      @see org.hl7.rim.PublicHealthCase#getDiseaseImportedCode
  */
  public CE getDiseaseImportedCode() { return _diseaseImportedCode; }
  /** Sets the property diseaseImportedCode.
      @see org.hl7.rim.PublicHealthCase#setDiseaseImportedCode
  */
  public void setDiseaseImportedCode(CE diseaseImportedCode) {
    if(diseaseImportedCode instanceof org.hl7.hibernate.ClonableCollection)
      diseaseImportedCode = ((org.hl7.hibernate.ClonableCollection<CE>) diseaseImportedCode).cloneHibernateCollectionIfNecessary();
    _diseaseImportedCode = diseaseImportedCode;
  }
  /** Sets the property diseaseImportedCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.PublicHealthCase#setDiseaseImportedCode
  */
  public void setDiseaseImportedCodeForHibernate(CE diseaseImportedCode) {
    _diseaseImportedCode = diseaseImportedCode;
  }
  public Object clone() throws CloneNotSupportedException {
    PublicHealthCaseImpl that = (PublicHealthCaseImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

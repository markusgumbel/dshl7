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

import org.hl7.rim.Patient;
import org.hl7.rim.impl.RoleImpl;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Patient as a simple data holder bean.
    @see org.hl7.rim.Patient
  */
public class PatientImpl extends org.hl7.rim.impl.RoleImpl implements Patient {

  private CE _veryImportantPersonCode;
  /** Gets the property veryImportantPersonCode.
      @see org.hl7.rim.Patient#getVeryImportantPersonCode
  */
  public CE getVeryImportantPersonCode() { return _veryImportantPersonCode; }
  /** Sets the property veryImportantPersonCode.
      @see org.hl7.rim.Patient#setVeryImportantPersonCode
  */
  public void setVeryImportantPersonCode(CE veryImportantPersonCode) {
    if(veryImportantPersonCode instanceof org.hl7.hibernate.ClonableCollection)
      veryImportantPersonCode = ((org.hl7.hibernate.ClonableCollection<CE>) veryImportantPersonCode).cloneHibernateCollectionIfNecessary();
    _veryImportantPersonCode = veryImportantPersonCode;
  }
  /** Sets the property veryImportantPersonCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Patient#setVeryImportantPersonCode
  */
  public void setVeryImportantPersonCodeForHibernate(CE veryImportantPersonCode) {
    _veryImportantPersonCode = veryImportantPersonCode;
  }
  public Object clone() throws CloneNotSupportedException {
    PatientImpl that = (PatientImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

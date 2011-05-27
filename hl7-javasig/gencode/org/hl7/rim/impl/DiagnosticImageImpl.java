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

import org.hl7.rim.DiagnosticImage;
import org.hl7.rim.impl.ObservationImpl;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.DiagnosticImage as a simple data holder bean.
    @see org.hl7.rim.DiagnosticImage
  */
public class DiagnosticImageImpl extends org.hl7.rim.impl.ObservationImpl implements DiagnosticImage {

  private CE _subjectOrientationCode;
  /** Gets the property subjectOrientationCode.
      @see org.hl7.rim.DiagnosticImage#getSubjectOrientationCode
  */
  public CE getSubjectOrientationCode() { return _subjectOrientationCode; }
  /** Sets the property subjectOrientationCode.
      @see org.hl7.rim.DiagnosticImage#setSubjectOrientationCode
  */
  public void setSubjectOrientationCode(CE subjectOrientationCode) {
    if(subjectOrientationCode instanceof org.hl7.hibernate.ClonableCollection)
      subjectOrientationCode = ((org.hl7.hibernate.ClonableCollection<CE>) subjectOrientationCode).cloneHibernateCollectionIfNecessary();
    _subjectOrientationCode = subjectOrientationCode;
  }
  /** Sets the property subjectOrientationCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.DiagnosticImage#setSubjectOrientationCode
  */
  public void setSubjectOrientationCodeForHibernate(CE subjectOrientationCode) {
    _subjectOrientationCode = subjectOrientationCode;
  }
  public Object clone() throws CloneNotSupportedException {
    DiagnosticImageImpl that = (DiagnosticImageImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

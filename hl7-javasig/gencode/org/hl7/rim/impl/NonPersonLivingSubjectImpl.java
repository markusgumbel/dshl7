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

import org.hl7.rim.NonPersonLivingSubject;
import org.hl7.rim.impl.LivingSubjectImpl;
import org.hl7.types.ED;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.NonPersonLivingSubject as a simple data holder bean.
    @see org.hl7.rim.NonPersonLivingSubject
  */
public class NonPersonLivingSubjectImpl extends org.hl7.rim.impl.LivingSubjectImpl implements NonPersonLivingSubject {

  private ED _strainText;
  /** Gets the property strainText.
      @see org.hl7.rim.NonPersonLivingSubject#getStrainText
  */
  public ED getStrainText() { return _strainText; }
  /** Sets the property strainText.
      @see org.hl7.rim.NonPersonLivingSubject#setStrainText
  */
  public void setStrainText(ED strainText) {
    if(strainText instanceof org.hl7.hibernate.ClonableCollection)
      strainText = ((org.hl7.hibernate.ClonableCollection<ED>) strainText).cloneHibernateCollectionIfNecessary();
    _strainText = strainText;
  }
  /** Sets the property strainText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.NonPersonLivingSubject#setStrainText
  */
  public void setStrainTextForHibernate(ED strainText) {
    _strainText = strainText;
  }

  private CE _genderStatusCode;
  /** Gets the property genderStatusCode.
      @see org.hl7.rim.NonPersonLivingSubject#getGenderStatusCode
  */
  public CE getGenderStatusCode() { return _genderStatusCode; }
  /** Sets the property genderStatusCode.
      @see org.hl7.rim.NonPersonLivingSubject#setGenderStatusCode
  */
  public void setGenderStatusCode(CE genderStatusCode) {
    if(genderStatusCode instanceof org.hl7.hibernate.ClonableCollection)
      genderStatusCode = ((org.hl7.hibernate.ClonableCollection<CE>) genderStatusCode).cloneHibernateCollectionIfNecessary();
    _genderStatusCode = genderStatusCode;
  }
  /** Sets the property genderStatusCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.NonPersonLivingSubject#setGenderStatusCode
  */
  public void setGenderStatusCodeForHibernate(CE genderStatusCode) {
    _genderStatusCode = genderStatusCode;
  }
  public Object clone() throws CloneNotSupportedException {
    NonPersonLivingSubjectImpl that = (NonPersonLivingSubjectImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

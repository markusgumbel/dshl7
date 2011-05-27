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

import org.hl7.rim.Document;
import org.hl7.rim.impl.ContextStructureImpl;
import org.hl7.types.CE;
import org.hl7.types.TS;
import org.hl7.types.SET;
import org.hl7.types.ED;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Document as a simple data holder bean.
    @see org.hl7.rim.Document
  */
public class DocumentImpl extends org.hl7.rim.impl.ContextStructureImpl implements Document {

  private CE _completionCode;
  /** Gets the property completionCode.
      @see org.hl7.rim.Document#getCompletionCode
  */
  public CE getCompletionCode() { return _completionCode; }
  /** Sets the property completionCode.
      @see org.hl7.rim.Document#setCompletionCode
  */
  public void setCompletionCode(CE completionCode) {
    if(completionCode instanceof org.hl7.hibernate.ClonableCollection)
      completionCode = ((org.hl7.hibernate.ClonableCollection<CE>) completionCode).cloneHibernateCollectionIfNecessary();
    _completionCode = completionCode;
  }
  /** Sets the property completionCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Document#setCompletionCode
  */
  public void setCompletionCodeForHibernate(CE completionCode) {
    _completionCode = completionCode;
  }

  private CE _storageCode;
  /** Gets the property storageCode.
      @see org.hl7.rim.Document#getStorageCode
  */
  public CE getStorageCode() { return _storageCode; }
  /** Sets the property storageCode.
      @see org.hl7.rim.Document#setStorageCode
  */
  public void setStorageCode(CE storageCode) {
    if(storageCode instanceof org.hl7.hibernate.ClonableCollection)
      storageCode = ((org.hl7.hibernate.ClonableCollection<CE>) storageCode).cloneHibernateCollectionIfNecessary();
    _storageCode = storageCode;
  }
  /** Sets the property storageCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Document#setStorageCode
  */
  public void setStorageCodeForHibernate(CE storageCode) {
    _storageCode = storageCode;
  }

  private TS _copyTime;
  /** Gets the property copyTime.
      @see org.hl7.rim.Document#getCopyTime
  */
  public TS getCopyTime() { return _copyTime; }
  /** Sets the property copyTime.
      @see org.hl7.rim.Document#setCopyTime
  */
  public void setCopyTime(TS copyTime) {
    if(copyTime instanceof org.hl7.hibernate.ClonableCollection)
      copyTime = ((org.hl7.hibernate.ClonableCollection<TS>) copyTime).cloneHibernateCollectionIfNecessary();
    _copyTime = copyTime;
  }
  /** Sets the property copyTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Document#setCopyTime
  */
  public void setCopyTimeForHibernate(TS copyTime) {
    _copyTime = copyTime;
  }

  private SET<ED> _bibliographicDesignationText;
  /** Gets the property bibliographicDesignationText.
      @see org.hl7.rim.Document#getBibliographicDesignationText
  */
  public SET<ED> getBibliographicDesignationText() { return _bibliographicDesignationText; }
  /** Sets the property bibliographicDesignationText.
      @see org.hl7.rim.Document#setBibliographicDesignationText
  */
  public void setBibliographicDesignationText(SET<ED> bibliographicDesignationText) {
    if(bibliographicDesignationText instanceof org.hl7.hibernate.ClonableCollection)
      bibliographicDesignationText = ((org.hl7.hibernate.ClonableCollection<SET<ED>>) bibliographicDesignationText).cloneHibernateCollectionIfNecessary();
    _bibliographicDesignationText = bibliographicDesignationText;
  }
  /** Sets the property bibliographicDesignationText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Document#setBibliographicDesignationText
  */
  public void setBibliographicDesignationTextForHibernate(SET<ED> bibliographicDesignationText) {
    _bibliographicDesignationText = bibliographicDesignationText;
  }
  public Object clone() throws CloneNotSupportedException {
    DocumentImpl that = (DocumentImpl) super.clone();

    // deep clone of persistent component collections
    that.setBibliographicDesignationText(that.getBibliographicDesignationText());
    return that;
  }
}

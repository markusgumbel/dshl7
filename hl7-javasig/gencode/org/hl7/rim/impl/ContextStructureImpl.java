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

import org.hl7.rim.ContextStructure;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.II;
import org.hl7.types.INT;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.ContextStructure as a simple data holder bean.
    @see org.hl7.rim.ContextStructure
  */
public class ContextStructureImpl extends org.hl7.rim.impl.ActImpl implements ContextStructure {

  private II _setId;
  /** Gets the property setId.
      @see org.hl7.rim.ContextStructure#getSetId
  */
  public II getSetId() { return _setId; }
  /** Sets the property setId.
      @see org.hl7.rim.ContextStructure#setSetId
  */
  public void setSetId(II setId) {
    if(setId instanceof org.hl7.hibernate.ClonableCollection)
      setId = ((org.hl7.hibernate.ClonableCollection<II>) setId).cloneHibernateCollectionIfNecessary();
    _setId = setId;
  }
  /** Sets the property setId. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ContextStructure#setSetId
  */
  public void setSetIdForHibernate(II setId) {
    _setId = setId;
  }

  private INT _versionNumber;
  /** Gets the property versionNumber.
      @see org.hl7.rim.ContextStructure#getVersionNumber
  */
  public INT getVersionNumber() { return _versionNumber; }
  /** Sets the property versionNumber.
      @see org.hl7.rim.ContextStructure#setVersionNumber
  */
  public void setVersionNumber(INT versionNumber) {
    if(versionNumber instanceof org.hl7.hibernate.ClonableCollection)
      versionNumber = ((org.hl7.hibernate.ClonableCollection<INT>) versionNumber).cloneHibernateCollectionIfNecessary();
    _versionNumber = versionNumber;
  }
  /** Sets the property versionNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ContextStructure#setVersionNumber
  */
  public void setVersionNumberForHibernate(INT versionNumber) {
    _versionNumber = versionNumber;
  }
  public Object clone() throws CloneNotSupportedException {
    ContextStructureImpl that = (ContextStructureImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

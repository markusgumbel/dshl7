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

import org.hl7.rim.SortControl;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.INT;
import org.hl7.types.SC;
import org.hl7.types.CS;

import org.hl7.rim.QuerySpec;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.SortControl as a simple data holder bean.
    @see org.hl7.rim.SortControl
  */
public class SortControlImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements SortControl {

  private INT _sequenceNumber;
  /** Gets the property sequenceNumber.
      @see org.hl7.rim.SortControl#getSequenceNumber
  */
  public INT getSequenceNumber() { return _sequenceNumber; }
  /** Sets the property sequenceNumber.
      @see org.hl7.rim.SortControl#setSequenceNumber
  */
  public void setSequenceNumber(INT sequenceNumber) {
    if(sequenceNumber instanceof org.hl7.hibernate.ClonableCollection)
      sequenceNumber = ((org.hl7.hibernate.ClonableCollection<INT>) sequenceNumber).cloneHibernateCollectionIfNecessary();
    _sequenceNumber = sequenceNumber;
  }
  /** Sets the property sequenceNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SortControl#setSequenceNumber
  */
  public void setSequenceNumberForHibernate(INT sequenceNumber) {
    _sequenceNumber = sequenceNumber;
  }

  private SC _elementName;
  /** Gets the property elementName.
      @see org.hl7.rim.SortControl#getElementName
  */
  public SC getElementName() { return _elementName; }
  /** Sets the property elementName.
      @see org.hl7.rim.SortControl#setElementName
  */
  public void setElementName(SC elementName) {
    if(elementName instanceof org.hl7.hibernate.ClonableCollection)
      elementName = ((org.hl7.hibernate.ClonableCollection<SC>) elementName).cloneHibernateCollectionIfNecessary();
    _elementName = elementName;
  }
  /** Sets the property elementName. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SortControl#setElementName
  */
  public void setElementNameForHibernate(SC elementName) {
    _elementName = elementName;
  }

  private CS _directionCode;
  /** Gets the property directionCode.
      @see org.hl7.rim.SortControl#getDirectionCode
  */
  public CS getDirectionCode() { return _directionCode; }
  /** Sets the property directionCode.
      @see org.hl7.rim.SortControl#setDirectionCode
  */
  public void setDirectionCode(CS directionCode) {
    if(directionCode instanceof org.hl7.hibernate.ClonableCollection)
      directionCode = ((org.hl7.hibernate.ClonableCollection<CS>) directionCode).cloneHibernateCollectionIfNecessary();
    _directionCode = directionCode;
  }
  /** Sets the property directionCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SortControl#setDirectionCode
  */
  public void setDirectionCodeForHibernate(CS directionCode) {
    _directionCode = directionCode;
  }

  private org.hl7.rim.QuerySpec _querySpec;
  /** Gets the property querySpec.
      @see org.hl7.rim.SortControl#getQuerySpec
  */
  public org.hl7.rim.QuerySpec getQuerySpec() {
    return _querySpec;
  }
  /** Sets the property querySpec.
      @see org.hl7.rim.SortControl#setQuerySpec
  */
  public void setQuerySpec(org.hl7.rim.QuerySpec querySpec) {
    _querySpec = querySpec;
  }
  public Object clone() throws CloneNotSupportedException {
    SortControlImpl that = (SortControlImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

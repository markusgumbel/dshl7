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

import org.hl7.rim.Supply;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.PQ;
import org.hl7.types.IVL;
import org.hl7.types.TS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Supply as a simple data holder bean.
    @see org.hl7.rim.Supply
  */
public class SupplyImpl extends org.hl7.rim.impl.ActImpl implements Supply {

  private PQ _quantity;
  /** Gets the property quantity.
      @see org.hl7.rim.Supply#getQuantity
  */
  public PQ getQuantity() { return _quantity; }
  /** Sets the property quantity.
      @see org.hl7.rim.Supply#setQuantity
  */
  public void setQuantity(PQ quantity) {
    if(quantity instanceof org.hl7.hibernate.ClonableCollection)
      quantity = ((org.hl7.hibernate.ClonableCollection<PQ>) quantity).cloneHibernateCollectionIfNecessary();
    _quantity = quantity;
  }
  /** Sets the property quantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Supply#setQuantity
  */
  public void setQuantityForHibernate(PQ quantity) {
    _quantity = quantity;
  }

  private IVL<TS> _expectedUseTime;
  /** Gets the property expectedUseTime.
      @see org.hl7.rim.Supply#getExpectedUseTime
  */
  public IVL<TS> getExpectedUseTime() { return _expectedUseTime; }
  /** Sets the property expectedUseTime.
      @see org.hl7.rim.Supply#setExpectedUseTime
  */
  public void setExpectedUseTime(IVL<TS> expectedUseTime) {
    if(expectedUseTime instanceof org.hl7.hibernate.ClonableCollection)
      expectedUseTime = ((org.hl7.hibernate.ClonableCollection<IVL<TS>>) expectedUseTime).cloneHibernateCollectionIfNecessary();
    _expectedUseTime = expectedUseTime;
  }
  /** Sets the property expectedUseTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Supply#setExpectedUseTime
  */
  public void setExpectedUseTimeForHibernate(IVL<TS> expectedUseTime) {
    _expectedUseTime = expectedUseTime;
  }
  public Object clone() throws CloneNotSupportedException {
    SupplyImpl that = (SupplyImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

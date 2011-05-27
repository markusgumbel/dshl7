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

import org.hl7.rim.InvoiceElement;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.PQ;
import org.hl7.types.PQ;
import org.hl7.types.MO;
import org.hl7.types.PQ;
import org.hl7.types.REAL;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.InvoiceElement as a simple data holder bean.
    @see org.hl7.rim.InvoiceElement
  */
public class InvoiceElementImpl extends org.hl7.rim.impl.ActImpl implements InvoiceElement {

  private SET<CE> _modifierCode;
  /** Gets the property modifierCode.
      @see org.hl7.rim.InvoiceElement#getModifierCode
  */
  public SET<CE> getModifierCode() { return _modifierCode; }
  /** Sets the property modifierCode.
      @see org.hl7.rim.InvoiceElement#setModifierCode
  */
  public void setModifierCode(SET<CE> modifierCode) {
    if(modifierCode instanceof org.hl7.hibernate.ClonableCollection)
      modifierCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) modifierCode).cloneHibernateCollectionIfNecessary();
    _modifierCode = modifierCode;
  }
  /** Sets the property modifierCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InvoiceElement#setModifierCode
  */
  public void setModifierCodeForHibernate(SET<CE> modifierCode) {
    _modifierCode = modifierCode;
  }

  private RTO _unitQuantity;
  /** Gets the property unitQuantity.
      @see org.hl7.rim.InvoiceElement#getUnitQuantity
  */
  public RTO getUnitQuantity() { return _unitQuantity; }
  /** Sets the property unitQuantity.
      @see org.hl7.rim.InvoiceElement#setUnitQuantity
  */
  public void setUnitQuantity(RTO unitQuantity) {
    if(unitQuantity instanceof org.hl7.hibernate.ClonableCollection)
      unitQuantity = ((org.hl7.hibernate.ClonableCollection<RTO>) unitQuantity).cloneHibernateCollectionIfNecessary();
    _unitQuantity = unitQuantity;
  }
  /** Sets the property unitQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InvoiceElement#setUnitQuantity
  */
  public void setUnitQuantityForHibernate(RTO unitQuantity) {
    _unitQuantity = unitQuantity;
  }

  private RTO _unitPriceAmt;
  /** Gets the property unitPriceAmt.
      @see org.hl7.rim.InvoiceElement#getUnitPriceAmt
  */
  public RTO getUnitPriceAmt() { return _unitPriceAmt; }
  /** Sets the property unitPriceAmt.
      @see org.hl7.rim.InvoiceElement#setUnitPriceAmt
  */
  public void setUnitPriceAmt(RTO unitPriceAmt) {
    if(unitPriceAmt instanceof org.hl7.hibernate.ClonableCollection)
      unitPriceAmt = ((org.hl7.hibernate.ClonableCollection<RTO>) unitPriceAmt).cloneHibernateCollectionIfNecessary();
    _unitPriceAmt = unitPriceAmt;
  }
  /** Sets the property unitPriceAmt. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InvoiceElement#setUnitPriceAmt
  */
  public void setUnitPriceAmtForHibernate(RTO unitPriceAmt) {
    _unitPriceAmt = unitPriceAmt;
  }

  private MO _netAmt;
  /** Gets the property netAmt.
      @see org.hl7.rim.InvoiceElement#getNetAmt
  */
  public MO getNetAmt() { return _netAmt; }
  /** Sets the property netAmt.
      @see org.hl7.rim.InvoiceElement#setNetAmt
  */
  public void setNetAmt(MO netAmt) {
    if(netAmt instanceof org.hl7.hibernate.ClonableCollection)
      netAmt = ((org.hl7.hibernate.ClonableCollection<MO>) netAmt).cloneHibernateCollectionIfNecessary();
    _netAmt = netAmt;
  }
  /** Sets the property netAmt. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InvoiceElement#setNetAmt
  */
  public void setNetAmtForHibernate(MO netAmt) {
    _netAmt = netAmt;
  }

  private REAL _factorNumber;
  /** Gets the property factorNumber.
      @see org.hl7.rim.InvoiceElement#getFactorNumber
  */
  public REAL getFactorNumber() { return _factorNumber; }
  /** Sets the property factorNumber.
      @see org.hl7.rim.InvoiceElement#setFactorNumber
  */
  public void setFactorNumber(REAL factorNumber) {
    if(factorNumber instanceof org.hl7.hibernate.ClonableCollection)
      factorNumber = ((org.hl7.hibernate.ClonableCollection<REAL>) factorNumber).cloneHibernateCollectionIfNecessary();
    _factorNumber = factorNumber;
  }
  /** Sets the property factorNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InvoiceElement#setFactorNumber
  */
  public void setFactorNumberForHibernate(REAL factorNumber) {
    _factorNumber = factorNumber;
  }

  private REAL _pointsNumber;
  /** Gets the property pointsNumber.
      @see org.hl7.rim.InvoiceElement#getPointsNumber
  */
  public REAL getPointsNumber() { return _pointsNumber; }
  /** Sets the property pointsNumber.
      @see org.hl7.rim.InvoiceElement#setPointsNumber
  */
  public void setPointsNumber(REAL pointsNumber) {
    if(pointsNumber instanceof org.hl7.hibernate.ClonableCollection)
      pointsNumber = ((org.hl7.hibernate.ClonableCollection<REAL>) pointsNumber).cloneHibernateCollectionIfNecessary();
    _pointsNumber = pointsNumber;
  }
  /** Sets the property pointsNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InvoiceElement#setPointsNumber
  */
  public void setPointsNumberForHibernate(REAL pointsNumber) {
    _pointsNumber = pointsNumber;
  }
  public Object clone() throws CloneNotSupportedException {
    InvoiceElementImpl that = (InvoiceElementImpl) super.clone();

    // deep clone of persistent component collections
    that.setModifierCode(that.getModifierCode());
    return that;
  }
}

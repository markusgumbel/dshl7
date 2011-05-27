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

import org.hl7.rim.FinancialTransaction;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.MO;
import org.hl7.types.REAL;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.FinancialTransaction as a simple data holder bean.
    @see org.hl7.rim.FinancialTransaction
  */
public class FinancialTransactionImpl extends org.hl7.rim.impl.ActImpl implements FinancialTransaction {

  private MO _amt;
  /** Gets the property amt.
      @see org.hl7.rim.FinancialTransaction#getAmt
  */
  public MO getAmt() { return _amt; }
  /** Sets the property amt.
      @see org.hl7.rim.FinancialTransaction#setAmt
  */
  public void setAmt(MO amt) {
    if(amt instanceof org.hl7.hibernate.ClonableCollection)
      amt = ((org.hl7.hibernate.ClonableCollection<MO>) amt).cloneHibernateCollectionIfNecessary();
    _amt = amt;
  }
  /** Sets the property amt. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.FinancialTransaction#setAmt
  */
  public void setAmtForHibernate(MO amt) {
    _amt = amt;
  }

  private REAL _creditExchangeRateQuantity;
  /** Gets the property creditExchangeRateQuantity.
      @see org.hl7.rim.FinancialTransaction#getCreditExchangeRateQuantity
  */
  public REAL getCreditExchangeRateQuantity() { return _creditExchangeRateQuantity; }
  /** Sets the property creditExchangeRateQuantity.
      @see org.hl7.rim.FinancialTransaction#setCreditExchangeRateQuantity
  */
  public void setCreditExchangeRateQuantity(REAL creditExchangeRateQuantity) {
    if(creditExchangeRateQuantity instanceof org.hl7.hibernate.ClonableCollection)
      creditExchangeRateQuantity = ((org.hl7.hibernate.ClonableCollection<REAL>) creditExchangeRateQuantity).cloneHibernateCollectionIfNecessary();
    _creditExchangeRateQuantity = creditExchangeRateQuantity;
  }
  /** Sets the property creditExchangeRateQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.FinancialTransaction#setCreditExchangeRateQuantity
  */
  public void setCreditExchangeRateQuantityForHibernate(REAL creditExchangeRateQuantity) {
    _creditExchangeRateQuantity = creditExchangeRateQuantity;
  }

  private REAL _debitExchangeRateQuantity;
  /** Gets the property debitExchangeRateQuantity.
      @see org.hl7.rim.FinancialTransaction#getDebitExchangeRateQuantity
  */
  public REAL getDebitExchangeRateQuantity() { return _debitExchangeRateQuantity; }
  /** Sets the property debitExchangeRateQuantity.
      @see org.hl7.rim.FinancialTransaction#setDebitExchangeRateQuantity
  */
  public void setDebitExchangeRateQuantity(REAL debitExchangeRateQuantity) {
    if(debitExchangeRateQuantity instanceof org.hl7.hibernate.ClonableCollection)
      debitExchangeRateQuantity = ((org.hl7.hibernate.ClonableCollection<REAL>) debitExchangeRateQuantity).cloneHibernateCollectionIfNecessary();
    _debitExchangeRateQuantity = debitExchangeRateQuantity;
  }
  /** Sets the property debitExchangeRateQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.FinancialTransaction#setDebitExchangeRateQuantity
  */
  public void setDebitExchangeRateQuantityForHibernate(REAL debitExchangeRateQuantity) {
    _debitExchangeRateQuantity = debitExchangeRateQuantity;
  }
  public Object clone() throws CloneNotSupportedException {
    FinancialTransactionImpl that = (FinancialTransactionImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

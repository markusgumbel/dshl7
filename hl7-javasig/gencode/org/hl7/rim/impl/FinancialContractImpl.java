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

import org.hl7.rim.FinancialContract;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.FinancialContract as a simple data holder bean.
    @see org.hl7.rim.FinancialContract
  */
public class FinancialContractImpl extends org.hl7.rim.impl.ActImpl implements FinancialContract {

  private CE _paymentTermsCode;
  /** Gets the property paymentTermsCode.
      @see org.hl7.rim.FinancialContract#getPaymentTermsCode
  */
  public CE getPaymentTermsCode() { return _paymentTermsCode; }
  /** Sets the property paymentTermsCode.
      @see org.hl7.rim.FinancialContract#setPaymentTermsCode
  */
  public void setPaymentTermsCode(CE paymentTermsCode) {
    if(paymentTermsCode instanceof org.hl7.hibernate.ClonableCollection)
      paymentTermsCode = ((org.hl7.hibernate.ClonableCollection<CE>) paymentTermsCode).cloneHibernateCollectionIfNecessary();
    _paymentTermsCode = paymentTermsCode;
  }
  /** Sets the property paymentTermsCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.FinancialContract#setPaymentTermsCode
  */
  public void setPaymentTermsCodeForHibernate(CE paymentTermsCode) {
    _paymentTermsCode = paymentTermsCode;
  }
  public Object clone() throws CloneNotSupportedException {
    FinancialContractImpl that = (FinancialContractImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

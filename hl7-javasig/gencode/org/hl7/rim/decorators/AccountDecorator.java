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
package org.hl7.rim.decorators;

import org.hl7.rim.Account;
import org.hl7.rim.decorators.ActDecorator;
import org.hl7.types.MO;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.MO;
import org.hl7.types.PQ;
import org.hl7.types.IVL;
import org.hl7.types.MO;

import org.hl7.types.impl.MOnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.RTOnull;
import org.hl7.types.impl.MOnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.MOnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Account as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Account
  */
public abstract class AccountDecorator extends org.hl7.rim.decorators.ActDecorator implements Account {
  /** Property accessor, returns NULL/NA if not overloaded.balanceAmt.
      @see org.hl7.rim.Account#getBalanceAmt
  */
  public MO getBalanceAmt() { return MOnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.balanceAmt.
      @see org.hl7.rim.Account#setBalanceAmt
  */
  public void setBalanceAmt(MO balanceAmt) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.currencyCode.
      @see org.hl7.rim.Account#getCurrencyCode
  */
  public CE getCurrencyCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.currencyCode.
      @see org.hl7.rim.Account#setCurrencyCode
  */
  public void setCurrencyCode(CE currencyCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.interestRateQuantity.
      @see org.hl7.rim.Account#getInterestRateQuantity
  */
  public RTO getInterestRateQuantity() { return RTOnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.interestRateQuantity.
      @see org.hl7.rim.Account#setInterestRateQuantity
  */
  public void setInterestRateQuantity(RTO interestRateQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.allowedBalanceQuantity.
      @see org.hl7.rim.Account#getAllowedBalanceQuantity
  */
  public IVL<MO> getAllowedBalanceQuantity() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.allowedBalanceQuantity.
      @see org.hl7.rim.Account#setAllowedBalanceQuantity
  */
  public void setAllowedBalanceQuantity(IVL<MO> allowedBalanceQuantity) { /*throw new UnsupportedOperationException();*/ }
}

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
package org.hl7.rim;

import org.hl7.rim.Act;
import org.hl7.types.MO;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.MO;
import org.hl7.types.PQ;
import org.hl7.types.IVL;
import org.hl7.types.MO;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An Act representing a category of financial transactions that are tracked and reported together with a single balance.</p>
<p><i>Discussion:</i> This can be used to represent the accumulated total of billable amounts for goods or services received, payments made for
   goods or services, and debit and credit accounts between which financial transactions flow.
</p>
<p><i>Examples:</i> Patient accounts; Encounter accounts; Cost centers; Accounts receivable
</p>
*/
public interface Account extends org.hl7.rim.Act {

  /**<p>The total of the debit and credit transactions that have occurred against the account over its lifetime.</p>
<p>Discussion: The balance of an account will generally be communicated in the currency identified as the account's currencyCode.
   However, it is allowed to communicate the balance in alternative currencies.
</p>
  */
  MO getBalanceAmt();
  /** Sets the property balanceAmt.
      @see #getBalanceAmt
  */
  void setBalanceAmt(MO balanceAmt);

  /**<p>Indicates the currency that the account is managed in.</p>
<p><i>Discussion:</i> Specific amounts might be reported in another currency however this represents the default currency for activity in this
   account.
</p>
  */
  CE getCurrencyCode();
  /** Sets the property currencyCode.
      @see #getCurrencyCode
  */
  void setCurrencyCode(CE currencyCode);

  /**<p>A ratio that indicates the rate of interest that the account balance is subject to, and the term over which the interest rate
   compounds.
</p>
<p><i>Discussion:</i> This may represent interest charged (e.g. for loans, overdue accounts, etc.) or credited (investments, etc.) depending on
   the type of account.
</p>
<p><i>Examples:</i> 0.10/1a (10%/year); 0.0005895/1d (.05895%/day)
</p>
<p><i>Constraints:</i> Unit of the denominator PQ data type must be comparable to seconds. (I.e. the denominator must be measured in time.)
</p>
  */
  RTO getInterestRateQuantity();
  /** Sets the property interestRateQuantity.
      @see #getInterestRateQuantity
  */
  void setInterestRateQuantity(RTO interestRateQuantity);

  /**<p>An interval describing the minimum and maximum allowed balances for an account.</p>
<p><i>Discussion:</i> These are not necessarily 'hard' limits (i.e. the account may go above or below the specified amounts), however, they represent
   the 'target' range for the account, and there may be consequences for going outside the specified boundaries. It is not necessary
   to specify both upper and lower limits (or either) for an account.
</p>
<p><i>Examples:</i> 'stop loss' limits; credit limits
</p>
  */
  IVL<MO> getAllowedBalanceQuantity();
  /** Sets the property allowedBalanceQuantity.
      @see #getAllowedBalanceQuantity
  */
  void setAllowedBalanceQuantity(IVL<MO> allowedBalanceQuantity);
}

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
import org.hl7.types.REAL;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An Act representing the movement of a monetary amount between two accounts.</p>
<p><i>Discussion:</i> Financial transactions always occur between two accounts (debit and credit), but there may be circumstances where one or
   both accounts are implied or inherited from the containing model.
</p>
<p>In the "order" mood, this represents a request for a transaction to be initiated.</p>
<p>In the "event" mood, this represents the posting of a transaction to an account.</p>
<p><i>Examples:</i> Cost of a service; Charge for a service; Payment of an invoice
</p>
*/
public interface FinancialTransaction extends org.hl7.rim.Act {

  /**<p>Indicates the monetary amount to be transferred from the debit to the credit account.</p>
<p><i>Discussion:</i> If the denomination of the amt differs from the denomination of the debit or credit account, then the associated exchange
   rate should be specified.
</p>
  */
  MO getAmt();
  /** Sets the property amt.
      @see #getAmt
  */
  void setAmt(MO amt);

  /**<p>A decimal number indicating the rate of exchange in effect between the currency of the account being credited, and the currency
   of the transaction net amount.
</p>
<p><i>Examples:</i> For the purchase of services valued in Mexican pesos using U.S. dollars paid from a Canadian dollar account, the credit exchange
   ratio would be communicated as real number "r" such that "y (USD) * r = x (CAD)".
</p>
<p><i>Rationale:</i> This allows a transaction to be expressed in a currency other than that of the credit and debit accounts. It also allows
   the credit and debit accounts to be based in different currencies.
</p>
  */
  REAL getCreditExchangeRateQuantity();
  /** Sets the property creditExchangeRateQuantity.
      @see #getCreditExchangeRateQuantity
  */
  void setCreditExchangeRateQuantity(REAL creditExchangeRateQuantity);

  /**<p>A decimal number indicating the rate of exchange in effect between the currency of the account being debited, and the currency
   of the transaction net amount.
</p>
<p><i>Examples:</i> For the purchase of services valued in Mexican pesos using U.S. dollars paid from a Canadian dollar account, the debit exchange
   ratio would be communicated as real number "r" such that "y (USD) * r = x (MXP)".
</p>
<p><i>Rationale:</i> This allows a transaction to be expressed in a currency other than that of the credit and debit accounts. It also allows
   the credit and debit accounts to be based in different currencies.
</p>
  */
  REAL getDebitExchangeRateQuantity();
  /** Sets the property debitExchangeRateQuantity.
      @see #getDebitExchangeRateQuantity
  */
  void setDebitExchangeRateQuantity(REAL debitExchangeRateQuantity);
}

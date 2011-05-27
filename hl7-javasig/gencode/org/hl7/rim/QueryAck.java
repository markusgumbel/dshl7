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

import org.hl7.rim.QueryEvent;
import org.hl7.types.CS;
import org.hl7.types.INT;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>This class carries information sent with responses to a query.</p>
*/
public interface QueryAck extends org.hl7.rim.QueryEvent {

  /**<p>This attribute allows the responding system to return a precise response status.</p>
  */
  CS getQueryResponseCode();
  /** Sets the property queryResponseCode.
      @see #getQueryResponseCode
  */
  void setQueryResponseCode(CS queryResponseCode);

  /**<p>Specifies total number of instance matches for query specification associated with this query response instance.</p>
  */
  INT getResultTotalQuantity();
  /** Sets the property resultTotalQuantity.
      @see #getResultTotalQuantity
  */
  void setResultTotalQuantity(INT resultTotalQuantity);

  /**<p>Specifies number of matches for processed query specification that occur in current bundle of matches.</p>
  */
  INT getResultCurrentQuantity();
  /** Sets the property resultCurrentQuantity.
      @see #getResultCurrentQuantity
  */
  void setResultCurrentQuantity(INT resultCurrentQuantity);

  /**<p>Specifies number of matches for processed query specification that have yet to be sent to receiver.</p>
  */
  INT getResultRemainingQuantity();
  /** Sets the property resultRemainingQuantity.
      @see #getResultRemainingQuantity
  */
  void setResultRemainingQuantity(INT resultRemainingQuantity);
}

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

import org.hl7.rim.InfrastructureRoot;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.CE;

import org.hl7.rim.AcknowledgementDetail;
import org.hl7.rim.Transmission;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>The Acknowledgement class contains information sent when acknowledging another message.</p>
*/
public interface Acknowledgement extends org.hl7.rim.InfrastructureRoot {

  /**<p>This attribute contains an acknowledgement code as described in the HL7 message processing rules.</p>
  */
  CS getTypeCode();
  /** Sets the property typeCode.
      @see #getTypeCode
  */
  void setTypeCode(CS typeCode);

  /**<p>This attribute is used in the sequence number protocol.</p>
  */
  INT getExpectedSequenceNumber();
  /** Sets the property expectedSequenceNumber.
      @see #getExpectedSequenceNumber
  */
  void setExpectedSequenceNumber(INT expectedSequenceNumber);

  /**<p>Indicates the number of messages the acknowledging application has waiting on a queue for the receiving application.</p>
<p>Discussion: These messages would then need to be retrieved via a query. This facilitates receiving applications that cannot
   receive unsolicited message (i.e. polling).
</p>
<p>Examples: If there are 3 low priority messages, 1 medium priority message and 1 high priority message, the message waiting
   number would be 5, because that is the total number of messages.
</p>
  */
  INT getMessageWaitingNumber();
  /** Sets the property messageWaitingNumber.
      @see #getMessageWaitingNumber
  */
  void setMessageWaitingNumber(INT messageWaitingNumber);

  /**<p>Indicates the highest importance level of the set of messages the acknowledging application has waiting on a queue for the
   receiving application.
</p>
<p>Discussion: These messages would need to be retrieved via a query. This facilitates receiving applications that cannot receive
   unsolicited messages (i.e. polling). The specific code specified identifies how important the most important waiting message
   is (and may govern how soon the receiving application is required to poll for the message).
</p>
<p>Priority may be used by local agreement to determine the timeframe in which the receiving application is expected to retrieve
   the messages from the queue.
</p>
  */
  CE getMessageWaitingPriorityCode();
  /** Sets the property messageWaitingPriorityCode.
      @see #getMessageWaitingPriorityCode
  */
  void setMessageWaitingPriorityCode(CE messageWaitingPriorityCode);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.AcknowledgementDetail> getAcknowledgementDetail();
  /** Sets the property acknowledgementDetail.
      @see #getAcknowledgementDetail
  */
  void setAcknowledgementDetail(/*AssociationSet*/List<org.hl7.rim.AcknowledgementDetail> acknowledgementDetail);
  /** Adds an association acknowledgementDetail.
      @see #addAcknowledgementDetail
  */
  void addAcknowledgementDetail(org.hl7.rim.AcknowledgementDetail acknowledgementDetail);

  /**
  */
  org.hl7.rim.Transmission getAcknowledges();
  /** Sets the property acknowledges.
      @see #getAcknowledges
  */
  void setAcknowledges(org.hl7.rim.Transmission acknowledges);

  /**
  */
  org.hl7.rim.Transmission getConveyingTransmission();
  /** Sets the property conveyingTransmission.
      @see #getConveyingTransmission
  */
  void setConveyingTransmission(org.hl7.rim.Transmission conveyingTransmission);
}

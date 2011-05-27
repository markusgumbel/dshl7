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

import org.hl7.rim.Transmission;
import org.hl7.types.LIST;
import org.hl7.types.II;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.SET;
import org.hl7.types.ED;

import org.hl7.rim.ControlAct;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>The Message class is the parent class of all HL7 Version 3 messages.</p>
*/
public interface Message extends org.hl7.rim.Transmission {

  /**<p>The message profile identifier allows a given implementation to explicitly state how it varies from the standard message definition.</p>
<p>When multiple profiles are specified, the message instance must be valid against all of them. However, a receiver may choose
   to validate against only the first one recognized. For this reason, 'preferred' or more-rigorous profiles should be listed
   first.
</p>
  */
  LIST<II> getProfileId();
  /** Sets the property profileId.
      @see #getProfileId
  */
  void setProfileId(LIST<II> profileId);

  /**<p>This attribute defines whether the message is part of a production, training, or debugging system.</p>
  */
  CS getProcessingCode();
  /** Sets the property processingCode.
      @see #getProcessingCode
  */
  void setProcessingCode(CS processingCode);

  /**<p>This attribute defines whether the message is being sent in current processing, archive mode, initial load mode, restore from
   archive mode, etc.
</p>
  */
  CS getProcessingModeCode();
  /** Sets the property processingModeCode.
      @see #getProcessingModeCode
  */
  void setProcessingModeCode(CS processingModeCode);

  /**<p>The attribute identifies the conditions under which accept acknowledgements are required to be returned in response to this
   message.
</p>
  */
  CS getAcceptAckCode();
  /** Sets the property acceptAckCode.
      @see #getAcceptAckCode
  */
  void setAcceptAckCode(CS acceptAckCode);

  /**<p>Specifies whether an application response is expected from the addressee of this interaction and what level of detail that
   response should include. This attribute restricts the default response options specified by the receiver responsibilities
   for the interaction. For example, if an interaction has receiver responsibilities to send either an accept interaction or
   a refuse interaction, and the responseCode is set to 'E' - Exception, the receiver should only respond if they refuse.
</p>
  */
  CS getResponseCode();
  /** Sets the property responseCode.
      @see #getResponseCode
  */
  void setResponseCode(CS responseCode);

  /**<p>This attribute is provided for implementing the sequence number protocol. This field is incremented by one for each subsequent
   value assignment.
</p>
  */
  INT getSequenceNumber();
  /** Sets the property sequenceNumber.
      @see #getSequenceNumber
  */
  void setSequenceNumber(INT sequenceNumber);

  /**<p>Contains arbitrary attachments of data blocks to which can be referred to from the interior of the message. Any ITS is advised
   to represent the attachments behind the main message body. Attachments are referred to from the message body using the reference
   functionality of the ED data type.
</p>
<p>OpenIssue: Use of this attribute has been superseded by the addition of the Attachment class to the RIM, and further use if
   this attribute is deprecated in favor of the new approach.
</p>
<p>OpenIssue: Constrain the use of the ED data type in the attachment message type. Additionally, the appropriateness of this
   attribute in the Message class is in question. An alternative to consider would be the placement of this attribute in a new
   class maintaining a 0 to many relationship with the Message class.
</p>
  */
  SET<ED> getAttachmentText();
  /** Sets the property attachmentText.
      @see #getAttachmentText
  */
  void setAttachmentText(SET<ED> attachmentText);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.ControlAct> getControlAct();
  /** Sets the property controlAct.
      @see #getControlAct
  */
  void setControlAct(/*AssociationSet*/List<org.hl7.rim.ControlAct> controlAct);
  /** Adds an association controlAct.
      @see #addControlAct
  */
  void addControlAct(org.hl7.rim.ControlAct controlAct);
}

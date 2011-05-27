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
import org.hl7.types.II;
import org.hl7.types.TS;
import org.hl7.types.ST;
import org.hl7.types.CS;

import org.hl7.rim.Acknowledgement;
import org.hl7.rim.Attachment;
import org.hl7.rim.AttentionLine;
import org.hl7.rim.Batch;
import org.hl7.rim.CommunicationFunction;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>Represents information about a specific transmission of information from one application to another.</p>
*/
public interface Transmission extends org.hl7.rim.InfrastructureRoot {

  /**<p>Unique identifier of the transmission.</p>
  */
  II getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(II id);

  /**<p>The date/time that the sending system created the transmission. If the time zone is specified, it will be used throughout
   the transmission as the default time zone.
</p>
  */
  TS getCreationTime();
  /** Sets the property creationTime.
      @see #getCreationTime
  */
  void setCreationTime(TS creationTime);

  /**<p>This attribute is specified for applications to implement security features for a transmission. Its use is not further specified
   at this time.
</p>
  */
  ST getSecurityText();
  /** Sets the property securityText.
      @see #getSecurityText
  */
  void setSecurityText(ST securityText);

  /**<p>This attribute defines the transmission mode with which a receiver should communicate its receiver responsibilities, i.e.
   the expected dynamic behavior of the receiving application with regard to application response transmissions.
</p>
  */
  CS getResponseModeCode();
  /** Sets the property responseModeCode.
      @see #getResponseModeCode
  */
  void setResponseModeCode(CS responseModeCode);

  /**<p>This attribute is matched by the receiving system to its own version to be sure the message will be interpreted correctly.</p>
<p><i>Note:</i> This attribute is also present in the sibling class, Message. This change was made rather than moving this attribute to their
   common ancestor class, Transmission. This decision was taken because we do not have all the methodology and backwards compatibility
   issues worked out. Once we have established our backwards compatibility, we should promote this attribute to the parent. The
   problem is the sequencing of attributes within the HDF and their impact on the ITSs.
</p>
  */
  CS getVersionCode();
  /** Sets the property versionCode.
      @see #getVersionCode
  */
  void setVersionCode(CS versionCode);

  /**<p>The interaction identifier is a reference to the unique information interchange derived from the V3 MDF for specifying a message.</p>
<p><i>Note:</i> This attribute is also present in the sibling class, Batch. This change was made rather than moving this attribute to their
   common ancestor class, Transmission. This decision was taken because we do not have all the methodology and backwards compatibility
   issues worked out. Once we have established our backwards compatibility, we should promote this attribute to the parent. The
   problem is the sequencing of attributes within the HDF and their impact on the ITSs.
</p>
  */
  II getInteractionId();
  /** Sets the property interactionId.
      @see #getInteractionId
  */
  void setInteractionId(II interactionId);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Acknowledgement> getAcknowledgedBy();
  /** Sets the property acknowledgedBy.
      @see #getAcknowledgedBy
  */
  void setAcknowledgedBy(/*AssociationSet*/List<org.hl7.rim.Acknowledgement> acknowledgedBy);
  /** Adds an association acknowledgedBy.
      @see #addAcknowledgedBy
  */
  void addAcknowledgedBy(org.hl7.rim.Acknowledgement acknowledgedBy);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Acknowledgement> getConveyedAcknowledgement();
  /** Sets the property conveyedAcknowledgement.
      @see #getConveyedAcknowledgement
  */
  void setConveyedAcknowledgement(/*AssociationSet*/List<org.hl7.rim.Acknowledgement> conveyedAcknowledgement);
  /** Adds an association conveyedAcknowledgement.
      @see #addConveyedAcknowledgement
  */
  void addConveyedAcknowledgement(org.hl7.rim.Acknowledgement conveyedAcknowledgement);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Attachment> getAttachment();
  /** Sets the property attachment.
      @see #getAttachment
  */
  void setAttachment(/*AssociationSet*/List<org.hl7.rim.Attachment> attachment);
  /** Adds an association attachment.
      @see #addAttachment
  */
  void addAttachment(org.hl7.rim.Attachment attachment);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.AttentionLine> getAttentionLine();
  /** Sets the property attentionLine.
      @see #getAttentionLine
  */
  void setAttentionLine(/*AssociationSet*/List<org.hl7.rim.AttentionLine> attentionLine);
  /** Adds an association attentionLine.
      @see #addAttentionLine
  */
  void addAttentionLine(org.hl7.rim.AttentionLine attentionLine);

  /**
  */
  org.hl7.rim.Batch getBatch();
  /** Sets the property batch.
      @see #getBatch
  */
  void setBatch(org.hl7.rim.Batch batch);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.CommunicationFunction> getCommunicationFunction();
  /** Sets the property communicationFunction.
      @see #getCommunicationFunction
  */
  void setCommunicationFunction(/*AssociationSet*/List<org.hl7.rim.CommunicationFunction> communicationFunction);
  /** Adds an association communicationFunction.
      @see #addCommunicationFunction
  */
  void addCommunicationFunction(org.hl7.rim.CommunicationFunction communicationFunction);
}

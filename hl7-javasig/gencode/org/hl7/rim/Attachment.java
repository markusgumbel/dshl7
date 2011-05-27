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
import org.hl7.types.ED;

import org.hl7.rim.Transmission;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>Contains arbitrary attachments of data blocks to which can be referred to from the interior of the message. Attachments are
   referred to from the message body using the reference functionality of the ED data type.
</p>
<p>OpenIssue: Proper use of this class (Attachment) requires an extension of the referencing mechanism of the ED data type.</p>
*/
public interface Attachment extends org.hl7.rim.InfrastructureRoot {

  /**<p>Identifies the attachment being linked to by an ED attribute contained elsewhere in the interaction.</p>
  */
  II getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(II id);

  /**<p>Contains the data block which plays the role of an attachment.</p>
  */
  ED getText();
  /** Sets the property text.
      @see #getText
  */
  void setText(ED text);

  /**
  */
  org.hl7.rim.Transmission getTransmission();
  /** Sets the property transmission.
      @see #getTransmission
  */
  void setTransmission(org.hl7.rim.Transmission transmission);
}

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
import org.hl7.types.CE;
import org.hl7.types.ED;
import org.hl7.types.SET;
import org.hl7.types.ST;

import org.hl7.rim.Acknowledgement;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A message that provides information about the communication, parsing or non-business-rule validation of the message being
   acknowledged.
</p>
*/
public interface AcknowledgementDetail extends org.hl7.rim.InfrastructureRoot {

  /**<p>Identifies the kind of information specified in the acknowledgement message. Options are: Error, Warning or Information.</p>
  */
  CS getTypeCode();
  /** Sets the property typeCode.
      @see #getTypeCode
  */
  void setTypeCode(CS typeCode);

  /**<p>A code identifying the specific message to be provided.</p>
<p>Discussion: A textual value may be specified as the print name, or for non-coded messages, as the original text.</p>
<p>Examples: 'Required attribute xxx is missing', 'System will be unavailable March 19 from 0100 to 0300'</p>
  */
  CE getCode();
  /** Sets the property code.
      @see #getCode
  */
  void setCode(CE code);

  /**<p>Identifies additional diagnostic information relevant to the message.</p>
<p>Discussion: This may be free text or structured data (e.g. XML).</p>
<p>Examples: Java exception, memory dump, internal error code, call-stack information, etc.</p>
  */
  ED getText();
  /** Sets the property text.
      @see #getText
  */
  void setText(ED text);

  /**<p>Identifies a position within the message being acknowledged that is related to the message.</p>
<p>Discussion: Not all messages will have an associated location. Some messages may relate to multiple locations.</p>
<p>Example: There is no location for a missing element, and there may be two locations if two elements violate a conditionality
   constraint.
</p>
<p>OpenIssue: The specific format for the string that defines the message location needs to be identified. This might be xPath
   or possibly OCL
</p>
  */
  SET<ST> getLocation();
  /** Sets the property location.
      @see #getLocation
  */
  void setLocation(SET<ST> location);

  /**
  */
  org.hl7.rim.Acknowledgement getAcknowledgement();
  /** Sets the property acknowledgement.
      @see #getAcknowledgement
  */
  void setAcknowledgement(org.hl7.rim.Acknowledgement acknowledgement);
}

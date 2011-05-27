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
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CE;
import org.hl7.types.BL;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.AD;
import org.hl7.types.TEL;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.ED;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.LIST;
import org.hl7.types.INT;

import org.hl7.rim.Participation;
import org.hl7.rim.Entity;
import org.hl7.rim.RoleLink;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A competency of the Entity playing the Role as identified, defined, guaranteed, or acknowledged by the Entity that scopes
   the Role.
</p>
<p><i>Discussion:</i> An Entity participates in an Act as in a particular Role. Note that a particular entity in a particular role can participate
   in an act in many ways. Thus, a Person in the role of a practitioner can participate in a patient encounter as a rounding
   physician or as an attending physician. The Role defines the competency of the Entity irrespective of any Act, as opposed
   to Participation, which are limited to the scope of an Act.
</p>
<p>Each role is "played" by one Entity, called the "player" and is "scoped" by another Entity, called the "scoper". Thus the
   Role of "patient" may be played by a person and scoped by the provider organization from which the patient will receive services.
   Similarly, the employer scopes an "employee" role.
</p>
<p>The identifier of the Role identifies the Entity playing the role in that role. This identifier is assigned by the scoping
   Entity to the player. The scoping Entity need not have issued the identifier, but it may have re-used an existing identifier
   for the Entity to also identify the Entity in the Role with the scoper.
</p>
<p>Most attributes of Role are attributes of the playing entity while in the particular role.</p>
*/
public interface BasicRole extends org.hl7.rim.InfrastructureRoot {

  /**<p>A code specifying the major category of a Role as defined by HL7 vocabulary.</p>
  */
  CS getClassCode();
  /** Sets the property classCode.
      @see #getClassCode
  */
  void setClassCode(CS classCode);
}

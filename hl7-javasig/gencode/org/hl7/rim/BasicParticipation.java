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
import org.hl7.types.CD;
import org.hl7.types.INT;
import org.hl7.types.BL;
import org.hl7.types.ED;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.CE;

import org.hl7.rim.Act;
import org.hl7.rim.Role;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An association between an Act and a Role with an Entity playing that Role. Each Entity (in a Role) involved in an Act in a
   certain way is linked to the act by one Participation-instance. The kind of involvement in the Act is specified by the Participation.typeCode.
</p>
<p><i>Examples:</i> 1) Performers of acts (surgeons, observers, practitioners).
</p>
<p>2) Subjects of acts, patient, devices</p>
<p>3) Locations</p>
<p>4) Author, co-signer, witness, informant</p>
<p>5) Addressee, information recipient</p>
<p><i>Rationale:</i> Participations represent performance while Roles represent competence. Participations specify the actual performance of an
   Entity in a certain Act, and thus a Participation is scoped by its specific Act. Conversely, Roles specify the competence
   of an Entity (i.e., how it may principally participate in what kinds of acts) irrespective of any individual Act.
</p>
<p>For example, the professional credentials of a person (Role) may be quite different from what a person actually does (Participation).
   A common example is interns and residents performing anesthesia or surgeries under (more or less) supervision of attending
   specialists. 
</p>
<p>An Act can have multiple participations of the same type, which indicates collaborative activities or group involvement. The
   notion of multiple performing Participations partially overlaps with sub-activities (Act components). Whenever multiple actors
   are involved in an act, each actor performs a different task (with the extremely rare exception of such symmetrical activities
   as two people pulling a rope from either end). Thus, the presence of multiple actors could be equally well represented as
   an act consisting of sub-acts where each act would have only one performing actor 
</p>
<p>For example, a record of a surgical service may include the actors of type: (a) consenter, (b) primary surgeon, and (c) anesthetist.
   These three actors really perform different tasks, which can be represented as three related acts: (a) the consent, (b) the
   surgery proper, and (c) the anesthesia act in parallel to the surgery. If we used the sub-acts, the consenter, surgeon and
   anesthetist could simply be of actor type "performer." Thus the more sub-acts we use, the fewer different actor types we need
   to distinguish; conversely, the fewer sub-acts we use, the more distinct actor types we need.
</p>
<p>As a rule of thumb, sub-tasks should be considered instead of multiple actors when each sub-task requires special scheduling,
   or billing, or if overall responsibilities for the sub-tasks are different. In most cases, however, human resources are scheduled
   by teams (instead of individuals), billing tends to lump many sub-tasks together into one position, and overall responsibility
   often rests with one attending physician, chief nurse, or head of department. This model allows both the multi-actor and the
   multi-act approach to represent the business reality, with a slight bias towards "lumping" minor sub-activities into the overall
   act.
</p>
*/
public interface BasicParticipation extends org.hl7.rim.InfrastructureRoot {

  /**<p>A code specifying the kind of Participation or involvement the Entity playing the Role associated with the Participation has
   with regard to the associated Act.
</p>
<p><i>Constraints:</i> The Participant.typeCode contains only categories that have crisp semantic relevance in the scope of HL7. It is a coded attribute
   without exceptions and no alternative coding systems allowed.
</p>
  */
  CS getTypeCode();
  /** Sets the property typeCode.
      @see #getTypeCode
  */
  void setTypeCode(CS typeCode);
}

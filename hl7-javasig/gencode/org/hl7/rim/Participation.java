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
public interface Participation extends BasicParticipation {

  /**<p>An optional code specifying additional detail about the function that the Participation has in the Act, if such detail is
   not implied by the Participation.typeCode.
</p>
<p><i>Examples:</i> First surgeon, second surgeon (or first assistant surgeon, the one facing the primary surgeon), second assistant (often standing
   next to the primary surgeon), potentially a third assistant, scrub nurse, circulating nurse, nurse assistant, anesthetist,
   attending anesthetist, anesthesia nurse, technician who positions the patient, postoperative watch nurse, assistants, midwives,
   students, etc.
</p>
<p><i>Constraints:</i> This code, if specified at all, <b>must not</b> be in conflict with the Participation.typeCode. Automated systems <b>should not</b> functionally depend on this code. 
</p>
<p>No HL7 standard specification may be written to technically depend on the functionCode. If that is deemed necessary, such
   concepts should be defined in the Participation.typeCode instead.
</p>
<p><i>Discussion:</i> This code can accommodate the huge variety and nuances of functions that participants may perform in the act. The number
   and kinds of functions applicable depends on the special kind of act. E.g., each operation and method may require a different
   number of assistant surgeons or nurses.
</p>
<p>Since participation functions refer to what people do in an Act, these are really sub-activities that may all occur in parallel.
   If any more detail needs to be said about these activities other than just who does them, one should consider using component
   acts instead.
</p>
  */
  CD getFunctionCode();
  /** Sets the property functionCode.
      @see #getFunctionCode
  */
  void setFunctionCode(CD functionCode);

  /**<p>A code that specifies how this Participation contributes to the context of the current Act, and whether it may be propagated
   to descendent Acts whose association allows such propagation (see ActRelationship.contextConductionInd).
</p>
<p><i>Discussion: </i> Refer to ActRelationship.contextControlCode for rationale, discussion and examples.
</p>
  */
  CS getContextControlCode();
  /** Sets the property contextControlCode.
      @see #getContextControlCode
  */
  void setContextControlCode(CS contextControlCode);

  /**<p>An integer specifying the relative order of the Participation in relation to other Participations of the same Act.</p>
<p><i>Rationale:</i> The sequencing might be undertaken for functional reasons or to establish a priority between participations. One example
   is the sequencing of covered party participations to establish a coordination of benefits sequence in insurance claims.
</p>
  */
  INT getSequenceNumber();
  /** Sets the property sequenceNumber.
      @see #getSequenceNumber
  */
  void setSequenceNumber(INT sequenceNumber);

  /**<p>If true, indicates that the specified participation did not, is not or should not occur (depending on mood).</p>
<p><i>Rationale:</i> This has two primary uses: (1) To indicate that a particular Role did not/should not participate in an Act. (2) To remove
   a participant from the context being propagated between Acts.
</p>
<p><i>Discussion:</i> A participation with a negationInd set to true is stronger than one with a negationInd of false. In other words, if there
   is a conflict, the negated participation takes precedence.
</p>
<p><i>Examples:</i> Dr. Smith did not participate; Patient Jones did not sign the consent. 
</p>
  */
  BL getNegationInd();
  /** Sets the property negationInd.
      @see #getNegationInd
  */
  void setNegationInd(BL negationInd);

  /**<p>A textual or multimedia depiction of commentary related to the participation. This note is attributed to this participant
   only.
</p>
  */
  ED getNoteText();
  /** Sets the property noteText.
      @see #getNoteText
  */
  void setNoteText(ED noteText);

  /**<p>An interval of time specifying the time during which the participant is involved in the act through this Participation.</p>
<p><i>Rationale:</i> Participation time is needed when the participant's involvement in the act spans only part of the Act's time. Participation
   time is also used to indicate the time at which certain very common sub-activities happen that are not worth mentioning in
   full acts.
</p>
<p><i>Examples:</i> 1) The time data was entered into the originating system is the Participation.time of the "data entry" participation.
</p>
<p>2) The end of the participation time of an author is the time associated with the signature.</p>
<p>3) The Participation.time of a co-signing participation is also the time of that co-signature.</p>
  */
  IVL<TS> getTime();
  /** Sets the property time.
      @see #getTime
  */
  void setTime(IVL<TS> time);

  /**<p>A code specifying the modality by which the Entity playing the Role is participating in the Act.</p>
<p><i>Examples:</i> Physically present, over the telephone, written communication. 
</p>
<p><i>Rationale:</i> Particularly for author (originator) participants this is used to specify whether the information represented by the act
   was initially provided verbally, (hand-)written, or electronically.
</p>
  */
  CE getModeCode();
  /** Sets the property modeCode.
      @see #getModeCode
  */
  void setModeCode(CE modeCode);

  /**<p>A code specifying the extent to which the Entity playing the participating Role (usually as a target Participation) is aware
   of the associated Act.
</p>
<p><i>Examples:</i> For diagnostic observations, is the patient, family member or other participant aware of the patient's terminal illness?
</p>
<p><i>Discussion:</i> If the awareness, denial, unconsciousness, etc. is the subject of medical considerations (e.g., part of the problem list),
   one should use explicit observations in these matters as well, and should not solely rely on this simple attribute in the
   Participation.
</p>
  */
  CE getAwarenessCode();
  /** Sets the property awarenessCode.
      @see #getAwarenessCode
  */
  void setAwarenessCode(CE awarenessCode);

  /**<p>A code specifying whether and how the participant has attested his participation through a signature and or whether such a
   signature is needed.
</p>
<p><i>Examples:</i> A surgical Procedure act object (representing a procedure report) requires a signature of the performing and responsible
   surgeon, and possibly other participants. (See also: Participation.signatureText.)
</p>
  */
  CE getSignatureCode();
  /** Sets the property signatureCode.
      @see #getSignatureCode
  */
  void setSignatureCode(CE signatureCode);

  /**<p>A textual or multimedia depiction of the signature by which the participant endorses his or her participation in the Act as
   specified in the Participation.typeCode and that he or she agrees to assume the associated accountability.
</p>
<p><i>Examples:</i> 1) An "author" participant assumes accountability for the truth of the Act statement to the best of his knowledge.
</p>
<p>2) An information recipient only attests to the fact that he or she has received the information.</p>
<p><i>Discussion:</i> The signature can be represented in many different ways either inline or by reference according to the ED data type. Typical
   cases are:
</p>
<p>1) Paper-based signatures: the ED data type may refer to some document or file that can be retrieved through an electronic
   interface to a hardcopy archive.
</p>
<p>2) Electronic signature: this attribute can represent virtually any electronic signature scheme.</p>
<p>3) Digital signature: in particular, this attribute can represent digital signatures, for example, by reference to a signature
   data block that is constructed in accordance to a digital signature standard, such as XML-DSIG, PKCS#7, PGP, etc.
</p>
  */
  ED getSignatureText();
  /** Sets the property signatureText.
      @see #getSignatureText
  */
  void setSignatureText(ED signatureText);

  /**<p>Indicates that the resource for this Participation must be reserved before use (i.e. it is controlled by a schedule).</p>
<p><i>Rationale:</i> This attribute serves a very specific need in the context of resource scheduling. It is not needed in the majority of participation
   expressions. In most circumstances, it applies to the participation of a particular location or piece of equipment whose use
   is controlled by a scheduler.
</p>
  */
  BL getPerformInd();
  /** Sets the property performInd.
      @see #getPerformInd
  */
  void setPerformInd(BL performInd);

  /**<p>Indicates the conditions under which a participating item may be substituted with a different one.</p>
  */
  CE getSubstitutionConditionCode();
  /** Sets the property substitutionConditionCode.
      @see #getSubstitutionConditionCode
  */
  void setSubstitutionConditionCode(CE substitutionConditionCode);

  /**<p>Used to indicate that the participation is a filtered subset of the total participations of the same type owned by the Act.
   
</p>
<p>Used when there is a need to limit the participations to the first, the last, the next or some other filtered subset.</p>
  */
  CS getSubsetCode();
  /** Sets the property subsetCode.
      @see #getSubsetCode
  */
  void setSubsetCode(CS subsetCode);

  /**
  */
  org.hl7.rim.Act getAct();
  /** Sets the property act.
      @see #getAct
  */
  void setAct(org.hl7.rim.Act act);

  /**
  */
  org.hl7.rim.Role getRole();
  /** Sets the property role.
      @see #getRole
  */
  void setRole(org.hl7.rim.Role role);
}

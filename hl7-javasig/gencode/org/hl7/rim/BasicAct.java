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
import org.hl7.types.CD;
import org.hl7.types.BL;
import org.hl7.types.ST;
import org.hl7.types.ED;
import org.hl7.types.TS;
import org.hl7.types.CE;
import org.hl7.types.CE;
import org.hl7.types.IVL;
import org.hl7.types.INT;
import org.hl7.types.CE;

import org.hl7.rim.ActRelationship;
import org.hl7.rim.Participation;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A record of something that is being done, has been done, can be done, or is intended or requested to be done.</p>
<p><i>Examples:</i> The kinds of acts that are common in health care are (1) a clinical observation, (2) an assessment of health condition (such
   as problems and diagnoses), (3) healthcare goals, (4) treatment services (such as medication, surgery, physical and psychological
   therapy), (5) assisting, monitoring or attending, (6) training and education services to patients and their next of kin, (7)
   and notary services (such as advanced directives or living will), (8) editing and maintaining documents, and many others.
</p>
<p><i>Discussion and Rationale:</i> Acts are the pivot of the RIM; all domain information and processes are represented primarily in Acts. Any profession or
   business, including healthcare, is primarily constituted of intentional actions, performed and recorded by responsible actors.
   An Act-instance is a record of such an intentional action. Intentional actions are distinguished from something that happens
   by forces of nature (natural events). Such natural events are not Acts by themselves, but may be recorded as observed (Observation).
</p>
<p>Acts connect to Entities in their Roles through Participations and connect to other Acts through ActRelationships. Participations
   are the authors, performers and other responsible parties as well as subjects and beneficiaries (which includes tools and
   material used in the performance of the act, which are also subjects). The moodCode distinguishes between Acts that are meant
   as factual records, vs. records of intended or ordered services, and the other modalities in which act can appear.
</p>
<p>One of the Participations that all acts have (at least implicitly) is a primary author, who is responsible of the Act and
   who "owns" the act. Responsibility for the act means responsibility for what is being stated in the Act and as what it is
   stated. Ownership of the act is assumed in the sense of who may operationally modify the same act. Ownership and responsibility
   of the Act is not the same as ownership or responsibility of what the Act-object refers to in the real world. The same real
   world activity can be described by two people, each being the author of their Act, describing the same real world activity.
   Yet one can be a witness while the other can be a principal performer. The performer has responsibilities for the physical
   actions; the witness only has responsibility for making a true statement to the best of his or her ability. The two Act-instances
   may even disagree, but because each is properly attributed to its author, such disagreements can exist side by side and left
   to arbitration by a recipient of these Act-instances.
</p>
<p>In this sense, an Act-instance represents a "statement" according to Rector and Nowlan (1991) [Foundations for an electronic
   medical record. Methods Inf Med. 30.] Rector and Nowlan have emphasized the importance of understanding the medical record
   not as a collection of facts, but "a faithful record of what clinicians have heard, seen, thought, and done." Rector and Nowlan
   go on saying that "the other requirements for a medical record, e.g., that it be attributable and permanent, follow naturally
   from this view." Indeed the Act class is this attributable statement, and the rules of updating acts (discussed in the state-transition
   model, see Act.statusCode) versus generating new Act-instances are designed according to this principle of permanent attributable
   statements.
</p>
<p>Rector and Nolan focus on the electronic medical record as a collection of statements, while attributed statements, these
   are still mostly factual statements. However, the Act class goes beyond this limitation to attributed factual statements,
   representing what is known as "speech-acts" in linguistics and philosophy. The notion of speech-act includes that there is
   pragmatic meaning in language utterances, aside from just factual statements; and that these utterances interact with the
   real world to change the state of affairs, even directly cause physical activities to happen. For example, an order is a speech
   act that (provided it is issued adequately) will cause the ordered action to be physically performed. The speech act theory
   has culminated in the seminal work by Austin (1962) [How to do things with words. Oxford University Press].
</p>
<p>An activity in the real world may progress from defined, through planned and ordered to executed, which is represented as
   the mood of the Act. Even though one might think of a single activity as progressing from planned to executed, this progression
   is reflected by multiple Act-instances, each having one and only one mood that will not change along the Act-instance's life
   cycle. This is because the attribution and content of speech acts along this progression of an activity may be different,
   and it is often critical that a permanent and faithful record be maintained of this progression. The specification of orders
   or promises or plans must not be overwritten by the specification of what was actually done, so as to allow comparing actions
   with their earlier specifications. Act-instances that describe this progression of the same real world activity are linked
   through the ActRelationships (of the relationship category "sequel").
</p>
<p>Act as statements or speech-acts are the only representation of real world facts or processes in the HL7 RIM. The truth about
   the real world is constructed through a combination (and arbitration) of such attributed statements only, and there is no
   class in the RIM whose objects represent "objective state of affairs" or "real processes" independent from attributed statements.
   As such, there is no distinction between an activity and its documentation. Every Act includes both to varying degrees. For
   example, a factual statement made about recent (but past) activities, authored (and signed) by the performer of such activities,
   is commonly known as a procedure report or original documentations (e.g., surgical procedure report, clinic note etc.). Conversely,
   a status update on an activity that is presently in progress, authored by the performer (or a close observer) is considered
   to capture that activity (and is later superceded by a full procedure report). However, both status update and procedure report
   are acts of the same kind, only distinguished by mood and state (see statusCode) and completeness of the information.
</p>
*/
public interface BasicAct extends org.hl7.rim.InfrastructureRoot {

  /**<p>A code specifying the major type of Act that this Act-instance represents.</p>
<p><i>Constraints:</i> The classCode domain is a tightly controlled vocabulary, not an external or user-defined vocabulary. 
</p>
<p>Every Act-instance must have a classCode. If the act class is not further specified, the most general Act.classCode (ACT)
   is used.
</p>
<p>The Act.classCode must be a generalization of the specific Act concept (e.g., as expressed in Act.code), in other words, the
   Act concepts conveyed in an Act must be specializations of the Act.classCode. Especially, the classCode is not a "modifier"
   or the Act.code that can alter the meaning of a class code. (See Act.code for contrast.)
</p>
  */
  CS getClassCode();
  /** Sets the property classCode.
      @see #getClassCode
  */
  void setClassCode(CS classCode);

  /**<p>A code distinguishing whether an Act is conceived of as a factual statement or in some other manner as a command, possibility,
   goal, etc.
</p>
<p><i>Constraints:</i> An Act-instance must have one and only one moodCode value.
</p>
<p>The moodCode of a single Act-instance never changes. Mood is not state.</p>
<p>To describe the progression of a business activity from defined to planned to executed, etc. one must instantiate different
   Act-instances in the different moods and link them using ActRelationship of general type "sequel". (See ActRelationship.typeCode.)
</p>
<p><i>Discussion:</i> The Act.moodCode includes the following notions: (1) event, i.e., factual description of an actions that occurred; (2) definition
   of possible actions and action plans (the master file layer); (3) intent, i.e., an action plan instantiated for a patient
   as a care plan or order; (4) goal, i.e., an desired outcome attached to patient problems and plans; and (5) criterion, i.e.,
   a predicate used to evaluate a logical expression.
</p>
<p>The Act.moodCode modifies the meaning of the Act class in a controlled way, just as in natural language, grammatical form
   of a verb modify the meaning of a sentence in defined ways. For example, if the mood is factual (event), then the entire act
   object represents a known fact. If the mood expresses a plan (intent), the entire act object represents the expectation of
   what should be done. The mood does not change the meaning of individual act properties in peculiar ways.
</p>
<p>Since the mood code is a determining factor for the meaning of an entire Act object, the mood must always be known. This means,
   whenever an act object is instantiated, the mood attribute must be assigned to a valid code, and the mood assignment cannot
   change throughout the lifetime of an act object.
</p>
<p>As the meaning of an act object is factored in the mood code, the mood code affects the interpretation of the entire Act object
   and with it every property (attributes and associations). Note that the mood code affects the interpretation of the act object,
   and the meaning of the act object in turn determines the meaning of the attributes. However, the mood code does not arbitrarily
   change the meaning of individual attributes.
</p>
<p><i>Inert vs. descriptive properties of Acts:</i> Acts have two kinds of act properties, inert and descriptive properties. Inert properties are not affected by the mood, descriptive
   properties follow the mood of the object. For example, there is an identifier attribute Act.id, which gives a unique identification
   to an act object. Being a unique identifier for the object is in no way dependent on the mood of the act object. Therefore,
   the "interpretation" of the Act.id attribute is inert with respect to the act object's mood.
</p>
<p>By contrast, most of the Act class' attributes are descriptive for what the Act statement expresses. Descriptive properties
   of the Act class give answer to the questions who, whom, where, with what, how and when the action is done. The questions
   who, whom, with what, and where are answered by Participations, while how and when are answered by descriptive attributes
   and ActRelationships. The interpretation of a descriptive attribute is aligned to the interpretation of the entire act object,
   and controlled by the mood. 
</p>
<p><i>Examples:</i> To illustrate the effect of mood code, consider a "blood glucose" observation:
</p>
<p>The DEFINITION mood specifies the Act of "obtaining blood glucose". Participations describe in general the characteristics
   of the people who must be involved in the act, and the required objects, e.g., specimen, facility, equipment, etc. involved.
   The Observation.value specifies the absolute domain (range) of the observation (e.g., 15-500 mg/dl).
</p>
<p>In INTENT mood the author of the intent expresses the intent that he or someone else "should obtain blood glucose". The participations
   are the people actually or supposedly involved in the intended act, especially the author of the intent or any individual
   assignments for group intents, and the objects actually or supposedly involved in the act (e.g., specimen sent, equipment
   requirements, etc.). The Observation.value is usually not specified, since the intent is to measure blood glucose, not to
   measure blood glucose in a specific range. (But compare with GOAL below).
</p>
<p>In REQUEST mood, a kind of intent, the author requests to "please obtain blood glucose". The Participations are the people
   actually and supposedly involved in the act, especially the placer and the designated filler, and the objects actually or
   supposedly involved in the act (e.g., specimen sent, equipment requirements, etc.). The Observation.value is usually not specified,
   since the order is not to measure blood glucose in a specific range.
</p>
<p>In EVENT mood, the author states that "blood glucose was obtained". Participations are the people actually involved in the
   act, and the objects actually involved (e.g., specimen, facilities, equipment). The Observation.value is the value actually
   obtained (e.g., 80 mg/dL, or &lt;15 mg/dL).
</p>
<p>In event-CRITERION mood, an author considers a certain class of "obtaining blood glucose" possibly with a certain value (range)
   as outcome. The Participations constrain the criterion, for instance, to a particular patient. The Observation.value is the
   range in which the criterion would hold (e.g. &gt; 180 mg/dL or 200-300 mg/dL).
</p>
<p>In GOAL mood (a kind of criterion), the author states that "our goal is to be able to obtain blood glucose with the given
   value (range)". The Participations are similar to intents, especially the author of the goal and the patient for whom the
   goal is made. The Observation.value is the range which defined when the goal is met (e.g. 80-120 mg/dl).
</p>
<p><i>Rationale:</i> The notion of "mood" is borrowed from natural language grammar, the mood of a verb (lat. modus verbi). 
</p>
<p>The notion of mood also resembles the various extensions of the logic of facts in modal logic and logic with modalities, where
   the moodCode specifies the modality (fact, possibility, intention, goal, etc.) under which the Act-statement is judged as
   appropriate or defective.
</p>
  */
  CS getMoodCode();
  /** Sets the property moodCode.
      @see #getMoodCode
  */
  void setMoodCode(CS moodCode);
}

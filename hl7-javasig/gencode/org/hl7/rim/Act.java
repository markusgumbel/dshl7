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
public interface Act extends BasicAct {

  /**<p>A unique identifier for the Act.</p>
  */
  SET<II> getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(SET<II> id);

  /**<p>A code specifying the particular kind of Act that the Act-instance represents within its class.</p>
<p><i>Constraints:</i> The kind of Act (e.g. physical examination, serum potassium, inpatient encounter, charge financial transaction, etc.) is
   specified with a code from one of several, typically external, coding systems. The coding system will depend on the class
   of Act, such as LOINC for observations, etc.
</p>
<p>Conceptually, the Act.code must be a specialization of the Act.classCode. This is why the structure of ActClass domain should
   be reflected in the superstructure of the ActCode domain and then individual codes or externally referenced vocabularies subordinated
   under these domains that reflect the ActClass structure.
</p>
<p>Act.classCode and Act.code are not modifiers of each other but the Act.code concept should really imply the Act.classCode
   concept. For a negative example, it is not appropriate to use an Act.code "potassium" together with and Act.classCode for
   "laboratory observation" to somehow mean "potassium laboratory observation" and then use the same Act.code for "potassium"
   together with Act.classCode for "medication" to mean "substitution of potassium". This mutually modifying use of Act.code
   and Act.classCode is not permitted.
</p>
<p><i>Discussion:</i> Act.code is not a required attribute of Act. Rather than naming the kind of Act using an Act.code, one can specify the Act
   using only the class code and other attributes and properties of the Act. In general and more commonly, the kind of Act is
   readily specified by an ActRelationship specifying that this Act instantiates another Act in definition mood. Or, even without
   reference to an act definition, the act may be readily described by other attributes, ActRelationships and Participations.
   For example, the kind of SubstanceAdministration may be readily described by referring to the specific drug, as the Participation
   of an Entity representing that drug.
</p>
  */
  CD getCode();
  /** Sets the property code.
      @see #getCode
  */
  void setCode(CD code);

  /**<p>An indicator specifying that the Act statement is a negation of the Act as described by the descriptive attributes.</p>
<p><i>Examples:</i> Used with an Observation event, it allows one to say "patient has NO chest pain". With an Observation criterion it negates
   the criterion analogously, e.g., "if patient has NO chest pain for 3 days ...", or "if systolic blood pressure is <b>not</b> within 90-100 mm Hg ..."
</p>
<p><i>Discussion:</i> The negationInd works as a negative existence quantifier. This is best explained on Acts in criterion mood, and then translates
   into all other moods. In criterion mood without negation, one usually only specifies a few critical attributes and relationships
   (features) of an Act, i.e., only those that are needed to test the criterion. The more features one specifies, the more constrained
   (specific) is the criterion. For example, to test for "systolic blood pressure of 90-100 mm Hg", one would use only the descriptive
   attributes Act.code (for systolic blood pressure) and Observation.value (for 90-100 mm Hg). If one would also specify an effectiveTime,
   i.e., for "yesterday", the criterion would be more constrained. If the negationInd is true for the above criterion, then the
   meaning of the test is whether a systolic blood pressure of 90-100 mm Hg yesterday does <b>not exist</b> (independent of whether any blood pressure was measured).
</p>
<p>The negationInd negates the Act as described by the descriptive properties (including Act.code, Act.effectiveTime, Observation.value,
   Act.doseQty, etc.) and any of its components. The inert properties such as Act.id, Act.moodCode, Act.confidentialityCode,
   and particularly the Author-Participation are <b>not</b> negated. These inert properties always have the same meaning: i.e., the author remains to be the author of the negative observation.
   Also, most ActRelationships (except for components) are not included in the negation. 
</p>
<p>For example, a highly confidential order written by Dr. Jones, to explicitly <b>not</b> give "succinyl choline" for the "reason" (ActRelationship) of a history of malignant hyperthermia (Observation) negates the
   descriptive properties "give succinyl choline" (Act.code), but it is still positively an order and written by Dr. Jones and
   for patient John Smith, and the reason for this order is the patient's history of malignant hyperthermia. 
</p>
<p>However, additional detail in descriptive attributes will be part of the negation which then limits the effectiveness of the
   negated statement. For example, had the order not to give a substance included a doseQuantity, it would mean that the substance
   should not be given at that particular dose (but any other dose might still be O.K.).
</p>
<p>An act statement with negationInd is still a statement about the specific fact described by the Act. For instance, a negated
   "finding of wheezing on July 1" means that the author positively denies that there was wheezing on July 1, and that he takes
   the same responsibility for such statement and the same requirement to have evidence for such statement than if he had not
   used negation. Conversely, negation indicator does <b>not</b> just negate that the fact was affirmed or that the statement had been made. This holds for all moods in the same way, e.g.,
   a negated order is an order <b>not</b> to do the described act, not just the lapidary statement that there is no such order.
</p>
  */
  BL getNegationInd();
  /** Sets the property negationInd.
      @see #getNegationInd
  */
  void setNegationInd(BL negationInd);

  /**<p>A character string containing a formal language expression that specifies how the Act's attributes are (should be or have
   been) derived from input parameters associated with derivation relationships.
</p>
<p><i>Discussion:</i> Derived observations can be defined through association with other observations using ActRelationships of type "derivation".
   For example, to define a derived observation for Mean Corpuscular Hemoglobin (MCH) one will associate the MCH observation
   with a Hemoglobin (HGB) observation and a Red Blood cell Count (RBC) observation. And the derivation expression encodes the
   formula: MCH = HGB / RBC.
</p>
<p>The derivation expression is represented as a character string.</p>
<p>[Note: The syntax of that expression is yet to be fully specified. There would be a single standard expression language rather
   than an optional choice between many expression languages. The syntax would be based on a de-facto standard for many object-oriented
   languages, such as C++, Java, OCL etc. A concrete specification of this expression language is being worked on now and drafts
   can be expected within the year 2003.]
</p>
  */
  ST getDerivationExpr();
  /** Sets the property derivationExpr.
      @see #getDerivationExpr
  */
  void setDerivationExpr(ST derivationExpr);

  /**<p>A word or phrase by which a specific Act may be known among people.</p>
<p><i>Example:</i> name of a research study (e.g. "Scandinavian Simvastatin Study"), name of a court case (e.g. "Brown v. Board of Education"),
   name of another kind of work project or operation. For acts representing documents, this is the title of the document.
</p>
<p><b>CONSTRAINT:</b> Previous to release 2.05 of the RIM, this attribute bore the datatype <b>ST</b>. From release 2.05 onwards, the datatype was extended to a <b>constrained</b> restriction of the <b>ED</b> datatype. The constraints to be imposed are identical to those for the <b>ST</b> datatype, except that the mediaType shall be "text/x-hl7-title+xml" or "text/plain". The intent is to allow sufficient mark-up
   to convey the semantics of scientific phrases, such as chemical compounds. This markup must not be used to convey simple display
   preferences. The default mediaType should be "text/plain".
</p>
<p><i>Discussion:</i> This is not a formal identifier but rather a human-recognizable common name. However it is similar to the id attribute in
   that it refers to a specific Act rather than a 'kind' of act. (For definition mood, the title refers to that specific definition,
   rather than to a broad category that might be conveyed with Act.code.)
</p>
<p><i>Note:</i> This attribute was <b>not</b> in the normative content balloted and approved for the first release of HL7's Reference Information Model Standard. The attribute
   will be considered when the RIM is prepared for balloting the second release. The attribute is being used in current HL7 Version
   3 designs.
</p>
  */
  ED getTitle();
  /** Sets the property title.
      @see #getTitle
  */
  void setTitle(ED title);

  /**<p>A textual or multimedia description (or reference to a description) of the Act.</p>
<p><i>Examples:</i> For act definitions, the Act.text can contain textbook-like information about that act. For act orders, the description will
   contain particular instructions pertaining only to that order. 
</p>
<p><i>Constraints:</i> No restriction on length or content is imposed on the Act.text attribute. 
</p>
<p>The content of the description is not considered part of the functional information communicated between computer systems.
   For Acts that involve human readers and performers, however, computer systems must show the Act.text field to a human user,
   who has responsibility for the activity; or at least must indicate the existence of the Act.text information and allow the
   user to see that information.
</p>
<p>Free text descriptions are used to help an individual interpret the content and context of the act, but all information relevant
   for automated functions must be communicated using the proper attributes and associated objects.
</p>
  */
  ED getText();
  /** Sets the property text.
      @see #getText
  */
  void setText(ED text);

  /**<p>A code specifying the state of the Act.</p>
<p><b>Design Advisory: </b>This attribute was defined in the original RIM as repeating, owing to the presence of nested states in the state machines.
   In actual practice, however, there is never a need to communicate more than a single status value. therefore, committees are
   advised to <b>constrain this attribute to a maximum cardinality of 1</b> in all message designs. 
</p>
  */
  CS getStatusCode();
  /** Sets the property statusCode.
      @see #getStatusCode
  */
  void setStatusCode(CS statusCode);

  /**<p>A time expression specifying the focal or operative time of the Act, the primary time for which the Act holds, the time of
   interest from the perspective of the Act's intention.
</p>
<p><i>Examples:</i> For clinical Observations, the effectiveTime is the time at which the observation holds (is effective) for the patient.
</p>
<p>For contracts, the effectiveTime is the time for which the contract is in effect.</p>
<p>For consents, the effectiveTime is the time for which the consent is valid.</p>
<p>For substance administrations, the effective time is the time over which the substance is to be administered, including the
   frequency of administration (e.g. TID for 10 days)
</p>
<p>For a surgical procedure (operation), the effectiveTime is the time relevant for the patient, i.e., between incision and last
   suture. 
</p>
<p>For transportation acts, the effective time is the time the transported payload is en route.</p>
<p>For patient encounters, this is the "administrative" time, i.e., the encounter start and end date required to be chosen by
   business rules, as opposed to the actual time the healthcare encounter related work is performed.
</p>
<p><i>Discussion:</i> The effectiveTime is also known as the "primary" time (Arden Syntax) or the "biologically relevant time" (HL7 v2.x). This
   attribute is distinguished from activityTime.
</p>
<p>For observations, the time of the observation activity may be much later than the time of the observed feature. For instance,
   in a Blood Gas Analysis (BGA), a result will always come up several minutes after the specimen was taken, meanwhile the patient's
   physiological state may have changed significantly. 
</p>
<p>For essentially physical activities (surgical procedures, transportations, etc.), the effective time is the time of interest
   for the Act's intention, i.e., since the intention of a transportation is to deliver a payload from location A to B, the effectiveTime
   is the time this payload is underway from A to B. However, the Act usually also includes accidental work which is necessary
   to perform the intention of the Act, but is not relevant for the Act's purpose. 
</p>
<p>For example, the time a driver needs to go to the pick-up location A and then return from drop-off location B to some home
   base, is included in the physical activity, but does not matter from the perspective of the payload's transportation. Another
   example is: a person's work hours (effectiveTime) may be from 8 AM to 5 PM, no matter whether that person needs 10 minutes
   for the commute or 2 hours. The commute is necessary to be at work, but it is not essential for the work hours.
</p>
  */
  SET<TS> getEffectiveTime();
  /** Sets the property effectiveTime.
      @see #getEffectiveTime
  */
  void setEffectiveTime(SET<TS> effectiveTime);

  /**<p>A time expression specifying when an Observation, Procedure, or other Act occurs, or, depending on the mood, is supposed to
   occur, scheduled to occur, etc. It tells you when the labor was done for an Act. The activityTime includes the times of component
   actions (such as preparation and clean-up)For Procedures and SubstanceAdministrations, the activityTime can provide a needed
   administrative / scheduling function by providing a more complete time that needs to be anticipated for particular acts.
</p>
<p>Discussion: The activityTime is more of administrative rather than clinical use. The clinically relevant time is the effectiveTime.
   When an observation of a prior symptom is made, the activityTime describes the time the observation is made, as opposed to
   effectiveTime which is the time the symptom occurred. Thus the activityTime may be entirely different than the effectiveTime
   of the same Act. But even apart from clinical use cases, designers should first consider effectiveTime as the primary relevant
   time for an Act.
</p>
<p>The activityTime is a descriptive attribute, i.e., like effectiveTime, it always describes the Act event as it does or would
   occur, even when working with different moods. For example, when a procedure is requested, the activityTime describes the
   requested total time of the procedure. By contrast, the author Participation.time is inert, i.e., author participation time
   on an order specifies when the order was written and has nothing to do with when the event might actually occur.
</p>
<p>ActivityTime indicates when an Act occurs, not when an Act is recorded. Many applications track the time an observation is
   recorded rather than the precise time during which an observation is made, in which case Participation.time (e.g. of the Author)
   should be used. These recorded observations can take place during an encounter, and the time of the encounter often provides
   enough information so that activityTime isn't clinically relevant.
</p>
  */
  SET<TS> getActivityTime();
  /** Sets the property activityTime.
      @see #getActivityTime
  */
  void setActivityTime(SET<TS> activityTime);

  /**<p>The point in time at which information about Act-instance (regardless of mood) first became available to a system reproducing
   this Act. 
</p>
<p><i>Examples:</i> An Act might record that a patient had a right-ventricular myocardial infarction effective three hours ago (see Act.effectiveTime),
   but we may only know about this unusual condition a few minutes ago (Act.availabilityTime). Thus, any interventions from three
   hours ago until a few minutes ago may have assumed the more common left-ventricular infarction, which can explain why these
   interventions (e.g., nitrate administration) may not have been appropriate in light of the more recent knowledge.
</p>
<p><i>Discussion:</i> The availabilityTime is a subjective secondary piece of information added (or changed) by a system that reproduces this Act,
   and is not attributed to the author of the act statement (it would not be included in the material the author would attest
   to with a signature). The system reproducing the Act is often not the same as the system originating the Act, but a system
   that received this Act from somewhere else, and, upon receipt of the Act, values the availabilityTime to convey the time since
   the users of that particular system could have known about this Act-instance.
</p>
<p>When communicating availabilityTime to another system, the availabilityTime of an Act A is attributed to the author of another
   Act B, that refers to or includes A. For example, if a medical record extract is compiled for reporting adverse events, availabilityTimes
   are attributed to the author who compiles that report.
</p>
  */
  TS getAvailabilityTime();
  /** Sets the property availabilityTime.
      @see #getAvailabilityTime
  */
  void setAvailabilityTime(TS availabilityTime);

  /**<p>A code or set of codes (e.g., for routine, emergency), specifying the urgency under which the Act happened, can happen, is
   happening, is intended to happen, or is requested/demanded to happen. 
</p>
<p><i>Discussion:</i> This attribute is used in orders to indicate the ordered priority, and in event documentation it indicates the actual priority
   used to perform the act. In definition mood it indicates the available priorities.
</p>
  */
  SET<CE> getPriorityCode();
  /** Sets the property priorityCode.
      @see #getPriorityCode
  */
  void setPriorityCode(SET<CE> priorityCode);

  /**<p>A code that controls the disclosure of information about this Act, regardless of mood.</p>
<p><i>Discussion:</i> It is important to note that the necessary confidentiality of the medical record cannot be achieved solely through confidentiality
   codes to mask individual record items from certain types of users. There are two important problems with per-item confidentiality:
   one is inference and the other is the danger of holding back information that may be critical in a certain care situation.
   Inference means that filtered sensitive information can still be assumed given the other information that was not filtered.
   The simplest form of inference is that even the existence of a test order for an HIV Western Blot test or a T4/T8 lymphocyte
   count is a strong indication for an existing HIV infection, even if the results are not known. Very often, diagnoses can be
   inferred from medication, such as Zidovudin for treatment of HIV infections. The problem of hiding individual items becomes
   especially difficult with current medications, since the continuing administration of the medication must be assured.
</p>
<p>To mitigate some of the inference-risk, aggregations of data should assume the confidentiality level of the most confidential
   action in the aggregation.
</p>
  */
  SET<CE> getConfidentialityCode();
  /** Sets the property confidentialityCode.
      @see #getConfidentialityCode
  */
  void setConfidentialityCode(SET<CE> confidentialityCode);

  /**<p>An interval of integer numbers stating the minimal and maximal number of repetitions of the Act.</p>
<p><i>Examples:</i> An oral surgeon's advice to a patient after tooth extraction might be: "replace the gauze every hour for 1 to 3 times until
   bleeding has stopped completely." This translates to repeatNumber with low boundary 1 and high boundary 3.
</p>
<p><i>Discussion:</i> This attribute is a member of the workflow control suite of attributes. 
</p>
<p>The number of repeats is additionally constrained by time. The act will repeat at least the minimal number of times and at
   most, the maximal number of times. Repetitions will also terminate when the time exceeds the maximal Act.effectiveTime, whichever
   comes first.
</p>
<p><i>Usage:</i> On an Act in Event mood, the repeatNumber is usally 1. If greater than 1, the Act is representing a summary of several event
   occurrences occurring over the time interval described by effectiveTime
</p>
<p>To distinguish occurrences of acts within a sequence of repetitions, use ActRelationship.sequenceNumber</p>
  */
  IVL<INT> getRepeatNumber();
  /** Sets the property repeatNumber.
      @see #getRepeatNumber
  */
  void setRepeatNumber(IVL<INT> repeatNumber);

  /**<p>An indicator specifying whether Act is interruptible by asynchronous events.</p>
<p><i>Discussion:</i> This attribute is part of the suite of workflow control attributes. Act events that are currently active can be interrupted
   in various ways. Interrupting events include: (1) when an explicit abort request is received against the Act (2) when the
   time allotted to this Act expires (timeout); (3) a "through condition" ceases to hold true for this Act (see ActRelationship.checkpointCode);
   (4) the Act is a component with the joinCode "kill" and all other components in that same group have terminated (see Act.joinCode);
   and (5) when a containing Act is being interrupted. 
</p>
<p>If an Act receives an interrupt and the Act itself is interruptible, but it has currently active component-Acts that are non-interruptible,
   the Act will be interrupted when all of its currently active non-interruptible component-acts have terminated.
</p>
  */
  BL getInterruptibleInd();
  /** Sets the property interruptibleInd.
      @see #getInterruptibleInd
  */
  void setInterruptibleInd(BL interruptibleInd);

  /**<p>Code specifying the level within a hierarchical Act composition structure and the kind of contextual information attached
   to composite Acts ("containers") and propagated to component Acts within those containers. The levelCode signifies the position
   within such a containment hierarchy and the applicable constraints.
</p>
<p><i>Discussion:</i> Readers should be aware that <b>this attribute may be declared "obsolescent"</b> in the next normative release of the HL7 RIM. An alternate representation of this concept using a specified hierarchy of
   Act classCode values is being considered. If the change is adopted, HL7's RIM maintenance procedures state that the levelCode
   would be declared "obsolescent" in the next RIM release, and then become "obsolete" in the release following that. Users are
   advised to check with the latest HL7 internal definitions of the RIM, before using this attribute.
</p>
<p>The levelCode concepts have been defined to meet specific health record transfer requirements. While these concepts are known
   to be applicable to some other types of transactions, they are not intended to be a complete closed list. Options exist for
   other sets of orthogonal levels where required to meet a business purpose (e.g. a multiple patient communication may be subdivided
   by a super-ordinate level of subject areas).
</p>
<p><i>Examples:</i> The "extract level" and the "folder level" must contain data about a single individual, whereas the "multiple subject level"
   may contain data about multiple individuals. While "extract" can originate from multiple sources, a "folder" should originate
   from a single source. The "composition" level usually has a single author.
</p>
<p><i>Constraints:</i> The constraints applicable to a particular level may include differing requirements for participations (e.g. patient, source
   organization, author or other signatory), relationships to or inclusion of other Acts, documents or use of templates. The
   constraints pertaining to a level may also specify the permissible levels that may be contained as components of that level.
   Several nested levels with the same levelCode may be permitted, prohibited (or limited). Instances of the next subordinate
   level are usually permitted within any level but some levels may be omitted from a model and it may be permissible to skip
   several layers.
</p>
  */
  CE getLevelCode();
  /** Sets the property levelCode.
      @see #getLevelCode
  */
  void setLevelCode(CE levelCode);

  /**<p>An indicator specifying whether the Act can be manipulated independently of other Acts or whether manipulation of the Act
   can only be through a super-ordinate composite Act that has this Act as a component. By default the independentInd should
   be true.
</p>
<p><i>Examples:</i> An Act definition is sometimes marked with independentInd=false if the business rules would not allow this act to be ordered
   without ordering the containing act group.
</p>
<p>An order may have a component that cannot be aborted independently of the other components.</p>
  */
  BL getIndependentInd();
  /** Sets the property independentInd.
      @see #getIndependentInd
  */
  void setIndependentInd(BL independentInd);

  /**<p>A code indicating whether the Act statement as a whole, with its subordinate components has been asserted to be uncertain
   in any way. 
</p>
<p><i>Examples:</i> Patient might have had a cholecystectomy procedure in the past (but isn't sure).
</p>
<p><i>Constraints:</i> Uncertainty asserted using this attribute applies to the combined meaning of the Act statement established by all descriptive
   attributes (e.g., Act.code, Act.effectiveTime, Observation.value, SubstanceAdministration.doseQuantity, etc.), and the meanings
   of any components.
</p>
<p><i>Discussion:</i>This is not intended for use to replace or compete with uncertainty associated with Observation.value alone or other individual
   attributes of the class. Such pointed indications of uncertainty should be specified by applying the PPD, UVP or UVN data
   type extensions to the specific attribute. Particularly if the uncertainty is uncertainty of a quantitative measurement value,
   this must still be represented by a PPD&lt;PQ&gt; in the value and<b>not</b>using the uncertaintyCode. Also, when differential diagnoses are enumerated or weighed for probability, the UVP&lt;CD&gt; or UVN&lt;CD&gt;
   must be used, not the uncertaintyCode. The use of the uncertaintyCode is appropriate only if the entirety of the Act and its
   dependent Acts is questioned.
</p>
<p>Note that very vague uncertainty may be thought related to negationInd, however, the two concepts are really independent.
   One may be very uncertain about an event, but that does not mean that one is certain about the negation of the event.
</p>
  */
  CE getUncertaintyCode();
  /** Sets the property uncertaintyCode.
      @see #getUncertaintyCode
  */
  void setUncertaintyCode(CE uncertaintyCode);

  /**<p>A code specifying the motivation, cause, or rationale of an Act, when such rationale is not reasonably represented as an ActRelationship
   of type "has reason" linking to another Act.
</p>
<p><i>Examples:</i> Example reasons that might qualify for being coded in this field might be: "routine requirement", "infectious disease reporting
   requirement", "on patient request", "required by law".
</p>
<p>Discussion </p>
<p>Most reasons for acts can be clearly expressed by linking the new Act to another prior Act using an ActRelationship of type
   "has reason". This simply states that the prior Act is a reason for the new Act (see ActRelationship). The prior act can then
   be a specific existing act or a textual explanation. This works for most cases, and the more specific the reason data is,
   the more should this reason ActRelationship be used instead of the reasonCode. 
</p>
<p>The reasonCode remains as a place for common reasons that are not related to a prior Act or any other condition expressed
   in Acts. Indicators that something was required by law or was on the request of a patient etc. may qualify. However, if that
   piece of legislation, regulation, or the contract or the patient request can be represented as an Act (and they usually can),
   the reasonCode should not be used.
</p>
  */
  SET<CE> getReasonCode();
  /** Sets the property reasonCode.
      @see #getReasonCode
  */
  void setReasonCode(SET<CE> reasonCode);

  /**<p>The primary language in which this Act statement is specified, particularly the language of the Act.text.</p>
  */
  CE getLanguageCode();
  /** Sets the property languageCode.
      @see #getLanguageCode
  */
  void setLanguageCode(CE languageCode);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.ActRelationship> getOutboundRelationship();
  /** Sets the property outboundRelationship.
      @see #getOutboundRelationship
  */
  void setOutboundRelationship(/*AssociationSet*/List<org.hl7.rim.ActRelationship> outboundRelationship);
  /** Adds an association outboundRelationship.
      @see #addOutboundRelationship
  */
  void addOutboundRelationship(org.hl7.rim.ActRelationship outboundRelationship);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.ActRelationship> getInboundRelationship();
  /** Sets the property inboundRelationship.
      @see #getInboundRelationship
  */
  void setInboundRelationship(/*AssociationSet*/List<org.hl7.rim.ActRelationship> inboundRelationship);
  /** Adds an association inboundRelationship.
      @see #addInboundRelationship
  */
  void addInboundRelationship(org.hl7.rim.ActRelationship inboundRelationship);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Participation> getParticipation();
  /** Sets the property participation.
      @see #getParticipation
  */
  void setParticipation(/*AssociationSet*/List<org.hl7.rim.Participation> participation);
  /** Adds an association participation.
      @see #addParticipation
  */
  void addParticipation(org.hl7.rim.Participation participation);
}

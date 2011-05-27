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
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.PQ;
import org.hl7.types.ST;
import org.hl7.types.CE;

import org.hl7.rim.Act;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A directed association between a source Act and a target Act. ActRelationship on the same source Act are called the "outbound"
   act relationships of that Act. ActRelationships on the same target Act are called the "inbound" relationships of that Act.
   The meaning and purpose of an ActRelationship is specified in the ActRelationship.typeCode attribute. 
</p>
<p><i>Examples:</i> 1) An electrolyte observation panel may have sodium, potassium, pH, and bicarbonate observations as components. The composite
   electrolyte panel would then have 4 outbound ActRelationships of type "has component".
</p>
<p>2) The electrolyte panel event has been performed in fulfillment of an observation order. The electrolyte panel event has
   an outbound ActRelationship of type "fulfills" with the order as target.
</p>
<p>3) A Procedure "cholecystectomy" may be performed for the reason of an Observation of "cholelithiasis". The procedure has
   an outbound ActRelationship of type "has reason" to the cholelithiasis observation.
</p>
<p><i>Discussion:</i> Consider every ActRelationship instance an arrow with a point (headed to the target) and a butt (coming from the source).
   The functions (sometimes called "roles") that source and target Acts play in that association are defined for each ActRelationship
   type differently. For instance in a composition relationship, the source is the composite and the target is the component.
   In a reason-relationship the source is any Act and the target is the reason or indication for the source-Act.
</p>
<p>The relationships associated with an Act are considered properties of the source act object. This means that the author of
   an Act-instance is also considered the author of all of the act relationships that have this Act as their source. There are
   no exceptions to this rule.
</p>
<p>See ActRelationship.typeCode for more overview of the different kinds of ActRelationships.</p>
<p>The ActRelationship class is used to construct action plans and to represent clinical reasoning or judgments about action
   relationships. Prior actions can be linked as the reasons for more recent actions. Supporting evidence can be linked with
   current clinical hypotheses. Problem lists and other networks of related judgments about clinical events are represented by
   the ActRelationship link.
</p>
<p>One of the most commonly used ActRelationship types is "has component" to describe the composition and de-composition of Acts.
   The relationship type allows specifying detail of Acts to varying degrees.
</p>
<p>The composition relationship can group actions into "batteries," e.g., LYTES, CHEM12, or CBC, where multiple routine laboratory
   tests are ordered as a group. Some groupings, such as CHEM12, appear more arbitrary; others, such as blood pressure, seem
   to naturally consist of systolic and diastolic pressure.
</p>
<p>The composition relationships can be arranged in a sequence to form temporal and conditional (non-temporal) action plans (e.g.,
   care plan, critical path, clinical trials protocol, drug treatment protocols). There is a group of attributes in both Act
   and ActRelationship that we refer to as the "workflow Control suite of attributes", and which allow the detailed specification
   of executable action plans. These attributes are:
</p>
<p>ActRelationship.sequenceNumber arranges the components of an Act as a sequence or as concurrent collections of components,
   expressing logical branches as well as parallel tasks (tasks carried out at the same time). The ActRelationship attributes
   splitCode and joinCode control how branches are selected or executable in parallel.
</p>
<p>Act.activityTime and ActRelationship.pauseQty allow one to explicitly time an action plan. Act.repeatNumber allows specifying
   act to repeat (loop).
</p>
<p>The ActRelationship type has-precondition allows plan steps to be conditional on the status or outcome of previous actions.
   The ActRelationhsip.checkpointCode specifies when pre-conditions of acts are tested during the flow of control.
</p>
<p>The composition ActRelationship allows these constructs to be organized in multiple layers of nesting to fully support workflow
   management. This nesting and the workflow control attributes are designed in analogy to a block-structured programming language
   with support for concurrency (fork, join, interrupts), and without "goto" statements. It is important to note that ALL plans
   are established through sequencing components (steps) in a composite act (block) as can be depicted in "Nassi-Schneiderman"
   diagrams (also known as "Chap Charts" or "Structograms"), not by chain-linking acts as in a flowchart diagram. 
</p>
<p>With the composition relationship, the detail of Acts can be revealed to different levels for different purposes, without
   the structure of the Act hierarchy needing to be rearranged. This allows supporting multiple viewpoints on the same business
   processes. For instance, a billing-viewpoint of a laboratory test battery may be as a single billable act. A clinician's view
   of the same laboratory test battery is as a set of individual observations, where the ordering among the observations is irrelevant.
   The laboratory's view of this act will be more detailed, including action plan steps that are never reported to the clinician
   (e.g., centrifugation, decantation, aliquoting, running certain machines etc.). The laboratory's viewpoint warrants a thorough
   specification of action plans (that can be automated). During this specification, more and more nested sub-activities will
   be defined. Still the Act is the same, with varying degrees of detail uncovered in the de-composition relationship. 
</p>
<p>We described the nature of varying detail saying that Acts are "fractal", ever more decomposable, just as the movements of
   a robotic arm can be decomposed in many fine control steps.
</p>
*/
public interface ActRelationship extends BasicActRelationship {

  /**<p>An indicator specifying that the ActRelationship.typeCode should be interpreted as if the roles of the source and target Acts
   were reversed. The inversion indicator is used when the meaning of ActRelationship.typeCode must be reversed.
</p>
  */
  BL getInversionInd();
  /** Sets the property inversionInd.
      @see #getInversionInd
  */
  void setInversionInd(BL inversionInd);

  /**<p>A code that specifies how this ActRelationship contributes to the context of the current Act, and whether it may be propagated
   to descendent Acts whose association allows such propagation (see ActRelationship.contextConductionInd).
</p>
<p><i>Rationale:</i> In the interest of reducing duplication, humans tend to rely on context when interpreting information. For example, when
   reading a report taken from a folder containing a patient's medical record, the reader will infer that the report deals with
   the patient, even if there is no direct reference to the patient on the form. However, other pieces of information such as
   the author of the folder (the hospital that maintains it) may sometimes apply to the contents of the folder (e.g. a report
   generated by a doctor at the hospital) and other times not (e.g. a copy of a report from another institution). Humans are
   quite good at making the necessary inferences about what context should be propagated from an item to something within that
   item. However, incorrect inferences can occur (perhaps the report in the patient's record deals with a relative). Furthermore,
   computers have substantially more difficulty making such inferences, even though they can be essential for decision-support
   systems.
</p>
<p><i>Discussion:</i> This attribute allows the clear specification of whether an association adds to the context associated with a particular
   item (e.g. adding an additional author) or whether it replaces (overrides) part of the context associated with a particular
   item (e.g. identifying a sole author, independent of the containing item). It also indicates whether the association applies
   to only this act (non-propagating), or whether it may apply to derived acts as well (propagating).
</p>
<p>This attribute is closely linked with ActRelationship.contextConductionInd which determines whether associations that have
   been marked as propagating will actually be conducted to a child Act. For example, an author participation might be marked
   as propagating, but still not conducted to a hyperlink to an external document.
</p>
<p>If no value or default is specified for this attribute (i.e. it is null), no inference can be made about context. Systems
   must make their own assumptions on the basis of what data is being represented. (For this reason, HL7 committees are encouraged
   to specify a default or fixed value for this attribute as part of their designs to ensure consistency of interpretation.)
</p>
<p><i>Examples:</i> An observation event has a patient participation marked "additive, propagating" (AP) and has component observation events
   linked through act relationships that are marked propagating. This means that the patient participation behaves as a patient
   participation of those component observation events in addition to the parent observation event.
</p>
<p>A composite order is created containing a pharmacy order as well as requests for several lab tests. The composite order has
   participations for patient and author, and an act relationship to a diagnosis, all marked as "additive, propagating". The
   "component" association between the composite order and the pharmacy order is marked as conductive (contextConductionInd is
   TRUE). The pharmacy order has an author participation marked as "additive, non-propagating" (AN), and a reason relationship
   to a diagnosis, marked as "overriding, propagating" (OP). The order further has a relationship to a dispense event, marked
   as conductive, and an association to a drug protocol marked as non-conductive (contextConductionInd is FALSE). The meaning
   would be as follows:
</p>
<p>The pharmacy order is interpreted as having the patient from the composite order, and having two authors (the one from the
   composite order, and the one on the pharmacy order itself). The diagnosis for the pharmacy order would <b>only</b> be the diagnosis specified on the pharmacy order, not the one specified on the composite order. The dispense event would
   carry the patient from the composite order and the diagnosis from the pharmacy order, but no author. The drug protocol would
   not be associated with a patient, diagnosis or author.
</p>
  */
  CS getContextControlCode();
  /** Sets the property contextControlCode.
      @see #getContextControlCode
  */
  void setContextControlCode(CS contextControlCode);

  /**<p>If true, associations in the parent act are conducted across the ActRelationship to the child act.</p>
<p><i>Discussion:</i> Only associations that have been added to the context of an Act and are marked "propagating" will be conducted. (See contextControlCode
   on ActRelationship and Participation)
</p>
<p>The identification of an Act as a parent or child (and therefore the direction context will be conducted) is determined by
   how the association is traversed when it is serialized. The first act to be encountered is considered to be the parent. Context
   conducts across the ActRelationship to the second (child) Act.
</p>
<p>Refer to ActRelationship.contextControlCode for rationale and examples.</p>
  */
  BL getContextConductionInd();
  /** Sets the property contextConductionInd.
      @see #getContextConductionInd
  */
  void setContextConductionInd(BL contextConductionInd);

  /**<p>An integer specifying the relative sequential or temporal ordering of this relationship among other like-types relationships
   having the same source Act.
</p>
<p><i>Discussion:</i> This attribute is part of the workflow control suite of attributes. An action plan is a composite Act with component Acts.
   In a sequential plan, each component has a sequenceNumber that specifies the ordering of the plan steps. Multiple components
   with the same sequenceNumber make a branch. Branches can be exclusive (case-switch) or can indicate parallel processes indicated
   by the splitCode.
</p>
<p>If value is null, the relative position of the target Act is unspecified. (i.e. it may occur anywhere.)</p>
<p>Use the 'priorityNumber' attribute to indicate relative preference instead of order of occurrence.</p>
  */
  INT getSequenceNumber();
  /** Sets the property sequenceNumber.
      @see #getSequenceNumber
  */
  void setSequenceNumber(INT sequenceNumber);

  /**<p>An integer specifying the relative preference for considering this relationship before other like-typed relationships having
   the same source Act. Relationships with lower priorityNumber values are considered before and above those with higher values.
</p>
<p><i>Examples:</i> For multiple criteria specifies which criteria are considered before others. For components with the same sequence number,
   specifies which ones are considered before others. Among alternatives or options that are being chosen by humans, the priorityNumber
   specifies preference.
</p>
<p><i>Discussion:</i> The ordering may be a total ordering in which all priority numbers are unique or a partial ordering, which assigns the same
   priority to more than one relationship.
</p>
  */
  INT getPriorityNumber();
  /** Sets the property priorityNumber.
      @see #getPriorityNumber
  */
  void setPriorityNumber(INT priorityNumber);

  /**<p>A quantity of time that should elapse between when an Act is ready for execution and the actual beginning of the execution.</p>
<p><i>Discussion:</i> This attribute is part of the workflow control suite of attributes. An action plan is a composite Act with component Acts.
   In a sequential plan, each component has a sequenceNumber that specifies the ordering of the plan steps. Before each step
   is executed and has preconditions these conditions are tested and if the test is positive, the Act has clearance for execution.
   At that time the pauseQuantity timer is started and the Act is executed after the pauseQuantity has elapsed.
</p>
  */
  PQ getPauseQuantity();
  /** Sets the property pauseQuantity.
      @see #getPauseQuantity
  */
  void setPauseQuantity(PQ pauseQuantity);

  /**<p>A code specifying when in the course of an Act a precondition for the Act is evaluated (e.g., before the Act starts for the
   first time, before every repetition, after each repetition but not before the first, or throughout the entire time of the
   Act).
</p>
<p><i>Discussion:</i> This attribute is part of the workflow control suite of attributes. An action plan is a composite Act with component Acts.
   In a sequential plan, each component has a sequenceNumber that specifies the ordering of the plan steps. Before each step
   is executed, those with preconditions have their conditions tested; where the test is positive, the Act has clearance for
   execution. The repeatNumber may indicate that an Act may be repeatedly executed. The checkpointCode is specifies when the
   precondition is checked and is analogous to the various conditional statements and loop constructs in programming languages
   "while-do" vs. "do-while" or "repeat-until" vs. "loop-exit".
</p>
<p>For all checkpointCodes, except "end", preconditions are being checked at the time when the preceding step of the plan has
   terminated and this step would be next in the sequence established by the sequenceNumber attribute. 
</p>
<p>When the checkpointCode for a criterion of a repeatable Act is "end", the criterion is tested only at the end of each repetition
   of that Act. When the condition holds true, the next repetition is ready for execution. 
</p>
<p>When the checkpointCode is "entry" the criterion is checked at the beginning of each repetition (if any) whereas "beginning"
   means the criterion is checked only once before the repetition "loop" starts.
</p>
<p>The checkpointCode "through" is special in that it requires the condition to hold throughout the execution of the Act, even
   throughout a single execution. As soon as the condition turns false, the Act should receive an interrupt event (see Act.interruptibleInd)
   and will eventually terminate.
</p>
<p>The checkpointCode "exit" is only used on a special plan step that represents a loop exit step. This allows an action plan
   to exit due to a condition tested inside the execution of this plan. Such exit criteria are sequenced with the other plan
   components using the ActRelationship.sequenceNumber.
</p>
  */
  CS getCheckpointCode();
  /** Sets the property checkpointCode.
      @see #getCheckpointCode
  */
  void setCheckpointCode(CS checkpointCode);

  /**<p>A code specifying how branches in an action plan are selected among other branches.</p>
<p><i>Discussion:</i> This attribute is part of the workflow control suite of attributes. An action plan is a composite Act with component Acts.
   In a sequential plan, each component has a sequenceNumber that specifies the ordering of the plan steps. Branches exist when
   multiple components have the same sequenceNumber. The splitCode specifies whether a branch is executed exclusively (case-switch)
   or inclusively, i.e., in parallel with other branches. 
</p>
<p>In addition to exclusive and inclusive split the splitCode specifies how the pre-condition (also known as "guard conditions"
   on the branch) are evaluated. A guard condition may be evaluated once when the branching step is entered and if the conditions
   do not hold at that time, the branch is abandoned. Conversely execution of a branch may wait until the guard condition turns
   true.
</p>
<p>In exclusive wait branches, the first branch whose guard conditions turn true will be executed and all other branches abandoned.
   In inclusive wait branches some branches may already be executed while other branches still wait for their guard conditions
   to turn true.
</p>
  */
  CS getSplitCode();
  /** Sets the property splitCode.
      @see #getSplitCode
  */
  void setSplitCode(CS splitCode);

  /**<p>A code specifying how concurrent Acts are resynchronized in a parallel branch construct.</p>
<p><i>Discussion:</i> This attribute is part of the workflow control suite of attributes. An action plan is a composite Act with component Acts.
   In a sequential plan, each component has a sequenceNumber that specifies the ordering of the plan steps. Branches exist when
   multiple components have the same sequenceNumber. Branches are parallel if the splitCode specifies that more than one branch
   can be executed at the same time. The joinCode then specifies if and how the branches are resynchronized.
</p>
<p>The principal re-synchronization actions are (1) the control flow waits for a branch to terminate (wait-branch), (2) the branch
   that is not yet terminated is aborted (kill-branch), (3) the branch is not re-synchronized at all and continues in parallel
   (detached branch).
</p>
<p>A kill-branch is only executed if there is at least one active wait (or exclusive wait) branch. If there is no other wait
   branch active, a kill-branch is not started at all (rather than being aborted shortly after it is started). Since a detached
   branch is unrelated to all other branches, active detached branches do not protect a kill-branch from being aborted.
</p>
  */
  CS getJoinCode();
  /** Sets the property joinCode.
      @see #getJoinCode
  */
  void setJoinCode(CS joinCode);

  /**<p>An indicator that asserts that the meaning of the link is negated.</p>
<p><i>Examples:</i> If the relationship without negation specifies that Act A has Act B as a component, then the negation indicator specifies
   that Act A does <b>not</b> have Act B as a component. If B is a reason for A, then negation means that B is <b>not</b> a reason for A. If B is a pre-condition for A, then negation means that B is <b>not</b> a precondition for A.
</p>
<p><i>Discussion:</i> As the examples show, the use of this attribute is quite limited, notably contrast this with the Act.negationInd that actually
   requires that the described Act not exist, not be done, etc. whereas the ActRelationship.negationInd merely negates this relationship
   between source and target act, but does not change the meaning of each Act. This is mostly used for clarifying statements.
   
</p>
<p>Note also the difference between negation and the contrary. A contraindication is the contrary of an indication (reason) but
   not the negation of the reason. The fact that lower back pain is not a reason to prescribe antibiotics doesn't mean that antibiotics
   are contraindicated with lower back pain.
</p>
  */
  BL getNegationInd();
  /** Sets the property negationInd.
      @see #getNegationInd
  */
  void setNegationInd(BL negationInd);

  /**<p>A code specifying the logical conjunction of the criteria among all the condition-links of Acts (e.g., and, or, exclusive-or).</p>
<p><i>Constraints:</i> All AND criteria must be true. If OR and AND criteria occur together, one criterion out of the OR-group must be true and
   all AND criteria must be true also. If XOR criteria occur together with OR and AND criteria, exactly one of the XOR criteria
   must be true, and at least one of the OR criteria and all AND criteria must be true. In other words, the sets of AND, OR,
   and XOR criteria are in turn combined by a logical AND operator (all AND criteria and at least one OR criterion and exactly
   one XOR criterion). To overcome this ordering, Act criteria can be nested in any way necessary.
</p>
  */
  CS getConjunctionCode();
  /** Sets the property conjunctionCode.
      @see #getConjunctionCode
  */
  void setConjunctionCode(CS conjunctionCode);

  /**<p>A character string name for the input parameter from which the source Act of this ActRelationship derives some of its attributes.
   The local variable name is bound in the scope of the Act.derivationExpr with its value being an Act selected based on the
   input parameter specification.
</p>
  */
  ST getLocalVariableName();
  /** Sets the property localVariableName.
      @see #getLocalVariableName
  */
  void setLocalVariableName(ST localVariableName);

  /**<p>This attribute indicates whether or not the source Act is intended to be interpreted independently of the target Act. The
   indicator cannot prevent an individual or application from separating the Acts, but indicates the author's desire and willingness
   to attest to the content of the source Act if separated from the target Act. Note that the default for this attribute will
   typically be "TRUE". Also note that this attribute is orthogonal and unrelated to the RIM's context/inheritance mechanism.
   If the context of an Act is propagated to nested Acts, it is assumed that those nested Acts are not intended to be interpreted
   without the propagated context.
</p>
  */
  BL getSeperatableInd();
  /** Sets the property seperatableInd.
      @see #getSeperatableInd
  */
  void setSeperatableInd(BL seperatableInd);

  /**<p>Used to indicate that the target of the relationship will be a filtered subset of the total related set of targets</p>
<p>Used when there is a need to limit the number of components to the first, the last, the next, the total, the average or some
   other filtered or calculated subset.
</p>
  */
  CS getSubsetCode();
  /** Sets the property subsetCode.
      @see #getSubsetCode
  */
  void setSubsetCode(CS subsetCode);

  /**<p>A code indicating whether the specific relationship between the source and target Acts has been asserted to be uncertain in
   any way. 
</p>
<p><i>Examples:</i> A particular exposure event is suspected but not known to have caused a particular symptom.
</p>
<p><i>Constraints:</i> Uncertainty asserted using this attribute applies only to the relationship between two acts. The certainty of the acts themselves
   should be conveyed via Act.uncertaintyCode.
</p>
<p><i>Discussion:</i> Note that very vague uncertainty may be thought related to ActRelationship.negationInd, however, the two concepts are really
   independent. One may be very uncertain about whether a relationship exists (e.g. not sure whether a drug contains an ingredient)
   an event, but that does not mean that one is certain about the negation of the relationship (e.g. A drug definitively does
   not contain an ingredient).
</p>
  */
  CE getUncertaintyCode();
  /** Sets the property uncertaintyCode.
      @see #getUncertaintyCode
  */
  void setUncertaintyCode(CE uncertaintyCode);

  /**
  */
  org.hl7.rim.Act getSource();
  /** Sets the property source.
      @see #getSource
  */
  void setSource(org.hl7.rim.Act source);

  /**
  */
  org.hl7.rim.Act getTarget();
  /** Sets the property target.
      @see #getTarget
  */
  void setTarget(org.hl7.rim.Act target);
}

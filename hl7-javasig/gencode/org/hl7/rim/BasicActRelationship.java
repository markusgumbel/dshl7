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
public interface BasicActRelationship extends org.hl7.rim.InfrastructureRoot {

  /**<p>A code specifying the meaning and purpose of every ActRelationship instance. Each of its values implies specific constraints
   to what kinds of Act objects can be related and in which way.
</p>
<p><i>Discussion:</i> The types of act relationships fall under one of 5 categories:
</p>
<p>1.) (De)-composition, with composite (source) and component (target).</p>
<p>2.) Sequel which includes follow-up, fulfillment, instantiation, replacement, transformation, etc. that all have in common
   that source and target are Acts of essentially the same kind but with variances in mood and other attributes, and where the
   target exists before the source and the source refers to the target that it links back to.
</p>
<p>3.) Pre-condition, trigger, reason, contraindication, with the conditioned Act at the source and the condition or reason at
   the target.
</p>
<p>4.) Post-condition, outcome, goal and risk, with the Act at the source having the outcome or goal at the target.</p>
<p>5.) A host of functional relationships including support, cause, derivation, etc. generalized under the notion of "pertinence".</p>
  */
  CS getTypeCode();
  /** Sets the property typeCode.
      @see #getTypeCode
  */
  void setTypeCode(CS typeCode);
}

/* THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

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
package org.hl7.types.enums;

import org.hl7.types.ValueFactory;
import org.hl7.types.ANY;
import org.hl7.types.CS;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.SET;
import org.hl7.types.LIST;
import org.hl7.types.CR;
import org.hl7.types.NullFlavor;
import org.hl7.types.impl.NullFlavorImpl;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.LISTnull;
import java.util.EnumSet;
import java.util.Map;
import java.util.HashMap;

/** A code specifying the meaning and purpose of every ActRelationship instance. Each of its values implies specific constraints
to what kinds of Act objects can be related and in which way.<p>
   <emph>Discussion:</emph> The types of act relationships fall under one of 5 categories:
</p>
<p>1.) (De)-composition, with composite (source) and component (target)</p>
<p>2.) Sequel which includes follow-up, fulfillment, instantiation, replacement, transformation, etc. that all have in common
   that source and target are Acts of essentially the same kind but with variances in mood and other attributes, and where the
   target exists before the source and the source refers to the target that it links back to.
</p>
<p>3.) Pre-condition, trigger, reason, contraindication, with the conditioned Act at the source and the condition or reason at
   the target.
</p>
<p>4.) Post-condition, outcome, goal and risk, with the Act at the source having the outcome or goal at the target.</p>
<p>5.) A host of functional relationships including support, cause, derivation, etc. generalized under the notion of "pertinence".</p>
<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum ActRelationshipType implements CS {

  // ACTUAL DATA

  root(null, "ActRelationshipType", null, null),
  /** Specifies under what circumstances (target Act) the source-Act may, must, must not or has occurred */
  ActRelationshipConditional(null, "ActRelationshipConditional", null, null),
  /** The reason or rationale for a service. A reason link is weaker than a trigger, it only suggests that some service may be or
might have been a reason for some action, but not that this reason requires/required the action to be taken. Also, as opposed
to the trigger, there is no strong timely relation between the reason and the action.<p>
   <emph>Discussion: </emph>In prior releases, the code "SUGG" (suggests) was expressed as "an inversion of the reason link." That code has been retired
   in favor of the inversion indicator that is an attribute of ActRelationship.
</p> */
  ActRelationshipReason(ActRelationshipConditional, "ActRelationshipReason", "RSON", "has reason"),
  /**  */
  AdjunctCurativeIndication(ActRelationshipReason, "Adjunct curative indication", "CURE.ADJ", "adjunct curative indication"),
  /**  */
  AdjunctMitigation(ActRelationshipReason, "Adjunct mitigation", "MTGT.ADJ", "adjunct mitigation"),
  /**  */
  CurativeIndication(ActRelationshipReason, "Curative indication", "CURE", "curative indication"),
  /**  */
  Diagnosis(ActRelationshipReason, "Diagnosis", "DIAG", "diagnosis"),
  /** The source act removes or lessens the occurrence or effect of the target act. */
  Mitigates(ActRelationshipReason, "Mitigates", "MITGT", "mitigates"),
  /** Used in the diagnosis of the indicated disease. */
  SymptomaticRelief(ActRelationshipReason, "Symptomatic relief", "SYMP", "symptomatic relief"),
  /** A contraindication is just a negation of a reason, i.e. it gives a condition under which the action is not to be done. Both,
source and target can be any kind of service; target service is in criterion mood. How the strength of a contraindication
is expressed (e.g., relative, absolute) is left as an open issue. The priorityNumber attribute could be used. */
  HasContraIndication(ActRelationshipConditional, "Has contra-indication", "CIND", "has contra-indication"),
  /** A requirement to be true before a service is performed. The target can be any service in criterion mood. For multiple pre-conditions
a conjunction attribute (AND, OR, XOR) is applicable. */
  HasPreCondition(ActRelationshipConditional, "Has pre-condition", "PRCN", "has pre-condition"),
  /** A pre-condition that if true should result in the source Act being executed. The target is in typically in criterion mood.
When reported after the fact (i.e. the criterion has been met) it may be in Event mood. A delay between the trigger and the
triggered action can be specified.<p>
   <emph>Discussion: </emph>This includes the concept of a required act for a service or financial instrument such as an insurance plan or policy. In
   such cases, the trigger is the occurrence of a specific condition such as coverage limits being exceeded.
</p> */
  HasTrigger(ActRelationshipConditional, "Has trigger", "TRIG", "has trigger"),
  /** A collection of sub-services as steps or subtasks performed for the source service. Services may be performed sequentially
or concurrently. */
  ActRelationshipHasComponent(null, "ActRelationshipHasComponent", "COMP", "has component"),
  /** The relationship that links to a Transportation Act (target) from another Act (source) indicating that the subject of the
source Act entered into the source Act by means of the target Transportation act. */
  Arrival(ActRelationshipHasComponent, "Arrival", "ARR", "arrival"),
  /** The relationship that links to a Transportation Act (target) from another Act (source) indicating that the subject of the
source Act departed from the source Act by means of the target Transportation act. */
  Departure(ActRelationshipHasComponent, "Departure", "DEP", "departure"),
  /** A relationship from an Act to a Control Variable. For example, if a Device makes an Observation, this relates the Observation
to its Control Variables documenting the device's settings that influenced the observation. */
  HasControlVariable(ActRelationshipHasComponent, "Has control variable", "CTRLV", "has control variable"),
  /** An observation that should follow or does actually follow as a result or consequence of a condition or action (sometimes called
"post-condition".) Target must be an observation as a goal, risk or any criterion. For complex outcomes a conjunction attribute */
  ActRelationshipOutcome(null, "ActRelationshipOutcome", "OUTC", "has outcome"),
  /** The target act is a desired outcome of the source act. Source is any act (typically an intervention). Target must be an observation
in criterion mood. */
  ActRelationshipObjective(ActRelationshipOutcome, "ActRelationshipObjective", null, null),
  /** A desired state that a service action aims to maintain. E.g., keep systolic blood pressure between 90 and 110 mm Hg. Source
is an intervention service. Target must be an observation in criterion mood. */
  HasContinuingObjective(ActRelationshipObjective, "Has continuing objective", "OBJC", "has continuing objective"),
  /** A desired outcome that a service action aims to meet finally. Source is any service (typically an intervention). Target must
be an observation in criterion mood. */
  HasFinalObjective(ActRelationshipObjective, "Has final objective", "OBJF", "has final objective"),
  /** A goal that one defines given a patient's health condition. Subsequently planned actions aim to meet that goal. Source is
an observation or condition node, target must be an observation in goal mood. */
  HasGoal(ActRelationshipOutcome, "Has goal", "GOAL", "has goal"),
  /** A noteworthy undesired outcome of a patient's condition that is either likely enough to become an issue or is less likely
but dangerous enough to be addressed. */
  HasRisk(ActRelationshipOutcome, "Has risk", "RISK", "has risk"),
  /** This is a very unspecific relationship from one item of clinical information to another. It does not judge about the role
the pertinent information plays. */
  ActRelationshipPertains(null, "ActRelationshipPertains", "PERT", "has pertinent information"),
  /** Codes that describe the relationship between an Act and a financial instrument such as a financial transaction, account or
invoice element. */
  ActRelationshipAccounting(ActRelationshipPertains, "ActRelationshipAccounting", null, null),
  /** Expresses values for describing the relationship relationship between an InvoiceElement or InvoiceElementGroup and a billable
act. */
  ActRelationshipCostTracking(ActRelationshipAccounting, "ActRelationshipCostTracking", null, null),
  /** A relationship that provides an ability to associate a financial transaction (target) as a charge to a clinical act (source).
A clinical act may have a charge associated with the execution or delivery of the service.<p>The financial transaction will define the charge (bill) for delivery or performance of the service.</p>
<p>Charges and costs are distinct terms. A charge defines what is charged or billed to another organization or entity within
   an organization. The cost defines what it costs an organization to perform or deliver a service or product.
</p> */
  HasCharge(ActRelationshipCostTracking, "Has charge", "CHRG", "has charge"),
  /** A relationship that provides an ability to associate a financial transaction (target) as a cost to a clinical act (source).
A clinical act may have an inherit cost associated with the execution or delivery of the service.<p>The financial transaction will define the cost of delivery or performance of the service.</p>
<p>Charges and costs are distinct terms. A charge defines what is charged or billed to another organization or entity within
   an organization. The cost defines what it costs an organization to perform or deliver a service or product.
</p> */
  HasCost(ActRelationshipCostTracking, "Has cost", "COST", "has cost"),
  /** Expresses values for describing the relationship between a FinancialTransaction and an Account. */
  ActRelationshipPosting(ActRelationshipAccounting, "ActRelationshipPosting", null, null),
  /** A credit relationship ties a financial transaction (target) to an account (source). A credit, once applied (posted), may have
either a positive or negative effect on the account balance, depending on the type of account. An asset account credit will
decrease the account balance. A non-asset account credit will decrease the account balance. */
  HasCredit(ActRelationshipPosting, "Has credit", "CREDIT", "has credit"),
  /** A debit relationship ties a financial transaction (target) to an account (source). A debit, once applied (posted), may have
either a positive or negative effect on the account balance, depending on the type of account. An asset account debit will
increase the account balance. A non-asset account debit will decrease the account balance. */
  HasDebit(ActRelationshipPosting, "Has debit", "DEBIT", "has debit"),
  /** Used to indicate that an existing service is suggesting evidence for a new observation. The assumption of support is attributed
to the same actor who asserts the observation. Source must be an observation, target may be any service (e.g., to indicate
a status post.) */
  ActRelationshipHasSupport(ActRelationshipPertains, "ActRelationshipHasSupport", "SPRT", "has support"),
  /** A specialization of "has support" (SPRT), used to relate a secondary observation to a Region of Interest on a multidimensional
observation, if the ROI specifies the true boundaries of the secondary observation as opposed to only marking the approximate
area. For example, if the start and end of an ST elevation episode is visible in an EKG, this relation would indicate the
ROI bounds the "ST elevation" observation -- the ROI defines the true beginning and ending of the episode. Conversely, if
a ROI simply contains ST elevation, but it does not define the bounds (start and end) of the episode, the more general "has
support" relation is used. Likewise, if a ROI on an image defines the true bounds of a "1st degree burn", the relation "has
bounded support" is used; but if the ROI only points to the approximate area of the burn, the general "has support" relation
is used. */
  HasBoundedSupport(ActRelationshipHasSupport, "Has bounded support", "SPRTBND", "has bounded support"),
  /**  */
  ActRelationshipTemporallyPertains(ActRelationshipPertains, "ActRelationshipTemporallyPertains", null, null),
  /** The source Act starts after the start of the target Act (i.e. if we say "ActOne SAS ActTwo", it means that ActOne starts after
the start of ActTwo, therefore ActOne is the source and ActTwo is the target). */
  StartsAfterStartOf(ActRelationshipTemporallyPertains, "Starts after start of", "SAS", "starts after start of"),
  /** Used to assign a "name" to a condition thread. Source is a condition node, target can be any service. */
  AssignsName(ActRelationshipPertains, "Assigns name", "NAME", "assigns name"),
  /** A relationship in which the target act authorizes or certifies the source act. */
  AuthorizedBy(ActRelationshipPertains, "Authorized by", "AUTH", "authorized by"),
  /** A relationship in which the source act is covered by or is under the authority of a target act. A financial instrument such
as an Invoice Element is covered by one or more specific instances of an Insurance Policy. */
  CoveredBy(ActRelationshipPertains, "Covered by", "COVBY", "covered by"),
  /** This is the inversion of support. Used to indicate that a given observation is explained by another observation or condition. */
  HasExplanation(ActRelationshipPertains, "Has explanation", "EXPL", "has explanation"),
  /** A relationship in which the target act is a predecessor instance to the source act. Generally each of these instances is similar,
but no identical. In healthcare coverage it is used to link a claim item to a previous claim item that might have claimed
for the same set of services. */
  HasPreviousInstance(ActRelationshipPertains, "Has previous instance", "PREV", "has previous instance"),
  /** Reference ranges are essentially descriptors of a class of result values assumed to be "normal", "abnormal", or "critical."
Those can vary by sex, age, or any other criterion. Source and target are observations, the target is in criterion mood. This
link type can act as a trigger in case of alarms being triggered by critical results. */
  HasReferenceValues(ActRelationshipPertains, "Has reference values", "REFV", "has reference values"),
  /** Relates an Act to its subject Act that the first Act is primarily concerned with.<p>Examples</p>
<p>Constraints</p>
<p>An Act may have multiple subject acts.</p>
<p>Rationale</p>
<p>The ActRelationshipType "has subject" is similar to the ParticipationType "subject", Acts that primarily operate on physical
   subjects use the Participation, those Acts that primarily operate on other Acts (other information) use the ActRelationship.
</p> */
  HasSubject(ActRelationshipPertains, "Has subject", "SUBJ", "has subject"),
  /** Associates a derived Act with its input parameters. E.G., an anion-gap observation can be associated as being derived from
given sodium-, (potassium-,), chloride-, and bicarbonate-observations. The narrative content (Act.text) of a source act is
wholly machine-derived from the collection of target acts. */
  IsDerivedFrom(ActRelationshipPertains, "Is derived from", "DRIV", "is derived from"),
  /** An assertion that a new observation was assumed to be the cause for another existing observation. The assumption is attributed
to the same actor who asserts the observation. This is stronger and more specific than the support link. For example, a growth
of Staphylococcus aureus may be considered the cause of an abscess. The source (cause) is typically an observation, but may
be any service, while the target must be an observation. */
  IsEtiologyFor(ActRelationshipPertains, "Is etiology for", "CAUS", "is etiology for"),
  /** An assertion that a new observation may be the manifestation of another existing observation or action. This assumption is
attributed to the same actor who asserts the manifestation. This is stronger and more specific than an inverted support link.
For example, an agitated appearance can be asserted to be the manifestation (effect) of a known hyperthyroxia. This expresses
that one might not have realized a symptom if it would not be a common manifestation of a known condition. The target (cause)
may be any service, while the source (manifestation) must be an observation. */
  IsManifestationOf(ActRelationshipPertains, "Is manifestation of", "MFST", "is manifestation of"),
  /** Items located */
  ItemsLocated(ActRelationshipPertains, "Items located", "ITEMSLOC", "items located"),
  /** A relationship that limits or restricts the source act by the elements of the target act. For example, an authorization may
be limited by a financial amount (up to $500). Target Act must be in EVN.CRIT mood. */
  LimitedBy(ActRelationshipPertains, "Limited by", "LIMIT", "limited by"),
  /** Indicates that the target Act provides evidence in support of the action represented by the source Act. The target is not
a 'reason' for the source act, but rather gives supporting information on why the source act is an appropriate course of action.
Possible targets might be clinical trial results, journal articles, similar successful therapies, etc.<p>
   <emph>Rationale:</emph> Provides a mechanism for conveying clinical justification for non-approved or otherwise non-traditional therapies.
</p> */
  ProvidesEvidenceFor(ActRelationshipPertains, "Provides evidence for", "EVID", "provides evidence for"),
  /** A relationship in which the target act is referred to by the source act. This permits a simple reference relationship that
distinguishes between the referent and the referee. */
  RefersTo(ActRelationshipPertains, "Refers to", "REFR", "refers to"),
  /** An act that contains summary values for a list or set of subordinate acts. For example, a summary of transactions for a particular
accounting period. */
  SummarizedBy(ActRelationshipPertains, "Summarized by", "SUMM", "summarized by"),
  /** An act relationship indicating that the source act follows the target act. The source act should in principle represent the
same kind of act as the target. Source and target need not have the same mood code (mood will often differ). The target of
a sequel is called antecedent. Examples for sequel relationships are: revision, transformation, derivation from a prototype
(as a specialization is a derivation of a generalization), followup, realization, instantiation. */
  ActRelationshipSequel(null, "ActRelationshipSequel", "SEQL", "is sequel"),
  /** The source is an excerpt from the target. */
  ActRelationshipExcerpt(ActRelationshipSequel, "ActRelationshipExcerpt", "XCRPT", "Excerpts"),
  /** The source is a direct quote from the target. */
  ExcerptVerbatim(ActRelationshipExcerpt, "Excerpt verbatim", "VRXCRPT", "Excerpt verbatim"),
  /** The source act fulfills (in whole or in part) the target act. Source act must be in a mood equal or more actual than the target
act. */
  ActRelationshipFulfills(ActRelationshipSequel, "ActRelationshipFulfills", "FLFS", "fulfills"),
  /** The source act is a single occurrence of a repeatable target act. The source and target act can be in any mood on the "completion
track" but the source act must be as far as or further along the track than the target act (i.e., the occurrence of an intent
can be an event but not vice versa). */
  Occurrence(ActRelationshipFulfills, "Occurrence", "OCCR", "occurrence"),
  /** Relates either an appointment request or an appointment to the order for the service being scheduled. */
  ReferencesOrder(ActRelationshipFulfills, "References order", "OREF", "references order"),
  /** Associates a specific time (and associated resources) with a scheduling request or other intent. */
  SchedulesRequest(ActRelationshipFulfills, "Schedules request", "SCH", "schedules request"),
  /** A replacement source act replaces an existing target act. The state of the target act being replaced becomes obselete, but
the act is typically still retained in the system for historical reference. The source and target must be of the same type. */
  ActRelationshipReplacement(ActRelationshipSequel, "ActRelationshipReplacement", "RPLC", "replaces"),
  /** <emph role="strong">Definition:</emph> A new act that carries forward the intention of the original act, but does not completely replace it. The status of the predecessor
act must be 'completed'. The original act is the target act and the successor is the source act. */
  ActRelationshipSucceeds(ActRelationshipSequel, "ActRelationshipSucceeds", "SUCC", "succeeds"),
  /** The source act documents the target act. */
  Documents(ActRelationshipSequel, "Documents", "DOC", "documents"),
  /** Expresses an association that links two instances of the same act over time, indicating that the instance are part of the
same episode, e.g. linking two condition nodes for episode of illness; linking two encounters for episode of encounter. */
  EpisodeLink(ActRelationshipSequel, "EpisodeLink", "ELNK", "episodeLink"),
  /** A goal-evaluation links an observation (intent or actual) to a goal to indicate that the observation evaluates the goal. Given
the goal and the observation, a "goal distance" (e.g., goal to observation) can be "calculated" and need not be sent explicitly. */
  Evaluates(ActRelationshipSequel, "Evaluates (goal)", "GEVL", "evaluates (goal)"),
  /** The generalization relationship can be used to express categorical knowledge about services (e.g., amilorid, triamterene,
and spironolactone have the common generalization potassium sparing diuretic). */
  HasGeneralization(ActRelationshipSequel, "Has generalization", "GEN", "has generalization"),
  /** A relationship between a source Act that provides more detailed properties to the target Act.<p>The source act thus is a specialization of the target act, but instead of mentioning all the inherited properties it only
   mentions new property bindings or refinements.
</p>
<p>The typical use case is to specify certain alternative variants of one kind of Act. The priorityNumber attribute is used to
   weigh refinements as preferred over other alternative refinements.
</p>
<p>Example: several routing options for a drug are specified as one SubstanceAdministration for the general treatment with attached
   refinements for the various routing options.
</p> */
  HasOption(ActRelationshipSequel, "Has option", "OPTN", "has option"),
  /** Used to capture the link between a potential service ("master" or plan) and an actual service, where the actual service instantiates
the potential service. The instantiation may override the master's defaults. */
  Instantiates(ActRelationshipSequel, "Instantiates (master)", "INST", "instantiates (master)"),
  /** An addendum (source) to an existing service object (target), containing supplemental information. The addendum is itself an
original service object linked to the supplemented service object. The supplemented service object remains in place and its
content and status are unaltered. */
  IsAppendage(ActRelationshipSequel, "Is appendage", "APND", "is appendage"),
  /** A trigger-match links an actual service (e.g., an observation or procedure that took place) with a service in criterion mood.
For example if the trigger is "observation of pain" and pain is actually observed, and if that pain-observation caused the
trigger to fire, that pain-observation can be linked with the trigger. */
  Matches(ActRelationshipSequel, "Matches (trigger)", "MTCH", "matches (trigger)"),
  /** Definition: Used to link a newer version or 'snapshot' of a business object (source) to an older version or 'snapshot' of
the same business object (target).<p>
   <emph>Usage:</emph>The identifier of the Act should be the same for both source and target. If the identifiers are distinct, RPLC should be used
   instead.
</p>
<p>Name from source to target = "modifiesPrior"</p>
<p>Name from target to source = "modifiesByNew"</p> */
  Modifies(ActRelationshipSequel, "Modifies", "MOD", "modifies"),
  /** A relationship between a source Act that seeks to reverse or undo the action of the prior target Act.<p>Example: A posted financial transaction (e.g., a debit transaction) was applied in error and must be reversed (e.g., by a
   credit transaction) the credit transaction is identified as an undo (or reversal) of the prior target transaction.
</p>
<p>Constraints: the "completion track" mood of the target Act must be equally or more "actual" than the source act. I.e., when
   the target act is EVN the source act can be EVN, or any INT. If the target act is INT, the source act can be INT.
</p> */
  Reverses(ActRelationshipSequel, "Reverses", "REV", "reverses"),
  /** Used when the target Act is a transformation of the source Act. (For instance, used to show that a CDA document is a transformation
of a DICOM SR document.) */
  Transformation(ActRelationshipSequel, "Transformation", "XFRM", "transformation"),
  /** A condition thread relationship specifically links condition nodes together to form a condition thread. The source is the
new condition node and the target links to the most recent node of the existing condition thread. */
  Updates(ActRelationshipSequel, "Updates (condition)", "UPDT", "updates (condition)");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.1002");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("ActRelationshipType");

  private final ActRelationshipType _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final ActRelationshipType _parent2;
  private final ActRelationshipType _parent3; // well, found a 3rd :(
  private EnumSet<ActRelationshipType> _impliedConcepts = null;

  private ActRelationshipType(ActRelationshipType parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private ActRelationshipType(ActRelationshipType parent, ActRelationshipType parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private ActRelationshipType(ActRelationshipType parent, ActRelationshipType parent2, ActRelationshipType parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<ActRelationshipType> getImpliedConcepts() {
    if(_impliedConcepts == null) {
      if(_parent == null) { // then _parent2, 3 is also null
	_impliedConcepts = EnumSet.of(root);
	_impliedConcepts.add(this);
      } else {
	_impliedConcepts  = EnumSet.copyOf(_parent.getImpliedConcepts());
	_impliedConcepts.add(this);
	if(_parent2 != null)
	  _impliedConcepts.addAll(_parent2.getImpliedConcepts());
	if(_parent3 != null)
	  _impliedConcepts.addAll(_parent3.getImpliedConcepts());
      }
    }
    return _impliedConcepts;
  }

  public final BL equal(ANY that) {
    if(this == that)
      return BLimpl.TRUE;
    if (!(that instanceof CD))
      return BLimpl.FALSE;
    else if (that instanceof ActRelationshipType)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof ActRelationshipType))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActRelationshipType thatActRelationshipType = (ActRelationshipType)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatActRelationshipType));
  }

  public ActRelationshipType mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof ActRelationshipType))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActRelationshipType thatActRelationshipType = (ActRelationshipType)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<ActRelationshipType> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatActRelationshipType.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    ActRelationshipType mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(ActRelationshipType candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,ActRelationshipType> _codeMap = null;

  public static ActRelationshipType valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(ActRelationshipType.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(ActRelationshipType concept : EnumSet.allOf(ActRelationshipType.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      ActRelationshipType concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.1002" + ", domain = " + "ActRelationshipType"));
      else
        return concept;
    } else 
      return null;
  }

  // INVARIANT BOILER PLATE CODE

  public String toString() {
    return code().toString();
  }

  private final String _domainName;
  private final ST _code;
  private final ST _displayName;

  public String domainName() { return _domainName; }
  public ST code() { return _code; }
  public ST displayName() { return _displayName; }
  public UID codeSystem() { return CODE_SYSTEM; }
  public ST codeSystemName() { return CODE_SYSTEM_NAME; }
  public ST codeSystemVersion() { return STnull.NI; }
  public ST originalText() { return STnull.NI; }
  public SET<CD> translation() { return SETnull.NA; }
  public LIST<CR> qualifier() { return LISTnull.NA; }

  public NullFlavor nullFlavor() { return NullFlavorImpl.NOT_A_NULL_FLAVOR; }
  public boolean isNullJ() { return false; }
  public boolean nonNullJ() { return true; }
  public boolean notApplicableJ() { return false; }
  public boolean unknownJ() { return false; }
  public boolean otherJ() { return false; }
  public BL isNull() { return BLimpl.FALSE; }
  public BL nonNull() { return BLimpl.TRUE; }
  public BL notApplicable() { return BLimpl.FALSE; }
  public BL unknown() { return BLimpl.FALSE; }
  public BL other() { return BLimpl.FALSE; }
}

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

/** A code distinguishing whether an Act is conceived of as a factual statement or in some other manner as a command, possibility,
goal, etc.<p>
   <emph>Constraints:</emph> An Act-instance must have one and only one moodCode value.
</p>
<p>The moodCode of a single Act-instance never changes. Mood is not state.</p>
<p>To describe the progression of a business activity from defined to planned to executed, etc. one must instantiate different
   Act-instances in the different moods and link them using ActRelationship of general type "sequel". (See ActRelationship.type.)
</p>
<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum ActMood implements CS {

  // ACTUAL DATA

  root(null, "ActMood", null, null),
  /** These are moods describing activities as they progress in the business cycle, from defined, through planned and ordered to
completed. */
  CompletionTrack(null, "ActMoodCompletionTrack", null, null),
  /** An intention or plan to perform a service. Historical note: in previous RIM versions, the intent mood was captured as a separate
class hierarchy, called Service_intent_or_order. */
  Intent(CompletionTrack, "ActMoodIntent", "INT", "intent"),
  /** A non-mandated intent to perform an act. Used to record intents that are explicitly not Orders. Professional responsibility
for the 'proposal' may or may not be present. */
  Proposal(Intent, "ActMoodProposal", "PRP", "proposal"),
  /** A non-mandated intent to perform an act where a level of professional responsibility is being accepted by making the proposal. */
  Recommendation(Proposal, "Recommendation", "RMD", "recommendation"),
  /** A planned Act for a specific time and place. */
  Appointment(Intent, "Appointment", "APT", "appointment"),
  /** A request for the booking of an appointment. */
  AppointmentRequest(Intent, "Appointment request", "ARQ", "appointment request"),
  /** An intent to perform a service that has the strength of a commitment, i.e., other parties may rely on the originator of such
promise that said originator will see to it that the promised act will be fulfilled. A promise can be either solicited or
unsolicited. */
  Promise(Intent, "Promise", "PRMS", "promise"),
  /** A request or order for a service is an intent directed from a placer (request author) to a fulfiller (service performer).<p>
   <emph>Rationale:</emph> The concepts of a "request" and an "order" are viewed as different, because there is an implication of a mandate associated
   with order. In practice, however, this distinction has no general functional value in the inter-operation of health care computing.
   "Orders" are commonly refused for a variety of clinical and business reasons, and the notion of a "request" obligates the
   recipient (the fulfiller) to respond to the sender (the author). Indeed, in many regions, including Australia and Europe,
   the common term used is "request."
</p>
<p>Thus, the concept embodies both notions, as there is no useful distinction to be made. If a mandate is to be associated with
   a request, this will be embodied in the "local" business rules applied to the transactions. Should HL7 desire to provide a
   distinction between these in the future, the individual concepts could be added as specializations of this concept.
</p>
<p>The critical distinction here, is the difference between this concept and an "intent", of which it is a specialization. An
   intent involves decisions by a single party, the author. A request, however, involves decisions by two parties, the author
   and the fulfiller, with an obligation on the part of the fulfiller to respond to the request indicating that the fulfiller
   will indeed fulfill the request.
</p> */
  Request(Intent, "Request", "RQO", "request"),
  /** A definition of a service (master).<p>Historical note: in previous RIM versions, the definition mood was captured as a separate class hierarchy, called Master_service.</p> */
  Definition(CompletionTrack, "Definition", "DEF", "definition"),
  /** A service that actually happens, may be an ongoing service or a documentation of a past service.<p>Historical note: in previous RIM versions, the event mood was captured as a separate class hierarchy, called Patient_service_event,
   and later Service_event.
</p> */
  Event(CompletionTrack, "Event (occurrence)", "EVN", "event (occurrence)"),
  /** Any of the above service moods (e.g., event, intent, or goal) can be turned into a predicate used as a criterion to express
conditionals (or queries.) However, currently we allow only criteria on service events. */
  Predicate(null, "ActMoodPredicate", null, null),
  /** <emph role="strong">Definition:</emph>An observation that is considered to be desirable to occur in the future. The essential feature of a goal is that if it occurs
it would be considered as a marker of a positive outcome or of progress towards a positive outcome.
<p>
   <emph role="strong">Examples:</emph>Target weight below 80Kg, Stop smoking, Regain ability to walk, goal is to administer thrombolytics to candidate patients
   presenting with acute myocardial infarction.
</p>
<p>
   <emph role="strong">Discussion:</emph> INT (intent) reflects a plan for the future, which is a declaration to do something. This contrasts with goal which doesn't
   represent an intention to act, merely a hope for an eventual result. A goal is distinct from the intended actions to reach
   that goal. "I will reduce the dose of drug x to 20mg" is an intent. "I hope to be able to get the patient to the point where
   I can reduce the dose of drug x to 20mg" is a goal. EXPEC (expectation) reflects a prediction rather than a hope. RSK (risk)
   reflects a potential negative event rather than a hope.
</p> */
  Goal(Predicate, "Goal", "GOL", "Goal"),
  /** A criterion or condition over service events that must apply for an associated service to be considered. */
  EventCriterion(Predicate, "Event criterion", "EVN.CRT", "event criterion"),
  /** <emph role="strong">Definition:</emph>An act that is considered likely to occur in the future. The essential feature of an act expressed in expectation mood is
that it is likely to occur. An expectation may be desirable, undesirable or neutral in effect.
<p>
   <emph role="strong">Examples:</emph>Prognosis of a condition, Expected date of discharge from hospital, patient will likely need an emergency decompression of
   the intracranial pressure by morning.
</p>
<p>
   <emph role="strong">Discussion:</emph>INT (intent) reflects a plan for the future, which is a declaration to do something. This contrasts with expectation, which
   is a prediction that something will happen in the future. GOL (goal) reflects a hope rather than a prediction. RSK (risk)
   reflects a potential negative event that may or may not be expected to happen.
</p> */
  Expectation(Predicate, "Expectation", "EXPEC", "expectation"),
  /** An option is an alternative set of property-value bindings. Options specify alternative sets of values, typically used in
definitions or orders to describe alternatives. An option can only be used as a group, that is, all assigned values must be
used together.<p>Historical note: in HL7 v2.x option existed in the special case for alternative medication routes (RXR segment).</p> */
  Option(Predicate, "Option", "OPT", "option"),
  /** A kind of service which is authorized to be performed. */
  Permission(Predicate, "Permission", "PERM", "permission"),
  /** A request for authorization to perform a kind of service.<p>This is distinct from RQO which is a request for an actual act. PERMRQ is merely a request for permission to perform an act.
   <emph>Discussion:</emph>
</p> */
  PermissionRequest(Predicate, "Permission request", "PERMRQ", "permission request"),
  /** <emph role="strong">Definition:</emph>An act that may occur in the future and which is regarded as undesirable. The essential feature of a risk is that if it occurs
this would be regarded as a marker of a negative outcome or of deterioration towards a negative outcome. Recording a risk
indicates that it is seen as more likely to occur in the subject than in a general member of the population but does not mean
it is expected to occur.
<p>
   <emph role="strong">Examples:</emph>Increased risk of DVT, at risk for sub-acute bacterial endocarditis.
</p>
<p>
   <emph role="strong">Discussion:</emph>Note: An observation in RSK mood expresses the undesirable act, and not the underlying risk factor. A risk factor that is
   present (e.g. obesity, smoking, etc) should be expressed in event mood. INT (intent) reflects a plan for the future, which
   is a declaration to do something. This contrasts with RSK (risk), which is the potential that something negative will occur
   that may or may not ever happen. GOL (goal) reflects a hope to achieve something. EXPEC (expectation) is the prediction of
   a positive or negative event. This contrasts with RSK (risk), which is the potential that something negative will occur that
   may or may not ever happen, and may not be expected to happen.
</p> */
  Risk(Predicate, "Risk", "RSK", "risk");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.1001");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("ActMood");

  private final ActMood _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final ActMood _parent2;
  private final ActMood _parent3; // well, found a 3rd :(
  private EnumSet<ActMood> _impliedConcepts = null;

  private ActMood(ActMood parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private ActMood(ActMood parent, ActMood parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private ActMood(ActMood parent, ActMood parent2, ActMood parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<ActMood> getImpliedConcepts() {
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
    else if (that instanceof ActMood)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof ActMood))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActMood thatActMood = (ActMood)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatActMood));
  }

  public ActMood mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof ActMood))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActMood thatActMood = (ActMood)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<ActMood> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatActMood.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    ActMood mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(ActMood candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,ActMood> _codeMap = null;

  public static ActMood valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(ActMood.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(ActMood concept : EnumSet.allOf(ActMood.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      ActMood concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.1001" + ", domain = " + "ActMood"));
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

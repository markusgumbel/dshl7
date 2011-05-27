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

/** A code or set of codes (e.g., for routine, emergency,) specifying the urgency under which the Act happened, can happen, is
happening, is intended to happen, or is requested/demanded to happen. <p>
   <emph>Discussion:</emph> This attribute is used in orders to indicate the ordered priority, and in event documentation it indicates the actual priority
   used to perform the act. In definition mood it indicates the available priorities.
</p> */
public enum ActPriority implements CS {

  // ACTUAL DATA

  root(null, "ActPriority", null, null),
  /** As soon as possible, next highest priority after stat. */
  ASAP(null, "ASAP", "A", "ASAP"),
  /** Filler should contact the placer (or target) to schedule the service. (Was "C" in HL7 version 2.3's TQ-priority component.) */
  Callback(null, "ActPriorityCallback", "CS", "callback for scheduling"),
  /** Filler should contact the placer to schedule the service. (Was "C" in HL7 version 2.3's TQ-priority component.) */
  CallbackPlacerForScheduling(Callback, "Callback placer for scheduling", "CSP", "callback placer for scheduling"),
  /** Filler should contact the service recipient (target) to schedule the service. (Was "C" in HL7 version 2.3's TQ-priority component.) */
  ContactRecipientForScheduling(Callback, "Contact recipient for scheduling", "CSR", "contact recipient for scheduling"),
  /** An "as needed" order should be accompanied by a description of what constitutes a need. This description is represented by
an observation service predicate as a precondition. */
  AsNeeded(null, "As needed", "PRN", "as needed"),
  /** Filler should contact the placer as soon as results are available, even for preliminary results. (Was "C" in HL7 verion 2.3's
reporting priority.) */
  CallbackResults(null, "Callback results", "CR", "callback results"),
  /** Beneficial to the patient but not essential for survival. */
  Elective(null, "Elective", "EL", "elective"),
  /** An unforeseen combination of circumstances or the resulting state that calls for immediate action. */
  Emergency(null, "Emergency", "EM", "emergency"),
  /** Used to indicate that a service is to be performed prior to a scheduled surgery. When ordering a service and using the pre-op
priority, a check is done to see the amount of time that must be allowed for performance of the service. When the order is
placed, a message can be generated indicating the time needed for the service so that it is not ordered in conflict with a
scheduled operation. */
  Preop(null, "Preop", "P", "preop"),
  /** Routine service, do at usual work hours. */
  Routine(null, "Routine", "R", "routine"),
  /** A report should be prepared and sent as quickly as possible. */
  RushReporting(null, "Rush reporting", "RR", "rush reporting"),
  /** With highest priority (e.g., emergency). */
  Stat(null, "Stat", "S", "stat"),
  /** It is critical to come as close as possible to the requested time (e.g., for a through antimicrobial level). */
  TimingCritical(null, "Timing critical", "T", "timing critical"),
  /** Calls for prompt action. */
  Urgent(null, "Urgent", "UR", "urgent"),
  /** Drug is to be used as directed by the prescriber. */
  UseAsDirected(null, "Use as directed", "UD", "use as directed");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.7");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("ActPriority");

  private final ActPriority _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final ActPriority _parent2;
  private final ActPriority _parent3; // well, found a 3rd :(
  private EnumSet<ActPriority> _impliedConcepts = null;

  private ActPriority(ActPriority parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private ActPriority(ActPriority parent, ActPriority parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private ActPriority(ActPriority parent, ActPriority parent2, ActPriority parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<ActPriority> getImpliedConcepts() {
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
    else if (that instanceof ActPriority)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof ActPriority))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActPriority thatActPriority = (ActPriority)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatActPriority));
  }

  public ActPriority mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof ActPriority))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActPriority thatActPriority = (ActPriority)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<ActPriority> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatActPriority.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    ActPriority mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(ActPriority candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,ActPriority> _codeMap = null;

  public static ActPriority valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(ActPriority.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(ActPriority concept : EnumSet.allOf(ActPriority.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      ActPriority concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.7" + ", domain = " + "ActPriority"));
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

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

/** A code specifying how branches in an action plan are selected among other branches.<p>
   <emph>Discussion:</emph> This attribute is part of the workflow control suite of attributes. An action plan is a composite Act with component Acts.
   In a sequential plan, each component has a sequenceNumber that specifies the ordering of the plan steps. Branches exist when
   multiple components have the same sequenceNumber. The splitCode specifies whether a branch is executed exclusively (case-switch)
   or inclusively, i.e., in parallel with other branches. 
</p>
<p>In addition to exlusive and inclusive split the splitCode specifies how the pre-condition (also known as "guard conditions"
   on the branch) are evaluated. A guard condition may be evaluated once when the branching step is entered and if the conditions
   do not hold at that time, the branch is abandoned. Conversely execution of a branch may wait until the guard condition turns
   true.
</p>
<p>In exclusive wait branches, the first branch whose guard conditions turn true will be executed and all other branches abandoned.
   In inclusive wait branches some branches may already be executed while other branches still wait for their guard conditions
   to turn true.
</p>
<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum ActRelationshipSplit implements CS {

  // ACTUAL DATA

  root(null, "ActRelationshipSplit", null, null),
  /** The pre-condition associated with the branch is evaluated once and if true the branch may be entered. All other exclusive
branches compete with each other and only one will be selected. This implements a COND, IF and CASE conditionals, or "XOR-split."
The order in which the branches are considered may be specified in the priorityNumber attribute. */
  ExclusiveTryOnce(null, "Exclusive try once", "E1", "exclusive try once"),
  /** A branch is selected as soon as the pre-condition associated with the branch evaluates to true. If the condition is false,
the branch may be entered later, when the condition turns true. All other exclusive branches compete with each other and only
one will be selected. Each waiting branch executes in parallel with the default join code wait (see below). The order in which
the branches are considered may be specified in the Service_relationship.priority_nmb. */
  ExclusiveWait(null, "Exclusive wait", "EW", "exclusive wait"),
  /** A branch is executed if its associated preconditions permit. If associated preconditions do not permit, the branch is dropped.
Inclusive branches are not suppressed and do not suppress other branches. */
  InclusiveTryOnce(null, "Inclusive try once", "I1", "inclusive try once"),
  /** A branch is executed as soon as its associated conditions permit. If the condition is false, the branch may be entered later,
when the condition turns true. Inclusive branches are not suppressed and do not suppress other branches. Each waiting branch
executes in parallel with the default join code wait (see below). */
  InclusiveWait(null, "Inclusive wait", "IW", "inclusive wait");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.13");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("ActRelationshipSplit");

  private final ActRelationshipSplit _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final ActRelationshipSplit _parent2;
  private final ActRelationshipSplit _parent3; // well, found a 3rd :(
  private EnumSet<ActRelationshipSplit> _impliedConcepts = null;

  private ActRelationshipSplit(ActRelationshipSplit parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private ActRelationshipSplit(ActRelationshipSplit parent, ActRelationshipSplit parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private ActRelationshipSplit(ActRelationshipSplit parent, ActRelationshipSplit parent2, ActRelationshipSplit parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<ActRelationshipSplit> getImpliedConcepts() {
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
    else if (that instanceof ActRelationshipSplit)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof ActRelationshipSplit))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActRelationshipSplit thatActRelationshipSplit = (ActRelationshipSplit)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatActRelationshipSplit));
  }

  public ActRelationshipSplit mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof ActRelationshipSplit))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActRelationshipSplit thatActRelationshipSplit = (ActRelationshipSplit)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<ActRelationshipSplit> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatActRelationshipSplit.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    ActRelationshipSplit mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(ActRelationshipSplit candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,ActRelationshipSplit> _codeMap = null;

  public static ActRelationshipSplit valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(ActRelationshipSplit.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(ActRelationshipSplit concept : EnumSet.allOf(ActRelationshipSplit.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      ActRelationshipSplit concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.13" + ", domain = " + "ActRelationshipSplit"));
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

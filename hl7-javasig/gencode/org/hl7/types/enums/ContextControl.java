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

/** A code that specifies how an ActRelationship or Participation contributes to the context of an Act, and whether it may be
propagated to descendent Acts whose association allows such propagation (see also attributes Participation.contextControlCode,
ActRelationship.contextControlCode, ActRelationship.contextConductionInd).<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum ContextControl implements CS {

  // ACTUAL DATA

  root(null, "ContextControl", null, null),
  /** The association adds to the existing context associated with the Act. Both this association and any associations propagated
from ancestor Acts are interpreted as being related to this Act. */
  Additive(null, "ContextControlAdditive", null, null),
  /** The association applies only to the current Act and will not propagate to any child Acts that are related via a conducting
ActRelationship (refer to contextConductionInd). */
  NonPropagating(null, "ContextControlNonPropagating", null, null),
  /** The association adds to the existing context associated with the Act, but will not propagate to any descendant Acts reached
by conducting ActRelationships (see contextControlCode). Examples: If an 'Author' Participation were marked as "Additive,
Non-Propagating" it means that the author will be added to the set of author participations that have propagated from ancestor
Acts for the purpose of this Act. However only the previously propagated authors will propagate to any child Acts that allow
context to be propagated. */
  Additive_NonPropagating(Additive, NonPropagating, "Additive, non-propagating", "AN", "additive, non-propagating"),
  /** The association adds to the existing context associated with the Act, but replaces associations propagated from ancestor Acts
whose typeCodes are the same. */
  Overriding(null, "ContextControlOverriding", null, null),
  /** The association is added to the existing context associated with the Act, but overrides an association with the same typeCode.
However, this overriding association will not propagate to any descendant Acts reached by conducting ActRelationships (see
contextControlCode). Examples: If an 'Author' Participation were marked as "Overriding, Non-Propagating" it means that the
author will replace the set of author participations that have propagated from ancestor Acts. Furthermore, no author participations
whatsoever will propagate to any child Acts that allow context to be propagated. */
  Overriding_NonPropagating(NonPropagating, Overriding, "Overriding, non-propagating", "ON", "overriding, non-propagating"),
  /** The association propagates to any child Acts that are related via a conducting ActRelationship (refer to contextConductionInd). */
  Propagating(null, "ContextControlPropagating", null, null),
  /** The association adds to the existing context associated with the Act, and will propagate to any descendant Acts reached by
conducting ActRelationships (see contextControlCode). Examples: If an 'Author' Participation were marked as "Additive, Propagating"
it means that the author will be added to the set of author participations that have propagated from ancestor Acts, and will
itself propagate with the other authors to any child Acts that allow context to be propagated. */
  Additive_Propagating(Additive, Propagating, "Additive, propagating", "AP", "additive, propagating"),
  /** The association is added to the existing context associated with the Act, but overrides an association with the same typeCode.
This overriding association will propagate to any descendant Acts reached by conducting ActRelationships (see contextControlCode).
Examples: If an 'Author' Participation were marked as "Overriding, Propagating" it means that the author will replace the
set of author participations that have propagated from ancestor Acts, and will itself be the only author to propagate to any
child Acts that allow context to be propagated. */
  Overriding_Propagating(Overriding, Propagating, "Overriding, propagating", "OP", "overriding, propagating");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.1057");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("ContextControl");

  private final ContextControl _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final ContextControl _parent2;
  private final ContextControl _parent3; // well, found a 3rd :(
  private EnumSet<ContextControl> _impliedConcepts = null;

  private ContextControl(ContextControl parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private ContextControl(ContextControl parent, ContextControl parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private ContextControl(ContextControl parent, ContextControl parent2, ContextControl parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<ContextControl> getImpliedConcepts() {
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
    else if (that instanceof ContextControl)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof ContextControl))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ContextControl thatContextControl = (ContextControl)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatContextControl));
  }

  public ContextControl mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof ContextControl))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ContextControl thatContextControl = (ContextControl)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<ContextControl> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatContextControl.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    ContextControl mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(ContextControl candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,ContextControl> _codeMap = null;

  public static ContextControl valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(ContextControl.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(ContextControl concept : EnumSet.allOf(ContextControl.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      ContextControl concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.1057" + ", domain = " + "ContextControl"));
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

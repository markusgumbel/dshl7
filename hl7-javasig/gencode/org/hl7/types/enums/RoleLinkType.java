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

/** A code specifying the meaning and purpose of every RoleLink instance. Each of its values implies specific constraints to what
kinds of Role objects can be related and in which way.<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum RoleLinkType implements CS {

  // ACTUAL DATA

  root(null, "RoleLinkType", null, null),
  /** An action taken with respect to a subject Entity by a regulatory or authoritative body with supervisory capacity over that
entity. The action is taken in response to behavior by the subject Entity that body finds to be undesirable.<p>Suspension, license restrictions, monetary fine, letter of reprimand, mandated training, mandated supervision, etc.
   <emph>Examples:</emph>
</p> */
  RelatedLinkType(null, "RelatedLinkType", "REL", "related"),
  /** The source Role has direct authority over the target role in a chain of authority. */
  HasDirectAuthorityOver(RelatedLinkType, "Has direct authority over", "DIRAUTH", "has direct authority over"),
  /** The source Role has indirect authority over the target role in a chain of authority. */
  HasIndirectAuthorityOver(RelatedLinkType, "Has indirect authority over", "INDAUTH", "has indirect authority over"),
  /** The target Role is part of the Source Role. */
  HasPart(RelatedLinkType, "Has part", "PART", "has part"),
  /** This relationship indicates the source Role is available to the target Role as a backup. An entity in a backup role will be
available as a substitute or replacement in the event that the entity assigned the role is unavailable. In medical roles where
it is critical that the function be performed and there is a possibility that the individual assigned may be ill or otherwise
indisposed, another individual is assigned to cover for the individual originally assigned the role. A backup may be required
to be identified, but unless the backup is actually used, he/she would not assume the assigned entity role. */
  IsBackupFor(RelatedLinkType, "Is backup for", "BACKUP", "is backup for"),
  /** This relationship indicates that the source Role replaces (or subsumes) the target Role. Allows for new identifiers and/or
new effective time for a registry entry or a certification, etc. */
  Replaces(RelatedLinkType, "Replaces", "REPL", "replaces");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.107");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("RoleLinkType");

  private final RoleLinkType _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final RoleLinkType _parent2;
  private final RoleLinkType _parent3; // well, found a 3rd :(
  private EnumSet<RoleLinkType> _impliedConcepts = null;

  private RoleLinkType(RoleLinkType parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private RoleLinkType(RoleLinkType parent, RoleLinkType parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private RoleLinkType(RoleLinkType parent, RoleLinkType parent2, RoleLinkType parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<RoleLinkType> getImpliedConcepts() {
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
    else if (that instanceof RoleLinkType)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof RoleLinkType))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final RoleLinkType thatRoleLinkType = (RoleLinkType)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatRoleLinkType));
  }

  public RoleLinkType mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof RoleLinkType))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final RoleLinkType thatRoleLinkType = (RoleLinkType)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<RoleLinkType> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatRoleLinkType.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    RoleLinkType mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(RoleLinkType candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,RoleLinkType> _codeMap = null;

  public static RoleLinkType valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(RoleLinkType.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(RoleLinkType concept : EnumSet.allOf(RoleLinkType.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      RoleLinkType concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.107" + ", domain = " + "RoleLinkType"));
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

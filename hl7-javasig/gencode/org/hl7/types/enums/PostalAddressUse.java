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

/**  */
public enum PostalAddressUse implements CS {

  // ACTUAL DATA

  root(null, "PostalAddressUse", null, null),
  /**  */
  AddressUse(null, "AddressUse", null, null),
  /** A communication address at a home, attempted contacts for business purposes might intrude privacy and chances are one will
contact family or other household members instead of the person one wishes to call. Typically used with urgent cases, or if
no other contacts are available. */
  HomeAddressUse(AddressUse, "HomeAddressUse", "H", "home address"),
  /** The primary home, to reach a person after business hours. */
  PrimaryHome(HomeAddressUse, "Primary home", "HP", "primary home"),
  /** A vacation home, to reach a person while on vacation. */
  VacationHome(HomeAddressUse, "Vacation home", "HV", "vacation home"),
  /** An office address. First choice for business related contacts during business hours. */
  WorkPlaceAddressUse(AddressUse, "WorkPlaceAddressUse", "WP", "work place"),
  /** Indicates a work place address or telecommunication address that reaches the individual or organization directly without intermediaries.
For phones, often referred to as a 'private line'. */
  Direct(WorkPlaceAddressUse, "Direct", "DIR", "Direct"),
  /** Indicates a work place address or telecommunication address that is a 'standard' address which may reach a reception service,
mail-room, or other intermediary prior to the target entity. */
  Public(WorkPlaceAddressUse, "Public", "PUB", "Public"),
  /** A flag indicating that the address is bad, in fact, useless. */
  BadAddress(AddressUse, "Bad address", "BAD", "bad address"),
  /** A temporary address, may be good for visit or mailing. Note that an address history can provide more detailed information. */
  TemporaryAddress(AddressUse, "Temporary address", "TMP", "temporary address"),
  /** Identifies the different representations of a name. The representation may affect how the name is used. (E.g. use of Ideographic
for formal communications.) */
  NameRepresentationUse(null, "NameRepresentationUse", null, null),
  /** Alphabetic transcription of name (Japanese: romaji) */
  Alphabetic(NameRepresentationUse, "Alphabetic", "ABC", "Alphabetic"),
  /** Ideographic representation of name (e.g., Japanese kanji, Chinese characters) */
  Ideographic(NameRepresentationUse, "Ideographic", "IDE", "Ideographic"),
  /** Syllabic transcription of name (e.g., Japanese kana, Korean hangul) */
  Syllabic(NameRepresentationUse, "Syllabic", "SYL", "Syllabic"),
  /** Used primarily to visit an address. */
  PhysicalVisitAddress(null, "Physical visit address", "PHYS", "physical visit address"),
  /** Used to send mail. */
  PostalAddress(null, "Postal address", "PST", "postal address");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.1012");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("PostalAddressUse");

  private final PostalAddressUse _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final PostalAddressUse _parent2;
  private final PostalAddressUse _parent3; // well, found a 3rd :(
  private EnumSet<PostalAddressUse> _impliedConcepts = null;

  private PostalAddressUse(PostalAddressUse parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private PostalAddressUse(PostalAddressUse parent, PostalAddressUse parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private PostalAddressUse(PostalAddressUse parent, PostalAddressUse parent2, PostalAddressUse parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<PostalAddressUse> getImpliedConcepts() {
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
    else if (that instanceof PostalAddressUse)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof PostalAddressUse))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final PostalAddressUse thatPostalAddressUse = (PostalAddressUse)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatPostalAddressUse));
  }

  public PostalAddressUse mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof PostalAddressUse))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final PostalAddressUse thatPostalAddressUse = (PostalAddressUse)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<PostalAddressUse> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatPostalAddressUse.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    PostalAddressUse mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(PostalAddressUse candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,PostalAddressUse> _codeMap = null;

  public static PostalAddressUse valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(PostalAddressUse.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(PostalAddressUse concept : EnumSet.allOf(PostalAddressUse.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      PostalAddressUse concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.1012" + ", domain = " + "PostalAddressUse"));
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

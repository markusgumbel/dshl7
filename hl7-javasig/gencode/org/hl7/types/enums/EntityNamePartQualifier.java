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
public enum EntityNamePartQualifier implements CS {

  // ACTUAL DATA

  root(null, "EntityNamePartQualifier", null, null),
  /**  */
  OrganizationNamePartQualifier(null, "OrganizationNamePartQualifier", null, null),
  /** For organizations a suffix indicating the legal status, e.g., "Inc.", "Co.", "AG", "GmbH", "B.V." "S.A.", "Ltd." etc. */
  LegalStatus(OrganizationNamePartQualifier, "Legal status", "LS", "Legal status"),
  /**  */
  PersonNamePartQualifier(null, "PersonNamePartQualifier", null, null),
  /**  */
  PersonNamePartAffixTypes(PersonNamePartQualifier, "PersonNamePartAffixTypes", null, null),
  /** Indicates that a prefix like "Dr." or a suffix like "M.D." or "Ph.D." is an academic title. */
  Academic(PersonNamePartAffixTypes, "Academic", "AC", "academic"),
  /** In Europe and Asia, there are still people with nobility titles (aristocrats). German "von" is generally a nobility title,
not a mere voorvoegsel. Others are "Earl of" or "His Majesty King of..." etc. Rarely used nowadays, but some systems do keep
track of this. */
  Nobility(PersonNamePartAffixTypes, "Nobility", "NB", "nobility"),
  /** Primarily in the British Imperial culture people tend to have an abbreviation of their professional organization as part of
their credential suffices. */
  Professional(PersonNamePartAffixTypes, "Professional", "PR", "professional"),
  /** A Dutch "voorvoegsel" is something like "van" or "de" that might have indicated nobility in the past but no longer so. Similar
prefixes exist in other languages such as Spanish, French or Portugese. */
  Voorvoegsel(PersonNamePartAffixTypes, "Voorvoegsel", "VV", "voorvoegsel"),
  /**  */
  PersonNamePartChangeQualifier(PersonNamePartQualifier, "PersonNamePartChangeQualifier", null, null),
  /** The name the person was given at the time of adoption. */
  Adopted(PersonNamePartChangeQualifier, "Adopted", "AD", "adopted"),
  /** A name that a person had shortly after being born. Usually for family names but may be used to mark given names at birth that
may have changed later. */
  Birth(PersonNamePartChangeQualifier, "Birth", "BR", "birth"),
  /** The name assumed from the partner in a marital relationship (hence the "M"). Usually the spouse's family name. Note that no
inference about gender can be made from the existence of spouse names. */
  Spouse(PersonNamePartChangeQualifier, "Spouse", "SP", "spouse"),
  /**  */
  PersonNamePartMiscQualifier(PersonNamePartQualifier, "PersonNamePartMiscQualifier", null, null),
  /** A callme name is (usually a given name) that is preferred when a person is directly addressed. */
  Callme(PersonNamePartMiscQualifier, "Callme", "CL", "callme"),
  /** Indicates that a name part is just an initial. Initials do not imply a trailing period since this would not work with non-Latin
scripts. Initials may consist of more than one letter, e.g., "Ph." could stand for "Philippe" or "Th." for "Thomas". */
  Initial(PersonNamePartQualifier, "Initial", "IN", "initial"),
  /** Indicates that a prefix or a suffix is a title that applies to the whole name, not just the adjacent name part. */
  Title(PersonNamePartQualifier, "Title", "TITLE", "title");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.43");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("EntityNamePartQualifier");

  private final EntityNamePartQualifier _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final EntityNamePartQualifier _parent2;
  private final EntityNamePartQualifier _parent3; // well, found a 3rd :(
  private EnumSet<EntityNamePartQualifier> _impliedConcepts = null;

  private EntityNamePartQualifier(EntityNamePartQualifier parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private EntityNamePartQualifier(EntityNamePartQualifier parent, EntityNamePartQualifier parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private EntityNamePartQualifier(EntityNamePartQualifier parent, EntityNamePartQualifier parent2, EntityNamePartQualifier parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<EntityNamePartQualifier> getImpliedConcepts() {
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
    else if (that instanceof EntityNamePartQualifier)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof EntityNamePartQualifier))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final EntityNamePartQualifier thatEntityNamePartQualifier = (EntityNamePartQualifier)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatEntityNamePartQualifier));
  }

  public EntityNamePartQualifier mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof EntityNamePartQualifier))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final EntityNamePartQualifier thatEntityNamePartQualifier = (EntityNamePartQualifier)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<EntityNamePartQualifier> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatEntityNamePartQualifier.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    EntityNamePartQualifier mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(EntityNamePartQualifier candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,EntityNamePartQualifier> _codeMap = null;

  public static EntityNamePartQualifier valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(EntityNamePartQualifier.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(EntityNamePartQualifier concept : EnumSet.allOf(EntityNamePartQualifier.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      EntityNamePartQualifier concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.43" + ", domain = " + "EntityNamePartQualifier"));
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

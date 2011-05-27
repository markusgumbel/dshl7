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
public enum EntityNameUse implements CS {

  // ACTUAL DATA

  root(null, "EntityNameUse", null, null),
  /** Known as/conventional/the one you use */
  NameLegalUse(null, "NameLegalUse", "L", "Legal"),
  /** <emph role="strong">Definition:</emph>The formal name as registered in an official (government) registry, but which name might not be commonly used. Particularly
used in countries with a law system based on Napoleonic law. */
  OfficialRegistry(NameLegalUse, "Official registry", "OR", "official registry"),
  /**  */
  OrganizationNameUse(null, "OrganizationNameUse", null, null),
  /** A code indicating the type of name (e.g. nickname, alias, maiden name, legal, adopted) */
  PersonNameUse(null, "PersonNameUse", null, null),
  /** A name intended for use in searching or matching */
  EntityNameSearchUse(null, OrganizationNameUse, PersonNameUse, "EntityNameSearchUse", "SRCH", "search"),
  /** A name spelled according to the SoundEx algorithm. */
  Soundex(EntityNameSearchUse, EntityNameSearchUse, EntityNameSearchUse, "Soundex", "SNDX", "Soundex"),
  /** A name spelled phonetically.<p>There are a variety of phonetic spelling algorithms. This code value does not distinguish between these.
   <emph>Discussion: </emph>
</p> */
  Phonetic(EntityNameSearchUse, EntityNameSearchUse, EntityNameSearchUse, "Phonetic", "PHON", "phonetic"),
  /** e.g. Chief Red Cloud */
  IndigenousTribal(PersonNameUse, "Indigenous/Tribal", "I", "Indigenous/Tribal"),
  /** Known as/conventional/the one you use */
  Legal(OrganizationNameUse, PersonNameUse, "Legal", "L", "Legal"),
  /** As recorded on a license, record, certificate, etc. (only if different from legal name) */
  License(null, OrganizationNameUse, PersonNameUse, "License", "C", "License"),
  /** Identifies the different representations of a name. The representation may affect how the name is used. (E.g. use of Ideographic
for formal communications.) */
  NameRepresentationUse(null, OrganizationNameUse, PersonNameUse, "NameRepresentationUse", null, null),
  /** Alphabetic transcription of name (Japanese: romaji) */
  Alphabetic(NameRepresentationUse, NameRepresentationUse, NameRepresentationUse, "Alphabetic", "ABC", "Alphabetic"),
  /** Ideographic representation of name (e.g., Japanese kanji, Chinese characters) */
  Ideographic(NameRepresentationUse, NameRepresentationUse, NameRepresentationUse, "Ideographic", "IDE", "Ideographic"),
  /** Syllabic transcription of name (e.g., Japanese kana, Korean hangul) */
  Syllabic(NameRepresentationUse, NameRepresentationUse, NameRepresentationUse, "Syllabic", "SYL", "Syllabic"),
  /** Any name which is known not to be the real name for the person. */
  Pseudonym(PersonNameUse, "Pseudonym", "P", "pseudonym"),
  /** Includes writer's pseudonym, stage name, etc */
  ArtistStage(Pseudonym, "Artist/Stage", "A", "Artist/Stage"),
  /** e.g. Sister Mary Francis, Brother John */
  Religious(PersonNameUse, "Religious", "R", "Religious"),
  /** A name assigned to a person. Reasons some organizations assign alternate names may include not knowing the person's name,
or to maintain anonymity. Some, but not necessarily all, of the name types that people call "alias" may fit into this category. */
  Assigned(PersonNameUse, "Assigned", "ASGN", "assigned");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.45");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("EntityNameUse");

  private final EntityNameUse _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final EntityNameUse _parent2;
  private final EntityNameUse _parent3; // well, found a 3rd :(
  private EnumSet<EntityNameUse> _impliedConcepts = null;

  private EntityNameUse(EntityNameUse parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private EntityNameUse(EntityNameUse parent, EntityNameUse parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private EntityNameUse(EntityNameUse parent, EntityNameUse parent2, EntityNameUse parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<EntityNameUse> getImpliedConcepts() {
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
    else if (that instanceof EntityNameUse)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof EntityNameUse))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final EntityNameUse thatEntityNameUse = (EntityNameUse)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatEntityNameUse));
  }

  public EntityNameUse mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof EntityNameUse))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final EntityNameUse thatEntityNameUse = (EntityNameUse)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<EntityNameUse> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatEntityNameUse.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    EntityNameUse mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(EntityNameUse candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,EntityNameUse> _codeMap = null;

  public static EntityNameUse valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(EntityNameUse.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(EntityNameUse concept : EnumSet.allOf(EntityNameUse.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      EntityNameUse concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.45" + ", domain = " + "EntityNameUse"));
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

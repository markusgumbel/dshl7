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

/** Values that control disclosure of information.<p>
   <emph>Example:</emph> Normal, restricted, substance abuse related.
</p> */
public enum Confidentiality implements CS {

  // ACTUAL DATA

  root(null, "Confidentiality", null, null),
  /** By accessing subject / role and relationship based rights (These concepts are mutually exclusive, one and only one is required
for a valid confidentiality coding.) */
  ByAccessKind(null, "ConfidentialityByAccessKind", null, null),
  /** Since the service class can represent knowledge structures that may be considered a trade or business secret, there is sometimes
(though rarely) the need to flag those items as of business level confidentiality. However, no patient related information
may ever be of this confidentiality level. */
  Business(ByAccessKind, "Business", "B", "business"),
  /** Only clinicians may see this item, billing and administration persons can not access this item without special permission. */
  Clinician(ByAccessKind, "Clinician", "D", "clinician"),
  /** Access only to individual persons who are mentioned explicitly as actors of this service and whose actor type warrants that
access (cf. to actor type code). */
  Individual(ByAccessKind, "Individual", "I", "individual"),
  /** No patient record item can be of low confidentiality. However, some service objects are not patient related and therefore
may have low confidentiality. */
  Low(ByAccessKind, "Low", "L", "low"),
  /** Normal confidentiality rules (according to good health care practice) apply, that is, only authorized individuals with a legitimate
medical or business need may access this item. */
  Normal(ByAccessKind, "Normal", "N", "normal"),
  /** Restricted access, e.g. only to providers having a current care relationship to the patient. */
  Restricted(ByAccessKind, "Restricted", "R", "restricted"),
  /** Very restricted access as declared by the Privacy Officer of the record holder. */
  VeryRestricted(ByAccessKind, "Very restricted", "V", "very restricted"),
  /** By information type, only for service catalog entries (multiples allowed). Not to be used with actual patient data! */
  ByInfoType(null, "ConfidentialityByInfoType", null, null),
  /** HIV and AIDS related item */
  HIVRelated(ByInfoType, "HIV related", "HIV", "HIV related"),
  /** Psychiatry related item */
  PsychiatryRelate(ByInfoType, "Psychiatry relate", "PSY", "psychiatry relate"),
  /** Sexual assault / domestic violence related item */
  SexualAndDomesticViolenceRelated(ByInfoType, "Sexual and domestic violence related", "SDV", "sexual and domestic violence related"),
  /** Alcohol/drug-abuse related item */
  SubstanceAbuseRelated(ByInfoType, "Substance abuse related", "ETH", "substance abuse related"),
  /** Modifiers of role based access rights (multiple allowed) */
  Modifiers(null, "ConfidentialityModifiers", null, null),
  /** Celebrities are people of public interest (VIP) including employees, whose information require special protection. */
  Celebrity(Modifiers, "Celebrity", "C", "celebrity"),
  /** Information for which the patient seeks heightened confidentiality. Sensitive information is not to be shared with family
members. Information reported by the patient about family members is sensitive by default. Flag can be set or cleared on patient's
request. */
  Sensitive(Modifiers, "Sensitive", "S", "sensitive"),
  /** Information not to be disclosed or discussed with patient except through physician assigned to patient in this case. This
is usually a temporary constraint only, example use is a new fatal diagnosis or finding, such as malignancy or HIV. */
  Taboo(Modifiers, "Taboo", "T", "taboo");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.25");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("Confidentiality");

  private final Confidentiality _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final Confidentiality _parent2;
  private final Confidentiality _parent3; // well, found a 3rd :(
  private EnumSet<Confidentiality> _impliedConcepts = null;

  private Confidentiality(Confidentiality parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private Confidentiality(Confidentiality parent, Confidentiality parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private Confidentiality(Confidentiality parent, Confidentiality parent2, Confidentiality parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<Confidentiality> getImpliedConcepts() {
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
    else if (that instanceof Confidentiality)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof Confidentiality))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final Confidentiality thatConfidentiality = (Confidentiality)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatConfidentiality));
  }

  public Confidentiality mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof Confidentiality))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final Confidentiality thatConfidentiality = (Confidentiality)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<Confidentiality> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatConfidentiality.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    Confidentiality mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(Confidentiality candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,Confidentiality> _codeMap = null;

  public static Confidentiality valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(Confidentiality.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(Confidentiality concept : EnumSet.allOf(Confidentiality.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      Confidentiality concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.25" + ", domain = " + "Confidentiality"));
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

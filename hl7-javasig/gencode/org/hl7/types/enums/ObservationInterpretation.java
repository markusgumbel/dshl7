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

/** One or more codes specifying a rough qualitative interpretation of the observation, such as "normal", "abnormal", "below normal",
"change up", "resistant", "susceptible", etc.<p>
   <emph>Discussion:</emph> These interpretation codes are sometimes called "abnormal flags", however, the judgment of normalcy is just one of the common
   rough interpretations, and is often not relevant. For example, the susceptibility interpretations are not about "normalcy",
   and for any observation of a pathologic condition, it does not make sense to state the normalcy, since pathologic conditions
   are never considered "normal."
</p> */
public enum ObservationInterpretation implements CS {

  // ACTUAL DATA

  root(null, "ObservationInterpretation", null, null),
  /** Change of quantity and/or severity. At most one of B or W and one of U or D allowed. */
  Change(null, "ObservationInterpretationChange", null, null),
  /** Better (of severity or nominal observations) */
  Better(Change, "Better", "B", "better"),
  /** Significant change down (quantitative observations, does not imply B or W) */
  Decreased(Change, "Decreased", "D", "decreased"),
  /** Significant change up (quantitative observations, does not imply B or W) */
  Increased(Change, "Increased", "U", "increased"),
  /** Worse (of severity or nominal observations) */
  Worse(Change, "Worse", "W", "worse"),
  /** Technical exceptions. At most one allowed. Does not imply normality or severity. */
  Exceptions(null, "ObservationInterpretationExceptions", null, null),
  /** Above absolute high-off instrument scale. This is statement depending on the instrument, logically does not imply LL or L
(e.g., if the instrument is inadequate). If an off-scale value is also high or critically high one must also report H and
HH respectively. */
  HighOffScale(Exceptions, "High off scale", ">", "high off scale"),
  /** Below absolute low-off instrument scale. This is statement depending on the instrument, logically does not imply LL or L (e.g.,
if the instrument is inadequate). If an off-scale value is also low or critically low one must also report L and LL respectively. */
  LowOffScale(Exceptions, "Low off scale", "<", "low off scale"),
  /** Normality, Abnormality, Alert. Concepts in this category are mutually exclusive, i.e., at most one is allowed. */
  Normality(null, "ObservationInterpretationNormality", null, null),
  /** Normal (for all service types) */
  Normal(Normality, "Normal", "N", "Normal"),
  /** Abnormal (for nominal observations, all service types) */
  NormalityAbnormal(Normality, "ObservationInterpretationNormalityAbnormal", "A", "Abnormal"),
  /** Abnormal alert (for nominal observations and all service types) */
  NormalityAlert(NormalityAbnormal, "ObservationInterpretationNormalityAlert", "AA", "Abnormal alert"),
  /** Above high normal (for quantitative observations) */
  NormalityHigh(NormalityAbnormal, "ObservationInterpretationNormalityHigh", "H", "High"),
  /** Above upper alert threshold (for quantitative observations) */
  HighAlert(NormalityAlert, NormalityHigh, "High alert", "HH", "High alert"),
  /** Below low normal (for quantitative observations) */
  NormalityLow(NormalityAbnormal, "ObservationInterpretationNormalityLow", "L", "Low"),
  /** Below lower alert threshold (for quantitative observations) */
  LowAlert(NormalityAlert, NormalityLow, "Low alert", "LL", "Low alert"),
  /**  */
  ProtocolInclusion(null, "ObservationInterpretationProtocolInclusion", null, null),
  /** <emph role="strong">Definition:</emph>The observation/test result is interpreted as being outside the inclusion range for a particular protocol within which the
result is being reported.
<p>
   <emph role="strong">Example:</emph>A positive result on a Hepatitis screening test.
</p> */
  OustsideThreshold(ProtocolInclusion, "ObservationInterpretationOustsideThreshold", "EX", "outside threshold"),
  /** <emph role="strong">Definition:</emph>The numeric observation/test result is interpreted as being above the high threshold value for a particular protocol within
which the result is being reported.
<p>
   <emph role="strong">Example:</emph>An ALT (SGOT) result above a protocol-defined threshold value of 2.5 times the upper limit of normal based on the subject's
   sex and age.
</p> */
  AboveHighThreshold(OustsideThreshold, "Above high threshold", "HX", "above high threshold"),
  /** <emph role="strong">Definition:</emph>The numeric observation/test result is interpreted as being below the low threshold value for a particular protocol within
which the result is being reported.
<p>
   <emph role="strong">Example:</emph>A Total White Blood Cell Count falling below a protocol-defined threshold value of 3000/mm^3
</p> */
  BelowLowThreshold(OustsideThreshold, "Below low threshold", "LX", "below low threshold"),
  /** Microbiology: interpretations of minimal inhibitory concentration (MIC) values. At most one allowed. */
  Susceptibility(null, "ObservationInterpretationSusceptibility", null, null),
  /** Intermediate */
  Intermediate(Susceptibility, "Intermediate", "I", "intermediate"),
  /** Moderately susceptible */
  ModeratelySusceptible(Susceptibility, "Moderately susceptible", "MS", "moderately susceptible"),
  /** Resistent */
  Resistent(Susceptibility, "Resistent", "R", "resistent"),
  /** Susceptible */
  Susceptible(Susceptibility, "Susceptible", "S", "susceptible"),
  /** Very susceptible */
  VerySusceptible(Susceptibility, "Very susceptible", "VS", "very susceptible");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.83");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("ObservationInterpretation");

  private final ObservationInterpretation _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final ObservationInterpretation _parent2;
  private final ObservationInterpretation _parent3; // well, found a 3rd :(
  private EnumSet<ObservationInterpretation> _impliedConcepts = null;

  private ObservationInterpretation(ObservationInterpretation parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private ObservationInterpretation(ObservationInterpretation parent, ObservationInterpretation parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private ObservationInterpretation(ObservationInterpretation parent, ObservationInterpretation parent2, ObservationInterpretation parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<ObservationInterpretation> getImpliedConcepts() {
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
    else if (that instanceof ObservationInterpretation)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof ObservationInterpretation))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ObservationInterpretation thatObservationInterpretation = (ObservationInterpretation)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatObservationInterpretation));
  }

  public ObservationInterpretation mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof ObservationInterpretation))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ObservationInterpretation thatObservationInterpretation = (ObservationInterpretation)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<ObservationInterpretation> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatObservationInterpretation.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    ObservationInterpretation mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(ObservationInterpretation candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,ObservationInterpretation> _codeMap = null;

  public static ObservationInterpretation valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(ObservationInterpretation.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(ObservationInterpretation concept : EnumSet.allOf(ObservationInterpretation.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      ObservationInterpretation concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.83" + ", domain = " + "ObservationInterpretation"));
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

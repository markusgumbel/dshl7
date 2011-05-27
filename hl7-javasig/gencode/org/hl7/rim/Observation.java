/*
THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

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
package org.hl7.rim;

import org.hl7.rim.Act;
import org.hl7.types.ANY;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CE;
import org.hl7.types.CD;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An Act of recognizing and noting information about the subject, and whose immediate and primary outcome (post-condition) is
   new data about a subject. Observations often involve measurement or other elaborate methods of investigation, but may also
   be simply assertive statements. 
</p>
<p><i>Discussion:</i> Structurally, many observations are name-value-pairs, where the Observation.code (inherited from Act) is the name and the
   Observation.value is the value of the property. Such a construct is also known as a "variable" (a named feature that can assume
   a value); hence, the Observation class is always used to hold generic name-value-pairs or variables, even though the variable
   valuation may not be the result of an elaborate observation method. It may be a simple answer to a question or it may be an
   assertion or setting of a parameter.
</p>
<p>As with all Act statements, Observation statements describe what was done, and in the case of Observations, this includes
   a description of what was actually observed ("results" or "answers"); and those "results" or "answers" are part of the observation
   and not split off into other objects. 
</p>
<p>An observation may consist of component observations each having their own Observation.code and Observation.value. In this
   case, the composite observation may not have an Observation.value for itself. For instance, a white blood cell count consists
   of the sub-observations for the counts of the various granulocytes, lymphocytes and other normal or abnormal blood cells (e.g.,
   blasts). The overall white blood cell count Observation itself may therefore not have a value by itself (even though it could
   have one, e.g., the sum total of white blood cells). Thus, as long as an Act is essentially an Act of recognizing and noting
   information about a subject, it is an Observation, regardless of whether it has a simple value by itself or whether it has
   sub-observations.
</p>
<p>Even though observations are professional acts (see Act) and as such are intentional actions, this does not require that every
   possible outcome of an observation be pondered in advance of it being actually made. For instance, differential white blood
   cell counts (WBC) rarely show blasts, but if they do, this is part of the WBC observation even though blasts might not be
   predefined in the structure of a normal WBC.
</p>
<p>Diagnoses, findings, symptoms, etc. are also Observations. The Observation.code (or the reference to the Observation definition)
   specifies the kind of diagnosis (e.g. "chief complaint" or "discharge diagnosis") and the value specifies the diagnosis code
   or symptom code.
</p>
*/
public interface Observation extends org.hl7.rim.Act {

  /**<p>Information that is assigned or determined by the observation action. </p>
<p><i>Constraints:</i> The Observation.value, if not otherwise constrained, can be of any data type.
</p>
<p>The appropriate data type of the Observation.value varies with the kind of Observation and can generally be described in Observation
   definitions or in a simple relation that pairs Act.codes to value data types.
</p>
<p>The following guidelines govern the choice of the appropriate value data type.</p>
<p>(1) Quantitative measurements use the data type Physical Quantity (PQ) in general. A PQ is essentially a real number with
   a unit. This is the general preference for all numeric values, subject to a few exceptions listed below. 
</p>
<p>Numeric values <b>must not</b> be communicated as simply a character string (ST). 
</p>
<p>(2) Titer (e.g., 1:64) and very few other ratios use the data type Ratio (RTO). For titers, the ratio would be a ratio of
   two integer numbers (e.g., 1:128). Other ratios may relate different quantitative data types, such as a "price" specified
   in Physical Quantity over Monetary Amount.
</p>
<p>Sometimes by local conventions titers are reported as just the denominator (e.g., 32 instead of 1/32). Such conventions are
   confusing and <b>should</b> be converted into correct ratios in HL7 messages. 
</p>
<p>(3) Index values (a number without unit) uses the Real Number (REAL) data type. When a quantity does not have a proper unit,
   one can just send the number as a real number. Alternatively one can use a PQ with a dimensionless unit (e.g., 1 or %). An
   integer number should only be sent when the measurement is by definition an integer, which is an extremely rare case and then
   is most likely an ordinal (see below).
</p>
<p>(4) Ranges (e.g., &lt;3; 12-20) must be expressed as Interval of Physical Quantity (IVL&lt;PQ&gt;) or intervals of other quantity data
   types.
</p>
<p>Sometimes such intervals are used to report the uncertainty of measurement value. For uncertainty there are dedicated data
   type extensions available. 
</p>
<p>(5) Ordinals (e.g., +, ++, +++; or I, IIa, IIb, III, IV) use the Coded Ordinal (CO) data type. </p>
<p>(6) Nominal results ("taxons", e.g., organism type). use any of the coded data types (CD, CE) that specify at least a code
   and a coding system, and optionally original text, translations to other coding systems and sometimes qualifiers. 
</p>
<p>(7) Imaging results use the Encapsulated Data (ED) type. The encapsulated data type allows one to send an image (e.g., chest
   X-ray) or a movie (e.g., coronary angiography, cardiac echo) as alternatively inline binary data or as a reference to an external
   address where the data can be downloaded on demand.
</p>
<p>(8) Waveforms can be sent using the Correlated Observation Sequences templates that provide all the data in an HL7 framework.
   In addition one can use the Encapsulated Data (ED) data type to send waveforms in other than HL7 formats or to refer to waveform
   data for on-demand download. 
</p>
<p>(9) The character string data type may exceptionally be used to convey formalized expressions that do not fit in any of the
   existing data types. However, the string data type <b>must not</b> be used if the meaning can be represented by one of the existing data types. 
</p>
<p>(10) Timestamps <b>should not</b> be sent in Observations if there are more appropriate places to send those, e.g., usually as Act.effectiveTime of some act.
   (E.g., "specimen received in lab" is in the effectiveTime of an Act describing the specimen transport to the lab, not in an
   Observation.
</p>
<p>(11) Sets of values of any data type, enumerated sets as well as intervals, are often used for Observation criteria (event-criterion
   mood) to specify "normal ranges" or "decision ranges" (for alerts) etc.
</p>
<p>(12) For sequences of observations (repeated measurements of the same property during a relatively short time) a Sequence
   (LIST) data type is used. Refer to the Correlated Observation Sequences specification for more detail.
</p>
<p>(13) Uncertainty of values is specified using the Probability and Probability Distribution data type extensions (UVP, PPD).
   If a statistical sample is reported with absolute frequencies of categories a Bag collection (BAG) can be used efficiently.
</p>
  */
  ANY getValue();
  /** Sets the property value.
      @see #getValue
  */
  void setValue(ANY value);

  /**<p>One or more codes specifying a rough qualitative interpretation of the observation, such as "normal", "abnormal", "below normal",
   "change up", "resistant", "susceptible", etc.
</p>
<p><i>Discussion:</i> These interpretation codes are sometimes called "abnormal flags", however, the judgment of normalcy is just one of the common
   rough interpretations, and is often not relevant. For example, the susceptibility interpretations are not about "normalcy",
   and for any observation of a pathologic condition, it does not make sense to state the normalcy, since pathologic conditions
   are never considered "normal."
</p>
  */
  SET<CE> getInterpretationCode();
  /** Sets the property interpretationCode.
      @see #getInterpretationCode
  */
  void setInterpretationCode(SET<CE> interpretationCode);

  /**<p>A code that provides additional detail about the means or technique used to ascertain the observation.</p>
<p><i>Examples:</i> Blood pressure measurement method: arterial puncture vs. sphygmomanometer (Riva-Rocci), sitting vs. supine position, etc.
   
</p>
<p><i>Constraints:</i> In all observations the method is already partially specified by the Act.code. In this case, the methodCode <b>need not</b> be used at all. The methodCode <b>may</b> still be used to identify this method more clearly in addition to what is implied from the Act.code. However, an information
   consumer system or process <b>should not</b> depend on this methodCode information for method detail that is implied by the Act.code. 
</p>
<p>If the methodCode is used to express method detail that is also implied by the Act.code, the methodCode <b>must not</b> be in conflict with the implied method of the Act.code.
</p>
<p><i>Discussion:</i> In all observations the method is already partially specified by simply knowing the kind of observation (observation definition,
   Act.code) and this implicit information about the method does not need to be specified in Observation.methodCode. For example,
   many LOINC codes are defined for specific methods as long as the method makes a practical difference in interpretation. Thus,
   using LOINC, the difference between susceptibility studies using the "minimal inhibitory concentration" (MIC) or the "agar
   diffusion method" (Kirby-Baur) are specifically assigned different codes. The methodCode therefore is only an additional qualifier
   to specify what may not be known already from the Act.code.
</p>
<p>Also, some variances in methods may be tied to the particular device used. The methodCode should not be used to identify the
   specific device or test-kit material used in the observation. Such information about devices or test-kits should be associated
   with the observation as "device" participations.
</p>
  */
  SET<CE> getMethodCode();
  /** Sets the property methodCode.
      @see #getMethodCode
  */
  void setMethodCode(SET<CE> methodCode);

  /**<p>A code specifying detail about the anatomical site or system that is the focus of the observation if this information is not
   already implied by the observation definition or Act.code.
</p>
<p><i>Constraints:</i> The targetSiteCode value, if specified, <b>must not</b> conflict with what is implied about the target site or system from the observation definition and the Act.code.
</p>
<p><i>Discussion:</i> Most observation target sites are implied by the observation definition and Act.code, or Observation.value. For example,
   "heart murmur" always has the heart as target. This attribute is used only when the observation target site needs to be refined,
   to distinguish right and left etc. 
</p>
<p>If the subject of the Observation is something other than a human patient or animal, the attribute is used analogously to
   specify a structural landmark of the thing where the act focuses. For example, if the subject is a lake, the site could be
   inflow and outflow, etc. If the subject is a lymphatic node, "hilus," "periphery," etc. would still be valid target sites.
</p>
  */
  SET<CD> getTargetSiteCode();
  /** Sets the property targetSiteCode.
      @see #getTargetSiteCode
  */
  void setTargetSiteCode(SET<CD> targetSiteCode);
}

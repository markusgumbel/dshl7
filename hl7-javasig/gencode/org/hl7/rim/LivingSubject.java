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

import org.hl7.rim.Entity;
import org.hl7.types.CE;
import org.hl7.types.TS;
import org.hl7.types.BL;
import org.hl7.types.INT;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of Entity representing an organism or complex animal, alive or not. </p>
<p><i>Examples:</i> A person, dog, microorganism or a plant of any taxonomic group.
</p>
<p><i>Constraints:</i> Instances of this class encompass mammals, birds, fishes, bacteria, parasites, fungi and viruses. Person is a specialization
   of this class. 
</p>
<p><i>Rationale:</i> This class contains "static" or "administrative" attributes of interest to medicine that differentiate living organisms from
   other Entities.
</p>
*/
public interface LivingSubject extends org.hl7.rim.Entity {

  /**<p>A value representing the gender (sex) of a Living subject.</p>
<p><i>Examples:</i> female, male 
</p>
<p><i>Discussion:</i> This attribute does not include terms related to clinical gender. Gender is a complex physiological, genetic and sociological
   concept that requires multiple observations in order to be comprehensively described. The purpose of this attribute is to
   provide a high level classification that can additionally be used for the appropriate allocation of inpatient bed assignment.
</p>
<p><i>Constraints:</i> This code is used for administrative purposes. 
</p>
<p>ExtRef: This information is reported on UB FL 15.</p>
  */
  CE getAdministrativeGenderCode();
  /** Sets the property administrativeGenderCode.
      @see #getAdministrativeGenderCode
  */
  void setAdministrativeGenderCode(CE administrativeGenderCode);

  /**<p>The date and time of a living subject's birth or hatching.</p>
  */
  TS getBirthTime();
  /** Sets the property birthTime.
      @see #getBirthTime
  */
  void setBirthTime(TS birthTime);

  /**<p>An indication that the subject is dead.</p>
  */
  BL getDeceasedInd();
  /** Sets the property deceasedInd.
      @see #getDeceasedInd
  */
  void setDeceasedInd(BL deceasedInd);

  /**<p>The date and time that a living subject's death occurred.</p>
  */
  TS getDeceasedTime();
  /** Sets the property deceasedTime.
      @see #getDeceasedTime
  */
  void setDeceasedTime(TS deceasedTime);

  /**<p>An indication as to whether the living subject is part of a multiple birth.</p>
  */
  BL getMultipleBirthInd();
  /** Sets the property multipleBirthInd.
      @see #getMultipleBirthInd
  */
  void setMultipleBirthInd(BL multipleBirthInd);

  /**<p>The order in which this living subject was born if part of a multiple birth.</p>
  */
  INT getMultipleBirthOrderNumber();
  /** Sets the property multipleBirthOrderNumber.
      @see #getMultipleBirthOrderNumber
  */
  void setMultipleBirthOrderNumber(INT multipleBirthOrderNumber);

  /**<p>An indication that the living subject is a candidate to serve as an organ donor.</p>
<p><i>Discussion:</i> This attribute specifies whether an individual living subject has donated or is willing to donate an organ.
</p>
  */
  BL getOrganDonorInd();
  /** Sets the property organDonorInd.
      @see #getOrganDonorInd
  */
  void setOrganDonorInd(BL organDonorInd);
}

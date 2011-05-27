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
import org.hl7.types.CE;
import org.hl7.types.PQ;
import org.hl7.types.BL;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An interaction between a patient and care provider(s) for the purpose of providing healthcare-related service(s). Healthcare
   services include health assessment.
</p>
<p><i>Examples:</i> outpatient visit to multiple departments, home health support (including physical therapy), inpatient hospital stay, emergency
   room visit, field visit (e.g., traffic accident), office visit, occupational therapy, telephone call.
</p>
*/
public interface PatientEncounter extends org.hl7.rim.Act {

  /**<p>The source of the referral for a patient encounter.</p>
  */
  CE getAdmissionReferralSourceCode();
  /** Sets the property admissionReferralSourceCode.
      @see #getAdmissionReferralSourceCode
  */
  void setAdmissionReferralSourceCode(CE admissionReferralSourceCode);

  /**<p>Identifies the total quantity of time when the subject is expected to be or was resident at a facility as part of an encounter.</p>
<p><i>Discussion:</i> The actual days quantity cannot be simply calculated from the admission and discharge dates because of possible leaves of
   absence.
</p>
  */
  PQ getLengthOfStayQuantity();
  /** Sets the property lengthOfStayQuantity.
      @see #getLengthOfStayQuantity
  */
  void setLengthOfStayQuantity(PQ lengthOfStayQuantity);

  /**<p>A code depicting the disposition of the patient at the time of discharge (e.g., discharged to home, expired, against medical
   advice, etc.). While the encounter is still "active" (the encounter doesn't have an end date yet) this attribute should be
   interpreted as the expected discharge disposition. When the encounter is "completed" this field contains the actual discharge
   disposition.
</p>
  */
  CE getDischargeDispositionCode();
  /** Sets the property dischargeDispositionCode.
      @see #getDischargeDispositionCode
  */
  void setDischargeDispositionCode(CE dischargeDispositionCode);

  /**<p>An indication that pre-admission tests are required for this patient encounter.</p>
  */
  BL getPreAdmitTestInd();
  /** Sets the property preAdmitTestInd.
      @see #getPreAdmitTestInd
  */
  void setPreAdmitTestInd(BL preAdmitTestInd);

  /**<p>A code identifying special courtesies extended to the patient. For example, no courtesies, extended courtesies, professional
   courtesy, VIP courtesies.
</p>
  */
  SET<CE> getSpecialCourtesiesCode();
  /** Sets the property specialCourtesiesCode.
      @see #getSpecialCourtesiesCode
  */
  void setSpecialCourtesiesCode(SET<CE> specialCourtesiesCode);

  /**<p>A code indicating the type of special arrangements provided for a patient encounter (e.g., wheelchair, stretcher, interpreter,
   attendant, seeing eye dog). For encounters in intention moods, this information can be used to identify special arrangements
   that will need to be made for the incoming patient. This is not related to the AccommodationEvent.
</p>
  */
  SET<CE> getSpecialArrangementCode();
  /** Sets the property specialArrangementCode.
      @see #getSpecialArrangementCode
  */
  void setSpecialArrangementCode(SET<CE> specialArrangementCode);
}

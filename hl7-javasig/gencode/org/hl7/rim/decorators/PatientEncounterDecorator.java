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
package org.hl7.rim.decorators;

import org.hl7.rim.PatientEncounter;
import org.hl7.rim.decorators.ActDecorator;
import org.hl7.types.CE;
import org.hl7.types.PQ;
import org.hl7.types.BL;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CE;

import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.CEnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.PatientEncounter as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.PatientEncounter
  */
public abstract class PatientEncounterDecorator extends org.hl7.rim.decorators.ActDecorator implements PatientEncounter {
  /** Property accessor, returns NULL/NA if not overloaded.admissionReferralSourceCode.
      @see org.hl7.rim.PatientEncounter#getAdmissionReferralSourceCode
  */
  public CE getAdmissionReferralSourceCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.admissionReferralSourceCode.
      @see org.hl7.rim.PatientEncounter#setAdmissionReferralSourceCode
  */
  public void setAdmissionReferralSourceCode(CE admissionReferralSourceCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.lengthOfStayQuantity.
      @see org.hl7.rim.PatientEncounter#getLengthOfStayQuantity
  */
  public PQ getLengthOfStayQuantity() { return PQnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.lengthOfStayQuantity.
      @see org.hl7.rim.PatientEncounter#setLengthOfStayQuantity
  */
  public void setLengthOfStayQuantity(PQ lengthOfStayQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.dischargeDispositionCode.
      @see org.hl7.rim.PatientEncounter#getDischargeDispositionCode
  */
  public CE getDischargeDispositionCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.dischargeDispositionCode.
      @see org.hl7.rim.PatientEncounter#setDischargeDispositionCode
  */
  public void setDischargeDispositionCode(CE dischargeDispositionCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.preAdmitTestInd.
      @see org.hl7.rim.PatientEncounter#getPreAdmitTestInd
  */
  public BL getPreAdmitTestInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.preAdmitTestInd.
      @see org.hl7.rim.PatientEncounter#setPreAdmitTestInd
  */
  public void setPreAdmitTestInd(BL preAdmitTestInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.specialCourtesiesCode.
      @see org.hl7.rim.PatientEncounter#getSpecialCourtesiesCode
  */
  public SET<CE> getSpecialCourtesiesCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.specialCourtesiesCode.
      @see org.hl7.rim.PatientEncounter#setSpecialCourtesiesCode
  */
  public void setSpecialCourtesiesCode(SET<CE> specialCourtesiesCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.specialArrangementCode.
      @see org.hl7.rim.PatientEncounter#getSpecialArrangementCode
  */
  public SET<CE> getSpecialArrangementCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.specialArrangementCode.
      @see org.hl7.rim.PatientEncounter#setSpecialArrangementCode
  */
  public void setSpecialArrangementCode(SET<CE> specialArrangementCode) { /*throw new UnsupportedOperationException();*/ }
}

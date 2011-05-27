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

import org.hl7.rim.LivingSubject;
import org.hl7.rim.decorators.EntityDecorator;
import org.hl7.types.CE;
import org.hl7.types.TS;
import org.hl7.types.BL;
import org.hl7.types.INT;

import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.INTnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.LivingSubject as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.LivingSubject
  */
public abstract class LivingSubjectDecorator extends org.hl7.rim.decorators.EntityDecorator implements LivingSubject {
  /** Property accessor, returns NULL/NA if not overloaded.administrativeGenderCode.
      @see org.hl7.rim.LivingSubject#getAdministrativeGenderCode
  */
  public CE getAdministrativeGenderCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.administrativeGenderCode.
      @see org.hl7.rim.LivingSubject#setAdministrativeGenderCode
  */
  public void setAdministrativeGenderCode(CE administrativeGenderCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.birthTime.
      @see org.hl7.rim.LivingSubject#getBirthTime
  */
  public TS getBirthTime() { return TSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.birthTime.
      @see org.hl7.rim.LivingSubject#setBirthTime
  */
  public void setBirthTime(TS birthTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.deceasedInd.
      @see org.hl7.rim.LivingSubject#getDeceasedInd
  */
  public BL getDeceasedInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.deceasedInd.
      @see org.hl7.rim.LivingSubject#setDeceasedInd
  */
  public void setDeceasedInd(BL deceasedInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.deceasedTime.
      @see org.hl7.rim.LivingSubject#getDeceasedTime
  */
  public TS getDeceasedTime() { return TSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.deceasedTime.
      @see org.hl7.rim.LivingSubject#setDeceasedTime
  */
  public void setDeceasedTime(TS deceasedTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.multipleBirthInd.
      @see org.hl7.rim.LivingSubject#getMultipleBirthInd
  */
  public BL getMultipleBirthInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.multipleBirthInd.
      @see org.hl7.rim.LivingSubject#setMultipleBirthInd
  */
  public void setMultipleBirthInd(BL multipleBirthInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.multipleBirthOrderNumber.
      @see org.hl7.rim.LivingSubject#getMultipleBirthOrderNumber
  */
  public INT getMultipleBirthOrderNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.multipleBirthOrderNumber.
      @see org.hl7.rim.LivingSubject#setMultipleBirthOrderNumber
  */
  public void setMultipleBirthOrderNumber(INT multipleBirthOrderNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.organDonorInd.
      @see org.hl7.rim.LivingSubject#getOrganDonorInd
  */
  public BL getOrganDonorInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.organDonorInd.
      @see org.hl7.rim.LivingSubject#setOrganDonorInd
  */
  public void setOrganDonorInd(BL organDonorInd) { /*throw new UnsupportedOperationException();*/ }
}

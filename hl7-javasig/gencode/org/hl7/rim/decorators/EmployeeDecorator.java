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

import org.hl7.rim.Employee;
import org.hl7.rim.decorators.RoleDecorator;
import org.hl7.types.CE;
import org.hl7.types.SC;
import org.hl7.types.MO;
import org.hl7.types.ED;

import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.SCnull;
import org.hl7.types.impl.MOnull;
import org.hl7.types.impl.EDnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Employee as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Employee
  */
public abstract class EmployeeDecorator extends org.hl7.rim.decorators.RoleDecorator implements Employee {
  /** Property accessor, returns NULL/NA if not overloaded.jobCode.
      @see org.hl7.rim.Employee#getJobCode
  */
  public CE getJobCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.jobCode.
      @see org.hl7.rim.Employee#setJobCode
  */
  public void setJobCode(CE jobCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.jobTitleName.
      @see org.hl7.rim.Employee#getJobTitleName
  */
  public SC getJobTitleName() { return SCnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.jobTitleName.
      @see org.hl7.rim.Employee#setJobTitleName
  */
  public void setJobTitleName(SC jobTitleName) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.jobClassCode.
      @see org.hl7.rim.Employee#getJobClassCode
  */
  public CE getJobClassCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.jobClassCode.
      @see org.hl7.rim.Employee#setJobClassCode
  */
  public void setJobClassCode(CE jobClassCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.occupationCode.
      @see org.hl7.rim.Employee#getOccupationCode
  */
  public CE getOccupationCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.occupationCode.
      @see org.hl7.rim.Employee#setOccupationCode
  */
  public void setOccupationCode(CE occupationCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.salaryTypeCode.
      @see org.hl7.rim.Employee#getSalaryTypeCode
  */
  public CE getSalaryTypeCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.salaryTypeCode.
      @see org.hl7.rim.Employee#setSalaryTypeCode
  */
  public void setSalaryTypeCode(CE salaryTypeCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.salaryQuantity.
      @see org.hl7.rim.Employee#getSalaryQuantity
  */
  public MO getSalaryQuantity() { return MOnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.salaryQuantity.
      @see org.hl7.rim.Employee#setSalaryQuantity
  */
  public void setSalaryQuantity(MO salaryQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.hazardExposureText.
      @see org.hl7.rim.Employee#getHazardExposureText
  */
  public ED getHazardExposureText() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.hazardExposureText.
      @see org.hl7.rim.Employee#setHazardExposureText
  */
  public void setHazardExposureText(ED hazardExposureText) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.protectiveEquipmentText.
      @see org.hl7.rim.Employee#getProtectiveEquipmentText
  */
  public ED getProtectiveEquipmentText() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.protectiveEquipmentText.
      @see org.hl7.rim.Employee#setProtectiveEquipmentText
  */
  public void setProtectiveEquipmentText(ED protectiveEquipmentText) { /*throw new UnsupportedOperationException();*/ }
}

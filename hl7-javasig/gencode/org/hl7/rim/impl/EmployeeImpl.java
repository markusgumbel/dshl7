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
package org.hl7.rim.impl;

import org.hibernate.Criteria;
import org.hl7.hibernate.Persistence;

import org.hl7.rim.Employee;
import org.hl7.rim.impl.RoleImpl;
import org.hl7.types.CE;
import org.hl7.types.SC;
import org.hl7.types.MO;
import org.hl7.types.ED;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Employee as a simple data holder bean.
    @see org.hl7.rim.Employee
  */
public class EmployeeImpl extends org.hl7.rim.impl.RoleImpl implements Employee {

  private CE _jobCode;
  /** Gets the property jobCode.
      @see org.hl7.rim.Employee#getJobCode
  */
  public CE getJobCode() { return _jobCode; }
  /** Sets the property jobCode.
      @see org.hl7.rim.Employee#setJobCode
  */
  public void setJobCode(CE jobCode) {
    if(jobCode instanceof org.hl7.hibernate.ClonableCollection)
      jobCode = ((org.hl7.hibernate.ClonableCollection<CE>) jobCode).cloneHibernateCollectionIfNecessary();
    _jobCode = jobCode;
  }
  /** Sets the property jobCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setJobCode
  */
  public void setJobCodeForHibernate(CE jobCode) {
    _jobCode = jobCode;
  }

  private SC _jobTitleName;
  /** Gets the property jobTitleName.
      @see org.hl7.rim.Employee#getJobTitleName
  */
  public SC getJobTitleName() { return _jobTitleName; }
  /** Sets the property jobTitleName.
      @see org.hl7.rim.Employee#setJobTitleName
  */
  public void setJobTitleName(SC jobTitleName) {
    if(jobTitleName instanceof org.hl7.hibernate.ClonableCollection)
      jobTitleName = ((org.hl7.hibernate.ClonableCollection<SC>) jobTitleName).cloneHibernateCollectionIfNecessary();
    _jobTitleName = jobTitleName;
  }
  /** Sets the property jobTitleName. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setJobTitleName
  */
  public void setJobTitleNameForHibernate(SC jobTitleName) {
    _jobTitleName = jobTitleName;
  }

  private CE _jobClassCode;
  /** Gets the property jobClassCode.
      @see org.hl7.rim.Employee#getJobClassCode
  */
  public CE getJobClassCode() { return _jobClassCode; }
  /** Sets the property jobClassCode.
      @see org.hl7.rim.Employee#setJobClassCode
  */
  public void setJobClassCode(CE jobClassCode) {
    if(jobClassCode instanceof org.hl7.hibernate.ClonableCollection)
      jobClassCode = ((org.hl7.hibernate.ClonableCollection<CE>) jobClassCode).cloneHibernateCollectionIfNecessary();
    _jobClassCode = jobClassCode;
  }
  /** Sets the property jobClassCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setJobClassCode
  */
  public void setJobClassCodeForHibernate(CE jobClassCode) {
    _jobClassCode = jobClassCode;
  }

  private CE _occupationCode;
  /** Gets the property occupationCode.
      @see org.hl7.rim.Employee#getOccupationCode
  */
  public CE getOccupationCode() { return _occupationCode; }
  /** Sets the property occupationCode.
      @see org.hl7.rim.Employee#setOccupationCode
  */
  public void setOccupationCode(CE occupationCode) {
    if(occupationCode instanceof org.hl7.hibernate.ClonableCollection)
      occupationCode = ((org.hl7.hibernate.ClonableCollection<CE>) occupationCode).cloneHibernateCollectionIfNecessary();
    _occupationCode = occupationCode;
  }
  /** Sets the property occupationCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setOccupationCode
  */
  public void setOccupationCodeForHibernate(CE occupationCode) {
    _occupationCode = occupationCode;
  }

  private CE _salaryTypeCode;
  /** Gets the property salaryTypeCode.
      @see org.hl7.rim.Employee#getSalaryTypeCode
  */
  public CE getSalaryTypeCode() { return _salaryTypeCode; }
  /** Sets the property salaryTypeCode.
      @see org.hl7.rim.Employee#setSalaryTypeCode
  */
  public void setSalaryTypeCode(CE salaryTypeCode) {
    if(salaryTypeCode instanceof org.hl7.hibernate.ClonableCollection)
      salaryTypeCode = ((org.hl7.hibernate.ClonableCollection<CE>) salaryTypeCode).cloneHibernateCollectionIfNecessary();
    _salaryTypeCode = salaryTypeCode;
  }
  /** Sets the property salaryTypeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setSalaryTypeCode
  */
  public void setSalaryTypeCodeForHibernate(CE salaryTypeCode) {
    _salaryTypeCode = salaryTypeCode;
  }

  private MO _salaryQuantity;
  /** Gets the property salaryQuantity.
      @see org.hl7.rim.Employee#getSalaryQuantity
  */
  public MO getSalaryQuantity() { return _salaryQuantity; }
  /** Sets the property salaryQuantity.
      @see org.hl7.rim.Employee#setSalaryQuantity
  */
  public void setSalaryQuantity(MO salaryQuantity) {
    if(salaryQuantity instanceof org.hl7.hibernate.ClonableCollection)
      salaryQuantity = ((org.hl7.hibernate.ClonableCollection<MO>) salaryQuantity).cloneHibernateCollectionIfNecessary();
    _salaryQuantity = salaryQuantity;
  }
  /** Sets the property salaryQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setSalaryQuantity
  */
  public void setSalaryQuantityForHibernate(MO salaryQuantity) {
    _salaryQuantity = salaryQuantity;
  }

  private ED _hazardExposureText;
  /** Gets the property hazardExposureText.
      @see org.hl7.rim.Employee#getHazardExposureText
  */
  public ED getHazardExposureText() { return _hazardExposureText; }
  /** Sets the property hazardExposureText.
      @see org.hl7.rim.Employee#setHazardExposureText
  */
  public void setHazardExposureText(ED hazardExposureText) {
    if(hazardExposureText instanceof org.hl7.hibernate.ClonableCollection)
      hazardExposureText = ((org.hl7.hibernate.ClonableCollection<ED>) hazardExposureText).cloneHibernateCollectionIfNecessary();
    _hazardExposureText = hazardExposureText;
  }
  /** Sets the property hazardExposureText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setHazardExposureText
  */
  public void setHazardExposureTextForHibernate(ED hazardExposureText) {
    _hazardExposureText = hazardExposureText;
  }

  private ED _protectiveEquipmentText;
  /** Gets the property protectiveEquipmentText.
      @see org.hl7.rim.Employee#getProtectiveEquipmentText
  */
  public ED getProtectiveEquipmentText() { return _protectiveEquipmentText; }
  /** Sets the property protectiveEquipmentText.
      @see org.hl7.rim.Employee#setProtectiveEquipmentText
  */
  public void setProtectiveEquipmentText(ED protectiveEquipmentText) {
    if(protectiveEquipmentText instanceof org.hl7.hibernate.ClonableCollection)
      protectiveEquipmentText = ((org.hl7.hibernate.ClonableCollection<ED>) protectiveEquipmentText).cloneHibernateCollectionIfNecessary();
    _protectiveEquipmentText = protectiveEquipmentText;
  }
  /** Sets the property protectiveEquipmentText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Employee#setProtectiveEquipmentText
  */
  public void setProtectiveEquipmentTextForHibernate(ED protectiveEquipmentText) {
    _protectiveEquipmentText = protectiveEquipmentText;
  }
  public Object clone() throws CloneNotSupportedException {
    EmployeeImpl that = (EmployeeImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

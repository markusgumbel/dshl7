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

import org.hl7.rim.Role;
import org.hl7.types.CE;
import org.hl7.types.SC;
import org.hl7.types.MO;
import org.hl7.types.ED;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A role played by a person who is associated with an organization (the employer, scoper) to receive wages or salary.</p>
<p><i>Discussion:</i> The purpose of the role is to identify the type of relationship the employee has to the employer rather than the nature of
   the work actually performed (contrast with AssignedEntity).
</p>
*/
public interface Employee extends org.hl7.rim.Role {

  /**<p>A code specifying an employer-defined categorization of work, used primarily for payroll/remuneration purposes and not necessarily
   indicative of an employee's specific work assignments, responsibilities and privileges. Examples are accountant, programmer
   analyst, patient care associate, staff nurse, etc.
</p>
  */
  CE getJobCode();
  /** Sets the property jobCode.
      @see #getJobCode
  */
  void setJobCode(CE jobCode);

  /**<p>The title of the job held, for example, Vice President, Senior Technical Analyst. This is a local name for the employee's
   occupation that doesn't necessarily correspond to any scheme for categorizing occupation. Trading partners that need a coded
   standard should be using Employee "occupation" attribute.
</p>
  */
  SC getJobTitleName();
  /** Sets the property jobTitleName.
      @see #getJobTitleName
  */
  void setJobTitleName(SC jobTitleName);

  /**<p>A code qualifying the employment regarding frequency or periodicity, such as, full-time vs. part-time.</p>
  */
  CE getJobClassCode();
  /** Sets the property jobClassCode.
      @see #getJobClassCode
  */
  void setJobClassCode(CE jobClassCode);

  /**<p>A code qualifying the classification of kind-of-work based upon a recognized industry or jurisdictional standard.</p>
  */
  CE getOccupationCode();
  /** Sets the property occupationCode.
      @see #getOccupationCode
  */
  void setOccupationCode(CE occupationCode);

  /**<p>A code specifying the method used by the employer to compute the employee's salary or wages. For example, hourly, annual,
   or commission.
</p>
  */
  CE getSalaryTypeCode();
  /** Sets the property salaryTypeCode.
      @see #getSalaryTypeCode
  */
  void setSalaryTypeCode(CE salaryTypeCode);

  /**<p>The amount paid in salary or wages to the employee according to the computation method specified in salaryTypeCode. E.g.,
   if the salaryTypeCode is "hourly" the salaryQuantity specifies the hourly wage.
</p>
  */
  MO getSalaryQuantity();
  /** Sets the property salaryQuantity.
      @see #getSalaryQuantity
  */
  void setSalaryQuantity(MO salaryQuantity);

  /**<p>The type of hazards associated with the work performed by the employee for the employer. For example, asbestos, infectious
   agents.
</p>
  */
  ED getHazardExposureText();
  /** Sets the property hazardExposureText.
      @see #getHazardExposureText
  */
  void setHazardExposureText(ED hazardExposureText);

  /**<p>Protective equipment needed for the job performed by the employee for the employer. For example, safety glasses, hardhat.</p>
  */
  ED getProtectiveEquipmentText();
  /** Sets the property protectiveEquipmentText.
      @see #getProtectiveEquipmentText
  */
  void setProtectiveEquipmentText(ED protectiveEquipmentText);
}

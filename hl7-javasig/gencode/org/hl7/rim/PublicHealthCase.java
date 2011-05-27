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

import org.hl7.rim.Observation;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A public health case is an Observation representing a condition or event that has a specific significance for public health.
   Typically it involves an instance or instances of a reportable infectious disease or other condition. The public health case
   can include a health-related event concerning a single individual or it may refer to multiple health-related events that are
   occurrences of the same disease or condition of interest to public health. An outbreak involving multiple individuals may
   be considered as a type of public health case. A public health case definition (Act.moodCode = "definition") includes the
   description of the clinical, laboratory, and epidemiologic indicators associated with a disease or condition of interest to
   public health. There are case definitions for conditions that are reportable, as well as for those that are not. There are
   also case definitions for outbreaks. A public health case definition is a construct used by public health for the purpose
   of counting cases, and should not be used as clinical indications for treatment. Examples include AIDS, toxic-shock syndrome,
   and salmonellosis and their associated indicators that are used to define a case.
</p>
*/
public interface PublicHealthCase extends org.hl7.rim.Observation {

  /**<p>Code for the method by which the public health department was made aware of the case. Includes provider report, patient self-referral,
   laboratory report, case or outbreak investigation, contact investigation, active surveillance, routine physical, prenatal
   testing, perinatal testing, prison entry screening, occupational disease surveillance, medical record review, etc.
</p>
  */
  CE getDetectionMethodCode();
  /** Sets the property detectionMethodCode.
      @see #getDetectionMethodCode
  */
  void setDetectionMethodCode(CE detectionMethodCode);

  /**<p>Code for the mechanism by which disease was acquired by the living subject involved in the public health case. Includes sexually
   transmitted, airborne, bloodborne, vectorborne, foodborne, zoonotic, nosocomial, mechanical, dermal, congenital, environmental
   exposure, indeterminate.
</p>
  */
  CE getTransmissionModeCode();
  /** Sets the property transmissionModeCode.
      @see #getTransmissionModeCode
  */
  void setTransmissionModeCode(CE transmissionModeCode);

  /**<p>Code that indicates whether the disease was likely acquired outside the jurisdiction of observation, and if so, the nature
   of the inter-jurisdictional relationship. Possible values include not imported, imported from another country, imported from
   another state, imported from another jurisdiction, and insufficient information to determine.
</p>
  */
  CE getDiseaseImportedCode();
  /** Sets the property diseaseImportedCode.
      @see #getDiseaseImportedCode
  */
  void setDiseaseImportedCode(CE diseaseImportedCode);
}

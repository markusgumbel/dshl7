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
import org.hl7.types.BAG;
import org.hl7.types.AD;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An Entity representing a formalized group of entities with a common purpose (e.g. administrative, legal, political) and the
   infrastructure to carry out that purpose. 
</p>
<p><i>Examples:</i> Companies and institutions, a government department, an incorporated body that is responsible for administering a facility,
   an insurance company.
</p>
*/
public interface Organization extends org.hl7.rim.Entity {

  /**<p>The postal and/or residential address of an organization.</p>
  */
  BAG<AD> getAddr();
  /** Sets the property addr.
      @see #getAddr
  */
  void setAddr(BAG<AD> addr);

  /**<p>A value representing the industrial category of an organization entity.</p>
<p><i>Examples:</i> 11231-Chicken Egg Production, 6211- Offices of Physicians, 621511-Medical Laboratories, 524114-Direct Health and Medical
   Insurance Carriers
</p>
  */
  CE getStandardIndustryClassCode();
  /** Sets the property standardIndustryClassCode.
      @see #getStandardIndustryClassCode
  */
  void setStandardIndustryClassCode(CE standardIndustryClassCode);
}

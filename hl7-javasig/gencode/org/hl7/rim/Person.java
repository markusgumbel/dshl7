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

import org.hl7.rim.LivingSubject;
import org.hl7.types.BAG;
import org.hl7.types.AD;
import org.hl7.types.CE;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CE;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of LivingSubject representing a human being.</p>
<p><i>Constraints:</i> This class can be used to represent either a single individual or a group of individuals based on the value of Entity.determinerCode
   and Entity.quantity.
</p>
*/
public interface Person extends org.hl7.rim.LivingSubject {

  /**<p>The postal and/or residential address of a Person.</p>
  */
  BAG<AD> getAddr();
  /** Sets the property addr.
      @see #getAddr
  */
  void setAddr(BAG<AD> addr);

  /**<p>A value representing the domestic partnership status of a person.</p>
<p><i>Examples:</i> Married, separated, divorced, widowed, common-law marriage.
</p>
<p><i>Rationale:</i> This information is reported on UB FL 16
</p>
  */
  CE getMaritalStatusCode();
  /** Sets the property maritalStatusCode.
      @see #getMaritalStatusCode
  */
  void setMaritalStatusCode(CE maritalStatusCode);

  /**<p>The highest level of education a person achieved (e.g. elementary school, high school or secondary school degree complete,
   college or baccalaureate degree complete).
</p>
  */
  CE getEducationLevelCode();
  /** Sets the property educationLevelCode.
      @see #getEducationLevelCode
  */
  void setEducationLevelCode(CE educationLevelCode);

  /**<p>A value identifying a person's disability.</p>
<p><i>Examples:</i> vision impaired, hearing impaired.
</p>
  */
  SET<CE> getDisabilityCode();
  /** Sets the property disabilityCode.
      @see #getDisabilityCode
  */
  void setDisabilityCode(SET<CE> disabilityCode);

  /**<p>A value specifying the housing situation of a person. </p>
<p><i>Examples:</i> Independent household, institution, nursing home, extended care facility, retirement community, etc.). 
</p>
<p><i>Discussion:</i> Used for discharge planning, social service assessment, psychosocial evaluation.
</p>
  */
  CE getLivingArrangementCode();
  /** Sets the property livingArrangementCode.
      @see #getLivingArrangementCode
  */
  void setLivingArrangementCode(CE livingArrangementCode);

  /**<p>The primary religious preference of a person (e.g. Hinduism, Islam, Roman Catholic Church).</p>
  */
  CE getReligiousAffiliationCode();
  /** Sets the property religiousAffiliationCode.
      @see #getReligiousAffiliationCode
  */
  void setReligiousAffiliationCode(CE religiousAffiliationCode);

  /**<p>A value representing the race of a person.</p>
  */
  SET<CE> getRaceCode();
  /** Sets the property raceCode.
      @see #getRaceCode
  */
  void setRaceCode(SET<CE> raceCode);

  /**<p>The ethnic group of the person.</p>
  */
  SET<CE> getEthnicGroupCode();
  /** Sets the property ethnicGroupCode.
      @see #getEthnicGroupCode
  */
  void setEthnicGroupCode(SET<CE> ethnicGroupCode);
}

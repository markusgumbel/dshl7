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
import org.hl7.types.ED;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of LivingSubject that includes all living things except the species homo sapiens.</p>
<p><i>Examples:</i> Cattle, birds, bacteria , plants molds and fungi, etc. 
</p>
<p><i>Rationale:</i> Living organisms other than human beings may require additional characterizing information such as genetic strain identification
   that cannot be conveyed in the Entity.code.
</p>
*/
public interface NonPersonLivingSubject extends org.hl7.rim.LivingSubject {

  /**<p>A text string representing a specific genotypic or phenotypic variant of a NonPersonLivingSubject.</p>
<p><i>Examples:</i> Minnesota5 (swine strain), DXL (poultry strain), RB51 (vaccine strain of Brucella abortus)
</p>
<p><i>Rationale:</i> There is no universal guideline for the naming or cataloging of strains. Many strain designations are created and eliminated
   over time, while some become established in various industries for a variety of reasons (vaccine production, breeding stock
   popularity, etc). However, the ability for anyone who cares to designate an organism as a "new" strain, precludes this field
   from being a coded value. Descriptive text is required to capture these designations.
</p>
  */
  ED getStrainText();
  /** Sets the property strainText.
      @see #getStrainText
  */
  void setStrainText(ED strainText);

  /**<p>A value representing whether the primary reproductive organs of NonPersonLivingSubject are present.</p>
  */
  CE getGenderStatusCode();
  /** Sets the property genderStatusCode.
      @see #getGenderStatusCode
  */
  void setGenderStatusCode(CE genderStatusCode);
}

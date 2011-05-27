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

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A Role of a LivingSubject (player) as a recipient of health care services from a healthcare provider (scoper).</p>
*/
public interface Patient extends org.hl7.rim.Role {

  /**<p>A code specifying the Patient's special status granted by the scoper organization, often resulting in preferred treatment
   and special considerations. For example, board member, diplomat, etc.
</p>
  */
  CE getVeryImportantPersonCode();
  /** Sets the property veryImportantPersonCode.
      @see #getVeryImportantPersonCode
  */
  void setVeryImportantPersonCode(CE veryImportantPersonCode);
}

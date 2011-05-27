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
import org.hl7.types.TS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A Role of an Entity (player) that is accredited with licenses or qualifications (diplomas) certifying that this Entity may
   properly perform specific functions.
</p>
<p><i>Examples:</i> 1.) A paramedical training diploma
</p>
<p>2.) The certification of equipment</p>
<p>3.) A license to a Person or Organization to provide health services.</p>
<p><i>Constraints:</i> The scoper is the Organization that issues the credential.
</p>
*/
public interface LicensedEntity extends org.hl7.rim.Role {

  /**<p>The date recertification is required.</p>
  */
  TS getRecertificationTime();
  /** Sets the property recertificationTime.
      @see #getRecertificationTime
  */
  void setRecertificationTime(TS recertificationTime);
}

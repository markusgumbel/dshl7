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

import org.hl7.rim.Act;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A dynamic list of individual instances of Act which reflect the needs of an individual worker, team of workers, or an organization
   to view groups of Acts for clinical or administrative reasons.
</p>
<p><i>Discussion:</i> The grouped Acts are related to the WorkingList via an ActRelationship of type 'COMP' (component).
</p>
<p><i>Examples:</i> Problem lists, goal lists, allergy lists, to-do lists, etc.
</p>
*/
public interface WorkingList extends org.hl7.rim.Act {

  /**<p>Indicates the category of representation for the personnel managing the list, whether person, team or organization.</p>
  */
  CE getOwnershipLevelCode();
  /** Sets the property ownershipLevelCode.
      @see #getOwnershipLevelCode
  */
  void setOwnershipLevelCode(CE ownershipLevelCode);
}

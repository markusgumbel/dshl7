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

import org.hl7.rim.InfrastructureRoot;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.TS;

import org.hl7.rim.Role;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A connection between two roles expressing a dependency between those roles.</p>
<p><i>Examples:</i> 1.) A role of assignment or agency depends on another role of employment, such that when the employment role is terminated,
   the assignments would be terminated as well. This is the dependency of the assignment role with the employment role, or in
   other words, the assignment is "part of" the employment.
</p>
<p>2.) One role has authority over another (in organizational relationships). For example, an employee of type "manager" may
   have authority over employees of type "analyst" which would be indicated by a role link for "direct authority".
</p>
<p><i>Discussion:</i> RoleLink specifies the relationships between roles, not between people (or other entities). People (or other Entities) are
   primarily related by their direct player/scoper relationships around the player's Role and more generally through their interactions
   (i.e. their participations in acts).
</p>
*/
public interface BasicRoleLink extends org.hl7.rim.InfrastructureRoot {

  /**<p>A code specifying the kind of connection represented by this RoleLink, e.g., has-part, has-authority.</p>
  */
  CS getTypeCode();
  /** Sets the property typeCode.
      @see #getTypeCode
  */
  void setTypeCode(CS typeCode);
}

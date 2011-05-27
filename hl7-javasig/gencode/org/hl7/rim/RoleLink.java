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
public interface RoleLink extends BasicRoleLink {

  /**<p>An integer specifying the relative preference for considering this relationship before other like-typed relationships having
   the same source RoleRelationships with lower priorityNumber values are considered before and above those with higher values.
</p>
<p><i>Examples:</i> For multiple backups specifies which backup is considered before others. 
</p>
<p>What is the preferred ServiceDeliveryLocation for a Physician working on behalf of a particular Health Authority?</p>
<p><i>Discussion:</i> The ordering may be a total ordering in which all priority numbers are unique or a partial ordering, which assigns the same
   priority to more than one relationship.
</p>
  */
  INT getPriorityNumber();
  /** Sets the property priorityNumber.
      @see #getPriorityNumber
  */
  void setPriorityNumber(INT priorityNumber);

  /**<p>An interval of time specifying the period during which the connection between Roles is in effect.</p>
  */
  IVL<TS> getEffectiveTime();
  /** Sets the property effectiveTime.
      @see #getEffectiveTime
  */
  void setEffectiveTime(IVL<TS> effectiveTime);

  /**
  */
  org.hl7.rim.Role getSource();
  /** Sets the property source.
      @see #getSource
  */
  void setSource(org.hl7.rim.Role source);

  /**
  */
  org.hl7.rim.Role getTarget();
  /** Sets the property target.
      @see #getTarget
  */
  void setTarget(org.hl7.rim.Role target);
}

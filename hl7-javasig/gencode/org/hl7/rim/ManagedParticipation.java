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

import org.hl7.rim.Participation;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A participation that will be operated on over time and thus whose state and identity must be managed. </p>
<p><i>Examples:</i> An attending practitioner for an inpatient encounter may change due to leave of absence and it is important to note when
   this participation will be available.
</p>
<p><i>Rationale:</i> ManagedParticipations are defined as a subclass of Participations to make explicit that not all Participations are stateful.
   In general, when the sub-activity realized by a Participation is of closer interest and needs to be managed, one SHOULD instead
   model that sub-activity as an Act component underneath the main Act.
</p>
<p>However, in certain environments the view of what these activities really are that the participants perform is not very well
   recognized and hence modeling those as sub-acts is deemed confusing or burdensome.
</p>
<p>Therefore, the ManagedParticipation extends Participation with an identity-attribute and a state-attribute to support these
   exceptional use cases. ManagedParticipations should be used with utmost caution so as to avoid confusion with Acts and to
   avoid having to duplicate the act-management infrastructure around participations.
</p>
*/
public interface ManagedParticipation extends org.hl7.rim.Participation {

  /**<p>A unique identifier used to refer to a specific instance of a Participation that may have the same Act and the same Role.
   (See ManagedParticipation.)
</p>
  */
  SET<II> getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(SET<II> id);

  /**<p>A code specifying whether the participation instance is pending, active, complete, or cancelled. (See ManagedParticipation.)</p>
<p><b>Design Advisory: </b>This attribute was defined in the original RIM as repeating, owing to the presence of nested states in the state machines.
   In actual practice, however, there is never a need to communicate more than a single status value. therefore, committees are
   advised to <b>constrain this attribute to a maximum cardinality of 1</b> in all message designs. 
</p>
  */
  CS getStatusCode();
  /** Sets the property statusCode.
      @see #getStatusCode
  */
  void setStatusCode(CS statusCode);
}

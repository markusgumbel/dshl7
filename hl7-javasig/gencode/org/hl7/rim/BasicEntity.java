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
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CE;
import org.hl7.types.PQ;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.ED;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.TEL;

import org.hl7.rim.CommunicationFunction;
import org.hl7.rim.LanguageCommunication;
import org.hl7.rim.Role;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A physical thing, group of physical things or an organization capable of participating in Acts, while in a role. </p>
<p><i>Discussion:</i> An entity is a physical object that has, had or will have existence. The only exception to this is Organization, which while
   not having a physical presence, fulfills the other characteristics of an Entity. The Entity hierarchy encompasses living subjects
   (including human beings), organizations, material, and places and their specializations. It does not indicate the roles played,
   or acts that these entities participate in.
</p>
<p><i>Constraints:</i> It does not include events/acts/actions, or the roles that things can play (e.g. patient, provider).
</p>
*/
public interface BasicEntity extends org.hl7.rim.InfrastructureRoot {

  /**<p>An HL7 defined value representing the class or category that the Entity instance represents.</p>
<p><i>Examples:</i> Person, Animal, Chemical Substance, Group, Organization
</p>
<p><i>Rationale:</i> Due to the extremely large number of potential values for a code set representing all physical things in the universe, the
   class code indicates both the subtype branch of the Entity hierarchy used as well as a high level classifier to represent
   the instance of Entity. This can be used to constrain the eligible value domains for the Entity.code attribute.
</p>
  */
  CS getClassCode();
  /** Sets the property classCode.
      @see #getClassCode
  */
  void setClassCode(CS classCode);

  /**<p>An HL7 defined value representing whether the Entity represents a kind-of or a specific instance.</p>
<p><i>Examples:</i> 1 human being (an instance), 3 syringes (quantified kind) or the population of Indianapolis (kind of group)
</p>
<p><i>Rationale:</i> An Entity may at times represent information concerning a specific instance (the most common), a quantifiable group with
   common characteristics or a general type of Entity. This code distinguishes these different representations.
</p>
  */
  CS getDeterminerCode();
  /** Sets the property determinerCode.
      @see #getDeterminerCode
  */
  void setDeterminerCode(CS determinerCode);
}

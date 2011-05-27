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
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of Entity that is inanimate and locationally independent. </p>
<p><i>Examples:</i> Pharmaceutical substances (including active vaccines containing retarded virus), disposable supplies, durable equipment,
   implantable devices, food items (including meat or plant products), waste, traded goods, etc.
</p>
<p><i>Discussion:</i> Manufactured or processed products are considered material, even if they originate as living matter. Materials come in a
   wide variety of physical forms and can pass through different states (ie. Gas, liquid, solid) while still retaining their
   physical composition and material characteristics.
</p>
<p><i>Rationale:</i> There are entities that have attributes in addition to the Entity class, yet cannot be classified as either LivingSubject
   or Place.
</p>
*/
public interface Material extends org.hl7.rim.Entity {

  /**<p>A value representing the state (solid, liquid, gas) and nature of the material. </p>
<p><i>Examples:</i> For therapeutic substances, the dose form, such as tablet, ointment, gel, etc.
</p>
  */
  CE getFormCode();
  /** Sets the property formCode.
      @see #getFormCode
  */
  void setFormCode(CE formCode);
}

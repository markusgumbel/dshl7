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

import org.hl7.rim.Material;
import org.hl7.types.ST;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.TS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of Material representing an Entity or combination of Entities transformed for a particular purpose by a non-natural
   or manufacturing process. 
</p>
<p><i>Examples:</i> Processed food products, disposable syringes, chemistry analyzer, saline for infusion, etc.
</p>
<p><i>Discussion:</i> This class encompasses containers, devices, software modules and facilities.
</p>
<p><i>Rationale:</i> This class is used to further define the characteristics of Entities that are created out of other Entities. These entities
   are identified and tracked through associations and mechanisms unique to the class, such as lotName, stabilityTime and expirationTime.
</p>
*/
public interface ManufacturedMaterial extends org.hl7.rim.Material {

  /**<p>An alphanumeric string used to identify a particular batch of manufactured material. </p>
<p><i>Discussion:</i> The lot name is usually printed on the label attached to the container holding the substance and/or on the packaging which
   houses the container. Note that a lot number is not meant to be a unique identifier, but is meaningful only when the product
   kind and manufacturer are also identified.
</p>
  */
  ST getLotNumberText();
  /** Sets the property lotNumberText.
      @see #getLotNumberText
  */
  void setLotNumberText(ST lotNumberText);

  /**<p>The date and time the manufacturer no longer ensures the safety, quality, and/or proper functioning of the material.</p>
<p><i>Rationale:</i> There is a need in many situations that the materials used are of a specific quality or potency or functional status. The
   ending date for this guarantee is specified by the manufacturer. After that date, while the material may still provide the
   same characteristics, the manufacturer no longer takes responsibility that the product will perform as specified and denies
   responsibility for failure of the material after that date.
</p>
  */
  IVL<TS> getExpirationTime();
  /** Sets the property expirationTime.
      @see #getExpirationTime
  */
  void setExpirationTime(IVL<TS> expirationTime);

  /**<p>The time at which the material is considered useable after it is activated.</p>
<p><i>Examples:</i> After opening a bottle of a liquid. The mixing of two chemicals for an analysis that must be mixed and used within two hours
   or their activity diminishes. 
</p>
<p><i>Discussion:</i> If a kind of material is described (determinerCode = KIND) only the width of that interval can be known, i.e., the duration
   after opening the reagent container at which the reagent substance is considered useable for its normal testing purpose. For
   an actual instance of the reagent (e.g., a specific bottle), the stabilityTime.low TS marks the time at which the reagent
   bottle has been opened (or the reagent was otherwise activated). Together with the typical stability duration, this determines
   the stabilityTime.high TS beyond which the reagent is no longer considered useable for its normal testing purpose.
</p>
  */
  IVL<TS> getStabilityTime();
  /** Sets the property stabilityTime.
      @see #getStabilityTime
  */
  void setStabilityTime(IVL<TS> stabilityTime);
}

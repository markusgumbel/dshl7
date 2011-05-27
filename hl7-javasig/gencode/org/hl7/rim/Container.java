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

import org.hl7.rim.ManufacturedMaterial;
import org.hl7.types.PQ;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of ManufacturedMaterial used to hold other Entities for purposes such as transportation or protection of contents
   from loss or damage. 
</p>
<p><i>Rationale:</i> The specifications for this class arose from the collaboration between HL7 and the NCCLS. Many of the attribute definitions
   are drawn from or reference the NCCLS standard. With amorphic substances (liquids, gases) a container is required. However,
   the content of a container is always distinguishable and relatively easily separable from the container, unlike the content
   (ingredient) of a mixture.
</p>
<p><i>Usage:</i> A container is related to a content material through Role.classCode = CONT (content).
</p>
*/
public interface Container extends org.hl7.rim.ManufacturedMaterial {

  /**<p>The functional capacity of the container.</p>
  */
  PQ getCapacityQuantity();
  /** Sets the property capacityQuantity.
      @see #getCapacityQuantity
  */
  void setCapacityQuantity(PQ capacityQuantity);

  /**<p>The height of the container.</p>
  */
  PQ getHeightQuantity();
  /** Sets the property heightQuantity.
      @see #getHeightQuantity
  */
  void setHeightQuantity(PQ heightQuantity);

  /**<p>The outside diameter of the container.</p>
  */
  PQ getDiameterQuantity();
  /** Sets the property diameterQuantity.
      @see #getDiameterQuantity
  */
  void setDiameterQuantity(PQ diameterQuantity);

  /**<p>The type of container cap consistent with decapping, piercing or other automated manipulation.</p>
  */
  CE getCapTypeCode();
  /** Sets the property capTypeCode.
      @see #getCapTypeCode
  */
  void setCapTypeCode(CE capTypeCode);

  /**<p>A material added to a container to facilitate and create a physical separation of specimen components of differing density.</p>
<p><i>Examples:</i> A gel material added to blood collection tubes that following centrifugation creates a physical barrier between the blood
   cells and the serum or plasma.
</p>
<p><i>Rationale:</i> The composition or nature of the separator material may have an effect on the analysis. Knowledge of the material aids interpretation
   of results.
</p>
  */
  CE getSeparatorTypeCode();
  /** Sets the property separatorTypeCode.
      @see #getSeparatorTypeCode
  */
  void setSeparatorTypeCode(CE separatorTypeCode);

  /**<p>The distance from the Point of Reference to the separator material (barrier) within a container. </p>
<p><i>Rationale:</i> This distance may be provided by a laboratory automation system to an instrument and/or specimen processing/handling device
   to facilitate the insertion of a sampling probe into the specimen without touching the separator. See the Point of Reference
   definition or in NCCLS standard AUTO5 Laboratory Automation: Electromechanical Interfaces.
</p>
  */
  PQ getBarrierDeltaQuantity();
  /** Sets the property barrierDeltaQuantity.
      @see #getBarrierDeltaQuantity
  */
  void setBarrierDeltaQuantity(PQ barrierDeltaQuantity);

  /**<p>The distance from the Point of Reference to the outside bottom of the container. </p>
<p><i>Rationale:</i> Refer to Point of Reference in NCCLS standard AUTO5 Laboratory Automation: Electromechanical Interfaces.
</p>
  */
  PQ getBottomDeltaQuantity();
  /** Sets the property bottomDeltaQuantity.
      @see #getBottomDeltaQuantity
  */
  void setBottomDeltaQuantity(PQ bottomDeltaQuantity);
}

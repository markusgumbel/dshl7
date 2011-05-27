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

import org.hl7.rim.Supply;
import org.hl7.types.PQ;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A supply act dealing specifically with the feeding or nourishment of a subject.</p>
<p><i>Discussion:</i> The detail of the diet is given as a description of the Material associated via Participation.typeCode="product". Medically
   relevant diet types may be communicated in the Diet.code, however, the detail of the food supplied and the various combinations
   of dishes should be communicated as Material instances.
</p>
<p><i>Examples:</i> Gluten free; Low sodium
</p>
*/
public interface Diet extends org.hl7.rim.Supply {

  /**<p>The supplied biologic energy (Calories) per day.</p>
<p><i>Discussion:</i> This physical quantity should be convertible to 1 kcal/d (or 1 kJ/d). Note, avoid the existing confusion between "large Calorie"
   and a "small calorie." Nutrition labels on food products list "large Calories." It is more appropriate to use the small calorie,
   which is 1/1000 of a large Calorie. These are clearly distinguished in the HL7 units of measure tables.
</p>
  */
  PQ getEnergyQuantity();
  /** Sets the property energyQuantity.
      @see #getEnergyQuantity
  */
  void setEnergyQuantity(PQ energyQuantity);

  /**<p>The supplied amount of carbohydrates (g) per day.</p>
<p><i>Discussion:</i> For a diabetes diet one typically restricts the amount of metabolized carbohydrates to a certain amount per day (e.g., 240
   g/d). This restriction can be communicated in the carbohydrateQuantity.
</p>
  */
  PQ getCarbohydrateQuantity();
  /** Sets the property carbohydrateQuantity.
      @see #getCarbohydrateQuantity
  */
  void setCarbohydrateQuantity(PQ carbohydrateQuantity);
}

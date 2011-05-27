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
import org.hl7.types.PQ;
import org.hl7.types.IVL;
import org.hl7.types.TS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An act that involves provision of a material by one entity to another.</p>
<p><i>Discussion:</i> The product is associated with the Supply Act via Participation.typeCode="product". With general Supply Acts, the precise
   identification of the Material (manufacturer, serial numbers, etc.) is important. Most of the detailed information about the
   Supply should be represented using the Material class. If delivery needs to be scheduled, tracked, and billed separately,
   one can associate a Transportation Act with the Supply Act. Pharmacy dispense services are represented as Supply Acts, associated
   with a SubstanceAdministration Act. The SubstanceAdministration class represents the administration of medication, while dispensing
   is supply.
</p>
<p><i>Examples:</i> Ordering bed sheets; Dispensing of a drug; Issuing medical supplies from storage
</p>
*/
public interface Supply extends org.hl7.rim.Act {

  /**<p>The amount that was or is to be supplied (depending on the moodCode)</p>
<p><i>Discussion:</i> This attribute may be used as an alternative to expectedUseTime or both may be used. If both are specified, then the specified
   quantity is the amount expected to be consumed within the expectedUseTime.
</p>
<p>The unit of measure is restricted to a measured unit such as milliliter and milligram. Non-measured, but countable units such
   as tablet and capsule must not be specified using the unit component of the PQ data type, except as an annotation, marked
   by {xxx}. Refer to Data Types Part II Unabridged Specification, Appendix A: Unified Code for Units of Measure. The type of
   'countable' information is determined by information in the 'product' entity.
</p>
  */
  PQ getQuantity();
  /** Sets the property quantity.
      @see #getQuantity
  */
  void setQuantity(PQ quantity);

  /**<p>Identifies the period time over which the supplied product is expected to be used, or the length of time the supply is expected
   to last.
</p>
<p>In some situations, this attribute may be used instead of Supply.quantity to identify the amount supplied by how long it is
   expected to last, rather than the physical quantity issued. E.g. 90 days supply of medication (based on an ordered dosage),
   10 hours of jet fuel, etc. NOTE: When possible, it is always better to specify Supply.quantity, as this tends to be more precise.
   Supply.expectedUseTime will always be an estimate that can be influenced by external factors.
</p>
  */
  IVL<TS> getExpectedUseTime();
  /** Sets the property expectedUseTime.
      @see #getExpectedUseTime
  */
  void setExpectedUseTime(IVL<TS> expectedUseTime);
}

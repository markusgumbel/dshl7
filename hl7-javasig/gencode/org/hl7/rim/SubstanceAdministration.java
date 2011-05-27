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
import org.hl7.types.SET;
import org.hl7.types.CD;
import org.hl7.types.IVL;
import org.hl7.types.PQ;
import org.hl7.types.PQ;
import org.hl7.types.RTO;
import org.hl7.types.RTO;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>The act of introducing or otherwise applying a substance to the subject.</p>
<p><i>Discussion:</i> The effect of the substance is typically established on a biochemical basis, however, that is not a requirement. For example,
   radiotherapy can largely be described in the same way, especially if it is a systemic therapy such as radio-iodine. This class
   also includes the application of chemical treatments to an area.
</p>
<p><i>Examples:</i> Chemotherapy protocol; Drug prescription; Vaccination record
</p>
*/
public interface SubstanceAdministration extends org.hl7.rim.Act {

  /**<p>The method of introducing the therapeutic material into or onto the subject.</p>
<p><i>Discussion:</i> Route, site of administration (administrationSiteCode) and the device used in administration are closely related. All three
   (if present) must be closely coordinated and in agreement. In some cases, the coding system used to specify one may pre-coordinate
   one or more of the others.
</p>
<p>When the medication is delivered to an environmental site, or a location, the route code indicates a site on its "body".</p>
<p><i>Examples:</i> per os (PO), sublingual (SL), rectal (PR), per inhalationem (IH), ophtalmic (OP), nasal (NS), otic (OT), vaginal (VG), intra-dermal
   (ID), subcutaneous (SC), intra-venous (IV), and intra-cardial (IC)
</p>
  */
  CE getRouteCode();
  /** Sets the property routeCode.
      @see #getRouteCode
  */
  void setRouteCode(CE routeCode);

  /**<p>The detailed anatomical site where the medication enters or is applied to the subject.</p>
<p><i>Discussion:</i> This attribute is only needed if the routeCode requires further specification. For example, if the routeCode is "by mouth",
   no further specification of approach site is needed. If, however, routeCode is intravenous or intra-muscular, the precise
   site may be specified in this attribute (e.g., right forearm or left deltoid muscle respectively).
</p>
<p>Route, site of administration (approachSiteCode) and the device used in administration are closely related. All three (if
   present) must be closely coordinated and in agreement. In some cases, the coding system used to specify one may pre-coordinate
   one or more of the others.
</p>
  */
  SET<CD> getApproachSiteCode();
  /** Sets the property approachSiteCode.
      @see #getApproachSiteCode
  */
  void setApproachSiteCode(SET<CD> approachSiteCode);

  /**<p>The amount of the therapeutic agent or other substance given at one administration event.</p>
<p><i>Discussion:</i> The dose may be specified either as a physical quantity of active ingredient (e.g. 200 mg) or as the count of administration-units
   (e.g., tablets, capsules, "eaches"). Which approach is chosen depends upon the player of the 'consumable' participation (which
   identifies the drug being administered). If the consumable has a non-countable dosage form (e.g. measured in milligram or
   litre) then the dose must be expressed in those units. If the consumable has a countable dosage form (tablets, capsules, "eaches"),
   then the dose must be expressed as a dimensionless count (i.e., with no other unit of measure specified).
</p>
<p>The unit of measure is restricted to a measurable unit such as milliliters and milligrams. Non-measurable, but countable units
   such as tablets and capsules must not be specified using the unit component of the PQ data type, except as an annotation,
   marked by {xxx}. Refer to Data Types Part II Unabridged Specification, Appendix A :Unified Code for Units of Measure.
</p>
  */
  IVL<PQ> getDoseQuantity();
  /** Sets the property doseQuantity.
      @see #getDoseQuantity
  */
  void setDoseQuantity(IVL<PQ> doseQuantity);

  /**<p>Identifies the speed with which the substance is introduced into the subject. Expressed as a physical (extensive) quantity
   over elapsed time (e.g., examples are 100 mL/h, 1 g/d, 40 mmol/h, etc.)
</p>
<p><i>Discussion:</i> This is appropriate for continuously divisible dose forms (e.g., liquids, gases). If specified as an interval, the rate should
   be anywhere in the specified range. This attribute is specified as a extensive physical quantity over elapsed time, i.e.,
   a quantity that could be used for the doseQuantity divided by a duration quantity.
</p>
  */
  IVL<PQ> getRateQuantity();
  /** Sets the property rateQuantity.
      @see #getRateQuantity
  */
  void setRateQuantity(IVL<PQ> rateQuantity);

  /**<p>This attribute identifies the expected quantity to be consumed over a period of time. It is used as a verification check on
   the values specified for other values.
</p>
<p><i>Discussion:</i> This attribute should not generally be used; it is only provided for a special purpose. In some countries, especially Japan,
   there is a regulatory requirement to note the total daily dose on the prescription and associated documentation. The purpose
   of this requirement obviously is to encourage and facilitate reviewing the total dose prescribed to avoid over- (or under-)
   dosage.
</p>
<p><i>Examples:</i></p>
<p>With Erythromycin 250 mg 1 tablet 3 times a day one can calculate the total daily dose as "doseCheckQuantity = doseQuantity
   (1) * Ingredient.quantity (250 mg) * effectiveTime (3 /d) = 750 mg/1d."
</p>
<p>For an intravenous example, this term would be "doseCheckQuantity = doseQuantity (100 ml) * Ingredient.quantity (5mg/L) /
   rateQuantity (1 h) = 0.5 mg/h" which can be calculated on a daily basis as "doseCheckQuantity = 0.5 mg/h * 24 h/d = 12 mg/d."
</p>
<p><i>Rationale:</i> Rather than defining a "total daily dose" attribute as HL7 v2.3 did, we define this general purpose doseCheckQuantity attribute
   of the Ratio (RTO) data type. 
</p>
<p><i>Constraints:</i> invariant(SubstanceAdministration med, RTO max) where med.doseCheckQuantity.contains(max) {max.numerator.compares(med.doseQuantity);
   max.denominator.compares(1 s);} Numerator must be in units comparable to doseQuantity and denominator must be a measurement
   of time.
</p>
  */
  SET<RTO> getDoseCheckQuantity();
  /** Sets the property doseCheckQuantity.
      @see #getDoseCheckQuantity
  */
  void setDoseCheckQuantity(SET<RTO> doseCheckQuantity);

  /**<p>Identifies the maximum total quantity of a therapeutic substance that may be administered to a subject over the period of
   time.
</p>
<p><i>Discussion:</i> This attribute is particularly useful where the allowed dosage is specified as a range or the timing is variable or PRN (as
   needed). It provides an overall limit on the quantity that may be administered in a period of time. Multiple occurrences of
   maxDoseQuantity may be used to indicate different limits over different time periods.
</p>
<p><i>Examples:</i> 500 mg/day; 1200mg/week.
</p>
<p><i>Constraints:</i> invariant(SubstanceAdministration med, RTO max) where med.maxDoseQuantity.contains(max) {max.numerator.compares(med.doseQuantity);
   max.denominator.compares(1 s);} Numerator must be in units comparable to doseQuantity and denominator must be a measurement
   of time.
</p>
  */
  SET<RTO> getMaxDoseQuantity();
  /** Sets the property maxDoseQuantity.
      @see #getMaxDoseQuantity
  */
  void setMaxDoseQuantity(SET<RTO> maxDoseQuantity);

  /**<p>A code for the administered thing taken from a larger whole. Used if the consumable material is specified as a larger whole
   but the doseQuantity relates to a specific portion of it rather than the entire consumable material.
</p>
<p><i>Example: </i>The ordering system only has a code for "Budesonide Metered Dose Inhaler" but the dose is to be measured in "number of actuations".
</p>
<p><i>Rationale: </i>In the given example, without an administrationUnitCode the doseQty = 1 would mean that the entire inhaler bottle is to be
   emptied upon a single administration event. The administrationUnitCode signifying "actuation" (or "puff") specifies that the
   doseQty relates to this fraction of the medication rather than to the whole.
</p>
<p><b>Constraints:</b></p>
<p>(1) This attribute should be used if and only if the material specified as the player of the Role attaching to the consumable
   participation is not in itself the finished dose form to be administered but a larger whole, pack, etc.
</p>
<p>(2) IF the material so specified is the proper administered dose form, such as a tablet, capsule, etc. THEN this attribute
   should be valued NULL (not applicable).
</p>
<p>(3) IF the material so specified is an amorphous substance (liquid, gas, powder, etc.) to be measured as a volume, mass, etc.,
   THEN this attribute should remain NULL (not applicable).
</p>
<p>(4) IF the material so specified is a container, and the content is to be measured as a volume, mass, etc., THEN this attribute
   should be specified as "measured portion".
</p>
  */
  CE getAdministrationUnitCode();
  /** Sets the property administrationUnitCode.
      @see #getAdministrationUnitCode
  */
  void setAdministrationUnitCode(CE administrationUnitCode);
}

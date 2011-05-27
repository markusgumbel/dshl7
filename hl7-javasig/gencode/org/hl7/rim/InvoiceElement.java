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
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.PQ;
import org.hl7.types.PQ;
import org.hl7.types.MO;
import org.hl7.types.PQ;
import org.hl7.types.REAL;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An Act representing a statement and justification of an "amount owed".</p>
<p><i>Discussion:</i> This represents the 'justification' portion of an invoice. It is frequently combined with a financial transaction representing
   the amount requested to be paid, agreed to be paid, or actually paid.
</p>
<p>A recursive relationship can be used to break a single InvoiceElement into constituent elements.</p>
<p>In definition mood, it represents "possible" justification for future billing. In request mood, it is a request to determine
   the amount owed. In event mood, this class represents the determination of a specific amount owed by a particular Entity.
</p>
*/
public interface InvoiceElement extends org.hl7.rim.Act {

  /**<p>Designates a modifier to the code attribute to provide additional information about the invoice element.</p>
<p><i>Examples:</i> Isolation allowance; After-hours service
</p>
<p><i>Rationale:</i> This is not pre-coordinated into the CD attribute because the modifier code set may not be specifically designed for use
   with the Act.code code set. This violates the constraint for using the 'modifier' property that the modifier code set must
   be defined as part of, or specifically for the base code set.
</p>
  */
  SET<CE> getModifierCode();
  /** Sets the property modifierCode.
      @see #getModifierCode
  */
  void setModifierCode(SET<CE> modifierCode);

  /**<p>A description of the number of instances of a product or service that is being billed or charged for.</p>
<p><i>Examples:</i> 4 hours, 4 mg, 4 boxes, and 15 each of a container of 1000 each, etc.
</p>
<p><i>Discussion:</i> Each InvoiceElement that is being charged or billed is identified by a charge or bill code (InvoiceElement.code). In some
   situations, this code is a pre-coordinated code set and represents a container (e.g. UPC code for a container of 1000 pills
   and another UPC code for a container of the same pills but in a container of 100). The UPC code is used in invoicing, but
   ratios are required to specify that only a portion of the container (e.g. bottle) is being billed or charged. If the InvoiceElement
   does not reference a container, then the denominator is not specified.
</p>
<p>For example, 15 pills in a container size of 1000 pills. In this case, the numerator can be expressed as "15 {pill}" or simply
   "15" and the denominator can be expressed as "1000 {bottle}" or simply "1000" (see discussion following for rationale of using
   descriptive text for countable units).
</p>
<p><i>Constraints:</i></p>
<p>The unit of measure is restricted to a measurable unit such as liters, milligrams and hours. Non-measurable, but countable
   units such as boxes, packages, visits, pills and containers must not be specified using the unit component of the PQ data
   type, except as an annotation, marked by {xxx}. Refer to Data Types Part II Unabridged Specification, Appendix A :Unified
   Code for Units of Measure.
</p>
<p>Specification of countable units can be handled with the following techniques:</p>
<p>(1) specify the countable unit in the InvoiceElement.code. That is, a specific InvoiceElement.code would indicate that the
   item referenced by the act represents a box of 20 items. There would be a different InvoiceElement.code for a box of 40 items,
   and so on.
</p>
<p>For example, if the InvoiceElement.code represents a box of 20 items, and the InvoiceElement.unitQuantity = 2 (no units),
   then this represents 2 boxes of 20 items for a total of 40 items.
</p>
<p>(2) If more detail is required (e.g. to describe the composition, packaging, manufacturer of a product), then use a participation
   (typeCode = "PRD"), and a combination of role and entity classes to describe the details of the packaging.
</p>
  */
  RTO getUnitQuantity();
  /** Sets the property unitQuantity.
      @see #getUnitQuantity
  */
  void setUnitQuantity(RTO unitQuantity);

  /**<p>The monetary cost per unit being accounted.</p>
<p><i>Constraints:</i> In constructing the ratio, the numerator must be of data type MO, and the denominator must be a PQ, specified in the same
   manner as the unitQuantity attribute.
</p>
<p><i>Examples:</i> $0.20/mg; $250/day; $50
</p>
  */
  RTO getUnitPriceAmt();
  /** Sets the property unitPriceAmt.
      @see #getUnitPriceAmt
  */
  void setUnitPriceAmt(RTO unitPriceAmt);

  /**<p>Identifies the total monetary amount for the invoice element, including the sum of any component elements.</p>
<p><i>Discussion:</i> For leaf-level amounts, this will be the value of the unitQuantity * unitPriceAmt [ * factorNumber] [* pointsNumber]. For
   grouping invoice elements, this will be the sum of the netAmt attributes of all contained InvoiceElements.
</p>
  */
  MO getNetAmt();
  /** Sets the property netAmt.
      @see #getNetAmt
  */
  void setNetAmt(MO netAmt);

  /**<p>Represents a multiplier used in determining the overall cost of services delivered and/or goods received.</p>
<p><i>Examples:</i> this could be 10 (Number of Treatments as Units) * $3.00 (Cost per Unit) * 1.5 (Factor) = $45.00 (Amount).
</p>
<p><i>Discussion:</i> This concept is frequently used in Europe to adjust the charge between that used for the public system and that used for
   private insurers.
</p>
<p>The simplest formula for deriving gross amounts is: unitQuantity * unitPriceAmount = netAmt.</p>
<p>The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount. For example, the
   formula, with a factor would be: unitQuantity * unitPrice (Cost/Point) * factorNumber = netAmt
</p>
<p>See related note on Points. Formula, with Points and Factors becomes: unitQuantity * unitPriceAmt * pointsNumber * factorNumber
   = netAmt
</p>
  */
  REAL getFactorNumber();
  /** Sets the property factorNumber.
      @see #getFactorNumber
  */
  void setFactorNumber(REAL factorNumber);

  /**<p>For charges whose quantity is expressed in 'points', this expresses the weighting (based on difficulty, cost and/or resource
   intensiveness) associated with the good or service delivered.
</p>
<p><i>Examples:</i> This could be 5 (Number of Treatments as Units) * 3 (Number of Points per treatment as Points)* $20.00 (Cost per Point) =
   $300.00 (Amount).
</p>
<p><i>Discussion:</i> This is commonly used in systems where services provided are assigned a relative 'cost or difficulty rating', and then a
   fixed price is assigned to a 'point'. Adjustments to all prices charged by an organization can then be handled by increasing
   or decreasing the cost per point to reflect changes in inflation, overhead, etc.
</p>
<p>The simplest formula for deriving gross amounts is: unitQuantity * unitPriceAmount = netAmt.</p>
<p>The concept of Points allows for assignment of point values for services and/or goods, such that a dollar amount can be assigned
   to each point. For example, the formula, with points would be: unitQuantity * pointsNumber * unitPriceAmt (Cost/Point) = netAmt.
</p>
<p>See related note on Factor. Formula, with Points and Factors becomes: unitQuantity * unitPriceAmt * pointsNumber * factorNumber
   = netAmt.
</p>
  */
  REAL getPointsNumber();
  /** Sets the property pointsNumber.
      @see #getPointsNumber
  */
  void setPointsNumber(REAL pointsNumber);
}

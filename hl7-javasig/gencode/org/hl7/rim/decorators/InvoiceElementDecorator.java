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
package org.hl7.rim.decorators;

import org.hl7.rim.InvoiceElement;
import org.hl7.rim.decorators.ActDecorator;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.PQ;
import org.hl7.types.PQ;
import org.hl7.types.MO;
import org.hl7.types.PQ;
import org.hl7.types.REAL;

import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.RTOnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.MOnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.REALnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.InvoiceElement as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.InvoiceElement
  */
public abstract class InvoiceElementDecorator extends org.hl7.rim.decorators.ActDecorator implements InvoiceElement {
  /** Property accessor, returns NULL/NA if not overloaded.modifierCode.
      @see org.hl7.rim.InvoiceElement#getModifierCode
  */
  public SET<CE> getModifierCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.modifierCode.
      @see org.hl7.rim.InvoiceElement#setModifierCode
  */
  public void setModifierCode(SET<CE> modifierCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.unitQuantity.
      @see org.hl7.rim.InvoiceElement#getUnitQuantity
  */
  public RTO getUnitQuantity() { return RTOnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.unitQuantity.
      @see org.hl7.rim.InvoiceElement#setUnitQuantity
  */
  public void setUnitQuantity(RTO unitQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.unitPriceAmt.
      @see org.hl7.rim.InvoiceElement#getUnitPriceAmt
  */
  public RTO getUnitPriceAmt() { return RTOnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.unitPriceAmt.
      @see org.hl7.rim.InvoiceElement#setUnitPriceAmt
  */
  public void setUnitPriceAmt(RTO unitPriceAmt) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.netAmt.
      @see org.hl7.rim.InvoiceElement#getNetAmt
  */
  public MO getNetAmt() { return MOnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.netAmt.
      @see org.hl7.rim.InvoiceElement#setNetAmt
  */
  public void setNetAmt(MO netAmt) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.factorNumber.
      @see org.hl7.rim.InvoiceElement#getFactorNumber
  */
  public REAL getFactorNumber() { return REALnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.factorNumber.
      @see org.hl7.rim.InvoiceElement#setFactorNumber
  */
  public void setFactorNumber(REAL factorNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.pointsNumber.
      @see org.hl7.rim.InvoiceElement#getPointsNumber
  */
  public REAL getPointsNumber() { return REALnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.pointsNumber.
      @see org.hl7.rim.InvoiceElement#setPointsNumber
  */
  public void setPointsNumber(REAL pointsNumber) { /*throw new UnsupportedOperationException();*/ }
}

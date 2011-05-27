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

import org.hl7.rim.Container;
import org.hl7.rim.decorators.ManufacturedMaterialDecorator;
import org.hl7.types.PQ;
import org.hl7.types.CE;

import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.CEnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Container as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Container
  */
public abstract class ContainerDecorator extends org.hl7.rim.decorators.ManufacturedMaterialDecorator implements Container {
  /** Property accessor, returns NULL/NA if not overloaded.capacityQuantity.
      @see org.hl7.rim.Container#getCapacityQuantity
  */
  public PQ getCapacityQuantity() { return PQnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.capacityQuantity.
      @see org.hl7.rim.Container#setCapacityQuantity
  */
  public void setCapacityQuantity(PQ capacityQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.heightQuantity.
      @see org.hl7.rim.Container#getHeightQuantity
  */
  public PQ getHeightQuantity() { return PQnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.heightQuantity.
      @see org.hl7.rim.Container#setHeightQuantity
  */
  public void setHeightQuantity(PQ heightQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.diameterQuantity.
      @see org.hl7.rim.Container#getDiameterQuantity
  */
  public PQ getDiameterQuantity() { return PQnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.diameterQuantity.
      @see org.hl7.rim.Container#setDiameterQuantity
  */
  public void setDiameterQuantity(PQ diameterQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.capTypeCode.
      @see org.hl7.rim.Container#getCapTypeCode
  */
  public CE getCapTypeCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.capTypeCode.
      @see org.hl7.rim.Container#setCapTypeCode
  */
  public void setCapTypeCode(CE capTypeCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.separatorTypeCode.
      @see org.hl7.rim.Container#getSeparatorTypeCode
  */
  public CE getSeparatorTypeCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.separatorTypeCode.
      @see org.hl7.rim.Container#setSeparatorTypeCode
  */
  public void setSeparatorTypeCode(CE separatorTypeCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.barrierDeltaQuantity.
      @see org.hl7.rim.Container#getBarrierDeltaQuantity
  */
  public PQ getBarrierDeltaQuantity() { return PQnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.barrierDeltaQuantity.
      @see org.hl7.rim.Container#setBarrierDeltaQuantity
  */
  public void setBarrierDeltaQuantity(PQ barrierDeltaQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.bottomDeltaQuantity.
      @see org.hl7.rim.Container#getBottomDeltaQuantity
  */
  public PQ getBottomDeltaQuantity() { return PQnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.bottomDeltaQuantity.
      @see org.hl7.rim.Container#setBottomDeltaQuantity
  */
  public void setBottomDeltaQuantity(PQ bottomDeltaQuantity) { /*throw new UnsupportedOperationException();*/ }
}

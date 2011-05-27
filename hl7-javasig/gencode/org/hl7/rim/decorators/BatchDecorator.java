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

import org.hl7.rim.Batch;
import org.hl7.rim.decorators.TransmissionDecorator;
import org.hl7.types.II;
import org.hl7.types.SC;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.INT;
import org.hl7.types.INT;

import org.hl7.rim.Transmission;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.SCnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.INTnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Batch as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Batch
  */
public abstract class BatchDecorator extends org.hl7.rim.decorators.TransmissionDecorator implements Batch {
  /** Property accessor, returns NULL/NA if not overloaded.referenceControlId.
      @see org.hl7.rim.Batch#getReferenceControlId
  */
  public II getReferenceControlId() { return IInull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.referenceControlId.
      @see org.hl7.rim.Batch#setReferenceControlId
  */
  public void setReferenceControlId(II referenceControlId) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.name.
      @see org.hl7.rim.Batch#getName
  */
  public SC getName() { return SCnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.name.
      @see org.hl7.rim.Batch#setName
  */
  public void setName(SC name) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.batchComment.
      @see org.hl7.rim.Batch#getBatchComment
  */
  public SET<ST> getBatchComment() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.batchComment.
      @see org.hl7.rim.Batch#setBatchComment
  */
  public void setBatchComment(SET<ST> batchComment) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.transmissionQuantity.
      @see org.hl7.rim.Batch#getTransmissionQuantity
  */
  public INT getTransmissionQuantity() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.transmissionQuantity.
      @see org.hl7.rim.Batch#setTransmissionQuantity
  */
  public void setTransmissionQuantity(INT transmissionQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.batchTotalNumber.
      @see org.hl7.rim.Batch#getBatchTotalNumber
  */
  public SET<INT> getBatchTotalNumber() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.batchTotalNumber.
      @see org.hl7.rim.Batch#setBatchTotalNumber
  */
  public void setBatchTotalNumber(SET<INT> batchTotalNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.transmission.
      @see org.hl7.rim.Batch#getTransmission
  */
  public /*AssociationSet*/List<org.hl7.rim.Transmission> getTransmission() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.transmission.
      @see org.hl7.rim.Batch#setTransmission
  */
  public void setTransmission(/*AssociationSet*/List<org.hl7.rim.Transmission> transmission) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded transmission.
      @see org.hl7.rim.Batch#setTransmission
  */
  public void addTransmission(org.hl7.rim.Transmission transmission) { throw new UnsupportedOperationException(); }
}

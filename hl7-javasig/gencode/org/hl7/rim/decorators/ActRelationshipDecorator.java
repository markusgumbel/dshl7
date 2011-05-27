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

import org.hl7.rim.ActRelationship;
import org.hl7.types.CS;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.PQ;
import org.hl7.types.ST;
import org.hl7.types.CE;

import org.hl7.rim.Act;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.CEnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.ActRelationship as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.ActRelationship
  */
public abstract class ActRelationshipDecorator extends BasicActRelationshipDecorator implements ActRelationship {
  /** Property accessor, returns NULL/NA if not overloaded.inversionInd.
      @see org.hl7.rim.ActRelationship#getInversionInd
  */
  public BL getInversionInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.inversionInd.
      @see org.hl7.rim.ActRelationship#setInversionInd
  */
  public void setInversionInd(BL inversionInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.contextControlCode.
      @see org.hl7.rim.ActRelationship#getContextControlCode
  */
  public CS getContextControlCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.contextControlCode.
      @see org.hl7.rim.ActRelationship#setContextControlCode
  */
  public void setContextControlCode(CS contextControlCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.contextConductionInd.
      @see org.hl7.rim.ActRelationship#getContextConductionInd
  */
  public BL getContextConductionInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.contextConductionInd.
      @see org.hl7.rim.ActRelationship#setContextConductionInd
  */
  public void setContextConductionInd(BL contextConductionInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.sequenceNumber.
      @see org.hl7.rim.ActRelationship#getSequenceNumber
  */
  public INT getSequenceNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.sequenceNumber.
      @see org.hl7.rim.ActRelationship#setSequenceNumber
  */
  public void setSequenceNumber(INT sequenceNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.priorityNumber.
      @see org.hl7.rim.ActRelationship#getPriorityNumber
  */
  public INT getPriorityNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.priorityNumber.
      @see org.hl7.rim.ActRelationship#setPriorityNumber
  */
  public void setPriorityNumber(INT priorityNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.pauseQuantity.
      @see org.hl7.rim.ActRelationship#getPauseQuantity
  */
  public PQ getPauseQuantity() { return PQnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.pauseQuantity.
      @see org.hl7.rim.ActRelationship#setPauseQuantity
  */
  public void setPauseQuantity(PQ pauseQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.checkpointCode.
      @see org.hl7.rim.ActRelationship#getCheckpointCode
  */
  public CS getCheckpointCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.checkpointCode.
      @see org.hl7.rim.ActRelationship#setCheckpointCode
  */
  public void setCheckpointCode(CS checkpointCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.splitCode.
      @see org.hl7.rim.ActRelationship#getSplitCode
  */
  public CS getSplitCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.splitCode.
      @see org.hl7.rim.ActRelationship#setSplitCode
  */
  public void setSplitCode(CS splitCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.joinCode.
      @see org.hl7.rim.ActRelationship#getJoinCode
  */
  public CS getJoinCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.joinCode.
      @see org.hl7.rim.ActRelationship#setJoinCode
  */
  public void setJoinCode(CS joinCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.negationInd.
      @see org.hl7.rim.ActRelationship#getNegationInd
  */
  public BL getNegationInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.negationInd.
      @see org.hl7.rim.ActRelationship#setNegationInd
  */
  public void setNegationInd(BL negationInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.conjunctionCode.
      @see org.hl7.rim.ActRelationship#getConjunctionCode
  */
  public CS getConjunctionCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.conjunctionCode.
      @see org.hl7.rim.ActRelationship#setConjunctionCode
  */
  public void setConjunctionCode(CS conjunctionCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.localVariableName.
      @see org.hl7.rim.ActRelationship#getLocalVariableName
  */
  public ST getLocalVariableName() { return STnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.localVariableName.
      @see org.hl7.rim.ActRelationship#setLocalVariableName
  */
  public void setLocalVariableName(ST localVariableName) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.seperatableInd.
      @see org.hl7.rim.ActRelationship#getSeperatableInd
  */
  public BL getSeperatableInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.seperatableInd.
      @see org.hl7.rim.ActRelationship#setSeperatableInd
  */
  public void setSeperatableInd(BL seperatableInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.subsetCode.
      @see org.hl7.rim.ActRelationship#getSubsetCode
  */
  public CS getSubsetCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.subsetCode.
      @see org.hl7.rim.ActRelationship#setSubsetCode
  */
  public void setSubsetCode(CS subsetCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.uncertaintyCode.
      @see org.hl7.rim.ActRelationship#getUncertaintyCode
  */
  public CE getUncertaintyCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.uncertaintyCode.
      @see org.hl7.rim.ActRelationship#setUncertaintyCode
  */
  public void setUncertaintyCode(CE uncertaintyCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns null if not overloaded.source.
      @see org.hl7.rim.ActRelationship#getSource
  */
  public org.hl7.rim.Act getSource() { return null; }
  /** Property mutator, does nothing if not overloaded.source.
      @see org.hl7.rim.ActRelationship#setSource
  */
  public void setSource(org.hl7.rim.Act source) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns null if not overloaded.target.
      @see org.hl7.rim.ActRelationship#getTarget
  */
  public org.hl7.rim.Act getTarget() { return null; }
  /** Property mutator, does nothing if not overloaded.target.
      @see org.hl7.rim.ActRelationship#setTarget
  */
  public void setTarget(org.hl7.rim.Act target) { /* throw new UnsupportedOperationException(); */ }
}

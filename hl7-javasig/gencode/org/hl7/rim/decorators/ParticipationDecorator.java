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

import org.hl7.rim.Participation;
import org.hl7.types.CS;
import org.hl7.types.CD;
import org.hl7.types.INT;
import org.hl7.types.BL;
import org.hl7.types.ED;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.CE;

import org.hl7.rim.Act;
import org.hl7.rim.Role;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.CDnull;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.EDnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.CEnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Participation as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Participation
  */
public abstract class ParticipationDecorator extends BasicParticipationDecorator implements Participation {
  /** Property accessor, returns NULL/NA if not overloaded.functionCode.
      @see org.hl7.rim.Participation#getFunctionCode
  */
  public CD getFunctionCode() { return CDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.functionCode.
      @see org.hl7.rim.Participation#setFunctionCode
  */
  public void setFunctionCode(CD functionCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.contextControlCode.
      @see org.hl7.rim.Participation#getContextControlCode
  */
  public CS getContextControlCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.contextControlCode.
      @see org.hl7.rim.Participation#setContextControlCode
  */
  public void setContextControlCode(CS contextControlCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.sequenceNumber.
      @see org.hl7.rim.Participation#getSequenceNumber
  */
  public INT getSequenceNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.sequenceNumber.
      @see org.hl7.rim.Participation#setSequenceNumber
  */
  public void setSequenceNumber(INT sequenceNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.negationInd.
      @see org.hl7.rim.Participation#getNegationInd
  */
  public BL getNegationInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.negationInd.
      @see org.hl7.rim.Participation#setNegationInd
  */
  public void setNegationInd(BL negationInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.noteText.
      @see org.hl7.rim.Participation#getNoteText
  */
  public ED getNoteText() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.noteText.
      @see org.hl7.rim.Participation#setNoteText
  */
  public void setNoteText(ED noteText) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.time.
      @see org.hl7.rim.Participation#getTime
  */
  public IVL<TS> getTime() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.time.
      @see org.hl7.rim.Participation#setTime
  */
  public void setTime(IVL<TS> time) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.modeCode.
      @see org.hl7.rim.Participation#getModeCode
  */
  public CE getModeCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.modeCode.
      @see org.hl7.rim.Participation#setModeCode
  */
  public void setModeCode(CE modeCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.awarenessCode.
      @see org.hl7.rim.Participation#getAwarenessCode
  */
  public CE getAwarenessCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.awarenessCode.
      @see org.hl7.rim.Participation#setAwarenessCode
  */
  public void setAwarenessCode(CE awarenessCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.signatureCode.
      @see org.hl7.rim.Participation#getSignatureCode
  */
  public CE getSignatureCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.signatureCode.
      @see org.hl7.rim.Participation#setSignatureCode
  */
  public void setSignatureCode(CE signatureCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.signatureText.
      @see org.hl7.rim.Participation#getSignatureText
  */
  public ED getSignatureText() { return EDnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.signatureText.
      @see org.hl7.rim.Participation#setSignatureText
  */
  public void setSignatureText(ED signatureText) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.performInd.
      @see org.hl7.rim.Participation#getPerformInd
  */
  public BL getPerformInd() { return BLimpl.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.performInd.
      @see org.hl7.rim.Participation#setPerformInd
  */
  public void setPerformInd(BL performInd) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.substitutionConditionCode.
      @see org.hl7.rim.Participation#getSubstitutionConditionCode
  */
  public CE getSubstitutionConditionCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.substitutionConditionCode.
      @see org.hl7.rim.Participation#setSubstitutionConditionCode
  */
  public void setSubstitutionConditionCode(CE substitutionConditionCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.subsetCode.
      @see org.hl7.rim.Participation#getSubsetCode
  */
  public CS getSubsetCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.subsetCode.
      @see org.hl7.rim.Participation#setSubsetCode
  */
  public void setSubsetCode(CS subsetCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns null if not overloaded.act.
      @see org.hl7.rim.Participation#getAct
  */
  public org.hl7.rim.Act getAct() { return null; }
  /** Property mutator, does nothing if not overloaded.act.
      @see org.hl7.rim.Participation#setAct
  */
  public void setAct(org.hl7.rim.Act act) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns null if not overloaded.role.
      @see org.hl7.rim.Participation#getRole
  */
  public org.hl7.rim.Role getRole() { return null; }
  /** Property mutator, does nothing if not overloaded.role.
      @see org.hl7.rim.Participation#setRole
  */
  public void setRole(org.hl7.rim.Role role) { /* throw new UnsupportedOperationException(); */ }
}

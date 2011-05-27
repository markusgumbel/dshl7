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

import org.hl7.rim.RoleLink;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.TS;

import org.hl7.rim.Role;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.TSnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.RoleLink as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.RoleLink
  */
public abstract class RoleLinkDecorator extends BasicRoleLinkDecorator implements RoleLink {
  /** Property accessor, returns NULL/NA if not overloaded.priorityNumber.
      @see org.hl7.rim.RoleLink#getPriorityNumber
  */
  public INT getPriorityNumber() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.priorityNumber.
      @see org.hl7.rim.RoleLink#setPriorityNumber
  */
  public void setPriorityNumber(INT priorityNumber) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.effectiveTime.
      @see org.hl7.rim.RoleLink#getEffectiveTime
  */
  public IVL<TS> getEffectiveTime() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.effectiveTime.
      @see org.hl7.rim.RoleLink#setEffectiveTime
  */
  public void setEffectiveTime(IVL<TS> effectiveTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns null if not overloaded.source.
      @see org.hl7.rim.RoleLink#getSource
  */
  public org.hl7.rim.Role getSource() { return null; }
  /** Property mutator, does nothing if not overloaded.source.
      @see org.hl7.rim.RoleLink#setSource
  */
  public void setSource(org.hl7.rim.Role source) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns null if not overloaded.target.
      @see org.hl7.rim.RoleLink#getTarget
  */
  public org.hl7.rim.Role getTarget() { return null; }
  /** Property mutator, does nothing if not overloaded.target.
      @see org.hl7.rim.RoleLink#setTarget
  */
  public void setTarget(org.hl7.rim.Role target) { /* throw new UnsupportedOperationException(); */ }
}

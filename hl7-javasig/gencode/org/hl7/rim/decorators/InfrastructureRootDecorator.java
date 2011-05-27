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

import org.hl7.rim.InfrastructureRoot;
import org.hl7.rim.impl.RimObjectImpl;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.CS;
import org.hl7.types.II;
import org.hl7.types.LIST;
import org.hl7.types.II;

import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.LISTnull;
import org.hl7.types.impl.IInull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.InfrastructureRoot as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.InfrastructureRoot
  */
public abstract class InfrastructureRootDecorator extends org.hl7.rim.impl.RimObjectImpl implements InfrastructureRoot {
  /** Property accessor, returns NULL/NA if not overloaded.nullFlavor.
      @see org.hl7.rim.InfrastructureRoot#getNullFlavor
  */
  public CS getNullFlavor() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.nullFlavor.
      @see org.hl7.rim.InfrastructureRoot#setNullFlavor
  */
  public void setNullFlavor(CS nullFlavor) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.realmCode.
      @see org.hl7.rim.InfrastructureRoot#getRealmCode
  */
  public SET<CS> getRealmCode() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.realmCode.
      @see org.hl7.rim.InfrastructureRoot#setRealmCode
  */
  public void setRealmCode(SET<CS> realmCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.typeId.
      @see org.hl7.rim.InfrastructureRoot#getTypeId
  */
  public II getTypeId() { return IInull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.typeId.
      @see org.hl7.rim.InfrastructureRoot#setTypeId
  */
  public void setTypeId(II typeId) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.templateId.
      @see org.hl7.rim.InfrastructureRoot#getTemplateId
  */
  public LIST<II> getTemplateId() { return LISTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.templateId.
      @see org.hl7.rim.InfrastructureRoot#setTemplateId
  */
  public void setTemplateId(LIST<II> templateId) { /*throw new UnsupportedOperationException();*/ }
}

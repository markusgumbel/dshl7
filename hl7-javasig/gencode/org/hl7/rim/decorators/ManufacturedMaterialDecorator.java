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

import org.hl7.rim.ManufacturedMaterial;
import org.hl7.rim.decorators.MaterialDecorator;
import org.hl7.types.ST;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.TS;

import org.hl7.types.impl.STnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.TSnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.ManufacturedMaterial as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.ManufacturedMaterial
  */
public abstract class ManufacturedMaterialDecorator extends org.hl7.rim.decorators.MaterialDecorator implements ManufacturedMaterial {
  /** Property accessor, returns NULL/NA if not overloaded.lotNumberText.
      @see org.hl7.rim.ManufacturedMaterial#getLotNumberText
  */
  public ST getLotNumberText() { return STnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.lotNumberText.
      @see org.hl7.rim.ManufacturedMaterial#setLotNumberText
  */
  public void setLotNumberText(ST lotNumberText) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.expirationTime.
      @see org.hl7.rim.ManufacturedMaterial#getExpirationTime
  */
  public IVL<TS> getExpirationTime() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.expirationTime.
      @see org.hl7.rim.ManufacturedMaterial#setExpirationTime
  */
  public void setExpirationTime(IVL<TS> expirationTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.stabilityTime.
      @see org.hl7.rim.ManufacturedMaterial#getStabilityTime
  */
  public IVL<TS> getStabilityTime() { return IVLnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.stabilityTime.
      @see org.hl7.rim.ManufacturedMaterial#setStabilityTime
  */
  public void setStabilityTime(IVL<TS> stabilityTime) { /*throw new UnsupportedOperationException();*/ }
}

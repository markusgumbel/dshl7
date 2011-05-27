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

import org.hl7.rim.BasicEntity;
import org.hl7.rim.decorators.InfrastructureRootDecorator;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CE;
import org.hl7.types.PQ;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.ED;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.TEL;

import org.hl7.rim.CommunicationFunction;
import org.hl7.rim.LanguageCommunication;
import org.hl7.rim.Role;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.PQnull;
import org.hl7.types.impl.BAGnull;
import org.hl7.types.impl.ENnull;
import org.hl7.types.impl.EDnull;
import org.hl7.types.impl.IVLnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.TELnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Entity as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Entity
  */
public abstract class BasicEntityDecorator extends org.hl7.rim.decorators.InfrastructureRootDecorator implements BasicEntity {
  /** Property accessor, returns NULL/NA if not overloaded.classCode.
      @see org.hl7.rim.Entity#getClassCode
  */
  public CS getClassCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.classCode.
      @see org.hl7.rim.Entity#setClassCode
  */
  public void setClassCode(CS classCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.determinerCode.
      @see org.hl7.rim.Entity#getDeterminerCode
  */
  public CS getDeterminerCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.determinerCode.
      @see org.hl7.rim.Entity#setDeterminerCode
  */
  public void setDeterminerCode(CS determinerCode) { /*throw new UnsupportedOperationException();*/ }
}

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

import org.hl7.rim.Document;
import org.hl7.rim.decorators.ContextStructureDecorator;
import org.hl7.types.CE;
import org.hl7.types.TS;
import org.hl7.types.SET;
import org.hl7.types.ED;

import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.TSnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.EDnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Document as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Document
  */
public abstract class DocumentDecorator extends org.hl7.rim.decorators.ContextStructureDecorator implements Document {
  /** Property accessor, returns NULL/NA if not overloaded.completionCode.
      @see org.hl7.rim.Document#getCompletionCode
  */
  public CE getCompletionCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.completionCode.
      @see org.hl7.rim.Document#setCompletionCode
  */
  public void setCompletionCode(CE completionCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.storageCode.
      @see org.hl7.rim.Document#getStorageCode
  */
  public CE getStorageCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.storageCode.
      @see org.hl7.rim.Document#setStorageCode
  */
  public void setStorageCode(CE storageCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.copyTime.
      @see org.hl7.rim.Document#getCopyTime
  */
  public TS getCopyTime() { return TSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.copyTime.
      @see org.hl7.rim.Document#setCopyTime
  */
  public void setCopyTime(TS copyTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.bibliographicDesignationText.
      @see org.hl7.rim.Document#getBibliographicDesignationText
  */
  public SET<ED> getBibliographicDesignationText() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.bibliographicDesignationText.
      @see org.hl7.rim.Document#setBibliographicDesignationText
  */
  public void setBibliographicDesignationText(SET<ED> bibliographicDesignationText) { /*throw new UnsupportedOperationException();*/ }
}

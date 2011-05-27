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

import org.hl7.rim.Parameter;
import org.hl7.rim.decorators.InfrastructureRootDecorator;
import org.hl7.types.II;

import org.hl7.rim.QueryByParameter;
import org.hl7.rim.ParameterList;
import org.hl7.types.impl.IInull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Parameter as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Parameter
  */
public abstract class ParameterDecorator extends org.hl7.rim.decorators.InfrastructureRootDecorator implements Parameter {
  /** Property accessor, returns NULL/NA if not overloaded.id.
      @see org.hl7.rim.Parameter#getId
  */
  public II getId() { return IInull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.id.
      @see org.hl7.rim.Parameter#setId
  */
  public void setId(II id) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns null if not overloaded.queryByParameter.
      @see org.hl7.rim.Parameter#getQueryByParameter
  */
  public org.hl7.rim.QueryByParameter getQueryByParameter() { return null; }
  /** Property mutator, does nothing if not overloaded.queryByParameter.
      @see org.hl7.rim.Parameter#setQueryByParameter
  */
  public void setQueryByParameter(org.hl7.rim.QueryByParameter queryByParameter) { /* throw new UnsupportedOperationException(); */ }
  /** Property accessor, returns null if not overloaded.parameterList.
      @see org.hl7.rim.Parameter#getParameterList
  */
  public org.hl7.rim.ParameterList getParameterList() { return null; }
  /** Property mutator, does nothing if not overloaded.parameterList.
      @see org.hl7.rim.Parameter#setParameterList
  */
  public void setParameterList(org.hl7.rim.ParameterList parameterList) { /* throw new UnsupportedOperationException(); */ }
}

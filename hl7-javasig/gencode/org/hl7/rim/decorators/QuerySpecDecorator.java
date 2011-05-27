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

import org.hl7.rim.QuerySpec;
import org.hl7.rim.decorators.QueryEventDecorator;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.INT;
import org.hl7.types.CE;
import org.hl7.types.TS;

import org.hl7.rim.SortControl;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.IInull;
import org.hl7.types.impl.INTnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.TSnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.QuerySpec as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.QuerySpec
  */
public abstract class QuerySpecDecorator extends org.hl7.rim.decorators.QueryEventDecorator implements QuerySpec {
  /** Property accessor, returns NULL/NA if not overloaded.modifyCode.
      @see org.hl7.rim.QuerySpec#getModifyCode
  */
  public CS getModifyCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.modifyCode.
      @see org.hl7.rim.QuerySpec#setModifyCode
  */
  public void setModifyCode(CS modifyCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.responseElementGroupId.
      @see org.hl7.rim.QuerySpec#getResponseElementGroupId
  */
  public SET<II> getResponseElementGroupId() { return SETnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.responseElementGroupId.
      @see org.hl7.rim.QuerySpec#setResponseElementGroupId
  */
  public void setResponseElementGroupId(SET<II> responseElementGroupId) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.responseModalityCode.
      @see org.hl7.rim.QuerySpec#getResponseModalityCode
  */
  public CS getResponseModalityCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.responseModalityCode.
      @see org.hl7.rim.QuerySpec#setResponseModalityCode
  */
  public void setResponseModalityCode(CS responseModalityCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.responsePriorityCode.
      @see org.hl7.rim.QuerySpec#getResponsePriorityCode
  */
  public CS getResponsePriorityCode() { return CSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.responsePriorityCode.
      @see org.hl7.rim.QuerySpec#setResponsePriorityCode
  */
  public void setResponsePriorityCode(CS responsePriorityCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.initialQuantity.
      @see org.hl7.rim.QuerySpec#getInitialQuantity
  */
  public INT getInitialQuantity() { return INTnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.initialQuantity.
      @see org.hl7.rim.QuerySpec#setInitialQuantity
  */
  public void setInitialQuantity(INT initialQuantity) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.initialQuantityCode.
      @see org.hl7.rim.QuerySpec#getInitialQuantityCode
  */
  public CE getInitialQuantityCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.initialQuantityCode.
      @see org.hl7.rim.QuerySpec#setInitialQuantityCode
  */
  public void setInitialQuantityCode(CE initialQuantityCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.executionAndDeliveryTime.
      @see org.hl7.rim.QuerySpec#getExecutionAndDeliveryTime
  */
  public TS getExecutionAndDeliveryTime() { return TSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.executionAndDeliveryTime.
      @see org.hl7.rim.QuerySpec#setExecutionAndDeliveryTime
  */
  public void setExecutionAndDeliveryTime(TS executionAndDeliveryTime) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns an empty collection if not overloaded.sortControl.
      @see org.hl7.rim.QuerySpec#getSortControl
  */
  public /*AssociationSet*/List<org.hl7.rim.SortControl> getSortControl() { return Collections.EMPTY_LIST; }
  /** Property mutator, does nothing if not overloaded.sortControl.
      @see org.hl7.rim.QuerySpec#setSortControl
  */
  public void setSortControl(/*AssociationSet*/List<org.hl7.rim.SortControl> sortControl) { /* throw new UnsupportedOperationException(); */ }
  /** Association adder, throws UnsupportedOperationException if not overloaded sortControl.
      @see org.hl7.rim.QuerySpec#setSortControl
  */
  public void addSortControl(org.hl7.rim.SortControl sortControl) { throw new UnsupportedOperationException(); }
}

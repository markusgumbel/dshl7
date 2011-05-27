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
package org.hl7.rim.impl;

import org.hibernate.Criteria;
import org.hl7.hibernate.Persistence;

import org.hl7.rim.ControlAct;
import org.hl7.rim.impl.ActImpl;

import org.hl7.rim.Message;
import org.hl7.rim.QueryEvent;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.ControlAct as a simple data holder bean.
    @see org.hl7.rim.ControlAct
  */
public class ControlActImpl extends org.hl7.rim.impl.ActImpl implements ControlAct {

  private org.hl7.rim.Message _payload;
  /** Gets the property payload.
      @see org.hl7.rim.ControlAct#getPayload
  */
  public org.hl7.rim.Message getPayload() {
    return _payload;
  }
  /** Sets the property payload.
      @see org.hl7.rim.ControlAct#setPayload
  */
  public void setPayload(org.hl7.rim.Message payload) {
    _payload = payload;
  }

  private org.hl7.rim.QueryEvent _queryEvent;
  /** Gets the property queryEvent.
      @see org.hl7.rim.ControlAct#getQueryEvent
  */
  public org.hl7.rim.QueryEvent getQueryEvent() {
    return _queryEvent;
  }
  /** Sets the property queryEvent.
      @see org.hl7.rim.ControlAct#setQueryEvent
  */
  public void setQueryEvent(org.hl7.rim.QueryEvent queryEvent) {
    _queryEvent = queryEvent;
  }
  public Object clone() throws CloneNotSupportedException {
    ControlActImpl that = (ControlActImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

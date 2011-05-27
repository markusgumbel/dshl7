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

import org.hl7.rim.QueryBySelection;
import org.hl7.rim.impl.QuerySpecImpl;

import org.hl7.rim.SelectionExpression;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.QueryBySelection as a simple data holder bean.
    @see org.hl7.rim.QueryBySelection
  */
public class QueryBySelectionImpl extends org.hl7.rim.impl.QuerySpecImpl implements QueryBySelection {

  private /*AssociationSet*/List<org.hl7.rim.SelectionExpression> _selectionExpression;
  /** Gets the property selectionExpression.
      @see org.hl7.rim.QueryBySelection#getSelectionExpression
  */
  public /*AssociationSet*/List<org.hl7.rim.SelectionExpression> getSelectionExpression() {
    return _selectionExpression;
  }
  /** Sets the property selectionExpression.
      @see org.hl7.rim.QueryBySelection#setSelectionExpression
  */
  public void setSelectionExpression(/*AssociationSet*/List<org.hl7.rim.SelectionExpression> selectionExpression) {
    _selectionExpression = selectionExpression;
  }
  /** Adds an association selectionExpression.
      @see org.hl7.rim.QueryBySelection#setSelectionExpression
  */
  public void addSelectionExpression(org.hl7.rim.SelectionExpression selectionExpression) {
        // create the association set if it doesn't exist already
    if(_selectionExpression == null) _selectionExpression = new AssociationSetImpl<org.hl7.rim.SelectionExpression>();
    // add the association to the association set
    getSelectionExpression().add(selectionExpression);
    // make the inverse link
    selectionExpression.setQueryBySelection(this);
  }
  public Object clone() throws CloneNotSupportedException {
    QueryBySelectionImpl that = (QueryBySelectionImpl) super.clone();

    // deep clone of persistent component collections
    that._selectionExpression= null;
    return that;
  }
}

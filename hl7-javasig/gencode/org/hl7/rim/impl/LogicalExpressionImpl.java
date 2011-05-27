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

import org.hl7.rim.LogicalExpression;
import org.hl7.rim.impl.SelectionExpressionImpl;
import org.hl7.types.CS;

import org.hl7.rim.SelectionExpression;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.LogicalExpression as a simple data holder bean.
    @see org.hl7.rim.LogicalExpression
  */
public class LogicalExpressionImpl extends org.hl7.rim.impl.SelectionExpressionImpl implements LogicalExpression {

  private CS _relationalConjunctionCode;
  /** Gets the property relationalConjunctionCode.
      @see org.hl7.rim.LogicalExpression#getRelationalConjunctionCode
  */
  public CS getRelationalConjunctionCode() { return _relationalConjunctionCode; }
  /** Sets the property relationalConjunctionCode.
      @see org.hl7.rim.LogicalExpression#setRelationalConjunctionCode
  */
  public void setRelationalConjunctionCode(CS relationalConjunctionCode) {
    if(relationalConjunctionCode instanceof org.hl7.hibernate.ClonableCollection)
      relationalConjunctionCode = ((org.hl7.hibernate.ClonableCollection<CS>) relationalConjunctionCode).cloneHibernateCollectionIfNecessary();
    _relationalConjunctionCode = relationalConjunctionCode;
  }
  /** Sets the property relationalConjunctionCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.LogicalExpression#setRelationalConjunctionCode
  */
  public void setRelationalConjunctionCodeForHibernate(CS relationalConjunctionCode) {
    _relationalConjunctionCode = relationalConjunctionCode;
  }

  private /*AssociationSet*/List<org.hl7.rim.SelectionExpression> _userAsLeft;
  /** Gets the property userAsLeft.
      @see org.hl7.rim.LogicalExpression#getUserAsLeft
  */
  public /*AssociationSet*/List<org.hl7.rim.SelectionExpression> getUserAsLeft() {
    return _userAsLeft;
  }
  /** Sets the property userAsLeft.
      @see org.hl7.rim.LogicalExpression#setUserAsLeft
  */
  public void setUserAsLeft(/*AssociationSet*/List<org.hl7.rim.SelectionExpression> userAsLeft) {
    _userAsLeft = userAsLeft;
  }
  /** Adds an association userAsLeft.
      @see org.hl7.rim.LogicalExpression#setUserAsLeft
  */
  public void addUserAsLeft(org.hl7.rim.SelectionExpression userAsLeft) {
        // create the association set if it doesn't exist already
    if(_userAsLeft == null) _userAsLeft = new AssociationSetImpl<org.hl7.rim.SelectionExpression>();
    // add the association to the association set
    getUserAsLeft().add(userAsLeft);
    // make the inverse link
    userAsLeft.setLeftSide(this);
  }

  private /*AssociationSet*/List<org.hl7.rim.SelectionExpression> _userAsRight;
  /** Gets the property userAsRight.
      @see org.hl7.rim.LogicalExpression#getUserAsRight
  */
  public /*AssociationSet*/List<org.hl7.rim.SelectionExpression> getUserAsRight() {
    return _userAsRight;
  }
  /** Sets the property userAsRight.
      @see org.hl7.rim.LogicalExpression#setUserAsRight
  */
  public void setUserAsRight(/*AssociationSet*/List<org.hl7.rim.SelectionExpression> userAsRight) {
    _userAsRight = userAsRight;
  }
  /** Adds an association userAsRight.
      @see org.hl7.rim.LogicalExpression#setUserAsRight
  */
  public void addUserAsRight(org.hl7.rim.SelectionExpression userAsRight) {
        // create the association set if it doesn't exist already
    if(_userAsRight == null) _userAsRight = new AssociationSetImpl<org.hl7.rim.SelectionExpression>();
    // add the association to the association set
    getUserAsRight().add(userAsRight);
    // make the inverse link
    userAsRight.setRightSide(this);
  }
  public Object clone() throws CloneNotSupportedException {
    LogicalExpressionImpl that = (LogicalExpressionImpl) super.clone();

    // deep clone of persistent component collections
    that._userAsLeft= null;
    that._userAsRight= null;
    return that;
  }
}

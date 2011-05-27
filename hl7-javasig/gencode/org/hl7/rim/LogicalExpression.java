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
package org.hl7.rim;

import org.hl7.rim.SelectionExpression;
import org.hl7.types.CS;

import org.hl7.rim.SelectionExpression;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**
*/
public interface LogicalExpression extends org.hl7.rim.SelectionExpression {

  /**<p>When more than one criteria is to be applied in the evaluation of candidate instances, a conjunction is supplied to identify
   how to relate an additional criteria.
</p>
  */
  CS getRelationalConjunctionCode();
  /** Sets the property relationalConjunctionCode.
      @see #getRelationalConjunctionCode
  */
  void setRelationalConjunctionCode(CS relationalConjunctionCode);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.SelectionExpression> getUserAsLeft();
  /** Sets the property userAsLeft.
      @see #getUserAsLeft
  */
  void setUserAsLeft(/*AssociationSet*/List<org.hl7.rim.SelectionExpression> userAsLeft);
  /** Adds an association userAsLeft.
      @see #addUserAsLeft
  */
  void addUserAsLeft(org.hl7.rim.SelectionExpression userAsLeft);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.SelectionExpression> getUserAsRight();
  /** Sets the property userAsRight.
      @see #getUserAsRight
  */
  void setUserAsRight(/*AssociationSet*/List<org.hl7.rim.SelectionExpression> userAsRight);
  /** Adds an association userAsRight.
      @see #addUserAsRight
  */
  void addUserAsRight(org.hl7.rim.SelectionExpression userAsRight);
}

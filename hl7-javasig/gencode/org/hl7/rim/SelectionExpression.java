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

import org.hl7.rim.InfrastructureRoot;

import org.hl7.rim.LogicalExpression;
import org.hl7.rim.QueryBySelection;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**
*/
public interface SelectionExpression extends org.hl7.rim.InfrastructureRoot {

  /**
  */
  org.hl7.rim.LogicalExpression getLeftSide();
  /** Sets the property leftSide.
      @see #getLeftSide
  */
  void setLeftSide(org.hl7.rim.LogicalExpression leftSide);

  /**
  */
  org.hl7.rim.QueryBySelection getQueryBySelection();
  /** Sets the property queryBySelection.
      @see #getQueryBySelection
  */
  void setQueryBySelection(org.hl7.rim.QueryBySelection queryBySelection);

  /**
  */
  org.hl7.rim.LogicalExpression getRightSide();
  /** Sets the property rightSide.
      @see #getRightSide
  */
  void setRightSide(org.hl7.rim.LogicalExpression rightSide);
}

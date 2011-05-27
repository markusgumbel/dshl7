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
import org.hl7.types.SC;
import org.hl7.types.CS;
import org.hl7.types.ST;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**
*/
public interface RelationalExpression extends org.hl7.rim.SelectionExpression {

  /**<p>Identifies RIM element as subject of selection criteria evaluation.</p>
  */
  SC getElementName();
  /** Sets the property elementName.
      @see #getElementName
  */
  void setElementName(SC elementName);

  /**<p>Identifies common relational operators used in selection criteria.</p>
  */
  CS getRelationalOperatorCode();
  /** Sets the property relationalOperatorCode.
      @see #getRelationalOperatorCode
  */
  void setRelationalOperatorCode(CS relationalOperatorCode);

  /**<p>Value supplied for comparison using criteria.</p>
  */
  ST getValue();
  /** Sets the property value.
      @see #getValue
  */
  void setValue(ST value);
}

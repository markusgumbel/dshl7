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
import org.hl7.types.INT;
import org.hl7.types.SC;
import org.hl7.types.CS;

import org.hl7.rim.QuerySpec;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>Holds specification of sort order for instance matches to a query.</p>
*/
public interface SortControl extends org.hl7.rim.InfrastructureRoot {

  /**<p>Provides the sequence or primacy of the various SortControls for a given query.</p>
  */
  INT getSequenceNumber();
  /** Sets the property sequenceNumber.
      @see #getSequenceNumber
  */
  void setSequenceNumber(INT sequenceNumber);

  /**<p>Identifies a RIM element in a query response upon which to sort.</p>
  */
  SC getElementName();
  /** Sets the property elementName.
      @see #getElementName
  */
  void setElementName(SC elementName);

  /**<p>Specifies direction (ascending or descending) of the sort.</p>
  */
  CS getDirectionCode();
  /** Sets the property directionCode.
      @see #getDirectionCode
  */
  void setDirectionCode(CS directionCode);

  /**
  */
  org.hl7.rim.QuerySpec getQuerySpec();
  /** Sets the property querySpec.
      @see #getQuerySpec
  */
  void setQuerySpec(org.hl7.rim.QuerySpec querySpec);
}

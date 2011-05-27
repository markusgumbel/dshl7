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

import org.hl7.rim.QuerySpec;

import org.hl7.rim.SelectionExpression;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>This class contains the definition of a Query by Selection. This is an HL7 query in which a request can specify any or all
   of the variables offered by a data server and may additionally specify any permissible operators and values for each variable
   as published in a query conformance statement. This query format is considered an open query because it allows a selection
   specification against a published data base schema.
</p>
*/
public interface QueryBySelection extends org.hl7.rim.QuerySpec {

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.SelectionExpression> getSelectionExpression();
  /** Sets the property selectionExpression.
      @see #getSelectionExpression
  */
  void setSelectionExpression(/*AssociationSet*/List<org.hl7.rim.SelectionExpression> selectionExpression);
  /** Adds an association selectionExpression.
      @see #addSelectionExpression
  */
  void addSelectionExpression(org.hl7.rim.SelectionExpression selectionExpression);
}

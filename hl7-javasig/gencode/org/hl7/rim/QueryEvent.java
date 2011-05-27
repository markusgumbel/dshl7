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
import org.hl7.types.II;
import org.hl7.types.CS;

import org.hl7.rim.ControlAct;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>This abstract class is used to gather the parts of a message interaction that are specific to a query message interaction.</p>
<p>Rationale: A message element type is defined by a TC to meet a messaging requirement for a query response (like the response
   message element type for a demographics query). An instance of such a message element type would be represented as a query
   message interaction in this revised view of the V3 query/response model. The "return_element_group" would identify the RIM
   view that would be similar in form to the RIM view specified in a declarative or imperative application message interaction.
</p>
<p>OpenIssue: State names need to be re-visited.</p>
*/
public interface QueryEvent extends org.hl7.rim.InfrastructureRoot {

  /**<p>This attribute may be valued by the initiating application to identify the query. It is intended to be used to match response
   messages to the originating query. QueryEvent.queryId may remain the same across multiple interactions when performing continuations
   of a previous query.
</p>
  */
  II getQueryId();
  /** Sets the property queryId.
      @see #getQueryId
  */
  void setQueryId(II queryId);

  /**
  */
  CS getStatusCode();
  /** Sets the property statusCode.
      @see #getStatusCode
  */
  void setStatusCode(CS statusCode);

  /**
  */
  org.hl7.rim.ControlAct getControlAct();
  /** Sets the property controlAct.
      @see #getControlAct
  */
  void setControlAct(org.hl7.rim.ControlAct controlAct);
}

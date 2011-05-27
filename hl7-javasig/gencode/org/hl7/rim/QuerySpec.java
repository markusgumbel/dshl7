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

import org.hl7.rim.QueryEvent;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.INT;
import org.hl7.types.CE;
import org.hl7.types.TS;

import org.hl7.rim.SortControl;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>This class contains the specification of all HL7 Version 3 queries. Attributes common to all queries appear in this class
   specification.
</p>
*/
public interface QuerySpec extends org.hl7.rim.QueryEvent {

  /**<p>Indicates whether the subscription to a query is new or is being modified.</p>
  */
  CS getModifyCode();
  /** Sets the property modifyCode.
      @see #getModifyCode
  */
  void setModifyCode(CS modifyCode);

  /**<p>The responseElementGroupId identifies the specific message type to be returned in the query response. This message type must
   be chosen from the set of message types supported by the receiver responsibilities associated with the query interaction.
</p>
  */
  SET<II> getResponseElementGroupId();
  /** Sets the property responseElementGroupId.
      @see #getResponseElementGroupId
  */
  void setResponseElementGroupId(SET<II> responseElementGroupId);

  /**<p>Defines the timing and grouping of the response instances.</p>
  */
  CS getResponseModalityCode();
  /** Sets the property responseModalityCode.
      @see #getResponseModalityCode
  */
  void setResponseModalityCode(CS responseModalityCode);

  /**<p>Identifies the time frame in which the response is expected.</p>
  */
  CS getResponsePriorityCode();
  /** Sets the property responsePriorityCode.
      @see #getResponsePriorityCode
  */
  void setResponsePriorityCode(CS responsePriorityCode);

  /**<p>Defines the maximum size of the response that can be accepted by the requesting application.</p>
  */
  INT getInitialQuantity();
  /** Sets the property initialQuantity.
      @see #getInitialQuantity
  */
  void setInitialQuantity(INT initialQuantity);

  /**<p>Defines the units associated with the magnitude of the maximum size limit of a query response that can be accepted by the
   requesting application
</p>
  */
  CE getInitialQuantityCode();
  /** Sets the property initialQuantityCode.
      @see #getInitialQuantityCode
  */
  void setInitialQuantityCode(CE initialQuantityCode);

  /**<p>Specifies the time the response is to be returned.</p>
  */
  TS getExecutionAndDeliveryTime();
  /** Sets the property executionAndDeliveryTime.
      @see #getExecutionAndDeliveryTime
  */
  void setExecutionAndDeliveryTime(TS executionAndDeliveryTime);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.SortControl> getSortControl();
  /** Sets the property sortControl.
      @see #getSortControl
  */
  void setSortControl(/*AssociationSet*/List<org.hl7.rim.SortControl> sortControl);
  /** Adds an association sortControl.
      @see #addSortControl
  */
  void addSortControl(org.hl7.rim.SortControl sortControl);
}

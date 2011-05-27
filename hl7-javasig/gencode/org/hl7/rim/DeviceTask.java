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

import org.hl7.rim.Act;
import org.hl7.types.LIST;
import org.hl7.types.ANY;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An activity of an automated system.</p>
<p><i>Discussion:</i>Such activities are invoked either by an outside command or are scheduled and executed spontaneously by the device (e.g.,
   regular calibration or flushing). The command to execute the task has moodCode &lt;= ORD; an executed task (including a task
   in progress) has moodCode &lt;= EVN, an automatic task on the schedule has moodCode &lt;= APT.
</p>
*/
public interface DeviceTask extends org.hl7.rim.Act {

  /**<p>The parameters of the task submitted to the device upon the issuance of a command (or configuring the schedule of spontaneously
   executed tasks).
</p>
<p><i>Rationale:</i> Some parameters for tasks are uniquely defined by a specific model of equipment. Most critical arguments of a task (e.g.,
   container to operate on, positioning, timing, etc.) are specified in an HL7 standardized structure, and the parameter list
   would not be used for those. The parameter list is used only for those parameters that cannot be standardized because they
   are uniquely defined for a specific model of equipment. NOTE: This means that the semantics and interpretation of a parameterValue
   can <b>only</b> be made with an understanding of the specifications or documentation for the specific device being addressed. This information
   is not conveyed as part of the message.
</p>
<p><i>Constraints:</i> Parameters are only specified here if they are not included in a separate HL7 defined structure. The parameters are a list
   of any data values interpreted by the device. The parameters should be typed with an appropriate HL7 data type (e.g., codes
   for nominal settings, such as flags, REAL and INT for numbers, TS for points in time, PQ for dimensioned quantities, etc.).
   However, besides this HL7 data typing, the functioning of the parameters is opaque to the HL7 standardization.
</p>
  */
  LIST<ANY> getParameterValue();
  /** Sets the property parameterValue.
      @see #getParameterValue
  */
  void setParameterValue(LIST<ANY> parameterValue);
}

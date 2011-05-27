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

import org.hl7.rim.Message;
import org.hl7.rim.QueryEvent;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An act representing a change to the state of another class, a user event (e.g. query), or a system event (e.g. time-based
   occurrences).
</p>
<p>
   <p><i>Examples: </i></p>
   <ul>
      <li>
         <p>Discharging a patient (Encounter from Active to Completed);</p>
      </li>
      <li>
         <p>Stopping a medication (SubstanceAdministration from Active to Aborted); </p>
      </li>
      <li>
         <p>Sending an end-of-day summary (time-based event).</p>
      </li>
   </ul>
</p>
<p><i>Discussion: </i>This class corresponds to the concept of 'Trigger Event', and as such, must be present as the focus of every messaging interaction
   (because of the 1..1 association between a trigger event and an interaction.) However, control acts can also appear within
   a message payload. For example, a set of control acts associated with a Lab Order identifying the events that have occurred
   against that order (first created, then revised, then suspended, then resumed, then completed.)
</p>
*/
public interface ControlAct extends org.hl7.rim.Act {

  /**
  */
  org.hl7.rim.Message getPayload();
  /** Sets the property payload.
      @see #getPayload
  */
  void setPayload(org.hl7.rim.Message payload);

  /**
  */
  org.hl7.rim.QueryEvent getQueryEvent();
  /** Sets the property queryEvent.
      @see #getQueryEvent
  */
  void setQueryEvent(org.hl7.rim.QueryEvent queryEvent);
}

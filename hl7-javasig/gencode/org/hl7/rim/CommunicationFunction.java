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
import org.hl7.types.CS;
import org.hl7.types.TEL;

import org.hl7.rim.Entity;
import org.hl7.rim.Transmission;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>Relationship class binds the various entities which function in the transmission (sender, receiver, respond-to) to be linked
   to the transmission.
</p>
*/
public interface CommunicationFunction extends org.hl7.rim.InfrastructureRoot {

  /**<p>The type of communication function being served by the entity with respect to the transmission, such as sender, receiver,
   respond-to party, etc.
</p>
  */
  CS getTypeCode();
  /** Sets the property typeCode.
      @see #getTypeCode
  */
  void setTypeCode(CS typeCode);

  /**<p>The telecomm address that can be used to reach the entity that is serving this function.</p>
  */
  TEL getTelecom();
  /** Sets the property telecom.
      @see #getTelecom
  */
  void setTelecom(TEL telecom);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Entity> getEntity();
  /** Sets the property entity.
      @see #getEntity
  */
  void setEntity(/*AssociationSet*/List<org.hl7.rim.Entity> entity);
  /** Adds an association entity.
      @see #addEntity
  */
  void addEntity(org.hl7.rim.Entity entity);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Transmission> getTransmission();
  /** Sets the property transmission.
      @see #getTransmission
  */
  void setTransmission(/*AssociationSet*/List<org.hl7.rim.Transmission> transmission);
  /** Adds an association transmission.
      @see #addTransmission
  */
  void addTransmission(org.hl7.rim.Transmission transmission);
}

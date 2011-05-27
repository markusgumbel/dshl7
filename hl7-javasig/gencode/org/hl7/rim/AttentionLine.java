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
import org.hl7.types.SC;
import org.hl7.types.ANY;

import org.hl7.rim.Transmission;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>This class allows parameters for a technology specific transport to be represented in the V3 message outer wrapper.</p>
*/
public interface AttentionLine extends org.hl7.rim.InfrastructureRoot {

  /**<p>A parameter defining word.</p>
  */
  SC getKeyWordText();
  /** Sets the property keyWordText.
      @see #getKeyWordText
  */
  void setKeyWordText(SC keyWordText);

  /**<p><b>OpenIssue:</b> Control-Query agreed to provide new definition that includes a strong set of constraints on the data tpes that this attribute
   can assume. Pending the delivery of that description, it was agreed in harmonization to drop the proposed description.
</p>
  */
  ANY getValue();
  /** Sets the property value.
      @see #getValue
  */
  void setValue(ANY value);

  /**
  */
  org.hl7.rim.Transmission getTransmission();
  /** Sets the property transmission.
      @see #getTransmission
  */
  void setTransmission(org.hl7.rim.Transmission transmission);
}

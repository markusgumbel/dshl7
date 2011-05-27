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
package org.hl7.UUDD_MT333333UV;

import org.hl7.rim.BasicEntity;
import org.hl7.types.CS;
import org.hl7.types.II;
import org.hl7.types.EN;
import org.hl7.types.CV;

import org.hl7.UUDD_MT333333UV.Citizen;
import org.hl7.UUDD_MT333333UV.Patient;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**
*/
public interface Person extends org.hl7.rim.BasicEntity {

  /**
  */
  II getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(II id);

  /**
  */
  EN getName();
  /** Sets the property name.
      @see #getName
  */
  void setName(EN name);

  /**
  */
  CV getRiskCode();
  /** Sets the property riskCode.
      @see #getRiskCode
  */
  void setRiskCode(CV riskCode);

  /**
  */
  /*AssociationSet*/List<Citizen> getAsCitizen();
  /** Sets the property asCitizen.
      @see #getAsCitizen
  */
  void setAsCitizen(/*AssociationSet*/List<Citizen> asCitizen);
  /** Adds an association asCitizen.
      @see #addAsCitizen
  */
  void addAsCitizen(Citizen asCitizen);

  /**
  */
  Patient getAsPatient();
  /** Sets the property asPatient.
      @see #getAsPatient
  */
  void setAsPatient(Patient asPatient);
}

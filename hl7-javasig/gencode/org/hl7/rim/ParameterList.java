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

import org.hl7.rim.Parameter;

import org.hl7.rim.Parameter;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>Specifies a named list of parameters (name/value pairs) that is referenced in a query conformance statement.</p>
*/
public interface ParameterList extends org.hl7.rim.Parameter {

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Parameter> getParameter();
  /** Sets the property parameter.
      @see #getParameter
  */
  void setParameter(/*AssociationSet*/List<org.hl7.rim.Parameter> parameter);
  /** Adds an association parameter.
      @see #addParameter
  */
  void addParameter(org.hl7.rim.Parameter parameter);
}

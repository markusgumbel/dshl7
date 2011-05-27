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

import org.hl7.rim.QueryByParameter;
import org.hl7.rim.ParameterList;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>The Parameter class is an implementation class that represents the structure of parameters that may be specified with the
   Query-by-parameter mechanisms of the V3 query framework. Parameters may be set of name/value pairs, a named parameter list
   with a set of name/value pairs or any combination the previous two options.
</p>
*/
public interface Parameter extends org.hl7.rim.InfrastructureRoot {

  /**<p>The Parameter.id can assist in tracing problems with implementing the query-by-parameter mechanism.</p>
  */
  II getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(II id);

  /**
  */
  org.hl7.rim.QueryByParameter getQueryByParameter();
  /** Sets the property queryByParameter.
      @see #getQueryByParameter
  */
  void setQueryByParameter(org.hl7.rim.QueryByParameter queryByParameter);

  /**
  */
  org.hl7.rim.ParameterList getParameterList();
  /** Sets the property parameterList.
      @see #getParameterList
  */
  void setParameterList(org.hl7.rim.ParameterList parameterList);
}

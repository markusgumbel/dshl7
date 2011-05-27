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
package org.hl7.rim.impl;

import org.hibernate.Criteria;
import org.hl7.hibernate.Persistence;

import org.hl7.rim.QueryByParameter;
import org.hl7.rim.impl.QuerySpecImpl;

import org.hl7.rim.Parameter;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.QueryByParameter as a simple data holder bean.
    @see org.hl7.rim.QueryByParameter
  */
public class QueryByParameterImpl extends org.hl7.rim.impl.QuerySpecImpl implements QueryByParameter {

  private /*AssociationSet*/List<org.hl7.rim.Parameter> _parameter;
  /** Gets the property parameter.
      @see org.hl7.rim.QueryByParameter#getParameter
  */
  public /*AssociationSet*/List<org.hl7.rim.Parameter> getParameter() {
    return _parameter;
  }
  /** Sets the property parameter.
      @see org.hl7.rim.QueryByParameter#setParameter
  */
  public void setParameter(/*AssociationSet*/List<org.hl7.rim.Parameter> parameter) {
    _parameter = parameter;
  }
  /** Adds an association parameter.
      @see org.hl7.rim.QueryByParameter#setParameter
  */
  public void addParameter(org.hl7.rim.Parameter parameter) {
        // create the association set if it doesn't exist already
    if(_parameter == null) _parameter = new AssociationSetImpl<org.hl7.rim.Parameter>();
    // add the association to the association set
    getParameter().add(parameter);
    // make the inverse link
    parameter.setQueryByParameter(this);
  }
  public Object clone() throws CloneNotSupportedException {
    QueryByParameterImpl that = (QueryByParameterImpl) super.clone();

    // deep clone of persistent component collections
    that._parameter= null;
    return that;
  }
}

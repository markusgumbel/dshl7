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

import org.hl7.rim.DeviceTask;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.LIST;
import org.hl7.types.ANY;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.DeviceTask as a simple data holder bean.
    @see org.hl7.rim.DeviceTask
  */
public class DeviceTaskImpl extends org.hl7.rim.impl.ActImpl implements DeviceTask {

  private LIST<ANY> _parameterValue;
  /** Gets the property parameterValue.
      @see org.hl7.rim.DeviceTask#getParameterValue
  */
  public LIST<ANY> getParameterValue() { return _parameterValue; }
  /** Sets the property parameterValue.
      @see org.hl7.rim.DeviceTask#setParameterValue
  */
  public void setParameterValue(LIST<ANY> parameterValue) {
    if(parameterValue instanceof org.hl7.hibernate.ClonableCollection)
      parameterValue = ((org.hl7.hibernate.ClonableCollection<LIST<ANY>>) parameterValue).cloneHibernateCollectionIfNecessary();
    _parameterValue = parameterValue;
  }
  /** Sets the property parameterValue. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.DeviceTask#setParameterValue
  */
  public void setParameterValueForHibernate(LIST<ANY> parameterValue) {
    _parameterValue = parameterValue;
  }
  public Object clone() throws CloneNotSupportedException {
    DeviceTaskImpl that = (DeviceTaskImpl) super.clone();

    // deep clone of persistent component collections
    that.setParameterValue(that.getParameterValue());
    return that;
  }
}

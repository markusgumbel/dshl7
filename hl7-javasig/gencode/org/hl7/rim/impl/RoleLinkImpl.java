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

import org.hl7.rim.RoleLink;
import org.hl7.types.CS;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.TS;

import org.hl7.rim.Role;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.RoleLink as a simple data holder bean.
    @see org.hl7.rim.RoleLink
  */
public class RoleLinkImpl extends BasicRoleLinkImpl implements RoleLink {

  private INT _priorityNumber;
  /** Gets the property priorityNumber.
      @see org.hl7.rim.RoleLink#getPriorityNumber
  */
  public INT getPriorityNumber() { return _priorityNumber; }
  /** Sets the property priorityNumber.
      @see org.hl7.rim.RoleLink#setPriorityNumber
  */
  public void setPriorityNumber(INT priorityNumber) {
    if(priorityNumber instanceof org.hl7.hibernate.ClonableCollection)
      priorityNumber = ((org.hl7.hibernate.ClonableCollection<INT>) priorityNumber).cloneHibernateCollectionIfNecessary();
    _priorityNumber = priorityNumber;
  }
  /** Sets the property priorityNumber. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.RoleLink#setPriorityNumber
  */
  public void setPriorityNumberForHibernate(INT priorityNumber) {
    _priorityNumber = priorityNumber;
  }

  private IVL<TS> _effectiveTime;
  /** Gets the property effectiveTime.
      @see org.hl7.rim.RoleLink#getEffectiveTime
  */
  public IVL<TS> getEffectiveTime() { return _effectiveTime; }
  /** Sets the property effectiveTime.
      @see org.hl7.rim.RoleLink#setEffectiveTime
  */
  public void setEffectiveTime(IVL<TS> effectiveTime) {
    if(effectiveTime instanceof org.hl7.hibernate.ClonableCollection)
      effectiveTime = ((org.hl7.hibernate.ClonableCollection<IVL<TS>>) effectiveTime).cloneHibernateCollectionIfNecessary();
    _effectiveTime = effectiveTime;
  }
  /** Sets the property effectiveTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.RoleLink#setEffectiveTime
  */
  public void setEffectiveTimeForHibernate(IVL<TS> effectiveTime) {
    _effectiveTime = effectiveTime;
  }

  private org.hl7.rim.Role _source;
  /** Gets the property source.
      @see org.hl7.rim.RoleLink#getSource
  */
  public org.hl7.rim.Role getSource() {
    return _source;
  }
  /** Sets the property source.
      @see org.hl7.rim.RoleLink#setSource
  */
  public void setSource(org.hl7.rim.Role source) {
    _source = source;
  }

  private org.hl7.rim.Role _target;
  /** Gets the property target.
      @see org.hl7.rim.RoleLink#getTarget
  */
  public org.hl7.rim.Role getTarget() {
    return _target;
  }
  /** Sets the property target.
      @see org.hl7.rim.RoleLink#setTarget
  */
  public void setTarget(org.hl7.rim.Role target) {
    _target = target;
  }
  public Object clone() throws CloneNotSupportedException {
    RoleLinkImpl that = (RoleLinkImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

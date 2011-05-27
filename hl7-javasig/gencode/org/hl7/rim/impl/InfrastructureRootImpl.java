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

import org.hl7.rim.InfrastructureRoot;
import org.hl7.rim.impl.RimObjectImpl;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.CS;
import org.hl7.types.II;
import org.hl7.types.LIST;
import org.hl7.types.II;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.InfrastructureRoot as a simple data holder bean.
    @see org.hl7.rim.InfrastructureRoot
  */
public class InfrastructureRootImpl extends org.hl7.rim.impl.RimObjectImpl implements InfrastructureRoot {

  private CS _nullFlavor;
  /** Gets the property nullFlavor.
      @see org.hl7.rim.InfrastructureRoot#getNullFlavor
  */
  public CS getNullFlavor() { return _nullFlavor; }
  /** Sets the property nullFlavor.
      @see org.hl7.rim.InfrastructureRoot#setNullFlavor
  */
  public void setNullFlavor(CS nullFlavor) {
    if(nullFlavor instanceof org.hl7.hibernate.ClonableCollection)
      nullFlavor = ((org.hl7.hibernate.ClonableCollection<CS>) nullFlavor).cloneHibernateCollectionIfNecessary();
    _nullFlavor = nullFlavor;
  }
  /** Sets the property nullFlavor. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InfrastructureRoot#setNullFlavor
  */
  public void setNullFlavorForHibernate(CS nullFlavor) {
    _nullFlavor = nullFlavor;
  }

  private SET<CS> _realmCode;
  /** Gets the property realmCode.
      @see org.hl7.rim.InfrastructureRoot#getRealmCode
  */
  public SET<CS> getRealmCode() { return _realmCode; }
  /** Sets the property realmCode.
      @see org.hl7.rim.InfrastructureRoot#setRealmCode
  */
  public void setRealmCode(SET<CS> realmCode) {
    if(realmCode instanceof org.hl7.hibernate.ClonableCollection)
      realmCode = ((org.hl7.hibernate.ClonableCollection<SET<CS>>) realmCode).cloneHibernateCollectionIfNecessary();
    _realmCode = realmCode;
  }
  /** Sets the property realmCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InfrastructureRoot#setRealmCode
  */
  public void setRealmCodeForHibernate(SET<CS> realmCode) {
    _realmCode = realmCode;
  }

  private II _typeId;
  /** Gets the property typeId.
      @see org.hl7.rim.InfrastructureRoot#getTypeId
  */
  public II getTypeId() { return _typeId; }
  /** Sets the property typeId.
      @see org.hl7.rim.InfrastructureRoot#setTypeId
  */
  public void setTypeId(II typeId) {
    if(typeId instanceof org.hl7.hibernate.ClonableCollection)
      typeId = ((org.hl7.hibernate.ClonableCollection<II>) typeId).cloneHibernateCollectionIfNecessary();
    _typeId = typeId;
  }
  /** Sets the property typeId. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InfrastructureRoot#setTypeId
  */
  public void setTypeIdForHibernate(II typeId) {
    _typeId = typeId;
  }

  private LIST<II> _templateId;
  /** Gets the property templateId.
      @see org.hl7.rim.InfrastructureRoot#getTemplateId
  */
  public LIST<II> getTemplateId() { return _templateId; }
  /** Sets the property templateId.
      @see org.hl7.rim.InfrastructureRoot#setTemplateId
  */
  public void setTemplateId(LIST<II> templateId) {
    if(templateId instanceof org.hl7.hibernate.ClonableCollection)
      templateId = ((org.hl7.hibernate.ClonableCollection<LIST<II>>) templateId).cloneHibernateCollectionIfNecessary();
    _templateId = templateId;
  }
  /** Sets the property templateId. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.InfrastructureRoot#setTemplateId
  */
  public void setTemplateIdForHibernate(LIST<II> templateId) {
    _templateId = templateId;
  }
  public Object clone() throws CloneNotSupportedException {
    InfrastructureRootImpl that = (InfrastructureRootImpl) super.clone();

    // deep clone of persistent component collections
    that.setRealmCode(that.getRealmCode());
    that.setTemplateId(that.getTemplateId());
    return that;
  }
}

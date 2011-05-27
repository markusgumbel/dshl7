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
package org.hl7.UUDD_MT333333UV.impl;

import org.hibernate.Criteria;
import org.hl7.hibernate.Persistence;

import org.hl7.UUDD_MT333333UV.Organization;
import org.hl7.rim.impl.AssociationSetImpl;
import org.hl7.UUDD_MT333333UV.impl.ProviderChoiceImpl;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.BAG;
import org.hl7.types.EN;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.UUDD_MT333333UV.Organization as a simple data holder bean.
    @see org.hl7.UUDD_MT333333UV.Organization
  */
public class OrganizationImpl extends org.hl7.UUDD_MT333333UV.impl.ProviderChoiceImpl implements Organization {

  private org.hl7.types.SET<II> _id;
  /** Gets the property id.
      @see org.hl7.UUDD_MT333333UV.Organization#getId
  */
  public org.hl7.types.SET<II> getId()  { 
    return _id;
  }
  /** Sets the property id.
      @see org.hl7.UUDD_MT333333UV.Organization#setId
  */
  public void setId(org.hl7.types.SET<II> id)  {
    if(id instanceof org.hl7.hibernate.ClonableCollection)
      id = ((org.hl7.hibernate.ClonableCollection<org.hl7.types.SET<II>>) id).cloneHibernateCollectionIfNecessary();
    _id = id;
  }

  private org.hl7.types.BAG<EN> _name;
  /** Gets the property name.
      @see org.hl7.UUDD_MT333333UV.Organization#getName
  */
  public org.hl7.types.BAG<EN> getName()  { 
    return _name;
  }
  /** Sets the property name.
      @see org.hl7.UUDD_MT333333UV.Organization#setName
  */
  public void setName(org.hl7.types.BAG<EN> name)  {
    if(name instanceof org.hl7.hibernate.ClonableCollection)
      name = ((org.hl7.hibernate.ClonableCollection<org.hl7.types.BAG<EN>>) name).cloneHibernateCollectionIfNecessary();
    _name = name;
  }
  public Object clone() throws CloneNotSupportedException {
    OrganizationImpl that = (OrganizationImpl) super.clone();

    // deep clone of persistent component collections
    that.setId(that.getId());
    that.setName(that.getName());
    return that;
  }
}

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

import org.hl7.rim.Organization;
import org.hl7.rim.impl.EntityImpl;
import org.hl7.types.BAG;
import org.hl7.types.AD;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Organization as a simple data holder bean.
    @see org.hl7.rim.Organization
  */
public class OrganizationImpl extends org.hl7.rim.impl.EntityImpl implements Organization {

  private BAG<AD> _addr;
  /** Gets the property addr.
      @see org.hl7.rim.Organization#getAddr
  */
  public BAG<AD> getAddr() { return _addr; }
  /** Sets the property addr.
      @see org.hl7.rim.Organization#setAddr
  */
  public void setAddr(BAG<AD> addr) {
    if(addr instanceof org.hl7.hibernate.ClonableCollection)
      addr = ((org.hl7.hibernate.ClonableCollection<BAG<AD>>) addr).cloneHibernateCollectionIfNecessary();
    _addr = addr;
  }
  /** Sets the property addr. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Organization#setAddr
  */
  public void setAddrForHibernate(BAG<AD> addr) {
    _addr = addr;
  }

  private CE _standardIndustryClassCode;
  /** Gets the property standardIndustryClassCode.
      @see org.hl7.rim.Organization#getStandardIndustryClassCode
  */
  public CE getStandardIndustryClassCode() { return _standardIndustryClassCode; }
  /** Sets the property standardIndustryClassCode.
      @see org.hl7.rim.Organization#setStandardIndustryClassCode
  */
  public void setStandardIndustryClassCode(CE standardIndustryClassCode) {
    if(standardIndustryClassCode instanceof org.hl7.hibernate.ClonableCollection)
      standardIndustryClassCode = ((org.hl7.hibernate.ClonableCollection<CE>) standardIndustryClassCode).cloneHibernateCollectionIfNecessary();
    _standardIndustryClassCode = standardIndustryClassCode;
  }
  /** Sets the property standardIndustryClassCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Organization#setStandardIndustryClassCode
  */
  public void setStandardIndustryClassCodeForHibernate(CE standardIndustryClassCode) {
    _standardIndustryClassCode = standardIndustryClassCode;
  }
  public Object clone() throws CloneNotSupportedException {
    OrganizationImpl that = (OrganizationImpl) super.clone();

    // deep clone of persistent component collections
    that.setAddr(that.getAddr());
    return that;
  }
}

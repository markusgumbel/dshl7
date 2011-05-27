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

import org.hl7.rim.Access;
import org.hl7.rim.impl.RoleImpl;
import org.hl7.types.CD;
import org.hl7.types.PQ;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Access as a simple data holder bean.
    @see org.hl7.rim.Access
  */
public class AccessImpl extends org.hl7.rim.impl.RoleImpl implements Access {

  private CD _approachSiteCode;
  /** Gets the property approachSiteCode.
      @see org.hl7.rim.Access#getApproachSiteCode
  */
  public CD getApproachSiteCode() { return _approachSiteCode; }
  /** Sets the property approachSiteCode.
      @see org.hl7.rim.Access#setApproachSiteCode
  */
  public void setApproachSiteCode(CD approachSiteCode) {
    if(approachSiteCode instanceof org.hl7.hibernate.ClonableCollection)
      approachSiteCode = ((org.hl7.hibernate.ClonableCollection<CD>) approachSiteCode).cloneHibernateCollectionIfNecessary();
    _approachSiteCode = approachSiteCode;
  }
  /** Sets the property approachSiteCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Access#setApproachSiteCode
  */
  public void setApproachSiteCodeForHibernate(CD approachSiteCode) {
    _approachSiteCode = approachSiteCode;
  }

  private CD _targetSiteCode;
  /** Gets the property targetSiteCode.
      @see org.hl7.rim.Access#getTargetSiteCode
  */
  public CD getTargetSiteCode() { return _targetSiteCode; }
  /** Sets the property targetSiteCode.
      @see org.hl7.rim.Access#setTargetSiteCode
  */
  public void setTargetSiteCode(CD targetSiteCode) {
    if(targetSiteCode instanceof org.hl7.hibernate.ClonableCollection)
      targetSiteCode = ((org.hl7.hibernate.ClonableCollection<CD>) targetSiteCode).cloneHibernateCollectionIfNecessary();
    _targetSiteCode = targetSiteCode;
  }
  /** Sets the property targetSiteCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Access#setTargetSiteCode
  */
  public void setTargetSiteCodeForHibernate(CD targetSiteCode) {
    _targetSiteCode = targetSiteCode;
  }

  private PQ _gaugeQuantity;
  /** Gets the property gaugeQuantity.
      @see org.hl7.rim.Access#getGaugeQuantity
  */
  public PQ getGaugeQuantity() { return _gaugeQuantity; }
  /** Sets the property gaugeQuantity.
      @see org.hl7.rim.Access#setGaugeQuantity
  */
  public void setGaugeQuantity(PQ gaugeQuantity) {
    if(gaugeQuantity instanceof org.hl7.hibernate.ClonableCollection)
      gaugeQuantity = ((org.hl7.hibernate.ClonableCollection<PQ>) gaugeQuantity).cloneHibernateCollectionIfNecessary();
    _gaugeQuantity = gaugeQuantity;
  }
  /** Sets the property gaugeQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Access#setGaugeQuantity
  */
  public void setGaugeQuantityForHibernate(PQ gaugeQuantity) {
    _gaugeQuantity = gaugeQuantity;
  }
  public Object clone() throws CloneNotSupportedException {
    AccessImpl that = (AccessImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

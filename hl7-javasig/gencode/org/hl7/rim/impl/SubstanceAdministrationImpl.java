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

import org.hl7.rim.SubstanceAdministration;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.CE;
import org.hl7.types.SET;
import org.hl7.types.CD;
import org.hl7.types.IVL;
import org.hl7.types.PQ;
import org.hl7.types.PQ;
import org.hl7.types.RTO;
import org.hl7.types.RTO;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.SubstanceAdministration as a simple data holder bean.
    @see org.hl7.rim.SubstanceAdministration
  */
public class SubstanceAdministrationImpl extends org.hl7.rim.impl.ActImpl implements SubstanceAdministration {

  private CE _routeCode;
  /** Gets the property routeCode.
      @see org.hl7.rim.SubstanceAdministration#getRouteCode
  */
  public CE getRouteCode() { return _routeCode; }
  /** Sets the property routeCode.
      @see org.hl7.rim.SubstanceAdministration#setRouteCode
  */
  public void setRouteCode(CE routeCode) {
    if(routeCode instanceof org.hl7.hibernate.ClonableCollection)
      routeCode = ((org.hl7.hibernate.ClonableCollection<CE>) routeCode).cloneHibernateCollectionIfNecessary();
    _routeCode = routeCode;
  }
  /** Sets the property routeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SubstanceAdministration#setRouteCode
  */
  public void setRouteCodeForHibernate(CE routeCode) {
    _routeCode = routeCode;
  }

  private SET<CD> _approachSiteCode;
  /** Gets the property approachSiteCode.
      @see org.hl7.rim.SubstanceAdministration#getApproachSiteCode
  */
  public SET<CD> getApproachSiteCode() { return _approachSiteCode; }
  /** Sets the property approachSiteCode.
      @see org.hl7.rim.SubstanceAdministration#setApproachSiteCode
  */
  public void setApproachSiteCode(SET<CD> approachSiteCode) {
    if(approachSiteCode instanceof org.hl7.hibernate.ClonableCollection)
      approachSiteCode = ((org.hl7.hibernate.ClonableCollection<SET<CD>>) approachSiteCode).cloneHibernateCollectionIfNecessary();
    _approachSiteCode = approachSiteCode;
  }
  /** Sets the property approachSiteCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SubstanceAdministration#setApproachSiteCode
  */
  public void setApproachSiteCodeForHibernate(SET<CD> approachSiteCode) {
    _approachSiteCode = approachSiteCode;
  }

  private IVL<PQ> _doseQuantity;
  /** Gets the property doseQuantity.
      @see org.hl7.rim.SubstanceAdministration#getDoseQuantity
  */
  public IVL<PQ> getDoseQuantity() { return _doseQuantity; }
  /** Sets the property doseQuantity.
      @see org.hl7.rim.SubstanceAdministration#setDoseQuantity
  */
  public void setDoseQuantity(IVL<PQ> doseQuantity) {
    if(doseQuantity instanceof org.hl7.hibernate.ClonableCollection)
      doseQuantity = ((org.hl7.hibernate.ClonableCollection<IVL<PQ>>) doseQuantity).cloneHibernateCollectionIfNecessary();
    _doseQuantity = doseQuantity;
  }
  /** Sets the property doseQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SubstanceAdministration#setDoseQuantity
  */
  public void setDoseQuantityForHibernate(IVL<PQ> doseQuantity) {
    _doseQuantity = doseQuantity;
  }

  private IVL<PQ> _rateQuantity;
  /** Gets the property rateQuantity.
      @see org.hl7.rim.SubstanceAdministration#getRateQuantity
  */
  public IVL<PQ> getRateQuantity() { return _rateQuantity; }
  /** Sets the property rateQuantity.
      @see org.hl7.rim.SubstanceAdministration#setRateQuantity
  */
  public void setRateQuantity(IVL<PQ> rateQuantity) {
    if(rateQuantity instanceof org.hl7.hibernate.ClonableCollection)
      rateQuantity = ((org.hl7.hibernate.ClonableCollection<IVL<PQ>>) rateQuantity).cloneHibernateCollectionIfNecessary();
    _rateQuantity = rateQuantity;
  }
  /** Sets the property rateQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SubstanceAdministration#setRateQuantity
  */
  public void setRateQuantityForHibernate(IVL<PQ> rateQuantity) {
    _rateQuantity = rateQuantity;
  }

  private SET<RTO> _doseCheckQuantity;
  /** Gets the property doseCheckQuantity.
      @see org.hl7.rim.SubstanceAdministration#getDoseCheckQuantity
  */
  public SET<RTO> getDoseCheckQuantity() { return _doseCheckQuantity; }
  /** Sets the property doseCheckQuantity.
      @see org.hl7.rim.SubstanceAdministration#setDoseCheckQuantity
  */
  public void setDoseCheckQuantity(SET<RTO> doseCheckQuantity) {
    if(doseCheckQuantity instanceof org.hl7.hibernate.ClonableCollection)
      doseCheckQuantity = ((org.hl7.hibernate.ClonableCollection<SET<RTO>>) doseCheckQuantity).cloneHibernateCollectionIfNecessary();
    _doseCheckQuantity = doseCheckQuantity;
  }
  /** Sets the property doseCheckQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SubstanceAdministration#setDoseCheckQuantity
  */
  public void setDoseCheckQuantityForHibernate(SET<RTO> doseCheckQuantity) {
    _doseCheckQuantity = doseCheckQuantity;
  }

  private SET<RTO> _maxDoseQuantity;
  /** Gets the property maxDoseQuantity.
      @see org.hl7.rim.SubstanceAdministration#getMaxDoseQuantity
  */
  public SET<RTO> getMaxDoseQuantity() { return _maxDoseQuantity; }
  /** Sets the property maxDoseQuantity.
      @see org.hl7.rim.SubstanceAdministration#setMaxDoseQuantity
  */
  public void setMaxDoseQuantity(SET<RTO> maxDoseQuantity) {
    if(maxDoseQuantity instanceof org.hl7.hibernate.ClonableCollection)
      maxDoseQuantity = ((org.hl7.hibernate.ClonableCollection<SET<RTO>>) maxDoseQuantity).cloneHibernateCollectionIfNecessary();
    _maxDoseQuantity = maxDoseQuantity;
  }
  /** Sets the property maxDoseQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SubstanceAdministration#setMaxDoseQuantity
  */
  public void setMaxDoseQuantityForHibernate(SET<RTO> maxDoseQuantity) {
    _maxDoseQuantity = maxDoseQuantity;
  }

  private CE _administrationUnitCode;
  /** Gets the property administrationUnitCode.
      @see org.hl7.rim.SubstanceAdministration#getAdministrationUnitCode
  */
  public CE getAdministrationUnitCode() { return _administrationUnitCode; }
  /** Sets the property administrationUnitCode.
      @see org.hl7.rim.SubstanceAdministration#setAdministrationUnitCode
  */
  public void setAdministrationUnitCode(CE administrationUnitCode) {
    if(administrationUnitCode instanceof org.hl7.hibernate.ClonableCollection)
      administrationUnitCode = ((org.hl7.hibernate.ClonableCollection<CE>) administrationUnitCode).cloneHibernateCollectionIfNecessary();
    _administrationUnitCode = administrationUnitCode;
  }
  /** Sets the property administrationUnitCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.SubstanceAdministration#setAdministrationUnitCode
  */
  public void setAdministrationUnitCodeForHibernate(CE administrationUnitCode) {
    _administrationUnitCode = administrationUnitCode;
  }
  public Object clone() throws CloneNotSupportedException {
    SubstanceAdministrationImpl that = (SubstanceAdministrationImpl) super.clone();

    // deep clone of persistent component collections
    that.setApproachSiteCode(that.getApproachSiteCode());
    that.setDoseCheckQuantity(that.getDoseCheckQuantity());
    that.setMaxDoseQuantity(that.getMaxDoseQuantity());
    return that;
  }
}

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

import org.hl7.rim.Place;
import org.hl7.rim.impl.EntityImpl;
import org.hl7.types.BL;
import org.hl7.types.AD;
import org.hl7.types.ED;
import org.hl7.types.ST;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Place as a simple data holder bean.
    @see org.hl7.rim.Place
  */
public class PlaceImpl extends org.hl7.rim.impl.EntityImpl implements Place {

  private BL _mobileInd;
  /** Gets the property mobileInd.
      @see org.hl7.rim.Place#getMobileInd
  */
  public BL getMobileInd() { return _mobileInd; }
  /** Sets the property mobileInd.
      @see org.hl7.rim.Place#setMobileInd
  */
  public void setMobileInd(BL mobileInd) {
    if(mobileInd instanceof org.hl7.hibernate.ClonableCollection)
      mobileInd = ((org.hl7.hibernate.ClonableCollection<BL>) mobileInd).cloneHibernateCollectionIfNecessary();
    _mobileInd = mobileInd;
  }
  /** Sets the property mobileInd. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Place#setMobileInd
  */
  public void setMobileIndForHibernate(BL mobileInd) {
    _mobileInd = mobileInd;
  }

  private AD _addr;
  /** Gets the property addr.
      @see org.hl7.rim.Place#getAddr
  */
  public AD getAddr() { return _addr; }
  /** Sets the property addr.
      @see org.hl7.rim.Place#setAddr
  */
  public void setAddr(AD addr) {
    if(addr instanceof org.hl7.hibernate.ClonableCollection)
      addr = ((org.hl7.hibernate.ClonableCollection<AD>) addr).cloneHibernateCollectionIfNecessary();
    _addr = addr;
  }
  /** Sets the property addr. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Place#setAddr
  */
  public void setAddrForHibernate(AD addr) {
    _addr = addr;
  }

  private ED _directionsText;
  /** Gets the property directionsText.
      @see org.hl7.rim.Place#getDirectionsText
  */
  public ED getDirectionsText() { return _directionsText; }
  /** Sets the property directionsText.
      @see org.hl7.rim.Place#setDirectionsText
  */
  public void setDirectionsText(ED directionsText) {
    if(directionsText instanceof org.hl7.hibernate.ClonableCollection)
      directionsText = ((org.hl7.hibernate.ClonableCollection<ED>) directionsText).cloneHibernateCollectionIfNecessary();
    _directionsText = directionsText;
  }
  /** Sets the property directionsText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Place#setDirectionsText
  */
  public void setDirectionsTextForHibernate(ED directionsText) {
    _directionsText = directionsText;
  }

  private ED _positionText;
  /** Gets the property positionText.
      @see org.hl7.rim.Place#getPositionText
  */
  public ED getPositionText() { return _positionText; }
  /** Sets the property positionText.
      @see org.hl7.rim.Place#setPositionText
  */
  public void setPositionText(ED positionText) {
    if(positionText instanceof org.hl7.hibernate.ClonableCollection)
      positionText = ((org.hl7.hibernate.ClonableCollection<ED>) positionText).cloneHibernateCollectionIfNecessary();
    _positionText = positionText;
  }
  /** Sets the property positionText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Place#setPositionText
  */
  public void setPositionTextForHibernate(ED positionText) {
    _positionText = positionText;
  }

  private ST _gpsText;
  /** Gets the property gpsText.
      @see org.hl7.rim.Place#getGpsText
  */
  public ST getGpsText() { return _gpsText; }
  /** Sets the property gpsText.
      @see org.hl7.rim.Place#setGpsText
  */
  public void setGpsText(ST gpsText) {
    if(gpsText instanceof org.hl7.hibernate.ClonableCollection)
      gpsText = ((org.hl7.hibernate.ClonableCollection<ST>) gpsText).cloneHibernateCollectionIfNecessary();
    _gpsText = gpsText;
  }
  /** Sets the property gpsText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Place#setGpsText
  */
  public void setGpsTextForHibernate(ST gpsText) {
    _gpsText = gpsText;
  }
  public Object clone() throws CloneNotSupportedException {
    PlaceImpl that = (PlaceImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

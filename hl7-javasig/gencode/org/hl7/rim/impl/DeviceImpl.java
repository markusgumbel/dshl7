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

import org.hl7.rim.Device;
import org.hl7.rim.impl.ManufacturedMaterialImpl;
import org.hl7.types.SC;
import org.hl7.types.CE;
import org.hl7.types.TS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Device as a simple data holder bean.
    @see org.hl7.rim.Device
  */
public class DeviceImpl extends org.hl7.rim.impl.ManufacturedMaterialImpl implements Device {

  private SC _manufacturerModelName;
  /** Gets the property manufacturerModelName.
      @see org.hl7.rim.Device#getManufacturerModelName
  */
  public SC getManufacturerModelName() { return _manufacturerModelName; }
  /** Sets the property manufacturerModelName.
      @see org.hl7.rim.Device#setManufacturerModelName
  */
  public void setManufacturerModelName(SC manufacturerModelName) {
    if(manufacturerModelName instanceof org.hl7.hibernate.ClonableCollection)
      manufacturerModelName = ((org.hl7.hibernate.ClonableCollection<SC>) manufacturerModelName).cloneHibernateCollectionIfNecessary();
    _manufacturerModelName = manufacturerModelName;
  }
  /** Sets the property manufacturerModelName. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Device#setManufacturerModelName
  */
  public void setManufacturerModelNameForHibernate(SC manufacturerModelName) {
    _manufacturerModelName = manufacturerModelName;
  }

  private SC _softwareName;
  /** Gets the property softwareName.
      @see org.hl7.rim.Device#getSoftwareName
  */
  public SC getSoftwareName() { return _softwareName; }
  /** Sets the property softwareName.
      @see org.hl7.rim.Device#setSoftwareName
  */
  public void setSoftwareName(SC softwareName) {
    if(softwareName instanceof org.hl7.hibernate.ClonableCollection)
      softwareName = ((org.hl7.hibernate.ClonableCollection<SC>) softwareName).cloneHibernateCollectionIfNecessary();
    _softwareName = softwareName;
  }
  /** Sets the property softwareName. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Device#setSoftwareName
  */
  public void setSoftwareNameForHibernate(SC softwareName) {
    _softwareName = softwareName;
  }

  private CE _localRemoteControlStateCode;
  /** Gets the property localRemoteControlStateCode.
      @see org.hl7.rim.Device#getLocalRemoteControlStateCode
  */
  public CE getLocalRemoteControlStateCode() { return _localRemoteControlStateCode; }
  /** Sets the property localRemoteControlStateCode.
      @see org.hl7.rim.Device#setLocalRemoteControlStateCode
  */
  public void setLocalRemoteControlStateCode(CE localRemoteControlStateCode) {
    if(localRemoteControlStateCode instanceof org.hl7.hibernate.ClonableCollection)
      localRemoteControlStateCode = ((org.hl7.hibernate.ClonableCollection<CE>) localRemoteControlStateCode).cloneHibernateCollectionIfNecessary();
    _localRemoteControlStateCode = localRemoteControlStateCode;
  }
  /** Sets the property localRemoteControlStateCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Device#setLocalRemoteControlStateCode
  */
  public void setLocalRemoteControlStateCodeForHibernate(CE localRemoteControlStateCode) {
    _localRemoteControlStateCode = localRemoteControlStateCode;
  }

  private CE _alertLevelCode;
  /** Gets the property alertLevelCode.
      @see org.hl7.rim.Device#getAlertLevelCode
  */
  public CE getAlertLevelCode() { return _alertLevelCode; }
  /** Sets the property alertLevelCode.
      @see org.hl7.rim.Device#setAlertLevelCode
  */
  public void setAlertLevelCode(CE alertLevelCode) {
    if(alertLevelCode instanceof org.hl7.hibernate.ClonableCollection)
      alertLevelCode = ((org.hl7.hibernate.ClonableCollection<CE>) alertLevelCode).cloneHibernateCollectionIfNecessary();
    _alertLevelCode = alertLevelCode;
  }
  /** Sets the property alertLevelCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Device#setAlertLevelCode
  */
  public void setAlertLevelCodeForHibernate(CE alertLevelCode) {
    _alertLevelCode = alertLevelCode;
  }

  private TS _lastCalibrationTime;
  /** Gets the property lastCalibrationTime.
      @see org.hl7.rim.Device#getLastCalibrationTime
  */
  public TS getLastCalibrationTime() { return _lastCalibrationTime; }
  /** Sets the property lastCalibrationTime.
      @see org.hl7.rim.Device#setLastCalibrationTime
  */
  public void setLastCalibrationTime(TS lastCalibrationTime) {
    if(lastCalibrationTime instanceof org.hl7.hibernate.ClonableCollection)
      lastCalibrationTime = ((org.hl7.hibernate.ClonableCollection<TS>) lastCalibrationTime).cloneHibernateCollectionIfNecessary();
    _lastCalibrationTime = lastCalibrationTime;
  }
  /** Sets the property lastCalibrationTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Device#setLastCalibrationTime
  */
  public void setLastCalibrationTimeForHibernate(TS lastCalibrationTime) {
    _lastCalibrationTime = lastCalibrationTime;
  }
  public Object clone() throws CloneNotSupportedException {
    DeviceImpl that = (DeviceImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

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
package org.hl7.rim.decorators;

import org.hl7.rim.Device;
import org.hl7.rim.decorators.ManufacturedMaterialDecorator;
import org.hl7.types.SC;
import org.hl7.types.CE;
import org.hl7.types.TS;

import org.hl7.types.impl.SCnull;
import org.hl7.types.impl.CEnull;
import org.hl7.types.impl.TSnull;
import /*org.hl7.rim.AssociationSet*/java.util.List;
import java.util.Collections;

/** Implementation of org.hl7.rim.Device as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.
 @see org.hl7.rim.Device
  */
public abstract class DeviceDecorator extends org.hl7.rim.decorators.ManufacturedMaterialDecorator implements Device {
  /** Property accessor, returns NULL/NA if not overloaded.manufacturerModelName.
      @see org.hl7.rim.Device#getManufacturerModelName
  */
  public SC getManufacturerModelName() { return SCnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.manufacturerModelName.
      @see org.hl7.rim.Device#setManufacturerModelName
  */
  public void setManufacturerModelName(SC manufacturerModelName) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.softwareName.
      @see org.hl7.rim.Device#getSoftwareName
  */
  public SC getSoftwareName() { return SCnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.softwareName.
      @see org.hl7.rim.Device#setSoftwareName
  */
  public void setSoftwareName(SC softwareName) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.localRemoteControlStateCode.
      @see org.hl7.rim.Device#getLocalRemoteControlStateCode
  */
  public CE getLocalRemoteControlStateCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.localRemoteControlStateCode.
      @see org.hl7.rim.Device#setLocalRemoteControlStateCode
  */
  public void setLocalRemoteControlStateCode(CE localRemoteControlStateCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.alertLevelCode.
      @see org.hl7.rim.Device#getAlertLevelCode
  */
  public CE getAlertLevelCode() { return CEnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.alertLevelCode.
      @see org.hl7.rim.Device#setAlertLevelCode
  */
  public void setAlertLevelCode(CE alertLevelCode) { /*throw new UnsupportedOperationException();*/ }
  /** Property accessor, returns NULL/NA if not overloaded.lastCalibrationTime.
      @see org.hl7.rim.Device#getLastCalibrationTime
  */
  public TS getLastCalibrationTime() { return TSnull.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }
  /** Property mutator, does nothing if not overloaded.lastCalibrationTime.
      @see org.hl7.rim.Device#setLastCalibrationTime
  */
  public void setLastCalibrationTime(TS lastCalibrationTime) { /*throw new UnsupportedOperationException();*/ }
}

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
package org.hl7.rim;

import org.hl7.rim.ManufacturedMaterial;
import org.hl7.types.SC;
import org.hl7.types.CE;
import org.hl7.types.TS;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of ManufacturedMaterial used in an activity, without being substantially changed through that activity. The kind
   of device is identified by the code attribute inherited from Entity.
</p>
<p><i>Usage:</i> This includes durable (reusable) medical equipment as well as disposable equipment.
</p>
*/
public interface Device extends org.hl7.rim.ManufacturedMaterial {

  /**<p>The human designated moniker for a device assigned by the manufacturer.</p>
<p><i>Examples:</i> Perkin Elmer 400 Inductively Coupled Plasma Unit
</p>
  */
  SC getManufacturerModelName();
  /** Sets the property manufacturerModelName.
      @see #getManufacturerModelName
  */
  void setManufacturerModelName(SC manufacturerModelName);

  /**<p>The moniker, version and release of the software that operates the device as assigned by the software manufacturer or developer.</p>
<p><i>Examples:</i> Agilent Technologies Chemstation A.08.xx
</p>
  */
  SC getSoftwareName();
  /** Sets the property softwareName.
      @see #getSoftwareName
  */
  void setSoftwareName(SC softwareName);

  /**<p>A value representing the current state of control associated with the device. </p>
<p><i>Examples:</i> A device can either work autonomously (localRemoteControlStateCode="Local") or it can be controlled by another system (localRemoteControlStateCode="Remote").
</p>
<p><i>Rationale:</i> The control status of a device must be communicated between devices prior to remote commands being transmitted. If the device
   is not in "Remote" status then external commands will be ignored.
</p>
  */
  CE getLocalRemoteControlStateCode();
  /** Sets the property localRemoteControlStateCode.
      @see #getLocalRemoteControlStateCode
  */
  void setLocalRemoteControlStateCode(CE localRemoteControlStateCode);

  /**<p>A value representing the current functional activity of an automated device. </p>
<p><i>Examples:</i> Normal, Warning, Critical
</p>
<p><i>Constraints:</i> The value of the attribute is determined by the device.
</p>
  */
  CE getAlertLevelCode();
  /** Sets the property alertLevelCode.
      @see #getAlertLevelCode
  */
  void setAlertLevelCode(CE alertLevelCode);

  /**<p>The date/time of the last calibration of the device.</p>
<p><i>Rationale:</i> Devices are required to be recalibrated at specific intervals to ensure they are performing within specifications. The accepted
   interval between calibrations varies with protocols. Thus for results to be valid, the precise time/date of last calibration
   is a critical component. 
</p>
  */
  TS getLastCalibrationTime();
  /** Sets the property lastCalibrationTime.
      @see #getLastCalibrationTime
  */
  void setLastCalibrationTime(TS lastCalibrationTime);
}

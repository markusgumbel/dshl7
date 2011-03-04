/*
Copyright 2010 the original author or authors.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package net.gumbix.hl7dsl.DSL

import org.hl7.rim.{RimObjectFactory, Device}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "Device"
 * @author Ahmet Gül
 */

class DeviceDSL(override val entity: Device) extends ManufacturedMaterialDSL {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Device").asInstanceOf[Device])
  }

  /**
   * @return SC
   */
  def manufacturerModelName: SC = entity.getManufacturerModelName

  def manufacturerModelName_=(v: SC) {
    entity.setManufacturerModelName(v)
  }

  /**
   * @return SC
   */
  def softwareName: SC = entity.getSoftwareName

  def softwareName_=(v: SC) {
    entity.setSoftwareName(v)
  }

  /**
   * @return II
   */
  def localRemoteControlStateCode: CE = entity.getLocalRemoteControlStateCode

  def localRemoteControlStateCode_=(v: CE) {
    entity.setLocalRemoteControlStateCode(v)
  }

  /**
   * @return CE
   */
  def alertLevelCode: CE = entity.getAlertLevelCode

  def alertLevelCode_=(v: CE) {
    entity.setAlertLevelCode(v)
  }

  /**
   * @return TS
   */
  def lastCalibrationTime: TS = entity.getLastCalibrationTime

  def lastCalibrationTime_=(v: String) {
    entity.setLastCalibrationTime(v)
  }
}
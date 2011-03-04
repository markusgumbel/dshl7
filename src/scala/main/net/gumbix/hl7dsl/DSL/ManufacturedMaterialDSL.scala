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

import org.hl7.rim.{RimObjectFactory, ManufacturedMaterial}
import org.hl7.types._

/**
 * Wrapper Class for the RIM Class "ManufacturedMaterial"
 * @author Ahmet Gül
 */

class ManufacturedMaterialDSL(manuMaterial: ManufacturedMaterial)
        extends MaterialDSL(manuMaterial) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("ManufacturedMaterial").asInstanceOf[ManufacturedMaterial])
  }

  /**
   * @return ST
   */
  def lotNumberTest: ST = manuMaterial.getLotNumberText

  def lotNumberTest_=(v: ST) {
    manuMaterial.setLotNumberText(v)
  }

  /**
   * @return IVL[TS]
   */
  def expirationTime: IVL[TS] = manuMaterial.getExistenceTime

  def expirationTime_=(v: IVL[TS]) {
    manuMaterial.setExpirationTime(v)
  }

  /**
   * @return IVL[TS]
   */
  def stabilityTime: IVL[TS] = manuMaterial.getStabilityTime

  def stabilityTime_=(v: IVL[TS]) {
    manuMaterial.setStabilityTime(v)
  }
}
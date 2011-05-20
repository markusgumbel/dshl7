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

import org.hl7.rim.{RimObjectFactory, Supply}
import org.hl7.types._

/**
 * Wrapper Class for RIM Class "Supply"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class SupplyDSL(supply: Supply) extends ActDSL(supply) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Supply").asInstanceOf[Supply])
  }

  /**
   * @return PQ
   */
  def quantity: PQ = supply.getQuantity

  def quantity_=(v: PQ) {
    supply.setQuantity(v)
  }

  /**
   * @return IVL[TS]
   */
  def expectedUseTime: IVL[TS] = supply.getExpectedUseTime

  def expectedUseTime_=(v: IVL[TS]) {
    supply.setExpectedUseTime(v)
  }

}
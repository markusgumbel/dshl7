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

import org.hl7.rim.{RimObjectFactory, Container}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "Container"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class ContainerDSL(override val entity: Container) extends ManufacturedMaterialDSL {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Container").asInstanceOf[Container])
  }

  /**
   * @return PQ
   */
  def capacityQuantity: PQ = entity.getCapacityQuantity

  def capacityQuantity_=(v: PQ) {
    entity.setCapacityQuantity(v)
  }

  /**
   * @return PQ
   */
  def heightQuantity: PQ = entity.getHeightQuantity

  def heightQuantity_=(v: PQ) {
    entity.setHeightQuantity(v)
  }

  /**
   * @return PQ
   */
  def diameterQuantity: PQ = entity.getDiameterQuantity

  def diameterQuantity_=(v: PQ) {
    entity.setDiameterQuantity(v)
  }

  /**
   * @return CE
   */
  def capTypeCode: Tuple2[String, String] = entity.getCapTypeCode

  def capTypeCode_=(v: Tuple2[String, String]) {
    entity.setCapTypeCode(v)
  }

  /**
   * @return CE
   */
  def separatorTypeCode: CE = entity.getSeparatorTypeCode

  def separatorTypeCode_=(v: CE) {
    entity.setSeparatorTypeCode(v)
  }

  /**
   * @return PQ
   */
  def barrierDeltaQuantity: PQ = entity.getBarrierDeltaQuantity

  def barrierDeltaQuantity_=(v: PQ) {
    entity.setBarrierDeltaQuantity(v)
  }

  /**
   * @return PQ
   */
  def bottomDeltaQuantity: PQ = entity.getBottomDeltaQuantity

  def bottomDeltaQuantity_=(v: PQ) {
    entity.setBottomDeltaQuantity(v)
  }
}
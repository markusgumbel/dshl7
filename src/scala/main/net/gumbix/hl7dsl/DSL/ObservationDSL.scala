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

import org.hl7.rim.{RimObjectFactory, Observation}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "Observation"
 * @author Ahmet Gül
 */

class ObservationDSL(observation: Observation) extends ActDSL(observation) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Observation").asInstanceOf[Observation])
  }

  /**
   * @return ANY
   */
  def value: ANY = observation.getValue

  def value_=(v: ANY) {
    observation.setValue(v)
  }

  /**
   * @return SET[CE]
   */
  def interpretationCode: SET[CE] = observation.getInterpretationCode

  def interpretationCode_=(v: SET[CE]) {
    observation.setInterpretationCode(v)
  }

  /**
   * @return SET[CE]
   */
  def methodCode: SET[CE] = observation.getMethodCode

  def methodCode_=(v: SET[CE]) {
    observation.setMethodCode(v)
  }

  /**
   * @return SET[CD]
   */
  def targetSiteCodeCode: SET[CD] = observation.getTargetSiteCode

  def targetSiteCodeCode_=(v: SET[CD]) {
    observation.setTargetSiteCode(v)
  }

}
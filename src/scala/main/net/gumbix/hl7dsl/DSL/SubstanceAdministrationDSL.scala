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

import org.hl7.rim.{RimObjectFactory, SubstanceAdministration}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "SubstanceAdministration"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class SubstanceAdministrationDSL(substAdm: SubstanceAdministration) extends ActDSL(substAdm) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("SubstanceAdministration").asInstanceOf[SubstanceAdministration])
  }

  /**
   * @return CE
   */
  def routeCode: CE = substAdm.getRouteCode

  def routeCode_=(v: CE) {
    substAdm.setRouteCode(v)
  }

  /**
   * @return SET[CD]
   */
  def approachSiteCode: SET[CD] = substAdm.getApproachSiteCode

  def approachSiteCode_=(v: SET[CD]) {
    substAdm.setApproachSiteCode(v)
  }

  /**
   * @return IVL[PQ]
   */
  def doseQuantity: IVL[PQ] = substAdm.getDoseQuantity

  def doseQuantity_=(v: IVL[PQ]) {
    substAdm.setDoseQuantity(v)
  }

  /**
   * @return IVL[PQ]
   */
  def rateQuantity: IVL[PQ] = substAdm.getRateQuantity

  def rateQuantity_=(v: IVL[PQ]) {
    substAdm.setRateQuantity(v)
  }

  /**
   * @return SET[RTO]
   */
  def doseCheckQuantity: SET[RTO] = substAdm.getDoseCheckQuantity

  def doseCheckQuantity_=(v: SET[RTO]) {
    substAdm.setDoseCheckQuantity(v)
  }

  /**
   * @return SET[RTO]
   */
  def maxDoseQuantity: SET[RTO] = substAdm.getMaxDoseQuantity

  def maxDoseQuantity_=(v: SET[RTO]) {
    substAdm.setMaxDoseQuantity(v)
  }

  /**
   * @return CE
   */
  def administrationUnitCode: CE = substAdm.getAdministrationUnitCode

  def administrationUnitCode_=(v: CE) {
    substAdm.setAdministrationUnitCode(v)
  }

  // ---------------- methodCode: SET <CD> ------------

}
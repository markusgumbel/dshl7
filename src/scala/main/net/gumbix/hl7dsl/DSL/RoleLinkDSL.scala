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

import org.hl7.rim.{RimObjectFactory, RoleLink, Role}
import org.hl7.types._

/**
 * Wrapper Class for the RIM Class "RoleLink"
 * @author Ahmet Gül
 */

class RoleLinkDSL(val roleLink: RoleLink) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("RoleLink").asInstanceOf[RoleLink])
  }

  /**
   * @return CS
   */
  def typeCode: CS = roleLink.getTypeCode

  def typeCode_=(v: CS) {
    roleLink.setTypeCode(v)
  }

  /**
   * @return INT
   */
  def priorityNumber: INT = roleLink.getPriorityNumber

  def priorityNumber_=(v: INT) {
    roleLink.setPriorityNumber(v)
  }

  /**
   * @return IVL[TS]
   */
  def effectiveTime: IVL[TS] = roleLink.getEffectiveTime

  def effectiveTime_=(v: IVL[TS]) {
    roleLink.setEffectiveTime(v)
  }

  /**
   * @return RoleDSL
   */
  def target = new RoleDSL(roleLink.getTarget)

  //------------ target: Role ------
  def target_=(v: Role) {
    roleLink.setTarget(v)
  }

  /**
   * @return RoleDSL
   */
  def source = new RoleDSL(roleLink.getSource)

  //------------ source: Role ------
  def source_=(v: Role) {
    roleLink.setSource(v)
  }
}
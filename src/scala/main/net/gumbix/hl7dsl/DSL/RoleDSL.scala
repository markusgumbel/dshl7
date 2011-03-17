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

import org.hl7.rim.{RimObjectFactory, Role, RoleLink, Entity}
import net.gumbix.hl7dsl.helper.ImplicitDef._
import org.hl7.types._
import java.util.ArrayList
import scala.collection.JavaConversions._
import java.util.List
import net.gumbix.hl7dsl.helper.{RimRelationshipOne, RimRelationshipMany}

/**
 * Wrapper Class for RIM Class "Role"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class RoleDSL(val role: Role) extends RimDSL(role) {

  /**
   * @param cloneName Required to navigate through the object graph.
   */
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Role").asInstanceOf[Role])
  }

  /**
   * @return CS
   */
  def classCode: CS = role.getClassCode

  def classCode_=(v: CS) {
    role.setClassCode(v)
  }

  /**
   * @return SET[II]
   */
  def id: SET[II] = role.getId

  def id_=(id: SET[II]) {
    role.setId(id)
  }

  /**
   * @return CE
   */
  def code: CE = role.getCode

  def code_=(v: CE) {
    role.setCode(v)
  }

  /**
   * @return BL
   */
  def negationInd: BL = role.getNegationInd

  def negationInd_=(v: BL) {
    role.setNegationInd(v)
  }

  /**
   * @return BAG[EN]
   */
  def name: BAG[EN] = role.getName

  def name_=(v: BAG[EN]) {
    role.setName(v)
  }

  /**
   * @return BAG[AD]
   */
  def addr: BAG[AD] = role.getAddr

  def addr_=(v: BAG[AD]) {
    role.setAddr(v)
  }

  /**
   * @return BAG[TEL]
   */
  def telecom: BAG[TEL] = role.getTelecom

  def telecom_=(v: BAG[TEL]) {
    role.setTelecom(v)
  }

  /**
   * @return CS
   */
  def statusCode: CS = role.getStatusCode

  def statusCode_=(v: CS) {
    role.setStatusCode(v)
  }

  /**
   * @return IVL[TS]
   */
  def effectiveTime: IVL[TS] = role.getEffectiveTime

  def effectiveTime_=(v: IVL[TS]) {
    role.setEffectiveTime(v)
  }

  /**
   * @return ED
   */
  def certificateText: ED = role.getCertificateText

  def certificateText_=(v: ED) {
    role.setCertificateText(v)
  }

  /**
   * @return SET[CE]
   */
  def confidentialityCode: SET[CE] = role.getConfidentialityCode

  def confidentialityCode_=(v: SET[CE]) {
    role.setConfidentialityCode(v)
  }

  /**
   * @return LIST[INT]
   */
  def positionNumber: LIST[INT] = role.getPositionNumber

  def positionNumber_=(v: LIST[INT]) {
    role.setPositionNumber(v)
  }

  def player = {
    new RimRelationshipOne[Entity, EntityDSL](
      {e => role.setPlayer(e)},
      role.getPlayer,
      {e => new EntityDSL(e)})
  }

  def scoper = {
    new RimRelationshipOne[Entity, EntityDSL](
      {s => role.setScoper(s)},
      role.getScoper,
      s => new EntityDSL(s)) // TODO
  }

  /**
   * @return RoleLinkDSL
   */
  def inboundLink(i: Int) = new RoleLinkDSL(role.getInboundLink.get(i))

  /**
   * @return List[RoleLinkDSL]
   */
  def inboundLink: List[RoleLinkDSL] = {
    val list: List[RoleLinkDSL] = new ArrayList[RoleLinkDSL]
    (role.getInboundLink).toList.map(a => list.add(new RoleLinkDSL(a)))
    list
  }

  def inboundLink_=(p: RoleLink) {
    role.addInboundLink(p)
  }

  /**
   * @return RoleLinkDSL
   */
  def outboundLink(i: Int) = new RoleLinkDSL(role.getOutboundLink.get(i))

  /**
   * @return List[RoleLinkDSL]
   */
  def outboundLink: List[RoleLinkDSL] = {
    val list: List[RoleLinkDSL] = new ArrayList[RoleLinkDSL]
    (role.getOutboundLink).toList.map(a => list.add(new RoleLinkDSL(a)))
    list
  }

  def outboundLink_=(p: RoleLink) {
    role.addOutboundLink(p)
  }
}
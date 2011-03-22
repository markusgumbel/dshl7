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

import org.hl7.rim.{Role, RimObjectFactory, Entity, LanguageCommunication}
import org.hl7.types._
import java.util.{ArrayList, List}
import scala.collection.JavaConversions._
import net.gumbix.hl7dsl.helper.ImplicitDef._
import net.gumbix.hl7dsl.helper.{Name, RimRelationshipMany, RimRelationshipOne}

/**
 * Wrapper Class for the RIM Class "Entity"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */
class EntityDSL(val entity: Entity) extends RimDSL(entity) {

  /**
   * @param cloneName Required to navigate through the object graph.
   */
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Entity").asInstanceOf[Entity])
  }

  /**
   * @return CS
   */
  def classCode: CS = entity.getClassCode

  def classCode_=(v: CS) {
    entity.setClassCode(v)
  }

  /**
   * @return CS
   */
  def determinerCode: CS = entity.getDeterminerCode

  def determinerCode_=(v: CS) {
    entity.setDeterminerCode(v)
  }

  /**
   * @return SET[II]
   */
  def id: SET[II] = entity.getId

  def id_=(v: SET[II]) {
    entity.setId(v)
  }

  /**
   * @return CE
   */
  def code: CE = entity.getCode

  def code_=(v: CE) {
    entity.setCode(v)
  }

  /**
   * @return SET[PQ]
   */
  def quantity: SET[PQ] = entity.getQuantity

  def quantity_=(v: SET[PQ]) {
    entity.setQuantity(v)
  }

  /**
   * @return BAG[EN]
   */
  def name = {
    def nameChanged(name: Name) {
      entity.setName(name.toRSBag)
    }
    new Name(entity.getName, nameChanged)
  }

  def name_=(pn: Name) {
    entity.setName(pn.toRSBag)
  }

  /**
   * @return ED
   */
  def desc: ED = entity.getDesc

  def desc_=(v: ED) {
    entity.setDesc(v)
  }

  /**
   * @return CS
   */
  def statusCode: CS = entity.getStatusCode

  def statusCode_=(v: CS) {
    entity.setStatusCode(v)
  }

  /**
   * @return IVL[TS]
   */
  def existenceTime: IVL[TS] = entity.getExistenceTime

  def existenceTime_=(v: IVL[TS]) {
    entity.setExistenceTime(v)
  }

  /**
   * @return BAG[TEL]
   */
  def telecom: BAG[TEL] = entity.getTelecom

  def telecom_=(v: BAG[TEL]) {
    entity.setTelecom(v)
  }

  /**
   * @return CE
   */
  def riskCode: CE = entity.getRiskCode

  def riskCode_=(v: CE) {
    entity.setRiskCode(v)
  }

  /**
   * @return CE
   */
  def handlingCode: CE = entity.getHandlingCode

  def handlingCode_=(v: CE) {
    entity.setHandlingCode(v)
  }

  val languageCommunication = {
    new RimRelationshipMany[LanguageCommunication, LanguageCommunicationDSL](
      v => entity.addLanguageCommunication(v),
      entity.getLanguageCommunication(),
      p => new LanguageCommunicationDSL(p))
  }

  val scopedRole = {
    new RimRelationshipMany[Role, RoleDSL](
      v => entity.addScopedRole(v),
      entity.getScopedRole(),
      p => new RoleDSL(p))
  }

  val playedRole = {
    new RimRelationshipMany[Role, RoleDSL](
      v => entity.addPlayedRole(v),
      entity.getPlayedRole(),
      p => new RoleDSL(p))
  }
}
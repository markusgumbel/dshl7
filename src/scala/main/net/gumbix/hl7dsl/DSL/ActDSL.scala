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

import org.hl7.rim.{RimObjectFactory, Act, Participation, ActRelationship}
import org.hl7.types._
import java.util.{ArrayList, List}
import scala.collection.JavaConversions._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "Act"
 * @author Ahmet Gül
 */

class ActDSL(val act: Act) {

  /**
   * @param cloneName Required to navigate through the object graph.
   */
  def this(cloneName: String) = {
    this (RimObjectFactory.getInstance.createRimObject("Act").asInstanceOf[Act])
    cloneCode = (cloneName, "egal")
  }

  /**
   * @return II
   */
  def typeId = act.getTypeId

  def typeId_=(v: II) {
    act.setTypeId(v)
  }

  /**
   * @return CS
   */
  def cloneCode = act.getCloneCode

  def cloneCode_=(v: CS) {
    act.setCloneCode(v)
  }

  /**
   * @return CS
   */
  def classCode: CS = act.getClassCode

  def classCode_=(v: CS) {
    act.setClassCode(v)
  }

  /**
   * @return CS
   */
  def moodCode: CS = act.getMoodCode

  def moodCode_=(v: CS) {
    act.setMoodCode(v)
  }

  /**
   * @return SET[II]
   */
  def id: SET[II] = act.getId

  def id_=(id: SET[II]) {
    act.setId(id)
  }

  /**
   * @return CD
   */
  def code: CD = act.getCode

  def code_=(v: CD) {
    act.setCode(v)
  }

  /**
   * @return BL
   */
  def negationInd: BL = act.getNegationInd

  def negationInd_=(v: BL) {
    act.setNegationInd(v)
  }

  /**
   * @return ST
   */
  def derivationExpr: ST = act.getDerivationExpr

  def derivationExpr_=(v: ST) {
    act.setDerivationExpr(v)
  }

  /**
   * @return ED
   */
  def title: ED = act.getTitle

  def title_=(v: ED) {
    act.setTitle(v)
  }

  /**
   * @return ED
   */
  def text: ED = act.getText

  def text_=(v: ED) {
    act.setText(v)
  }

  /**
   * @return CS
   */
  def statusCode: CS = act.getStatusCode

  def statusCode_=(v: CS) {
    act.setStatusCode(v)
  }

  /**
   * @return SET[TS]
   */
  def effectiveTime: SET[TS] = act.getEffectiveTime

  def effectiveTime_=(time: SET[TS]) {
    act.setEffectiveTime(time)
  }

  /**
   * @return SET[TS]
   */
  def activityTime: SET[TS] = act.getActivityTime

  def activityTime_=(time: SET[TS]) {
    act.setActivityTime(time)
  }

  /**
   * @return TS
   */
  def availabilityTime: TS = act.getAvailabilityTime

  def availabilityTime_=(v: TS) {
    act.setAvailabilityTime(v)
  }

  /**
   * @return SET[CE]
   */
  def priorityCode: SET[CE] = act.getPriorityCode

  def priorityCode_=(v: SET[CE]) {
    act.setConfidentialityCode(v)
  }

  /**
   * @return SET[CE]
   */
  def confidentialityCode: SET[CE] = act.getConfidentialityCode

  def confidentialityCode_=(v: SET[CE]) {
    act.setConfidentialityCode(v)
  }

  /**
   * @return IVL[INT]
   */
  def repeatNumber: IVL[INT] = act.getRepeatNumber

  def repeatNumber_=(v: IVL[INT]) {
    act.setRepeatNumber(v)
  }

  /**
   * @return BL
   */
  def interruptibleInd: BL = act.getInterruptibleInd

  def interruptibleInd_=(v: BL) {
    act.setInterruptibleInd(v)
  }

  /**
   * @return CE
   */
  def levelCode: CE = act.getLevelCode

  def levelCode_=(v: CE) {
    act.setLevelCode(v)
  }

  /**
   * @return BL
   */
  def independentInd: BL = act.getIndependentInd

  def independentInd_=(v: BL) {
    act.setIndependentInd(v)
  }

  /**
   * @return CE
   */
  def uncertaintyCode: CE = act.getUncertaintyCode

  def uncertaintyCode_=(v: CE) {
    act.setUncertaintyCode(v)
  }

  /**
   * @return SET[CE]
   */
  def reasonCode: SET[CE] = act.getReasonCode

  def reasonCode_=(v: SET[CE]) {
    act.setReasonCode(v)
  }

  /**
   * @return CE
   */
  def languageCode: CE = act.getLanguageCode

  def languageCode_=(v: CE) {
    act.setLanguageCode(v)
  }

  /**
   * @return ParticipationDSL
   */
  def participation(i: Int) = new ParticipationDSL(act.getParticipation.get(i))

  /**
   * @return List[ParticipationDSL]
   */
  def participations: List[ParticipationDSL] = {
    val list = new ArrayList[ParticipationDSL]
    (act.getParticipation).toList.map(a => list.add(new ParticipationDSL(a)))
    list
  }

  def participation = act.getParticipation.get(0)

  def participation_=(p: Participation) {
    act.addParticipation(p)
  }

  def addParticipation(p: Participation) {
    act.addParticipation(p)
  }

  def ap(cloneName: String, dox: => Unit) {
    val p = new ParticipationDSL(cloneName) {
      dox
    }
    act.addParticipation(p)
  }
  def addParticipation(cloneName: String)(dox: => Unit) = ap(cloneName, dox)

  /**
   * @return ActRelationshipDSL
   */
  def inboundRelationship(i: Int) = new ActRelationshipDSL(act.getInboundRelationship.get(i))

  /**
   * @return List[ActRelationshipDSL]
   */
  def inboundRelationship: List[ActRelationshipDSL] = {
    val list: List[ActRelationshipDSL] = new ArrayList[ActRelationshipDSL]
    (act.getInboundRelationship).toList.map(a => list.add(new ActRelationshipDSL(a)))
    list
  }

  def inboundRelationship_=(v: ActRelationship) {
    act.addInboundRelationship(v)
  }

  /**
   * @return ActRelationshipDSL
   */
  def outboundRelationship(i: Int) = new ActRelationshipDSL(act.getOutboundRelationship.get(i))

  /**
   * @return List[ActRelationshipDSL]
   */
  def outboundRelationship: List[ActRelationshipDSL] = {
    val list: List[ActRelationshipDSL] = new ArrayList[ActRelationshipDSL]
    (act.getOutboundRelationship).toList.map(a => list.add(new ActRelationshipDSL(a)))
    list
  }

  def outboundRelationship_=(v: ActRelationship) {
    act.addOutboundRelationship(v)
  }

  def getAct: Act = act

}
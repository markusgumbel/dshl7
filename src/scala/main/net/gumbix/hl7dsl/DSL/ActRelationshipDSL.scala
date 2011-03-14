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

import org.hl7.rim.{RimObjectFactory, ActRelationship, Act}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._
import net.gumbix.hl7dsl.build.{RimRelationshipOne, RimRelationshipMany}

/**
 * Wrapper Class for the RIM Class "ActRelationship"
 * @author Ahmet Gül
 */

class ActRelationshipDSL(actRelationship: ActRelationship) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("ActRelationship").asInstanceOf[ActRelationship])
  }

  /**
   * @return CS
   */
  def cloneCode: CS = actRelationship.getCloneCode

  def cloneCode_=(v: CS) {
    actRelationship.setCloneCode(v)
  }

  /**
   * @return CS
   */
  def typeCode: CS = actRelationship.getTypeCode

  def typeCode_=(v: CS) {
    actRelationship.setTypeCode(v)
  }

  /**
   * @return BL
   */
  def inversionInd: BL = actRelationship.getInversionInd

  def inversionInd_=(v: BL) {
    actRelationship.setInversionInd(v)
  }

  /**
   * @return CS
   */
  def contextControlCode: CS = actRelationship.getContextControlCode

  def contextControlCode_=(v: CS) {
    actRelationship.setContextControlCode(v)
  }

  /**
   * @return BL
   */
  def contextConductionInd: BL = actRelationship.getContextConductionInd

  def contextConductionInd_=(v: BL) {
    actRelationship.setContextConductionInd(v)
  }

  /**
   * @return INT
   */
  def sequenceNumber: INT = actRelationship.getSequenceNumber

  def sequenceNumber_=(v: INT) {
    actRelationship.setSequenceNumber(v)
  }

  /**
   * @return INT
   */
  def priorityNumber: INT = actRelationship.getPriorityNumber

  def priorityNumber_=(v: INT) {
    actRelationship.setPriorityNumber(v)
  }

  /**
   * @return PQ
   */
  def pauseQuantity: PQ = actRelationship.getPauseQuantity

  def pauseQuantity_=(v: PQ) {
    actRelationship.setPauseQuantity(v)
  }

  /**
   * @return CS
   */
  def checkpointCode: CS = actRelationship.getCheckpointCode

  def checkpointCode_=(v: CS) {
    actRelationship.setCheckpointCode(v)
  }

  /**
   * @return CS
   */
  def splitCode: CS = actRelationship.getSplitCode

  def splitCode_=(c: Tuple2[String, String]) {
    actRelationship.setSplitCode((c._1, c._2))
  }

  /**
   * @return CS
   */
  def joinCode: CS = actRelationship.getJoinCode

  def joinCode_=(v: CS) {
    actRelationship.setJoinCode(v)
  }

  /**
   * @return BL
   */
  def negationInd: BL = actRelationship.getNegationInd

  def negationInd_=(v: BL) {
    actRelationship.setNegationInd(v)
  }

  /**
   * @return CS
   */
  def conjunctionCode: CS = actRelationship.getConjunctionCode

  def conjunctionCode_=(v: CS) {
    actRelationship.setConjunctionCode(v)
  }

  /**
   * @return ST
   */
  def localVariableName: ST = actRelationship.getLocalVariableName

  def localVariableName_=(v: ST) {
    actRelationship.setLocalVariableName(v)
  }

  /**
   * @return BL
   */
  def seperatableInd: BL = actRelationship.getSeperatableInd

  def seperatableInd_=(v: BL) {
    actRelationship.setSeperatableInd(v)
  }

  /**
   * @return CS
   */
  def subsetCode: CS = actRelationship.getSubsetCode

  def subsetCode_=(v: CS) {
    actRelationship.setSubsetCode(v)
  }

  /**
   * @return CE
   */
  def uncertaintyCode: CE = actRelationship.getUncertaintyCode

  def uncertaintyCode_=(v: CE) {
    actRelationship.setUncertaintyCode(v)
  }

  /**
   * @return ActDSL
   */
  def source = new ActDSL(actRelationship.getSource)

  def source_=(v: Act) {
    actRelationship.setSource(v)
  }

  def target = {
    new RimRelationshipOne[Act, ActDSL](
      v => actRelationship.setTarget(v),
      actRelationship.getTarget,
      v => new ActDSL(v))
  }

  /**
   * @return ActRelationship
   */
  def getActRelationship: ActRelationship = actRelationship
}
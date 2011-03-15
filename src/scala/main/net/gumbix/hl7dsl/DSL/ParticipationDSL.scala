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

import org.hl7.rim.{Participation}
import org.hl7.rim.{Patient}
import org.hl7.rim.{RimObjectFactory, Role}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._
import util.Random
import net.gumbix.hl7dsl.build.{RimRelationshipOne, RimRelationshipMany}

/**
 * Wrapper Class for the RIM Class "Participation"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
class ParticipationDSL(val participation: Participation)
        extends RimDSL(participation) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Participation").asInstanceOf[Participation])
  }

  /**
   * @return CS
   */
  def typeCode: CS = participation.getTypeCode

  def typeCode_=(v: CS) {
    participation.setTypeCode(v)
  }

  /**
   * @return BAG[AD]
   */
  def functionCode = participation.getFunctionCode

  def functionCode_=(v: CD) {
    participation.setFunctionCode(v)
  }

  /**
   * @return CS
   */
  def contextControlCode = participation.getContextControlCode

  def contextControlCode_=(v: CS) {
    participation.setContextControlCode(v)
  }

  /**
   * @return BAG[AD]
   */
  def sequenceNumber = participation.getSequenceNumber

  def sequenceNumber_=(v: INT) {
    participation.setSequenceNumber(v)
  }

  /**
   * @return BL
   */
  def negationInd = participation.getNegationInd

  def negationInd_=(v: BL) {
    participation.setNegationInd(v)
  }

  /**
   * @return ED
   */
  def noteText = participation.getNoteText

  def noteText_=(v: ED) {
    participation.setNoteText(v)
  }

  /**
   * @return IVL[TS]
   */
  def time = participation.getTime

  def time_=(v: IVL[TS]) {
    participation.setTime(v)
  }

  /**
   * @return CE
   */
  def modeCode = participation.getModeCode

  def modeCode_=(v: CE) {
    participation.setModeCode(v)
  }

  /**
   * @return CE
   */
  def awarenessCode = participation.getAwarenessCode

  def awarenessCode_=(v: CE) {
    participation.setAwarenessCode(v)
  }

  /**
   * @return BAG[AD]
   */
  def signatureCode = participation.getSignatureCode

  def signatureCode_=(v: CE) {
    participation.setSignatureCode(v)
  }

  /**
   * @return ED
   */
  def signatureTest = participation.getSignatureText

  def signatureTest_=(v: ED) {
    participation.setSignatureText(v)
  }

  /**
   * @return BL
   */
  def performInd = participation.getPerformInd

  def performInd_=(v: BL) {
    participation.setPerformInd(v)
  }

  /**
   * @return CE
   */
  def substitutionConditionCode = participation.getSubstitutionConditionCode

  def substitutionConditionCode_=(v: CE) {
    participation.setSubstitutionConditionCode(v)
  }

  /**
   * @return CS
   */
  def subsetCode = participation.getSubsetCode

  def subsetCode_=(v: CS) {
    participation.setSubsetCode(v)
  }

  def role = {
    new RimRelationshipOne[Role, RoleDSL](
      p => participation.setRole(p),
      participation.getRole,
      p => new RoleDSL(p))
  }
}
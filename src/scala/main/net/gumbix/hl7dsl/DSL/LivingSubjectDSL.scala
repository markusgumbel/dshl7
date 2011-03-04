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

import org.hl7.rim.{RimObjectFactory, LivingSubject}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "LivingSubject"
 * @author Ahmet Gül
 */

class LivingSubjectDSL(livingSubject: LivingSubject) extends EntityDSL(livingSubject) {

  def this(cloneName: String) = {
    this (RimObjectFactory.getInstance.createRimObject("LivingSubject").asInstanceOf[LivingSubject])
    cloneCode = (cloneName, "egal")
  }

  /**
   * @return CE
   */
  def administrativeGenderCode: CE = livingSubject.getAdministrativeGenderCode

  def administrativeGenderCode_=(v: CE) {
    livingSubject.setAdministrativeGenderCode(v)
  }

  /**
   * @return TS
   */
  def birthTime: TS = livingSubject.getBirthTime

  def birthTime_=(v: TS) {
    livingSubject.setBirthTime(v)
  }

  /**
   * @return BL
   */
  def deceasedInd: BL = livingSubject.getDeceasedInd

  def deceasedInd_=(v: BL) {
    livingSubject.setDeceasedInd(v)
  }

  /**
   * @return BL
   */
  def multipleBirthInd: BL = livingSubject.getMultipleBirthInd

  def multipleBirthInd_=(v: BL) {
    livingSubject.setMultipleBirthInd(v)
  }

  /**
   * @return INT
   */
  def multipleBirthOrderNumber: INT = livingSubject.getMultipleBirthOrderNumber

  def multipleBirthOrderNumber_=(v: INT) {
    livingSubject.setMultipleBirthOrderNumber(v)
  }

  /**
   * @return BL
   */
  def organDonorInd: BL = livingSubject.getOrganDonorInd

  def organDonorInd_=(v: Boolean) {
    livingSubject.setOrganDonorInd(v)
  }
}



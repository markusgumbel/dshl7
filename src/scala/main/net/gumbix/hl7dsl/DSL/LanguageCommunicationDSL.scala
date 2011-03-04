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

import org.hl7.rim.{RimObjectFactory, LanguageCommunication}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "LanguageCommunication"
 * @author Ahmet Gül
 */

class LanguageCommunicationDSL(val language: LanguageCommunication) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("LanguageCommunication").asInstanceOf[LanguageCommunication])
  }

  /**
   * @return CE
   */
  def languageCode: CE = language.getLanguageCode

  def languageCode_=(v: CE) {
    language.setLanguageCode(v)
  }

  /**
   * @return CE
   */
  def modeCode: CE = language.getModeCode

  def modeCode_=(v: CE) {
    language.setModeCode(v)
  }

  /**
   * @return CE
   */
  def proficiencyLevelCode: CE = language.getProficiencyLevelCode

  def proficiencyLevelCode_=(v: CE) {
    language.setProficiencyLevelCode(v)
  }

  /**
   * @return BL
   */
  def preferenceInd: BL = language.getPreferenceInd

  def preferenceInd_=(v: Boolean) {
    language.setPreferenceInd(v)
  }

  /**
   * @return LanguageCommunication
   */
  def getLanguageCommunication: LanguageCommunication = language
}
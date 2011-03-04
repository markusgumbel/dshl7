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

import org.hl7.rim.{RimObjectFactory, NonPersonLivingSubject}
import org.hl7.types._

/**
 * Wrapper Class for the RIM Class "NonPersonLivingSubject"
 * @author Ahmet Gül
 */

class NonPersonLivingSubjectDSL(nonLivingSubject: NonPersonLivingSubject)
        extends LivingSubjectDSL(nonLivingSubject) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("NonPersonLivingSubject").asInstanceOf[NonPersonLivingSubject])
  }

  /**
   * @return ED
   */
  def strainText: ED = nonLivingSubject.getStrainText

  def strainText_=(v: ED) {
    nonLivingSubject.setStrainText(v)
  }

  /**
   * @return CE
   */
  def genderStatusCode: CE = nonLivingSubject.getGenderStatusCode

  def genderStatusCode_=(v: CE) {
    nonLivingSubject.setGenderStatusCode(v)
  }
}
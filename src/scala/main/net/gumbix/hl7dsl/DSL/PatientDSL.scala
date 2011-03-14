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

import org.hl7.rim.{Patient}
import org.hl7.types._
import org.hl7.rim.{RimObjectFactory}
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "Patient"
 * @author Ahmet Gül
 */

class PatientDSL(patient: Patient) extends RoleDSL(patient) {

  /**
   * @param cloneName Required to navigate through the object graph.
   */
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Patient").asInstanceOf[Patient])
  }
  
  /**
   * @return CE
   */
  def veryImportantPersonCode: CE = patient.getVeryImportantPersonCode

  def veryImportantPersonCode_=(v: CE) {
    patient.setVeryImportantPersonCode(v)
  }
}
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

import org.hl7.rim.{RimObjectFactory, Person}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._
import org.hl7.rim.impl.PersonImpl

/**
 * Wrapper class for the RIM Class "Person"
 * @author Ahmet Gül
 */

class PersonDSL(person: Person) extends LivingSubjectDSL(person) {

  /**
   * @param cloneName Required to navigate through the object graph.
   */
  def this(cloneName: String) = {
    this (RimObjectFactory.getInstance.createRimObject("Person").asInstanceOf[Person])
    cloneCode = (cloneName, "egal")
  }  

  /**
   * @return BAG[AD]
   */
  def addr: BAG[AD] = person.getAddr

  def addr_=(v: BAG[AD]) {
    person.setAddr(v)
  }

  /**
   * @return CE
   */
  def maritalStatusCode: CE = person.getMaritalStatusCode

  def maritalStatusCode_=(v: CE) {
    person.setMaritalStatusCode(v)
  }

  /**
   * @return CE
   */
  def educationLevelCode: CE = person.getEducationLevelCode

  def educationLevelCode_=(v: CE) {
    person.setEducationLevelCode(v)
  }

  /**
   * @return SET[CE]
   */
  def raceCode: SET[CE] = person.getRaceCode

  def raceCode_=(v: SET[CE]) {
    person.setRaceCode(v)
  }

  /**
   * @return SET[CE]
   */
  def disabilityCode: SET[CE] = person.getDisabilityCode

  def disabilityCode_=(v: SET[CE]) {
    person.setDisabilityCode(v)
  }

  /**
   * @return CE
   */
  def livingArrangementCode: CE = person.getLivingArrangementCode

  def livingArrangementCode_=(v: CE) {
    person.setLivingArrangementCode(v)
  }

  /**
   * @return CE
   */
  def religiousAffiliationCode: CE = person.getReligiousAffiliationCode

  def religiousAffiliationCode_=(v: CE) {
    person.setReligiousAffiliationCode(v)
  }

  /**
   * @return SET[CE]
   */
  def ethnicGroupCode: SET[CE] = person.getEthnicGroupCode

  def ethnicGroupCode_=(v: SET[CE]) {
    person.setEthnicGroupCode(v)
  }

  def cloneAs(cloneName: String) = {
    val clonedPerson = person.asInstanceOf[PersonImpl].clone()
    val newPerson = new PersonDSL(clonedPerson.asInstanceOf[Person])
    newPerson.cloneCode = (cloneName, "egal")
    newPerson
  }

  /**
   * @return Person
   */
  // def getPerson: Person = entity
}
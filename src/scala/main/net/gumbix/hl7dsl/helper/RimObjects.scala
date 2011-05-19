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
package net.gumbix.hl7dsl.helper

import org.hl7.rim._
import org.hl7.types._

/**
 * Object to create the RimObject from the RIM Classes
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

object RimObjects    {

  def RIMObject(name: String) = {
    RimObjectFactory.getInstance.createRimObject(name)
  }

  def Document = {
    RimObjectFactory.getInstance.createRimObject("Document").asInstanceOf[Document]
  }

  def Participation = {
    RimObjectFactory.getInstance.createRimObject("Participation").asInstanceOf[Participation]
  }

  def Patient = {
    RimObjectFactory.getInstance.createRimObject("Patient").asInstanceOf[Patient]
  }

  def Person = {
    RimObjectFactory.getInstance.createRimObject("Person").asInstanceOf[Person]
  }

  def Role = {
    RimObjectFactory.getInstance.createRimObject("Role").asInstanceOf[Role]
  }

  def Organization = {
    RimObjectFactory.getInstance.createRimObject("Organization").asInstanceOf[Organization]
  }

  def ActRelationship = {
    RimObjectFactory.getInstance.createRimObject("ActRelationship").asInstanceOf[ActRelationship]
  }

  def Act = {
    RimObjectFactory.getInstance.createRimObject("Act").asInstanceOf[Act]
  }

  def Observation = {
    RimObjectFactory.getInstance.createRimObject("Observation").asInstanceOf[Observation]
  }
}
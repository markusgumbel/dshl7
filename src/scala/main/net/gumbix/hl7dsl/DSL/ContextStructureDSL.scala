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

import org.hl7.rim.{RimObjectFactory, ContextStructure}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for RIM Class "ContextStructure"
 * @author Ahmet Gül
 */

class ContextStructureDSL(contextStructure: ContextStructure)
        extends ActDSL(contextStructure) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("ContextStructure").asInstanceOf[ContextStructure])
  }

  /**
   * @return II
   */
  def setId: II = contextStructure.getSetId

  def setId_=(v: II) {
    contextStructure.setSetId(v)
  }

  /**
   * @return INT
   */
  def versionNumber: INT = contextStructure.getVersionNumber

  def versionNumber_=(v: INT) {
    contextStructure.setVersionNumber(v)
  }

}
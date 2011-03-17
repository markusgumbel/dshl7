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

import org.hl7.rim.{RimObjectFactory, Organization}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "Organization"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class OrganizationDSL(organization: Organization)
        extends EntityDSL(organization) {
  
  /**
   * @param cloneName Required to navigate through the object graph.
   */
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Organization").asInstanceOf[Organization])
  }

  /**
   * @return BAG[AD]
   */
  def addr: BAG[AD] = organization.getAddr

  def addr_=(v: BAG[AD]) {
    organization.setAddr(v)
  }

  /**
   * @return CE
   */
  def standardIndustryClassCode: CE = organization.getStandardIndustryClassCode

  def standardIndustryClassCode_=(v: CE) {
    organization.setStandardIndustryClassCode(v)
  }
}
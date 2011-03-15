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

import org.hl7.types.impl._
import org.hl7.types._
import org.hl7.types.enums._
import scala.collection._
import scala.collection.JavaConversions._
import java.util.ArrayList
import java.util.List

/**
 * Class to build a Person or Organization Name
 * @author Ahmet Gül
 */

class Name {
  
  private val vf = new ValueFactoryImpl
  private val list: List[ENXP] = new ArrayList[ENXP]

  // ---------------- given: ENXP --------------------
  def given: String = DatatypeTool.EntityNameTool.getGivenName(personName)

  def given_=(v: String) {
    var givenENXP: ENXP = vf.PNXPvalueOf(v, EntityNamePartType.Given, null)
    list.add(givenENXP)
  }

  // ---------------- family: ENXP --------------------
  def family: String = DatatypeTool.EntityNameTool.getFamilyName(personName)

  def family_=(v: String) {
    var familyENXP: ENXP = vf.PNXPvalueOf(v, EntityNamePartType.Family, null)
    list.add(familyENXP)
  }

  // ---------------- suffix: ENXP --------------------
  def suffix: String = ""

  def suffix_=(v: String) {
    var suffixENXP: ENXP = vf.PNXPvalueOf(v, EntityNamePartType.Suffix, null)
    list.add(suffixENXP)
  }

  // ---------------- prefix: ENXP --------------------
  def prefix: String = ""

  def prefix_=(v: String) {
    var prefixENXP: ENXP = vf.PNXPvalueOf(v, EntityNamePartType.Prefix, null)
    list.add(prefixENXP)
  }

  // ---------------- orgName: ENXP --------------------
  def orgName: String = DatatypeTool.EntityNameTool.getTrivialName(personName)

  def orgName_=(v: String) {
    var orgENXP: ENXP = vf.ONXPvalueOf(v, null, null)
    list.add(orgENXP)
  }

  // TODO not used?
  // def getPersonNameList = list

  def personName = {
    val ad: EN = ENimpl.valueOf(list)
    val adList: List[EN] = new ArrayList[EN]
    adList.add(ad)
    BAGjuCollectionAdapter.valueOf(adList)
  }
}
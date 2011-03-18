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

import org.hl7.types._
import enums._
import impl._
import scala.collection._
import scala.collection.JavaConversions._
import java.util.ArrayList
import java.util.List
import org.hl7.types.DatatypeTool.EntityNameTool
import javax.print.attribute.standard.MediaSize.NA

/**
 * Class to build a Person or Organization Name
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class Name(var name: BAG[EN]) {
  def this() = {
    this (new BAGjuCollectionAdapter[EN](new java.util.ArrayList()))
  }

  def family = DatatypeTool.EntityNameTool.getFamilyName(name)

  def family_=(n: String) {
    name = DatatypeTool.EntityNameTool.setFamilyName(name, n)
    val i = 0
    // TODO how to propagate changes???
  }

  def given = DatatypeTool.EntityNameTool.getGivenName(name)

  def given_=(n: String) {
    name = DatatypeTool.EntityNameTool.setGivenName(name, n)
    val i = 0
  }

  val enxpList = new ArrayList[ENXP]

  def prefix = ""

  def prefix_=(n: String) {
    enxpList.add(ENXPimpl.valueOf(n,
      EntityNamePartType.Prefix, DSETnull.NA.asInstanceOf[DSET[CS]]))
  }

  def suffix = ""

  def suffix_=(n: String) {
  }

  def orgName = ""

  def orgName_=(n: String) {
  }
}
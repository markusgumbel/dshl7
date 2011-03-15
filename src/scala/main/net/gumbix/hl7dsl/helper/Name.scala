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
import org.hl7.types.DatatypeTool.EntityNameTool

/**
 * Class to build a Person or Organization Name
 * @author Ahmet Gül
 */

class Name(val name: BAG[EN]) {

  def this() = {
    this(new BAGjuCollectionAdapter[EN](new java.util.ArrayList()))
  }

  def family = DatatypeTool.EntityNameTool.getFamilyName(name)

  def family_=(n: String) {
    val newName = DatatypeTool.EntityNameTool.setFamilyName(name, n)
    // Copy changes:
    // TODO how to propagate changes???

    for (name <- name.iterator) {
      for (entry <- name) {
        val a: ENXP = entry
        /*
        if (a.type.equals(EntityNamePartType.Family)) {
          
        }
        */
      }
    }
    // EntityNamePartType.Family
    val i = 0
  }

  def given = DatatypeTool.EntityNameTool.getGivenName(name)

  def given_=(n: String) {
    val newName = DatatypeTool.EntityNameTool.setGivenName(name, n)
  }

  def prefix = ""

  def prefix_=(n: String) {
  }

  def suffix = ""

  def suffix_=(n: String) {
  }

  def orgName = ""

  def orgName_=(n: String) {
  }
}
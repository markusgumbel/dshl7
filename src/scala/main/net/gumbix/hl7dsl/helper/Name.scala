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
import org.hl7.types.enums.EntityNamePartType._

/**
 * Class to build a Person or Organization Name
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class Name(name: BAG[EN], change: Name => Unit) {
  def this() = {
    this (new BAGjuCollectionAdapter[EN](new java.util.ArrayList()),
      {a: Name =>})
  }

  private val wrapper: PersonNameWrapper = {
    val it = name.iterator
    var wrapper = new PersonNameWrapper(ENimpl.valueOf(new ArrayList[ENXP]()))
    while (it.hasNext) {
      val name = it.next
      wrapper = new PersonNameWrapper(name)
    }
    wrapper
  }

  // TODO remove bag
  def toRSBag = {
    val rsList = new ArrayList[EN]()
    rsList.add(wrapper.currentRimValue)
    BAGjuListAdapter.valueOf(rsList)
  }

  def family = wrapper.get(Family).get

  def family_=(n: String) {
    wrapper.set(Family, n)
    change(this)
  }

  def given = wrapper.get(Given).get

  def given_=(n: String) {
    wrapper.set(Given, n)
    change(this)
  }

  def prefix = wrapper.get(Prefix).get

  def prefix_=(n: String) {
    wrapper.set(Prefix, n)
    change(this)
    /*
    enxpList.add(ENXPimpl.valueOf(n,
      EntityNamePartType.Prefix, DSETnull.NA.asInstanceOf[DSET[CS]]))
      */
  }

  def suffix = wrapper.get(Suffix).get

  def suffix_=(n: String) {
    wrapper.set(Suffix, n)
    change(this)
  }

  def orgName = ""

  def orgName_=(n: String) {
  }
}
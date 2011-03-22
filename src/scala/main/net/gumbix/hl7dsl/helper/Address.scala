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
import java.util.ArrayList
import java.util.List

/**
 * Class to build an address
 * @author Ahmet Gül (guel.ahmet@hotmail.de), Markus Gumbel
 */
class Address(addr: BAG[AD], change: Address => Unit) {
  def this() = {
    this (new BAGjuCollectionAdapter[AD](new java.util.ArrayList()),
      {a: Address =>})
  }

  private val vf: ValueFactory = new ValueFactoryImpl

  private val list: AddressWrapper = {
    val it = addr.iterator
    var wrapper = new AddressWrapper()
    while (it.hasNext) {
      val ad: AD = it.next
      wrapper = new AddressWrapper(ad)
    }
    wrapper
  }

  // ---------------- city: ADXP --------------------
  def city: String = list.get(AddressPartType.Municipality).get

  def city_=(v: String) {
    list.set(AddressPartType.Municipality, v)
    change(this)
  }

  // TODO remove bag
  def toRSBag = {
    val rsList = new ArrayList[AD]()
    rsList.add(list.currentRimValue)
    BAGjuListAdapter.valueOf(rsList)
  }
}
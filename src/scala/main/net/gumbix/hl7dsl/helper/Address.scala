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
import org.hl7.types.enums.AddressPartType._
import scala.collection._
import java.util.ArrayList
import java.util.List

/**
 * Class to build an address
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 * @author Markus Gumbel
 */
class Address(addr: BAG[AD], change: Address => Unit) {
  def this() = {
    this (new BAGjuCollectionAdapter[AD](new java.util.ArrayList()),
      {a: Address =>})
  }

  private val wrapper: AddressWrapper = {
    var wrapper = new AddressWrapper(ADimpl.valueOf(new ArrayList[ADXP]()))
    if (addr != null) {
      val it = addr.iterator
      while (it.hasNext) {
        val ad: AD = it.next
        wrapper = new AddressWrapper(ad)
      }
    }
    wrapper
  }

  // TODO remove bag
  def toRSBag = {
    val rsList = new ArrayList[AD]()
    rsList.add(wrapper.currentRimValue)
    BAGjuListAdapter.valueOf(rsList)
  }

  def toRS = wrapper.currentRimValue

  def country = wrapper.get(Country)

  def country_=(v: String) {
    wrapper.set(Country, v)
    change(this)
  }

  def county = wrapper.get(CountyOrParish)

  def county_=(v: String) {
    wrapper.set(CountyOrParish, v)
    change(this)
  }

  def postalCode = wrapper.get(PostalCode)

  def postalCode_=(v: String) {
    wrapper.set(PostalCode, v)
    change(this)
  }

  def city = wrapper.get(Municipality)

  def city_=(v: String) {
    wrapper.set(Municipality, v)
    change(this)
  }

  def houseNumber = wrapper.get(BuildingNumber)

  def houseNumber_=(v: String) {
    wrapper.set(BuildingNumber, v)
    change(this)
  }

  def streetName = wrapper.get(StreetName)

  def streetName_=(v: String) {
    wrapper.set(StreetName, v)
    change(this)
  }

  def streetAddressLine = wrapper.get(StreetAddressLine)

  def streetAddressLine_=(v: String) {
    wrapper.set(StreetAddressLine, v)
    change(this)
  }

  def additionalLocator = wrapper.get(AdditionalLocator)

  def additionalLocator_=(v: String) {
    wrapper.set(AdditionalLocator, v)
    change(this)
  }

  def postBox = wrapper.get(PostBox)

  def postBox_=(v: String) {
    wrapper.set(PostBox, v)
    change(this)
  }

  override def toString = {
    val buffer = new StringBuffer
    buffer.append(postalCode.getOrElse("") + " ")
    buffer.append(city.getOrElse("") + " ")
    buffer.toString
  }
}
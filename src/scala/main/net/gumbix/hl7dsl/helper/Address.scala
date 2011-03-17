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

class Address {
  private val vf: ValueFactory = new ValueFactoryImpl
  private val list: List[ADXP] = new ArrayList[ADXP]

  implicit def stringToADXP(in: String) = ADXPimpl.valueOf(in)

  // ---------------- country: ADXP --------------------
  def country: String = ""

  def country_=(v: String) {
    list.add(vf.ADXPvalueOf(v, AddressPartType.Country))
  }

  // ---------------- county: ADXP --------------------
  def county: String = ""

  def county_=(v: String) {
    var countyAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.CountyOrParish)
    list.add(countyAsADXP)
  }

  // ---------------- city: ADXP --------------------
  def city: String = ""

  def city_=(v: String) {
    var cityAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.Municipality)
    list.add(cityAsADXP)
  }

  // ---------------- postalCode: ADXP --------------------
  def postalCode: String = ""

  def postalCode_=(v: String) {
    var postalCodeAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.PostalCode)
    list.add(postalCodeAsADXP)
  }

  // ---------------- houseNumber: ADXP --------------------
  def houseNumber: String = ""

  def houseNumber_=(v: String) {
    var houseNumberAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.BuildingNumber)
    list.add(houseNumberAsADXP)
  }

  // ---------------- streetName: ADXP --------------------
  def streetName: String = ""

  def streetName_=(v: String) {
    var streetNameAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.StreetName)
    list.add(streetNameAsADXP)
  }

  // ---------------- streetAddressLine: ADXP --------------------
  def streetAddressLine: String = ""

  def streetAddressLine_=(v: String) {
    var streetAddressLineAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.StreetAddressLine)
    list.add(streetAddressLineAsADXP)
  }

  // ---------------- additionalLocator: ADXP --------------------
  def additionalLocator: String = ""

  def additionalLocator_=(v: String) {
    var additionalLocatorAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.AdditionalLocator)
    list.add(additionalLocatorAsADXP)
  }

  // ---------------- postBox: ADXP --------------------
  def postBox: String = ""

  def postBox_=(v: String) {
    var postBoxAsADXP: ADXP = vf.ADXPvalueOf(v, AddressPartType.PostBox)
    list.add(postBoxAsADXP)
  }

  def getAddress = list
}
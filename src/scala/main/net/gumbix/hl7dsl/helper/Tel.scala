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
import scala.collection.JavaConversions._
import java.util.ArrayList
import java.util.List

/**
 * Class to Build the Telecommunication Address
 * @author Ahmet Gül
 */

class Tel {
  val list: List[TEL] = new ArrayList[TEL]

  //-------------- Tel  -----------------------

  def number: List[TEL] = list

  def number_=(v: Tuple2[String, TelecommunicationAddressUse]){
    var numberTel = TELimpl.valueOf(v._1, v._2.toString, null)
    list.add(numberTel)
  }

  def getTel = BAGjuCollectionAdapter.valueOf(list)

}
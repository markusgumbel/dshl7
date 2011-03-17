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
 * Class to Build a Timestamp (TS) or interval of Time as Timestamp (IVL<TS>)
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class Time {
  private var l: TS = null
  private var h: TS = null
  private var lc: BL = BLimpl.TRUE
  private var hc: BL = BLimpl.TRUE
  private var c: TS = null
  private var w: TS = null
  private var time: IVL[TS] = null

  def high: TS = h
  def high_=(h: String) {this.h = TSjuDateAdapter.valueOf(h)}

  def low: TS = l
  def low_=(l: String) {this.l = TSjuDateAdapter.valueOf(l)}

  def lowClosed: BL = lc
  def low_=(l: Boolean) {this.lc = lc}

  def highClosed: BL = hc
  def high_=(l: Boolean) {this.hc = hc}

  def getTimeASIVL = IVLimpl.valueOf(lc, l, h, hc)
}
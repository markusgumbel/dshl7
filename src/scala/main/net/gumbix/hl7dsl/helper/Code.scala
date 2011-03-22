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
import java.util.ArrayList
import java.util.List

/**
 * Class to Build the Datatype CD
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class Code {
  var qList: List[CR] = new ArrayList[CR]
  var tList: List[CD] = new ArrayList[CD]
  private var c: ST = null
  private var cS: UID = null
  private var cSN: ST = null
  private var cSV: ST = null
  private var dN: ST = null
  private var oT: ED = null
  private var oTS: ST = null

  def code: ST = c

  def code_=(c: String) = {this.c = STjlStringAdapter.valueOf(c)}


  def codeSystem: UID = cS

  def codeSystem_=(cS: String) = {this.cS = UIDimpl.valueOf(cS)}


  def codeSystemName: ST = cSN

  def codeSystemName_=(cSN: String) {this.cSN = STjlStringAdapter.valueOf(cSN)}


  def codeSystemVersion: ST = cSV

  def codeSystemVersion_=(cSV: String) {this.cSV = STjlStringAdapter.valueOf(cSV)}


  def displayName: ST = dN

  def displayName_=(dN: String) {this.dN = STjlStringAdapter.valueOf(dN)}


  def originalText: ED = oT

  def originalText_=(oT: ED) {this.oT = oT}


  def originalTextString: ST = oTS

  def originalTextString_=(oT: ST) {this.oTS = oTS}


  def qualifier: List[CR] = qList

  def qualifier_=(q: CR) {qList.add(q)}


  def translation: List[CD] = tList

  def translation_=(t: CD) {tList.add(t)}

  def getCodeAsCD: CD = CDimpl.valueOf(c, cS, cSN, cSV, dN, oT, LISTjuListAdapter.valueOf(qList), SETjuSetAdapter.valueOf(tList))

  def getCodeAsCV: CV = CVimpl.valueOf(c, cS, oTS, dN, cSN, cSV)

}
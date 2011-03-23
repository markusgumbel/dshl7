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
import org.hl7.rim._
import java.util.{ArrayList, List}
import net.gumbix.hl7dsl.DSL._

/**
 * Class with all implicit conversions for the DSL. All conversions
 * are listed pairwise forward-backward.
 * TODO remove redundant implicit defs.
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 * @author Markus Gumbel
 */
object ImplicitDef {

  // -------------- all RIM relationships ----------------------------------
  implicit def asActRelationship(actRelationship: ActRelationshipDSL) =
    actRelationship.getActRelationship

  implicit def asParticipation(participation: ParticipationDSL) =
    participation.participation

  implicit def asAct(act: ActDSL) = act.act

  implicit def asRole(role: RoleDSL) = role.role

  implicit def asEntity(entity: EntityDSL) = entity.entity

  // -------------- Implicit for basic data types -------------------------

  implicit def booleanToBL(v: Boolean): BL = BLimpl.valueOf(v)

  implicit def intToINT(v: Int): INT = INTlongAdapter.valueOf(v)

  implicit def INTToInt(int: INT): Int = int.intValue

  implicit def stringToST(v: String): ST = STjlStringAdapter.valueOf(v)

  implicit def STToString(st: ST): String = st.toString

  implicit def ED2String(ed: ED): String = ed.toString

  /**
   * Strings to Encapsulated Data
   * @param ( text : String, mediaType : String )
   * @return Encapsulated Data as ED
   */
  implicit def string2ED(v: Tuple2[String, String]): ED = {
    var text: String = v._1
    var mediaTypeString: String = v._2
    var charSetString: String = "UTF-8"
    var languageString: String = "de"
    var compressionString: String = null
    var reference: TEL = null
    var integrityCheck: BIN = BINbyteArrayImpl.valueOf("SHA-1")
    var integrityCheckAlgorithmString: String = null
    var thumbnail: ED = null
    EDjlStringAdapter.valueOf(text, mediaTypeString, charSetString, languageString, compressionString, reference, integrityCheck, integrityCheckAlgorithmString, thumbnail)
  }


  // -------------- Implicit for coded data types ----------------------

  implicit def stringToCE(s: String) = CSimpl.valueOf(s, "")
  implicit def CEToString(ce: CS) = ce.code.toString

  implicit def stringTupleToCS(v: Tuple2[String, String]) = {
    CSimpl.valueOf(v._1, v._2)
  }
  implicit def CSToStringTuple(cs: CS) = (cs.code.toString, cs.codeSystem.toString)


  implicit def stringsTripleToCE(v: Tuple3[String, String, String]) = {
    CEimpl.valueOf(v._1, v._2, v._3)
  }

  implicit def CEToStringTuple(ce: CE): Tuple2[String, String] =
    (ce.code.toString, ce.codeSystem.toString)


  implicit def codeToCE(code: Code): CE = code.getCodeAsCV

  // Convenience
  /* implied by CE
  implicit def codeToSETCD(code: Code): SET[CD] = {
    val array = new ArrayList[CD]
    array.add(code.getCodeAsCD)
    SETjuSetAdapter.valueOf(array)
  }
  */

  // Convenience
  implicit def CEToSETCE(ce: CE) = {
    val array = new ArrayList[CE]
    array.add(ce)
    SETjuSetAdapter.valueOf(array)
  }

  // Convenience (this is code -> CE -> SET_CE)
  implicit def codeToSETCE(code: Code): SET[CE] = {
    val array = new ArrayList[CE]
    array.add(code.getCodeAsCV)
    SETjuSetAdapter.valueOf(array)
  }

  // Most generic case:
  implicit def codeListToSetCE(list: scala.List[Code]) = {
    val l = new ArrayList[CE]
    list.foreach(c => l.add(c.getCodeAsCV))
    SETjuSetAdapter.valueOf(l)
  }

  // Convenience (this is code -> CE -> SET_CE)
  implicit def stringTupleToSETCE(tuple: Tuple2[String, String]): SET[CE] = {
    val array = new ArrayList[CE]
    array.add(stringTupleToCS(tuple._1, tuple._2))
    SETjuSetAdapter.valueOf(array)
  }

  // TODO left over?
  // implicit def cdToStrings2(cd: CD): Tuple2[String, String] = (cd.code.toString, cd.codeSystem.toString)


  // ------------- Instance identifier ----------------------
  implicit def stringTupleToII(v: Tuple2[String, String]) = IIimpl.valueOf(v._1, v._2)

  implicit def IIToStringTuple(ii: II) = (ii.root.toString, ii.extension.toString)

  implicit def stringTupleToSETII(args: Tuple2[String, String]) = {
    val iid = IIimpl.valueOf(args._1, args._2)
    val idarr = new ArrayList[II]
    idarr.add(iid)
    SETjuSetAdapter.valueOf(idarr)
  }

  // Most generic case:
  implicit def stringTupleListToSetII(args: scala.List[Tuple2[String, String]]) = {
    val idarr = new ArrayList[II]
    args.foreach {
      ii =>
        val iid = IIimpl.valueOf(ii._1, ii._2)
        idarr.add(iid)
    }
    SETjuSetAdapter.valueOf(idarr)
  }

  // TODO from here not revised...


  //------------- SC (data: String, code: String, codeSystem: String)-----------------------------
  implicit def stringCode2SC(v: Tuple3[String, String, String]): SC = SCimpl.valueOf(v._1, CVimpl.valueOf(v._2, v._3))

  implicit def SCToString3(v: SC): Tuple3[String, String, String] = (v.charset.toString, v.code.code, v.code.codeSystem)

  /**
   * Point in Time (TS)
   * @param ( date : String )
   * @return TS
   */
  implicit def stringToTS(v: String): TS = TSjuDateAdapter.valueOf(v)

  /**
   * Interval of Point in Time IVL<TS>
   * @param ( date : String )
   * @return IVL < TS >
   */
  // ----------------Time: TS (date : String)--------------------
  implicit def stringToIVL_TS(v: String): IVL[TS] = {
    val effectiveTime = TSjuDateAdapter.valueOf(v)
    var valueFactory: ValueFactory = new ValueFactoryImpl
    valueFactory.IVLvalueOf(BLimpl.TRUE, effectiveTime, effectiveTime, BLimpl.TRUE)
  }

  implicit def timeToIVL_TS(v: Time): IVL[TS] = v.getTimeASIVL

  /**
   * Interval of Point in Time IVL<TS>
   * Kombinationsmöglichkeiten:
   * - Eine Untergrenze <low> und eine Obergrenze <high>
   * - Nur eine Untergrenze <low>.
   * - Nur eine Obergrenze <high>.
   * @param ( lowClosed : Boolean | default = true, low : String, high : String, highClosed : Boolean | default = true )
   * @return Interval of Point in Time (IVL<TS>)
   */
  implicit def strings2IvlTs(v: Tuple4[Boolean, String, String, Boolean]): IVL[TS] = {
    val low = TSjuDateAdapter.valueOf(v._2)
    val high = TSjuDateAdapter.valueOf(v._3)
    IVLimpl.valueOf(v._1, low, high, v._4)
  }

  // ---------------- PQ : (magnitudeString: String, unitString: String )--------------------
  implicit def strings2PQ(v: Tuple2[REAL, String]): PQ = PQimpl.valueOf(v._1, v._2)

  implicit def telToBAG_TEL(tel: Tel): BAG[TEL] = tel.getTel

  implicit def toCR1(v: Tuple2[CV, Code]): CR = CRimpl.valueOf(v._1, (v._2).getCodeAsCD)

  implicit def toCR2(v: Tuple2[CV, CD]): CR = CRimpl.valueOf(v._1, v._2)

  implicit def toCR3(v: Tuple2[Code, Code]): CR = CRimpl.valueOf((v._1).getCodeAsCV, (v._2).getCodeAsCD)
}
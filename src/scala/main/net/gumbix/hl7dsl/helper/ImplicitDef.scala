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
 * Class with all implicit conversation for the DSL
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

object ImplicitDef {

  //       Implicit for basic data types

  /**
   * The Boolean type stands for the values of two-valued logic
   * @param true or false (boolean)
   * @return true or false as BL
   */
  implicit def booleanToBL(v: Boolean): BL = BLimpl.valueOf(v)


  /**
   * Integer numbers
   * @param Int
   * @return INT
   */
  implicit def intToINT(v: Int): INT = INTlongAdapter.valueOf(v)

  /**
   * Integer numbers
   * @param INT
   * @return Int
   */
  implicit def INTToInt(int: INT): Int = int.intValue

  /**
   * Integer numbers
   * @param String
   * @return ST
   */
  implicit def stringToST(v: String): ST = STjlStringAdapter.valueOf(v)

  /**
   * Integer numbers
   * @param ST
   * @return String
   */
  implicit def STToString(st: ST): String = st.toString


  /**
   * Integer numbers
   * @param ED
   * @return String
   */
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


  //           implicit for coded data types

  /**
   * String to Coded Simple (CS)
   * @param ( code : String, codeSystem : String )
   * @return Coded Simple as (CS)
   */
  implicit def strings2CSimpl2(v: Tuple2[String, String]): CS = {
    CSimpl.valueOf(v._1, v._2)
  }

  /**
   * @param ( code : String, codeSystem : String, displayName : String )
   * @return Coded With Equivalents as (CE)
   */
  implicit def strings2CEimpl3(v: Tuple3[String, String, String]): CE = {
    CEimpl.valueOf(v._1, v._2, v._3)
  }


  //------------- SET <CE> ------------------------
  implicit def CEToSet_CE(v: CE): SET[CE] = {
    val array = new ArrayList[CE]
    array.add(v)
    SETjuSetAdapter.valueOf(array)
  }


  implicit def csToStrings2(cs: CS): Tuple2[String, String] = (cs.code.toString, cs.codeSystem.toString)

  implicit def ceToStrings2(ce: CE): Tuple2[String, String] = (ce.code.toString, ce.codeSystem.toString)

  implicit def cdToStrings2(cd: CD): Tuple2[String, String] = (cd.code.toString, cd.codeSystem.toString)

  //------------- SC (data: String, code: String, codeSystem: String)-----------------------------
  implicit def stringCode2SC(v: Tuple3[String, String, String]): SC = SCimpl.valueOf(v._1, CVimpl.valueOf(v._2, v._3))

  implicit def SCToString3(v: SC): Tuple3[String, String, String] = (v.charset.toString, v.code.code, v.code.codeSystem)

  //------------- II (rootString: String, extensionString: String) ------------------------
  implicit def strings2II(v: Tuple2[String, String]): II = IIimpl.valueOf(v._1, v._2)

  implicit def II2Strings2(ii: II): Tuple2[String, String] = (ii.root.toString, ii.extension.toString)


  //------------- SET <II> ------------------------
  implicit def strings2SetII(args: Tuple2[String, String]) = {
    val iid = IIimpl.valueOf(args._1, args._2)
    val idarr = new ArrayList[II]
    idarr.add(iid)
    SETjuSetAdapter.valueOf(idarr)
  }

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

  implicit def addressToBAG_AD(address: Address): BAG[AD] = {
    var ad: AD = ADimpl.valueOf(address.getAddress)
    var adList: List[AD] = new ArrayList[AD]
    adList.add(ad)
    BAGjuListAdapter.valueOf(adList)
  }

  implicit def BAG_ADtoAdress(bag: BAG[AD]): Address = {
    val address = new Address
    // address.city = bag.
    // TODO no idea how to tackle this?!
    address
  }

  implicit def addressToAD(address: Address): AD = ADimpl.valueOf(address.getAddress)

  implicit def nameToBAG_EN(name: Name): BAG[EN] = name.name

  implicit def BAG_ENToName(bag: BAG[EN])= new Name(bag)

  implicit def telToBAG_TEL(tel: Tel): BAG[TEL] = tel.getTel

  implicit def codedDataTypesToCD(code: CodedDataTypes): CD = code.getCodeAsCD

  implicit def toCR1(v: Tuple2[CV, CodedDataTypes]): CR = CRimpl.valueOf(v._1, (v._2).getCodeAsCD)

  implicit def toCR2(v: Tuple2[CV, CD]): CR = CRimpl.valueOf(v._1, v._2)

  implicit def toCR3(v: Tuple2[CodedDataTypes, CodedDataTypes]): CR = CRimpl.valueOf((v._1).getCodeAsCV, (v._2).getCodeAsCD)

  //    implicit def for DSL Classes

  implicit def asActRelationship(actRelationship: ActRelationshipDSL): ActRelationship = actRelationship.getActRelationship

  implicit def asParticipation(participation: ParticipationDSL): Participation = participation.participation

  implicit def asAct(act: ActDSL): Act = act.act

  implicit def asRole(role: RoleDSL): Role = role.role

  //implicit def asEntity(entity: EntityDSL): Entity = entity.getEntity
  implicit def asEntity(entity: EntityDSL): Entity = entity.entity
}
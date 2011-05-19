package net.gumbix.hl7dsl.helper

import java.util.ArrayList
import org.hl7.types._
import enums.{EntityNamePartType, AddressPartType}
import impl._

/**
 * Wrap the HL7v3 data type and make it mutable.
 * TODO make it generic such that it can be used for Names etc.
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */


trait XP {
  def `type` : CS
}

/**
 * A (e.g. AD or ED)
 * B2 (e.g. ADXP or EDXP)
 * B (e.g. MyADXP or MyEDXP)
 * C (e.g. AddressPartType)
 * @param newA Factory method to create an instance of A
 * (e.g. ADimpl.valueOf())
 * @param newB Factory method to create an instance of B
 * (e.g. ADXPimpl.valueOf()) 
 */
abstract class KeyValueDatatypeWrapper[A <: LIST[B], B <: ANY, C <: CS]
(rimValue: A) {
  def newA(list: java.util.List[B]): A

  def newB(value: String, key: C): B

  def toXP(from: B): XP

  var currentRimValue = copy(rimValue)

  def copy(rimValue: A) = {
    val list = new ArrayList[B]
    val it = rimValue.iterator
    while (it.hasNext) {
      val e = toXP(it.next)
      e.`type` match {
        case a: C => {
          list.add(newB(e.toString, a))
        }
        case _ =>
      }
    }
    newA(list)
  }

  def get(key: C): Option[String] = {
    // All fields in this list:
    val it = currentRimValue.iterator
    while (it.hasNext) {
      val e = toXP(it.next)
      if (e.`type`.implies(key).isTrue()) {
        // println("found")
        return Some(e.toString)
      }
    }
    None
  }

  /**
   * Go through all key-/value-pairs and copy them
   * except the one which is modified. Here the value is overwritten
   * or added.
   */
  def set(key: C, value: String) {
    var found = false
    val list = new ArrayList[B]
    val it = currentRimValue.iterator
    while (it.hasNext) {
      val e = toXP(it.next)
      if (e.`type`.implies(key).isTrue()) {
        // println("set " + key + " replace: " + e.toString + "->" + value)
        found = true
        list.add(newB(value, key))
      } else {
        e.`type` match {
          case a: C => {
            // println("set " + a + " copy: " + e.toString)
            list.add(newB(e.toString, a))
          }
          case _ =>
        }
      }
    }
    // If the key was not already found, we can add the
    // new key-value-pair:
    if (!found) {
      list.add(newB(value, key))
    }
    currentRimValue = newA(list)
  }
}

class AddressWrapper2(rimValue: AD)
        extends KeyValueDatatypeWrapper[AD, ADXP, AddressPartType](rimValue) {

  def newA(list: java.util.List[ADXP]) = ADimpl.valueOf(list)

  def newB(value: String, key: AddressPartType) = ADXPimpl.valueOf(value, key)

  def toXP(from: ADXP) = from match {
    case a: ADXPimpl => {
      object f extends XP {
        def `type` = a.`type`
      }
      f
    }
    case _ => { {
      object f extends XP {
        def `type` = CSimpl.valueOf("", "")
      }
      f
    }
    }
  }
}

// --------- Workaround, as long as the generic method does not work --------
class AddressWrapper(rimValue: AD) {
  def this() = this (ADimpl.valueOf(new ArrayList[ADXP]()))

  var currentRimValue = copy(rimValue)

  def copy(rimValue: AD) = {
    val list = new ArrayList[ADXP]
    val it = rimValue.iterator
    while (it.hasNext) {
      val e: ADXP = it.next
      e.`type` match {
        case a: AddressPartType => {
          list.add(ADXPimpl.valueOf(e.toString, a))
        }
        case _ =>
      }
    }
    ADimpl.valueOf(list)
  }

  def get(key: AddressPartType): Option[String] = {
    // All fields in this list:
    val it = currentRimValue.iterator
    while (it.hasNext) {
      val e: ADXP = it.next
      if (e.`type`.implies(key).isTrue()) {
        // println("found")
        return Some(e.toString)
      }
    }
    None
  }

  /**
   * Go through all key-/value-pairs and copy them
   * except the one which is modified. Here the value is overwritten
   * or added.
   */
  def set(key: AddressPartType, value: String) {
    var found = false
    val list = new ArrayList[ADXP]
    val it = currentRimValue.iterator
    while (it.hasNext) {
      val e: ADXP = it.next
      if (e.`type`.implies(key).isTrue()) {
        // println("set " + key + " replace: " + e.toString + "->" + value)
        found = true
        list.add(ADXPimpl.valueOf(value, key))
      } else {
        e.`type` match {
          case a: AddressPartType => {
            // println("set " + a + " copy: " + e.toString)
            list.add(ADXPimpl.valueOf(e.toString, a))
          }
          case _ =>
        }
      }
    }
    // If the key was not already found, we can add the
    // new key-value-pair:
    if (!found) {
      list.add(ADXPimpl.valueOf(value, key))
    }
    currentRimValue = ADimpl.valueOf(list)
  }
}

class PersonNameWrapper(rimValue: EN) {
  def this() = this (ENimpl.valueOf(new ArrayList[ENXP]()))

  var currentRimValue = copy(rimValue)

  def copy(rimValue: EN) = {
    val list = new ArrayList[ENXP]
    val it = rimValue.iterator
    while (it.hasNext) {
      val e = it.next
      e.`type` match {
        case a: EntityNamePartType => {
          list.add(ENXPimpl.valueOf(e.toString, a))
        }
        case _ =>
      }
    }
    ENimpl.valueOf(list)
  }

  def get(key: EntityNamePartType): Option[String] = {
    // All fields in this list:
    val it = currentRimValue.iterator
    while (it.hasNext) {
      val e = it.next
      if (e.`type`.implies(key).isTrue()) {
        // println("found")
        return Some(e.toString)
      }
    }
    None
  }

  /**
   * Go through all key-/value-pairs and copy them
   * except the one which is modified. Here the value is overwritten
   * or added.
   */
  def set(key: EntityNamePartType, value: String) {
    var found = false
    val list = new ArrayList[ENXP]
    val it = currentRimValue.iterator
    while (it.hasNext) {
      val e = it.next
      if (e.`type`.implies(key).isTrue()) {
        // println("set " + key + " replace: " + e.toString + "->" + value)
        found = true
        list.add(ENXPimpl.valueOf(value, key))
      } else {
        e.`type` match {
          case a: EntityNamePartType => {
            // println("set " + a + " copy: " + e.toString)
            list.add(ENXPimpl.valueOf(e.toString, a))
          }
          case _ =>
        }
      }
    }
    // If the key was not already found, we can add the
    // new key-value-pair:
    if (!found) {
      list.add(ENXPimpl.valueOf(value, key))
    }
    currentRimValue = ENimpl.valueOf(list)
  }
}
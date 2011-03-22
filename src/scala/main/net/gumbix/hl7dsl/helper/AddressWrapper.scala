package net.gumbix.hl7dsl.helper

import org.hl7.types.enums.AddressPartType
import java.util.ArrayList
import org.hl7.types.{AD, ADXP, ANY, LIST}
import org.hl7.types.impl.{ADimpl, ADXPimpl}

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */

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
        println("found")
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
        println("set " + key + " replace: " + e.toString + "->" + value)
        found = true
        list.add(ADXPimpl.valueOf(value, key))
      } else {
        e.`type` match {
          case a: AddressPartType => {
            println("set " + a + " copy: " + e.toString)
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
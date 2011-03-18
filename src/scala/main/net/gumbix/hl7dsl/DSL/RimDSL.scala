package net.gumbix.hl7dsl.DSL

import org.hl7.types.CS
import org.hl7.rim.RimObject
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */

class RimDSL(rimo: RimObject) {

  // TODO a RIM object could be a root element, which means
  // it does not get a clone code.
  // This is required by the RS framework though!
  cloneCode = ("default", "default")

  /**
   * @return CS
   */
  def cloneCode = rimo.getCloneCode

  def cloneCode_=(v: CS) {
    rimo.setCloneCode(v)
  }

  override def toString = rimo.getClass.getName + ": " + cloneCode._1
}
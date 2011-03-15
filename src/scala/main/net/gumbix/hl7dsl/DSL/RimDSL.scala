package net.gumbix.hl7dsl.DSL

import org.hl7.types.CS
import org.hl7.rim.RimObject
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */

class RimDSL(rimo: RimObject) {

  /**
   * @return CS
   */
  def cloneCode = rimo.getCloneCode

  def cloneCode_=(v: CS) {
    rimo.setCloneCode(v)
  }
}
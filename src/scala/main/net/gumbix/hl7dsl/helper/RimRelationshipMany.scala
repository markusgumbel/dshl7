package net.gumbix.hl7dsl.helper

import scala.collection.JavaConversions._
import org.hl7.types.impl.CSimpl
import org.hl7.rim.RimObject
import collection.mutable.ArrayBuffer

/**
 * A class that models a relationship between two RIM objects.
 * @param setLink Closure how to set a relationship
 * @param getLinks Closure how to retrieve a relationship
 * (as this could be a one-to-many it is called getLinkS).
 * @param create Factory method to create DSL-objects. 
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
class RimRelationship[A <: RimObject, B](setLink: A => Unit,
                                         create: A => B) {
  def update(cloneName: String, p: A) {
    p.setCloneCode(CSimpl.valueOf(cloneName, "egal"))
    setLink(p)
  }
}

class RimRelationshipMany[A <: RimObject, B](setLink: A => Unit,
                                             getLinks: => java.util.List[A],
                                             create: A => B)
        extends RimRelationship[A, B](setLink, create) {
  /**
   * Access via a key which is the clone name.
   */
  def apply(cloneName: String): Option[B] = {
    // Regenstrief's links may be set to null:
    if (getLinks != null) {
      for (e <- getLinks.toList) {
        if (e.getCloneCode.code.toString == cloneName) return Some(create(e))
      }
    }
    None
  }

  /**
   * Get all relationships as a list.
   * TODO Q&D: should be a map (clonename, rim-object) to iterate.
   */
  def list = {
    val list = new ArrayBuffer[B]
    if (getLinks != null) getLinks.toList.foreach(list += create(_))
    list.toList
  }
}

class RimRelationshipOne[A <: RimObject, B](setLink: A => Unit,
                                            getLink: => A,
                                            create: A => B)
        extends RimRelationship[A, B](setLink, create) {
  /**
   * Just one relationship is stored, so we access it directly.
   * Note that you have to put a () behind your variable.
   * TODO can be improved.
   */
  def apply(): Option[B] = if (getLink == null) None else Some(create(getLink))
}
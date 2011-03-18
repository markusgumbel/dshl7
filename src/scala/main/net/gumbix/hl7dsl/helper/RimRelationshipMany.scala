package net.gumbix.hl7dsl.helper

import scala.collection.JavaConversions._
import org.hl7.types.impl.CSimpl
import org.hl7.rim.RimObject
import net.gumbix.hl7dsl.DSL.RimDSL
import net.gumbix.hl7dsl.helper.ImplicitDef._
import collection.mutable.{HashMap, ArrayBuffer}

/**
 * A class that models a relationship between two RIM objects.
 * TODO for 1:1 ther must be only one assignment.
 * @param setLink Closure how to set a relationship
 * @param getLinks Closure how to retrieve a relationship
 * (as this could be a one-to-many it is called getLinkS).
 * @param create Factory method to create DSL-objects. 
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
class RimRelationship[A <: RimObject, B <: RimDSL](setLink: A => Unit,
                                                   create: A => B) {
  /**
   * A map that keeps track of DSL object which have already been
   * created.
   */
  protected val links = new HashMap[String, B]

  def update(cloneName: String, p: A) {
    if (links.contains(cloneName) == false) {
      p.setCloneCode(CSimpl.valueOf(cloneName, "egal"))
      setLink(p)
      val c = create(p)
      c.cloneCode = (cloneName, "egal")
      links(cloneName) = c
    } else {
      throw new RuntimeException("Cannot assign relationship again.")
    }
  }
}

class RimRelationshipMany[A <: RimObject, B <: RimDSL](setLink: A => Unit,
                                                       getLinks: => java.util.List[A],
                                                       create: A => B)
        extends RimRelationship[A, B](setLink, create) {
  /**
   * Access via a key which is the clone name.
   */
  def apply(cloneName: String): Option[B] = {
    try {
      // Try to return the already created DSL object.
      Some(links(cloneName))
    } catch {
      case e: Exception => {
        // It is not found. Soe we try to create one from the
        // underlying Regenstrief graph object.
        // Note that Regenstrief's links may be set to null:
        if (getLinks != null) {
          for (e <- getLinks.toList) {
            if (e.getCloneCode.code.toString == cloneName) {
              update(cloneName, e)  // Add it ot our map.
              return Some(links(cloneName))
            }
          }
        }
        None  // Finally, this relationship could not be found.
      }
    }
  }

  /**
   * Get all relationships as a list.
   * TODO Q&D: should be a map (clonename, rim-object) to iterate.
   */
  def list = links.values.toList
}

class RimRelationshipOne[A <: RimObject, B <: RimDSL](setLink: A => Unit,
                                                      getLink: => A,
                                                      create: A => B)
        extends RimRelationship[A, B](setLink, create) {
  /**
   * Just one relationship is stored, so we access it directly.
   * Note that you have to put a () behind your variable.
   * TODO can be improved.
   */
  def apply(): Option[B] = {
    links.size match {
      // If there is exactly one element, we just return it:
      case 1 => Some(links.values.toList.head)
      case _ => {
        
        if (getLink == null) None else {
          val c = create(getLink)
          // c.cloneCode = (cloneName, "egal")
          update(getLink.getCloneCode._1, getLink)
          Some(links.values.toList.head)
        }
      }
    }
  }
}
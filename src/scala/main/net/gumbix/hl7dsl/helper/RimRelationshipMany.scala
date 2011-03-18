package net.gumbix.hl7dsl.helper

import scala.collection.JavaConversions._
import org.hl7.types.impl.CSimpl
import org.hl7.rim.RimObject
import net.gumbix.hl7dsl.DSL.RimDSL
import net.gumbix.hl7dsl.helper.ImplicitDef._
import collection.mutable.{ListBuffer, HashMap, ArrayBuffer}

/**
 * A class that models a relationship between two RIM objects.
 * TODO for 1:1 there must be only one assignment.
 * @param setLink Closure how to set a relationship
 * @param getLinks Closure how to retrieve a relationship
 * (as this could be a one-to-many it is called getLinkS).
 * @param create Factory method to create DSL-objects. 
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
class RimRelationship[A <: RimObject, B <: RimDSL](setLink: A => Unit,
                                                   getLinks: => java.util.List[A],
                                                   create: A => B,
                                                   manyRelationship: Boolean) {
  /**
   * A map that keeps track of DSL object which have already been
   * created.
   */
  protected val links = new HashMap[String, ListBuffer[B]]
  private var initDone = false

  /**
   * Create a relationship to a RIM object which the given clone name.
   * @param cloneName
   */
  def update(cloneName: String, p: A) {

    init()

    def append(listBuffer: ListBuffer[B]) {
      p.setCloneCode(CSimpl.valueOf(cloneName, "egal"))
      setLink(p)
      val c = create(p)
      c.cloneCode = (cloneName, "egal")
      listBuffer += c
    }

    if (links.contains(cloneName) == false) {
      val listBuffer = new ListBuffer[B]
      append(listBuffer)
      links(cloneName) = listBuffer
    } else { // key exists
      if (manyRelationship) {
        // We can append the new entry (with the same key):
        append(links(cloneName))
      } else throw new RuntimeException("Cannot assign relationship again.")
    }
  }

  /**
   * Ensure that we have all links set before return the list.
   */
  def init() {
    if (!initDone) {
      initDone = true
      if (getLinks != null) {
        for (e <- getLinks.toList) {
          val cloneName = e.getCloneCode.code.toString
          update(cloneName, e) // Add it ot our map.
        }
      }
    }
  }
}

class RimRelationshipMany[A <: RimObject, B <: RimDSL](setLink: A => Unit,
                                                       getLinks: => java.util.List[A],
                                                       create: A => B)
        extends RimRelationship[A, B](setLink, getLinks, create, true) {
  /**
   * Access via a key which is the clone name.
   */
  def apply(cloneName: String): List[B] = {
    init()

    try {
      // Try to return the already created DSL object.
      links(cloneName).toList
    } catch {
      case e: Exception => {
        // It is not found. So we try to create one from the
        // underlying Regenstrief graph object.
        // Note that Regenstrief's links may be set to null:
        if (getLinks != null) {
          for (e <- getLinks.toList) {
            if (e.getCloneCode.code.toString == cloneName) {
              update(cloneName, e) // Add it ot our map.
            }
          }
          return links(cloneName).toList
        }
        Nil // Finally, this relationship could not be found.
      }
    }
  }

  /**
   * Get all relationships as a list.
   * TODO Q&D: should be a map (clonename, rim-object) to iterate.
   */
  def list = {
    init()

    val x = links.values.flatten.toList
    x
  }
}

class RimRelationshipOne[A <: RimObject, B <: RimDSL](setLink: A => Unit,
                                                      getLink: => A,
                                                      create: A => B)
        extends RimRelationship[A, B](setLink,
          {val l = new java.util.ArrayList[A]; l.add(getLink); l},
          create, false) {
  /**
   * Just one relationship is stored, so we access it directly.
   * Note that you have to put a () behind your variable.
   * TODO can be improved.
   */
  def apply(): Option[B] = {
    init()

    links.size match {
    // If there is exactly one element, we just return it:
      case 1 => Some(links.values.toList.head.head)
      case _ => {
        if (getLink == null) None else {
          update(getLink.getCloneCode._1, getLink)
          Some(links.values.toList.head.head)
        }
      }
    }
  }
}
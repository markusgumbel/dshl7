package net.gumbix.hl7dsl

import net.gumbix.hl7dsl.build.{SimpleLoadMessage, BuildMessage}
import junit.framework.TestCase
import org.hl7.util.MessageLoader
import org.hl7.rim.{RimObject, Document}
import org.hl7.util._
import org.hl7.types._
import org.hl7.types.impl._
import net.gumbix.hl7dsl.DSL.DocumentDSL
import net.gumbix.hl7dsl.helper.ImplicitDef._
import net.gumbix.hl7dsl.helper.Address
import scala.collection.JavaConversions._

/**
 * @author Ahmet Gül
 * Simple load CDA and read data
 */

class HL7LoadTest extends TestCase {
  def testLoadVHitG01() {
    loadCDA("cda-examples/vhitg-POCD_EX000001.xml")
  }

  def testLoadMessage() {
    loadCDA("cda-examples/Arztbrief-02-Level3.xml")
  }

  def loadCDA(filename: String) {
    val doc = StringFromFile.readFileAsString(filename)
    val cda = new DocumentDSL(doc)

    println("\nPatientenakten:")
    val rt = cda.participation("recordTarget")
    rt match {
      case None => println(" CDA enthält keine Patientenakte")
      case _ => println(" Patientenakte = " + rt.get)
    }

    println("\nAlle Überschriften auslesen:")
    val sections = cda.outboundRelationship("component")
    sections match {
      case None => println("Dokument hat keine Sections")
      case Some(rel) => {
        rel.target().get.outboundRelationship.list.foreach {
          s => println(" " + s.target().get.title)
        }
      }
    }

    println("\nAlle Adressen auslesen:")
    cda.participation.list.foreach {
      a =>
        println(" " + DatatypeTool.AddressTool.getAll(a.role().get.getAddr))
    }

    println("\nTraversiere 'component' bis Ebene 2:")
    cda.outboundRelationship.list.foreach {
      o => // ActRelationship
        println("level 1")
        o.target().get.outboundRelationship.list.foreach {
          o => // ActRelationship
            println("level 2")
            println("Überschrift: " + o.target().get.title)
            println("Inhalt:      " + o.target().get.text)
        }
    }
  }

  def testModifyMessage1() {
    modifyCDA("cda-examples/Arztbrief-02-Level3.xml")
  }

  def modifyCDA(filename: String) {
    val doc = StringFromFile.readFileAsString(filename)
    val cda = new DocumentDSL(doc)

    cda.participation("recordTarget").get.role().get.player() match {
      case None =>
      case Some(patient) => {
        patient.name.family = "Gumbel"
      }
    }
    val modified = BuildMessage.toXML(cda, "POCD_HD000040")
    println(modified)

  }
}
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
    loadMessage("cda-examples/vhitg-POCD_EX000001.xml")
  }

  def testLoadMessage() {
    loadMessage("cda-examples/Arztbrief-02-Level3.xml")
  }

  def loadMessage(filename: String) {
    val doc = StringFromFile.readFileAsString(filename)

    //--------------- document -------------------
    val clinicalDocument = MessageLoader.LoadMessage("POCD_HD000040", doc).asInstanceOf[Document]

    val cda = new DocumentDSL(clinicalDocument)

    val rt = cda.participation("recordTarget")
    println("recordTarget = " + rt.getRole)

    println("------------- Anamnese Komponente ---------------")
    cda.outboundRelationship.list.foreach {
      o =>
        println("level 1")
        /*
        o.outboundRelationship.list.foreach {
          o =>
            println("level 2")
            println(o.target.title)
            println(o.target.text)
        }
        */
    }

    /*
    println(" //------------- Befund Komponente ---------------")
    println(cda.outboundRelationship(0).target.outboundRelationship(1).target.title)
    println(cda.outboundRelationship(0).target.outboundRelationship(1).target.text)
    */

    println("")
    println("")
    println("// Alle Überschriften auslesen")
    val sections = cda.outboundRelationship("component").
            target.list
    sections.foreach(a => println("-> " + a.title))

    println("")
    println("")
    println("// Alle Adressen auslesen")
    cda.participation.list.foreach {
      a =>
        println("-> " + DatatypeTool.AddressTool.getAll(a.role.list(0).getAddr))
    }

    // -------------------------------------

    val ms = cda.outboundRelationship.list(0).target.list
    ms.foreach(a => a.title = a.title + "!!!")

    val modified = BuildMessage.toXML(cda, "POCD_HD000040")
    println("=============")
    println(modified)
  }

  def testLoadMessage2() {
    var slm: SimpleLoadMessage = new SimpleLoadMessage
    var rim: RimObject = slm.LoadMessage("POCD_HD000040", "Arztbrief-2aufLevel3.XML").asInstanceOf[RimObject]
    println(BuildMessage.toXML(rim, "Arztbrief-2aufLevel3_0.XML"))
  }
}
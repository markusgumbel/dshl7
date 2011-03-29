package net.gumbix.hl7dsl

import build.BuildMessage
import DSL._
import helper.{Tel, Address, Name, Code}
import net.gumbix.hl7dsl.helper.ImplicitDef._
import junit.framework.TestCase
import org.hl7.types.CE
import java.util.ArrayList
import org.hl7.types.impl.{SETjuSetAdapter, CEimpl}
import org.hl7.types.enums.TelecommunicationAddressUse._
import org.hl7.types.enums.ActStatus._
import org.hl7.types.enums.ActRelationshipType._

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
class VHitGDemo extends TestCase {
  def testPOCD_EX000001() {
    val clinicalDocument = new DocumentDSL {
      id = ("6014161089", "1.2.276.0.76.3645.239")
      title = "Arztbrief auf der Basis von CDA Release 2"
      effectiveTime = "20050829"
      code = new Code {
        code = "11488-4"
        codeSystem = "2.16.840.1.113883.6.1"
        displayName = "Consultation note"
      }
      confidentialityCode = ("N", "2.16.840.1.113883.5.25")

      /*
      val cc = CEimpl.valueOf("N", "2.16.840.1.113883.5.25", "")
      val ccList = new ArrayList[CE]
      ccList.add(cc)
      val e = SETjuSetAdapter.valueOf(ccList)
      confidentialityCode = e
      */

      languageCode = "de-DE"

      participation("recordTarget") = new ParticipationDSL {
        id = List(
          ("6245", "2.16.840.1.113883.3.933"),
          ("1543627549", "1.2.276.0.76.4.1")
          )
        role("patientRole") = new PatientDSL {
          player("patient") = new PersonDSL {
            name = new Name {
              given = "Paul"
              family = "Pappel"
            }
            birthTime = "19551217"
            administrativeGenderCode = ("M", "2.16.840.1.113883.5.1")
            // birthPlace = TODO
          }
          addr = new Address {
            streetName = "Riedemannweg"
            houseNumber = "59"
            postalCode = "13627"
            city = "Berlin"
          }
          telecom = new Tel {
            number = ("tel:030.456.345345", HomeAddressUse)
          }
        }
      }

      participation("author") = new ParticipationDSL {
        id = ("2.16.840.1.113883.3.67.933", "ied8984938")
        role("assignedAuthor") = new RoleDSL {
          id = ("2.16.840.1.113883.3.933", "2112345")
          player("assignedPerson") = new PersonDSL {
            name = new Name {
              prefix = "Dr. med."
              given = "Theo"
              family = "Phyllin"
            }
          }
          // represented organization = TODO
        }
      }

      participation("custodian") = new ParticipationDSL {
        role("assignedCustodian") = new RoleDSL {
          scoper("representedCustodianOrganization") = new OrganizationDSL {
            id = ("1.2.276.0.58", "M345")
            name = new Name() {
              orgName = "Praxis Dr. med. Phyllin"
            }
            addr = new Address {
              streetName = "Krankenhausstraße"
              houseNumber = "59"
              postalCode = "51371"
              city = "Leverkusen"
            }
          }
        }
      }

      outboundRelationship("relatedDocument") = new ActRelationshipDSL {
        typeCode = IsAppendage // "APND"
        target("parentDocument") = new DocumentDSL {
          id = ("463957847", "1.2.276.0.58")
        }
      }

      outboundRelationship("authorization") = new ActRelationshipDSL {
        target("consent") = new ActDSL {
          id = ("cs856727-298784", "1.2.276.0.76.3645.239")
          code = ("3-00d", "1.2.276.0.76.5.310")
          statusCode = Completed
        }
      }

      outboundRelationship("component") = new ActRelationshipDSL {
        target("structuredBody") = new ActDSL {
          outboundRelationship("component") = new ActRelationshipDSL {
            target("section") = new ActDSL {
              code = new Code {
                code = "10164-2"
                codeSystem = "2.16.840.1.113883.6.1"
                codeSystemName = "LOINC"
              }
              title = "29.08.2005: Anamnese"
              text = """Sei Jahren wiederholt
              <content styleCode="Bold">chronische Bronchitiden</content>
              besonders bei kalter Luft. Bei Anstrengung expiratorische
              Atemnot. Kontakt mit Haustieren."""
            }
          }
        }
      }
    }
    println(BuildMessage.toXML(clinicalDocument, "POCD_HD000040"))
  }
}
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
 * This demo is used to have a reference CDA.
 * TODO confidentialityCode: codeSystem is not displayed
 *
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
class VHitGDemo extends TestCase {
  def testPOCD_EX000001() {
    val clinicalDocument = new DocumentDSL {
      typeId = ("POCD_HD000040", "2.16.840.1.113883.1.3")
      id = ("6014161089", "1.2.276.0.76.3645.239")
      title = "Arztbrief auf der Basis von CDA Release 2"
      effectiveTime = "20050829"
      code = new Code {
        code = "11488-4"
        codeSystem = "2.16.840.1.113883.6.1"
        displayName = "Consultation note"
      }
      confidentialityCode = ("N", "2.16.840.1.113883.5.25")
      languageCode = ("de-DE", "1.22.333.4444")

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
            administrativeGenderCode = ("M", "2.16.840.1.113883.5.1")
            birthTime = "19551217"
            // birthPlace = TODO
          }
          addr = new Address {
            streetName = "Riedemannweg 59"
            postalCode = "13627"
            city = "Berlin"
          }
          telecom = new Tel {
            number = ("tel:030.456.345345", HomeAddressUse)
          }
          scoper("providerOrganziation") = new OrganizationDSL {
            addr = new Address {
              streetName = "Krankenhausstraße"
              postalCode = "51371"
              city = "Leverkusen"
            }
          }
        }
      }
      participation("author") = new ParticipationDSL {
        id = ("2.16.840.1.113883.3.67.933", "ied8984938")
        time = "20050829"
        role("assignedAuthor") = new RoleDSL {
          id = ("2.16.840.1.113883.3.933", "2112345")
          player("assignedPerson") = new PersonDSL {
            name = new Name {
              prefix = "Dr. med."
              given = "Theo"
              family = "Phyllin"
            }
          }
          scoper("representedOrganization") = new OrganizationDSL {
            name = new Name() {
              orgName = "Praxis Dr. med. Phyllin"
            }
            telecom = new Tel {
              number = ("tel:0214.2127070", WorkPlaceAddressUse)
            }
            addr = new Address {
              streetName = "Krankenhausstraße"
              postalCode = "51371"
              city = "Leverkusen"
            }
          }
        }
      }

      participation("custodian") = new ParticipationDSL {
        role("assignedCustodian") = new RoleDSL {
          scoper("representedCustodianOrganization") = new OrganizationDSL {
            id = ("1.2.276.0.58", "M345")
            name = new Name() {
              orgName = "Praxis Dr. med. Phyllin"
            }
            telecom = new Tel {
              number = ("tel:0214.2127070", WorkPlaceAddressUse)
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

      participation("informationRecipient") = new ParticipationDSL {
        role("intendedRecipient") = new RoleDSL {
          player("informationRecipient") = new PersonDSL {
            name = new Name {
              prefix = "Dr. med."
              given = "Kai"
              family = "Heitmann"
            }
          }
          scoper("receivedOrganization") = new OrganizationDSL {
            name = new Name() {
              orgName = "Gemeinschaftspraxis Dr. Heitmann"
            }
            telecom = new Tel {
              number = ("fax:02473.54637.2938", WorkPlaceAddressUse)
            }
            addr = new Address {
              streetName = "Mühlenweg 1a"
              postalCode = "52152"
              city = "Simmerath"
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
package net.gumbix.hl7dsl

import build.BuildMessage
import DSL._
import helper.{Address, Name, Code}
import net.gumbix.hl7dsl.helper.ImplicitDef._
import junit.framework.TestCase
import org.hl7.types.CE
import java.util.ArrayList
import org.hl7.types.impl.{SETjuSetAdapter, CEimpl}

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

      confidentialityCode = new Code {
        code = "N"
        codeSystem = "2.16.840.1.113883.5.25"
      }

      /*
      val cc = CEimpl.valueOf("N", "2.16.840.1.113883.5.25", "")
      val ccList = new ArrayList[CE]
      ccList.add(cc)
      val e = SETjuSetAdapter.valueOf(ccList)
      confidentialityCode = e
      */

      languageCode = "de-DE"

      participation("recordTarget") = new ParticipationDSL {
        id = ("6245", "2.16.840.1.113883.3.933")
        id = ("1543627549", "1.2.276.0.76.4.1")
        role("patientRole") = new PatientDSL {
          player("patient") = new PersonDSL {
            name = new Name {
              given = "Paul"
              family = "Pappel"
            }
            birthTime = "19551217"
            /*
            administrativeGenderCode = new Code {
              code = "M"
              codeSystem = "2.16.840.1.113883.5.1"
            }
            */
            // birthPlace = ... TODO
          }
          addr = new Address {
            streetName = "Riedemannweg 59"
            postalCode = "13627"
            city = "Berlin"
          }
          // telecom = 
        }

      }

      participation("author") = new ParticipationDSL {
        role("assignedAuthor") = new RoleDSL {
          id = ("2.16.840.1.113883.3.933", "2112345")
          player("assignedPerson") = new PersonDSL {
            name = new Name {
              prefix = "Dr"
              given = "John"
              family = "Night"
              suffix = "FACP"
            }
          }
        }
      }

      participation("author") = new ParticipationDSL {
        role("assignedAuthor") = new RoleDSL {
          id = ("2.16.840.1.113883.3.933", "2112345")
          player("assignedPerson") = new PersonDSL {
            name = new Name {
              prefix = "Dr"
              given = "John"
              family = "Night"
              suffix = "FACP"
            }
          }
        }
      }

      participation("custodian") = new ParticipationDSL {
        time = "20070905"
        role("assignedCustodian") = new RoleDSL {
          scoper("representedCustodianOrganization") = new OrganizationDSL {
            id = ("1.22.333.4444", "")
            name = new Name() {
              orgName = "Good Health Clinic"
            }
          }
        }
      }

      outboundRelationship("component") = new ActRelationshipDSL {
        target("structuredBody") = new ActDSL {
          outboundRelationship("component") = new ActRelationshipDSL {
            typeCode = ("COMP", "1.22.333.4444")
            target("section") = new ActDSL {
              participation("subject") = new ParticipationDSL {
                /*
                role("relatedSubject") = new RoleDSL {
                  // TODO each element could automatically be cloned?
                  // player("subject") = patient clone
                }
                */
              }
              outboundRelationship("entry") = new ActRelationshipDSL {
                target("observation") = new ObservationDSL {
                  code = ("44100-6", "2.16.840.1.113883.6.1")
                  moodCode = ("EVN", "1.22.333.4444")
                  title = "History"
                  text = (""""Pt is a 46 yo male with a hx of CREST syndrome first diagnosed 4 years ago at Stanford.
 He is treated with Cupramine and Prilosec.  Family Hx is neg for CTD.
 His condition is most remarkable for dysphagia Raynauds and telangiectasias on face.""")
                }
              }
              outboundRelationship("entry") = new ActRelationshipDSL {
                target("observation") = new ObservationDSL {
                  code = ("44100-6", "2.16.840.1.113883.6.1")
                  moodCode = ("EVN", "1.22.333.4444")
                  title = "History"
                  text = (""""Telangiectasias on face, palms and in mouth.
Nail fold capillaries dilated and redundant.
Skin and joints of upper extremeties unremarkable.""")
                }
              }
            }
          }
        }
      }
    }
    println(BuildMessage.toXML(clinicalDocument, "POCD_HD000040"))
  }
}
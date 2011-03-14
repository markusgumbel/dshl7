package net.gumbix.hl7dsl

import net.gumbix.hl7dsl.build.BuildMessage
import net.gumbix.hl7dsl.helper.{Name, CodedDataTypes}
import junit.framework.TestCase
import net.gumbix.hl7dsl.DSL._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Implementation CDADemo.java from HL7 Java SIG with the Scala DSL
 * @author Ahmet Gül
 */

class HL7TestCDADemo extends TestCase {
  def testCDADemo() {

    // Create a patient-clone (for reuse):
    val patient = new PersonDSL {
      id = ("123433", "123.234.345")
      name = new Name {
        prefix = "Mr"
        given = "Robert"
        family = "Zimmerman"
        suffix = "VII"
      }
    }

    val clinicalDocument = new DocumentDSL {
      id = ("1234567", "123.123.123")
      effectiveTime = "20061105212900.000"
      code = new CodedDataTypes {
        code = "11488-4"
        codeSystem = "2.16.840.1.113883.6.1"
      }

      // Basically, this reflects better a 1:n relationship
      participation("recordTarget") = new ParticipationDSL {
        role("patientRole") = new PatientDSL {
          player("patient") = patient // see above.
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

      /*
      xparticipation("recordTarget") = new ParticipationDSL("") {
          role = new PatientDSL("patientRole") {
            player = patient // see above.
          }
        }


      xparticipation("recordTarget") {
        new ParticipationDSL("") {
          role = new PatientDSL("patientRole") {
            player = patient // see above.
          }
        }
      }
      */

      /*
      participation = new ParticipationDSL("recordTarget") {
        role = new PatientDSL("patientRole") {
          player = patient // see above.
        }
      }
      */
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
                role("relatedSubject") = new RoleDSL {
                  player("subject") = patient clone
                }
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
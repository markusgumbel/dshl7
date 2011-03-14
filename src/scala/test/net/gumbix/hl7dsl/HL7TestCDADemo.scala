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
    val patient = new PersonDSL("patient") {
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
      participates("recordTarget") = new ParticipationDSL("") {
        role = new PatientDSL("patientRole") {
          player = patient // see above.
        }
      }

      participates("author") = new ParticipationDSL("") {
        role = new RoleDSL("assignedAuthor")  {
          id = ("2.16.840.1.113883.3.933", "2112345")
          player = new PersonDSL("assignedPerson") {
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
      participation = new ParticipationDSL("author") {
        role = new RoleDSL("assignedAuthor") {
          id = ("2.16.840.1.113883.3.933", "2112345")
          player = new PersonDSL("assignedPerson") {
            name = new Name {
              prefix = "Dr"
              given = "John"
              family = "Night"
              suffix = "FACP"
            }
          }
        }
      }
      participation = new ParticipationDSL("custodian") {
        time = "20070905"
        role = new RoleDSL("assignedCustodian") {
          scoper = new OrganizationDSL("representedCustodianOrganization") {
            id = ("1.22.333.4444", "")
            name = new Name() {
              orgName = "Good Health Clinic"
            }
          }
        }
      }

      outboundRelationship = new ActRelationshipDSL("component") {
        target = new ActDSL("structuredBody") {
          outboundRelationship = new ActRelationshipDSL("component") {
            typeCode = ("COMP", "1.22.333.4444")
            target = new ActDSL("section") {
              participation = new ParticipationDSL("subject") {
                role = new RoleDSL("relatedSubject") {
                  player = patient cloneAs "subject"
                }
              }
              outboundRelationship = new ActRelationshipDSL("entry") {
                target = new ObservationDSL("observation") {
                  code = ("44100-6", "2.16.840.1.113883.6.1")
                  moodCode = ("EVN", "1.22.333.4444")
                  title = "History"
                  text = (""""Pt is a 46 yo male with a hx of CREST syndrome first diagnosed 4 years ago at Stanford.
 He is treated with Cupramine and Prilosec.  Family Hx is neg for CTD.
 His condition is most remarkable for dysphagia Raynauds and telangiectasias on face.""")
                }
              }
              outboundRelationship = new ActRelationshipDSL("entry") {
                target = new ObservationDSL("observation") {
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
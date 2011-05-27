/* THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

The contents of this file are subject to the Health Level-7 Public
License Version 1.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License
at http://www.hl7.org/HPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
the License for the specific language governing rights and limitations
under the License.

The Original Code is all this file.

The Initial Developer of the Original Code is automatically generated
from HL7 copyrighted standards specification which may be subject to
different license. Portions created by Initial Developer are Copyright
(C) 2002-2004 Health Level Seven, Inc. All Rights Reserved.

THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.
*/
package org.hl7.types.enums;

import org.hl7.types.ValueFactory;
import org.hl7.types.ANY;
import org.hl7.types.CS;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.SET;
import org.hl7.types.LIST;
import org.hl7.types.CR;
import org.hl7.types.NullFlavor;
import org.hl7.types.impl.NullFlavorImpl;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.LISTnull;
import java.util.EnumSet;
import java.util.Map;
import java.util.HashMap;

/** A code specifying the major type of Act that this Act-instance represents.<p>
   <emph>Constraints:</emph> The classCode domain is a tightly controlled vocabulary, not an external or user-defined vocabulary. 
</p>
<p>Every Act-instance must have a classCode. If the act class is not further specified, the most general Act.classCode (ACT)
   is used.
</p>
<p>The Act.classCode must be a generalization of the specific Act concept (e.g., as expressed in Act.code), in other words, the
   Act concepts conveyed in an Act must be specializations of the Act.classCode. Especially, Act.code is not a "modifier" that
   can alter the meaning of a class code. (See Act.code for contrast.)
</p>
<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum ActClass implements CS {

  // ACTUAL DATA

  root(null, "ActClass", null, null),
  /** An action of interest that has happened, can happen, is happening, is intended to happen, or is requested/demanded to happen.
An act is an intentional action in the business domain of HL7. Healthcare (and any profession or business) is constituted
of intentional actions. An Act instance is a record of such an intentional action.<p>Any intentional action can exist in different ""moods"". Moods describe activities as they progress in the business cycle,
   from defined, through planned and ordered to complete.
</p>
<p>Any instance of an Act assumes one and only one mood and will not change its mood along its life cycle. The moods - definition,
   intent, order, event - seem to specify a life cycle of an activity. However, the participants in the activity in these different
   moods are different, as is the data. Therefore, the mood of an Act instance is static. The progression actualization (i.e.,
   the progression from defined, through planned and ordered, to being performed) is called the ""business cycle"" to distinguish
   it from the ""life cycle"" of a single act instance. Related Act instances that form such a ""business cycle"" are linked
   through the ActRelationship class.
</p>
<p>Examples for acts in health care are: a clinical test, an assessment of health condition (such as problems and diagnoses),
   the setting of healthcare goals, the performance of treatment services (such as medication, surgery, physical and psychological
   therapy), assisting, monitoring or attending, training and education services to patients and their next of kin, and notary
   services (such as advanced directives or living will).
</p>
<p>Acts have participants, which can be actors or targets. Examples of actors are nurses, doctors, family members, notary publics,
   and service organizations -- every person or organization that is capable of independent decisions and can thus is responsible
   (and liable) for the actions performed.
</p>
<p>Target participants in an act may include the patient, the patient's spouse, family, or community, a specimen drawn from the
   patient or from any object of interest. As patients do play active roles in their own healthcare, the patient can be both
   an active participant and a target participant at the same time (self-administered or reflexive services).
</p>
<p>An act can have multiple active participants and multiple target participants, their specific role being distinguished in
   the ""typeCode"" of the respective instance of the Participation class. In particular, an act involving coordination of care
   may involve two or more active participants -- playing different roles -- who interact on behalf of a patient, family, or
   aggregate in the role of target participant. For example, a nurse (active participant) calls Meals on Wheels (active participant)
   on behalf of the patient (target participant).
</p>
<p>An act includes the ""results"", ""answers"" or informational ""procedure products"" gained during the act. In this model,
   ""results"" do not exist without an act, and every clinical result, including those results gained accidentally, is gleaned
   via an act. In other moods, such as ""definition "" or ""intent"", the results are the possible results, the expected or aimed-for
   results, or the tested-for results.
</p> */
  Root(null, "ActClassRoot", "ACT", "act"),
  /** Used to group a set of acts sharing a common context. Container structures can nest within other context structures - such
as where a document is contained within a folder, or a folder is contained within an EHR extract.<p>
   <emph role="strong">Open issue: </emph> There is a clear conflict between this act and the use of the more general "component" ActRelationship. The question that
   must be resolved is what should be the class code of the parent (or containing) Act.
</p> */
  Container(Root, "ActClassContainer", null, null),
  /** A context representing a grouped commitment of information to the EHR. It is considered the unit of modification of the record,
the unit of transmission in record extracts, and the unit of attestation by authorizing clinicians.<p>A composition represents part of a patient record originating from a single interaction between an authenticator and the record.</p>
<p>Unless otherwise stated all statements within a composition have the same authenticator, apply to the same patient and were
   recorded in a single session of use of a single application.
</p>
<p>A composition contains organizers and entries.</p> */
  Composition(Container, "ActClassComposition", "COMPOSITION", "composition"),
  /** Specialization of Act to add the characteristics unique to document management services. */
  Document(Composition, "ActClassDocument", "DOC", "document"),
  /** A clinical document is a documentation of clinical observations and services, with the following characteristics: (1) Persistence
- A clinical document continues to exist in an unaltered state, for a time period defined by local and regulatory requirements;
(2) Stewardship - A clinical document is maintained by a person or organization entrusted with its care; (3) Potential for
authentication - A clinical document is an assemblage of information that is intended to be legally authenticated; (4) Wholeness
- Authentication of a clinical document applies to the whole and does not apply to portions of the document without the full
context of the document; (5) Human readability - A clinical document is human readable." */
  ClinicalDocument(Document, "ActClassClinicalDocument", "DOCCLIN", "clinical document"),
  /** A clinical document that conforms to Level One of the HL7 Clinical Document Architecture (CDA) */
  CDALevelOneClinicalDocument(ClinicalDocument, "CDA Level One clinical document", "CDALVLONE", "CDA Level One clinical document"),
  /** This context represents the information acquired and recorded for an observation, a clinical statement such as a portion of
the patient's history or an inference or assertion, or an action that might be intended or has actually been performed. This
class may represent both the actual data describing the observation, inference, or action, and optionally the details supporting
the clinical reasoning process such as a reference to an electronic guideline, decision support system, or other knowledge
reference. */
  Entry(Container, "ActClassEntry", "ENTRY", "entry"),
  /** A group of entries within a composition, topic or category that have a logical association with one another. <p>The representation of a single observation or action might itself be multi-part. The data might need to be represented as
   a nested set of values, as a table, list, or as a time series. The Cluster class permits such aggregation within an entry
   for such compound data.
</p>
<p>Examples include "Haematology investigations" which might include two or more distinct batteries.</p>
<p>A cluster may contain batteries and/or individual entries</p> */
  Cluster(Entry, "Cluster", "CLUSTER", "Cluster"),
  /** A battery specifies a set of observations. These observations typically have a logical or practical grouping for generally
accepted clinical or functional purposes, such as observfations that are fun together because of automation. A battery can
define required and optional components and, in some cases, will define complex rules that determine whether or not a particular
observation is made. Examples include "Blood pressure", "Full blood count", "Chemistry panel". */
  Battery(Entry, "Battery", "BATTERY", "battery"),
  /** This context represents the part of a patient record conveyed in a single communication. It is drawn from a providing system
for the purposes of communication to a requesting process (which might be another repository, a client application or a middleware
service such as an electronic guideline engine), and supporting the faithful inclusion of the communicated data in the receiving
system.<p>An extract may be the entirety of the patient record as held by the sender or it may be a part of that record (e.g. changes
   since a specified date).
</p>
<p>An extract contains folders or compositions.</p>
<p>An extract cannot contain another extract.</p> */
  Extract(Container, "ActClassExtract", "EXTRACT", "extract"),
  /** A context that comprises all compositions. The EHR is an extract that includes the entire chart.<p>
   <emph role="strong">NOTE:</emph> In an exchange scenario, an EHR is a specialization of an extract.
</p> */
  ElectronicHealthRecord(Extract, "Electronic health record", "EHR", "electronic health record"),
  /** Organizer of entries. Navigational. No semantic content. Knowledge of the section code is not required to interpret contained
observations. Represents a heading in a heading structure, or "organizer tree".<p>The record entries relating to a single clinical session are usually grouped under headings that represent phases of the encounter,
   or assist with layout and navigation. Clinical headings usually reflect the clinical workflow during a care session, and might
   also reflect the main author's reasoning processes. Much research has demonstrated that headings are used differently by different
   professional groups and specialties, and that headings are not used consistently enough to support safe automatic processing
   of the E H R.
</p> */
  Organizer(Container, "ActClassOrganizer", "ORGANIZER", "organizer"),
  /** A group of entries within a composition or topic that have a common characteristic - for example, Examination, Diagnosis,
Management OR Subjective, Objective, Analysis, Plan.<p>The distinction from Topic relates to value sets. For Category there is a bounded list of things like "Examination", "Diagnosis"
   or SOAP categories. For Topic the list is wide open to any clinical condition or reason for a part of an encounter.
</p>
<p>A CATEGORY MAY CONTAIN ENTRIES.</p> */
  Category(Organizer, "Category", "CATEGORY", "category"),
  /** A context that distinguishes the body of a document from the document header. This is seen, for instance, in HTML documents,
which have discrete &lt;head&gt; and &lt;body&gt; elements. */
  DocumentBody(Organizer, "Document body", "DOCBODY", "document body"),
  /** A context that subdivides the body of a document. Document sections are typically used for human navigation, to give a reader
a clue as to the expected content. Document sections are used to organize and provide consistency to the contents of a document
body. Document sections can contain document sections and can contain entries. */
  DocumentSection(Organizer, "Document section", "DOCSECT", "document section"),
  /** A group of entries within a composition that are related to a common clinical theme - such as a specific disorder or problem,
prevention, screening and provision of contraceptive services.<p>A topic may contain categories and entries. </p> */
  Topic(Organizer, "Topic", "TOPIC", "topic"),
  /** A context representing the high-level organization of an extract e.g. to group parts of the record by episode, care team,
clinical specialty, clinical condition, or source application. Internationally, this kind of organizing structure is used
variably: in some centers and systems the folder is treated as an informal compartmentalization of the overall health record;
in others it might represent a significant legal portion of the EHR relating to the originating enterprise or team.<p>A folder contains compositions.</p>
<p>Folders may be nested within folders.</p> */
  Folder(Container, "Folder", "FOLDER", "folder"),
  /** An agreement of obligation between two or more parties that is subject to contractual law and enforcement. */
  Contract(Root, "ActClassContract", "CNTRCT", "contract"),
  /** A contract whose value is measured in monetary terms. */
  FinancialContract(Contract, "ActClassFinancialContract", "FCNTRCT", "financial contract"),
  /** When used in the EVN mood, this concept means with respect to a covered party:<p>(1) A health care insurance policy or plan that is contractually binding between two or more parties; or </p>
<p>(2) A health care program, usually administered by government entities, that provides coverage to persons determined eligible
   under the terms of the program.
   <list role="unordered">
      <item>When used in the definition (DEF) mood, COV means potential coverage for a patient who may or may not be a covered party.</item>
      <item>The concept's meaning is fully specified by the choice of ActCoverageTypeCode (abstract) ActProgramCode or ActInsurancePolicyCode.</item>
   </list>
</p> */
  Coverage(FinancialContract, "Coverage", "COV", "coverage"),
  /** An act representing a system action such as the change of state of another act or the initiation of a query. All control acts
represent trigger events in the HL7 context. ControlActs may occur in different moods. */
  ControlAct(Root, "ActClassControlAct", "CACT", "control act"),
  /** Sender asks addressee to do something depending on the focal Act of the payload. An example is "fulfill this order". Addressee
has responsibilities to either reject the message or to act on it in an appropriate way (specified by the specific receiver
responsibilities for the interaction). */
  Action(ControlAct, "Action", "ACTN", "action"),
  /** Sender sends payload to addressee as information. Addressee does not have responsibilities beyond serving addressee's own
interest (i.e., read and memorize if you see fit). This is equivalent to an FYI on a memo. */
  Information(ControlAct, "Information", "INFO", "information"),
  /** Sender transmits a status change pertaining to the focal act of the payload. This status of the focal act is the final state
of the state transition. This can be either a request or a command, according to the mood of the control act. */
  StateTransitionControl(ControlAct, "State transition control", "STC", "state transition control"),
  /** Observations are actions performed in order to determine an answer or result value. Observation result values (Observation.value)
include specific information about the observed object. The type and constraints of result values depend on the kind of action
performed.<p>Clinical documents commonly have 'Subjective' and 'Objective' findings, both of which are kinds of Observations. In addition,
   clinical documents commonly contain 'Assessments', which are also kinds of Observations. Thus, the establishment of a diagnosis
   is an Observation.
</p> */
  Observation(Root, "ActClassObservation", "OBS", "observation"),
  /** An observable finding or state that persists over time and tends to require intervention or management, and, therefore, distinguished
from an Observation made at a point in time; may exist before an Observation of the Condition is made or after interventions
to manage the Condition are undertaken. Examples: equipment repair status, device recall status, a health risk, a financial
risk, public health risk, pregnancy, health maintenance, chronic illness */
  Condition(Observation, "ActClassCondition", "COND", "Condition"),
  /** A public health case is an Observation representing a condition or event that has a specific significance for public health.
Typically it involves an instance or instances of a reportable infectious disease or other condition. The public health case
can include a health-related event concerning a single individual or it may refer to multiple health-related events that are
occurrences of the same disease or condition of interest to public health. An outbreak involving multiple individuals may
be considered as a type of public health case. A public health case definition (Act.moodCode = "definition") includes the
description of the clinical, laboratory, and epidemiologic indicators associated with a disease or condition of interest to
public health. There are case definitions for conditions that are reportable, as well as for those that are not. There are
also case definitions for outbreaks. A public health case definition is a construct used by public health for the purpose
of counting cases, and should not be used as clinical indications for treatment. Examples include AIDS, toxic-shock syndrome,
and salmonellosis and their associated indicators that are used to define a case. */
  PublicHealthCase(Condition, "ActClassPublicHealthCase", "CASE", "public health case"),
  /** An outbreak represents a series of public health cases. The date on which an outbreak starts is the earliest date of onset
among the cases assigned to the outbreak, and its ending date is the last date of onset among the cases assigned to the outbreak. */
  Outbreak(PublicHealthCase, "Outbreak", "OUTB", "outbreak"),
  /** Container for Correlated Observation Sequences sharing a common frame of reference. All Observations of the same cd must be
comparable and relative to the common frame of reference. For example, a 3-channel ECG device records a 12-lead ECG in 4 steps
(3 leads at a time). Each of the separate 3-channel recordings would be in their own "OBSCOR". And, all 4 OBSCOR would be
contained in one OBSSER because all the times are relative to the same origin (beginning of the recording) and all the ECG
signals were from a fixed set of electrodes. */
  ObservationSeries(Observation, "ActClassObservationSeries", "OBSSER", "observation series"),
  /** Container for Observation Sequences (Observations whose values are contained in LIST&lt;&gt;'s) having values correlated with each
other. Each contained Observation Sequence LIST&lt;&gt; must be the same length. Values in the LIST&lt;&gt;'s are correlated based on
index. E.g. the values in position 2 in all the LIST&lt;&gt;'s are correlated. This is analogous to a table where each column is
an Observation Sequence with a LIST&lt;&gt; of values, and each row in the table is a correlation between the columns. For example,
a 12-lead ECG would contain 13 sequences: one sequence for time, and a sequence for each of the 12 leads. */
  CorrelatedObservationSequences(ObservationSeries, "Correlated observation sequences", "OBSCOR", "correlated observation sequences"),
  /** Regions of Interest (ROI) within a subject Act. Primarily used for making secondary observations on a subset of a subject
observation. The relationship between a ROI and its referenced Act is specified through an ActRelationship of type "subject"
(SUBJ), which must always be present. */
  ROI(Observation, "ActClassROI", null, null),
  /** A Region of Interest (ROI) specified for a multidimensional observation, such as an Observation Series (OBSSER). The ROI is
specified using a set of observation criteria, each delineating the boundary of the region in one of the dimensions in the
multidimensional observation. The relationship between a ROI and its referenced Act is specified through an ActRelationship
of type subject (SUBJ), which must always be present. Each of the boundary criteria observations is connected with the ROI
using ActRelationships of type "has component" (COMP). In each boundary criterion, the Act.code names the dimension and the
Observation.value specifies the range of values inside the region. Typically the bounded dimension is continuous, and so the
Observation.value will be an interval (IVL) data type. The Observation.value need not be specified if the respective dimension
is only named but not constrained. For example, an ROI for the QT interval of a certain beat in ECG Lead II would contain
2 boundary criteria, one naming the interval in time (constrained), and the other naming the interval in ECG Lead II (only
named, but not constrained). */
  BoundedROI(ROI, "Bounded ROI", "ROIBND", "bounded ROI"),
  /** A Region of Interest (ROI) specified for an image using an overlay shape. Typically used to make reference to specific regions
in images, e.g., to specify the location of a radiologic finding in an image or to specify the site of a physical finding
by "circling" a region in a schematic picture of a human body. The units of the coordinate values are in pixels. The origin
is in the upper left hand corner, with positive X values going to the right and positive Y values going down. The relationship
between a ROI and its referenced Act is specified through an ActRelationship of type "subject" (SUBJ), which must always be
present. */
  OverlayROI(ROI, "Overlay ROI", "ROIOVL", "overlay ROI"),
  /** An instance of Observation of a Condition at a point in time that includes any Observations or Procedures associated with
that Condition as well as links to previous instances of Condition Node for the same Condition */
  ConditionNode(Observation, "Condition Node", "CNOD", "Condition Node"),
  /** <emph role="strong">Description:</emph>An observation representing the physical location of a place based on a reference coordinate system. */
  Position(Observation, "Position", "POS", "position"),
  /** <emph role="strong">Description:</emph>An observation representing the degree to which the assignment of the spatial coordinates, based on a matching algorithm by
a geocoding engine against a reference spatial database, matches true or accepted values. */
  PositionAccuracy(Position, "Position accuracy", "POSACC", "position accuracy"),
  /** <emph role="strong">Description:</emph>An observation representing one of a set of numerical values used to determine the position of a place. The name of the coordinate
value is determined by the reference coordinate system. */
  PositionCoordinate(Position, "Position coordinate", "POSCOORD", "position coordinate"),
  /**  */
  SubjectPhysicalPosition(Observation, "SubjectPhysicalPosition", null, null),
  /**  */
  ImagingSubjectOrientation(SubjectPhysicalPosition, "ImagingSubjectOrientation", null, null),
  /**  */
  SubjectBodyPosition(SubjectPhysicalPosition, "SubjectBodyPosition", null, null),
  /** A semi-sitting position in bed with the head of the bed elevated approximately 45 degrees. */
  SemiFowler_s(SubjectBodyPosition, "Semi-Fowler's", "SFWL", "Semi-Fowler's"),
  /**  */
  Supine(SubjectBodyPosition, "Supine", "SUP", "supine"),
  /** Lying on the back, on an inclined plane, typically about 30-45 degrees with head raised and feet lowered. */
  ReverseTrendelenburg(Supine, "Reverse trendelenburg", "RTRD", "reverse trendelenburg"),
  /** Lying on the back, on an inclined plane, typically about 30-45 degrees, with head lowered and feet raised. */
  Trendelenburg(Supine, "Trendelenburg", "TRD", "trendelenburg"),
  /** Lying on the left side. */
  LeftLateralDecubitus(SubjectBodyPosition, "Left lateral decubitus", "LLD", "left lateral decubitus"),
  /** Lying with the front or ventral surface downward; lying face down. */
  Prone(SubjectBodyPosition, "Prone", "PRN", "prone"),
  /** Lying on the right side. */
  RightLateralDecubitus(SubjectBodyPosition, "Right lateral decubitus", "RLD", "right lateral decubitus"),
  /** Resting the body on the buttocks, typically with upper torso erect or semi erect. */
  Sitting(SubjectBodyPosition, "Sitting", "SIT", "sitting"),
  /** To be stationary, upright, vertical, on oneaTMs legs. */
  Standing(SubjectBodyPosition, "Standing", "STN", "standing"),
  /** An act which describes the process whereby a 'verifying party' validates either the existence of a Role and its associated
classes, or of an Act and its participations.<p>
   <emph>Discussion:</emph>
   <p>Roles are frequently attested to by some Credential. The activities involved in a Verification act can extend to other attributes,
      including reference to an original (role) vetting act and its details. 
   </p>
</p> */
  Verification(Observation, "Verification", "VERIF", "Verification"),
  /** The set of actions that define an experiment to assess the effectiveness and/or safety of a biopharmaceutical product (food,
drug, device, etc.). In definition mood, this set of actions is often embodied in a clinical trial protocol; in event mood,
this designates the aggregate act of applying the actions to one or more subjects. */
  ClinicalTrial(Observation, "Clinical trial", "CLNTRL", "clinical trial"),
  /** An observation identifying a potential adverse outcome as a result of an Act or combination of Acts.<p>
   <emph>Examples:</emph> Detection of a drug-drug interaction; Identification of a late-submission for an invoice; Requesting discharge for a patient
   who does not meet hospital-defined discharge criteria.
</p>
<p>
   <emph>Discussion:</emph> This class is commonly used for identifying 'business rule' or 'process' problems that may result in a refusal to carry out
   a particular request. In some circumstances it may be possible to 'bypass' a problem by modifying the request to acknowledge
   the issue and/or by providing some form of mitigation.
</p>
<p>
   <emph>Constraints:</emph> the Act or Acts that may cause the the adverse outcome are the target of a subject ActRelationship. The subbtypes of this
   concept indicate the type of problem being detected (e.g. drug-drug interaction) while the Observation.value is used to repesent
   a specific problem code (e.g. specific drug-drug interaction id).
</p> */
  DetectedIssue(Observation, "Detected issue", "ALRT", "detected issue"),
  /** Class for holding attributes unique to diagnostic images. */
  DiagnosticImage(Observation, "Diagnostic image", "DGIMG", "diagnostic image"),
  /** An formalized inquiry into the circumstances surrounding a particular unplanned event or potential event for the purposes
of identifying possible causes and contributing factors for the event. This investigation could be conducted at a local institutional
level or at the level of a local or national government. */
  Investigation(Observation, "Investigation", "INVSTG", "investigation"),
  /** An observation on a specimen in a laboratory environment that may affect processing, analysis or result interpretation */
  SpecimenObservation(Observation, "Specimen observation", "SPCOBS", "specimen observation"),
  /** ################ */
  Procedure(Root, "ActClassProcedure", "PROC", "procedure"),
  /** A procedure for obtaining a specimen from a source entity. */
  SpecimenCollection(Procedure, "ActClassSpecimenCollection", "SPECCOLLECT", "Specimen Collection"),
  /** The act of putting something away for safe keeping. The "something" may be physical object such as a specimen, or information,
such as observations regarding a specimen. */
  Storage(Root, "ActClassStorage", "STORE", "Storage"),
  /** Supply orders and deliveries are simple Acts that focus on the delivered product. The product is associated with the Supply
Act via Participation.typeCode="product". With general Supply Acts, the precise identification of the Material (manufacturer,
serial numbers, etc.) is important. Most of the detailed information about the Supply should be represented using the Material
class. If delivery needs to be scheduled, tracked, and billed separately, one can associate a Transportation Act with the
Supply Act. Pharmacy dispense services are represented as Supply Acts, associated with a SubstanceAdministration Act. The
SubstanceAdministration class represents the administration of medication, while dispensing is supply. */
  Supply(Root, "ActClassSupply", "SPLY", "supply"),
  /** Diet services are supply services, with some aspects resembling Medication services: the detail of the diet is given as a
description of the Material associated via Participation.typeCode="product". Medically relevant diet types may be communicated
in the Diet.code attribute using domain ActDietCode, however, the detail of the food supplied and the various combinations
of dishes should be communicated as Material instances. */
  Diet(Supply, "Diet", "DIET", "diet"),
  /** Definition: Indicates that the subject Act has undergone or should undergo substitution of a type indicated by Act.code.<p>Rationale: Used to specify "allowed" substitution when creating orders, "actual" susbstitution when sending events, as well
   as the reason for the substitution and who was responsible for it.
</p> */
  Substitution(Root, "Substitution", "SUBST", "Substitution"),
  /** A unit of work, a grouper of work items as defined by the system performing that work. Typically some laboratory order fulfillers
communicate references to accessions in their communications regarding laboratory orders. Often one or more specimens are
related to an accession such that in some environments the accession number is taken as an identifier for a specimen (group). */
  Accession(Root, "Accession", "ACSN", "accession"),
  /** An accommodation is a service provided for a Person or other LivingSubject in which a place is provided for the subject to
reside for a period of time. Commonly used to track the provision of ward, private and semi-private accommodations for a patient. */
  Accommodation(Root, "Accommodation", "ACCM", "accommodation"),
  /** A financial account established to track the net result of financial acts. */
  Account(Root, "Account", "ACCT", "account"),
  /**  */
  CareProvision(Root, "Care provision", "PCPR", "care provision"),
  /** An identified point during a clinical trial at which one or more actions are scheduled to be performed (definition mood),
or are actually performed (event mood). The actions may or may not involve an encounter between the subject and a healthcare
professional. */
  ClinicalTrialTimepointEvent(Root, "Clinical trial timepoint event", "CTTEVENT", "clinical trial timepoint event"),
  /** The Consent class represents informed consents and all similar medico-legal transactions between the patient (or his legal
guardian) and the provider. Examples are informed consent for surgical procedures, informed consent for clinical trials, advanced
beneficiary notice, against medical advice decline from service, release of information agreement, etc.<p>The details of consents vary. Often an institution has a number of different consent forms for various purposes, including
   reminding the physician about the topics to mention. Such forms also include patient education material. In electronic medical
   record communication, consents thus are information-generating acts on their own and need to be managed similar to medical
   activities. Thus, Consent is modeled as a special class of Act.
</p>
<p>The "signatures" to the consent document are represented electronically through Participation instances to the consent object.
   Typically an informed consent has Participation.typeCode of "performer", the healthcare provider informing the patient, and
   "consenter", the patient or legal guardian. Some consent may associate a witness or a notary public (e.g., living wills, advanced
   directives). In consents where a healthcare provider is not required (e.g. living will), the performer may be the patient
   himself or a notary public.
</p>
<p>Some consent has a minimum required delay between the consent and the service, so as to allow the patient to rethink his decisions.
   This minimum delay can be expressed in the act definition by the ActRelationship.pauseQuantity attribute that delays the service
   until the pause time has elapsed after the consent has been completed.
</p> */
  Consent(Root, "Consent", "CONS", "consent"),
  /** An Act where a container is registered either via an automated sensor, such as a barcode reader, or by manual receipt */
  ContainerRegistration(Root, "Container registration", "CONTREG", "container registration"),
  /** An action taken with respect to a subject Entity by a regulatory or authoritative body with supervisory capacity over that
entity. The action is taken in response to behavior by the subject Entity that body finds to be undesirable.<p>Suspension, license restrictions, monetary fine, letter of reprimand, mandated training, mandated supervision, etc.
   <emph>Examples:</emph>
</p> */
  DisciplinaryAction(Root, "Disciplinary action", "DISPACT", "disciplinary action"),
  /** An interaction between a patient and healthcare participant(s) for the purpose of providing patient service(s) or assessing
the health status of a patient. For example, outpatient visit to multiple departments, home health support (including physical
therapy), inpatient hospital stay, emergency room visit, field visit (e.g., traffic accident), office visit, occupational
therapy, telephone call. */
  Encounter(Root, "Encounter", "ENC", "encounter"),
  /** A transformation process where a requested invoice is transformed into an agreed invoice. Represents the adjudication processing
of an invoice (claim). Adjudication results can be adjudicated as submitted, with adjustments or refused.<p>Adjudication results comprise 2 components: the adjudication processing results and a restated (or adjudicated) invoice or
   claim
</p> */
  FinancialAdjudication(Root, "Financial adjudication", "ADJUD", "financial adjudication"),
  /** A sub-class of Act representing any transaction between two accounts whose value is measured in monetary terms.<p>In the "intent" mood, communicates a request for a transaction to be initiated, or communicates a transfer of value between
   two accounts.
</p>
<p>In the "event" mood, communicates the posting of a transaction to an account.</p> */
  FinancialTransaction(Root, "Financial transaction", "XACT", "financial transaction"),
  /** An event that occurred outside of the control of one or more of the parties involved. Includes the concept of an accident. */
  Incident(Root, "Incident", "INC", "incident"),
  /** The act of transmitting information and understanding about a topic to a subject.<p>
   <emph role="strong">Discussion:</emph> This act may be used to request that a patient or provider be informed about an Act, or to indicate that a person was informed
   about a particular act.
</p> */
  Inform(Root, "Inform", "INFRM", "inform"),
  /** Represents concepts related to invoice processing in health care */
  InvoiceElement(Root, "Invoice element", "INVE", "invoice element"),
  /** An officially or unofficially instituted program to track acts of a particular type or categorization. */
  MonitoringProgram(Root, "Monitoring program", "MPROT", "monitoring program"),
  /** Represents the act of maintaining information about the registration of its associated registered subject. The subject can
be either an Act or a Role, and includes subjects such as lab exam definitions, drug protocol definitions, prescriptions,
persons, patients, practitioners, and equipment.<p>The registration may have a unique identifier - separate from the unique identification of the subject - as well as a core
   set of related participations and act relationships that characterize the registration event and aid in the disposition of
   the subject information by a receiving system.
   <emph>Usage notes: </emph>
</p> */
  Registration(Root, "Registration", "REG", "registration"),
  /** The act of examining and evaluating the subject, usually another act. For example, "This prescription needs to be reviewed
in 2 months." */
  Review(Root, "Review", "REV", "review"),
  /** A procedure or treatment performed on a specimen to prepare it for analysis */
  SpecimenTreatment(Root, "Specimen treatment", "SPCTRT", "specimen treatment"),
  /** The act of introducing or otherwise applying a substance to the subject.<p>
   <emph>Discussion:</emph> The effect of the substance is typically established on a biochemical basis, however, that is not a requirement. For example,
   radiotherapy can largely be described in the same way, especially if it is a systemic therapy such as radio-iodine. This class
   also includes the application of chemical treatments to an area.
</p>
<p>
   <emph>Examples:</emph> Chemotherapy protocol; Drug prescription; Vaccination record
</p> */
  SubstanceAdministration(Root, "Substance administration", "SBADM", "substance administration"),
  /** Transportation is the moving of a payload (people or material) from a location of origin to a destination location. Thus,
any transport service has the three target instances of type payload, origin, and destination, besides the targets that are
generally used for any service (i.e., performer, device, etc.) */
  Transportation(Root, "Transportation", "TRNS", "transportation"),
  /** Working list collects a dynamic list of individual instances of Act via ActRelationship which reflects the need of an individual
worker, team of workers, or an organization to manage lists of acts for many different clinical and administrative reasons.
Examples of working lists include problem lists, goal lists, allergy lists, and to-do lists. */
  WorkingList(Root, "Working list", "LIST", "working list");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.6");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("ActClass");

  private final ActClass _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final ActClass _parent2;
  private final ActClass _parent3; // well, found a 3rd :(
  private EnumSet<ActClass> _impliedConcepts = null;

  private ActClass(ActClass parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private ActClass(ActClass parent, ActClass parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private ActClass(ActClass parent, ActClass parent2, ActClass parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<ActClass> getImpliedConcepts() {
    if(_impliedConcepts == null) {
      if(_parent == null) { // then _parent2, 3 is also null
	_impliedConcepts = EnumSet.of(root);
	_impliedConcepts.add(this);
      } else {
	_impliedConcepts  = EnumSet.copyOf(_parent.getImpliedConcepts());
	_impliedConcepts.add(this);
	if(_parent2 != null)
	  _impliedConcepts.addAll(_parent2.getImpliedConcepts());
	if(_parent3 != null)
	  _impliedConcepts.addAll(_parent3.getImpliedConcepts());
      }
    }
    return _impliedConcepts;
  }

  public final BL equal(ANY that) {
    if(this == that)
      return BLimpl.TRUE;
    if (!(that instanceof CD))
      return BLimpl.FALSE;
    else if (that instanceof ActClass)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof ActClass))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActClass thatActClass = (ActClass)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatActClass));
  }

  public ActClass mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof ActClass))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final ActClass thatActClass = (ActClass)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<ActClass> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatActClass.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    ActClass mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(ActClass candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,ActClass> _codeMap = null;

  public static ActClass valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(ActClass.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(ActClass concept : EnumSet.allOf(ActClass.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      ActClass concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.6" + ", domain = " + "ActClass"));
      else
        return concept;
    } else 
      return null;
  }

  // INVARIANT BOILER PLATE CODE

  public String toString() {
    return code().toString();
  }

  private final String _domainName;
  private final ST _code;
  private final ST _displayName;

  public String domainName() { return _domainName; }
  public ST code() { return _code; }
  public ST displayName() { return _displayName; }
  public UID codeSystem() { return CODE_SYSTEM; }
  public ST codeSystemName() { return CODE_SYSTEM_NAME; }
  public ST codeSystemVersion() { return STnull.NI; }
  public ST originalText() { return STnull.NI; }
  public SET<CD> translation() { return SETnull.NA; }
  public LIST<CR> qualifier() { return LISTnull.NA; }

  public NullFlavor nullFlavor() { return NullFlavorImpl.NOT_A_NULL_FLAVOR; }
  public boolean isNullJ() { return false; }
  public boolean nonNullJ() { return true; }
  public boolean notApplicableJ() { return false; }
  public boolean unknownJ() { return false; }
  public boolean otherJ() { return false; }
  public BL isNull() { return BLimpl.FALSE; }
  public BL nonNull() { return BLimpl.TRUE; }
  public BL notApplicable() { return BLimpl.FALSE; }
  public BL unknown() { return BLimpl.FALSE; }
  public BL other() { return BLimpl.FALSE; }
}

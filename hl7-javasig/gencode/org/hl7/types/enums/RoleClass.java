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

/** This table includes codes for the Role class hierarchy. The values in this hierarchy, represent a Role which is an association
or relationship between two entities - the entity that plays the role and the entity that scopes the role. Roles names are
derived from the name of the playing entity in that role.<p>The role hierarchy stems from three core concepts, or abstract domains:</p>
<p>The hierarchy discussed above is represented In the current vocabulary tables as a set of abstract domains, with the exception
   of the "Personal relationship" which is a leaf concept.
</p>
<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum RoleClass implements CS {

  // ACTUAL DATA

  root(null, "RoleClass", null, null),
  /** Corresponds to the Role class */
  Root(null, "RoleClassRoot", "ROL", "role"),
  /** A general association between two entities that is neither partitive nor ontological. */
  Associative(Root, "RoleClassAssociative", null, null),
  /** A relationship that is based on mutual behavior of the two Entities as being related. The basis of such relationship may be
agreements (e.g., spouses, contract parties) or they may be <emph>de facto</emph> behavior (e.g. friends) or may be an incidental involvement with each other (e.g. parties over a dispute, siblings, children). */
  MutualRelationship(Associative, "RoleClassMutualRelationship", null, null),
  /** A relationship between two entities that is formally recognized, frequently by a contract or similar agreement. */
  RelationshipFormal(MutualRelationship, "RoleClassRelationshipFormal", null, null),
  /** A relationship in which the scoper certifies the player ( e. g. a medical care giver, a medical device or a provider organization)
to perform certain activities that fall under the jurisdiction of the scoper (e.g., a health authority licensing healthcare
prlviders, a medical quality authority certifying healthcare professionals,) */
  LicensedEntityRole(RelationshipFormal, "LicensedEntityRole", "LIC", "licensed entity"),
  /** An Entity (player) that is authorized to provide health care services by some authorizing agency (scoper). */
  HealthcareProvider(LicensedEntityRole, "Healthcare provider", "PROV", "healthcare provider"),
  /**  */
  NotaryPublic(LicensedEntityRole, "Notary public", "NOT", "notary public"),
  /** An entity (player) that acts or is authorized to act on behalf of another entity (scoper). */
  Agent(RelationshipFormal, "RoleClassAgent", "AGNT", "agent"),
  /** An agent role in which the agent is an Entity acting in the employ of an organization. The focus is on functional role on
behalf of the organization, unlike the Employee role where the focus is on the 'Human Resources' relationship between the
employee and the organization. */
  AssignedEntity(Agent, "RoleClassAssignedEntity", "ASSIGNED", "assigned entity"),
  /** A person or an organization (player) which provides or receives information regarding another entity (scoper). Examples; patient
NOK and emergency contacts; guarantor contact; employer contact. */
  Contact(AssignedEntity, "RoleClassContact", "CON", "contact"),
  /** An entity to be contacted in the event of an emergency. */
  EmergencyContact(Contact, "Emergency contact", "ECON", "emergency contact"),
  /** An individual designated for notification as the next of kin for a given entity. */
  NextOfKin(Contact, "Next of kin", "NOK", "next of kin"),
  /** An Entity that is authorized to issue or instantiate permissions, privileges, credentials or other formal/legal authorizations. */
  CommissioningParty(AssignedEntity, "Commissioning party", "COMPAR", "commissioning party"),
  /** The role of a person (player) who is the officer or signature authority for of a scoping entity, usually an organization (scoper). */
  SigningAuthorityOrOfficer(AssignedEntity, "Signing authority or officer", "SGNOFF", "signing authority or officer"),
  /** Guardian of a ward */
  Guardian(Agent, "Guardian", "GUARD", "guardian"),
  /** A relationship between a person or organization and a person or organization formed for the purpose of exchanging work for
compensation. The purpose of the role is to identify the type of relationship the employee has to the employer, rather than
the nature of the work actually performed. (Contrast with AssignedEntity.) */
  Employee(RelationshipFormal, "RoleClassEmployee", "EMP", "employee"),
  /** A role played by a member of a military service. Scoper is the military service (e.g. Army, Navy, Air Force, etc.) or, more
specifically, the unit (e.g. Company C, 3rd Battalion, 4th Division, etc.) */
  MilitaryPerson(Employee, "Military person", "MIL", "military person"),
  /** An entity that is the subject of an investigation. This role is scoped by the party responsible for the investigation. */
  InvestigationSubject(RelationshipFormal, "RoleClassInvestigationSubject", "INVSBJ", "Investigation Subject"),
  /** A person, non-person living subject, or place that is the subject of an investigation related to a notifiable condition (health
circumstance that is reportable within the applicable public health jurisdiction) */
  CaseSubject(InvestigationSubject, "Case Subject", "CASEBJ", "Case Subject"),
  /** <emph role="strong">Definition:</emph>Specifies the administrative functionality within a formal experimental design for which the ResearchSubject role was established.
<p>
   <emph role="strong">Examples:</emph> Screening - role is used for pre-enrollment evaluation portion of the design; enrolled - role is used for subjects admitted
   to the experimental portion of the design.
</p> */
  ResearchSubject(InvestigationSubject, "Research subject", "RESBJ", "research subject"),
  /** Player of the Affiliate role has a business/professional relationship with scoper. Player and scoper may be persons or organization.
The Affiliate relationship does not imply membership in a group, nor does it exist for resource scheduling purposes.<p>
   <emph>Example: </emph>A healthcare provider is affiliated with another provider as a business associate.
</p> */
  Affiliate(RelationshipFormal, "Affiliate", "AFFL", "affiliate"),
  /** Citizen of apolitical entity */
  Citizen(RelationshipFormal, "Citizen", "CIT", "citizen"),
  /** A role played by a provider, always a person, who has agency authority from a Clinical Research Sponsor to direct the conduct
of a clinical research trial or study on behalf of the sponsor. */
  ClinicalResearchInvestigator(RelationshipFormal, "Clinical research investigator", "CRINV", "clinical research investigator"),
  /** A role played by an entity, usually an organization, that is the sponsor of a clinical research trial or study. The sponsor
commissions the study, bears the expenses, is responsible for satisfying all legal requirements concerning subject safety
and privacy, and is generally responsible for collection, storage and analysis of the data generated during the trial. No
scoper is necessary, as a clinical research sponsor undertakes the role on its own authority and declaration. Clinical research
sponsors are usually educational or other research organizations, government agencies or biopharmaceutical companies. */
  ClinicalResearchSponsor(RelationshipFormal, "Clinical research sponsor", "CRSPNSR", "clinical research sponsor"),
  /** A role played by an entity, usually an organization that is the sponsor of an insurance plan or a health program. A sponsor
is the party that is ultimately accountable for the coverage by employment contract or by law. A sponsor can be an employer,
union, government agency, or association. Fully insured sponsors establish the terms of the plan and contract with health
insurance plans to assume the risk and to administer the plan. Self-insured sponsors delegate coverage administration, but
not risk, to third-party administrators. Program sponsors designate services to be covered in accordance with statute. Program
sponsors may administer the coverage themselves, delegate coverage administration, but not risk to third-party administrators,
or contract with health insurance plans to assume the risk and administrator a program. Sponsors qualify individuals who may
become <list role="ordered">
   <item>a policy holder of the plan;</item>
   <item>where the sponsor is the policy holder, who may become a subscriber or a dependent to a policy under the plan; or</item>
   <item>where the sponsor is a government agency, who may become program eligibles under a program. </item>
</list>
<p>The sponsor role may be further qualified by the SponsorRole.code. Entities playing the sponsor role may also play the role
   of a Coverage Administrator.
</p>
<p>
   <emph>Example:</emph> An employer, union, government agency, or association.
</p> */
  CoverageSponsor(RelationshipFormal, "Coverage sponsor", "SPNSR", "coverage sponsor"),
  /** A role class played by a person who receives benefit coverage under the terms of a particular insurance policy. The underwriter
of that policy is the scoping entity. The covered party receives coverage because of some contractual or other relationship
with the holder of that policy.<p>
   <emph>Discussion:</emph>This reason for coverage is captured in 'Role.code' and a relationship link with type code of indirect authority should be
   included using the policy holder role as the source, and the covered party role as the target.
</p>
<p>Note that a particular policy may cover several individuals one of whom may be, but need not be, the policy holder. Thus the
   notion of covered party is a role that is distinct from that of the policy holder.
</p> */
  CoveredParty(RelationshipFormal, "Covered party", "COVPTY", "covered party"),
  /** A person or organization (player) that serves as a financial guarantor for another person or organization (scoper). */
  Guarantor(RelationshipFormal, "Guarantor", "GUAR", "guarantor"),
  /** The role of an organization that undertakes to accept claims invoices, assess the coverage or payments due for those invoices
and pay to the designated payees for those invoices. This role may be either the underwriter or a third-party organization
authorized by the underwriter. The scoping entity is the organization that underwrites the claimed coverage. */
  InvoicePayor(RelationshipFormal, "Invoice payor", "PAYOR", "invoice payor"),
  /** Scoped by a provider */
  Patient(RelationshipFormal, "Patient", "PAT", "patient"),
  /** The role of an organization or individual designated to receive payment for a claim against a particular coverage. The scoping
entity is the organization that is the submitter of the invoice in question. */
  Payee(RelationshipFormal, "Payee", "PAYEE", "payee"),
  /** A role played by a person or organization that holds an insurance policy. The underwriter of that policy is the scoping entity.<p>
   <emph>Discussion:</emph>The identifier of the policy is captured in 'Role.id' when the Role is a policy holder.
</p>
<p>A particular policy may cover several individuals one of whom may be, but need not be, the policy holder. Thus the notion
   of covered party is a role that is distinct from that of the policy holder.
</p> */
  PolicyHolder(RelationshipFormal, "Policy holder", "POLHOLD", "policy holder"),
  /** An entity (player) that has been recognized as having certain training/experience or other characteristics that would make
said entity an appropriate performer for a certain activity. The scoper is an organization that educates or qualifies entities. */
  QualifiedEntity(RelationshipFormal, "Qualified entity", "QUAL", "qualified entity"),
  /** A role played by an individual who is a student of a school, which is the scoping entity. */
  Student(RelationshipFormal, "Student", "STD", "student"),
  /** A role played by a person or an organization. It is the party that <list role="ordered">
   <item>accepts fiscal responsibility for insurance plans and the policies created under those plans;</item>
   <item>administers and accepts fiscal responsibility for a program that provides coverage for services to eligible individuals; and/or</item>
   <item>has the responsibility to assess the merits of each risk and decide a suitable premium for accepting all or part of the risk.
      If played by an organization, this role may be further specified by an appropriate RoleCode.
   </item>
</list>
<p>
   <emph>Example:</emph>
   <list role="ordered">
      <item>A health insurer; </item>
      <item>Medicaid Program;</item>
      <item>Lloyd's of London</item>
   </list>
</p> */
  Underwriter(RelationshipFormal, "Underwriter", "UNDWRT", "underwriter"),
  /** A person responsible for the primary care of a patient at home. */
  Caregiver(MutualRelationship, "Caregiver", "CAREGIVER", "caregiver"),
  /** Links two people in a personal relationship. The character of the relationship must be defined by a PersonalRelationshipRoleType
code. The player and scoper are determined by PersonalRelationshipRoleType code as well. */
  PersonalRelationship(MutualRelationship, "Personal relationship", "PRS", "personal relationship"),
  /** An association for a playing Entity that is used, known, treated, handled, built, or destroyed, etc. under the auspices of
the scoping Entity. The playing Entity is passive in these roles (even though it may be active in other roles), in the sense
that the kinds of things done to it in this role happen without an agreement from the playing Entity. */
  Passive(Associative, "RoleClassPassive", null, null),
  /** A material (player) that can be administered to an Entity (scoper). */
  AdministerableMaterial(Passive, "Administerable Material", "ADMM", "Administerable Material"),
  /** A material (player) distributed by a distributor (scoper) who functions between a manufacturer and a buyer or retailer. */
  DistributedMaterial(Passive, "RoleClassDistributedMaterial", "DST", "distributed material"),
  /** Material (player) sold by a retailer (scoper), who also give advice to prospective buyers. */
  RetailedMaterial(DistributedMaterial, "Retailed material", "RET", "retailed material"),
  /** Scoped by the manufacturer */
  ManufacturedProduct(Passive, "RoleClassManufacturedProduct", "MANU", "manufactured product"),
  /** A manufactured material (player) that is used for its therapeutic properties. The manufacturer is the scoper. */
  TherapeuticAgent(ManufacturedProduct, "Therapeutic agent", "THER", "therapeutic agent"),
  /** A role played by a place at which services may be provided. */
  ServiceDeliveryLocation(Passive, "RoleClassServiceDeliveryLocation", "SDLOC", "service delivery location"),
  /** A role of a place (player) that is intended to house the provision of services. Scoper is the Entity (typically Organization)
that provides these services. This is not synonymous with "ownership." */
  DedicatedServiceDeliveryLocation(ServiceDeliveryLocation, "Dedicated service delivery location", "DSDLOC", "dedicated service delivery location"),
  /** A role played by a place at which health care services may be provided without prior designation or authorization. */
  IncidentalServiceDeliveryLocation(ServiceDeliveryLocation, "Incidental service delivery location", "ISDLOC", "incidental service delivery location"),
  /** A role in which the playing entity (material) provides access to another entity. The principal use case is intravenous (or
other bodily) access lines that preexist and need to be referred to for medication routing instructions. */
  Access(Passive, "Access", "ACCESS", "access"),
  /** Relates a place (playing Entity) as the location where a living subject (scoping Entity) was born. */
  Birthplace(Passive, "Birthplace", "BIRTHPL", "birthplace"),
  /** A role played by an entity that has been exposed to a person or animal suffering a contagious disease, or with a location
from which a toxin has been distributed. The player of the role is normally a person or animal, but it is possible that other
entity types could become exposed. The role is scoped by the source of the exposure, and it is quite possible for a person
playing the role of exposed party to also become the scoper a role played by another person. That is to say, once a person
has become infected, it is possible, perhaps likely, for that person to infect others.<p>Management of exposures and tracking exposed parties is a key function within public health, and within most public health
   contexts - exposed parties are known as "contacts."
</p> */
  ExposedEntity(Passive, "Exposed entity", "EXPR", "exposed entity"),
  /** The role of a material (player) that is the physical health chart belonging to an organization (scoper). */
  HealthChart(Passive, "Health chart", "HLTHCHRT", "health chart"),
  /** Entity that is currently in the possession of a holder (scoper), who holds, or uses it, usually based on some agreement with
the owner. */
  HeldEntity(Passive, "Held entity", "HLD", "held entity"),
  /** Roles played by entities and scoped by entities that identify them for various purposes. */
  IdentifiedEntity(Passive, "Identified entity", "IDENT", "identified entity"),
  /** An entity (player) that is maintained by another entity (scoper). This is typical role held by durable equipment. The scoper
assumes responsibility for proper operation, quality, and safety. */
  MaintainedEntity(Passive, "Maintained entity", "MNT", "maintained entity"),
  /** An Entity (player) for which someone (scoper) is granted by law the right to call the material (player) his own. This entitles
the scoper to make decisions about the disposition of that material. */
  OwnedEntity(Passive, "Owned entity", "OWN", "owned entity"),
  /** Definition: Relates a place (playing Entity) as the location where a living subject (scoping Entity) died. */
  PlaceOfDeath(Passive, "Place of death", "DEATHPLC", "place of death"),
  /** A product regulated by some governmentatl orgnization. The role is played by Material and scoped by Organization.<p>Rationale: To support an entity clone used to identify the NDC number for a drug product.</p> */
  RegulatedProduct(Passive, "Regulated product", "RGPR", "regulated product"),
  /** Relates a place entity (player) as the region over which the scoper (typically an Organization) has certain authority (jurisdiction).
For example, the Calgary Regional Health Authority (scoper) has authority over the territory "Region 4 of Alberta" (player)
in matters of health. */
  TerritoryOfAuthority(Passive, "Territory of authority", "TERR", "territory of authority"),
  /** A role a product plays when a guarantee is given to the purchaser by the seller (scoping entity) stating that the product
is reliable and free from known defects and that the seller will repair or replace defective parts within a given time limit
and under certain conditions. */
  WarrantedProduct(Passive, "Warranted product", "WRTE", "warranted product"),
  /** A relationship in which the scoping Entity defines or specifies what the playing Entity is. Thus, the player's "being" (Greek:
ontos) is specified. */
  Ontological(Root, "RoleClassOntological", null, null),
  /** Relates a specialized material concept (player) to its generalization (scoper). */
  IsSpeciesEntity(Ontological, "RoleClassIsSpeciesEntity", "GEN", "has generalization"),
  /** A special link between pharmaceuticals indicating that the target (scoper) is a generic for the source (player). */
  HasGeneric(IsSpeciesEntity, "Has generic", "GRIC", "has generic"),
  /** An individual piece of material (player) instantiating a class of material (scoper). */
  Instance(Ontological, "Instance", "INST", "instance"),
  /** The "same" roleclass asserts an identity between playing and scoping entities: that they are in fact instances of the same
entity and, in the case of discrepancies (e.g different DOB, gender), that one or both are in error.<p>
   <emph>Usage:</emph>
</p>
<p>playing and scoping entities must have same classcode, but need not have identical attributes or values.</p>
<p>
   <emph>Example: </emph>
</p>
<p>a provider registry maintains sets of conflicting demographic data for what is reported to be the same individual.</p> */
  Same(Ontological, "Same", "SAME", "same"),
  /** Relates a prevailing record of an Entity (scoper) with another record (player) that it subsumes.<p>
   <emph>Examples:</emph> Show a correct new Person object (scoper) that subsumes one or more duplicate Person objects that had accidentally been created
   for the same physical person.
</p>
<p>
   <emph>Constraints:</emph> Both the player and scoper must have the same classCode.
</p> */
  SubsumedBy(Ontological, "Subsumed by", "SUBY", "subsumed by"),
  /** An entity that subsumes the identity of another. Used in the context of merging documented entity instances. Both the player
and scoper must have the same classCode.<p>The use of this code is 
   <emph role="strong">deprecated</emph> in favor of the term SUBY which is its inverse and is more ontologically correct.
</p> */
  Subsumer(Ontological, "Subsumer", "SUBS", "subsumer"),
  /** An association between two Entities where the playing Entity is considered in some way "part" of the scoping Entity, e.g.,
as a member, component, ingredient, or content. Being "part" in the broadest sense of the word can mean anything from being
an integral structural component to a mere incidental temporary association of a playing Entity with a (generally larger)
scoping Entity. */
  Partitive(Root, "RoleClassPartitive", null, null),
  /** Relates a component (player) to a mixture (scoper). E.g., Glucose and Water are ingredients of D5W, latex may be an ingredient
in a tracheal tube. */
  IngredientEntity(Partitive, "RoleClassIngredientEntity", "INGR", "ingredient"),
  /**  */
  InactiveIngredient(IngredientEntity, "RoleClassInactiveIngredient", "IACT", "inactive ingredient"),
  /** A substance (player) influencing the optical aspect of material (scoper). */
  ColorAdditive(InactiveIngredient, "Color additive", "COLR", "color additive"),
  /** A substance (player) added to a mixture (scoper) to make it taste a certain way. In food the use is obvious, in pharmaceuticals
flavors can hide disgusting taste of the active ingredient (important in pediatric treatments). */
  FlavorAdditive(InactiveIngredient, "Flavor additive", "FLVR", "flavor additive"),
  /** A substance (player) added to a mixture (scoper) to prevent microorganisms (fungi, bacteria) to spoil the mixture. */
  Preservative(InactiveIngredient, "Preservative", "PRSV", "preservative"),
  /** A stabilizer (player) added to a mixture (scoper) in order to prevent the molecular disintegration of the main substance. */
  Stabilizer(InactiveIngredient, "Stabilizer", "STBL", "stabilizer"),
  /** A therapeutically active ingredient (player) in a mixture (scoper), where the mixture is typically a manufactured pharmaceutical. */
  ActiveIngredient(IngredientEntity, "Active ingredient", "ACTI", "active ingredient"),
  /** The molecule or ion that is responsible for the intended pharmacological action of the drug substance, excluding those appended
or associated parts of the molecule that make the molecule an ester, salt (including a salt with hydrogen or coordination
bonds), or other noncovalent derivative (such as a complex, chelate, or clathrate).<p>Examples: heparin-sodium and heparin-potassium have the same active moiety, heparin; the active moiety of morphine-hydrochloride
   is morphine.
</p> */
  ActiveMoiety(IngredientEntity, "Active moiety", "ACTM", "active moiety"),
  /** An ingredient (player) that is added to a base (scoper), that amounts to a minor part of the overall mixture. */
  Additive(IngredientEntity, "Additive", "ADTV", "additive"),
  /** A base ingredient (player) is what comprises the major part of a mixture (scoper). E.g., Water in most i.v. solutions, or
Vaseline in salves. Among all ingredients of a material, there should be only one base. A base substance can, in turn, be
a mixture. */
  Base(IngredientEntity, "Base", "BASE", "base"),
  /** Relates an entity (player) to a location (scoper) at which it is present in some way. This presence may be limited in time. */
  LocatedEntity(Partitive, "RoleClassLocatedEntity", "LOCE", "located entity"),
  /** Relates an entity (player) (e.g. a device) to a location (scoper) at which it is normally found or stored when not used. */
  StoredEntity(LocatedEntity, "Stored entity", "STOR", "stored entity"),
  /** A role played by a material entity that is a specimen for an act. It is scoped by the source of the specimen. */
  Specimen(Partitive, "RoleClassSpecimen", "SPEC", "specimen"),
  /** A portion (player) of an original or source specimen (scoper) used for testing or transportation. */
  Aliquot(Specimen, "Aliquot", "ALQT", "aliquot"),
  /** A microorganism that has been isolated from other microorganisms or a source matrix. */
  Isolate(Specimen, "Isolate", "ISLT", "isolate"),
  /** Relates a material as the content (player) to a container (scoper). Unlike ingredients, the content and a container remain
separate (not mixed) and the content can be removed from the container. A content is not part of an empty container. */
  Content(Partitive, "Content", "CONT", "content"),
  /** A role played by an entity that is a member of a group. The group provides the scope for this role.<p>Among other uses, groups as used in insurance (groups of covered individuals) and in scheduling where resources may be grouped
   for scheduling and logistical purposes.
</p> */
  Member(Partitive, "Member", "MBR", "member"),
  /** An association between two Entities where the playing Entity is considered in some way "part" of the scoping Entity, e.g.,
as a member, component, ingredient, or content. Being "part" in the broadest sense of the word can mean anything from being
an integral structural component to a mere incidental temporary association of a playing Entity with a (generally larger)
scoping Entity. */
  Part(Partitive, "Part", "PART", "part");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.110");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("RoleClass");

  private final RoleClass _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final RoleClass _parent2;
  private final RoleClass _parent3; // well, found a 3rd :(
  private EnumSet<RoleClass> _impliedConcepts = null;

  private RoleClass(RoleClass parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private RoleClass(RoleClass parent, RoleClass parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private RoleClass(RoleClass parent, RoleClass parent2, RoleClass parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<RoleClass> getImpliedConcepts() {
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
    else if (that instanceof RoleClass)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof RoleClass))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final RoleClass thatRoleClass = (RoleClass)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatRoleClass));
  }

  public RoleClass mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof RoleClass))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final RoleClass thatRoleClass = (RoleClass)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<RoleClass> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatRoleClass.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    RoleClass mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(RoleClass candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,RoleClass> _codeMap = null;

  public static RoleClass valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(RoleClass.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(RoleClass concept : EnumSet.allOf(RoleClass.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      RoleClass concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.110" + ", domain = " + "RoleClass"));
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

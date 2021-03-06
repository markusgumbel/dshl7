package net.gumbix.hl7javasig.demo;

import org.hl7.rim.*;
import org.hl7.types.*;
import org.hl7.types.enums.ActRelationshipType;
import org.hl7.types.impl.*;

import java.util.ArrayList;
import java.util.List;

import static org.hl7.types.DatatypeTool.EntityNameTool.*;
import static org.hl7.types.enums.AddressPartType.*;
import static org.hl7.types.enums.ActStatus.*;

public class CDAVHitGEx1 {

  public static void main(String[] args) throws Exception {

    Document clinicalDocument = (Document) RimObjectFactory
            .getInstance().createRimObject("Document");
    clinicalDocument.setCloneCode(CSimpl.valueOf("clinicalDocument", "1.22.333.4444"));

    clinicalDocument.setTypeId(IIimpl.valueOf("2.16.840.1.113883.1.3", "POCD_HD000040"));
    clinicalDocument.setId(makeId("1.2.276.0.76.3645.239", "6014161089"));
    clinicalDocument.setTitle(STjlStringAdapter.
            valueOf("Arztbrief auf der Basis von CDA Release 2"));

    TS effectiveTime = TSjuDateAdapter.valueOf("20050829");
    ValueFactory valueFactory = new ValueFactoryImpl();
    SET<TS> effectiveTimeSet = valueFactory.IVLvalueOf(BLimpl.TRUE,
            effectiveTime, effectiveTime, BLimpl.TRUE);
    clinicalDocument.setEffectiveTime(effectiveTimeSet);

    // TODO display name is missing
    clinicalDocument.setCode(CSimpl.valueOf("11488-4", "2.16.840.1.113883.6.1"));

    clinicalDocument.setTypeId(IIimpl.valueOf("2.16.840.1.113883.1.3", "POCD_HD000040"));
    List<CE> setConf = new ArrayList<CE>();
    setConf.add(CSimpl.valueOf("N", "2.16.840.1.113883.5.25"));
    SETjuSetAdapter.valueOf(setConf);
    clinicalDocument.setConfidentialityCode(SETjuSetAdapter.valueOf(setConf));

    clinicalDocument.setLanguageCode(CEimpl.valueOf("de-DE", "1.22.333.4444", " "));

    // recordTarget
    Participation recordTarget = (Participation) RimObjectFactory
            .getInstance().createRimObject("Participation");
    recordTarget.setCloneCode(CSimpl.valueOf("recordTarget", "1.22.333.444"));

    Role patientRole = (Patient) RimObjectFactory.getInstance()
            .createRimObject("Patient");
    patientRole.setCloneCode(CSimpl.valueOf("patientRole", "1.22.333.444"));
    // TODO 2x
    SET<II> mrnset = makeId("6245", "2.16.840.1.113883.3.933");
    patientRole.setId(mrnset);

    Person patient = (Person) RimObjectFactory.getInstance().createRimObject("Person");
    patient.setCloneCode(CSimpl.valueOf("patient", "1.22.333.444"));

    BAG<EN> patName = null;    // Strange, isn't it?
    patName = setGivenName(patName, "Paul");
    patName = setFamilyName(patName, "Pappel");
    patient.setName(patName);
    patient.setAdministrativeGenderCode(CEimpl.valueOf("M", "2.16.840.1.113883.5.1", ""));
    patient.setBirthTime(TSjuDateAdapter.valueOf("19551217"));
    // TODO birthplace

    /*
    // Yields null pointer exception
    BAG<AD> address = null;
    address = setStreetAddress(address, "Riedemannweg");
    address = setPostalCode(address, "13627");
    address = setMunicipality(address, "Berlin");
    */
    List<ADXP> patAddrElemList = new ArrayList<ADXP>();
    patAddrElemList.add(ADXPimpl.valueOf("Riedemannweg 59", StreetAddressLine));
    patAddrElemList.add(ADXPimpl.valueOf("13627", PostalCode));
    patAddrElemList.add(ADXPimpl.valueOf("Berlin", Municipality));
    AD patAddr = ADimpl.valueOf(patAddrElemList);
    List<AD> patAddrList = new ArrayList<AD>();
    patAddrList.add(patAddr);
    BAG patAddrBag = BAGjuListAdapter.valueOf(patAddrList);
    patientRole.setAddr(patAddrBag);

    List<TEL> patTelList = new ArrayList<TEL>();
    patTelList.add(TELimpl.valueOf("tel:030.456.345345"));
    BAG patTelBag = BAGjuListAdapter.valueOf(patTelList);
    patientRole.setTelecom(patTelBag);

    Organization provOrg = (Organization) RimObjectFactory.getInstance()
            .createRimObject("Organization");
    provOrg.setCloneCode(CSimpl.valueOf("providerOrganziation", "1.22.333.444"));
    List<ADXP> provOrgAddrElemList = new ArrayList<ADXP>();
    provOrgAddrElemList.add(ADXPimpl.valueOf("Krankenhausstraße", StreetName));
    provOrgAddrElemList.add(ADXPimpl.valueOf("51371", PostalCode));
    provOrgAddrElemList.add(ADXPimpl.valueOf("Leverkusen", Municipality));
    AD provOrgAddr = ADimpl.valueOf(provOrgAddrElemList);
    List<AD> provOrgAddrList = new ArrayList<AD>();
    provOrgAddrList.add(provOrgAddr);
    BAG provOrgAddrBag = BAGjuListAdapter.valueOf(provOrgAddrList);
    provOrg.setAddr(provOrgAddrBag);

    patientRole.setPlayer(patient);
    patientRole.setScoper(provOrg);
    recordTarget.setRole(patientRole);
    clinicalDocument.addParticipation(recordTarget);

    // author
    Participation author = (Participation) RimObjectFactory.getInstance()
            .createRimObject("Participation");
    author.setCloneCode(CSimpl.valueOf("author", "1.22.333.4444"));

    Role assignedAuthor = (Role) RimObjectFactory.getInstance().createRimObject("Role");
    assignedAuthor.setCloneCode(CSimpl.valueOf("assignedAuthor", "1.22.333.4444"));
    author.setRole(assignedAuthor);
    assignedAuthor.setId(makeId("2.16.840.1.113883.3.67.933", "ied8984938"));

    Person assignedPerson = (Person) RimObjectFactory.
            getInstance().createRimObject("Person");
    assignedPerson.setCloneCode(CSimpl.valueOf("assignedPerson", "1.22.333.4444"));

    BAG<EN> authName = null;
    authName = setPrefixName(authName, "Dr. med.");
    authName = setGivenName(authName, "Theo");
    authName = setFamilyName(authName, "Phyllin");
    assignedPerson.setName(authName);

    assignedAuthor.setPlayer(assignedPerson);

    Organization authOrg = (Organization) RimObjectFactory.getInstance()
            .createRimObject("Organization");
    authOrg.setCloneCode(CSimpl.valueOf("representedOrganization", "1.22.333.444"));

    List<ENXP> authReprNameParts = new ArrayList<ENXP>();
    authReprNameParts.add(ENXPimpl.valueOf("Praxis Dr. med. Phyllin", null));
    EN authReprOrgName = ENimpl.valueOf(authReprNameParts);
    List<EN> authReprNameList = new ArrayList<EN>();
    authReprNameList.add(authReprOrgName);
    BAG<EN> authReprNameBag = BAGjuCollectionAdapter.valueOf(authReprNameList);
    authOrg.setName(authReprNameBag);

    List<TEL> authOrgTelList = new ArrayList<TEL>();
    authOrgTelList.add(TELimpl.valueOf("tel:0214.2127070"));
    authOrgTelList.add(TELimpl.valueOf("tel:0214.212707122"));
    BAG authOrgTelBag = BAGjuListAdapter.valueOf(authOrgTelList);
    authOrg.setTelecom(authOrgTelBag);

    List<ADXP> authOrgAddrElemList = new ArrayList<ADXP>();
    authOrgAddrElemList.add(ADXPimpl.valueOf("Krankenhausstraße", StreetName));
    authOrgAddrElemList.add(ADXPimpl.valueOf("51371", PostalCode));
    authOrgAddrElemList.add(ADXPimpl.valueOf("Leverkusen", Municipality));
    AD authOrgAddr = ADimpl.valueOf(authOrgAddrElemList);
    List<AD> authOrgAddrList = new ArrayList<AD>();
    authOrgAddrList.add(authOrgAddr);
    BAG authOrgAddrBag = BAGjuListAdapter.valueOf(authOrgAddrList);
    authOrg.setAddr(authOrgAddrBag);

    assignedAuthor.setScoper(authOrg);
    clinicalDocument.addParticipation(author);

    // custodian
    Participation custodian = (Participation) RimObjectFactory
            .getInstance().createRimObject("Participation");
    custodian.setCloneCode(CSimpl.valueOf("custodian", "1.22.333.4444"));

    Role assignedCustodian = (Role) RimObjectFactory
            .getInstance().createRimObject("Role");
    assignedCustodian.setCloneCode(CSimpl.valueOf("assignedCustodian",
            "1.22.333.4444"));
    custodian.setRole(assignedCustodian);

    Organization representedCustodianOrganization = (Organization) RimObjectFactory
            .getInstance().createRimObject("Organization");
    representedCustodianOrganization.setCloneCode(CSimpl.valueOf(
            "representedCustodianOrganization", "1.22.333.4444"));
    representedCustodianOrganization.setId(makeId("1.2.276.0.58", "M345"));

    List<ENXP> orgNameParts = new ArrayList<ENXP>();
    orgNameParts.add(ENXPimpl.valueOf("Praxis Dr. med. Phyllin", null));
    EN orgName = ENimpl.valueOf(orgNameParts);
    List<EN> orgNameList = new ArrayList<EN>();
    orgNameList.add(orgName);
    BAG<EN> orgNameBag = BAGjuCollectionAdapter.valueOf(orgNameList);

    List<ADXP> orgAddrElemList = new ArrayList<ADXP>();
    orgAddrElemList.add(ADXPimpl.valueOf("Krankenhausstraße", StreetName));
    // TODO housenumber
    orgAddrElemList.add(ADXPimpl.valueOf("51371", PostalCode));
    orgAddrElemList.add(ADXPimpl.valueOf("Leverkusen", Municipality));
    AD orgAddr = ADimpl.valueOf(orgAddrElemList);
    List<AD> orgAddrList = new ArrayList<AD>();
    orgAddrList.add(orgAddr);
    BAG<AD> orgAddrBag = BAGjuListAdapter.valueOf(orgAddrList);

    representedCustodianOrganization.setName(orgNameBag);
    representedCustodianOrganization.setAddr(orgAddrBag);
    assignedCustodian.setScoper(representedCustodianOrganization);
    clinicalDocument.addParticipation(custodian);

    // informationRecipient
    Participation informationRecipient = (Participation) RimObjectFactory
            .getInstance().createRimObject("Participation");
    informationRecipient.setCloneCode(CSimpl.valueOf("informationRecipient",
            "1.22.333.4444"));

    Role intendedRecipient = (Role) RimObjectFactory.
            getInstance().createRimObject("Role");
    intendedRecipient.setCloneCode(CSimpl.valueOf("intendedRecipient", "1.22.333.4444"));
    informationRecipient.setRole(intendedRecipient);


    Person infRecPerson = (Person) RimObjectFactory.
            getInstance().createRimObject("Person");
    infRecPerson.setCloneCode(CSimpl.valueOf(
            "informationRecipient", "1.22.333.4444"));
    BAG<EN> infRecPersonName = null;
    infRecPersonName = setPrefixName(infRecPersonName, "Dr. med.");
    infRecPersonName = setGivenName(infRecPersonName, "Kai");
    infRecPersonName = setFamilyName(infRecPersonName, "Heitmann");
    infRecPerson.setName(infRecPersonName);

    intendedRecipient.setPlayer(infRecPerson);

    Organization receivedOrganization = (Organization) RimObjectFactory
            .getInstance().createRimObject("Organization");
    receivedOrganization.setCloneCode(CSimpl.valueOf(
            "receivedOrganization", "1.22.333.4444"));
    // receivedOrganization.setId(makeId("1.2.276.0.58", "M345"));

    List<ENXP> recOrgNameParts = new ArrayList<ENXP>();
    recOrgNameParts.add(ENXPimpl.valueOf("Gemeinschaftspraxis Dr. Heitmann", null));
    EN recOrgName = ENimpl.valueOf(recOrgNameParts);
    List<EN> recOrgNameList = new ArrayList<EN>();
    recOrgNameList.add(recOrgName);
    BAG<EN> recOrgNameBag = BAGjuCollectionAdapter.valueOf(recOrgNameList);

    List<ADXP> recOrgAddrElemList = new ArrayList<ADXP>();
    recOrgAddrElemList.add(ADXPimpl.valueOf("Mühlenweg 1a", StreetAddressLine));
    recOrgAddrElemList.add(ADXPimpl.valueOf("52152", PostalCode));
    recOrgAddrElemList.add(ADXPimpl.valueOf("Simmerath", Municipality));
    AD recOrgAddr = ADimpl.valueOf(recOrgAddrElemList);
    List<AD> recOrgAddrList = new ArrayList<AD>();
    recOrgAddrList.add(recOrgAddr);
    BAG<AD> recOrgAddrBag = BAGjuListAdapter.valueOf(recOrgAddrList);

    List<TEL> recOrgTelList = new ArrayList<TEL>();
    recOrgTelList.add(TELimpl.valueOf("fax:02473.54637.2938"));
    BAG recOrgTelBag = BAGjuListAdapter.valueOf(recOrgTelList);

    receivedOrganization.setTelecom(recOrgTelBag);
    receivedOrganization.setName(recOrgNameBag);
    receivedOrganization.setAddr(recOrgAddrBag);
    
    intendedRecipient.setScoper(receivedOrganization);
    clinicalDocument.addParticipation(informationRecipient);

    // related documents:
    ActRelationship relatedDocument = (ActRelationship) RimObjectFactory
            .getInstance().createRimObject("ActRelationship");
    relatedDocument.setCloneCode(CSimpl.valueOf("relatedDocument", "1.22.333.4444"));
    relatedDocument.setTypeCode(ActRelationshipType.IsAppendage);
    Document parentDocument = (Document) RimObjectFactory
            .getInstance().createRimObject("Document");
    parentDocument.setCloneCode(CSimpl.valueOf("parentDocument", "clinicalDocument"));
    parentDocument.setId(makeId("1.2.276.0.58", "463957847"));
    relatedDocument.setTarget(parentDocument);
    clinicalDocument.addOutboundRelationship(relatedDocument);

    // authorization:
    ActRelationship authorization = (ActRelationship) RimObjectFactory
            .getInstance().createRimObject("ActRelationship");
    authorization.setCloneCode(CSimpl.valueOf("authorization", "1.22.333.4444"));
    Document consent = (Document) RimObjectFactory
            .getInstance().createRimObject("Document");
    consent.setCloneCode(CSimpl.valueOf("consent", "1.22.333.4444"));
    consent.setId(makeId("1.2.276.0.76.3645.239", "cs856727-298784"));
    consent.setCode(CDimpl.valueOf("1.2.276.0.76.5.310", "3-00d"));
    authorization.setTarget(consent);
    consent.setStatusCode(Completed);
    clinicalDocument.addOutboundRelationship(authorization);

    // Sections:

    ActRelationship component = (ActRelationship) RimObjectFactory
            .getInstance().createRimObject("ActRelationship");
    component.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));

    Act structuredBody = (Act) RimObjectFactory.getInstance().createRimObject("Act");
    structuredBody.setCloneCode(CSimpl.valueOf("structuredBody", "1.22.333.4444"));
    component.setTarget(structuredBody);

    ActRelationship subcomponent = (ActRelationship) RimObjectFactory
            .getInstance().createRimObject("ActRelationship");
    subcomponent.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));

    Act section = (Act) RimObjectFactory.getInstance().createRimObject("Act");
    section.setCloneCode(CSimpl.valueOf("section", "1.22.333.4444"));

    section.setCode(CDimpl.valueOf("10164-2", "2.16.840.1.113883.6.1"));

    ST title = STjlStringAdapter.valueOf("29.08.2005: Anamnese");

    ST text = STjlStringAdapter
            .valueOf("Sei Jahren wiederholt\n" +
                    "<content styleCode=\"Bold\">chronische Bronchitiden</content> " +
                    "besonders bei kalter Luft. Bei Anstrengung expiratorische " +
                    "Atemnot. Kontakt mit Haustieren.");
    section.setTitle(title); // this didn't show up
    section.setText(text);

    subcomponent.setTarget(section);

    structuredBody.addOutboundRelationship(subcomponent);
    clinicalDocument.addOutboundRelationship(component);

    Util.buildMessage(clinicalDocument, "POCD_HD000040", "VHitG01-Java.xml");
  }

  public static SET<II> makeId(String ext, String root) {
    II iid = IIimpl.valueOf(ext, root);
    List<II> idarr = new ArrayList<II>();
    idarr.add(iid);
    return SETjuSetAdapter.valueOf(idarr);
  }
}
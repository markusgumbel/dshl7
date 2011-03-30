package net.gumbix.hl7javasig.demo;

import org.hl7.rim.*;
import org.hl7.types.*;
import org.hl7.types.enums.ActRelationshipType;
import org.hl7.types.impl.*;

import java.util.ArrayList;
import java.util.List;

import static org.hl7.types.DatatypeTool.EntityNameTool.*;
import static org.hl7.types.enums.AddressPartType.*;

public class CDAVHitGEx1 {

    public static void main(String[] args) throws Exception {

        Document clinicalDocument = (Document) RimObjectFactory
                .getInstance().createRimObject("Document");
        clinicalDocument.setCloneCode(CSimpl.valueOf("clinicalDocument",
                "clinicalDocument"));

        clinicalDocument.setTypeId(IIimpl.valueOf("2.16.840.1.113883.1.3", "POCD_HD000040"));
        SET<II> idset = makeId("6014161089", "1.2.276.0.76.3645.239");
        clinicalDocument.setId(idset);

        TS effectiveTime = TSjuDateAdapter.valueOf("20050829");
        ValueFactory valueFactory = new ValueFactoryImpl();
        SET<TS> effectiveTimeSet = valueFactory.IVLvalueOf(BLimpl.TRUE,
                effectiveTime, effectiveTime, BLimpl.TRUE);
        clinicalDocument.setEffectiveTime(effectiveTimeSet);

        CD code = CSimpl.valueOf("11488-4", "2.16.840.1.113883.6.1");
        // display name is missing
        clinicalDocument.setCode(code);

        List<CE> setConf = new ArrayList<CE>();
        setConf.add(CSimpl.valueOf("N", "2.16.840.1.113883.5.25"));
        SETjuSetAdapter.valueOf(setConf);
        clinicalDocument.setConfidentialityCode(SETjuSetAdapter.valueOf(setConf));

        clinicalDocument.setLanguageCode(CEimpl.valueOf("de-DE", "", ""));

        // recordTarget
        Participation recordTarget = (Participation) RimObjectFactory
                .getInstance().createRimObject("Participation");
        recordTarget.setCloneCode(CSimpl.valueOf("recordTarget", "1.22.333.444"));

        Role patientRole = (Patient) RimObjectFactory.getInstance()
                .createRimObject("Patient");
        patientRole.setCloneCode(CSimpl.valueOf("patientRole", "1.22.333.444"));

        Person patient = (Person) RimObjectFactory.getInstance()
                .createRimObject("Person");
        patient.setCloneCode(CSimpl.valueOf("patient", "1.22.333.444"));

        // TODO 2x
        SET<II> mrnset = makeId("123433", "123.234.345");
        patient.setId(mrnset);

        BAG<EN> name = null;    // Strange, isn't it?
        name = setGivenName(name, "Paul");
        name = setFamilyName(name, "Pappel");
        patient.setName(name);

        /*
        // Yields null pointer exception
        BAG<AD> address = null;
        address = setStreetAddress(address, "Riedemannweg");
        address = setPostalCode(address, "13627");
        address = setMunicipality(address, "Berlin");
        */
        List<ADXP> aList = new ArrayList<ADXP>();
        aList.add(ADXPimpl.valueOf("Riedmannweg", StreetType));
        aList.add(ADXPimpl.valueOf("13627", PostalCode));
        AD addr = ADimpl.valueOf(aList);
        List<AD> paddr = new ArrayList<AD>();
        paddr.add(addr);
        BAG address = BAGjuListAdapter.valueOf(paddr);

        patientRole.setPlayer(patient);
        patientRole.setAddr(address);
        recordTarget.setRole(patientRole);
        clinicalDocument.addParticipation(recordTarget);

        // author
        Participation author = (Participation) RimObjectFactory.getInstance()
                .createRimObject("Participation");
        author.setCloneCode(CSimpl.valueOf("author", "1.22.333.4444"));

        Role assignedAuthor = (Role) RimObjectFactory.getInstance()
                .createRimObject("Role");
        assignedAuthor.setCloneCode(CSimpl.valueOf("assignedAuthor", "1.22.333.4444"));
        author.setRole(assignedAuthor);

        Person assignedPerson = (Person) RimObjectFactory
                .getInstance().createRimObject("Person");
        assignedPerson.setCloneCode(CSimpl.valueOf("assignedPerson", "1.22.333.4444"));

        BAG<EN> docNameBag = null;
        docNameBag = setPrefixName(docNameBag, "Dr. med.");
        docNameBag = setGivenName(docNameBag, "Theo");
        docNameBag = setFamilyName(docNameBag, "Phyllin");
        assignedPerson.setName(docNameBag);

        assignedAuthor.setPlayer(assignedPerson);
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
        representedCustodianOrganization.setId(makeId("M345", "1.2.276.0.58"));

        // set the name here
        List<ENXP> orgNameParts = new ArrayList<ENXP>();
        orgNameParts.add(ENXPimpl.valueOf("Praxis Dr. med. Phyllin", null));
        EN orgName = ENimpl.valueOf(orgNameParts);
        List<EN> orgNameList = new ArrayList<EN>();
        orgNameList.add(orgName);
        BAG<EN> orgNameBag = BAGjuCollectionAdapter.valueOf(orgNameList);

        aList = new ArrayList<ADXP>();
        aList.add(ADXPimpl.valueOf("Krankenhausstraße", StreetType));
        // TODO housenumber
        aList.add(ADXPimpl.valueOf("51371", PostalCode));
        aList.add(ADXPimpl.valueOf("Leverkusen", Municipality));
        addr = ADimpl.valueOf(aList);
        paddr = new ArrayList<AD>();
        paddr.add(addr);
        address = BAGjuListAdapter.valueOf(paddr);

        representedCustodianOrganization.setName(orgNameBag);
        representedCustodianOrganization.setAddr(address);
        assignedCustodian.setScoper(representedCustodianOrganization);
        clinicalDocument.addParticipation(custodian);

        // related documents:
        ActRelationship relatedDocument = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        relatedDocument.setCloneCode(CSimpl.valueOf("relatedDocument",
                "1.22.333.4444"));
        relatedDocument.setType(ActRelationshipType.IsAppendage);
        Document parentDocument = (Document) RimObjectFactory
                .getInstance().createRimObject("Document");
        parentDocument.setCloneCode(CSimpl.valueOf("parentDocument",
                "clinicalDocument"));
        parentDocument.setId(makeId("1.2.276.0.58", "463957847"));
        relatedDocument.setTarget(parentDocument);
        clinicalDocument.addOutboundRelationship(relatedDocument);

        // authorization:
        ActRelationship authorization = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        authorization.setCloneCode(CSimpl.valueOf("authorization",
                "1.22.333.4444"));
        Document consent = (Document) RimObjectFactory
                .getInstance().createRimObject("Document");
        consent.setCloneCode(CSimpl.valueOf("consent",
                "1.22.333.4444"));
        consent.setId(makeId("1.2.276.0.76.3645.239", "cs856727-298784"));
        consent.setCode(CDimpl.valueOf("1.2.276.0.76.5.310", "3-00d"));
        authorization.setTarget(consent);
        clinicalDocument.addOutboundRelationship(authorization);

        // Sections:

        ActRelationship component = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        component.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));

        Act structuredBody = (Act) RimObjectFactory.getInstance()
                .createRimObject("Act");
        structuredBody.setCloneCode(CSimpl.valueOf("structuredBody",
                "1.22.333.4444"));
        component.setTarget(structuredBody);

        ActRelationship subcomponent = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        subcomponent.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));

        Act section = (Act) RimObjectFactory.getInstance()
                .createRimObject("Act");
        section.setCloneCode(CSimpl.valueOf("section", "1.22.333.4444"));

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

        Util.buildMessage(clinicalDocument, "POCD_HD000040");
    }

    public static SET<II> makeId(String ext, String root) {
        II iid = IIimpl.valueOf(ext, root);
        List<II> idarr = new ArrayList<II>();
        idarr.add(iid);
        return SETjuSetAdapter.valueOf(idarr);
    }
}
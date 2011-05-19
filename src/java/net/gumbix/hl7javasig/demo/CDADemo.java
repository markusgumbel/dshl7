package net.gumbix.hl7javasig.demo;

import org.hl7.meta.MessageType;
import org.hl7.meta.mif.MessageTypeLoaderAdapter;
import org.hl7.rim.*;
import org.hl7.types.*;
import org.hl7.types.impl.*;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.RimGraphXMLSpeaker;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CDADemo {


    public static void main(String[] args) throws Exception {
        CDADemo mcda = new CDADemo();
        Document cda = mcda.makeCDA();
        // convert it to message
        mcda.buildMessage(cda);
        // now print it out
    }

    /**
     * My theory: I create an arbitrary RIM object (or a graph if you
     * include the references objects). This has nothing to do with
     * the CDA domain as such. This is later checked in the buildMessage
     * method.
     * @return
     * @throws FactoryException
     */
    public Document makeCDA() throws FactoryException {
        Document clinicalDocument = (org.hl7.rim.Document) RimObjectFactory
                .getInstance().createRimObject("Document");
        clinicalDocument.setCloneCode(CSimpl.valueOf("clinicalDocument",
                "clinicalDocument"));

        SET<II> idset = makeId("1234567", "123.123.123");
        clinicalDocument.setId(idset);

        final TS effectiveTime = TSjuDateAdapter.valueOf("20061105212900.000");
        ValueFactory valueFactory = new ValueFactoryImpl();
        final SET<TS> effectiveTimeSet = valueFactory.IVLvalueOf(BLimpl.TRUE,
                effectiveTime, effectiveTime, BLimpl.TRUE);

        clinicalDocument.setEffectiveTime(effectiveTimeSet);

        CD code = CSimpl.valueOf("11488-4", "2.16.840.1.113883.6.1");
        clinicalDocument.setCode(code);

        Participation recordTarget = (Participation) RimObjectFactory
                .getInstance().createRimObject("Participation");
        recordTarget.setCloneCode(CSimpl.valueOf("recordTarget",
                "1.22.333.4444"));
        CS typeCode = CSimpl.valueOf("RCT", "getCodeSystem");
        recordTarget.setTypeCode(typeCode);

        Role patientRole = (Patient) RimObjectFactory.getInstance()
                .createRimObject("Patient");

        patientRole.setCloneCode(CSimpl.valueOf("patientRole", "1.22.333.4444"));
        patientRole.setClassCode(CSimpl.valueOf("PAT", "1.22.333.4444"));

        Person patient = (org.hl7.rim.Person) RimObjectFactory.getInstance()
                .createRimObject("Person");
        patient.setCloneCode(CSimpl.valueOf("patient", "1.22.333.4444"));
        patient.setClassCode(CSimpl.valueOf("PSN", "1.22.333.4444"));

        SET<II> mrnset = makeId("123433", "123.234.345");
        patient.setId(mrnset);

        BAG<EN> name = null;
        name = DatatypeTool.EntityNameTool.setPrefixName(name, "Mr");
        name = DatatypeTool.EntityNameTool.setGivenName(name, "Robert");
        name = DatatypeTool.EntityNameTool.setFamilyName(name, "Zimmerman");
        name = DatatypeTool.EntityNameTool.setSuffixName(name, "VII");

        patient.setName(name);

        patientRole.setPlayer(patient);

        recordTarget.setRole(patientRole);
        clinicalDocument.addParticipation(recordTarget);

        // author

        Participation author = (Participation) RimObjectFactory.getInstance()
                .createRimObject("Participation");
        author.setCloneCode(CSimpl.valueOf("author", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("AUT", "1.22.333.4444");
        author.setTypeCode(typeCode);
        // Now add the Role

        Role assignedAuthor = (Role) RimObjectFactory.getInstance()
                .createRimObject("Role");
        assignedAuthor.setCloneCode(CSimpl.valueOf("assignedAuthor",
                "1.22.333.4444"));
        assignedAuthor.setClassCode(CSimpl.valueOf("ASSIGNED", "1.22.333.4444"));

        author.setRole(assignedAuthor);

        Person assignedPerson = (org.hl7.rim.Person) RimObjectFactory
                .getInstance().createRimObject("Person");
        assignedPerson.setCloneCode(CSimpl.valueOf("assignedPerson",
                "1.22.333.4444"));
        assignedPerson.setClassCode(CSimpl.valueOf("PSN", "1.22.333.4444"));

        // set the name here

        BAG<EN> docNameBag = null;
        docNameBag = DatatypeTool.EntityNameTool.setPrefixName(docNameBag, "Dr");
        docNameBag = DatatypeTool.EntityNameTool.setGivenName(docNameBag,"John");
        docNameBag = DatatypeTool.EntityNameTool.setFamilyName(docNameBag,"Night");
        docNameBag = DatatypeTool.EntityNameTool.setSuffixName(docNameBag,"FACP");
        assignedPerson.setName(docNameBag);

        assignedAuthor.setPlayer(assignedPerson);
        clinicalDocument.addParticipation(author);

        // custodian

        Participation custodian = (Participation) RimObjectFactory
                .getInstance().createRimObject("Participation");
        custodian.setCloneCode(CSimpl.valueOf("custodian", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("CST", "1.22.333.4444");
        custodian.setTypeCode(typeCode);

        // Now add the Role

        Role assignedCustodian = (org.hl7.rim.Role) RimObjectFactory
                .getInstance().createRimObject("Role");
        assignedCustodian.setCloneCode(CSimpl.valueOf("assignedCustodian",
                "1.22.333.4444"));
        assignedCustodian.setClassCode(CSimpl.valueOf("ASSIGNED",
                "1.22.333.4444"));
        custodian.setRole(assignedCustodian);

        Organization representedCustodianOrganization = (Organization) RimObjectFactory
                .getInstance().createRimObject("Organization");
        representedCustodianOrganization.setCloneCode(CSimpl.valueOf(
                "representedCustodianOrganization", "1.22.333.4444"));
        representedCustodianOrganization.setClassCode(CSimpl.valueOf("ORG",
                "1.22.333.4444"));
        representedCustodianOrganization.setDeterminerCode(CSimpl.valueOf(
                "INSTANCE", "1.22.333.4444"));
        Set<II> idSet = new HashSet<II>();
        idSet.add(IIimpl.valueOf("1234", "", ""));
        SET<II> idOrg = SETjuSetAdapter.valueOf(idSet);
        representedCustodianOrganization.setId(idOrg);

        // set the name here
        java.util.List<ENXP> orgNameParts = new ArrayList<ENXP>();
        orgNameParts.add(ENXPimpl.valueOf("Good Health Clinic", null));
        EN orgName = ENimpl.valueOf(orgNameParts);
        List<EN> orgNameList = new ArrayList<EN>();
        orgNameList.add(orgName);
        BAG<EN> orgNameBag = BAGjuCollectionAdapter.valueOf(orgNameList);
        representedCustodianOrganization.setName(orgNameBag);
        // join the pieces together
        assignedCustodian.setScoper(representedCustodianOrganization);
        clinicalDocument.addParticipation(custodian);

        // 1st component
        // Now we add some (in this case one) ActRelationships
        // *****************************************************************************

        ActRelationship component = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        component.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("COMP", "1.22.333.4444");
        component.setTypeCode(typeCode);

        Act structuredBody = (Act) RimObjectFactory.getInstance()
                .createRimObject("Act");
        structuredBody.setCloneCode(CSimpl.valueOf("structuredBody",
                "1.22.333.4444"));
        component.setTarget(structuredBody);

        ActRelationship subcomponent = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        subcomponent.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("COMP", "1.22.333.4444");
        subcomponent.setTypeCode(typeCode);

        Act section = (Act) RimObjectFactory.getInstance()
                .createRimObject("Act");
        section.setCloneCode(CSimpl.valueOf("section", "1.22.333.4444"));

        Participation subject = (Participation) RimObjectFactory
                .getInstance().createRimObject("Participation");


        subject.setCloneCode(CSimpl.valueOf("subject", "1.22.333.4444"));

        Role relatedSubject = (Role) RimObjectFactory.getInstance()
                .createRimObject("Role");
        relatedSubject.setCloneCode(CSimpl.valueOf("relatedSubject",
                "1.22.333.4444"));
        subject.setRole(relatedSubject);

        section.addParticipation(subject);

        ActRelationship entry = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        entry.setCloneCode(CSimpl.valueOf("entry", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("x_ActRelationshipEntry", "1.22.333.4444");
        entry.setTypeCode(typeCode);

        Observation observation = (Observation) RimObjectFactory
                .getInstance().createRimObject("Observation");
        observation.setCloneCode(CSimpl.valueOf("observation", "1.22.333.4444"));
        observation.setCode(CDimpl.valueOf("44100-6", "2.16.840.1.113883.6.1"));
        observation.setMoodCode(CSimpl.valueOf("EVN", "1.22.333.4444"));
        ST title = STjlStringAdapter.valueOf("History");

        ST text = STjlStringAdapter
                .valueOf("\n Pt is a 46 yo male with a hx of CREST syndrome first diagnosed 4 years ago at Stanford."
                        + "\n He is treated with Cupramine and Prilosec.  Family Hx is neg for CTD."
                        + "\n His condition is most remarkable for dysphagia Raynauds and telangiectasias on face.\n ");
        observation.setTitle(title); // this didn't show up
        observation.setText(text);

        entry.setTarget(observation);

        subcomponent.setTarget(section);

        // Lets add another entry here

        ActRelationship entry2 = (ActRelationship) RimObjectFactory
                .getInstance().createRimObject("ActRelationship");
        entry2.setCloneCode(CSimpl.valueOf("entry", "1.22.333.4444"));
        // type code set above
        entry2.setTypeCode(typeCode);

        Observation observation2 = (Observation) RimObjectFactory
                .getInstance().createRimObject("Observation");
        observation2.setCloneCode(CSimpl.valueOf("observation", "1.22.333.4444"));
        observation2.setCode(CDimpl.valueOf("44100-6", "2.16.840.1.113883.6.1"));
        observation2.setMoodCode(CSimpl.valueOf("EVN", "1.22.333.4444"));
        ST text2 = STjlStringAdapter
                .valueOf("\nTelangiectasias on face, palms and in mouth."
                        + "\nNail fold capillaries dilated and redundant."
                        + "\nSkin and joints of upper extremeties unremarkable.\n");
        observation2.setText(text2);

        entry2.setTarget(observation2);

        subcomponent.setTarget(section);

        section.addOutboundRelationship(entry);
        // here we add the second entr
        section.addOutboundRelationship(entry2);

        structuredBody.addOutboundRelationship(subcomponent);

        clinicalDocument.addOutboundRelationship(component);

        System.out.println("result " + clinicalDocument.toString());
        return clinicalDocument;
    }

    public void buildMessage(RimObject rim) {
        try {
            final RimGraphXMLSpeaker speaker = new RimGraphXMLSpeaker();
            final MessageTypeLoaderAdapter jmtl = MessageTypeLoaderAdapter
                    .getInstance();
            // MIF for CDA documents:
            // final MessageType messageType = jmtl.loadMessageType("POCD_HD000040");
            // Unknown domain...:
            final MessageType messageType = jmtl.loadMessageType("POCD_HD000040");
            final FileOutputStream fos = new FileOutputStream("makeCDA.xml");

            final Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            final Source source = new SAXSource(speaker,
                    new RimGraphXMLSpeaker.InputSource(rim,
                            messageType.getRootClass()));
            transformer.transform(source, new StreamResult(System.out));
            transformer.transform(source, new StreamResult(fos));
        } catch (final Exception x) {
            x.printStackTrace();
            System.out.println("Error: " + x.getMessage());
        }
    }

    public SET<II> makeId(final String id, final String root) {
        /**
         * Give it a unique ID could be used for fast lookup from object
         * database given a separate table that matches this id with something
         * else you want to find normally deep down in the object graph like
         * patient MRN
         */
        final II iid = IIimpl.valueOf(id, root);
        final List<II> idarr = new ArrayList<II>();
        idarr.add(iid);

        return SETjuSetAdapter.valueOf(idarr);
    }
}


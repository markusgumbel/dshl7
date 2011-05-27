/*
 * MakeCDA.java
 *
 * Created on November 5, 2006, 12:14 PM
 * Author Peter Hendler
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.hl7.demo;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.hl7.meta.MessageType;
import org.hl7.meta.mif.MessageTypeLoaderAdapter;
import org.hl7.rim.Act;
import org.hl7.rim.Observation;
import org.hl7.rim.Organization;
import org.hl7.rim.Participation;
import org.hl7.rim.Person;
import org.hl7.rim.RimObject;
import org.hl7.rim.RimObjectFactory;
import org.hl7.rim.Role;
import org.hl7.rim.impl.RoleImpl;
import org.hl7.types.BAG;
import org.hl7.types.CD;
import org.hl7.types.CS;
import org.hl7.types.DatatypeTool;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.II;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.TS;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.BAGjuListAdapter;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.CDimpl;
import org.hl7.types.impl.CEimpl;
import org.hl7.types.impl.CSimpl;
import org.hl7.types.impl.CVimpl;
import org.hl7.types.impl.ENXPimpl;
import org.hl7.types.impl.ENimpl;
import org.hl7.types.impl.IIimpl;
import org.hl7.types.impl.SETjuSetAdapter;
import org.hl7.types.impl.STjlStringAdapter;
import org.hl7.types.impl.TSjuDateAdapter;
import org.hl7.types.impl.ValueFactoryImpl;
import org.hl7.xml.builder.RimGraphXMLSpeaker;

/**
 * Make a CDA message by first creating a RIM graph manually. Later do this from
 * a GUI input form
 *
 * @author phend
 * @author nradov
 */

// ToDo Break out a separate method to name Entities that preserves name parts
// ToDo Find the right CS codes and roots for these examples.
public class MakeCDA {
    private Object rim = null;

    private CS typeCode = null;

    private CD code = null;

    private final ValueFactory valueFactory = new ValueFactoryImpl();

    public static void main(String[] args) {
        MakeCDA mcda = new MakeCDA();
        mcda.rim = mcda.makeCDA();
        // convert it to message
        mcda.buildMessage(mcda.rim);
        // now print it out
    }

    public Object getRim() {
        return rim;
    }

    public Object makeCDA() { // maybe later feed this input
        Object result = null; // just make it compile
        // ****************************************************************************
        // first we make the empty root document and set some attributes

        org.hl7.rim.Document clinicalDocument = null;
        try {
            clinicalDocument = (org.hl7.rim.Document) RimObjectFactory
                    .getInstance().createRimObject("Document");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        clinicalDocument.setCloneCode(CSimpl.valueOf("clinicalDocument",
                "clinicalDocument"));

        final SET<II> idset = makeId("1234567", "123.123.123");
        clinicalDocument.setId(idset);

        // Supported format is: "yyyyMMddHHmmss.SSS"
        // But it wont work until you pack the TS into a SET<TS>
        // That you do by using ValueFactory to make an IVL<TS> which is a type
        // of SET<TS>
        final TS effectiveTime = TSjuDateAdapter.valueOf("20061105212900.000");
        final SET<TS> effectiveTimeSet = valueFactory.IVLvalueOf(BLimpl.TRUE,
                effectiveTime, effectiveTime, BLimpl.TRUE);

        clinicalDocument.setEffectiveTime(effectiveTimeSet);

        code = CSimpl.valueOf("11488-4", "2.16.840.1.113883.6.1");
        clinicalDocument.setCode(code);

        // Now we add some Participations
        // ***************************************************************************

        org.hl7.rim.Participation recordTarget = null;
        try {
            recordTarget = (org.hl7.rim.Participation) RimObjectFactory
                    .getInstance().createRimObject("Participation");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        recordTarget.setCloneCode(CSimpl.valueOf("recordTarget",
                "1.22.333.4444"));
        typeCode = CSimpl.valueOf("RCT", "getCodeSystem");
        recordTarget.setTypeCode(typeCode);
        // now add the patientRole to the participation

        Role patientRole = null;
        try {
            patientRole = (org.hl7.rim.Patient) RimObjectFactory.getInstance()
                    .createRimObject("Patient");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }

        patientRole
                .setCloneCode(CSimpl.valueOf("patientRole", "1.22.333.4444"));
        patientRole.setClassCode(CSimpl.valueOf("PAT", "1.22.333.4444"));
        // Add a person entity to the patient role (not using CMET here)

        Person patient = null;
        try {
            patient = (org.hl7.rim.Person) RimObjectFactory.getInstance()
                    .createRimObject("Person");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        patient.setCloneCode(CSimpl.valueOf("patient", "1.22.333.4444"));
        // TODO Remove
        patient.setClassCode(CSimpl.valueOf("NOPSN", "1.22.333.4444"));

        final SET<II> mrnset = makeId("123433", "123.234.345");
        patient.setId(mrnset);

        BAG<EN> name = null;
        name = DatatypeTool.EntityNameTool.setPrefixName(name, "Mr");
        name = DatatypeTool.EntityNameTool.setGivenName(name, "Robert");
        name = DatatypeTool.EntityNameTool.setFamilyName(name, "Zimmerman");
        name = DatatypeTool.EntityNameTool.setSuffixName(name, "VII");
        patient.setName(name);

        patient.setAdministrativeGenderCode(CEimpl.valueOf(CVimpl.valueOf("M",
                "10173"), null));

        patient.setBirthTime(TSjuDateAdapter.valueOf((new GregorianCalendar(
                1987, Calendar.JUNE, 5)).getTime(), 8));
        patient.setEducationLevelCode(CEimpl.valueOf(CVimpl.valueOf("GD",
                "19183"), null));

        patientRole.setPlayer(patient);

        recordTarget.setRole(patientRole);
        clinicalDocument.addParticipation(recordTarget);

        // author

        org.hl7.rim.Participation author = null;
        try {
            author = (org.hl7.rim.Participation) RimObjectFactory.getInstance()
                    .createRimObject("Participation");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        author.setCloneCode(CSimpl.valueOf("author", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("AUT", "1.22.333.4444");
        author.setTypeCode(typeCode);
        // Now add the Role

        Role assignedAuthor = new RoleImpl();
        try {
            assignedAuthor = (org.hl7.rim.Role) RimObjectFactory.getInstance()
                    .createRimObject("Role");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        assignedAuthor.setCloneCode(CSimpl.valueOf("assignedAuthor",
                "1.22.333.4444"));
        assignedAuthor
                .setClassCode(CSimpl.valueOf("ASSIGNED", "1.22.333.4444"));

        author.setRole(assignedAuthor);

        Person assignedPerson = null;
        try {
            assignedPerson = (org.hl7.rim.Person) RimObjectFactory
                    .getInstance().createRimObject("Person");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        assignedPerson.setCloneCode(CSimpl.valueOf("assignedPerson",
                "1.22.333.4444"));
        assignedPerson.setClassCode(CSimpl.valueOf("PSN", "1.22.333.4444"));

        // set the name here

        BAG<EN> docNameBag = null;
        docNameBag = DatatypeTool.EntityNameTool
                .setPrefixName(docNameBag, "Dr");
        docNameBag = DatatypeTool.EntityNameTool.setGivenName(docNameBag,
                "John");
        docNameBag = DatatypeTool.EntityNameTool.setFamilyName(docNameBag,
                "Night");
        docNameBag = DatatypeTool.EntityNameTool.setSuffixName(docNameBag,
                "FACP");
        assignedPerson.setName(docNameBag);

        assignedAuthor.setPlayer(assignedPerson);
        clinicalDocument.addParticipation(author);

        // custodian

        org.hl7.rim.Participation custodian = null;
        try {
            custodian = (org.hl7.rim.Participation) RimObjectFactory
                    .getInstance().createRimObject("Participation");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        custodian.setCloneCode(CSimpl.valueOf("custodian", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("CST", "1.22.333.4444");
        custodian.setTypeCode(typeCode);
        // Now add the Role

        Role assignedCustodian = null;
        try {
            assignedCustodian = (org.hl7.rim.Role) RimObjectFactory
                    .getInstance().createRimObject("Role");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        assignedCustodian.setCloneCode(CSimpl.valueOf("assignedCustodian",
                "1.22.333.4444"));
        assignedCustodian.setClassCode(CSimpl.valueOf("ASSIGNED",
                "1.22.333.4444"));
        custodian.setRole(assignedCustodian);

        Organization representedCustodianOrganization = null;
        try {
            representedCustodianOrganization = (org.hl7.rim.Organization) RimObjectFactory
                    .getInstance().createRimObject("Organization");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
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
        BAG<EN> orgNameBag = BAGjuListAdapter.valueOf(orgNameList);
        representedCustodianOrganization.setName(orgNameBag);
        // join the pieces together
        assignedCustodian.setScoper(representedCustodianOrganization);
        clinicalDocument.addParticipation(custodian);

        // 1st component
        // Now we add some (in this case one) ActRelationships
        // *****************************************************************************

        org.hl7.rim.ActRelationship component = null;
        try {
            component = (org.hl7.rim.ActRelationship) RimObjectFactory
                    .getInstance().createRimObject("ActRelationship");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        component.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("COMP", "1.22.333.4444");
        component.setTypeCode(typeCode);

        Act structuredBody = null;
        try {
            structuredBody = (org.hl7.rim.Act) RimObjectFactory.getInstance()
                    .createRimObject("Act");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        structuredBody.setCloneCode(CSimpl.valueOf("structuredBody",
                "1.22.333.4444"));
        component.setTarget(structuredBody);

        org.hl7.rim.ActRelationship subcomponent = null;
        try {
            subcomponent = (org.hl7.rim.ActRelationship) RimObjectFactory
                    .getInstance().createRimObject("ActRelationship");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        subcomponent.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("COMP", "1.22.333.4444");
        subcomponent.setTypeCode(typeCode);

        Act section = null;
        try {
            section = (org.hl7.rim.Act) RimObjectFactory.getInstance()
                    .createRimObject("Act");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        section.setCloneCode(CSimpl.valueOf("section", "1.22.333.4444"));

        Participation subject = null;
        try {
            subject = (org.hl7.rim.Participation) RimObjectFactory
                    .getInstance().createRimObject("Participation");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        subject.setCloneCode(CSimpl.valueOf("subject", "1.22.333.4444"));

        Role relatedSubject = null;
        try {
            relatedSubject = (org.hl7.rim.Role) RimObjectFactory.getInstance()
                    .createRimObject("Role");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        relatedSubject.setCloneCode(CSimpl.valueOf("relatedSubject",
                "1.22.333.4444"));
        subject.setRole(relatedSubject);

        section.addParticipation(subject);

        org.hl7.rim.ActRelationship entry = null;
        try {
            entry = (org.hl7.rim.ActRelationship) RimObjectFactory
                    .getInstance().createRimObject("ActRelationship");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        entry.setCloneCode(CSimpl.valueOf("entry", "1.22.333.4444"));
        typeCode = CSimpl.valueOf("x_ActRelationshipEntry", "1.22.333.4444");
        entry.setTypeCode(typeCode);

        Observation observation = null;
        try {
            observation = (org.hl7.rim.Observation) RimObjectFactory
                    .getInstance().createRimObject("Observation");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        observation
                .setCloneCode(CSimpl.valueOf("observation", "1.22.333.4444"));
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

        org.hl7.rim.ActRelationship entry2 = null;
        try {
            entry2 = (org.hl7.rim.ActRelationship) RimObjectFactory
                    .getInstance().createRimObject("ActRelationship");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        entry2.setCloneCode(CSimpl.valueOf("entry", "1.22.333.4444"));
        // type code set above
        entry2.setTypeCode(typeCode);

        Observation observation2 = null;
        try {
            observation2 = (org.hl7.rim.Observation) RimObjectFactory
                    .getInstance().createRimObject("Observation");
        } catch (org.hl7.util.FactoryException e) {
            e.printStackTrace();
        }
        observation2.setCloneCode(CSimpl
                .valueOf("observation", "1.22.333.4444"));
        observation2
                .setCode(CDimpl.valueOf("44100-6", "2.16.840.1.113883.6.1"));
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
        result = clinicalDocument;
        return result;
    }

    public void buildMessage(Object rim) {
        try {
            final RimGraphXMLSpeaker speaker = new RimGraphXMLSpeaker();
            final MessageTypeLoaderAdapter jmtl = MessageTypeLoaderAdapter
                    .getInstance();
            final MessageType messageType = jmtl.loadMessageType("POCD_HD000040");
            final FileOutputStream fos = new FileOutputStream("etc/makeCDA.xml");

            final Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            final Source source = new SAXSource(speaker,
                    new RimGraphXMLSpeaker.InputSource((RimObject) rim,
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

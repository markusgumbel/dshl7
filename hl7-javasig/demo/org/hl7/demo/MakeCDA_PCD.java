/*
 * MakeCDA_PCD.java
 *
 * Created on November 13, 2007, 12:14 PM
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

import org.dom4j.*;
import org.dom4j.io.*;

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
import org.hl7.types.PQ;
import org.hl7.types.CS;
import org.hl7.types.DatatypeTool;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.II;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.TS;
import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.BAGjuListAdapter;
import org.hl7.types.impl.REALdoubleAdapter;
import org.hl7.types.impl.PQimpl;
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
public class MakeCDA_PCD {
    private Object rim = null;

    private CS typeCode = null;

    private CD code = null;

    private final ValueFactory valueFactory = new ValueFactoryImpl();

    public static void main(String[] args) {
	MakeCDA_PCD mcda = new MakeCDA_PCD();
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
	// get some data Strings from a much simpler xml file
	// that could be from the device
	
	String url = "etc/pcd_inputInstance1.xml";
	org.dom4j.Document doc = null;
	org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();
	try{
		doc = reader.read(url);
	}catch(Exception e){System.out.println("Couldn't read input XML file:etc/pcd_inputInstance1.xml");}
	
	String patNamePre = doc.valueOf("glucoseDeviceObservation/patientName/prefix");
	String patNameGiv = doc.valueOf("glucoseDeviceObservation/patientName/given");
	String patNameFam = doc.valueOf("glucoseDeviceObservation/patientName/family");
	String patNameSuf = doc.valueOf("glucoseDeviceObservation/patientName/suffix");
	
	String docNamePre = doc.valueOf("glucoseDeviceObservation/provider/prefix");
	String docNameGiv = doc.valueOf("glucoseDeviceObservation/provider/given");
	String docNameFam = doc.valueOf("glucoseDeviceObservation/provider/family");
	String docNameSuf = doc.valueOf("glucoseDeviceObservation/provider/suffix");
	
	String mrn = doc.valueOf("glucoseDeviceObservation/mrn");
	String time = doc.valueOf("glucoseDeviceObservation/time");
	
	
	String value = doc.valueOf("glucoseDeviceObservation/observation/value");
	String units = doc.valueOf("glucoseDeviceObservation/observation/units");
	
	// Make the empty root document and set some attributes

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
	final TS effectiveTime = TSjuDateAdapter.valueOf(time);
	final SET<TS> effectiveTimeSet = valueFactory.IVLvalueOf(BLimpl.TRUE,
		effectiveTime, effectiveTime, BLimpl.TRUE);

	clinicalDocument.setEffectiveTime(effectiveTimeSet);

	code = CSimpl.valueOf("DOCCLIN", "2.16.840.1.113883.6.1");
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
	patient.setClassCode(CSimpl.valueOf("PSN", "1.22.333.4444"));

	final SET<II> mrnset = makeId(mrn, "123.234.345");
	patient.setId(mrnset);

	BAG<EN> name = null;
	name = DatatypeTool.EntityNameTool.setPrefixName(name, patNamePre);
	name = DatatypeTool.EntityNameTool.setGivenName(name, patNameGiv);
	name = DatatypeTool.EntityNameTool.setFamilyName(name, patNameFam);
	name = DatatypeTool.EntityNameTool.setSuffixName(name, patNameSuf);
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
	typeCode = CSimpl.valueOf("AUT", "2.16.840.1.113883.5");
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
		"2.16.840.1.113883.5"));
	assignedAuthor
		.setClassCode(CSimpl.valueOf("ASSIGNED", "2.16.840.1.113883.5"));

	author.setRole(assignedAuthor);

	Person assignedPerson = null;
	try {
	    assignedPerson = (org.hl7.rim.Person) RimObjectFactory
		    .getInstance().createRimObject("Person");
	} catch (org.hl7.util.FactoryException e) {
	    e.printStackTrace();
	}
	assignedPerson.setCloneCode(CSimpl.valueOf("assignedPerson",
		"2.16.840.1.113883.5"));
	assignedPerson.setClassCode(CSimpl.valueOf("PSN", "2.16.840.1.113883.5"));

	// set the name here

	BAG<EN> docNameBag = null;
	docNameBag = DatatypeTool.EntityNameTool
		.setPrefixName(docNameBag, docNamePre);
	docNameBag = DatatypeTool.EntityNameTool.setGivenName(docNameBag,
		docNameGiv);
	docNameBag = DatatypeTool.EntityNameTool.setFamilyName(docNameBag,
		docNameFam);
	docNameBag = DatatypeTool.EntityNameTool.setSuffixName(docNameBag,
		docNameSuf);
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
	custodian.setCloneCode(CSimpl.valueOf("custodian", "2.16.840.1.113883.5"));
	typeCode = CSimpl.valueOf("CST", "2.16.840.1.113883.5");
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
		"2.16.840.1.113883.5"));
	assignedCustodian.setClassCode(CSimpl.valueOf("ASSIGNED",
		"2.16.840.1.113883.5"));
	custodian.setRole(assignedCustodian);

	Organization representedCustodianOrganization = null;
	try {
	    representedCustodianOrganization = (org.hl7.rim.Organization) RimObjectFactory
		    .getInstance().createRimObject("Organization");
	} catch (org.hl7.util.FactoryException e) {
	    e.printStackTrace();
	}
	representedCustodianOrganization.setCloneCode(CSimpl.valueOf(
		"representedCustodianOrganization", "2.16.840.1.113883.5"));
	representedCustodianOrganization.setClassCode(CSimpl.valueOf("ORG",
		"2.16.840.1.113883.5"));
	representedCustodianOrganization.setDeterminerCode(CSimpl.valueOf(
		"INSTANCE", "2.16.840.1.113883.5"));
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
	component.setCloneCode(CSimpl.valueOf("component", "2.16.840.1.113883.5"));
	typeCode = CSimpl.valueOf("COMP", "2.16.840.1.113883.5");
	component.setTypeCode(typeCode);

	Act structuredBody = null;
	try {
	    structuredBody = (org.hl7.rim.Act) RimObjectFactory.getInstance()
		    .createRimObject("Act");
	} catch (org.hl7.util.FactoryException e) {
	    e.printStackTrace();
	}
	structuredBody.setCloneCode(CSimpl.valueOf("structuredBody",
		"2.16.840.1.113883.5"));
	component.setTarget(structuredBody);

	org.hl7.rim.ActRelationship subcomponent = null;
	try {
	    subcomponent = (org.hl7.rim.ActRelationship) RimObjectFactory
		    .getInstance().createRimObject("ActRelationship");
	} catch (org.hl7.util.FactoryException e) {
	    e.printStackTrace();
	}
	subcomponent.setCloneCode(CSimpl.valueOf("component", "2.16.840.1.113883.5"));
	typeCode = CSimpl.valueOf("COMP", "2.16.840.1.113883.5");
	subcomponent.setTypeCode(typeCode);

	Act section = null;
	try {
	    section = (org.hl7.rim.Act) RimObjectFactory.getInstance()
		    .createRimObject("Act");
	} catch (org.hl7.util.FactoryException e) {
	    e.printStackTrace();
	}
	section.setCloneCode(CSimpl.valueOf("section", "2.16.840.1.113883.5"));

	org.hl7.rim.ActRelationship entry = null;
	try {
	    entry = (org.hl7.rim.ActRelationship) RimObjectFactory
		    .getInstance().createRimObject("ActRelationship");
	} catch (org.hl7.util.FactoryException e) {
	    e.printStackTrace();
	}
	entry.setCloneCode(CSimpl.valueOf("entry", "2.16.840.1.113883.5"));
	typeCode = CSimpl.valueOf("x_ActRelationshipEntry", "2.16.840.1.113883.5");
	entry.setTypeCode(typeCode);

	Observation observation = null;
	try {
	    observation = (org.hl7.rim.Observation) RimObjectFactory
		    .getInstance().createRimObject("Observation");
	} catch (org.hl7.util.FactoryException e) {
	    e.printStackTrace();
	}
	observation
		.setCloneCode(CSimpl.valueOf("observation", "2.16.840.1.113883.5"));
	observation.setCode(CDimpl.valueOf("365811003", "2.16.840.1.113883.6.96", "SNOMED","2007","Glucose level",null,null,null));
	observation.setMoodCode(CSimpl.valueOf("EVN", "2.16.840.1.113883.5"));
	ST title = STjlStringAdapter.valueOf("Serum Glucose");

	
	REAL gluval = REALdoubleAdapter.valueOf(Integer.valueOf( value ).intValue());
	PQ glucose = PQimpl.valueOf(gluval,units);
	observation.setValue(glucose);

	entry.setTarget(observation);

	subcomponent.setTarget(section);


	subcomponent.setTarget(section);

	section.addOutboundRelationship(entry);


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
	    final FileOutputStream fos = new FileOutputStream("etc/makeCDA_PCD.xml");

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

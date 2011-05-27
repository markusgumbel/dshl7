/**
 * 9/01/2007
 * @author  Peter Hendler
 * a demonstration of calling the HL7 JavaSIG API from Groovy
 * In Groovy lib directory you must remove xerces-2.4.0.jar
 * and replace it with xercesImpl.jar from Java SIG lib directory
 * Copy all jar files from Java SIG lib to Groovy lib
 * Generate jsig.far from our ant jar and put it in Groovy lib
 * Remove xml-apis-1.0.b2.jar from Groovy lib
 * Remove xml-apis.jar which came from Java SIG lib
 * Groovy seems to find files if they are in it's src directory
 * which is where I put the mif files and message files
 */
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.sax.SAXSource;

import org.hl7.meta.MessageType;
import org.hl7.meta.mif.MessageTypeLoaderAdapter;
import org.hl7.rim.*;
import org.hl7.rim.impl.*;
import org.hl7.types.*;
import org.hl7.types.impl.*;
import org.hl7.xml.builder.RimGraphXMLSpeaker;

valueFactory = new ValueFactoryImpl();
rim = makeCDA();
buildMessage(rim);

def makeCDA() { 
	result = null; 
// first we make the empty root document and set some attributes	
	clinicalDocument = (org.hl7.rim.Document) RimObjectFactory
		    .getInstance().createRimObject("Document");	
	clinicalDocument.setCloneCode(CSimpl.valueOf("clinicalDocument",
		"clinicalDocument"));
	idset = makeId("1234567", "123.123.123");
// ---------------now adding to clinical Document------------------------
	clinicalDocument.setId(idset);
	final TS effectiveTime = TSjuDateAdapter.valueOf("20061105212900.000");
	final SET<TS> effectiveTimeSet = valueFactory.IVLvalueOf(BLimpl.TRUE,
		effectiveTime, effectiveTime, BLimpl.TRUE);
// ---------------now adding to clinical Document------------------------
	clinicalDocument.setEffectiveTime(effectiveTimeSet);
	code = CSimpl.valueOf("11488-4", "2.16.840.1.113883.6.1");
// ---------------now adding to clinical Document------------------------
	clinicalDocument.setCode(code);
// Now we add some Participations
	org.hl7.rim.Participation recordTarget = null;	
	    recordTarget = (org.hl7.rim.Participation) RimObjectFactory
		    .getInstance().createRimObject("Participation");	
	recordTarget.setCloneCode(CSimpl.valueOf("recordTarget",
		"1.22.333.4444"));
	typeCode = CSimpl.valueOf("RCT", "getCodeSystem");
	recordTarget.setTypeCode(typeCode);
// now add the patientRole to the participation
	patientRole = null;
	patientRole = (org.hl7.rim.Patient) RimObjectFactory.getInstance()
		    .createRimObject("Patient");
	patientRole
		.setCloneCode(CSimpl.valueOf("patientRole", "1.22.333.4444"));
	patientRole.setClassCode(CSimpl.valueOf("PAT", "1.22.333.4444"));
// Add a person entity to the patient role (not using CMET here)
	patient = null;	
	patient = (org.hl7.rim.Person) RimObjectFactory.getInstance()
		    .createRimObject("Person");	
	patient.setCloneCode(CSimpl.valueOf("patient", "1.22.333.4444"));
	patient.setClassCode(CSimpl.valueOf("PSN", "1.22.333.4444"));
	mrnset = makeId("123433", "123.234.345");
	patient.setId(mrnset);
	name = null;
	name = DatatypeTool.EntityNameTool.setPrefixName(name, "Mr");
	name = DatatypeTool.EntityNameTool.setGivenName(name, "Robert");
	name = DatatypeTool.EntityNameTool.setFamilyName(name, "Zimmerman");
	name = DatatypeTool.EntityNameTool.setSuffixName(name, "VII");
	patient.setName(name);	
	patient.setBirthTime(TSjuDateAdapter.valueOf((new GregorianCalendar(
		1987, Calendar.JUNE, 5)).getTime(), 8));
	patientRole.setPlayer(patient);
	recordTarget.setRole(patientRole);
// ---------------now adding to clinical Document------------------------
	clinicalDocument.addParticipation(recordTarget);	
// author
	org.hl7.rim.Participation author = null;	
	author = (org.hl7.rim.Participation) RimObjectFactory.getInstance()
		    .createRimObject("Participation");	
	author.setCloneCode(CSimpl.valueOf("author", "1.22.333.4444"));
	typeCode = CSimpl.valueOf("AUT", "1.22.333.4444");
	author.setTypeCode(typeCode);
// Now add the Role-------------------------------------------------------
	assignedAuthor = new RoleImpl();	
	assignedAuthor = (org.hl7.rim.Role) RimObjectFactory.getInstance()
		    .createRimObject("Role");	
	assignedAuthor.setCloneCode(CSimpl.valueOf("assignedAuthor",
		"1.22.333.4444"));
	assignedAuthor
		.setClassCode(CSimpl.valueOf("ASSIGNED", "1.22.333.4444"));
	author.setRole(assignedAuthor);	
	assignedPerson = null;	
	assignedPerson = (org.hl7.rim.Person) RimObjectFactory
		    .getInstance().createRimObject("Person");	
	assignedPerson.setCloneCode(CSimpl.valueOf("assignedPerson",
		"1.22.333.4444"));
	assignedPerson.setClassCode(CSimpl.valueOf("PSN", "1.22.333.4444"));	
// set the name here---------------------------------------------------------
	docNameBag = null;
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
// ---------------now adding to clinical Document------------------------
	clinicalDocument.addParticipation(author);	
// custodian-------------------------------------------------------------
	custodian = null;	
	custodian = (org.hl7.rim.Participation) RimObjectFactory
		    .getInstance().createRimObject("Participation");
	custodian.setCloneCode(CSimpl.valueOf("custodian", "1.22.333.4444"));
	typeCode = CSimpl.valueOf("CST", "1.22.333.4444");
	custodian.setTypeCode(typeCode);
// Now add the Role----------------------------------------------------------
	Role assignedCustodian = null;
	assignedCustodian = (org.hl7.rim.Role) RimObjectFactory
		    .getInstance().createRimObject("Role");	
	assignedCustodian.setCloneCode(CSimpl.valueOf("assignedCustodian",
		"1.22.333.4444"));
	assignedCustodian.setClassCode(CSimpl.valueOf("ASSIGNED",
		"1.22.333.4444"));
	custodian.setRole(assignedCustodian);	
	representedCustodianOrganization = null;	
	representedCustodianOrganization = (org.hl7.rim.Organization) RimObjectFactory
		    .getInstance().createRimObject("Organization");
	representedCustodianOrganization.setCloneCode(CSimpl.valueOf(
		"representedCustodianOrganization", "1.22.333.4444"));
	representedCustodianOrganization.setClassCode(CSimpl.valueOf("ORG",
		"1.22.333.4444"));
	representedCustodianOrganization.setDeterminerCode(CSimpl.valueOf(
		"INSTANCE", "1.22.333.4444"));
	idSet = new HashSet<II>();
	idSet.add(IIimpl.valueOf("1234", "", ""));
	idOrg = SETjuSetAdapter.valueOf(idSet);
	representedCustodianOrganization.setId(idOrg);
// set the name here----------------------------------------------------
	orgNameParts = new ArrayList<ENXP>();
	orgNameParts.add(ENXPimpl.valueOf("Good Health Clinic", null));
	orgName = ENimpl.valueOf(orgNameParts);
	orgNameList = new ArrayList<EN>();
	orgNameList.add(orgName);
	orgNameBag = BAGjuListAdapter.valueOf(orgNameList);
	representedCustodianOrganization.setName(orgNameBag);
// join the pieces together---------------------------------------------
	assignedCustodian.setScoper(representedCustodianOrganization);
// ---------------now adding to clinical Document------------------------
	clinicalDocument.addParticipation(custodian);
// 1st component
// Now we add some (in this case one) ActRelationships
//component--------------------------------------------------------------
	component = null;	
	component = (org.hl7.rim.ActRelationship) RimObjectFactory
		    .getInstance().createRimObject("ActRelationship");
	component.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));
	typeCode = CSimpl.valueOf("COMP", "1.22.333.4444");
	component.setTypeCode(typeCode);
//structuredBody--------------------------------------------------------
	structuredBody = null;	
	structuredBody = (org.hl7.rim.Act) RimObjectFactory.getInstance()
		    .createRimObject("Act");
	structuredBody.setCloneCode(CSimpl.valueOf("structuredBody",
		"1.22.333.4444"));
	component.setTarget(structuredBody);
//subcomponent----------------------------------------------------------
	subcomponent = null;	
	subcomponent = (org.hl7.rim.ActRelationship) RimObjectFactory
		    .getInstance().createRimObject("ActRelationship");
	subcomponent.setCloneCode(CSimpl.valueOf("component", "1.22.333.4444"));
	typeCode = CSimpl.valueOf("COMP", "1.22.333.4444");
	subcomponent.setTypeCode(typeCode);
//section-----------------------------------------------------------------
	section = null;	
	section = (org.hl7.rim.Act) RimObjectFactory.getInstance()
		    .createRimObject("Act");
	section.setCloneCode(CSimpl.valueOf("section", "1.22.333.4444"));
//subject-----------------------------------------------------------------
	subject = null;	
	subject = (org.hl7.rim.Participation) RimObjectFactory
		    .getInstance().createRimObject("Participation");
	subject.setCloneCode(CSimpl.valueOf("subject", "1.22.333.4444"));
//relatedSubject----------------------------------------------------------
	relatedSubject = null;
	relatedSubject = (org.hl7.rim.Role) RimObjectFactory.getInstance()
		    .createRimObject("Role");
	relatedSubject.setCloneCode(CSimpl.valueOf("relatedSubject",
		"1.22.333.4444"));
	subject.setRole(relatedSubject);
	section.addParticipation(subject);
//entry----------------------------------------------------------------------	
	entry = (org.hl7.rim.ActRelationship) RimObjectFactory
		    .getInstance().createRimObject("ActRelationship");
	entry.setCloneCode(CSimpl.valueOf("entry", "1.22.333.4444"));
	typeCode = CSimpl.valueOf("x_ActRelationshipEntry", "1.22.333.4444");
	entry.setTypeCode(typeCode);
//observation---------------------------------------------------------------
	observation = null;	
	observation = (org.hl7.rim.Observation) RimObjectFactory
		    .getInstance().createRimObject("Observation");
	observation
		.setCloneCode(CSimpl.valueOf("observation", "1.22.333.4444"));
	observation.setCode(CDimpl.valueOf("44100-6", "2.16.840.1.113883.6.1"));
	observation.setMoodCode(CSimpl.valueOf("EVN", "1.22.333.4444"));
	title = STjlStringAdapter.valueOf("History");
//text added-----------------------------------------------------------
	text = STjlStringAdapter
		.valueOf("\n Pt is a 46 yo male with a hx of CREST syndrome first diagnosed 4 years ago at Stanford."
			+ "\n He is treated with Cupramine and Prilosec.  Family Hx is neg for CTD."
			+ "\n His condition is most remarkable for dysphagia Raynauds and telangiectasias on face.\n ");
	observation.setTitle(title); // this didn't show up
	observation.setText(text);
	entry.setTarget(observation);
	subcomponent.setTarget(section);
// Lets add another entry here-------------------------------------------------
	entry2 = null;	
	entry2 = (org.hl7.rim.ActRelationship) RimObjectFactory
		    .getInstance().createRimObject("ActRelationship");
	entry2.setCloneCode(CSimpl.valueOf("entry", "1.22.333.4444"));
// type code set above
	entry2.setTypeCode(typeCode);
//observation--------------------------------------------------------------------
	observation2 = null;	
	observation2 = (org.hl7.rim.Observation) RimObjectFactory
		    .getInstance().createRimObject("Observation");
	observation2.setCloneCode(CSimpl
		.valueOf("observation", "1.22.333.4444"));
	observation2
		.setCode(CDimpl.valueOf("44100-6", "2.16.840.1.113883.6.1"));
	observation2.setMoodCode(CSimpl.valueOf("EVN", "1.22.333.4444"));
//add text-----------------------------------------------------------------------
	text2 = STjlStringAdapter
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
// ---------------now adding to clinical Document----------------------------------
	clinicalDocument.addOutboundRelationship(component);
	return clinicalDocument;
	}

def buildMessage(Object rim) {
// create the XML from the RIM graph in memory------------------------------------
	    speaker = new RimGraphXMLSpeaker();
	    jmtl = MessageTypeLoaderAdapter.getInstance();
	    messageType = jmtl.loadMessageType("POCD_HD000040");
	    fos = new FileOutputStream("makeCDA.xml");
	    transformer = TransformerFactory.newInstance().newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    source = new SAXSource(speaker, new RimGraphXMLSpeaker.InputSource((RimObject) rim, messageType.getRootClass()));
	    println rim.getClass();
		transformer.transform(source, new StreamResult(System.out));
	    transformer.transform(source, new StreamResult(fos));	
    }

def makeId(final String id, final String root) {
	/**
	 * Give it a unique ID could be used for fast lookup from object
	 * database given a separate table that matches this id with something
	 * else you want to find normally deep down in the object graph like
	 * patient MRN
	  */
	iid = IIimpl.valueOf(id, root);
	idarr = new ArrayList<II>();
	idarr.add(iid);
	return SETjuSetAdapter.valueOf(idarr);
	}
	
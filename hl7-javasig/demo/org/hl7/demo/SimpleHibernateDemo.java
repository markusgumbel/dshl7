/**
 * The simplest possible example of persisting
 * and retrieving a RIM graph
 * @author Peter Hendler 9/22/2007
 */
package org.hl7.demo;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hl7.hibernate.Persistence;
import org.hl7.rim.impl.RimObjectImpl;
import org.hibernate.Query;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hl7.rim.*;


public class SimpleHibernateDemo {
	String id = null; //needed to retrieve graph from Hibernate
	Class clazz = null;
	SimpleHibernateDemo(){}
	
	public void HibernateIt(Object graph){
		Persistence.instance().save(graph);
		clazz = graph.getClass();
		id = ((RimObjectImpl) graph).getInternalId();

		Persistence.instance().commit();
		Persistence.instance().close();
	}
	public Object getRimGraphBack(String _id, Class _clazz){
		Object graph = null;
		graph = Persistence.instance().load(_clazz, _id);
		return graph;
	}
	
	public String getId(){
		return this.id;
	}
	public Class getClazz(){
		return this.clazz;
	}
	
	private void hibernateSetup() {
		try {
			new SchemaExport(Persistence.instance().instance().getConfiguration()).create(true, true);
		}
		catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	public Query getHQLQuery(String hql){
		return Persistence.instance().createHQLQuery(hql);
		
	}
	
	public static void main(String[] args) {
		Object rim = null;
		SimpleLoadMessage slm = new SimpleLoadMessage();
		SimpleHibernateDemo shd = new SimpleHibernateDemo();
		shd.hibernateSetup();
		
		// make a list of CDA documents to load
		ArrayList<String> docs = new ArrayList<String>();
		docs.add("etc/cda1.xml");
		docs.add("etc/cda2.xml");
		docs.add("etc/cda3.xml");
		docs.add("etc/cda4.xml");
		docs.add("etc/cda5.xml");
		docs.add("etc/cda6.xml");
		docs.add("etc/cda7.xml");
		docs.add("etc/cda8.xml");
		docs.add("etc/cda9.xml");
		docs.add("etc/cda10.xml");
		
/*note: the following SQL statement will then
work when testing Hibernate data manager
This one assumes the MRN is in the subject of record of
the whole document.
SELECT  observation.value_number, observation.value_unit, observation.code_displayname 
FROM ACT doc
INNER JOIN ACTRELATIONSHIP actrel ON (doc.internalid = actrel.sourceinternalid)
INNER JOIN ACT body ON(actrel.targetinternalid = body.internalid)
INNER JOIN ACTRELATIONSHIP component ON(body.internalid = component.sourceinternalid)
INNER JOIN ACT section ON(component.targetinternalid = section.internalid)
INNER JOIN ACTRELATIONSHIP entry ON (entry.sourceinternalid = section.internalid)
INNER JOIN ACT observation ON (entry.targetinternalid = observation.internalid)

INNER JOIN Participation subject ON(doc.internalId = subject.actInternalId)
INNER JOIN Role_id mrn ON(mrn.roleInternalId = subject.roleInternalId)
WHERE subject.typeCode IN ('SBJ', 'RCT')
AND mrn.root ='2.16.840.1.113883.19.5'
AND mrn.extension =  '12345'

------------------------------------------------------
if mrn is in the observation then this works

SELECT obs.Value_Number,  obs.Effectivetime_low, obs.* 
FROM ACT obs
INNER JOIN PARTICIPATION participant ON(obs.internalId = participant.actInternalId)
INNER JOIN Role_id mrn ON(mrn.roleInternalId = participant.roleInternalId)
WHERE  obs.Code_displayname = 'Glucose level'
AND mrn.root ='2.16.840.1.113883.19.5'
AND mrn.extension =  '12345'

or with some math if you needed to correct for units etc

SELECT (CAST(obs.Value_Number AS INTEGER)/10) AS number, 'mg/dl' AS units,  obs.Effectivetime_low AS time, obs.* 
FROM ACT obs
INNER JOIN PARTICIPATION participant ON(obs.internalId = participant.actInternalId)
INNER JOIN Role_id mrn ON(mrn.roleInternalId = participant.roleInternalId)
WHERE  obs.Code_displayname = 'Glucose level'
AND mrn.root ='2.16.840.1.113883.19.5'
AND mrn.extension =  '12345'

Fixing substring of time

SELECT obs.Value_Number AS number, 'mg/dl' AS units,  SUBSTR(obs.Effectivetime_low,5,4) AS time, '12345' AS MRN 
FROM ACT obs
INNER JOIN PARTICIPATION participant ON(obs.internalId = participant.actInternalId)
INNER JOIN Role_id mrn ON(mrn.roleInternalId = participant.roleInternalId)
WHERE  obs.Code_displayname = 'Glucose level'
AND mrn.root ='2.16.840.1.113883.19.5'
AND mrn.extension =  '12345'


*/
		
		// start of block to load messages
		Iterator<String> docit = docs.iterator();
		while(docit.hasNext()){
		rim = slm.LoadMessage("POCD_HD000040", docit.next());
		System.out.println("Message loaded " + rim.toString());
		System.out.println("Now to Hibernate it");	
		shd.HibernateIt(rim);
		Act act = (Act)rim;
		System.out.println("moodCode " + act.getMoodCode());
		System.out.println("CloneCode " + act.getCloneCode());
		System.out.println("And now get it back again");
		Object backagain = shd.getRimGraphBack(shd.getId(), shd.getClazz());
			if(backagain!=null){
				System.out.println("Got our graph back again " + backagain);
			}// if
		}//while 
		// end of block to load messages into Hibernate
		
		
		
		//Now to demo a hibernate query
		//Change this HibQL String parameter to play with Hibernate Queries
		//"from org.hl7.rim.impl.DocumentImpl"  works
		//"from org.hl7.rim.impl.DocumentImpl as di where di.moodCode like 'EVN'" works
		//"from org.hl7.rim.impl.DocumentImpl as di where di.cloneCode like 'ClinicalDocument'" works
		List result = shd.getHQLQuery("from org.hl7.rim.impl.DocumentImpl as di where di.cloneCode like 'ClinicalDocument'").list();
		if(result!=null){
			System.out.println("Hibernate Query also worked");
			System.out.println(result.iterator().next().toString());
		}
		
	}

}

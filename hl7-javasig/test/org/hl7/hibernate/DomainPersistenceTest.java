package org.hl7.hibernate;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hl7.UUDD_MT333333UV.Citizen;
import org.hl7.UUDD_MT333333UV.Patient;
import org.hl7.UUDD_MT333333UV.Person;
import org.hl7.UUDD_MT333333UV.impl.PersonImpl;

public class DomainPersistenceTest extends TestCase {
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Persistence.getConfiguration().addResource("org/hl7/UUDD_MT333333UV/package.hbm.xml");
        Persistence.getConfiguration().addResource("org/hl7/UUDD_MT333334UV/package.hbm.xml");
    }
    
    public void testDomainPersistence() throws Exception {
        Person person1 = new DomainObjectFactory().createPerson();
        
        Session session = Persistence.getSessionFactory().openSession();

        session.getTransaction().begin();
        session.save(person1);
        session.getTransaction().commit();
        session.close();

        String internalId1 = person1.getInternalId();
        session = Persistence.getSessionFactory().openSession();

        // Read back out and compare
        session.beginTransaction();
        
        Person person2 = (Person)session.load(PersonImpl.class, internalId1);
        assertEquals(person1.getInternalId(), person2.getInternalId());
        assertEquals(person1.getId(), person2.getId());
        assertEquals(person1.getClassCode(), person2.getClassCode());
        assertEquals(person1.getName(), person2.getName()); 
        assertEquals(person1.getClassCode(), person2.getClassCode());
        
        List<Citizen> p1citizens = person1.getAsCitizen();
        List<Citizen> p2citizens = person2.getAsCitizen();
        assertEquals(p1citizens.size(), p2citizens.size());
        
        Patient p1patient = person1.getAsPatient();
        Patient p2patient = person2.getAsPatient();
        assertEquals(p1patient.getInternalId(), p2patient.getInternalId());
        
        session.close();
        Persistence.instance().close();
    }
}

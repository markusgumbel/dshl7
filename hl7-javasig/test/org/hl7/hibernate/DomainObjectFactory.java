package org.hl7.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hl7.UUDD_MT333333UV.Citizen;
import org.hl7.UUDD_MT333333UV.Country;
import org.hl7.UUDD_MT333333UV.Patient;
import org.hl7.UUDD_MT333333UV.Person;
import org.hl7.UUDD_MT333333UV.impl.CitizenImpl;
import org.hl7.UUDD_MT333333UV.impl.CountryImpl;
import org.hl7.UUDD_MT333333UV.impl.PatientImpl;
import org.hl7.UUDD_MT333333UV.impl.PersonImpl;
import org.hl7.UUDD_MT333334UV.City;
import org.hl7.UUDD_MT333334UV.impl.CityImpl;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.II;
import org.hl7.types.enums.EntityClass;
import org.hl7.types.enums.RoleClass;
import org.hl7.types.impl.BAGjuListAdapter;
import org.hl7.types.impl.ENXPimpl;
import org.hl7.types.impl.ENimpl;
import org.hl7.types.impl.IIimpl;
import org.hl7.types.impl.SETjuSetAdapter;

class DomainObjectFactory {
    Person createPerson() {
        Person person = new PersonImpl();
        person.setClassCode(EntityClass.Person);

        List<ENXP> nameParts = new ArrayList<ENXP>();
        nameParts.add(ENXPimpl.valueOf("Mark"));
        person.setName(ENimpl.valueOf(nameParts));

        // Add IDs (SET<II>)
        person.setId(IIimpl.valueOf("local", "1.2.3"));
        
        // Set Citizens
        person.setAsCitizen(createCitizens());
        
        // Set Patient
        person.setAsPatient(createPatient());

        return person;
    }

    Patient createPatient() {
        Patient asPatient = new PatientImpl();
        asPatient.setClassCode(RoleClass.Patient);
        return asPatient;
    }
    
    List<Citizen> createCitizens() {
        List<Citizen> citizens = new ArrayList<Citizen>(); 
        Citizen citizen = new CitizenImpl();
        citizen.setClassCode(RoleClass.Citizen);
        citizen.setPoliticalLocationChoice(createCountry(new String[] {"Bhutan", "Drukpa"}));
        citizens.add(citizen);
        
        citizen = new CitizenImpl();
        citizen.setClassCode(RoleClass.Citizen);
        citizen.setPoliticalLocationChoice(createCity());
        citizens.add(citizen);
        
        return citizens;
    }
    
    City createCity() {
        City city = new CityImpl();
        city.setName(createName("Paro"));
        return city;
    }
    
    Country createCountry(String[] strNames) {
        Country country = new CountryImpl();
        country.setName(createNames(strNames));
        
        return country;
    }

    private BAG<EN> createName(String name) { return createNames(new String[] {name}); }
    private BAG<EN> createNames(String[] strNames) {
        List<EN> names = new ArrayList<EN>();
        for(String name : strNames) {
            List<ENXP> nameParts = new ArrayList<ENXP>();
            nameParts.add(ENXPimpl.valueOf(name));
            names.add(ENimpl.valueOf(nameParts));
        }
        return new BAGjuListAdapter<EN>(names);
    }
}

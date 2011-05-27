/*
THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

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
package org.hl7.UUDD_MT333333UV.impl;

import org.hibernate.Criteria;
import org.hl7.hibernate.Persistence;

import org.hl7.UUDD_MT333333UV.Person;
import org.hl7.rim.impl.AssociationSetImpl;
import org.hl7.rim.impl.BasicEntityImpl;
import org.hl7.types.CS;
import org.hl7.types.II;
import org.hl7.types.EN;
import org.hl7.types.CV;

import org.hl7.UUDD_MT333333UV.Citizen;
import org.hl7.UUDD_MT333333UV.Patient;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.UUDD_MT333333UV.Person as a simple data holder bean.
    @see org.hl7.UUDD_MT333333UV.Person
  */
public class PersonImpl extends org.hl7.rim.impl.BasicEntityImpl implements Person {

  private org.hl7.types.SET<org.hl7.types.II> _id;
  /** Gets the property id.
      @see org.hl7.UUDD_MT333333UV.Person#getId
  */
  public org.hl7.types.II getId()  { 
    if(_id == null || _id.isNull().isTrue() || _id.isEmpty().isTrue()) {
      return org.hl7.types.impl.IInull.NI;
    } else {
      return _id.any();
    }
  }
  /** Sets the property id.
      @see org.hl7.UUDD_MT333333UV.Person#setId
  */
  public void setId(org.hl7.types.II id)  {
    if(id == null || id.isNull().isTrue()) {
      _id = org.hl7.types.impl.SETnull.NI;
    } else {
      java.util.Set<org.hl7.types.II> value = new java.util.LinkedHashSet<org.hl7.types.II>();
      value.add(id);
      _id = org.hl7.types.impl.SETjuSetAdapter.valueOf(value);
    }
  }

  private org.hl7.types.BAG<org.hl7.types.EN> _name;
  /** Gets the property name.
      @see org.hl7.UUDD_MT333333UV.Person#getName
  */
  public org.hl7.types.EN getName()  { 
    if(_name == null || _name.isNull().isTrue() || _name.isEmpty().isTrue()) {
      return org.hl7.types.impl.ENnull.NI;
    } else {
      return _name.iterator().next();
    }
  }
  /** Sets the property name.
      @see org.hl7.UUDD_MT333333UV.Person#setName
  */
  public void setName(org.hl7.types.EN name)  {
    if(name == null || name.isNull().isTrue()) {
      _name = org.hl7.types.impl.BAGnull.NI;
    } else {
      java.util.List<org.hl7.types.EN> value = new java.util.ArrayList<org.hl7.types.EN>();
      value.add(name);
      _name = org.hl7.types.impl.BAGjuListAdapter.valueOf(value);
    }
  }

  private org.hl7.types.CV _riskCode;
  /** Gets the property riskCode.
      @see org.hl7.UUDD_MT333333UV.Person#getRiskCode
  */
  public org.hl7.types.CV getRiskCode()  { 
    return _riskCode;
  }
  /** Sets the property riskCode.
      @see org.hl7.UUDD_MT333333UV.Person#setRiskCode
  */
  public void setRiskCode(org.hl7.types.CV riskCode)  {
    if(riskCode instanceof org.hl7.hibernate.ClonableCollection)
      riskCode = ((org.hl7.hibernate.ClonableCollection<org.hl7.types.CV>) riskCode).cloneHibernateCollectionIfNecessary();
    _riskCode = riskCode;
  }

  private /*AssociationSet*/java.util.List<Citizen> _asCitizen;
  /** Gets the property asCitizen.
      @see org.hl7.UUDD_MT333333UV.Person#getAsCitizen
  */
  public /*AssociationSet*/java.util.List<Citizen> getAsCitizen()  {
    return _asCitizen;
  }
  /** Sets the property asCitizen.
      @see org.hl7.UUDD_MT333333UV.Person#setAsCitizen
  */
  public void setAsCitizen(/*AssociationSet*/java.util.List<Citizen> asCitizen)  {
    _asCitizen = asCitizen;
    if (asCitizen == null) return;
    for (Citizen item : asCitizen)
      item.setCloneCode(org.hl7.types.impl.CSimpl.valueOf("asCitizen", "2.16.840.1.113883.5.6"));
  }
  /** Adds an association asCitizen.
      @see org.hl7.UUDD_MT333333UV.Person#setAsCitizen
  */
  public void addAsCitizen(Citizen asCitizen) {
        // create the association set if it doesn't exist already
    if(_asCitizen == null) _asCitizen = new AssociationSetImpl<Citizen>();
    // add the association to the association set
    getAsCitizen().add(asCitizen);
    if (asCitizen == null) return;
    asCitizen.setCloneCode(org.hl7.types.impl.CSimpl.valueOf("asCitizen", "2.16.840.1.113883.5.6"));
  }

  private /*AssociationSet*/java.util.List<Patient> _asPatient;
  /** Gets the property asPatient.
      @see org.hl7.UUDD_MT333333UV.Person#getAsPatient
  */
  public Patient getAsPatient()  {
    if(_asPatient == null || _asPatient.isEmpty()) return null;
    else return _asPatient.get(0);
  }
  /** Sets the property asPatient.
      @see org.hl7.UUDD_MT333333UV.Person#setAsPatient
  */
  public void setAsPatient(Patient asPatient)  {
    if(asPatient == null) {
      _asPatient = null;
    } else {
      _asPatient = new AssociationSetImpl<Patient>();
      _asPatient.add(asPatient);
    }
    if (asPatient == null) return;
    asPatient.setCloneCode(org.hl7.types.impl.CSimpl.valueOf("asPatient", "2.16.840.1.113883.5.6"));
  }
  public Object clone() throws CloneNotSupportedException {
    PersonImpl that = (PersonImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

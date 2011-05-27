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

import org.hl7.UUDD_MT333333UV.Citizen;
import org.hl7.rim.impl.AssociationSetImpl;
import org.hl7.rim.impl.BasicRoleImpl;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;

import org.hl7.UUDD_MT333333UV.LocationChoice;
import org.hl7.UUDD_MT333333UV.Person;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.UUDD_MT333333UV.Citizen as a simple data holder bean.
    @see org.hl7.UUDD_MT333333UV.Citizen
  */
public class CitizenImpl extends org.hl7.rim.impl.BasicRoleImpl implements Citizen {

  private org.hl7.types.SET<II> _id;
  /** Gets the property id.
      @see org.hl7.UUDD_MT333333UV.Citizen#getId
  */
  public org.hl7.types.SET<II> getId()  { 
    return _id;
  }
  /** Sets the property id.
      @see org.hl7.UUDD_MT333333UV.Citizen#setId
  */
  public void setId(org.hl7.types.SET<II> id)  {
    if(id instanceof org.hl7.hibernate.ClonableCollection)
      id = ((org.hl7.hibernate.ClonableCollection<org.hl7.types.SET<II>>) id).cloneHibernateCollectionIfNecessary();
    _id = id;
  }

  private org.hl7.rim.BasicEntity _politicalLocationChoice;
  /** Gets the property politicalLocationChoice.
      @see org.hl7.UUDD_MT333333UV.Citizen#getPoliticalLocationChoice
  */
  public org.hl7.rim.BasicEntity getPoliticalLocationChoice()  {
    return _politicalLocationChoice;
  }
  /** Sets the property politicalLocationChoice.
      @see org.hl7.UUDD_MT333333UV.Citizen#setPoliticalLocationChoice
  */
  public void setPoliticalLocationChoice(org.hl7.rim.BasicEntity politicalLocationChoice)  {
    _politicalLocationChoice = politicalLocationChoice;
    if (politicalLocationChoice == null) return;
// Find the correct specialization of a choice target and use its traversal name
    if (politicalLocationChoice.getCloneCode() != null) {}
    else if (politicalLocationChoice.getClass().getSimpleName().equals("CountryImpl"))politicalLocationChoice.setCloneCode(org.hl7.types.impl.CSimpl.valueOf("politicalCountry", "2.16.840.1.113883.5.6"));
    else if (politicalLocationChoice.getClass().getSimpleName().equals("E_LocationUniversalImpl"))politicalLocationChoice.setCloneCode(org.hl7.types.impl.CSimpl.valueOf("1", "2.16.840.1.113883.5.6"));
    else java.util.logging.Logger.getLogger(politicalLocationChoice.getClass().getName()).warning("Unable to automatically set cloneClass for " + politicalLocationChoice.getClass().getName() + " as the target of the association org.hl7.UUDD_MT333333UV.Citizen.politicalLocationChoice");
  }
  public Object clone() throws CloneNotSupportedException {
    CitizenImpl that = (CitizenImpl) super.clone();

    // deep clone of persistent component collections
    that.setId(that.getId());
    return that;
  }
}

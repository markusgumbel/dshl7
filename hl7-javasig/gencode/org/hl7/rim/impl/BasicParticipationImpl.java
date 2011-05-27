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
package org.hl7.rim.impl;

import org.hibernate.Criteria;
import org.hl7.hibernate.Persistence;

import org.hl7.rim.BasicParticipation;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.CS;
import org.hl7.types.CD;
import org.hl7.types.INT;
import org.hl7.types.BL;
import org.hl7.types.ED;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.CE;

import org.hl7.rim.Act;
import org.hl7.rim.Role;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Participation as a simple data holder bean.
    @see org.hl7.rim.Participation
  */
public class BasicParticipationImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements BasicParticipation {

  private CS _typeCode;
  /** Gets the property typeCode.
      @see org.hl7.rim.Participation#getTypeCode
  */
  public CS getTypeCode() { return _typeCode; }
  /** Sets the property typeCode.
      @see org.hl7.rim.Participation#setTypeCode
  */
  public void setTypeCode(CS typeCode) {
    if(typeCode instanceof org.hl7.hibernate.ClonableCollection)
      typeCode = ((org.hl7.hibernate.ClonableCollection<CS>) typeCode).cloneHibernateCollectionIfNecessary();
    _typeCode = typeCode;
  }
  /** Sets the property typeCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Participation#setTypeCode
  */
  public void setTypeCodeForHibernate(CS typeCode) {
    _typeCode = typeCode;
  }
  public Object clone() throws CloneNotSupportedException {
    BasicParticipationImpl that = (BasicParticipationImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

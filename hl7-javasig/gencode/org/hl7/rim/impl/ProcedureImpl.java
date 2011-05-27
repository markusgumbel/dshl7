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

import org.hl7.rim.Procedure;
import org.hl7.rim.impl.ActImpl;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CD;
import org.hl7.types.CD;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Procedure as a simple data holder bean.
    @see org.hl7.rim.Procedure
  */
public class ProcedureImpl extends org.hl7.rim.impl.ActImpl implements Procedure {

  private SET<CE> _methodCode;
  /** Gets the property methodCode.
      @see org.hl7.rim.Procedure#getMethodCode
  */
  public SET<CE> getMethodCode() { return _methodCode; }
  /** Sets the property methodCode.
      @see org.hl7.rim.Procedure#setMethodCode
  */
  public void setMethodCode(SET<CE> methodCode) {
    if(methodCode instanceof org.hl7.hibernate.ClonableCollection)
      methodCode = ((org.hl7.hibernate.ClonableCollection<SET<CE>>) methodCode).cloneHibernateCollectionIfNecessary();
    _methodCode = methodCode;
  }
  /** Sets the property methodCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Procedure#setMethodCode
  */
  public void setMethodCodeForHibernate(SET<CE> methodCode) {
    _methodCode = methodCode;
  }

  private SET<CD> _approachSiteCode;
  /** Gets the property approachSiteCode.
      @see org.hl7.rim.Procedure#getApproachSiteCode
  */
  public SET<CD> getApproachSiteCode() { return _approachSiteCode; }
  /** Sets the property approachSiteCode.
      @see org.hl7.rim.Procedure#setApproachSiteCode
  */
  public void setApproachSiteCode(SET<CD> approachSiteCode) {
    if(approachSiteCode instanceof org.hl7.hibernate.ClonableCollection)
      approachSiteCode = ((org.hl7.hibernate.ClonableCollection<SET<CD>>) approachSiteCode).cloneHibernateCollectionIfNecessary();
    _approachSiteCode = approachSiteCode;
  }
  /** Sets the property approachSiteCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Procedure#setApproachSiteCode
  */
  public void setApproachSiteCodeForHibernate(SET<CD> approachSiteCode) {
    _approachSiteCode = approachSiteCode;
  }

  private SET<CD> _targetSiteCode;
  /** Gets the property targetSiteCode.
      @see org.hl7.rim.Procedure#getTargetSiteCode
  */
  public SET<CD> getTargetSiteCode() { return _targetSiteCode; }
  /** Sets the property targetSiteCode.
      @see org.hl7.rim.Procedure#setTargetSiteCode
  */
  public void setTargetSiteCode(SET<CD> targetSiteCode) {
    if(targetSiteCode instanceof org.hl7.hibernate.ClonableCollection)
      targetSiteCode = ((org.hl7.hibernate.ClonableCollection<SET<CD>>) targetSiteCode).cloneHibernateCollectionIfNecessary();
    _targetSiteCode = targetSiteCode;
  }
  /** Sets the property targetSiteCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Procedure#setTargetSiteCode
  */
  public void setTargetSiteCodeForHibernate(SET<CD> targetSiteCode) {
    _targetSiteCode = targetSiteCode;
  }
  public Object clone() throws CloneNotSupportedException {
    ProcedureImpl that = (ProcedureImpl) super.clone();

    // deep clone of persistent component collections
    that.setMethodCode(that.getMethodCode());
    that.setApproachSiteCode(that.getApproachSiteCode());
    that.setTargetSiteCode(that.getTargetSiteCode());
    return that;
  }
}

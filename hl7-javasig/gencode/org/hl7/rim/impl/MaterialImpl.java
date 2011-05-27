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

import org.hl7.rim.Material;
import org.hl7.rim.impl.EntityImpl;
import org.hl7.types.CE;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Material as a simple data holder bean.
    @see org.hl7.rim.Material
  */
public class MaterialImpl extends org.hl7.rim.impl.EntityImpl implements Material {

  private CE _formCode;
  /** Gets the property formCode.
      @see org.hl7.rim.Material#getFormCode
  */
  public CE getFormCode() { return _formCode; }
  /** Sets the property formCode.
      @see org.hl7.rim.Material#setFormCode
  */
  public void setFormCode(CE formCode) {
    if(formCode instanceof org.hl7.hibernate.ClonableCollection)
      formCode = ((org.hl7.hibernate.ClonableCollection<CE>) formCode).cloneHibernateCollectionIfNecessary();
    _formCode = formCode;
  }
  /** Sets the property formCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Material#setFormCode
  */
  public void setFormCodeForHibernate(CE formCode) {
    _formCode = formCode;
  }
  public Object clone() throws CloneNotSupportedException {
    MaterialImpl that = (MaterialImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

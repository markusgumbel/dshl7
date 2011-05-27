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

import org.hl7.rim.ParameterItem;
import org.hl7.rim.impl.ParameterImpl;
import org.hl7.types.ANY;
import org.hl7.types.ST;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.ParameterItem as a simple data holder bean.
    @see org.hl7.rim.ParameterItem
  */
public class ParameterItemImpl extends org.hl7.rim.impl.ParameterImpl implements ParameterItem {

  private ANY _value;
  /** Gets the property value.
      @see org.hl7.rim.ParameterItem#getValue
  */
  public ANY getValue() { return _value; }
  /** Sets the property value.
      @see org.hl7.rim.ParameterItem#setValue
  */
  public void setValue(ANY value) {
    if(value instanceof org.hl7.hibernate.ClonableCollection)
      value = ((org.hl7.hibernate.ClonableCollection<ANY>) value).cloneHibernateCollectionIfNecessary();
    _value = value;
  }
  /** Sets the property value. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ParameterItem#setValue
  */
  public void setValueForHibernate(ANY value) {
    _value = value;
  }

  private ST _semanticsText;
  /** Gets the property semanticsText.
      @see org.hl7.rim.ParameterItem#getSemanticsText
  */
  public ST getSemanticsText() { return _semanticsText; }
  /** Sets the property semanticsText.
      @see org.hl7.rim.ParameterItem#setSemanticsText
  */
  public void setSemanticsText(ST semanticsText) {
    if(semanticsText instanceof org.hl7.hibernate.ClonableCollection)
      semanticsText = ((org.hl7.hibernate.ClonableCollection<ST>) semanticsText).cloneHibernateCollectionIfNecessary();
    _semanticsText = semanticsText;
  }
  /** Sets the property semanticsText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.ParameterItem#setSemanticsText
  */
  public void setSemanticsTextForHibernate(ST semanticsText) {
    _semanticsText = semanticsText;
  }
  public Object clone() throws CloneNotSupportedException {
    ParameterItemImpl that = (ParameterItemImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

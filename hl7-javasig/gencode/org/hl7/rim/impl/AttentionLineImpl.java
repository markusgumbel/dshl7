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

import org.hl7.rim.AttentionLine;
import org.hl7.rim.impl.InfrastructureRootImpl;
import org.hl7.types.SC;
import org.hl7.types.ANY;

import org.hl7.rim.Transmission;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.AttentionLine as a simple data holder bean.
    @see org.hl7.rim.AttentionLine
  */
public class AttentionLineImpl extends org.hl7.rim.impl.InfrastructureRootImpl implements AttentionLine {

  private SC _keyWordText;
  /** Gets the property keyWordText.
      @see org.hl7.rim.AttentionLine#getKeyWordText
  */
  public SC getKeyWordText() { return _keyWordText; }
  /** Sets the property keyWordText.
      @see org.hl7.rim.AttentionLine#setKeyWordText
  */
  public void setKeyWordText(SC keyWordText) {
    if(keyWordText instanceof org.hl7.hibernate.ClonableCollection)
      keyWordText = ((org.hl7.hibernate.ClonableCollection<SC>) keyWordText).cloneHibernateCollectionIfNecessary();
    _keyWordText = keyWordText;
  }
  /** Sets the property keyWordText. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.AttentionLine#setKeyWordText
  */
  public void setKeyWordTextForHibernate(SC keyWordText) {
    _keyWordText = keyWordText;
  }

  private ANY _value;
  /** Gets the property value.
      @see org.hl7.rim.AttentionLine#getValue
  */
  public ANY getValue() { return _value; }
  /** Sets the property value.
      @see org.hl7.rim.AttentionLine#setValue
  */
  public void setValue(ANY value) {
    if(value instanceof org.hl7.hibernate.ClonableCollection)
      value = ((org.hl7.hibernate.ClonableCollection<ANY>) value).cloneHibernateCollectionIfNecessary();
    _value = value;
  }
  /** Sets the property value. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.AttentionLine#setValue
  */
  public void setValueForHibernate(ANY value) {
    _value = value;
  }

  private org.hl7.rim.Transmission _transmission;
  /** Gets the property transmission.
      @see org.hl7.rim.AttentionLine#getTransmission
  */
  public org.hl7.rim.Transmission getTransmission() {
    return _transmission;
  }
  /** Sets the property transmission.
      @see org.hl7.rim.AttentionLine#setTransmission
  */
  public void setTransmission(org.hl7.rim.Transmission transmission) {
    _transmission = transmission;
  }
  public Object clone() throws CloneNotSupportedException {
    AttentionLineImpl that = (AttentionLineImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

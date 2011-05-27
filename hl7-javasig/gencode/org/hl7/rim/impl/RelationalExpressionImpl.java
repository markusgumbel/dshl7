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

import org.hl7.rim.RelationalExpression;
import org.hl7.rim.impl.SelectionExpressionImpl;
import org.hl7.types.SC;
import org.hl7.types.CS;
import org.hl7.types.ST;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.RelationalExpression as a simple data holder bean.
    @see org.hl7.rim.RelationalExpression
  */
public class RelationalExpressionImpl extends org.hl7.rim.impl.SelectionExpressionImpl implements RelationalExpression {

  private SC _elementName;
  /** Gets the property elementName.
      @see org.hl7.rim.RelationalExpression#getElementName
  */
  public SC getElementName() { return _elementName; }
  /** Sets the property elementName.
      @see org.hl7.rim.RelationalExpression#setElementName
  */
  public void setElementName(SC elementName) {
    if(elementName instanceof org.hl7.hibernate.ClonableCollection)
      elementName = ((org.hl7.hibernate.ClonableCollection<SC>) elementName).cloneHibernateCollectionIfNecessary();
    _elementName = elementName;
  }
  /** Sets the property elementName. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.RelationalExpression#setElementName
  */
  public void setElementNameForHibernate(SC elementName) {
    _elementName = elementName;
  }

  private CS _relationalOperatorCode;
  /** Gets the property relationalOperatorCode.
      @see org.hl7.rim.RelationalExpression#getRelationalOperatorCode
  */
  public CS getRelationalOperatorCode() { return _relationalOperatorCode; }
  /** Sets the property relationalOperatorCode.
      @see org.hl7.rim.RelationalExpression#setRelationalOperatorCode
  */
  public void setRelationalOperatorCode(CS relationalOperatorCode) {
    if(relationalOperatorCode instanceof org.hl7.hibernate.ClonableCollection)
      relationalOperatorCode = ((org.hl7.hibernate.ClonableCollection<CS>) relationalOperatorCode).cloneHibernateCollectionIfNecessary();
    _relationalOperatorCode = relationalOperatorCode;
  }
  /** Sets the property relationalOperatorCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.RelationalExpression#setRelationalOperatorCode
  */
  public void setRelationalOperatorCodeForHibernate(CS relationalOperatorCode) {
    _relationalOperatorCode = relationalOperatorCode;
  }

  private ST _value;
  /** Gets the property value.
      @see org.hl7.rim.RelationalExpression#getValue
  */
  public ST getValue() { return _value; }
  /** Sets the property value.
      @see org.hl7.rim.RelationalExpression#setValue
  */
  public void setValue(ST value) {
    if(value instanceof org.hl7.hibernate.ClonableCollection)
      value = ((org.hl7.hibernate.ClonableCollection<ST>) value).cloneHibernateCollectionIfNecessary();
    _value = value;
  }
  /** Sets the property value. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.RelationalExpression#setValue
  */
  public void setValueForHibernate(ST value) {
    _value = value;
  }
  public Object clone() throws CloneNotSupportedException {
    RelationalExpressionImpl that = (RelationalExpressionImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

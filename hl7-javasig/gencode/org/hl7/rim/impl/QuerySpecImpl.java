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

import org.hl7.rim.QuerySpec;
import org.hl7.rim.impl.QueryEventImpl;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.INT;
import org.hl7.types.CE;
import org.hl7.types.TS;

import org.hl7.rim.SortControl;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.QuerySpec as a simple data holder bean.
    @see org.hl7.rim.QuerySpec
  */
public class QuerySpecImpl extends org.hl7.rim.impl.QueryEventImpl implements QuerySpec {

  private CS _modifyCode;
  /** Gets the property modifyCode.
      @see org.hl7.rim.QuerySpec#getModifyCode
  */
  public CS getModifyCode() { return _modifyCode; }
  /** Sets the property modifyCode.
      @see org.hl7.rim.QuerySpec#setModifyCode
  */
  public void setModifyCode(CS modifyCode) {
    if(modifyCode instanceof org.hl7.hibernate.ClonableCollection)
      modifyCode = ((org.hl7.hibernate.ClonableCollection<CS>) modifyCode).cloneHibernateCollectionIfNecessary();
    _modifyCode = modifyCode;
  }
  /** Sets the property modifyCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QuerySpec#setModifyCode
  */
  public void setModifyCodeForHibernate(CS modifyCode) {
    _modifyCode = modifyCode;
  }

  private SET<II> _responseElementGroupId;
  /** Gets the property responseElementGroupId.
      @see org.hl7.rim.QuerySpec#getResponseElementGroupId
  */
  public SET<II> getResponseElementGroupId() { return _responseElementGroupId; }
  /** Sets the property responseElementGroupId.
      @see org.hl7.rim.QuerySpec#setResponseElementGroupId
  */
  public void setResponseElementGroupId(SET<II> responseElementGroupId) {
    if(responseElementGroupId instanceof org.hl7.hibernate.ClonableCollection)
      responseElementGroupId = ((org.hl7.hibernate.ClonableCollection<SET<II>>) responseElementGroupId).cloneHibernateCollectionIfNecessary();
    _responseElementGroupId = responseElementGroupId;
  }
  /** Sets the property responseElementGroupId. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QuerySpec#setResponseElementGroupId
  */
  public void setResponseElementGroupIdForHibernate(SET<II> responseElementGroupId) {
    _responseElementGroupId = responseElementGroupId;
  }

  private CS _responseModalityCode;
  /** Gets the property responseModalityCode.
      @see org.hl7.rim.QuerySpec#getResponseModalityCode
  */
  public CS getResponseModalityCode() { return _responseModalityCode; }
  /** Sets the property responseModalityCode.
      @see org.hl7.rim.QuerySpec#setResponseModalityCode
  */
  public void setResponseModalityCode(CS responseModalityCode) {
    if(responseModalityCode instanceof org.hl7.hibernate.ClonableCollection)
      responseModalityCode = ((org.hl7.hibernate.ClonableCollection<CS>) responseModalityCode).cloneHibernateCollectionIfNecessary();
    _responseModalityCode = responseModalityCode;
  }
  /** Sets the property responseModalityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QuerySpec#setResponseModalityCode
  */
  public void setResponseModalityCodeForHibernate(CS responseModalityCode) {
    _responseModalityCode = responseModalityCode;
  }

  private CS _responsePriorityCode;
  /** Gets the property responsePriorityCode.
      @see org.hl7.rim.QuerySpec#getResponsePriorityCode
  */
  public CS getResponsePriorityCode() { return _responsePriorityCode; }
  /** Sets the property responsePriorityCode.
      @see org.hl7.rim.QuerySpec#setResponsePriorityCode
  */
  public void setResponsePriorityCode(CS responsePriorityCode) {
    if(responsePriorityCode instanceof org.hl7.hibernate.ClonableCollection)
      responsePriorityCode = ((org.hl7.hibernate.ClonableCollection<CS>) responsePriorityCode).cloneHibernateCollectionIfNecessary();
    _responsePriorityCode = responsePriorityCode;
  }
  /** Sets the property responsePriorityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QuerySpec#setResponsePriorityCode
  */
  public void setResponsePriorityCodeForHibernate(CS responsePriorityCode) {
    _responsePriorityCode = responsePriorityCode;
  }

  private INT _initialQuantity;
  /** Gets the property initialQuantity.
      @see org.hl7.rim.QuerySpec#getInitialQuantity
  */
  public INT getInitialQuantity() { return _initialQuantity; }
  /** Sets the property initialQuantity.
      @see org.hl7.rim.QuerySpec#setInitialQuantity
  */
  public void setInitialQuantity(INT initialQuantity) {
    if(initialQuantity instanceof org.hl7.hibernate.ClonableCollection)
      initialQuantity = ((org.hl7.hibernate.ClonableCollection<INT>) initialQuantity).cloneHibernateCollectionIfNecessary();
    _initialQuantity = initialQuantity;
  }
  /** Sets the property initialQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QuerySpec#setInitialQuantity
  */
  public void setInitialQuantityForHibernate(INT initialQuantity) {
    _initialQuantity = initialQuantity;
  }

  private CE _initialQuantityCode;
  /** Gets the property initialQuantityCode.
      @see org.hl7.rim.QuerySpec#getInitialQuantityCode
  */
  public CE getInitialQuantityCode() { return _initialQuantityCode; }
  /** Sets the property initialQuantityCode.
      @see org.hl7.rim.QuerySpec#setInitialQuantityCode
  */
  public void setInitialQuantityCode(CE initialQuantityCode) {
    if(initialQuantityCode instanceof org.hl7.hibernate.ClonableCollection)
      initialQuantityCode = ((org.hl7.hibernate.ClonableCollection<CE>) initialQuantityCode).cloneHibernateCollectionIfNecessary();
    _initialQuantityCode = initialQuantityCode;
  }
  /** Sets the property initialQuantityCode. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QuerySpec#setInitialQuantityCode
  */
  public void setInitialQuantityCodeForHibernate(CE initialQuantityCode) {
    _initialQuantityCode = initialQuantityCode;
  }

  private TS _executionAndDeliveryTime;
  /** Gets the property executionAndDeliveryTime.
      @see org.hl7.rim.QuerySpec#getExecutionAndDeliveryTime
  */
  public TS getExecutionAndDeliveryTime() { return _executionAndDeliveryTime; }
  /** Sets the property executionAndDeliveryTime.
      @see org.hl7.rim.QuerySpec#setExecutionAndDeliveryTime
  */
  public void setExecutionAndDeliveryTime(TS executionAndDeliveryTime) {
    if(executionAndDeliveryTime instanceof org.hl7.hibernate.ClonableCollection)
      executionAndDeliveryTime = ((org.hl7.hibernate.ClonableCollection<TS>) executionAndDeliveryTime).cloneHibernateCollectionIfNecessary();
    _executionAndDeliveryTime = executionAndDeliveryTime;
  }
  /** Sets the property executionAndDeliveryTime. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.QuerySpec#setExecutionAndDeliveryTime
  */
  public void setExecutionAndDeliveryTimeForHibernate(TS executionAndDeliveryTime) {
    _executionAndDeliveryTime = executionAndDeliveryTime;
  }

  private /*AssociationSet*/List<org.hl7.rim.SortControl> _sortControl;
  /** Gets the property sortControl.
      @see org.hl7.rim.QuerySpec#getSortControl
  */
  public /*AssociationSet*/List<org.hl7.rim.SortControl> getSortControl() {
    return _sortControl;
  }
  /** Sets the property sortControl.
      @see org.hl7.rim.QuerySpec#setSortControl
  */
  public void setSortControl(/*AssociationSet*/List<org.hl7.rim.SortControl> sortControl) {
    _sortControl = sortControl;
  }
  /** Adds an association sortControl.
      @see org.hl7.rim.QuerySpec#setSortControl
  */
  public void addSortControl(org.hl7.rim.SortControl sortControl) {
        // create the association set if it doesn't exist already
    if(_sortControl == null) _sortControl = new AssociationSetImpl<org.hl7.rim.SortControl>();
    // add the association to the association set
    getSortControl().add(sortControl);
    // make the inverse link
    sortControl.setQuerySpec(this);
  }
  public Object clone() throws CloneNotSupportedException {
    QuerySpecImpl that = (QuerySpecImpl) super.clone();

    // deep clone of persistent component collections
    that.setResponseElementGroupId(that.getResponseElementGroupId());
    that._sortControl= null;
    return that;
  }
}

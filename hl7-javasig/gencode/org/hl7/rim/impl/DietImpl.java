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

import org.hl7.rim.Diet;
import org.hl7.rim.impl.SupplyImpl;
import org.hl7.types.PQ;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/** Implementation of org.hl7.rim.Diet as a simple data holder bean.
    @see org.hl7.rim.Diet
  */
public class DietImpl extends org.hl7.rim.impl.SupplyImpl implements Diet {

  private PQ _energyQuantity;
  /** Gets the property energyQuantity.
      @see org.hl7.rim.Diet#getEnergyQuantity
  */
  public PQ getEnergyQuantity() { return _energyQuantity; }
  /** Sets the property energyQuantity.
      @see org.hl7.rim.Diet#setEnergyQuantity
  */
  public void setEnergyQuantity(PQ energyQuantity) {
    if(energyQuantity instanceof org.hl7.hibernate.ClonableCollection)
      energyQuantity = ((org.hl7.hibernate.ClonableCollection<PQ>) energyQuantity).cloneHibernateCollectionIfNecessary();
    _energyQuantity = energyQuantity;
  }
  /** Sets the property energyQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Diet#setEnergyQuantity
  */
  public void setEnergyQuantityForHibernate(PQ energyQuantity) {
    _energyQuantity = energyQuantity;
  }

  private PQ _carbohydrateQuantity;
  /** Gets the property carbohydrateQuantity.
      @see org.hl7.rim.Diet#getCarbohydrateQuantity
  */
  public PQ getCarbohydrateQuantity() { return _carbohydrateQuantity; }
  /** Sets the property carbohydrateQuantity.
      @see org.hl7.rim.Diet#setCarbohydrateQuantity
  */
  public void setCarbohydrateQuantity(PQ carbohydrateQuantity) {
    if(carbohydrateQuantity instanceof org.hl7.hibernate.ClonableCollection)
      carbohydrateQuantity = ((org.hl7.hibernate.ClonableCollection<PQ>) carbohydrateQuantity).cloneHibernateCollectionIfNecessary();
    _carbohydrateQuantity = carbohydrateQuantity;
  }
  /** Sets the property carbohydrateQuantity. To be used only by hibernate.
      @deprecated to be used only by hibernate
      @see org.hl7.rim.Diet#setCarbohydrateQuantity
  */
  public void setCarbohydrateQuantityForHibernate(PQ carbohydrateQuantity) {
    _carbohydrateQuantity = carbohydrateQuantity;
  }
  public Object clone() throws CloneNotSupportedException {
    DietImpl that = (DietImpl) super.clone();

    // deep clone of persistent component collections
    return that;
  }
}

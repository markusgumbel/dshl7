/* The contents of this file are subject to the Health Level-7 Public
* License Version 1.0 (the "License"); you may not use this file
* except in compliance with the License. You may obtain a copy of the
* License at http://www.hl7.org/HPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
* the License for the specific language governing rights and
* limitations under the License.
*
* The Original Code is all this file.
*
* The Initial Developer of the Original Code is Peter Hendler.
* Portions created by Initial Developer are Copyright (C) 2002-2004 
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*
* $Id: AttributeAdapter.java 5652 2007-03-30 15:35:44Z crosenthal $
*/
package org.hl7.meta.mif;

import java.util.ArrayList;
import java.util.List;
import org.hl7.meta.Cardinality;
import org.hl7.meta.CodingStrength;
import org.hl7.meta.Conformance;
import org.hl7.meta.Datatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;

public class AttributeAdapter extends FeatureAdapter implements org.hl7.meta.Attribute {
  private hl7OrgV3.mif.Attribute _mifThing;

  /*package*/ AttributeAdapter(CloneClassAdapter owner, hl7OrgV3.mif.Attribute mifThing) {
    super(owner, mifThing, mifThing.getSortKey());
    _mifThing = mifThing;
  };

  /*package*/ AttributeAdapter copyTo(CloneClassAdapter newOwner) {
    return (AttributeAdapter)super.copyTo(newOwner);
  }
  
  public String getName() {
    return _mifThing.getName();
  }

  private Cardinality _cardinality = null;

  public Cardinality getCardinality() {
    if(_cardinality == null)
      try {
	String min = String.valueOf(_mifThing.getMinimumMultiplicity());
	String max = _mifThing.getMaximumMultiplicity().toString();
	_cardinality = Cardinality.create(min + ".." + max);
      } catch (RuntimeException e) {
	System.err.println("bad cardinality " + this.getName());
	_cardinality = Cardinality.create("0..*");
      }
    return _cardinality;
  }
  
  public String getRimPropertyName() {
    return _mifThing.getDerivationSupplierArray(0).getAttributeName();
  }

  public boolean isMandatory() {
    return _mifThing.getIsMandatory();
  }

  private Datatype getDatatype(hl7OrgV3.mif.DatatypeRef mifThing) {
    hl7OrgV3.mif.DatatypeRef params[] = mifThing.getSupplierBindingArgumentDatatypeArray();
    try {
      if(params == null || params.length == 0)
	return DatatypeMetadataFactoryImpl.instance().create(mifThing.getName(),null);
      else {
	List<Datatype> parameters = new ArrayList(params.length);
	for(int i = 0; i < params.length; i++) {
	  parameters.add(getDatatype(params[i]));
	}
	return DatatypeMetadataFactoryImpl.instance().create(mifThing.getName(),parameters);
      }
    } catch (UnknownDatatypeException e) {
      throw new RuntimeException(e);
    }
  }

  Datatype _datatype = null;
  public Datatype getDatatype() {
    if(_datatype == null)
      _datatype = getDatatype(_mifThing.getType());
    return _datatype;
  }
  
  public String[] getDomains() {
    String domain = null;
    try {
      domain = _mifThing.getSupplierDomainSpecification()
	.getDomainName();
    } catch (RuntimeException e) {
      //keep going
    }
    if (domain != null) {
      String[] domainarray = { domain };
      return domainarray;
    } else {
      return null;
    }
  }
  
  public String[] getFixedValues() {
    String fixedValue = _mifThing.getFixedValue();
    String[] fvarray = { fixedValue };
    return fvarray;
  }
  
  public String getDefaultValue() {
    return _mifThing.getDefaultValue();
  }
  
  public CodingStrength getCodingStrength() {
    String cdstr = null;
    
    try {
      cdstr = _mifThing.getSupplierDomainSpecification().getCodingStrength().toString();
    } catch (RuntimeException e) {
      //do nothing keep going
    }
    return CodingStrength.create(cdstr);
  }
  
  public boolean isStructural() {
    return _mifThing.getIsStructural();
  }
  
  public Conformance getConformance() {
    //no Idea if this works yet
    hl7OrgV3.mif.ConformanceKind.Enum confenum = _mifThing.getConformance();
    String conf = null;
    try {
      conf = confenum.toString();
    } catch (RuntimeException e) {
      //do nothing keep going
    }
    return Conformance.create(conf);
  }
  
  public String getRimClass() {
    return _mifThing.getDerivationSupplierArray(0).getClassName();
  }

  public String getConstraint() {
    throw new Error("this is not used");
  }	
}

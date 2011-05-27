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
* The Initial Developer of the Original Code is Gunther Schadow.
* Portions created by Initial Developer are Copyright (C) 2002-2004 
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*
* $Id: FeatureAdapter.java 7053 2007-08-31 18:05:06Z jmoore $
*/
package org.hl7.meta.mif;

import java.util.Comparator;
import org.hl7.meta.Association;
import org.hl7.meta.Cardinality;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Conformance;

/**
 * @author Gunther Schadow
 * 
 */
public abstract class FeatureAdapter implements Cloneable, org.hl7.meta.Feature {
  protected CloneClassAdapter _owner;
  
  /** This number says from how far away this feature is inherited. 0 means it
      has been specified in this class, 1 in this class' parent, 2 grandparent,
      and so on. This is used to sort the attributes and to control overrides
      if necessary.
  */
  protected int _inheritanceDegree = 0;

  /** This thing specifies a sort order among all the siblings. */
  private String _sortKey;
  
  /** This class specifies the sort order of features.*/
  /*package*/ static final Comparator COMPARATOR = new Comparator() {
      /** @return a negative integer if the first argument is less than the second, zero if they are equal and a positive integer otherwise. */
      public int compare(Object a, Object b) {
				FeatureAdapter featureA = (FeatureAdapter)a;
				FeatureAdapter featureB = (FeatureAdapter)b;
				int orderA = intOrZero(featureA._sortKey);
				int orderB = intOrZero(featureB._sortKey);
				return basePenalty(featureA) - basePenalty(featureB) + ( (orderA != 0 && orderB != 0) ? orderA - orderB : featureA._sortKey.compareTo(featureB._sortKey) );
      }
      
      public boolean equals(Object a, Object b) {
				return a == b;
      }
			
      private int basePenalty(FeatureAdapter feature) {
				return (feature instanceof Association ? 0x20000000 : 0);
//					| ( (feature._inheritanceDegree << 16) & 0x1fff0000 );  //ingore the inheritanceDegree
      }    
			
    };


	protected FeatureAdapter(CloneClassAdapter owner, hl7OrgV3.mif.ModelElement element, String sortKey) {
    _owner = owner;
    if(sortKey == null)
			_sortKey = "";
		else {
			//Replace the underscores with space
            _sortKey = sortKey.replace("_", "");
        }
  }

  /** This not only copies but also increases the inheritance degree. */
  /*package*/ FeatureAdapter copyTo(CloneClassAdapter newOwner) {
    try {
      FeatureAdapter newAdapter = (FeatureAdapter)this.clone();
       
      // Change the owner in the new copy, not the original.
      newAdapter._owner = newOwner;
      newAdapter._inheritanceDegree = _inheritanceDegree + 1;
      return newAdapter;
    } catch(CloneNotSupportedException ex) {
      throw new Error(ex);
    }
  }

	public int getSortKey() {
		return intOrZero(_sortKey);
	}

  public abstract String getName();
  public abstract Cardinality getCardinality();
  public abstract String getRimClass();
  public abstract String getRimPropertyName();

  public CloneClass getParent() {
    return _owner;
  }

  public abstract boolean isMandatory();
  public abstract Conformance getConformance();
  public abstract String getConstraint();
	
	private static final int intOrZero(String s) {
		try {
			return Integer.parseInt(s);
		} catch(NumberFormatException ex) {
			return 0;
		}
	}
	
	public String toString() {
		return getName() + "[" + getRimClass() + "." + getRimPropertyName() + "]";
	}
}

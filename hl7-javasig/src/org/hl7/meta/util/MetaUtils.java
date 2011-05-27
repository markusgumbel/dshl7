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
* The Initial Developer of the Original Code is Eric Chen.
* Portions created by Initial Developer are Copyright (C) 2002-2004
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s):
*/


package org.hl7.meta.util;

import java.util.logging.Logger;
import org.hl7.meta.Attribute;
import org.hl7.types.ANY;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.hl7.types.ValueFactoryException;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.CSimpl;
import org.hl7.types.impl.CVimpl;
import org.hl7.xml.builder.FeatureException;

/**
 * A utility class for Meta data.
 *
 * @author Eric Chen
 */

public class MetaUtils {

  protected static final Logger LOGGER =
    Logger.getLogger("org.hl7.xml.meta");

    /**
     * The default value of the attribute. Most time is used for HL7 Structure Attributes
     * @param attribute
     * @return
     */
    public static String getDefaultValue (Attribute attribute) {
        try {
            ANY attributeDefaultValue = getAttributeDefaultValue(null, attribute);
            return attributeDefaultValue != null ? attributeDefaultValue.toString() : null;
        } catch (FeatureException e) {
            return null;
        }
    }


    /**
     * @param value
     * @param attribute
     * @return ANY
     * @throws FeatureException
     */
    public static ANY getAttributeDefaultValue(ANY value, Attribute attribute) //FIXME: does param value needs to be passed ?
      throws FeatureException
    {
      
      String fixedValues[] = attribute.getFixedValues();
      String defaultValue = attribute.getDefaultValue();

      String newValue = defaultValue;
      if(newValue == null)
				if(fixedValues != null && fixedValues.length > 0)
					newValue = fixedValues[0];

			if(newValue == null)
				return value;
			
      String datatype = attribute.getDatatype().getFullName();
      //    peter added to handle BL which has no domain

			// FIXME: string comparison is very bad here. We should have a proper hierarchical enumeration
      if(datatype.equals("BL"))
    	  return BLimpl.valueOf(newValue);

			else if(datatype.equals("CD") || datatype.equals("CE") || datatype.equals("CV")) {
				UID codeSystem = getCodeSystemId(attribute.getDomains());      

				if(codeSystem != null)
					return CVimpl.valueOf(newValue, codeSystem.toString());			
				else
					return value;

			} else if (datatype.equals("CS")) {
				UID codeSystem = getCodeSystemId(attribute.getDomains());      
                   
				// XXX: we never get here any more, why was this needed?? if(newValue== null) return CSnull.NI;
                    
				if(codeSystem != null)
					return CSimpl.valueOf(newValue, codeSystem.toString());
				if(codeSystem == null)
				        return CSimpl.valueOf(newValue, "myCodeSystem");	

      } else {
      	try {
      		return ValueFactory.getInstance().valueOfLiteral(datatype, newValue);
      	} catch (ValueFactoryException e) {
					throw new FeatureException(attribute, e);
      	} 
      }
			throw new Error("should not be here: " + attribute + ": " + datatype + ": " + newValue);
    }

    /**
     * @param vocabDomains
     * @return the codeSystem UID 
     */
	  public static UID getCodeSystemId(String[] vocabDomains)
      {
	      if (vocabDomains == null) return null;
          
			for(String domain : vocabDomains) {
				
				UID result = org.hl7.types.impl.DomainMapImpl.getInstance().get(domain);
				if(result != null)
					return result;
				
			System.out.println(" ------------null domain to be added to domain-oid-map.xml is " + domain);					
			}
		  	
			return null;
		}
}

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
* The Initial Developer of the Original Code is .
* Portions created by Initial Developer are Copyright (C) 2002-2004 
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*/
package org.hl7.xml.builder;

import org.hl7.meta.Attribute;
import org.hl7.xml.validator.FeatureCardinalityException;

/**
 * Exception that is thrown when an attribute cardinality specified in HMD
 * and values present in RIM object are incompatible.  May happen in the
 * following cases:
 * <ol>
 * <li>HMD specifies cardinality 1..1 or 1..*, but value is not present
 * in RIM object.</li>
 * <li>HMD specifies cardinality 0..1 or 0..*, but multiple values are
 * present in RIM object.</li>
 * </ol>
 *
 * @author Skirmantas Kligys
 */
public class AttributeCardinalityException extends FeatureCardinalityException {
    //-------------------------------------------------------------------------
    /**
     * Number of values present in RIM object.
     */
    private final String actualCardinality_;

    //-------------------------------------------------------------------------
    public AttributeCardinalityException(Attribute attribute,
                                         String actualCardinality) {
        super(attribute, actualCardinality);

        actualCardinality_ = actualCardinality;
    }

    //-------------------------------------------------------------------------
    /**
     * @return Returns the actual cardinality.
     */
    public String getActualCardinality() {
        return String.valueOf(actualCardinality_);
    }
}

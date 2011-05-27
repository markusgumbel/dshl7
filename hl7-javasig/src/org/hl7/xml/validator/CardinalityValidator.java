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


package org.hl7.xml.validator;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import org.hl7.meta.Association;
import org.hl7.meta.Attribute;
import org.hl7.meta.Feature;
import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.rim.RimObject;
import org.hl7.types.AD;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BIN;
import org.hl7.types.EN;
import org.hl7.types.LIST;
import org.hl7.types.QSET;
import org.hl7.types.SET;
import org.hl7.xml.builder.AttributeCardinalityException;

/**
 * Check the Rim Object Graph Cardinality based on HL7 v3 Meta cardinality
 * User: Eric Chen
 * Date: Nov 1, 2004
 * Time: 10:47:53 PM
 */
public class CardinalityValidator {

    //-------------------------------------------------------------------------
    public static void checkAssociationCardinality(Collection collection,
                                                   Association association) throws AssociationCardinalityException {
        int actualCardinality = (collection != null) ? collection.size() : 0;
        if (actualCardinality < association.getCardinality().getMin() ||
                actualCardinality > association.getCardinality().getMax()) {
            throw new AssociationCardinalityException(association,
                    String.valueOf(actualCardinality));
        }
    }

    public static void checkAssocaitonMaxCardinality(Iterator iterator, RimObject value, Association association) throws FeatureCardinalityException {
        int totalElements = 0;
        while (iterator.hasNext()) {
            RimObject rimObject = (RimObject) iterator.next();
            if (value.getCloneCode().equal(rimObject.getCloneCode()).isTrue())
                totalElements++;
        }
        if ((totalElements > association.getCardinality().getMax()))
            throw new AssociationCardinalityException(association,
                    "more than " + association.getCardinality().getMax());
    }


    //-------------------------------------------------------------------------
    public static void checkAttributeCardinality(Collection collection, Attribute attribute) throws FeatureCardinalityException {
        int totalElements = (collection != null) ? collection.size() : 0;

        if ((totalElements > attribute.getCardinality().getMax()) && !attribute.getDatatype().equals(DatatypeMetadataFactoryDatatypes.instance().ANYTYPE))
            throw new AttributeCardinalityException(attribute,
                    "more than " + attribute.getCardinality().getMax());

        if ((totalElements < attribute.getCardinality().getMin()))
            throw new AttributeCardinalityException(attribute,
                    "less than " + attribute.getCardinality().getMin());
    }


    //-------------------------------------------------------------------------
    public static void checkAttributeCardinality(ANY value, Attribute attribute)
            throws FeatureCardinalityException {
        int actualCardinality = getDatatypeCardinality(value);
        if (actualCardinality < attribute.getCardinality().getMin()) {
            boolean fixedValuePresent = (attribute.getFixedValues() != null) &&
                    (attribute.getFixedValues().length > 0);
            boolean defaultValuePresent = attribute.getDefaultValue() != null;
            if (!fixedValuePresent && !defaultValuePresent) {
                throw new AttributeCardinalityException(attribute, String.valueOf(actualCardinality));
            }
        } else if (actualCardinality > attribute.getCardinality().getMax() && !attribute.getDatatype().equals(DatatypeMetadataFactoryDatatypes.instance().ANYTYPE)) {
            throw new AttributeCardinalityException(attribute, String.valueOf(actualCardinality));
        }
    }

    //-------------------------------------------------------------------------
    public static int getDatatypeCardinality(ANY value) {
        if (value == null || value.isNullJ())
            return 0;
        else if (value instanceof BIN) {
            // Don't treat as LIST<BL>.
            return 1;
        } else if (value instanceof AD) {
            return 1;
        } else if (value instanceof EN) {
            return 1;
        } else if (value instanceof QSET) {
            // QSET and IVL are special, may have infinite cardinality
            return 1;
        }else if (value instanceof BAG) {
            return ((BAG) value).size().intValue();
        } else if (value instanceof LIST) {
            return ((LIST) value).length().intValue();
        } else if (value instanceof SET) {
            return ((SET) value).cardinality().intValue();
        } else
            return 1;
    }

    //-------------------------------------------------------------------------
    public static void checkMaxOneCardinality(Object result, String methodNameStem, Feature feature) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, FeatureCardinalityException {
        // let's check if cardinality is one
        String getMethodName = ("get" + methodNameStem).intern();
        Object distalObject = result.getClass().getMethod(getMethodName)
                .invoke(result);
        if (distalObject != null) {
	  throw new FeatureCardinalityException(feature, "more than 1");
        }
        // End of checking the cardinality
    }


}

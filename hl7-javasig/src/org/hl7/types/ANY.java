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
package org.hl7.types;

/**
 * Defines the basic properties of every data value. This is an abstract type,
 * meaning that no value can be just a data value without belonging to any
 * concrete type. Every concrete type is a specialization of this general
 * abstract DataValue type.
 */
public interface ANY {
    /**
     * An exceptional value expressing missing information and possibly the
     * reason why the information is missing.
     */
    NullFlavor nullFlavor();
    
    BL nonNull();
    
    BL isNull();
    
    BL notApplicable();
    
    BL unknown();
    
    BL other();
    
    BL equal(ANY x);
    
    /**
     * @deprecated use <code>nonNull().isTrue()</code> or
     *             <code>!nonNull().isFalse()</code> instead, it's different
     *             and you do want to know!
     */
    @Deprecated
    boolean nonNullJ();
    
    /**
     * @deprecated use <code>isNull().isTrue()</code> or
     *             <code>!isNull().isFalse()</code> instead, it's different
     *             and you do want to know!
     */
    @Deprecated
    boolean isNullJ();
    
    /** @deprecated this is more confusing than helpful */
    @Deprecated
    boolean notApplicableJ();
    
    /** @deprecated this is more confusing than helpful */
    @Deprecated
    boolean unknownJ();
    
    /** @deprecated this is more confusing than helpful */
    @Deprecated
    boolean otherJ();
}

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
 * <p>
 * <code>BL</code> stands for the values of two-valued logic. A
 * <code>BL</code> value can be either true or false, or, as any other value
 * may be NULL.
 * </p>
 * <p>
 * With any data value potentially being NULL, the two-valued logic is
 * effectively extended to a three-valued logic as shown in the following truth
 * tables:
 * </p>
 * <p>
 * Where a boolean operation is performed upon 2 data types with different
 * nullFlavors, the nullFlavor of the result is the first common ancestor of the
 * 2 different nullFlavors, though conformant applications may also create a
 * result that is any common ancestor.
 * </p>
 */
public interface BL extends ANY {
    static final String TRUE_STRING = Boolean.toString(true);
    static final String FALSE_STRING = Boolean.toString(false);
    
    BL and(BL x);
    
    BL not();
    
    BL or(BL x);
    
    BL eor(BL x);
    
    BL implies(BL x);
    
    boolean isTrue();
    
    boolean isFalse();
    
    String toString();
}
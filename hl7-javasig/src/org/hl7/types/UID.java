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
 * A unique identifier string is a character string which identifies an object
 * in a globally unique and timeless manner. The allowable formats and values
 * and procedures of this data type are strictly controlled by HL7. At this
 * time, user-assigned identifiers may be certain character representations of
 * ISO Object Identifiers ({@link OID}) and DCE Universally Unique Identifiers ({@link UUID}).
 * HL7 also reserves the right to assign other forms of <code>UID</code>s (<code>RUID</code>),
 * such as mnemonic identifiers for code systems.
 * </p>
 * <p>
 * The sole purpose of <code>UID</code> is to be a globally and timelessly
 * unique identifier. The form of <code>UID</code>, whether it is an
 * <code>OID</code>, a <code>UUID</code> or a <code>RUID</code>, is
 * entirely irrelevant. As far as HL7 is concerned, the only thing one can do
 * with a <code>UID</code> is denote to the object for which it stands.
 * Comparison of <code>UID</code>s is literal, i.e. if two <code>UID</code>s
 * are literally identical, they are assumed to denote to the same object. If
 * two <code>UID</code>s are not literally identical they may not denote to
 * the same object.
 * </p>
 * 
 * <pre>
 * type UniqueIdentifierString alias UID specializes ST { };
 * </pre>
 * 
 * <p>
 * No difference in semantics is recognized between the different allowed forms
 * of the <code>UID</code>. The different forms are not distinguished by a
 * component within or aside from the identifier string itself.
 * </p>
 * <p>
 * Even though this specification recognizes no semantic difference between the
 * different forms of the unique identifier forms, there are differences of how
 * these identifiers are built and managed, which is the sole reason to define
 * subtypes to the <code>UID</code> for each of the variants.
 * </p>
 * 
 * @see <a
 *      href="http://www.hl7.org/v3ballot2007sep/html/infrastructure/datatypes/datatypes.htm#dt-UID"
 *      target="_" title="HL7 Version 3 Standard">Unique Identifier String (UID)</a>
 */
public interface UID extends ST {
    // simple specialization with the same behavior
}

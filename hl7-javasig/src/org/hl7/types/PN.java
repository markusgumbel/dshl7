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
 * A name for a person. A sequence of name parts, such as given name or family
 * name, prefix, suffix, etc. <code>PN</code> differs from {@link EN} because
 * the qualifier type cannot include &quot;LS&quot; ({@link org.hl7.types.enums.EntityNamePartQualifier#LegalStatus Legal Status}).
 */
public interface PN extends EN {
    // simple specialization with the same behavior
}

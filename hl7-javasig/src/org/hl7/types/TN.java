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
 * A restriction of entity name that is effectively a simple string used for a
 * simple name for things and places.
 * </p>
 * <p>
 * <code>TN</code> is an <code>EN</code> that consists of only one name part
 * without any name part type or qualifier. The <code>TN</code>, and its
 * single name part are therefore equivalent to a simple character string. This
 * equivalence is expressed by a defined demotion to {@link ST} and promotion
 * from <code>ST</code>.
 * </p>
 * 
 * <pre>
 *  type TrivialName alias TN specializes EN {
 *      demotion   ST;
 *      promotion  TN (ST x);
 *  };
 * 
 *  invariant(TN x) where x.nonNull {
 *      x.head.nonNull;
 *      x.tail.isEmpty;
 *      x.formatted.equal(x.head);
 *  };
 * 
 *  invariant(ST x) {
 *      ((TN)x).head.equal(x);
 *  };
 * </pre>
 * 
 * <p>
 * Trivial names are typically used for places and things, such as Lake Erie or
 * Washington-Reagan National Airport:
 * </p>
 * 
 * Example 12.
 * 
 * <pre>
 * &lt;name&gt;Lake Erie&lt;/name&gt;
 * &lt;name&gt;Washington-Reagan National Airport&lt;/name&gt;
 * </pre>
 * 
 * @see <a
 *      href="http://www.hl7.org/v3ballot2007sep/html/infrastructure/datatypes/datatypes.htm#dt-TN"
 *      target="_" title="HL7 Version 3 Standard">Trivial Name (TN)</a>
 */
public interface TN extends EN {
    // simple specialization with the same behavior
}

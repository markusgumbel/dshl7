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
 * A name for a person, organization, place or thing. A sequence of name parts,
 * such as given name or family name, prefix, suffix, etc. Examples for entity
 * name values are &quot;Jim Bob Walton, Jr.&quot;, &quot;Health Level Seven,
 * Inc.&quot;, &quot;Lake Tahoe&quot;, etc. An entity name may be as simple as a
 * character string or may consist of several entity name parts, such as,
 * &quot;Jim&quot;, &quot;Bob&quot;, &quot;Walton&quot;, and &quot;Jr.&quot;,
 * &quot;Health Level Seven&quot; and &quot;Inc.&quot;, &quot;Lake&quot; and
 * &quot;Tahoe&quot;.
 * 
 * @see <a
 *      href="http://www.hl7.org/v3ballot/html/infrastructure/datatypes/datatypes.htm#dt-EN"
 *      target="_" title="HL7 Version 3 Ballot Site - May 2008">Entity Name</a>
 */
public interface EN extends LIST<ENXP> {
    /**
     * Two name values are considered equal if both contain the same name parts,
     * independent of ordering. Use code and valid time are excluded from the
     * equality test.
     * 
     * <pre>
     * invariant(EN x, y)
     *       where x.nonNull.and(y.nonNull) {
     *    x.equal(y).equal((
     *       forall(ENXP p) where x.contains(p) {
     *              y.contains(p);
     *              }).and.(
     *             forall(ENXP p) where x.contains(p) {
     *                    y.contains(p);
     *                    }));
     * </pre>
     */
    BL equal(ANY y);
    
    /**
     * 
     * A set of codes advising a system or user which name in a set of like
     * names to select for a given purpose. A name without specific use code
     * might be a default name useful for any purpose, but a name with a
     * specific use code would be preferred for that respective purpose.
     * 
     * @generatedComment
     */
    DSET<CS> use();
    
    /** @deprecated use {@link #validTime()} instead. */
    @Deprecated
    IVL<TS> useablePeriod();
    
    /**
     * An interval of time specifying the time during which the name is or was
     * used for the entity. This accomodates the fact that people change names
     * for people, places and things.
     */
    IVL<TS> validTime();
    
    /**
     * A character string value with the entity name formatted with proper
     * spacing. This is only a semantic property to define the function of some
     * of the name part types. Remember that semantic properties are bare of all
     * control flow semantics. The formatted could be implemented as a
     * &quot;procedure&quot; that would &quot;return&quot; the formatted name,
     * but it would not usually be a variable to which one could assign a
     * formatted name. However, HL7 does not define applications but only the
     * semantics of exchanged data values. Hence, the semantic model abstracts
     * from concepts like &quot;procedure&quot;, &quot;return&quot;, and
     * &quot;assignment&quot; but speaks only of property and value.
     */
    ST formatted();
}

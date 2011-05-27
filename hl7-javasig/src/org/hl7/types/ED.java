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
 * Data that is primarily intended for human interpretation or for further
 * machine processing outside the scope of HL7. This includes unformatted or
 * formatted written language, multimedia data, or structured information in as
 * defined by a different standard (e.g., XML-signatures.) Instead of the data
 * itself, an <code>ED</code> may contain only a reference (see {@link TEL}.)
 * Note that {@link ST} is a specialization of the <code>ED</code> where the
 * {@link #mediaType() mediaType} is fixed to text/plain.
 */
public interface ED extends BIN {
    /**
     * Identifies the type of the encapsulated data and identifies a method to
     * interpret or render the data.
     */
    CS mediaType();
    
    /**
     * For character-based encoding types, this property specifies the character
     * set and character encoding used. The charset shall be identified by an
     * Internet Assigned Numbers Authority (IANA) <a
     * href="http://www.iana.org/assignments/character-sets" title="IANA"
     * target="_">Charset Registration</a> in accordance with <a
     * href="http://www.ietf.org/rfc/rfc2978.txt" title="IETF" target="_">RFC
     * 2978</a>.
     */
    CS charset();
    
    /**
     * For character based information the language property specifies the human
     * language of the text.
     */
    CS language();
    
    /**
     * Indicates whether the raw byte data is compressed, and what compression
     * algorithm was used.
     */
    CS compression();
    
    /**
     * A telecommunication address (<code>TEL</code>), such as a URL for
     * HTTP or FTP, which will resolve to precisely the same binary data that
     * could as well have been provided as inline data.
     */
    TEL reference();
    
    /**
     * The integrity check is a short binary value representing a
     * cryptographically strong checksum that is calculated over the binary
     * data. The purpose of this property, when communicated with a reference is
     * for anyone to validate later whether the reference still resolved to the
     * same data that the reference resolved to when the encapsulated data value
     * with reference was created.
     */
    BIN integrityCheck();
    
    /**
     * Specifies the algorithm used to compute the integrityCheck value. The
     * cryptographically strong checksum algorithm Secure Hash Algorithm-1
     * (SHA-1) is currently the industry standard. It has superseded the MD5
     * algorithm only a couple of years ago, when certain flaws in the security
     * of MD5 were discovered. Currently the SHA-1 hash algorithm is the default
     * choice for the integrity check algorithm. Note that SHA-256 is also
     * entering widespread usage.
     */
    CS integrityCheckAlgorithm();
    
    /**
     * An abbreviated rendition of the full data. A thumbnail requires
     * significantly fewer resources than the full data, while still maintaining
     * some distinctive similarity with the full data. A thumbnail is typically
     * used with by-reference encapsulated data. It allows a user to select data
     * more efficiently before actually downloading through the reference.
     */
    ED thumbnail();
}
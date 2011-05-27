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


/* rudimentary right now so I can get bootstrapped */
/**
 * 
 * A concept descriptor represents any kind of concept usually
 * by giving a code defined in a code system.  A concept
 * descriptor can contain the original text or phrase that
 * served as the basis of the coding and one or more
 * translations into different coding systems. A concept
 * descriptor can also contain qualifiers to describe, e.g.,
 * the concept of a "left foot" as a postcoordinated term built
 * from the primary code "FOOT" and the qualifier "LEFT".
 * In exceptional cases, the concept descriptor need not
 * contain a code but only the original text describing
 * that concept.
 * 
 * @generatedComment
 */
public interface CD extends ANY {
  /**
   * 
   * The plain code symbol defined by the code system.
   * For example, "784.0" is the code symbol of the ICD-9
   * code "784.0" for headache.
   * 
   * @generatedComment
   */
  ST    code();
  /**
   * 
   * A name or title for the code, under which the sending
   * system shows the code value to its users.
   * 
   * @generatedComment
   */
  ST    displayName();
  /**
   * 
   * Specifies the code system that defines the code.
   * 
   * @generatedComment
   */
  UID   codeSystem();
  /**
   * 
   * A common name of the coding system.
   * 
   * @generatedComment
   */
  ST    codeSystemName();
  /**
   * 
   * If applicable, a version descriptor defined
   * specifically for the given code system.
   * 
   * @generatedComment
   */
  ST    codeSystemVersion();
  ED    originalText();
  LIST<CR> qualifier();
  SET<CD>  translation();
  
  BL    implies(CD x);
  CD    mostSpecificGeneralization(CD x);
}


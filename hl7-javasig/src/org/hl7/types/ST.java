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

import java.util.Iterator;

/**
 * 
 * The character string data type stands for text data,
 * primarily intended for machine processing (e.g.,
 * sorting, querying, indexing, etc.) Used for names,
 * symbols, and formal expressions.
 * 
 * @generatedComment
 */
public interface ST extends ED {
  // redefined
  // INT length();

  // the head and tail override with different return type is 
  // In Java we have to put the return type into the name of
  // the function. The V3DT language is sensitive to return
  // types.
  ST  headST();
  ST  tailST();
  Iterator<ST> listSTIterator();

  String toString();
}
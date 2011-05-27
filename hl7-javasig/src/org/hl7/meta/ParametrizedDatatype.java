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
package org.hl7.meta;

import java.util.List;

/**
 * Generic data type with one parameter, e.g. <code>LIST&lt;TEL&gt;</code>,
 * <code>BAG&lt;TS&gt;</code>, <code>SET&lt;CS&gt;</code>,
 * <code>UVP&lt;CD&gt;</code> or <code>IVL&lt;TS&gt;</code>.

 *
 * @author Skirmantas Kligys, Eric Chen
 */
public interface ParametrizedDatatype extends Datatype
{
 /**
  * type of ParametrizedDatatype, such as "SET", "IVL", "BAG", "RTO"
  * @return
  */
  String getType();
  
  /**
   * List of Data type, such as "TS", "PQ"
   * @return
   */
  List<Datatype> getParameter();

  /**
   *
   * @param i
   * @return
   */
  Datatype getParameter(int i);

  /**
   * Is pure parametrized data type? SET, BAG and LIST only; IVL<TS>, RTO<QTY, QTY> are not.
   * @return
   */
  boolean isPure();
}

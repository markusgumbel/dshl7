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
* Portions created by Initial Developer are Copyright (C) 2002-2006
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*/
package org.hl7.hibernate;

import java.io.Serializable;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.util.StringHelper;

/**
 * An improved naming strategy that just adds an "_" to everything :)
 * @see DefaultNamingStrategy the default strategy
 * @author Matt Carlson
 */
public class OracleNamingStrategy extends ReservedWordAvoidanceNamingStrategy implements NamingStrategy, Serializable {
  /**
   * The singleton instance
   */
  public static final NamingStrategy INSTANCE = new OracleNamingStrategy();

  protected OracleNamingStrategy() {}

	protected String avoidReservedWord(String s) {
		s = super.avoidReservedWord(StringHelper.unqualify(s));
		if(s.length() > 30)
			return s.substring(0,29) + Math.abs(s.substring(29).hashCode() % 10);
		else 
			return s;
	}
}

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
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2005 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hpath;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.regenstrief.util.RegexTokenizer;

/** A macro which we replace before parsing an expression. 
		This is a first step to making convenient access paths.

		#author -> participation[typeCode.implies(CS:ParticipationType:Author)]
		#component -> outboundRelationship[typeCode.implies(CS:ActRelationshipType:ActRelationshipHasComponent)]

		however, some replacements we would like to have context dependent, such as for subject,
		so instead of #subject we need to distinguish between #subjectParticipation and #subjectRelationship
*/
public class Macros {
	/*package*/ Macros() { }

	Map<String,String> _macroTable = new HashMap<String,String>();

	public void define(String name, String expansion) {
		expansion = expandMacros(expansion);
		String oldExpansion = _macroTable.put(name, expansion);
		if(oldExpansion != null && !oldExpansion.equals(expansion))
			throw new RuntimeException("redefined macro " + name + " := " + expansion + " was " + oldExpansion);
	}

	private static final Pattern PATTERN = Pattern.compile("#([a-zA-Z_][a-zA-Z0-9_]*)");

	public String expandMacros(String expression) {
		RegexTokenizer tokenizer = new RegexTokenizer(PATTERN, expression);
		StringBuffer resultBuffer = new StringBuffer();
		while(tokenizer.seek()) {
			String skipped = tokenizer.skipped();
			if(skipped != null)
				resultBuffer.append(skipped);
			String macro = tokenizer.group(1);
			String expansion = _macroTable.get(macro);
			if(expansion == null)
				throw new SyntaxError("undefined macro #" + macro);
			resultBuffer.append(expansion);
		}
		resultBuffer.append(tokenizer.rest());
		return resultBuffer.toString();
	}
}

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
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import junit.framework.TestCase;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.CE;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;

/**
 * Test to check on the infamous handling of the <matt> tag in the standalone 
 * serialization of ANY and SET<T> data type values.
 *
 * @author Gunther Schadow
 * @version $Revision: 5073 $
 */
public class TestMatt extends TestCase {

	public void testParse() throws Exception {
		final String input="<matt xmlns='urn:hl7-org:v3' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'><a xsi:type='CE' code='55130001' displayName='Laryngotracheitis (disorder)' codeSystem='2.16.840.1.113883.6.96'/><a xsi:type='CE' code='55130001' displayName='Laryngotracheitis' codeSystem='2.16.840.1.113883.6.96'/></matt>";

		InputStream is = new ByteArrayInputStream(input.getBytes());
		ANY value = (ANY)StandaloneDataTypeContentHandler.parseValue(is, null);
		assertTrue("data is a bag", value instanceof BAG);
		BAG bag = (BAG)value;
		assertEquals("bag size ", 2, bag.size().intValue());
		Iterator iter = bag.iterator();
		assertTrue("first element is of type CE", iter.next() instanceof CE);
		assertTrue("second element is of type CE", iter.next() instanceof CE);
		// System.err.println("echo passed " + value);
	}

	public static void main(String args[]) throws Exception {
		new TestMatt().testParse();
	}
}

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
package org.hl7.xml;

import org.hl7.types.ANY;
import org.hl7.util.FactoryException;
import org.hl7.util.FactoryFinder;

public abstract class DatatypePresenterFactory {
	// -------------------------------------------------------------------------
	private static final String FACTORY_ID = "org.hl7.xml.DatatypePresenterFactory";

	// -------------------------------------------------------------------------
	/**
	 * Similar to javax.xml.parsers.SAXParserFactory.newInstance():
	 * <nl>
	 * <li>Checks org.hl7.xml.DatatypePresenterFactory system property.</li>
	 * <li>Checks file hl7.properties for key org.hl7.xml.DatatypePresenterFactory in the current directory.</li>
	 * <li>Uses Services API to look for factory class name in the file
	 * <code>META-INF/services/org.hl7.xml.DatatypePresenterFactory</code> in jars on the classpath.</li>
	 * <li>If none of the above exist, uses default properties based factory.</li>
	 * </nl>
	 */
	public static DatatypePresenterFactory getInstance() throws FactoryException {
		return (DatatypePresenterFactory) FactoryFinder.find(FACTORY_ID,
				"org.hl7.xml.DatatypePresenterPropertiesBasedFactory");
	}

	/** Get a presenter for the specified data type */
	public abstract DatatypePresenter createPresenter(String typeSpec) throws FactoryException;

	/** Get a presenter appropriate to render the value */
	public abstract DatatypePresenter createPresenter(ANY value) throws FactoryException;
}

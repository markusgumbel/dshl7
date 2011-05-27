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
 * Portions created by Initial Developer are Copyright (C) 2002-2006
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.util;

import java.util.Properties;
	
/** Configuration properties to control any action subsequent to parsing.
		This is the better alternative to System properties because the configuration may be different in different execution contexts. 
		But the default of the properties are still the System properties, so just use this instead of System properties.
*/
public class ThreadProperties {
	private static ThreadLocal<Properties> _threadLocalProperties = new ThreadLocal();

	public static Properties getProperties() {
		Properties properties = _threadLocalProperties.get();
		if(properties == null) {
			properties = new Properties(System.getProperties());
			_threadLocalProperties.set(properties);
		}
		return properties;
	}

	public static void setProperties(Properties properties) {
		_threadLocalProperties.set(properties);
	}

	public static String getProperty(String propertyName, String defaultValue) {
		return getProperties().getProperty(propertyName, defaultValue);
	}
	
	public static String getProperty(String propertyName) {
		return getProperties().getProperty(propertyName);
	}
	
	public static void setProperty(String propertyName, String propertyValue) {
		getProperties().setProperty(propertyName, propertyValue);
	}
}

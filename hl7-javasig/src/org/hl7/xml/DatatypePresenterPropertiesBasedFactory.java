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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hl7.types.ANY;
import org.hl7.util.DatatypeAnalyzer;
import org.hl7.util.FactoryException;

public class DatatypePresenterPropertiesBasedFactory extends DatatypePresenterFactory {
	// -------------------------------------------------------------------------
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml");
	// -------------------------------------------------------------------------
	private Properties props_ = new Properties();
	private HashMap<String, DatatypePresenter> presenterCache_ = new HashMap<String, DatatypePresenter>();

	// -------------------------------------------------------------------------
	public DatatypePresenterPropertiesBasedFactory() throws FactoryException {
		InputStream fi = null;
		try {
			fi = Thread.currentThread().getContextClassLoader().getResourceAsStream("type-map.properties");
			props_.load(fi);
		} catch (IOException ex) {
			throw new FactoryException("Factory " + "DatatypePresenterPropertiesBasedFactory cannot be created", ex);
		} finally {
			if (fi != null)
				try {
					fi.close();
				} catch (IOException ignore) {}
		}
	}

	// -------------------------------------------------------------------------
	private String getClassName(String name) throws FactoryException {
		String className = (String) props_.get(name);
		if (className != null)
			return className;
		// first try to chop of parameters
		int parameterStart = name.indexOf("<");
		if (parameterStart < 0)
			parameterStart = name.indexOf("_");
		if (parameterStart >= 0) {
			className = (String) props_.get(name.substring(0, parameterStart));
		}
		if (className != null) {
			return className;
		} else {
			// now give up
			throw new FactoryException("Handler for " + name + " cannot be found");
		}
	}

	// -------------------------------------------------------------------------
	public DatatypePresenter createPresenter(String name) throws FactoryException {
		DatatypePresenter presenter;
		synchronized (presenterCache_) {
			presenter = presenterCache_.get(name);
			if (presenter == null) {
				String className = getClassName(name);
				try {
					Class clazz = Class.forName(className);
					Method method = clazz.getDeclaredMethod("instance", (Class[]) null);
					presenter = (DatatypePresenter) method.invoke(null, (Object[]) null);
				} catch (ClassNotFoundException ex) {
					String message = "Data type presenter " + name + " cannot be created";
					LOGGER.log(Level.WARNING, message, ex);
					throw new FactoryException(message, ex);
				} catch (NoSuchMethodException ex) {
					String message = "Data type presenter " + name + " cannot be created";
					LOGGER.log(Level.WARNING, message, ex);
					throw new FactoryException(message, ex);
				} catch (IllegalAccessException ex) {
					String message = "Data type presenter " + name + " cannot be created";
					LOGGER.log(Level.WARNING, message, ex);
					throw new FactoryException(message, ex);
				} catch (InvocationTargetException ex) {
					String message = "Data type presenter " + name + " cannot be created";
					LOGGER.log(Level.WARNING, message, ex);
					throw new FactoryException(message, ex);
				}
				presenterCache_.put(name, presenter);
			}
		}
		return presenter;
	}

	public DatatypePresenter createPresenter(ANY value) throws FactoryException {
		try {
			String typeSpec = DatatypeAnalyzer.getTypeBase(value);
			// LOGGER.log(Level.INFO, "value " + value.toString() + " has type " + typeSpec);
			return createPresenter(typeSpec);
		} catch (DatatypeAnalyzer.AnalysisException ex) {
			throw new FactoryException(ex);
		}
	}
}

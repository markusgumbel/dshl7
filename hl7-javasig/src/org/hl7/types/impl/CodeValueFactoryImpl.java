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
package org.hl7.types.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.SAXParserFactory;

import org.hl7.types.CV;
import org.hl7.types.CodeValueFactory;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * A factory for CV value objects that can be configured to use different implementation classes for difference
 * codeSystems. Configuration is done in an XML file of the following example:
 * 
 * <pre>
 *  &lt;codeValueFactory&gt;
 *  &lt;codeSystem codeSystem=&quot;1.2.3.4.5&quot; class=&quot;org.hl7.types.impl.CVUMLSimpl&quot; cache=&quot;true&quot;&gt;
 *  &lt;parameter name=&quot;SAB&quot; value=&quot;XYZ&quot;/&gt;
 *  &lt;parameter name=&quot;jndi-database id=&quot;...&quot;/&gt;
 *  &lt;parameter name=&quot;jdbc-driver&quot; value=&quot;com.oracle.driver.OracleDriver&quot;/&gt;
 *  &lt;parameter name=&quot;jdbc-url&quot; value=&quot;jdbc:oracle:thin:usename/password@umlsdb.here.net:1521:umls&quot;/&gt;
 *  &lt;parameter name=&quot;hibernate-database&quot; value=&quot;true|false&quot;/&gt;
 *  &lt;parameter name=&quot;table-prefix&quot; value=&quot;UMLS.&quot;/&gt;
 *  &lt;/codeSystem&gt; 
 *  &lt;codeSystem codeSystem=&quot;&amp;ucum&quot; class=&quot;org.regenstrief.ucum.Unit&quot; cache=&quot;false&quot;/&gt;
 *  &lt;codeSystem codeSystem=&quot;&amp;loinc&quot; class=&quot;org.hl7.types.impl.CVLOINCimpl&quot; cache=&quot;true&quot;&gt;
 *  &lt;parameter name=&quot;SAB&quot; value=&quot;XYZ&quot;/&gt;
 *  &lt;parameter name=&quot;jndi-database id=&quot;...&quot;/&gt;
 *  &lt;parameter name=&quot;jdbc-driver&quot; value=&quot;com.oracle.driver.OracleDriver&quot;/&gt;
 *  &lt;parameter name=&quot;jdbc-url&quot; value=&quot;jdbc:oracle:thin:usename/password@umlsdb.here.net:1521:umls&quot;/&gt;
 *  &lt;parameter name=&quot;hibernate-database&quot; value=&quot;true|false&quot;/&gt;
 *  &lt;parameter name=&quot;table-prefix&quot; value=&quot;LOINC.&quot;/&gt;
 *  &lt;/codeSystem&gt;
 *  &lt;/codeValueFactory&gt;
 * </pre>
 * 
 * <p>
 * A CV implementation can be parameterized (for instance, in order to bind an SAB code to a code system when the CV is
 * instantiated). To be able to accept parameters, the implementation should expose a static factory method valueOf with
 * the first argument being a java.util.Properties object.
 * </p>
 * 
 * <p>
 * In the future, this factory will also use a cache such that frequently needed code values would be locally fetched
 * without accessing the external resource
 * </p>
 */
public class CodeValueFactoryImpl extends CodeValueFactory {
	@Override
    public CV valueOf(final ST code, final UID codeSystemUID, final ST codeSystemVersion, final ST displayName, final ST originalText) {
		final CodeSystem codeSystem = _map.get(codeSystemUID);
		if (codeSystem != null) {
			return codeSystem.valueOf(code, codeSystemVersion, displayName, originalText);
		} else {
            throw new CodeValueFactory.UnknownCodeSystemException("unknown code system: " + codeSystemUID.toString());
        }
	}

	@Override
    public CV valueOf(final String code, final String codeSystem) {
		return valueOf(ValueFactory.getInstance().STvalueOfLiteral(code), ValueFactory.getInstance().UIDvalueOfLiteral(
				codeSystem), STnull.NI, STnull.NI, STnull.NI);
	}

	public static class CodeSystem {
		UID _codeSystem = UIDnull.NI;
		Class _class = null;
		Method _staticFactoryMethod = null;
		boolean _acceptsParameters = false;
		int _staticFactoryMethodType = 0;
		ST _codeSystemName = STnull.NI;
		Properties _parameters = null;

		void setParameter(final String name, final String value) {
			if (_acceptsParameters) {
				if (_parameters == null) {
                    _parameters = new Properties();
                }
				_parameters.setProperty(name, value);
			} else {
                throw new CodeValueFactory.ConfigurationException("code value implementation " + _class
						+ " does not accept parameters");
            }
		}

		CodeSystem(final UID codeSystem, final Class clazz) {
			_class = clazz;
			_codeSystem = codeSystem;
			_staticFactoryMethod = null;
			try {
				_staticFactoryMethod = _class.getMethod("valueOf", Properties.class, ST.class, UID.class, ST.class, ST.class,
						ST.class, ST.class);
				_acceptsParameters = true;
				_staticFactoryMethodType = 1;
			} catch (final NoSuchMethodException x) {}
			if (_staticFactoryMethod == null || ((_staticFactoryMethod.getModifiers() & Modifier.STATIC) == 0)) {
				_staticFactoryMethod = tryMethod(_class, "valueOf", ST.class, UID.class, ST.class, ST.class, ST.class, ST.class);
				_staticFactoryMethodType = 2;
			}
			if (_staticFactoryMethod == null || ((_staticFactoryMethod.getModifiers() & Modifier.STATIC) == 0)) {
				_staticFactoryMethod = tryMethod(_class, "valueOf", ST.class, UID.class);
				_staticFactoryMethodType = 3;
			}
			if (_staticFactoryMethod == null || ((_staticFactoryMethod.getModifiers() & Modifier.STATIC) == 0)) {
				_staticFactoryMethod = tryMethod(_class, "valueOf", ST.class);
				_staticFactoryMethodType = 4;
			}
			if (_staticFactoryMethod == null || ((_staticFactoryMethod.getModifiers() & Modifier.STATIC) == 0)) {
                throw new CodeValueFactory.ConfigurationException("no suitable static factory method found");
            }
		}

		private static Method tryMethod(final Class clazz, final String name, final Class... formalArgumentTypes) {
			try {
				return clazz.getMethod(name, formalArgumentTypes);
			} catch (final NoSuchMethodException x) {
				return null;
			}
		}

		public UID codeSystem() {
			return _codeSystem;
		}

		public ST codeSystemName() {
			return _codeSystemName;
		}

		public CV valueOf(final ST code, final ST codeSystemVersion, final ST displayName, final ST originalText) {
			try {
				switch (_staticFactoryMethodType) {
				case 1:
					return (CV) _staticFactoryMethod.invoke(null, _parameters, code, _codeSystem, _codeSystemName,
							codeSystemVersion, displayName, originalText);
				case 2:
					return (CV) _staticFactoryMethod.invoke(null, code, _codeSystem, _codeSystemName, codeSystemVersion,
							displayName, originalText);
				case 3:
					return (CV) _staticFactoryMethod.invoke(null, code, _codeSystem);
				case 4:
					return (CV) _staticFactoryMethod.invoke(null, code);
				default: // this case should not occur!
					throw new Error("programming error, please report stackdump");
				}
			} catch (final IllegalAccessException x) {
				throw new CodeValueFactory.ConfigurationException("cannot call method: " + _staticFactoryMethod.toString());
			} catch (final InvocationTargetException x) {
				if (x.getCause() instanceof CodeValueFactory.Exception) {
                    throw (CodeValueFactory.Exception) x.getCause();
                } else {
                    throw new CodeValueFactory.DelegateException("error when calling method: " + _staticFactoryMethod.toString(),
							x.getCause());
                }
			}
		}

		public CV valueOf(final String code) {
			return valueOf(ValueFactory.getInstance().STvalueOf(code), STnull.NI, STnull.NI, STnull.NI);
		}
	}

	public static class CodeSystemMap implements ContentHandler {
		private Map<UID, CodeSystem> _map;

		public CodeSystem get(final UID codeSystem) {
			return _map.get(codeSystem);
		}

		public CodeSystem get(final String codeSystem) {
			return _map.get(ValueFactory.getInstance().UIDvalueOfLiteral(codeSystem));
		}

		/* ContentHandler interface */
		CodeSystem _currentCodeSystem = null;

		public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) throws SAXException {
			if (localName == "codeSystemMap") {
				_map = new HashMap();
			} else if (localName == "codeSystem") {
				if (_currentCodeSystem != null) {
                    throw new SAXException("schema error, codeSystem element cannot nest");
                }
				final String codeSystemString = atts.getValue("codeSystem");
				final String className = atts.getValue("class");
				if (codeSystemString != null && className != null) {
					try {
						_currentCodeSystem = new CodeSystem(ValueFactory.getInstance().UIDvalueOfLiteral(codeSystemString), Class
								.forName(className));
						final String codeSystemNameString = atts.getValue("codeSystemName");
						if (codeSystemNameString != null) {
                            _currentCodeSystem._codeSystemName = ValueFactory.getInstance().STvalueOfLiteral(codeSystemNameString);
                        }
					} catch (final ClassNotFoundException x) {
						throw new SAXException("class " + className + " not found for code system " + codeSystemString);
					} catch (final CodeValueFactory.Exception x) {
						throw new SAXException("exception when creating code system " + codeSystemString, x);
					}
				}
			} else if (localName == "parameter") {
				if (_currentCodeSystem == null) {
                    throw new SAXException("schema error, parameter element must be inside codeSystem element");
                }
				try {
					_currentCodeSystem.setParameter(atts.getValue("name"), atts.getValue("value"));
				} catch (final CodeValueFactory.Exception x) {
					throw new SAXException("exception when creating code system " + _currentCodeSystem.codeSystem(), x);
				}
			} else {
				throw new SAXException("schema error, illegal element " + localName);
			}
		}

		public void endElement(final String namespaceURI, final String localName, final String qName) {
			if (localName == "codeSystem") {
				_map.put(_currentCodeSystem.codeSystem(), _currentCodeSystem);
				_currentCodeSystem = null;
			}
		}

		public void characters(final char[] ch, final int start, final int length) {}

		public void startDocument() {}

		public void endDocument() {}

		public void startPrefixMapping(final String prefix, final String uri) {}

		public void endPrefixMapping(final String prefix) {}

		public void ignorableWhitespace(final char[] ch, final int start, final int length) {}

		public void processingInstruction(final String target, final String data) {}

		public void setDocumentLocator(final org.xml.sax.Locator locator) {}

		public void skippedEntity(final String name) {}
	}

	CodeSystemMap _map = new CodeSystemMap();
	/** The field holding the singleton instance. */
	private static CodeValueFactory _instance = null;

	public static CodeValueFactory getInstance() {
		if (_instance == null) {
			_instance = new CodeValueFactoryImpl();
		}
		return _instance;
	}

	private CodeValueFactoryImpl() {
		java.net.URL url = null;
		try {
			_map = new CodeSystemMap();
			final XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			reader.setFeature("http://xml.org/sax/features/namespaces", true);
			reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
			reader.setContentHandler(_map);
			url = Thread.currentThread().getContextClassLoader().getResource("code-value-factory.xml");
			if (url == null) {
                throw new CodeValueFactory.ConfigurationException("code-value-factory.xml file not found in classpath");
            }
			reader.parse(new InputSource(url.openStream()));
		} catch (final Throwable ex) {
			// If we just use url.toString() when the url is null,
			// then our error handling throws a NullPointerException,
			// and we lose the real error message.
			throw new CodeValueFactory.ConfigurationException((url == null ? "No URL" : url.toString()) + ": " + ex.getMessage(), ex);
		}
	}
}

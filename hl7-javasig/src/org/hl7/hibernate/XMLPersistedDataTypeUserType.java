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

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.hl7.meta.Datatype;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.types.AD;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BIN;
import org.hl7.types.EIVL;
import org.hl7.types.EN;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.PIVL;
import org.hl7.types.SET;
import org.hl7.types.ValueFactory;
import org.hl7.types.ValueFactoryException;
import org.hl7.types.impl.NullFlavorImpl;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;
import org.xml.sax.SAXException;

/** A Hibernate UserType that persists HL7 data type values through their XML serialization.
 This is a generic approach, where one mapper class does it all. It uses the ParameterizedType
 interface of hibernate

 <property name="effectiveTime">
 <type class="org.hl7.hibernate.XMLPersistedDataTypeUserType"> 
 <param name="type">QSET&lt;TS></param>
 </type>
 </property> 

 @author Gunther Schadow
 @version $Id: XMLPersistedDataTypeUserType.java 6473 2007-06-20 17:55:23Z crosenthal $
 */
public class XMLPersistedDataTypeUserType extends AbstractDataTypeUserType implements ParameterizedType, UserType {

	private Datatype _datatype = null;
	private Class _interface = null;
	private final String DEFAULT_TAG = "a";

	/** Gets called by Hibernate to pass the configured type parameters to the implementation. */
	public void setParameterValues(Properties parameters) {
		String typeSpec = (String)parameters.get("type");
		try {      
			_datatype = DatatypeMetadataFactoryImpl.instance().create(typeSpec);
			if (_datatype instanceof ParametrizedDatatype)
				_interface = Class.forName("org.hl7.types." + ((ParametrizedDatatype)_datatype).getType());
			else
				_interface = Class.forName("org.hl7.types." + _datatype.getFullName());
		} catch(ClassNotFoundException x) {
			throw new Error("data type interface not found", x);
		} catch(UnknownDatatypeException x) {
			throw new Error(x);
		}
	}

	/** The class returned by nullSafeGet(). */
	public Class returnedClass() {
		return _interface;
	}

	/** Return the SQL type codes for the columns mapped by this type. */
	public int[] sqlTypes() { return SQL_TYPES; }

	private static final int[] SQL_TYPES = new int[] { Hibernate.TEXT.sqlType() };

	/** Retrieve an instance of the mapped class from a JDBC resultset. */
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException {
		try {

			// Reading from a binary stream would be so much better, however, there is some problem where the
			// string which we store is stored in Latin-1 form, not in UTF-8.
			// InputStream is = rs.getAsciiStream(names[0]);
			// so we have to do this:
			String string = rs.getString(names[0]);
			Reader is = string == null ? null : new StringReader(string);

			if(!rs.wasNull())
				return StandaloneDataTypeContentHandler.parseValue(is, _datatype);
			else if (_datatype instanceof ParametrizedDatatype)
				return ValueFactory.getInstance().nullValueOf(((ParametrizedDatatype)_datatype).getType(),"NI");
			else
				return ValueFactory.getInstance().nullValueOf(_datatype.getFullName(),"NI");

		} catch(SQLException ex) {
			throw new HibernateException(ex);
		} catch(SAXException ex) {
			String offendingText = null;
			try {
				offendingText = rs.getString(names[0]);
			} catch(SQLException why) { }
			throw new HibernateException(ex + ": " + offendingText);
		} catch(ValueFactoryException ex) {
			throw new HibernateException(ex);
		}
	}

	public static final TransformerFactory _transformerFactory = TransformerFactory.newInstance();

	/** Write an instance of the mapped class to a prepared statement. */
	public void nullSafeSet(PreparedStatement st, Object rawValue, int index) throws HibernateException {
		try {
			if(rawValue!=null) {
				final ANY value = (ANY)rawValue;

				final NullFlavor nf = value.nullFlavor();
				if(nf == null || nf.equal(NullFlavorImpl.NI).isFalse()) {

					Transformer transformer = _transformerFactory.newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "no");
					transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

					StringWriter sw = new StringWriter();

					// iff we have a QSET, we must wrap the QSET in an extra root element to keep the XML well formed.  
					// The StandaloneDatatypeContentHandler knows this happens, and it looks for the QSET when it is called

					RimGraphXMLSpeaker.DataValueInputSource is;				

					if (value.isNull().isFalse() && ((value instanceof SET && !(value instanceof IVL || value instanceof PIVL || value instanceof EIVL)) || (value instanceof BAG) || (value instanceof LIST && !(value instanceof EN || value instanceof AD || value instanceof BIN))))
						is = new RimGraphXMLSpeaker.DataValueInputSource(value, DEFAULT_TAG, _datatype, "matt");	
					else
						is = new RimGraphXMLSpeaker.DataValueInputSource(value, DEFAULT_TAG, _datatype);

					transformer.transform(new SAXSource(new RimGraphXMLSpeaker(), is), new StreamResult(sw));

					String string = sw.toString();

					if(string.length() > 0) {
						Hibernate.TEXT.nullSafeSet(st, string, index);
						return;
					}					
				}				
			} 

			// don't ever forget to set this to null explicitly or 
			// else the value from the previous insert might be used!
			Hibernate.TEXT.nullSafeSet(st, null, index);	

		} catch(TransformerConfigurationException ex) {
			throw new HibernateException(ex);
		} catch(TransformerException ex) {
			throw new HibernateException(ex);
		} catch(SQLException ex) {
			throw new HibernateException(ex);
		}
	}
}

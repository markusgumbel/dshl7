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
package org.hl7.xml.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import org.hl7.hibernate.Persistence;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Datatype;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.SimpleDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.meta.mif.MessageTypeLoaderAdapter;
import org.hl7.rim.Matt;
import org.hl7.rim.impl.MattImpl;
import org.hl7.types.BAG;
import org.hl7.types.LIST;
import org.hl7.types.SET;
import org.hl7.types.TS;
import org.hl7.types.ValueFactory;
import org.hl7.util.ApplicationContext;
import org.hl7.util.FactoryException;
import org.hl7.xml.DatatypePresenterBase;
import org.hl7.xml.parser.DynamicContentHandler.ResultReceiver;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * A simple thing that bootstraps the XML parsing process for standalone data type value. This is used when serializing
 * just a data type value without any RIM classes (as in some object/relational persistence mechanisms)
 * 
 * I do this as an extension of DataTypeContentHandler
 */
public class StandaloneDataTypeContentHandler extends DataTypeContentHandler implements DynamicContentHandler,
		ResultReceiver {
	Collection _collection = null;
	Datatype _datatype = null;
	Object _result = null;
	
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml.parser");

	private static String MATT_MIF = "UUDD_MT000000";
	private static CloneClass MATT_CLONE_CLASS = 
		MessageTypeLoaderAdapter.getInstance().loadMessageType(MATT_MIF).getRootClass();
	private Matt MATT_OBJECT = new MattImpl();

	private StandaloneDataTypeContentHandler(Datatype datatype) {
		_datatype = datatype;
	}

	public void notifyResult(Object result) {
		assert _result == null;
		_result = result;
		if (_result instanceof Matt)
			return;
		// XXX: Wouldn't it be nice if we could ask this QSET.class.isAssignableFrom(type)
		// FIXME: instead we have this very, very ugly hack because we can't trust our meta stuff
		if (_result instanceof TS && _datatype.toString().equals("SET<TS>")) {
			// XXX: or should we create an IVL instead with this value being the center?
			_result = ValueFactory.getInstance().QSETvalueOf((TS) _result);
		} 
		else {
			if (this._collection == null)
				this._collection = new ArrayList();
			if (_result == null)
				new Error("WARNING: Result is null!").printStackTrace();
			this._collection.add(_result);
		}
	}

	public Object getResult() throws SAXException {
		if (_collection != null)
			flushCollection();
		return _result;
	}
	
	private static final ApplicationContext CHEAP_FAKE_APPLICATION_CONTEXT = new ApplicationContext() {
		public Object getSetting(String name) {
			return null;
		}

		public <T> T getSetting(String name, T defaultValue) {
			return defaultValue;
		}

		public void setSetting(String name, Object value) {}

		public Persistence getPersistence() {
			throw new Error("this isn't supposed to happen");
		}
	};

	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		// Here we have two cases:
		// 1) e.g. <a xsi:type="CD" code=.../>
		// 2) e.g. <matt><a xsi:type="CD" code=.../><a xsi:type="CD" code=.../></matt>
		// the <matt> tag is being inserted only when needed, i.e., when a collection had been serialized.
		// We do not know the type of the collection, then, i.e., if it's a set, list, or bag.
		// The proper solution would be to have <a xsi:type="SXCM_CD" .../> or LXCM or BXCM to identify
		// with the constraint that all ?XCM in a group must be all S, all B and all L but not mixed.
		// With that not being the case, we currently assume SET (not always right, but consistent with
		// the message element content handler.
		// So, if we have a matt tag, we need to use another StandaloneDataTypeContentHandler to deal
		// with the <a .../> elements. If we have an <a .../> element only we can proceed.
		//
		// We used to have it messed up trying to use only one S.D.T.C.H for both cases. The result
		// of that is that on the matt tag we engage an SXPR presenter, which doesn't do anything
		// if the element is not a PIVL or IVL or other QSET. It could though, so, we could
		//
		// option 1) fix SXPR presenter to deal with any singleton element values (not just with TS)
		//
		// but this is tricky, a big extension of its present duties. Alternatively we would have
		// to detect the DSET matt-tag case before we (incorrectly) hand it off to the SXPRPresenter.
		// one approach is to put an xsi type on the matt tag or use a different tag to check for
		// the difference beforehand.
		//
		// OR 
		//
		// option 2) we either rewrite the logic to be the same as the MessageElementContentHandler
		// in fact, we actually use the M.E.C.H for handling the matt-tag, just that we use a mini-MIF that
		// specified elements named <a...> are expected of type ANY. That would do it. We can do this
		// now simply by making a special CloneClass, which is from the "matt-mif" that has one a-property
		// of type ANY.
		//
		// CURRENTLY WE DO IMPLEMENT OPTION 2!!!
		//
		if (_collection != null)
			flushCollection();
		DynamicContentHandler newContext = null;

		// CATCH the "matt" element first that is added in XMLPersistedDataTypeUserType
		if (localName.equals("matt")) {
			MATT_OBJECT.setA(null);
			newContext = new MessageElementContentHandler(CHEAP_FAKE_APPLICATION_CONTEXT, MATT_OBJECT, MATT_CLONE_CLASS, atts);
		} else {
			/* If we have ANY, we must do a little extra work to make sure the appropriate handler is called. We know we have
			 * an xsi type, so we can use that to determine what the datatype actually is
			 */
			if (_datatype.equals(DatatypeMetadataFactoryDatatypes.instance().ANYTYPE)) {
				try {
					String xsi = atts.getValue(DatatypePresenterBase.W3_XML_SCHEMA, "type");
					_datatype = DatatypeMetadataFactoryImpl.instance().createByXsiType(xsi);
				} catch (UnknownDatatypeException ex) {
					new UnknownDatatypeException(ex.toString());
				}
			}
			
			if (_datatype instanceof SimpleDatatype) {
				try {
					newContext = _datatype.getHandler(namespaceURI, localName, qName, atts);
				} catch (FactoryException ex) {
					newContext = null;
				}
			} else if (_datatype instanceof ParametrizedDatatype) {
				// the ParametrizedDatatype.getHandler() for SET, LIST, and
				// BAG returns the content handler for the parameter type,
				// so that's very nice for us here!
				try {
					newContext = _datatype.getHandler(namespaceURI, localName, qName, atts);
				} catch (FactoryException ex) {
					newContext = null;
				}
			}
		}
		// Now we make the new context the content handler of the XML
		// parser.
		if (newContext != null) {
			this.suspendWith(newContext, atts);
		} else {
			// no special ContentHandler class available, so we will use
			// the TreeContentHandler and then later we will hopefully
			// find a factory method that can use the little DOM to build
			// a value of this kind.
			LOGGER.warning("no content handler for data type " + _datatype.getFullName() + " build tree " + localName);
			DynamicContentHandler tree = new TreeContentHandler(namespaceURI, localName, qName, atts);
			this.suspendWith(tree, null);
		}
	}

	public void flushCollection() throws SAXException {
		if (_datatype.getFullName().contains("LIST") && !(_result instanceof LIST))
			_result = ValueFactory.getInstance().LISTvalueOf(_collection);
		else if (_datatype.getFullName().contains("SET") && !(_result instanceof SET))
			_result = ValueFactory.getInstance().DSETvalueOf(_collection);
		else if (_datatype.getFullName().contains("BAG") && !(_result instanceof BAG))
			_result = ValueFactory.getInstance().BAGvalueOf(_collection);
		else if (_datatype.getFullName().equals("ANY"))
			if (_collection.size() == 1)
				_result = _collection.iterator().next();
			else if (_collection.size() > 1)
				_result = ValueFactory.getInstance().DSETvalueOf(_collection);
			else
				_result = null;

		_collection = null;
	}

	public static Object parseValue(InputStream is, Datatype datatype) throws SAXException {
		return parseValue(new InputSource(is), datatype);
	}

	public static Object parseValue(Reader ir, Datatype datatype) throws SAXException {
		return parseValue(new InputSource(ir), datatype);
	}

	public static Object parseValue(InputSource is, Datatype datatype) throws SAXException {
		try {
			if (datatype == null)
				datatype = DatatypeMetadataFactoryDatatypes.instance().ANYTYPE;
			StandaloneDataTypeContentHandler parser = new StandaloneDataTypeContentHandler(datatype);
			XMLReader reader = XMLReaderFactory.createXMLReader();
			parser.setReader(reader, null);
			reader.parse(is);
			Object result = parser.getResult();
			return result instanceof Matt ? ((Matt) result).getA() : result;
		} catch (IOException ex) {
			throw new Error(ex);
		}
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		// Be a good citizen and return results and release the reader.
		if (_collection != null)
			flushCollection();
		returnResult(_result);
	}
}

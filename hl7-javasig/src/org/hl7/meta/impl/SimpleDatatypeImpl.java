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

package org.hl7.meta.impl;

import java.lang.reflect.Field;
import org.hl7.meta.Datatype;
import org.hl7.meta.JavaIts;
import org.hl7.meta.SimpleDatatype;
import org.hl7.types.AD;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BIN;
import org.hl7.types.EN;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.QTY;
import org.hl7.types.SET;
import org.hl7.util.FactoryException;
import org.hl7.xml.DatatypePresenter;
import org.hl7.xml.DatatypePresenterBase;
import org.hl7.xml.DatatypePresenterFactory;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.parser.DynamicContentHandler;
import org.xml.sax.Attributes;

/**
 * Metadata for a simple datatype (i.e. not container and not generic).
 *
 * @author Skirmantas Kligys
 * @author Eric Chen
 */
public class SimpleDatatypeImpl extends DatatypeImpl
  implements SimpleDatatype
{

  //-------------------------------------------------------------------------
  public SimpleDatatypeImpl(String fullName)
  {
    super(fullName);
    fullName_ = fullName;
  }

  public Datatype diff() {
    if(fullName_.equals("TS")) return new SimpleDatatypeImpl("PQ");
    else if(fullName_.equals("INT")) return this;
    else if(fullName_.equals("REAL")) return this;
    else if(fullName_.equals("MO")) return this;
    else if(fullName_.equals("RTO")) return this;
    else throw new Error("diff() called for " + fullName_);
  }

  public boolean equals(Object otherDatatype) {
    return ((otherDatatype instanceof SimpleDatatype) &&
	    ((SimpleDatatype)otherDatatype).getFullName().equals(fullName_));
  }
    
    /**
     * Provides basic implementation of equals().
     * Descendant classes shall consider overiding this implementation.
     *
     * @return
     */
    public int hashCode()
    {
        /**
         * Implementation Rationale:
         * 1) get class.getName()'s hashCode;
         * 2) get FullName's hashCode;
         */
        int result = getClass().getName().hashCode();
        result = result * 31 + getFullName().hashCode();
        return result;
    }


  //-------------------------------------------------------------------------
  /**
   * Returns the full name of datatype, e.g.
   * LIST&lt;UVP&lt;RTO&lt;INT,PQ&gt;&gt;&gt;.
   *
   * @return  full name of datatype
   */
  public String getFullName()
  {
    return fullName_;
  }

  //-------------------------------------------------------------------------
  /**
   * Datatype XML format attribute names
   * @return  Datatype Presenter constant value
   * @throws FactoryException
   * @throws IllegalAccessException
   */

  public String[] getAttributeNames() throws FactoryException, IllegalAccessException {
    DatatypePresenter presenter =
      DatatypePresenterFactory.getInstance().createPresenter(fullName_);
    Class aClass =  presenter.getClass();
    Field[] fields = aClass.getFields();
    String[] attributeNames = new String[fields.length];
    for (int i = 0; i < fields.length; i++) {
      Object v = fields[i].get(presenter);
      if (v instanceof String) {
	    attributeNames[i]  = (String)v;
      } else {
	    throw new IllegalStateException("All the constant field should be String type. But this field "
			+ fields[i].getName() + "'s value type is " + fields[i].getType());
      }
    }
    return attributeNames;
  }

  public DatatypeBuilder getBuilder(ANY value) throws FactoryException {
    String datatype = (_xsiTypeString == null) ? fullName_ : _xsiTypeString;
    return DatatypePresenterFactory.getInstance().createPresenter(datatype).getBuilder();
  }

  //-------------------------------------------------------------------------
  /**
   * Returns a handler for the data type, uses the configured data type
   * handler factory.
   *
   * @param namespaceURI  namespace of the XML element
   * @param localName  local name of the XML element
   * @param qName  qualified name of the XML element
   * @param atts  attributes of the XML element
   * @return  an instance of a handler
   * @throws FactoryException  if creating a handler fails
   */
  public DynamicContentHandler getHandler(String namespaceURI, String localName, String qName, Attributes atts) throws FactoryException {
    // New: xsi:type support.
    String xsi = null;
    if(atts != null) {
      xsi = atts.getValue(DatatypePresenterBase.W3_XML_SCHEMA, "type");
      if(xsi != null && xsi.startsWith("SXCM_"))
	xsi = xsi.substring(5);
    }
    setXsiTypeString(xsi);
    
    String datatype = (_xsiTypeString == null) ? fullName_ : _xsiTypeString;

    return (DynamicContentHandler)DatatypePresenterFactory.getInstance().
      createPresenter(datatype).
      getContentHandler(namespaceURI, localName, qName, atts, this);
  }

  // FIXME: This is a HACK, do not propagate it into the interface!!!
  // xsi:type name.
  protected String _xsiTypeString;
  // xsi:type name.For example: <value xsi:type="PQ" .. />, PQ will be value to catched
  public String getXsiTypeString() {
    return _xsiTypeString;
  }
  public void setXsiTypeString(String xsiTypeString) {
    _xsiTypeString=xsiTypeString;
  }
  // END OF HACK


  //-------------------------------------------------------------------------
  /**
   * For validation purpose
   * check incoming value is validated against datatype
   * @param value
   * @return  boolean
   * @throws ClassNotFoundException
   */

  public boolean checkDataType(ANY value)  throws ClassNotFoundException {
    final JavaIts javaIts_ = new JavaItsImpl();
    Class hmdJavaClass = javaIts_.getDatatypeInterface(this);

    if (value.isNull().isTrue()) {
      return true;
    }

    // IVL, BAG, LIST, SET with a single element are acceptable here.
    if (value instanceof BAG) {
	BAG bagValue = (BAG) value;

	INT sizeINT = bagValue.size();
	int size = sizeINT.nonNull().isTrue() ? sizeINT.intValue() : 0;	  

	if (size == 0) return true;
	else if (size > 1) return false;

	// No methods in BAG interface to iterate, cast to implementation.
	ANY element = (ANY) ((BAG)bagValue).iterator().next();
	return hmdJavaClass.isAssignableFrom(element.getClass());
    }
    // All these are simple data types that extend LIST.
    // Should not look inside at LIST elements.
    // This also covers the following datatypes that extend/restrict the
    // 3 types metioned below: ED, ST, ON, PN, TN.
    else if (value instanceof AD || value instanceof BIN ||
	     value instanceof EN)
      {
	return hmdJavaClass.isAssignableFrom(value.getClass());
      }
    else if (value instanceof LIST)
      {
	LIST<ANY> listValue = (LIST<ANY>) value;

	INT sizeINT = listValue.length();
	int size = sizeINT.nonNull().isTrue() ? sizeINT.intValue() : 0;	  
	if (size == 0) return true;
	else if (size > 1) return false;

	// size == 1 by now.
	return hmdJavaClass.isAssignableFrom(listValue.head().getClass());
      }
    else if (value instanceof IVL)
      {                               // This is a kluge for one element for IVL
	IVL<QTY> ivlValue = (IVL<QTY>) value;

	// WHAT IS THE POINT OF THIS???? int size = ivlValue.cardinality().intValue();
	// if (size == 0) return true;
        //else if (size > 1) return false;
	
	// size == 1 and only assign the center
	//            QTY element = ivlValue.center();
	QTY element = ivlValue.low();
	return hmdJavaClass.isAssignableFrom(element.getClass());
      }

    else if (value instanceof SET) {
			SET<ANY> setValue = (SET<ANY>) value;
			
			// WHAT IS THAT??? int size = setValue.cardinality().intValue();
			// if (size == 0) return true;
			// else if (size > 1) return false;
			// size == 1 by now.
			
			// FIXME: THIS IS STILL NOT RIGHT!!!!
			
			ANY anyElement = setValue.any();
			if(anyElement == null)
				return hmdJavaClass.isAssignableFrom(anyElement.getClass());
			else 
				return true;
		} else {
			// Scalar value.
			return hmdJavaClass.isAssignableFrom(value.getClass());
		}
  }	
}

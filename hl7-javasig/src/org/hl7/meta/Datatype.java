///* The contents of this file are subject to the Health Level-7 Public
// * License Version 1.0 (the "License"); you may not use this file
// * except in compliance with the License. You may obtain a copy of the
// * License at http://www.hl7.org/HPL/
// *
// * Software distributed under the License is distributed on an "AS IS"
// * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
// * the License for the specific language governing rights and
// * limitations under the License.
// *
// * The Original Code is all this file.
// *
// * The Initial Developer of the Original Code is .
// * Portions created by Initial Developer are Copyright (C) 2002-2004
// * Health Level Seven, Inc. All Rights Reserved.
// *
// * Contributor(s):
// */
package org.hl7.meta;

import org.hl7.types.ANY;
import org.hl7.util.FactoryException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.parser.DynamicContentHandler;
import org.xml.sax.Attributes;

/**
 * Metadata for a datatype.  Base interface for <code>SimpleDatatype</code>,
 * <code>ContainerDatatype</code>, and <code>ParamterizedDatatype</code>
 *
 * @author Skirmantas Kligys
 */
public interface Datatype extends Metadata
{
  //-------------------------------------------------------------------------
  /**
   * Returns the full name of datatype.  For example,
   * <code>LIST&lt;UVP&lt;RTO&lt;MO,PQ&gt;&gt;&gt;</code>,
   *
   * @return  name of datatype
   */
  String getFullName();

  // xsi:type name.For example: <value xsi:type="PQ" .. />, PQ will be value to catched
  //String getXsiTypeString();
  
  //void setXsiTypeString(String xsiTypeString);
  
  /**
   * Returns Datatype Builder.
   */
  DatatypeBuilder getBuilder(ANY value) throws FactoryException;
  
  
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
  DynamicContentHandler getHandler(String namespaceURI, String localName, String qName, Attributes atts) throws FactoryException;

  //-------------------------------------------------------------------------
  /**
   * check incoming value is validated against datatype. If validated, true; unvalidated, false
   * @param value
   * @return  boolean
   * @throws ClassNotFoundException
   */

  boolean checkDataType(ANY value)  throws ClassNotFoundException;

 // should use equals(Object otherDatatype) instead. -Eric
//  boolean equals(Datatype otherDatatype);
  
  boolean isCompleteTypeOf(Datatype genericType);

  // returns the datatype of the diff of this datatype with itself
  Datatype diff();
}

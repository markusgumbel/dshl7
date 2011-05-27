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

import org.hl7.meta.Datatype;
import org.hl7.util.FactoryException;
import org.hl7.xml.parser.DynamicContentHandler;
import org.xml.sax.Attributes;

/**
 * Fake implementation of datatype metadata for now.
 *
 * @author  Jerry Joyce
 */
public abstract class DatatypeImpl implements Datatype
{
  //-------------------------------------------------------------------------
  /** Name of datatype. */
  protected String fullName_;

  //-------------------------------------------------------------------------
  /**
   * Constructs a new datatype metatdatum instance.
   *
   * @param fullName name of datatype
   */
  public DatatypeImpl(String fullName)
  {
    fullName_ = fullName;
  }

  //-------------------------------------------------------------------------
  /**
   * Returns the name of datatype.
   *
   * @return  name of datatype
   */
  public String getFullName()
  {
    return fullName_;
  }

  public boolean isCompleteTypeOf(Datatype genericType) {
    return this.getFullName().startsWith(genericType.getFullName()) && !this.getFullName().equals(genericType.getFullName());
  }
  

  public String toString() {
    return getFullName();
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
  public abstract DynamicContentHandler getHandler(String namespaceURI,
						   String localName, String qName, Attributes atts) throws FactoryException;

}

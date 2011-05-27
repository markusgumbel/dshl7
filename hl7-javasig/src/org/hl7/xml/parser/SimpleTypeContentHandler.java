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

package org.hl7.xml.parser;

import org.hl7.types.ANY;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/** A simple type content handler is a dynamic content handler that
    by default ignores any content nodes (elements or text) and only
    considers attributes unless an known result is known before passing
    xml element.

    <p>The simple type content handler simplifies the call protocol
    for the DynamicContentHandler. The notifyActivation method should
    still be served so that some attributes are actually handled. The
    returnResult method is automatically called upon the endElement
    event finalizing this handler's context.</p>
 */
public class SimpleTypeContentHandler
  extends DataTypeContentHandler
  implements DynamicContentHandler
{
  private ANY _result;

    protected SimpleTypeContentHandler()
    {
    }

    public SimpleTypeContentHandler(ANY _result)
  {
    this._result = _result;
  }

    protected Object getResult()
  {
    return _result;
  }

  public void startElement(String namespaceURI,
                           String localName,
                           String qName,
                           Attributes atts) {
    // don't expect anything here, so, we can ignore it
    suspendWith(new IgnoreContentHandler(), null);
  }

  public void endElement(String namespaceURI,
                         String localName,
                         String qName) throws SAXException {
    returnResult(getResult());
  }
}


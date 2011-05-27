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

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.XMLReader;

/** A SAX ContentHandler that keeps a reference to the XMLReader
    that it is registered with. This provides a framework in which
    ContentHandlers can be swapped and restored dynamically,
    suitable for recursive descent parsing or any hybrid that 
    sometimes needs to switch the content handler to a specialist.

    <p>The contract of this interface is that setReader must keep a
    reference to the content handler that was registered with that
    reader at the time setReader was called and restore this 
    previous reader at an appropriate time.</p>

    <p>The typical call protocol is:</p>
   
    <ol><li>setReader is called upon this handler from somewhere else, 
    from which point on this handler takes over handling of attributes 
    and any subsequent elements. If this handler can be expected to 
    terminate before the XML document ends, then the previous handler
    registered with the XMLReader must be saved so that it can be 
    restored later.</li>

    <li>If the caller is a ResultReceiver or delegates some other 
    object as its ResultReceiver, it calls the new handler's
    setResultReceiver method to register that result receiver.</li>

    <li>if this handler is finished (typically at endElement of the 
    element for which this handler was invoked)
    
      <ol><li>if this handler's activator registered a ResultReceiver, 
      this handler calls the notifyResult method of that ResultReceiver.</li>

      <li>if the XML event stream is not finished, the previous handler
      should be restored with the XMLReader.</li></ol>
    </li></ol>
    
    @author: Gunther Schadow
    @version: $Id: DynamicContentHandler.java 4607 2006-10-18 19:43:42Z crosenthal $
*/
public interface DynamicContentHandler extends ContentHandler {   
  /** Get the current reader. 
      @return the current org.xml.sax.XMLReader or null if this
              is not currently registered with a reader.
   */
  public XMLReader getReader();

  /** Registers this content handler with the given XMLReader and 
      saves a reference to the content handler that was registered
      with that reader up to this point. XML attributes should be 
      passed with the setReader function, so that this new
      ContentHandler can know the attributes in the element that
      it is about to parse.
  */
  public void setReader(XMLReader reader, Attributes atts);

  /** A content handler result receiver is an object that expects a 
      notification once the content handler is done. It's almost like 
      an observer or action listener, but the only action that we make 
      available is when the content handler is done handling content.

      <p>When a content handler is registered with an XMLReader, and 
      the previous content handler is a result receiver, that previous
      content handler will be notified of the result of this content 
      handler.</p>
  */
  public interface ResultReceiver {
    /** Called by a dynamic content handler when it is done parsing.
     */
    void notifyResult(Object result);
  }

  /** Registers a result receiver with the content handler if the
      content handler doesn't have a result receiver yet.

      <p>The result receiver is called when this content handler is done 
      with parsing its stuff. If the previous content handler is by 
      itself a result receiver, it receives the notification.</p>

      <p>Note: this method will fail if this content handler already
      has a result receiver. Since this is a usage error rather than
      a dynamic condition, an error will be thrown, not an exception.</p>
  */
  public void setResultReceiver(ResultReceiver rr);

	/** Get the document locator of this or an earlier dynamic content handler. */
	public Locator getDocumentLocator();
}

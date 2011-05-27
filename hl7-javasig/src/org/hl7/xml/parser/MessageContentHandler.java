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

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hl7.meta.CloneClass;
import org.hl7.meta.MessageType;
import org.hl7.util.ApplicationContext;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * A simple thing that bootstraps the XML parsing process.
 * 
 * 1.) get the XML parser instance 2.) figure out the current HMD (root
 * CloneClass) 3.) start the parsing
 * 
 * I do this as an extension of MessageElementContentHandler
 */
public class MessageContentHandler extends DynamicContentHandlerBase implements DynamicContentHandler, DynamicContentHandler.ResultReceiver
{
    protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml.parser");

    CloneClass _cloneClass;
    Object _result = null; // object graph
    ApplicationContext _applicationContext;

    private MessageContentHandler(ApplicationContext applicationContext, CloneClass cloneClass)
    {
        _cloneClass = cloneClass;
        _applicationContext = applicationContext;
    }

    public void notifyResult(Object result)
    {
        assert _result == null;
        _result = result;
    }

    public Object getResult()
    {
        return _result;
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
    {                
        // Get the correct specialization (given the element's tag) for a 
        // choice/abstract clone class.
        CloneClass cloneClass = _cloneClass.getSpecialization(localName);
        if (cloneClass == null)
        {
            cloneClass = _cloneClass;
        }

        MessageElementContentHandler context = new MessageElementContentHandler(_applicationContext, cloneClass, atts);
        context.setCloneCodeWhichIsABadHackAndShouldBeRemovedAtTheEarliestPossibleTime(localName);
        this.suspendWith(context, atts);
    }

    public static Object parseMessage(ApplicationContext applicationContext, InputStream is, MessageType messageType)
    {
        try
        {
            MessageContentHandler parser = new MessageContentHandler(applicationContext, messageType.getRootClass());
            XMLReader reader = XMLReaderFactory.createXMLReader();
            parser.setReader(reader, null);
            reader.parse(new InputSource(is));

            // and return the thing!
            return parser.getResult();

        }
        catch (IOException ex)
        {
            throw new Error(ex);
        }
        catch (SAXException ex)
        {
            if (ex instanceof SAXParseException) LOGGER.log(Level.SEVERE, "Parsing(" + messageType.getName() + ") error at line " + ((SAXParseException) ex).getLineNumber(), ex.getCause());
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

            throw new Error(ex);
            // give the blank RimGraph to prevent blow out
            // return new RimObjectImpl();
        }
    }

    /** ignore it all */
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException
    {
        /* Be a good citizen and return results and release the reader. */
        returnResult(_result);
    }
}

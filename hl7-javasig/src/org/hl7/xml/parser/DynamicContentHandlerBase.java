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
 * Contributor(s):   Original code by Gunther Schadow, Peter Hendler added method to conduct patient subject to nested observations
 */

package org.hl7.xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.hl7.merger.*;



/**
 * An abstract base class that make implementation of DynamicContentHandlers
 * easier. This handles all the responsibilities with the handler chain (a LIFO
 * stack of handlers that had been invoked in a nested fashion to handle special
 * content.)
 * 
 * <p>
 * A subclass of this only needs to implement the SAX ContentHandler interface
 * and obey the following simple call protocol: an activation hook may be
 * implemented as notifyActivation, which would handle XML attributes of the
 * element that has begun the new handler context. If the handler has a result
 * and if the prior DynamicContentHandler is a ResultReceiver, returnResult must
 * be called with that result. In practice, one should always call returnResult
 * if one has a result, the returnResult method will handle the resultReceiver
 * issue.
 * </p>
 * 
 * <p>
 * The typical call protocol is:
 * </p>
 * 
 * <ol>
 * <li>The setReader method is implemented by this base class and handles all
 * responsibilities of setReader defined by the DynamicContentHandler interface.</li>
 * 
 * <li>If the previous handler itself specializes the DynamicContentHandlerBase
 * class, it probably invoked this handler using the suspendWith method. The
 * suspendWith method also set the ResultReceiver if the caller object
 * implements the ResultReceiver interface.</li>
 * 
 * <li>If this handler is finished (typically at endElement of the element for
 * which this handler was invoked) and it calls its returnResults method with or
 * without arguments. This method handles the ResultReceiver issue and restores
 * the handler to the previous handler.</li>
 * </ol>
 */
public abstract class DynamicContentHandlerBase implements
DynamicContentHandler {
	private XMLReader _reader = null;
	private ContentHandler _previousHandler = null;
	private ResultReceiver _resultReceiver = null;

	// the following static variable is reset to null
	// in endDocument()


	Locator _documentLocator = null;

	public XMLReader getReader() {
		return _reader;
	};

	public ContentHandler getPreviousHandler() {
		return this._previousHandler;
	}

	public ResultReceiver getResultReceiver() {
		return _resultReceiver;
	};

	/**
	 * Called by setReader to forward the attributes to the new content handler
	 * specializt. This is a hook one can use to only handle attributes and
	 * still leave the handling of the setReader responsibilities to this base
	 * class.
	 */
	protected void notifyActivation(Attributes atts) {
	}

	public void setReader(XMLReader reader, Attributes atts) {

		// setReader() is called by suspendWith(), which could have been called
		// multiple times at least in several functions defined in
		// MessageElementContentHandler class;
		// Given so, I commented out following thrown Error code.
		// Alternatively, we could consider re-factorize this part to:
		// check if the given atts is null and act based on the results;
		// or, have another function to explicitly clear reader variable and its
		// associated member variables;
		// if(this._reader != null)
		// throw new Error("cannot sign on to two readers!");

		this._reader = reader;
		this._previousHandler = reader.getContentHandler();

		if(this._previousHandler instanceof ResultReceiver) {
			setResultReceiver((ResultReceiver) this._previousHandler);
		}

		this._reader.setContentHandler(this);
		this.notifyActivation(atts);
	}

	protected void suspendWith(DynamicContentHandler that, Attributes atts) {
		that.setReader(this._reader, atts);
	}

	public void setResultReceiver(ResultReceiver resultReceiver) {
		if(_resultReceiver != null && _resultReceiver != resultReceiver)
			throw new Error("cannot register two result receivers");
		else
			this._resultReceiver = resultReceiver;
	}
	


/*	static final boolean CONTEXT_CONDUCTION_ENABLED;
	static {
		String enabledText = System.getProperty("org.hl7.merger.ContextConductionMerger.enabled");
		CONTEXT_CONDUCTION_ENABLED = enabledText != null && Boolean.parseBoolean(enabledText);
		
	}*/

	protected void returnResult(Object result) throws SAXException {
		
/*		if(CONTEXT_CONDUCTION_ENABLED){
		//	org.hl7.merger.ContextConductionMerger ccm = new org.hl7.merger.ContextConductionMerger();
		//	ccm.conductSubject(result);	
		}*/
		
		if(this._resultReceiver != null) {
			this._resultReceiver.notifyResult(result);
		}
		releaseReader();
	}// end of method

	protected void returnResult() throws SAXException {
		returnResult(null);
	}

	private void releaseReader() {
		this._reader.setContentHandler(this._previousHandler);
		this._reader = null;
		this._previousHandler = null;
		this._resultReceiver = null;
	}

	public void setDocumentLocator(Locator locator) {
		_documentLocator = locator;
	}

	public Locator getDocumentLocator() {
		if(_documentLocator == null) {
			ContentHandler previousHandler = getPreviousHandler();
			if(previousHandler != null
				 && previousHandler instanceof DynamicContentHandler)
				_documentLocator = ((DynamicContentHandler) previousHandler)
					.getDocumentLocator();
		}
		return _documentLocator;
	}

	/**
	 * An easy way of adding location information to error or log messages.
	 * 
	 * @param message
	 *            the message text
	 * @return the message text with location information added to it
	 */
	protected String addLoc(String message) {
		Locator loc = getDocumentLocator();
		String sysId = loc.getSystemId();
		String pubId = loc.getPublicId();
		return message + " at " + (pubId != null ? pubId + ":" : "")
			+ (sysId != null ? sysId + ":" : "") + loc.getLineNumber()
			+ ":" + loc.getColumnNumber();
	}

	/** ignore it all */
	public void characters(char[] ch, int start, int length) {
	}

	public void ignorableWhitespace(char[] ch, int start, int length) {
	}

	public void startDocument() {
	}

	// better set this to null at the end of the document
	public void endDocument() {
		//ContextConductionMerger.endMessage();

	}

	public void processingInstruction(String target, String data) {
	}

	public void startPrefixMapping(String prefix, String uri) {
	}

	public void endPrefixMapping(String prefix) {
	}

	public void skippedEntity(String name) {
	}
}

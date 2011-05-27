package org.hl7.xml;

import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.TreeContentHandler;

/**
 * Created by IntelliJ IDEA. User: Eric Chen Date: Oct 20, 2004 Time: 6:43:16 PM To change this template use File |
 * Settings | File Templates.
 */
public interface ElementBuilder {
	// .......................................................................
	void build(RimGraphXMLSpeaker.ContentBuilder builder, TreeContentHandler.Element element) throws BuilderException;
}

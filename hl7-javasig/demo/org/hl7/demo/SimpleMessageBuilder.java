/**
 * The Simplest example of serializing a RIM graph
 * out to an xml message
 * @author Peter Hendler 9/22/2007
 */

package org.hl7.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.hl7.meta.MessageType;
import org.hl7.meta.MessageTypeLoader;
import org.hl7.rim.RimObject;
import org.hl7.xml.builder.RimGraphXMLSpeaker;

public class SimpleMessageBuilder {
	SimpleMessageBuilder(){}
	
	public void BuildMessageFromGraph(Object graph, String messagetypestr){
		MessageTypeLoader<MessageType> mtl =org.hl7.meta.mif.MessageTypeLoaderAdapter.getInstance();
		MessageType messageType = mtl.loadMessageType(messagetypestr);
		try{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "US-ASCII");

		RimGraphXMLSpeaker speaker = new RimGraphXMLSpeaker();
		Source source = new SAXSource(speaker, new RimGraphXMLSpeaker.InputSource((RimObject) graph, messageType
				.getRootClass()));
		StringWriter sw = new StringWriter();

		transformer.transform(source, new StreamResult(sw));
		PrintStream p;
		

		p = new PrintStream(System.out);
		System.out.println("Here is the message----------------------");
		p.println(sw);
		p.close();
		}catch(Exception e){System.out.println("Error building message " + messagetypestr);}
		
	}
	
	public static void main(String[] args) {
		SimpleLoadMessage slm = new SimpleLoadMessage();
		Object rim = slm.LoadMessage("POCD_HD000040", "etc/makeCDA.xml");
		System.out.println("Message loaded " + rim.toString());
		System.out.println("Now to build message");
		SimpleMessageBuilder smb = new SimpleMessageBuilder();
		smb.BuildMessageFromGraph(rim, "POCD_HD000040");
	}
	

}

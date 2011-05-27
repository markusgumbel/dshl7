package org.hl7.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.hl7.meta.Datatype;
import org.hl7.meta.impl.SimpleDatatypeImpl;
import org.hl7.types.CS;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;

/**
 * CREATED BY: Eric Chen  CREATION DATE:  Apr 5, 2005
 * UPDATED BY:            UPDATE DATE:
 * Purpose:
 *   
 */
public class CSTest extends XMLTestCase
{
	public static final String HL7_URI = "urn:hl7-org:v3";
	private static Transformer transformer;
	private static Datatype datatype = new SimpleDatatypeImpl("CS");

	static
	{
		try
		{
			final TransformerFactory _transformerFactory = TransformerFactory.newInstance();
			transformer = _transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		}
		catch (Exception e)
		{
		}
	}
	/* need Structural attribute test case code
    public void testCS_01ValidLengthNormalCase() throws Exception
    {
        final String input = "<ObservationEvent classCode=\"ASSIGNED\"  xmlns=\""+HL7_URI+"\"/>";
        final String output = "<ObservationEvent classCode=\"ASSIGNED\" xmlns=\""+HL7_URI+"\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "ObservationEvent"));
    }
    
    public void testCS_02ValidLengthLowBoundary() throws Exception
    {
        final String input = "<ObservationEvent classCode=\"A\" xmlns=\""+HL7_URI+"\"/>";
        final String output = "<ObservationEvent classCode=\"A\" xmlns=\""+HL7_URI+"\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "ObservationEvent"));
    }
    
    public void testCS_03ValidLengthLowOutOfBoundary() throws Exception
    {
        final String input = "<ObservationEvent classCode=\"\" xmlns=\""+HL7_URI+"\"/>";
        final String output = "<ObservationEvent classCode=\"\" xmlns=\""+HL7_URI+"\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "ObservationEvent"));
    } 
    
	public void testCS_04ValidFormat() throws Exception
    {
        final String input = "<ObservationEvent xmlns=\"urn:hl7-org:v3\" classCode=\"ACTIVE\"/>";
        final String output = "<ObservationEvent classCode=\"ASSIGNED\" xmlns=\"urn:hl7-org:v3\" />";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "ObservationEvent"));
    }
	
	public void testCS_05InvalidFormat() throws Exception
    {
        final String input = "<ObservationEvent xmlns=\"urn:hl7-org:v3\" classCode=\"ABC ABC\" />";
        final String output = "<ObservationEvent classCode=\"ABC ABC\" xmlns=\"urn:hl7-org:v3\" />";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "ObservationEvent"));
    }
	*/
	// non-structural
	public void testCS_06RequiredComplete() throws Exception
	{
//        final String input = "<statusCode xmlns=\""+HL7_URI+"\" code=\"active\" codeSystem=\"1.2.3.4\"/>";
//		final String input = "<statusCode xmlns=\""+HL7_URI+"\" codeSystemName=\"String\" codeSystem=\"Text\" codeSystemVersion=\"String\" code=\"active\"/>";
		final String input = "<statusCode xmlns=\""+HL7_URI+"\" codeSystemName=\"String\" codeSystem=\"Text\" codeSystemVersion=\"String\" code=\"active\"/>";
		final String output = "<statusCode xmlns=\""+HL7_URI+"\" code=\"active\"/>";
//        final String input = "<statusCode xmlns=\""+HL7_URI+"\" xsi:type=\"CS\" code=\"ACTIVE\" codeSystem=\"1.2.3.4\"/>";
//		final String output = "<statusCode xmlns=\""+HL7_URI+"\" code=\"ACTIVE\" codeSystem=\"1.2.3.4\"/>";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		String fromXML = getXMLForValue(value, "statusCode");
		DetailedDiff detailedDiff = CommonTestUtils.getInstance().doDiff(output, fromXML, new ElementNameAndAttributeQualifier());
		assertEquals("testCS_06RequiredComplete ", 0, detailedDiff.getAllDifferences().size());
//		assertXMLEqual(output, fromXML);
	}

	//Failure case
	public void testCS_07RequiredMissing() throws Exception
	{
		final String input = "<statusCode xmlns=\""+HL7_URI+"\" codeSystem=\"1.2.3.4\"/>";
		final String output = "<statusCode xmlns=\""+HL7_URI+"\" code=\"ACTIVE\" codeSystem=\"1.2.3.4\"/>";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		assertXMLNotEqual(output, getXMLForValue(value, "statusCode"));
	}

	public void testCS_08RequiredNull() throws Exception
	{
//		final String input = "<statusCode xmlns=\""+HL7_URI+"\" code=\"\" codeSystem=\"1.2.3.4\"/>";
//		final String output = "<statusCode xmlns=\""+HL7_URI+"\" code=\"\" codeSystem=\"1.2.3.4\"/>";
		final String input = "<statusCode xmlns=\"" + HL7_URI + "\" codeSystemName=\"String\" codeSystem=\"Text\" codeSystemVersion=\"String\" code=\"\"/>";
		final String output = "<statusCode xmlns=\"" + HL7_URI + "\" code=\"\"/>";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		CS value = (CS) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		String fromXML = getXMLForValue(value, "statusCode");
		DetailedDiff detailedDiff = CommonTestUtils.getInstance().doDiff(output, fromXML, new ElementNameAndAttributeQualifier());
		assertEquals("testCS_08RequiredNull ", 0, detailedDiff.getAllDifferences().size());
		//		assertXMLEqual(output, getXMLForValue(value, "statusCode"));
	}

	private String getXMLForValue(CS value, String localName) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException
	{
		StringWriter sw = new StringWriter();
		transformer.transform(new SAXSource(new RimGraphXMLSpeaker(), new RimGraphXMLSpeaker.DataValueInputSource(value, localName, datatype)), new StreamResult(sw));
		return sw.toString();
	}


}


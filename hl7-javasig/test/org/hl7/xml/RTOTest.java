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
import org.custommonkey.xmlunit.ElementNameAndTextQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.hl7.meta.Datatype;
import org.hl7.meta.impl.ParametrizedDatatypeImpl;
import org.hl7.meta.impl.SimpleDatatypeImpl;
import org.hl7.types.ANY;
import org.hl7.types.RTO;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;

/**
 * This class defines unit test for RTO.
 *
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: crosenthal $
 * @version Since HL7 SDK v1.2
 *          revision    $Revision: 5652 $
 *          date        $Date: 2007-03-30 11:35:44 -0400 (Fri, 30 Mar 2007) $
 */
public class RTOTest extends XMLTestCase
{
	/**
	 * Logging constant used to identify source of log entry, that could be later used to create
	 * logging mechanism to uniquely identify the logged class.
	 */
	private static final String LOGID = "$RCSfile$";

	/**
	 * String that identifies the class version and solves the serial version UID problem.
	 * This String is for informational purposes only and MUST not be made final.
	 *
	 * @see <a href="http://www.visi.com/~gyles19/cgi-bin/fom.cgi?file=63">JBuilder vice javac serial version UID</a>
	 */
	public static String RCSID = "$Header$";

	public static final String HL7_URI = "urn:hl7-org:v3";
	private static Transformer transformer;
	private static Datatype datatype = new ParametrizedDatatypeImpl("RTO", new SimpleDatatypeImpl("INT"), new SimpleDatatypeImpl("INT"));
//	private static Datatype datatype = new SimpleDatatypeImpl("RTO");

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

	public void testRTO_01_TypeInfoInParentAndChidrenTag() throws Exception
	{
		//<originalText mediaType = "text/plain" representation = "TXT" > O </originalText >
		final String input = "<quantity xmlns=\"" + HL7_URI + "\" type=\"RTO\">" + "<numerator type=\"INT\" value=\"1\"/><denominator type=\"INT\" value=\"2\"/></quantity>";
		final String output = "<quantity xmlns=\"" + HL7_URI + "\">" + "<numerator value=\"1\"/><denominator value=\"2\"/></quantity>";
//		final String output = "<quantity xmlns:uri=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"" + HL7_URI + "\" type=\"RTO\">" + "<numerator type=\"INT\" value=\"1\"/><denominator type=\"INT\" value=\"2\"/></quantity>";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		RTO value = (RTO) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		String fromXML = getXMLForValue(value, "quantity");
		DetailedDiff detailedDiff = CommonTestUtils.getInstance().doDiff(output, fromXML, new ElementNameAndTextQualifier());
		assertEquals("testDifferences ", 0, detailedDiff.getAllDifferences().size());
	}

	public void testRTO_02_TypeInfoInParentTag() throws Exception
	{
		//<originalText mediaType = "text/plain" representation = "TXT" > O </originalText >
		final String input = "<quantity xmlns=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" type=\"RTO_INT_INT\"><numerator value=\"1\"/><denominator value=\"2\"/></quantity>";
		final String output = "<quantity xmlns=\"" + HL7_URI + "\">" + "<numerator value=\"1\"/><denominator value=\"2\"/></quantity>";
//		final String output = "<quantity xmlns:uri=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"" + HL7_URI + "\" type=\"RTO\">" + "<numerator type=\"INT\" value=\"1\"/><denominator type=\"INT\" value=\"2\"/></quantity>";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		RTO value = (RTO) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		String fromXML = getXMLForValue(value, "quantity");
		DetailedDiff detailedDiff = CommonTestUtils.getInstance().doDiff(output, fromXML, new ElementNameAndTextQualifier());
		assertEquals("testDifferences ", 0, detailedDiff.getAllDifferences().size());
	}

	private String getXMLForValue(ANY value, String localName) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException
	{
		StringWriter sw = new StringWriter();
		transformer.transform(new SAXSource(new RimGraphXMLSpeaker(), new RimGraphXMLSpeaker.DataValueInputSource(value, localName, datatype)), new StreamResult(sw));
		return sw.toString();
	}


}
/**
 * HISTORY      : $Log$
 * HISTORY      : Revision 1.1  2006/05/31 12:49:30  sjiang
 * HISTORY      : More Test Cases
 * HISTORY      :
 */

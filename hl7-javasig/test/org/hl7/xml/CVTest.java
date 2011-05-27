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
import org.hl7.meta.impl.SimpleDatatypeImpl;
import org.hl7.types.ANY;
import org.hl7.types.CV;
import org.hl7.types.ValueFactory;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;

/**
 * This class defines unit test for CV.
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: crosenthal $
 * @version Since HL7 SDK v1.2 revision $Revision: 5671 $ date $Date: 2007-04-03 16:01:45 -0400 (Tue, 03 Apr 2007) $
 */
public class CVTest extends XMLTestCase {
	/**
	 * Logging constant used to identify source of log entry, that could be later used to create logging mechanism to
	 * uniquely identify the logged class.
	 */
	private static final String LOGID = "$RCSfile$";
	/**
	 * String that identifies the class version and solves the serial version UID problem. This String is for
	 * informational purposes only and MUST not be made final.
	 * 
	 * @see <a href="http://www.visi.com/~gyles19/cgi-bin/fom.cgi?file=63">JBuilder vice javac serial version UID</a>
	 */
	public static String RCSID = "$Header$";
	public static final String HL7_URI = "urn:hl7-org:v3";
	private static Transformer transformer;
	private static final ValueFactory VFACTORY = ValueFactory.getInstance();
	private static Datatype datatype = new SimpleDatatypeImpl("CV");
	static {
		try {
			final TransformerFactory _transformerFactory = TransformerFactory.newInstance();
			transformer = _transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		} catch (Exception e) {}
	}

	public void testCV_01() throws Exception {
		// <originalText mediaType = "text/plain" representation = "TXT" > O </originalText >
		final String input = "<realmCode code = \"30971-6\" codeSystem = \"2.16.840.1.113883.19\" xmlns=\"" + HL7_URI
				+ "\"/>";
		final String output = input;
		// final String output = "\t\t<addr use=\"WP\"xmlns=\"" + HL7_URI + "\\\">\n" + "\t\t <streetAddressLine>123 Main
		// St.Suite 500</streetAddressLine>\n" + "\t\t <city>Rockville</city>\n" + "\t\t <state>MD</state>\n" + "\t\t
		// <postalCode>20852</postalCode>\n" + "\t\t</addr>";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		CV value = (CV) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		String fromXML = getXMLForValue(value, "realmCode");
		DetailedDiff detailedDiff = CommonTestUtils.getInstance()
				.doDiff(output, fromXML, new ElementNameAndTextQualifier());
		assertEquals("testDifferences ", 0, detailedDiff.getAllDifferences().size());
	}

	public void testCV_OTH() throws Exception {
		final String input = 
			"<realmCode nullFlavor=\"OTH\" xmlns=\"" + HL7_URI	+ "\"><originalText>asdfgh</originalText></realmCode>";
		final String output = input;
		InputStream is = new ByteArrayInputStream(input.getBytes());
		CV value = (CV) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		String fromXML = getXMLForValue(value, "realmCode");
		DetailedDiff detailedDiff = CommonTestUtils.getInstance()
				.doDiff(output, fromXML, new ElementNameAndTextQualifier());
		assertEquals("testDifferences ", 0, detailedDiff.getAllDifferences().size());
	}

	private String getXMLForValue(ANY value, String localName) throws TransformerFactoryConfigurationError,
			TransformerConfigurationException, TransformerException {
		StringWriter sw = new StringWriter();
		transformer.transform(new SAXSource(new RimGraphXMLSpeaker(), new RimGraphXMLSpeaker.DataValueInputSource(value,
				localName, datatype)), new StreamResult(sw));
		return sw.toString();
	}
}
/**
 * HISTORY : $Log$ HISTORY : Revision 1.1 2006/05/22 18:29:26 sjiang HISTORY : Updated with new test cases HISTORY :
 */

package org.hl7.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.ElementNameAndTextQualifier;
import org.custommonkey.xmlunit.ElementNameQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.hl7.meta.Datatype;
import org.hl7.meta.impl.SimpleDatatypeImpl;
import org.hl7.types.AD;
import org.hl7.types.ANY;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;

/**
 * This class defines unit test for AD.
 *
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: crosenthal $
 * @version Since HL7 SDK v1.2
 *          revision    $Revision: 5652 $
 *          date        $Date: 2007-03-30 11:35:44 -0400 (Fri, 30 Mar 2007) $
 */
public class ADTest extends XMLTestCase
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
	private static Datatype datatype = new SimpleDatatypeImpl("AD");

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

	public void testAD_01() throws Exception
	{
		//<streetAddressLine>123 Main St.Suite 500</streetAddressLine>
		final String input = "<addr use=\"WP\" xmlns=\"" + HL7_URI + "\">" + "<streetAddressLine>123 Main St.Suite 500</streetAddressLine>" + "<city>Rockville</city>" + "<state>MD</state>" + "<postalCode>20852</postalCode>" + "</addr>";
		final String output = "<addr use=\"WP\" xmlns=\"" + HL7_URI + "\">" + "<streetAddressLine>123 Main St.Suite 500</streetAddressLine>" + "<city>Rockville</city>" + "<state>MD</state>" + "<postalCode>20852</postalCode>" + "</addr>";
//		final String output = "\t\t<addr use=\"WP\"xmlns=\"" + HL7_URI + "\\\">\n" + "\t\t  <streetAddressLine>123 Main St.Suite 500</streetAddressLine>\n" + "\t\t  <city>Rockville</city>\n" + "\t\t  <state>MD</state>\n" + "\t\t  <postalCode>20852</postalCode>\n" + "\t\t</addr>";
		InputStream is = new ByteArrayInputStream(input.getBytes());
		AD value = (AD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
		String fromXML = getXMLForValue(value, "addr");
		DetailedDiff detailedDiff = doDiff(output, fromXML, new ElementNameAndTextQualifier());
		assertEquals("testDifferences ", 0, detailedDiff.getAllDifferences().size());
	}

	protected DetailedDiff doDiff(String control, String test, ElementNameQualifier qualifier) throws Exception
	{
		Diff diff = new Diff(control, test);
		MessageDifferenceListener differenceListener = new MessageDifferenceListener();
		DetailedDiff detailedDiff = new DetailedDiff(diff);
		detailedDiff.overrideDifferenceListener(differenceListener);
		detailedDiff.overrideElementQualifier(qualifier);
		List<Difference> diffList = (List<Difference>) detailedDiff.getAllDifferences();

		// print debug information.
		printDifferences(diffList);
			//differenceListener.printSummary();
		return detailedDiff;
	}

	protected void printDifferences(List<Difference> diffList)
	{
		for (int i = 0; i < diffList.size(); i++)
		{
			Difference difference = diffList.get(i);
			System.out.println("difference = " + i);
			System.out.println("\tdifference.getId = " + difference.getId());
			System.out.println("\tdifference.getDescription = " + difference.getDescription());
			System.out.println("\tdifference.isRecoverable = " + difference.isRecoverable());
			System.out.println("\tdifference.toString = " + difference.toString());
		}
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
 * HISTORY      : Revision 1.1  2006/05/02 21:23:02  sjiang
 * HISTORY      : more tests come up.
 * HISTORY      :
 */

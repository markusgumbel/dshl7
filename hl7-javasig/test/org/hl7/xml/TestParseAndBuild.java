package org.hl7.xml;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.hl7.util.StringUtils;
import org.hl7.util.ThreadProperties;

/**
 * Parse xml file into Rim Graph, build XML and compare the input xml and output xml. 
 *
 * @author OWNER: Eric Chen
 * @author LAST UPDATE $Author: crosenthal $
 * @version Since HL7 SDK v1.2
 *          revision    $Revision: 5652 $
 *          date        $Date: 2007-03-30 11:35:44 -0400 (Fri, 30 Mar 2007) $
 */
public class TestParseAndBuild extends XMLTestCase
{
    /**
     * Logging constant used to identify source of log entry, that could be later used to create
     * logging mechanism to uniquely identify the logged class.
     */

    @SuppressWarnings("unused")
	private static final String LOGID = "$RCSfile$";

	public static String XSD_DIR = null;
  	public static String XML_DIR = null;
  	
  	static
  	{
  		XSD_DIR = ThreadProperties.getProperty("hl7v3.schema.dir");
  	  	XML_DIR = System.getProperty("etc.dir");  		
  	}

    public TestParseAndBuild()
    {
        XMLUnit.setIgnoreWhitespace(true);
    }

    public void testCOCT() throws Exception {
        String[] messages = new String[]{
					"UUDD_MT999999"
            //"COCT_MT999900", //R_ServiceDeliveryLocation identified (CMET)
            //"PORT_MT030001", // Clinical Trial Lab Report (message)
            //"PORR_MT040001", // ICSR Reaction Report (message)
            //"PORR_MT040002", // ICSR Event A_Drug Intervention (IMET)
            //"PORR_MT040003", // ICSR Event A_Device Intervention (IMET)
            //"PORR_MT040004", // ICSR R_Drug (IMET)
            //"PORR_MT040005", // ICSR R_Device (IMET)
            //"COCT_MT070000", //R_LocationLocatedEntity universal (CMET)
            //"COCT_MT090000", //R_Assigned_Entity universal (CMET)
            //"COCT_MT090100", //R_AssignedPerson universal (CMET)
            //"COCT_MT150000", //E_Organization universal (CMET)
            //"COCT_MT150003",  //E_Organization contact (CMET)
            //"COCT_MT240000",  //  R_ServiceDeliveryLocation universal (CMET)
            //"COCT_MT240001", //R_ServiceDeliveryLocation identified (CMET)
            //"COCT_MT710000", //E_Place universal (CMET)
        };

        for (int i = 0; i < messages.length; i++) {
            parseAndBuild(messages[i]);
        }
    }

    public void _testPORR() throws Exception{
        String[] messages = new String[]{
            "PORR_MT040003","PORR_MT040005"};

        for (int i = 0; i < messages.length; i++) {
            parseAndBuild(messages[i]);
        }
    }

    public void parseAndBuild(String messagetype) throws Exception {
        parseAndBuild(StringUtils.searchMessageTypeFileName(TestParseAndBuild.XML_DIR, messagetype, "xml",false), messagetype,
            StringUtils.searchMessageTypeFileName(TestParseAndBuild.XSD_DIR, messagetype, "xsd",true));
    }

    public void parseAndBuild(String xmlfile, String messagetype, String xsdfile) throws Exception {
			if(SchemaValidator.validate(xmlfile, xsdfile).getNumberOfErrors() > 0)
				System.err.println("" + xmlfile + " is invalid against schema, skipping this test");
			else {
        // parse and build.
        TestDriver driver = new TestDriver(xmlfile, messagetype);

        driver.loadMif();
        driver.createObjectGraph();
        driver.buildXML();
        System.out.println("driver.getBuiltXml() = \n" + driver.getBuiltXml());

        //If xml is not valid against schema, it will return false
				assertTrue("Output was invalid against schemas", SchemaValidator.validate(driver.getBuiltXml(), xsdfile).getNumberOfErrors() == 0);

        // do the diff.
        DetailedDiff dd = doDiff(new FileReader(xmlfile), new StringReader(driver.getBuiltXml()));
        assertTrue("There are "+messagetype+" parse/build differences!", dd.getAllDifferences().size() == 0);

				System.err.println("" + xmlfile + " SUCCEEDED!");
			}
    }

    protected DetailedDiff doDiff(Reader control, Reader test) throws Exception {
        Diff diff = new Diff(control, test);
        MessageDifferenceListener differenceListener = new MessageDifferenceListener();
        DetailedDiff detailedDiff = new DetailedDiff(diff);
        detailedDiff.overrideDifferenceListener(differenceListener);
        detailedDiff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
        List<Difference> diffList = (List<Difference>) detailedDiff.getAllDifferences();

        // print debug information.
        printDifferences(diffList);

        return detailedDiff;
    }

    protected void printDifferences(List<Difference> diffList) {
        for (int i = 0; i < diffList.size(); i++) {
            Difference difference = diffList.get(i);
            System.out.println("difference = " + i);
            System.out.println("\tdifference.getId = " + difference.getId());
            System.out.println("\tdifference.getDescription = " + difference.getDescription());
            System.out.println("\tdifference.isRecoverable = " + difference.isRecoverable());
            System.out.println("\tdifference.toString = " + difference.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        TestRunner.run(new TestSuite(TestParseAndBuild.class));
    }


}

/**
 * HISTORY      : $Log$
 * HISTORY      : Revision 1.22  2006/06/25 22:40:58  echen
 * HISTORY      : Add COCT_MT999900 support
 * HISTORY      :
 * HISTORY      : Revision 1.21  2006/06/25 22:25:50  echen
 * HISTORY      : Add PORR_MT040003,4 support
 * HISTORY      :
 * HISTORY      : Revision 1.20  2006/06/21 02:36:22  echen
 * HISTORY      : Add PORR_MT040005 support
 * HISTORY      :
 * HISTORY      : Revision 1.19  2006/06/20 22:21:20  echen
 * HISTORY      : Add PORR_MT040011 support
 * HISTORY      :
 * HISTORY      : Revision 1.18  2006/05/23 21:44:42  echen
 * HISTORY      : Support PORT_MT030001
 * HISTORY      :
 * HISTORY      : Revision 1.17  2006/05/19 15:21:57  echen
 * HISTORY      : Fix sortKey problem to replace "_" with space
 * HISTORY      :
 * HISTORY      : Revision 1.16  2006/05/19 15:20:04  echen
 * HISTORY      : Fix sortKey problem to replace "_" with space
 * HISTORY      :
 * HISTORY      : Revision 1.15  2006/05/16 14:10:28  echen
 * HISTORY      : Fix RTO H3s missing data type spec error
 * HISTORY      :
 * HISTORY      : Revision 1.14  2006/05/15 16:16:51  echen
 * HISTORY      : Support CS data type as a RIM object attribute but not as a structure attribute
 * HISTORY      :
 * HISTORY      : Revision 1.13  2006/05/10 16:03:42  echen
 * HISTORY      : Add 090100 Message, schema validated
 * HISTORY      :
 * HISTORY      : Revision 1.12  2006/05/01 16:02:31  echen
 * HISTORY      : Refactory TesterDriver using MIF file since MIF file does not need the RIM file and HMD file anymore
 * HISTORY      :
 * HISTORY      : Revision 1.11  2006/04/27 17:29:42  echen
 * HISTORY      : Plug In COCT_MT150003  message parse and build
 * HISTORY      :
 * HISTORY      : Revision 1.10  2006/04/20 22:08:03  echen
 * HISTORY      : Saved Point
 * HISTORY      :
 * HISTORY      : Revision 1.9  2006/04/20 21:33:44  echen
 * HISTORY      : Saved Point
 * HISTORY      :
 * HISTORY      : Revision 1.8  2006/04/20 19:54:08  echen
 * HISTORY      : Update with 2005 Normative Edition XML instance and Schema
 * HISTORY      :
 * HISTORY      : Revision 1.7  2006/01/12 22:29:17  echen
 * HISTORY      : caAdapter generic enhancement
 * HISTORY      :
 * HISTORY      : Revision 1.6  2005/12/28 03:39:41  echen
 * HISTORY      : Optimize input
 * HISTORY      :
 * HISTORY      : Revision 1.5  2005/12/15 19:54:41  echen
 * HISTORY      : Collect all schema validation error when parsing the xml
 * HISTORY      :
 * HISTORY      : Revision 1.4  2005/12/08 15:15:47  gschadow
 * HISTORY      : *** empty log message ***
 * HISTORY      :
 * HISTORY      : Revision 1.3  2005/11/23 16:16:28  echen
 * HISTORY      : Add unit test for comparing PQ input output
 * HISTORY      :
 * HISTORY      : Revision 1.2  2005/11/21 20:54:40  echen
 * HISTORY      : Add Test Case for Parse and Build
 * HISTORY      :
 * HISTORY      : Revision 1.1  2005/11/21 20:46:48  echen
 * HISTORY      : Add Test Case for Parse and Build
 * HISTORY      :
 */

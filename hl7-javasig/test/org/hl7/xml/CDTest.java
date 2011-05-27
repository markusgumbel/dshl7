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
import org.custommonkey.xmlunit.XMLTestCase;
import org.hl7.meta.Datatype;
import org.hl7.meta.impl.SimpleDatatypeImpl;
import org.hl7.types.CD;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;

/**
 * CREATED BY: Dan Grandquist  CREATION DATE:  Apr 20, 2005
 * UPDATED BY:                 UPDATE DATE:
 * Purpose:
 *   
 */
public class CDTest extends XMLTestCase
{
    public static final String HL7_URI = "urn:hl7-org:v3";
    private static Transformer transformer;
    private static Datatype datatype = new SimpleDatatypeImpl("CD");

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

    // todo: we still add the xsi:type=\"CD\" the value element is a ANY datatype
    public void testCD_01CodeValidLengthNormalCase() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_02CodeValidLengthLowBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"1\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"1\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_03CodeValidLengthLowOutOfBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_04CodeValidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_05CodeInvalidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"12345 6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"12345 6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_06CodeRequiredComplete() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    //Failure case... 
    public void testCD_07CodeRequiredMissing() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLNotEqual(output, getXMLForValue(value, "value"));
    }
    
    
    public void testCD_08CodeRequiredNull() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_09CodeSystemValidLengthNormalCase() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_10CodeSystemValidLengthLowBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"1\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"1\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    //Failure case???
    public void testCD_11CodeSystemValidLengthLowOutOfBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"UNKNOWN\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLNotEqual(output, getXMLForValue(value, "value"));

    }

    public void testCD_12CodeSystemValidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_13CodeSystemInvalidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"1A\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"1A\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 

    public void testCD_14CodeSystemRequiredComplete() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    //Failure case... 
    public void testCD_15CodeSystemRequiredMissing() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLNotEqual(output, getXMLForValue(value, "value"));
    }

    //Failure case... 
    public void testCD_16CodeSystemRequiredNull() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"UNKNOWN\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLNotEqual(output, getXMLForValue(value, "value"));

    }

    public void testCD_17CodeSystemNameValidLengthNormalCase() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_18CodeSystemNameValidLengthLowBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"A\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"A\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_19CodeSystemNameValidLengthLowOutOfBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_20CodeSystemNameValidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_21CodeSystemNameInvalidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"ABC ABC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"ABC ABC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 

    public void testCD_22CodeSystemNameOptionalComplete() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    //Failure case... errors? is code system
    public void testCD_23CodeSystemNameOptionalMissing() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" codeSystemVersion=\"2.13\" codeSystem=\"2.16.840.1.113883.19\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" codeSystemVersion=\"2.13\" codeSystem=\"2.16.840.1.113883.19\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLNotEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_24CodeSystemNameOptionalNull() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_25CodeSystemVersionValidLengthNormalCase() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_26CodeSystemVersionValidLengthLowBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"a\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"a\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_27CodeSystemVersionValidLengthLowOutOfBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_28CodeSystemVersionValidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_29CodeSystemVersionInvalidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"ABC 123\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"ABC 123\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 

    public void testCD_30CodeSystemVersionOptionalComplete() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_31CodeSystemVersionOptionalMissing() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_32CodeSystemVersionOptionalNull() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_33DisplayNameValidLengthNormalCase() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_34DisplayNameValidLengthLowBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"A\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"A\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
    public void testCD_35DisplayNameValidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_36DisplayNameInvalidFormat() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
   } 

    public void testCD_37DisplayNameOptionalComplete() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
   }

    public void testCD_38DisplayNameOptionalMissing() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
   }

    public void testCD_39DisplayNameOptionalNull() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }

    public void testCD_40TranslationValidNormalCase() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"1.1\" codeSystemName=\"MyCodeSystem\" displayName=\"AE\"/></value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"1.1\" codeSystemName=\"MyCodeSystem\" displayName=\"AE\"/></value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 
    
    public void testCD_41TranslationValidLowBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\"/></value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\"/></value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
    
/* test case fails: <test track 77> CD does not support more then one translations. 
    public void testCD_43TranslationValidHighBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"1.1\" codeSystemName=\"MyCodeSystem\" displayName=\"AE\"/><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"1.1\" codeSystemName=\"MyCodeSystem\" displayName=\"AE\"/></value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"1.1\" codeSystemName=\"MyCodeSystem\" displayName=\"AE\"/><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"1.1\" codeSystemName=\"MyCodeSystem\" displayName=\"AE\"/></value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLNotEqual(output, getXMLForValue(value, "value"));
    } 
*/

/* test case fails: <test track 79> CD does not accept original text 
    public void testCD_44OriginalTextValidNormalCase() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\">The text or phrase used as the basis for the coding.</value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\">The text or phrase used as the basis for the coding.</value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 
*/
    public void testCD_45OriginalTextMissing() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"></value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"></value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 
    
    public void testCD_46OriginalTextNull() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"/>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 

/*  All 3 test case fails: <test track 80> CD data type does not support Qualifier 
    public void testCD_47QualifierValidNormalCase() throws Exception
    {
    	final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><qualifier><name code=\"246075003\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"causative agent\"/><value code=\"1476002\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"codeine\"/></qualifier></value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><qualifier><name code=\"246075003\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"causative agent\"/><value code=\"1476002\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"codeine\"/></qualifier></value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
        System.out.println("********************************");
        System.out.println("testCD_47QualifierValidNormalCase");
        System.out.println(input);
        System.out.println(getXMLForValue(value, "value"));
        System.out.println(output);
        System.out.println("********************************");
        
    } 
   
    public void testCD_48QualifierValidLowBoundary() throws Exception
    {
    	final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\"/><value code=\"1476002\" codeSystem=\"2.16.840.1.113883.6.96\"/></qualifier></value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\"/><value code=\"1476002\" codeSystem=\"2.16.840.1.113883.6.96\"/></qualifier></value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    }
   
    public void testCD_49QualifierValidHighBoundary() throws Exception
    {
        final String input = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><qualifier><name code=\"246075003\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"causative agent\"/><value code=\"1476002\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"codeine\"/></qualifier><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\"/></value>";
        final String output = "<value xmlns=\""+HL7_URI+"\" code=\"30971-6\" codeSystem=\"2.16.840.1.113883.19\" codeSystemVersion=\"2.13\" codeSystemName=\"LONIC\" displayName=\"ADVERSE EVENT\"><qualifier><name code=\"246075003\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"causative agent\"/><value code=\"1476002\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemVersion=\"2.1\" codeSystemName=\"SNOMED CT\" displayName=\"codeine\"/></qualifier><translation code=\"ABC123\" codeSystem=\"2.16.840.1.113883.19\"/></value>";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        CD value = (CD) StandaloneDataTypeContentHandler.parseValue(is, datatype);
        assertXMLEqual(output, getXMLForValue(value, "value"));
    } 
*/
    private String getXMLForValue(CD value, String localName) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException
    {
        StringWriter sw = new StringWriter();
        transformer.transform(new SAXSource(new RimGraphXMLSpeaker(), new RimGraphXMLSpeaker.DataValueInputSource(value, localName, datatype)), new StreamResult(sw));
        return sw.toString();
    }
}

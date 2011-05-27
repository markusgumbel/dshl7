package org.hl7.xml.builder;

import java.io.IOException;
import junit.framework.TestCase;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Jere Krischel
 */
public class XMLSpeakerTest extends TestCase
{
    class FakeXMLSpeaker extends XMLSpeaker
    {
        public void parse(InputSource input) throws IOException, SAXException
        {
        }

        public void parse(String systemId) throws IOException, SAXException
        {

        }
    }

    private boolean worked = false;

    public void testSetFeatureWithNoThrow() throws Exception
    {
        //none of these setFeature() calls do anything
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        fakeXMLSpeaker.setFeature("http://xml.org/sax/features/namespaces", true);
        fakeXMLSpeaker.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
        fakeXMLSpeaker.setFeature("http://xml.org/sax/features/namespaces", false);
        fakeXMLSpeaker.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
    }    
    
    public void testSetFeature() throws Exception
    {
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        try
        {
            fakeXMLSpeaker.setFeature("name", true);
            fail("Should have thrown SAXNotRecognizedException");
        }
        catch (SAXNotRecognizedException success)
        {
            assertEquals("name", success.getMessage());
        }
    }    
    public void testGetFeature() throws Exception
    {
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        try
        {
            fakeXMLSpeaker.getFeature("name");
            fail("Should have thrown SAXNotRecognizedException");
        }
        catch (SAXNotRecognizedException success)
        {
            assertEquals("name", success.getMessage());
        }
    }
    public void testContentBuilderCharactersWithString() throws Exception
    {
        worked = false;
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        XMLSpeaker.ContentBuilder contentBuilder = fakeXMLSpeaker.new ContentBuilder();
        fakeXMLSpeaker.setContentHandler(new DefaultHandler()
        {
            public void characters(char ch[], int start, int length) throws SAXException
            {
                worked = true;
                assertEquals('s', ch[0]);
                assertEquals('t', ch[10]);
                assertEquals(0, start);
                assertEquals(11, length);
            }
        });
        contentBuilder.characters("sample text");
        assertTrue(worked);
    }

    public void testContentBuilderEndElementWithoutPrefix() throws Exception
    {
        worked = false;
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        XMLSpeaker.ContentBuilder contentBuilder = fakeXMLSpeaker.new ContentBuilder();
        fakeXMLSpeaker.setContentHandler(new DefaultHandler()
        {
            public void endElement(String namespaceURI, String localName, String qName) throws SAXException
            {
                worked = true;
                assertEquals("uri", namespaceURI);
                assertEquals("localName", localName);
                assertEquals("localName", qName);
            }
        });
        contentBuilder.setNamespace("uri");
        contentBuilder.endElement("localName");
        assertTrue(worked);
    }

    public void testContentBuilderEndElementWithPrefix() throws Exception
    {
        worked = false;
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        XMLSpeaker.ContentBuilder contentBuilder = fakeXMLSpeaker.new ContentBuilder();
        fakeXMLSpeaker.setContentHandler(new DefaultHandler()
        {
            public void endElement(String namespaceURI, String localName, String qName) throws SAXException
            {
                worked = true;
                assertEquals("uri", namespaceURI);
                assertEquals("localName", localName);
                assertEquals("prefixlocalName", qName);
            }
        });
        contentBuilder.setNamespace("uri", "prefix");
        contentBuilder.endElement("localName");
        assertTrue(worked);
    }

    public void testContentBuilderStartElementWithoutPrefix() throws Exception
    {
        worked = false;
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        XMLSpeaker.ContentBuilder contentBuilder = fakeXMLSpeaker.new ContentBuilder();
        fakeXMLSpeaker.setContentHandler(new DefaultHandler()
        {
            public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException
            {
                worked = true;
                assertEquals("uri", namespaceURI);
                assertEquals("localName", localName);
                assertEquals("localName", qName);
            }
        });
        contentBuilder.setNamespace("uri");
        contentBuilder.startElement("localName");
        assertTrue(worked);
    }

    public void testContentBuilderStartElementWithPrefix() throws Exception
    {
        worked = false;
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        XMLSpeaker.ContentBuilder contentBuilder = fakeXMLSpeaker.new ContentBuilder();
        fakeXMLSpeaker.setContentHandler(new DefaultHandler()
        {
            public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException
            {
                worked = true;
                assertEquals("uri", namespaceURI);
                assertEquals("localName", localName);
                assertEquals("prefixlocalName", qName);
            }
        });
        contentBuilder.setNamespace("uri", "prefix");
        contentBuilder.startElement("localName");
        assertTrue(worked);
    }

    public void testContentBuilderAttributes()
    {
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        XMLSpeaker.ContentBuilder contentBuilder = fakeXMLSpeaker.new ContentBuilder();
        AttributesImpl attributes = contentBuilder.getAttributes();

        contentBuilder.addAttribute("namespace", "localName", "qname", "type", "value");
        assertEquals(1, attributes.getLength());
        assertEquals("namespace", attributes.getURI(0));
        assertEquals("localName", attributes.getLocalName(0));
        assertEquals("qname", attributes.getQName(0));
        assertEquals("type", attributes.getType(0));
        assertEquals("value", attributes.getValue(0));

        contentBuilder.addAttribute("localName2", "type2", "value2");
        assertEquals(2, attributes.getLength());
        assertEquals("", attributes.getURI(1));
        assertEquals("localName2", attributes.getLocalName(1));
        assertEquals("localName2", attributes.getQName(1));
        assertEquals("type2", attributes.getType(1));
        assertEquals("value2", attributes.getValue(1));

        contentBuilder.addAttribute("localName3", "value3");
        assertEquals(3, attributes.getLength());
        assertEquals("", attributes.getURI(2));
        assertEquals("localName3", attributes.getLocalName(2));
        assertEquals("localName3", attributes.getQName(2));
        assertEquals("CDATA", attributes.getType(2));
        assertEquals("value3", attributes.getValue(2));
    }

    public void testContentBuilderNamespaceSettersAndGetters()
    {
        FakeXMLSpeaker fakeXMLSpeaker = new FakeXMLSpeaker();
        XMLSpeaker.ContentBuilder contentBuilder = fakeXMLSpeaker.new ContentBuilder();
        contentBuilder.setNamespace("URI", "PREFIX");
        assertEquals("URI", contentBuilder.getNamespaceURI());
        assertEquals("PREFIX", contentBuilder.getNamespacePrefix());
        contentBuilder.setNamespace("URI");
        assertEquals("URI", contentBuilder.getNamespaceURI());
        assertNull(contentBuilder.getNamespacePrefix());
    }

}

package org.hl7.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.hl7.hibernate.Persistence;
import org.hl7.meta.MessageType;
import org.hl7.meta.mif.CloneClassAdapter;
import org.hl7.meta.mif.MessageTypeLoaderAdapter;
import org.hl7.rim.RimObject;
import org.hl7.util.ApplicationContext;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.MessageContentHandler;

public class TestDriver implements ApplicationContext {

    public static final List<String> supportedMessageIds
        = new ArrayList<String>(Arrays.asList(new String[]{"PORR_MT040011", "PORT_MT030001"}));

    protected static final Logger LOGGER = Logger.getLogger("gov.nih.nci.hl7");

    protected Object graph = null;
    protected MessageType messageType = null;

    private String xmlFile = null;
    private InputStream xmlInputStream = null;


    // @deprecated: hmdFile is deprecated with mif file
    private String hmdFile = null;

    // @deprecated: mif
    private String rimFile = null;
    private String msgType = null;

    String builtXml = null;

    public TestDriver() {
    }

    public TestDriver(String xmlFile, String messageType)
    {
        this.xmlFile = xmlFile;
        this.msgType = messageType;
    }

    /**
     * @param xmlFile
     * @param hmdFile
     * @param rimFile
     * @param messageType
     * @deprecated since caAdapter v1.3, using MIF file instead
     */
    public TestDriver(String xmlFile, String hmdFile, String rimFile, String messageType) {
        this.xmlFile = xmlFile;
        this.hmdFile = hmdFile;
        this.rimFile = rimFile;
        this.msgType = messageType;
        //banner();
    }

    // for the Object Graph..
    public Object getGraph() {
        return graph;
    }

    public void setGraph(Object graph) {
        this.graph = graph;
    }

    // for the MessageType object..
    public MessageType getMessageType() {
        return messageType;
    }

    // for the XML input (for parsing)
    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    public InputStream getXmlInputStream() {
        return xmlInputStream;
    }

    public void setXmlInputStream(InputStream xmlInputStream) {
        this.xmlInputStream = xmlInputStream;
    }

    //

    /**
     * For the HMD file
     * @return String
     * @deprecated since caAdapter v1.3, using MIF file instead
     */
    public String getHmdFile() {
        return hmdFile;
    }

    /**
     * @param hmdFile
     * @deprecated since caAdapter v1.3, using MIF file instead
     */
    public void setHmdFile(String hmdFile) {
        this.hmdFile = hmdFile;
    }

    /**
     *
     * @return RIM file
     * @deprecated since caAdapter v1.3. Using MIF file does not need RIM file anymore
     */
    public String getRimFile() {
        return rimFile;
    }

    /**
     * @param rimFile
     * @deprecated since caAdapter v1.3. Using MIF file does not need RIM file anymore
     */
    public void setRimFile(String rimFile) {
        this.rimFile = rimFile;
    }

    // for the Message Type String
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    // for the Build XML
    public String getBuiltXml() {
        return builtXml;
    }

    /**
     * @throws Exception
     * @since version 1.3
     */
    public void loadMif() throws Exception
    {
        MessageTypeLoaderAdapter jmtl = MessageTypeLoaderAdapter.getInstance();
        messageType = jmtl.loadMessageType(msgType);
    }

    public void createObjectGraph() throws Exception {
        //System.out.println("********* Build Object Graph ************");
        if (xmlInputStream == null) {
            File f = new File(xmlFile);
            xmlInputStream = new FileInputStream(f);
        }

        graph = MessageContentHandler.parseMessage(this, xmlInputStream, messageType);
    }

    public void buildXML() throws Exception {

        String messageId = messageType.getId();
        if(!supportedMessageIds.contains(messageId)){
            CloneClassAdapter cc = (CloneClassAdapter)messageType.getRootClass();
            cc.setName(messageId + "." + cc.getName());
        }

        //System.out.println("********* Build XML ************");
        Transformer transformer =
            TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        RimGraphXMLSpeaker speaker = new RimGraphXMLSpeaker();

        Source source = new SAXSource(speaker,
            new RimGraphXMLSpeaker.InputSource((RimObject) graph,
                messageType.getRootClass()));
        StringWriter sw = new StringWriter();

        try {
            transformer.transform(source, new StreamResult(sw));
        } catch (Exception e) {
            e.printStackTrace();
        }
        builtXml = sw.toString();
    }

    private void banner() {
        System.out.println("Begin : " + this.toString());
        System.out.println("xmlFile = " + xmlFile);
        System.out.println("hmdFile = " + hmdFile);
        System.out.println("rimFile = " + rimFile);
        System.out.println("msgType = " + msgType);
    }


	// Begin implementing org.hl7.util.ApplicationContext 
	private Map<String, Object> _settings = new HashMap(System.getProperties());

	public Object getSetting(String name) { 
		return 	_settings.get(name);
	}
	public <T> T getSetting(String name, T defaultValue) { 
		T result = (T)_settings.get(name);
		if(result == null)
			return defaultValue;
		else
			return result;
	}
	public void setSetting(String name, Object value) { 
		_settings.put(name, value);
	}
	public Persistence getPersistence() { 
		return Persistence.instance();
	}
	// End implementing org.hl7.util.ApplicationContext
}

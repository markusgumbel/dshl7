package net.gumbix.hl7javasig.demo;

import org.hl7.meta.MessageType;
import org.hl7.meta.mif.MessageTypeLoaderAdapter;
import org.hl7.rim.RimObject;
import org.hl7.xml.builder.RimGraphXMLSpeaker;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class Util {

    public static void buildMessage(RimObject rim, String mif, String filename) {
        try {
            final RimGraphXMLSpeaker speaker = new RimGraphXMLSpeaker();
            final MessageTypeLoaderAdapter jmtl = MessageTypeLoaderAdapter
                    .getInstance();
            // MIF for CDA documents:
            // final MessageType messageType = jmtl.loadMessageType("POCD_HD000040");
            // Unknown domain...:
            final MessageType messageType = jmtl.loadMessageType(mif);
            final FileOutputStream fos = new FileOutputStream(filename);

            final Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            final Source source = new SAXSource(speaker,
                    new RimGraphXMLSpeaker.InputSource(rim,
                            messageType.getRootClass()));
            transformer.transform(source, new StreamResult(System.out));
            transformer.transform(source, new StreamResult(fos));
        } catch (final Exception x) {
            x.printStackTrace();
            System.out.println("Error: " + x.getMessage());
        }
    }
}

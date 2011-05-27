/**
 * The simplest possible way to demonstrate loading a message
 * @author Peter Hendler   9/22/2007
 */

package org.hl7.demo;

import java.io.*;

import org.hl7.meta.MessageType;
import org.hl7.meta.MessageTypeLoader;
import org.hl7.util.ApplicationContext;
import org.hl7.xml.parser.MessageContentHandler;


public class SimpleLoadMessage {
    SimpleLoadMessage() {
    }

    Object LoadMessage(String messagetypestr, String message) {
        Object rim = null;
        MessageTypeLoader<MessageType> mtl = org.hl7.meta.mif.MessageTypeLoaderAdapter.getInstance();
        MessageType messageType = mtl.loadMessageType(messagetypestr);
        try {
            File f = new File(message);
            FileInputStream in = new FileInputStream(f);
            ApplicationContext ac = new ContextForThis();
            rim = MessageContentHandler.parseMessage(ac, in, messageType);
        } catch (Exception e) {
            System.out.println("Cant load message file");
        }
        return rim;
    }

    public static void main(String[] args) {
        SimpleLoadMessage slm = new SimpleLoadMessage();
        Object rim = slm.LoadMessage("POCD_HD000040", "etc/makeCDA.xml");
        System.out.println("Message loaded " + rim.toString());
    }
}
 
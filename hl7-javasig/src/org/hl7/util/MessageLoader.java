package org.hl7.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.hl7.meta.MessageType;
import org.hl7.meta.MessageTypeLoader;
import org.hl7.xml.parser.MessageContentHandler;

/**
 * 
 * @author phend note the message parameter is a String of the contents of an
 *         entire message such as a CDA In the case of a CDA you use
 *         messagetypestr = "POCD_HD000040"
 */
public class MessageLoader {

	@SuppressWarnings("unchecked")
	public static Object LoadMessage(String messagetypestr, String message) {

		Object rim = null;
		MessageTypeLoader<MessageType> mtl = org.hl7.meta.mif.MessageTypeLoaderAdapter
				.getInstance();
		MessageType messageType = mtl.loadMessageType(messagetypestr);
		try {
			InputStream in = new ByteArrayInputStream(message.getBytes("UTF-8"));

			ApplicationContext ac = new ApplicationContextImpl();
			rim = MessageContentHandler.parseMessage(ac, in, messageType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rim;
	}

}

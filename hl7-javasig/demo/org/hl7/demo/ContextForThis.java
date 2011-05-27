/**
 * Only needed as a parameter to get 
 * MessageContentHandler.parseMessage() to work
 * @author Peter Hendler
 * 9/22/2007
 */


package org.hl7.demo;

import org.hl7.util.ApplicationContext;
import org.hl7.xml.parser.MessageContentHandler;
import org.hl7.hibernate.Persistence;
import java.util.*;

class ContextForThis implements ApplicationContext {
	// Begin implementing org.hl7.util.ApplicationContext 
	// needed as the this argument in  the method
	// graph = MessageContentHandler.parseMessage(this, ins, mt);  below
	
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

}// end of class
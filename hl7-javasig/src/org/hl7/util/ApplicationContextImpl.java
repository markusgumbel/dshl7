/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hl7.util;

import java.util.HashMap;
import java.util.Map;

import org.hl7.hibernate.Persistence;

/**
 *
 * @author phend
 */
public class ApplicationContextImpl implements ApplicationContext {
    
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



package org.hl7.rim.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class can be used from Eclipse to help build the properties file for
 * RimObjectFactoryUsingProperties.  It spits out properties for all classes
 * in packages for CMETs.
 * 
 * @author jmoore
 *
 */
public class CMETPropertiesBuilder
{
    static final private int ID_LENGTH = "AAAA_AA000000".length();
    
    public static void main(String[] args) throws Exception
    {
        CMETPropertiesBuilder builder = new CMETPropertiesBuilder(); 
    }
    
    private final List<String> _keys = new LinkedList<String>();
    private final Map<String, String> _mappings = new HashMap<String, String>();
    
    public CMETPropertiesBuilder() throws IOException
    {
        URL url = Thread.currentThread().getContextClassLoader().getResource("org/hl7");
        File hl7Dir = new File(url.getFile());
        System.out.println(hl7Dir);
        for (File packageDir : hl7Dir.listFiles())
        {
            String messageType = packageDir.getName();
            char firstChar = messageType.charAt(0);
            if (! Character.isUpperCase(firstChar)) continue;
            String messageTypeId = messageType.substring(0, ID_LENGTH);
            for (File clazz : packageDir.listFiles())
            {
                if (clazz.isDirectory()) continue;
                String className = clazz.getName();
                if (! className.endsWith(".class")) continue;
                className = className.substring(0, className.length() - 6);
                String key = messageTypeId + "." + className;
                _keys.add(key);
                String value = "org.hl7." + messageType + ".impl." + className + "Impl";
                _mappings.put(key, value);
            }
        }
        
        for (String key : _keys)
        {
            String value = _mappings.get(key);
            System.out.println(key + "=" + value);
        }
    }    
}

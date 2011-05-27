package org.hl7.meta.mif;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Implements ICmetNameToMifMap by loading the mapping from the properties file
 * CMET_PROPERTIES_FILE. 
 * 
 * @author jmoore
 *
 */
public class CmetNameToMifMap implements ICmetNameToMifMap
{    
    private static String CMET_PROPERTIES_FILE = "cmet-file.properties";

    final private Properties _properties = new Properties();

    /**
     * @return The URL specified in the properties file for the CMET.
     */
    public URL getMifURL(String cmetName)
    {
        loadProperties();
        String mifName = (String) _properties.get(cmetName);
        if (mifName == null) return null;
        Class clazz = CmetNameToMifMap.class;
        ClassLoader loader = clazz.getClassLoader();
        URL url = loader.getResource(mifName);
        return url;
    }
    
    /**
     * @return The ID of the CMET.
     */
    public String getMifId(String cmetName)
    {
        URL mifUrl = getMifURL(cmetName);
        return mifId(mifUrl);
    }

    /**
     * Utility method for parsing a CMET's ID out of its URL.
     *    
     * @param mifUrl
     * @return The first ID_LENGTH characters after the last / in the URL for
     * the given CMET.
     */
    protected static String mifId(URL mifUrl)
    {
        if (mifUrl == null) return null;
        String path = mifUrl.getPath();
        int begin = path.lastIndexOf('/') + 1;
        String mifId = path.substring(begin, begin + ID_LENGTH);
        return mifId;
    }

    /**
     * Lazy loads the properties file only once.
     */
    private void loadProperties()
    {
        if (! _properties.isEmpty()) return;

        InputStream input = null;
        try
        {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(CMET_PROPERTIES_FILE);
            _properties.load(input);
        }
        catch (Exception e)
        {
            String msg = "Could not load properties: " + CMET_PROPERTIES_FILE + " Be sure it's in classpath.";
            System.err.println(msg + e.getMessage());
            throw new IllegalArgumentException(msg, e);
        }
        finally
        {
            if (input != null) try
            {
                input.close();
            }
            catch (IOException ignore) {}
        }               
    }   
}

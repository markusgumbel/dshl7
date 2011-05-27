package org.hl7.meta.mif;

import java.net.URL;

/**
 * Interface for mapping CMET names to MIF URLs.
 * 
 * @author jmoore
 *
 */
public interface ICmetNameToMifMap
{
    static final int ID_LENGTH = "AAAA_AA000000".length();
    
    /**
     * @param cmetName The name used to reference a CMET when parsing a set of
     * MIF files.
     * @return The URL to the referenced CMET's MIF file.
     */
    URL getMifURL(String cmetName);
    
    /**
     * @param cmetName The name used to reference a CMET when parsing a set of
     * MIF files.
     * @return The first ID_LENGTH characters after the last / in the URL for
     * the given CMET.
     */
    String getMifId(String cmetName);
}

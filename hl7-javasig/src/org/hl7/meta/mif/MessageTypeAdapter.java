/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 *
 * $Id: MessageTypeAdapter.java 7474 2007-10-03 16:14:49Z jmoore $
 */
package org.hl7.meta.mif;

import hl7OrgV3.mif.AffiliateKind;
import hl7OrgV3.mif.PackageRef;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.hl7.meta.CloneClass;
import org.hl7.meta.LoaderException;
import org.hl7.meta.MessageType;
import org.hl7.util.ForwardReferenceTool;

/**
 * Maintains a cache of mifs/CMETs that have been read to build meta data used 
 * to serialize and deserialize messages.
 * 
 * @see MessageTypeAdapter#createCmetToNameMap() and @see ICmetNameToMifMap 
 * for info on the mapping of CMET names to their mifs' URLs. 
 * 
 * @author Gunther Schadow, Peter Hendler, jmoore
 */
public class MessageTypeAdapter implements MessageType
{
    static private final Map<String, MessageTypeAdapter> s_cmets = new HashMap<String, MessageTypeAdapter>();     
    static private final ICmetNameToMifMap s_cmetNameToMifMap = createCmetToNameMap();
        
    private hl7OrgV3.mif.SerializedStaticModel _mifThing;
    private Map<String, CloneClass> _cloneClassMap = new HashMap<String, CloneClass>();
    private Map<String, MessageTypeAdapter> _referencedCmets = new HashMap<String, MessageTypeAdapter>();
    private CloneClass _rootClass;
    final private String _name;
    final private String _id;

    /**
     * Factory method to prevent CMETs from being read many times. 
     * 
     * @param messageTypeName name (not id) of the CMET.
     * @return The CMET's MessageType
	 * 
     * @author jmoore
     */
    static MessageTypeAdapter getMessageType(String messageTypeName)
    {
        String id = getId(messageTypeName);
        
        MessageTypeAdapter cmet = s_cmets.get(id);
        if (cmet == null)
        {
            cmet = new MessageTypeAdapter(messageTypeName);
            id = cmet.getId();
            s_cmets.put(id, cmet);
        }
        return cmet;
    }
    
    private MessageTypeAdapter(String messagetype) throws LoaderException
    {
        _mifThing = mifThing(messagetype);
        _name = messagetype;
        
        // Set the MessageType's id first by reading the mifThing
        String idFromMifThing = readIdFromMifThing(_mifThing);
        
        // If the mif did not have a valid id, use the map.
        if (idFromMifThing == null || idFromMifThing.length() < ICmetNameToMifMap.ID_LENGTH)
        {
            _id = getId(messagetype);
        }        
        else            
        {
            _id = idFromMifThing;
        }
        _rootClass = new CloneClassAdapter(this, _mifThing.getOwnedEntryPoint().getSpecializedClass().getClass1());
    }

    /**
     * Determines the CMET's id by looking up the given name in the config 
     * properties and truncating the filename before any version info or 
     * extension.
     *  
     * @param cmetName
     * @return The id of the CMET as given in the config properties.
     * 
     * @author jmoore 2007.08.23
     */
    private static String getId(String cmetName)
    {
        String mifId = s_cmetNameToMifMap.getMifId(cmetName);
        if (mifId == null)
        {
            throw new IllegalArgumentException("Invalid CMET name: " + cmetName);
        }
        return mifId;
    }

    /**
     * mifThings seem to sometimes be missing some of the information that is 
     * used to build the id.  In those cases we base the id off of the file's 
     * name. 
     *
     * @see MessageTypeAdapter#getId()
     *  
     * @author jmoore 2007.08.23
     */
    private static String readIdFromMifThing(hl7OrgV3.mif.SerializedStaticModel mifThing)
    {
        try
        {
            PackageRef packageLocation = mifThing.getPackageLocation();
            String subSection = packageLocation.getSubSection().toString();
            String domain = packageLocation.getDomain().toString();
            String artifact = packageLocation.getArtifact().toString().substring(0, 2);
            AffiliateKind.Enum realmEnum = packageLocation.getRealm();
            if (realmEnum != null)
            {
                String realm = realmEnum.toString();
            }
            String version = packageLocation.getVersion();
    
            String id = mifThing.getName();
            StringBuffer sb = new StringBuffer(subSection).append(domain).append("_").append(artifact).append(id);
            return sb.toString();
        }
        catch (Exception e)
        {
            return null;
        }
    }


    private static hl7OrgV3.mif.SerializedStaticModel mifThing(String messagetype)
    {
        URL mifUrl = s_cmetNameToMifMap.getMifURL(messagetype);
        
        if (mifUrl == null)
        {
            throw new RuntimeException("Could not get configuration info for message type: " + messagetype);
        }

        InputStream input = null;
        try
        {
            input = mifUrl.openStream();
            return hl7OrgV3.mif.SerializedStaticModelDocument.Factory.parse(input).getSerializedStaticModel();
        }
        catch (Exception ex)
        {
            throw new Error("Could not load MIF " + messagetype + " from " + mifUrl + ". Be sure it's in classpath. " + ex.getMessage(), ex);
        }
        finally
        {
            if (input != null) try
            {
                input.close();
            }
            catch (IOException ignore)
            {
            }
        }
    }
   
    /**
     * @see MessageType.addReferencedCmet(String referencedCmetName)
     * @author jmoore 2007.08.23
     */
    public MessageType addReferencedCmet(String referencedCmetName)
    {
        String id = getId(referencedCmetName);
        MessageTypeAdapter referencedCmet = _referencedCmets.get(id);
        if (referencedCmet == null)
        {
            referencedCmet = MessageTypeLoaderAdapter.getInstance().loadCmet(referencedCmetName);        
            _referencedCmets.put(id, referencedCmet);
        }
        return referencedCmet;
    }

    /**
     * @return The CMET with the given id (not name).
     * 
     * @author jmoore 2007.08.23
     */
    MessageTypeAdapter getReferencedCmet(String cmetId)
    {
        MessageTypeAdapter referencedCmet = _referencedCmets.get(cmetId); 
        return referencedCmet;
    }

    /**
     * All the lookup methods take a name which can either be a the name of a 
     * referenced CMET or a CloneClass.  This method looks for a MessageType 
     * for a CMET with the given name and returns the CloneClass of that CMET's
     * entry point.
     * 
     * @param name 
     * @return The root CloneClass of the named CMET or null if name is not in
     * the config mapping CMET names to files/ids.
     * 
     * @author jmoore
     */
    private CloneClass rootOfReferencedCmet(String name) 
    {
        String cmetId = s_cmetNameToMifMap.getMifId(name);
        if (cmetId == null)
        {
            return null;
        }
        MessageType targetCmet = addReferencedCmet(name);
        CloneClass cloneClass = targetCmet.getRootClass();
        return cloneClass;
    }
    
    /**
     * @author jmoore
     */
    public CloneClass lookupCloneClass(String name) throws NoSuchElementException
    {                
        // First see if name is a CMET name, not a class name
        CloneClass cloneClass = rootOfReferencedCmet(name);
        if (cloneClass == null)
        {
            cloneClass = _cloneClassMap.get(name);
        }
        
        // Java Docs say to return null?
        if (cloneClass == null || cloneClass instanceof ForwardReferenceTool.Reference) throw new NoSuchElementException("class not found: " + name + " " + cloneClass);
        
        return cloneClass;
    }

    /**
     * 
     * @param name
     * @param replacer
     * @return
     * 
     * @author jmoore
     */
    CloneClass lookupCloneClass(String name, ForwardReferenceTool.Replacer<CloneClass> replacer)
    {
        // First see if name is a CMET name, not a class name
        CloneClass cloneClass = rootOfReferencedCmet(name);
        if (cloneClass == null)
        {
            cloneClass = ForwardReferenceTool.get(_cloneClassMap, name, CloneClass.class, replacer);
        }
        return cloneClass;
    }

    /**
     * 
     * @param name
     * @param replacerFactory
     * @return
     */
    CloneClass lookupCloneClass(String name, ForwardReferenceTool.ReplacerFactory<CloneClass> replacerFactory)
    {                
        // First see if name is a CMET name, not a class name
        CloneClass cloneClass = rootOfReferencedCmet(name);
        if (cloneClass == null)
        {
            cloneClass = ForwardReferenceTool.get(_cloneClassMap, name, CloneClass.class, replacerFactory);            
        }
        return cloneClass;
    }

    public String getName()
    {
        // FIXME: not right?
        return _mifThing.getTitle();
    }

    /**
     * @author jmoore
     */
    public String toString()
    {
        return _name + "(" + _id + ")";
    }
    
    /**
     * _id is set when this object is created, either by reading the mifThing
     * or based on the mif file's name.
     * 
     * @see MessageTypeAdapter#getId()
     * 
     * @author jmoore
     */
    public String getId()
    {
        return _id;
    }

    public Set<String> getAllCloneClassNames()
    {
        return _cloneClassMap.keySet();
    }

    public CloneClass getRootClass()
    {
        return _rootClass;
    }

    public void addCloneClass(String name, CloneClass cc)
    {
        ForwardReferenceTool.put(_cloneClassMap, name, cc);
    }

    /**
     * Dynamically loads the class used to map CMET names to MIF URLs.
     * By default the class CmetNameToMifMap is used to load the mappings from
     * a properties file. To use a difference class the System property 
     * org.hlf.meta.mif.CmetNameToMifMap must be set to a class's name that 
     * implements ICmetNameToMifMap
     * 
     * @author jmoore
     */
    private static ICmetNameToMifMap createCmetToNameMap()
    {               
        String className = System.getProperty(CmetNameToMifMap.class.getName());
     
        // The default.
        if (className == null)
        {
            return new CmetNameToMifMap();
        }
        
        Exception ex = null;
        try
        {            
            Class clazz = Class.forName(className);
            return (ICmetNameToMifMap) clazz.newInstance();            
        }
        catch (ClassNotFoundException e)
        {
            ex = e; 
        }
        catch (InstantiationException e)
        {
            ex = e;
        }
        catch (IllegalAccessException e)
        {
            ex = e;
        }   
        
        if (ex != null)
        {
            throw new IllegalArgumentException("Could not create " + className + " for property " + CmetNameToMifMap.class.getName(), ex);
        }
        
        // This will never be reached.
        return new CmetNameToMifMap();
    }   
}

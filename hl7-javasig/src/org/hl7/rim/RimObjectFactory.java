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
* The Initial Developer of the Original Code is .
* Portions created by Initial Developer are Copyright (C) 2002-2004 
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*/

package org.hl7.rim;

import org.hl7.util.FactoryException;
import org.hl7.util.FactoryFinder;

public abstract class RimObjectFactory
{
  //-------------------------------------------------------------------------
  private static final String FACTORY_ID = "org.hl7.rim.RimObjectFactory";

  //-------------------------------------------------------------------------
  /**
   * Similar to javax.xml.parsers.SAXParserFactory.newInstance():
   * <nl>
   * <li>Checks org.hl7.rim.RimObjectFactory system property.</li>
   * <li>Checks file hl7.properties for key
   * org.hl7.rim.RimObjectFactory in the current directory.</li>
   * <li>Uses Services API to look for factory class name in the file
   * <code>META-INF/services/org.hl7.rim.RimObjectFactory</code> in
   * jars on the classpath.</li>
   * <li>If none of the above exist, uses default properties based factory.</li>
   * </nl>
   */
   public static RimObjectFactory getInstance() throws FactoryException
   {
     return (RimObjectFactory)FactoryFinder.find(FACTORY_ID,
       "org.hl7.rim.impl.RimObjectFactoryUsingProperties");
   }

  //-------------------------------------------------------------------------
   public abstract RimObject createRimObject(String name) throws FactoryException;
   
   /**
    * NOTE: This method should be abstract. This default implementation is
    * provided for backwards compability of concrete RimObjectFactory 
    * implementation that are not in the javasig project.
    * 
    * @param relativeClassName @see #mostSpecializedClassName(String[])
    * @return true if this factory can create a RimObject for the given
    * 
    * @author jmoore
    */
   public boolean canCreate(String relativeClassName)
   {
       try
       {
           createRimObject(relativeClassName);
       }
       catch (FactoryException e)
       {
           return false;
       }
       
       return true;
   }
   
   /**
    * NOTE: This method should be abstract. This default implementation is
    * provided for backwards compability of concrete RimObjectFactory 
    * implementation that are not in the javasig project.
    * 
    * @param prioritizedRelativeClassNames @see #mostSpecializedClassName(String[])
    * @return the class of the most specialized class that this factory can
    * create.
    * @throws FactoryException 
    * 
    * @author jmoore
    */
   public Class<? extends RimObject> rimObjectClass(String... prioritizedRelativeClassNames) throws FactoryException
   {
       RimObject rimObject = createRimObject(prioritizedRelativeClassNames);
       Class<? extends RimObject> rimObjectClass = rimObject == null ? null : rimObject.getClass();
       return rimObjectClass;
   }
   
   /**
    * @param prioritizedClassNames A list of partially qualified or unqualified
    * class names.  The names are sorted such that the more general a class the 
    * farther down it is on the list. 
    * 
    * @return The partially qualified or unqualified class name of the highest
    * priority relative class name that this factory can create.  null if non
    * of the classes on the list can be created by this factory.
    * 
    * @author jmoore
    */
   public String mostSpecializedClassName(String... prioritizedRelativeClassNames)
   {
       for (String relativeClassName : prioritizedRelativeClassNames)
       {
           if (canCreate(relativeClassName))
           {
               return relativeClassName;
           }
       }
       return null;
   }
   
   /**
    * @param prioritizedClassNames @see #mostSpecializedClassName(String[])
    * @return An instance of the most specialized class that this factory can 
    * create.
    * @throws FactoryException
    * 
    * @author jmoore
    */
   public RimObject createRimObject(String... prioritizedRelativeClassNames) throws FactoryException
   {
       StringBuffer classesTried = new StringBuffer();
       StringBuffer exe = new StringBuffer();
       
       for (String relativeClassName : prioritizedRelativeClassNames)
       {
           if (canCreate(relativeClassName))
           {
               try
               {
                   RimObject rimObject = createRimObject(relativeClassName);
                   return rimObject;
               }
               catch (FactoryException e)
               {
                   exe.append(e.getMessage());
               }
           }
           
           if (classesTried.length() > 0)
           {
               classesTried.append(", ");
           }
           classesTried.append(relativeClassName);
       }
       
       throw new FactoryException("Cannot create any class of " + classesTried + ". " + exe);      
   }
}

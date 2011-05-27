/**
 * Created on Apr 13, 2005
 * @author Peter Hendler
 * Test code to see how to load Mif files with XML beans
 * To be used by our meta loader
 */
package org.hl7.mif;

import hl7OrgV3.mif.SerializedAssociationEnd;
import hl7OrgV3.mif.SerializedStaticModel;
import hl7OrgV3.mif.SerializedStaticModelDocument;
import java.io.File;
import junit.framework.TestCase;

/**
 * @author phend
 * 
 * experimenting with ways to get meta data from ApacheXmlBeans For populating
 * our meta cloneclass files.
 */
public class MifTest extends TestCase
{
    public SerializedStaticModel makeModel()
    {
        SerializedStaticModelDocument modeldoc = null;
        SerializedStaticModel model = null;
        try
        {
            // ToDo I know, dont hard code it, but just working on it now
            File mif = new File("data\\NewMifs\\PORR_HD040001.mif");
            modeldoc = SerializedStaticModelDocument.Factory.parse(mif);
            model = modeldoc.getSerializedStaticModel();
            // model = StaticModel.Factory.newInstance();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return model;
    }

    public void testModel()
    {
        SerializedStaticModel model = makeModel();
        SerializedAssociationEnd[] ass = model.getOwnedEntryPoint().getSpecializedClass().getClass1().getAssociationArray();
        doAssociations(ass);
        //put asserts here to verify the size/contents of ass
    }

    // this just prints it out to see
    public void doAssociations(SerializedAssociationEnd[] ass)
    {
        // System.out.println("---------------------");
        // System.out.println("assoc length" + ass.length);
        for (int i = 0; i < ass.length; i++)
        {
            String target = ass[i].getTargetConnection().getName();
            String source = ass[i].getSourceConnection().getNonTraversableConnection().getParticipantClassName();
            System.out.println("Target... " + target + " ..source... " + source);
            try
            {
                SerializedAssociationEnd[] recurs = ass[i].getTargetConnection().getParticipantClass().getClass1().getAssociationArray();
                doAssociations(recurs);
            }
            catch (NullPointerException e)
            {
            }
        }
    }
}
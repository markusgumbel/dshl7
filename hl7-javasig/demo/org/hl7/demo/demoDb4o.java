/**
* use db4o to store, retrieve and explore (with object explorer) a rim graph
* Peter Hendler 12/01/2006
* After running this you can use the db4o Object Manager that you download from 
* http://www.db4o.com/ and you can explore the rim graph
* This class is specific for CDA messages as you can see by the explicit casts
* But any Object can be stored by db4o
* More interestingly try the descend and other "Native Queries" as well as "Query By Example"
* and "SODA Queries" that are part of db4o.
*/

package org.hl7.demo;

/**
 *
 * @author  phend
 */
 
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hl7.hibernate.Persistence;
import org.hl7.meta.MessageType;
import org.hl7.util.ApplicationContext;
import org.hl7.xml.parser.MessageContentHandler;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class demoDb4o implements ApplicationContext {

//String messageType = "POCD_HD000040";
//String messageFile = "etc\\SampleCDADocument.xml";
//String messageFile2 = "etc\\makeCDA.xml";


public Object getRimGraph(String messageType, String messageFile){
	Object graph = null;
	Object graph2 = null;
	try{
    org.hl7.meta.MessageTypeLoader<MessageType> mtl = org.hl7.meta.mif.MessageTypeLoaderAdapter.getInstance(); 
    MessageType mt = mtl.loadMessageType(messageType);
    File f = new File(messageFile);
    FileInputStream in = new FileInputStream(f);
			
    graph = MessageContentHandler.parseMessage(this, in, mt);
    System.out.println("graph is " + graph.toString());

       
  }catch(Exception e){e.printStackTrace();}
   return graph;
  }//getRimGraph
  
  public void storeCDA(org.hl7.rim.impl.DocumentImpl cda, ObjectContainer db){
  	

        try {            
            db.set(cda);
        System.out.println("Stored "+ cda);
        }
       catch(Exception e) {
            e.printStackTrace();
        }
  }
  
  public org.hl7.rim.impl.DocumentImpl  getCDA(ObjectContainer db){
  	//missed named now that there are more than one in the set
  	Query query=db.query();
        query.constrain(org.hl7.rim.impl.DocumentImpl.class);
        //query.descend("name").orderAscending();
        ObjectSet result=query.execute();
        //listResult(result);
        org.hl7.rim.impl.DocumentImpl cda = null;
        while(result.hasNext()){
          cda = (org.hl7.rim.impl.DocumentImpl)result.next();
          System.out.println("got class" + cda.getClass().getName());
        }
       // for now just return the last one
        return cda;       
    }
    
    public void descendQuery(ObjectContainer db){
    	Query query=db.query();
        query.constrain(org.hl7.rim.impl.DocumentImpl.class);
        // this next line will only return objects with id = "12234567"
        query.descend("_id").descend("_set").descend("_root").descend("_data").constrain("1234567");       
       ObjectSet result=query.execute();
       System.out.println("Now list result set from decendQuery");
      listResult(result);       
    }

    public void listResult(ObjectSet result){
    	System.out.println("Listing result set -----------------------");
    	System.out.println(result.size());
    	while(result.hasNext()){
    		System.out.println(result.next());
    	}		
    }	
   
   public List <org.hl7.rim.impl.DocumentImpl> nativeQ(ObjectContainer db){
   // a native query
   List <org.hl7.rim.impl.DocumentImpl> cdas = db.query(new Predicate<org.hl7.rim.impl.DocumentImpl>() {
    public boolean match(org.hl7.rim.impl.DocumentImpl cda) {
        return cda.getMoodCode() == org.hl7.types.enums.ActMood.Event;
    }
   });
   return cdas;
  }

   public List <org.hl7.rim.impl.PersonImpl> personQuery(ObjectContainer db){
   List <org.hl7.rim.impl.PersonImpl> people = db.query(new Predicate<org.hl7.rim.impl.PersonImpl>() {
    public boolean match(org.hl7.rim.impl.PersonImpl person) {
        return true;
    }
   }); 
   return people;
}   
  		
 
  public static void main(String args[]) {
  	        new File("db4odata.yap").delete();
        ObjectContainer db=Db4o.openFile("db4odata.yap");
  	demoDb4o db4o = new demoDb4o();
  	org.hl7.rim.impl.DocumentImpl rimmy = (org.hl7.rim.impl.DocumentImpl)db4o.getRimGraph("POCD_HD000040", "etc\\SampleCDADocument.xml");
  	//System.out.println("class:" + rimmy.getClass().getName());
  	db4o.storeCDA(rimmy, db);
  	org.hl7.rim.impl.DocumentImpl rimmy2 = (org.hl7.rim.impl.DocumentImpl)db4o.getRimGraph("POCD_HD000040", "etc\\makeCDA.xml");
  	db4o.storeCDA(rimmy2, db);
  	org.hl7.rim.impl.DocumentImpl gotarimmy = db4o.getCDA(db);
  	System.out.println("got a rimmy " + gotarimmy);
  	//Now try some different queries
  	//Query Bye Example
  	db4o.descendQuery(db);
  	List <org.hl7.rim.impl.DocumentImpl> cdas = db4o.nativeQ(db);
  	System.out.println("cdas: " + cdas);
	
	List<org.hl7.rim.impl.PersonImpl> people = db4o.personQuery(db);
	System.out.println("Persons: " + people);
  	db.close();
  	}

	// Begin implementing org.hl7.util.ApplicationContext 
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
}//class end

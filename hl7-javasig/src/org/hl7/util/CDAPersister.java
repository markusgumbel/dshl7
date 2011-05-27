

package org.hl7.util;

import org.hl7.hibernate.Persistence;
import org.hl7.rim.impl.RimObjectImpl;
/**
 *
 * @author phend
 * actually this will persist any rim graph whether it's a CDA or not
 */
public class CDAPersister {
    CDAPersister(){}
		// clazz not used. carried over from other code and can be used to see what kind of graph it is
		// with clazz.getName()
    	static Class clazz = null;
        
 public static String HibernateIt(Object graph){
                if (graph==null) return "rimGraph was null";
                String id = " no id yet";
            try{
		Persistence.instance().save(graph);
		clazz = graph.getClass();
                
		id = ((RimObjectImpl) graph).getInternalId();

		Persistence.instance().commit();
		Persistence.instance().close();

            }catch(Exception e){e.printStackTrace();}
            return id;
	}

}

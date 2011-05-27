/**
 * The simplest possible example of 
 * Using Hibernate Query Language.
 * @author Peter Hendler 3/29/2008
 */
package org.hl7.demo;

import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hl7.hibernate.Persistence;
import org.hl7.rim.*;
import org.hl7.rim.impl.*;
import org.hibernate.Query;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hl7.rim.*;
import org.hl7.types.*;

public class HibernateQueryDemo {

	/**
	 * @param args
	 */
	
	HibernateQueryDemo(){}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateQueryDemo hqd = new HibernateQueryDemo();
		if(args.length == 0){
			hqd.query("select new list( obs.value, role, player ) from ObservationImpl as obs inner join obs.participation as part inner join part.role as role inner join role.player as player" +     		
	      		"where obs.classCode like 'OBS' " );
		}else{
			hqd.query(args[0]);			
		}
	}
	
	public void query(String query){
		Session session = Persistence.getSessionFactory().openSession();
		Iterator resit = session.iterate(query);
                 showIt(resit);
		}

       
        public void showIt(Iterator resit){
		while(resit.hasNext()){
		  List list = (List)resit.next();
                  System.out.print("value " + list.get(0) + " : ");
                  System.out.print("root " +  list.get(1) + " : ");
                  System.out.print("ext " +  list.get(2) + "\n" );  
                }
                 
	}
	


}

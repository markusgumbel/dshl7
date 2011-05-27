/**
 * 8/28/2007
 * @author  Peter Hendler
 * a demonstration of calling the HL7 JavaSIG API from Groovy
 * In Groovy lib directory you must remove xerces-2.4.0.jar
 * and replace it with xercesImpl.jar from Java SIG lib directory
 * Copy all jar files from Java SIG lib to Groovy lib
 * Generate jsig.far from our ant jar and put it in Groovy lib
 * Remove xml-apis-1.0.b2.jar from Groovy lib
 * Remove xml-apis.jar which came from Java SIG lib
 * Groovy seems to find files if they are in it's src directory
 * which is where I put the mif files and message files
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import org.hl7.hibernate.Persistence;
import org.hl7.meta.MessageType;
import org.hl7.util.ApplicationContext;
import org.hl7.xml.parser.MessageContentHandler;
import org.hl7.demo.ContextForThis;
import groovy.swing.SwingBuilder;
import java.awt.FlowLayout;

graph = null;
swing = new SwingBuilder();

gui = swing.frame(title:'Groovy HPath Browser', size:[1000,500]) {
    panel(layout:new FlowLayout(FlowLayout.LEADING)) {
        panel(layout:new FlowLayout(FlowLayout.LEADING)) {           
		t1 = textField(text:"UUDD_MT999999",columns:40);
		label(text:"MessageType");
        }
	panel(layout:new FlowLayout(FlowLayout.LEADING)) { 	
        t2 = textField(text:"etc/rim-instance3.xml",columns:40);
		label(text:"MessageFile ");
	}
	panel(layout:new FlowLayout(FlowLayout.LEADING)) { 
		t3 = textField(text:"#subjectRelationship.target.#subjectParticipation.role.#subjectParticipation.act.#consumable.role.player.name[1][1]",columns:70);
		label(text:"HPath Expression");				
        }	
	panel(layout:new FlowLayout(FlowLayout.LEADING)) { 
		t5 = textArea(text:"First Load the RIM graph",columns:80,rows:15);		
        }        
    panel(layout:new FlowLayout(FlowLayout.LEADING)) {
	   	
            button(text:"Load RIM",
              actionPerformed: {
                messageType = t1.text;				
                messageFile = t2.text;				
                      org.hl7.meta.MessageTypeLoader mtl = org.hl7.meta.mif.MessageTypeLoaderAdapter.getInstance(); 
					  println "-------------------- ${mtl}";
                      MessageType mt = mtl.loadMessageType(messageType);
					  println "+++++++++++++++ got message type and it is ${mt}";
                      File f = new File(messageFile);
					  println "======== got messageFile";
                      FileInputStream ins = new FileInputStream(f);
					  println "============= got input stream";
					  ContextForThis cft = new ContextForThis();
                      graph = MessageContentHandler.parseMessage(cft, ins, mt);
                     t5.text = "RIM graph is loaded, Now execute HPath \n" + graph.toString();                     
              }
            )
	        button(text:"Execute HPath",
              actionPerformed: {
                 hpath = t3.text;
                 output = "";
                 t5.text =("Nothing Found: \n" + hpath);
                 org.hl7.hpath.Expression expr = org.hl7.hpath.Expression.valueOf(hpath);
                   for(Object item : expr.evaluate(graph)){
                   output += (item);
	               t5.text = output;
                   println(">>> " + item);
                   }				   
              } 
		    )
		}
    }
}
gui.show()

/**
 * 8/31/2007
 * @author Peter Hendler
 * a demonstration of calling the HL7 JavaSIG API from Groovy
 * In Groovy lib directory you must remove xerces-2.4.0.jar
 * and replace it with xercesImpl.jar from Java SIG lib directory
 * Copy all jar files from Java SIG lib to Groovy lib
 * Generate jsig.far from our ant jar and put it in Groovy lib
 * Remove xml-apis-1.0.b2.jar from Groovy lib
 * Remove xml-apis.jar which came from Java SIG lib
 * Groovy seems to find files if they are in it's src directory
 * which is where I put the mif files and message files
 *
 * the button closures were cut and pasted from CDAMaker.java
 * the text field "getters" try catch blocks and some variable type declarations
 * were removed but the rest of the code in the closures is more Java than Groovy
 * Also, need to learn better swing layout to line things up better
 */

//import java.io.File;
//import java.io.FileInputStream;
//import java.util.HashMap;
//import java.util.Map;

import org.hl7.hibernate.Persistence;
import org.hl7.meta.MessageType;
import org.hl7.util.ApplicationContext;
import org.hl7.xml.parser.MessageContentHandler;
import org.hl7.demo.ContextForThis;
import groovy.swing.SwingBuilder;
import java.awt.FlowLayout;

import org.hl7.demo.MakeCDA;
import org.hl7.rim.Document;
import org.hl7.types.*;

import org.hl7.meta.mif.*;
import org.hl7.meta.MessageType;
import org.hl7.meta.util.*;
import org.hl7.rim.*;
import org.hl7.rim.impl.*;
import org.hl7.types.*;
import org.hl7.types.impl.*;
import org.hl7.xml.parser.*;
import org.hl7.util.*;
import org.hl7.xml.*;
import org.hl7.xml.builder.*;
import org.hl7.xml.builder.impl.*;
import org.hl7.hpath.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

mcda = new MakeCDA();

def rim

swing = new SwingBuilder();

gui = swing.frame(title: 'Groovy HPath based CDAMaker', size: [1000, 500]) {
  panel(layout: new FlowLayout(FlowLayout.LEADING)) {
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
      button(text: "Load Default CDA",
              actionPerformed: {
                this.rim = mcda.makeCDA();
                ta2.text = ("rim is " + rim);
              })
    }
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
      t1 = textField(text: "Dr", columns: 4)
      label(text: "Auth Prefix")
      t2 = textField(text: "", columns: 10)
      label(text: "Auth First")
      t3 = textField(text: "", columns: 20)
      label(text: "Auth LastName")
      t4 = textField(text: "", columns: 4)
      label(text: "Auth Suffix")
      button(text: "Change Author",
              actionPerformed: {
                org.hl7.rim.Document cda = (org.hl7.rim.Document) rim;
                docNameBag = null;
                docNameBag = DatatypeTool.EntityNameTool.setPrefixName(docNameBag, t1.text);
                docNameBag = DatatypeTool.EntityNameTool.setGivenName(docNameBag, t2.text);
                docNameBag = DatatypeTool.EntityNameTool.setFamilyName(docNameBag, t3.text);
                docNameBag = DatatypeTool.EntityNameTool.setSuffixName(docNameBag, t4.text);

                org.hl7.hpath.Expression expr = org.hl7.hpath.Expression.valueOf("participation[2].role.player");
                Evaluation playerEval = expr.evaluate(cda);

                EvaluationIterator evalIter = playerEval.iterator();

                PersonImpl author = (PersonImpl) evalIter.next();
                author.setName(docNameBag);
                ta2.text = ("author name replaced");
              }
      )
    }
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
      t5 = textField(text: "Mr", columns: 4)
      label(text: "Subj Prefix")
      t6 = textField(text: "", columns: 10)
      label(text: "Subj First")
      t7 = textField(text: "", columns: 20)
      label(text: "Subj LastName")
      t8 = textField(text: "", columns: 4)
      label(text: "Auth Prefix")
      button(text: "Change Subject",
              actionPerformed: {
                org.hl7.rim.Document cda = (org.hl7.rim.Document) rim;
                patNameBag = null;
                patNameBag = DatatypeTool.EntityNameTool.setPrefixName(patNameBag, t5.text);
                patNameBag = DatatypeTool.EntityNameTool.setGivenName(patNameBag, t6.text);
                patNameBag = DatatypeTool.EntityNameTool.setFamilyName(patNameBag, t7.text);
                patNameBag = DatatypeTool.EntityNameTool.setSuffixName(patNameBag, t8.text);

                org.hl7.hpath.Expression expr = org.hl7.hpath.Expression.valueOf("participation[1].role.player");
                Evaluation playerEval = expr.evaluate(cda);

                EvaluationIterator evalIter = playerEval.iterator();

                PersonImpl patient = (PersonImpl) evalIter.next();
                patient.setName(patNameBag);
                ta2.text = ("patient name replaced");

              }
      )
    }
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
      ta1 = textArea(text: "Enter Observation Text", columns: 80, rows: 5)
    }
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
      button(text: "Append new observation text",
              actionPerformed: {
                //--------------------------------------------------
                // not sure I even have to declare these variables
                // I will remove all try catch blocks.
                // otherwise this is pure java taken from the original CDAMaker.java


                org.hl7.rim.Document cda = (org.hl7.rim.Document) rim;
                org.hl7.hpath.Expression expr = org.hl7.hpath.Expression.valueOf("outboundRelationship.target.outboundRelationship.target[1]");
                Evaluation docSecEval = expr.evaluate(cda);

                EvaluationIterator evalIter = docSecEval.iterator();
                // finally we get the docSection<Act>
                ActImpl docSection = (ActImpl) evalIter.next();
                ta2.text = ("docSection " + docSection.getCloneCode());
                // now we make a subcomponent<ActRelationship>
                org.hl7.rim.ActRelationship entry = null;

                entry = (org.hl7.rim.ActRelationship) RimObjectFactory.getInstance().createRimObject("ActRelationship");
                // fake codes sorry
                entry.setCloneCode(CSimpl.valueOf("entry", "1.22.333.4444"));
                CS typeCode = CSimpl.valueOf("COMP", "1.22.333.4444");
                entry.setTypeCode(typeCode);

                ta2.text = ("entry " + entry.getCloneCode());
                // now we make an observation<Act>
                Observation observation = null;
                // this formerly in try catch block
                observation = (org.hl7.rim.Observation) RimObjectFactory.getInstance().createRimObject("Observation");
                // fake codes sorry just for demo
                observation.setCloneCode(CSimpl.valueOf("observation", "1.22.333.4444"));
                observation.setCode(CDimpl.valueOf("44100-6", "2.16.840.1.113883.6.1"));
                observation.setMoodCode(CSimpl.valueOf("EVN", "1.22.333.4444"));
                ST title = STjlStringAdapter.valueOf("History");

                ST text = STjlStringAdapter.valueOf("\n " + ta1.text + "\n ");
                observation.setTitle(title);  //this didn't show up
                observation.setText(text);
                ta2.text = ("observation " + observation.getCloneCode());
                // now add the observation to the subcomponent
                entry.setTarget(observation);
                // and finally add the subcomponent to the docSection
                docSection.addOutboundRelationship(entry);
                ta2.text = ("section Added");
              })
    }
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
      button(text: "Generate new CDA instance",
              actionPerformed: {
                mcda.buildMessage(rim);
                ta2.text = ("New version of XML written to output file and System.out");
              })
    }
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
    }
    panel(layout: new FlowLayout(FlowLayout.LEADING)) {
      ta2 = textArea(text: "Debug info printed here", columns: 80, rows: 5)
    }
  }
}
gui.show()
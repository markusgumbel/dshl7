/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hl7.demo;

import org.hl7.util.*;
import java.io.*;
import org.hl7.demo.*;

/**
 *
 * @author phend
 */
public class TestNewUtils {
    TestNewUtils(){}
    public static void main(String[] args) {
        try{
        TestNewUtils tnu = new TestNewUtils();
        File f = new File("etc/cda3.xml");
        System.out.println("--" + f.getAbsolutePath());
        String parameter = StringFromFile.readFileAsString("etc/cda2.xml");
        String result = tnu.operation(parameter);
        System.out.println (result);
        }catch(Exception e){e.printStackTrace();}
    }
    public String operation(String message) {
        String id = "cda not persisted yet";
        String rimstr = "no rim yet";
        try{
        
        
            Object rim = MessageLoader.LoadMessage("POCD_HD000040", message);
        if (rim!=null){
        rimstr = rim.toString();
        }
        id = CDAPersister.HibernateIt(rim);

        }catch(Exception e){e.printStackTrace();}
                return ("------------------------------------------------cda persistance id: " + id + "\n\nrim " + rimstr + "\n\n") ;
    }

    

}

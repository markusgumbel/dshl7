/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hl7.util;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author phend
 * Usedin conjunction with MessageLoader.java and CDAPersister.java
 * use this to get a string from a file path, then feed that to MessageLoader to get a rim graph
 * then feed the rim graph to the CDAPersister to save it in the database
 */

	
	public class StringFromFile{
	
	/** @param filePath the name of the file to open. Not sure if it can accept URLs or just filenames. Path 	handling could be better, and buffer sizes are hardcoded
    */ 
    public static String readFileAsString(String filePath)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

}

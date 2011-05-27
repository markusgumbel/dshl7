/*  TRAXPipe - a simple XMLFilter based transformer pipeline.
 *
 *  Copyright (C) 2004, Regenstrief Institute. All rights reserved.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Written and maintained by Gunther Schadow <gschadow@regenstrief.org>
 *  Regenstrief Institute for Health Care
 *  1050 Wishard Blvd., Indianapolis, IN 46202, USA.
 *
 * $Id: TRAXPipe.java 12316 2008-01-05 23:16:54Z gschadow $
 */
package org.regenstrief.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/** Apply a pipeline of XSLT transforms to one or more sources

<p>System properties used: 
<dl>
<dt>javax.xml.transform.TransformerFactory</dt>
<dt>org.xml.sax.driver</dt>
</dl>
    
<p>The first XMLReader can be specified to use a special class
specified with the -x option.

@author Gunther Schadow
@version $Id: TRAXPipe.java 12316 2008-01-05 23:16:54Z gschadow $
*/
public class TRAXPipe {
  static Map variables = new HashMap();

  public static void main(String args[]) 
    throws Exception 
    {
      XMLReader reader = null;
      XMLReader xslreader = null;
      URIResolver resolver = null;
      String sourceParser = null;
      String styleParser = null;

      int argi = 0;

      String inputName = null;
      String outputName = null;
    
    optLoop:
      while(argi<args.length && args[argi].charAt(0) == '-') {
	switch(args[argi].charAt(1)) {
	case 'x':	argi++;
	  sourceParser = args[argi++];
	  reader = XMLReaderFactory.createXMLReader(sourceParser);
	  break;
	case 'y':	argi++;
	  styleParser = args[argi++];
	  break;
	case 'r':	argi++;
	  resolver = (URIResolver)Class.forName(args[argi++]).newInstance();
	  break;
	case 'i': argi++;
	  inputName = args[argi++];
	  break;
	case 'o': argi++;
	  outputName = args[argi++];
	  break;
	case '-': argi++;
	  break optLoop;
	default:
	  throw new IllegalArgumentException("args: -i src -o dest [-r resolver] [-x reader] [-y xslreader] xfrm ...");
	}
      }

      if(reader == null)
	reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();

      reader.setFeature("http://xml.org/sax/features/namespaces",true);
      reader.setFeature("http://xml.org/sax/features/namespace-prefixes",false);

      SAXTransformerFactory tfact 
	= (SAXTransformerFactory)TransformerFactory.newInstance();

      if(resolver != null)
	tfact.setURIResolver(resolver);

      if(sourceParser != null)
	tfact.setAttribute("http://saxon.sf.net/feature/sourceParserClass", sourceParser);

      if(styleParser != null)
	tfact.setAttribute("http://saxon.sf.net/feature/styleParserClass", styleParser);

      // build filter chain of all but the last transform
      int argiLastTransform = argi;
      for(int i=argi; i < args.length; i++) {
	if(args[i].indexOf('=') == -1)
	  argiLastTransform = i;
      }
    
      while(argi < argiLastTransform) {
	String arg = args[argi];
	int equalSignPosition = arg.indexOf('=');      
	if(equalSignPosition == -1) {
	  System.err.println("next transform: " + arg);
	  if(arg.startsWith("class:")) {
	    XMLFilter filter = (XMLFilter)Class.forName(arg.substring("class:".length())).newInstance();
	    filter.setParent(reader);
	    reader = filter;
	    //} else if(arg.startsWith("schema:")) {
	    //	arg = arg.substring("schema:".length());
	    //	XMLFilter filter = new XMLValidator(arg);
	    //	filter.setParent(reader);
	    //	reader = filter;
	  } else {
	    XMLFilter filter = new XMLMultiTransformer(tfact, tfact.newTemplates(new StreamSource(arg)));
	    ((XMLMultiTransformer)filter).setParameter("TRAXPipe.variables",variables);
	    filter.setParent(reader);
	    reader = filter;
	  }
	} else {
	  String parameterName = arg.substring(0,equalSignPosition);
	  String parameterValue = arg.substring(equalSignPosition+1);
	  if(reader instanceof net.sf.saxon.Filter) {
	    Transformer transformer = ((net.sf.saxon.Filter)reader).getTransformer();
	    transformer.setParameter(parameterName, parameterValue);
	  } else if(reader instanceof XMLMultiTransformer) {
	    ((XMLMultiTransformer)reader).setParameter(parameterName, parameterValue);
	  } else 
	    System.err.println("WARNING: parameter ignored: " + parameterName + "=" + parameterValue);
	}      
	argi++;
      }

      // here is the last transform
      XMLMultiTransformer transformer = null;
      if(argi < args.length) {
	if(args[argi].equals("-I")) {
	  System.err.println("last transform: IDENTITY");
	  transformer = new XMLMultiTransformer(tfact,null);
	  transformer.setOutputProperty(OutputKeys.INDENT,"yes");	
	  transformer.setParameter("TRAXPipe.variables",variables);
	} else {
	  String arg = args[argi];
	  System.err.println("last transform: " + arg);
					
	  transformer = new XMLMultiTransformer(tfact,tfact.newTemplates(new StreamSource(arg)));
	  transformer.setParameter("TRAXPipe.variables",variables);
					
	  // set any remaining parameters
	  while(++argi < args.length) {
	    arg = args[argi];
	    int equalSignPosition = args[argi].indexOf('=');
	    if(equalSignPosition > -1)
	      transformer.setParameter(arg.substring(0,equalSignPosition), arg.substring(equalSignPosition+1));
	  }
	}
      }

      InputSource input = null;
      Result result = null;
    
      File inputDirectory = null;
      File outputDirectory = null;

      if(inputName == null || inputName.equals("-")) {
	input = new InputSource(System.in);
      } else {
	File file = new File(inputName);
	if(file.exists()) {
	  if(file.isDirectory()) {
	    inputDirectory = file;
	  } else {
	    input = new InputSource(new BufferedInputStream(new FileInputStream(file)));
	    input.setSystemId(file.toURI().toString());
	  }
	} else { // last resort
	  input = new InputSource(inputName);
	}
      }

      if(outputName == null || outputName.equals("-")) {
	result = new StreamResult(System.out);
      } else {
	File file = new File(outputName);
	if(file.isDirectory()) {
	  outputDirectory = file;
	} else {
	  result = new StreamResult(file.toURI().toString());
	}
      }
    
      if(input != null && result != null)
	transformer.transform(new SAXSource(reader, input), result);
      else if(inputDirectory != null && outputDirectory != null) {
	File files[] = inputDirectory.listFiles();
	for(int i = 0; i < files.length; i++) {
	  File inputFile = files[i];
	  if(inputFile.isFile()) {
	    input = new InputSource(new BufferedInputStream(new FileInputStream(inputFile)));
	    input.setSystemId(inputFile.toURI().toString());
	    result = new StreamResult(new File(outputDirectory, inputFile.getName()).toURI().toString());
	    
	    System.err.println("next source: " + inputFile.getName() + " variables: " + variables);
	    
	    try {
	      transformer.transform(new SAXSource(reader, input), result);
	    } catch(Exception ex) {
	      ex.printStackTrace();
	    }
	  }
	}
      } else
	throw new IllegalArgumentException("input and output must both be directories or not");
    }
}

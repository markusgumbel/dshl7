package org.hl7.xml;
/*
The contents of this file are subject to the Health Level-7 Public
License Version 1.0 (the "License"); you may not use this file
except in compliance with the License. You may obtain a copy of the
License at http://www.hl7.org/HPL/hpl.txt.

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
the License for the specific language governing rights and
limitations under the License.

The Original Code is all this file.

The Initial Developer of the Original Code is Gunther Schadow.
Portions created by Initial Developer are Copyright (C) 2002-2004
Health Level Seven, Inc. All Rights Reserved.

Contributor(s): Steven Gitterman, Brian Keller

Revision: $Id: SchemaValidator.java,v 1.2 2004/11/25 20:58:29 gschadow Exp $
*/
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/** A simple schema validator using the JAXP magic. */
public class SchemaValidator
{
  //Constants when using XML Schema for SAX parsing.
  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage"; 
  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema"; 
  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  public static class ValidationResult extends DefaultHandler { 
    public static final int LVL_WARNING=0;
    public static final int LVL_ERROR=1;
    public static final int LVL_FATAL_ERROR=2;
    private static final String[] _levelName = { "WARNING", "ERROR", "FATAL" };

    private String _source;
    private int _thresholdLevel = LVL_WARNING;
		private int _numberOfErrors = 0;

    public ValidationResult(String source, int thresholdLevel) {  
      _source = source; 
      _thresholdLevel = thresholdLevel;
    }

    public void fatalError(SAXParseException ex) { log(ex, LVL_FATAL_ERROR); }
    public void error(SAXParseException ex) { log(ex, LVL_ERROR); }
    public void warning(SAXParseException ex) { log(ex, LVL_WARNING); }

    private void log(SAXParseException ex, int level) {
      if(level >= _thresholdLevel)
				System.err.println(_source+":"+ex.getLineNumber()+":"+_levelName[level]+":"+ex.toString());
			if(level >= LVL_ERROR)
				_numberOfErrors++;
    }

		public String getSource() { return _source; }
		public int getNumberOfErrors() { return _numberOfErrors; }
		public boolean hasErrors() { return _numberOfErrors > 0; }
  }

  public static ValidationResult validate(String source, String schema) {
    return validate(source, schema, ValidationResult.LVL_WARNING);
  }
	
  public static ValidationResult validate(String source, String schema, int thresholdLevel) {
    try {
      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      parserFactory.setNamespaceAware(true);
      parserFactory.setValidating(true);
      SAXParser parser = parserFactory.newSAXParser();
      parser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
      parser.setProperty(JAXP_SCHEMA_SOURCE, schema);

      ValidationResult validationResult = new ValidationResult(source, thresholdLevel);
			if(source.startsWith("<"))
				parser.parse(new java.io.ByteArrayInputStream(source.getBytes()), validationResult);
			else
				parser.parse(source, validationResult);
			 
			return validationResult;
    } catch(Throwable ex) {
      throw new Error(ex);
    }
  }

  public static void main(String args[]) {
    if(args.length == 3)
      SchemaValidator.validate(args[0], args[1], Integer.parseInt(args[2]));
    else
      SchemaValidator.validate(args[0], args[1]);
  }
}


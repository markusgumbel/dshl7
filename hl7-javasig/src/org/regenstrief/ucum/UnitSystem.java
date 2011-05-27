/*
 * Copyright (c) 1998-2003 Regenstrief Institute.  All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA */
package org.regenstrief.ucum;

import java.net.URL;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.SAXParserFactory;
import org.hl7.types.REAL;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.UIDnull;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/** 
  Loads the Unit definitions from an XML document accessed from
  a certain URL.

  This class is a singleton whose object at this time acts as
  a ContentHandler. The static method loadUnits is called from some
  static context and causes the units definition to be parsed and 
  the units created.

  FIXME: this is too much static stuff. I should probably call the
  UnitLoader a "UnitSystem", not have it be a singleton, but have it
  contain all those static data structures like the dimension
  definitions, the prefix map, and the unit maps. I don't really 
  want to suggest that there should ever be multiple unit systems 
  used at the same time.

  @author Gunther Schadow.
  @version $Id: UnitSystem.java 5652 2007-03-30 15:35:44Z crosenthal $
*/
public final class UnitSystem implements ContentHandler {

  private UnitSystem() { }

  private static UnitSystem SYSTEM = null;
  
  private static UID _codeSystem = null;
  private static ST _codeSystemName = null;
  private static ST _codeSystemVersion = null;
  static boolean _initializing = false;

  public static UnitSystem getInstance() {
    if(SYSTEM == null) {
      URL url = Thread.currentThread().getContextClassLoader().getResource("units.xml"); 
      if(url == null)
	throw new Error("units.xml file not found in classpath");
      loadUnits(url);
    }
    assert SYSTEM != null;
    return SYSTEM;
  }

  private static void loadUnits(URL url) 
    throws Exception
  {
    try { 
      SYSTEM = new UnitSystem();
      XMLReader reader 
	= SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      reader.setFeature( "http://xml.org/sax/features/namespaces",true );
      reader.setFeature( "http://xml.org/sax/features/namespace-prefixes",false );
      reader.setContentHandler(SYSTEM);
      
      reader.parse(new InputSource(url.openStream()));
    } catch(Throwable ex) {
      throw new Exception(url.toString()+": "+ex.getMessage(), ex);
    }
  }

  public static class Exception extends RuntimeException {
    public Exception(Throwable cause) { super(cause); }
    public Exception(String message, Throwable cause) {super(message, cause);}
  }


  // These are the variables that used to be statically owned by the
  // various classes, Dimension, Prefix, Unit. We collect them here 
  // with package protection as a first step to reorganize this 
  // mess of static variables.

  /** The number of components in the dimension vectors. */
  static int _baseVectorSize = 10;

  /** The array of base unit strings, e.g., "m", "s", "g", etc., this
      is linearly searched with interned strings, should be the
      fastest approach that way. Used also to create a canonical
      form of a unit string. */
  static String _baseUnitString[] = null;

  /** The array of base dimension strings, e.g., "L", "T", "M" etc.,
      used to create string respresentation of the dimension. */
  static String _baseDimensionString[] = null;

  /** An array of ready-made base dimensions, so we don't have to 
      create those all the time. */
  static Dimension _baseDimension[] = null;

  /** The initialization method that sets the maximum dimensionality.
      This method can only be called once (when the unit system file 
      is loaded.)
  */
  public static int initialize(int max) {
    assert UnitSystem._baseVectorSize != 0 : 
      "initialize can only be called once";
    assert max > 0 : "maximum dimensionality must be greater than 0";

    UnitSystem._baseVectorSize = max;
    UnitSystem._baseUnitString = new String[UnitSystem._baseVectorSize];
    UnitSystem._baseDimensionString = new String[UnitSystem._baseVectorSize];
    UnitSystem._baseDimension = new Dimension[UnitSystem._baseVectorSize];
    
    return max;
  }

  /** Initialization counter, used when the base is initialized. */
  private static int _initializationIndex = 0;
  
  /** Initialize a specific base dimension. */
  public static Unit initializeBase(String baseUnitString, 
				    String baseDimensionString) {
  	
    assert UnitSystem._baseVectorSize > 0: "not initialized";
    assert baseUnitString != null: "baseUnitString must not be null";
    assert baseUnitString.length() != 0: "baseUnitString must not be empty"; 
    assert baseDimensionString != null: "baseDimensionString must not be null";
    assert baseDimensionString.length() != 0: 
      "baseDimensionString must not be empty"; 
    assert UnitSystem._initializationIndex < UnitSystem._baseVectorSize :
      "too many components";
    
    UnitSystem._baseUnitString[UnitSystem._initializationIndex]
      = baseUnitString.intern();
    UnitSystem._baseDimensionString[UnitSystem._initializationIndex]
      = baseDimensionString.intern();
    Dimension baseDimension
      = new Dimension(UnitSystem._initializationIndex);    
    UnitSystem._baseDimension[UnitSystem._initializationIndex]
      = baseDimension;
    
    Unit toBeReturned 
      = defineBaseUnit(baseUnitString, "1", baseDimension);

    UnitSystem._initializationIndex ++;

    return toBeReturned;
  }

  public static Iterator<Dimension> baseIterator() {
    return Arrays.asList(UnitSystem._baseDimension).iterator();
  }
  
  /** A map relating Strings to Prefix values. */
  static Map<String,Prefix> _stringPrefixMap 
    = new IdentityHashMap<String,Prefix>();
  
  /** An iterator over all prefixes. This is mainly used for 
      testing purposes.
      
      @deprecated there should be no need for this apart from testing!
  */
  public static Iterator<Prefix> prefixIterator() {
    return UnitSystem._stringPrefixMap.values().iterator();
  }

  /** Define a new prefix. This is only needed for the initialization,
      i.e., loading the unit definitions. It is package visible so that
      I can use it from an XML content handler inside this package.
  */
  private static Prefix definePrefix(ST code, REAL value) {
    return new Prefix(code, value);
  }
  
  /** Define a new prefix from stings. This is only needed if I want 
      to define prefixes from XSLT script. As soon as I use my own 
      XML parser for the unit definitions, I can get rid of this.

      @deprecated do not try to define new prefixes at runtime.
  */
  public static Prefix definePrefix(String code, String value) {
    return new Prefix(ValueFactory.getInstance().STvalueOf(code),
											ValueFactory.getInstance().REALvalueOfLiteral(value));
  }
  
  /** A map relating Strings to Unit values. */
  static Map<String,Unit> _stringUnitMap 
    = new IdentityHashMap<String,Unit>();

  /** An iterator over all cached units. Useful for analysis or testing.

      @deprecated use only for testing or analysis.
  */
  public static Iterator<Unit> unitsIterator() {
    return UnitSystem._stringUnitMap.values().iterator();
  }
  
  /** Define a new base unit. 
			This is used to define the first set of base units.
  */
  private static Unit defineBaseUnit(ST code, REAL magnitude, Dimension dimension) {
    return new Unit(code, magnitude, dimension, Unit.FL_METRIC|Unit.FL_ATOMIC|Unit.FL_BASE);
  }
  
  /** Define a new base unit. This is used from Dimension to define the 
      first set of base units.
  */
  private static Unit defineBaseUnit(String code, String magnitude, Dimension dimension) {
    return defineBaseUnit(ValueFactory.getInstance().STvalueOf(code.intern()),
													ValueFactory.getInstance().REALvalueOfLiteral(magnitude),
													dimension);
  }

  /** Define a new unit. 
			Use this only for testing purposes from the XSLT script.
			
      @deprecated don't attempt to define new units on the fly.
  */
  public static Unit defineUnit(String code, String magnitude, String unit,	boolean metric) {
  	ValueFactory vf = ValueFactory.getInstance();
    ST st = vf.STvalueOfLiteral(code);
    Unit b = Unit.valueOf(unit);
    Unit u = new Unit(st, vf.REALvalueOfLiteral(magnitude), Unit.valueOf(unit), (metric?Unit.FL_METRIC:0)|Unit.FL_ATOMIC);
    return u;
  }

  /** A map relating Strings to FunctionPair objects. */
  static Map<String,FunctionPair> _stringFunctionPairMap = new IdentityHashMap<String,FunctionPair>();

  /** Define a new function pair. Use this only for testing purposes 
`     from the XSLT script.

      @deprecated don't attempt to define new units on the fly.
  */
  public static FunctionPair defineFunctionPair(String code, String clazz) {
    FunctionPair functionPair = null;

    try {
      functionPair = (FunctionPair)Class.forName(clazz).newInstance(); 
    } catch(ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    } catch(InstantiationException ex) {
      throw new RuntimeException(ex);
    } catch(IllegalAccessException ex) {
      throw new RuntimeException(ex);
    }

    code = code.toLowerCase().intern();
    FunctionPair oldFp 
      = UnitSystem._stringFunctionPairMap.put(code, functionPair);
    assert oldFp==null : "function " + code + " redefined";
    
    return functionPair;
  }

  /** Define a new special unit. 
			Use this only for testing purposes from the XSLT script.

      @deprecated don't attempt to define new units on the fly.
  */
  public static Unit defineSpecialUnit(String code, String magnitude, String unit, String functionPairName, boolean metric) {
    ValueFactory vf = ValueFactory.getInstance();
    functionPairName = functionPairName.toLowerCase().intern();
    FunctionPair functionPair = UnitSystem._stringFunctionPairMap.get(functionPairName);
    if(functionPair == null)
      throw new IllegalArgumentException("undefined function pair " + functionPairName + " in " + _stringFunctionPairMap);
    return new Unit(vf.STvalueOfLiteral(code), vf.REALvalueOfLiteral(magnitude), Unit.valueOf(unit), functionPair, (metric?Unit.FL_METRIC:0)|Unit.FL_ATOMIC);
  }

  /* ContentHandler interface */

  public void startElement(String namespaceURI, String localName,
			   String qName, Attributes atts)  
    throws SAXException
  {
    if(localName == "units") {
      String codeSystemString = atts.getValue("codeSystem");      
      UnitSystem._codeSystem = ValueFactory.getInstance()
				.UIDvalueOfLiteral(codeSystemString);      
      String codeSystemNameString = atts.getValue("codeSystemName");      
      if(codeSystemNameString != null)
				UnitSystem._codeSystemName = ValueFactory.getInstance()
					.STvalueOfLiteral(codeSystemNameString);      
      String codeSystemVersionString = atts.getValue("codeSystemVersion");
      if(codeSystemVersionString != null)
				UnitSystem._codeSystemVersion = ValueFactory.getInstance()
					.STvalueOfLiteral(codeSystemVersionString);      
    } else if(localName == "base") {
      UnitSystem.initialize(Integer.parseInt(atts.getValue("max")));
    } else if(localName == "base-unit") {
      UnitSystem.initializeBase(atts.getValue("code"),
																atts.getValue("dimension"));
    } else if(localName == "prefix") {
      definePrefix(atts.getValue("code"), atts.getValue("value"));
    } else if(localName == "function-pair") {
      defineFunctionPair(atts.getValue("code"), atts.getValue("class"));
    } else if(localName == "unit") { 
      defineUnit(atts.getValue("code"), 
								 atts.getValue("magnitude"),
								 atts.getValue("canonical"),
								 atts.getValue("metric").equals("yes"));
    } else if(localName == "special-unit") { 
      defineSpecialUnit(atts.getValue("code"), 
												atts.getValue("magnitude"),
												atts.getValue("canonical"),
												atts.getValue("function-pair"),
												atts.getValue("metric").equals("yes"));
    } else {
      throw new SAXException("schema error, illegal element " + localName);
    }
  }
  public void endElement(String namespaceURI,
			 String localName, String qName) { }
  public void characters(char[] ch, int start, int length) { }
  public void startDocument() {}
  public void endDocument() { }
  public void startPrefixMapping(String prefix, String uri) {}
  public void endPrefixMapping(String prefix) {}
  public void ignorableWhitespace(char[] ch, int start, int length) {}
  public void processingInstruction(String target, String data) {}
  public void setDocumentLocator(org.xml.sax.Locator locator) {}
  public void skippedEntity(String name) {}

  public static void main(String args[]) 
    throws Exception
  {
    getInstance();

    Unit unit = Unit.valueOf(args[0]);
    System.out.println(unit.code() 
		       + " " + unit.magnitude()
		       + " " + unit.dimension().toUnitString()
		       + " " + unit.dimension());
  }


  /** For the CS interface of Unit. */
  public static UID codeSystem() {
    if(_codeSystem == null)
      _codeSystem = UIDnull.NI;
    return _codeSystem;
  }
  /** For the CS interface of Unit. */
  public static ST codeSystemVersion() {
    if(_codeSystemVersion == null)
      _codeSystemVersion = STnull.NI;
    return _codeSystemVersion;
  }
  /** For the CS interface of Unit. */
  public static ST codeSystemName() {
    if(_codeSystemName == null)
      _codeSystemName = STnull.NI;
    return _codeSystemName;
  }
}

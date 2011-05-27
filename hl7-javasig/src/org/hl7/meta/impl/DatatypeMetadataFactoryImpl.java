/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is .
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */

package org.hl7.meta.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.hl7.meta.Datatype;
import org.hl7.meta.DatatypeMetadataFactory;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.types.ANY;
import org.hl7.util.DatatypeAnalyzer;
import org.hl7.util.DatatypeAnalyzer.AnalysisException;

/**
 * Creates descendants of Datatype based on the passed string.
 * 
 * @author Skirmantas Kligys
 */
public class DatatypeMetadataFactoryImpl implements DatatypeMetadataFactory
{
  // -------------------------------------------------------------------------
  private static final DatatypeMetadataFactory INSTANCE = new DatatypeMetadataFactoryImpl();

  // -------------------------------------------------------------------------
  public static DatatypeMetadataFactory instance()
  {
    return INSTANCE;
  }

  // -------------------------------------------------------------------------
  // BNF grammar:
  //
  // <letter> ::= "a".."z" | "A".."Z"
  // <identifier> ::= <letter>...
  // <datatype> ::= <simple-datatype> | <parametrized-datatype-1> |
  // <parametrized-datatype-2>
  // <simple-datatype> ::= <identifier>
  // <parametrized-datatype-1> ::= <identifier> "<" <datatype> ">"
  // <parametrized-datatype-2> ::= <identifier> "<" <datatype> "," datatype
  // ">"


  // -------------------------------------------------------------------------
  private String getIdentifier(BufferedReader in) throws IOException
  {
    skipWhitespace(in);

    // I know, I know, should really deal with indices and substrings of the
    // original string.
    StringBuffer sb = new StringBuffer();
    while (isLetter((char) peek(in)))
      sb.append((char) in.read());
    return sb.toString();
  }

  // -------------------------------------------------------------------------
  private Datatype getDatatype(BufferedReader in, String s) throws IOException, UnknownDatatypeException
  {
    skipWhitespace(in);
    String name = getIdentifier(in);

    skipWhitespace(in);
    if (name.equals("GTS")) {
      // Special on-the-fly translation of GTS -> SET<TS>.
      return new ParametrizedDatatypeImpl("SET", new SimpleDatatypeImpl("TS"));
    } else if (peek(in) != '<') {
      // Simple data type.
      return new SimpleDatatypeImpl(name);
    } else {
      // Parametrized data type, not clear yet, with 1 parameter or 2.
      
      // Skip "<".
      in.read();
      
      // Extract 1st parameter.
      Datatype dt1 = getDatatype(in,s);
      
      // Determine, is there a second parameter.
      skipWhitespace(in);
      if (peek(in) == '>') {
	// one parameter
	
	// Skip '>'.
	in.read();
	
	return new ParametrizedDatatypeImpl(name, dt1);
      } else if (peek(in) == ',') {
	// Skip "<".
	in.read();
	
	// Extract 2nd parameter.
	Datatype dt2 = getDatatype(in,s);
	
	// Ensure ">".
	if ((char) in.read() != '>')
	  throw new UnknownDatatypeException(s);

	return new ParametrizedDatatypeImpl(name, dt1, dt2);
      } else
	throw new UnknownDatatypeException(s);
    }
  }

  // -------------------------------------------------------------------------
  /**
   * Uses recursive descent parser to parse data type specification. Start
   * with <code>&lt;</code> and end with <code>&gt;</code> is
   * ParametrizedDatatype, others is SimpleDatatype
   * 
   * @param s
   *            data type sepcification
   * @return a structured data type description
   * @throws UnknownDatatypeException
   *             if parsing fails
   */
  public Datatype create(String s) throws UnknownDatatypeException
  {
    if(s.equals("LIST"))
      throw new Error("A list must have a parameter type!");
    else if (s.equals("RTO")) // Special on-the-fly translation of RTO -> RTO<QTY,QTY>.
      return new ParametrizedDatatypeImpl("RTO", new SimpleDatatypeImpl("QTY"), new SimpleDatatypeImpl("QTY"));
    // FIXME: should have thrown a warning to make sure the root cause of this gets fixed at some point

    BufferedReader in = new BufferedReader(new StringReader(s));
    try {
      Datatype dt = getDatatype(in, s);

      // Ensure the string is finished.
      skipWhitespace(in);
      if (peek(in) != -1)
	throw new UnknownDatatypeException(s);

      return dt;
    } catch (IOException ex) {
      throw new Error(ex);
    } finally {
      try { in.close(); } catch (IOException ignore) { }
    }
  }

  public Datatype create(String name, List<Datatype> parameters) throws UnknownDatatypeException {
    if(parameters == null || parameters.size() == 0)
      if(!name.equals("GTS") && !name.equals("RTO"))
				return new SimpleDatatypeImpl(name);
      else
				return create(name);
    else
      return new ParametrizedDatatypeImpl(name, parameters);
  }

  /**
   * Parse xsi:type data type specification.
   * <code>_</code> as seperator. Only support xx, xx_xx, and xx_xx_xx
   * format
   *
   * @param s
   *            data type sepcification
   * @return a structured data type description
   * @throws UnknownDatatypeException
   *             if parsing fails
   *
   * TODO
   * Parameters can nest and this doesn't handle that nesting.
   * For instance, you could have IVL<RTO<PQ,PQ>> or PIVL<PPD<TS>>
   * which would come out in xsi:type as IVL_RTO_PQ_PQ and
   * PIVL_PPD_TS respectively. This needs to actually parse the string.
   * Probably should make a StringTokenizer with "_" as the delimiter,
   * throw exceptions on any space character and other weird glitch-character.
   *
   * Then parse with knowledge of how many parameters the types have:
   *  IVL 1
   *  PIVL 1
   *  RTO 2
   *  PQ 0

   static Datatype parseXsiType(StringTokenizer tokenizer) {
   if(tokenizer.hasNext()) {
   String token = tokenizer.next();
   int numberOfFormalParameters = numberOfFormalParameters(token);
   if(numberOfFormalParameters == 0)
   return new SimpleDatatypeImpl(token);
   else {
   List<Datatype> parameters;
   for(int i = 0; i < numberOfFormalParameters; i++)
   parameter.set(i) = parseXsiType(tokenizer);
   return new ParametrizedDatatypeImpl(token, parameters);
   }
   } else
   throw new SyntaxError("expected data type parameter not provided"); }

   *
   *
   *
   */
  public Datatype createByXsiType(String s) throws UnknownDatatypeException {
		if (s == null) throw new UnknownDatatypeException("Create the data type by xsi type'" + s + "' failed");
    if(s.startsWith("SXCM_"))
      return createByXsiType(s.substring(5));
		if(s.equals("RTO"))
			s = "RTO_QTY_QTY";		
    String[] ss = s.split("_");
    if (ss.length == 1) return new SimpleDatatypeImpl(s);
    if (ss.length == 2)	return new ParametrizedDatatypeImpl(ss[0], new SimpleDatatypeImpl(ss[1]));
    if (ss.length == 3) return new ParametrizedDatatypeImpl(ss[0], new SimpleDatatypeImpl(ss[1]), new SimpleDatatypeImpl(ss[2]));
    if (ss.length > 3) throw new UnknownDatatypeException(s);
    return null;
  }

  // -------------------------------------------------------------------------
  private int peek(BufferedReader in) throws IOException
  {
    in.mark(1);
    int result = in.read();
    in.reset();
    return result;
  }

  // -------------------------------------------------------------------------
  private void skipWhitespace(BufferedReader in) throws IOException
  {
    while (Character.isWhitespace((char) peek(in)))
      in.read();
  }

  // -------------------------------------------------------------------------
  private boolean isLetter(char c)
  {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
  }

  public  Datatype createFromValue(ANY value) throws AnalysisException, UnknownDatatypeException {
    return create(DatatypeAnalyzer.getTypeBase(value));
  }
}

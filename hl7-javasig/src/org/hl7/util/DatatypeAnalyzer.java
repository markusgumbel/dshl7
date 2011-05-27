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
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.util;

import org.hl7.types.ANY;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.MO;
import org.hl7.types.PIVL;
import org.hl7.types.PQ;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.RTO;
import org.hl7.types.TS;
import org.hl7.types.impl.QTYnull;

/** Provides static methods to analyze a data value to understand what it is in terms 
    of the HL7 data type specification. The HL7 data type specification and the Java
    SIG API are pretty exactly parallel, but there are some exceptions and corner 
    cases which need to be considered.

    Note: we cannot figure out a complete description of the type as it was instantiated.
    The bindings of the TypeVariables cannot be recovered. Only classes that are declared
    to impement interfaces of bound generic types can be recovered. E.g., I can find
    that class Foo implements Bar<Baz> { } has Bar's type variable bound to Baz, but I
    cannot figure out that Bar<Baz> object ... .getClass() has Bar's type variable bound
    to Baz. So, we are stuck here.
*/
public class DatatypeAnalyzer {

  /** Figure out the basis of this value, which is the data type in case of simple 
      data types and the generic type, such as SET, LIST, IVL, etc for parameterized
      types.
  */
  public static String getTypeBase(ANY value) throws AnalysisException { 
    // XXX: this implementation is naive, not very efficient, and possibly incorrect!
    // We simply check for all interfaces whether one comes from the
    // org.hl7.types package and has an all-caps base-name.
    Class interfaces[] = value.getClass().getInterfaces();
    final java.util.regex.Matcher matcher = PATTERN.matcher("");
    for(int i = 0; i < interfaces.length; i++) {
      matcher.reset(interfaces[i].getName());
      if(matcher.lookingAt()) {
				// Am I forgetting any here?
				if (matcher.group(1).equals("IVL") ||
						matcher.group(1).equals("PIVL") || 
						matcher.group(1).equals("RTO")) {
					// we have to now inspect the value to figure out what goes in the < >
					String paramType=null;
					if (value instanceof RTO) {
						String numType=null; String denType=null;
						RTO rto=(RTO)value;
						QTY.diff num=rto.numerator();
						QTY.diff den=rto.denominator();
						if (num instanceof PQ) numType=getTypeBase((PQ)num);
						else if (num instanceof TS) numType=getTypeBase((TS)num);
						else if (num instanceof MO) numType=getTypeBase((MO)num);
						else if (num instanceof INT) numType=getTypeBase((INT)num);
						else if (num instanceof REAL) numType=getTypeBase((REAL)num);
						else if (num instanceof RTO) numType=getTypeBase((RTO)num);
						else numType=getTypeBase(QTYnull.NI);
						
						if (den instanceof PQ) denType=getTypeBase((PQ)den);
						else if (den instanceof TS) denType=getTypeBase((TS)den);
						else if (den instanceof MO) denType=getTypeBase((MO)den);
						else if (den instanceof INT) denType=getTypeBase((INT)den);
						else if (den instanceof REAL) denType=getTypeBase((REAL)den);
						else if (den instanceof RTO) denType=getTypeBase((RTO)den);
						else denType=getTypeBase(QTYnull.NI);
						
						return matcher.group(1)+"<"+numType+","+denType+">";
						
					} else if (value instanceof IVL) {
						IVL ivl=(IVL)value;
						if (ivl.isNull().isFalse()) {
							QTY qty=ivl.low();
							if (qty.isNull().isTrue())
								qty=ivl.high();
							if (qty instanceof PQ) paramType=getTypeBase((PQ)qty);
							else if (qty instanceof TS) paramType=getTypeBase((TS)qty);
							else if (qty instanceof MO) paramType=getTypeBase((MO)qty);
							else if (qty instanceof INT) paramType=getTypeBase((INT)qty);
							else if (qty instanceof REAL) paramType=getTypeBase((REAL)qty);
							else if (qty instanceof RTO) paramType=getTypeBase((RTO)qty);
							else paramType=getTypeBase(QTYnull.NI);
							return matcher.group(1)+"<"+paramType+">";
						} else { 
							return matcher.group(1);//+"null";
						}
					} else if (value instanceof PIVL) {
						PIVL pivl=(PIVL)value;
						if (pivl.isNull().isFalse()) {
							
							return matcher.group(1)+"<TS>";
						} else {
							return matcher.group(1);//+"null";
						}
					} else 
						throw new AnalysisException("value "+value+" does is not an instance of IVL/PIVL/SET/BAG/LIST");
				}
				else 
					return matcher.group(1);
      }
    }
    // second level search
    // XXX: why should we search on only 2 levels?
    for(int i = 0; i < interfaces.length; i++) {
      Class supers[] = interfaces[i].getInterfaces();
      for(int j = 0; j < supers.length; j++) {
				matcher.reset(supers[i].getName());
				if(matcher.lookingAt()) {
					// Am I forgetting any here?
					if (matcher.group(1).equals("IVL") ||
							matcher.group(1).equals("PIVL") || 
							matcher.group(1).equals("RTO")) {
						// we have to now inspect the value to figure out what goes in the < >
						String paramType=null;
						if (value instanceof RTO) {
							String numType=null; String denType=null;
							RTO rto=(RTO)value;
							QTY.diff num=rto.numerator();
							QTY.diff den=rto.denominator();
							if (num instanceof PQ) numType=getTypeBase((PQ)num);
							else if (num instanceof TS) numType=getTypeBase((TS)num);
							else if (num instanceof MO) numType=getTypeBase((MO)num);
							else if (num instanceof INT) numType=getTypeBase((INT)num);
							else if (num instanceof REAL) numType=getTypeBase((REAL)num);
							else if (num instanceof RTO) numType=getTypeBase((RTO)num);
							else numType=getTypeBase(QTYnull.NI);
							
							if (den instanceof PQ) denType=getTypeBase((PQ)den);
							else if (den instanceof TS) denType=getTypeBase((TS)den);
							else if (den instanceof MO) denType=getTypeBase((MO)den);
							else if (den instanceof INT) denType=getTypeBase((INT)den);
							else if (den instanceof REAL) denType=getTypeBase((REAL)den);
							else if (den instanceof RTO) denType=getTypeBase((RTO)den);
							else denType=getTypeBase(QTYnull.NI);
							
							return matcher.group(1)+"<"+numType+","+denType+">";
							
							
						} else if (value instanceof IVL) {
							IVL ivl=(IVL)value;
							if (ivl.isNull().isFalse()) {
								QTY qty=ivl.low();
								if (qty.isNull().isTrue())
									qty=ivl.high();
								if (qty instanceof PQ) paramType=getTypeBase((PQ)qty);
								else if (qty instanceof TS) paramType=getTypeBase((TS)qty);
								else if (qty instanceof MO) paramType=getTypeBase((MO)qty);
								else if (qty instanceof INT) paramType=getTypeBase((INT)qty);
								else if (qty instanceof REAL) paramType=getTypeBase((REAL)qty);
								else if (qty instanceof RTO) paramType=getTypeBase((RTO)qty);
								else paramType=getTypeBase(QTYnull.NI);
								return matcher.group(1)+"<"+paramType+">";
							} else {
								return matcher.group(1);//+"null";
							}
						} else if (value instanceof PIVL) {
							PIVL pivl=(PIVL)value;
							if (pivl.isNull().isFalse()) {
								return matcher.group(1)+"<TS>";
							} else {
								return matcher.group(1);//+"null";
							}
						} else  
							throw new AnalysisException("value "+value+" does is not an instance of IVL/PIVL/SET/BAG/LIST");
					}
					else {
						return matcher.group(1);
					}
				}
      }
    }
    throw new AnalysisException("value does not implement any known data type interface: " + value);
  }
  
  // the reges stuff
  private static final java.util.regex.Pattern PATTERN = java.util.regex.Pattern.compile("^org\\.hl7\\.types\\.([A-Z]+)$");
  
  /** An exception that we throw. */
  public static class AnalysisException extends Exception {
    public AnalysisException(String message) { super(message); }
    public AnalysisException(Throwable nestedThrowable) { super(nestedThrowable); }
    public AnalysisException(String message, Throwable nestedThrowable) { super(message, nestedThrowable); }
  }
}


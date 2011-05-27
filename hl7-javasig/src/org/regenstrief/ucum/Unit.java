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
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.regenstrief.ucum;

import java.util.regex.Pattern;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.CS;
import org.hl7.types.ED;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.REAL;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.EDnull;
import org.hl7.types.impl.LISTnull;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.STnull;
import org.regenstrief.util.RegexTokenizer;

/** This class implements a Unit as a dimensioned quantity that
 has a code (name).
 
 @author Gunther Schadow.
 @version $Id: Unit.java 24342 2009-04-24 17:40:09Z gschadow $
 */
public class Unit extends DimensionedQuantity implements CS
{
	/** The unit symbol. */
	private ST _code;
	
	/** A set of flags */
	private byte _flags = 0;
	/** Indicates whether the unit is metric. */
	static final byte FL_METRIC = 1<<0;
	/** Indicates whether the unit is a simple unit (i.e., not a term.) */
	static final byte FL_SIMPLE = 1<<1;
	/** Indicates whether the unit is a unit atom (which implies that
	 *  the unit is also simple.) */
	static final byte FL_ATOMIC = 1<<2|FL_SIMPLE;
	/** Indicates whether the unit is special (with functions). */
	static final byte FL_SPECIAL= 1<<3;
	/** Indicates whether the unit is a dimensionless factor. */
	static final byte FL_FACTOR = 1<<4;
	/** Indicates whether the unit is a toplevel unit encountered
	 * by the application rather than a component extracted from
	 * the parsing of a unit. We may cache all components of a 
	 * unit as a unit is parsed, but we may want to know which ones
	 * the application really dealt with. 
	 */
	static final byte FL_TOP    = 1<<5;
	/** Indicates whether the unit is a base unit. */
	static final byte FL_BASE   = 1<<6;
	
	public boolean isMetric() { return (this._flags & FL_METRIC) != 0; }
	public boolean isAtomic() { return (this._flags & FL_ATOMIC) != 0; }
	public boolean isSimple() { return (this._flags & FL_SIMPLE) != 0; }
	public boolean isSpecial() { return (this._flags & FL_SPECIAL) != 0; }
	public boolean isFactor() { return (this._flags & FL_FACTOR) != 0; }
	public boolean isToplevelUnit() { return (this._flags & FL_TOP) != 0; }
	public boolean isBaseUnit() { return (this._flags & FL_BASE) != 0; }

	/** A constructor for a new unit.
			Package protected because only UnitSystem calls this.
			This is usually used only for defining base units.
	 */
	/*package*/ Unit(ST code, REAL magnitude, Dimension dimension, int flags) {
		super(magnitude, dimension);
		
		if(code == null)
			throw new NullPointerException("code");
		if(!code.nonEmpty().isTrue())
			throw new IllegalArgumentException("code must be a non-empty string");
		
		this._code  = code;
		this._flags = (byte)flags;
		
		if(UnitSystem._stringUnitMap.put(code.toString().intern(), this) != null)
			throw new IllegalArgumentException("1 duplicate unit " + code.toString());
	}
	
	/** A constructor for a new unit defined based on another quantity.
			Package protected because only UnitSystem calls this.
			This is usually used to define new derived units.
	 */
	/*package*/ Unit(ST code, REAL magnitude, DimensionedQuantity qty, int flags) {
		super(magnitude.times(qty.magnitude()), qty.dimension());
		if(code == null)
			throw new NullPointerException("code");
		if(!code.nonEmpty().isTrue())
			throw new IllegalArgumentException("code must be a non-empty string");
		
		this._code  = code;
		this._flags = (byte)flags;
		
		if(UnitSystem._stringUnitMap.put(code.toString().intern(), this) != null)
			throw new IllegalArgumentException("2 duplicate unit " + code.toString());
	}
	
	/** The function pair is needed for special units.
			There may be better ways to implement special units, but at this late moment
			all I will do is deal with special units as transforms on the input and output side.
			That is, internal representation is as regular units, and only when we display the
			data we would use the functions to convert. Is that even possible?
	*/
	private FunctionPair _functionPair = null; // XXX: function pair determines FL_SPECIAL, redundancy, inconsistency may occur

	/** @deprecated only XSLT to use this. */
	public FunctionPair getFunctionPair() { return _functionPair; }
	
	/** A constructor for a special unit.
			Package protected because only UnitSystem calls this.
			The magnitude is often 1, but in case of degF it is 5.
			The dimensionedQuantity is the corresponding unit, e.g. for Cel it is K, for degF it is K/9.
			The function pair takes the magnitude expressed in this corresponding magnitude*unit, e.g., in 5 K/9.		  
	 */
	/*package*/ Unit(ST code, REAL magnitude, DimensionedQuantity qty, FunctionPair functionPair, int flags) {
		super(magnitude.times(qty.magnitude()), qty.dimension());
		if(code == null)
			throw new NullPointerException("code");
		if(!code.nonEmpty().isTrue())
			throw new IllegalArgumentException("code must be a non-empty string");
		this._code  = code;
		this._flags = (byte)(flags | FL_SPECIAL);
		this._functionPair = functionPair;

		if(UnitSystem._stringUnitMap.put(code.toString().intern(), this) != null)
			throw new IllegalArgumentException("2 duplicate unit " + code.toString());
	}

	/** Returns the magnitude of a dimensioned quantity measured in this unit. 
			This method MUST be used instead of q.dividedBy(this) in order to properly deal with special units.
	*/ 
	public REAL magnitudeOf(DimensionedQuantity q) {
		if(_functionPair != null)
			return _functionPair.f_to(q.dividedBy(this).toREAL());
	  else
			return q.dividedBy(this).toREAL();	
	}
	
	/** Returns the DimensionedQuantity representing the specified magnitude expressed in this unit. 
			This method MUST be used instead of this.times(magnitude) in order to properly deal with special units. 
	*/
	public DimensionedQuantity quantityOfMagnitude(REAL magnitude) {
		if(_functionPair != null)
			return this.times(_functionPair.f_from(magnitude));
		else
			return this.times(magnitude);
	}
	
	/** A constructor for a new unit defined as another quantity.
			This is private, only called from within here.
	 */
	private Unit(ST code, DimensionedQuantity qty, int flags) {
		super(qty);
		
		if(code == null)
			throw new NullPointerException("code");
		if(!code.nonEmpty().isTrue())
			throw new IllegalArgumentException("code must be a non-empty string");
		
		this._code  = code;
		this._flags = (byte)flags;
		
		if(UnitSystem._stringUnitMap.put(code.toString().intern(), this) != null)
			throw new IllegalArgumentException("3 duplicate unit " + code.toString());
	}
	
	// THE HL7 CS INTERFACE
	
	/** The unit symbol. */
	public ST code() {
		return this._code;
	}
	/** For the CS interface. */
	public UID codeSystem() {
		return UnitSystem.codeSystem();
	}
	/** For the CS interface. */
	public ST codeSystemVersion() {
		return UnitSystem.codeSystemVersion();
	}
	/** For the CS interface. */
	public ST codeSystemName() {
		return UnitSystem.codeSystemName();
	}
	/** For the CS interface. */
	public ST displayName() {
		return STnull.NI;
	}
	/** For the CS interface. */
	public ED originalText() {
		return EDnull.NA;
	}
	/** For the CS interface. */
	public BL implies(CD x) { 
		BL equality = this.equal(x);
		if(equality.isTrue())
			return equality;
		else
			throw new UnsupportedOperationException();      
	}
	/** For the CS interface. */
	public CD mostSpecificGeneralization(CD x) {
		throw new UnsupportedOperationException();
	}
	/** For the CS interface. */
	public LIST<CR> qualifier() {
		return LISTnull.NA;
	}
	/** For the CD and the PQ interface.
	 This is really SET<PQR> but PQR is an extension of CD and we need 
	 this so that one can have CDs that are also PQs (which is Units 
	 of measure. */
	public SET<CD> translation() {
		return SETnull.NA;
	}
	
	// LITERAL FORM
	
	/** A string representation of the unit that is a valid literal.
	 */
	public String toString() {
		return code().toString();
	}
	
	public static Unit _valueOf(String symbol) {
		return valueOf(symbol);
	}
	
	/** Get a unit value given a String. This just creates an ST value
	 around the string, because it's the STs that go into the string
	 map. 
	 */
	public static Unit valueOf(String symbol) {
		return valueOf(ValueFactory.getInstance().STvalueOf(symbol.intern()));
	}
	
	/** Get a unit value given a String. This is the entrance for the 
	 unit term parser.
	 
	 The trick is that we can thow every unit into the string map
	 as an easy cache after we parsed it. Not just to save time 
	 parsing units we've already seen, but also to save ourselves
	 from creating new objects, and it's an interesting statistical
	 overview to see how many different top level units an
	 application uses over the course of its runtime.
	 
	 This should not be called from within the Unit class itself,
	 because it will set the toplevel flag FL_TOP of any unit 
	 accessed, whether newly created or out of the map.
	 */
	public static Unit valueOf(ST s) {
		if(s == null)
			throw new IllegalArgumentException("unit must be non-null");
		if(!s.nonEmpty().isTrue())
			throw new IllegalArgumentException("unit must be a non-empty string");
		
		String symbol = s.toString().intern();
		
		Unit unit = UnitSystem._stringUnitMap.get(symbol);
		
		if(unit != null) {
			if((unit._flags&FL_TOP) == 0) {
				unit._flags |= FL_TOP;
			}
			return unit;
		} else {
			try {
				DimensionedQuantity qty = parseUnitTerm(symbol);
				if(qty != null) {
					// check if the parser has created this unit already?
					unit = UnitSystem._stringUnitMap.get(symbol);
					if(unit != null) {
						return unit;
					} else {
						// FIXME: don't we want these strings all interned?
						unit = new Unit(s, qty, Unit.FL_TOP);
						UnitSystem._stringUnitMap.put(symbol, unit);
						return unit;
					}
				} else 
					throw new IllegalArgumentException("trailing junk in \"" + symbol + "\"");
			} catch(SyntaxException ex) { 
				throw ex;
				// throw new IllegalArgumentException(symbol + ": " + ex.getMessage());
			}
		}
	}
	
	
	// OPERATIONS
	
	// Do you miss operations like times, inverted etc.? These are not
	// here. Operations with units will not reliably return units, but
	// will return general dimensioned quantities.
	//
	// If you want to determine the unit of an operation with two 
	// dimensioned quantities, you need to do something else, such as
	// generate the unit from the dimension.
	
	// PARSER
	
	static final String STEM = "[!#-'*,:-z|!]+";
	static final String BRAC = "\\[[!-Z\\^-~]*\\]";
	static final String CURL = "\\{[!-z|~]*\\}";
	static final String EXPO = "[+-]?[0-9]+";
	static final String FACT = "[0-9]+";
	static final String BPAR = "[(]";
	static final String EPAR = "[(]";
	static final String OPER = "[/.]";
	
	/** This pattern contains several capture groups as defined
	 * by the integer constant values starting with G_.
	 */
	static final Pattern termComponentPattern = Pattern.compile(
			"("+EPAR+")|("+OPER+")?(?:((?:((?:"+STEM+"|"+BRAC+"|[0-9+-]+(?:"+STEM+"|"+BRAC+"))+)("+EXPO+")?|("+FACT+"))("+CURL+")?|("+CURL+"))|("+BPAR+"))");
	
	/** Capture group number for the entire term component. */
	static final int G_ALL = 0;
	/** Capture group number for the operator symbol. */
	static final int G_OPERATOR = 2;
	/** Capture group number for the unit token including simple-unit,
	 * exponent and annotation. This is stored in the symbol table. */
	static final int G_UNIT_TOKEN = 3;
	/** Capture group number for the simple unit, excluding exponent and
	 * annotation. */
	static final int G_SIMPLE_UNIT = 4;
	/** Capture group number for the exponent. */
	static final int G_EXPONENT = 5;
	/** Capture group number for the factor. This is mutually exclusive
	 * with simple unit and exponent. */
	static final int G_FACTOR = 6;
	/** Capture group number for the annotation. */
	static final int G_ANNOTATION = 7;
	/** Capture group number for the annotation. */
	static final int G_ANNOTATION_ONLY = 8;
	/** Capture group number for an opening paren. */
	static final int G_BPAREN = 9;
	/** Capture group number for a closing paren. */
	static final int G_EPAREN = 1;
	
	public static class SyntaxException extends RuntimeException {
		SyntaxException() { super(); }
		SyntaxException(String msg) { super(msg); }
		SyntaxException(Throwable twb) { super(twb); }
	}
	
	static DimensionedQuantity parseUnitTerm(String s) 
	throws SyntaxException
	{
		return parseUnitTerm(new RegexTokenizer(termComponentPattern, s));
	}
	
	/** The unit term parser is extremely simple because all work that
	 * can be done in a single regular expression, except for the
	 * parentheses.
	 */
	static DimensionedQuantity parseUnitTerm(RegexTokenizer seq) 
	throws SyntaxException
	{
		DimensionedQuantity accumulator = null;
		
		while(seq.next()) {
			String operator = seq.group(G_OPERATOR);
			DimensionedQuantity operand = null;

			/*
			System.out.println("G_ALL: \"" + seq.group(G_ALL) + "\"");
			System.out.println("G_OPERATOR: \"" + seq.group(G_OPERATOR) + "\"");
			System.out.println("G_UNIT_TOKEN: \"" + seq.group(G_UNIT_TOKEN) + "\"");
			System.out.println("G_SIMPLE_UNIT: \"" + seq.group(G_SIMPLE_UNIT) + "\"");
			System.out.println("G_EXPONENT: \"" + seq.group(G_EXPONENT) + "\"");
			System.out.println("G_FACTOR: \"" + seq.group(G_FACTOR) + "\"");
			System.out.println("G_ANNOTATION: \"" + seq.group(G_ANNOTATION) + "\"");
			System.out.println("G_ANNOTATION_ONLY: \"" + seq.group(G_ANNOTATION_ONLY) + "\"");
			System.out.println("G_BPAREN: \"" + seq.group(G_BPAREN) + "\"");
			System.out.println("G_EPAREN: \"" + seq.group(G_EPAREN) + "\"");
			*/

			if(seq.group(G_BPAREN) != null) {
				operand = parseUnitTerm(seq);
				if(!(seq.next() && seq.group(G_EPAREN) != null))
					throw new SyntaxException("incomplete paren before "+seq.rest());
			} else if(seq.group(G_EPAREN) != null) {
				return accumulator;
			} else if(seq.group(G_FACTOR) != null) {
				String symbol = seq.group(G_FACTOR).intern();
				operand = UnitSystem._stringUnitMap.get(symbol);
				if(operand == null)
					operand = new Unit(ValueFactory.getInstance().STvalueOfLiteral(symbol),	
														 ValueFactory.getInstance().REALvalueOfLiteral(symbol), 
														 Dimension.ZERO, 
														 FL_FACTOR);
			} else if(seq.group(G_ANNOTATION_ONLY) != null) {
				String symbol = seq.group(G_ANNOTATION_ONLY).intern();
				operand = UnitSystem._stringUnitMap.get(symbol);
				if(operand == null)
					operand = new Unit(ValueFactory.getInstance().STvalueOfLiteral(symbol),	
														 ValueFactory.getInstance().REALvalueOfLiteral("1"), 
														 Dimension.ZERO, 
														 FL_FACTOR);				
			} else if(seq.group(G_UNIT_TOKEN) != null) {
				// mind the order of this, as it must occur after the test for G_FACTOR.
				String symbol = seq.group(G_UNIT_TOKEN).intern();
				operand = UnitSystem._stringUnitMap.get(symbol);
				if(operand == null && seq.group(G_SIMPLE_UNIT) != null) {
					Unit operandUnit = parseSimpleUnit(seq.group(G_SIMPLE_UNIT));
					if(seq.group(G_EXPONENT) == null) 
						operand = operandUnit;
					else {
						operand = new Unit(ValueFactory.getInstance().STvalueOfLiteral(symbol),
															 operandUnit.power(ValueFactory.getInstance().INTvalueOfLiteral(seq.group(G_EXPONENT))), 0);
					}
				}
			}

			if(operand != null) {
				if(accumulator == null) {
					if(operator == null) {
						accumulator = operand;
					} else if(operator.equals("/")) {
						accumulator = operand.inverted();
					} else if(operator.equals(".")) {
						throw new 
						SyntaxException("leading multiplication operator before "
								+ seq.rest());
					} else 
						throw new Error("unhandled operator "+ operator +
								" before "+ seq.rest() + " (program error)");
				} else {
					if(operator == null) {
						throw new SyntaxException("missing operator before "
								+ seq.rest());	    
					} else if(operator.equals(".")) {
						accumulator = accumulator.times(operand);
					} else if(operator.equals("/")) {
						accumulator = accumulator.dividedBy(operand);
					} else 
						throw new Error("unhandled operator "+ operator +
								" before "+ seq.rest() + " (program error)");
				}
			} else 
				throw new SyntaxException("no operand in " + seq.token());
		}
		if(seq.rest() != null && !seq.rest().equals("")) 
			throw new SyntaxException("trailing junk: "+seq.rest());
		else
			return accumulator;
	}
	
	/** Parses a simple prefix-atom pair. */
	static Unit parseSimpleUnit(String symbol) 
	throws SyntaxException
	{
		symbol = symbol.intern();
		Unit unit = UnitSystem._stringUnitMap.get(symbol);
		if(unit != null) {
			return unit;
		} else {
			int max = symbol.length(); 
			for(int i = 1; i < max; i++) {
				String atom = symbol.substring(i).intern();
				unit = UnitSystem._stringUnitMap.get(atom);
				if(unit != null && unit.isMetric() && unit.isAtomic()) {
					String prefixString = symbol.substring(0,i);
					Prefix prefix = Prefix.valueOf(prefixString);
					if(prefix != null) {
						DimensionedQuantity qty = unit.times(prefix.value());
						return new Unit(ValueFactory.getInstance().STvalueOf(symbol),	qty, FL_SIMPLE);
					}
				}    
			}
			throw new SyntaxException("no prefix-unit combination found for " 
					+ symbol);
		}
	}

  public boolean equals(Object that) {
  	return super.equals(that) && (_functionPair == (that instanceof Unit ? ((Unit)that)._functionPair : null));
  }
	
	/** ANY interface */
	public NullFlavor nullFlavor() { return null; }
	public boolean isNullJ() { return false; }
	public boolean nonNullJ() { return true; }
	public boolean notApplicableJ() { return false; }
	public boolean unknownJ() { return false; }
	public boolean otherJ() { return false; }
	public BL isNull() { return BLimpl.FALSE; }
	public BL nonNull() { return BLimpl.TRUE; }
	public BL notApplicable() { return BLimpl.FALSE; }
	public BL unknown() { return BLimpl.FALSE; }
	public BL other() { return BLimpl.FALSE; }
	public BL equal(ANY that) { 
		if(this == that || this.equals(that))
			return BLimpl.TRUE;
		else if(that instanceof CS && !(that instanceof Unit))
			throw new UnsupportedOperationException();
		else
			return BLimpl.FALSE;
	}
}

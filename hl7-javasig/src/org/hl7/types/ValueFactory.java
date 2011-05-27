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

package org.hl7.types;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hl7.types.enums.AddressPartType;
import org.hl7.types.enums.EntityNamePartType;
import org.hl7.types.impl.ValueFactoryImpl;
import org.regenstrief.ucum.Unit;

/**
 * <p>
 * Abstact base class for factories that create data type values from all sorts
 * of sources including literal forms.
 * </p>
 * <p>
 * A different implementation of data types would subclass this factory to
 * provide instances of their own type implementations.
 * </p>
 * <p>
 * A factory is made a singleton and a factory is obtained with the static
 * method {@link #getInstance()}, this might in the future retrieve a different
 * class depending on preferences, properties, or classpath settings.
 * </p>
 */
public abstract class ValueFactory {
    
    /** The field holding the singleton instance. */
    private static ValueFactory _valueFactory = new ValueFactoryImpl();
    
    /**
     * Returns a value factory instance. At this time this is simply a singleton
     * instance of this class, in the future this may return a subclass
     * depending on some property or preference of classpath setting.
     */
    public static ValueFactory getInstance() {
        return _valueFactory;
    }
    
    /**
     * This class wants to manage its instances and therefore does not permit
     * rogue <code>new</code>s.
     */
    protected ValueFactory() {
        super();
    }
    
    /**
     * Create any data value that has a literal form given a type specification
     * (typeSpec) and the literal string itself.
     * 
     */
    /*
     * FIXME: typeSpec should be a DataType value and literal should be an ST
     * value.
     */
    public abstract ANY valueOfLiteral(String typeSpec, String literal)
            throws ValueFactoryException;
    
    /** Create a NULL value dynamically. */
    public abstract ANY nullValueOf(String typeSpec, String nullFlavorString)
            throws ValueFactoryException;
    
    /**
     * Create a NULL value dynamically which is of the same type as the
     * exampleValue.
     */
    public abstract <T extends ANY> T nullValueLike(String nullFlavorString,
            T exampleValue) throws ValueFactoryException;
    
    /* here we have all data types with a literal form */

    public abstract BL BLvalueOfLiteral(String that);
    
    public abstract ST STvalueOfLiteral(String that);
    
    public abstract UID UIDvalueOfLiteral(String that);
    
    public abstract URL URLvalueOfLiteral(String that);
    
    public abstract INT INTvalueOfLiteral(String that);
    
    public abstract REAL REALvalueOfLiteral(String that);
    
    public abstract PQ PQvalueOfLiteral(String that);
    
    public abstract MO MOvalueOfLiteral(String that);
    
    public abstract TS TSvalueOfLiteral(String that);
    
    public abstract OID OIDvalueOfLiteral(String that);
    
    public abstract ENXP ENXPvalueOfLiteral(String that);
    
    public abstract PNXP PNXPvalueOfLiteral(String that);
    
    public abstract ONXP ONXPvalueOfLiteral(String that);
    
    public abstract ADXP ADXPvalueOfLiteral(String that);
    
    /* generic types and generic type extensions follow */
    // not yet ...
    /* special, non-literal factory methods */
    public abstract BL BLvalueOf(boolean jvalue);
    
    public abstract ST STvalueOf(String jvalue);
    
    public abstract CS CSvalueOf(String code, String codeSystem);
    
    public abstract INT INTvalueOf(byte jvalue);
    
    public abstract INT INTvalueOf(short jvalue);
    
    public abstract INT INTvalueOf(int jvalue);
    
    public abstract INT INTvalueOf(long jvalue);
    
    public abstract INT INTvalueOf(BigInteger jvalue);
    
    public abstract REAL REALvalueOf(String literal, INT precision);
    
    public abstract REAL REALvalueOf(byte jvalue);
    
    public abstract REAL REALvalueOf(short jvalue);
    
    public abstract REAL REALvalueOf(int jvalue);
    
    public abstract REAL REALvalueOf(long jvalue);
    
    public abstract REAL REALvalueOf(BigInteger jvalue);
    
    public abstract REAL REALvalueOf(float jvalue);
    
    public abstract REAL REALvalueOf(float jvalue, int precision);
    
    public abstract REAL REALvalueOf(double jvalue);
    
    public abstract REAL REALvalueOf(double jvalue, int precision);
    
    public abstract REAL REALvalueOf(BigDecimal jvalue);
    
    public abstract TS TSvalueOf(Date jvalue);
    
    public abstract TS TSvalueOf(Date jvalue, int precision);
    
    public abstract TS TSvalueOf(Calendar object);
    
    public abstract TN TNvalueOf(String jvalue);
    
    public abstract TN TNvalueOf(String jvalue, DSET<CS> use,
            IVL<TS> useablePeriod);
    
    public abstract RTO RTOvalueOf(QTY.diff numerator, QTY.diff denominator);
    
    public abstract II IIvalueOf(String root, String extension);
    
    public abstract II IIvalueOf(UID root, ST extension);
    
    public abstract II IIvalueOf(UID root, String extension);
    
    public abstract PQ PQvalueOf(String magnitudeString, String unitString);
    
    public abstract PQ PQvalueOf(REAL magnitude, CS unit);
    
    public abstract PQ PQvalueOf(REAL magnitude, Unit unit);
    
    public abstract PQ PQvalueOf(String magnitude, CS unit);
    
    public abstract PQ PQvalueOf(String magnitude, Unit unit);
    
		public abstract PQ PQvalueOf(String dose, String unit, PQR pqr);
    
    public abstract PQ PQvalueOf(REAL magnitude, PQR countnoun);
    
    public abstract PQR PQRvalueof(REAL value, ST code, UID codeSystem, ST originalText, ST displayName, ST codeSystemName,
  			ST codeSystemVersion);
    
    public abstract SC SCvalueOf(String data);
    
    public abstract SC SCvalueOf(String data, CE code);
    
    public abstract Unit UnitvalueOf(String s);
    
    public abstract Unit UnitvalueOf(ST s);
    
    public abstract TEL TELvalueOf(String urlThatMightBeIrregular,
            String spaceDelimitedUseCodeString, QSET<TS> useablePeriod);
    
    public abstract TEL TELvalueOf(CS scheme, ST address,
            DSET<? extends CS> use, QSET<TS> useablePeriod);
    
    public abstract <T extends QTY> IVL<T> IVLvalueOf(BL lowClosed, T low,
            T high, BL highClosed);
    
    public abstract <T extends QTY> IVL<T> IVLvalueOf(T center, QTY.diff width,
            BL lowClosed, BL highClosed);
    
    public abstract <T extends QTY> IVL<T> IVLvalueOf(BL lowClosed,
            BL highClosed, T low, QTY.diff width);
    
    public abstract <T extends QTY> IVL<T> IVLvalueOf(BL lowClosed,
            QTY.diff width, BL highClosed, T high);
    
    public abstract <T extends QTY> PIVL<T> PIVLvalueOf(QTY low, QTY high,
            QTY.diff period, CS alignment, BL institutionSpecified);
    
    public abstract <T extends QTY> PIVL<T> PIVLvalueOf(IVL<T> ivl,
            QTY.diff period, CS alignment, BL institutionSpecified);
    
    public abstract <T extends QTY> QSET<T> QSETvalueOf(T t);
    
    public abstract EN ENvalueOf(List<ENXP> parts,
            DSET<CS/* FIXME: EntityNameUse */> use, IVL<TS> useablePeriod);
    
    public abstract PN PNvalueOf(List<PNXP> parts,
            DSET<CS/* FIXME: EntityNameUse */> use, IVL<TS> useablePeriod);
    
    public abstract ON ONvalueOf(List<ONXP> parts,
            DSET<CS/* FIXME: EntityNameUse */> use, IVL<TS> useablePeriod);
    
    public abstract AD ADvalueOf(List<ADXP> parts,
            DSET<CS/* FIXME: PostalAddressUse */> use, QSET<TS> validTime);
    
    public abstract ENXP ENXPvalueOf(String data, EntityNamePartType type,
            DSET<CS> qualifier);
    
    public abstract PNXP PNXPvalueOf(String data, EntityNamePartType type,
            DSET<CS> qualifier);
    
    public abstract ONXP ONXPvalueOf(String data, EntityNamePartType type,
            DSET<CS> qualifier);
    
    public abstract ADXP ADXPvalueOf(String data, AddressPartType type);
    
    public abstract LIST LISTvalueOf(Collection collection);
    
    public abstract DSET DSETvalueOf(Collection collection);
    
    public abstract BAG BAGvalueOf(Collection collection);
    
    // FIXME: this is bogus:
    public abstract BAG<ST> BAGvalueOfLiteral(String that);	
}

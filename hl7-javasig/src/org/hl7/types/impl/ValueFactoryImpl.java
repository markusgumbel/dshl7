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
package org.hl7.types.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.hl7.types.AD;
import org.hl7.types.ADXP;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.CE;
import org.hl7.types.CS;
import org.hl7.types.CV;
import org.hl7.types.DSET;
import org.hl7.types.ED;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.II;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.MO;
import org.hl7.types.OID;
import org.hl7.types.ON;
import org.hl7.types.ONXP;
import org.hl7.types.ORD;
import org.hl7.types.PIVL;
import org.hl7.types.PN;
import org.hl7.types.PNXP;
import org.hl7.types.PQ;
import org.hl7.types.PQR;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.REAL;
import org.hl7.types.RTO;
import org.hl7.types.SC;
import org.hl7.types.ST;
import org.hl7.types.TEL;
import org.hl7.types.TN;
import org.hl7.types.TS;
import org.hl7.types.UID;
import org.hl7.types.URL;
import org.hl7.types.ValueFactory;
import org.hl7.types.ValueFactoryException;
import org.hl7.types.enums.AddressPartType;
import org.hl7.types.enums.EntityNamePartType;
import org.regenstrief.ucum.Unit;

/**
 * Abstact base class for factories that create data type values from all sorts of sources including literal forms. <p/>
 * A different implementation of data types would subclass this factory to provide instances of their own type
 * implementations. <p/> A factory is made a singleton and a factory is obtained with the static method getInstance(),
 * this might in the future retrieve a different class depending on preferences, properties, or classpath settings.
 */
public class ValueFactoryImpl extends ValueFactory {
  /**
   * Create any data value that has a literal form given a type specification (typeSpec) and the literal string itself.
   * <p/> FIXME: typeSpec should be a DataType value and literal should be an ST value.
   */
  @Override
public ANY valueOfLiteral(final String typeSpec, final String literal) {
    try {
      // sorry, I am using varargs style because I keep getting warning in 1.5
      return (ANY) getClass().getMethod(typeSpec + "valueOfLiteral", String.class).invoke(this, literal);
    } catch (final NoSuchMethodException ex) {
      throw new ValueFactoryException(ex);
    } catch (final IllegalAccessException ex) {
      throw new ValueFactoryException(ex);
    } catch (final InvocationTargetException ex) {
      throw new ValueFactoryException(ex);
    }
  }

  // THIS IS ALL A MESS
  // We should use the Datatype objects to do all that, that's using the strategy pattern.
  /**
   * Create a NULL value dynamically. <p/> FIXME: nullFlavorString should be a proper NullFlavor code.
   */
  @Override
public ANY nullValueOf(final String typeSpec, final String nullFlavorString) {
    try {
      if (typeSpec.equals("BL")) {
        return BLimpl.valueOf(NullFlavorImpl.valueOf(nullFlavorString));
    } else {
        return (ANY) Class.forName("org.hl7.types.impl." + typeSpec + "null").getMethod("valueOf",
        										new Class[] { String.class }).invoke(null, new Object[] { nullFlavorString });
    }
    } catch (final NoSuchMethodException ex) {
      throw new ValueFactoryException(ex);
    } catch (final ClassNotFoundException ex) {
      throw new ValueFactoryException(ex);
    } catch (final IllegalAccessException ex) {
      throw new ValueFactoryException(ex);
    } catch (final InvocationTargetException ex) {
      throw new ValueFactoryException(ex);
    }
  }

  /** Create a NULL value dynamically which is of the same type as the exampleValue. */
  @Override
public <T extends ANY> T nullValueLike(final String nullFlavorString, final T exampleValue) {
    if (exampleValue instanceof INT) {
        return (T) nullValueOf("INT", nullFlavorString);
    } else if (exampleValue instanceof REAL) {
        return (T) nullValueOf("REAL", nullFlavorString);
    } else if (exampleValue instanceof PQ) {
        return (T) nullValueOf("PQ", nullFlavorString);
    } else if (exampleValue instanceof MO) {
        return (T) nullValueOf("MO", nullFlavorString);
    } else if (exampleValue instanceof SC) {
        return (T) nullValueOf("SC", nullFlavorString);
    } else if (exampleValue instanceof CS) {
        return (T) nullValueOf("CS", nullFlavorString);
    } else if (exampleValue instanceof CV) {
        return (T) nullValueOf("CV", nullFlavorString);
    } else if (exampleValue instanceof CE) {
        return (T) nullValueOf("CE", nullFlavorString);
    } else if (exampleValue instanceof CD) {
        return (T) nullValueOf("CD", nullFlavorString);
    } else if (exampleValue instanceof QTY) {
        return (T) nullValueOf("QTY", nullFlavorString);
    } else if (exampleValue instanceof ORD) {
        return (T) nullValueOf("ORD", nullFlavorString);
    } else if (exampleValue instanceof ST) {
        return (T) nullValueOf("ST", nullFlavorString);
    } else if (exampleValue instanceof ED) {
        return (T) nullValueOf("ED", nullFlavorString);
    } else {
        throw new UnsupportedOperationException("not supported for " + exampleValue.getClass().getName());
    }
  }

  /* here we have all data types with a literal form */
  @Override
public BL BLvalueOfLiteral(final String that) {
    return BLimpl.valueOf(that);
  }

  @Override
public ENXP ENXPvalueOfLiteral(final String that) {
    return ENXPimpl.valueOf(that);
  }
  @Override
public PNXP PNXPvalueOfLiteral(final String that) {
    return PNXPimpl.valueOf(that);
  }
  @Override
public ONXP ONXPvalueOfLiteral(final String that) {
    return ONXPimpl.valueOf(that);
  }
  @Override
public ADXP ADXPvalueOfLiteral(final String that) {
    return ADXPimpl.valueOf(that);
  }

  @Override
public ST STvalueOfLiteral(final String that) {
    return STjlStringAdapter.valueOf(that);
  }
	
  @Override
public CS  CSvalueOf(final String code, final String codeSystem) {
    return CSimpl.valueOf(code, codeSystem);
  }

  @Override
public OID OIDvalueOfLiteral(final String that) {
    return OIDimpl.valueOf(that);
  }

  @Override
public UID UIDvalueOfLiteral(final String that) {
    return UIDimpl.valueOf(that);
  }

  @Override
public URL URLvalueOfLiteral(final String that) {
    try {
      return URLjnURLAdapter.valueOf(new java.net.URL(that));
    } catch (final java.net.MalformedURLException x) {
      return URLnull.OTH;
    }
  }

  @Override
public INT INTvalueOfLiteral(final String that) {
    return INTlongAdapter.valueOf(that);
  }

  @Override
public REAL REALvalueOfLiteral(final String that) {
    return REALdoubleAdapter.valueOf(that);
  }

  @Override
public REAL REALvalueOf(final String literal, final INT precision) {
    return REALdoubleAdapter.valueOf(literal, precision);
  }

  @Override
public PQ PQvalueOfLiteral(final String that) {
    // the string better have two parts "part1 part2" else we throw an error
    if (that == null || that.equals("")) {
      return PQnull.NA;
    }
    final StringTokenizer st = new StringTokenizer(that.trim(), " ");
    final String number = st.nextToken();
    String unit = "1";
    if (st.hasMoreTokens()) {
        unit = st.nextToken();
    }
    return PQimpl.valueOf(number, unit);
  }

  @Override
public <T extends QTY> IVL<T> IVLvalueOf(final BL lowClosed, final T low, final T high, final BL highClosed) {
    return IVLimpl.valueOf(lowClosed, low, high, highClosed);
  }

  @Override
public <T extends QTY> IVL<T> IVLvalueOf(final T center, final QTY.diff width, final BL lowClosed, final BL highClosed) {
    return IVLimpl.valueOf(center, width, lowClosed, highClosed);
  }

  @Override
public <T extends QTY> IVL<T> IVLvalueOf(final BL lowClosed, final BL highClosed, final T low, final QTY.diff width) {
    return IVLimpl.valueOf(lowClosed, highClosed, low, width);
  }

  @Override
public <T extends QTY> IVL<T> IVLvalueOf(final BL lowClosed, final QTY.diff width, final BL highClosed, final T high) {
    return IVLimpl.valueOf(lowClosed, width, highClosed, high);
  }

  @Override
public <T extends QTY> PIVL<T> PIVLvalueOf(final QTY low, final QTY high, final QTY.diff period, final CS alignment, final BL institutionSpecified) {
    return PIVLimpl.valueOf(low, high, period, alignment, institutionSpecified);
  }

  @Override
public <T extends QTY> PIVL<T> PIVLvalueOf(final IVL<T> ivls,final QTY.diff period,final CS alignment,final BL institutionSpecified) {
    return PIVLimpl.valueOf(ivls, period, alignment, institutionSpecified);
  }

  @Override
public MO MOvalueOfLiteral(final String that) {
    throw new UnsupportedOperationException();
  }

  @Override
public TS TSvalueOfLiteral(final String that) {
    return TSjuDateAdapter.valueOf(that);
  }

  /* generic types and generic type extensions follow */
  // not yet ...
  /* special, non-literal factory methods */
  @Override
public BL BLvalueOf(final boolean jvalue) {
    return BLimpl.valueOf(jvalue);
  }

  @Override
public ST STvalueOf(final String jvalue) {
    return STjlStringAdapter.valueOf(jvalue);
  }

  @Override
public INT INTvalueOf(final byte jvalue) {
    return INTlongAdapter.valueOf(jvalue);
  }

  @Override
public INT INTvalueOf(final short jvalue) {
    return INTlongAdapter.valueOf(jvalue);
  }

  @Override
public INT INTvalueOf(final int jvalue) {
    return INTlongAdapter.valueOf(jvalue);
  }

  @Override
public INT INTvalueOf(final long jvalue) {
    return INTlongAdapter.valueOf(jvalue);
  }

  @Override
public INT INTvalueOf(final BigInteger jvalue) {
    throw new UnsupportedOperationException();
  }

  @Override
public REAL REALvalueOf(final byte jvalue) {
    return REALdoubleAdapter.valueOf(jvalue);
  }

  @Override
public REAL REALvalueOf(final short jvalue) {
    return REALdoubleAdapter.valueOf(jvalue);
  }

  @Override
public REAL REALvalueOf(final int jvalue) {
    return REALdoubleAdapter.valueOf(jvalue);
  }

  @Override
public REAL REALvalueOf(final long jvalue) {
    return REALdoubleAdapter.valueOf(jvalue);
  }

  @Override
public REAL REALvalueOf(final BigInteger jvalue) {
    throw new UnsupportedOperationException();
  }

  @Override
public REAL REALvalueOf(final float jvalue) {
    return REALdoubleAdapter.valueOf(jvalue);
  }

  @Override
public REAL REALvalueOf(final float jvalue, final int precision) {
    return REALdoubleAdapter.valueOf(jvalue, INTvalueOf(precision));
  }

  @Override
public REAL REALvalueOf(final double jvalue) {
    return REALdoubleAdapter.valueOf(jvalue);
  }

  @Override
public REAL REALvalueOf(final double jvalue, final int precision) {
    return REALdoubleAdapter.valueOf(jvalue, INTvalueOf(precision));
  }

  @Override
public REAL REALvalueOf(final BigDecimal jvalue) {
    throw new UnsupportedOperationException();
  }

  @Override
public TS TSvalueOf(final Date jvalue) {
    return TSjuDateAdapter.valueOf(jvalue, 0);
  }

  @Override
public TS TSvalueOf(final Date jvalue, final int precision) {
    return TSjuDateAdapter.valueOf(jvalue, precision);
  }

  @Override
public TS TSvalueOf(final Calendar jvalue) {
    return TSjuDateAdapter.valueOf(jvalue);
  }

  @Override
public TN TNvalueOf(final String jvalue) {
    return TNjlStringAdapter.valueOf(jvalue);
  }

  @Override
public TN TNvalueOf(final String jvalue, final DSET<CS> use, final IVL<TS> useablePeriod) {
    return TNjlStringAdapter.valueOf(jvalue, use, useablePeriod);
  }

  @Override
public RTO RTOvalueOf(final QTY.diff numerator, final QTY.diff denominator) {
    return RTOimpl.valueOf(numerator, denominator);
  }

  @Override
public II IIvalueOf(final String root, final String extension) {
    return IIimpl.valueOf(root, extension);
  }

  @Override
public II IIvalueOf(final UID root, final ST extension) {
    return IIimpl.valueOf(root, extension);
  }

  @Override
public II IIvalueOf(final UID root, final String extension) {
    return IIimpl.valueOf(root, STvalueOfLiteral(extension));
  }

  @Override
public PQ PQvalueOf(final String magnitudeString, final String unitString) {
    return PQimpl.valueOf(magnitudeString, unitString);
  }

  @Override
public PQ PQvalueOf(final String magnitudeString, final CS unit) {
    return PQimpl.valueOf(magnitudeString, (Unit) unit);
  }

  @Override
public PQ PQvalueOf(final String magnitudeString, final Unit unit) {
    return PQimpl.valueOf(magnitudeString, unit);
  }

  @Override
public PQ PQvalueOf(final REAL magnitude, final CS unit) {
    return PQimpl.valueOf(magnitude, (Unit) unit);
  }

  @Override
public PQ PQvalueOf(final REAL magnitude, final Unit unit) {
    return PQimpl.valueOf(magnitude, unit);
  }
  
  @Override
public PQ PQvalueOf(final REAL magnitude, final PQR countnoun) {
  	return PQimpl.valueOf(magnitude, countnoun);
  }
  
  @Override
public PQ PQvalueOf(final String magnitude, final String unit, final PQR pqr) {
  	return PQimpl.valueOf(magnitude, unit, pqr);
  }  
	
  @Override
public PQR PQRvalueof(final REAL value, final ST code, final UID codeSystem, final ST originalText, final ST displayName, final ST codeSystemName,
			final ST codeSystemVersion) {
    return PQRimpl.valueOf(value, code, codeSystem, originalText, displayName, codeSystemName, codeSystemVersion);
  }
  

  @Override
public Unit UnitvalueOf(final ST s) {
    return Unit.valueOf(s);
  }

  @Override
public Unit UnitvalueOf(final String s) {
    return UnitvalueOf(STvalueOfLiteral(s));
  }

  @Override
public SC SCvalueOf(final String data) {
    return SCimpl.valueOf(data);
  }

  @Override
public SC SCvalueOf(final String data, final CE code) {
    return SCimpl.valueOf(data, code);
  }

  @Override
public LIST LISTvalueOf(final Collection collection) {
    return LISTjuListAdapter.valueOf(collection);
  }

  @Override
public DSET DSETvalueOf(final Collection collection) {
    return SETjuSetAdapter.valueOf(collection);
  }

  @Override
@SuppressWarnings("unchecked")
  public <T extends QTY> QSET<T> QSETvalueOf(final T singleValue) {
    return QSETSingularityImpl.valueOf(singleValue);
  }

  @Override
public BAG BAGvalueOf(final Collection collection) {
    return BAGjuListAdapter.valueOf(collection);
  }

  @Override
public TEL TELvalueOf(final String urlThatMightBeIrregular, final String spaceDelimitedUseCodeString, final QSET<TS> useablePeriod) {
    return TELimpl.valueOf(urlThatMightBeIrregular, spaceDelimitedUseCodeString, useablePeriod);
  }
  @Override
public TEL TELvalueOf(final CS scheme, final ST address, final DSET<? extends CS> use, final QSET<TS> useablePeriod) {	
    return TELimpl.valueOf(scheme, address, use, useablePeriod);
  }

  @Override
public EN ENvalueOf(final List<ENXP> parts, final DSET<CS/*FIXME: EntityNameUse*/> use, final IVL<TS> useablePeriod) {
    return ENimpl.valueOf(parts, use, useablePeriod);
  }
  @Override
public PN PNvalueOf(final List<PNXP> parts, final DSET<CS/*FIXME: EntityNameUse*/> use, final IVL<TS> useablePeriod) {
    return PNimpl.valueOf(parts, use, useablePeriod);
  }
  @Override
public ON ONvalueOf(final List<ONXP> parts, final DSET<CS/*FIXME: EntityNameUse*/> use, final IVL<TS> useablePeriod) {
    return ONimpl.valueOf(parts, use, useablePeriod);
  }
  @Override
public AD ADvalueOf(final List<ADXP> parts, final DSET<CS/*FIXME: PostalAddressUse*/> use, final QSET<TS> validTime) {
    return ADimpl.valueOf(parts, use, validTime);
  }

  @Override
public ENXP ENXPvalueOf(final String data, final EntityNamePartType type, final DSET<CS> qualifier) {
    return ENXPimpl.valueOf(data, type, qualifier);
  }
  @Override
public PNXP PNXPvalueOf(final String data, final EntityNamePartType type, final DSET<CS> qualifier) {
    return PNXPimpl.valueOf(data, type, qualifier);
  }
  @Override
public ONXP ONXPvalueOf(final String data, final EntityNamePartType type, final DSET<CS> qualifier) {
    return ONXPimpl.valueOf(data, type, qualifier);
  }
  @Override
public ADXP ADXPvalueOf(final String data, final AddressPartType type) {
    return ADXPimpl.valueOf(data, type);
  }

  @Override
public BAG<ST> BAGvalueOfLiteral(final String that) {
    final Collection<ST> collection = new ArrayList<ST>();
    collection.add(STvalueOfLiteral(that));
    return BAGvalueOf(collection);
  }


}

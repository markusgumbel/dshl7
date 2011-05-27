/* THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

The contents of this file are subject to the Health Level-7 Public
License Version 1.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License
at http://www.hl7.org/HPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
the License for the specific language governing rights and limitations
under the License.

The Original Code is all this file.

The Initial Developer of the Original Code is automatically generated
from HL7 copyrighted standards specification which may be subject to
different license. Portions created by Initial Developer are Copyright
(C) 2002-2004 Health Level Seven, Inc. All Rights Reserved.

THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.
*/
package org.hl7.types.enums;

import org.hl7.types.ValueFactory;
import org.hl7.types.ANY;
import org.hl7.types.CS;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.SET;
import org.hl7.types.LIST;
import org.hl7.types.CR;
import org.hl7.types.NullFlavor;
import org.hl7.types.impl.NullFlavorImpl;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.LISTnull;
import java.util.EnumSet;
import java.util.Map;
import java.util.HashMap;

/** Discussion: The hierarchical nature of these concepts shows composition. E.g. "Street Name" is part of "Street Address Line" */
public enum AddressPartType implements CS {

  // ACTUAL DATA

  root(null, "AddressPartType", null, null),
  /** This can be a unit designator, such as apartment number, suite number, or floor. There may be several unit designators in
an address (e.g., "3rd floor, Appt. 342".) This can also be a designator pointing away from the location (e.g. Across the
street from). */
  AdditionalLocator(null, "AdditionalLocator", "ADL", "additional locator"),
  /** Indicates the type of specific unit contained within a building or complex. E.g. Appartment, Floor */
  UnitDesignator(AdditionalLocator, "Unit designator", "UNIT", "unit designator"),
  /** The number or name of a specific unit contained within a building or complex, as assigned by that building or complex. */
  UnitIdentifier(AdditionalLocator, "Unit identifier", "UNID", "unit identifier"),
  /** A delivery address line is frequently used instead of breaking out delivery mode, delivery installation, etc. An address generally
has only a delivery address line or a street address line, but not both. */
  DeliveryAddressLine(null, "DeliveryAddressLine", "DAL", "delivery address line"),
  /** The location of the delivery installation, usually a town or city, and is only required if the area is different from the
municipality. Area to which mail delivery service is provided from any postal facility or service such as an individual letter
carrier, rural route, or postal route. */
  DeliveryInstallationArea(DeliveryAddressLine, "Delivery installation area", "DINSTA", "delivery installation area"),
  /** A number, letter or name identifying a delivery installation. E.g., for Station A, the delivery installation qualifier would
be 'A'. */
  DeliveryInstallationQualifier(DeliveryAddressLine, "Delivery installation qualifier", "DINSTQ", "delivery installation qualifier"),
  /** Indicates the type of delivery installation (the facility to which the mail will be delivered prior to final shipping via
the delivery mode.) Example: post office, letter carrier depot, community mail center, station, etc. */
  DeliveryInstallationType(DeliveryAddressLine, "Delivery installation type", "DINST", "delivery installation type"),
  /** Indicates the type of service offered, method of delivery. For example: post office box, rural route, general delivery, etc. */
  DeliveryMode(DeliveryAddressLine, "Delivery mode", "DMOD", "delivery mode"),
  /** Represents the routing information such as a letter carrier route number. It is the identifying number of the designator (the
box number or rural route number). */
  DeliveryModeIdentifier(DeliveryAddressLine, "Delivery mode identifier", "DMODID", "delivery mode identifier"),
  /**  */
  StreetAddressLine(null, "StreetAddressLine", "SAL", "street address line"),
  /** The number of a building, house or lot alongside the street. Also known as "primary street number". This does not number the
street but rather the building. */
  BuildingNumber(StreetAddressLine, "BuildingNumber", "BNR", "building number"),
  /** The numeric portion of a building number */
  BuildingNumberNumeric(BuildingNumber, "Building number numeric", "BNN", "building number numeric"),
  /** Any alphabetic character, fraction or other text that may appear after the numeric portion of a building number */
  BuildingNumberSuffix(BuildingNumber, "Building number suffix", "BNS", "building number suffix"),
  /**  */
  StreetName(StreetAddressLine, "StreetName", "STR", "street name"),
  /** The base name of a roadway or artery recognized by a municipality (excluding street type and direction) */
  StreetNameBase(StreetName, "Street name base", "STB", "street name base"),
  /** The designation given to the street. (e.g. Street, Avenue, Crescent, etc.) */
  StreetType(StreetName, "Street type", "STTYP", "street type"),
  /** Direction (e.g., N, S, W, E) */
  Direction(StreetAddressLine, "Direction", "DIR", "direction"),
  /** The name of the party who will take receipt at the specified address, and will take on responsibility for ensuring delivery
to the target recipient */
  CareOf(null, "Care of", "CAR", "care of"),
  /** A geographic sub-unit delineated for demographic purposes. */
  CensusTract(null, "Census tract", "CEN", "census tract"),
  /** Country */
  Country(null, "Country", "CNT", "country"),
  /** A sub-unit of a state or province. (49 of the United States of America use the term "county;" Louisiana uses the term "parish".) */
  CountyOrParish(null, "County or parish", "CPA", "county or parish"),
  /** Delimiters are printed without framing white space. If no value component is provided, the delimiter appears as a line break. */
  Delimiter(null, "Delimiter", "DEL", "delimiter"),
  /** The name of the city, town, village, or other community or delivery center */
  Municipality(null, "Municipality", "CTY", "municipality"),
  /** A numbered box located in a post station. */
  PostBox(null, "Post box", "POB", "post box"),
  /** A postal code designating a region defined by the postal service. */
  PostalCode(null, "Postal code", "ZIP", "postal code"),
  /** A subsection of a municipality */
  Precinct(null, "Precinct", "PRE", "precinct"),
  /** A sub-unit of a country with limited sovereignty in a federally organized country. */
  StateOrProvince(null, "State or province", "STA", "state or province");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.16");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("AddressPartType");

  private final AddressPartType _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final AddressPartType _parent2;
  private final AddressPartType _parent3; // well, found a 3rd :(
  private EnumSet<AddressPartType> _impliedConcepts = null;

  private AddressPartType(AddressPartType parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private AddressPartType(AddressPartType parent, AddressPartType parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private AddressPartType(AddressPartType parent, AddressPartType parent2, AddressPartType parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<AddressPartType> getImpliedConcepts() {
    if(_impliedConcepts == null) {
      if(_parent == null) { // then _parent2, 3 is also null
	_impliedConcepts = EnumSet.of(root);
	_impliedConcepts.add(this);
      } else {
	_impliedConcepts  = EnumSet.copyOf(_parent.getImpliedConcepts());
	_impliedConcepts.add(this);
	if(_parent2 != null)
	  _impliedConcepts.addAll(_parent2.getImpliedConcepts());
	if(_parent3 != null)
	  _impliedConcepts.addAll(_parent3.getImpliedConcepts());
      }
    }
    return _impliedConcepts;
  }

  public final BL equal(ANY that) {
    if(this == that)
      return BLimpl.TRUE;
    if (!(that instanceof CD))
      return BLimpl.FALSE;
    else if (that instanceof AddressPartType)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof AddressPartType))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final AddressPartType thatAddressPartType = (AddressPartType)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatAddressPartType));
  }

  public AddressPartType mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof AddressPartType))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final AddressPartType thatAddressPartType = (AddressPartType)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<AddressPartType> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatAddressPartType.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    AddressPartType mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(AddressPartType candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,AddressPartType> _codeMap = null;

  public static AddressPartType valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(AddressPartType.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(AddressPartType concept : EnumSet.allOf(AddressPartType.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      AddressPartType concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.16" + ", domain = " + "AddressPartType"));
      else
        return concept;
    } else 
      return null;
  }

  // INVARIANT BOILER PLATE CODE

  public String toString() {
    return code().toString();
  }

  private final String _domainName;
  private final ST _code;
  private final ST _displayName;

  public String domainName() { return _domainName; }
  public ST code() { return _code; }
  public ST displayName() { return _displayName; }
  public UID codeSystem() { return CODE_SYSTEM; }
  public ST codeSystemName() { return CODE_SYSTEM_NAME; }
  public ST codeSystemVersion() { return STnull.NI; }
  public ST originalText() { return STnull.NI; }
  public SET<CD> translation() { return SETnull.NA; }
  public LIST<CR> qualifier() { return LISTnull.NA; }

  public NullFlavor nullFlavor() { return NullFlavorImpl.NOT_A_NULL_FLAVOR; }
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
}

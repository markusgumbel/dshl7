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

/** Classifies the Entity class and all of its subclasses. The terminology is hierarchical. At the top is this HL7-defined domain
of high-level categories (such as represented by the Entity subclasses). Each of these terms must be harmonized and is specializable.<p>The value sets beneath are encoded in Entity.code and are drawn from multiple, frequently external, domains that reflect much
   more fine-grained typing.
</p>
<p>This table controls values for structural elements of the HL7 Reference Information Model. Therefore, it is part of the Normative
   Ballot for the RIM.
</p> */
public enum EntityClass implements CS {

  // ACTUAL DATA

  root(null, "EntityClass", null, null),
  /** Corresponds to the Entity class */
  Root(null, "EntityClassRoot", "ENT", "entity"),
  /** Anything that essentially has the property of life, independent of current state (a dead human corpse is still essentially
a living subject.) */
  LivingSubject(Root, "EntityClassLivingSubject", "LIV", "living subject"),
  /**  */
  NonPersonLivingSubject(LivingSubject, "EntityClassNonPersonLivingSubject", "NLIV", "non-person living subject"),
  /** A living subject from the animal kingdom. */
  Animal(NonPersonLivingSubject, "Animal", "ANM", "animal"),
  /** All single celled living organisms including protozoa, bacteria, yeast, viruses, etc. */
  Microorganism(NonPersonLivingSubject, "Microorganism", "MIC", "microorganism"),
  /** A living subject from the order of plants. */
  Plant(NonPersonLivingSubject, "Plant", "PLNT", "plant"),
  /** A living subject of the species homo sapiens. */
  Person(LivingSubject, "Person", "PSN", "person"),
  /** Any thing that has extension in space and mass, may be of living or non-living origin. */
  Material(Root, "EntityClassMaterial", "MAT", "material"),
  /** Corresponds to the ManufacturedMaterial class */
  ManufacturedMaterial(Material, "EntityClassManufacturedMaterial", "MMAT", "manufactured material"),
  /** A container of other entities. */
  Container(ManufacturedMaterial, "EntityClassContainer", "CONT", "container"),
  /** A type of container that can hold other containers or other holders. */
  Holder(Container, "Holder", "HOLD", "holder"),
  /** A subtype of ManufacturedMaterial used in an activity, without being substantially changed through that activity. The kind
of device is identified by the code attribute inherited from Entity.<p>
   <emph>Usage:</emph> This includes durable (reusable) medical equipment as well as disposable equipment.
</p> */
  Device(ManufacturedMaterial, "EntityClassDevice", "DEV", "device"),
  /** A physical artifact that stores information about the granting of authorization. */
  CertificateRepresentation(Device, "Certificate representation", "CER", "certificate representation"),
  /** Class to contain unique attributes of diagnostic imaging equipment. */
  ImagingModality(Device, "Imaging modality", "MODDV", "imaging modality"),
  /** A substance that is fully defined by an organic or inorganic chemical formula, includes mixtures of other chemical substances.
Refine using, e.g., IUPAC codes. */
  ChemicalSubstance(Material, "Chemical substance", "CHEM", "chemical substance"),
  /** Naturally occurring, processed or manufactured entities that are primarily used as food for humans and animals. */
  Food(Material, "Food", "FOOD", "food"),
  /** A social or legal structure formed by human beings. */
  Organization(Root, "EntityClassOrganization", "ORG", "organization"),
  /** A politically organized body of people bonded by territory, culture, or ethnicity, having sovereignty (to a certain extent)
granted by other states (enclosing or neighboring states). This includes countries (nations), provinces (e.g., one of the
United States of America or a French departement), counties or municipalities. Refine using, e.g., ISO country codes, FIPS-PUB
state codes, etc. */
  State(Organization, "State", "STATE", "state"),
  /** A politically organized body of people bonded by territory and known as a nation. */
  Nation(State, "Nation", "NAT", "Nation"),
  /** An agency of the people of a state often assuming some authority over a certain matter. Includes government, governmental
agencies, associations. */
  PublicInstitution(Organization, "Public institution", "PUB", "public institution"),
  /** A physicial place or site with its containing structure. May be natural or man-made. The geographic position of a place may
or may not be constant. */
  Place(Root, "EntityClassPlace", "PLC", "place"),
  /** The territory of a city, town or other municipality. */
  CityOrTown(Place, "City or town", "CITY", "city or town"),
  /** The territory of a sovereign nation. */
  Country(Place, "Country", "COUNTRY", "country"),
  /** The territory of a county, parish or other division of a state or province. */
  CountyOrParish(Place, "County or parish", "COUNTY", "county or parish"),
  /** The territory of a state, province, department or other division of a sovereign country. */
  StateOrProvince(Place, "State or province", "PROVINCE", "state or province"),
  /** A grouping of resources (personnel, material, or places) to be used for scheduling purposes. May be a pool of like-type resources,
a team, or combination of personnel, material and places. */
  Group(Root, "Group", "RGRP", "group"),
  /** A health chart included to serve as a document receiving entity in the management of medical records. */
  HealthChartEntity(Root, "Health chart entity", "HCE", "health chart entity");

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral("2.16.840.1.113883.5.41");
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral("EntityClass");

  private final EntityClass _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final EntityClass _parent2;
  private final EntityClass _parent3; // well, found a 3rd :(
  private EnumSet<EntityClass> _impliedConcepts = null;

  private EntityClass(EntityClass parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private EntityClass(EntityClass parent, EntityClass parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private EntityClass(EntityClass parent, EntityClass parent2, EntityClass parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet<EntityClass> getImpliedConcepts() {
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
    else if (that instanceof EntityClass)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof EntityClass))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final EntityClass thatEntityClass = (EntityClass)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(thatEntityClass));
  }

  public EntityClass mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof EntityClass))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final EntityClass thatEntityClass = (EntityClass)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet<EntityClass> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(thatEntityClass.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    EntityClass mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(EntityClass candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map<ST,EntityClass> _codeMap = null;

  public static EntityClass valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(EntityClass.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(EntityClass concept : EnumSet.allOf(EntityClass.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null && conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      EntityClass concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          "2.16.840.1.113883.5.41" + ", domain = " + "EntityClass"));
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

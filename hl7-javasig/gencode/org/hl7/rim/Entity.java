/*
THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

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
package org.hl7.rim;

import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.II;
import org.hl7.types.CE;
import org.hl7.types.PQ;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.ED;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.TEL;

import org.hl7.rim.CommunicationFunction;
import org.hl7.rim.LanguageCommunication;
import org.hl7.rim.Role;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A physical thing, group of physical things or an organization capable of participating in Acts, while in a role. </p>
<p><i>Discussion:</i> An entity is a physical object that has, had or will have existence. The only exception to this is Organization, which while
   not having a physical presence, fulfills the other characteristics of an Entity. The Entity hierarchy encompasses living subjects
   (including human beings), organizations, material, and places and their specializations. It does not indicate the roles played,
   or acts that these entities participate in.
</p>
<p><i>Constraints:</i> It does not include events/acts/actions, or the roles that things can play (e.g. patient, provider).
</p>
*/
public interface Entity extends BasicEntity {

  /**<p>A unique identifier for the Entity.</p>
<p><i>Rationale:</i> Successful communication only requires that an entity have a single identifier assigned to it. However, it is recognized
   that as different systems maintain different databases, there may be different instance identifiers assigned by different
   systems. Note that an instance identifier is a pure identifier and not a classifier. For Material, serial numbers assigned
   by specific manufacturers, catalog numbers of specific distributors, or inventory numbers issued by owners, may also be represented
   by the Role.id, which allows a more clear expression of the fact that such a code is assigned by a specific party associated
   with that material.
</p>
  */
  SET<II> getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(SET<II> id);

  /**<p>A value representing the specific kind of Entity the instance represents.</p>
<p><i>Examples:</i> A medical building, a Doberman Pinscher, a blood collection tube, a tissue biopsy.
</p>
<p><i>Rationale:</i> For each Entity, the value for this attribute is drawn from one of several coding systems depending on the Entity.classCode,
   such as living subjects (animal and plant taxonomies), chemical substance (e.g., IUPAC code), organizations, insurance company,
   government agency, hospital, park, lake, syringe, etc. It is possible that Entity.code may be so fine grained that it represents
   a single instance. An example is the CDC vaccine manufacturer code, modeled as a concept vocabulary, when in fact each concept
   refers to a single instance.
</p>
  */
  CE getCode();
  /** Sets the property code.
      @see #getCode
  */
  void setCode(CE code);

  /**<p>The number or quantity of the Entity, with appropriate units, congruent with the value of Entity.determinerCode. </p>
<p><i>Examples:</i> With undetermined kinds, the quantity is but a reference quantity for the specification of the proportion of ingredients
   or components (e.g. through a has-part, has-ingredient, or has-content Role). For example, a kind of group with 60% females
   is Person(quantity = 100) has-part Person(quantity = 60; sex = female). Amoxicillin 500 mg per tablet is Material(Tablet,
   quantity = 1) has-ingredient Material(Amoxicillin, quantity = 500 mg). Glucose 5% (D5W) is Material(D5W, quantity = 1 kg)
   has-ingredient Material(Glucose, quantity = 50 g).
</p>
<p>Material-specific quantity relations are expressed using the fact that the data type of this attribute is a set of physical
   quantity (SET&lt;PQ&gt;). If more than one quantity value are specified in this set, each element in this set is considered to specify
   the same amount of the material. For example, for one liter of water one could use the set 1 L, 1 kg, 55.56 mol to specify
   the volume, mass, and amount of substance for the same amount of water, this is equivalent with specifying the mass density
   (volumic mass 1 kg/L) and the molar mass (18 g/mol). For Glucose one could specify 180 g, 1 mol according to the molar mass
   (180 g/mol).
</p>
<p><i>Discussion:</i> When the Entity instance is a portion of a substance, the quantity specifies the amount of that substance comprised by that
   portion. For an undetermined substance (kind) the quantity serves two purposes at the same time: (a) it provides a means of
   relations between quantities specific for that substance, and (b) it is a reference quantity for the specification of ingredients
   or components. In all cases, the quantity is an extensive "amount" kind of quantity (e.g., number, length, volume, mass, surface
   area, energy, etc.). Note that most relative or fractional quantities are not amounts, in particular, mass fraction, substance
   concentration, mass ratios, percentages, etc. are not extensive quantities and are prohibited values for this attribute.
</p>
<p><i>Constraints:</i> For Entities with determinerCode = INSTANCE, the quantity is 1. For an Entity with determinerCode = QUANTIFIED_KIND, the
   quantity is the number of individual members in the group; for an Entity with a determinerCode = KIND, the value is undetermined.
</p>
  */
  SET<PQ> getQuantity();
  /** Sets the property quantity.
      @see #getQuantity
  */
  void setQuantity(SET<PQ> quantity);

  /**<p>A non-unique textual identifier or moniker for the Entity.</p>
<p><i>Examples:</i> Proper names, nicknames, legal names of persons, places or things.
</p>
<p><i>Rationale:</i> Most entities have a commonly used name that can be used to differentiate them from other Entities, but does not provide
   a unique identifier.
</p>
  */
  BAG<EN> getName();
  /** Sets the property name.
      @see #getName
  */
  void setName(BAG<EN> name);

  /**<p>A textual or multimedia depiction of the Entity.</p>
<p><i>Discussion:</i> The content of the description is not considered part of the functional information communicated between systems. Descriptions
   are meant to be shown to interested human individuals. All information relevant for automated functions must be communicated
   using the proper attributes and associated objects.
</p>
<p><i>Rationale:</i> Names and descriptions of entities are typically more meaningful to human viewers than numeric, mnemonic or abbreviated code
   values. The description allows for additional context about the entity to be conveyed to human viewers without impacting the
   functional components of the message.
</p>
  */
  ED getDesc();
  /** Sets the property desc.
      @see #getDesc
  */
  void setDesc(ED desc);

  /**<p>A value representing whether the information associated with the Entity is currently active or inactive for the purpose of
   participation in acts.
</p>
<p><b>Design Advisory: </b>This attribute was defined in the original RIM as repeating, owing to the presence of nested states in the state machines.
   In actual practice, however, there is never a need to communicate more than a single status value. therefore, committees are
   advised to <b>constrain this attribute to a maximum cardinality of 1</b> in all message designs. 
</p>
  */
  CS getStatusCode();
  /** Sets the property statusCode.
      @see #getStatusCode
  */
  void setStatusCode(CS statusCode);

  /**<p>An interval of time specifying the period in which the Entity physically existed. </p>
<p><i>Examples:</i> ManufactureDate/DisposalDate
</p>
<p><i>Constraints:</i> This period may represent past, present or future time periods.
</p>
<p><i>Rationale:</i> Physical entities have specified periods in which they exist. Equipment is manufactured, placed in service, retired and salvaged.
   The relevance of this attribute is in planning, availability and retrospective analysis.
</p>
  */
  IVL<TS> getExistenceTime();
  /** Sets the property existenceTime.
      @see #getExistenceTime
  */
  void setExistenceTime(IVL<TS> existenceTime);

  /**<p>A telecommunication address for the Entity.</p>
  */
  BAG<TEL> getTelecom();
  /** Sets the property telecom.
      @see #getTelecom
  */
  void setTelecom(BAG<TEL> telecom);

  /**<p>A value representing the type of hazard or threat associated with the Entity.</p>
<p><i>Examples:</i> Petrochemical or organic chemicals are highly flammable agents that pose an increased risk of fire under certain conditions.
   Entities with either natural or introduced radioactive character pose a risk to those handling them. Entities comprising specimens
   from diseased individuals pose an increased risk of infection to those handling them. Persons or animals of irascible temperament
   may prove to be a risk to healthcare personnel.
</p>
<p><i>Rationale:</i> Some entities have characteristics that pose potential increased risk of injury or damage to other Entities. This attribute
   identifies the type of risk without specifying the level of risk.
</p>
  */
  CE getRiskCode();
  /** Sets the property riskCode.
      @see #getRiskCode
  */
  void setRiskCode(CE riskCode);

  /**<p>A value representing special handling requirements for the Entity.</p>
<p><i>Examples:</i> Keep at room temperature; Keep frozen below 0 C; Keep in a dry environment; Keep upright, do not turn upside down.
</p>
<p><i>Rationale:</i> This attribute is used to describe special handling required by the Entity to avoid damage to it or other entities.
</p>
  */
  CE getHandlingCode();
  /** Sets the property handlingCode.
      @see #getHandlingCode
  */
  void setHandlingCode(CE handlingCode);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.CommunicationFunction> getCommunicationFunction();
  /** Sets the property communicationFunction.
      @see #getCommunicationFunction
  */
  void setCommunicationFunction(/*AssociationSet*/List<org.hl7.rim.CommunicationFunction> communicationFunction);
  /** Adds an association communicationFunction.
      @see #addCommunicationFunction
  */
  void addCommunicationFunction(org.hl7.rim.CommunicationFunction communicationFunction);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.LanguageCommunication> getLanguageCommunication();
  /** Sets the property languageCommunication.
      @see #getLanguageCommunication
  */
  void setLanguageCommunication(/*AssociationSet*/List<org.hl7.rim.LanguageCommunication> languageCommunication);
  /** Adds an association languageCommunication.
      @see #addLanguageCommunication
  */
  void addLanguageCommunication(org.hl7.rim.LanguageCommunication languageCommunication);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Role> getPlayedRole();
  /** Sets the property playedRole.
      @see #getPlayedRole
  */
  void setPlayedRole(/*AssociationSet*/List<org.hl7.rim.Role> playedRole);
  /** Adds an association playedRole.
      @see #addPlayedRole
  */
  void addPlayedRole(org.hl7.rim.Role playedRole);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Role> getScopedRole();
  /** Sets the property scopedRole.
      @see #getScopedRole
  */
  void setScopedRole(/*AssociationSet*/List<org.hl7.rim.Role> scopedRole);
  /** Adds an association scopedRole.
      @see #addScopedRole
  */
  void addScopedRole(org.hl7.rim.Role scopedRole);
}

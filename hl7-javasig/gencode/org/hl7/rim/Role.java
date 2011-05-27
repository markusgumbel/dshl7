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
import org.hl7.types.BL;
import org.hl7.types.BAG;
import org.hl7.types.EN;
import org.hl7.types.AD;
import org.hl7.types.TEL;
import org.hl7.types.IVL;
import org.hl7.types.TS;
import org.hl7.types.ED;
import org.hl7.types.CE;
import org.hl7.types.RTO;
import org.hl7.types.LIST;
import org.hl7.types.INT;

import org.hl7.rim.Participation;
import org.hl7.rim.Entity;
import org.hl7.rim.RoleLink;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A competency of the Entity playing the Role as identified, defined, guaranteed, or acknowledged by the Entity that scopes
   the Role.
</p>
<p><i>Discussion:</i> An Entity participates in an Act as in a particular Role. Note that a particular entity in a particular role can participate
   in an act in many ways. Thus, a Person in the role of a practitioner can participate in a patient encounter as a rounding
   physician or as an attending physician. The Role defines the competency of the Entity irrespective of any Act, as opposed
   to Participation, which are limited to the scope of an Act.
</p>
<p>Each role is "played" by one Entity, called the "player" and is "scoped" by another Entity, called the "scoper". Thus the
   Role of "patient" may be played by a person and scoped by the provider organization from which the patient will receive services.
   Similarly, the employer scopes an "employee" role.
</p>
<p>The identifier of the Role identifies the Entity playing the role in that role. This identifier is assigned by the scoping
   Entity to the player. The scoping Entity need not have issued the identifier, but it may have re-used an existing identifier
   for the Entity to also identify the Entity in the Role with the scoper.
</p>
<p>Most attributes of Role are attributes of the playing entity while in the particular role.</p>
*/
public interface Role extends BasicRole {

  /**<p>A unique identifier for the player Entity in this Role.</p>
  */
  SET<II> getId();
  /** Sets the property id.
      @see #getId
  */
  void setId(SET<II> id);

  /**<p>A code further specifying the kind of Role.</p>
<p><i>Discussion: </i> The Role.code must conceptually be a proper specialization of Role.classCode. Role.code does not modify Role.classCode. Rather,
   each is a complete concept or a Role-like relationship between two Entities, but Role.code may be more specific than Role.classCode.
</p>
<p>The Role.code may not be coded if only an un-coded name for the type of role is commonly used.</p>
  */
  CE getCode();
  /** Sets the property code.
      @see #getCode
  */
  void setCode(CE code);

  /**<p>An indicator specifying that the Role is a competency that is specifically not attributed to the Entity playing the Role.</p>
<p><i>Examples:</i> 1.) This Person is <b>not</b> our Employee
</p>
<p>2.) This Mouthwash does <b>not</b> have Alcohol as an ingredient.
</p>
<p>Constraint</p>
<p>Normally all Roles are considered to be affirmative. (This attribute defaults to FALSE).</p>
  */
  BL getNegationInd();
  /** Sets the property negationInd.
      @see #getNegationInd
  */
  void setNegationInd(BL negationInd);

  /**<p>A non-unique textual identifier or moniker for the playing Entity intended for use principally when playing the Role.</p>
<p><i>Examples:</i> Names used as an employee, as a licensed professional, etc.
</p>
<p><i>Usage:</i> In general, names are specified using Entity.name. Role.name is only used when the standard wishes to distinguish names that
   are appropriate for use when referring to the Entity in one Role as opposed to other Roles..
</p>
  */
  BAG<EN> getName();
  /** Sets the property name.
      @see #getName
  */
  void setName(BAG<EN> name);

  /**<p>An address for the Entity while in the Role.</p>
  */
  BAG<AD> getAddr();
  /** Sets the property addr.
      @see #getAddr
  */
  void setAddr(BAG<AD> addr);

  /**<p>A telecommunication address for the Entity while in the Role.</p>
  */
  BAG<TEL> getTelecom();
  /** Sets the property telecom.
      @see #getTelecom
  */
  void setTelecom(BAG<TEL> telecom);

  /**<p>A code specifying the state of this Role as defined in the state-transition model.</p>
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

  /**<p>An interval of time specifying the period during which the Role is in effect, if such time limit is applicable and known.</p>
  */
  IVL<TS> getEffectiveTime();
  /** Sets the property effectiveTime.
      @see #getEffectiveTime
  */
  void setEffectiveTime(IVL<TS> effectiveTime);

  /**<p>A textual or multimedia depiction of a certificate issued by the scoping Entity of a Role certifying that this Role is indeed
   played by the player Entity.
</p>
<p><i>Examples:</i> The certificate can be represented in many different ways, either inline or by reference, according to the ED data type.
   Typical cases are:
</p>
<p>1.) Paper-based certificate: the ED data type may refer to some document or file that can be retrieved through an electronic
   interface to a hardcopy archive.
</p>
<p>2.) Electronic certificate: this attribute can represent virtually any electronic certification scheme, such as, an electronically
   (including digitally) signed electronic text document.
</p>
<p>3.) Digital certificate (public key certificate): in particular, this attribute can represent digital certificates, as an
   inline data block or by reference to such data. The certificate data block would be constructed in accordance to a digital
   certificate standard, such as X509, SPKI, PGP, etc.
</p>
<p>4. The 'security' code provided with a credit card</p>
<p>5. The 'version number' associated with a particular copy of an insurance or health card</p>
<p>The certificate subject is the Entity that plays the Role. The certificate issuer is the Entity that scopes the Role.</p>
  */
  ED getCertificateText();
  /** Sets the property certificateText.
      @see #getCertificateText
  */
  void setCertificateText(ED certificateText);

  /**<p>A code that controls the disclosure of information about this Role with respect to the playing Entity..</p>
<p><i>Discussion:</i> It is important to note that the necessary confidentiality of the medical record cannot be achieved solely through confidentiality
   codes to mask individual record items from certain types of users. There are two important problems with per-item confidentiality:
   one is inference and the other is the danger of holding back information that may be critical in a certain care situation.
   Inference means that filtered sensitive information can still be assumed given the other information that was not filtered.
   The simplest form of inference is that even the existence of a test order for an HIV Western Blot test or a T4/T8 lymphocyte
   count is a strong indication for an existing HIV infection, even if the results are not known. Very often, diagnoses can be
   inferred from medication, such as Zidovudin for treatment of HIV infections. The problem of hiding individual items becomes
   especially difficult with current medications, since the continuing administration of the medication must be assured.
</p>
<p>To mitigate some of the inference-risk, aggregations of data should assume the confidentiality level of the most confidential
   action in the aggregation.
</p>
  */
  SET<CE> getConfidentialityCode();
  /** Sets the property confidentialityCode.
      @see #getConfidentialityCode
  */
  void setConfidentialityCode(SET<CE> confidentialityCode);

  /**<p>A ratio (numerator : denominator) specifying the relative quantities of the Entity playing the Role in the Entity scoping
   the Role, used for Roles that represent composition relationships between the scoping and playing Entities.
</p>
<p><i>Examples:</i> 1.) This syrup's (scoper) ingredients include 160 mg (numerator) Acetaminophen (player) per tablespoon (denominator).
</p>
<p>2.) This herd (scoper) consists of 500 (numerator) cattle (player).</p>
<p>3.) A VAX 6630 computer (scoper) has 3 (numerator) CPUs (player) as parts.</p>
<p>4.) This package (scoper) contains 100 (numerator) pills (player).</p>
<p><i>Discussion:</i> In composition-relationships (e.g., has-parts, has-ingredient, has-content) the Role.quantity attribute specifies that a
   numerator amount of the target entity is comprised by a denominator amount of the source entity of such composition-relationship.
   For example, if a box (source) has-content 10 eggs (target), the relationship quantity is 10:1; if 0.6 mL contain 75 mg of
   FeSO4 the ingredient relationship quantity is 75 mg : 0.6 mL. Both numerator and denominator must be amount quantities (extensive
   quantities, i.e., a count number, mass, volume, amount of substance, amount of energy, etc.).
</p>
  */
  RTO getQuantity();
  /** Sets the property quantity.
      @see #getQuantity
  */
  void setQuantity(RTO quantity);

  /**<p>An integer specifying the position of the Entity playing the Role with respect to the Entity that scopes the Role.</p>
<p><i>Discussion:</i> This attribute is primarily used with respect to containment roles. For example, some containers have discrete positions
   in which content may be located. Depending on the geometry of the container, the position may be referenced as a scalar ordinal
   number, or as a vector of ordinal numbers (coordinates). Coordinates always begin counting at 1.
</p>
<p>Some containers may have customary ways of referring to the positions or no way at all. In the absence of any specific regulation
   for a specific container type, the rule of thumb is that the coordinate that is changed earlier is positioned first. For an
   automated blood chemistry analyzer with a square shaped tray, this means that the first coordinate is the one in which direction
   the tray moves at each step and the second coordinate is the one in which the tray moves only every 10 (or so) steps.
</p>
  */
  LIST<INT> getPositionNumber();
  /** Sets the property positionNumber.
      @see #getPositionNumber
  */
  void setPositionNumber(LIST<INT> positionNumber);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.Participation> getParticipation();
  /** Sets the property participation.
      @see #getParticipation
  */
  void setParticipation(/*AssociationSet*/List<org.hl7.rim.Participation> participation);
  /** Adds an association participation.
      @see #addParticipation
  */
  void addParticipation(org.hl7.rim.Participation participation);

  /**
  */
  org.hl7.rim.Entity getPlayer();
  /** Sets the property player.
      @see #getPlayer
  */
  void setPlayer(org.hl7.rim.Entity player);

  /**
  */
  org.hl7.rim.Entity getScoper();
  /** Sets the property scoper.
      @see #getScoper
  */
  void setScoper(org.hl7.rim.Entity scoper);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.RoleLink> getOutboundLink();
  /** Sets the property outboundLink.
      @see #getOutboundLink
  */
  void setOutboundLink(/*AssociationSet*/List<org.hl7.rim.RoleLink> outboundLink);
  /** Adds an association outboundLink.
      @see #addOutboundLink
  */
  void addOutboundLink(org.hl7.rim.RoleLink outboundLink);

  /**
  */
  /*AssociationSet*/List<org.hl7.rim.RoleLink> getInboundLink();
  /** Sets the property inboundLink.
      @see #getInboundLink
  */
  void setInboundLink(/*AssociationSet*/List<org.hl7.rim.RoleLink> inboundLink);
  /** Adds an association inboundLink.
      @see #addInboundLink
  */
  void addInboundLink(org.hl7.rim.RoleLink inboundLink);
}

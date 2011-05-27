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
 * Portions created by Initial Developer are Copyright (C) 2002-2005 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hpath;

import java.util.Arrays;
import java.util.List;

/** A singleton class to represent the HPath language itself. 

    <p>The HPath language is an expression language, similar to XPath.
    There is an expression string which is first subject to macro expansion,
    then the expression is parsed to build a list of Expression objects.
    The basic structure of HPath expression is that of a path with 
    steps, written as a.b.c and so on. Where a, b, and c are identifiers,
    usually referencing properties of objects. The expression is evaluated
    in a context. Evaluating such expression in a context. If we understand
    a, b, and c as 



*/
/*package protected*/ final class Language {
  private static final Language _INSTANCE = new Language();
  private Language() { } 

  public static Language instance() {
    return _INSTANCE;
  }

  private static Macros _macros = new Macros();
  public Macros macros() { return _macros; }
	
  static {
    // Name Parts
    _macros.define("given", "[type.implies(CS:EntityNamePartType:Given)]");
    _macros.define("family", "[type.implies(CS:EntityNamePartType:Family)]");
    _macros.define("prefix", "[type.implies(CS:EntityNamePartType:Prefix)]");
    _macros.define("suffix", "[type.implies(CS:EntityNamePartType:Suffix)]");    
    _macros.define("streetAddressLine", "[type.implies(CS:AddressPartType:StreetAddressLine)]");
    _macros.define("municipality", "[type.implies(CS:AddressPartType:Municipality)]");
    _macros.define("stateOrProvince", "[type.implies(CS:AddressPartType:StateOrProvince)]");
    _macros.define("postalCode", "[type.implies(CS:AddressPartType:PostalCode)]");
    _macros.define("country", "[type.implies(CS:AddressPartType:Country)]");

    // Participations
    _macros.define("author", "participation[typeCode.implies(CS:ParticipationType:Author)]");
    // subject is ambiguous, therefore we add the Relationship/ Participation suffix
    _macros.define("subjectParticipation", "participation[typeCode.implies(CS:ParticipationType:ParticipationTargetSubject)]");
    _macros.define("physicalPerformer", "participation[typeCode.implies(CS:ParticipationType:ParticipationPhysicalPerformer)]");
    _macros.define("recordTarget", "participation[typeCode.implies(CS:ParticipationType:RecordTarget)]");
    _macros.define("consumable", "participation[typeCode.implies(CS:ParticipationType:Consumable)]");
				
    // ActRelationships, outbound
    // subject is ambiguous, therefore we add the Relationship/ Participation suffix
    _macros.define("subjectRelationship", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:HasSubject)]");
    // others named by implicit "has" semantics:
    _macros.define("definition", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:Instantiates)]");
    _macros.define("component", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:ActRelationshipHasComponent)]");
    _macros.define("precondition", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:HasPreCondition)]");
    _macros.define("continuingObjective", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:HasContinuingObjective)]");
    _macros.define("finalObjective", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:HasFinalObjective)]");
    _macros.define("problemSubject", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:HasSubject)]");
    _macros.define("contraindications", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:HasContraIndication)]");
    
    _macros.define("allergy", "participation[typeCode.implies(CS:ParticipationType:CausativeAgent)]");

    _macros.define("reason", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:ActRelationshipReason)]");
    _macros.define("motivatedAct", "inboundRelationship[typeCode.implies(CS:ActRelationshipType:ActRelationshipReason)]");
		
    _macros.define("updatedAct", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:Updates)]");
    _macros.define("fulfillmentOf", "outboundRelationship[typeCode.implies(CS:ActRelationshipType:ActRelationshipFulfills)]");
		
    _macros.define("supply", "inboundRelationship[typeCode.implies(CS:ActRelationshipType:ActRelationshipReason)]");
		
    _macros.define("ingredient", "scopedRole[classCode.implies(CS:RoleClass:IngredientEntity)]");
    _macros.define("activeIngredient", "scopedRole[classCode.implies(CS:RoleClass:ActiveIngredient)]");
    _macros.define("manufacturedProduct", "scopedRole[classCode.implies(CS:RoleClass:ManufacturedProduct)]");
    _macros.define("activeMoiety", "scopedRole[classCode.implies(CS:RoleClass:ActiveMoiety)]");
    _macros.define("inactiveIngredient", "scopedRole[classCode.implies(CS:RoleClass:InctiveIngredient)]");
		
    Conversion.define(TSasDateStringConversion.DEFINITION);
    Conversion.define(TSasCalendarConversion.DEFINITION);
    Conversion.define(STasStringConversion.DEFINITION);
    Conversion.define(ONasStringConversion.DEFINITION);
    Conversion.define(IIasExtensionStringConversion.DEFINITION);
    Conversion.define(CSasEnumStringConversion.DEFINITION);
    Conversion.define(ANYasStringConversion.DEFINITION);
    Conversion.define(IVL_INTasStringConversion.DEFINITION);
    Conversion.define(IVL_PQasStringConversion.DEFINITION);
    Conversion.define(REALasStringConversion.DEFINITION);
    Conversion.define(PQasStringConversion.DEFINITION);
  }

  public interface ExpressionForm {
    Expression parse(StaticContext staticContext) throws SyntaxError;
  }

  private static final List<ExpressionForm> _EXPRESSION_FORMS = Arrays.asList(new ExpressionForm[] {
      VariableReference.FORM,
      Parenthesis.FORM,
      Literal.FORM,
      Property.FORM,
      Filter.FORM,
      Concatenation.FORM
    });
  
  public List<ExpressionForm> expressionForm() {
    return _EXPRESSION_FORMS;
  }
}

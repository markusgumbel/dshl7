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

import org.hl7.rim.InfrastructureRoot;
import org.hl7.types.CE;
import org.hl7.types.BL;

import org.hl7.rim.Entity;
import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>The language communication capabilities for an Entity.</p>
<p><i>Examples:</i> A patient who originally came from Mexico may have fluent language capabilities to speak, read and write in Spanish, but
   only rudimentary capabilities in English. A person from Russia may have the capability to communicate equally well in spoken
   language in Russian, Armenian or Ukrainian, but prefers to speak in Armenian. 
</p>
<p><i>Discussion:</i> While it may seem on the surface that this class would be restricted in usage to only the LivingSubject subtypes, Devices
   also have the ability to communicate, such as automated telephony devices that transmit patient information to live operators
   on a triage line or provide automated laboratory results to clinicians.
</p>
<p><i>Rationale:</i> Each Entity with the ability to communicate verbally has differing language and proficiency level. This class specifies the
   languages with which the entity can communicate, the mode of communication (speak, read, write), the proficiency of that communication,
   and the Entity's preference.
</p>
*/
public interface LanguageCommunication extends org.hl7.rim.InfrastructureRoot {

  /**<p>A value representing a language for which the Entity has some level of proficiency for written or spoken communication.</p>
<p><i>Examples:</i> Spanish, Italian, German, English, American Sign, etc.
</p>
<p><i>Discussion:</i> Communication via spoken or written language is not solely restricted to LivingSubjects. Devices that communicate with persons
   using human language also must specify in which languages they are capable. Automated voice response systems respond to human
   language and communicate with other devices or persons using human language. 
</p>
<p><i>Rationale:</i> Many individuals and devices have the capability to communicate at varying levels in multiple languages. This code specifies
   a language capability that the entity wishes to make known.
</p>
  */
  CE getLanguageCode();
  /** Sets the property languageCode.
      @see #getLanguageCode
  */
  void setLanguageCode(CE languageCode);

  /**<p>A value representing the method of expression of the language</p>
<p><i>Examples:</i> expressed spoken, expressed written, expressed signed, received spoken, received written, received signed
</p>
  */
  CE getModeCode();
  /** Sets the property modeCode.
      @see #getModeCode
  */
  void setModeCode(CE modeCode);

  /**<p>A value representing the level of proficiency in a language.</p>
<p><i>Examples:</i> excellent, good, fair, poor
</p>
  */
  CE getProficiencyLevelCode();
  /** Sets the property proficiencyLevelCode.
      @see #getProficiencyLevelCode
  */
  void setProficiencyLevelCode(CE proficiencyLevelCode);

  /**<p>An indicator specifying whether or not the language is preferred by the entity for the associated mode.</p>
  */
  BL getPreferenceInd();
  /** Sets the property preferenceInd.
      @see #getPreferenceInd
  */
  void setPreferenceInd(BL preferenceInd);

  /**
  */
  org.hl7.rim.Entity getEntity();
  /** Sets the property entity.
      @see #getEntity
  */
  void setEntity(org.hl7.rim.Entity entity);
}

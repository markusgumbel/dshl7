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

import org.hl7.rim.RimObject;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.CS;
import org.hl7.types.II;
import org.hl7.types.LIST;
import org.hl7.types.II;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>This abstract class is a super-type for all RIM classes, either directly or through inheritance. It provides a set of communication
   infrastructure attributes that may be used in instances of HL7-specified, RIM-based communications. When valued in a communication
   instance, these attributes indicate whether the information structure is being constrained by specifically defined templates,
   realms or common communication element types.
</p>
<p>In general, constraint declarations, such as those communicated in this class's attributes, may occur wherever a RIM class
   or one of its derived clones is instantiated in an HL7 communication. Thus the attributes must be available in all RIM classes
   and clones.
</p>
*/
public interface InfrastructureRoot extends org.hl7.rim.RimObject {

  /**<p>When valued in an instance, this attribute signals that the class instance is null, and that the remainder of the information
   for this class and its properties will not be communicated. The value of this attribute specifies the flavor of null that
   is intended.
</p>
  */
  CS getNullFlavor();
  /** Sets the property nullFlavor.
      @see #getNullFlavor
  */
  void setNullFlavor(CS nullFlavor);

  /**<p>When valued in an instance, this attribute signals the imposition of realm-specific constraints. The value of this attribute
   identifies the realm in question.
</p>
  */
  SET<CS> getRealmCode();
  /** Sets the property realmCode.
      @see #getRealmCode
  */
  void setRealmCode(SET<CS> realmCode);

  /**<p>When valued in an instance, this attribute signals the imposition of constraints defined in an HL7-specified message type.
   This might be a common type (also known as CMET in the messaging communication environment), or content included within a
   wrapper. The value of this attribute provides a unique identifier for the type in question.
</p>
  */
  II getTypeId();
  /** Sets the property typeId.
      @see #getTypeId
  */
  void setTypeId(II typeId);

  /**<p>When valued in an instance, this attribute signals the imposition of a set of template-defined constraints. The value of this
   attribute provides a unique identifier for the templates in question.
</p>
  */
  LIST<II> getTemplateId();
  /** Sets the property templateId.
      @see #getTemplateId
  */
  void setTemplateId(LIST<II> templateId);
}

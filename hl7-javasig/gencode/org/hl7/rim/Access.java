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

import org.hl7.rim.Role;
import org.hl7.types.CD;
import org.hl7.types.PQ;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A role played by a device when the device is used to administer therapeutic agents (medication and vital elements) into the
   body, or to drain material (e.g., exudates, pus, urine, air, blood) out of the body.
</p>
<p><i>Discussion:</i> In general, Access is a Role of a ManufacturedMaterial or Device, something specifically manufactured or created to serve
   that purpose, such as a catheter or cannula inserted into a compartment of the body. 
</p>
<p>Devices in the role of an Access are typically used in intake/outflow observations, and in medication routing instructions.
   Microbiologic observations on the material itself or on fluids coming out of a drain, are also common.
</p>
<p><i>Rationale:</i> The Access role primarily exists in order to describe material actually deployed as an access, and not so much the fresh
   material as it comes from the manufacturer. For example, in supply ordering a box of catheters from a distributor, it is not
   necessary to use the Access role class, since the material attributes will usually suffice to describe and identify the product
   for the order. But the Access role is used to communicate about the maintenance, intake/outflow, and due replacement of tubes
   and drains.
</p>
*/
public interface Access extends org.hl7.rim.Role {

  /**<p>A coded specification of the anatomic site where the Access (cannula, line or drain) first enters the body and, if applicable,
   a routing from the first entrance to the target site. 
</p>
<p><i>Examples:</i> For example an arteria pulmonalis catheter targets a pulmonary artery but the access approach site is typically the vena
   carotis interna at the neck, or the vena subclavia at the fossa subclavia.
</p>
<p><i>Constraints:</i> The coding system is the same as for Procedure.approachSiteCode; indeed the Access.approachSiteCode has been copied from
   the Procedure class into the Access role class. The value of the Access.approachSiteCode should be identical to the value
   of the Procedure.approachSiteCode of an associated access placement procedure.
</p>
<p><i>Rationale:</i> Since accesses are typically placed for a considerable period of time and since the access is used as a resource of many
   acts, the access approach site becomes an important identifying attribute of the access itself (as opposed to merely being
   an attribute of the placement procedure).
</p>
  */
  CD getApproachSiteCode();
  /** Sets the property approachSiteCode.
      @see #getApproachSiteCode
  */
  void setApproachSiteCode(CD approachSiteCode);

  /**<p>A coded specification of the site or body compartment into which access is being provided, i.e., the compartment into which
   material is administered or from which it is collected.
</p>
<p><i>Examples:</i> For example, a pulmonary artery catheter will have the target site "arteria pulmonalis".
</p>
<p><i>Constraints:</i> The coding system is the same as for Procedure.targetSiteCode; indeed the Access.targetSiteCode has been copied from the
   Procedure class into the Access role class. The value of the Access.targetSiteCode should be identical to the value of the
   Procedure.targetSiteCode of an associated access placement procedure.
</p>
<p><i>Rationale:</i> Since accesses are typically placed for a considerable period of time and since the access is used as a resource of many
   acts, the target site becomes an important identifying attribute of the access itself (as opposed to merely being an attribute
   of the placement procedure). The target site is important information that determines what kinds of substances may or may
   not be administered (e.g., special care to avoid medication injections into an arterial access).
</p>
  */
  CD getTargetSiteCode();
  /** Sets the property targetSiteCode.
      @see #getTargetSiteCode
  */
  void setTargetSiteCode(CD targetSiteCode);

  /**<p>A measure for the inner diameter of the Access (e.g. the lumen of the tube).</p>
  */
  PQ getGaugeQuantity();
  /** Sets the property gaugeQuantity.
      @see #getGaugeQuantity
  */
  void setGaugeQuantity(PQ gaugeQuantity);
}

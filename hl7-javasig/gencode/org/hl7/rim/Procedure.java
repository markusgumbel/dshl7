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

import org.hl7.rim.Act;
import org.hl7.types.SET;
import org.hl7.types.CE;
import org.hl7.types.CD;
import org.hl7.types.CD;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>An Act whose immediate and primary outcome (post-condition) is the alteration of the physical condition of the subject.</p>
<p><i>Examples:</i> Procedures may involve the disruption of some body surface (e.g. an incision in a surgical procedure) conservative procedures
   such as reduction of a luxated join, including physiotherapy such as chiropractic treatment, massage, balneotherapy, acupuncture,
   shiatsu, etc. Outside of clinical medicine, procedures may be such things as alteration of environments (e.g. straightening
   rivers, draining swamps, building dams) or the repair or change of machinery etc.
</p>
<p><i>Discussion:</i> Applied to clinical medicine, procedure is but one among several types of clinical activities such as observation, substance-administrations,
   and communicative interactions (e.g. teaching, advice, psychotherapy, represented simply as Acts without special attributes).
   Procedure does not subsume those other activities nor is procedure subsumed by them. Notably Procedure does not comprise all
   acts of whose intent is intervention or treatment. Whether the bodily alteration is appreciated or intended as beneficial
   to the subject is likewise irrelevant, what counts is that the act is essentially an alteration of the physical condition
   of the subject.
</p>
<p>The choice between representations for real activities is based on whether the specific properties of procedure are applicable
   and whether the activity or activity step's necessary post-condition is the physical alteration. For example, taking an x-ray
   image may sometimes be called "procedure", but it is not a Procedure in the RIM sense for an x-ray image is not done to alter
   the physical condition of the body.
</p>
<p>Many clinical activities combine Acts of Observation and Procedure nature into one composite. For instance, interventional
   radiology (e.g., catheter directed thrombolysis) does both observing and treating, and most surgical procedures include conscious
   and documented Observation steps. These clinical activities therefore are best represented by multiple component acts each
   of the appropriate type.
</p>
*/
public interface Procedure extends org.hl7.rim.Act {

  /**<p>Identifies the means or technique used to perform the procedure.</p>
<p><i>Discussion:</i> For any Procedure there may be several different methods to achieve by and large the same result, but may be important to
   know when interpreting a report more thoroughly (e.g., cholecystectomy: open vs. laparoscopic). Method concepts can be "pre-coordinated"
   in the Act definition. There are many possible methods, which all depend heavily on the particular kind of Procedure, so that
   defining a vocabulary domain of all methods is difficult. However, a code system might be designed such that it specifies
   a set of available methods for each defined Procedure concept. Thus, a user ordering a Procedure could select one of several
   variances of the act by means of the method code. Available method variances may also be defined in a master service catalog
   for each defined Procedure. In act definition records (Act.moodCode = DEF) the methodCode attribute is a set of all available
   method codes that a user may select while ordering, or expect while receiving results.
</p>
  */
  SET<CE> getMethodCode();
  /** Sets the property methodCode.
      @see #getMethodCode
  */
  void setMethodCode(SET<CE> methodCode);

  /**<p>The anatomical site or system through which the procedure reaches its target (see targetSiteCode).</p>
<p><i>Examples:</i></p>
<p>Nephrectomy can have a trans-abdominal or a primarily retroperitoneal approach</p>
<p>An arteria pulmonalis catheter targets a pulmonary artery but the approach site is typically the vena carotis interna or the
   vena subclavia, at the neck or the fossa subclavia respectively.
</p>
<p>For non-invasive procedures, e.g., acupuncture, the approach site is the punctured area of the skin.</p>
<p><i>Discussion:</i> If the subject of the Act is something other than a human patient or animal, the attribute is used analogously to specify
   a structural landmark of the thing where the act focuses.
</p>
<p>Some approach sites can also be "pre-coordinated" in the Act definition, so that there is never an option to select different
   body sites. The same information structure can handle both the pre-coordinated and the post-coordinated approach.
</p>
  */
  SET<CD> getApproachSiteCode();
  /** Sets the property approachSiteCode.
      @see #getApproachSiteCode
  */
  void setApproachSiteCode(SET<CD> approachSiteCode);

  /**<p>The anatomical site or system that is the focus of the procedure.</p>
<p><i>Examples:</i></p>
<p>A Nephrectomy's target site is the right or left kidney</p>
<p>An arteria pulmonalis catheter targets a pulmonary artery.</p>
<p>For non-invasive procedures, e.g., acupuncture, the target site is the organ/system that is sought to be influenced (e.g.,
   "the liver").
</p>
<p><i>Discussion:</i> If the subject of the Act is something other than a human patient or animal, the attribute is used analogously to specify
   a structural landmark of the thing where the act focuses.
</p>
<p>Some target sites can also be "pre-coordinated" in the Act definition, so that there is never an option to select different
   body sites. The same information structure can handle both the pre-coordinated and the post-coordinated approach.
</p>
  */
  SET<CD> getTargetSiteCode();
  /** Sets the property targetSiteCode.
      @see #getTargetSiteCode
  */
  void setTargetSiteCode(SET<CD> targetSiteCode);
}

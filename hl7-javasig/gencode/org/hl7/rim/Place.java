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

import org.hl7.rim.Entity;
import org.hl7.types.BL;
import org.hl7.types.AD;
import org.hl7.types.ED;
import org.hl7.types.ST;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>A subtype of Entity representing a bounded physical place or site with its contained structures, if any. </p>
<p><i>Examples:</i> A field, lake, city, county, state, country, lot (land), building, pipeline, power line, playground, ship, truck. 
</p>
<p><i>Constraints:</i> Place may be natural or man-made. The geographic position of a place may or may not be constant. 
</p>
<p><i>Discussion:</i> Places may be work facilities (where relevant acts occur), homes (where people live) or offices (where people work). Places
   may contain sub-places (floor, room, booth, bed). Places may also be sites that are investigated in the context of health
   care, social work, public health administration (e.g., buildings, picnic grounds, day care centers, prisons, counties, states,
   and other focuses of epidemiological events).
</p>
*/
public interface Place extends org.hl7.rim.Entity {

  /**<p>An Indication of whether the facility has the capability to move freely from one location to another.</p>
<p><i>Examples:</i> Ships, aircraft and ambulances all have the capability to participate in healthcare acts.
</p>
  */
  BL getMobileInd();
  /** Sets the property mobileInd.
      @see #getMobileInd
  */
  void setMobileInd(BL mobileInd);

  /**<p>The physical address of this place. </p>
<p><i>Constraints:</i> Must be the address that allows the physical location of the place on a map.
</p>
  */
  AD getAddr();
  /** Sets the property addr.
      @see #getAddr
  */
  void setAddr(AD addr);

  /**<p>A free text note that carries information related to a site that is useful for entities accessing that site. </p>
<p><i>Discussion:</i> The attribute could include directions for finding the site when address information is inadequate, GPS information is not
   available, and/or the entity accessing the site cannot make direct use of the GPS information. It could also include information
   useful to people visiting the location. E.g., "Last house on the right", "If owner not present, check whereabouts with neighbor
   down the road".
</p>
<p>ExtRef: PHCDM-02.01.04.01(Public Health Common Data Model)</p>
  */
  ED getDirectionsText();
  /** Sets the property directionsText.
      @see #getDirectionsText
  */
  void setDirectionsText(ED directionsText);

  /**<p>A set of codes that locates the site within a mapping scheme. </p>
<p><i>Examples:</i> map coordinates for US Geological Survey maps.
</p>
  */
  ED getPositionText();
  /** Sets the property positionText.
      @see #getPositionText
  */
  void setPositionText(ED positionText);

  /**<p>The global positioning system coordinates of a place.</p>
<p><i>Discussion:</i> The global positioning system values for this attribute should conform with the USGS Spatial Data Transmission Standards.
   Among other things this includes the nature of the latitude and longitude readings, the offset error, the projection.
</p>
<p><i>Rationale:</i> In some field conditions, there will be no physical address to identify a place of interest. As all locations on the surface
   of the earth have unique geographic coordinates, the GPS values allow for precise location information to be captured and
   transmitted.
</p>
  */
  ST getGpsText();
  /** Sets the property gpsText.
      @see #getGpsText
  */
  void setGpsText(ST gpsText);
}

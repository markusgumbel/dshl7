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
* The Initial Developer of the Original Code is .
* Portions created by Initial Developer are Copyright (C) 2002-2004 
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*/
package org.hl7.xml.builder;

import org.hl7.meta.Feature;
import org.hl7.xml.validator.ValidatorException;

/**
 * ...
 * 
 * @author Eric Chen
 */
public class FeatureException extends ValidatorException
{
  //-------------------------------------------------------------------------
  /** Feature definition in HMD. */
  private final Feature _feature;

  //-------------------------------------------------------------------------
  public FeatureException(Feature feature)
  {
    super(createMessage(feature));
    _feature = feature;
  }

  //-------------------------------------------------------------------------
  public FeatureException(Feature feature, String message)
  {
    super(createMessage(feature) + ": " + message);
    _feature = feature;
  }

  //-------------------------------------------------------------------------
  public FeatureException(Feature feature, Exception ex)
  {
    super(ex, createMessage(feature));
    _feature = feature;
  }

  //-------------------------------------------------------------------------
  public static String createMessage(Feature feature)
  {
    return feature.getParent().getName() + "." +
      feature.getName();
  }

  //-------------------------------------------------------------------------
  /**
   * @return Returns the feature.
   */
  public Feature getFeature() { return _feature; }
}

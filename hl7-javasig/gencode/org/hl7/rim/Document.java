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

import org.hl7.rim.ContextStructure;
import org.hl7.types.CE;
import org.hl7.types.TS;
import org.hl7.types.SET;
import org.hl7.types.ED;

import /*org.hl7.rim.AssociationSet*/java.util.List;

/**<p>Specialization of Act to add the characteristics unique to document management services.</p>
*/
public interface Document extends org.hl7.rim.ContextStructure {

  /**<p>A code depicting the completion status of a report (e.g., incomplete, authenticated, legally authenticated).</p>
  */
  CE getCompletionCode();
  /** Sets the property completionCode.
      @see #getCompletionCode
  */
  void setCompletionCode(CE completionCode);

  /**<p>A code depicting the storage status (e.g., active, archived, purged) of a report.</p>
  */
  CE getStorageCode();
  /** Sets the property storageCode.
      @see #getStorageCode
  */
  void setStorageCode(CE storageCode);

  /**<p>Time a document is released (i.e., copied or sent to a display device) from a document management system that maintains revision
   control over the document. Once valued, cannot be changed. Intent of this attribute is to give the viewer of the document
   some notion as to how long the document has been out of the safe context of its document management system.
</p>
  */
  TS getCopyTime();
  /** Sets the property copyTime.
      @see #getCopyTime
  */
  void setCopyTime(TS copyTime);

  /**<p>Citation for a cataloged document that permits its identification, location and/or retrieval from common collections.</p>
  */
  SET<ED> getBibliographicDesignationText();
  /** Sets the property bibliographicDesignationText.
      @see #getBibliographicDesignationText
  */
  void setBibliographicDesignationText(SET<ED> bibliographicDesignationText);
}

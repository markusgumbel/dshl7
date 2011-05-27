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
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 *
 * $Id: CmetAssociationAdapter.java 7053 2007-08-31 18:05:06Z jmoore $
 */
package org.hl7.meta.mif;

import hl7OrgV3.mif.ClassDerivation;
import hl7OrgV3.mif.SerializedCommonModelElementRef;

import org.hl7.meta.CmetAssociation;
import org.hl7.meta.LoaderException;
import org.hl7.meta.MessageType;

/**
 * @author Gunther Schadow FIXME: to be deleted along with its interface!!! We
 *         need it for getting the Cmet Id
 */
public class CmetAssociationAdapter extends AssociationAdapter implements CmetAssociation
{
    // private String _cmetName;
    private String _cmetId;

    CmetAssociationAdapter(CloneClassAdapter owner, hl7OrgV3.mif.AssociationEndWithClass mifThing, String sortKey) throws LoaderException
    {
        super(owner, mifThing, sortKey);
        hl7OrgV3.mif.ClassOrReference mifRef = _mifThing.getParticipantClass();
        if (!mifRef.isSetCommonModelElementRef()) throw new Error("internal error, please report stacktrace");
    }

    public String getCmetId()
    {
        return _cmetId;
    }

    /**
     * @author jmoore
     */
    protected void setupTarget() throws LoaderException
    {
        hl7OrgV3.mif.ClassOrReference mifThing = _mifThing.getParticipantClass();
        SerializedCommonModelElementRef cmetMifThing = mifThing.getCommonModelElementRef();
        String cmetName = cmetMifThing.getName();        
        
        // The target of this association is a reference to another CMET.
        // Add the referenced CMET's MessageType to the owning MessageType 
        MessageTypeAdapter ownersCmet = _owner.getMessageType();
        MessageType targetCmet = ownersCmet.addReferencedCmet(cmetName);
        _cmetId = targetCmet.getId();
        _target = targetCmet.getRootClass();
    }   
}

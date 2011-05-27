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
 * $Id: AssociationAdapter.java 7377 2007-09-26 19:28:36Z gschadow $
 */
package org.hl7.meta.mif;

import java.util.logging.Logger;

import hl7OrgV3.mif.SerializedClass;

import org.hl7.meta.Cardinality;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Conformance;
import org.hl7.meta.LoaderException;
import org.hl7.meta.MetSource;
import org.hl7.rim.RimObjectFactory;
import org.hl7.util.FactoryException;
import org.hl7.util.ForwardReferenceTool;

/**
 * @author Gunther Schadow, Peter Hendler
 */
public class AssociationAdapter extends FeatureAdapter implements org.hl7.meta.Association
{
    protected hl7OrgV3.mif.AssociationEndWithClass _mifThing;

    protected CloneClass _target;
    protected boolean isReference = false;

    /* package */
    static AssociationAdapter make(CloneClassAdapter owner, hl7OrgV3.mif.SerializedAssociationEnd mifThing) throws LoaderException
    {
        hl7OrgV3.mif.AssociationEndWithClass mifThingTarget = mifThing.getTargetConnection();
        hl7OrgV3.mif.AssociationEndSpecialization mifBranches[] = mifThingTarget.getParticipantClassSpecializationArray();
        
        boolean isChoice = mifBranches != null && mifBranches.length > 0;
        boolean isCmetRef = mifThingTarget.getParticipantClass().isSetCommonModelElementRef();
                
        if (isChoice && isCmetRef) // handle referenced cmets with a choice entry point.
        {
            AssociationAdapter adapter = new CmetChoiceAssociationAdapter(owner, mifThingTarget, mifThingTarget.getSortKey());
            return adapter;           
        }
        else if (isChoice)
        {
            AssociationAdapter adapter = new ChoiceAssociationAdapter(owner, mifThingTarget, mifThingTarget.getSortKey());
            return adapter;
        }
        else if (isCmetRef)
        {
            AssociationAdapter adapter = new CmetAssociationAdapter(owner, mifThingTarget, mifThingTarget.getSortKey()); 
            return adapter;
        }
        else
        // return new AssociationAdapter(owner, mifThingTarget,
        // mifThing.getSortKey());
        return new AssociationAdapter(owner, mifThingTarget, mifThingTarget.getSortKey());
    }

    protected AssociationAdapter(CloneClassAdapter owner, hl7OrgV3.mif.AssociationEndWithClass mifThing, String sortKey) throws LoaderException
    {
        super(owner, mifThing, sortKey);
        _mifThing = mifThing;
        setupTarget();
    }

    /* package */AssociationAdapter copyTo(CloneClassAdapter newOwner)
    {
        return (AssociationAdapter) super.copyTo(newOwner);
    }

    private final ForwardReferenceTool.ReplacerFactory<CloneClass> REPLACER_FACTORY = new ForwardReferenceTool.ReplacerFactory<CloneClass>()
    {
        public ForwardReferenceTool.Replacer<CloneClass> createReplacer()
        {
            return new ForwardReferenceTool.Replacer<CloneClass>()
            {
                public void replace(CloneClass target)
                {
                    _target = target;
                }
            };
        }
    };

    protected void setupTarget() throws LoaderException
    {
        hl7OrgV3.mif.ClassOrReference mifThing = _mifThing.getParticipantClass();
        if (mifThing.isSetClass1())
        {
            SerializedClass targetClass = mifThing.getClass1();
            _target = new CloneClassAdapter(_owner.getMessageType(), targetClass);
        }
        else if (mifThing.isSetReference())
        {
            String name = mifThing.getReference().getName();
            setReference(true);
            MessageTypeAdapter ownersCmet = _owner.getMessageType();
            _target = ownersCmet.lookupCloneClass(name, REPLACER_FACTORY);
        }
                
        // this condition should be handled in make() by creating a CmetChoiceAssociationAdapter.        
        else if (mifThing.isSetCommonModelElementRef())
        {
            throw new Error("Should not be here. Entry point of referenced cmet is a choice." + mifThing);
        }
        else throw new Error("mif thing has neither class nor reference nor CMET: " + mifThing);
    }

    public String getName()
    {
        return _mifThing.getName();
    }

    // FIXME: remove from interface
    // @deprecated replaced by isRecursive
    public MetSource getMetSource()
    {
        // Actually we need it to identify REUSED or RECURSIVE
        throw new Error("don't use this method, will be scrapped");
    }

    public boolean isReference()
    {
        return isReference;
    }

    protected void setReference(boolean isReference)
    {
        this.isReference = isReference;
    }

    public Conformance getConformance()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isMandatory()
    {
        return _mifThing.getIsMandatory();
    }

    public String getRimClass()
    {
        return _mifThing.getDerivationSupplierArray(0).getClassName();
    }

    public String getRimPropertyName()
    {
        return _mifThing.getDerivationSupplierArray(0).getAssociationEndName();
    }

    /**
     * @see Association#getPropertyName()
     * 
     * @author jmoore
     */
    public String getPropertyName()
    {
        // By default use the general rim property.
        String propertyName = getRimPropertyName();

        // If the clone class that owns this association has a specialized
        // implementation that can be created by the RimObjectFactory, use
        // specific name of this association instead of the RIM name.
        try
        {
            RimObjectFactory factory = RimObjectFactory.getInstance();
            String[] classes = _owner.getClasses();
            String className = factory.mostSpecializedClassName(classes);
            String rimClass = classes[classes.length - 1];  // The last/default class is always the rim class.
            if (! rimClass.equals(className))
            {
                propertyName = getName();
            }
        }
        catch (FactoryException e) 
        {
            // Just use the RIM default.
        } 

        return propertyName;
    }

    public Cardinality getCardinality()
    {
        try
        {
            String min = String.valueOf(_mifThing.getMinimumMultiplicity());
            String max = _mifThing.getMaximumMultiplicity().toString();
            return Cardinality.create(min + ".." + max);
        }
        catch (RuntimeException e)
        {
            System.err.println("WARNING: bad cardinality " + this.getName());
            return Cardinality.create("0..*");
        }
    }

    public String getConstraint()
    {
        throw new UnsupportedOperationException();
    }

    public CloneClass getTarget()
    {
        if (_target == null) throw new Error("target is null in " + _mifThing);
        return _target;
    }
    
    public String toString()
    {
        String s = getName() + " (" + getParent() + " --> " + getTarget() + ")";
        return s;
    }
}

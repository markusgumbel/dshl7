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
 * $Id: CloneClassAdapter.java 7053 2007-08-31 18:05:06Z jmoore $
 */
package org.hl7.meta.mif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hl7.meta.Attribute;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Feature;
import org.hl7.meta.LoaderException;
import org.hl7.meta.MessageType;
import org.hl7.rim.RimObject;
import org.hl7.rim.RimObjectFactory;
import org.hl7.util.FactoryException;
import org.hl7.util.ForwardReferenceTool;
import org.hl7.util.NullIterator;

/**
 * @author Gunther Schadow, Peter Hendler
 */
public class CloneClassAdapter implements CloneClass
{
    private hl7OrgV3.mif.SerializedClass _mifThing;

    /** The list of all features, including declared and inherited ones. */
    private List<FeatureAdapter> _features = new ArrayList<FeatureAdapter>();

    /**
     * The list of structural attributes.
     * 
     * FIXME: there should be no need for this
     */
    private List<AttributeAdapter> _structuralAttributes = new ArrayList<AttributeAdapter>();

    private List<CloneClass> _specializations = new ArrayList<CloneClass>();

    private MessageTypeAdapter _messageType;

    private String _name;

    /**
     * Contructor without the generalization
     * 
     * @param messageType
     * @param mifThing
     * @throws LoaderException
     */
    CloneClassAdapter(MessageTypeAdapter messageType, hl7OrgV3.mif.SerializedClass mifThing) throws LoaderException
    {
        this(messageType, null, mifThing);
    }

    /**
     * 
     * @param messageType
     * @param generalization,
     *            usually it is choice associations related clone class
     * @param mifThing
     * @throws LoaderException
     */
    CloneClassAdapter(MessageTypeAdapter messageType, CloneClassAdapter generalization, hl7OrgV3.mif.SerializedClass mifThing) throws LoaderException
    {
        _mifThing = mifThing;
        _messageType = messageType;

        setupAttributes();
        setupAssociations();

        inheritFeaturesFrom(generalization);

        // it is important that setupSpecializations follows the features
        setupSpecializations();

        // Commented by Eric Chen
        // The feature of Class has an attribute 'sortKey', which is defined by
        // MIF schema file as
        // 'A name used in determining the sort order of the model element
        // within its siblings.'
        // However, the MIF file does not display the class associations element
        // sequentially as the sortKey indicates, for example at COCT_MT090100
        // message type, PrincipalChoiceList display the association
        // "asRoleOther" first, which has the sortKey value 3:
        // And it's related COCT_MT090100 schema file follows the displaying
        // sequence of MIF, but not the sortKey value

        Collections.sort(_features, FeatureAdapter.COMPARATOR);

        messageType.addCloneClass(getName(), this);
    }

    private void setupAttributes()
    {
        hl7OrgV3.mif.Attribute[] mifThing = _mifThing.getAttributeArray();
        if (mifThing != null) for (int i = 0; i < mifThing.length; i++)
        {
            AttributeAdapter aa = new AttributeAdapter(this, mifThing[i]);
            _features.add(aa);
            if (aa.isStructural()) _structuralAttributes.add(aa);
        }
    }

    private void setupAssociations() throws LoaderException
    {
        hl7OrgV3.mif.SerializedAssociationEnd[] mifThing = _mifThing.getAssociationArray();
        if (mifThing != null) for (int i = 0; i < mifThing.length; i++)
        {
            AssociationAdapter adapter = AssociationAdapter.make(this, mifThing[i]);
            _features.add(adapter);
        }
    }

    private class SpecializationReplacer implements ForwardReferenceTool.Replacer<CloneClass>
    {
        int _index;

        SpecializationReplacer(int index)
        {
            _index = index;
        }

        public void replace(CloneClass cloneClass)
        {
            CloneClassAdapter specialization = (CloneClassAdapter) cloneClass;
            specialization.inheritFeaturesFrom(CloneClassAdapter.this);
            // XXX: this *might* not be sufficient. There could be a case in
            // which
            // our clone class receives new features late, and it would then
            // have to push these features down into its subclasses.
            _specializations.set(_index, specialization);
        }
    }

    /**
     * Choice specialization set up
     * 
     * @throws LoaderException
     */
    private void setupSpecializations() throws LoaderException
    {
        hl7OrgV3.mif.SerializedClassGeneralization mifThing[] = _mifThing.getSpecializationChildArray();
        if (mifThing != null) for (int i = 0; i < mifThing.length; i++)
        {
            hl7OrgV3.mif.ClassOrReference mifSpecThing = mifThing[i].getSpecializedClass();
            if (mifSpecThing.isSetClass1()) _specializations.add(new CloneClassAdapter(_messageType, this, mifSpecThing.getClass1()));
            else if (mifSpecThing.isSetReference())
            {
                // This is peculiar: if a class is used in two choices (by means
                // of reference
                // it will inherit the properties from both choices! It will
                // then have
                // multiple inheritance. However, this can practically only
                // happen with
                // associations, so we will only do it with those
                int index = _specializations.size();
                CloneClass specialization = _messageType.lookupCloneClass(mifSpecThing.getReference().getName(), new SpecializationReplacer(index));
                if (specialization instanceof CloneClassAdapter) ((CloneClassAdapter) specialization).inheritFeaturesFrom(this);
                _specializations.add(index, specialization);
            }
            else if (mifSpecThing.isSetCommonModelElementRef())
            {
                // XXX: what if the CMET's entry point is a choice? -- it might
                // just still work
                String cmetName = mifSpecThing.getCommonModelElementRef().getName();
                                
                MessageType referencedCmet = _messageType.addReferencedCmet(cmetName);
                CloneClass specialization = referencedCmet.getRootClass();
                if (specialization instanceof CloneClassAdapter) ((CloneClassAdapter) specialization).inheritFeaturesFrom(this);
                _specializations.add(specialization);
                // System.err.println("CMET:"+cmetName+":"+((CloneClassAdapter)_target).getMessageType()+":"+_owner.getMessageType());
            }
            else throw new Error("mif thing has neither class nor reference: " + mifSpecThing);
        }
    }

    private void inheritFeaturesFrom(CloneClassAdapter generalization)
    {
        if (generalization != null)
        {
            for (FeatureAdapter adapter : generalization._features)
            {
                FeatureAdapter copy = adapter.copyTo(this);
                _features.add(copy);
                if (copy instanceof AttributeAdapter && ((AttributeAdapter) copy).isStructural()) _structuralAttributes.add((AttributeAdapter) copy);
            }
        }
    }

    /* package */MessageTypeAdapter getMessageType()
    {
        return _messageType;
    }

    public String getName()
    {
        if (_name == null)
        {
            _name = _mifThing.getName();
        }
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    // FIXME: remove this
    public String getFullName()
    {
        return getName();
    }

    public String getRimClass()
    {
        return _mifThing.getDerivationSupplierArray(0).getClassName();
    }

    /**
     * @see CloneClass.getClasses()
	 * 
	 * @author jmoore
     */
    public String[] getClasses()
    {
        String qualifier = _messageType.getId();
        String specializedClass = qualifier + "." + getName();
        String rimClass = getRimClass();
        return new String[]
        { specializedClass, rimClass };
    }

    /**
     * @see CloneClass#getSpecialization()
     *
     * @author jmoore
     */
    public CloneClass getSpecialization(String cloneCode)
    {
        for (CloneClass specialization : this._specializations)
        {
            String trialName = specialization.getName();
            if (cloneCode.equals(trialName))
            {
                return specialization;
            }
        }
        return null;
    }

    /**
     * @return An instance of the most specialized class supported by the factory.
     *
     * @author jmoore
     */
    public RimObject getInstance() throws FactoryException
    {
        RimObjectFactory factory = RimObjectFactory.getInstance();
        String[] prioritizedClassNames = getClasses();
        RimObject rimObject = factory.createRimObject(prioritizedClassNames);
        return rimObject;
    }

    // FIXME: Remove from interface
    public String[] getChoices()
    {
        throw new Error("this should not be used");
    }

    public boolean isAbstract()
    {
        return _mifThing.getIsAbstract();
    }

    public Iterator<org.hl7.meta.Feature> iterateChildren()
    {
        if (_features == null || _features.size() == 0) return new NullIterator<org.hl7.meta.Feature>();
        else // XXX: why do I have to do such draconian casting? Isn't an
                // iter<x> isa iter<y> if x isa y???
        return (Iterator<Feature>) ((Object) _features.iterator());
    }

    public Iterator<Attribute> iterateStructuralAttributes()
    {
        if (_structuralAttributes == null || _structuralAttributes.size() == 0) return new NullIterator<Attribute>();
        else return (Iterator<Attribute>) (Iterator) _structuralAttributes.iterator();
    }

    public CloneClass getParent()
    {
        throw new RuntimeException("Not Yet Implemented");
    }

    // FIXME: remove from interface
    public int countStructuralAttributes()
    {
        return _structuralAttributes.size();
    }

    // FIXME: remove from interface
    public org.hl7.meta.Attribute getStructuralAttribute(int index)
    {
        return _structuralAttributes.get(index);
    }

    // We don't know what "extra" attributes are any more
    // FIXME: remove from interface
    public Iterator<Attribute> iterateExtraAttributes()
    {
        return new NullIterator<Attribute>();
    }

    // FIXME: remove from interface
    public void setFullName(String s)
    {
        throw new Error("don't call this method, it is to be deleted.");
    }

    /**
     * @author jmoore
     */
    public String toString()
    {
        String qualifier = _messageType.getId();
        String specializedClass = qualifier + "." + getName();
        return specializedClass;
    }
}

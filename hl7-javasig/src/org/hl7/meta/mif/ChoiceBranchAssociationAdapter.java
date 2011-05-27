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
 * $Id: ChoiceBranchAssociationAdapter.java 6971 2007-08-23 17:19:22Z jmoore $
 */
package org.hl7.meta.mif;

import org.hl7.meta.Cardinality;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Conformance;
import org.hl7.meta.LoaderException;
import org.hl7.meta.MetSource;
import org.hl7.util.ForwardReferenceTool;

/**
 * @author Gunther Schadow, Peter Hendler
 */
public class ChoiceBranchAssociationAdapter extends FeatureAdapter implements org.hl7.meta.Association
{
    protected ChoiceAssociationAdapter _choice;

    private String _name;
    protected CloneClass _target;
    protected boolean isRecursive = false;

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

    ChoiceBranchAssociationAdapter(ChoiceAssociationAdapter choice, String name, String className) throws LoaderException
    {
        super(choice._owner, choice._mifThing, null); // this mifThing is only
                                                        // used for sorting, and
                                                        // it is not relevant
                                                        // how choice branches
                                                        // are sorted
        _choice = choice;
        _name = name;
        _target = _owner.getMessageType().lookupCloneClass(className, REPLACER_FACTORY);
        if (_target == null) throw new Error("target class not found " + name + " " + className);
    }

    /* package */ChoiceAssociationAdapter copyTo(CloneClassAdapter newOwner)
    {
        return (ChoiceAssociationAdapter) super.copyTo(newOwner);
    }

    public String getName()
    {
        return _name;
    }

    // FIXME: remove from interface
    // @deprecated replaced by isRecursive
    public MetSource getMetSource()
    {
        throw new Error("don't use this method, will be scrapped");
    }

    public Conformance getConformance()
    {
        return _choice.getConformance();
    }

    public boolean isMandatory()
    {
        return _choice.isMandatory();
    }

    // XXX: is this so? what RIM class are we talking about?
    public String getRimClass()
    {
        return _choice.getRimClass();
    }

    public String getRimPropertyName()
    {
        return _choice.getRimPropertyName();
    }

    public String getPropertyName()
    {
        return _choice.getPropertyName();
    }

    public Cardinality getCardinality()
    {
        return _choice.getCardinality();
    }

    public String getConstraint()
    {
        return _choice.getConstraint();
    }

    public CloneClass getTarget()
    {
        if (_target == null) throw new Error("target is null in " + _name);
        return _target;
    }

    public boolean isReference()
    {
        return isRecursive;
    }

    protected void setRecursive(boolean isRecursive)
    {
        this.isRecursive = isRecursive;
    }
}

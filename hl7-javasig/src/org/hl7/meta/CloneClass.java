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
package org.hl7.meta;

import java.util.Iterator;
import org.hl7.rim.RimObject;
import org.hl7.util.FactoryException;

/**
 * A read only interface to the class
 * {@link org.hl7.meta.impl.CloneClassImpl CloneClassImpl},
 * which represents an HL7 v3 clone class inside a message type.
 * 
 * @author Skirmantas Kligys (skirmis@hotmail.com)
 * @author Eric Chen
 */
public interface CloneClass extends Metadata
{
    //-------------------------------------------------------------------------
    /**
     * Returns the clone class name, unique in a message type.
     *
     * @return the clone class name, known as clone code
     */
    String getName();

    //-------------------------------------------------------------------------
    /**
     * Returns the clone class full path name, unique in a message type.
     *
     * @return the clone class full path name
     */
    String getFullName();

    //-------------------------------------------------------------------------
    /**
     * Set the clone class full path name, unique in a message type.
     *
     */
    void setFullName(String s);


    /**
     * Returns name of the RIM class name from which this clone class was
     * derived.
     *
     * @return the RIM class name
     */
    String getRimClass();
    
    /**
     * @return A list of the partially qualified or unqualifed class names that 
     * should be instantiaed for this CloneClass.  The list is sorted from most
     * specific class name to most general.  The last name on the list should 
     * always be the same as the value returned from getRimClass(). 
     *
     * @author jmoore
     */
    String[] getClasses();

    /** 
     * @param cloneCode the name of the specialization of this CloneClass.
     * @return The specialization of this CloneClass with the given name. 
     *
     * @author jmoore
     */
    CloneClass getSpecialization(String cloneCode);
    
    /**
     * Returns an instance of the RIM class name from which this clone class was
     * derived.
     *
     * @return the RIM object
     * @throws FactoryException if creating a RIM object failed
     */
    RimObject getInstance() throws FactoryException;

    /**
     * Returns the list of 'choices'.  Should be redone as a choice between
     * metadata classes.
     *
     * @return the list of choices
     */
    String[] getChoices();

    /**
     * Returns the flag indicating if the clone class is abstract.
     *
     * @return abstract indicator
     */
    boolean isAbstract();

    //-------------------------------------------------------------------------
    /**
     * Returns the iterator of attributes and associations contained in this
     * clone class.
     *
     * @return the iterator of children
     */
    Iterator<Feature> iterateChildren();

    //-------------------------------------------------------------------------
    /**
     * Returns the iterator of structural attributes contained in this
     * clone class.
     *
     * @return the iterator of structural attributes
     */
    Iterator<Attribute> iterateStructuralAttributes();

    /**
     * Counts structural attributes contained in this clone class.
     *
     * @return the number of structural attributes
     */
    int countStructuralAttributes();

    /**
     * Indexed accessor to structural attributes.
     *
     * @param index index of structural attribute to return
     * @return the reference to the structural attribute at the index
     */
    Attribute getStructuralAttribute(int index);

    /**
     * Counts extra attributes contained in this clone class.
     *
     * @return the iterator of extra attributes
     */
    public Iterator<Attribute> iterateExtraAttributes();

    /**
     * Not yet implemented
     * 
     * @return
     */
    public CloneClass getParent();
}

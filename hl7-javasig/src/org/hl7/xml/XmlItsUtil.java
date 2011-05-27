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
 * The Initial Developer of the Original Code is Jerry Joyce.
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): Gunther Schadow, Eric Chen
 */
package org.hl7.xml;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.hl7.meta.Association;
import org.hl7.meta.Attribute;
import org.hl7.meta.ChoiceAssociation;
import org.hl7.meta.CloneClass;
import org.hl7.meta.CmetAssociation;
import org.hl7.meta.Feature;

/**
 * Tool to find metadata by tag
 * 
 * @author Jerry Joyce
 */
public class XmlItsUtil {
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml.util");
	private static final Set<String> XMLITS_EXTRA_ATTRIBUTES = new HashSet<String>(Arrays
			.asList(new String[] { "type", }));

	/**
	 * Additional attribute defined by the XML ITS, not by Rim attribute
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean isAdditionalAttribute(String name) {
		return XMLITS_EXTRA_ATTRIBUTES.contains(name);
	}

	/**
	 * Looks up metadata for a linked clone class or nested datatype, based on XML tag name. Really belongs in XML ITS
	 * class.
	 * 
	 * @param cloneClass
	 *          parent clone class to start from
	 * @param tag
	 *          XML tag to look up
	 * @return metadata found, or null if not found
	 */
	public static Feature lookupMetadataByTag(CloneClass cloneClass, String tag) {
		if (tag == null)
			throw new IllegalArgumentException("tag cannot be null");
		String assocPrefix = tag + '_';
		LOGGER.finest("resolving tag: " + tag + " in clone class " + cloneClass.getName());
		for (Iterator it = cloneClass.iterateChildren(); it.hasNext();) {
			Feature feature = (Feature) it.next();
			LOGGER.finest("feature: " + feature.getName() + ":" + feature.getClass());
			if (feature instanceof Attribute) {
				if (tag.equals(feature.getName())) {
					LOGGER.finest("found attribute: " + feature);
					return (Attribute) feature;
				}
				// else continue
			} else if (feature instanceof Association) {
				if (feature instanceof ChoiceAssociation) {
					ChoiceAssociation choiceAssociation = (ChoiceAssociation) feature;
					LOGGER.finest("doing choices: " + choiceAssociation);
					for (Iterator it2 = choiceAssociation.iterateChoices(); it2.hasNext();) {
						Association association = (Association) it2.next();
						LOGGER.finest("trying choice: " + association.getName());
						if (association.getName().equals(tag)) {
							LOGGER.finest("found right choice: " + association);
							return association;
						}
					}
				} else if (feature instanceof CmetAssociation) {
					CmetAssociation cmetAssociation = (CmetAssociation) feature;
					// This is VERY dangerous. It is looking for meta information
					// based on elementname=cmetid OR if the elemementname is
					// *contained in* the choiceAssociationName.
					if (feature.getName().indexOf(tag) != -1) {
						LOGGER.finest("found cmet: " + feature);
						return (Association) feature;
					}
				} else { // normal Attribute
					if (tag.equals(feature.getName()) || feature.getName().startsWith(assocPrefix)) {
						LOGGER.finest("found association: " + feature);
						return (Association) feature;
					}
				}
			} else
				throw new Error("this case should not occur, please report: " + feature.getClass());
		}
		// Feature not found.
		LOGGER.finest("not found: " + tag);
		return null;
	}
}

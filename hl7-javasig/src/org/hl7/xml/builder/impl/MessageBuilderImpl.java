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
package org.hl7.xml.builder.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.hl7.meta.Association;
import org.hl7.meta.Attribute;
import org.hl7.meta.ChoiceAssociation;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Datatype;
import org.hl7.meta.Feature;
import org.hl7.meta.JavaIts;
import org.hl7.meta.LoaderException;
import org.hl7.meta.SimpleDatatype;
import org.hl7.meta.impl.JavaItsImpl;
import org.hl7.meta.util.MetaUtils;
import org.hl7.rim.RimObject;
import org.hl7.types.AD;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BIN;
import org.hl7.types.CS;
import org.hl7.types.EN;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.QTY;
import org.hl7.types.SET;
import org.hl7.util.FactoryException;
import org.hl7.xml.DatatypePresenterFactory;
import org.hl7.xml.builder.AssociationBuilderException;
import org.hl7.xml.builder.AttributeBuilderException;
import org.hl7.xml.builder.AttributeTypeException;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.ChoiceAssociationException;
import org.hl7.xml.builder.FeatureException;
import org.hl7.xml.builder.MessageBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.validator.CardinalityValidator;
import org.hl7.xml.validator.FeatureCardinalityException;
import org.hl7.xml.validator.ValidatorException;
import org.xml.sax.SAXException;

/**
 * Given a RIM class graph and HMD metadata, emits a sequence of SAX events corresponding to an XML document.
 * 
 * @author Skirmantas Kligys
 */
public class MessageBuilderImpl implements MessageBuilder {
	// -------------------------------------------------------------------------
	private static final JavaIts javaIts_ = new JavaItsImpl();
	// -------------------------------------------------------------------------
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml.builder");
	// -------------------------------------------------------------------------
	private final boolean laxMode_;

	// -------------------------------------------------------------------------
	public MessageBuilderImpl() {
		this(/* laxMode: */false);
	}

	// -------------------------------------------------------------------------
	public MessageBuilderImpl(boolean laxMode) {
		laxMode_ = laxMode;
	}

	// -------------------------------------------------------------------------
	/**
	 * @see org.hl7.xml.builder.MessageBuilder#
	 */
	public void build(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimEntryPoint, CloneClass metadataEntryPoint,
			String localName) throws BuilderException {
		// Debug.
		LOGGER.finest("Processing clone class " + metadataEntryPoint.getName());
		boolean started = false;
		try {
			builder.startDocument();
			// builder.addAttribute(DatatypePresenter.HL7_URI,"xsi", "xmlns:xsi", "CDATA", DatatypePresenter.W3_XML_SCHEMA);
			processStructuralAttributes(builder, rimEntryPoint, metadataEntryPoint);
			builder.startElement(localName);
			started = true;
			processFeaturesExceptStructuralAttributes(builder, rimEntryPoint, metadataEntryPoint);
		} catch (SAXException ex) {
			throw new BuilderException(ex);
		} finally {
			try {
				if (started) {
					builder.endElement(localName);
					builder.endDocument();
				}
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}
	}

	// FIXME: need to cut cyclical links.
	// This is tricky:
	// Keep a set of Objects which we have already emitted.
	// Then when coming over this object again, we emit only a stub.
	// But, the problem is, what if the first object had no id?
	// - We would have had to create one before!
	// - How do we know whether an object needs to have an id?
	// Second (more difficult) problem is: what if the MIF didn't allow us to emit all attributes, and we have additional
	// properties to represent?
	// - Might need to keep a table not of objects visited, but of object's features emited already.
	// - Then if we went accross a feature already, we skip it, unless it is an id, structural codes, or, perhaps, code
	// (to use code for linking in DEF/KIND cases).
	// - But that doesn't solve the problem entirely: if we crossed a feature twice, the second time descendent objects
	// may include other properties?
	// - we could make an entry for each pair of (RimObject, CloneClass)
	private static class SerializationTracingNode {
		private RimObject _object;
		private CloneClass _cloneClass;

		public SerializationTracingNode(RimObject object, CloneClass cloneClass) {
			_object = object;
			_cloneClass = cloneClass;
		}

		public boolean equals(Object that) {
			return this == that
					|| ((that instanceof SerializationTracingNode) && equivalent((SerializationTracingNode) that));
		}

		private boolean equivalent(SerializationTracingNode that) {
			return (this._object == that._object) && (this._cloneClass == that._cloneClass);
		}

		public int hashCode() {
			return _object.hashCode() + _cloneClass.hashCode();
		}
	}

	private Set<SerializationTracingNode> _serializationTrace = new HashSet<SerializationTracingNode>();

	private boolean hasNotAlreadyBeenSerialized(RimObject object, CloneClass cloneClass) {
		return _serializationTrace.add(new SerializationTracingNode(object, cloneClass));
	}

	// -------------------------------------------------------------------------
	/**
	 * Called recursively to process associations.
	 */
	private void buildInternal(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimEntryPoint,
			CloneClass metadataEntryPoint, String localName) throws BuilderException {
		// Debug.
		LOGGER.finest("Processing clone class " + metadataEntryPoint.getName());
		boolean started = false;
		try {
			processStructuralAttributes(builder, rimEntryPoint, metadataEntryPoint);
			builder.startElement(localName);
			started = true;
			if (hasNotAlreadyBeenSerialized(rimEntryPoint, metadataEntryPoint)) {
				processFeaturesExceptStructuralAttributes(builder, rimEntryPoint, metadataEntryPoint);
			} else {
				processFeaturesForObjectStub(builder, rimEntryPoint, metadataEntryPoint);
			}
		} catch (SAXException ex) {
			throw new BuilderException(ex);
		} finally {
			try {
				if (started) {
					builder.endElement(localName);
				}
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}
	}

	// -------------------------------------------------------------------------
	private void processStructuralAttributes(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimObject,
			CloneClass cloneClass) throws BuilderException {
		// Structural attributes must be processed before an enclosing XML elements is started.
		// FIXME: this is only true for those structural attributes which are in fact XML attributes!!! levelCode is an
		// exception
		for (Iterator<Attribute> it = cloneClass.iterateStructuralAttributes(); it.hasNext();) {
			Attribute attribute = (Attribute) it.next();
			processStructuralAttribute(builder, rimObject, attribute);
		}
		// Extra attributes must be processed before an enclosing XML elements is started.
		for (Iterator<Attribute> it = cloneClass.iterateExtraAttributes(); it.hasNext();) {
			Attribute attribute = (Attribute) it.next();
			processStructuralAttribute(builder, rimObject, attribute);
		}
	}

	// -------------------------------------------------------------------------
	private void processFeaturesExceptStructuralAttributes(RimGraphXMLSpeaker.ContentBuilder builder,
			RimObject rimObject, CloneClass cloneClass) throws BuilderException {
		for (Iterator<Feature> it = cloneClass.iterateChildren(); it.hasNext();) {
			Feature feature = (Feature) it.next();
			if (feature instanceof Attribute) {
				Attribute attribute = (Attribute) feature;
				// if(!attribute.isStructural() && !attribute.isExtra()) {
				if (!attribute.isStructural()) {
					processAttribute(builder, rimObject, attribute);
				}
			} else if (feature instanceof Association) {
				if (feature instanceof ChoiceAssociation)
					processChoiceAssociation(builder, rimObject, (ChoiceAssociation) feature);
				else
					processAssociation(builder, rimObject, (Association) feature);
			}
		}
		// split out the extension code as it is
		/*
		 * THIS CODE MAY BE WELL INTENTIONED BUT SOMEHOW IT MANIFESTS A BUG WHERE ALL EXTENSION ELEMENTS ARE DUMPED WITHIN A
		 * SINGLE FLAT LIST (EVEN THOUGH THEY WERE NESTED. WE BETTER NOT CONFUSE THINGS BY OUTPUTTING EXTENSION ELEMENTS FOR
		 * NOW. (GS) if (laxMode_ && false) { if (rimObject instanceof Extensible ) { Extensible extensible = (Extensible)
		 * rimObject; if (extensible.getExtensionNodes() != null) { for (Iterator iterator =
		 * extensible.getExtensionNodes().iterator(); iterator.hasNext();) { TreeContentHandler.Node node =
		 * (TreeContentHandler.Node) iterator.next(); if (node instanceof TreeContentHandler.Attribute) {
		 * TreeContentHandler.Attribute attribute = (TreeContentHandler.Attribute) node; // todo Handle the attribute } else
		 * if (node instanceof TreeContentHandler.Element) { TreeContentHandler.Element element =
		 * (TreeContentHandler.Element) node; ElementBuilder elementBuilder = new ElementPresenter().getBuilder();
		 * elementBuilder.build(builder, element); } } } } } END COMMENTING OUT PROBLEMATIC CODE
		 */
	}

	private void processFeaturesForObjectStub(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimObject,
			CloneClass cloneClass) throws BuilderException {
		for (Iterator<Feature> it = cloneClass.iterateChildren(); it.hasNext();) {
			Feature feature = (Feature) it.next();
			if (feature instanceof Attribute && feature.getName().equals("id")) {
				Attribute attribute = (Attribute) feature;
				// if(!attribute.isStructural() && !attribute.isExtra()) {
				if (!attribute.isStructural()) {
					processAttribute(builder, rimObject, attribute);
				}
			}
		}
	}

	// -------------------------------------------------------------------------
	private void processStructuralAttribute(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimObject,
			Attribute attribute) throws BuilderException {
		try {
			// TODO: Get rid of reflection.
			String rimPropertyName = attribute.getRimPropertyName();
			if ("typeID".equals(rimPropertyName)) // TODO: kludge fix the schema generator error refer
																						// MessageElementContentHandler
				rimPropertyName = "typeId";
			Method getter = javaIts_.getFeatureGetter(rimObject.getClass(), rimPropertyName);
			ANY value = (ANY) getter.invoke(rimObject, (Object[]) null);
			// No filling in if the value is present.
			if (CardinalityValidator.getDatatypeCardinality(value) == 0) {
				try {
					value = MetaUtils.getAttributeDefaultValue(value, attribute);
				} catch (FeatureException e) {
					LOGGER.warning(e.getFeature().getName() + ":" + e.getMessage());
				}
			}
			CardinalityValidator.checkAttributeCardinality(value, attribute);
			checkAttributeDataType(value, attribute);
			// Add an XML attribute to dtBuilder attribute list.
			if (value != null && value.nonNullJ()) {
				DatatypePresenterFactory.getInstance().createPresenter(value).getBuilder().buildStructural(builder, value,
						attribute.getName());
			}
		} catch (NoSuchMethodException ex) {
			handleAttributeException(attribute, ex);
		} catch (IllegalAccessException ex) {
			handleAttributeException(attribute, ex);
		} catch (InvocationTargetException ex) {
			handleAttributeException(attribute, ex);
		} catch (FactoryException ex) {
			handleAttributeException(attribute, ex);
		} catch (FeatureCardinalityException ex) {
			handleAttributeException(attribute, ex);
		} catch (BuilderException ex) {
			handleAttributeException(attribute, ex);
		}
	}

	// -------------------------------------------------------------------------
	private void processAttribute(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimObject, Attribute attribute)
			throws BuilderException {
		try {
			// TODO: Get rid of reflection. GS: why?
			Method getter = javaIts_.getFeatureGetter(rimObject.getClass(), attribute.getRimPropertyName());
			ANY value = (ANY) getter.invoke(rimObject, (Object[]) null);
			try { // Check cardinality and log any errors
				CardinalityValidator.checkAttributeCardinality(value, attribute);
			} catch (FeatureCardinalityException e) {
				handleAttributeException(attribute, e);
			}
			try { // Check Rim vs. HMD datatype and log any errors
				checkAttributeDataType(value, attribute);
			} catch (BuilderException e) {
				handleAttributeException(attribute, e);
			}
			// I TOTALLY DO NOT UNDERSTAND HOW WE COULD EVER DO THIS:
			// value = foldSingleDataTypeElement(value, attribute);
			// It turns any IVL<T> into a T
			// WHY, OH WHY????? (GS)
			// THIS WAS WROMG
			// Emit.
			builder.build(value, attribute);
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
			handleAttributeException(attribute, ex);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
			handleAttributeException(attribute, ex);
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
			handleAttributeException(attribute, ex);
		} catch (SAXException ex) {
			ex.printStackTrace();
			handleAttributeException(attribute, ex);
		} catch (BuilderException ex) {
			ex.printStackTrace();
			handleAttributeException(attribute, ex);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			handleAttributeException(attribute, ex);
		}
	}

	// -------------------------------------------------------------------------
	private void handleAttributeException(Attribute attribute, Exception exception) throws BuilderException {
		AttributeBuilderException wrappedException = (exception instanceof AttributeBuilderException) ? (AttributeBuilderException) exception
				: new AttributeBuilderException(attribute, exception);
		if (laxMode_)
			LOGGER.warning(exception.toString());
		else
			throw wrappedException;
	}

	// -------------------------------------------------------------------------
	private void checkAttributeDataType(ANY value, Attribute attribute) throws BuilderException {
		if (value == null)
			return;
		try {
			Datatype datatype = attribute.getDatatype();
			if (!datatype.checkDataType(value)) {
				throw new AttributeTypeException(attribute, value.getClass());
			}
		} catch (ClassNotFoundException ex) {
			throw new AttributeBuilderException(attribute, ex);
		}
	}

	private ANY foldSingleDataTypeElement(ANY value, Attribute attribute) {
		if (value == null)
			return null;
		if (value.isNull().isTrue())
			return value;
		Datatype datatype = attribute.getDatatype();
		if (datatype instanceof SimpleDatatype) {
			// IVL, BAG, LIST, SET with a single element are acceptable here.
			if (value instanceof BAG) {
				Iterator iter = ((BAG<ANY>) value).iterator();
				if (iter.hasNext())
					return (ANY) iter.next();
				else
					return null;
			} else if (value instanceof AD || value instanceof BIN || value instanceof EN) {
				return value;
			} else if (value instanceof LIST) {
				return ((LIST<ANY>) value).head();
			} else if (value instanceof IVL) {
				// should use of center. But because of the bug of IVL.center(), we use low instead.
				// return ((IVL<QTY>) value).center();
				return ((IVL<QTY>) value).low();
			} else if (value instanceof SET) {
				SET setvalue = (SET) value;
				if (setvalue.isEmpty().isTrue())
					return null;
				else
					// FIXME: SET.iterator does not ALWAYS return the type of element!!!!
					return setvalue.any();
			}
		}
		return value;
	}

	// -------------------------------------------------------------------------
	private void processAssociation(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimObject,
			Association association) throws BuilderException {
		// Debug.
		LOGGER.finest("Processing association " + association.getName());
		LOGGER.finest(association.toString());
		try {
			// TODO: Get rid of reflection.            
            // Use getPropertyName() instead of getRimPropertyName() to get 
            // the specialized name of the association.
			Method getter = javaIts_.getFeatureGetter(rimObject.getClass(), association.getPropertyName());
			Object distalObjectOrSet = getter.invoke(rimObject, (Object[]) null);
			CloneClass distalClass = association.getTarget();
			List<RimObject> matchingLinks = findMatchingLinks(distalObjectOrSet, association);
			CardinalityValidator.checkAssociationCardinality(matchingLinks, association);
			// Emit.
			if (matchingLinks != null) {
				for (Iterator<RimObject> it = matchingLinks.iterator(); it.hasNext();) {
					RimObject distalObject = (RimObject) it.next();
					// fix choice tag name <cmet040002> kludge. should be fixed in xml ITS spec
					// String elementname = association.getParent().getChoices() == null? association.getName()
					// : ((CmetAssociation)association).getCmetId();
					String elementname = association.getName();
					buildInternal(builder, distalObject, distalClass, elementname);
				}
			}
		} catch (NoSuchMethodException ex) {
			handleAssociationException(association, ex);
		} catch (IllegalAccessException ex) {
			handleAssociationException(association, ex);
		} catch (InvocationTargetException ex) {
			handleAssociationException(association, ex);
		} catch (LoaderException ex) {
			handleAssociationException(association, ex);
		} catch (BuilderException ex) {
			handleAssociationException(association, ex);
		} catch (ValidatorException e) {
			handleValidatorException(e);
		}
	}

	// -------------------------------------------------------------------------
	private List<RimObject> findMatchingLinks(Object distalObjectOrSet, Association association)
			throws AssociationBuilderException {
		if (distalObjectOrSet == null) {
			return null;
		} else if (distalObjectOrSet instanceof /* AssociationSet */Collection) {
			/* AssociationSet */Collection distalSet = (/* AssociationSet */Collection) distalObjectOrSet;
			// Make sure the cloneCode == association name.
			return checkCloneCode(distalSet.iterator(), association.getName());
		} else if (distalObjectOrSet instanceof RimObject) {
			RimObject distalObject = (RimObject) distalObjectOrSet;
			List<RimObject> l = new ArrayList<RimObject>();
			l.add(distalObject);
			return checkCloneCode(l.iterator(), association.getName());
		} else {
			throw new AssociationBuilderException(association, "Unknown association type: "
					+ distalObjectOrSet.getClass().getName());
		}
	}

	// -------------------------------------------------------------------------
	private void handleValidatorException(Exception exception) throws BuilderException {
		ValidatorException wrappedException = (exception instanceof ValidatorException) ? (ValidatorException) exception
				: new ValidatorException(exception);
		if (laxMode_) {
			LOGGER.warning(wrappedException.toString());
		} else {
			throw new BuilderException(wrappedException.toString());
		}
	}

	private void handleAssociationException(Association association, Exception exception) throws BuilderException {
		AssociationBuilderException wrappedException = (exception instanceof AssociationBuilderException) ? (AssociationBuilderException) exception
				: new AssociationBuilderException(association, exception);
		if (laxMode_)
			LOGGER.warning(wrappedException.toString());
		else
			throw wrappedException;
	}

	// -------------------------------------------------------------------------
	/**
	 * Iterate a list of RIM objects and return the ones with a specific cloneCode.
	 */
	private List<RimObject> checkCloneCode(Iterator<RimObject> i, String cloneCode) {
		List<RimObject> result = new ArrayList<RimObject>();
		while (i.hasNext()) {
			RimObject r = i.next();
			CS cc = r.getCloneCode();
			if (cc.code().toString().equals(cloneCode)) {
				result.add(r);
			}
		}
		return result;
	}

	// -------------------------------------------------------------------------
	private void processChoiceAssociation(RimGraphXMLSpeaker.ContentBuilder builder, RimObject rimObject,
			ChoiceAssociation choiceAssociation) throws BuilderException {
		LOGGER.finest("Processing choice association " + choiceAssociation.getName());
		LOGGER.finest(choiceAssociation.toString());
		try {
			// TODO: Get rid of reflection.
            
            // Use getPropertyName() instead of getRimPropertyName() to get 
            // the specialized name of the association.
			Method getter = javaIts_.getFeatureGetter(rimObject.getClass(), choiceAssociation.getPropertyName());
			Object distalObjectOrSet = getter.invoke(rimObject, (Object[]) null);
			LOGGER.finest("Distal object: " + distalObjectOrSet);
			List<RimObject> matchingLinks = null;
			CloneClass distalClass = null;
			String localName = null;
			for (Iterator<Association> itChoices = choiceAssociation.iterateChoices(); itChoices.hasNext();) {
				Association association = itChoices.next();
				CloneClass dc = association.getTarget();
				List<RimObject> currentMatchingLinks = findMatchingLinks(distalObjectOrSet, association);
				if (currentMatchingLinks != null && !currentMatchingLinks.isEmpty()) {
					if (matchingLinks == null) {
						localName = association.getName();
						matchingLinks = currentMatchingLinks;
						distalClass = dc;
						CardinalityValidator.checkAssociationCardinality(matchingLinks, association);
					} else {
						throw new ChoiceAssociationException(choiceAssociation, "Two or more choices match");
					}
				}
			}
			if (matchingLinks == null && choiceAssociation.getCardinality().getMin() >= 1) {
				throw new ChoiceAssociationException(choiceAssociation, "No matching choices found");
			}
			// Emit.
			if (matchingLinks != null) {
				for (Iterator<RimObject> it = matchingLinks.iterator(); it.hasNext();) {
					RimObject distalObject = it.next();
					buildInternal(builder, distalObject, distalClass, localName);
				}
			}
		} catch (NoSuchMethodException ex) {
			handleChoiceAssociationException(choiceAssociation, ex);
		} catch (IllegalAccessException ex) {
			handleChoiceAssociationException(choiceAssociation, ex);
		} catch (InvocationTargetException ex) {
			handleChoiceAssociationException(choiceAssociation, ex);
		} catch (LoaderException ex) {
			handleChoiceAssociationException(choiceAssociation, ex);
		} catch (BuilderException ex) {
			handleChoiceAssociationException(choiceAssociation, ex);
		} catch (ValidatorException e) {
			handleValidatorException(e);
		}
	}

	// -------------------------------------------------------------------------
	private void handleChoiceAssociationException(ChoiceAssociation choiceAssociation, Exception exception)
			throws BuilderException {
		ChoiceAssociationException wrappedException = (exception instanceof ChoiceAssociationException) ? (ChoiceAssociationException) exception
				: new ChoiceAssociationException(choiceAssociation, exception);
		if (laxMode_) {
			LOGGER.warning(wrappedException.toString());
		} else {
			throw wrappedException;
		}
	}
}

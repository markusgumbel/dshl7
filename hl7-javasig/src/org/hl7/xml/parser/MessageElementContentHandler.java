/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distribute on an "AS IS"
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
 * Contributor(s): Skirmantas Kligys, Eric Chen
 */
package org.hl7.xml.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import org.hl7.merger.Merger;
import org.hl7.merger.MergerManager;
import org.hl7.meta.Association;
import org.hl7.meta.Attribute;
import org.hl7.meta.CloneClass;
import org.hl7.meta.Datatype;
import org.hl7.meta.Feature;
import org.hl7.meta.JavaIts;
import org.hl7.meta.LoaderException;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.meta.impl.JavaItsImpl;
import org.hl7.meta.util.MetaUtils;
import org.hl7.rim.RimObject;
import org.hl7.types.ANY;
import org.hl7.types.CS;
import org.hl7.types.SET;
import org.hl7.types.UID;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.CSimpl;
import org.hl7.types.impl.OIDimpl;
import org.hl7.types.impl.SETjuSetAdapter;
import org.hl7.util.ApplicationContext;
import org.hl7.util.FactoryException;
import org.hl7.util.StringUtils;
import org.hl7.xml.DatatypePresenterBase;
import org.hl7.xml.XmlItsUtil;
import org.hl7.xml.builder.FeatureException;
import org.hl7.xml.validator.CardinalityValidator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * An XML SAX content handler for parsing generic HL7 message elements that stem
 * from RIM class clones. <p/> If you need an introduction into SAX parser
 * design, see the appendix after the Java code in this file. <p/> Also note the
 * DynamicContentHandler interface which adds significant magic to the
 * functioning of this code.
 * 
 * @author Gunther Schadow
 * @version $Id: MessageElementContentHandler.java 6234 2007-06-04 13:45:00Z
 *          gschadow $
 */
public class MessageElementContentHandler extends DynamicContentHandlerBase implements DynamicContentHandler, DynamicContentHandler.ResultReceiver {
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.xml.parser");

	// -------------------------------------------------------------------------
	private static final JavaIts JAVA_ITS_HELPER = new JavaItsImpl();

	/**
	 * The clone class we are parsing.
	 */
	CloneClass _cloneClass;
	/**
	 * The currently parsed feature in that clone class.
	 */
	private Feature _currentFeature;

	/**
	 * The result object that we are about to construct.
	 */
	private RimObject _result;

	/**
	 * The context where we find settings, persistence, etc.
	 */
	private final ApplicationContext _applicationContext;

	/**
	 * Get the result of this parse.
	 */
	public RimObject getResult() {
		return _result;
	}

	private static final String JSIG_NAMESPACE_URI = "urn:hl7-jsig";
	private static final String JSIG_ATT_CLASS = "class";

	/*
	 * TODO: this is not yet implemented and it raises serious questions about
	 * what we have so far: <p/> If mergeObjectsWithSameId, when updating, merge
	 * the roles, participations, act-relationships and roleLinks, if they have
	 * (a) the same source and target (b) matching typeCode (or classCode and
	 * code) (c) same sequence number (if applicable) (d) the role that we have
	 * parsed must not have an id of itself This is implemented in notifyResults
	 * when the result is such an associative object. We then query for another
	 * such association with same target, and then pick the first one that has:
	 * typeCode.nonNull.and(candidate.typeCode.nonNull).implies(typeCode.equals(candidate.typeCode))
	 * .and(sequenceNumber.nonNull.and(candidate.sequenceNumber.nonNull).implies(sequenceNumber.equals(candidate.sequenceNumber)))
	 * all of which we can actually do in the query itself by inner joining on
	 * all these keys.
	 */
	// private final boolean
	// CONF_MERGE_ASSOCIATIVE_OBJECT_WITH_SAME_SOURCE_AND_TARGET; // set in
	// constructor based on application context
	/**
	 * This is an experimental feature, where the source message can contain an
	 * attribute j:class with a Java FQCN of the class to instantiate. This is
	 * enabled only if the jclass option is set on. This is strictly speaking
	 * not very safe. Instead, the annotated MIF file should have such
	 * information. But even if and when it can be done with annotated MIF
	 * files, there may be the need for such info at runtime.
	 */
	private final boolean CONF_OBEY_JCLASS_ATTRIBUTE; // set in constructor
	// based on application
	// context

	private final boolean CONF_PRESERVE_EXTENSIONS; // set in constructor based
	// on application context

	private List<Merger> _mergers;

	/**
	 * This is called from a child content handler return back the result. Here,
	 * we set the current feature to the value returned.
	 */
	public void notifyResult(Object value) {
		for(Merger merger : _mergers) {
			if(_result == null) break;
			else if(merger.isApplicable(_result, value)) {
				_result = merger.merge(_result, _currentFeature, value, getDocumentLocator());
			}
		}
	}

	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if(_result == null) {
			// if we were turned off by a previous merger, do nothing 
			ignoreFeature(namespaceURI, localName, qName);
		}
		
		try {
			/*
			 * 1. check with metadata if this is a good element. We can do this
			 * in various modes: we can insist on the correct ordering, or we
			 * may not. We can throw up errors on unknown elements or we may
			 * not. In reality, it's better to be more tolerant than paranoid.
			 */
			LOGGER.finer(addLoc("parse " + _cloneClass.getName() + " " + localName));

			Feature thisFeature = XmlItsUtil.lookupMetadataByTag(_cloneClass, localName);

			if(_currentFeature != null && _currentFeature != thisFeature) {
				for(Merger merger : _mergers) {
					_result = merger.finish(_result, getDocumentLocator());
				}
			}

			_currentFeature = thisFeature;

			if(_currentFeature == null) {
				if(_result instanceof Extensible && CONF_PRESERVE_EXTENSIONS) {
					LOGGER.warning(addLoc("warning: unknown element " + localName + ", preserved as tree"));
					parseTree(namespaceURI, localName, qName, atts);
				} else {
					LOGGER.warning(addLoc("warning: unknown element " + localName + ", ignored"));
					ignoreFeature(namespaceURI, localName, qName);
				}
			} else {
				// Now, depending on whether the current feature is an Attribute
				// or an Association we do our thing.

				if(_currentFeature instanceof Attribute) parseAttribute((Attribute) _currentFeature, namespaceURI, localName, qName, atts);

				else if(_currentFeature instanceof Association) parseAssociation((Association) _currentFeature, namespaceURI, localName, qName, atts);

				else /* Oops, we should never get here! */
					throw new Error(addLoc("unhandled type of feature " + _currentFeature.getClass()));
			}

			/*
			 * and that's all we have to do here. See endElement for how the
			 * story continues.
			 */
			return;

		} catch(Exception ex) {
			LOGGER.throwing(this.getClass().getName(), "startElement", ex);
			throw new SAXParseException(addLoc(ex.getMessage()), getDocumentLocator(), ex);
		} catch(Throwable ex) {
			LOGGER.throwing(this.getClass().getName(), "startElement", ex);
			throw new SAXParseException(addLoc(ex.getMessage()), getDocumentLocator(), new Exception(ex));
		}
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if(_result == null) // if result has been turned off do nothing
			returnResult(null);
		else {
			try {
				// We only see an endElement for the things that we parse
				// ourselves.
				// if the data type value parser was called on an Attribute,
				// it will handle that endElement for us before it returns.

				// We will now do three things:

				// 1) conclude the parsing of the current object,
				// 2) notify the parent context about our result
				// 3) release ourselves from the XMLReader

				// NOTE: while doing any validation, the object cannot look into
				// properties of it's parent yet!

				// 1) To conclude parsing of this object may include
				// filling in defaults or perhaps doing some validation.
				// Note: defaults for structural codes must be applied initially
				// because those are used in some important decisions.
				// therefore, this applyDefaults(); is not needed here

				// FIXME: non structural code defaults are not at all applied at
				// this point!

				// Checks if there is a current collection pending that needs to
				// be flushed.
				for(Merger merger : _mergers)
					_result = merger.finish(_result, getDocumentLocator());

				// compact extension trees
				if(_result instanceof Extensible) {
					((Extensible) _result).compactExtensionNodes();
				}

				// 2) Return the results via the results notification and
				// release ourselves as a content handler.
				returnResult(_result);

			} catch(Exception ex) {
				throw new RuntimeException(addLoc(ex.getMessage()), ex);
				// throw new SAXParseException(addLoc(ex.getMessage()),
				// getDocumentLocator(), ex);

			} catch(Throwable ex) {
				throw new RuntimeException(addLoc(ex.getMessage()), ex);
			}
		}
	}

	/** Fill in defaults. This can be done multiple times. */
	private void applyDefaults() {
		LOGGER.fine(addLoc("add default attributes to " + _cloneClass.getName()));

		Iterator<Attribute> structuralAttributes = _cloneClass.iterateStructuralAttributes();

		while(structuralAttributes.hasNext()) {
			Attribute attribute = (Attribute) structuralAttributes.next();

			String defaultString = attribute.getDefaultValue();
			if(defaultString == null) {
				String constraints[] = attribute.getFixedValues();
				if(constraints != null && constraints.length > 0) {
					defaultString = constraints[0];
				}
			} // defaultString == null

			LOGGER.fine(addLoc("add default attribute " + attribute.getName() + " = \"" + defaultString + "\""));

			if(defaultString != null) {
				try {
					String attributeName = attribute.getRimPropertyName();
					Datatype dataType = attribute.getDatatype();

					String methodNameStem = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);

					Class type = JAVA_ITS_HELPER.getRIMDataType(_result, methodNameStem);

					// XXX: I only handle structural *codes* here for now
					if(type.isAssignableFrom(CS.class)) {
						ANY value = (ANY) _result.getClass().getMethod("get" + methodNameStem, (Class[]) null).invoke(_result, (Object[]) null);

						if(CardinalityValidator.getDatatypeCardinality(value) == 0) {
							LOGGER.fine(addLoc("add default attribute: " + attribute.getName() + " RIM name: " + attributeName + " attr: " + attribute + " RIM type: " + type + " type: " + dataType.getFullName()));
							CS cs = null;
							try {
								cs = (CS) MetaUtils.getAttributeDefaultValue(value, attribute);
							} catch(FeatureException e) {
								LOGGER.warning(addLoc(e.getFeature().getName() + ":" + e.getMessage()));
							}

							_result.getClass().getMethod("set" + methodNameStem, new Class[]
								{ type }).invoke(_result, new Object[]
									{ cs });
						}
					}
				} catch(NoSuchMethodException ex) {
					throw new Error(addLoc(ex.getMessage()), ex);
				} catch(IllegalAccessException ex) {
					throw new Error(addLoc(ex.getMessage()), ex);
				} catch(InvocationTargetException ex) {
					throw new Error(addLoc(ex.getMessage()), ex);
				}
			}
		} // end of while(structuralAttributes.hasNext())
	}

	private void parseAttribute(Attribute attribute, String namespaceURI, String localName, String qName, Attributes atts) {
		// Also, need to pass in the current attribute metadata,
		// because things like domain is here as well!

		Datatype dataType = attribute.getDatatype();

		// If we have ANY, we must do a little extra work to
		// make sure the appropriate handler is called.
		// We know we have an xsi type, so
		// we can use that to determine what the datatype actually is

		if(dataType.equals(DatatypeMetadataFactoryDatatypes.instance().ANYTYPE)) {
			try {
				String xsi = atts.getValue(DatatypePresenterBase.W3_XML_SCHEMA, "type");
				dataType = DatatypeMetadataFactoryImpl.instance().createByXsiType(xsi);
			} catch(UnknownDatatypeException ex) {
				LOGGER.warning(addLoc(ex.getMessage()));
			}
		}

		// If we have CS datatype as a regular xml element, such as
		// 'statusCode', we need to find out the code system name
		// from the damain name sicne CS xml schema data type does not allow to
		// have code system name
		if(dataType.equals(DatatypeMetadataFactoryDatatypes.instance().CSTYPE)) {
			UID codeSystem = MetaUtils.getCodeSystemId(attribute.getDomains());
			if(codeSystem != null) {
				CS cs = CSimpl.valueOf(atts.getValue("code"), codeSystem.toString());
				this.suspendWith(new SimpleTypeContentHandler(cs), null);
				return;
			}
		}

		DynamicContentHandler newContext = null;

		try {
			newContext = dataType.getHandler(namespaceURI, localName, qName, atts);
		} catch(FactoryException ex) {
			newContext = null;
		} catch(Throwable th) {
			newContext = null;
		}

		// Now we make the new context the content handler of the XML parser.
		if(newContext != null) this.suspendWith(newContext, atts);
		else {
			// no special ContentHandler class available, so we will use
			// the TreeContentHandler and then later we will hopefully
			// find a factory method that can use the little DOM to build
			// a value of this kind.
			LOGGER.warning(addLoc("No content handler for data type '" + dataType.getFullName() + "', element name " + localName));

			parseTree(namespaceURI, localName, qName, atts);
		}
	}

	private void ignoreFeature(String namespaceURI, String localName, String qName) {
		this.suspendWith(new IgnoreContentHandler(), null);
	}

	private void parseAssociation(Association association, String namespaceURI, String localName, String qName, Attributes atts) {
		try {
			CloneClass target = association.getTarget();
			MessageElementContentHandler newContext = new MessageElementContentHandler(_applicationContext, target, atts);

			// Now we make the new context the content handler of the XML
			// parser.
			this.suspendWith(newContext, atts);
		} catch(LoaderException ex) {
			throw new Error(addLoc(ex.getMessage()), ex);
		}
	}

	private void parseTree(String namespaceURI, String localName, String qName, Attributes atts) {
		DynamicContentHandler tree = new TreeContentHandler(namespaceURI, localName, qName, atts);
		this.suspendWith(tree, null);
	}

	/**
	 * Called when this DynamicContentHandler is activated. This gives us a
	 * chance to inspect and handle attributes. Process the structure attributes
	 */
	protected void notifyActivation(Attributes atts) {
		if(atts != null) {
			int max = atts.getLength();
			for(int i = 0; i < max; i++) {
				/*
				 * This process is similar as startElement()
				 * 
				 * Check with metadata if this is a good element. We can do this
				 * in various modes: we can insist on the correct ordering, or
				 * we may not. We can throw up errors on unknown elements or we
				 * may not. In reality, it's better to be more tolerant than
				 * paranoid.
				 * 
				 * The current feature must be an Attribute.
				 */
				Attribute attribute = null;

				attribute = (Attribute) XmlItsUtil.lookupMetadataByTag(_cloneClass, atts.getLocalName(i));
				if(attribute == null) {
					if(atts.getURI(i) == null || atts.getURI(i).length() == 0)
						{ // ignore if there is some namespace
							if(_result instanceof Extensible) {
								LOGGER.warning(addLoc("unhandled attribute: " + atts.getLocalName(i) + " add as extension node"));
								((Extensible) _result).addExtensionNode(new TreeContentHandler.Attribute(atts.getURI(i), atts.getLocalName(i), atts.getQName(i), atts.getValue(i)));

							} else {
								LOGGER.warning(addLoc("ignore unhandled attribute: " + atts.getLocalName(i)));
							}
						}
					continue;
				}

				Datatype attributeDatatype = attribute.getDatatype();

				try {
					String attributeName = attribute.getRimPropertyName();

					LOGGER.fine(addLoc("add structural code: " + attribute.getName() + " RIM name: " + attributeName + " attr: " + attribute));

					String methodNameStem = StringUtils.forceInitialCap(attributeName);

					Class type = JAVA_ITS_HELPER.getRIMDataType(_result, methodNameStem);
					String setterName = "set" + methodNameStem;
					ANY value = null;
					if(!type.isAssignableFrom(SET.class)) {

						Method setter = _result.getClass().getMethod(setterName, new Class[]
							{ Class.forName("org.hl7.types." + attributeDatatype.getFullName()) });

						if(attributeDatatype.equals(DatatypeMetadataFactoryDatatypes.instance().CSTYPE)) {
							/*
							 * FIXME: this could be something other than CS or
							 * BL, but turns out that right now it is only CS or
							 * BL.
							 */                             
							// Protect against null domains.
							String[] domains = attribute.getDomains();
							if(domains == null) domains = new String[] {};
							UID codeSystem = MetaUtils.getCodeSystemId(domains);
							if(codeSystem != null) value = CSimpl.valueOf(atts.getValue(i), codeSystem.toString());
							else {
								LOGGER.warning(addLoc("unknown code system for " + Arrays.asList(domains)));
							}
						} else if(attributeDatatype.equals(DatatypeMetadataFactoryDatatypes.instance().BLTYPE)) {
							value = BLimpl.valueOf(atts.getValue(i));
						} else /* Oops, we should never get here! */
							throw new Error(addLoc("assertion failed, data type is " + attributeDatatype.getFullName()));

						setter.invoke(_result, new Object[]
							{ value });

					} else {
						/*
						 * FIXME: this could be something other than SET<CS>,
						 * SET<OID>
						 */
						Datatype parameterType = ((ParametrizedDatatype) attribute.getDatatype()).getParameter(0);
						if(parameterType.equals(DatatypeMetadataFactoryDatatypes.instance().CSTYPE)) {
							/*
							 * FIXME: this could be something other than CS or
							 * BL, but turns out that right now it is only CS or
							 * BL.
							 */
							value = CSimpl.valueOf(atts.getValue(i), "CS-ID");

						} else if(parameterType.equals(DatatypeMetadataFactoryDatatypes.instance().OIDTYPE)) {
							value = OIDimpl.valueOf(atts.getValue(i));

						} else /* Oops, we should never get here! */
							throw new Error(addLoc("assertion failed, data type is " + attributeDatatype.getFullName()));

						value = SETjuSetAdapter.valueOf(Arrays.asList(new ANY[]
							{ value }));
						_result.getClass().getMethod(setterName, new Class[]
							{ type }).invoke(_result, new Object[]
								{ value });

					}

				} catch(ClassNotFoundException ex) {
					throw new Error(addLoc(ex.getMessage()), ex);
				} catch(NoSuchMethodException ex) {
					throw new Error(addLoc(ex.getMessage()), ex);
				} catch(IllegalAccessException ex) {
					throw new Error(addLoc(ex.getMessage()), ex);
				} catch(InvocationTargetException ex) {
					throw new Error(addLoc(ex.getMessage()), ex);
				}
			}
		}

		// Now apply defaults
		applyDefaults();

		// A HACK to make a reverse link, without it the graph is incomplete
		// till the
		// time that it is saved and loaded again through hibernate.
		//
		// Alternatively we should use the add... methods instead of the
		// currentCollection
		// and the setter.
		//
		// We do use the add method, but we do it too late, only when this
		// object is
		// completed and returned as a result. But some mergers may need the
		// context
		// information.
		if(_result instanceof org.hl7.rim.Role) {
			org.xml.sax.ContentHandler previousContentHandler = getPreviousHandler();
			if(previousContentHandler instanceof MessageElementContentHandler) {
				MessageElementContentHandler previousMessageElementContentHandler = (MessageElementContentHandler) previousContentHandler;
				RimObject previousResult = previousMessageElementContentHandler._result;
				Feature previousFeature = previousMessageElementContentHandler._currentFeature;
				org.hl7.merger.MergerOfRolesByScoperPlayerAndClass.establishBackLink(_result, previousFeature, previousResult);
			}
		}
	}

	MessageElementContentHandler(ApplicationContext applicationContext, CloneClass cloneClass, Attributes atts) {
		super();
		_applicationContext = applicationContext;
		CONF_OBEY_JCLASS_ATTRIBUTE = Boolean.parseBoolean(_applicationContext.getSetting("org.hl7.xml.parser.MessageElementContentHandler.obeyJClassAttribute", "false"));
		CONF_PRESERVE_EXTENSIONS = Boolean.parseBoolean(_applicationContext.getSetting("org.hl7.xml.parser.MessageElementContentHandler.preserveExtensions", "false"));
		_cloneClass = cloneClass;

		// Now we instantiate a RIM class

		_result = null;

		if(CONF_OBEY_JCLASS_ATTRIBUTE) {
			// This is a new experimental feature, where the source message can
			// contain
			// an attribute j:class with a Java FQCN of the class to
			// instantiate. This
			// is enabled only if the jclass option is set on.

			String jclass = atts.getValue(JSIG_NAMESPACE_URI, JSIG_ATT_CLASS);
			if(jclass != null) {
				try {
					_result = (RimObject) Class.forName(jclass).newInstance();
				} catch(Throwable ex) {
					LOGGER.warning(addLoc("could not instantiate j:class " + jclass));
				}
			}
		}

		if(_result == null) {
			try {
				_result = cloneClass.getInstance();
			} catch(FactoryException ex) {
				throw new Error(addLoc(ex.getMessage()), ex);
			}
		}

		_mergers = MergerManager.getApplicableMergers(applicationContext, _result);
	}
		
	// WHAT IS THIS FOR??? This is for the StandaloneDataTypeContentHandler ONLY!
	MessageElementContentHandler(ApplicationContext applicationContext, RimObject result, CloneClass cloneClass, Attributes atts) {
		super();
		CONF_OBEY_JCLASS_ATTRIBUTE = false;
		CONF_PRESERVE_EXTENSIONS = false;
		_applicationContext = applicationContext;
		_cloneClass = cloneClass;
		_result = result;
		_mergers = MergerManager.getAttributeMergerOnly(applicationContext, _result);
	}

	/** @deprecated */
	/* package */void setCloneCodeWhichIsABadHackAndShouldBeRemovedAtTheEarliestPossibleTime(String localName) {
		CS cloneCode = CSimpl.valueOf(localName, "1.2.3.4"); // FIXME
		_result.setCloneCode(cloneCode);
	}
}

/*
 * APPENDIX: About SAX Parser Writing
 * 
 * We first must understand that a SAX parser is an XML parser, usually a black
 * box product that deals with most of the pointy-bracket parsing. The sax
 * parser implements the XMLReader interface. Our parser is simply a
 * ContentHandler to the XML parser gizmo that we use. So, our parser receives
 * callback calls from the main parser. In order to receive such calls, it needs
 * to be registered as the parser's ContentHandler.
 * 
 * parser.setContentHandler(this);
 * 
 * This is a very important method, because we will have several content
 * handlers, who are specialists in parsing things like RIM objects, and data
 * type values. The RIM object parser will probably be a single ContentHandler
 * that interprets the HMD metadata to work. But data types will use a different
 * content handler per each different data type.
 * 
 * The content handler methods are (in the order of practical importance):
 * 
 * void startElement(String namespaceURI, String localName, String qName,
 * Attributes atts) Receive notification of the beginning of an element.
 *  -> this is the only start-event that really matters for the bulk of the RIM
 * object parsing. On a start element event, the RIM parser will have to query
 * the HMD metadata thing to see - if this new one is a valid element allowed in
 * the current element - if it should create any other default elements that
 * should have occurred in the message (default handling) - which other handler
 * (if any) to use to proceed with parsing this element.
 * 
 * If our RIM object parser does not call another specialist parser, it will now
 * put its current context on a stack and set up a new context for the current
 * element, then it will listen for additional events.
 * 
 * NOTE: we have identified the parser itself with the stack frame (or
 * "context"), and use the DynamicContentHandler framework to implement the
 * stack.
 * 
 * void endElement(String namespaceURI, String localName, String qName) Receive
 * notification of the end of an element.
 *  -> this is an indication that the parser is done with something. Typically
 * corresponds with completing the creation and setup of the newly instantiated
 * RIM object filling in all remaining defaults, then popping the old context
 * off the stack and proceeding with parsing the parent element.
 * 
 * NOTE: with the DynamicContentHandler, the popping off the stack is a little
 * different, so transparent it's almost like magic :-) (clue: watch for
 * releaseReader() call.)
 * 
 * void characters(char[] ch, int start, int length) Receive notification of
 * character data. void ignorableWhitespace(char[] ch, int start, int length)
 * Receive notification of ignorable whitespace in element content.
 *  -> these are probably only used by the data type value parsers!
 * 
 * 
 * void startDocument() Receive notification of the beginning of a document.
 * void endDocument() Receive notification of the end of a document.
 *  -> these are trivial, probably handled by some top-level setter-upper gizmo.
 * 
 * The rest is special things that I won't even list here.
 */

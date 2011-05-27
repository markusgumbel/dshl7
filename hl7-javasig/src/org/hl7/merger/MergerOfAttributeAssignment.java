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
 */
package org.hl7.merger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import org.hl7.meta.Attribute;
import org.hl7.meta.Datatype;
import org.hl7.meta.Feature;
import org.hl7.meta.JavaIts;
import org.hl7.meta.impl.JavaItsImpl;
import org.hl7.rim.RimObject;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.QSET;
import org.hl7.types.SC;
import org.hl7.types.SET;
import org.hl7.types.TS;
import org.hl7.types.ValueFactory;
import org.hl7.types.enums.SetOperator;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.QSETDifferenceImpl;
import org.hl7.types.impl.QSETIntersectionImpl;
import org.hl7.types.impl.QSETPeriodicHullImpl;
import org.hl7.types.impl.QSETUnionImpl;
import org.hl7.util.ApplicationContext;
import org.hl7.util.StringUtils;
import org.xml.sax.Locator;

/** Merges attribute data. */
public class MergerOfAttributeAssignment<C extends RimObject, T extends ANY> implements Merger<C, T> {
	protected static final Logger LOGGER = Logger.getLogger("org.hl7.merger");

	public MergerOfAttributeAssignment(ApplicationContext applicationContext) { }

	public boolean isStaticallyApplicable(Object object) {
		return object != null && object instanceof RimObject;
	}

	public boolean isApplicable(C object, Object value) {
		return value != null && value instanceof ANY;
	}

	/**
	 * If this is not null, those are the collection elements that we
	 * are just in the middle of parsing.
	 */
	private Collection<T> _currentCollection = null;

	/**
	 * If this is not null, this is the type of the current collection
	 * that we are just in the middle of collecting all its elements.
	 */
	private Class _currentCollectionType = null;

	/** The currently parse attribute, allows us to detect when we need to flush collections. */
	private Attribute _currentAttribute;

	public C merge(C object, Feature feature, T value, Locator loc) {
		// check if there is a current collection pending that needs to be flushed
		if (_currentAttribute != null && _currentAttribute != feature && _currentCollection != null)
			object = flushCurrentCollection(object, loc);

		if(feature instanceof Attribute) {
			_currentAttribute = (Attribute)feature;
			return add(object, _currentAttribute, value, loc);
		} else
			return object;

		// FIXME: this reall should go into isApplicable ... and that questions why we even need this two-part thing.
		// why can't we just call one optioinal merge?
	}

	private static final JavaIts JAVA_ITS_HELPER = new JavaItsImpl();

	public C finish(C object, Locator loc) { 
		// check if there is a current collection pending that needs to be flushed
		if (_currentCollection != null)
			object = flushCurrentCollection(object, loc);
		_currentCollection = null;
		_currentAttribute = null;
		_currentCollectionType = null;
		return object;
	}

	public C add(C result, Attribute attribute, T value, Locator loc) {
		LOGGER.finest(MergerUsingCache.addLoc("add attribute: " + attribute.getName() +
												" RIM property name: " + attribute.getRimPropertyName() +
												" assoc: " + attribute, loc));

		try {
			String rimPropertyName = attribute.getRimPropertyName();
			Datatype dataType = attribute.getDatatype();
			String methodNameStem = StringUtils.forceInitialCap(rimPropertyName);
			Class type = JAVA_ITS_HELPER.getRIMDataType(result, methodNameStem);

			//XXX: Wouldn't it be nice if we could ask this QSET.class.isAssignableFrom(type)
			//FIXME: instead we have this very, very ugly hack because we can't trust our meta stuff
			if(value instanceof TS && IVL.class.isAssignableFrom(type)) {
				value = (T)ValueFactory.getInstance().IVLvalueOf(BLimpl.TRUE, (TS)value,(TS)value, BLimpl.TRUE);
			} else if(value instanceof TS && SET.class.isAssignableFrom(type)) {
				//XXX: or should we create an IVL instead with this value being the center?
				value = (T)ValueFactory.getInstance().QSETvalueOf((TS)value);
			}

			if(value instanceof SC) {
				result.getClass()
					.getMethod("set" + methodNameStem, new Class[]{type})
					.invoke(result, new Object[]{value});
				return result;
			}

			if(type.isAssignableFrom(value.getClass())
				 && !type.equals(ANY.class) 
                                 && !type.equals(SET.class) // XXX: all these tests look silly, there's something wacko here'
                                 && !type.equals(LIST.class)
                                 && !type.equals(BAG.class)
				 && attribute.getCardinality().getMax() == 1) {
				// Maxim cardinality is 1,
				// Do not care about SimpleDatatype or ParametrizedDatatype
				LOGGER.fine(MergerUsingCache.addLoc("add simple attribute: " + attribute.getName() +
																						" RIM name: " + rimPropertyName +
																						" attr: " + attribute +
																						" RIM type: " + type +
																						" type: " + dataType.getFullName(), loc) +
										" value.type: " + value.getClass() + 
										" value: " + value.toString());
				
				if (type != null)	{
					// XXX, GS sez don't do this:
					// CardinalityValidator.checkMaxOneCardinality(result, methodNameStem, attribute);
					// 1) this is not a specific criterion to check this message, as the data
					//    object can have been resolved to the database
					// 2) it is too expensive of a check to do this reflection dance done inside this
					//    procedure
					// 3) instead cardinality checks should be done exclusively in the message parser
					//    if need be by checking off a list of features already seen
					// 4) I don't want to spend cycles for these checks anyway
					result.getClass()
						.getMethod("set" + methodNameStem, new Class[]{type})
						.invoke(result, new Object[]{value});
					return result;
				}
			}

			if(SET.class.isAssignableFrom(type) // NOW INCLUDING QSET.class.isAssignableFrom(type) !!!
				 || BAG.class.isAssignableFrom(type)
				 || (LIST.class.isAssignableFrom(type) && !SC.class.isAssignableFrom(type))
				 || type.equals(ANY.class)) // this is new, because ANY can mean collection (even if it says that cardinality is 1)	
			{
				// In RMIM, RIM class's IVL<TS> could constrain to TS
				LOGGER.fine(MergerUsingCache.addLoc("add collection element: " + attribute.getName() +
													 " RIM name: " + rimPropertyName +
													 " attr: " + attribute +
													 " RIM type: " + type +
													 " type: " + dataType.getFullName() +
													 " value.type: " + value.getClass() + 
													 " value: " + value.toString(),loc));
				
				if(value instanceof TS)
					throw new Error(MergerUsingCache.addLoc("should not be here " + value + " " + dataType, loc));
				
				// if we have an HL7 collection we will save the elements
				// on the currentCollection
				if (_currentCollection == null) {
					_currentCollection = new ArrayList();
					_currentCollectionType = type; //FIXME: that needs to be changed, to be the most specific generalization of the two types
				}
				_currentCollection.add(value);
				
				return result;
			}
			
			/* FIXME: this is a hack to get the following to work:
			 *  <value value="13" unit="mm" xsi:type="PQ"/>
			 *
			 * XXX: GS sez, this looks like a hack, how can that be right?
			 if (value instanceof QTY) {
			 result.getClass()
			 .getMethod("set" + methodNameStem, new Class[]{type})
			 .invoke(result, new Object[]{value});
			 return result;
			 }
			*/
			
			// XXX: GS sez: why do people comment out exceptions??? This is a
			// case of a program deficiency, we need these exception to help
			// us complete our work properly. If we comment these out, it only
			// looks like it's working when in fact it isn't!
			throw new Error(MergerUsingCache.addLoc("unhandled case add of value '" + value + "' to attribute " + attribute + " : " + type, loc));
			
		} catch (NoSuchMethodException ex) {
			throw new Error(MergerUsingCache.addLoc(ex.getMessage(),loc), ex);
		}	catch (IllegalAccessException ex) {
			throw new Error(MergerUsingCache.addLoc(ex.getMessage(),loc), ex);
		}	catch (InvocationTargetException ex) {
			throw new Error(MergerUsingCache.addLoc(ex.getMessage(),loc), ex);
		}
	}

	/**
	 * Assign the currentCollection to the currentAttribute.
	 * <p/>
	 * XXX: this only works well if all elements that are members of this
	 * collection do indeed appear together. This is true for strict
	 * XML schema data, but need not be true for a relaxed syntax.
	 */
	public C flushCurrentCollection(C result, Locator loc) {
		try {
			String attributeName = _currentAttribute.getRimPropertyName();

			LOGGER.finer(MergerUsingCache.addLoc("flush collection: "
													+ _currentAttribute.getName() +
													", RIM name: " + attributeName +
													", attr: " + _currentAttribute +
													", type: " + _currentCollectionType, loc));

			ANY value = null;

			// FIXME: confusion about currentCollectionType:
			// - result object type (for creating the setter)
			// - type inferred from values (for ANY)
			// - - inferred from xsi:type(s)
			// - - inferred from Java object type(s)
			// FIXME: presently _curentCollectionType comes from the MIF type of the feature
			Class type = _currentCollectionType;

			// if current collection type is ANY, we have to find out what it really is!
			// And there is no way to distinguish between LIST and SET and BAG
			// Here we just fish for QSET because we must handle that specifically
			if(_currentCollectionType.equals(ANY.class)
                            || _currentCollectionType.equals(SET.class)) {
				boolean couldBeQSET = true;
				boolean someIndicationForQSET = false;
				for(ANY component : _currentCollection) {
					if(component instanceof QSET) {
						someIndicationForQSET = true;
					}	else
						couldBeQSET = false;
				}
				if(couldBeQSET && someIndicationForQSET)
					_currentCollectionType = QSET.class;
			}

			if (LIST.class.isAssignableFrom(_currentCollectionType)) {
				value = ValueFactory.getInstance().LISTvalueOf(_currentCollection);
				// type = LIST.class;

			} else if (QSET.class.isAssignableFrom(_currentCollectionType)) {
				// A QSET collection will have these operator elements still here which we now have to use to build a proper QSET term
				QSET resultTerm = null;
				for(QSET component : (Collection<QSET>)_currentCollection) {
					SetOperator operator = component.getOperator();
					component.setOperator(null);
					
					if(resultTerm == null)
						if(operator == null || operator.implies(SetOperator.Include).isTrue())
							resultTerm = component;
						else
							throw new RuntimeException("don't have a starting value for a QSET combination");
					else switch(operator) {
					case Include:
						resultTerm = QSETUnionImpl.valueOf(resultTerm, component);						
						break;
					case Exclude:
						resultTerm = QSETDifferenceImpl.valueOf(resultTerm, component);						
						break;
					case ConvexHull:
						throw new UnsupportedOperationException("QSETConvexHull");
						// resultTerm = QSETConvexHullImpl.valueOf(resultTerm, component);						
						// break;
					case PeriodicHull:
						resultTerm = QSETPeriodicHullImpl.valueOf(resultTerm, component);						
						break;
					case Intersect: //FALLTHROUGH
					default: 
						resultTerm = QSETIntersectionImpl.valueOf(resultTerm, component);					 
					}
				}

				value = resultTerm;
				// type = SET.class;

			}	else if (SET.class.isAssignableFrom(_currentCollectionType)) {
				value = ValueFactory.getInstance().DSETvalueOf(_currentCollection);
				// type = SET.class;

			}	else if (BAG.class.isAssignableFrom(_currentCollectionType)) {
				value = ValueFactory.getInstance().BAGvalueOf(_currentCollection);
				// type = BAG.class;

			}	else if (_currentCollectionType.equals(ANY.class)) {
				if(_currentCollection.size() > 1)
					value = ValueFactory.getInstance().BAGvalueOf(_currentCollection); // XXX: this could well be LIST
				else if(_currentCollection.size() == 1)
					value = (ANY)_currentCollection.iterator().next();
				// type = ANY.class;
			}

			//Don't do this here, it's not right to do this on objects:
			//CardinalityValidator.checkAttributeCardinality(_currentCollection, _currentAttribute);

			result.getClass()
				.getMethod("set" + StringUtils.forceInitialCap(attributeName), new Class[]{type})
				.invoke(result, new Object[]{value});
			return result;
					
		} catch(NoSuchMethodException ex) {
			throw new Error(MergerUsingCache.addLoc(ex.getMessage(),loc), ex);

		} catch(IllegalAccessException ex) {
			throw new Error(MergerUsingCache.addLoc(ex.getMessage(),loc), ex);

		} catch(InvocationTargetException ex) {
			throw new Error(MergerUsingCache.addLoc(ex.getMessage(),loc), ex);

		}	finally {
			_currentCollection = null;
			_currentCollectionType = null;
		}
	}
}

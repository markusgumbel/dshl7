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
 * Portions created by Initial Developer are Copyright (C) 2002-2005 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hpath;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.hl7.rim.Act;
import org.hl7.rim.ActRelationship;
import org.hl7.rim.Entity;
import org.hl7.rim.Participation;
import org.hl7.rim.Role;
import org.hl7.rim.RoleLink;
import org.hl7.types.TEL;
import org.hl7.types.ENXP;
import org.hl7.types.PNXP;
import org.hl7.types.ONXP;
import org.hl7.types.ADXP;
import org.hl7.types.ST;
import org.hl7.types.BL;
import org.hl7.types.SET;
import org.hl7.types.DSET;
import org.hl7.types.LIST;
import org.hl7.types.BAG;
import org.hl7.types.ValueFactory;

/**
 * The Property Expression of HPath can follow any expression after the property operator.
 * 
 * @author Gunther Schadow
 * @version $Id$
 * 
 * TODO: Support RIM special properties e.g. consumable instead of
 * participation[typeCode.implies(CS:ParticipationType:Consumable)]
 * 
 * TODO: Support defauly values for properties which exist in Role and also in the player Entity
 */ 
/* package */ class Property<R,P,Q,T> extends ExpressionStep<R,P,Q,T> {
  List<Expression<R,?>> _arguments = null;
  String _name; // this name is intern()-ed, so we can compare by identity
  
  public static final Language.ExpressionForm FORM = new Language.ExpressionForm() {
      public Expression parse(StaticContext staticContext) throws SyntaxError {
				return Property.parse(staticContext);
      }
    };
  
  public static Expression parse(StaticContext staticContext) throws SyntaxError {
    final Token token = staticContext.tokenizer().peek();
    switch(token.type()) {
    case IDENT:
      if(staticContext.expression() == null) {
				staticContext.tokenizer().next();
				List<Expression> arguments = parseArguments(staticContext);
				return staticContext.expression(makePropertyOrConversion(null, token.string(), arguments, false));
      } else 
				throw new SyntaxError("in " + staticContext.expressionString() + " at '" + token.string() + "'");
    case CPROPIDENT:
      if(staticContext.expression() == null) {
				staticContext.tokenizer().next();
				List<Expression> arguments = parseArguments(staticContext);
				return staticContext.expression(makePropertyOrConversion(null, (String)token.value(), arguments, true));
      } else 
				throw new SyntaxError("in " + staticContext.expressionString() + " at '" + token.string() + "'");
    case PROP:
      Expression previousExpression = staticContext.expression();
      if(previousExpression != null) {
				staticContext.tokenizer().next();
				Token ident = staticContext.tokenizer().peek();
				switch(ident.type()) {
				case IDENT: {
					staticContext.tokenizer().next();
					List<Expression> arguments = parseArguments(staticContext);
					return staticContext.expression(makePropertyOrConversion(staticContext.expression(), ident.string(), arguments, false));	  
				}
				case CPROPIDENT: {
					staticContext.tokenizer().next();
					List<Expression> arguments = parseArguments(staticContext);
					return staticContext.expression(makePropertyOrConversion(staticContext.expression(), (String)ident.value(), arguments, true));
				}
				default:
					throw new SyntaxError("ident or @ident expected in " + staticContext.expressionString() + " at '" + ident.string() + "'");
				}
      } else
				throw new SyntaxError("in " + staticContext.expressionString() + " at '" + token.string() + "'");
			// FIXME: allow an identity-property which will just return the context, needed for coalescing (a|.)[1]
    default:
      return null;
    }
  }

	private static Expression makePropertyOrConversion(Expression previousExpression, String name, List<Expression> arguments, boolean doNotItemize) {		
		Conversion conversion = null;
		conversion = Conversion.instance(previousExpression, name, arguments, doNotItemize);
		if(conversion != null)
			return conversion;
		else
			return new Property(previousExpression, name, arguments, doNotItemize);
	}

  private static List<Expression> parseArguments(StaticContext staticContext) {
    if(staticContext.tokenizer().peek().type() == TokenType.LPAR) {
      Expression savedStaticContextExpression = staticContext.expression();
      staticContext.tokenizer().next();
      List<Expression> arguments = new ArrayList<Expression>();
      boolean isFresh = true;
      while(staticContext.tokenizer().peek().type() != TokenType.RPAR) {
				if(isFresh)
					isFresh = false;
				else
					staticContext.tokenizer().next(TokenType.COMMA);
				staticContext.expression(null);
				arguments.add(Expression.parse(staticContext));
      }
      staticContext.tokenizer().next(TokenType.RPAR);
      staticContext.expression(savedStaticContextExpression);
      return arguments;
    } else 
      return null;
  }
 
	/* package */static <R> List<List<?>> evaluateArguments(Evaluation<R, ?> evaluation, List<Expression<R, ?>> arguments) {
		List<List<?>> evaluatedArguments = new ArrayList<List<?>>(arguments.size());

		for(Expression<R, ?> argument : arguments) {
			List arg = new ArrayList();
			for(Object element : evaluation.getCurrentAsChild(argument))
				arg.add(element);
			evaluatedArguments.add(arg);
		}
		
		return evaluatedArguments;
	}
  		  
	/* package */static <R> Object[] prepareArguments(List<List<?>> evaluatedArguments, Class<?> parameterTypes[]) {
		Object[] argvals = new Object[parameterTypes.length];
		int i = 0;
		for(List<?> evaluatedArgument : evaluatedArguments) {
			Class<?> parameterType = parameterTypes[i];
			
			if(parameterType.equals(BL.class))
				argvals[i] = ValueFactory.getInstance().BLvalueOf(Evaluation.effectiveBooleanValue(evaluatedArgument.iterator()));

			else if(parameterType.equals(Boolean.class))
				argvals[i] = Evaluation.effectiveBooleanValue(evaluatedArgument.iterator());

			else if(parameterType.equals(Set.class))
				argvals[i] = new HashSet(evaluatedArgument);
			
			else if(parameterType.equals(List.class))
				argvals[i] = evaluatedArgument;
			
			else if(parameterType.equals(LIST.class))
				argvals[i] = ValueFactory.getInstance().LISTvalueOf(evaluatedArgument);
			
			else if(parameterType.equals(BAG.class))
				argvals[i] = ValueFactory.getInstance().BAGvalueOf(evaluatedArgument);
			
			else if(parameterType.equals(SET.class) || parameterType.equals(DSET.class))
				argvals[i] = ValueFactory.getInstance().DSETvalueOf(evaluatedArgument);

			else if(evaluatedArgument.size() > 0)
				argvals[i] = evaluatedArgument.get(0);

			else if(evaluatedArgument.size() > 0)
				argvals[i] = null;

			i++;
		}
		return argvals;
	}
  		  
  private Property(Expression<R,P> previousExpression, String name, List<Expression<R,?>> arguments, boolean doNotItemize) {
    super(previousExpression, doNotItemize);
    _name = name.intern();
    _arguments = arguments;
	}

  public String toString() {
    return "Property[" + _previousExpression + " " + (_doNotItemize ? "@" : "" ) + _name + " " + _arguments + "]";
  }
  
	protected InnerIterator newIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,P> parentIterator) {
		return new InnerIterator(evaluation, parentIterator);
	}  

  class InnerIterator extends ExpressionStepIterator<R,P,Q,T>  {
    InnerIterator(Evaluation<R,?> evaluation,  EvaluationIterator<R,P> parentIterator) {
			super(evaluation, parentIterator, _doNotItemize, false);
		}

		public String toString() {
			return super.toString() + "[" + _evaluation + ", " 
				+ _parentIterator + (_parentIterator == null ? "" : "("+_parentIterator.hasOnlySingleValue()+")") +", " 
				+ _itemizerIterator + (_itemizerIterator == null ? "" : "("+ _itemizerIterator.hasOnlySingleValue()+")") +", " + hasOnlySingleValue() + "]";
		}

		/** The type of the last result, used to borrow a value against this property if it had returned no value. */
		Type _currentType = null;

		/** The type of the last result, used to borrow a value against this property if it had returned no value. */
		public Type currentType() { 
			if(_currentType != null) // this usually means we actually had a result, so don't need to make anything up
				return _currentType;
			else {
				// This happens if we had not received a useable result from upstream, now we have to use inferencing.
				// See the implementation of ExpressionStepIterator#currentType()
				Type typeQforApply = super.currentType();

				if(_arguments != null && _arguments.size() == 0) { // special cases:
					// a function self() with no arguments. In this case T = Q
					if(_name == "self")
						return typeQforApply;
					else if(_name == "null")
						return null;
					// XXX: here would be our way of adding extension functions also
				}
				
				// Given this type all we have to do is find the method that we could apply and get its return type
				if(!(typeQforApply instanceof Class)) 
					throw new Error("unprepared to handle typeQforApply other than Class, got: " + typeQforApply + 
											(typeQforApply != null ? " " + typeQforApply.getClass() : ""));

				Method method = findMethod((Class/*Q*/)typeQforApply, null);
				return method.getGenericReturnType();
			}
		}

		/** Holds evaluated arguments as a side-effect of findMethod. This might happend just when we want to get the type of the method. */
		private Object _theArgvals[];
		
    protected T apply(Q context) {
			if(context == null)
				return null;
			
      if(_arguments != null && _arguments.size() == 0) { // special cases:
				// a function self() with no arguments. In this case T = Q
				if(_name == "self")
					return (T)context;
				else if(_name == "null")
					return null;
				// XXX: here would be our way of adding extension functions also
			}

			Method method = findMethod(context.getClass(), context/*for making error messages only*/);
			_currentType = method.getGenericReturnType();
			
			// NOTE: _theArgvals was set as a side-effect of findMethod.
			
			try {
				return (T)method.invoke(context, _theArgvals);
			} catch(IllegalAccessException ex) {
				throw new EvaluationException(Property.this, context, method.toString(), ex);
			} catch(InvocationTargetException ex) {
				throw new EvaluationException(Property.this, context, method.toString(), ex);
			}	

			// TODO: handle fields
		}
		
		private Method findMethod(Class/*Q*/ contextClass, Q contextForError) {
			_theArgvals = null; // RESET the argvals

      if(_arguments == null) { // this part we know works, so won't mess it up
				try { // check for name()
					return contextClass.getMethod(_name);
				} catch(NoSuchMethodException ex) { /* FALLTHROUGH */ }
	
				try { // check for getName()
					return contextClass.getMethod("get" + _name.substring(0,1).toUpperCase() + _name.substring(1));
				} catch(NoSuchMethodException ex) { /* FALLTHROUGH */ }
	
				/*FALLTHROUGH*/
				throw new PropertyNotFoundException(Property.this, contextForError, _name + " in " + contextClass);

			} else { // with arguments we have to dynamically find the best matching method

				Method methods[] = contextClass.getMethods();
				
				// we save the evaluated arguments on a list so that we don't have to evaluate them twice
				// even if we might cast them differently with respect to effectiveBooleanValue, collection,
				// or singleton.
				List<List<?>> evaluatedArguments = null;
				
			method:
				for(int i = 0; i<methods.length; i++) {
					Method method = methods[i];
					if(method.getName().equals(_name) || method.getName().equals("get" + _name.substring(0,1).toUpperCase() + _name.substring(1))) {
						Class parameterTypes[] = method.getParameterTypes();
						if(parameterTypes.length == _arguments.size()) { // o.k. matching number of arguments
							
							if(evaluatedArguments == null) // deferred evaluation now
								evaluatedArguments = evaluateArguments(_evaluation, _arguments); 
							
							Object argvals[] = prepareArguments(evaluatedArguments, parameterTypes); 
							
							for(int argi = 0; argi < parameterTypes.length; argi++) // now check the types
								if(! (argvals[argi] == null || parameterTypes[argi].isAssignableFrom(argvals[argi].getClass())))
									continue method; // next method
							
							// if we made it until here:							
							_theArgvals = argvals;
							return method;
						}
					}
				} 
				/*FALLTHROUGH*/
				throw new PropertyNotFoundException(Property.this, contextForError, _name + "(" + argsToString(evaluatedArguments) + ")");
      }
    }

		boolean _updateIsConstructive = true;

		/** XXX: But we can only tell after we have tried to update at least one element to check what the strategy was. */
		public boolean updateIsConstructive() {
			return _updateIsConstructive;
		}
		
		public void update(T newValue) {
			EvaluationIterator<?,Q> contextIterator = _itemizerIterator != null ? _itemizerIterator : (EvaluationIterator<?,Q>)_parentIterator;
			Q currentContext = contextIterator.current();
			if(currentContext == null)
				throw new RuntimeException("attempt to update a null context: " + Property.this + " to be set to " + newValue);
			set(contextIterator, newValue);
		}
		
		public void insert(T newValue) { 
			EvaluationIterator<?,Q> contextIterator = _itemizerIterator != null ? _itemizerIterator : (EvaluationIterator<?,Q>)_parentIterator;
			Q currentContext = contextIterator.current();
			if(currentContext == null)
				throw new RuntimeException("attempt to update a null context: " + Property.this + " to be set to " + newValue);
			if(current() != null)
				throw new RuntimeException("attempt to insert a singular value where one already exists: " + Property.this + " to be set to " + newValue + " but was " + current());
			set(contextIterator, newValue);
		}
		
		private void set(EvaluationIterator<?,Q> contextIterator, T newValue) {
			Q currentContext = contextIterator.current();
			Q newContext = currentContext;
			
			for(UpdateStrategy<Q,T> updateStrategy : (UpdateStrategy<Q,T>[])UPDATE_STRATEGIES) {
				if(updateStrategy.isApplicable(currentContext, _name, newValue)) {
					_updateIsConstructive |= updateStrategy.updateIsConstructive();
					newContext = updateStrategy.update((Property)Property.this, currentContext, _name, newValue);
					break;
				}
			}

			if(newContext != currentContext)
				contextIterator.update(newContext);
		}

		public void remove() { throw new UnsupportedOperationException(); }		
	}

	/** An object that knows how to update some object's property with a new value. */
	public static interface UpdateStrategy<Q,T> { 
		boolean isApplicable(Object context, String name, Object newValue);
		boolean updateIsConstructive();
		Q update(Property expression, Q context, String name, T newValue);
	}

	/** A list of update strategies worked down from beginning to end, i.e., the strategy matching first will be chosen. */
	static final UpdateStrategy UPDATE_STRATEGIES[] = new UpdateStrategy[] {
		new UpdateStrategy<ActRelationship,Act>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof ActRelationship && newValue instanceof Act && name.equals("target");
			}
			public ActRelationship update(Property expression, ActRelationship context, String name, Act newValue) {
				Act oldValue = context.getTarget();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getInboundRelationship() != null)	
						oldValue.getInboundRelationship().remove(context);
					newValue.addInboundRelationship(context);
				}
				return context;
			}
		},

		new UpdateStrategy<ActRelationship,Act>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof ActRelationship && newValue instanceof Act && name.equals("source");
			}
			public ActRelationship update(Property expression, ActRelationship context, String name, Act newValue) {
				Act oldValue = context.getSource();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getOutboundRelationship() != null)	
						oldValue.getOutboundRelationship().remove(context);
					newValue.addOutboundRelationship(context);
				}
				return context;
			}
		},

		new UpdateStrategy<Role,Entity>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof Role && newValue instanceof Entity && name.equals("player");
			}
			public Role update(Property expression, Role context, String name, Entity newValue) {
				Entity oldValue = context.getPlayer();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getPlayedRole() != null)	
						oldValue.getPlayedRole().remove(context);
					newValue.addPlayedRole(context);
				}
				return context;
			}
		},

		new UpdateStrategy<Role,Entity>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof Role && newValue instanceof Entity && name.equals("scoper");
			}
			public Role update(Property expression, Role context, String name, Entity newValue) {
				Entity oldValue = context.getScoper();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getScopedRole() != null)	
						oldValue.getScopedRole().remove(context);
					newValue.addScopedRole(context);
				}
				return context;
			}
		},

		new UpdateStrategy<Participation,Act>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof Participation && newValue instanceof Act && name.equals("act");
			}
			public Participation update(Property expression, Participation context, String name, Act newValue) {
				Act oldValue = context.getAct();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getParticipation() != null)	
						oldValue.getParticipation().remove(context);
					newValue.addParticipation(context);
				}
				return context;
			}
		},

		new UpdateStrategy<Participation,Role>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof Participation && newValue instanceof Role && name.equals("role");
			}
			public Participation update(Property expression, Participation context, String name, Role newValue) {
				Role oldValue = context.getRole();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getParticipation() != null)	
						oldValue.getParticipation().remove(context);
					newValue.addParticipation(context);
				}
				return context;
			}
		},

		new UpdateStrategy<RoleLink,Role>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof RoleLink && newValue instanceof Role && name.equals("target");
			}
			public RoleLink update(Property expression, RoleLink context, String name, Role newValue) {
				Role oldValue = context.getTarget();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getInboundLink() != null)	
						oldValue.getInboundLink().remove(context);
					newValue.addInboundLink(context);
				}
				return context;
			}
		},

		new UpdateStrategy<RoleLink,Role>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof RoleLink && newValue instanceof Role && name.equals("source");
			}
			public RoleLink update(Property expression, RoleLink context, String name, Role newValue) {
				Role oldValue = context.getSource();
				if(oldValue != newValue) {
					if(oldValue != null && oldValue.getOutboundLink() != null)	
						oldValue.getOutboundLink().remove(context);
					newValue.addOutboundLink(context);
				}
				return context;
			}
		},
		
		new UpdateStrategy<TEL,ST>() {
			public boolean updateIsConstructive() {	return true;	}
			public boolean isApplicable(Object context, String name, Object newValue) { 
				return context instanceof TEL && newValue instanceof ST;
			}
			public TEL update(Property expression, TEL context, String name, ST newValue) {
				if(name.equals("address")) {
					context = ValueFactory.getInstance().TELvalueOf(context.scheme(), newValue, context.use(), context.useablePeriod());
				} else if(name.equals("scheme")) {
				  context = ValueFactory.getInstance().TELvalueOf(context.scheme(), newValue, context.use(), context.useablePeriod());
				} else {
					throw new PropertyNotFoundException(expression, context, name + " is not scheme or address");
				}
				return context;
			}			
		},
		
		// ADD ADDITIONAL METHODS HERE AS NEEDED TO SET PARTS OF DATA VALUES
		// ENXP, ADXP, PQ are good examples where it can be useful. Probably PQ first.
			
		new UpdateStrategy<Object,Object>() {
			public boolean updateIsConstructive() {	return false;	}
			public boolean isApplicable(Object context, String name, Object newValue) { return true; }
			public Object update(Property expression, Object context, String name, Object newValue) {
				// Find the method, this is simple, it must be a setter only, and it can't be a funky property such as item or
				// part, etc.
				Class clazz = context.getClass();
				List<Method> candidates = new ArrayList<Method>();
				String searchName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
				for(Method method : clazz.getMethods()) {
					if((method.getName().equals(name) || 
							method.getName().equals(searchName)) && 
						 (method.getModifiers() & Modifier.STATIC) == 0) {
						Class parameterTypes[] = method.getParameterTypes();
						if(parameterTypes.length == 1 && (newValue == null || parameterTypes[0].isAssignableFrom(newValue.getClass())))
							candidates.add(method);
					}
				}
				
				if(candidates.size() > 1) {
					throw new UpdateException(expression, context, "ambiguous property " + name + " matching " + newValue);
				} else if(candidates.size() == 0) {
					if(context instanceof ValueFactory)
						return newValue;
					else
						throw new PropertyNotFoundException(expression, context, name + " matching " + newValue);
				} else { 
					Method method = candidates.get(0);
					try {
						method.invoke(context, newValue);
					} catch(IllegalAccessException ex) {
						throw new EvaluationException(expression, context, method.toString(), ex);
					} catch(InvocationTargetException ex) {
						throw new EvaluationException(expression, context, method.toString(), ex);
					}
				}
				return context;
			}			
		}
	};

	public static class PropertyNotFoundException extends EvaluationException {
		/* package */ PropertyNotFoundException(Expression expression, Object context, String propertyName) { super(expression, context, propertyName); }
		/* package */ PropertyNotFoundException(Expression expression, Object context, String propertyName, Throwable ex) { super(expression, context, propertyName, ex); }		
	}

	// END UPDATE STUFF
		
  /* package */ String argsToString(Iterable args) {
    if(args == null)
      return "";
    else {
      StringBuffer sb = new StringBuffer();
      boolean isFresh = true;
      sb.append('(');
      for(Object arg : args) {
				if(isFresh)
					isFresh = false;
				else
					sb.append(", ");
				if(arg != null) {
					sb.append(arg.getClass().toString());
					sb.append(':');
					sb.append(arg.toString());
				} else
					sb.append("null");
      }
      sb.append(')');
      return sb.toString();
    }
  }

  /* package */ String argsToString(Object[] args) {
    if(args == null) {
      return "";
    } else {
      final StringBuffer sb = new StringBuffer();
      boolean isFresh = true;
      sb.append('(');
      for(Object arg : args) {
				if(isFresh) {
					isFresh = false;
				} else {
					sb.append(", ");
				} if (arg != null) {
					sb.append(arg.getClass().toString());
					sb.append(':');
					sb.append(arg.toString());
				} else {
					sb.append("null");
				}
      }
      sb.append(')');
      return sb.toString();
    }
  }
}

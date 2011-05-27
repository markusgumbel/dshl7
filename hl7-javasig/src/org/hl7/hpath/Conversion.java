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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;
import java.lang.reflect.Method;

/** A Conversion expression form can be used to turn business objects from and to string representations, for example.
		But any type conversions are possible. For the parser, conversions are syntactically indistinguishable from properties.
		However, the names of conversions is statically defined in the Language, and hence, 
		every token that looks like a property but which has the name of a conversion will be interpreted as a conversiom.

		@param R - the type of the root context
		@param P - the type of the previous expression,
		@param Q - the what goes into the forward conversion,
		@param T - the type of the expression, i.e., the type of the result.

		@author Gunther Schadow
		@version $Id: Conversion.java,v 1.9 2006/09/28 18:46:56 gschadow Exp $
*/ 
public abstract class Conversion<R,P,Q,T> extends ExpressionStep<R,P,Q,T> {
  private List<Expression<R,?>> _arguments = null;
	private Q _currentSource;

  public interface Definition {
		public String name();
		public Conversion instantiate(Expression previousExpression, List<Expression> arguments);
	}

	public abstract String name();

	/** The current source, used by some conversions to copy some data from the previous value for convertBackward. */
	protected Q currentSource() {
		return _currentSource;
	}

	public abstract T convertForward(Q object, Object... args);

	public abstract P convertBackward(T object, Object... args);

	protected Conversion(Expression<R,P> previousExpression, List<Expression<R,?>> arguments, boolean doNotItemize) {
    super(previousExpression, doNotItemize);
    _arguments = arguments;
  }

	// FIXME: remove this doubtful convenience variant
	protected Conversion(Expression<R,P> previousExpression, List<Expression<R,?>> arguments) {
		this(previousExpression, arguments, false);
  }

	private static final Map<String, Definition> _DEFINITIONS = new HashMap<String, Definition>();

	public static void define(Definition definition) {
		_DEFINITIONS.put(definition.name(), definition);
	}

	public static Conversion instance(Expression previousExpression, String name, List<Expression> arguments, boolean doNotItemize) {
		Definition definition = _DEFINITIONS.get(name);
		if(definition == null)
			return null;
		else {
			Conversion result = definition.instantiate(previousExpression, arguments);
			result._doNotItemize = doNotItemize;
			return result;
		}
	}

  public String toString() {
    return "Conversion[" + _previousExpression + " " + name() + " " + _arguments + "]";
  }
  
	protected InnerIterator newIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,P> parentIterator) {
		return new InnerIterator(evaluation, parentIterator);
	}  

	static <R> Object[] evaluateArguments(Evaluation<R, ?> evaluation, List<Expression<R, ?>> arguments) {
		Object argvals[] = new Object[arguments.size()];
		for (int i = 0; i < argvals.length; i++) {
			Iterator<?> iter = evaluation.getCurrentAsChild(arguments.get(i)).iterator();
			if (iter.hasNext())
				argvals[i] = iter.next();
			else
				argvals[i] = null;
		}
		return argvals;
	}

	private static final Object EMPTY_ARGUMENTS[] = new Object[0];

  class InnerIterator extends ExpressionStepIterator<R,P,Q,T> {
    InnerIterator(Evaluation<R,?> evaluation,  EvaluationIterator<R,P> parentIterator) {
			super(evaluation, parentIterator, _doNotItemize, true);
		}

		public String toString() {
			return super.toString() + "[" + _evaluation + ", " 
				+ _parentIterator + (_parentIterator == null ? "" : "("+_parentIterator.hasOnlySingleValue()+")") +", " 
				+ _itemizerIterator + (_itemizerIterator == null ? "" : "("+ _itemizerIterator.hasOnlySingleValue()+")") +", " + hasOnlySingleValue() + "]";
		}

    protected T apply(Q source) {
			_currentSource = source;
			Object[] argvals = EMPTY_ARGUMENTS;
			if(_arguments != null && _arguments.size() > 0)
				argvals = evaluateArguments(_evaluation, _arguments);
			
      return convertForward(source, argvals);
    }

		public Type currentType() {
			for(Method method : getClass().getMethods())
				if(method.getName().equals("convertForward"))
					return method.getGenericReturnType();
			throw new Error("it is not possible to have a Conversion without convertForward method");
		}

		public void update(T newValue) { 
			Object[] argvals = EMPTY_ARGUMENTS;
			if(_arguments != null && _arguments.size() > 0)
				argvals = evaluateArguments(_evaluation, _arguments);

			if(_itemizerIterator == null)
				_parentIterator.update(convertBackward(newValue, argvals));
			else
				_itemizerIterator.update((Q)convertBackward(newValue, argvals));
		}

		public void insert(T newValue) { 
			Object[] argvals = EMPTY_ARGUMENTS;
			if(_arguments != null && _arguments.size() > 0)
				argvals = evaluateArguments(_evaluation, _arguments);			
			
			if(_itemizerIterator == null)	
				_parentIterator.insert(convertBackward(newValue, argvals));
			else
				_itemizerIterator.insert((Q)convertBackward(newValue, argvals));
		}

		/** Remove current item from parent iterator, override if that is not the correct action (e.g., if the conversion is somehow a function like a property. */
		public void remove() { 
			if(_itemizerIterator == null)
				_parentIterator.remove();
			else
				_itemizerIterator.remove();
		}
	}
}

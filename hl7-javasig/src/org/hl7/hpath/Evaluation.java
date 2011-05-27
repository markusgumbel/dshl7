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
 * Portions created by Initial Developer are Copyright (C) 2002-2006
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hpath;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.hl7.types.ValueFactory;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BIN;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.LIST;
import org.hl7.types.SET;
import org.hl7.types.ST;

/** An evaluation context for an Expression. Notably this context contains named variables. Even though an expression can have multiple evaluation steps linked in a chain (e.g., foo[...].bar[...][1], there is usually only one Evaluation context. In the future special forms such as some ... satisfies ... or for ... in ... return ... might set up child evaluations. Also, arguments are evaluated in child contexts. However, it is important to distinguish the parent context from the previous expression step in a chain of expressions.

    @author Gunther Schadow
    @version $Id: Evaluation.java,v 1.4 2006/09/28 18:46:56 gschadow Exp $
*/
public class Evaluation<R,T> implements Iterable<T> {
	private Evaluation<R,?> _parent; // parent context
	private EvaluationIterator<R,R> _sourceIterator;
	private Expression<R,T> _expression;
	private Map<String,Object> _bindings = null;
	private R _sourceObject;

	/*package*/ Evaluation(R sourceObject, Expression<R,T> expression, Evaluation<R,?> parent) {
		_sourceIterator = new SingletonIterator(sourceObject); // the root object is always simply put in a singleton iterator
		_expression = expression;
		_parent = parent;
		// bind the VALUE_FACTORY so we can make values on the fly
		bindVariable("VALUE_FACTORY",ValueFactory.getInstance());
	}
	
	/*package*/ Evaluation(R sourceObject, Expression<R,T> expression) {
		this(sourceObject, expression, null);
	}

	/** Returns a child Evaluation whose context is the current context object. */
	/*package*/ <U> Evaluation<R,U> getCurrentAsChild(Expression<R,U> expression) {
		return new Evaluation(_sourceIterator.current(), expression, this); 
	}
	
	/** Returns the value iterator for this expression evaluation. */
	public EvaluationIterator<R,T> iterator() {
		return _expression.createEvaluationIterator(this, _sourceIterator);
	}

	/** Initialize the sourceIterator for updates */
	public void initializeSourceIteratorForUpdate() {
		if(_sourceIterator.hasNext())
			_sourceIterator.next();
	}

	/** Return the current source object. */
	public void getSource() {
		_sourceIterator.current();
	}
	
  /** Retrieve the dynamic value of a variable from this or any previous dynamic contexts. */
  public Object getVariableValue(String variableName) {
		Object value = null;
		if(_bindings != null)
			value = _bindings.get(variableName);
    if(value != null)
      return value;
    else if(_parent != null)
      return _parent.getVariableValue(variableName);
    else
      throw new EvaluationException(_expression, _sourceIterator.current(), "no such variable: " + variableName);
  }

  /** Bind a value to a new variable in this context. 
			SETQ style assignments are not permitted. 
	*/
  public void bindVariable(String variableName, Object variableValue) {
  	variableName = variableName.trim();
		if(_bindings == null)
			_bindings = new HashMap<String,Object>();
    if(_bindings.put(variableName, variableValue) != null)
      throw new EvaluationException(_expression, _sourceIterator.current(), "we do not allow changing a variable as a side-effect");   
  }

	public String toString() {
		return _expression.toString() + _bindings;
	}

	/** This is called by evaluations to itemize a collections that may have been pulled from the parentIterator. Note that the name of this function is somewhat confusing. It is NOT the same as the R _sourceIterator of this Evaluation. */
  public static <R> EvaluationIterator<R,?> sourceIterator(Object sourceObject) { 
    if(sourceObject == null) { 
			// at the root, we allow one null value in order to allow expressions rooted in a constant or variable reference.
      return new SingletonIterator(sourceObject);
		} else {
			EvaluationIterator itemizer = itemizerIterator(sourceObject);
			if(itemizer != null)
				return itemizer;
			else
				return new SingletonIterator(sourceObject);
		}
  }

	/** Returns an iterator if the source is a collection to be itemized and null if that object does not need to be itemized. */
	public static <R,P,Q> EvaluationIterator<R,Q> itemizerIterator(P sourceObject) {
		if(sourceObject instanceof BIN)
			return null;
		else if(sourceObject instanceof Object[])
			return new ItemizerIterator((Object[]) sourceObject);
		else if(sourceObject instanceof Iterable)
			return new ItemizerIterator((Iterable) sourceObject);
		else 
			return null;
	}

	/** An interpretation of an expression value as a boolean.
			The rules are: 
			<ul>
			<li>null is false;</li>
			<li>boolean, BL, are true if true, false otherwise;</li>
			<li>strings are true if length greater than zero;</li>
			<li>integers are true if not 0 or null;</li>
 			<li>collections are true if they are not empty; -- collections might have been unrolled</li>
			<li>everything else is true if it is not null</li>
			</ul>
	*/
  public boolean effectiveBooleanValue() {
		return effectiveBooleanValue(iterator());
	}

	public static boolean effectiveBooleanValue(Iterator<?> testIterator) {
    while(testIterator.hasNext()) {
      Object value = testIterator.next();
			if(value == null)
				return false;
      else if(value instanceof Boolean)
				return ((Boolean)value).booleanValue();
      else if(value instanceof BL)
				return ((BL)value).isTrue();
      else if(value instanceof String)
				return ((String)value).length() > 0;
      else if(value instanceof ST)
				return ((ST)value).isEmpty().isFalse();
      else if(value instanceof Integer)
				return ((Integer)value).intValue() != 0;
      else if(value instanceof Long)
				return ((Long)value).longValue() != 0;
      else if(value instanceof INT)
				return ((INT)value).isZero().isFalse();
      else if(value instanceof Collection)
				return !((Collection)value).isEmpty();
      else if(value instanceof SET)
				return ((SET)value).isEmpty().isFalse();
      else if(value instanceof LIST)
				return ((LIST)value).isEmpty().isFalse();
      else if(value instanceof BAG)
				return ((BAG)value).isEmpty().isFalse();
      else if(value instanceof ANY)
				return ((ANY)value).nonNull().isTrue();
      else
				return true;
    }
    return false;
  }
}

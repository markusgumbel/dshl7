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


/** A Filter Expression which chooses only a single item by index. */
/*package protected*/ class SingleIndexFilter<R,P,Q,T> extends Filter<R,P,Q,T>{
  private int _index;
	
  /*package*/ SingleIndexFilter(Expression<R,P> previousExpression, int index, boolean doNotItemize) {
		super(previousExpression, doNotItemize);
    _index = index;
  }

  public String toString() {
    return "SingleIndexFilter[" + _previousExpression + " " + (_doNotItemize ? "@" : "" ) + _index + "]";
  }


	protected InnerIterator newIterator(Evaluation<R,?> evaluation, EvaluationIterator<R,P> parentIterator) {
		return new InnerIterator(evaluation, parentIterator);
	}  
  
  class InnerIterator extends FilterIterator<R,P,Q,T> {
    InnerIterator(Evaluation evaluation,  EvaluationIterator parentIterator) {
			super(evaluation, parentIterator, _doNotItemize);
    }

		private int _counter = 0;
		
    protected boolean test(Q element) {
			return ++_counter == _index;
		}

		public boolean hasOnlySingleValue() {	return true;	}
  }
}

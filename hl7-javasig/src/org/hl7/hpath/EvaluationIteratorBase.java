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

import java.lang.reflect.Type;

/** Common functions which all EvaluationIterators use. It is 
    important to use this, and not to extend hasNext and next
    but implement only seek(). It is important that the iterator
    stay in a defined state after next and only be changed again
    after hasNext.
*/
/*package*/ abstract class EvaluationIteratorBase<R,T> implements EvaluationIterator<R,T> {
	private boolean _seekIsCurrent = false;
	private T _nextElement;
	private T _current;
	
	public boolean hasNext() {
		if(!_seekIsCurrent) {
			_nextElement = seek();
			_seekIsCurrent = true;
		}
		return _nextElement != null;
	}
		
	public T next() {
		if(!_seekIsCurrent) {
			_nextElement = seek();
			_seekIsCurrent = true;
		}
		if(_nextElement != null) {
			_seekIsCurrent = false;
			return _current = _nextElement;
		}
		else
			throw new java.util.NoSuchElementException();
	}

	protected abstract T seek();

	public T current() { return _current; }
	
	public Type currentType() {
		if(_current != null)
			return _current.getClass();
		else 
			return null;
	}
}

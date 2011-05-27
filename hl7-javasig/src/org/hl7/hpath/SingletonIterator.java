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

import java.util.NoSuchElementException;
import java.lang.reflect.Type;

/** A pseudo-iterator for a single object. */
public final class SingletonIterator<S> implements EvaluationIterator<S,S> {
	private final S _source; 
	private boolean _hasNext;

  public SingletonIterator(S source) { 
		_source = source;
		_hasNext = source != null;
	}

  public boolean hasNext() { return _hasNext; }
  
  public S next() { 
    if(_hasNext) {
			_hasNext = false;
      return _source;
    } else {
      throw new NoSuchElementException();
    }
  }

	public S current() { return _hasNext ? null : _source; }
	public Type currentType() {
		if(_source != null)
			return _source.getClass();
		else 
			return null;
	}
	public boolean hasOnlySingleValue() {	return true; }

	private S _updatedSource = null;

	public boolean updateIsConstructive() { return true; }
	public void beginUpdate() { /* nothing to begin */ }	
	public void update(final S newValue) {
		if(_hasNext) {
			throw new IllegalStateException("no element has been selected for update");
		} else {
			_updatedSource = newValue;
		}
	}
	public void insert(final S newValue) { throw new UnsupportedOperationException(); }
	public void remove() { throw new UnsupportedOperationException(); }		
	public S finalizeUpdates() { 
		if(_updatedSource == null) {
		    return _source;
		} else {
		    return _updatedSource;
		}
	}
}

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
package org.hl7.types.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.collection.PersistentCollection;
import org.hl7.hibernate.ClonableCollection;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.INT;
import org.hl7.types.LIST;

public class LISTjuListAdapter<T extends ANY> extends ANYimpl implements
								LIST<T>, ClonableCollection<LIST<T>> {
  private final List<T> _list;
    
  public LISTjuListAdapter(final Collection<T> data) {
    super(null);
    this._list = new ArrayList<T>(data);
  }
    
  protected LISTjuListAdapter(final List<T> data, final boolean makeCopy) {
    super(null);
    if (data == null) {
      throw new IllegalArgumentException();
    } else if (makeCopy) {
      this._list = new ArrayList<T>(data);
    } else {
      this._list = data;
    }
  }
    
  public static <T extends ANY> LIST<T> valueOf(final Collection<T> data) {
    if (data == null) {
      return LISTnull.NI;
    } else {
      return new LISTjuListAdapter<T>(data);
    }
  }
    
  public T head() {
    return this._list.get(0);
  }
    
  /**
   * The tail function is dangerous as it wastes objects if used for tail
   * recursion. We need an interator instead. Iterators are defined for
   * DISCRETE LISTS.
   */
  public LIST<T> tail() {
    return new LISTjuListAdapter<T>(this._list
				    .subList(1, this._list.size()));
  }
    
  public BL isEmpty() {
    return BLimpl.valueOf(this._list.isEmpty());
  }
    
  public BL nonEmpty() {
    return BLimpl.valueOf(!this._list.isEmpty());
  }
    
  public INT length() {
    return INTlongAdapter.valueOf(this._list.size());
  }
    
  public List<T> toList() {
    return Collections.unmodifiableList(this._list);
  }
    
  public Iterator<T> iterator() {
    return this._list.listIterator();
  }
    
  @Override
  public BL equal(final ANY that) {
    if (that instanceof LIST) {
      final Iterator<T> thissi = this.iterator();
      final Iterator<?> thatti = ((LIST<?>) that).iterator();
      while (thissi.hasNext() && thatti.hasNext()) {
	final T thisNext = thissi.next();
	final Object thatNext = thatti.next();
	if (thatNext instanceof ANY) {
	  if (thisNext.equal((ANY) thatNext).isFalse()) {
	    return BLimpl.FALSE;
	  }
	} else {
	  throw new IllegalStateException(LIST.class.getSimpleName()
					  + " element is not an instance of "
					  + ANY.class.getSimpleName());
	}
      }
      if (thissi.hasNext() || thatti.hasNext()) {
	// mismatched element counts
	return BLimpl.FALSE;
      } else {
	return BLimpl.TRUE;
      }
    } else {
      return BLimpl.FALSE;
    }
  }
    
  /**
   * For Hibernate, use with caution!
   * 
   * @return a LIST wrapping the supplied List without checking or cloning
   * @deprecated no one but Hibernate should use this!
   */
  @Deprecated
  public static <T extends ANY> LIST<T> _hibernateWrap(final List<T> list) {
    return new LISTjuListAdapter<T>(list, false);
  }
    
  /**
   * For Hibernate, use with caution!
   * 
   * @return the internal list without cloning.
   * @deprecated no one but Hibernate should use this!
   */
  @Deprecated
  public List<T> _hibernateUnwrap() {
    return _list;
  }
    
  public LIST<T> cloneHibernateCollectionIfNecessary() {
    if (this._list instanceof PersistentCollection) {
      return new LISTjuListAdapter(this._list);
    } else {
      return this;
    }
  }
}

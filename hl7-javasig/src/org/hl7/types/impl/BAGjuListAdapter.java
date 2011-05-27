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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.collection.PersistentCollection;
import org.hl7.hibernate.ClonableCollection;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BL;
import org.hl7.types.INT;

/**
 * 
 * @deprecated use the new {@link BAGjuCollectionAdapter} class instead as it is more general.
 *
 * @param <T>
 */
@Deprecated
public class BAGjuListAdapter<T extends ANY> extends ANYimpl implements BAG<T>, ClonableCollection<BAG<T>> {
  private final List<T> _list;

  public BAGjuListAdapter(final Collection<T> data) {
    super(null);
    this._list = new ArrayList<T>(data);
  }

  public static <T extends ANY> BAG<T> valueOf(final Collection<T> data) {
    if (data == null) {
      return BAGnull.NI;
    } else {
      return new BAGjuListAdapter<T>(data);
    }
  }

  protected BAGjuListAdapter(final List<T> data, final boolean makeCopy) {
    super(null);
    if (data == null) {
      throw new IllegalArgumentException();
    } else if (makeCopy) {
      this._list = new ArrayList<T>(data);
    } else {
      this._list = data;
    }
  }

  public BL isEmpty() {
    return BLimpl.valueOf(this._list.isEmpty());
  }

  @Deprecated
  public BL nonEmpty() {
    return notEmpty();
  }

  public BL notEmpty() {
    return BLimpl.valueOf(!this._list.isEmpty());
  }
	
  public INT size() {
    return INTlongAdapter.valueOf(this._list.size());
  }

  public INT contains(final T kind) {
    int count = 0;
    for(final T item : _list) {
      if(item.equal(kind).isTrue()) {
	count++;
      }
    }      
    return INTlongAdapter.valueOf(count);
  }

  public BAG<T> plus(final BAG<T> y) {
    final List<T> data = new ArrayList<T>(size().intValue() +
					  y.size().intValue());
    for (final T item : this) {
      data.add(item);
    }
    for (final T item : y) {
      data.add(item);
    }
    return new BAGjuListAdapter<T>(data, false);
  }

  public BAG<T> minus(final BAG<T> y) {
    throw new UnsupportedOperationException();
  }

  public List<T> toList() {
    return Collections.unmodifiableList(this._list);
  }

  public Iterator<T> iterator() {
    return this._list.listIterator();
  }

  @Override
  public BL equal(final ANY that) {
    if(this == that) {
      return BLimpl.TRUE;
    } else if (that instanceof BAG) {
      return equal((BAG<T>)that);
    } else {
      return BLimpl.FALSE;
    }
  }

  public BL equal(final BAG<T> that) {
    final Set<T> checkedSet = new HashSet<T>();
    for(final T item : _list) {
      if(!checkedSet.contains(item)) {
	final INT itemCountA = this.contains(item);
	final INT itemCountB = that.contains(item);
	final BL isEqual = itemCountA.equal(itemCountB);
	if(!isEqual.isTrue()) {
	  return isEqual;
	}
	else 
	  checkedSet.add(item);
      }
    }
    for(final T item : that) {
      if(!checkedSet.contains(item)) {
	return BLimpl.FALSE;
      }
    }
    return BLimpl.TRUE;
  }

  /**
   * For Hibernate, use with caution!
   * 
   * @return a BAG wrapping the supplied bag without checking or cloning
   * @deprecated noone but Hibernate should use this!
   */
  @Deprecated
  public static <T extends ANY> BAG<T> _hibernateWrap(final List<T> list) {
    return new BAGjuListAdapter<T>(list, false);
  }

  /**
   * For Hibernate, use with caution!
   * 
   * @return the internal list without cloning.
   * @deprecated noone but Hibernate should use this!
   */
  @Deprecated
  public List<T> _hibernateUnwrap() {
    return _list;
  }

  public BAG<T> cloneHibernateCollectionIfNecessary() {
    if (this._list instanceof PersistentCollection) {
      return new BAGjuListAdapter<T>(this._list);
    } else {
      return this;
    }
  }
}

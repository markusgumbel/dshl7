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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.collection.PersistentCollection;
import org.hl7.hibernate.ClonableCollection;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.Criterion;
import org.hl7.types.DSET;
import org.hl7.types.INT;
import org.hl7.types.SET;
import org.hl7.types.TS;

/*
 * A few caveats:
 * 
 * The java.util.Set is NOT the most general kind of Set we need to consider, since java.util.Set is only for
 * enumerated sets, not continuous ones, such as intervals.
 * 
 * Another issue is that java.util.Set is inherently not immutable and this will cause us some grief.
 * 
 * GS: for now we just copy the elements so that we don't care about the argument we were given in the static factory
 * method.
 */
/**
 * An adapter for {@link java.util.Set} to {@link SET}.
 * 
 * @author Gunther Schadow
 */
public class SETjuSetAdapter<T extends ANY> extends ANYimpl implements DSET<T>, ClonableCollection<SET<T>> {
  private Set<T> _set;

  protected SETjuSetAdapter() {
    super(null);
  }

  protected SETjuSetAdapter(final Set<T> data, final boolean makeCopy) {
    super(null);
    if (data == null) {
      throw new IllegalArgumentException();
    } else if (makeCopy) {
      this._set = new LinkedHashSet<T>(data);
    } else {
      this._set = data;
    }
  }

  public SETjuSetAdapter(final Collection<T> data) {
    super(null);
    if (data == null) {
      throw new IllegalArgumentException();
    } else {
      this._set = new LinkedHashSet<T>(data);
      if (this.any() instanceof TS) {
	new Throwable("should never create a DSET<TS>").printStackTrace();
      }
    }
  }

  protected SETjuSetAdapter(final SETjuSetAdapter<T> data) {
    super(null);
    if (data == null) {
      throw new IllegalArgumentException();
    } else {
      this._set = data.getClonedSet();
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends ANY> DSET<T> valueOf(final Collection<T> data) {
    if (data == null) {
      return (DSET<T>) DSETnull.NI;
    } else {
      return new SETjuSetAdapter<T>(data);
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends ANY> DSET<T> valueOf(final SETjuSetAdapter<T> data) {
    if (data == null) {
      return (DSET<T>) DSETnull.NI;
    } else {
      return new SETjuSetAdapter<T>(data);
    }
  }

  /** SET interface */
  /**
   * @param element
   *          the elements to be compared against this SET.
   * @return BL true if this SET contains the parameter element.
   */
  public BL contains(final T element) {
    if (this._set.contains(element)) {
      return BLimpl.TRUE;
    } else {
      return BLimpl.FALSE;
    }
  }

  /**
   * @return BL true if this SET contains no elements.
   */
  public BL isEmpty() {
    if (this._set.isEmpty()) {
      return BLimpl.TRUE;
    } else {
      return BLimpl.FALSE;
    }
  }

  /**
   * @return BL true if this SET contains at least one element.
   */
  public BL nonEmpty() {
    if (this._set.isEmpty()) {
      return BLimpl.FALSE;
    } else {
      return BLimpl.TRUE;
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public BL equal(final ANY that) {
    if (this == that) {
      return BLimpl.TRUE;
    } else if (that instanceof SET) {
      return equal((SET<T>) that);
    } else {
      return BLimpl.FALSE;
    }
  }

  /**
   * @param that
   *          should be a SET that can be compared to this SET for equality.
   * @return BL true if the parameter is the same as this SET or contains the same elements and this SET.
   */
  public BL equal(final SET<T> that) {
    if (this == that) {
      return BLimpl.TRUE;
    }
	    
    // if that is an enumerated set, we can compare element by element, but it's costly!
    // instead of comparing elements pairwise which would be an O(n2) complexity, we
    // can use the equivalence:
    // A.equal(B) <=> A.contains(B).and(B.contains(A))
    // which would be only n * whatever the complexity is to find an element in the set,
    // which is O(n2) in the worst case but usually would be O(n*log(n)) or something
    // in that range.
    return this.contains(that).and(that.contains(this));
    /*
     * FIXME: we might generalize this a little bit, i.e., ask for whether this is a juCollection-based implementation
     * (by means of a simple interface ...)
     * 
     * NOTE: we cannot up-cast to the specific DSET<T> at all, so, here we rely on the fact that it isn't really
     * important for the containsAll operation.
     */
  }
	
  /**
   * @param subset
   *          SET that will compared to this SET.
   * @return BL true if the elements in the parameter SET are contained in this SET.
   */
  public BL contains(final SET<T> subset) {
    // TODO: this was a stupid implementation, let's make it simpler and faster
    if (this == subset || subset.isEmpty().isTrue()) {
      return BLimpl.TRUE;
    } else if (subset instanceof DSET) {
      for (final T element : (DSET<T>) subset) {
	if (!this._set.contains(element)) {
	  return BLimpl.FALSE;
	}
      }
      return BLimpl.TRUE;
    } else {
      throw new UnsupportedOperationException("subset was: " + subset.getClass());
    }
  }

  /**
   * @return the number of elements in this SET.
   */
  public INT cardinality() {
    return INTlongAdapter.valueOf(this._set.size());
  }

  /**
   * @param otherset
   *          SET that defines the elements that will be added to this SET.
   * @return a new SET that contains the elements that are both in this SET and in the parameter SET.
   */
  public SET<T> union(final SET<T> otherset) {
    final Set<T> resultSet = getClonedSet();
    if (otherset instanceof SETjuSetAdapter) {
      resultSet.addAll(((SETjuSetAdapter<T>) otherset)._set);
    }
    if (otherset instanceof SETSingleton) {
      resultSet.add(otherset.any());
    }
    return new SETjuSetAdapter<T>(resultSet, false);
  }

  /**
   * @param element
   *          SET defines which element of this SET that will not be in the returned SET.
   * @return a new SET that does not contain the parameter element.
   */
  public SET<T> except(final T element) {
    final Set<T> resultSet = getClonedSet();
    resultSet.remove(element);
    if (resultSet.size() == 0) {
      return EmptySet.VALUE;
    } else {
      return new SETjuSetAdapter<T>(resultSet, false);
    }
  }

  /**
   * @param otherset
   *          SET that defines which elements will not be in the returned SET.
   * @return a new SET that contains the elements that are in this SET but not in the parameter SET.
   */
  public SET<T> except(final SET<T> otherset) {
    final Set<T> resultSet = getClonedSet();
    if (otherset instanceof SETjuSetAdapter) {
      resultSet.removeAll(((SETjuSetAdapter<T>) otherset)._set);
    }
    if (otherset instanceof SETSingleton) {
      resultSet.remove(otherset.any());
    }
    if (resultSet.size() == 0) {
      return EmptySet.VALUE;
    } else {
      return new SETjuSetAdapter<T>(resultSet, false);
    }
  }

  /**
   * @param otherset
   *          SET that defines which elements the returned SET will retain.
   * @return a new SET that contains the elements that are in both this SET and the parameter SET.
   */
  public SET<T> intersection(final SET<T> otherset) {
    if (otherset instanceof SETSingleton) {
      return otherset.intersection(this);
    }
    // result is all of this
    final Set<T> resultSet = getClonedSet();
    // except those that aren't contained in the other set for sure
    for (final T element : this) {
      if (!otherset.contains(element).isTrue()) {
	resultSet.remove(element);
      }
    }
    if (resultSet.size() == 0) {
      return EmptySet.VALUE;
    } else {
      return new SETjuSetAdapter<T>(resultSet, false);
    }
  }

  /**
   * @return a cloned internal representation of SET, which is of type java.util.Set.
   */
  protected Set<T> getClonedSet() {
    final Set<T> returnSet = new LinkedHashSet<T>(this._set);
    return returnSet;
  }

  public Iterator<T> iterator() {
    return this._set.iterator();
  }

  /**
   * For Hibernate, use with caution!
   * 
   * @return a SET wrapping the supplied Set without checking or cloning
   * @deprecated noone but Hibernate should use this!
   */
  @Deprecated
  public static <T extends ANY> SET<T> _hibernateWrap(final Set<T> set) {
    return new SETjuSetAdapter<T>(set, false);
  }

  /**
   * For Hibernate, use with caution!
   * 
   * @return the internal set without cloning.
   * @deprecated noone but Hibernate should use this!
   */
  @Deprecated
  public Set<T> _hibernateUnwrap() {
    return _set;
  }

  public SET<T> select(final Criterion c) {
    // FIXME: this is bad, we shouldn't create sets, but some iterator that can be pipelined
    final Set<T> returnSet = new LinkedHashSet<T>();
    for (final T element : this) {
      if (c.test(element)) {
	returnSet.add(element);
      }
    }
    return SETjuSetAdapter.valueOf(returnSet);
  }

  public T any() {
    final Iterator<T> iterator = _set.iterator();
    if (iterator.hasNext()) {
      return iterator.next();
    } else {
      return (T) ANYnull.NA; // XXX: This is a common issue in that this will always throw a ClassCastEx
    }
  }

  public Set<T> toSet() {
    return Collections.unmodifiableSet(this._set);
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (final T item : this) {
      result = (17 * result) + (item.hashCode());
    }
    return result;
  }

  public SET<T> cloneHibernateCollectionIfNecessary() {
    if (this._set instanceof PersistentCollection) {
      return new SETjuSetAdapter<T>(this._set);
    } else {
      return this;
    }
  }
}

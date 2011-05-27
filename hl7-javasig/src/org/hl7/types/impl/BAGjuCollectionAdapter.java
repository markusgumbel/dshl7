package org.hl7.types.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.collection.PersistentCollection;
import org.hl7.hibernate.ClonableCollection;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BL;
import org.hl7.types.INT;

/**
 * Provides methods for converting back and forth between <code>BAG</code> and
 * <code>java.util.Collection</code>. This class replaces the old
 * {@link BAGjuListAdapter} class, which was insufficiently general.
 * 
 * @author nradov
 * 
 * @param <T>
 *                contained data type
 */
public class BAGjuCollectionAdapter<T extends ANY> extends ANYimpl implements BAG<T>, ClonableCollection<BAG<T>> {
  private final Collection<T> contents;
    
  public BAGjuCollectionAdapter(final Collection<T> data) {
    super(null);
    contents = new ArrayList<T>(data);
  }
    
  public static <T extends ANY> BAG<T> valueOf(final Collection<T> data) {
    if (data == null) {
      return BAGnull.NI;
    } else {
      return new BAGjuCollectionAdapter<T>(data);
    }
  }
    
  protected BAGjuCollectionAdapter(final Collection<T> data,
				   final boolean makeCopy) {
    super(null);
    if (data == null) {
      throw new IllegalArgumentException();
    } else if (makeCopy) {
      contents = new ArrayList<T>(data);
    } else {
      contents = data;
    }
  }
    
  public BL isEmpty() {
    return BLimpl.valueOf(contents.isEmpty());
  }
    
  @Deprecated
  public BL nonEmpty() {
    return notEmpty();
  }
    
  public BL notEmpty() {
    return BLimpl.valueOf(!contents.isEmpty());
  }
    
  public INT size() {
    return INTlongAdapter.valueOf(contents.size());
  }
    
  public INT contains(final T kind) {
    int count = 0;
    for (final T item : contents) {
      if (item.equal(kind).isTrue()) {
	count++;
      }
    }
    return INTlongAdapter.valueOf(count);
  }
    
  public BAG<T> plus(final BAG<T> y) {
    final Collection<T> data =
      new ArrayList<T>(size().intValue() + y.size().intValue());
    for (final T item : this) {
      data.add(item);
    }
    for (final T item : y) {
      data.add(item);
    }
    return new BAGjuCollectionAdapter<T>(data, false);
  }
    
  public BAG<T> minus(final BAG<T> y) {
    throw new UnsupportedOperationException();
  }
    
  public Collection<T> toCollection() {
    return Collections.unmodifiableCollection(contents);
  }
    
  public Iterator<T> iterator() {
    return contents.iterator();
  }
    
  @Override
  public BL equal(final ANY that) {
    if (this == that) {
      return BLimpl.TRUE;
    } else if (that instanceof BAG) {
      return equal((BAG<T>)that);
    } else {
      return BLimpl.FALSE;
    }
  }
    
  public BL equal(final BAG<T> that) {
    final Set<T> checkedSet = new HashSet<T>();
    for (final T item : contents) {
      if (!checkedSet.contains(item)) {
	final INT itemCountA = this.contains(item);
	final INT itemCountB = that.contains(item);
	final BL isEqual = itemCountA.equal(itemCountB);
	if (!isEqual.isTrue()) {
	  return isEqual;
	}
	else 
	  checkedSet.add(item);
      }
    }
    for (final T item : that) {
      if (!checkedSet.contains(item)) {
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
  public static <T extends ANY> BAG<T> _hibernateWrap(final Collection<T> list) {
    return new BAGjuCollectionAdapter<T>(list, false);
  }
    
  /**
   * For Hibernate, use with caution!
   * 
   * @return the internal list without cloning.
   * @deprecated noone but Hibernate should use this!
   */
  @Deprecated
  public Collection<T> _hibernateUnwrap() {
    return contents;
  }
    
  public BAG<T> cloneHibernateCollectionIfNecessary() {
    if (contents instanceof PersistentCollection) {
      return new BAGjuCollectionAdapter<T>(contents);
    } else {
      return this;
    }
  }
}

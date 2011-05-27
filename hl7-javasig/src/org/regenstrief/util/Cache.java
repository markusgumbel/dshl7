/*  Cache - A simple in-memory object cache
 *
 *  Copyright (C) 2002, Regenstrief Institute. All rights reserved.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Written and maintained by Gunther Schadow <gschadow@regenstrief.org>
 *  Regenstrief Institute for Health Care
 *  1050 Wishard Blvd., Indianapolis, IN 46202, USA.
 *
 * $Id: Cache.java 4607 2006-10-18 19:43:42Z crosenthal $
 */
package org.regenstrief.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** A Map like class that implements an object cache with least-recently-used 
 * (LRU) eviction strategy. Specify the minimum capacity as the number of objects 
 * in the constructor argument and use get and put operations as you would with a 
 * map. Objects beyond the capacity are evicted when the Java garbage collector
 * claims that memory. They are not evicted if the memory is available, so the
 * cache can grow very large, but memory would never be exhausted.
 *
 * @author Gunther Schadow
 */
public class Cache<K,V> implements Map<K, V>
{
  private Map<K,Entry> _cacheMap = new HashMap<K,Entry>();
  private ReferenceQueue _refQueue = new ReferenceQueue();
  private Entry _mostRecentlyUsed = null;
  private Entry _leastRecentlyUsed = null;
  private int _mostRecentlyUsedCount = 0;
  private int _mostRecentlyUsedLimit = 8192;
	
  // Performance statistics
  private int _accessed = 0;
  private int _hit = 0;
  private int _evicted = 0;
  private int _added = 0;
  private int _maxAccessed = 0;
	
  /** Get number of calls to get(). */
  public int getAccessCount() {
    return _accessed;
  }
	
  /** Get number of objects evicted from cache. */
  public int getEvictionCount() {
    return _evicted;
  }

  /** Get number of objects added to cache. If addition count and evict count are 
      both high, the cache has an inefficient retention capacity. */
  public int getAdditionCount() {
    return _added;
  }

  /** Get number of get calls that were successful. */
  public int getHitCount() {
    return _hit;
  }

  /** Get number of accesses to the most frequently accessed object. */
  public int getMaxAccessCount() {
    return _maxAccessed;
  }

  /** Get the current size of the cache. */
  public int getSize() {
    return _cacheMap.size();
  }

  private void cleanup() {
    while(true) {
      Entry deadRef = (Entry)_refQueue.poll();
      if(deadRef == null)
				return;
      else {
				remove(deadRef._key);
				/*
					System.err.print("CCE|");
					System.err.print(_accessed);
					System.err.print("|");
					System.err.print(_hit);
					System.err.print("|");
					System.err.print(_maxAccessed);
					System.err.print("|");
					System.err.print(deadRef._accessed);
					System.err.print("|");
					System.err.print(deadRef._moreRecentlyUsed != null);
					System.err.print("|");
					System.err.print(deadRef._lessRecentlyUsed != null);
					System.err.print("|");
					System.err.print(deadRef._hardValue != null);
					System.err.print("|");
					System.err.print(deadRef._key);
					System.err.println("|");
				*/
      }
    }
  }
	
  /** The cache entry container. Adds some statistics to each cache entry. */
  private class Entry extends SoftReference<V> implements Map.Entry<K,V> {
    private K _key;
    int _accessed = 0;
    Entry _moreRecentlyUsed = null;
    Entry _lessRecentlyUsed = null;
    private V _hardValue = null;
    
    private Entry(K key, V value) {
      super(value, _refQueue);

      _key = key;
      _hardValue = value;

      _lessRecentlyUsed = _mostRecentlyUsed;
      if(_lessRecentlyUsed != null)
				_lessRecentlyUsed._moreRecentlyUsed = this;
      _mostRecentlyUsed = this;
      if(_leastRecentlyUsed == null)
				_leastRecentlyUsed = this;
			
      if(_mostRecentlyUsedCount < _mostRecentlyUsedLimit)
				_mostRecentlyUsedCount++;
      else { // clip off least recently used
				Entry old = _leastRecentlyUsed;
				_leastRecentlyUsed = old._moreRecentlyUsed;
				_leastRecentlyUsed._lessRecentlyUsed = null;
				old._moreRecentlyUsed = null;
				old._lessRecentlyUsed = null;
				old._hardValue = null;
      }
    }

    public V get() {
      V _hardValue = super.get();
      _maxAccessed = Math.max(++_accessed, _maxAccessed);

      // put first in the MRU chain
      if(_mostRecentlyUsed != this) {
				if(_moreRecentlyUsed == null && _lessRecentlyUsed == null) {
					if(_mostRecentlyUsedCount < _mostRecentlyUsedLimit)
						_mostRecentlyUsedCount++;
					else { // clip off least recently used
						Entry old = _leastRecentlyUsed;
						_leastRecentlyUsed = old._moreRecentlyUsed;
						_leastRecentlyUsed._lessRecentlyUsed = null;
						old._moreRecentlyUsed = null;
						old._lessRecentlyUsed = null;
						old._hardValue = null;
					}	
				} else {
					if(_moreRecentlyUsed != null) {
						_moreRecentlyUsed._lessRecentlyUsed = _lessRecentlyUsed;
						if(_leastRecentlyUsed == this)
							_leastRecentlyUsed = _moreRecentlyUsed;
					}
					if(_lessRecentlyUsed != null)
						_lessRecentlyUsed._moreRecentlyUsed = _moreRecentlyUsed;
				}
				
				_moreRecentlyUsed = null;
				_lessRecentlyUsed = _mostRecentlyUsed;
				if(_lessRecentlyUsed != null)
					_lessRecentlyUsed._moreRecentlyUsed = this;
				_mostRecentlyUsed = this;
      }
      return _hardValue;
    }

    /*package*/ V peek() {
			return super.get();
		}

		// Map.Entry interface

		/** Compares the specified object with this entry for equality. */
		public boolean equals(Object o) {
			if(o instanceof Map.Entry) {
				Entry that = (Entry)o;
				return (this.getKey()==null ?	that.getKey()==null : this.getKey().equals(that.getKey()))  &&
					(this.getValue()==null ? that.getValue()==null : this.getValue().equals(that.getValue()));
			} else 
				return false;
		}

		/** Returns the key corresponding to this entry. */
		public K getKey() { return _key; }
		/** Returns the value corresponding to this entry. */
		public V getValue() { return get(); }

		/** Returns the hash code value for this map entry. */
		public int hashCode() {
			return (this.getKey()==null   ? 0 : this.getKey().hashCode()) ^	(this.getValue()==null ? 0 : this.getValue().hashCode());
		}
		/** Replaces the value corresponding to this entry with the specified value (optional operation). */
		public V setValue(V value) {
			throw new UnsupportedOperationException();
		}
  }
	
  /** Create new cache with minimum retention capacity 8192. */
  public Cache() {
    this(8192);
  }

  /** Create new cache with the given limit of retention capacity. */
  public Cache(int mruLimit) {
    _mostRecentlyUsedLimit = mruLimit;
  }

  /** Put an object into the cache map using the key. */
  public V put(K key, V value) {
    V oldValue = _cacheMap.put(key, new Entry(key, value)).peek();
    _added++;
    cleanup();
		return oldValue;
  }

  /** Get the object from the cache map using the key.
      
      @return the object or null if the object was not in the cache.
  */
  public V get(Object key) {
    cleanup();
    Entry entry = _cacheMap.get(key);
    _accessed++;
    if(entry != null) {
      _hit++;
      return entry.get();
    } else
      return null;
  }
	
  /** Evict the object filed under the given key from the cache. */
  public V remove(Object key) {
    _evicted++;
    Entry e = _cacheMap.remove(key);
		V oldValue = e.peek();
    if(e._moreRecentlyUsed != null) {
      if(e._moreRecentlyUsed._lessRecentlyUsed != e)
				throw new AssertionError();      
    }
    if(e._lessRecentlyUsed != null) {
      if(e._lessRecentlyUsed._moreRecentlyUsed != e)
				throw new AssertionError();      
    }
    if(e._moreRecentlyUsed != null)
      e._moreRecentlyUsed._lessRecentlyUsed = e._lessRecentlyUsed;
    if(e._lessRecentlyUsed != null)
      e._lessRecentlyUsed._moreRecentlyUsed = e._moreRecentlyUsed;
    if(e == _mostRecentlyUsed)
      _mostRecentlyUsed = e._lessRecentlyUsed;
    if(e == _leastRecentlyUsed)
      _leastRecentlyUsed = e._moreRecentlyUsed;
    if(e._moreRecentlyUsed != null
       || e._lessRecentlyUsed != null)
      _mostRecentlyUsedCount--;
    e._moreRecentlyUsed = null;
    e._lessRecentlyUsed = null;
		return oldValue;
  }
	
  /** Dump the entire cache to standard output. This is for debugging only. */
  public void dump() {
    System.out.print("CCH|");
    System.out.print(_accessed);
    System.out.print("|");
    System.out.print(_hit);
    System.out.print("|");
    System.out.print(_maxAccessed);
    System.out.println("|");
    int rank = 0;
    for(Entry e = _mostRecentlyUsed; e != null; e = e._lessRecentlyUsed) {
      System.out.print("MRU|");
      System.out.print(rank++);
      System.out.print("|");
      System.out.print(e._accessed);
      System.out.print("|");
      System.out.print(e._key);
      System.out.println("|");
    }
    rank--;
    for(Entry e = _leastRecentlyUsed; e != null; e = e._moreRecentlyUsed) {
      System.out.print("LRU|");
      System.out.print(rank--);
      System.out.print("|");
      System.out.print(e._accessed);
      System.out.print("|");
      System.out.print(e._key);
      System.out.println("|");
    }
  }

	// Map interface

	/** Clears the cache and the statistics. */
	public void clear() {	
		_cacheMap.clear();
		_mostRecentlyUsed = null;
		_leastRecentlyUsed = null;
		_mostRecentlyUsedCount = 0;
		_accessed = 0;
		_hit = 0;
		_evicted = 0;
		_added = 0;
		_maxAccessed = 0;
	}

	/** Returns true if this map contains a mapping for the specified key. */
	public boolean containsKey(Object key) { return _cacheMap.containsKey(key);	}          
	/** Returns true if this map maps one or more keys to the specified value. */
	public boolean containsValue(Object value) { throw new UnsupportedOperationException(); }        
  /** Returns a set view of the mappings contained in this map. */
	public Set<Map.Entry<K,V>> entrySet() { throw new UnsupportedOperationException(); }
	/** Returns true if this map contains no key-value mappings. */
	public boolean isEmpty() { return _cacheMap.isEmpty(); }
	/** Returns a set view of the keys contained in this map. */
	public Set<K> keySet() { throw new UnsupportedOperationException(); }
	/** Copies all of the mappings from the specified map to this map. */
	public void 	putAll(Map<? extends K,? extends V> t) {
		for(Map.Entry e : t.entrySet())
			put((K)e.getKey(), (V)e.getValue());
	}
	/** Returns the number of key-value mappings in this map. */
	public int size() { return _cacheMap.size(); }
	/** Returns a collection view of the values contained in this map. */
	public Collection<V> values() { throw new UnsupportedOperationException(); }

}


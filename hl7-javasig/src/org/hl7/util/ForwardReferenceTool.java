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
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class deals with forward references that can happen in any symbol-table
 * driven structure. It is so general it can be used with any map. This is used
 * as follows: suppose you have a <code>Map<String,Thing> map</code> and you
 * want to resolve <code>String name</code>. Then you do this:
 * 
 * <p>
 * <code>ForwardReferenceTool.get(map, name, Thing.class, new ForwardReferenceTool.Replacer<Thing>() { public void replace(Thing value) { _value = value; } });</code>
 * 
 * <p>
 * And suppose you are defining a new <code>Thing value</code> under that
 * name, then you do this:
 * 
 * <p>
 * <code>ForwardReferenceTool.put(map, name, value);</code>
 * 
 * 
 * @author Gunther Schadow
 */
public class ForwardReferenceTool<V>
{

    /**
     * A forward reference object that can be returned from the map in the
     * disguise of an actual object that should be on the map. A caller can tell
     * whether he has received a proper object or a forward reference by testing
     * instanceof Reference.
     */
    public static interface Reference<V>
    {
        void addReplacer(Replacer<V> replacer);

        void replace(V value);
    }

    /**
     * An object that knows how to replace forward references with proper
     * objects once the references are resolved.
     */
    public static interface Replacer<V>
    {
        /**
         * Replaces the forward reference pseudo object with the real object.
         * The implementation of this methods must be sure to reassign all
         * variables that may hold the forward reference object.
         */
        void replace(V value);
    }

    /**
     * A factory to create a replacer only if needed. Avoids creating a bunch of
     * garbage replacers if the references can be resolved right away.
     */
    public static interface ReplacerFactory<V>
    {
        Replacer<V> createReplacer();
    }

    /**
     * Resolve a key in the map, this is guaranteed to return some non-null
     * object that at least looks like an object of the value class.
     * 
     * @return the value if it had been defined or a Reference object in the
     *         disguise of a Value class.
     * @deprecated use the method with the ReplacerFactory instead to avoid
     *             creating all that Replacer garbage
     */
    public static <K, V> V get(Map<K, V> map, K key, Class Vclass, Replacer<V> replacer)
    {
        V result = map.get(key);
        if (result == null)
        {
            Class[] interfaces = new Class[] { Vclass, Reference.class };
            ReferenceHandler referenceHandler = new ReferenceHandler<V>(key.toString());
            result = (V) Proxy.newProxyInstance(Vclass.getClassLoader(), interfaces, referenceHandler);
            map.put(key, result);
        }
        if (result instanceof Reference)
        {
            ((Reference) result).addReplacer(replacer);
        }
        return result;
    }

    /**
     * Resolve a key in the map, this is guaranteed to return some non-null
     * object that at least looks like an object of the value class.
     * 
     * @return the value if it had been defined or a Reference object in the
     *         disguise of a Value class.
     */
    public static <K, V> V get(Map<K, V> map, K key, Class Vclass, ReplacerFactory<V> replacerFactory)
    {
        V result = map.get(key);
        if (result == null)
        {
            Class[] interfaces = new Class[] { Vclass, Reference.class };
            ReferenceHandler referenceHandler = new ReferenceHandler<V>(key.toString());
            result = (V) Proxy.newProxyInstance(Vclass.getClassLoader(), interfaces, referenceHandler);
            map.put(key, result);
        }
        if (result instanceof Reference)
        {
            ((Reference) result).addReplacer(replacerFactory.createReplacer());
        }
        return result;
    }

    public static <K, V> void put(Map<K, V> map, K key, V value)
    {
        V existingEntry = map.get(key);
        if (existingEntry == null || existingEntry instanceof Reference) map.put(key, value);
        else throw new DuplicateKeyException(key);
        // System.out.println(new DuplicateKeyException(key));
        if (existingEntry instanceof Reference) ((Reference) existingEntry).replace(value);
    }

    public static class UnresolvedReferenceException extends RuntimeException
    {
        UnresolvedReferenceException(Method method)
        {
            super(method.toString());
        }
    }

    public static class DuplicateKeyException extends RuntimeException
    {
        DuplicateKeyException(Object key)
        {
            super(key.toString());
        }
    }

    static class ReferenceHandler<V> implements InvocationHandler
    {
        private final Method _addReplacerMethod;
        private final Method _replaceMethod;
        private final List<Replacer<V>> _replacers;
        private final String _name;

        ReferenceHandler(String name)
        {
            try
            {
                _name = name;
                _addReplacerMethod = Reference.class.getMethod("addReplacer", new Class[]
                { Replacer.class });
                _replaceMethod = Reference.class.getMethod("replace", new Class[]
                { Object.class });
                // System.err.println(_addReplacerMethod.toString() +
                // _addReplacerMethod.hashCode());
                // System.err.println(_replaceMethod.toString()+
                // _replaceMethod.hashCode());
            }
            catch (NoSuchMethodException ex)
            {
                throw new Error(ex);
            }
            _replacers = new LinkedList<Replacer<V>>();
        }

        /**
         * @author jmoore
         */
        public String toString()
        {
            return "ForwardReference to " + _name;
        }
        
        public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, UnresolvedReferenceException
        {
            // System.err.println(method.toString() + method.hashCode());
            if (method.equals(_addReplacerMethod))
            {
                _replacers.add((Replacer<V>) args[0]);
                return null;
            }
            else if (method.equals(_replaceMethod))
            {
                for (Replacer<V> replacer : _replacers)
                    replacer.replace((V) args[0]);
                return null;
            }
            else
            {
                try
                {
                    Method defaultMethod = Object.class.getMethod(method.getName(), method.getParameterTypes());
                    return defaultMethod.invoke(this, args);
                }
                catch (NoSuchMethodException ex)
                {
                    throw new UnresolvedReferenceException(method);
                }
                catch (IllegalAccessException ex)
                {
                    throw new UnresolvedReferenceException(method);
                }
            }
        }
    }
}

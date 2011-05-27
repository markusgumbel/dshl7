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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A tool to find a method or constructor on an object that matches a signature.
 * 
 * @author Gunther Schadow
 */
public class ClassMethodTool {
    
    static class Candidate implements Comparable<Candidate> {
        private final Method _method;
        private final Constructor _constructor;
        private final String _name;
        private final Class<?> _parameterTypes[];
        private int _penalty = 0;
        
        Candidate(final Method method) {
            _method = method;
            _constructor = null;
            _name = method.getName();
            _parameterTypes = method.getParameterTypes();
        }
        
        Candidate(final Constructor<?> constructor) {
            _method = null;
            _constructor = constructor;
            _name = null;
            _parameterTypes = constructor.getParameterTypes();
        }
        
        public int compareTo(final Candidate that) {
            return this._penalty - that._penalty;
        }
        
        boolean matches(final Class<?> args[]) {
            return matches(null, args);
        }
        
        boolean matches(final String name, final Class<?> args[]) {
            if (!(_name == name || _name.equals(name))) {
                return false;
            }
            
            if (_parameterTypes.length != args.length) {
                return false;
            }
            
            // now check the types
            for (int argi = 0; argi < _parameterTypes.length; argi++) {
                final Class<?> parameterType = _parameterTypes[argi];
                final Class<?> arg = args[argi];
                
                if (parameterType.equals(arg)) {
                    _penalty += 0;
                } else if (parameterType.isAssignableFrom(arg)) {
                    _penalty += 10;
                    for (Class<?> iface : arg.getInterfaces()) {
                        if (parameterType.equals(iface)) {
                            _penalty -= 10;
                        }
                    }
                } else {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    public static Method getMethod(final Class<?> clazz, final String name,
            final Class<?> args[]) throws NoSuchMethodException {
        final List<Candidate> candidates = new ArrayList<Candidate>();
        
        for (Method method : clazz.getMethods()) {
            Candidate candidate = new Candidate(method);
            if (candidate.matches(name, args))
                candidates.add(candidate);
        }
        
        if (candidates.size() > 0) {
            Collections.sort(candidates);
            return candidates.get(0)._method;
        } else
            throw new NoSuchMethodException(name + Arrays.asList(args));
    }
    
    public static <T> Constructor<T> getConstructor(final Class<T> clazz,
            final Class<?> args[]) throws NoSuchMethodException {
        final List<Candidate> candidates = new ArrayList<Candidate>();
        
        for (Constructor<?> ctor : clazz.getConstructors()) {
            Candidate candidate = new Candidate(ctor);
            if (candidate.matches(args))
                candidates.add(candidate);
        }
        
        if (candidates.size() > 0) {
            Collections.sort(candidates);
            return candidates.get(0)._constructor;
        } else
            throw new NoSuchMethodException("constructor "
                    + Arrays.asList(args));
    }
}

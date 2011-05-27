/** A tool which creates an instance of a proxy class which extends a base class with any number of additional classes in one class. The classes can be interfaces in the definition of the proxy class, but the proxy instance requires implementations of these interfaces. The MixinMaker supplies a general InvocationHandler which resolves method invocations to the base class or mixins in the order in which the mixins were supplied. This means, if a mixin has a property of the same signature as the base, that mixin property will never be accessible. */
package org.hl7.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MixinMaker<T> {
  public static <T> T makeMixin(Class<T> baseInterface, T base, Class mixinInterfaces[], Object... mixins) {
		int partCount = mixins.length + 1;
		Object parts[] = new Object[partCount];
		Class interfaces[] = new Class[partCount];
		parts[0] = base;
		interfaces[0] = baseInterface;
		for(int i = 1 ; i < partCount; i++) {
			parts[i] = mixins[i-1];
			interfaces[i] = mixinInterfaces[i-1];
		}			
		Handler handler = new Handler<T>(parts, interfaces);
		
		return (T)Proxy.newProxyInstance(interfaces[0].getClassLoader(), interfaces, handler);
	}

	static class Handler<T> implements InvocationHandler {
		Class _interfaces[];
		Object _parts[];
		Handler(Object parts[], Class interfaces[]) { 
			_parts = parts;
			_interfaces = interfaces;	
		}
		public Object	invoke(Object proxy, Method method, Object[] args) {
			Class declaringClass = method.getDeclaringClass();
			for(int i = 0; i < _parts.length; i++) {
				if(declaringClass.isAssignableFrom(_interfaces[i])) {
					try {
						return method.invoke(_parts[i], args);
					} catch(IllegalAccessException ex) {
						throw new RuntimeException(ex);
					} catch(InvocationTargetException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
			throw new UnsupportedOperationException(method.toString());
		}
	}
}


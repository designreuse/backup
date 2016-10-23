package com.tresbu.nvidia.common;

import static com.tresbu.nvidia.common.AppConstant.CHARACTER_COMMA;
import static com.tresbu.nvidia.common.AppConstant.CHARACTER_EQUALS;
import static com.tresbu.nvidia.common.AppConstant.CHARACTER_SQUARE_BRACKET_CLOSE;
import static com.tresbu.nvidia.common.AppConstant.CHARACTER_SQUARE_BRACKET_OPEN;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tresbu.nvidia.common.exception.NvidiaException;

public class ReflectionHelper {

	public static Object createInstance(String className) throws NvidiaException {

		return createInstance(className, null, null);
	}

	public static Object createInstance(String className, Class<?>[] paramTypes, Object arglist[]) throws NvidiaException {
		Object classInstance = null;

		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> ct = clazz.getConstructor(paramTypes);
			classInstance = ct.newInstance(arglist);
		} catch (InstantiationException e) {
			throw new NvidiaException(e);
		} catch (IllegalAccessException e) {
			throw new NvidiaException(e);
		} catch (ClassNotFoundException e) {
			throw new NvidiaException(e);
		} catch (SecurityException e) {
			throw new NvidiaException(e);
		} catch (NoSuchMethodException e) {
			throw new NvidiaException(e);
		} catch (IllegalArgumentException e) {
			throw new NvidiaException(e);
		} catch (InvocationTargetException e) {
			throw new NvidiaException(e);
		}

		return classInstance;
	}

	public static void getReadMethods(Class<?> clazz, Map<String, Method> TO_STRING_PROPERTIES) throws NvidiaException {
		PropertyDescriptor[] propertyDescriptors;
		try {
			propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor pd : propertyDescriptors) {
				for (String field : TO_STRING_PROPERTIES.keySet()) {
					if (pd.getReadMethod() != null && field.equals(pd.getName())) {
						TO_STRING_PROPERTIES.put(field, pd.getReadMethod());
					}
				}
			}
		} catch (IntrospectionException e) {
			throw new NvidiaException(e);
		}
	}

	public static void getReadWriteMethods(Class<?> clazz, Map<String, Method[]> TO_STRING_PROPERTIES) throws NvidiaException {
		PropertyDescriptor[] propertyDescriptors;
		try {
			propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor pd : propertyDescriptors) {
				for (String field : TO_STRING_PROPERTIES.keySet()) {
					if (pd.getReadMethod() != null && field.equals(pd.getName())) {
						Method[] readWriteMethods = new Method[2];
						readWriteMethods[0] = pd.getReadMethod();
						readWriteMethods[1] = pd.getWriteMethod();
						TO_STRING_PROPERTIES.put(field, readWriteMethods);
					}
				}
			}
		} catch (IntrospectionException e) {
			throw new NvidiaException(e);
		}
	}

	public static Map<String, Object> invokeReadMethod(Object dataObject, Map<String, Method> readMethods) throws NvidiaException {
		Map<String, Object> values = new HashMap<String, Object>();

		StringBuilder keyValueString = new StringBuilder();

		keyValueString.append(dataObject.getClass().getSimpleName()).append(CHARACTER_SQUARE_BRACKET_OPEN);

		try {
			for (Entry<String, Method> entry : readMethods.entrySet()) {
				values.put(entry.getKey(), entry.getValue().invoke(dataObject));
				keyValueString.append(entry.getKey() + CHARACTER_EQUALS + entry.getValue().invoke(dataObject)).append(CHARACTER_COMMA);
			}
		} catch (IllegalArgumentException e) {
			throw new NvidiaException(e);
		} catch (IllegalAccessException e) {
			throw new NvidiaException(e);
		} catch (InvocationTargetException e) {
			throw new NvidiaException(e);
		}

		int lastComma = keyValueString.lastIndexOf(CHARACTER_COMMA);
		keyValueString.replace(lastComma, lastComma + 2, "");

		keyValueString.append(CHARACTER_SQUARE_BRACKET_CLOSE);

		return values;
	}

}

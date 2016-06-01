package com.sky.projects.apache.common.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean Reflect Util
 * 
 * @author zt
 *
 */
public class BeanReflects {
	private static final Logger logger = LoggerFactory.getLogger(BeanReflects.class);
	private static final Map<String, Field[]> fieldCache = new HashMap<String, Field[]>(50);

	private static final String SETTER_PREFIX = "set";
	private static final String GETTER_PREFIX = "get";

	public static Object invokeGetter(Object obj, String propertyName) {
		String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(propertyName);

		return invokeMethod(obj, getterMethodName, new Class[] {}, new Object[] {});
	}

	public static void invokeSetter(Object obj, String propertyName, Object value) {
		String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(propertyName);

		invokeMethodByName(obj, setterMethodName, new Object[] { value });
	}

	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);

		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			logger.error("Invoker error", e);
		}

		return null;
	}

	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);

		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			logger.error("method invoker error:", e);
		}

		return null;
	}

	public static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {
		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType
				.getSuperclass()) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
			}
		}

		return null;
	}

	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType
				.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}

		return null;
	}

	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * 通过setter 和 getter 反射对象属性的 ，并初始化值
	 * 
	 * @param info
	 */
	public static void reflect(Object info) {
		Field[] fields = fieldCache.get(info.getClass().getCanonicalName());

		if (fields == null) {
			fields = (Field[]) ArrayUtils.addAll(info.getClass().getDeclaredFields(),
					info.getClass().getSuperclass().getDeclaredFields());

			fieldCache.put(info.getClass().getCanonicalName(), fields);
		}

		for (Field field : fields) {
			Object o = invokeGetter(info, field.getName());

			if (o == null) {
				Object parm = new String();

				if (field.getType().getName().equals(Long.class.getName())) {
					parm = new Long(0L);
				} else if (field.getType().getName().equals(String.class.getName())) {
					parm = new String("MULL");
				}

				invokeSetter(info, field.getName(), parm);
			}
		}
	}

}
